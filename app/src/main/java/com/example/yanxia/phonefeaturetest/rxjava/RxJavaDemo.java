package com.example.yanxia.phonefeaturetest.rxjava;

import android.os.SystemClock;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaDemo {
    private static final RxJavaDemo ourInstance = new RxJavaDemo();
    private String TAG = RxJavaDemo.class.getSimpleName();

    // private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;

    public static RxJavaDemo getInstance() {
        return ourInstance;
    }

    private RxJavaDemo() {
    }

    // 按照顺序loop，意味着第一次结果请求完成后，再考虑下次请求
    public void loopSequence() {
        disposable = getDataFromServer()
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
                // .delay(5, TimeUnit.SECONDS, true) // 设置delayError为true，表示出现错误的时候也需要延迟5s进行通知，达到无论是请求正常还是请求失败，都是5s后重新订阅，即重新请求。
                .subscribeOn(Schedulers.io())
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        Log.d(TAG, "延迟两秒");
                        return objectObservable.delay(2, TimeUnit.SECONDS);
                    }
                })
                .retry()    // retry保证请求失败后能重新订阅
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "消费者接收到了数字: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "消费者出现问题: " + throwable.getMessage());
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
        disposable.dispose();
        Log.i(TAG, "====Rx定时器取消======");
    }

    public void testNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Log.d(TAG, "开始网络请求!");
        retrofit.create(WanAndroidDemoService.class).listData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanAndroidItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "打印 onSubscribe");
                    }

                    @Override
                    public void onNext(WanAndroidItem repos) {
                        Log.d(TAG, "打印 onNext:" + repos.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "打印 onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "打印 onComplete");
                    }
                });
    }
}
