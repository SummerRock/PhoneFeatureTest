package com.example.yanxia.phonefeaturetest.rvData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TestIndirectDataAdapter adapter;
    private final List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_data);
        recyclerView = findViewById(R.id.test_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new TestIndirectDataAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setStringList(getDataList(30));
    }

    private List<String> getDataList(int size) {
        strings.clear();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add("TEST_STR" + i);
        }
        strings.addAll(data);
        return strings;
    }

    public void test1(View view) {
        recyclerView.fling(0, 20000);
    }

    public void test2(View view) {
        adapter.setStringList(getDataList(20));
    }

    public void test3(View view) {
        strings.clear();
    }
}
