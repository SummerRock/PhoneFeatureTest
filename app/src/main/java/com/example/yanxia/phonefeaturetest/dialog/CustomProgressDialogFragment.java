package com.example.yanxia.phonefeaturetest.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class CustomProgressDialogFragment extends DialogFragment {


    public CustomProgressDialogFragment() {
        // Required empty public constructor
    }

    public static CustomProgressDialogFragment newInstance() {
        return new CustomProgressDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Title");
        progressDialog.setMessage("Loading...");
        return progressDialog;
    }
}
