package com.example.yanxia.phonefeaturetest.doublerecyclerview.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;

import java.util.List;

public class DemoChildFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<DemoChild> demoChildList;

    public DemoChildFragmentStatePagerAdapter(FragmentManager fm, @NonNull List<DemoChild> demoChildList) {
        super(fm);
        this.demoChildList = demoChildList;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        return DemoChildFragment.newInstance(demoChildList.get(position));
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return demoChildList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }
}
