package com.example.yanxia.phonefeaturetest.notifyitemtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yanxia.phonefeaturetest.R;

/**
 * @author yanxia-Mac
 */
public class RecyclerViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        RecyclerView recyclerView = findViewById(R.id.test_rv);
        GridLayoutManager layout = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        final TestSelectAdapter adapter = new TestSelectAdapter();
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeSelectPosition(20);
            }
        });
    }
}
