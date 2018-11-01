package com.example.yanxia.phonefeaturetest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class Myapplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
