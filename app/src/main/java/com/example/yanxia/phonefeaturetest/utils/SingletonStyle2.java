package com.example.yanxia.phonefeaturetest.utils;

public class SingletonStyle2 {

    private static class LazyHolder {
        private static final SingletonStyle2 INSTANCE = new SingletonStyle2();
    }

    public static SingletonStyle2 getInstance() {
        return LazyHolder.INSTANCE;
    }

    private SingletonStyle2() {
    }
}
