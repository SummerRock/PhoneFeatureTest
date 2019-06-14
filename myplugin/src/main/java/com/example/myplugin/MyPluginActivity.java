package com.example.myplugin;

import android.os.Bundle;

import com.example.pluginlibrary.PluginActivity;

public class MyPluginActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plugin);
    }
}
