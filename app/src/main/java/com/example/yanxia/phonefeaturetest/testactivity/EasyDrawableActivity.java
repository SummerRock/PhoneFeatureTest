package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;
import com.noober.background.BackgroundLibrary;

public class EasyDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //https://github.com/JavaNoober/BackgroundLibrary
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_drawable);
    }
}
