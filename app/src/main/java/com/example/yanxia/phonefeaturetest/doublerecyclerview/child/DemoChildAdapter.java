package com.example.yanxia.phonefeaturetest.doublerecyclerview.child;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.OnGetCurrentDemoChildListener;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class DemoChildAdapter extends RecyclerView.Adapter<DemoChildViewHolder> {

    private List<DemoChild> demoChildList;
    private OnGetCurrentDemoChildListener getCurrentDemoChildListener;

    public DemoChildAdapter(@NonNull List<DemoChild> childList, OnGetCurrentDemoChildListener getCurrentDemoChildListener) {
        this.demoChildList = childList;
        this.getCurrentDemoChildListener = getCurrentDemoChildListener;
    }

    @NonNull
    @Override
    public DemoChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_child_item, parent, false);
        return new DemoChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoChildViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DemoChildViewHolder holder, int position, @NonNull List<Object> payloads) {
        DemoChild demoChild = demoChildList.get(position);
        boolean isSelected = getCurrentDemoChildListener.getCurrentDemoChild().equals(demoChild);
        holder.textView.setText(demoChild.getData());
        if (isSelected) {
            holder.textView.setTextColor(Color.BLACK);
        } else {
            holder.textView.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return demoChildList.size();
    }

    public void setData(@NonNull List<DemoChild> list) {
        demoChildList = list;
        notifyDataSetChanged();
    }
}
