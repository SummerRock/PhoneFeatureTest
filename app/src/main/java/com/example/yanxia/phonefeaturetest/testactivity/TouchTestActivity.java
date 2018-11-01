package com.example.yanxia.phonefeaturetest.testactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clock.scratch.ScratchView;
import com.example.yanxia.phonefeaturetest.R;

/**
 * @author yanxia-Mac
 */
public class TouchTestActivity extends AppCompatActivity {

    private final static String TAG = "TouchTestActivity";
    private ScratchView mScratchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_test);

        mScratchView = findViewById(R.id.scratch_view);
        mScratchView.setMaxPercent(90);
        mScratchView.setEraseStatusListener(new ScratchView.EraseStatusListener() {
            @Override
            public void onProgress(int percent) {
                setTitle("TouchTest clear percent: " + percent + "%");
            }

            @Override
            public void onCompleted(View view) {
                Toast.makeText(TouchTestActivity.this, "completed !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
