package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils;

import android.graphics.Rect;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int i) {
        this.space = i;
    }

    @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.space;
        rect.left = i;
        rect.right = i;
        rect.bottom = i;
        if (recyclerView.getChildPosition(view) == 0) {
            rect.top = this.space;
        }
    }
}
