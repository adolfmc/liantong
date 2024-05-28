package com.sinovatech.unicom.separatemodule.video.viewpager;

import android.content.Context;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.PagerSnapHelper;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ViewPagerLayoutManager extends LinearLayoutManager {
    private static final String TAG = "ViewPagerLayoutManager";
    private boolean isScrollEnabled;
    private RecyclerView.OnChildAttachStateChangeListener mChildAttachStateChangeListener;
    private int mDrift;
    private OnViewPagerListener mOnViewPagerListener;
    private PagerSnapHelper mPagerSnapHelper;
    private RecyclerView mRecyclerView;

    public ViewPagerLayoutManager(Context context, int i) {
        super(context, i, false);
        this.isScrollEnabled = true;
        this.mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager.1
            @Override // android.support.p086v7.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                if (ViewPagerLayoutManager.this.mDrift >= 0) {
                    if (ViewPagerLayoutManager.this.mOnViewPagerListener != null) {
                        ViewPagerLayoutManager.this.mOnViewPagerListener.onPageRelease(true, ViewPagerLayoutManager.this.getPosition(view));
                    }
                } else if (ViewPagerLayoutManager.this.mOnViewPagerListener != null) {
                    ViewPagerLayoutManager.this.mOnViewPagerListener.onPageRelease(false, ViewPagerLayoutManager.this.getPosition(view));
                }
            }
        };
        init();
    }

    public ViewPagerLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.isScrollEnabled = true;
        this.mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager.1
            @Override // android.support.p086v7.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                if (ViewPagerLayoutManager.this.mDrift >= 0) {
                    if (ViewPagerLayoutManager.this.mOnViewPagerListener != null) {
                        ViewPagerLayoutManager.this.mOnViewPagerListener.onPageRelease(true, ViewPagerLayoutManager.this.getPosition(view));
                    }
                } else if (ViewPagerLayoutManager.this.mOnViewPagerListener != null) {
                    ViewPagerLayoutManager.this.mOnViewPagerListener.onPageRelease(false, ViewPagerLayoutManager.this.getPosition(view));
                }
            }
        };
        init();
    }

    private void init() {
        this.mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mPagerSnapHelper.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.addOnChildAttachStateChangeListener(this.mChildAttachStateChangeListener);
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScrollEnabled(boolean z) {
        this.isScrollEnabled = z;
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.isScrollEnabled && super.canScrollVertically();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        switch (i) {
            case 0:
                try {
                    int position = getPosition(this.mPagerSnapHelper.findSnapView(this));
                    if (this.mOnViewPagerListener != null) {
                        boolean z = true;
                        if (getChildCount() == 1) {
                            OnViewPagerListener onViewPagerListener = this.mOnViewPagerListener;
                            if (position != getItemCount() - 1) {
                                z = false;
                            }
                            onViewPagerListener.onPageSelected(position, z);
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                try {
                    getPosition(this.mPagerSnapHelper.findSnapView(this));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 2:
                try {
                    getPosition(this.mPagerSnapHelper.findSnapView(this));
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        OnViewPagerListener onViewPagerListener = this.mOnViewPagerListener;
        if (onViewPagerListener != null) {
            onViewPagerListener.onLayoutComplete();
        }
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = i;
        return super.scrollVerticallyBy(i, recycler, state);
    }

    @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            this.mDrift = i;
            return super.scrollHorizontallyBy(i, recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setOnViewPagerListener(OnViewPagerListener onViewPagerListener) {
        this.mOnViewPagerListener = onViewPagerListener;
    }
}
