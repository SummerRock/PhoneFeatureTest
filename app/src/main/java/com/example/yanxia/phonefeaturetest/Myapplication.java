package com.example.yanxia.phonefeaturetest;

import android.app.Application;
import android.content.Context;

/**
 * Created by yanxia on 2017/2/10.
 */

public class Myapplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
