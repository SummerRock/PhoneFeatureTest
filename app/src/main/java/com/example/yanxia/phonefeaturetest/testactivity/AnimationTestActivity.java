package com.example.yanxia.phonefeaturetest.testactivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;
import com.example.yanxia.phonefeaturetest.widget.ScaleAnimationImageView;

public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ScaleAnimationImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test2);
        imageView = findViewById(R.id.animation_test_imageView);
    }

    @Override
    public void onClick(View v) {

    }

    public void startAnimationType1(View view) {
        imageView.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
        animation.setFillAfter(true);
        animation.setDuration(5000);
        animation.setInterpolator(new FastOutSlowInInterpolator());
        imageView.startAnimation(animation);
    }

    public void startAnimationType2(View view) {
        imageView.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, 0.8f, Animation.RELATIVE_TO_PARENT, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(5000);
        animation.setInterpolator(new LinearInterpolator());
        imageView.startAnimation(animation);
    }

    public void startAnimationType3(View view) {
        imageView.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, 200, Animation.ABSOLUTE, 50);
        animation.setFillAfter(true);
        animation.setDuration(5000);
        imageView.startAnimation(animation);
    }

    public void startAnimationType4(View view) {
        imageView.clearAnimation();
        imageView.expandWithAnimation();
    }

    public void startAnimationType5(View view) {
        imageView.clearAnimation();
        imageView.shrinkWithAnimation();
    }

    public void startAnimationType6(View view) {
        int distance = DisplayUtils.dpToPx(80);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.addUpdateListener(animation -> {
            float progress = animation.getAnimatedFraction();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.topMargin = (int) (distance * (1 - progress));
            Log.d("AnimationTest_LOG", "progress: " + progress);
            imageView.setLayoutParams(layoutParams);
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }
}
