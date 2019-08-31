package com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;

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
        final RecyclerView recyclerView = rootView.findViewById(R.id.test_fragment_position_rv);
        recyclerView.setAdapter(new ViewPagerTestRvAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                CommonLog.d("onScrolled dx: " + dx +
                        " dy: " + dy +
                        " canScrollLeft: " + recyclerView.canScrollHorizontally(-1) +
                        " canScrollRight: " + recyclerView.canScrollHorizontally(1));
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                CommonLog.d("onScrollStateChanged newState: " + newState +
                        " canScrollLeft: " + recyclerView.canScrollHorizontally(-1) +
                        " canScrollRight: " + recyclerView.canScrollHorizontally(1));
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
