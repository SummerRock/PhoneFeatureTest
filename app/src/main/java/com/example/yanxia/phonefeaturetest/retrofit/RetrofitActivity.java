package com.example.yanxia.phonefeaturetest.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yanxia.phonefeaturetest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitActivity extends AppCompatActivity {

    public interface GitHubService {
        @GET("user")
        Call<Repo> listRepos(@Path("user") String user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }

    public void startRetrofit(View view) {
        testRetrofit();
    }

    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<Repo> repos = service.listRepos("user");
        repos.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Log.i("debug", "");
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.i("debug", "");
            }
        });
    }
}
