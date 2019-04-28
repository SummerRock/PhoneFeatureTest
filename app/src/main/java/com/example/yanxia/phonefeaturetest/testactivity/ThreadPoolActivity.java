package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.common.CommonFinishInterface;
import com.example.yanxia.phonefeaturetest.utils.ThreadPoolUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池必须认真学习
 * https://blog.csdn.net/u012702547/article/details/52259529
 * https://juejin.im/post/5a743c526fb9a063557d7eba
 */

public class ThreadPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
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
        @Override
        public void run() {
            testNumber++;
            Log.d("ThreadPoolActivity_LOG", "testNumber: " + testNumber);
        }
    }

    public void startSingleThreadPool(View view) {
        for (int i = 0; i < 30; i++) {
            newSingleThreadExecutor.execute(new TestRunnableAddDirectly());
        }
    }

    public void startThreadPriorityTest(View view) {
        final AtomicInteger mCount = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            ThreadPoolUtils.postOnThreadPoolExecutor(new ThreadPoolUtils.CustomDelayRunnable(i, new CommonFinishInterface() {
                @Override
                public void onSuccess() {
                    mCount.getAndIncrement();
                    Log.d("ThreadPoolActivity_LOG", "onSuccess size: " + mCount.intValue());
                }

                @Override
                public void onFailed(@Nullable Exception e) {

                }
            }));
        }
    }
}
