package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.sinovatech.unicom.basic.p315ui.adapter.HomeGridAdapter;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PageRecyclerView extends RecyclerView {
    private int currentPage;
    private AutoGridLayoutManager mAutoGridLayoutManager;
    private Context mContext;
    private PageIndicatorView mIndicatorView;
    private HomeGridAdapter myAdapter;
    private int pageMargin;
    private int realPosition;
    private int scrollState;
    private float scrollX;
    private int shortestDistance;
    private float slideDistance;
    private int spanColumn;
    private int spanRow;
    private int totalPage;

    public PageRecyclerView(Context context) {
        this(context, null);
    }

    public PageRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PageRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = null;
        this.myAdapter = null;
        this.slideDistance = 0.0f;
        this.scrollX = 0.0f;
        this.spanRow = 2;
        this.spanColumn = 5;
        this.totalPage = 0;
        this.currentPage = 1;
        this.pageMargin = 0;
        this.mIndicatorView = null;
        this.realPosition = 0;
        this.scrollState = 0;
        defaultInit(context);
    }

    private void defaultInit(Context context) {
        this.mContext = context;
        this.mAutoGridLayoutManager = new AutoGridLayoutManager(this.mContext, this.spanRow, 0, false);
        setLayoutManager(this.mAutoGridLayoutManager);
        setOverScrollMode(2);
    }

    public void setPageSize(int i, int i2) {
        if (i <= 0) {
            i = this.spanRow;
        }
        this.spanRow = i;
        if (i2 <= 0) {
            i2 = this.spanColumn;
        }
        this.spanColumn = i2;
        this.mAutoGridLayoutManager = new AutoGridLayoutManager(this.mContext, this.spanRow, 0, false);
        setLayoutManager(this.mAutoGridLayoutManager);
    }

    public void setPageMargin(int i) {
        this.pageMargin = i;
    }

    public void setIndicator(PageIndicatorView pageIndicatorView) {
        this.mIndicatorView = pageIndicatorView;
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.shortestDistance = getMeasuredWidth() / 3;
    }

    @Override // android.support.p086v7.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        this.myAdapter = (HomeGridAdapter) adapter;
    }

    @Override // android.support.p086v7.widget.RecyclerView
    public void onScrollStateChanged(int i) {
        switch (i) {
            case 0:
                float f = this.slideDistance;
                if (f != 0.0f) {
                    this.scrollState = 0;
                    if (f < 0.0f) {
                        this.currentPage = (int) Math.ceil(this.scrollX / getWidth());
                        if ((this.currentPage * getWidth()) - this.scrollX < this.shortestDistance) {
                            this.currentPage++;
                        }
                    } else {
                        this.currentPage = ((int) Math.ceil(this.scrollX / getWidth())) + 1;
                        int i2 = this.currentPage;
                        int i3 = this.totalPage;
                        if (i2 <= i3) {
                            if (this.scrollX - ((i2 - 2) * getWidth()) < this.shortestDistance) {
                                this.currentPage--;
                            }
                        } else {
                            this.currentPage = i3;
                        }
                    }
                    smoothScrollBy((int) (((this.currentPage - 1) * getWidth()) - this.scrollX), 0);
                    this.mIndicatorView.setSelectedPage(this.currentPage - 1);
                    this.slideDistance = 0.0f;
                    break;
                }
                break;
            case 1:
                this.scrollState = 1;
                break;
            case 2:
                this.scrollState = 2;
                break;
        }
        super.onScrollStateChanged(i);
    }

    @Override // android.support.p086v7.widget.RecyclerView
    public void onScrolled(int i, int i2) {
        float f = i;
        this.scrollX += f;
        if (this.scrollState == 1) {
            this.slideDistance += f;
        }
        super.onScrolled(i, i2);
    }
}
