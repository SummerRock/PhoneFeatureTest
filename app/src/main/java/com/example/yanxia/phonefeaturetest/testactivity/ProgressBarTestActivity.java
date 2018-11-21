package com.example.yanxia.phonefeaturetest.testactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.widget.CircleProgressBar;
import com.example.yanxia.phonefeaturetest.widget.ProgressButton;

/**
 * @author yanxia-Mac
 */
public class ProgressBarTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);

        final CircleProgressBar circleProgressBar = findViewById(R.id.custom_progress_bar);
        final ProgressButton progressButton = findViewById(R.id.custom_progress_button);

        SeekBar seekBar = findViewById(R.id.test_seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                circleProgressBar.setProgress(i);
                progressButton.setProgress(i);
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
