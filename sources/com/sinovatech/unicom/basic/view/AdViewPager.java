package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdViewPager extends ViewPager {
    public AdViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (getCurrentItem() != 0) {
                    SwipePanel.isAdViewPager = true;
                    break;
                } else {
                    SwipePanel.isAdViewPager = false;
                    break;
                }
            case 1:
                SwipePanel.isAdViewPager = false;
                break;
            case 2:
                SwipePanel.isAdViewPager = false;
                break;
            case 3:
                SwipePanel.isAdViewPager = false;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
