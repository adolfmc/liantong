package com.zhy.android.percent.support;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.zhy.android.percent.support.PercentLayoutHelper;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PercentLinearLayout extends LinearLayout {
    private static final String TAG = "PercentLinearLayout";
    private PercentLayoutHelper mPercentLayoutHelper;

    public PercentLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPercentLayoutHelper = new PercentLayoutHelper(this);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int screenHeight;
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, mode);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), View.MeasureSpec.getMode(i));
        if (mode == 0 && getParent() != null && (getParent() instanceof ScrollView)) {
            Context context = getContext();
            if (context instanceof Activity) {
                screenHeight = ((Activity) context).findViewById(16908290).getMeasuredHeight();
            } else {
                screenHeight = getScreenHeight();
            }
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(screenHeight, mode);
        }
        this.mPercentLayoutHelper.adjustChildren(makeMeasureSpec2, makeMeasureSpec);
        super.onMeasure(i, i2);
        if (this.mPercentLayoutHelper.handleMeasuredStateTooSmall()) {
            super.onMeasure(i, i2);
        }
    }

    private int getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mPercentLayoutHelper.restoreOriginalParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class LayoutParams extends LinearLayout.LayoutParams implements PercentLayoutHelper.PercentLayoutParams {
        private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(context, attributeSet);
        }

        @Override // com.zhy.android.percent.support.PercentLayoutHelper.PercentLayoutParams
        public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo() {
            return this.mPercentLayoutInfo;
        }

        @Override // android.view.ViewGroup.LayoutParams
        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            PercentLayoutHelper.fetchWidthAndHeight(this, typedArray, i, i2);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }
}
