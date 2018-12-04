package com.example.yanxia.phonefeaturetest.testactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

public class DialogStyleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_style);
    }

    @Override
    public void onClick(View v) {

    }

    public void showNormalDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog);
        builder.setTitle("Title").setMessage("Message hello!").setCancelable(false).setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void showCustomDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        builder.setView(dialogView).setTitle("Message hello!").setCancelable(false).setPositiveButton("Got it", (dialog, which) -> dialog.dismiss()).show();
    }
}
