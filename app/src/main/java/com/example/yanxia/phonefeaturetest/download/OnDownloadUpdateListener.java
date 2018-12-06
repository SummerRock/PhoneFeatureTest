package com.example.yanxia.phonefeaturetest.download;

public interface OnDownloadUpdateListener {
    void onDownloadStart(Downloadable downloadItem);

    void onDownloadProgressUpdate(Downloadable downloadItem, float percent);

    void onDownloadSuccess(Downloadable downloadItem, long downloadTime);

    void onDownloadFailure(Downloadable downloadItem);
}
