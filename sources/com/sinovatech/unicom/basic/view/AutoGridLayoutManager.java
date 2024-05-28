package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AutoGridLayoutManager extends GridLayoutManager {
    private int measuredHeight;
    private int measuredWidth;
    private int totalPages;

    public AutoGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.measuredWidth = 0;
        this.measuredHeight = 0;
    }

    public AutoGridLayoutManager(Context context, int i) {
        super(context, i);
        this.measuredWidth = 0;
        this.measuredHeight = 0;
    }

    public AutoGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        this.measuredWidth = 0;
        this.measuredHeight = 0;
    }

    public void setTotalPages(int i) {
        this.totalPages = i;
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        View viewForPosition;
        if (this.measuredHeight <= 0 && (viewForPosition = recycler.getViewForPosition(0)) != null) {
            measureChild(viewForPosition, i, i2);
            this.measuredWidth = View.MeasureSpec.getSize(i);
            this.measuredHeight = viewForPosition.getMeasuredHeight() * getSpanCount();
        }
        setMeasuredDimension(this.measuredWidth, this.measuredHeight);
    }

    @Override // android.support.p086v7.widget.GridLayoutManager, android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.totalPages > 1) {
            return super.scrollHorizontallyBy(i, recycler, state);
        }
        return 0;
    }
}
