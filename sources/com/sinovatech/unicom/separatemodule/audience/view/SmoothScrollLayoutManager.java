package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.LinearSmoothScroller;
import android.support.p086v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SmoothScrollLayoutManager extends LinearLayoutManager {
    public SmoothScrollLayoutManager(Context context) {
        super(context);
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) { // from class: com.sinovatech.unicom.separatemodule.audience.view.SmoothScrollLayoutManager.1
            @Override // android.support.p086v7.widget.LinearSmoothScroller
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 200.0f / displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }
}
