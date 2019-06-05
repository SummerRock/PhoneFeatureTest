package com.example.yanxia.phonefeaturetest.doublerecyclerview.child;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class DemoChildAdapter extends RecyclerView.Adapter<DemoChildViewHolder> {

    private List<DemoChild> demoChildList;

    public DemoChildAdapter(@NonNull List<DemoChild> childList) {
        this.demoChildList = childList;
    }

    @NonNull
    @Override
    public DemoChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_parent_item, parent, false);
        return new DemoChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoChildViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DemoChildViewHolder holder, int position, @NonNull List<Object> payloads) {
        // if (holder.recyclerView.getAdapter() == null) {
        //     holder.recyclerView.setAdapter(new ChildInfoAdapter(context, list.get(position).getMenuList()));
        // } else {
        //     holder.recyclerView.getAdapter().notifyDataSetChanged();
        // }
    }

    @Override
    public int getItemCount() {
        return demoChildList.size();
    }
}
