package com.scwang.smartrefresh.layout.util;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.listener.CoordinatorLayoutListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DesignUtil {
    public static void checkCoordinatorLayout(View view, RefreshKernel refreshKernel, CoordinatorLayoutListener coordinatorLayoutListener) {
        try {
            if (view instanceof CoordinatorLayout) {
                refreshKernel.getRefreshLayout().setEnableNestedScroll(false);
                wrapperCoordinatorLayout((ViewGroup) view, coordinatorLayoutListener);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void wrapperCoordinatorLayout(ViewGroup viewGroup, final CoordinatorLayoutListener coordinatorLayoutListener) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof AppBarLayout) {
                ((AppBarLayout) childAt).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.scwang.smartrefresh.layout.util.DesignUtil.1
                    @Override // android.support.design.widget.AppBarLayout.OnOffsetChangedListener, android.support.design.widget.AppBarLayout.BaseOnOffsetChangedListener
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        CoordinatorLayoutListener.this.onCoordinatorUpdate(i >= 0, appBarLayout.getTotalScrollRange() + i <= 0);
                    }
                });
            }
        }
    }
}
