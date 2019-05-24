package com.example.yanxia.phonefeaturetest.testFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.CommonLog;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

public class BitmapTestFragment extends BaseTestFragment implements View.OnClickListener {

    private Bitmap originBitmap;
    private ImageView imageView;

    public static BitmapTestFragment newInstance() {
        return new BitmapTestFragment();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl_1, options);
        CommonLog.d("");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bitmap_dialog, container, false);
        View button = rootView.findViewById(R.id.bitmap_fragment_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCircle();
            }
        });
        imageView = rootView.findViewById(R.id.bitmap_test_image);
        return rootView;
    }

    public void addCircle() {
        Bitmap bitmap = DisplayUtils.addColorBackgroundForBitmap(originBitmap, Color.CYAN);
        Glide.with(this).asBitmap().load(bitmap).apply(new RequestOptions().circleCrop()).into(imageView);
    }
}
