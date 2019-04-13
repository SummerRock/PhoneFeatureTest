package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.annotation.BindView;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

/**
 * @author yanxia-Mac
 */
public final class ColorMatrixTestActivity extends AppCompatActivity {
    static {
        CommonLog.d("static field.");
    }

    private ColorMatrix mColorMatrix = new ColorMatrix();
    private ImageView testImageView;
    @BindView(R.id.color_matrix_test_saturation_tv)
    private TextView saturationText;

    public ColorMatrixTestActivity() {
        CommonLog.d("construct init!");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CommonLog.d("onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix_test);
        testImageView = findViewById(R.id.color_matrix_test_image);
        saturationText = findViewById(R.id.color_matrix_test_saturation_tv);
        saturationText.setTextColor(ContextCompat.getColor(this, DisplayUtils.getResourceIdByName(this, "control_background", "color")));
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
    }
}
