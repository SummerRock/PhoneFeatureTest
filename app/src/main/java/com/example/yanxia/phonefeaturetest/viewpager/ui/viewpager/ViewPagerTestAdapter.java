package com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerTestAdapter extends FragmentStatePagerAdapter {

    private int orientation;
    private int count = 9;

    public ViewPagerTestAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(position, orientation);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "ITEM_" + String.valueOf(position);
    }
}
