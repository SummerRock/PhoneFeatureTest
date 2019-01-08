package com.example.yanxia.phonefeaturetest.notifyitemtest;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

/**
 * @author yanxia-Mac
 */
public class RecyclerViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        RecyclerView recyclerView = findViewById(R.id.test_rv);
        GridLayoutManager layout = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        final TestSelectAdapter adapter = new TestSelectAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new CustomItemDecoration(DisplayUtils.dpToPx(4)));

        Button button = findViewById(R.id.test_button);
        button.setOnClickListener(v -> adapter.changeSelectPosition(20));
    }

    public class CustomItemDecoration extends RecyclerView.ItemDecoration {

        private int gap;

        CustomItemDecoration(int gap) {
            this.gap = gap;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.set(gap, gap, gap, gap);
        }
    }
}
