package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.p318ui.App;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomDropOutView extends RelativeLayout {
    private float lastDownY;
    private Interpolator polator;
    private SharePreferenceUtil preference;
    private Scroller scroller;
    private int windowHeight;

    public CustomDropOutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.polator = new BounceInterpolator();
        this.scroller = new Scroller(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.windowHeight = displayMetrics.heightPixels;
        this.preference = App.getSharePreferenceUtil();
    }

    public void start() {
        setVisibility(0);
        startAnimation(AnimationUtils.loadAnimation(getContext(), 2130772045));
    }

    public void close(boolean z, boolean z2) {
        this.preference.putBoolean("dropoutAdvertise_reshow_afte_rrestart", Boolean.valueOf(z));
        setVisibility(8);
        if (z2) {
            startAnimation(AnimationUtils.loadAnimation(getContext(), 2130772046));
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.scroller.computeScrollOffset()) {
            scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.lastDownY = motionEvent.getRawY();
                break;
            case 1:
                float rawY = this.lastDownY - motionEvent.getRawY();
                if (rawY > 0.0f) {
                    if (rawY > this.windowHeight / 5) {
                        close(true, true);
                        break;
                    } else {
                        this.scroller.startScroll(0, getScrollY(), 0, -getScrollY(), 500);
                        invalidate();
                        break;
                    }
                }
                break;
            case 2:
                float rawY2 = this.lastDownY - motionEvent.getRawY();
                if (rawY2 > 0.0f) {
                    scrollTo(0, (int) rawY2);
                    break;
                }
                break;
        }
        return true;
    }
}
