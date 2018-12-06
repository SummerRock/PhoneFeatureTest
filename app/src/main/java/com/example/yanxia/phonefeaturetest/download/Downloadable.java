package com.example.yanxia.phonefeaturetest.download;

public interface Downloadable {
    /**
     * 下载内容名称
     */
    void getDownloadName();

    /**
     * 下载内容类型
     */
    void getDownloadType();

    /**
     * 下载地址
     */
    String getDownloadUrl();

    /**
     * 下载到本地目录
     */
    String getDownloadFileDirectoryPath();

    /**
     * 下载文件临时名称
     */
    String getDownloadFileTempName();

    /**
     * 下载文件名称
     */
    String getDownloadFileName();

    /**
     * 是否已下载
     */
    boolean isDownloaded();
}
