package com.example.yanxia.phonefeaturetest.download;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloadManager {
    private volatile static DownloadManager instance;

    private ThreadPoolExecutor poolExecutor;

    public static DownloadManager getInstance() {
        if (instance == null) {
            synchronized (DownloadManager.class) {
                if (instance == null) {
                    instance = new DownloadManager();
                }
            }
        }
        return instance;
    }

    private DownloadManager() {
        poolExecutor = new ThreadPoolExecutor(3, 5,
                1, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(128));
    }
}
