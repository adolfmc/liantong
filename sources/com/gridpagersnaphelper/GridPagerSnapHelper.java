package com.gridpagersnaphelper;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.LinearSmoothScroller;
import android.support.p086v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class GridPagerSnapHelper extends SnapHelper {
    private static final int DEFAULT_COLUMN = 1;
    private static final int DEFAULT_ROW = 1;
    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;
    private int mRow = 1;
    private int mColumn = 1;

    public GridPagerSnapHelper setRow(int i) {
        if (this.mRow <= 0) {
            throw new IllegalArgumentException("row must be greater than zero");
        }
        this.mRow = i;
        return this;
    }

    public GridPagerSnapHelper setColumn(int i) {
        if (this.mColumn <= 0) {
            throw new IllegalArgumentException("column must be greater than zero");
        }
        this.mColumn = i;
        return this;
    }

    @Override // com.gridpagersnaphelper.SnapHelper
    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(layoutManager, view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter(layoutManager, view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    private int distanceToCenter(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        if (layoutManager.canScrollHorizontally()) {
            int width = this.mRecyclerView.getWidth() / this.mColumn;
            int position = layoutManager.getPosition(view);
            return orientationHelper.getDecoratedStart(view) - (((position - (pageIndex(position) * countOfpage())) / this.mRow) * width);
        }
        int height = this.mRecyclerView.getHeight() / this.mRow;
        int position2 = layoutManager.getPosition(view);
        return orientationHelper.getDecoratedStart(view) - (((position2 - (pageIndex(position2) * countOfpage())) / this.mColumn) * height);
    }

    @Override // com.gridpagersnaphelper.SnapHelper
    @Nullable
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    private int pageIndex(int i) {
        return i / countOfpage();
    }

    private int countOfpage() {
        return this.mRow * this.mColumn;
    }

    @Nullable
    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int end;
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        if (layoutManager.getClipToPadding()) {
            end = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            end = orientationHelper.getEnd() / 2;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = layoutManager.getChildAt(i2);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - end);
            if (abs < i) {
                view = childAt;
                i = abs;
            }
        }
        return view;
    }

    @Override // com.gridpagersnaphelper.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        int position;
        PointF computeScrollVectorForPosition;
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View view = null;
        if (layoutManager.canScrollVertically()) {
            view = findStartView(layoutManager, getVerticalHelper(layoutManager));
        } else if (layoutManager.canScrollHorizontally()) {
            view = findStartView(layoutManager, getHorizontalHelper(layoutManager));
        }
        if (view == null || (position = layoutManager.getPosition(view)) == -1) {
            return -1;
        }
        boolean z = false;
        boolean z2 = layoutManager.canScrollHorizontally() ? i > 0 : i2 > 0;
        if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) && (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) != null && (computeScrollVectorForPosition.x < 0.0f || computeScrollVectorForPosition.y < 0.0f)) {
            z = true;
        }
        int pageIndex = pageIndex(position) * countOfpage();
        return z ? z2 ? pageIndex - countOfpage() : pageIndex : z2 ? pageIndex + countOfpage() : (pageIndex + countOfpage()) - 1;
    }

    @Nullable
    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = layoutManager.getChildAt(i2);
            int decoratedStart = orientationHelper.getDecoratedStart(childAt);
            if (decoratedStart < i) {
                view = childAt;
                i = decoratedStart;
            }
        }
        return view;
    }

    @Override // com.gridpagersnaphelper.SnapHelper
    protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new LinearSmoothScroller(this.mRecyclerView.getContext()) { // from class: com.gridpagersnaphelper.GridPagerSnapHelper.1
                @Override // android.support.p086v7.widget.RecyclerView.SmoothScroller
                public PointF computeScrollVectorForPosition(int i) {
                    return null;
                }

                @Override // android.support.p086v7.widget.LinearSmoothScroller, android.support.p086v7.widget.RecyclerView.SmoothScroller
                public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    GridPagerSnapHelper gridPagerSnapHelper = GridPagerSnapHelper.this;
                    int[] calculateDistanceToFinalSnap = gridPagerSnapHelper.calculateDistanceToFinalSnap(gridPagerSnapHelper.mRecyclerView.getLayoutManager(), view);
                    int i = calculateDistanceToFinalSnap[0];
                    int i2 = calculateDistanceToFinalSnap[1];
                    int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
                    if (calculateTimeForDeceleration > 0) {
                        action.update(i, i2, calculateTimeForDeceleration, this.mDecelerateInterpolator);
                    }
                }

                @Override // android.support.p086v7.widget.LinearSmoothScroller
                public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }

                @Override // android.support.p086v7.widget.LinearSmoothScroller
                public int calculateTimeForScrolling(int i) {
                    return Math.min(100, super.calculateTimeForScrolling(i));
                }
            };
        }
        return null;
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
}
