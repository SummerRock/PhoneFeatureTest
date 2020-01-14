package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.dialog.BottomDialogFragment;

public class AutoFocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_focus);
    }

    public void showLastThing(View view) {
        BottomDialogFragment.newInstance().show(getSupportFragmentManager(), BottomDialogFragment.class.getSimpleName());
    }
}
