package com.example.yanxia.phonefeaturetest;

import com.tencent.mrs.plugin.IDynamicConfig;

public class DynamicConfigImplDemo implements IDynamicConfig {

    public DynamicConfigImplDemo() {
    }

    public boolean isFPSEnable() {
        return true;
    }

    public boolean isTraceEnable() {
        return true;
    }

    public boolean isMatrixEnable() {
        return true;
    }

    public boolean isDumpHprof() {
        return false;
    }

    @Override
    public String get(String key, String defStr) {
        return null;
    }

    @Override
    public int get(String key, int defInt) {
        return 0;
    }

    @Override
    public long get(String key, long defLong) {
        return 0;
    }

    @Override
    public boolean get(String key, boolean defBool) {
        return false;
    }

    @Override
    public float get(String key, float defFloat) {
        return 0;
    }
}
