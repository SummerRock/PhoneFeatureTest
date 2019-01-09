package com.example.yanxia.phonefeaturetest;

import com.example.yanxia.phonefeaturetest.utils.CommonLog;

public class TestClass {

    static final String TEST_STRING;

    static {
        CommonLog.d("TestClass static init!");
        // try {
        //     Thread.sleep(2000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        TEST_STRING = "testOne";
        CommonLog.d("TestClass static end!");
    }

    {
        CommonLog.d("TestClass common init!");
    }

    /**
     * https://www.cnblogs.com/dolphin0520/p/3780005.html
     */
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

    private Integer test() {
        Integer result;
        try {
            result = 10 / 0;
            return result;
        } catch (Exception e) {
            result = 35;
            return result;
        } finally {
            result = 46;
            // return result;
        }
    }

    public void throwException() throws Exception {
        CommonLog.d("haha");
    }

    public void noException(int a) {
        int b = a + 2;
        CommonLog.d("haha" + b);
    }

}
