package com.example.yanxia.phonefeaturetest.horizonRv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yanxia.phonefeaturetest.R;

public class HorizonRvTestActivity extends AppCompatActivity {
    private static final String TAG = "HorizonRvTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_rv_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.horizontal_rv);
        final HorizonRvTestAdapter adapter = new HorizonRvTestAdapter();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            }
        });
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);

        EditText editText = findViewById(R.id.test_scroll_to_position);
        // SnapHelper snapHelper = new PagerSnapHelper();
        // snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        FloatingActionButton fab = findViewById(R.id.fab);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "first visible item :" +
                        linearLayoutManager.findFirstVisibleItemPosition() +
                        " last visible item: " +
                        linearLayoutManager.findLastVisibleItemPosition());
            }
        });
        // fab.setOnClickListener(v -> recyclerView.scrollToPosition(Integer.valueOf(editText.getText().toString())));
        fab.setOnClickListener(v -> {
            int position = Integer.valueOf(editText.getText().toString());
            if (position >= linearLayoutManager.findFirstCompletelyVisibleItemPosition() && position <= linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                return;
            }
            if (position > linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                if (position + 2 > adapter.getItemCount() - 1) {
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                } else {
                    recyclerView.scrollToPosition(position + 2);
                }
            } else if (position < linearLayoutManager.findFirstCompletelyVisibleItemPosition()) {
                if (position - 2 < 0) {
                    recyclerView.scrollToPosition(0);
                } else {
                    recyclerView.scrollToPosition(position - 2);
                }
            }
        });
    }

}
