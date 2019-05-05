package com.example.yanxia.phonefeaturetest.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Process;

import com.example.yanxia.phonefeaturetest.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class ScreenStatusManager {

    public interface OnScreenStateChangeListener {
        void onScreenOn();

        void onScreenOff();
    }

    private static ScreenStatusManager ourInstance;

    private List<OnScreenStateChangeListener> screenStateChangeListeners;

    public static ScreenStatusManager getInstance() {
        if (ourInstance == null) {
            synchronized (ScreenStatusManager.class) {
                if (ourInstance == null) {
                    ourInstance = new ScreenStatusManager();
                }
            }
        }
        return ourInstance;
    }

    private ScreenStatusManager() {
        screenStateChangeListeners = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                super.run();
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

                final IntentFilter screenFilter = new IntentFilter();
                screenFilter.addAction(Intent.ACTION_SCREEN_OFF);
                screenFilter.addAction(Intent.ACTION_SCREEN_ON);

                MyApplication.getContext().registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Runnable runnable = () -> {
                            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                                notifyScreenOff();
                            } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
                                notifyScreenOn();
                            }
                        };
                        new Handler().post(runnable);
                    }
                }, screenFilter);
            }
        }.start();
    }

    public void addListener(OnScreenStateChangeListener listener) {
        screenStateChangeListeners.add(listener);
    }

    public void removeListener(OnScreenStateChangeListener listener) {
        screenStateChangeListeners.remove(listener);
    }

    private void notifyScreenOn() {
        for (OnScreenStateChangeListener listener : screenStateChangeListeners) {
            listener.onScreenOn();
        }
    }

    private void notifyScreenOff() {
        for (OnScreenStateChangeListener listener : screenStateChangeListeners) {
            listener.onScreenOff();
        }
    }
}
