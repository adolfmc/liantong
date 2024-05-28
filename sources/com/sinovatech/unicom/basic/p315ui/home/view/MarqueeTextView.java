package com.sinovatech.unicom.basic.p315ui.home.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.view.MarqueeTextView */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MarqueeTextView extends AppCompatTextView {
    private boolean isFocus;

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z) {
    }

    public MarqueeTextView(Context context) {
        super(context);
        this.isFocus = false;
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isFocus = false;
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFocus = false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return isFocus();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
    }

    public boolean isFocus() {
        return this.isFocus;
    }

    public void setFocus(boolean z) {
        this.isFocus = z;
    }
}
