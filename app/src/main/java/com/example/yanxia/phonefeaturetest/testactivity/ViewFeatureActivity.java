package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.yanxia.phonefeaturetest.R;

public class ViewFeatureActivity extends AppCompatActivity {

    private ImageView imageView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feature);

        imageView = findViewById(R.id.imageView_01);
        seekBar = findViewById(R.id.seekBar_01);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float f = progress / 100f;
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
    }
}
