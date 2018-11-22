package com.example.yanxia.phonefeaturetest.horizonRv;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class HorizonRvTestAdapter extends RecyclerView.Adapter<HorizonRvTestAdapter.HorizonTestViewHolder> {

    @NonNull
    @Override
    public HorizonTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_test_item, parent, false);
        return new HorizonTestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizonTestViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HorizonTestViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            holder.textView.setText("pos: " + position);
        } else {
            holder.textView.setText("payloads is not empty!");
        }
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
        return 30;
    }

    class HorizonTestViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        HorizonTestViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.test_image);
            textView = itemView.findViewById(R.id.test_name);
        }
    }
}
