package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MeasureListView extends GridView {
    public MeasureListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MeasureListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
