package com.example.yanxia.phonefeaturetest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annotation.BindView;
import com.example.butterknifelibrary.ButterKnife;
import com.example.yanxia.phonefeaturetest.dataModel.People;
import com.example.yanxia.phonefeaturetest.dialog.CustomDialogFragment;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.DoubleRecyclerViewActivity;
import com.example.yanxia.phonefeaturetest.download.test.DownloadTestActivity;
import com.example.yanxia.phonefeaturetest.horizonRv.HorizonRvTestActivity;
import com.example.yanxia.phonefeaturetest.keepalive.MyJobService;
import com.example.yanxia.phonefeaturetest.multiProcess.BookAIDLTestActivity;
import com.example.yanxia.phonefeaturetest.multiProcess.MessengerActivity;
import com.example.yanxia.phonefeaturetest.multiProcess.MyContentProvider;
import com.example.yanxia.phonefeaturetest.multiProcess.socket.TCPClientActivity;
import com.example.yanxia.phonefeaturetest.notifyitemtest.RecyclerViewTestActivity;
import com.example.yanxia.phonefeaturetest.retrofit.RetrofitActivity;
import com.example.yanxia.phonefeaturetest.rvData.RecyclerViewDataActivity;
import com.example.yanxia.phonefeaturetest.testFragment.BitmapTestFragment;
import com.example.yanxia.phonefeaturetest.testFragment.PluginTestFragment;
import com.example.yanxia.phonefeaturetest.testactivity.AnimationTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.AutoFocusActivity;
import com.example.yanxia.phonefeaturetest.testactivity.CameraTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ColorMatrixTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.DialogStyleActivity;
import com.example.yanxia.phonefeaturetest.testactivity.EasyDrawableActivity;
import com.example.yanxia.phonefeaturetest.testactivity.EventBusTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.FullscreenActivity;
import com.example.yanxia.phonefeaturetest.handlerthread.HandlerThreadActivity;
import com.example.yanxia.phonefeaturetest.testactivity.InputMethodTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.JavaQueueActivity;
import com.example.yanxia.phonefeaturetest.testactivity.LongImageActivity;
import com.example.yanxia.phonefeaturetest.testactivity.PeopleEditActivity;
import com.example.yanxia.phonefeaturetest.testactivity.PermissionTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ProgressBarTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.RemoteDemoActivity;
import com.example.yanxia.phonefeaturetest.rxjava.RxJavaActivity;
import com.example.yanxia.phonefeaturetest.testactivity.SVGTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ScrollingCustomActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ScrollingDemoActivity;
import com.example.yanxia.phonefeaturetest.testactivity.SecondTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.SettingsActivity;
import com.example.yanxia.phonefeaturetest.testactivity.StorageTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ThreadPoolActivity;
import com.example.yanxia.phonefeaturetest.testactivity.ViewFeatureActivity;
import com.example.yanxia.phonefeaturetest.utils.Constant;
import com.example.yanxia.phonefeaturetest.utils.SingletonEnum;
import com.example.yanxia.phonefeaturetest.viewpager.ViewPagerActivity;
import com.example.yanxia.phonefeaturetest.viewpager2.ViewPager2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxia-Mac
 */
public class MainActivity extends AppCompatActivity implements CustomDialogFragment.OnFragmentInteractionListener {


    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

