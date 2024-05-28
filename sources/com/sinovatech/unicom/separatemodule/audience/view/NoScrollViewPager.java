package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoScrollViewPager extends ViewPager {
    private boolean noScroll;

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.noScroll = true;
    }

    public NoScrollViewPager(Context context) {
        super(context);
        this.noScroll = true;
    }

    public void setNoScroll(boolean z) {
        this.noScroll = z;
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.p083v4.view.ViewPager
    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i, z);
    }

    @Override // android.support.p083v4.view.ViewPager
    public void setCurrentItem(int i) {
        super.setCurrentItem(i);
    }
}
