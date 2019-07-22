package com.example.pluginlibrary;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {

    //插件Apk的实体对象
    private final DexClassLoader dexClassLoader;
    private final Resources resources;
    private final PackageInfo packageInfo;
    private final AssetManager assetManager;

    public PluginApk(DexClassLoader dexClassLoader, Resources resources, PackageInfo packageInfo, AssetManager assetManager) {
        this.dexClassLoader = dexClassLoader;
        this.resources = resources;
        this.packageInfo = packageInfo;
        this.assetManager = assetManager;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
