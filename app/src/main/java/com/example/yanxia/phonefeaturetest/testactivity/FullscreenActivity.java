package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

    private static final String PIC_URL = "http://a.hiphotos.baidu.com/image/pic/item/aa18972bd40735faee21b63393510fb30e240862.jpg";

    private ImageView easyDialogTestImage;
    private ImageView imageViewGif;
    private ImageView imageViewSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        imageViewGif = findViewById(R.id.glide_test_image_view_0);
        // Glide.with(this).asGif().load(GIF_2).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).preload();
        ImageView imageView1 = findViewById(R.id.glide_test_image_view_1);
        Glide.with(this).load(PIC_URL).into(imageView1);
        imageViewSmall = findViewById(R.id.glide_test_image_view_2);

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
        View one = view.findViewById(R.id.select_item_1);
        one.setOnClickListener(v -> Log.i("easy", "do something"));
        View two = view.findViewById(R.id.select_item_2);
        two.setOnClickListener(v -> Log.i("easy", "do something 2"));
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

    public void startLoadImage(View view) {
        Glide.with(FullscreenActivity.this).load(PIC_URL).into(imageViewSmall);
    }

    public void startLoadGif(View view) {
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
        }).into(imageViewGif);
    }

    public void clearGifCache(View view) {
        Glide.with(this).clear(imageViewGif);
    }
}
