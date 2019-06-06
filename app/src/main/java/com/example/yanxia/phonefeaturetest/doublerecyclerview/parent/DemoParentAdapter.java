package com.example.yanxia.phonefeaturetest.doublerecyclerview.parent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.child.DemoChildAdapter;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoParent;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class DemoParentAdapter extends RecyclerView.Adapter<DemoParentViewHolder> {

    private List<DemoParent> demoParentList;
    private int selectPosition;

    public DemoParentAdapter(@NonNull List<DemoParent> demoParentList) {
        this.demoParentList = demoParentList;
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

    @Override
    public void onBindViewHolder(@NonNull DemoParentViewHolder holder, int position, @NonNull List<Object> payloads) {

        DemoParent demoParent = demoParentList.get(position);
        boolean selected = position == selectPosition;
        boolean isSingleItem = demoParent.isSingleParent();

        if (selected) {
            if (isSingleItem) {
                holder.name.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.GONE);
            } else {
                holder.name.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.VISIBLE);

                if (holder.recyclerView.getAdapter() == null) {
                    holder.recyclerView.setAdapter(new DemoChildAdapter(demoParent.getDemoChildList()));
                } else {
                    holder.recyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        } else {
            holder.name.setVisibility(View.VISIBLE);
            holder.recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return demoParentList.size();
    }
}
