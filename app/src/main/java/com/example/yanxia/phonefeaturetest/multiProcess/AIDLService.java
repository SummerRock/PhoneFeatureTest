package com.example.yanxia.phonefeaturetest.multiProcess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.Book;
import com.example.yanxia.phonefeaturetest.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：leavesC
 * 时间：2017/8/26 0:07
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class AIDLService extends Service {

    public static final String TAG = "AIDL_test_log";

    private List<Book> bookList = new ArrayList<>();

    public AIDLService() {
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

    private final IBookManager.Stub stub = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() {
            return bookList;
        }

        @Override
        public void addBookInOut(Book book) {
            if (book != null) {
                Log.i(TAG, "addBookInOut 服务端接收的书名: " + book.getBookName());
                book.setBookName(book.getBookName() + "_Server");
                bookList.add(book);
            } else {
                Log.e(TAG, "addBookInOut 接收到了一个空对象");
            }
        }

        @Override
        public void addBookIn(Book book) {
            if (book != null) {
                Log.i(TAG, "addBookIn 服务端接收的书名: " + book.getBookName());
                book.setBookName(book.getBookName() + "_Server");
                bookList.add(book);
            } else {
                Log.e(TAG, "addBookIn 接收到了一个空对象");
            }
        }

        @Override
        public void addBookOut(Book book) {
            if (book != null) {
                Log.i(TAG, "addBookOut 服务端接收的书名：" + book.getBookName());
                book.setBookName(book.getBookName() + "_Server");
                bookList.add(book);
            } else {
                Log.e(TAG, "addBookOut 接收到了一个空对象");
            }
        }

    };

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

}
