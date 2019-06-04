package com.example.yanxia.phonefeaturetest.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import androidx.percentlayout.widget.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

public class CustomRelativeLayout extends RelativeLayout implements ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = CustomRelativeLayout.class.getSimpleName();
    private ValueAnimator animExpand, animShrink;
    private boolean expanded;

    private long lastAnimationTime;

    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initAnimators() {
        if (null == animExpand || null == animShrink) {
            PercentRelativeLayout.LayoutParams params = (PercentRelativeLayout.LayoutParams) getLayoutParams();
            float expandSize = params.getPercentLayoutInfo().heightPercent * 1.2f;
            float normalSize = params.getPercentLayoutInfo().heightPercent;
            if (null == animExpand) {
                animExpand = ValueAnimator.ofFloat(normalSize, expandSize);
                animExpand.setDuration(100);
                animExpand.setInterpolator(new LinearInterpolator());
                animExpand.addUpdateListener(this);
            }
            if (null == animShrink) {
                animShrink = ValueAnimator.ofFloat(expandSize, normalSize);
                animShrink.setDuration(100);
                animShrink.setInterpolator(new LinearInterpolator());
                animShrink.addUpdateListener(this);
            }
        }
    }

    public void expand() {
        if (!expanded) {
            initAnimators();
            if (isAnimationRunning()) {
                return;
            }
            animExpand.start();
            expanded = true;
        }
    }

    public void shrink() {
        if (expanded) {
            initAnimators();
            if (isAnimationRunning()) {
                return;
            }
            animShrink.start();
            expanded = false;
        }
    }

    public boolean isAnimationRunning() {
        if (System.currentTimeMillis() - lastAnimationTime < 30) {
            return true;
        }
        if (animExpand != null && animExpand.isRunning()) {
            return true;
        }
        if (animShrink != null && animShrink.isRunning()) {
            return true;
        }
        return false;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        PercentRelativeLayout.LayoutParams params = (PercentRelativeLayout.LayoutParams) getLayoutParams();
        params.getPercentLayoutInfo().heightPercent = (float) animation.getAnimatedValue();
        requestLayout();
        lastAnimationTime = System.currentTimeMillis();
    }
}
