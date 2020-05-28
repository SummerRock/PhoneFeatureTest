package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.horizonRv.FlexTestAdapter;
import com.example.yanxia.phonefeaturetest.horizonRv.RecyclerViewItemClickInterface;
import com.example.yanxia.phonefeaturetest.utils.MultiThreadDemoManager;
import com.example.yanxia.phonefeaturetest.utils.PermissionUtils;
import com.example.yanxia.phonefeaturetest.utils.ToastUtils;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

public class PermissionTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
        Button button = findViewById(R.id.permission_button);
        button.setOnClickListener(v -> PermissionUtils.verifyStoragePermissions(PermissionTestActivity.this));

        RecyclerView recyclerView = findViewById(R.id.horizontal_flex_rv);
        recyclerView.setLayoutManager(new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP));
        recyclerView.setAdapter(new FlexTestAdapter(MultiThreadDemoManager.getInstance()
                .getStringListWithRandomLength(), new RecyclerViewItemClickInterface() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showToast("点击了" + position);
            }
        }));
    }
}
