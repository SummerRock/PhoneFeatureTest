package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;
import com.example.yanxia.phonefeaturetest.handlerthread.HandlerThreadSingletonDemo;
import com.example.yanxia.phonefeaturetest.widget.TextWithExtraView;

import java.util.List;

public class EasyDrawableActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //https://github.com/JavaNoober/BackgroundLibrary
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_drawable);
        ImageView imageView = findViewById(R.id.drawable_image_view);
        imageView.setBackground(createNewDrawable());
        TextView textView = findViewById(R.id.drawable_text_view);
        textView.setCompoundDrawables(null, null, null, getDrawableFromRes());


        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        // 设置 Lottie 远程 JSON 文件的 URL
        String remoteJsonUrl = "https://assets-v2.lottiefiles.com/a/58c243b0-65e1-11ee-8eda-dbb22b4eb863/EAC0n56FPV.json";
        lottieAnimationView.setAnimationFromUrl(remoteJsonUrl);
        lottieAnimationView.playAnimation();

        TextWithExtraView text = findViewById(R.id.text_with_extra_view);
        TextView temp = new TextView(this);
        temp.setText("你好");
        temp.setTextSize(9);
        temp.setBackgroundColor(Color.CYAN);
        text.setText("一二三四一二三四一二三四一二三四一二三四一二三四一二三四一二三四");
        text.setExtraView(temp, null, DisplayUtils.spToPx(9));
    }

    @Override
    public void onClick(View v) {

    }

    public void testOne(View view) {
        HandlerThreadSingletonDemo.getInstance().updateList();
    }

    public void testTwo(View view) {
        List<String> list = HandlerThreadSingletonDemo.getInstance().getList();
        Toast.makeText(this, "item size: " + list.size(), Toast.LENGTH_SHORT).show();
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
