package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class JavaQueueActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayDeque<String> arrayDeque = new ArrayDeque<>(5);
    private int arrayQueueIndex;
    private PriorityQueue<String> priorityQueue = new PriorityQueue<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_queue);
    }

    @Override
    public void onClick(View v) {

    }

    public void logOut(View view) {
        Log.i("QueueTest", arrayDeque.toString());
    }

    public void addArrayQueue(View view) {
        arrayDeque.add(String.valueOf(arrayQueueIndex++));
    }

    public void addArrayQueueFirst(View view) {
        arrayDeque.addFirst(String.valueOf(arrayQueueIndex++));
    }

    public void addArrayQueueLast(View view) {
        arrayDeque.addLast(String.valueOf(arrayQueueIndex++));
    }

    public void removeArrayQueue(View view) {
        arrayDeque.remove();
    }

    public void removeArrayQueueFirst(View view) {
        arrayDeque.removeFirst();
    }

    public void removeArrayQueueLast(View view) {
        arrayDeque.removeLast();
    }
}
