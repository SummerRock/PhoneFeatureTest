package com.example.yanxia.phonefeaturetest.recyclerview.data;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class DemoGroup {
    private List<Demo> demoList;
    private final String groupName;

    public DemoGroup(@NonNull List<Demo> demoList, String groupName) {
        this.demoList = demoList;
        this.groupName = groupName;
    }

    public List<Demo> getDemoList() {
        return demoList;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoGroup demoGroup = (DemoGroup) o;
        return groupName.equals(demoGroup.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName);
    }
}
