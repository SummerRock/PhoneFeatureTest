package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.yanxia.phonefeaturetest.MyApplication;

/**
 * This toast utility is better than directly calling {@link Toast#makeText(Context, CharSequence, int)} in two ways:
 * <p>
 * (1) Consecutive calls would result in updated toast text (not new toasts).
 * (2) This utility forces usage of application context to avoid memory leak with activity context caused by an internal
 * bug of {@link Toast}.
 *
 * @see https://groups.google.com/forum/#!topic/android-developers/3i8M6-wAIwM
 */
public class ToastUtils {

    private static Toast sToast;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void showToast(@StringRes int msgResId) {
        showToast(msgResId, Toast.LENGTH_SHORT);
    }

    public static void showToast(@StringRes int msgResId, int length) {
        showToast(MyApplication.getContext().getString(msgResId), length);
    }

    public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(String msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void showToast(String msg, int length) {
        handler.post(() -> {
            if (sToast == null) {
                sToast = Toast.makeText(MyApplication.getContext(), msg, length);
            }
            sToast.setText(msg);
            sToast.show();
        });
    }
}
