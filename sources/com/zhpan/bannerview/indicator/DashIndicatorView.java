package com.zhpan.bannerview.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DashIndicatorView extends BaseIndicatorView {
    private float maxWidth;
    private float minWidth;
    private float sliderHeight;

    public DashIndicatorView(Context context) {
        this(context, null);
    }

    public DashIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DashIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint.setColor(this.normalColor);
        this.sliderHeight = this.normalIndicatorWidth / 2.0f;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.maxWidth = Math.max(this.normalIndicatorWidth, this.checkedIndicatorWidth);
        this.minWidth = Math.min(this.normalIndicatorWidth, this.checkedIndicatorWidth);
        setMeasuredDimension((int) (((this.pageSize - 1) * this.indicatorGap) + this.maxWidth + ((this.pageSize - 1) * this.minWidth)), (int) this.sliderHeight);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.pageSize > 1) {
            for (int i = 0; i < this.pageSize; i++) {
                if (this.slideMode == 1) {
                    smoothSlide(canvas, i);
                } else {
                    normalSlide(canvas, i);
                }
            }
        }
    }

    private void normalSlide(Canvas canvas, int i) {
        if (this.normalIndicatorWidth == this.checkedIndicatorWidth) {
            this.mPaint.setColor(this.normalColor);
            float f = i;
            float f2 = (this.normalIndicatorWidth * f) + (f * this.indicatorGap);
            canvas.drawRect(f2, 0.0f, f2 + this.normalIndicatorWidth, this.sliderHeight, this.mPaint);
            drawSliderStyle(canvas);
        } else if (i < this.currentPosition) {
            this.mPaint.setColor(this.normalColor);
            float f3 = i;
            float f4 = (this.minWidth * f3) + (f3 * this.indicatorGap);
            canvas.drawRect(f4, 0.0f, f4 + this.minWidth, this.sliderHeight, this.mPaint);
        } else if (i == this.currentPosition) {
            this.mPaint.setColor(this.checkedColor);
            float f5 = i;
            float f6 = (this.minWidth * f5) + (f5 * this.indicatorGap);
            float f7 = this.minWidth;
            canvas.drawRect(f6, 0.0f, f6 + f7 + (this.maxWidth - f7), this.sliderHeight, this.mPaint);
        } else {
            this.mPaint.setColor(this.normalColor);
            float f8 = i;
            float f9 = (this.minWidth * f8) + (f8 * this.indicatorGap);
            float f10 = this.maxWidth;
            float f11 = this.minWidth;
            float f12 = f9 + (f10 - f11);
            canvas.drawRect(f12, 0.0f, f12 + f11, this.sliderHeight, this.mPaint);
        }
    }

    private void smoothSlide(Canvas canvas, int i) {
        this.mPaint.setColor(this.normalColor);
        float f = i;
        float f2 = (this.maxWidth * f) + (f * this.indicatorGap);
        float f3 = this.maxWidth;
        float f4 = this.minWidth;
        float f5 = f2 + (f3 - f4);
        canvas.drawRect(f5, 0.0f, f5 + f4, this.sliderHeight, this.mPaint);
        drawSliderStyle(canvas);
    }

    @Override // com.zhpan.bannerview.indicator.BaseIndicatorView, android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        super.onPageSelected(i);
        NBSActionInstrumentation.onPageSelectedExit();
    }

    @Override // com.zhpan.bannerview.indicator.BaseIndicatorView, android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        super.onPageScrolled(i, f, i2);
    }

    @Override // com.zhpan.bannerview.indicator.BaseIndicatorView, android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        super.onPageScrollStateChanged(i);
    }

    private void drawSliderStyle(Canvas canvas) {
        this.mPaint.setColor(this.checkedColor);
        float f = (this.currentPosition * this.maxWidth) + (this.currentPosition * this.indicatorGap) + ((this.maxWidth + this.indicatorGap) * this.slideProgress);
        canvas.drawRect(f, 0.0f, f + this.maxWidth, this.sliderHeight, this.mPaint);
    }

    public DashIndicatorView setSliderHeight(int i) {
        this.sliderHeight = i;
        return this;
    }
}
