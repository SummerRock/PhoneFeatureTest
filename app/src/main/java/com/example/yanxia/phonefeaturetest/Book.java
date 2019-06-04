package com.example.yanxia.phonefeaturetest;


import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/**
 * Book.aidl与Book.java的包名应当是一样的。
 */
public class Book implements Parcelable {

    private String bookName;

    public Book() {
        this("unknown");
    }

    public Book(@NonNull String name) {
        this.bookName = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "book name：" + bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookName);
    }

    public void readFromParcel(Parcel dest) {
        bookName = dest.readString();
    }

    protected Book(Parcel in) {
        this.bookName = in.readString();
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