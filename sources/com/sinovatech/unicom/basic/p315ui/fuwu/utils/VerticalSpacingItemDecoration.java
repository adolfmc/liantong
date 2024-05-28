package com.sinovatech.unicom.basic.p315ui.fuwu.utils;

import android.graphics.Rect;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.utils.VerticalSpacingItemDecoration */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VerticalSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int bottomSpacing;
    private int topSpacing;

    public VerticalSpacingItemDecoration(int i, int i2) {
        this.topSpacing = i;
        this.bottomSpacing = i2;
    }

    @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.bottom = this.bottomSpacing;
    }
}
