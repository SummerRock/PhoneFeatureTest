package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.common.CommonFinishInterface;
import com.example.yanxia.phonefeaturetest.utils.ThreadPoolUtils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池必须认真学习
 * https://blog.csdn.net/u012702547/article/details/52259529
 * https://juejin.im/post/5a743c526fb9a063557d7eba
 */

public class ThreadPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private ThreadPoolExecutor poolExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
        poolExecutor = new ThreadPoolExecutor(3, 5,
                1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(128));
    }

    @Override
    public void onClick(View v) {

    }

    public void startThreadPool(View view) {
        for (int i = 0; i < 30; i++) {
            final int finalInt = i;
            Runnable runnable = () -> {
                SystemClock.sleep(2000);
                Log.d("ThreadPoolActivity_LOG", "run: " + finalInt);
            };
            poolExecutor.execute(runnable);
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
