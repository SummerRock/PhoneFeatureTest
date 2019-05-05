package com.example.yanxia.phonefeaturetest;

import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.utils.CommonLog;

/**
 * @author yanxia-Mac
 */
public class TestString {

    public static void testStringFormat() {
        String str = String.format(MyApplication.getContext().getString(R.string.test_string_format), MyApplication.getContext().getString(R.string.app_name));
        Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT).show();
        CommonLog.d(str);
    }
}
