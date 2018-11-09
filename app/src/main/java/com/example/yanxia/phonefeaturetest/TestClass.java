package com.example.yanxia.phonefeaturetest;

import com.example.yanxia.phonefeaturetest.utils.CommonLog;

import java.util.List;

public class TestClass {

    // public static String method(List<String> list) {
    //     CommonLog.d("string list");
    //     return "";
    // }
    //
    // public static int method(List<Integer> list) {
    //     CommonLog.d("string list");
    //     return 0;
    // }

    public static void mainTest() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        CommonLog.d(c == d ? "true" : "false");
    }
}
