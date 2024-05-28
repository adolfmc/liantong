package com.gridpagersnaphelper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BasePageIndicator extends View implements PageIndicator {
    private static final int DEFAULT_COLUMN = 1;
    protected int mCurrentPage;
    private OnPageChangeListener mListener;
    private int mPageColumn;
    protected RecyclerView mRecyclerView;
    private RecyclerView.OnScrollListener mScrollListener;
    private int mScrollState;

    public BasePageIndicator(Context context) {
        super(context);
        this.mPageColumn = 1;
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.gridpagersnaphelper.BasePageIndicator.1
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                BasePageIndicator.this.onPageScrollStateChanged(i);
                if (i == 0) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    int i2 = 0;
                    if (layoutManager instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                        i2 = gridLayoutManager.findFirstVisibleItemPosition() / (gridLayoutManager.getSpanCount() * BasePageIndicator.this.mPageColumn);
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        i2 = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    }
                    BasePageIndicator.this.onPageSelected(i2);
                }
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        };
    }

    public BasePageIndicator(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPageColumn = 1;
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.gridpagersnaphelper.BasePageIndicator.1
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                BasePageIndicator.this.onPageScrollStateChanged(i);
                if (i == 0) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    int i2 = 0;
                    if (layoutManager instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                        i2 = gridLayoutManager.findFirstVisibleItemPosition() / (gridLayoutManager.getSpanCount() * BasePageIndicator.this.mPageColumn);
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        i2 = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    }
                    BasePageIndicator.this.onPageSelected(i2);
                }
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        };
    }

    public BasePageIndicator(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPageColumn = 1;
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.gridpagersnaphelper.BasePageIndicator.1
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                BasePageIndicator.this.onPageScrollStateChanged(i2);
                if (i2 == 0) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    int i22 = 0;
                    if (layoutManager instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                        i22 = gridLayoutManager.findFirstVisibleItemPosition() / (gridLayoutManager.getSpanCount() * BasePageIndicator.this.mPageColumn);
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        i22 = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    }
                    BasePageIndicator.this.onPageSelected(i22);
                }
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i22) {
                super.onScrolled(recyclerView, i2, i22);
            }
        };
    }

    @Override // com.gridpagersnaphelper.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        if (this.mCurrentPage == i) {
            NBSActionInstrumentation.onPageSelectedExit();
            return;
        }
        this.mCurrentPage = i;
        invalidate();
        OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
        NBSActionInstrumentation.onPageSelectedExit();
    }

    @Override // com.gridpagersnaphelper.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.mScrollState = i;
        OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // com.gridpagersnaphelper.PageIndicator
    public void setRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.mScrollListener);
        }
        if (recyclerView.getAdapter() == null) {
            throw new IllegalStateException("RecyclerView does not have adapter instance.");
        }
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        invalidate();
    }

    @Override // com.gridpagersnaphelper.PageIndicator
    public void setRecyclerView(RecyclerView recyclerView, int i) {
        setRecyclerView(recyclerView);
        setCurrentItem(i);
    }

    @Override // com.gridpagersnaphelper.PageIndicator
    public void setCurrentItem(int i) {
        if (this.mRecyclerView == null) {
            throw new IllegalStateException("RecyclerView has not been bound.");
        }
        this.mRecyclerView.smoothScrollToPosition(eachPageItemCount() * i);
        this.mCurrentPage = i;
        invalidate();
    }

    @Override // com.gridpagersnaphelper.PageIndicator
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mListener = onPageChangeListener;
    }

    @Override // com.gridpagersnaphelper.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override // com.gridpagersnaphelper.PageIndicator
    public void setPageColumn(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("column must be not null");
        }
        this.mPageColumn = i;
    }

    protected int eachPageItemCount() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return 0;
        }
        int i = 1;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null && (layoutManager instanceof GridLayoutManager)) {
            i = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return i * this.mPageColumn;
    }

    protected int pageCount() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return 0;
        }
        int itemCount = this.mRecyclerView.getAdapter().getItemCount();
        int eachPageItemCount = eachPageItemCount();
        if (eachPageItemCount <= 0) {
            return 0;
        }
        return itemCount % eachPageItemCount == 0 ? itemCount / eachPageItemCount : (itemCount / eachPageItemCount) + 1;
    }
}
