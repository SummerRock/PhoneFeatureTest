package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;
import com.example.yanxia.phonefeaturetest.utils.SingletonDemo;
import com.noober.background.BackgroundLibrary;

import java.util.List;

public class EasyDrawableActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //https://github.com/JavaNoober/BackgroundLibrary
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_drawable);
        ImageView imageView = findViewById(R.id.drawable_image_view);
        imageView.setBackground(createNewDrawable());
        TextView textView = findViewById(R.id.drawable_text_view);
        textView.setCompoundDrawables(null, null, null, getDrawableFromRes());

        TextView fontTv = findViewById(R.id.font_test);
        Spannable span = new SpannableString(fontTv.getText());
        span.setSpan(new AbsoluteSizeSpan(58, true), fontTv.getText().toString().indexOf("1"), fontTv.getText().toString().indexOf("%"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // span.setSpan(new ForegroundColorSpan(Color.RED), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // span.setSpan(new BackgroundColorSpan(Color.YELLOW), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fontTv.setText(span);
    }

    @Override
    public void onClick(View v) {

    }

    public void testOne(View view) {
        SingletonDemo.getInstance().updateList();
    }

    public void testTwo(View view) {
        List<String> list = SingletonDemo.getInstance().getList();
        Toast.makeText(this, "item size: " + String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
        list.add("temp new one!");
    }

    private Drawable createNewDrawable() {
        float radius = DisplayUtils.dpToPx(1.7f);
        final float[] roundedCorners = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners, null, null));
        //指定填充颜色
        shapeDrawable.getPaint().setColor(Color.YELLOW);
        // 指定填充模式
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.setBounds(0, 0, DisplayUtils.dpToPx(12.7f), DisplayUtils.dpToPx(3.3f));
        return shapeDrawable;
    }

    private Drawable createBorderlessDrawable() {
        // 外部矩形弧度
        float[] outerR = new float[]{8, 8, 8, 8, 8, 8, 8, 8};
        // 内部矩形与外部矩形的距离
        RectF inset = new RectF(100, 100, 50, 50);
        // 内部矩形弧度
        float[] innerRadii = new float[]{20, 20, 20, 20, 20, 20, 20, 20};

        RoundRectShape rr = new RoundRectShape(outerR, null, null);
        ShapeDrawable drawable = new ShapeDrawable(rr);
        //指定填充颜色
        drawable.getPaint().setColor(Color.YELLOW);
        // 指定填充模式
        drawable.getPaint().setStyle(Paint.Style.FILL);
        return drawable;
    }

    public Drawable getDrawableFromRes() {
        return AppCompatResources.getDrawable(this, R.drawable.shape_round_corner_rect);
    }
}
