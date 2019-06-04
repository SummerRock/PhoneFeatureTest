package com.example.yanxia.phonefeaturetest.download;

import androidx.annotation.NonNull;

public interface Downloadable {
    /**
     * 下载内容名称
     */
    @NonNull
    String getDownloadName();

    /**
     * 下载内容类型
     */
    @NonNull
    String getDownloadType();

    /**
     * 下载地址
     */
    @NonNull
    String getDownloadUrl();

    /**
     * 下载到本地目录
     */
    @NonNull
    String getDownloadFileDirectoryPath();

    /**
     * 下载文件名称
     */
    @NonNull
    String getDownloadFileName();

    /**
     * 是否已下载
     */
    boolean isDownloaded();

    boolean equals(Object obj);
}
