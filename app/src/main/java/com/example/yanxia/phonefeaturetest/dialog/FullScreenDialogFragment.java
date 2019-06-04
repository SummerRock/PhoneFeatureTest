package com.example.yanxia.phonefeaturetest.dialog;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanxia.phonefeaturetest.R;

public class FullScreenDialogFragment extends DialogFragment {

    public FullScreenDialogFragment() {
        // Required empty public constructor
    }

    public static FullScreenDialogFragment newInstance() {
        return new FullScreenDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogFragmentStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_screen_dialog, container, false);
    }
}
