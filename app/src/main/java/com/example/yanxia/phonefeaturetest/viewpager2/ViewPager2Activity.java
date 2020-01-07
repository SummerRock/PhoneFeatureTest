package com.example.yanxia.phonefeaturetest.viewpager2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.yanxia.phonefeaturetest.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private final List<String> stringList = new ArrayList<>();
    private ViewPager2Adapter viewPager2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        viewPager2 = findViewById(R.id.view_pager_2);
        for (int i = 0; i < 30; i++) {
            stringList.add("number: " + i);
        }
        viewPager2Adapter = new ViewPager2Adapter(stringList, null);
        viewPager2.setAdapter(viewPager2Adapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
    }
}
