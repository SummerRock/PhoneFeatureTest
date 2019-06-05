package com.example.yanxia.phonefeaturetest.doublerecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;

public class DoubleRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler_view);

        recyclerView = findViewById(R.id.recycler_view_demo);
    }
}
