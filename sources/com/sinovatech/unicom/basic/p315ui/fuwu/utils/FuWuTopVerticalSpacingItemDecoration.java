package com.sinovatech.unicom.basic.p315ui.fuwu.utils;

import android.graphics.Rect;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.utils.FuWuTopVerticalSpacingItemDecoration */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FuWuTopVerticalSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int bottomSpacing;
    private boolean includeEdge;
    private int spacing;
    private int spanCount;

    public FuWuTopVerticalSpacingItemDecoration(int i, int i2, boolean z, int i3) {
        this.bottomSpacing = i3;
        this.spanCount = i;
        this.spacing = i2;
        this.includeEdge = z;
    }

    @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.spanCount;
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (this.includeEdge) {
            int i2 = this.spacing;
            int i3 = this.spanCount;
            rect.left = i2 - ((i * i2) / i3);
            rect.right = ((i + 1) * i2) / i3;
            if (childAdapterPosition < i3) {
                rect.top = this.bottomSpacing;
            }
            if (isLastRow(childAdapterPosition, itemCount)) {
                rect.bottom = 0;
                return;
            } else {
                rect.bottom = this.bottomSpacing;
                return;
            }
        }
        int i4 = this.spacing;
        int i5 = this.spanCount;
        rect.left = (i * i4) / i5;
        rect.right = i4 - (((i + 1) * i4) / i5);
        if (childAdapterPosition >= i5) {
            rect.top = this.bottomSpacing;
        }
    }

    private boolean isLastRow(int i, int i2) {
        int i3 = this.spanCount;
        int i4 = i2 % i3;
        int i5 = i2 / i3;
        if (i4 == 0) {
            i5--;
        }
        return i / this.spanCount == i5;
    }
}
