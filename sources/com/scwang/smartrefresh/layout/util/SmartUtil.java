package com.scwang.smartrefresh.layout.util;

import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.p083v4.view.NestedScrollingChild;
import android.support.p083v4.view.NestedScrollingParent;
import android.support.p083v4.view.ScrollingView;
import android.support.p083v4.view.ViewPager;
import android.support.p083v4.widget.NestedScrollView;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmartUtil implements Interpolator {
    private static final float VISCOUS_FLUID_SCALE = 8.0f;
    private static float density = Resources.getSystem().getDisplayMetrics().density;
    private static final float VISCOUS_FLUID_NORMALIZE = 1.0f / viscousFluid(1.0f);
    private static final float VISCOUS_FLUID_OFFSET = 1.0f - (VISCOUS_FLUID_NORMALIZE * viscousFluid(1.0f));

    public static int measureViewHeight(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        if (layoutParams.height > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
        return view.getMeasuredHeight();
    }

    public static void scrollListBy(@NonNull AbsListView absListView, int i) {
        View childAt;
        if (Build.VERSION.SDK_INT >= 19) {
            absListView.scrollListBy(i);
        } else if (absListView instanceof ListView) {
            int firstVisiblePosition = absListView.getFirstVisiblePosition();
            if (firstVisiblePosition == -1 || (childAt = absListView.getChildAt(0)) == null) {
                return;
            }
            ((ListView) absListView).setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
        } else {
            absListView.smoothScrollBy(i, 0);
        }
    }

    public static boolean isScrollableView(View view) {
        return (view instanceof AbsListView) || (view instanceof ScrollView) || (view instanceof ScrollingView) || (view instanceof WebView) || (view instanceof NestedScrollingChild);
    }

    public static boolean isContentView(View view) {
        return isScrollableView(view) || (view instanceof ViewPager) || (view instanceof NestedScrollingParent);
    }

    public static void fling(View view, int i) {
        if (view instanceof ScrollView) {
            ((ScrollView) view).fling(i);
        } else if (view instanceof AbsListView) {
            if (Build.VERSION.SDK_INT >= 21) {
                ((AbsListView) view).fling(i);
            }
        } else if (view instanceof WebView) {
            ((WebView) view).flingScroll(0, i);
        } else if (view instanceof NestedScrollView) {
            ((NestedScrollView) view).fling(i);
        } else if (view instanceof RecyclerView) {
            ((RecyclerView) view).fling(0, i);
        }
    }

    public static boolean canRefresh(@NonNull View view, PointF pointF) {
        if (canScrollVertically(view, -1) && view.getVisibility() == 0) {
            return false;
        }
        if (!(view instanceof ViewGroup) || pointF == null) {
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        PointF pointF2 = new PointF();
        for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount - 1);
            if (isTransformedTouchPointInView(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                if ("fixed".equals(childAt.getTag()) || "fixed-bottom".equals(childAt.getTag())) {
                    return false;
                }
                pointF.offset(pointF2.x, pointF2.y);
                boolean canRefresh = canRefresh(childAt, pointF);
                pointF.offset(-pointF2.x, -pointF2.y);
                return canRefresh;
            }
        }
        return true;
    }

    public static boolean canLoadMore(@NonNull View view, PointF pointF, boolean z) {
        if (canScrollVertically(view, 1) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && pointF != null && !isScrollableView(view)) {
            ViewGroup viewGroup = (ViewGroup) view;
            PointF pointF2 = new PointF();
            for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (isTransformedTouchPointInView(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    if ("fixed".equals(childAt.getTag()) || "fixed-top".equals(childAt.getTag())) {
                        return false;
                    }
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean canLoadMore = canLoadMore(childAt, pointF, z);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return canLoadMore;
                }
            }
        }
        return z || canScrollVertically(view, -1);
    }

    public static boolean canScrollVertically(@NonNull View view, int i) {
        int i2;
        if (Build.VERSION.SDK_INT < 14) {
            if (!(view instanceof AbsListView)) {
                return i > 0 ? view.getScrollY() < 0 : view.getScrollY() > 0;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            AbsListView absListView = (AbsListView) view;
            int childCount = viewGroup.getChildCount();
            return i > 0 ? childCount > 0 && (absListView.getLastVisiblePosition() < (i2 = childCount - 1) || viewGroup.getChildAt(i2).getBottom() > view.getPaddingBottom()) : childCount > 0 && (absListView.getFirstVisiblePosition() > 0 || viewGroup.getChildAt(0).getTop() < view.getPaddingTop());
        }
        return view.canScrollVertically(i);
    }

    public static boolean isTransformedTouchPointInView(@NonNull View view, @NonNull View view2, float f, float f2, PointF pointF) {
        if (view2.getVisibility() != 0) {
            return false;
        }
        float[] fArr = {f, f2};
        fArr[0] = fArr[0] + (view.getScrollX() - view2.getLeft());
        fArr[1] = fArr[1] + (view.getScrollY() - view2.getTop());
        boolean z = fArr[0] >= 0.0f && fArr[1] >= 0.0f && fArr[0] < ((float) view2.getWidth()) && fArr[1] < ((float) view2.getHeight());
        if (z && pointF != null) {
            pointF.set(fArr[0] - f, fArr[1] - f2);
        }
        return z;
    }

    public static int dp2px(float f) {
        return (int) ((f * density) + 0.5f);
    }

    public static float px2dp(int i) {
        return i / density;
    }

    private static float viscousFluid(float f) {
        float f2 = f * VISCOUS_FLUID_SCALE;
        if (f2 < 1.0f) {
            return f2 - (1.0f - ((float) Math.exp(-f2)));
        }
        return ((1.0f - ((float) Math.exp(1.0f - f2))) * 0.63212055f) + 0.36787945f;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        float viscousFluid = VISCOUS_FLUID_NORMALIZE * viscousFluid(f);
        return viscousFluid > 0.0f ? viscousFluid + VISCOUS_FLUID_OFFSET : viscousFluid;
    }
}
