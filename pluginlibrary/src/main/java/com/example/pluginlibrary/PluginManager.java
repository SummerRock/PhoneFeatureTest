package com.example.pluginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 创建DexClassLoader加载插件代码
 * 创建Resource加载资源文件
 * 管理插件Activity生命周期
 */
public class PluginManager {
    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    private Context context;

    private PluginApk pluginApk;

    public void init(Context context) {
        this.context = context;
    }

    //加载apk文件

    public void loadApk(String apkPath) {
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return;
        }

        DexClassLoader classLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResources(assetManager);

        pluginApk = new PluginApk(classLoader, resources, packageInfo, assetManager);
    }

    public PluginApk getPluginApk() {
        return pluginApk;
    }

    private Resources createResources(AssetManager assetManager) {
        Resources res = context.getResources();
        return new Resources(assetManager, res.getDisplayMetrics(), res.getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(am, apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = context.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(), null, context.getClassLoader());
    }
}
