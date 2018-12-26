package com.example.yanxia.phonefeaturetest.download;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = DownloadTestActivity.class.getSimpleName();

    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);
    }

    @Override
    public void onClick(View v) {

    }

    public void startDownload(View view) {
        File file = new File(getFilesDir() + File.separator + "testBigFile" + File.separator + "bigFile.bin");
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        download("http://speedtest.tokyo.linode.com/100MB-tokyo.bin", getFilesDir() + File.separator + "testBigFile", "bigFile.bin", null);
    }

    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称
     * @param listener     下载监听
     */
    public void download(final String url, final String destFileDir, final String destFileName, final OnDownloadUpdateListener listener) {
        Request request = new Request.Builder().url(url).build();
        //异步请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 下载失败监听回调
                if (listener != null) {
                    listener.onDownloadFailure(null);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Log.d(TAG, "onResponse start response: " + response.message());
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;

                //储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    if (!dir.mkdirs()) {
                        if (listener != null) {
                            listener.onDownloadFailure(null);
                        }
                        return;
                    }
                }
                File file = new File(dir, destFileName);

                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        float progress = (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        Log.d(TAG, "update progress: " + String.valueOf(progress));
                        if (listener != null) {
                            listener.onDownloadProgressUpdate(null, progress);
                        }
                    }
                    fos.flush();
                    //下载完成
                    if (listener != null) {
                        listener.onDownloadSuccess(null, 0);
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onDownloadFailure(null);
                    }
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
