package com.example.yanxia.phonefeaturetest.doublerecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoParent;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.parent.DemoParentAdapter;

import java.util.List;

public class DoubleRecyclerViewActivity extends AppCompatActivity {

    private List<DemoParent> demoParentList = DemoDataManager.getInstance().getDemoGroupList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_demo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new DemoParentAdapter(demoParentList));
    }
}
