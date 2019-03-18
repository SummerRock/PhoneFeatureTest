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
import android.util.Pair;

import com.example.yanxia.phonefeaturetest.Myapplication;

import java.util.ArrayList;
import java.util.List;

public class ScreenRecordManager {
    private static final String TAG = "ScreenRecordManager";
    private static ScreenRecordManager ourInstance;
    private CustomHandler handler;
    private final boolean enable = true;

    private static final long HALF_HOUR = DateUtils.MINUTE_IN_MILLIS * 30;
    private static final long ONE_HOUR = DateUtils.HOUR_IN_MILLIS;
    // private static final long THREE_HOUR = DateUtils.HOUR_IN_MILLIS * 3;

    private static final long TWO_HOUR = 2 * ONE_HOUR;
    private static final long TWO_AND_HALF_HOUR = HALF_HOUR + 2 * ONE_HOUR;

    /**
     * 上一次亮屏时间
     */
    private long lastScreenOnTime;
    private List<Pair<Long, Long>> timeRecordList = new ArrayList<>();

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
        Log.i(TAG, "ScreenRecordManager init!");
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
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(MESSAGE_SCREEN_ON_LAST_HALF_HOUR, HALF_HOUR);
        handler.sendEmptyMessageDelayed(MESSAGE_SCREEN_ON_LAST_ONE_HOUR, ONE_HOUR);
        long screenOnTotalTimeTwoAndHalfHourPast = getScreenOnTotalTimeFromTimeRecordList(TWO_AND_HALF_HOUR);
        if (screenOnTotalTimeTwoAndHalfHourPast < HALF_HOUR) {
            handler.sendEmptyMessageDelayed(MESSAGE_SCREEN_ON_TOTAL_HALF_HOUR, HALF_HOUR - screenOnTotalTimeTwoAndHalfHourPast);
        }
        long screenOnTotalTimeTwoHoursPast = getScreenOnTotalTimeFromTimeRecordList(TWO_HOUR);
        if (screenOnTotalTimeTwoHoursPast < ONE_HOUR) {
            handler.sendEmptyMessageDelayed(MESSAGE_SCREEN_ON_TOTAL_ONE_HOUR, ONE_HOUR - screenOnTotalTimeTwoHoursPast);
        }
    }

    private void handleScreenOff() {
        long currentTime = System.currentTimeMillis();
        int seconds = (int) ((currentTime - lastScreenOnTime) / 1000);
        Log.i(TAG, "handleScreenOff, 本次亮屏持续时间: " + seconds + "秒");
        Pair<Long, Long> pair = new Pair<>(lastScreenOnTime, currentTime);
        timeRecordList.add(pair);
        handler.removeCallbacksAndMessages(null);
    }

    private long getScreenOnTotalTimeFromTimeRecordList(long timeInterval) {
        if (timeRecordList.isEmpty()) {
            return 0;
        }
        long minutesAgoTime = System.currentTimeMillis() - timeInterval;
        long total = 0;
        for (Pair<Long, Long> longLongPair : timeRecordList) {
            if (longLongPair.first > minutesAgoTime) {
                total = total + (longLongPair.second - longLongPair.first);
            }
        }
        Log.i(TAG, "屏幕在前" + (timeInterval / 1000) + "秒内亮屏了: " + (total / 1000) + "秒");
        return total;
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
