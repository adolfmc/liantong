package com.sinovatech.unicom.separatemodule.audience.util;

import android.content.Context;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.LinearSmoothScroller;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CenterLayoutManager extends LinearLayoutManager {
    public CenterLayoutManager(Context context) {
        super(context);
    }

    public CenterLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public CenterLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        CenterSmoothScroller centerSmoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        centerSmoothScroller.setTargetPosition(i);
        startSmoothScroll(centerSmoothScroller);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class CenterSmoothScroller extends LinearSmoothScroller {
        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override // android.support.p086v7.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return (i3 + ((i4 - i3) / 2)) - (i + ((i2 - i) / 2));
        }

        @Override // android.support.p086v7.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }
}
