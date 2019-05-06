package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;
import android.os.Environment;

import com.example.yanxia.phonefeaturetest.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JsonFileSaveUtils {
    private static String FILENAME = "hello_file";
    private static String string = "hello world!";

    private static void saveData() {
        try {
            FileOutputStream fos = MyApplication.getContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static File dir = new File(Environment.getExternalStorageDirectory() + "/data/json/");

    public static void saveToSDCard(String filename, String content) {
        String en = Environment.getExternalStorageState();
        //获取SDCard状态,如果SDCard插入了手机且为非写保护状态
        if (en.equals(Environment.MEDIA_MOUNTED)) {
            try {
                dir.mkdirs(); //create folders where write files
                File file = new File(dir, filename);

                OutputStream out = new FileOutputStream(file);
                out.write(content.getBytes());
                out.close();
                ToastUtils.showToast("保存成功");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast("保存失败");
            }
        } else {
            //提示用户SDCard不存在或者为写保护状态
            ToastUtils.showToast("SDCard不存在或者为写保护状态");
        }
    }

    /**
     * 从本地读取json
     *
     * @param filePath
     */
    public static String readTextFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(dir + "/" + filePath);
            InputStream in = null;
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                sb.append((char) tempbyte);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getJsonString(String key, Object value) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject("{\"name\":\"李四\",\"age\":99,\"hobby\":\"爱好是练习截拳道\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //put和element都是往JSONObject对象中放入 key/value 对
        jsonObject.toString();
        return jsonObject.toString();
    }
}
