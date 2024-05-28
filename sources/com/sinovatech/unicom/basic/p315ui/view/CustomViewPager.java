package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* renamed from: com.sinovatech.unicom.basic.ui.view.CustomViewPager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomViewPager extends ViewPager {
    private boolean isCanScroll;

    public CustomViewPager(Context context) {
        super(context);
        this.isCanScroll = true;
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCanScroll = true;
    }

    public void setCanScroll(boolean z) {
        this.isCanScroll = z;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isCanScroll) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isCanScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
