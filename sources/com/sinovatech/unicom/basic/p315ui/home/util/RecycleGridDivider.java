package com.sinovatech.unicom.basic.p315ui.home.util;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/* renamed from: com.sinovatech.unicom.basic.ui.home.util.RecycleGridDivider */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecycleGridDivider extends RecyclerView.ItemDecoration {
    private int fullPosition;
    private int mEndFromSize;
    private boolean mIncludeEdge;
    private int mSpacing;
    private int mSpanCount;
    private int mStartFromSize;

    public RecycleGridDivider(int i) {
        this(i, true);
    }

    public RecycleGridDivider(int i, boolean z) {
        this.mEndFromSize = 0;
        this.fullPosition = -1;
        this.mSpacing = i;
        this.mIncludeEdge = z;
    }

    @Deprecated
    public RecycleGridDivider(int i, int i2) {
        this(i, i2, true);
    }

    @Deprecated
    public RecycleGridDivider(int i, int i2, boolean z) {
        this.mEndFromSize = 0;
        this.fullPosition = -1;
        this.mSpanCount = i;
        this.mSpacing = i2;
        this.mIncludeEdge = z;
    }

    @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int i;
        boolean z;
        int i2;
        int itemCount = state.getItemCount() - 1;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (this.mStartFromSize > childAdapterPosition || childAdapterPosition > itemCount - this.mEndFromSize) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            int spanCount = gridLayoutManager.getSpanCount();
            int spanSize = spanSizeLookup.getSpanSize(childAdapterPosition);
            this.mSpanCount = spanCount / spanSize;
            i2 = spanSizeLookup.getSpanIndex(childAdapterPosition, spanCount) / spanSize;
            i = spanSizeLookup.getSpanGroupIndex(childAdapterPosition, spanCount) - this.mStartFromSize;
            z = false;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            i2 = layoutParams.getSpanIndex();
            z = layoutParams.isFullSpan();
            this.mSpanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            i = -1;
        } else {
            i = -1;
            z = false;
            i2 = 0;
        }
        int i3 = childAdapterPosition - this.mStartFromSize;
        if (this.mIncludeEdge) {
            if (z) {
                rect.left = 0;
                rect.right = 0;
            } else {
                int i4 = this.mSpacing;
                int i5 = this.mSpanCount;
                rect.left = i4 - ((i2 * i4) / i5);
                rect.right = ((i2 + 1) * i4) / i5;
            }
            if (i > -1) {
                if (i < 1 && i3 < this.mSpanCount) {
                    rect.top = this.mSpacing;
                }
            } else {
                if (this.fullPosition == -1 && i3 < this.mSpanCount && z) {
                    this.fullPosition = i3;
                }
                int i6 = this.fullPosition;
                if ((i6 == -1 || i3 < i6) && i3 < this.mSpanCount) {
                    rect.top = this.mSpacing;
                }
            }
            rect.bottom = this.mSpacing;
            return;
        }
        if (z) {
            rect.left = 0;
            rect.right = 0;
        } else {
            int i7 = this.mSpacing;
            int i8 = this.mSpanCount;
            rect.left = (i2 * i7) / i8;
            rect.right = i7 - (((i2 + 1) * i7) / i8);
        }
        if (i > -1) {
            if (i >= 1) {
                rect.top = this.mSpacing;
                return;
            }
            return;
        }
        if (this.fullPosition == -1 && i3 < this.mSpanCount && z) {
            this.fullPosition = i3;
        }
        if (i3 < this.mSpanCount && ((!z || i3 == 0) && (this.fullPosition == -1 || i3 == 0))) {
            r0 = false;
        }
        if (r0) {
            rect.top = this.mSpacing;
        }
    }

    public RecycleGridDivider setStartFrom(int i) {
        this.mStartFromSize = i;
        return this;
    }

    public RecycleGridDivider setEndFromSize(int i) {
        this.mEndFromSize = i;
        return this;
    }

    public RecycleGridDivider setNoShowSpace(int i, int i2) {
        this.mStartFromSize = i;
        this.mEndFromSize = i2;
        return this;
    }
}
