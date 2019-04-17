package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    }

    @Override
    protected void onDestroy() {
        lottieAnimationView.removeCallbacks(null);
        lottieAnimationView.cancelAnimation();
        super.onDestroy();
    }
}
