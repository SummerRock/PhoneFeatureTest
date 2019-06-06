package com.example.yanxia.phonefeaturetest.doublerecyclerview.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class DemoChild implements Parcelable {
    private final String data;
    private final String parentName;

    public DemoChild(String data, String parentName) {
        this.data = data;
        this.parentName = parentName;
    }

    public String getData() {
        return data;
    }

    public String getParentName() {
        return parentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoChild demoChild = (DemoChild) o;
        return data.equals(demoChild.data) && parentName.equals(demoChild.parentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, parentName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
        dest.writeString(this.parentName);
    }

    protected DemoChild(Parcel in) {
        this.data = in.readString();
        this.parentName = in.readString();
    }

    public static final Parcelable.Creator<DemoChild> CREATOR = new Parcelable.Creator<DemoChild>() {
        @Override
        public DemoChild createFromParcel(Parcel source) {
            return new DemoChild(source);
        }

        @Override
        public DemoChild[] newArray(int size) {
            return new DemoChild[size];
        }
    };
}
