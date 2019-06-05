// BookController.aidl
package com.example.yanxia.phonefeaturetest;

import com.example.yanxia.phonefeaturetest.dataModel.Book;
import com.example.yanxia.phonefeaturetest.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements 引入其他aidl文件

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();

    void addBookInOut(inout Book book);

    void addBookIn(in Book book);

    void addBookOut(out Book book);

    void registerListener(IOnNewBookArrivedListener listener);

    void unregisterListener(IOnNewBookArrivedListener listener);
}
