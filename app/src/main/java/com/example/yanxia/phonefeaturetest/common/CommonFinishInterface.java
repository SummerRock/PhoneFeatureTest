package com.example.yanxia.phonefeaturetest.common;

import androidx.annotation.Nullable;

public interface CommonFinishInterface {
    void onSuccess();

    void onFailed(@Nullable Exception e);
}
