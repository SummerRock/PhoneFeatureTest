package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;

import com.example.yanxia.phonefeaturetest.Myapplication;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileDataManager {
    private static final FileDataManager ourInstance = new FileDataManager();

    public static FileDataManager getInstance() {
        return ourInstance;
    }

    private String FILENAME = "hello_file";
    private String string = "hello world!";

    private FileDataManager() {

    }

    private void saveData() {
        try {
            FileOutputStream fos = Myapplication.getContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
