package com.example.pluginlibrary;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 代理Activity, 管理插件Activity的生命周期
 */
public class ProxyActivity extends AppCompatActivity {

    private String mClassName;
    private PluginApk pluginApk;
    private IPlugin mIplugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        pluginApk = PluginManager.getInstance().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (pluginApk == null) {
            Log.e("===>", "Loading your apk first please");
        }

        try {
            Class<?> clazz = pluginApk.dexClassLoader.loadClass(mClassName);
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
        return pluginApk != null ? pluginApk.resources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return pluginApk != null ? pluginApk.assetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginApk != null ? pluginApk.dexClassLoader : super.getClassLoader();
    }
}
