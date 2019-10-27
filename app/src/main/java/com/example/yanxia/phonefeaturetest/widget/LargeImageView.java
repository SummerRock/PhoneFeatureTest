package com.example.yanxia.phonefeaturetest.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class LargeImageView extends View implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private static final String TAG = LargeImageView.class.getName();
    private final Rect mRect;
    private final BitmapFactory.Options mOptions;
    private final Scroller mScroller;
    private int mImageWidth;
    private int mImageHeight;
    private BitmapRegionDecoder mDecoder;
    private int mViewWidth;
    private int mViewHeight;
    private float mScale;
    private Bitmap mBitmap;
    private GestureDetector mGestureDetector;

    private Matrix matrix = new Matrix();

    public LargeImageView(Context context) {
        this(context, null);
    }

    public LargeImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LargeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRect = new Rect();

        mOptions = new BitmapFactory.Options();

        mGestureDetector = new GestureDetector(context, this);
        mScroller = new Scroller(context);

        setOnTouchListener(this);
    }

    public void setImage(@NonNull InputStream is) {
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, mOptions);

        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;

        //开启复用
        mOptions.inMutable = true;
        //设置格式
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        mOptions.inJustDecodeBounds = false;

        try {
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mImageWidth;

        mScale = mViewWidth / (float) mImageWidth;
        mRect.bottom = (int) (mViewHeight / mScale);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged w: " + w + " h: " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDecoder == null) {
            return;
        }

        mOptions.inBitmap = mBitmap;
        mBitmap = mDecoder.decodeRegion(mRect, mOptions);

        //TODO:
        matrix.reset();
        matrix.setScale(mScale, mScale);

        canvas.drawBitmap(mBitmap, matrix, null);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(true);
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(TAG, "onScroll distanceX: " + distanceX + " distanceY: " + distanceY);
        boolean needRefresh = true;
        if (Float.compare(distanceY, 0f) == 0) {
            needRefresh = false;
        }
        mRect.offset(0, (int) distanceY);
        if (mRect.bottom > mImageHeight) {
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight - (int) (mViewHeight / mScale);
            needRefresh = false;
        }
        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = (int) (mViewHeight / mScale);
            needRefresh = false;
        }
        if (needRefresh) {
            invalidate();
        }
        return false;
    }

    @Override
    public void computeScroll() {
        if (mScroller.isFinished()) {
            return;
        }
        if (mScroller.computeScrollOffset()) {
            mRect.top = mScroller.getCurrY();
            mRect.bottom = mRect.top + (int) (mViewHeight / mScale);
            invalidate();
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(0, mRect.top, 0, (int) -velocityY, 0, 0, 0,
                mImageHeight - (int) (mViewHeight / mScale));
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mBitmap != null) {
            mBitmap.recycle();
        }
    }
}
