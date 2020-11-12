package com.example.yanxia.phonefeaturetest.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager.ViewPagerTestAdapter;
import com.google.android.material.tabs.TabLayout;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_activity);
        ViewPager viewPager = findViewById(R.id.test_view_pager);
        viewPager.setAdapter(new ViewPagerTestAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.view_pager_indicator);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            TextView textView = tab.getCustomView().findViewById(R.id.tab_text);
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);//第一个tab被选中
            }
            textView.setText(viewPager.getAdapter().getPageTitle(i));//设置tab上的文字
        }

        Log.i("CustomViewGroup", "onCreate");

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                Log.i("CustomViewGroup", "getDecorView post hasWindowFocus: " + hasWindowFocus());
            }
        });
        getWindow().getDecorView().getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.i("CustomViewGroup", "getDecorView onDraw!!!");
            }
        });
        getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                Log.i("CustomViewGroup", "getDecorView onPreDraw!!!");
                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("CustomViewGroup", "onWindowFocusChanged: " + hasFocus + " hasWindowFocus: " + hasWindowFocus());
    }
}
