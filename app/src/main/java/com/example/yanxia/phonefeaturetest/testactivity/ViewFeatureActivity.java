package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;

public class ViewFeatureActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView autoSizeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feature);

        imageView = findViewById(R.id.imageView_01);
        imageView.post(() -> imageView.setPivotX(imageView.getWidth() / 2f));
        imageView.setPivotY(1);
        SeekBar seekBar = findViewById(R.id.seekBar_01);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float f = 1 + progress / 100f;
                imageView.setScaleX(f);
                imageView.setScaleY(f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        autoSizeTextView = findViewById(R.id.auto_size_test_tv);
        //autoSizeTextView的 layout_width 不能是 wrap_content
        SeekBar seekBar2 = findViewById(R.id.seekBar_02);
        int baseHeight = autoSizeTextView.getLayoutParams().height;
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float progressFloat = progress / 50f;
                autoSizeTextView.getLayoutParams().height = (int) (baseHeight + baseHeight * progressFloat);
                autoSizeTextView.requestLayout();
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
