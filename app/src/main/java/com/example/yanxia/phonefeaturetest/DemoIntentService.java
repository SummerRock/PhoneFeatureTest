package com.example.yanxia.phonefeaturetest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class DemoIntentService extends IntentService {
    private static final String TAG = "DemoIntentService_log";
    private static final String ACTION_FOO = "com.example.yanxia.phonefeaturetest.action.FOO";

    public DemoIntentService() {
        super("DemoIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context) {
        Intent intent = new Intent(context, DemoIntentService.class);
        intent.setAction(ACTION_FOO);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "current thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            SystemClock.sleep(1000);
        }
        Log.i(TAG, "onHandleIntent Done!=============");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
