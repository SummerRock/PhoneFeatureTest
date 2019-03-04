package com.example.yanxia.phonefeaturetest.utils;

import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtils {

    private static final String TAG = ThreadPoolUtils.class.getSimpleName();

    private static final String THREAD_TAG_POOL = "summer-thread-pool-";

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static final ThreadPoolExecutor sExecutor;

    static {
        sExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, // Initial pool size
                MAXIMUM_POOL_SIZE, // Max pool size, not used as we are providing an unbounded queue to the executor
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                new LinkedBlockingDeque<>(64),
                new ThreadFactory() {
                    private final AtomicInteger mCount = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, THREAD_TAG_POOL + mCount.getAndIncrement());
                        thread.setPriority(Thread.MAX_PRIORITY);
                        return thread;
                    }
                });
    }

    public static void postOnThreadPoolExecutor(Runnable r) {
        sExecutor.execute(r);
    }

    public class CustomDelayRunnable implements Runnable {
        int index;

        public CustomDelayRunnable(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            Log.d("CustomDelayRunnable", "index: " + index);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
