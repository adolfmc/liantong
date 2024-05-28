package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.common.UIUtils;
import com.zhpan.bannerview.indicator.BaseIndicatorView;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.view.DashIndicatorView1 */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DashIndicatorView1 extends BaseIndicatorView {
    private float corner;
    private float maxWidth;
    private float minWidth;
    private float sliderHeight;

    public DashIndicatorView1(Context context) {
        this(context, null);
    }

    public DashIndicatorView1(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DashIndicatorView1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.corner = 20.0f;
        this.mPaint.setColor(this.normalColor);
        this.sliderHeight = UIUtils.dip2px(2.0f);
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
            float f3 = this.sliderHeight;
            float f4 = this.corner;
            canvas.drawRoundRect(f2, 0.0f, f2 + this.normalIndicatorWidth, f3, f4, f4, this.mPaint);
            drawSliderStyle(canvas);
        } else if (i < this.currentPosition) {
            this.mPaint.setColor(this.normalColor);
            float f5 = i;
            float f6 = (this.minWidth * f5) + (f5 * this.indicatorGap);
            float f7 = this.sliderHeight;
            float f8 = this.corner;
            canvas.drawRoundRect(f6, 0.0f, f6 + this.minWidth, f7, f8, f8, this.mPaint);
        } else if (i == this.currentPosition) {
            this.mPaint.setColor(this.checkedColor);
            float f9 = i;
            float f10 = (this.minWidth * f9) + (f9 * this.indicatorGap);
            float f11 = this.minWidth;
            float f12 = this.sliderHeight;
            float f13 = this.corner;
            canvas.drawRoundRect(f10, 0.0f, f10 + f11 + (this.maxWidth - f11), f12, f13, f13, this.mPaint);
        } else {
            this.mPaint.setColor(this.normalColor);
            float f14 = i;
            float f15 = (this.minWidth * f14) + (f14 * this.indicatorGap);
            float f16 = this.maxWidth;
            float f17 = this.minWidth;
            float f18 = f15 + (f16 - f17);
            float f19 = this.sliderHeight;
            float f20 = this.corner;
            canvas.drawRoundRect(f18, 0.0f, f18 + f17, f19, f20, f20, this.mPaint);
        }
    }

    private void smoothSlide(Canvas canvas, int i) {
        this.mPaint.setColor(this.normalColor);
        float f = i;
        float f2 = (this.maxWidth * f) + (f * this.indicatorGap);
        float f3 = this.maxWidth;
        float f4 = this.minWidth;
        float f5 = f2 + (f3 - f4);
        float f6 = this.sliderHeight;
        float f7 = this.corner;
        canvas.drawRoundRect(f5, 0.0f, f5 + f4, f6, f7, f7, this.mPaint);
        drawSliderStyle(canvas);
    }

    @Override // com.zhpan.bannerview.indicator.BaseIndicatorView, android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        setContentDescription("");
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
        float f2 = this.sliderHeight;
        float f3 = this.corner;
        canvas.drawRoundRect(f, 0.0f, f + this.maxWidth, f2, f3, f3, this.mPaint);
    }

    public DashIndicatorView1 setSliderHeight(int i) {
        this.sliderHeight = i;
        return this;
    }
}
