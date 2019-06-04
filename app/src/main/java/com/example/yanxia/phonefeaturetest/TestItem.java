package com.example.yanxia.phonefeaturetest;

import androidx.annotation.Nullable;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class TestItem {
    private String name;
    private int imageId;
    private Class activityClass;

    public TestItem(String name, int imageId) {
        this(name, imageId, null);
    }

    public TestItem(String name, int imageId, @Nullable Class activityClass) {
        this.name = name;
        this.imageId = imageId;
        this.activityClass = activityClass;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    @Nullable
    public Class getActivityClass() {
        return activityClass;
    }
}
