package com.example.yanxia.phonefeaturetest.common;

import android.support.annotation.Nullable;

public interface CommonFinishInterface {
    void onSuccess();

    void onFailed(@Nullable Exception e);
}
