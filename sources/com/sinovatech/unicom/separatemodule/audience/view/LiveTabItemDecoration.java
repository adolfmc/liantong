package com.sinovatech.unicom.separatemodule.audience.view;

import android.graphics.Rect;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import com.sinovatech.unicom.common.UIUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveTabItemDecoration extends RecyclerView.ItemDecoration {
    private boolean isOneNo;
    private int spaceBottom;
    private int spaceLeft;
    private int spaceRight;
    private int spaceTop;

    public LiveTabItemDecoration(int i, int i2, int i3, int i4, boolean z) {
        if (z) {
            i = UIUtils.dip2px(i);
            i2 = UIUtils.dip2px(i2);
            i3 = UIUtils.dip2px(i3);
            i4 = UIUtils.dip2px(i4);
        }
        this.spaceTop = i2;
        this.spaceBottom = i4;
        this.spaceLeft = i;
        this.spaceRight = i3;
    }

    public LiveTabItemDecoration(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (z) {
            i = UIUtils.dip2px(i);
            i2 = UIUtils.dip2px(i2);
            i3 = UIUtils.dip2px(i3);
            i4 = UIUtils.dip2px(i4);
        }
        this.isOneNo = z2;
        this.spaceTop = i2;
        this.spaceBottom = i4;
        this.spaceLeft = i;
        this.spaceRight = i3;
    }

    @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.isOneNo) {
            if (recyclerView.getChildAdapterPosition(view) != 0) {
                rect.left = this.spaceLeft;
                rect.right = this.spaceRight;
                rect.bottom = this.spaceBottom;
                rect.top = this.spaceTop;
                return;
            }
            return;
        }
        rect.left = this.spaceLeft;
        rect.right = this.spaceRight;
        rect.bottom = this.spaceBottom;
        rect.top = this.spaceTop;
    }
}
