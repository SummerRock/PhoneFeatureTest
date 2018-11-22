package com.example.yanxia.phonefeaturetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.horizonRv.HorizonRvTestActivity;
import com.example.yanxia.phonefeaturetest.notifyitemtest.RecyclerViewTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.HandlerThreadActivity;
import com.example.yanxia.phonefeaturetest.testactivity.LaunchOrderTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ProgressBarTestActivity;
import com.example.yanxia.phonefeaturetest.utils.Constant;
import com.example.yanxia.phonefeaturetest.viewpager.ViewPagerActivity;

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
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        TestItemAdapter adapter = new TestItemAdapter(testItemList, new TestItemAdapter.OnTestItemClickListener() {
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
        testItemList.add(new TestItem(Constant.TEST_HANDLER_THREAD, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_LAUNCH_ORDER, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_AUTO_PACKAGE, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_STRING_FORMAT, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_PROGRESS_BAR, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_HORIZONTAL_RV, R.drawable.ic_lock_24dp));
    }

    private void startTestActivity(String testName) {
        switch (testName) {
            case Constant.TEST_RECYCLER:
                startActivityWithNoAnim(RecyclerViewTestActivity.class);
                break;
            case Constant.TEST_DIALOG:
                startActivityWithNoAnim(ViewPagerActivity.class);
                break;
            case Constant.TEST_HANDLER_THREAD:
                startActivityWithNoAnim(HandlerThreadActivity.class);
                break;
            case Constant.TEST_LAUNCH_ORDER:
                startActivityWithNoAnim(LaunchOrderTestActivity.class);
                break;
            case Constant.TEST_AUTO_PACKAGE:
                TestClass.mainTest();
                break;
            case Constant.TEST_STRING_FORMAT:
                TestString.testStringFormat();
                break;
            case Constant.TEST_PROGRESS_BAR:
                startActivityWithNoAnim(ProgressBarTestActivity.class);
                break;
            case Constant.TEST_HORIZONTAL_RV:
                startActivityWithNoAnim(HorizonRvTestActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "you clicked testName " + testName, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据传入的类(class)打开指定的activity
     */
    private void startActivityWithNoAnim(Class<?> pClass) {
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
    }
}
