package com.example.yanxia.phonefeaturetest.doublerecyclerview.data;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class DemoParent {
    private List<DemoChild> demoList;
    private final String name;

    public DemoParent(@NonNull List<DemoChild> demoList, String name) {
        this.demoList = demoList;
        this.name = name;
    }

    public List<DemoChild> getDemoList() {
        return demoList;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoParent demoGroup = (DemoParent) o;
        return name.equals(demoGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
