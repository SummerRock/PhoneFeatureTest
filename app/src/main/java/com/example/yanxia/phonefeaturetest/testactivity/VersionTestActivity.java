package com.example.yanxia.phonefeaturetest.testactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import com.example.yanxia.phonefeaturetest.R;

public class VersionTestActivity extends BaseActivity {

    private TextView phoneVersionTv = null;
    private TextView phoneBuildTimeTv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        phoneVersionTv = ((TextView) findViewById(R.id.phoneversiontv));
        phoneVersionTv.setText(Build.FINGERPRINT);
        phoneBuildTimeTv = (TextView) findViewById(R.id.phonebuildtimetv);
        Date d = new Date(Build.TIME);
        phoneBuildTimeTv.setText(d.toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setBackgroundTintList(getButtonTintList());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(VersionTestActivity.this);
                dialog.setTitle(R.string.title_activity_version_test);
                dialog.setMessage(R.string.dialogmessage);
                dialog.setCancelable(false);
                dialog.setPositiveButton(R.string.dialogposbtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton(R.string.dialognavbtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private ColorStateList getButtonTintList() {
        //noinspection ConstantConditions
        int normalColor = ContextCompat.getColor(this, R.color.design_default_color_primary);
        return new ColorStateList(
                new int[][]
                        {
                                new int[]{android.R.attr.state_pressed},
                                new int[]{android.R.attr.state_focused},
                                new int[]{android.R.attr.state_activated},
                                new int[]{android.R.attr.state_checked},
                                new int[]{}
                        },
                new int[]
                        {
                                normalColor,
                                normalColor,
                                normalColor,
                                normalColor,
                                normalColor
                        }
        );
    }

}
