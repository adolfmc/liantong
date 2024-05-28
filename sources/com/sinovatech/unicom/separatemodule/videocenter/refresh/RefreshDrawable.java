package com.sinovatech.unicom.separatemodule.videocenter.refresh;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class RefreshDrawable extends Drawable implements Animatable, Drawable.Callback {
    private PullRefreshLayout mRefreshLayout;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public abstract void offsetTopAndBottom(int i);

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public abstract void setColorSchemeColors(int[] iArr);

    public abstract void setPercent(float f);

    public RefreshDrawable(Context context, PullRefreshLayout pullRefreshLayout) {
        this.mRefreshLayout = pullRefreshLayout;
    }

    public Context getContext() {
        PullRefreshLayout pullRefreshLayout = this.mRefreshLayout;
        if (pullRefreshLayout != null) {
            return pullRefreshLayout.getContext();
        }
        return null;
    }

    public PullRefreshLayout getRefreshLayout() {
        return this.mRefreshLayout;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }
}
