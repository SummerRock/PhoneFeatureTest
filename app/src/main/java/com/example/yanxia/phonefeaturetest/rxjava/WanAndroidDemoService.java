package com.example.yanxia.phonefeaturetest.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WanAndroidDemoService {
    @GET("wxarticle/chapters/json")
    Observable<WanAndroidItem> listData();
}
