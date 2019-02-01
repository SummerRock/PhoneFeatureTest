package com.example.yanxia.phonefeaturetest.testactivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.testjava.Person;

public class SecondTestActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA = "extra";

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        person = getIntent().getParcelableExtra(EXTRA);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    public void setOK(View view) {
        person.setAge(30);
        setResult(Activity.RESULT_OK);
        finish();
    }

    public void setFail(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    static void setStatusBarColor(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(statusColor);

        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

}
