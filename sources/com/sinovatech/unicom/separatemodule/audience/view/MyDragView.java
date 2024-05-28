package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.p083v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyDragView extends RelativeLayout {
    private View mAutoBackView;
    private ViewDragHelper mDragger;

    public MyDragView(Context context) {
        super(context);
    }

    public MyDragView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.view.MyDragView.1
            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                return true;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int getViewHorizontalDragRange(View view) {
                return MyDragView.this.getMeasuredWidth() - view.getMeasuredWidth();
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                return MyDragView.this.getMeasuredHeight() - view.getMeasuredHeight();
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                if (i > MyDragView.this.getWidth() - view.getMeasuredWidth()) {
                    return MyDragView.this.getWidth() - view.getMeasuredWidth();
                }
                if (i < 0) {
                    return 0;
                }
                return i;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                if (i > MyDragView.this.getHeight() - view.getMeasuredHeight()) {
                    return MyDragView.this.getHeight() - view.getMeasuredHeight();
                }
                if (i < 0) {
                    return 0;
                }
                return i;
            }
        });
        this.mDragger.setEdgeTrackingEnabled(1);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDragger.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int x2 = (int) this.mAutoBackView.getX();
        int y2 = (int) this.mAutoBackView.getY();
        int width = this.mAutoBackView.getWidth();
        int height = this.mAutoBackView.getHeight();
        if (x < x2 || y < y2 || x > x2 + width || y > y2 + height) {
            return false;
        }
        try {
            this.mDragger.processTouchEvent(motionEvent);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAutoBackView = getChildAt(0);
    }
}
