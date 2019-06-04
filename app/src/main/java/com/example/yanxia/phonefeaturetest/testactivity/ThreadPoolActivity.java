package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池必须认真学习
 * https://blog.csdn.net/u012702547/article/details/52259529
 * https://juejin.im/post/5a743c526fb9a063557d7eba
 */

public class ThreadPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ThreadPoolActivity_LOG";

    private ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
    private ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    private int testNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
    }

    @Override
    public void onClick(View v) {

    }

    private class TestRunnableAddDirectly implements Runnable {
        private long sleepTime;

        TestRunnableAddDirectly(long sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            if (sleepTime != 0) {
                SystemClock.sleep(sleepTime);
            }
            addValueSync();
        }
    }

    private void addValueAsync() {
        testNumber++;
        String name = Thread.currentThread().getName();
        Log.d(TAG, name + " testNumber: " + testNumber);
    }

    private synchronized void addValueSync() {
        testNumber++;
        String name = Thread.currentThread().getName();
        Log.d(TAG, name + " testNumber: " + testNumber);
    }

    public void startSingleThreadPool(View view) {
        testNumber = 0;
        for (int i = 0; i < 30; i++) {
            newSingleThreadExecutor.execute(new TestRunnableAddDirectly(500));
        }
    }

    public void startNewCachedThreadPool(View view) {
        testNumber = 0;
        for (int i = 0; i < 30; i++) {
            newCachedThreadPool.execute(new TestRunnableAddDirectly(500));
        }
    }

    public void startNewFixedThreadPool(View view) {
        testNumber = 0;
        for (int i = 0; i < 30; i++) {
            newFixedThreadPool.execute(new TestRunnableAddDirectly(500));
        }
    }

    public void startScheduledThreadPool(View view) {
        testNumber = 0;
        Log.d(TAG, "scheduledExecutorService start!");
        for (int i = 0; i < 20; i++) {
            scheduledExecutorService.schedule(new TestRunnableAddDirectly(300), 1000, TimeUnit.MILLISECONDS);
        }
    }
}
