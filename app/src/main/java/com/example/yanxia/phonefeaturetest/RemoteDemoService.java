package com.example.yanxia.phonefeaturetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.testjava.People;

import java.util.ArrayList;
import java.util.List;

public class RemoteDemoService extends Service {

    public static final String TAG = "RemoteDemoLogTag";
    // For bind service
    private final IBinder mBinder = new ServiceBinder();

    private List<People> peopleList = new ArrayList<>();

    public RemoteDemoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        People people1 = new People(30, "YYF", "上海", "3号位");
        People people2 = new People(28, "Zhou", "湖南", "后期");
        peopleList.add(people1);
        peopleList.add(people2);
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

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void addPeopleInOut(People people) {
        if (people != null) {
            Log.i(TAG, "addPeopleInOut 服务端接收的地址名: " + people.getAddress());
            people.setAddress(people.getAddress() + "_Server");
            peopleList.add(people);
        } else {
            Log.e(TAG, "addPeopleInOut 接收到了一个空对象");
        }
    }
}
