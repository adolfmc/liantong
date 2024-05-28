package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SquareLayout extends RelativeLayout {
    public SquareLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareLayout(Context context) {
        super(context);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        getMeasuredHeight();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }
}
