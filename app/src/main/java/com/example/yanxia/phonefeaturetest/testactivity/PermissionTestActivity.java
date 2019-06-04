package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.PermissionUtils;

public class PermissionTestActivity extends AppCompatActivity {

    private static final String TAG = "Lottie_debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
        Button button = findViewById(R.id.permission_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.verifyStoragePermissions(PermissionTestActivity.this);
            }
        });
    }
}
