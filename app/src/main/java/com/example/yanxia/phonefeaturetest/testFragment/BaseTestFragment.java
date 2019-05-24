package com.example.yanxia.phonefeaturetest.testFragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.yanxia.phonefeaturetest.R;

public class BaseTestFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogFragmentStyle);
    }
}
