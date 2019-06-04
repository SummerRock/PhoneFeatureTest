package com.example.yanxia.phonefeaturetest.horizonRv;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class HorizonRvTestAdapter extends RecyclerView.Adapter<HorizonRvTestAdapter.HorizonTestViewHolder> {

    private List<String> stringList;
    private RecyclerViewItemClickInterface recyclerViewItemClickInterface;

    HorizonRvTestAdapter(@NonNull List<String> stringList, @Nullable RecyclerViewItemClickInterface itemClickInterface) {
        this.stringList = stringList;
        recyclerViewItemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public HorizonTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_test_item, parent, false);
        HorizonTestViewHolder horizonTestViewHolder = new HorizonTestViewHolder(view);
        horizonTestViewHolder.itemView.setOnClickListener(v -> {
            int position = horizonTestViewHolder.getAdapterPosition();
            if (recyclerViewItemClickInterface != null) {
                recyclerViewItemClickInterface.onItemClick(position);
            }
            // Toast.makeText(parent.getContext(), "Click position: " + position + " item content: " + stringList.get(position), Toast.LENGTH_SHORT).show();
        });
        return horizonTestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HorizonTestViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HorizonTestViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.positionNumTv.setText(stringList.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull HorizonTestViewHolder holder) {
        CommonLog.d("onViewRecycled holder: " + holder.getAdapterPosition());
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull HorizonTestViewHolder holder) {
        CommonLog.d("onFailedToRecycleView holder: " + holder.getAdapterPosition());
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class HorizonTestViewHolder extends RecyclerView.ViewHolder {
        private TextView positionNumTv;

        HorizonTestViewHolder(View itemView) {
            super(itemView);
            positionNumTv = itemView.findViewById(R.id.test_position_number_tv);
        }
    }
}
