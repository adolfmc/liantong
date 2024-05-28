package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomViewPager extends ViewPager {
    private boolean isPagingEnabled;

    public CustomViewPager(Context context) {
        super(context);
        this.isPagingEnabled = true;
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isPagingEnabled = true;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isPagingEnabled && super.onTouchEvent(motionEvent);
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.isPagingEnabled = z;
    }
}
