package com.example.yanxia.phonefeaturetest.testFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pluginlibrary.PluginManager;
import com.example.pluginlibrary.ProxyActivity;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.FileUtils;
import com.example.yanxia.phonefeaturetest.utils.ToastUtils;

public class PluginTestFragment extends BaseTestFragment {

    public static final String PLUG_IN_APK_PATH = "myplugin.apk";
    public static PluginTestFragment newInstance() {
        return new PluginTestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PluginManager.getInstance().init(getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plugin_dialog, container, false);
        Button loadButton = rootView.findViewById(R.id.plugin_load_apk_button);
        loadButton.setOnClickListener(v -> new CopyApkToCacheDirTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR));

        Button jumpButton = rootView.findViewById(R.id.plugin_jump_button);
        jumpButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ProxyActivity.class);
            intent.putExtra("className", "com.example.myplugin.MyPluginActivity");
            startActivity(intent);
        });
        return rootView;
    }

    private class CopyApkToCacheDirTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return FileUtils.copyAssetAndWrite(getContext(), PLUG_IN_APK_PATH);
        }

        @Override
        protected void onPostExecute(String apkPath) {
            Log.i("xiayan", "apkPath: " + apkPath);
            if (!TextUtils.isEmpty(apkPath)) {
                ToastUtils.showToast("拷贝成功！");
                PluginManager.getInstance().loadApk(apkPath);
            } else {
                ToastUtils.showToast("拷贝错误！");
            }
        }
    }
}
