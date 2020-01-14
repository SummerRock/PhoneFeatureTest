package com.example.yanxia.phonefeaturetest.dialog;

import android.app.Dialog;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

public abstract class BaseCustomWidthDialogFragment extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(DisplayUtils.dpToPx(300), ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    protected abstract int getWidth();
}
