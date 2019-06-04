package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;

import java.io.File;
import java.io.IOException;

public class StorageTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_test);

        Button button1 = findViewById(R.id.storage_test_button_1);
        button1.setOnClickListener(v -> {
            try {
                createFile1();
            } catch (IOException e) {
                Toast.makeText(StorageTestActivity.this, "create file1 failed!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
        Button button2 = findViewById(R.id.storage_test_button_2);
        button2.setOnClickListener(v -> {
            try {
                createNewFile(getFilesDir() + File.separator + "testDirectory" + File.separator + "testDir2.zip");
            } catch (IOException e) {
                Toast.makeText(StorageTestActivity.this, "create file2 failed!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        Button button3 = findViewById(R.id.storage_test_button_3);
        button3.setOnClickListener(v -> {
            try {
                createFile3();
            } catch (IOException e) {
                Toast.makeText(StorageTestActivity.this, "rename file3 failed!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void createFile1() throws IOException {
        File file = new File(getFilesDir() + File.separator + "test.zip");
        boolean result = file.createNewFile();
        CommonLog.d("create file result: " + result);
    }

    private File createNewFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                boolean result = file.createNewFile();
                CommonLog.d("createNewFile result: " + result);
            }
        }
        return file;
    }

    private void createFile3() throws IOException {
        File file = createNewFile(getFilesDir() + File.separator + "testDirectoryTemp");
        File dstFile = new File(getFilesDir() + File.separator + "testDirectory");
        boolean result = file.renameTo(dstFile);
        CommonLog.d("rename file result: " + result);
    }
}
