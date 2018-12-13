package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.yanxia.phonefeaturetest.R;

public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

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
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
        animation.setFillAfter(true);
        animation.setDuration(360);
        animation.setInterpolator(new FastOutSlowInInterpolator());
        imageView.startAnimation(animation);
    }

    public void startAnimationType2(View view) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animation.setFillAfter(true);
        animation.setDuration(360);
        animation.setInterpolator(new FastOutSlowInInterpolator());
        imageView.startAnimation(animation);
    }
}
