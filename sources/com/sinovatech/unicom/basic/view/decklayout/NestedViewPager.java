package com.sinovatech.unicom.basic.view.decklayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.view.NestedScrollingChild;
import android.support.p083v4.view.NestedScrollingChildHelper;
import android.support.p083v4.view.NestedScrollingParent;
import android.support.p083v4.view.NestedScrollingParentHelper;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NestedViewPager extends ViewPager implements NestedScrollingChild, NestedScrollingParent {
    private boolean isSwipeEnabled;
    private final NestedScrollingChildHelper mChildHelper;
    private final NestedScrollingParentHelper mParentHelper;

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        return (i & 2) != 0;
    }

    public NestedViewPager(@NonNull Context context) {
        this(context, null);
    }

    public NestedViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onStopNestedScroll(@NonNull View view) {
        this.mParentHelper.onStopNestedScroll(view);
        stopNestedScroll();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        dispatchNestedPreScroll(i, i2, iArr, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.support.p083v4.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isSwipeEnabled && super.onTouchEvent(motionEvent);
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isSwipeEnabled && super.onInterceptTouchEvent(motionEvent);
    }

    public void setSwipeEnabled(boolean z) {
        this.isSwipeEnabled = z;
    }
}
