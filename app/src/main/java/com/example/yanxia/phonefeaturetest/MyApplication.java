package com.example.yanxia.phonefeaturetest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.example.componentlib.AppConfig;
import com.example.componentlib.IAppInterface;
import com.example.yanxia.phonefeaturetest.utils.MultiThreadDemoManager;
import com.example.yanxia.phonefeaturetest.utils.ScreenStatusManager;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static final String TAG = "MyApplication";

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
        MultiDex.install(this);
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        context = getApplicationContext();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        // HandlerThreadSingletonDemo.getInstance();
        initThirdService();
        ScreenStatusManager.getInstance();

        init();

        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }

    private void init() {
        for (String component : AppConfig.COMPNENTS) {
            try {
                Class<?> clazz = Class.forName(component);
                Object object = clazz.newInstance();
                if (object instanceof IAppInterface) {
                    ((IAppInterface) object).initialize(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    private static String searchProcessName(Context context) {
        String processName = null;

        try {
            File file = new File("/proc/" + Process.myPid() + "/cmdline");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            processName = bufferedReader.readLine().trim();
            bufferedReader.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        if (TextUtils.isEmpty(processName)) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
            if (processInfos != null) {
                int pid = Process.myPid();
                Iterator iterator = processInfos.iterator();

                while (iterator.hasNext()) {
                    ActivityManager.RunningAppProcessInfo appProcess = (ActivityManager.RunningAppProcessInfo) iterator.next();
                    if (appProcess.pid == pid) {
                        processName = appProcess.processName;
                        break;
                    }
                }
            }
        }

        return processName;
    }
}
