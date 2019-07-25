package com.example.pluginlibrary;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 代理Activity, 管理插件Activity的生命周期
 */
public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginApk pluginApk;
    private IPlugin mIplugin;

    public static final String EXTRA_KEY_STRING_CLASSNAME = "className";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra(EXTRA_KEY_STRING_CLASSNAME);
        pluginApk = PluginManager.getInstance().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (pluginApk == null) {
            Log.e("===>", "Loading your apk first please");
        }

        try {
            Class<?> clazz = pluginApk.getDexClassLoader().loadClass(mClassName);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                mIplugin = (IPlugin) object;
                mIplugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                mIplugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 插件Activity 应该调用传进来的系统对象
     */
    @Override
    public Resources getResources() {
        return pluginApk != null ? pluginApk.getResources() : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return pluginApk != null ? pluginApk.getAssetManager() : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginApk != null ? pluginApk.getDexClassLoader() : super.getClassLoader();
    }
}
