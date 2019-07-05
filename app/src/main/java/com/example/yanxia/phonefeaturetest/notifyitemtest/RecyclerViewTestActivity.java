package com.example.yanxia.phonefeaturetest.notifyitemtest;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;
import com.example.yanxia.phonefeaturetest.widget.CustomRelativeLayout;

/**
 * @author yanxia-Mac
 */
public class RecyclerViewTestActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomRelativeLayout customRelativeLayout;
    private TestSelectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        customRelativeLayout = findViewById(R.id.custom_relative_layout);
        RecyclerView recyclerView = findViewById(R.id.test_rv);
        GridLayoutManager layout = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        adapter = new TestSelectAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new CustomItemDecoration(DisplayUtils.dpToPx(2)));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // Log.d("rv_test_scroll_state", "newState: " + newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("rv_test_scrolling", "dx: " + dx + " dy: " + dy + " canScrollVerticalUp: " + recyclerView.canScrollVertically(-1)
                        + " VerticalScrollOffset: " + recyclerView.computeVerticalScrollOffset()
                        + " VerticalScrollExtent: " + recyclerView.computeVerticalScrollExtent()
                        + " VerticalScrollRange: " + recyclerView.computeVerticalScrollRange());

                if (dy > 0) {
                    if (Math.abs(dy) > 3) {
                        Log.d("kobe", "should expand");
                        customRelativeLayout.expand();
                    }
                } else {
                    if (Math.abs(dy) > 0 && !recyclerView.canScrollVertically(-1)) {
                        Log.d("kobe", "should shrink");
                        customRelativeLayout.shrink();
                    }
                }
            }
        });

        Button button = findViewById(R.id.test_button);
        button.setOnClickListener(v -> adapter.changeSelectPosition(20));
    }

    @Override
    public void onClick(View v) {

    }

    public void notifyDataSetChangedTest(View view) {
        adapter.notifyDataSetChanged();
    }

    public void notifyItemChangedTest(View view) {
        adapter.notifyItemChanged(20);
    }

    public void notifyItemAnimation(View view) {
        adapter.notifyItemChanged(1, TestSelectAdapter.PAYLOADS_ANIMATION);
    }

    public class CustomItemDecoration extends RecyclerView.ItemDecoration {

        private int gap;

        CustomItemDecoration(int gap) {
            this.gap = gap;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            // int position = parent.getChildAdapterPosition(view);
            outRect.set(gap, gap, gap, gap);
        }
    }
}
