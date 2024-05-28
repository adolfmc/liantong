package com.zhpan.bannerview.provider;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BannerScroller extends Scroller {
    private int mDuration;

    public BannerScroller(Context context) {
        super(context);
        this.mDuration = 1000;
    }

    public BannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.mDuration = 1000;
    }

    public BannerScroller(Context context, Interpolator interpolator, boolean z) {
        super(context, interpolator, z);
        this.mDuration = 1000;
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.mDuration);
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.mDuration);
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }
}
