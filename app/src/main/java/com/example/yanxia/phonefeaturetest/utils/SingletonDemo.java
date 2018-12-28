package com.example.yanxia.phonefeaturetest.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingletonDemo {
    private static final String TAG = SingletonDemo.class.getSimpleName();
    private static volatile SingletonDemo ourInstance;
    private List<String> stringList;
    private volatile boolean isUpdating;

    public static SingletonDemo getInstance() {
        if (ourInstance == null) {
            synchronized (SingletonDemo.class) {
                if (ourInstance == null) {
                    ourInstance = new SingletonDemo();
                }
            }
        }
        return ourInstance;
    }

    private SingletonDemo() {
        Log.d(TAG, "init!");
        stringList = new ArrayList<>();
    }

    public void updateList() {
        if (isUpdating) {
            Log.d(TAG, "updateList is update! return!");
            return;
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                Log.d(TAG, "updateList start!");
                isUpdating = true;
                List<String> tempList = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    tempList.add("test item " + String.valueOf(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stringList.clear();
                stringList.addAll(tempList);
                Log.d(TAG, "updateList end!");
                isUpdating = false;
            }
        }.start();
    }

    public List<String> getList() {
        if (isUpdating) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(stringList);
        }
    }
}
