package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class EventBusTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(List<String> list) {
        Toast.makeText(this, "size: " + list.size(), Toast.LENGTH_SHORT).show();
    }
}
