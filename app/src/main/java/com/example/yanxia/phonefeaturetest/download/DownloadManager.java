package com.example.yanxia.phonefeaturetest.download;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadManager {
    private static final String TAG = "DownloadManager";
    private volatile static DownloadManager instance;

    private ThreadPoolExecutor poolExecutor;
    private OkHttpClient client;

    private List<Downloadable> waitingForDownloadList = new ArrayList<>();
    private Map<Downloadable, Call> downloadingItems;
    private Map<Downloadable, List<OnDownloadUpdateListener>> downloadItemListenerMap;
    private List<OnDownloadUpdateListener> globalListeners;

    private Handler handler;

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

        client = new OkHttpClient();

        handler = new Handler(Looper.getMainLooper());

        downloadingItems = new HashMap<>();
        downloadItemListenerMap = new HashMap<>();
        globalListeners = new ArrayList<>();
    }

    public void downloadItem(@NonNull Downloadable downloadable) {
        Log.d(TAG, "downloadable url: " + downloadable.getDownloadUrl());
        if (downloadable.isDownloaded()) {
            return;
        }

        if (isWaitingForDownload(downloadable)) {
            return;
        }

        waitingForDownloadList.add(downloadable);

        if (!downloadingItems.keySet().contains(downloadable)) {
            Runnable runnable = () -> {
                Request request = new Request.Builder().url(downloadable.getDownloadUrl()).build();
                Call call = client.newCall(request);

                handler.post(() -> {
                    waitingForDownloadList.remove(downloadable);
                    downloadingItems.put(downloadable, call);
                });
                notifyDownloadStart(downloadable);

                final long startTime = SystemClock.elapsedRealtime();
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse start response: " + response.message());
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len;
                        FileOutputStream fos = null;

                        //储存下载文件的目录
                        File dir = new File(downloadable.getDownloadFileDirectoryPath());
                        if (!dir.exists()) {
                            if (!dir.mkdirs()) {
                                notifyDownloadFailed(downloadable, new RuntimeException("mkdirs failed!"));
                                return;
                            }
                        }
                        File file = new File(dir, downloadable.getDownloadFileName());

                        try {
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            fos = new FileOutputStream(file);
                            long sum = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                float progress = (sum * 1.0f / total * 100);
                                notifyDownloadProgress(downloadable, progress);
                            }
                            fos.flush();
                            //下载完成
                            long downloadTime = SystemClock.elapsedRealtime() - startTime;
                            notifyDownloadSuccess(downloadable, downloadTime);
                        } catch (Exception e) {
                            notifyDownloadFailed(downloadable, e);
                        } finally {
                            try {
                                if (is != null) {
                                    is.close();
                                }
                                if (fos != null) {
                                    fos.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        notifyDownloadFailed(downloadable, new RuntimeException("response is failed!"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    notifyDownloadFailed(downloadable, e);
                }
            };
            poolExecutor.execute(runnable);
        }
    }

    public boolean isWaitingForDownload(@NonNull Downloadable downloadable) {
        return waitingForDownloadList.contains(downloadable);
    }

    private synchronized void notifyDownloadStart(@NonNull Downloadable downloadable) {

    }

    private synchronized void notifyDownloadProgress(@NonNull Downloadable downloadable, float progress) {

    }

    private synchronized void notifyDownloadSuccess(@NonNull Downloadable downloadable, long downloadTime) {

    }

    private synchronized void notifyDownloadFailed(@NonNull Downloadable downloadable, @NonNull Exception e) {

    }
}
