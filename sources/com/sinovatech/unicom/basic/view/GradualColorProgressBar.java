package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GradualColorProgressBar extends View {
    private float currentCount;
    private LinearGradient linearGradient;
    private Context mContext;
    private int mHeight;
    private int mWidth;
    private float maxCount;

    public GradualColorProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxCount = 100.0f;
        initView(context);
    }

    public GradualColorProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxCount = 100.0f;
        initView(context);
    }

    public GradualColorProgressBar(Context context) {
        super(context);
        this.maxCount = 100.0f;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(2131099782));
        float f = this.mHeight / 2;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, this.mWidth, this.mHeight), f, f, paint);
        float f2 = this.currentCount / this.maxCount;
        RectF rectF = new RectF(0.0f, 0.0f, this.mWidth * f2, this.mHeight);
        Log.e("GradualColorProgressBar", this.currentCount + "");
        Log.e("GradualColorProgressBar", f2 + "");
        paint.setColor(getResources().getColor(2131100060));
        paint.setShader(getLinearGradient());
        canvas.drawRoundRect(rectF, f, f, paint);
        if (this.maxCount != this.currentCount) {
            int i = this.mWidth;
            RectF rectF2 = new RectF((i * f2) - f, 0.0f, i * f2, this.mHeight);
            paint.setShader(getLinearGradient());
            canvas.drawRect(rectF2, paint);
        }
    }

    private LinearGradient getLinearGradient() {
        if (this.linearGradient == null) {
            this.linearGradient = new LinearGradient(0.0f, 0.0f, getWidth(), this.mHeight, new int[]{this.mContext.getResources().getColor(2131099784), this.mContext.getResources().getColor(2131099783)}, (float[]) null, Shader.TileMode.CLAMP);
        }
        return this.linearGradient;
    }

    private int dipToPx(int i) {
        return (int) ((i * getContext().getResources().getDisplayMetrics().density) + ((i >= 0 ? 1 : -1) * 0.5f));
    }

    public void setMaxCount(float f) {
        this.maxCount = f;
    }

    public void setCurrentCount(float f) {
        float f2 = this.maxCount;
        if (f > f2) {
            f = f2;
        }
        this.currentCount = f;
        invalidate();
    }

    public float getMaxCount() {
        return this.maxCount;
    }

    public float getCurrentCount() {
        return this.currentCount;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 || mode == Integer.MIN_VALUE) {
            this.mWidth = size;
        } else {
            this.mWidth = 0;
        }
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            this.mHeight = dipToPx(18);
        } else {
            this.mHeight = size2;
        }
        setMeasuredDimension(this.mWidth, this.mHeight);
    }
}
