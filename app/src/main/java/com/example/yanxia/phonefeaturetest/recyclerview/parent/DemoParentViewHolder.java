package com.example.yanxia.phonefeaturetest.recyclerview.parent;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DemoParentViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView;

    public DemoParentViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView;
    }
}
