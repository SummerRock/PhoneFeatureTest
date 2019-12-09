package com.example.yanxia.phonefeaturetest.handlerthread;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.dataModel.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HandlerThreadSingletonDemo {
    public interface OnDataLoadedInterface {
        void onDataLoadFinish(@NonNull List<String> dataList);
    }

    private static final String TAG = HandlerThreadSingletonDemo.class.getSimpleName();
    private static final int MESSAGE_UPDATE_DATA = 0;
    private static volatile HandlerThreadSingletonDemo ourInstance;
    private List<String> stringList;
    private volatile boolean isUpdating;
    private Random random = new Random();
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<OnDataLoadedInterface> dataLoadedInterfaceList;
    @SuppressLint("HandlerLeak")
    private Handler updateDataHandler;

    public static HandlerThreadSingletonDemo getInstance() {
        if (ourInstance == null) {
            synchronized (HandlerThreadSingletonDemo.class) {
                if (ourInstance == null) {
                    ourInstance = new HandlerThreadSingletonDemo();
                }
            }
        }
        return ourInstance;
    }

    private HandlerThreadSingletonDemo() {
        Log.d(TAG, "init!");
        stringList = new ArrayList<>();
        dataLoadedInterfaceList = new ArrayList<>();
        HandlerThread updateDataThread = new HandlerThread("update-data");
        updateDataThread.start();
        updateDataHandler = new Handler(updateDataThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_UPDATE_DATA:
                        updateList();
                        int randomTime = random.nextInt(3) + 3;
                        Log.d(TAG, "should update data after " + randomTime + " seconds!");
                        updateDataHandler.sendEmptyMessageDelayed(MESSAGE_UPDATE_DATA, randomTime * 1000);
                        break;
                    default:
                        break;
                }
            }
        };
        updateDataHandler.sendEmptyMessage(MESSAGE_UPDATE_DATA);
    }

    public void updateList() {
        if (isUpdating) {
            Log.d(TAG, "updateList is update! return!");
            return;
        }
        // new Thread() {
        //     @Override
        //     public void run() {
        //         super.run();
        //
        //     }
        // }.start();
        Log.d(TAG, "updateList start!");
        isUpdating = true;
        List<String> tempList = new ArrayList<>();
        int length = random.nextInt(20) + 3;
        Log.d(TAG, "updateList length: " + length);
        for (int i = 0; i < length; i++) {
            tempList.add("test item " + String.valueOf(i));
            SystemClock.sleep(1000);
        }
        stringList.clear();
        stringList.addAll(tempList);
        isUpdating = false;
        Log.d(TAG, "updateList end! post event bus!");
        EventBus.getDefault().post(new MessageEvent("stringList size: " + stringList.size()));
        handler.post(() -> {
            for (OnDataLoadedInterface dataLoadedInterface : dataLoadedInterfaceList) {
                dataLoadedInterface.onDataLoadFinish(new ArrayList<>(stringList));
            }
        });
    }

    public List<String> getList() {
        return new ArrayList<>(stringList);
    }
}
