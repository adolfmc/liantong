package com.zhpan.bannerview.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import com.zhpan.bannerview.provider.BannerScroller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CatchViewPager extends ViewPager {
    public static final int DEFAULT_SCROLL_DURATION = 800;
    private boolean disableTouchScroll;
    private ArrayList<Integer> mArrayList;
    private BannerScroller mBannerScroller;
    private boolean mOverlapStyle;
    private SparseIntArray mSparseIntArray;

    public CatchViewPager(Context context) {
        this(context, null);
    }

    public CatchViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mArrayList = new ArrayList<>();
        this.mSparseIntArray = new SparseIntArray();
        this.mOverlapStyle = false;
        hookScroller();
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.disableTouchScroll) {
                return false;
            }
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void hookScroller() {
        try {
            this.mBannerScroller = new BannerScroller(getContext());
            this.mBannerScroller.setDuration(800);
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, this.mBannerScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        if (this.mOverlapStyle) {
            if (i2 == 0 || this.mSparseIntArray.size() != i) {
                this.mArrayList.clear();
                this.mSparseIntArray.clear();
                int viewCenterX = getViewCenterX(this);
                for (int i3 = 0; i3 < i; i3++) {
                    int abs = Math.abs(viewCenterX - getViewCenterX(getChildAt(i3))) + 1;
                    this.mArrayList.add(Integer.valueOf(abs));
                    this.mSparseIntArray.append(abs, i3);
                }
                Collections.sort(this.mArrayList);
            }
            return this.mSparseIntArray.get(this.mArrayList.get((i - 1) - i2).intValue());
        }
        return super.getChildDrawingOrder(i, i2);
    }

    private int getViewCenterX(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[0] + (view.getWidth() / 2);
    }

    public void setOverlapStyle(boolean z) {
        this.mOverlapStyle = z;
    }

    public void setScrollDuration(int i) {
        this.mBannerScroller.setDuration(i);
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.disableTouchScroll) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void disableTouchScroll(boolean z) {
        this.disableTouchScroll = z;
    }
}
