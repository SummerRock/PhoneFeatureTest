package com.example.yanxia.phonefeaturetest.testactivity;

import android.support.v7.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.dialog.CustomProgressDialogFragment;

public class BaseLoadingActivity extends AppCompatActivity {

    private CustomProgressDialogFragment customProgressDialogFragment;

    protected void showLoading() {
        if (customProgressDialogFragment == null) {
            customProgressDialogFragment = CustomProgressDialogFragment.newInstance();
        }
        customProgressDialogFragment.show(getSupportFragmentManager(), CustomProgressDialogFragment.class.getSimpleName());
    }

    protected void hideLoading() {
        if (customProgressDialogFragment != null) {
            customProgressDialogFragment.dismiss();
        }
    }
}
