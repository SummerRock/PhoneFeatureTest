package com.example.yanxia.phonefeaturetest.download.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.download.Downloadable;
import com.example.yanxia.phonefeaturetest.download.OnDownloadUpdateListener;
import com.example.yanxia.phonefeaturetest.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTestActivity extends AppCompatActivity implements OnDownloadUpdateListener {

    private static final String TAG = "download_tag";

    private OkHttpClient client = new OkHttpClient();
    private Call call;

    private static final String LOCALE_FILE_DIR_NAME = "testBigFile";
    private static final String LOCALE_FILE_NAME = "bigFile.bin";

    private static final String TEST_URL = "http://speedtest.tokyo.linode.com/100MB-tokyo.bin";

    private ProgressBar progressBar;
    private TextView progressTextView;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private List<DownloadDemoItem> getDemoList() {
        List<DownloadDemoItem> downloadDemoItemList = new ArrayList<>();
        downloadDemoItemList.add(new DownloadDemoItem("tokyo", "demo", "http://speedtest.tokyo.linode.com/100MB-tokyo.bin", LOCALE_FILE_DIR_NAME, "demo_file_tokyo.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("london", "demo", "http://speedtest.london.linode.com/100MB-london.bin", LOCALE_FILE_DIR_NAME, "demo_file_london.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("newark", "demo", "http://speedtest.newark.linode.com/100MB-newark.bin", LOCALE_FILE_DIR_NAME, "demo_file_newark.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("atlanta", "demo", "http://speedtest.atlanta.linode.com/100MB-atlanta.bin", LOCALE_FILE_DIR_NAME, "demo_file_atlanta.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("dallas", "demo", "http://speedtest.dallas.linode.com/100MB-dallas.bin", LOCALE_FILE_DIR_NAME, "demo_file_dallas.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("fremont", "demo", "http://speedtest.fremont.linode.com/100MB-fremont.bin", LOCALE_FILE_DIR_NAME, "demo_file_fremont.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("cachefly", "demo", "http://cachefly.cachefly.net/100mb.test", LOCALE_FILE_DIR_NAME, "demo_file_cachefly.test"));
        downloadDemoItemList.add(new DownloadDemoItem("direct", "demo", "http://direct.netcloud.com/100MB.BIN", LOCALE_FILE_DIR_NAME, "demo_file_direct.bin"));
        return downloadDemoItemList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);
        progressTextView = findViewById(R.id.progress_tv);
        progressBar = findViewById(R.id.download_progress_bar);
    }

    @Override
    public void onDownloadStart(Downloadable downloadItem) {
        runOnUiThread(() -> {
            progressTextView.setText("start download!");
            progressBar.setProgress(0);
        });
    }

    @Override
    public void onDownloadProgressUpdate(Downloadable downloadItem, float percent) {
        runOnUiThread(() -> {
            progressBar.setProgress(Math.round(percent));
            progressTextView.setText(String.format(Locale.getDefault(), "progress: %d", Math.round(percent)));
        });
    }

    @Override
    public void onDownloadSuccess(Downloadable downloadItem, long downloadTime) {
        runOnUiThread(() -> {
            progressTextView.setText("success!");
            progressBar.setProgress(100);
        });
    }

    @Override
    public void onDownloadFailure(Downloadable downloadItem, @NonNull Exception e) {
        runOnUiThread(() -> {
            progressBar.setProgress(0);
            progressTextView.setText(e.getMessage());
        });
    }

    public void startDownload(View view) {
        File file = new File(getFilesDir() + File.separator + LOCALE_FILE_DIR_NAME + File.separator + LOCALE_FILE_NAME);
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
            ToastUtils.showToast("删除文件！");
        }
        download(TEST_URL, getFilesDir() + File.separator + LOCALE_FILE_DIR_NAME, LOCALE_FILE_NAME, this);
    }

    public void cancelDownload(View view) {
        if (call != null) {
            call.cancel();
            call = null;
        }
    }

    @Override
    protected void onDestroy() {
        if (call != null) {
            call.cancel();
            call = null;
        }
        super.onDestroy();
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
        if (call != null) {
            call.cancel();
            call = null;
        }
        call = client.newCall(request);
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
                File tempFile = new File(dir, destFileName + "_temp");
                File realFile = new File(dir, destFileName);

                if (listener != null) {
                    listener.onDownloadStart(null);
                }

                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(tempFile);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        float progress = (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        String format = decimalFormat.format(progress);
                        Log.d(TAG, "update progress: " + format);
                        if (listener != null) {
                            listener.onDownloadProgressUpdate(null, Float.valueOf(format));
                        }
                    }
                    fos.flush();
                    //下载完成
                    boolean renameResult = tempFile.renameTo(realFile);
                    if (listener != null) {
                        if (renameResult) {
                            listener.onDownloadSuccess(null, 0);
                        } else {
                            listener.onDownloadFailure(null, new RuntimeException("rename failed!"));
                        }
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
