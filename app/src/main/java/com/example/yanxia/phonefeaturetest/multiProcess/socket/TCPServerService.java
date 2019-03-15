package com.example.yanxia.phonefeaturetest.multiProcess.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.yanxia.phonefeaturetest.utils.ThreadPoolUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    public static final String LOG_TAG = "TCP_Test_Log";
    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessages = new String[]{
            "这波很急，很关键",
            "他们凭什么跟我们打？",
            "这个行为真的是非常非常愚蠢，等于是一个自杀的行为。",
            "你知道吗？我可是可以和多个人同时聊天的哦",
            "我周雄，不求人！",
            "那些年欠我的冠军我要一个一个拿回来",
            "三个打一个还被反杀，会不会玩？！",
            "我就问你满足不满足！",
            "我TM又不是最菜的。",
            "拉谁，说话！"
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @SuppressWarnings("resource")
        @Override
        public void run() {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                Log.e(TCPServerService.LOG_TAG, "establish tcp server failed, port:8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestroyed) {
                try {
                    // 接受客户端请求
                    final Socket client = serverSocket.accept();
                    Log.d(TCPServerService.LOG_TAG, "accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(
                client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室！");
        while (!mIsServiceDestroyed) {
            String str = in.readLine();
            Log.d(TCPServerService.LOG_TAG, "msg from client:" + str);
            if (str == null) {
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            Log.d(TCPServerService.LOG_TAG, "send :" + msg);
        }
        Log.d(TCPServerService.LOG_TAG, "client quit.");
        // 关闭流
        ThreadPoolUtils.close(out);
        ThreadPoolUtils.close(in);
        client.close();
    }

}
