package com.example.yanxia.phonefeaturetest.doublerecyclerview.parent;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.OnGetCurrentDemoChildListener;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.child.DemoChildAdapter;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoChild;
import com.example.yanxia.phonefeaturetest.doublerecyclerview.data.DemoParent;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class DemoParentAdapter extends RecyclerView.Adapter<DemoParentViewHolder> {

    private List<DemoParent> demoParentList;
    private int selectPosition;
    private OnGetCurrentDemoChildListener getCurrentDemoChildListener;

    public DemoParentAdapter(List<DemoParent> demoParentList, OnGetCurrentDemoChildListener getCurrentDemoChildListener) {
        this.demoParentList = demoParentList;
        this.getCurrentDemoChildListener = getCurrentDemoChildListener;
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

        holder.parentName.setText(demoParent.getName());

        if (selected) {
            if (isSingleItem) {
                holder.parentName.setVisibility(View.VISIBLE);
                holder.parentName.setTextColor(Color.BLACK);
                holder.recyclerView.setVisibility(View.GONE);
            } else {
                holder.parentName.setVisibility(View.GONE);
                holder.parentName.setTextColor(Color.WHITE);
                holder.recyclerView.setVisibility(View.VISIBLE);

                if (holder.recyclerView.getAdapter() == null) {
                    holder.recyclerView.setAdapter(new DemoChildAdapter(demoParent.getDemoChildList(), getCurrentDemoChildListener));
                } else {
                    ((DemoChildAdapter) holder.recyclerView.getAdapter()).setData(demoParent.getDemoChildList());
                }
            }
        } else {
            holder.parentName.setVisibility(View.VISIBLE);
            holder.parentName.setTextColor(Color.WHITE);
            holder.recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return demoParentList.size();
    }

    public void changeSelectPosition(DemoChild demoChild) {
        notifyItemChanged(selectPosition);
        selectPosition = getPosition(demoChild);
        notifyItemChanged(selectPosition);
    }

    private int getPosition(DemoChild demoChild) {
        for (int i = 0; i < demoParentList.size(); i++) {
            if (TextUtils.equals(demoChild.getParentName(), demoParentList.get(i).getName())) {
                return i;
            }
        }
        return 0;
    }
}
