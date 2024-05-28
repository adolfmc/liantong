package com.sinovatech.unicom.basic.view.decklayout;

import android.graphics.Rect;
import android.os.Build;
import android.support.p083v4.view.ScrollingView;
import android.support.p083v4.view.ViewCompat;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ScrollUtils {
    static Method computeVerticalScrollExtentMethod;
    static Method computeVerticalScrollOffsetMethod;
    static Method computeVerticalScrollRangeMethod;
    private static final Rect mBounds = new Rect();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeVerticalScrollOffset(View view) {
        View scrolledView = getScrolledView(view);
        if (scrolledView instanceof ScrollingView) {
            return ((ScrollingView) scrolledView).computeVerticalScrollOffset();
        }
        try {
            if (computeVerticalScrollOffsetMethod == null) {
                computeVerticalScrollOffsetMethod = View.class.getDeclaredMethod("computeVerticalScrollOffset", new Class[0]);
                computeVerticalScrollOffsetMethod.setAccessible(true);
            }
            Object invoke = computeVerticalScrollOffsetMethod.invoke(scrolledView, new Object[0]);
            if (invoke != null) {
                return ((Integer) invoke).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrolledView.getScrollY();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeVerticalScrollRange(View view) {
        View scrolledView = getScrolledView(view);
        if (scrolledView instanceof ScrollingView) {
            return ((ScrollingView) scrolledView).computeVerticalScrollRange();
        }
        try {
            if (computeVerticalScrollRangeMethod == null) {
                computeVerticalScrollRangeMethod = View.class.getDeclaredMethod("computeVerticalScrollRange", new Class[0]);
                computeVerticalScrollRangeMethod.setAccessible(true);
            }
            Object invoke = computeVerticalScrollRangeMethod.invoke(scrolledView, new Object[0]);
            if (invoke != null) {
                return ((Integer) invoke).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrolledView.getHeight();
    }

    static int computeVerticalScrollExtent(View view) {
        View scrolledView = getScrolledView(view);
        if (scrolledView instanceof ScrollingView) {
            return ((ScrollingView) scrolledView).computeVerticalScrollExtent();
        }
        try {
            if (computeVerticalScrollExtentMethod == null) {
                computeVerticalScrollExtentMethod = View.class.getDeclaredMethod("computeVerticalScrollExtent", new Class[0]);
                computeVerticalScrollExtentMethod.setAccessible(true);
            }
            Object invoke = computeVerticalScrollExtentMethod.invoke(scrolledView, new Object[0]);
            if (invoke != null) {
                return ((Integer) invoke).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrolledView.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getScrollTopOffset(View view) {
        if (isConsecutiveScrollerChild(view) && canScrollVertically(view, -1)) {
            return Math.min(-computeVerticalScrollOffset(view), -1);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getScrollBottomOffset(View view) {
        if (isConsecutiveScrollerChild(view) && canScrollVertically(view, 1)) {
            return Math.max((computeVerticalScrollRange(view) - computeVerticalScrollOffset(view)) - computeVerticalScrollExtent(view), 1);
        }
        return 0;
    }

    static boolean canScrollHorizontally(View view) {
        return isConsecutiveScrollerChild(view) && (view.canScrollHorizontally(1) || view.canScrollHorizontally(-1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean canScrollVertically(View view) {
        return isConsecutiveScrollerChild(view) && (canScrollVertically(view, 1) || canScrollVertically(view, -1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean canScrollVertically(View view, int i) {
        boolean reverseLayout;
        int itemCount;
        View scrolledView = getScrolledView(view);
        if (scrolledView.getVisibility() == 8) {
            return false;
        }
        if (scrolledView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) scrolledView;
            if (Build.VERSION.SDK_INT >= 19) {
                return absListView.canScrollList(i);
            }
            return false;
        } else if (scrolledView instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) scrolledView;
            if ((recyclerView.canScrollHorizontally(1) || recyclerView.canScrollHorizontally(-1)) && !recyclerView.canScrollVertically(i)) {
                return false;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (layoutManager == null || adapter == null || adapter.getItemCount() <= 0) {
                return false;
            }
            if (layoutManager instanceof LinearLayoutManager) {
                reverseLayout = ((LinearLayoutManager) layoutManager).getReverseLayout();
            } else {
                reverseLayout = layoutManager instanceof StaggeredGridLayoutManager ? ((StaggeredGridLayoutManager) layoutManager).getReverseLayout() : false;
            }
            if (reverseLayout) {
                itemCount = i < 0 ? adapter.getItemCount() - 1 : 0;
            } else {
                itemCount = i > 0 ? adapter.getItemCount() - 1 : 0;
            }
            if (layoutManager.findViewByPosition(itemCount) == null) {
                return true;
            }
            int childCount = recyclerView.getChildCount();
            if (i > 0) {
                for (int i2 = childCount - 1; i2 >= 0; i2--) {
                    recyclerView.getDecoratedBoundsWithMargins(recyclerView.getChildAt(i2), mBounds);
                    if (mBounds.bottom > recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                        return true;
                    }
                }
                return false;
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                recyclerView.getDecoratedBoundsWithMargins(recyclerView.getChildAt(i3), mBounds);
                if (mBounds.top < recyclerView.getPaddingTop()) {
                    return true;
                }
            }
            return false;
        } else {
            return scrolledView.canScrollVertically(i);
        }
    }

    static List<View> getTouchViews(View view, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        addTouchViews(arrayList, view, i, i2);
        return arrayList;
    }

    private static void addTouchViews(List<View> list, View view, int i, int i2) {
        if (isConsecutiveScrollerChild(view) && isTouchPointInView(view, i, i2)) {
            list.add(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    addTouchViews(list, viewGroup.getChildAt(i3), i, i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTouchPointInView(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        return i >= i3 && i <= view.getMeasuredWidth() + i3 && i2 >= i4 && i2 <= view.getMeasuredHeight() + i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getRawX(View view, MotionEvent motionEvent, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            return (int) motionEvent.getRawX(i);
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return (int) (iArr[0] + motionEvent.getX(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getRawY(View view, MotionEvent motionEvent, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            return (int) motionEvent.getRawY(i);
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return (int) (iArr[1] + motionEvent.getY(i));
    }

    static List<Integer> getScrollOffsetForViews(List<View> list) {
        ArrayList arrayList = new ArrayList();
        for (View view : list) {
            arrayList.add(Integer.valueOf(computeVerticalScrollOffset(view)));
        }
        return arrayList;
    }

    static boolean equalsOffsets(List<Integer> list, List<Integer> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!list.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isConsecutiveScrollerChild(View view) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ConsecutiveScrollerLayout.LayoutParams) {
                return ((ConsecutiveScrollerLayout.LayoutParams) layoutParams).isConsecutive;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View getScrolledView(View view) {
        View scrollChild = getScrollChild(view);
        while (scrollChild instanceof IConsecutiveScroller) {
            View currentScrollerView = ((IConsecutiveScroller) scrollChild).getCurrentScrollerView();
            if (scrollChild == currentScrollerView) {
                return currentScrollerView;
            }
            scrollChild = currentScrollerView;
        }
        return scrollChild;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View getScrollChild(View view) {
        int i;
        View findViewById;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if ((layoutParams instanceof ConsecutiveScrollerLayout.LayoutParams) && (i = ((ConsecutiveScrollerLayout.LayoutParams) layoutParams).scrollChild) != -1 && (findViewById = view.findViewById(i)) != null) {
                return findViewById;
            }
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean startInterceptRequestLayout(RecyclerView recyclerView) {
        if ("InterceptRequestLayout".equals(recyclerView.getTag())) {
            try {
                Method declaredMethod = RecyclerView.class.getDeclaredMethod("startInterceptRequestLayout", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(recyclerView, new Object[0]);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void stopInterceptRequestLayout(RecyclerView recyclerView) {
        if ("InterceptRequestLayout".equals(recyclerView.getTag())) {
            try {
                Method declaredMethod = RecyclerView.class.getDeclaredMethod("stopInterceptRequestLayout", Boolean.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(recyclerView, false);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isConsecutiveScrollParent(View view) {
        while ((view.getParent() instanceof ViewGroup) && !(view.getParent() instanceof ConsecutiveScrollerLayout)) {
            view = (View) view.getParent();
        }
        if (view.getParent() instanceof ConsecutiveScrollerLayout) {
            return isConsecutiveScrollerChild(view);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:5:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isHorizontalScroll(android.view.View r1, int r2, int r3) {
        /*
            java.util.List r1 = getTouchViews(r1, r2, r3)
            java.util.Iterator r1 = r1.iterator()
        L8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L23
            java.lang.Object r2 = r1.next()
            android.view.View r2 = (android.view.View) r2
            r3 = 1
            boolean r0 = r2.canScrollHorizontally(r3)
            if (r0 != 0) goto L22
            r0 = -1
            boolean r2 = r2.canScrollHorizontally(r0)
            if (r2 == 0) goto L8
        L22:
            return r3
        L23:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.view.decklayout.ScrollUtils.isHorizontalScroll(android.view.View, int, int):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTouchNotTriggerScrollStick(View view, int i, int i2) {
        List<ConsecutiveScrollerLayout> inTouchCSLayout = getInTouchCSLayout(view, i, i2);
        for (int size = inTouchCSLayout.size() - 1; size >= 0; size--) {
            ConsecutiveScrollerLayout consecutiveScrollerLayout = inTouchCSLayout.get(size);
            View topViewInTouch = getTopViewInTouch(consecutiveScrollerLayout, i, i2);
            if (topViewInTouch != null && consecutiveScrollerLayout.isStickyView(topViewInTouch) && consecutiveScrollerLayout.theChildIsStick(topViewInTouch) && !((ConsecutiveScrollerLayout.LayoutParams) topViewInTouch.getLayoutParams()).isTriggerScroll) {
                return true;
            }
        }
        return false;
    }

    static List<ConsecutiveScrollerLayout> getInTouchCSLayout(View view, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (View view2 : getTouchViews(view, i, i2)) {
            if (view2 instanceof ConsecutiveScrollerLayout) {
                arrayList.add((ConsecutiveScrollerLayout) view2);
            }
        }
        return arrayList;
    }

    static View getTopViewInTouch(ConsecutiveScrollerLayout consecutiveScrollerLayout, int i, int i2) {
        int childCount = consecutiveScrollerLayout.getChildCount();
        View view = null;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = consecutiveScrollerLayout.getChildAt(i3);
            if (childAt.getVisibility() == 0 && isTouchPointInView(childAt, i, i2) && (view == null || ViewCompat.getZ(childAt) > ViewCompat.getZ(view) || (ViewCompat.getZ(childAt) == ViewCompat.getZ(view) && consecutiveScrollerLayout.getDrawingPosition(childAt) > consecutiveScrollerLayout.getDrawingPosition(view)))) {
                view = childAt;
            }
        }
        return view;
    }
}
