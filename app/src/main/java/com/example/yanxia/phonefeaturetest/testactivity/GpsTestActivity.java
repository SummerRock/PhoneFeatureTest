package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.caverock.androidsvg.SVGImageView;
import com.example.yanxia.phonefeaturetest.R;

import java.util.Locale;

public class GpsTestActivity extends AppCompatActivity {
    private static final String TAG = "GpsTestActivity";

    private static final String COLOR_ENABLE = "#f55b70";
    private static final String COLOR_DISABLE = "#641D1D";

    private SVGImageView puzzleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_test);

        puzzleImageView = findViewById(R.id.puzzle_svg_image_view);

        long[] dataArray = new long[9];
        dataArray[3] = 2;
        dataArray[5] = 2;
        dataArray[8] = 2;
        String css = getCssStringFromDataArray(dataArray);
        puzzleImageView.setCSS(css);
    }

    private String getCssStringFromDataArray(long[] dataArray) {
        StringBuilder cssString = new StringBuilder();
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i] > 0) {
                String temp = String.format(Locale.getDefault(), ".brick%d { fill:%s; } ", i, COLOR_ENABLE);
                cssString.append(temp);
            } else {
                String temp = String.format(Locale.getDefault(), ".brick%d { fill:%s; } ", i, COLOR_DISABLE);
                cssString.append(temp);
            }
        }
        return cssString.toString();
    }
}
