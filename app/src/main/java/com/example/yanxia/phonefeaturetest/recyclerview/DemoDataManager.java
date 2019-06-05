package com.example.yanxia.phonefeaturetest.recyclerview;

import com.example.yanxia.phonefeaturetest.recyclerview.data.Demo;
import com.example.yanxia.phonefeaturetest.recyclerview.data.DemoGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DemoDataManager {
    private static final DemoDataManager ourInstance = new DemoDataManager();

    public static DemoDataManager getInstance() {
        return ourInstance;
    }


    private List<DemoGroup> demoGroupList = new ArrayList<>();

    private Random random = new Random();
    private int demoData;

    private DemoDataManager() {
        String baseName = "parent";
        for (int i = 0; i < 10; i++) {
            String name = baseName + i;
            int start = demoData;
            demoData = demoData + random.nextInt(5) + 1;
            int end = demoData;
            DemoGroup demoGroup = new DemoGroup(getDemoList(start, end, name), name);
            demoGroupList.add(demoGroup);
        }
    }

    public List<DemoGroup> getDemoGroupList() {
        return new ArrayList<>(demoGroupList);
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
