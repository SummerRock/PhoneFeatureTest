package com.example.yanxia.phonefeaturetest.notifyitemtest;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class TestSelectAdapter extends RecyclerView.Adapter<TestSelectAdapter.TestViewHolder> {

    private static final String PAYLOADS = "payloads";
    private int selectPosition = RecyclerView.NO_POSITION;

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dpToPx(44)));
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TestViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.contains(PAYLOADS)) {
            if (position == selectPosition) {
                holder.textView.setSelected(true);
            } else {
                holder.textView.setSelected(false);
            }
            return;
        }
        holder.textView.setText("position: " + position);
        if (position == selectPosition) {
            holder.textView.setSelected(true);
        } else {
            holder.textView.setSelected(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelectPosition(holder.getAdapterPosition());
            }
        });
    }

    public void changeSelectPosition(int position) {
        notifyItemChanged(selectPosition, PAYLOADS);
        selectPosition = position;
        notifyItemChanged(selectPosition, PAYLOADS);
    }

    @Override
    public void onViewRecycled(@NonNull TestViewHolder holder) {
        CommonLog.d("onViewRecycled holder: " + holder.getAdapterPosition());
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull TestViewHolder holder) {
        CommonLog.d("onFailedToRecycleView holder: " + holder.getAdapterPosition());
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        TestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.test_item_text_view);
            // setIsRecyclable(false);
        }
    }
}
