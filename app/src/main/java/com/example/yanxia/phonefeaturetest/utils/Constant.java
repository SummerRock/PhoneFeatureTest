package com.example.yanxia.phonefeaturetest.utils;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.TestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxia-Mac
 */
public class Constant {
    public static final String TEST_RECYCLER = "RV选中状态";
    public static final String TEST_DIALOG = "Dialog样式";
    public static final String TEST_HANDLER_THREAD = "HandlerThread测试";
    public static final String TEST_COLOR_MATRIX = "Color Matrix";
    public static final String TEST_AUTO_PACKAGE = "自动装箱";
    public static final String TEST_STRING_FORMAT = "String格式";
    public static final String TEST_PROGRESS_BAR = "进度条";
    public static final String TEST_HORIZONTAL_RV = "横向RV";
    public static final String TEST_RV_INSIDE_VIEW_PAGER = "ViewPager嵌套横向RV";
    public static final String TEST_GPS = "GPS测试";
    public static final String TEST_SENSOR = "Sensor传感器";
    public static final String TEST_STORAGE = "Storage测试";
    public static final String TEST_CAMERA = "Camera测试";
    public static final String TEST_EASY_DRAWABLE = "背景测试";
    public static final String TEST_START_ACTIVITY_FOR_RESULT = "启动Activity";
    public static final String TEST_THREAD_POOL = "线程池";
    public static final String TEST_DOWNLOAD_FILE = "下载测试";
    public static final String TEST_ACTIVITY_THEME = "主题测试";
    public static final String TEST_ANIMATION = "动画测试";
    public static final String TEST_CONTENT_PROVIDER = "ContentProvider测试";
    public static final String TEST_DIALOG_FRAGMENT = "Dialog Fragment";
    public static final String TEST_VIEW_FEATURE = "View 属性";
    public static final String TEST_AIDL = "AIDL";
    public static final String TEST_REMOTE = "Remote";
    public static final String TEST_MESSENGER = "Messenger";

    private static List<TestItem> initTestItems() {
        List<TestItem> testItemList = new ArrayList<>();
        TestItem touchTest = new TestItem("TouchScreen", R.drawable.ic_smartphone_black_48dp);
        testItemList.add(touchTest);
        TestItem qrScanTest = new TestItem("QRScan", R.drawable.ic_blur_on_black_48dp);
        testItemList.add(qrScanTest);
        TestItem gpsTest = new TestItem("GPS", R.drawable.ic_gps_fixed_black_48dp);
        testItemList.add(gpsTest);
        TestItem powerTest = new TestItem("Battery", R.drawable.ic_battery_charging_full_black_48dp);
        testItemList.add(powerTest);
        TestItem keyTest = new TestItem("Keyboard", R.drawable.ic_keyboard_black_48dp);
        testItemList.add(keyTest);
        TestItem speakerTest = new TestItem("Speaker", R.drawable.ic_speaker_black_48dp);
        testItemList.add(speakerTest);
        TestItem headsetTest = new TestItem("Headset", R.drawable.ic_headset_black_48dp);
        testItemList.add(headsetTest);
        TestItem microphoneTest = new TestItem("Microphone", R.drawable.ic_headset_mic_black_48dp);
        testItemList.add(microphoneTest);
        TestItem receiverTest = new TestItem("Receiver", R.drawable.ic_phone_forwarded_black_48dp);
        testItemList.add(receiverTest);
        TestItem wifiTest = new TestItem("Wifi", R.drawable.ic_wifi_black_48dp);
        testItemList.add(wifiTest);
        TestItem bluetoothTest = new TestItem("Bluetooth", R.drawable.ic_bluetooth_black_48dp);
        testItemList.add(bluetoothTest);
        TestItem vibratorTest = new TestItem("Vibrator", R.drawable.ic_vibration_black_48dp);
        testItemList.add(vibratorTest);
        TestItem simcardTest = new TestItem("Simcard", R.drawable.ic_sim_card_black_48dp);
        testItemList.add(simcardTest);
        TestItem brightnessTest = new TestItem("Brightness", R.drawable.ic_brightness_auto_black_48dp);
        testItemList.add(brightnessTest);
        TestItem romTest = new TestItem("ROM", R.drawable.ic_disc_full_black_48dp);
        testItemList.add(romTest);
        TestItem ramTest = new TestItem("RAM", R.drawable.ic_receipt_black_48dp);
        testItemList.add(ramTest);
        TestItem gravityTest = new TestItem("Gravity", R.drawable.ic_screen_rotation_black_48dp);
        testItemList.add(gravityTest);
        TestItem lightTest = new TestItem("Light", R.drawable.ic_lightbulb_outline_black_48dp);
        testItemList.add(lightTest);
        TestItem rangeTest = new TestItem("Range", R.drawable.ic_vertical_align_top_black_48dp);
        testItemList.add(rangeTest);
        TestItem tfcardTest = new TestItem("TFCard", R.drawable.ic_storage_black_48dp);
        testItemList.add(tfcardTest);
        TestItem cameraTest = new TestItem("Camera", R.drawable.ic_camera_black_48dp);
        testItemList.add(cameraTest);
        TestItem fmradioTest = new TestItem("FMRadio", R.drawable.ic_radio_black_48dp);
        testItemList.add(fmradioTest);
        TestItem dialTest = new TestItem("Dial", R.drawable.ic_dialpad_black_48dp);
        testItemList.add(dialTest);
        TestItem flashlightTest = new TestItem("Flashlight", R.drawable.ic_highlight_black_48dp);
        testItemList.add(flashlightTest);
        TestItem versionTest = new TestItem("Version", R.drawable.ic_developer_board_black_48dp);
        testItemList.add(versionTest);
        return testItemList;
    }
}
