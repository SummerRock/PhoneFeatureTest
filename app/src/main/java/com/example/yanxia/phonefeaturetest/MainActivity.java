package com.example.yanxia.phonefeaturetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.testactivity.CameraTestActivity;
import com.example.yanxia.phonefeaturetest.testactivity.VersionTestActivity;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.name;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<TestItem> testItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initTestItems();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(testItemList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ItemAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int imageID) {
                startTestActivity(imageID);
            }
        });
    }

    private void startTestActivity(int id) {
        switch (id) {
            case R.drawable.ic_developer_board_black_48dp:
                startActivityWithAnim(VersionTestActivity.class);
                break;
            case R.drawable.ic_camera_black_48dp:
                startActivityWithAnim(CameraTestActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "you clicked image " + id, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected void startActivityWithAnim(Class<?> pClass) {
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    private void initTestItems(){
        TestItem touchTest = new TestItem(getResources().getString(R.string.touchScreen),R.drawable.ic_smartphone_black_48dp);
        testItemList.add(touchTest);
        TestItem lcdTest = new TestItem("Color",R.drawable.ic_color_lens_black_48dp);
        testItemList.add(lcdTest);
        TestItem gpsTest = new TestItem("GPS",R.drawable.ic_gps_fixed_black_48dp);
        testItemList.add(gpsTest);
        TestItem powerTest = new TestItem("Power",R.drawable.ic_battery_charging_full_black_48dp);
        testItemList.add(powerTest);
        TestItem keyTest = new TestItem("Keyboard",R.drawable.ic_keyboard_black_48dp);
        testItemList.add(keyTest);
        TestItem speakerTest = new TestItem("Speaker",R.drawable.ic_speaker_black_48dp);
        testItemList.add(speakerTest);
        TestItem headsetTest = new TestItem("Headset",R.drawable.ic_headset_black_48dp);
        testItemList.add(headsetTest);
        TestItem microphoneTest = new TestItem("Microphone",R.drawable.ic_headset_mic_black_48dp);
        testItemList.add(microphoneTest);
        TestItem receiverTest = new TestItem("Receiver",R.drawable.ic_phone_forwarded_black_48dp);
        testItemList.add(receiverTest);
        TestItem wifiTest = new TestItem("Wifi",R.drawable.ic_wifi_black_48dp);
        testItemList.add(wifiTest);
        TestItem bluetoothTest = new TestItem("Bluetooth",R.drawable.ic_bluetooth_black_48dp);
        testItemList.add(bluetoothTest);
        TestItem vibratorTest = new TestItem("Vibrator",R.drawable.ic_vibration_black_48dp);
        testItemList.add(vibratorTest);
        TestItem simcardTest = new TestItem("Simcard",R.drawable.ic_sim_card_black_48dp);
        testItemList.add(simcardTest);
        TestItem brightnessTest = new TestItem("Brightness",R.drawable.ic_brightness_auto_black_48dp);
        testItemList.add(brightnessTest);
        TestItem romTest = new TestItem("ROM",R.drawable.ic_disc_full_black_48dp);
        testItemList.add(romTest);
        TestItem gravityTest = new TestItem("Gravity",R.drawable.ic_screen_rotation_black_48dp);
        testItemList.add(gravityTest);
        TestItem lightTest = new TestItem("Light",R.drawable.ic_lightbulb_outline_black_48dp);
        testItemList.add(lightTest);
        TestItem rangeTest = new TestItem("Range",R.drawable.ic_vertical_align_top_black_48dp);
        testItemList.add(rangeTest);
        TestItem tfcardTest = new TestItem("TFCard",R.drawable.ic_storage_black_48dp);
        testItemList.add(tfcardTest);
        TestItem cameraTest = new TestItem("Camera",R.drawable.ic_camera_black_48dp);
        testItemList.add(cameraTest);
        TestItem frontcameraTest = new TestItem("FrontCamera",R.drawable.ic_camera_front_black_48dp);
        testItemList.add(frontcameraTest);
        TestItem fmradioTest = new TestItem("FMRadio",R.drawable.ic_radio_black_48dp);
        testItemList.add(fmradioTest);
        TestItem dialTest = new TestItem("Dial",R.drawable.ic_dialpad_black_48dp);
        testItemList.add(dialTest);
        TestItem flashlightTest = new TestItem("Flashlight",R.drawable.ic_highlight_black_48dp);
        testItemList.add(flashlightTest);
        TestItem versionTest = new TestItem(getResources().getString(R.string.version),R.drawable.ic_developer_board_black_48dp);
        testItemList.add(versionTest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
