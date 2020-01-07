package com.example.yanxia.phonefeaturetest.viewpager2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.horizonRv.RecyclerViewItemClickInterface;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {

    private List<String> stringList;
    private RecyclerViewItemClickInterface recyclerViewItemClickInterface;

    ViewPager2Adapter(@NonNull List<String> stringList, @Nullable RecyclerViewItemClickInterface itemClickInterface) {
        this.stringList = stringList;
        recyclerViewItemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager2_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if (recyclerViewItemClickInterface != null) {
                recyclerViewItemClickInterface.onItemClick(position);
            }
            // Toast.makeText(parent.getContext(), "Click position: " + position + " item content: " + stringList.get(position), Toast.LENGTH_SHORT).show();
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.positionNumTv.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView positionNumTv;

        ViewHolder(View itemView) {
            super(itemView);
            positionNumTv = itemView.findViewById(R.id.tv_text);
        }
    }
}
