package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

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

    public static int getResourceIdByName(@NonNull Context context, String resourceName, String type) {
        if (TextUtils.isEmpty(resourceName)) {
            return 0;
        }

        if (resourceName.contains(".")) {
            int dotPosition = resourceName.lastIndexOf(".");
            resourceName = resourceName.substring(0, dotPosition);
        }

        try {
            return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("common", "getResourceIdByName wrong! resourceName: " + resourceName);
        return 0;
    }
}
