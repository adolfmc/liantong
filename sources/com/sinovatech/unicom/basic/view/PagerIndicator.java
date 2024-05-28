package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PagerIndicator extends View {
    private int RADIUS;
    private int kongxinColor;
    private Paint mBgPaint;
    private int mCount;
    private float mOffset;
    private Paint mPaint;
    private int mPosition;
    private int screenWidth;
    private int shixinColor;
    private int startX;
    private int startY;

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.startX = (this.screenWidth / 2) - (((this.RADIUS * 3) * (this.mCount - 1)) / 2);
        for (int i = 0; i < this.mCount; i++) {
            int i2 = this.startX;
            int i3 = this.RADIUS;
            canvas.drawCircle(i2 + (i * 3 * i3), this.startY, i3 - 1, this.mBgPaint);
        }
        int i4 = this.RADIUS;
        canvas.drawCircle(this.startX + ((this.mPosition + this.mOffset) * 3.0f * i4), this.startY, i4 - 1, this.mPaint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.startY = i2 / 2;
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public void onPageScrolled(int i, float f) {
        this.mPosition = i;
        this.mOffset = f;
        invalidate();
    }

    public PagerIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.RADIUS = 10;
        this.mCount = 5;
        this.kongxinColor = -1579033;
        this.shixinColor = -39424;
        this.screenWidth = UIUtils.getScreenWidth(context);
        initPaint();
    }

    private void initPaint() {
        this.mBgPaint = new Paint();
        this.mBgPaint.setColor(this.kongxinColor);
        this.mBgPaint.setAntiAlias(true);
        this.mPaint = new Paint();
        this.mPaint.setColor(this.shixinColor);
        this.mPaint.setAntiAlias(true);
    }
}
