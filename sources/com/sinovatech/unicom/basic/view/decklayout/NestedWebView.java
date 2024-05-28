package com.sinovatech.unicom.basic.view.decklayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p083v4.view.NestedScrollingChild2;
import android.support.p083v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.OverScroller;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NestedWebView extends WebView implements NestedScrollingChild2 {
    private static final String TAG = "NestedWebView";
    private boolean isFlinging;
    private int mActivePointerId;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    private int mLastScrollerY;
    private int mLastY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private NestedScrollingChildHelper mScrollingChildHelper;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private int moveDistance;

    public NestedWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mScroller = new OverScroller(getContext());
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setNestedScrollingEnabled(true);
    }

    public NestedWebView(Context context) {
        this(context, null);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0126  */
    @Override // android.webkit.WebView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.view.decklayout.NestedWebView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void caculateV(int i, int i2) {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        int yVelocity = (int) this.mVelocityTracker.getYVelocity(i);
        Log.d(TAG, " caculateV curY:" + i2 + " mLastY:" + this.mLastY + " initialVelocity:" + yVelocity);
        if (Math.abs(yVelocity) > this.mMinimumVelocity) {
            this.mLastScrollerY = getScrollY();
            this.isFlinging = true;
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.isFlinging) {
            int scrollY = getScrollY() - this.mLastScrollerY;
            if (getScrollY() == 0) {
                startNestedScroll(2, 1);
                if (this.moveDistance < 0) {
                    dispatchNestedScroll(0, scrollY, 0, -1000, null, 1);
                }
                this.isFlinging = false;
                stopNestedScroll(1);
            }
        }
        Log.d(TAG, "computeScroll webView:getScrollY:" + getScrollY());
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll(0);
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    @Override // android.webkit.WebView
    public void flingScroll(int i, int i2) {
        super.flingScroll(i, i2);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean startNestedScroll(int i, int i2) {
        return getScrollingChildHelper().startNestedScroll(i, i2);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public void stopNestedScroll(int i) {
        getScrollingChildHelper().stopNestedScroll(i);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i) {
        return getScrollingChildHelper().hasNestedScrollingParent(i);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2, int i3) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, @Nullable int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }
}
