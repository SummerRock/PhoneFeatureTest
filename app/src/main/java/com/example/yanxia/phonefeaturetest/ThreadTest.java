package com.example.yanxia.phonefeaturetest;

import android.os.Process;
import android.util.Log;

public class ThreadTest {

    private static final String TAG = "ThreadTest";
    private static final int COUNT = 1000;

    public static void startThreadPriorityTest() {
        Thread threadOne = new Thread() {
            @Override
            public void run() {
                super.run();
                Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
                for (int i = 0; i < COUNT; i++) {
                    Log.i(TAG, "name: " + getName() + " count:" + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadOne.setName("threadOne");

        Thread threadTwo = new Thread() {
            @Override
            public void run() {
                super.run();
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                for (int i = 0; i < COUNT; i++) {
                    Log.i(TAG, "name: " + getName() + " count:" + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadTwo.setName("threadTwo");

        threadOne.start();
        threadTwo.start();
    }
}
