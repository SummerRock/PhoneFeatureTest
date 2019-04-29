package com.example.yanxia.phonefeaturetest.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanxia.phonefeaturetest.R;

public class CustomWidthDialogFragment extends DialogFragment {

    public CustomWidthDialogFragment() {
        // Required empty public constructor
    }

    public static CustomWidthDialogFragment newInstance() {
        return new CustomWidthDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.customWidthDialog);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_width_dialog, container, false);
    }
}
