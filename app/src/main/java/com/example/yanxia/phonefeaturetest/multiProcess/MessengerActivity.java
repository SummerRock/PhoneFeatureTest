package com.example.yanxia.phonefeaturetest.multiProcess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

public class MessengerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MSG_FROM_CLIENT = 1000;
    public static final int MSG_FROM_SERVICE = 1001;
    public static final String BUNDLE_STRING_KEY = "string_key";
    private Messenger clientMessenger;

    private ServiceConnection messengerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //1、发送消息给服务端
            clientMessenger = new Messenger(service);
            tryToConnectServer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private final Messenger getReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_SERVICE:
                    //5、服务端回复消息给客户端，客户端接送消息
                    Log.d(MessengerService.TAG, "client receiver message from server: " + msg.getData().getString(BUNDLE_STRING_KEY));
                    break;
            }
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Messenger 进行通信
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, messengerServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        unbindService(messengerServiceConnection);
        super.onStop();
    }

    private void tryToConnectServer() {
        Message message = Message.obtain(null, MSG_FROM_CLIENT);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_STRING_KEY, "Hello from client.");
        message.setData(bundle);
        //3、这句是服务端回复客户端使用
        message.replyTo = getReplyMessenger;
        try {
            Log.d(MessengerService.TAG, "client try to send message to server!");
            clientMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void sendMessageToServer(View view) {
        tryToConnectServer();
    }

}
