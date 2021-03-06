package com.example.yanxia.phonefeaturetest.testactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.dialog.BottomDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.CustomProgressDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.FullScreenDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.NormalDialogFragment;
import com.example.yanxia.phonefeaturetest.dialog.TermsDialogFragment;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

public class DialogStyleActivity extends AppCompatActivity {

    private CustomProgressDialogFragment customProgressDialogFragment;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_style);
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        Button button = findViewById(R.id.dialog_test_btn_1);
        button.post(new Runnable() {
            @Override
            public void run() {
                CommonLog.logAsOne("DialogStyleActivity button run");
                View view = LayoutInflater.from(DialogStyleActivity.this).inflate(R.layout.popup_window_demo, null);
                PopupWindow popupWindow = new PopupWindow(view, DisplayUtils.dpToPx(160), DisplayUtils.dpToPx(100));
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAsDropDown(button);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        CommonLog.logAsOne("DialogStyleActivity onWindowFocusChanged: " + hasFocus);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            item.getActionView();
        }
        return super.onOptionsItemSelected(item);
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

    public void showAlertDialog(View view) {
        if (alertDialog != null) {
            alertDialog.show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("message")
                .setCancelable(true)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void showProgressDialogFragment(View view) {
        new ShowProgressDialogTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void showBottomFragment(View view) {
        BottomDialogFragment.newInstance().show(getSupportFragmentManager(), BottomDialogFragment.class.getSimpleName());
    }

    public void showNormalFragment(View view) {
        NormalDialogFragment.newInstance().show(getSupportFragmentManager(), NormalDialogFragment.class.getSimpleName());
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
