package com.sinovatech.unicom.basic.p315ui.view;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.sinovatech.unicom.common.UIUtils;

/* renamed from: com.sinovatech.unicom.basic.ui.view.FixedSpeedScroller */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FixedSpeedScroller extends Scroller {
    private Context context;
    private int mDuration;

    public FixedSpeedScroller(Activity activity) {
        super(activity);
        this.mDuration = 1000;
        this.context = activity;
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.mDuration = 1000;
        this.context = context;
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, int i) {
        super(context, interpolator);
        this.mDuration = 1000;
        this.context = context;
        this.mDuration = i;
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, (this.mDuration * i3) / Math.max(UIUtils.getScreenWidth((Activity) this.context), Math.abs(i3)));
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, (this.mDuration * i3) / Math.max(UIUtils.getScreenWidth((Activity) this.context), Math.abs(i3)));
    }
}
