package com.example.yanxia.phonefeaturetest.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitActivity extends AppCompatActivity {


    public interface GitHubService {
        @GET("users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }

    public interface YouDaoTranslateService {
        @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
        Call<Translation> getTranslationData();
        // @GET注解的作用:采用Get方法发送网络请求
        // getTranslationData() = 接收网络请求数据的方法
        // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }

    public void startGithubRetrofit(View view) {
        testRetrofit();
    }

    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = service.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i("debug", "response: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i("debug", "onFailure: " + t.getMessage());
            }
        });
    }

    public void startYouDao(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YouDaoTranslateService service = retrofit.create(YouDaoTranslateService.class);

        Call<Translation> repos = service.getTranslationData();
        repos.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                Log.i("debug", "response: " + response.body().getTranslation().toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.i("debug", "onFailure: " + t.getMessage());
            }
        });

    }
}
