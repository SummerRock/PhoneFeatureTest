package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

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
}
