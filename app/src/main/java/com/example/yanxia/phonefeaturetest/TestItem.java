package com.example.yanxia.phonefeaturetest;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class TestItem {
    private String name;

    private int imageId;

    public TestItem(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
