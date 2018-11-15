package com.example.yanxia.phonefeaturetest.viewpager.ui.viewpager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.TestItem;

import java.util.List;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class ViewPagerTestRvAdapter extends RecyclerView.Adapter<ViewPagerTestRvAdapter.RoundViewHolder> {

    ViewPagerTestRvAdapter() {
    }

    @NonNull
    @Override
    public RoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_circle_item, parent, false);
        return new RoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoundViewHolder holder, int position) {
        holder.testName.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class RoundViewHolder extends RecyclerView.ViewHolder {
        TextView testName;

        RoundViewHolder(View view) {
            super(view);
            testName = view.findViewById(R.id.test_tv);
        }
    }
}
