package com.example.yanxia.phonefeaturetest.testjava;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class People implements Parcelable {
    private int age;
    private String name;
    private String address;
    private String race;

    public People(int age, @NonNull String name, @NonNull String address, @NonNull String race) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.race = race;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.race);
    }

    protected People(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
        this.address = in.readString();
        this.race = in.readString();
    }

    public static final Parcelable.Creator<People> CREATOR = new Parcelable.Creator<People>() {
        @Override
        public People createFromParcel(Parcel source) {
            return new People(source);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };
}
