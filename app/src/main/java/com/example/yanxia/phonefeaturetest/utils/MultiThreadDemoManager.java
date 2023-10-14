package com.example.yanxia.phonefeaturetest.utils;

import android.util.Log;

import com.example.yanxia.phonefeaturetest.callback.CommonCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiThreadDemoManager {
    private static final String TAG = "MultiThreadDemoManager";
    private static volatile MultiThreadDemoManager ourInstance;
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

    private final Random random = new Random();

    public List<String> getStringListWithRandomLength() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < random.nextInt(10) + 15; i++) {
            StringBuilder stringBuilder = new StringBuilder("AA");
            for (int j = 0; j < random.nextInt(22) + 2; j++) {
                stringBuilder.append("B");
            }
            stringList.add(stringBuilder.toString());
        }
        return stringList;
    }
}
