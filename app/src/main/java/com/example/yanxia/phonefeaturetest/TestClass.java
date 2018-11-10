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
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        printBoolean(c == d);
        printBoolean(e == f);
        printBoolean(c == (a + b));
        printBoolean(c.equals(a + b));
        printBoolean(g == (a + b));
        printBoolean(g.equals(a + b));
    }

    private static void printBoolean(boolean b) {
        CommonLog.d(String.valueOf(b));
    }
}
