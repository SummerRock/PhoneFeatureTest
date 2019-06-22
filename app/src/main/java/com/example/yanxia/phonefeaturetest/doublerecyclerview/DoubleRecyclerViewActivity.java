package com.example.yanxia.phonefeaturetest.doublerecyclerview;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoParent;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.fragment.DemoChildFragmentStatePagerAdapter;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.parent.DemoParentAdapter;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class DoubleRecyclerViewActivity extends AppCompatActivity implements OnGetCurrentDemoChildListener {

    private List<DemoParent> demoParentList = DemoDataManager.getInstance().getDemoGroupList();
    private List<DemoChild> demoChildList = new ArrayList<>();

    private ViewPager viewPager;
    private DemoParentAdapter demoParentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler_view);

        for (DemoParent demoParent : demoParentList) {
            demoChildList.addAll(demoParent.getDemoChildList());
        }

        viewPager = findViewById(R.id.view_pager_demo);
        viewPager.setAdapter(new DemoChildFragmentStatePagerAdapter(getSupportFragmentManager(), demoChildList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                demoParentAdapter.changeSelectPosition(demoChildList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_demo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        demoParentAdapter = new DemoParentAdapter(demoParentList, this);
        recyclerView.setAdapter(demoParentAdapter);
        recyclerView.addItemDecoration(new CustomItemDecoration());
    }

    @NonNull
    @Override
    public DemoChild getCurrentDemoChild() {
        return demoChildList.get(viewPager.getCurrentItem());
    }

    private class CustomItemDecoration extends RecyclerView.ItemDecoration {

        private int gap = DisplayUtils.dpToPx(12);
        private int largeGap = DisplayUtils.dpToPx(14);

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            if (parent.getAdapter() == null) {
                outRect.set(gap, 0, gap, 0);
                return;
            }
            int position = parent.getChildAdapterPosition(view);
            if (position == 0) {
                outRect.set(largeGap, 0, gap, 0);
            } else if (position == parent.getAdapter().getItemCount() - 1) {
                outRect.set(gap, 0, largeGap, 0);
            } else {
                outRect.set(gap, 0, gap, 0);
            }
        }
    }
}
