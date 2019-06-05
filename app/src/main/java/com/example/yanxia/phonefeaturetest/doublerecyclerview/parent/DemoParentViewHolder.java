package com.example.yanxia.phonefeaturetest.doublerecyclerview.parent;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DemoParentViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView;

    public DemoParentViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
    }
}
