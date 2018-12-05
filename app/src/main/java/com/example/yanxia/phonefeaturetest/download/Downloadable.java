package com.example.yanxia.phonefeaturetest.download;

public interface Downloadable {
    void getDownloadName();

    void getDownloadType();

    String getDownloadUrl();

    String getDownloadFilePath();

    String getDownloadTempFilePath();

    boolean isDownloaded();
}
