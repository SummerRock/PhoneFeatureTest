package com.example.yanxia.phonefeaturetest.utils;

import android.util.Log;

public class SingletonDemo {
    private static final String TAG = SingletonDemo.class.getSimpleName();
    private static volatile SingletonDemo ourInstance;

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
    }

    public synchronized void function1() {
        Log.d(TAG, "function1 start!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "function1 end!");
    }

    public synchronized void function2() {
        Log.d(TAG, "function2 start!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "function2 end!");
    }
}
