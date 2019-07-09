package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yanxia.phonefeaturetest.MyApplication;

public class DisplayUtils {

    public static int getScreenWidthPixels() {
        Resources resources = MyApplication.getContext().getResources();
        return resources.getDisplayMetrics().widthPixels;
    }

    public static int dpToPx(float dp) {
        float density = MyApplication.getContext().getResources().getDisplayMetrics().density;
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

        int resID = context.getResources().getIdentifier(resourceName, type, context.getPackageName());
        if (resID == 0) {
            Log.e("common", "getResourceIdByName wrong! resourceName: " + resourceName);
        }
        return 0;
    }

    public static Bitmap getRoundCornerBitmap(@NonNull Bitmap bitmap, int cornerRadius) {
        float roundPx = dpToPx(cornerRadius);

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap addColorBackgroundForBitmap(@NonNull Bitmap bitmap, @ColorInt int color) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int size = Math.max(width, height);
        Bitmap newBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawColor(color);
        Point canvasCenter = new Point(size / 2, size / 2);
        Point originBitmapCenter = new Point(width / 2, height / 2);
        Matrix matrix = new Matrix();
        matrix.postTranslate(canvasCenter.x - originBitmapCenter.x, canvasCenter.y - originBitmapCenter.y);
        canvas.drawBitmap(bitmap, matrix, paint);
        return newBitmap;
    }

    private static final Canvas CANVAS = new Canvas();

    public static Bitmap createBitmapFromView(@Nullable View view) {
        if (view == null) {
            return null;
        }
        view.clearFocus();
        Bitmap bitmap = createBitmapSafely(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888, 3);
        if (bitmap != null) {
            synchronized (CANVAS) {
                CANVAS.setBitmap(bitmap);
                view.draw(CANVAS);
                CANVAS.setBitmap(null);
            }
        }
        return bitmap;
    }

    private static Bitmap createBitmapSafely(int width, int height, @NonNull Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }
    }
}
