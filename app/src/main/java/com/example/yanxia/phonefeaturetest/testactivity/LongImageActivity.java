package com.example.yanxia.phonefeaturetest.testactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.widget.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

public class LongImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image);

        LargeImageView largeImageView = findViewById(R.id.long_image_view);
        InputStream inputStream;
        try {
            inputStream = getAssets().open("long_image.jpg");
            largeImageView.setImage(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
