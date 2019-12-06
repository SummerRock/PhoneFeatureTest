package com.example.yanxia.phonefeaturetest.testactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.dialog.CustomWidthDialogFragment;

public class AutoFocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_focus);
    }

    public void showLastThing(View view) {
        CustomWidthDialogFragment.newInstance().show(getSupportFragmentManager(), CustomWidthDialogFragment.class.getSimpleName());
    }
}
