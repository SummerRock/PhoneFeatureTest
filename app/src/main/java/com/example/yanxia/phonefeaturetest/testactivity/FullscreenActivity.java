package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yanxia.phonefeaturetest.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private static final String GIF_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545740863490&di=e9db5a7b321cd18615fe15ba166c24e0&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161113%2Fbec7f33085a9449182d2b92c2160288c_th.gif";

    private static final String PIC_URL = "http://a.hiphotos.baidu.com/image/pic/item/aa18972bd40735faee21b63393510fb30e240862.jpg";

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

        Button button = findViewById(R.id.glide_test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(FullscreenActivity.this).load(PIC_URL).into(imageView2);
            }
        });
    }

}
