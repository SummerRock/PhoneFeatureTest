package com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerTestAdapter extends FragmentStatePagerAdapter {

    public ViewPagerTestAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 9;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "ITEM_" + String.valueOf(position);
    }
}
