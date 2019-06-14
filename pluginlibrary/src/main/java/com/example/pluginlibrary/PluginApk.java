package com.example.pluginlibrary;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {

    //插件Apk的实体对象
    public DexClassLoader dexClassLoader;
    public Resources resources;
    public PackageInfo packageInfo;
    public AssetManager assetManager;

    public PluginApk(DexClassLoader dexClassLoader, Resources resources, PackageInfo packageInfo, AssetManager assetManager) {
        this.dexClassLoader = dexClassLoader;
        this.resources = resources;
        this.packageInfo = packageInfo;
        this.assetManager = assetManager;
    }
}
