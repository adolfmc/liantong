package com.sinovatech.unicom.separatemodule.videocenter.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.p083v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import java.lang.reflect.Field;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomBehavior extends AppBarLayout.Behavior {
    private OverScroller mScroller;

    @Override // android.support.design.widget.HeaderBehavior, android.support.design.widget.CoordinatorLayout.Behavior
    public /* bridge */ /* synthetic */ boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
    }

    public CustomBehavior() {
    }

    public CustomBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getParentScroller(context);
    }

    private void getParentScroller(Context context) {
        if (this.mScroller != null) {
            return;
        }
        this.mScroller = new OverScroller(context);
        try {
            Field declaredField = getClass().getSuperclass().getSuperclass().getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, this.mScroller);
        } catch (Exception unused) {
        }
    }

    @Override // android.support.design.widget.AppBarLayout.BaseBehavior, android.support.design.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && overScroller.computeScrollOffset()) {
            this.mScroller.abortAnimation();
        }
        if (i3 == 1 && getTopAndBottomOffset() == 0) {
            ViewCompat.stopNestedScroll(view, i3);
        }
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
    }

    @Override // android.support.design.widget.HeaderBehavior, android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, MotionEvent motionEvent) {
        motionEvent.getActionMasked();
        return super.onTouchEvent(coordinatorLayout, (CoordinatorLayout) appBarLayout, motionEvent);
    }
}
