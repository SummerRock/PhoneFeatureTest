package com.example.yanxia.phonefeaturetest.dataModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class People implements Parcelable {
    private long id;
    private String name;
    private String birthPlace;
    private String race;
    private List<Car> carList;

    public People(long id, @NonNull String name, @NonNull String birthPlace, @NonNull String race) {
        this(id, name, birthPlace, race, new ArrayList<>());
    }

    public People(long id, @NonNull String name, @NonNull String birthPlace, @NonNull String race, @NonNull List<Car> carList) {
        this.id = id;
        this.name = name;
        this.birthPlace = birthPlace;
        this.race = race;
        this.carList = carList;
    }

    public long getId() {
        return id;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.birthPlace);
        dest.writeString(this.race);
        dest.writeTypedList(this.carList);
    }

    protected People(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.birthPlace = in.readString();
        this.race = in.readString();
        this.carList = in.createTypedArrayList(Car.CREATOR);
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
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
