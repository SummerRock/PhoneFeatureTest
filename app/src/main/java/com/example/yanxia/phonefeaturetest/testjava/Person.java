package com.example.yanxia.phonefeaturetest.testjava;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private int age;
    private String name;
    private String address;
    private String race;

    public Person(int age, String name, String address, String race) {
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

    protected Person(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
        this.address = in.readString();
        this.race = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
