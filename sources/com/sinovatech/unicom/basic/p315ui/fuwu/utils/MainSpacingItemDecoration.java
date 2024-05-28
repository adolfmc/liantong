package com.sinovatech.unicom.basic.p315ui.fuwu.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.utils.MainSpacingItemDecoration */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private Activity activity;

    public MainSpacingItemDecoration(Activity activity) {
        this.activity = activity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131166079);
        int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131166075);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view) % 5;
        rect.left = dimensionPixelSize;
        rect.right = dimensionPixelSize;
        rect.bottom = dimensionPixelSize2;
    }
}
