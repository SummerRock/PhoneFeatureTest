package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.widget.ScratchView;

/**
 * @author yanxia-Mac
 */
public class TouchTestActivity extends AppCompatActivity {

    private static final String TAG = "ScratchView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_test);

        ScratchView scratchView = findViewById(R.id.scratch_view);
        scratchView.setEraseStatusListener(new ScratchView.EraseStatusListener() {
            @Override
            public void onProgress(int percent) {
                Log.i(TAG, "onProgress mark: " + percent);
            }

            @Override
            public void onCompleted(View view) {
                Log.i(TAG, "onCompleted!");
                scratchView.clear();
            }
        });

    }
}
