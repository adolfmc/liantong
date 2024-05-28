package com.sinovatech.unicom.basic.view.decklayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ConsecutiveViewPager extends ViewPager implements IConsecutiveScroller {
    private boolean isPagingEnabled;
    private int mAdjustHeight;

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isPagingEnabled && super.onTouchEvent(motionEvent);
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.isPagingEnabled = z;
    }

    public ConsecutiveViewPager(@NonNull Context context) {
        super(context);
        this.isPagingEnabled = true;
    }

    public ConsecutiveViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isPagingEnabled = true;
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        if (isConsecutiveParentAndBottom() && this.mAdjustHeight > 0) {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getDefaultSize(0, i2) - this.mAdjustHeight, View.MeasureSpec.getMode(i2)));
            return;
        }
        try {
            super.onMeasure(i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (ScrollUtils.isConsecutiveScrollerChild(this)) {
            disableChildScroll(view);
            if (view instanceof ViewGroup) {
                ((ViewGroup) view).setClipToPadding(false);
            }
        }
    }

    private void disableChildScroll(View view) {
        view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);
        view.setOverScrollMode(2);
        ViewCompat.setNestedScrollingEnabled(view, false);
    }

    private boolean isConsecutiveParentAndBottom() {
        ViewParent parent = getParent();
        if (parent instanceof ConsecutiveScrollerLayout) {
            ConsecutiveScrollerLayout consecutiveScrollerLayout = (ConsecutiveScrollerLayout) parent;
            return consecutiveScrollerLayout.indexOfChild(this) == consecutiveScrollerLayout.getChildCount() - 1;
        }
        return false;
    }

    public int getAdjustHeight() {
        return this.mAdjustHeight;
    }

    public void setAdjustHeight(int i) {
        if (this.mAdjustHeight != i) {
            this.mAdjustHeight = i;
            requestLayout();
        }
    }

    @Override // com.sinovatech.unicom.basic.view.decklayout.IConsecutiveScroller
    public View getCurrentScrollerView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getX() == getScrollX() + getPaddingLeft()) {
                return childAt;
            }
        }
        return this;
    }

    @Override // com.sinovatech.unicom.basic.view.decklayout.IConsecutiveScroller
    public List<View> getScrolledViews() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                arrayList.add(getChildAt(i));
            }
        }
        return arrayList;
    }
}
