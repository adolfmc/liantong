package com.sinovatech.unicom.separatemodule.keyboard;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NoHorizontalScrollerViewPager extends ViewPager {
    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public NoHorizontalScrollerViewPager(Context context) {
        super(context);
    }

    public NoHorizontalScrollerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
