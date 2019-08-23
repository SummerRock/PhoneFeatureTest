package com.example.butterknifelibrary;

import android.app.Activity;

public class ButterKnife {

    public static void bind(Activity activity) {
        String className = activity.getClass().getName() + "$$ViewBinder";
        try {
            //如果确实有这个类
            Class<?> viewBindClass = Class.forName(className);
            ViewBinder viewBinder = (ViewBinder) viewBindClass.newInstance();
            viewBinder.bind(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
