package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.view.HomeWebView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeWebView extends PBWebView {
    private boolean flagX;
    private boolean flagY;
    private boolean isClampedX;
    private boolean isClampedY;
    private boolean isDialogOpen;
    private float latestX;
    private float latestY;
    private int scrollY;
    private float startX;
    private float startY;
    private int tabIndex;

    public HomeWebView(Context context) {
        super(context);
        this.flagX = false;
        this.flagY = false;
        this.isDialogOpen = false;
        this.isClampedX = false;
        this.isClampedY = false;
        this.isDialogOpen = false;
    }

    public HomeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.flagX = false;
        this.flagY = false;
        this.isDialogOpen = false;
        this.isClampedX = false;
        this.isClampedY = false;
        this.isDialogOpen = false;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        MsLogUtil.m7979d("onScrollChanged", "---" + getScrollY());
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.tabIndex == 99) {
            getParent().requestDisallowInterceptTouchEvent(false);
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            this.scrollY = getScrollY();
            getParent().requestDisallowInterceptTouchEvent(this.isDialogOpen);
        } else if (motionEvent.getAction() == 2) {
            this.latestX = motionEvent.getX();
            this.latestY = motionEvent.getY();
            MsLogUtil.m7979d("onScrollChanged", "y:" + this.scrollY + "   on:" + getScrollY());
            if (this.isClampedX) {
                this.flagX = false;
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (Math.abs(this.latestX - this.startX) >= Math.abs(this.latestY - this.startY)) {
                this.flagX = true;
                getParent().requestDisallowInterceptTouchEvent(true);
            } else if (this.isClampedY) {
                this.flagX = false;
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (Math.abs(this.latestX - this.startX) < Math.abs(this.latestY - this.startY)) {
                this.flagX = false;
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                this.flagX = false;
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.scrollY = getScrollY();
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            if (motionEvent.getAction() == 3) {
                this.flagX = true;
                getParent().requestDisallowInterceptTouchEvent(this.isDialogOpen);
            } else {
                this.flagX = false;
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.isClampedX = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOpenDialog(final boolean z) {
        this.isDialogOpen = z;
        postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.view.HomeWebView.1
            @Override // java.lang.Runnable
            public void run() {
                HomeWebView.this.isDialogOpen = z;
            }
        }, 500L);
    }

    public void setTabIndex(int i) {
        this.tabIndex = i;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        this.isClampedX = z;
        if (z && this.flagX) {
            getParent().requestDisallowInterceptTouchEvent(false);
            MainGestureTabFragmeLayout.allowFlipTouch = false;
        }
    }
}
