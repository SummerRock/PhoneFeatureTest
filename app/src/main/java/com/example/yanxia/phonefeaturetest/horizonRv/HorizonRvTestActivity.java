package com.example.yanxia.phonefeaturetest.horizonRv;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.testactivity.BaseLoadingActivity;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;
import com.example.yanxia.phonefeaturetest.widget.HorizonItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HorizonRvTestActivity extends BaseLoadingActivity implements View.OnClickListener, RecyclerViewItemClickInterface {

    private NumberPicker numberPicker;
    private LinearLayoutManager linearLayoutManager;
    private HorizonRvTestAdapter adapter;
    private RecyclerView recyclerView;
    private List<String> stringList = new ArrayList<>();

    private int listIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_rv_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.horizontal_rv);
        adapter = new HorizonRvTestAdapter(stringList, this);
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
        linearLayoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        numberPicker = findViewById(R.id.test_scroll_to_position);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(39);
        numberPicker.setValue(0);
        // String[] stockArr = new String[stringList.size()];
        // stockArr = stringList.toArray(stockArr);
        // numberPicker.setDisplayedValues(stockArr);

        // SnapHelper snapHelper = new PagerSnapHelper();
        // snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new HorizonItemDecoration(DisplayUtils.dpToPx(6)));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("rv_onScrolled", "first visible item :" +
                        linearLayoutManager.findFirstVisibleItemPosition() +
                        " last visible item: " +
                        linearLayoutManager.findLastVisibleItemPosition());
            }
        });

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Log.d("ChildAttachStateChange", "onChildViewAttachedToWindow position :" + position);
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Log.d("ChildAttachStateChange", "onChildViewDetachedFromWindow position :" + position);
            }
        });
        new GetDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class GetDataAsync extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            for (; listIndex < 30; listIndex++) {
                SystemClock.sleep(50);
                stringList.add(String.valueOf(listIndex));
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<String> list) {
            super.onPostExecute(list);
            hideLoading();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void jumpToPosition(View view) {
        int position = numberPicker.getValue();
        recyclerView.smoothScrollToPosition(position);
    }

    public void jumpToPosition(int position) {
        int jumpPosition;

        if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == linearLayoutManager.findFirstVisibleItemPosition()) {
            if (position - linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 2) {
                return;
            }
            if (position - linearLayoutManager.findFirstCompletelyVisibleItemPosition() > 2) {
                jumpPosition = position + 2;
            } else {
                jumpPosition = position - 2;
            }
        } else {
            if (position - linearLayoutManager.findFirstVisibleItemPosition() == 2) {
                return;
            }
            if (position - linearLayoutManager.findFirstVisibleItemPosition() > 2) {
                jumpPosition = position + 2;
            } else {
                jumpPosition = position - 2;
            }
        }

        if (jumpPosition < 0) {
            jumpPosition = 0;
        }
        if (jumpPosition > adapter.getItemCount() - 1) {
            jumpPosition = adapter.getItemCount() - 1;
        }
        recyclerView.smoothScrollToPosition(jumpPosition);
    }

    @Override
    public void onItemClick(int position) {
        jumpToPosition(position);
    }

    public void notifyItemInserted(View view) {
        int position = numberPicker.getValue();
        adapter.notifyItemInserted(position);
    }

    public void notifyItemRemoved(View view) {
        int position = numberPicker.getValue();
        adapter.notifyItemRemoved(position);
    }

    public void addItem(View view) {
        int position = numberPicker.getValue();
        stringList.add(position, String.valueOf(++listIndex));
        adapter.notifyDataSetChanged();
    }

    public void removeItem(View view) {
        int position = numberPicker.getValue();
        stringList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
