package com.example.yanxia.phonefeaturetest.widget;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class HorizonItemDecoration extends RecyclerView.ItemDecoration {
    private int columnSpace;

    public HorizonItemDecoration(int columnSpace) {
        this.columnSpace = columnSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // int position = parent.getChildAdapterPosition(view);
        int leftPadding = columnSpace / 2;
        int rightPadding = columnSpace / 2;
        outRect.set(leftPadding, 0, rightPadding, 0);
    }
}