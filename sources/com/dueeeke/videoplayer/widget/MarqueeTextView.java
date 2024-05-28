package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.support.p086v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MarqueeTextView extends AppCompatTextView {
    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
