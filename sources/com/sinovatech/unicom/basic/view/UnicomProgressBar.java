package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.C9718R;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomProgressBar extends View {
    public static final int FILL = 1;
    public static final int STROKE = 0;
    private int max;
    private Paint paint;
    private int progress;
    private int roundColor;
    private int roundProgressColor;
    private float roundWidth;
    private int style;
    private int textColor;
    private boolean textIsDisplayable;
    private float textSize;

    public UnicomProgressBar(Context context) {
        this(context, null);
    }

    public UnicomProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UnicomProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.RoundProgressBar);
        this.roundColor = obtainStyledAttributes.getColor(1, Color.argb(1, 229, 229, 229));
        this.roundProgressColor = obtainStyledAttributes.getColor(2, -16711936);
        this.textColor = obtainStyledAttributes.getColor(5, -16777216);
        this.textSize = obtainStyledAttributes.getDimension(7, UIUtils.dip2px(14.0f));
        this.roundWidth = obtainStyledAttributes.getDimension(3, UIUtils.dip2px(70.0f));
        this.max = obtainStyledAttributes.getInteger(0, 100);
        this.textIsDisplayable = obtainStyledAttributes.getBoolean(6, true);
        this.style = obtainStyledAttributes.getInt(4, 0);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        int width = getWidth() / 2;
        float f = width;
        int i2 = (int) (f - (this.roundWidth / 2.0f));
        this.paint.setColor(this.roundColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.roundWidth);
        this.paint.setAntiAlias(true);
        canvas.drawCircle(f, f, i2, this.paint);
        MsLogUtil.m7979d("log", width + "");
        this.paint.setStrokeWidth(0.0f);
        this.paint.setColor(this.textColor);
        this.paint.setTextSize(this.textSize);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        int i3 = (int) ((this.progress / this.max) * 100.0f);
        float measureText = this.paint.measureText(i3 + "%");
        if (this.textIsDisplayable && i3 != 0 && this.style == 0) {
            canvas.drawText(i3 + "%", f - (measureText / 2.0f), f + (this.textSize / 2.0f), this.paint);
        }
        this.paint.setStrokeWidth(this.roundWidth);
        this.paint.setColor(this.roundProgressColor);
        float f2 = width - i2;
        float f3 = width + i2;
        RectF rectF = new RectF(f2, f2, f3, f3);
        switch (this.style) {
            case 0:
                this.paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF, 0.0f, (this.progress * 360) / this.max, false, this.paint);
                return;
            case 1:
                this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (this.progress != 0) {
                    canvas.drawArc(rectF, 0.0f, (i * 360) / this.max, true, this.paint);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public synchronized int getMax() {
        return this.max;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = i;
    }

    public synchronized int getProgress() {
        return this.progress;
    }

    public synchronized void setProgress(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (i > this.max) {
            i = this.max;
        }
        if (i <= this.max) {
            this.progress = i;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return this.roundColor;
    }

    public void setCricleColor(int i) {
        this.roundColor = i;
    }

    public int getCricleProgressColor() {
        return this.roundProgressColor;
    }

    public void setCricleProgressColor(int i) {
        this.roundProgressColor = i;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    public float getRoundWidth() {
        return this.roundWidth;
    }

    public void setRoundWidth(float f) {
        this.roundWidth = f;
    }
}
