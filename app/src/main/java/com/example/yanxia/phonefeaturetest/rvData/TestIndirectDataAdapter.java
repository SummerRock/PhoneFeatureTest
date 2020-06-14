package com.example.yanxia.phonefeaturetest.rvData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxia-Mac
 */
public final class TestIndirectDataAdapter extends RecyclerView.Adapter<TestIndirectDataAdapter.TestViewHolder> {

    private final List<String> stringList = new ArrayList<>();

    public void setStringList(List<String> list) {
        stringList.clear();
        stringList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull final TestViewHolder holder, int position, @NonNull List<Object> payloads) {
        String ss = stringList.get(position);
        holder.textView.setText(ss);
    }

    @Override
    public int getItemCount() {
        if (stringList == null) {
            return 0;
        }
        return stringList.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        TestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.test_item_text_view);
        }

    }
}
