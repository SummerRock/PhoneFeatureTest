package com.example.yanxia.phonefeaturetest.utils;

import android.content.res.Resources;

import com.example.yanxia.phonefeaturetest.Myapplication;

public class DisplayUtils {

    public static int getScreenWidthPixels() {
        Resources resources = Myapplication.getContext().getResources();
        return resources.getDisplayMetrics().widthPixels;
    }

    public static int dpToPx(float dp) {
        float density = Myapplication.getContext().getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
