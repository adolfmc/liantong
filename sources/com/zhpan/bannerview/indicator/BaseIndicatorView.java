package com.zhpan.bannerview.indicator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.zhpan.bannerview.utils.BannerUtils;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BaseIndicatorView extends View implements IIndicator {
    protected int checkedColor;
    protected float checkedIndicatorWidth;
    protected int currentPosition;
    protected float indicatorGap;
    protected Paint mPaint;
    protected int normalColor;
    protected float normalIndicatorWidth;
    protected int pageSize;
    private int prePosition;
    protected int slideMode;
    protected float slideProgress;
    protected boolean slideToRight;

    public void onPageScrollStateChanged(int i) {
    }

    public BaseIndicatorView(Context context) {
        super(context);
    }

    public BaseIndicatorView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseIndicatorView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.normalIndicatorWidth = BannerUtils.dp2px(8.0f);
        float f = this.normalIndicatorWidth;
        this.checkedIndicatorWidth = f;
        this.indicatorGap = f;
        this.normalColor = Color.parseColor("#8C18171C");
        this.checkedColor = Color.parseColor("#8C6C6D72");
        this.slideMode = 0;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
    }

    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        int i2 = this.slideMode;
        if (i2 == 0) {
            this.currentPosition = i;
            this.slideProgress = 0.0f;
            invalidate();
        } else if (i2 == 1) {
            if (i == 0 && this.slideToRight) {
                this.currentPosition = 0;
                this.slideProgress = 0.0f;
                invalidate();
            } else {
                int i3 = this.pageSize;
                if (i == i3 - 1 && !this.slideToRight) {
                    this.currentPosition = i3 - 1;
                    this.slideProgress = 0.0f;
                    invalidate();
                }
            }
        }
        NBSActionInstrumentation.onPageSelectedExit();
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.slideMode == 1) {
            this.slideToRight = isSlideToRight(i, f);
            if (f == 0.0f) {
                this.prePosition = i;
            }
            int i3 = this.pageSize;
            if (i != i3 - 1) {
                if (this.currentPosition == i3 - 1 && this.slideToRight) {
                    f = 0.0f;
                }
                this.slideProgress = f;
                this.currentPosition = i;
                invalidate();
            }
        }
    }

    private boolean isSlideToRight(int i, float f) {
        if (this.prePosition == 0 && i == this.pageSize - 1) {
            return false;
        }
        return (this.prePosition == this.pageSize - 1 && i == 0) || (((float) i) + f) - ((float) this.prePosition) > 0.0f;
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void setPageSize(int i) {
        this.pageSize = i;
        requestLayout();
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void setNormalColor(int i) {
        this.normalColor = i;
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void setCheckedColor(int i) {
        this.checkedColor = i;
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void setIndicatorGap(int i) {
        if (i >= 0) {
            this.indicatorGap = i;
        }
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void setSlideMode(int i) {
        this.slideMode = i;
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void setIndicatorWidth(int i, int i2) {
        this.normalIndicatorWidth = i;
        this.checkedIndicatorWidth = i2;
    }

    @Override // com.zhpan.bannerview.indicator.IIndicator
    public void notifyDataChanged() {
        invalidate();
    }
}
