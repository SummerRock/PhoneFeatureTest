package com.example.yanxia.phonefeaturetest.download;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTestActivity extends AppCompatActivity implements OnDownloadUpdateListener {

    private static final String TAG = "download_tag";

    private OkHttpClient client = new OkHttpClient();

    private static final String LOCALE_FILE_DIR_NAME = "testBigFile";
    private static final String LOCALE_FILE_NAME = "bigFile.bin";

    private static final String TEST_URL = "http://b.hiphotos.baidu.com/image/pic/item/9825bc315c6034a8ef5250cec5134954082376c9.jpg";

    private TextView progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);
        progressTextView = findViewById(R.id.progress_tv);
    }

    @Override
    public void onDownloadStart(Downloadable downloadItem) {
        progressTextView.setText("start download!");
    }

    @Override
    public void onDownloadProgressUpdate(Downloadable downloadItem, float percent) {
        progressTextView.setText(String.format(Locale.getDefault(), "progress: %f", percent));
    }

    @Override
    public void onDownloadSuccess(Downloadable downloadItem, long downloadTime) {
        progressTextView.setText("success!");
    }

    @Override
    public void onDownloadFailure(Downloadable downloadItem, @NonNull Exception e) {
        progressTextView.setText(e.getMessage());
    }

    public void startDownload(View view) {
        File file = new File(getFilesDir() + File.separator + LOCALE_FILE_DIR_NAME + File.separator + LOCALE_FILE_NAME);
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        download(TEST_URL, getFilesDir() + File.separator + LOCALE_FILE_DIR_NAME, LOCALE_FILE_NAME, this);
    }

    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称
     * @param listener     下载监听
     */
    private void download(final String url, final String destFileDir, final String destFileName, final OnDownloadUpdateListener listener) {
        Request request = new Request.Builder().url(url).build();
        //异步请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 下载失败监听回调
                if (listener != null) {
                    listener.onDownloadFailure(null, e);
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
                            listener.onDownloadFailure(null, new RuntimeException("mkdirs failed!"));
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
                        Log.d(TAG, "update progress: " + progress);
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
                        listener.onDownloadFailure(null, e);
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
