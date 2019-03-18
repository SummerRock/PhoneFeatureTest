package com.example.yanxia.phonefeaturetest.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.Myapplication;

public class ScreenRecordManager {
    private static final String TAG = "ScreenRecordManager";
    private static ScreenRecordManager ourInstance;
    private CustomHandler handler;
    private final boolean enable = true;

    private static final long HALF_HOUR = DateUtils.MINUTE_IN_MILLIS * 30;
    private static final long ONE_HOUR = DateUtils.HOUR_IN_MILLIS;
    private static final long THREE_HOUR = DateUtils.HOUR_IN_MILLIS * 3;

    /**
     * 上一次亮屏时间
     */
    private long lastScreenOnTime;

    private static final int MESSAGE_SCREEN_ON_LAST_HALF_HOUR = 0;
    private static final int MESSAGE_SCREEN_ON_LAST_ONE_HOUR = 1;
    /**
     * N小时内，累计亮屏时间超过M分钟
     */
    private static final int MESSAGE_SCREEN_ON_TOTAL_HALF_HOUR = 2;
    private static final int MESSAGE_SCREEN_ON_TOTAL_ONE_HOUR = 3;

    @SuppressLint("HandlerLeak")
    private class CustomHandler extends Handler {
        CustomHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_SCREEN_ON_LAST_HALF_HOUR:
                    notifyScreenOnLastHalfHour();
                    break;
                case MESSAGE_SCREEN_ON_LAST_ONE_HOUR:
                    notifyScreenOnLastOneHour();
                    break;
                case MESSAGE_SCREEN_ON_TOTAL_HALF_HOUR:
                    notifyScreenOnTotalTimeHalfHour();
                    break;
                case MESSAGE_SCREEN_ON_TOTAL_ONE_HOUR:
                    notifyScreenOnTotalTimeOneHour();
                    break;
                default:
                    break;
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static ScreenRecordManager getInstance() {
        if (ourInstance == null) {
            synchronized (SingletonDemo.class) {
                if (ourInstance == null) {
                    ourInstance = new ScreenRecordManager();
                }
            }
        }
        return ourInstance;
    }

    private boolean isScreenManagerEnable() {
        return enable;
    }

    private ScreenRecordManager() {
        handler = new CustomHandler();
        lastScreenOnTime = System.currentTimeMillis();
        if (isScreenManagerEnable()) {

            final IntentFilter screenFilter = new IntentFilter();
            screenFilter.addAction(Intent.ACTION_SCREEN_OFF);
            screenFilter.addAction(Intent.ACTION_SCREEN_ON);

            Myapplication.getContext().registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), Intent.ACTION_SCREEN_OFF)) {
                        handleScreenOff();
                    } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_SCREEN_ON)) {
                        handleScreenOn();
                    }
                }
            }, screenFilter);
        }
    }

    private void handleScreenOn() {
        Log.i(TAG, "handleScreenOn");
        lastScreenOnTime = System.currentTimeMillis();
        handler.removeMessages(MESSAGE_SCREEN_ON_LAST_HALF_HOUR);
        handler.removeMessages(MESSAGE_SCREEN_ON_LAST_ONE_HOUR);
        handler.sendEmptyMessageDelayed(MESSAGE_SCREEN_ON_LAST_HALF_HOUR, HALF_HOUR);
        handler.sendEmptyMessageDelayed(MESSAGE_SCREEN_ON_LAST_ONE_HOUR, ONE_HOUR);
    }

    private void handleScreenOff() {
        Log.i(TAG, "handleScreenOff");
        handler.removeMessages(MESSAGE_SCREEN_ON_LAST_HALF_HOUR);
        handler.removeMessages(MESSAGE_SCREEN_ON_LAST_ONE_HOUR);
    }

    private void notifyScreenOnLastHalfHour() {
        Log.i(TAG, "屏幕持续亮了30分钟！");
    }

    private void notifyScreenOnLastOneHour() {
        Log.i(TAG, "屏幕持续亮了1小时！");
    }

    private void notifyScreenOnTotalTimeHalfHour() {
        Log.i(TAG, "3小时内，累计亮屏时间超过30分钟");
    }

    private void notifyScreenOnTotalTimeOneHour() {
        Log.i(TAG, "3小时内，累计亮屏时间超过1小时");
    }
}
