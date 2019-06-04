package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;

import java.util.Locale;

/**
 * @author yanxia-Mac
 * https://blog.csdn.net/lmj623565791/article/details/47079737
 */
public class HandlerThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvServiceInfo;

    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    private volatile boolean isUpdateInfo;

    private static final int MSG_UPDATE_INFO = 0x110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTvServiceInfo = findViewById(R.id.ht_text_view);
        //创建后台线程
        initBackThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);

    }

    @Override
    public void onClick(View v) {

    }

    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isUpdateInfo) {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };
    }

    /**
     * 模拟从服务器解析数据
     */
    private void checkForUpdate() {
        //模拟耗时
        SystemClock.sleep(2000);
        runOnUiThread(() -> {
            String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
            result = String.format(Locale.getDefault(), result, (int) (Math.random() * 3000 + 1000));
            mTvServiceInfo.setText(Html.fromHtml(result));
        });
    }

    public void quitThread(View view) {
        mCheckMsgThread.quit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mCheckMsgThread.quit();
    }
}
