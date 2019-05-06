package com.example.yanxia.phonefeaturetest.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private final long productDate;
    private final String brand;
    /**
     * 车牌号
     */
    private final String licenseNumber;

    public Car(long productDate, String brand, String licenseNumber) {
        this.productDate = productDate;
        this.brand = brand;
        this.licenseNumber = licenseNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public long getProductDate() {
        return productDate;
    }

    public void run() {
        System.out.println("car is running!");
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.productDate);
        dest.writeString(this.brand);
        dest.writeString(this.licenseNumber);
    }

    protected Car(Parcel in) {
        this.productDate = in.readLong();
        this.brand = in.readString();
        this.licenseNumber = in.readString();
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}