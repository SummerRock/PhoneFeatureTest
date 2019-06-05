package com.example.yanxia.phonefeaturetest.doublerecyclerview.parent;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;

public class DemoParentViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public RecyclerView recyclerView;

    public DemoParentViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.parent_item_name);
        recyclerView = itemView.findViewById(R.id.parent_item_rv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
    }
}
