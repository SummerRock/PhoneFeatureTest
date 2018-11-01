package com.example.yanxia.phonefeaturetest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


/**
 * Created by yanxia on 2017/2/10.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<TestItem> mTestList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int imageID);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View testView;
        ImageView testImage;
        TextView testName;

        public ViewHolder(View view) {
            super(view);
            testView = view;
            testImage = (ImageView) view.findViewById(R.id.test_image);
            testName = (TextView) view.findViewById(R.id.test_name);
        }
    }

    public ItemAdapter(List<TestItem> testList) {
        mTestList = testList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                TestItem testItem = mTestList.get(position);
                Toast.makeText(v.getContext(), "you clicked testview " + testItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.testImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                TestItem testItem = mTestList.get(position);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, testItem.getImageId());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TestItem testItem = mTestList.get(position);
        holder.testImage.setImageResource(testItem.getImageId());
        holder.testName.setText(testItem.getName());
        holder.itemView.setTag(testItem.getImageId());
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mTestList.size();
    }
}
