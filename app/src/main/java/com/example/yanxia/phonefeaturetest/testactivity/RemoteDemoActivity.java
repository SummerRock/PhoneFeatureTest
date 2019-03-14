package com.example.yanxia.phonefeaturetest.testactivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.RemoteDemoService;
import com.example.yanxia.phonefeaturetest.testjava.People;

public class RemoteDemoActivity extends AppCompatActivity implements View.OnClickListener {

    // State variables
    private boolean mIsServiceStarted = false;

    private boolean mIsServiceBinded = false;

    // Instance variables
    private RemoteDemoService mIRemoteService = null;

    // When call bind service, it will call service connect. register call back
    // listener and initial device
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        /**
         * called by system when bind service
         *
         * @param className component name
         * @param service service binder
         */
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            //不在同一个process中 返回的是android.os.BinderProxy
            Log.i(RemoteDemoService.TAG, "onServiceConnected service type: " + service.getClass().getName());
            if (service instanceof RemoteDemoService.ServiceBinder) {
                mIRemoteService = ((RemoteDemoService.ServiceBinder) service).getService();
                if (null == mIRemoteService) {
                    Log.e(RemoteDemoService.TAG, "onServiceConnected, mService is null");
                    finish();
                }
            } else {
                Log.e(RemoteDemoService.TAG, "service is wrong!");
                finish();
            }
        }

        /**
         * When unbind service will call this method
         *
         * @param className The component name
         */
        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.e(RemoteDemoService.TAG, "onServiceDisconnected!");
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_demo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (null == startService(new Intent(RemoteDemoActivity.this, RemoteDemoService.class))) {
            Log.e(RemoteDemoService.TAG, "onStart, cannot start RemoteDemoService service");
            return;
        }

        mIsServiceStarted = true;
        mIsServiceBinded = bindService(new Intent(RemoteDemoActivity.this, RemoteDemoService.class),
                mServiceConnection, Context.BIND_AUTO_CREATE);

        if (!mIsServiceBinded) {
            Log.e(RemoteDemoService.TAG, "onStart, cannot bind RemoteDemoService service");
            finish();
        }
    }

    @Override
    protected void onStop() {
        exitService();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        exitService();
        super.onDestroy();
    }

    /**
     * Exit service
     */
    private void exitService() {
        if (mIsServiceBinded) {
            unbindService(mServiceConnection);
            mIsServiceBinded = false;
        }

        if (mIsServiceStarted) {
            stopService(new Intent(RemoteDemoActivity.this, RemoteDemoService.class));
            mIsServiceStarted = false;
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void addPeopleInOut(View view) {
        People people = new People(30, "DD", "黄浦江", "辅助");
        mIRemoteService.addPeopleInOut(people);
    }
}
