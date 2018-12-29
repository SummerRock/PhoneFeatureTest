package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.SingletonDemo;
import com.noober.background.BackgroundLibrary;

import java.util.List;

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
        SingletonDemo.getInstance().updateList();
    }

    public void testTwo(View view) {
        List<String> list = SingletonDemo.getInstance().getList();
        Toast.makeText(this, "item size: " + String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
        list.add("temp new one!");
    }
}
