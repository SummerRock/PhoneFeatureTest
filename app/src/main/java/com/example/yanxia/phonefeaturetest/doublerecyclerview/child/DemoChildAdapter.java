package com.example.yanxia.phonefeaturetest.doublerecyclerview.child;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.onGetCurrentDemoChildListener;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class DemoChildAdapter extends RecyclerView.Adapter<DemoChildViewHolder> {

    private List<DemoChild> demoChildList;
    private onGetCurrentDemoChildListener getCurrentDemoChildListener;

    public DemoChildAdapter(@NonNull List<DemoChild> childList, onGetCurrentDemoChildListener getCurrentDemoChildListener) {
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
        holder.textView.setText(demoChild.getData());
    }

    @Override
    public int getItemCount() {
        return demoChildList.size();
    }
}
