package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        Button button = findViewById(R.id.test_reset_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressButton.initState();
                progressButton.setText("Wait");
                progressButton.setBackgroundResource(R.drawable.button_bg_selector);
            }
        });
    }
}
