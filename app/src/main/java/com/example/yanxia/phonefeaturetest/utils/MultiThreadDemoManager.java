package com.example.yanxia.phonefeaturetest.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.callback.CommonCallBack;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadDemoManager {
    private static final String TAG = "MultiThreadDemoManager";
    private static MultiThreadDemoManager ourInstance;
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<CommonCallBack> commonCallBackList = new ArrayList<>();

    public static MultiThreadDemoManager getInstance() {
        if (ourInstance == null) {
            synchronized (MultiThreadDemoManager.class) {
                if (ourInstance == null) {
                    ourInstance = new MultiThreadDemoManager();
                }
            }
        }
        return ourInstance;
    }

    private MultiThreadDemoManager() {
        Log.i(TAG, "init!");
    }

    public void addListener(CommonCallBack commonCallBack) {
        commonCallBackList.add(commonCallBack);
    }

    public void removeListener(CommonCallBack commonCallBack) {
        commonCallBackList.remove(commonCallBack);
    }

    public void notifyList() {
        for (CommonCallBack commonCallBack : commonCallBackList) {
            commonCallBack.onResult(true);
        }
    }
}
