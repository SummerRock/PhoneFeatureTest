package com.example.yanxia.phonefeaturetest.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.Log;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public class GraphicUtils {

    private static final String TAG = "GraphicUtils";

    @NonNull
    public static Bitmap createBitmapFromPicture(@NonNull Picture source, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (source.getWidth() != width || source.getHeight() != height) {
            canvas.scale(width / (float) source.getWidth(), height / (float) source.getHeight());
        }
        canvas.drawPicture(source);
        canvas.setBitmap(null);
        return bitmap;
    }

    public static String convertColorIntToHexString(@ColorInt int colorInt) {
        String hexColor = String.format("#%06X", (0xFFFFFF & colorInt));
        Log.d(TAG, "convertColorIntToHexString: " + hexColor);
        return hexColor;
    }
}
