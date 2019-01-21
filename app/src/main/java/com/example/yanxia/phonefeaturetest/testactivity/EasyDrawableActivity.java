package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
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
        imageView.setImageDrawable(getDrawableFromRes());
        // imageView.setImageResource(R.drawable.shake_round_corner_rect);
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
        DrawableCompat.setTint(shapeDrawable, Color.YELLOW);
        shapeDrawable.setBounds(0, 0, DisplayUtils.dpToPx(12.7f), DisplayUtils.dpToPx(3.3f));
        return shapeDrawable;
    }

    public Drawable getDrawableFromRes() {
        return AppCompatResources.getDrawable(this, R.drawable.shake_round_corner_rect);
    }
}
