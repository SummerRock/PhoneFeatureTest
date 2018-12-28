package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.SingletonDemo;
import com.noober.background.BackgroundLibrary;

public class EasyDrawableActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //https://github.com/JavaNoober/BackgroundLibrary
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_drawable);
    }

    @Override
    public void onClick(View v) {

    }

    public void testOne(View view) {
        SingletonDemo.getInstance().function1();
    }

    public void testTwo(View view) {
        SingletonDemo.getInstance().function2();
    }
}
