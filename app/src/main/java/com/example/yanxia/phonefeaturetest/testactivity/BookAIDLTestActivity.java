package com.example.yanxia.phonefeaturetest.testactivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.multiProcess.AIDLService;
import com.example.yanxia.phonefeaturetest.Book;
import com.example.yanxia.phonefeaturetest.IBookManager;
import com.example.yanxia.phonefeaturetest.R;

import java.util.List;

/**
 * AIDL中的定向 tag 表示了在跨进程通信中数据的流向，其中 in 表示数据只能由客户端流向服务端， out 表示数据只能由服务端流向客户端
 * 而 inout 则表示数据可在服务端与客户端之间双向流通。其中，数据流向是针对在客户端中的那个传入方法的对象而言的,
 * in 为定向 tag 的话表现为服务端将会接收到一个那个对象的完整数据，但是客户端的那个对象不会因为服务端对传参的修改而发生变动,
 * out 的话表现为服务端将会接收到那个对象的的空对象，但是在服务端对接收到的空对象有任何修改之后客户端将会同步变动;
 * inout 为定向 tag 的情况下，服务端将会接收到客户端传来对象的完整信息，并且客户端将会同步服务端对该对象的任何变动。
 * <p>
 * 作者：lypeer
 * 链接：https://www.jianshu.com/p/ddbb40c7a251
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class BookAIDLTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BookAIDLTestActivity";

    // State variables
    private boolean mIsServiceStarted = false;

    private boolean mIsServiceBinded = false;

    private IBookManager mIRemoteService;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = IBookManager.Stub.asInterface(service);
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            mIRemoteService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_aidltest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (null == startService(new Intent(BookAIDLTestActivity.this, AIDLService.class))) {
            Log.e(TAG, "onStart, cannot start BOOK service");
            return;
        }
        mIsServiceStarted = true;
        mIsServiceBinded = bindService(new Intent(BookAIDLTestActivity.this, AIDLService.class), mConnection, Context.BIND_AUTO_CREATE);

        if (!mIsServiceBinded) {
            Log.e(TAG, "onStart, cannot bind FM service");
            finish();
        }
    }

    @Override
    protected void onStop() {
        exitService();
        super.onStop();
    }

    /**
     * Exit FM service
     */
    private void exitService() {
        if (mIsServiceBinded) {
            unbindService(mConnection);
            mIsServiceBinded = false;
        }

        if (mIsServiceStarted) {
            stopService(new Intent(BookAIDLTestActivity.this, AIDLService.class));
            mIsServiceStarted = false;
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void addBookInOut(View view) {
        try {
            Book book = new Book("小天鹅");
            Log.d(AIDLService.TAG, "addBookInOut 客户端添了书名： " + book.getBookName());
            mIRemoteService.addBookInOut(book);
            Log.d(AIDLService.TAG, "addBookInOut 服务端处理后的书名： " + book.getBookName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookIn(View view) {
        try {
            Book book = new Book("天命");
            Log.d(AIDLService.TAG, "addBookIn 客户端添了书名： " + book.getBookName());
            mIRemoteService.addBookIn(book);
            Log.d(AIDLService.TAG, "addBookIn 服务端处理后的书名： " + book.getBookName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookOut(View view) {
        try {
            Book book = new Book("卧底");
            Log.d(AIDLService.TAG, "addBookOut 客户端添了书名： " + book.getBookName());
            mIRemoteService.addBookOut(book);
            Log.d(AIDLService.TAG, "addBookOut 服务端处理后的书名： " + book.getBookName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBookList(View view) {
        try {
            List<Book> bookList = mIRemoteService.getBookList();
            for (Book book : bookList) {
                Log.d(AIDLService.TAG, "服务端返回的书名集合： " + book.getBookName());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
