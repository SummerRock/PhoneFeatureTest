package com.example.yanxia.phonefeaturetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.testactivity.VersionTestActivity;
import com.example.yanxia.phonefeaturetest.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxia-Mac
 */
public class MainActivity extends AppCompatActivity {

    private List<TestItem> testItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTestItems();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(testItemList, new ItemAdapter.OnTestItemClickListener() {
            @Override
            public void onItemClick(TestItem testItem) {
                startTestActivity(testItem.getName());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initTestItems() {
        testItemList.add(new TestItem(Constant.TEST_RECYCLER, R.drawable.ic_menu_gallery));
        testItemList.add(new TestItem(Constant.TEST_DIALOG, R.drawable.ic_blur_on_black_48dp));
    }

    private void startTestActivity(String testName) {
        switch (testName) {
            case Constant.TEST_RECYCLER:
                startActivityWithAnim(VersionTestActivity.class);
                break;
            case Constant.TEST_DIALOG:
                startActivityWithAnim(VersionTestActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "you clicked testName " + testName, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据传入的类(class)打开指定的activity
     */
    private void startActivityWithAnim(Class<?> pClass) {
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
    }
}
