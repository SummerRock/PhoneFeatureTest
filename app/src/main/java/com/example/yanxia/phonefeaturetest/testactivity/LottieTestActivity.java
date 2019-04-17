package com.example.yanxia.phonefeaturetest.testactivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yanxia.phonefeaturetest.R;

public class LottieTestActivity extends AppCompatActivity {

    private static final String TAG = "Lottie_debug";

    private boolean hasUpdateBitmap;

    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_test);

        lottieAnimationView = findViewById(R.id.test_lottie_1);
        lottieAnimationView.setAnimation("lottie/test/data.json");
        lottieAnimationView.setImageAssetsFolder("lottie/test/images");
        lottieAnimationView.addAnimatorUpdateListener(animation -> {
            float f = animation.getAnimatedFraction();
            Log.i(TAG, "onAnimationUpdate progress: " + f);
            if (f > 0 && !hasUpdateBitmap) {
                lottieAnimationView.updateBitmap("image_3", BitmapFactory.decodeResource(getResources(), R.drawable.test_image_01));
                Log.i(TAG, "updateBitmap.");
                hasUpdateBitmap = true;
            }
        });
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.i(TAG, "onAnimationStart.");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                lottieAnimationView.setMinProgress(0.5f);
                lottieAnimationView.playAnimation();
            }
        });
        lottieAnimationView.postDelayed(() -> lottieAnimationView.playAnimation(), 1000);
    }

    @Override
    protected void onDestroy() {
        lottieAnimationView.removeCallbacks(null);
        lottieAnimationView.cancelAnimation();
        super.onDestroy();
    }
}
