package com.example.yanxia.phonefeaturetest.viewpager;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

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
