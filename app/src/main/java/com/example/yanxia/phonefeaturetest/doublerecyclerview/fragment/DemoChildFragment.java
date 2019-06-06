package com.example.yanxia.phonefeaturetest.doublerecyclerview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;

public class DemoChildFragment extends Fragment {

    private static final String KEY_DATA = "key_data";

    private DemoChild demoChild;

    public static DemoChildFragment newInstance(DemoChild demoChild) {
        DemoChildFragment viewPagerFragment = new DemoChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_DATA, demoChild);
        viewPagerFragment.setArguments(args);
        return viewPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demoChild = getArguments().getParcelable(KEY_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.demo_child_fragment, container, false);
        TextView data = rootView.findViewById(R.id.demo_child_fragment_tv);
        data.setText(demoChild.getData());
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
