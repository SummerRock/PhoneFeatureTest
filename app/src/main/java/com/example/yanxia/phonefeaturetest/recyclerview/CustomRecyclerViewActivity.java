package com.example.yanxia.phonefeaturetest.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.recyclerview.data.Demo;
import com.example.yanxia.phonefeaturetest.recyclerview.data.DemoGroup;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler_view);
    }

    private List<DemoGroup> getData() {
        List<DemoGroup> demoGroupList = new ArrayList<>();
        DemoGroup demoGroupA = new DemoGroup(getDemoList(0, 1, "demoGroupA"), "demoGroupA");
        DemoGroup demoGroupB = new DemoGroup(getDemoList(1, 2, "demoGroupB"), "demoGroupB");
        DemoGroup demoGroupC = new DemoGroup(getDemoList(2, 5, "demoGroupC"), "demoGroupC");
        demoGroupList.add(demoGroupA);
        demoGroupList.add(demoGroupB);
        demoGroupList.add(demoGroupC);
        return demoGroupList;
    }

    private List<Demo> getDemoList(int start, int end, String groupName) {
        List<Demo> demoList = new ArrayList<>();
        if (end <= start) {
            throw new IllegalArgumentException("wrong!");
        } else {
            for (int i = start; i < end; i++) {
                demoList.add(new Demo(String.valueOf(i), groupName));
            }
        }
        return demoList;
    }
}
