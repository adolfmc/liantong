package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.support.p086v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DisallowInterceptSeekBar extends AppCompatSeekBar {
    public DisallowInterceptSeekBar(Context context) {
        super(context);
    }

    public DisallowInterceptSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DisallowInterceptSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }
}
