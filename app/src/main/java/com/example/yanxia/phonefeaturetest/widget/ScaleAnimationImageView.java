package com.example.yanxia.phonefeaturetest.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

public class ScaleAnimationImageView extends AppCompatImageView {
    private static final float BACKGROUND_ORIGIN_SCALE = 1f;
    private static final float BACKGROUND_EXPAND_SCALE = 2f;
    private boolean expanded;


    public ScaleAnimationImageView(Context context) {
        this(context, null);
    }

    public ScaleAnimationImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleAnimationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPivotY(1f);
    }

    public void expand() {
        if (!expanded) {
            setScaleX(BACKGROUND_EXPAND_SCALE);
            setScaleY(BACKGROUND_EXPAND_SCALE);
            expanded = true;
        }
    }

    public void shrink() {
        if (expanded) {
            setScaleX(BACKGROUND_ORIGIN_SCALE);
            setScaleY(BACKGROUND_ORIGIN_SCALE);
            expanded = false;
        }
    }

    public void expandWithAnimation() {
        if (!expanded) {
            clearAnimation();
            ScaleAnimation animExpand = new ScaleAnimation(BACKGROUND_ORIGIN_SCALE, BACKGROUND_EXPAND_SCALE, BACKGROUND_ORIGIN_SCALE, BACKGROUND_EXPAND_SCALE);
            animExpand.setDuration(300);
            animExpand.setFillAfter(true);
            animExpand.setInterpolator(new LinearInterpolator());
            startAnimation(animExpand);
            expanded = true;
        }
    }

    public void shrinkWithAnimation() {
        if (expanded) {
            clearAnimation();
            ScaleAnimation animShrink = new ScaleAnimation(BACKGROUND_EXPAND_SCALE, BACKGROUND_ORIGIN_SCALE, BACKGROUND_EXPAND_SCALE, BACKGROUND_ORIGIN_SCALE);
            animShrink.setDuration(300);
            animShrink.setFillAfter(true);
            animShrink.setInterpolator(new LinearInterpolator());
            setScaleX(BACKGROUND_ORIGIN_SCALE);
            setScaleY(BACKGROUND_ORIGIN_SCALE);
            startAnimation(animShrink);
            expanded = false;
        }
    }
}
