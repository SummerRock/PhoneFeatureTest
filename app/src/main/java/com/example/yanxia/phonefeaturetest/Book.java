package com.example.yanxia.phonefeaturetest;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Book.aidl与Book.java的包名应当是一样的。
 */
public class Book implements Parcelable {

    private String name;

    public Book() {
        this("unknown");
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "book name：" + name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel dest) {
        name = dest.readString();
    }

    protected Book(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}