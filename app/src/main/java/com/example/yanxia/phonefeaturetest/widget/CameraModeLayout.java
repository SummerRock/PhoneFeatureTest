package com.example.yanxia.phonefeaturetest.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;


public class CameraModeLayout extends FrameLayout {

    protected static final int MODE_PHOTO = 0;
    protected static final int MODE_VIDEO = 1;
    private ShapeDrawable cameraModeButtonIndicatorDrawable;

    public CameraModeLayout(Context context) {
        super(context);
    }

    public CameraModeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraModeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_camera_mode, this, true);
        cameraModeButtonIndicatorDrawable = new ShapeDrawable(new RoundRectShape());
        DrawableCompat.setTint(cameraModeButtonIndicatorDrawable, Color.WHITE);
        cameraModeButtonIndicatorDrawable.setBounds(0, 0, DisplayUtils.dpToPx(4), DisplayUtils.dpToPx(4));
    }
}
