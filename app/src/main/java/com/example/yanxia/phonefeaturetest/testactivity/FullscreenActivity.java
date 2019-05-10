package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.callback.CommonCallBack;
import com.example.yanxia.phonefeaturetest.utils.MultiThreadDemoManager;
import com.example.yanxia.phonefeaturetest.widget.EasyDialog;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener, CommonCallBack {

    private static final String GIF_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545740863490&di=e9db5a7b321cd18615fe15ba166c24e0&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161113%2Fbec7f33085a9449182d2b92c2160288c_th.gif";

    private static final String PIC_URL = "http://a.hiphotos.baidu.com/image/pic/item/aa18972bd40735faee21b63393510fb30e240862.jpg";

    private Button button;

    private ImageView easyDialogTestImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ImageView imageView = findViewById(R.id.glide_test_image_view_0);
        Glide.with(this).load(GIF_URL).into(imageView);
        ImageView imageView1 = findViewById(R.id.glide_test_image_view_1);
        // Glide.with(this).load(PIC_URL).apply(RequestOptions.bitmapTransform(new RoundedCorners(DisplayUtils.dpToPx(10)))).into(imageView1);
        Glide.with(this).load(PIC_URL).into(imageView1);
        ImageView imageView2 = findViewById(R.id.glide_test_image_view_2);

        button = findViewById(R.id.glide_test_button);
        button.setOnClickListener(v -> Glide.with(FullscreenActivity.this).load(PIC_URL).into(imageView2));

        MultiThreadDemoManager.getInstance().addListener(this);

        easyDialogTestImage = findViewById(R.id.easy_dialog_test);
        easyDialogTestImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEasyDialog();
            }
        });
    }

    private void showEasyDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.test_select_items, null);
        EasyDialog easyDialog = new EasyDialog(this)
                .setLayout(view)
                .setBackgroundColor(Color.WHITE)
                //point in screen
                .setDialogLocationAuto(easyDialogTestImage)
                .setGravity(EasyDialog.GRAVITY_BOTTOM)
                .setMatchParent(false)
                .setOutsideColor(ContextCompat.getColor(this, R.color.black_70_transparent))
                .setAnimationAlphaShow(200, 0f, 1.0f)
                .show();
    }

    @Override
    protected void onDestroy() {
        MultiThreadDemoManager.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResult(boolean success) {
        button.setText("EE");
    }

    public void notifyList(View view) {
        MultiThreadDemoManager.getInstance().notifyList();
    }
}
