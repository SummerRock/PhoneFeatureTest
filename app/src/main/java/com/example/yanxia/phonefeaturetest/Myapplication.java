package com.example.yanxia.phonefeaturetest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.utils.MultiThreadDemoManager;
import com.example.yanxia.phonefeaturetest.utils.ScreenStatusManager;
import com.example.yanxia.phonefeaturetest.utils.SingletonDemo;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class Myapplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static final String TAG = "Myapplication";

    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.d(TAG, "onActivityStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG, "onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.d(TAG, "onActivityPaused");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.d(TAG, "onActivityStopped");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.d(TAG, "onActivitySaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.d(TAG, "onActivityDestroyed");
        }
    };

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        context = getApplicationContext();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        SingletonDemo.getInstance();
        initThirdService();
        ScreenStatusManager.getInstance();
    }

    public static Context getContext() {
        return context;
    }

    public void initThirdService() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
                Log.d(TAG, "initThirdService start!");
                MultiThreadDemoManager.getInstance();
            }
        }.start();
    }

}
