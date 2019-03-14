package com.example.yanxia.phonefeaturetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RemoteDemoService extends Service {

    public static final String TAG = "RemoteDemoLogTag";
    // For bind service
    private final IBinder mBinder = new ServiceBinder();

    private List<Book> bookList = new ArrayList<>();

    public RemoteDemoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        Book book1 = new Book("位置");
        Book book2 = new Book("牌面");
        Book book3 = new Book("情商");
        Book book4 = new Book("圈子");
        Book book5 = new Book("态度");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * class use to return service instance
     */
    public class ServiceBinder extends Binder {
        /**
         * get RemoteDemoService service instance
         *
         * @return service instance
         */
        public RemoteDemoService getService() {
            return RemoteDemoService.this;
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBookInOut(Book book) {
        if (book != null) {
            Log.i(TAG, "addBookInOut 服务端接收的书名: " + book.getBookName());
            book.setBookName(book.getBookName() + "_Server");
            bookList.add(book);
        } else {
            Log.e(TAG, "addBookInOut 接收到了一个空对象");
        }
    }

    public void addBookIn(Book book) {
        if (book != null) {
            Log.i(TAG, "addBookIn 服务端接收的书名: " + book.getBookName());
            book.setBookName(book.getBookName() + "_Server");
            bookList.add(book);
        } else {
            Log.e(TAG, "addBookIn 接收到了一个空对象");
        }
    }

    public void addBookOut(Book book) {
        if (book != null) {
            Log.i(TAG, "addBookOut 服务端接收的书名：" + book.getBookName());
            book.setBookName(book.getBookName() + "_Server");
            bookList.add(book);
        } else {
            Log.e(TAG, "addBookOut 接收到了一个空对象");
        }
    }
}
