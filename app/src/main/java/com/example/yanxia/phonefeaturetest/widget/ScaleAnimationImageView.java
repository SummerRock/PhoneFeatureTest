package com.example.yanxia.phonefeaturetest.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

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
        // setPivotY(1f);
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
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(BACKGROUND_ORIGIN_SCALE, BACKGROUND_EXPAND_SCALE);
            valueAnimator.addUpdateListener(animation -> {
                float progress = animation.getAnimatedFraction();
                setScaleX(progress);
                setScaleY(progress);
            });
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.start();
            expanded = true;
        }
    }

    public void shrinkWithAnimation() {
        if (expanded) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(BACKGROUND_EXPAND_SCALE, BACKGROUND_ORIGIN_SCALE);
            valueAnimator.addUpdateListener(animation -> {
                float progress = animation.getAnimatedFraction();
                setScaleX(progress);
                setScaleY(progress);
            });
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.start();
            expanded = false;
        }
    }
}
