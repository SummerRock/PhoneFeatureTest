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

import com.example.yanxia.phonefeaturetest.AIDLService;
import com.example.yanxia.phonefeaturetest.Book;
import com.example.yanxia.phonefeaturetest.BookController;
import com.example.yanxia.phonefeaturetest.R;

public class BookAIDLTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BookAIDLTestActivity";

    // State variables
    private boolean mIsServiceStarted = false;

    private boolean mIsServiceBinded = false;

    private BookController mIRemoteService;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = BookController.Stub.asInterface(service);
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

    public void addBook(View view) {
        try {
            Book book = new Book("牌面");
            Log.d(AIDLService.TAG, "客户端添了书名： " + book.getName());
            mIRemoteService.addBookInOut(book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startTest2(View view) {

    }

}
