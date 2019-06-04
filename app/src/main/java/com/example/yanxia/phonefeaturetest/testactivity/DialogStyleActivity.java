package com.example.yanxia.phonefeaturetest.testactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.dialog.CustomProgressDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.CustomWidthDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.FullScreenDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.TermsDialogFragment;

public class DialogStyleActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomProgressDialogFragment customProgressDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_style);
    }

    @Override
    public void onClick(View v) {

    }

    public void showNormalDialog(View view) {
        // android.R.style.Theme_DeviceDefault_Light_Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogColorTheme);
        builder.setMessage("Message hello!").setCancelable(false)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                .show();
    }

    public void showCustomDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.customTransparentDialog);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        builder.setView(dialogView).setCancelable(true);
        Dialog dialog = builder.show();
        View okButton = dialogView.findViewById(R.id.exit_dialog_ok_btn);
        okButton.setOnClickListener(v -> dialog.dismiss());
        View cancelButton = dialogView.findViewById(R.id.exit_dialog_cancel_btn);
        cancelButton.setOnClickListener(v -> dialog.dismiss());
    }

    public void showCustomDialogFragment(View view) {
        TermsDialogFragment.newInstance().show(getSupportFragmentManager(), TermsDialogFragment.class.getSimpleName());
    }

    public void showFullScreenDialogFragment(View view) {
        FullScreenDialogFragment.newInstance().show(getSupportFragmentManager(), FullScreenDialogFragment.class.getSimpleName());
    }

    public void showCustomWidthDialogFragment(View view) {
        CustomWidthDialogFragment.newInstance().show(getSupportFragmentManager(), CustomWidthDialogFragment.class.getSimpleName());
    }

    public void showProgressDialogFragment(View view) {
        new ShowProgressDialogTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class ShowProgressDialogTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (customProgressDialogFragment == null) {
                customProgressDialogFragment = CustomProgressDialogFragment.newInstance();
            }
            customProgressDialogFragment.show(getSupportFragmentManager(), CustomProgressDialogFragment.class.getSimpleName());
        }

        @Override
        protected void onPostExecute(Object o) {
            if (customProgressDialogFragment != null) {
                customProgressDialogFragment.dismiss();
            }
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            SystemClock.sleep(5000);
            return null;
        }
    }
}
