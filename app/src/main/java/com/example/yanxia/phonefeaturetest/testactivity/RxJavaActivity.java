package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxJavaTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }

    public void rxJavaTest1(View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("烤鸡一份");
                e.onNext("薯条一份");
                e.onNext("可乐一杯");
                // e.onError(new NullPointerException());
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe : 订阅成功");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext : 接收事件  " + s);
                //按顺序得到：烤鸡一份、薯条一份、可乐一杯
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError : 事件异常  " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete : 事件执行完毕  ");
            }
        });
    }

    public void rxJavaTest2(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.i(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.i(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                Log.i(TAG, "Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete" + "\n");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}