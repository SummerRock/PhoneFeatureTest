package com.example.yanxia.phonefeaturetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class TestItemAdapter extends RecyclerView.Adapter<TestItemAdapter.SimpleViewHolder> {
    private List<TestItem> mTestList;
    private OnTestItemClickListener testItemClickListener;

    public interface OnTestItemClickListener {
        void onItemClick(TestItem testItem);
    }

    TestItemAdapter(@NonNull List<TestItem> testList, @Nullable OnTestItemClickListener listener) {
        mTestList = testList;
        testItemClickListener = listener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        SimpleViewHolder holder = new SimpleViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            if (testItemClickListener != null) {
                final TestItem testItem = mTestList.get(holder.getAdapterPosition());
                testItemClickListener.onItemClick(testItem);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        final TestItem testItem = mTestList.get(position);
        holder.testImage.setImageResource(testItem.getImageId());
        holder.testName.setText(testItem.getName());
    }

    @Override
    public int getItemCount() {
        return mTestList.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        View testView;
        ImageView testImage;
        TextView testName;

        SimpleViewHolder(View view) {
            super(view);
            testView = view;
            testImage = view.findViewById(R.id.test_image);
            testName = view.findViewById(R.id.test_name);
        }
    }
}
