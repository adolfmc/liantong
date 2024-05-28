package com.youth.banner.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BannerViewPager extends ViewPager {
    private boolean scrollable;

    public BannerViewPager(Context context) {
        super(context);
        this.scrollable = true;
    }

    public BannerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scrollable = true;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.scrollable) {
            if (getCurrentItem() == 0 && getChildCount() == 0) {
                return false;
            }
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.scrollable) {
            if (getCurrentItem() == 0 && getChildCount() == 0) {
                return false;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public void setScrollable(boolean z) {
        this.scrollable = z;
    }
}
