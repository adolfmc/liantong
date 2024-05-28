package com.sinovatech.unicom.basic.p315ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.HomeScrollView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeScrollView extends ScrollView {
    private List<OnScrollChangedListener> listenerList;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.HomeScrollView$OnScrollChangedListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnScrollChangedListener {
        void onScrollChangedListener(int i, int i2, int i3, int i4);
    }

    public HomeScrollView(Context context) {
        super(context);
        this.listenerList = new ArrayList();
    }

    public HomeScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.listenerList = new ArrayList();
    }

    public void addOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.listenerList.add(onScrollChangedListener);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        for (OnScrollChangedListener onScrollChangedListener : this.listenerList) {
            onScrollChangedListener.onScrollChangedListener(i, i2, i3, i4);
        }
    }
}
