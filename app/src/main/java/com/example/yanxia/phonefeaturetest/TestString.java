package com.example.yanxia.phonefeaturetest;

import com.example.yanxia.phonefeaturetest.utils.CommonLog;

/**
 * @author yanxia-Mac
 */
public class TestString {

    public static void testStringFormat() {
        String str = String.format(Myapplication.getContext().getString(R.string.test_string_format), "test");
        CommonLog.d(str);
    }
}
