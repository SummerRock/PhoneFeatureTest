package com.example.yanxia.phonefeaturetest.horizonRv;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class FlexTestAdapter extends RecyclerView.Adapter<FlexTestAdapter.TestViewHolder> {

    private List<String> stringList;
    private RecyclerViewItemClickInterface recyclerViewItemClickInterface;

    public FlexTestAdapter(@NonNull List<String> stringList, @Nullable RecyclerViewItemClickInterface itemClickInterface) {
        this.stringList = stringList;
        recyclerViewItemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_item, parent, false);
        TestViewHolder horizonTestViewHolder = new TestViewHolder(view);
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
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        TestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tab_text);
        }
    }
}
