package com.example.yanxia.phonefeaturetest.doublerecyclerview;

import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoParent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DemoDataManager {
    private static final DemoDataManager ourInstance = new DemoDataManager();

    public static DemoDataManager getInstance() {
        return ourInstance;
    }


    private List<DemoParent> demoGroupList = new ArrayList<>();

    private Random random = new Random();
    private int demoData;

    private DemoDataManager() {
        String baseName = "Pa_";
        for (int i = 0; i < 10; i++) {
            char ch = (char) (i + 65);
            String name = baseName + ch;
            int start = demoData;
            int range;
            if (i % 2 == 0) {
                range = 1;
            } else {
                range = 4;
            }
            demoData = demoData + range;
            int end = demoData;
            DemoParent demoGroup = new DemoParent(getDemoList(start, end, name), name);
            demoGroupList.add(demoGroup);
        }
    }

    public List<DemoParent> getDemoGroupList() {
        return new ArrayList<>(demoGroupList);
    }

    private List<DemoChild> getDemoList(int start, int end, String groupName) {
        List<DemoChild> demoList = new ArrayList<>();
        if (end <= start) {
            throw new IllegalArgumentException("wrong!");
        } else {
            for (int i = start; i < end; i++) {
                demoList.add(new DemoChild("Ch_" + i, groupName));
            }
        }
        return demoList;
    }
}
