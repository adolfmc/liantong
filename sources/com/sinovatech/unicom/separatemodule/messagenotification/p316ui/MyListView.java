package com.sinovatech.unicom.separatemodule.messagenotification.p316ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* renamed from: com.sinovatech.unicom.separatemodule.messagenotification.ui.MyListView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
