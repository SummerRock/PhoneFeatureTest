package com.example.yanxia.phonefeaturetest.utils;

import android.os.SystemClock;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo {
    private static final RxJavaDemo ourInstance = new RxJavaDemo();
    private String TAG = RxJavaDemo.class.getSimpleName();

    public static RxJavaDemo getInstance() {
        return ourInstance;
    }

    private RxJavaDemo() {
    }

    private Disposable mDisposable;

    // 按照顺序loop，意味着第一次结果请求完成后，再考虑下次请求
    public void loopSequence() {
        mDisposable = getDataFromServer()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "loopSequence subscribe");
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "loopSequence doOnNext: " + integer);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "loopSequence doOnError: " + throwable.getMessage());
                    }
                })
                .delay(5, TimeUnit.SECONDS, true) // 设置delayError为true，表示出现错误的时候也需要延迟5s进行通知，达到无论是请求正常还是请求失败，都是5s后重新订阅，即重新请求。
                .subscribeOn(Schedulers.io())
                .repeat()   // repeat保证请求成功后能够重新订阅。
                .retry()    // retry保证请求失败后能重新订阅
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "Consumer accept: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "Consumer accept throwable: " + throwable.getMessage());
                    }
                });
        // compositeDisposable.add(disposable);
    }

    private Observable<Integer> getDataFromServer() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (emitter.isDisposed()) {
                    return;
                }
                int randomSleep = new Random().nextInt(3) + 5;
                Log.i(TAG, "randomSleep: " + randomSleep);
                SystemClock.sleep(randomSleep * 1000);
                if (emitter.isDisposed()) {
                    return;
                }
                if (randomSleep == 6) {
                    emitter.onError(new Exception("get fake error for randomSleep is 6!!!"));
                    return;
                }
                emitter.onNext(randomSleep);
                emitter.onComplete();
            }
        });
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            Log.i(TAG, "====Rx定时器取消======");
        }
    }
}
