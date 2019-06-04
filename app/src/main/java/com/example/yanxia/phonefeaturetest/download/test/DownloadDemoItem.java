package com.example.yanxia.phonefeaturetest.download.test;

import com.example.yanxia.phonefeaturetest.download.Downloadable;

public class DownloadDemoItem implements Downloadable {

    private String name;
    private String type;
    private String url;
    private String dirName;
    private String fileName;

    public DownloadDemoItem(String name, String type, String url, String dirName, String fileName) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.dirName = dirName;
        this.fileName = fileName;
    }

    @Override
    public void getDownloadName() {

    }

    @Override
    public void getDownloadType() {

    }

    @Override
    public String getDownloadUrl() {
        return null;
    }

    @Override
    public String getDownloadFileDirectoryPath() {
        return null;
    }

    @Override
    public String getDownloadFileName() {
        return null;
    }

    @Override
    public boolean isDownloaded() {
        return false;
    }
}
