package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CScrollView extends ScrollView {
    OnScrollChangedListener onScrollChangedListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnScrollChangedListener {
        void onScrollChangedListener(int i, int i2, int i3, int i4);
    }

    public CScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.onScrollChangedListener.onScrollChangedListener(i, i2, i3, i4);
    }
}
