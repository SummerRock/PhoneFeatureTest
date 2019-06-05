package com.example.yanxia.phonefeaturetest.doublerecyclerview.parent;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoParent;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class DemoParentAdapter extends RecyclerView.Adapter<DemoParentViewHolder> {

    private List<DemoParent> demoGroupList;

    public DemoParentAdapter(@NonNull List<DemoParent> demoGroupList) {
        this.demoGroupList = demoGroupList;
    }

    @NonNull
    @Override
    public DemoParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_parent_item, parent, false);
        return new DemoParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoParentViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DemoParentViewHolder holder, int position, @NonNull List<Object> payloads) {
        // if (holder.recyclerView.getAdapter() == null) {
        //     holder.recyclerView.setAdapter(new ChildInfoAdapter(context, list.get(position).getMenuList()));
        // } else {
        //     holder.recyclerView.getAdapter().notifyDataSetChanged();
        // }
    }

    @Override
    public int getItemCount() {
        return demoGroupList.size();
    }
}