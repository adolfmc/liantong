package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MaxHeightView extends LinearLayout {
    private static final float DEFAULT_MAX_DIMEN = 0.0f;
    private static final float DEFAULT_MAX_RATIO = 0.0f;
    private static final float DEFAULT_MAX_RATIO_WITHOUT_ARGU = 0.57f;
    private float mMaxDimen;
    private float mMaxHeight;
    private float mMaxRatio;

    public MaxHeightView(Context context) {
        super(context);
        this.mMaxRatio = 0.0f;
        this.mMaxDimen = 0.0f;
        this.mMaxHeight = 0.0f;
        init();
    }

    public MaxHeightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxRatio = 0.0f;
        this.mMaxDimen = 0.0f;
        this.mMaxHeight = 0.0f;
        init();
    }

    public MaxHeightView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxRatio = 0.0f;
        this.mMaxDimen = 0.0f;
        this.mMaxHeight = 0.0f;
        init();
    }

    private void init() {
        if (this.mMaxDimen <= 0.0f && this.mMaxRatio <= 0.0f) {
            this.mMaxHeight = getScreenHeight(getContext()) * DEFAULT_MAX_RATIO_WITHOUT_ARGU;
            return;
        }
        if (this.mMaxDimen <= 0.0f) {
            float f = this.mMaxRatio;
            if (f > 0.0f) {
                this.mMaxHeight = f * getScreenHeight(getContext());
                return;
            }
        }
        float f2 = this.mMaxDimen;
        if (f2 > 0.0f && this.mMaxRatio <= 0.0f) {
            this.mMaxHeight = f2;
        } else {
            this.mMaxHeight = Math.min(this.mMaxDimen, this.mMaxRatio * getScreenHeight(getContext()));
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            float f = this.mMaxHeight;
            if (size > f) {
                size = (int) f;
            }
        }
        if (mode == 0) {
            float f2 = this.mMaxHeight;
            if (size > f2) {
                size = (int) f2;
            }
        }
        if (mode == Integer.MIN_VALUE) {
            float f3 = this.mMaxHeight;
            if (size > f3) {
                size = (int) f3;
            }
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(size, mode));
    }

    private int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }
}
