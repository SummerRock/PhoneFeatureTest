package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;
import com.example.yanxia.phonefeaturetest.widget.EasyDialog;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String GIF_URL = "http://img.soogif.com/nCQ8lWATbFgLakRNMQhMMwPqT3GVRk0K.gif";
    private static final String GIF_2 = "https://dev-appcloudbox.s3.amazonaws.com/050/gif_test/LuckyDraw_3MB.gif";

    private static final String PIC_URL = "http://a.hiphotos.baidu.com/image/pic/item/aa18972bd40735faee21b63393510fb30e240862.jpg";

    private Button button;

    private ImageView easyDialogTestImage;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        imageView = findViewById(R.id.glide_test_image_view_0);
        Glide.with(this).asGif().load(GIF_URL).preload();
        ImageView imageView1 = findViewById(R.id.glide_test_image_view_1);
        Glide.with(this).load(PIC_URL).into(imageView1);
        ImageView imageView2 = findViewById(R.id.glide_test_image_view_2);

        button = findViewById(R.id.glide_test_button);
        button.setOnClickListener(v -> Glide.with(FullscreenActivity.this).load(PIC_URL).into(imageView2));

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
                .setDialogLocationAuto(easyDialogTestImage)
                .setMarginLeftAndRight(0, DisplayUtils.dpToPx(20))
                .setGravity(EasyDialog.GRAVITY_BOTTOM)
                .setMatchParent(false)
                .setOutsideColor(ContextCompat.getColor(this, R.color.black_70_transparent))
                .setAnimationAlphaShow(200, 0f, 1.0f)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    public void loadAgain(View view) {
        Glide.with(this).asGif().load(GIF_URL).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                Log.e("GlideError", "GlideException: " + e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                if (resource != null) {
                    resource.setLoopCount(1);
                }
                return false;
            }
        }).into(imageView);
    }
}
