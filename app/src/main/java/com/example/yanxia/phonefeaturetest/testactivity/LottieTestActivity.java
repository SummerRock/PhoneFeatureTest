package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yanxia.phonefeaturetest.R;

public class LottieTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_test);

        LottieAnimationView lottieAnimationView = findViewById(R.id.test_lottie_1);
        lottieAnimationView.updateBitmap("image_3", BitmapFactory.decodeResource(getResources(), R.drawable.test_image_01));
        lottieAnimationView.playAnimation();
    }
}
