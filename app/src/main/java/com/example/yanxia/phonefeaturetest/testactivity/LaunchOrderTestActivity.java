package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;

/**
 * @author yanxia-Mac
 */
public final class LaunchOrderTestActivity extends AppCompatActivity {
    static {
        CommonLog.d("static field.");
    }

    public LaunchOrderTestActivity() {
        CommonLog.d("construct init!");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CommonLog.d("onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_order);
    }
}
