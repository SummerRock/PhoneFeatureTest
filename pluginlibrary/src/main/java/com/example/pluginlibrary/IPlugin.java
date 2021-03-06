package com.example.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;

/**
 * 插件Activity的生命周期调用
 */
public interface IPlugin {

    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle savedInstanceState);
}