    private static final String TAG = "MainActivityTag";
    private List<TestItem> testItemList = new ArrayList<>();
    private static final int REQUEST_CODE = 10;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private People person = new People(System.currentTimeMillis(), "Summer", "BJ", "none");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "main onCreate!");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar myToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
        initTestItems();
        //        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        TestItemAdapter adapter = new TestItemAdapter(testItemList, this::startTest);
        recyclerView.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            MyJobService.startJob(this);
        }

        SingletonEnum.INSTANCE.demoMethod();

        //        Looper.getMainLooper().setMessageLogging(new Printer() {
        //            @Override
        //            public void println(String x) {
        //                Log.i("xiayan", "x: " + x);
        //            }
        //        });

        getLifecycle().addObserver(new MyObserver());
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "getDecorView post done.");
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "main onWindowFocusChanged hasFocus: " + hasFocus);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "main onResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivityWithNoAnim(SettingsActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction() {
        Toast.makeText(this, "People age: " + person.getId(), Toast.LENGTH_SHORT).show();
    }

    private void initTestItems() {
        testItemList.add(new TestItem(Constant.TEST_RECYCLER, R.drawable.ic_lock_24dp, RecyclerViewDataActivity.class));
        testItemList.add(new TestItem(Constant.TEST_LONG_IMAGE, R.drawable.ic_lock_24dp, LongImageActivity.class));
        testItemList.add(new TestItem(Constant.TEST_PEOPLE_DATA, R.drawable.ic_lock_24dp, PeopleEditActivity.class));
        testItemList.add(new TestItem(Constant.TEST_INPUT_METHOD, R.drawable.ic_lock_24dp, null));
        testItemList.add(new TestItem(Constant.TEST_DOUBLE_RV, R.drawable.ic_lock_24dp, DoubleRecyclerViewActivity.class));
        testItemList.add(new TestItem(Constant.TEST_BITMAP, R.drawable.ic_lock_24dp, null));
        testItemList.add(new TestItem(Constant.TEST_PLUGIN, R.drawable.ic_lock_24dp, null));
        testItemList.add(new TestItem(Constant.TEST_RX_JAVA, R.drawable.ic_lock_24dp, RxJavaActivity.class));
        testItemList.add(new TestItem(Constant.TEST_PERMISSION, R.drawable.ic_lock_24dp, PermissionTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_DIALOG, R.drawable.ic_lock_24dp, DialogStyleActivity.class));
        testItemList.add(new TestItem(Constant.TEST_HANDLER_THREAD, R.drawable.ic_lock_24dp, HandlerThreadActivity.class));
        testItemList.add(new TestItem(Constant.TEST_COLOR_MATRIX, R.drawable.ic_lock_24dp, ColorMatrixTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_STRING_FORMAT, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_PROGRESS_BAR, R.drawable.ic_lock_24dp, ProgressBarTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_HORIZONTAL_RV, R.drawable.ic_lock_24dp, HorizonRvTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_RV_INSIDE_VIEW_PAGER, R.drawable.ic_lock_24dp, ViewPagerActivity.class));
        testItemList.add(new TestItem(Constant.TEST_SVG, R.drawable.ic_lock_24dp, SVGTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_EVENT_BUS, R.drawable.ic_lock_24dp, EventBusTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_STORAGE, R.drawable.ic_lock_24dp, StorageTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_CAMERA, R.drawable.ic_lock_24dp, CameraTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_EASY_DRAWABLE, R.drawable.ic_lock_24dp, EasyDrawableActivity.class));
        testItemList.add(new TestItem(Constant.TEST_START_ACTIVITY_FOR_RESULT, R.drawable.ic_lock_24dp));
        testItemList.add(new TestItem(Constant.TEST_THREAD_POOL, R.drawable.ic_lock_24dp, ThreadPoolActivity.class));
        testItemList.add(new TestItem(Constant.TEST_DOWNLOAD_FILE, R.drawable.ic_lock_24dp, DownloadTestActivity.class));
        testItemList.add(new TestItem(Constant.FULL_SCREEN_ACTIVITY, R.drawable.ic_lock_24dp, FullscreenActivity.class));
        testItemList.add(new TestItem(Constant.TEST_ANIMATION, R.drawable.ic_lock_24dp, AnimationTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_CONTENT_PROVIDER, R.drawable.ic_lock_24dp, null));
        testItemList.add(new TestItem(Constant.TEST_DIALOG_FRAGMENT, R.drawable.ic_lock_24dp, null));
        testItemList.add(new TestItem(Constant.TEST_VIEW_FEATURE, R.drawable.ic_lock_24dp, ViewFeatureActivity.class));
        testItemList.add(new TestItem(Constant.TEST_AIDL, R.drawable.ic_lock_24dp, BookAIDLTestActivity.class));
        testItemList.add(new TestItem(Constant.TEST_REMOTE, R.drawable.ic_lock_24dp, RemoteDemoActivity.class));
        testItemList.add(new TestItem(Constant.TEST_MESSENGER, R.drawable.ic_lock_24dp, MessengerActivity.class));
        testItemList.add(new TestItem(Constant.TEST_TCP, R.drawable.ic_lock_24dp, TCPClientActivity.class));
        testItemList.add(new TestItem(Constant.TEST_INTENT_SERVICE, R.drawable.ic_lock_24dp, null));
        testItemList.add(new TestItem(Constant.TEST_JAVA_QUEUE, R.drawable.ic_lock_24dp, JavaQueueActivity.class));
        testItemList.add(new TestItem(Constant.TEST_RETROFIT, R.drawable.ic_lock_24dp, RetrofitActivity.class));
        testItemList.add(new TestItem(Constant.TEST_AUTO_FUCUS, R.drawable.ic_lock_24dp, AutoFocusActivity.class));
        testItemList.add(new TestItem(Constant.TEST_VP2, R.drawable.ic_lock_24dp, ViewPager2Activity.class));
        testItemList.add(new TestItem(Constant.TEST_SCROLLING, R.drawable.ic_lock_24dp, ScrollingCustomActivity.class));
    }

    private void startTest(@NonNull TestItem testItem) {
        if (testItem.getActivityClass() != null) {
            startActivityWithNoAnim(testItem.getActivityClass());
            return;
        }
        switch (testItem.getName()) {
            case Constant.TEST_STRING_FORMAT:
                // TestString.testStringFormat();
                Toast.makeText(this, stringFromJNI(), Toast.LENGTH_SHORT).show();
                break;
            case Constant.TEST_START_ACTIVITY_FOR_RESULT:
                startSecondActivityForResult();
                break;
            case Constant.TEST_CONTENT_PROVIDER:
                testContentProvider();
                break;
            case Constant.TEST_DIALOG_FRAGMENT:
                CustomDialogFragment.newInstance(person).show(getSupportFragmentManager(), CustomDialogFragment.class.getSimpleName());
                break;
            case Constant.TEST_INTENT_SERVICE:
                DemoIntentService.startActionFoo(this);
                break;
            case Constant.TEST_INPUT_METHOD:
                Intent intent = new Intent(MyApplication.getContext(), InputMethodTestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ContextCompat.startActivity(MyApplication.getContext(), intent, null);
                break;
            case Constant.TEST_BITMAP:
                BitmapTestFragment.newInstance().show(getSupportFragmentManager(), BitmapTestFragment.class.getSimpleName());
                break;
            case Constant.TEST_PLUGIN:
                PluginTestFragment.newInstance().show(getSupportFragmentManager(), PluginTestFragment.class.getSimpleName());
                break;
            default:
                Toast.makeText(MainActivity.this, "you clicked testName " + testItem.getName(), Toast.LENGTH_SHORT).show();
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

    private void startSecondActivityForResult() {
        Intent intent = new Intent(this, SecondTestActivity.class);
        intent.putExtra(SecondTestActivity.EXTRA, person);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            Toast.makeText(this, "People age: " + person.getId(), Toast.LENGTH_SHORT).show();
        }
    }

    private void testContentProvider() {
        long currentTime = System.currentTimeMillis();
        Log.d(MyContentProvider.TAG, "contentProvider test start!");
        String method = "isSuccess";
        String uri = "content://otherwork/abc.txt";
        Bundle bundle = getContentResolver().call(Uri.parse(uri), method, null, null);
        boolean result = bundle != null && bundle.getBoolean(method);
        Log.d(MyContentProvider.TAG, "contentProvider test end with result: " + result + " cost time: " + String.valueOf(System.currentTimeMillis() - currentTime));
    }

    public class MyObserver implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResumeNext() {
            Log.i("LifecycleObserver", "onResumeNext");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPauseNext() {
            Log.i("LifecycleObserver", "onPauseNext");
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
