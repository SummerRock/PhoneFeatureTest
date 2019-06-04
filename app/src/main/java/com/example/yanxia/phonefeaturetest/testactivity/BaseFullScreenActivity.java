package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class BaseFullScreenActivity extends AppCompatActivity {
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final int BAR_SHOW_TIME = 1000;
    protected boolean hideSystemUiImmediately = false;
    private boolean hideSystemUiWhenSwitching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
    }

    @Override
    protected void onDestroy() {
        mainHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                mainHandler.postDelayed(this::hideSystemUI, BAR_SHOW_TIME);
            } else {
                mainHandler.removeCallbacksAndMessages(null);
            }
        });
        // restore default settings
        hideSystemUiWhenSwitching = false;
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        // Shows the system bars by removing all the flags
        // except for the ones that make the content appear under the system bars.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mainHandler.removeCallbacksAndMessages(null);
        if (hasFocus) {
            if (!hideSystemUiImmediately) {
                mainHandler.postDelayed(() -> hideSystemUI(), BAR_SHOW_TIME);
            } else {
                hideSystemUI();
            }
        } else {
            if (!hideSystemUiWhenSwitching) {
                showSystemUI();
            }
        }
    }

    public void hideSystemUIWhenLoseFocus() {
        hideSystemUiWhenSwitching = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(null);
    }

}
