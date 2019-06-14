package com.example.pluginlibrary;

public class PluginManager {
    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }
}
