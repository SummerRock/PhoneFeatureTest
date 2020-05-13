package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;

import java.util.Arrays;

/**
 * @author yanxia-Mac
 */
public final class ColorMatrixTestActivity extends AppCompatActivity {
    static {
        CommonLog.logAsOne("static field.");
    }

    private ColorMatrix mColorMatrix = new ColorMatrix();
    private ImageView testImageView;
    private TextView saturationText;


    private final int originColor = Color.parseColor("#F2F2F2");
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    public ColorMatrixTestActivity() {
        CommonLog.logAsOne("construct init!");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CommonLog.logAsOne("onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix_test);
        testImageView = findViewById(R.id.color_matrix_test_image);
        saturationText = findViewById(R.id.color_matrix_test_saturation_tv);
        // saturationText.setTextColor(ContextCompat.getColor(this, DisplayUtils.getResourceIdByName(this, "control_background", "color")));
        SeekBar seekBar = findViewById(R.id.color_matrix_adjust_seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                saturationText.setText(String.format(getString(R.string.color_matrix_saturation), String.valueOf(progress)));
                float f = progress / 100f;
                mColorMatrix.setSaturation(f);
                testImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imageView1 = findViewById(R.id.color_image_1);
        imageView2 = findViewById(R.id.color_image_2);
        imageView3 = findViewById(R.id.color_image_3);

        imageView1.setBackgroundColor(originColor);
        imageView2.setBackgroundColor(getDarkSkinColor(originColor));
        imageView3.setBackgroundColor(getNewColor());
    }

    @ColorInt
    public static int getDarkSkinColor(@ColorInt int originColor) {
        final float[] hsv = new float[3];
        Color.colorToHSV(originColor, hsv);
        Log.d("ColorConvert", "origin hsv: " + Arrays.toString(hsv));
        float hue = hsv[0];
        if (hue - 5 < 0) {
            hsv[0] = 360 + hue - 5;
        } else {
            hsv[0] = hue - 5;
        }
        hsv[1] = (hsv[1] + 5f) / 100f;
        hsv[2] = hsv[2] - 0.1f;
        Log.d("ColorConvert", "after hsv: " + Arrays.toString(hsv));
        return Color.HSVToColor(hsv);
    }

    private static int getNewColor() {
        final float[] hsv = new float[3];
        hsv[0] = 0;
        hsv[1] = 0;
        hsv[2] = 0.84f;
        return Color.HSVToColor(hsv);
    }

    public static String converColorIntToHexString(@ColorInt int colorInt) {
        return String.format("#%06X", (0xFFFFFF & colorInt));
    }
}
