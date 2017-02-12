package com.example.yanxia.phonefeaturetest.Util;

import android.content.Context;
import android.content.Intent;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.testactivity.VersionTestActivity;


/**
 * Created by yanxia on 2017/2/10.
 */

public class TestUtil {

    public static void startActivityWithAnim(Context context, String testName){
        Intent intent = new Intent(context, VersionTestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
