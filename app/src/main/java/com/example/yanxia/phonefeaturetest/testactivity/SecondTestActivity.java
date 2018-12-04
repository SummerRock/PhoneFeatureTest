package com.example.yanxia.phonefeaturetest.testactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

public class SecondTestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onClick(View v) {

    }

    public void setOK(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }

    public void setFail(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
