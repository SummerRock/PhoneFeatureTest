package com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;

public class ViewPagerFragment extends Fragment {

    private static final String KEY_GROUP_POSITION = "group_position";

    private int groupPosition;

    public static ViewPagerFragment newInstance(int position) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_GROUP_POSITION, position);
        viewPagerFragment.setArguments(args);
        return viewPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupPosition = getArguments().getInt(KEY_GROUP_POSITION, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_pager_fragment, container, false);
        TextView textView = rootView.findViewById(R.id.test_fragment_position_tv);
        textView.setText("position: " + String.valueOf(groupPosition));
        RecyclerView recyclerView = rootView.findViewById(R.id.test_fragment_position_rv);
        recyclerView.setAdapter(new ViewPagerTestRvAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
