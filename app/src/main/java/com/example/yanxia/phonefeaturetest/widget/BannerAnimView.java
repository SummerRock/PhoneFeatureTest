package com.example.yanxia.phonefeaturetest.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class BannerAnimView extends RelativeLayout {

    protected List<String> urls = new ArrayList<>();

    public BannerAnimView(Context context) {
        super(context);
    }

    public BannerAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrls(List<String> imageUrls) {
        urls.clear();
        if (imageUrls != null) {
            urls.addAll(imageUrls);
        }
        for (String url : urls) {
            Glide.with(this).asDrawable().load(url).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).preload();
        }

    }
}
