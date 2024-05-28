package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.zhpan.bannerview.BannerViewPager;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomHomeBannerView extends BannerViewPager {
    public CustomHomeBannerView(Context context) {
        super(context);
    }

    public CustomHomeBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                SwipePanel.isAdViewPager = true;
                break;
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
