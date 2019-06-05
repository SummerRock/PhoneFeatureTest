package com.example.yanxia.phonefeaturetest.doublerecyclerview.child;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;

public class DemoChildViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public DemoChildViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.demo_data_tv);
    }
}
