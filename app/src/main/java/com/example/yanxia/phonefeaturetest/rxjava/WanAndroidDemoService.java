package com.example.yanxia.phonefeaturetest.rxjava;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidDemoService {
    @GET("wxarticle/chapters/{type}")
    Observable<List<WanAndroidItem>> listData(@Path("type") String type);
}
