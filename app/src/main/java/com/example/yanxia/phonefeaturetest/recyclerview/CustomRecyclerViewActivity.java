package com.example.yanxia.phonefeaturetest.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.recyclerview.data.Demo;
import com.example.yanxia.phonefeaturetest.recyclerview.data.DemoGroup;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler_view);

        recyclerView = findViewById(R.id.recycler_view_demo);
    }

    private List<DemoGroup> getData() {
        List<DemoGroup> demoGroupList = new ArrayList<>();
        DemoGroup parentA = new DemoGroup(getDemoList(0, 1, "parentA"), "parentA");
        DemoGroup parentB = new DemoGroup(getDemoList(1, 2, "parentB"), "parentB");
        DemoGroup parentC = new DemoGroup(getDemoList(2, 5, "parentC"), "parentC");
        DemoGroup parentD = new DemoGroup(getDemoList(6, 10, "parentD"), "parentD");
        DemoGroup parentE = new DemoGroup(getDemoList(11, 13, "parentE"), "parentE");
        DemoGroup parentF = new DemoGroup(getDemoList(13, 14, "parentF"), "parentF");
        demoGroupList.add(parentA);
        demoGroupList.add(parentB);
        demoGroupList.add(parentC);
        demoGroupList.add(parentD);
        demoGroupList.add(parentE);
        demoGroupList.add(parentF);
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
