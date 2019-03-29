package com.example.yanxia.phonefeaturetest.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingletonDemo {
    public interface OnDataLoadedInterface {
        void onDataLoadFinish(@NonNull List<String> dataList);
    }

    private static final String TAG = SingletonDemo.class.getSimpleName();
    private static volatile SingletonDemo ourInstance;
    private List<String> stringList;
    private volatile boolean isUpdating;
    private Random random = new Random();
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<OnDataLoadedInterface> dataLoadedInterfaceList;

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
        dataLoadedInterfaceList = new ArrayList<>();
        updateList();
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
                int length = random.nextInt(4) + 3;
                Log.d(TAG, "updateList length: " + length);
                for (int i = 0; i < length; i++) {
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
                handler.post(() -> {
                    for (OnDataLoadedInterface dataLoadedInterface : dataLoadedInterfaceList) {
                        dataLoadedInterface.onDataLoadFinish(new ArrayList<>(stringList));
                    }
                });
            }
        }.start();
    }

    public List<String> getList() {
        return new ArrayList<>(stringList);
    }
}
