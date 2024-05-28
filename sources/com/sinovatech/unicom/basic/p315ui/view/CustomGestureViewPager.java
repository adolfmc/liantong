package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.view.CustomGestureViewPager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomGestureViewPager extends ViewPager {
    private static final String TAG = "CustomGestureViewPager";
    private boolean isCanScroll;

    public CustomGestureViewPager(Context context) {
        super(context);
        this.isCanScroll = true;
    }

    public CustomGestureViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCanScroll = true;
    }

    public void setCanScroll(boolean z) {
        this.isCanScroll = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isCanScroll) {
            if (motionEvent.getAction() == 0) {
                MainGestureTabFragmeLayout.allowFlipTouch = false;
                String str = MainGestureTabFragmeLayout.TAG;
                MsLogUtil.m7979d(str, "CustomGestureViewPager=> ACTION_DOWN = " + MainGestureTabFragmeLayout.allowFlipTouch);
            }
        } else {
            MainGestureTabFragmeLayout.allowFlipTouch = true;
            String str2 = MainGestureTabFragmeLayout.TAG;
            MsLogUtil.m7979d(str2, "CustomGestureViewPager = " + MainGestureTabFragmeLayout.allowFlipTouch);
        }
        return super.dispatchTouchEvent(motionEvent);
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
