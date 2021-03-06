package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager.ViewPagerTestAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

public class ScrollingCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_custom);

        ViewPager viewPager = findViewById(R.id.view_pager_scroll);
        ViewPagerTestAdapter viewPagerTestAdapter = new ViewPagerTestAdapter(getSupportFragmentManager());
        viewPagerTestAdapter.setOrientation(LinearLayoutManager.VERTICAL);
        viewPagerTestAdapter.setCount(3);
        viewPager.setAdapter(viewPagerTestAdapter);

        TabLayout tabLayout = findViewById(R.id.vp_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                Log.i("tab_test", "i= " + i);
            }
        });
    }
}
