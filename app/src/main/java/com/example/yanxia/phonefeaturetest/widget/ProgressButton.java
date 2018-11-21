package com.example.yanxia.phonefeaturetest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.example.yanxia.phonefeaturetest.R;

import java.util.Locale;

/**
 * 下载进度
 */

public class ProgressButton extends AppCompatButton {

    private float mProgress; //当前进度
    @SuppressWarnings("FieldCanBeLocal")
    private static float MAX_PROGRESS = 100f; //最大进度：默认为100
    @SuppressWarnings("FieldCanBeLocal")
    private static float MIN_PROGRESS = 0f;//最小进度：默认为0
    private boolean isShowProgress;  //是否展示进度
    private boolean isFinish = true; //状态是否结束
    private float cornerRadius;

    private String progressText; //进度提示文本
    private int textColor; //进度提示文本颜色
    private float textSize; //文本大小
    private Paint textPaint;
    private Rect textRect;

    private Paint pgPaint;
    private Bitmap pgBitmap; //进度条 bitmap
    private Canvas pgCanvas;
    private int progressColor;
    private int borderWidth;
    private RectF bgRectF;
    private BitmapShader bitmapShader;

    private Paint backgroundPaint;

    private int backgroundColor = Color.LTGRAY;

    private static final int BACKGROUND_TYPE_STROKE = 0;
    private static final int BACKGROUND_TYPE_FILL = 1;

    private int backgroundType = BACKGROUND_TYPE_FILL;

    public ProgressButton(Context context) {
        this(context, null);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textRect = new Rect();

        pgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pgPaint.setStyle(Paint.Style.FILL);

        float density = context.getResources().getDisplayMetrics().density;

        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressButton);

        try {
            cornerRadius = attr.getDimension(R.styleable.ProgressButton_buttonCornerRadius, density * 4);

            int defTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
            textColor = attr.getColor(R.styleable.ProgressButton_finishColor, defTextColor);

            textSize = attr.getDimension(R.styleable.ProgressButton_progressTextSize, density * 10);

            isShowProgress = attr.getBoolean(R.styleable.ProgressButton_showProgressNum, true);

            progressColor = attr.getColor(R.styleable.ProgressButton_progressColor, getResources().getColor(R.color.colorPrimary));
            backgroundColor = attr.getColor(R.styleable.ProgressButton_progressButtonBgColor, Color.LTGRAY);
            borderWidth = (int) attr.getDimension(R.styleable.ProgressButton_backgroundBorderWidth, density * 3);

            backgroundType = attr.getInt(R.styleable.ProgressButton_backgroundType, BACKGROUND_TYPE_FILL);

        } finally {
            attr.recycle();
        }
        isFinish = true;

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        backgroundPaint.setColor(backgroundColor);
        if (backgroundType == BACKGROUND_TYPE_FILL) {
            backgroundPaint.setStyle(Paint.Style.FILL);
        } else if (backgroundType == BACKGROUND_TYPE_STROKE) {
            backgroundPaint.setStyle(Paint.Style.STROKE);
            backgroundPaint.setStrokeWidth(borderWidth);
            backgroundPaint.setStrokeJoin(Paint.Join.ROUND);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (backgroundType == BACKGROUND_TYPE_STROKE) {
            canvas.drawRect(getBgRectF(), backgroundPaint);
        } else {
            canvas.drawRoundRect(getBgRectF(), cornerRadius, cornerRadius, backgroundPaint);
        }
        if (mProgress >= MIN_PROGRESS && mProgress <= MAX_PROGRESS && !isFinish) {
            drawProgress(canvas);
            if (isShowProgress) {
                drawProgressText(canvas);
                drawColorProgressText(canvas);
            }
            if (mProgress >= MAX_PROGRESS) {
                isFinish = true;
            }
        }
        super.onDraw(canvas);
    }

    /**
     * 进度
     */
    private void drawProgress(Canvas canvas) {
        float right = (mProgress / MAX_PROGRESS) * getMeasuredWidth();
        Bitmap bitmap = getPgBitmap();
        pgCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        pgCanvas.save();
        pgCanvas.clipRect(0, 0, right, getMeasuredHeight());
        pgCanvas.drawColor(progressColor);
        pgCanvas.restore();
        //控制显示区域
        BitmapShader bitmapShader = getBitmapShader(bitmap);
        pgPaint.setColor(progressColor);
        pgPaint.setShader(bitmapShader);
        if (cornerRadius > getMeasuredHeight() / 2) {
            canvas.drawRoundRect(getBgRectF(), getMeasuredHeight() / 2, getMeasuredHeight() / 2, pgPaint);
        } else {
            canvas.drawRoundRect(getBgRectF(), cornerRadius, cornerRadius, pgPaint);
        }
    }

    private Bitmap getPgBitmap() {
        if (pgBitmap == null) {
            pgBitmap = Bitmap.createBitmap(getMeasuredWidth() - borderWidth, getMeasuredHeight() - borderWidth, Bitmap.Config.ARGB_8888);
            pgCanvas = new Canvas(pgBitmap);
        }
        return pgBitmap;
    }

    private RectF getBgRectF() {
        if (bgRectF == null) {
            bgRectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }
        return bgRectF;
    }

    private BitmapShader getBitmapShader(Bitmap bitmap) {
        if (bitmapShader == null) {
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }
        return bitmapShader;
    }

    /**
     * 进度提示文本
     *
     * @param canvas
     */
    private void drawProgressText(Canvas canvas) {
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        progressText = getProgress();
        textPaint.getTextBounds(progressText, 0, progressText.length(), textRect);
        int tWidth = textRect.width();
        int tHeight = textRect.height();
        float xCoordinate = (getMeasuredWidth() - tWidth) / 2;
        float yCoordinate = (getMeasuredHeight() + tHeight) / 2;
        canvas.drawText(progressText, xCoordinate, yCoordinate, textPaint);
    }

    private void drawColorProgressText(Canvas canvas) {
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
        int tWidth = textRect.width();
        int tHeight = textRect.height();
        float xCoordinate = (getMeasuredWidth() - tWidth) / 2;
        float yCoordinate = (getMeasuredHeight() + tHeight) / 2;
        float progressWidth = (mProgress / MAX_PROGRESS) * getMeasuredWidth();
        if (progressWidth > xCoordinate) {
            canvas.save();
            float right = Math.min(progressWidth, xCoordinate + tWidth * 1.15f);
            canvas.clipRect(xCoordinate, 0, right, getMeasuredHeight());
            canvas.drawText(progressText, xCoordinate, yCoordinate, textPaint);
            canvas.restore();
        }
    }

    private String getProgress() {
        return String.format(Locale.getDefault(), "%3.0f %%", mProgress);
    }

    public void setProgress(float progress) {
        isFinish = false;
        setText("");
        mProgress = progress;
        invalidate();
    }

    public void initState() {
        isFinish = true;
        mProgress = MIN_PROGRESS;
        invalidate();
    }
}
