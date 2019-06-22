package com.example.yanxia.phonefeaturetest.doublerecyclerview;

import androidx.annotation.NonNull;

import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;

public interface OnGetCurrentDemoChildListener {
    @NonNull
    DemoChild getCurrentDemoChild();
}
