package com.example.yanxia.phonefeaturetest.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

public class TextWithExtraView extends RelativeLayout {

    protected TextView textView;
    protected Runnable runnable;

    public TextWithExtraView(Context context) {
        this(context, null);
    }

    public TextWithExtraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextWithExtraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.text_with_extra_view, this, true);
        textView = findViewById(R.id.text_view_real);
    }

    public TextWithExtraView setText(String text) {
        textView.setText(text);
        return this;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(runnable);
    }

    public void setExtraView(@Nullable View view, @Nullable RelativeLayout.LayoutParams layoutParams, int expectHeight) {
        runnable = new Runnable() {
            @Override
            public void run() {
                attachExtraView(view, layoutParams, expectHeight);
            }
        };
        post(runnable);
    }

    private void attachExtraView(View view, @Nullable RelativeLayout.LayoutParams layoutParams, int expectHeight) {
        if (view == null || textView == null) {
            return;
        }
        if (textView.getText() == null || textView.getText().length() == 0) {
            return;
        }
        if (view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        Layout layout = textView.getLayout();
        if (layout == null) {
            return;
        }
        int lineCount = layout.getLineCount();
        if (lineCount == 0) {
            return;
        }
        int lineTop = layout.getLineTop(lineCount - 1);
        float lineRight = layout.getLineRight(lineCount - 1);
        int lineHeight = layout.getLineBottom(lineCount - 1) - lineTop;
        int marginTop = 0;
        if (expectHeight < lineHeight) {
            marginTop = lineTop + (lineHeight - expectHeight) / 2;
        }

        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            layoutParams.leftMargin = (int) (lineRight + DisplayUtils.dpToPx(4) + textView.getPaddingLeft());
            layoutParams.topMargin = marginTop;
        }
        addView(view, layoutParams);
    }
}
