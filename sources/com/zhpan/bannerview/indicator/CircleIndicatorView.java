package com.zhpan.bannerview.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CircleIndicatorView extends BaseIndicatorView {
    private int height;
    private float mCheckedRadius;
    private float mNormalRadius;
    private float maxRadius;

    public CircleIndicatorView(Context context) {
        this(context, null);
    }

    public CircleIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint.setColor(this.normalColor);
        this.mNormalRadius = this.normalIndicatorWidth / 2.0f;
        this.mCheckedRadius = this.checkedIndicatorWidth / 2.0f;
        this.indicatorGap = this.mNormalRadius * 2.0f;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.height = getHeight();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mNormalRadius = this.normalIndicatorWidth / 2.0f;
        this.mCheckedRadius = this.checkedIndicatorWidth / 2.0f;
        this.maxRadius = Math.max(this.mCheckedRadius, this.mNormalRadius);
        setMeasuredDimension((int) (((this.pageSize - 1) * this.indicatorGap) + ((this.maxRadius + (this.mNormalRadius * (this.pageSize - 1))) * 2.0f)), (int) (this.maxRadius * 2.0f));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.pageSize > 1) {
            for (int i = 0; i < this.pageSize; i++) {
                this.mPaint.setColor(this.normalColor);
                canvas.drawCircle(this.maxRadius + (((this.mNormalRadius * 2.0f) + this.indicatorGap) * i), this.height / 2.0f, this.mNormalRadius, this.mPaint);
            }
            drawSliderStyle(canvas);
        }
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
        canvas.drawCircle(this.maxRadius + (((this.mNormalRadius * 2.0f) + this.indicatorGap) * this.currentPosition) + (((this.mNormalRadius * 2.0f) + this.indicatorGap) * this.slideProgress), this.height / 2.0f, this.mCheckedRadius, this.mPaint);
    }
}
