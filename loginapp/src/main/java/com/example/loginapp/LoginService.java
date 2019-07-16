package com.example.loginapp;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.componentlib.ILoginInterface;
import com.example.loginapp.ui.login.LoginActivity;

public class LoginService implements ILoginInterface {

    @Override
    public void launch(@NonNull Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
