package com.example.loginapp;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.componentlib.IAppInterface;
import com.example.componentlib.ServiceFactory;

public class LoginApplication extends Application implements IAppInterface {

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void initialize(@NonNull Application app) {
        application = app;
        ServiceFactory.getInstance().setiLoginInterface(new LoginService());
    }
}
