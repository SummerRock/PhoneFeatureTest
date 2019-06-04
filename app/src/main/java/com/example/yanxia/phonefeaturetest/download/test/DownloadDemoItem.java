package com.example.yanxia.phonefeaturetest.download.test;

import android.support.annotation.NonNull;

import com.example.yanxia.phonefeaturetest.download.Downloadable;

import java.io.File;

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

    @NonNull
    @Override
    public String getDownloadName() {
        return name;
    }

    @NonNull
    @Override
    public String getDownloadType() {
        return type;
    }

    @NonNull
    @Override
    public String getDownloadUrl() {
        return url;
    }

    @NonNull
    @Override
    public String getDownloadFileDirectoryPath() {
        return dirName;
    }

    @NonNull
    @Override
    public String getDownloadFileName() {
        return fileName;
    }

    @Override
    public boolean isDownloaded() {
        File file = new File(dirName, fileName);
        return file.exists();
    }
}
