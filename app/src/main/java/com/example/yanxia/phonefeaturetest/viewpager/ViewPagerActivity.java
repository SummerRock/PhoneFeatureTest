package com.example.yanxia.phonefeaturetest.viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager.ViewPagerTestAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_activity);
        ViewPager viewPager = findViewById(R.id.test_view_pager);
        viewPager.setAdapter(new ViewPagerTestAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.view_pager_indicator);
        tabLayout.setupWithViewPager(viewPager);
    }
}
