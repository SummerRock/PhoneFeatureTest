package com.example.yanxia.phonefeaturetest.multiProcess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.dataModel.Book;
import com.example.yanxia.phonefeaturetest.IBookManager;
import com.example.yanxia.phonefeaturetest.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者：leavesC
 * 时间：2017/8/26 0:07
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class AIDLService extends Service {

    public static final String TAG = "AIDL_test_log";

    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();
    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    public AIDLService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed.set(true);
        super.onDestroy();
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

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) {
            mListenerList.register(listener);

            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) {
            boolean success = mListenerList.unregister(listener);

            if (success) {
                Log.d(TAG, "unregister success.");
            } else {
                Log.d(TAG, "not found, can not unregister.");
            }
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);
        }

        ;
    };

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            // do background processing here.....
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // SystemClock.sleep(5000);
                int bookId = bookList.size() + 1;
                Book newBook = new Book("new_book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        bookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

}
