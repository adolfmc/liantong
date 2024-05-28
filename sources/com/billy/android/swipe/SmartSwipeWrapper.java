package com.billy.android.swipe;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.billy.android.swipe.internal.SwipeHelper;
import com.billy.android.swipe.internal.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SmartSwipeWrapper extends ViewGroup {
    private static final int NESTED_TYPE_INVALID = -1;
    private Boolean flyToClose;
    private Boolean flyToOpen;
    protected final List<SwipeConsumer> mConsumers;
    protected View mContentView;
    protected int mCurNestedType;
    protected SwipeHelper mHelper;
    protected final List<SwipeHelper> mHelpers;
    protected boolean mInflateFromXml;
    protected boolean mIsNestedScrollingEnabled;
    private final ArrayList<View> mMatchParentChildren;
    protected boolean mNestedFlyConsumed;
    protected boolean mNestedInProgress;
    protected int[] mParentOffsetInWindow;

    protected void init() {
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public SmartSwipeWrapper(Context context) {
        this(context, null, 0);
    }

    public SmartSwipeWrapper(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmartSwipeWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHelpers = new LinkedList();
        this.mConsumers = new LinkedList();
        this.mIsNestedScrollingEnabled = true;
        this.mMatchParentChildren = new ArrayList<>(1);
        this.mCurNestedType = -1;
        this.mParentOffsetInWindow = new int[2];
        init();
    }

    @TargetApi(21)
    public SmartSwipeWrapper(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mHelpers = new LinkedList();
        this.mConsumers = new LinkedList();
        this.mIsNestedScrollingEnabled = true;
        this.mMatchParentChildren = new ArrayList<>(1);
        this.mCurNestedType = -1;
        this.mParentOffsetInWindow = new int[2];
        init();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mHelper = null;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mNestedInProgress) {
            SwipeHelper swipeHelper = this.mHelper;
            if (swipeHelper != null) {
                return swipeHelper.shouldInterceptTouchEvent(motionEvent);
            }
            for (SwipeHelper swipeHelper2 : this.mHelpers) {
                if (swipeHelper2.shouldInterceptTouchEvent(motionEvent)) {
                    this.mHelper = swipeHelper2;
                    return true;
                }
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mNestedInProgress) {
            SwipeHelper swipeHelper = this.mHelper;
            if (swipeHelper != null) {
                swipeHelper.processTouchEvent(motionEvent);
            } else {
                for (SwipeHelper swipeHelper2 : this.mHelpers) {
                    swipeHelper2.processTouchEvent(motionEvent);
                    if (swipeHelper2.getDragState() == 1) {
                        this.mHelper = swipeHelper2;
                        return true;
                    }
                }
            }
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null) {
                swipeConsumer.dispatchDraw(canvas);
            }
        }
    }

    public void drawChild(Canvas canvas, View view) {
        drawChild(canvas, view, getDrawingTime());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null) {
                swipeConsumer.onDraw(canvas);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int childMeasureSpec;
        int childMeasureSpec2;
        int childCount = getChildCount();
        boolean z = (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) ? false : true;
        this.mMatchParentChildren.clear();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(i, 0, layoutParams.width), getChildMeasureSpec(i2, 0, layoutParams.height));
            i5 = Math.max(i5, childAt.getMeasuredWidth());
            i4 = Math.max(i4, childAt.getMeasuredHeight());
            i6 = combineMeasuredStates(i6, childAt.getMeasuredState());
            if (z && (layoutParams.width == -1 || layoutParams.height == -1)) {
                this.mMatchParentChildren.add(childAt);
            }
        }
        setMeasuredDimension(resolveSizeAndState(Math.max(i5, getSuggestedMinimumWidth()), i, i6), resolveSizeAndState(Math.max(i4, getSuggestedMinimumHeight()), i2, i6 << 16));
        int size = this.mMatchParentChildren.size();
        if (size > 1) {
            for (int i8 = 0; i8 < size; i8++) {
                View view = this.mMatchParentChildren.get(i8);
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                if (layoutParams2.width == -1) {
                    i3 = 1073741824;
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredWidth()), 1073741824);
                } else {
                    i3 = 1073741824;
                    childMeasureSpec = getChildMeasureSpec(i, 0, layoutParams2.width);
                }
                if (layoutParams2.height == -1) {
                    childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredHeight()), i3);
                } else {
                    childMeasureSpec2 = getChildMeasureSpec(i2, 0, layoutParams2.height);
                }
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null) {
                swipeConsumer.onMeasure(i, i2);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        View view;
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper != null) {
            z2 = swipeHelper.getSwipeConsumer().onLayout(z, i, i2, i3, i4);
        } else {
            boolean z3 = false;
            for (SwipeConsumer swipeConsumer : this.mConsumers) {
                if (swipeConsumer != null && swipeConsumer.onLayout(z, i, i2, i3, i4)) {
                    z3 = true;
                }
            }
            z2 = z3;
        }
        if (z2 || (view = this.mContentView) == null) {
            return;
        }
        view.layout(0, 0, view.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mInflateFromXml = true;
        int childCount = getChildCount();
        if (childCount <= 0 || this.mContentView != null) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof LayoutParams) && ((LayoutParams) layoutParams).gravity == 0) {
                setContentView(childAt);
                return;
            }
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mHelpers.isEmpty()) {
            return;
        }
        boolean z = false;
        for (SwipeHelper swipeHelper : this.mHelpers) {
            if (swipeHelper.continueSettling()) {
                z = true;
            }
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public <T extends SwipeConsumer> T addConsumer(T t) {
        if (t != null) {
            this.mConsumers.add(t);
            SwipeHelper swipeHelper = t.getSwipeHelper();
            if (swipeHelper == null) {
                swipeHelper = SwipeHelper.create(this, t.getSensitivity(), t, t.getInterpolator());
            }
            t.onAttachToWrapper(this, swipeHelper);
            this.mHelpers.add(swipeHelper);
        }
        return t;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            swipeConsumer.close();
        }
    }

    public SmartSwipeWrapper removeAllConsumers() {
        Iterator<SwipeConsumer> it = this.mConsumers.iterator();
        while (it.hasNext()) {
            SwipeConsumer next = it.next();
            it.remove();
            if (next != null) {
                next.onDetachFromWrapper();
                SwipeHelper swipeHelper = next.getSwipeHelper();
                this.mHelpers.remove(swipeHelper);
                if (this.mHelper == swipeHelper) {
                    this.mHelper = null;
                }
            }
        }
        return this;
    }

    public SmartSwipeWrapper removeConsumer(SwipeConsumer swipeConsumer) {
        if (this.mConsumers.remove(swipeConsumer)) {
            swipeConsumer.onDetachFromWrapper();
            SwipeHelper swipeHelper = swipeConsumer.getSwipeHelper();
            this.mHelpers.remove(swipeHelper);
            if (this.mHelper == swipeHelper) {
                this.mHelper = null;
            }
        }
        return this;
    }

    public SwipeConsumer getConsumerByType(Class<? extends SwipeConsumer> cls) {
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null && swipeConsumer.getClass() == cls) {
                return swipeConsumer;
            }
        }
        return null;
    }

    public void setContentView(View view) {
        if (view == null || this.mContentView == view) {
            return;
        }
        this.mContentView = view;
        if (view.getParent() == null) {
            addView(view);
        }
    }

    public View getContentView() {
        return this.mContentView;
    }

    public List<SwipeConsumer> getAllConsumers() {
        return this.mConsumers;
    }

    public SmartSwipeWrapper enableDirection(int i) {
        return enableDirection(i, true);
    }

    public SmartSwipeWrapper enableDirection(int i, boolean z) {
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            swipeConsumer.enableDirection(i, z);
        }
        return this;
    }

    public boolean isInflateFromXml() {
        return this.mInflateFromXml;
    }

    public void consumeInflateFromXml() {
        this.mInflateFromXml = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int UNSPECIFIED_GRAVITY = 0;
        public int gravity;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3336R.styleable.SmartSwipeWrapper_Layout);
            this.gravity = obtainStyledAttributes.getInt(C3336R.styleable.SmartSwipeWrapper_Layout_swipe_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2);
            this.gravity = 0;
            this.gravity = i3;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setNestedScrollingEnabled(this.mIsNestedScrollingEnabled);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.mIsNestedScrollingEnabled = z;
        if (Build.VERSION.SDK_INT >= 21) {
            super.setNestedScrollingEnabled(z);
        }
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.isNestedScrollingEnabled();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return onStartNestedScroll(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        onNestedScrollAccepted(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        onNestedPreScroll(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        onNestedScroll(view, i, i2, i3, i4, 0);
    }

    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        boolean z;
        boolean z2 = (i & 2) != 0;
        boolean z3 = (i & 1) != 0;
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            int direction = swipeConsumer.getDirection();
            if (direction != 0) {
                if ((z3 && (direction == 1 || direction == 2)) || (z2 && (direction == 4 || direction == 8))) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } else if ((z3 && (swipeConsumer.isLeftEnable() || swipeConsumer.isRightEnable())) || (z2 && (swipeConsumer.isTopEnable() || swipeConsumer.isBottomEnable()))) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                startNestedScroll(i, i2);
                return true;
            }
        }
        return false;
    }

    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        this.mNestedInProgress = true;
        this.mNestedFlyConsumed = false;
        this.flyToClose = null;
        this.flyToOpen = null;
        this.mCurNestedType = i2;
        helperOnNestedScrollAccepted(view, view2, i, i2);
    }

    public void onStopNestedScroll(View view, int i) {
        this.mNestedInProgress = false;
        helperOnStopNestedScroll(view, i);
        if (i == this.mCurNestedType) {
            this.mCurNestedType = -1;
            SwipeHelper swipeHelper = this.mHelper;
            if (swipeHelper != null) {
                swipeHelper.nestedScrollingRelease();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.onNestedPreFling(view, f, f2);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.onNestedFling(view, f, f2, z);
        }
        return false;
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        helperOnNestedScroll(view, i, i2, i3, i4, i5);
        int[] iArr = this.mParentOffsetInWindow;
        int i6 = i3 + iArr[0];
        int i7 = i4 + iArr[1];
        if (i6 == 0 && i7 == 0) {
            return;
        }
        if (i5 == 1) {
            requestDisallowInterceptTouchEvent(false);
        }
        wrapperNestedScroll(i6, i7, new int[2], i5);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        int i4;
        int i5;
        boolean z;
        int i6;
        int i7;
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper == null || swipeHelper.getSwipeConsumer().getProgress() == 0.0f) {
            Arrays.fill(iArr, 0);
            helperOnNestedPreScroll(view, i, i2, iArr, i3);
            i4 = iArr[0] + 0;
            i5 = iArr[1] + 0;
            z = true;
        } else {
            i4 = 0;
            i5 = 0;
            z = false;
        }
        SwipeHelper swipeHelper2 = this.mHelper;
        if (swipeHelper2 == null || swipeHelper2.getSwipeConsumer().getDirection() == 0) {
            i6 = i4;
            i7 = i5;
        } else {
            Arrays.fill(iArr, 0);
            wrapperNestedScroll(i - i4, i2 - i5, iArr, i3);
            i6 = i4 - iArr[0];
            i7 = i5 - iArr[1];
        }
        if (!z) {
            Arrays.fill(iArr, 0);
            helperOnNestedPreScroll(view, i - i6, i2 - i7, iArr, i3);
            i6 += iArr[0];
            i7 += iArr[1];
        }
        iArr[0] = i6;
        iArr[1] = i7;
    }

    private void wrapperNestedScroll(int i, int i2, int[] iArr, int i3) {
        if (this.mCurNestedType == -1) {
            this.mCurNestedType = i3;
            this.mNestedFlyConsumed = false;
            this.flyToClose = null;
            this.flyToOpen = null;
        }
        boolean z = i3 == 1;
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper != null) {
            SwipeConsumer swipeConsumer = swipeHelper.getSwipeConsumer();
            float overSwipeFactor = swipeConsumer.getOverSwipeFactor() + 1.0f;
            if (z) {
                if (this.flyToOpen == null) {
                    int direction = swipeConsumer.getDirection();
                    if (direction != 4) {
                        if (direction == 8) {
                            this.flyToOpen = Boolean.valueOf(i2 > 0);
                            this.flyToClose = Boolean.valueOf(i2 < 0);
                            if (i2 == 0) {
                                return;
                            }
                        } else {
                            switch (direction) {
                                case 1:
                                    this.flyToOpen = Boolean.valueOf(i < 0);
                                    this.flyToClose = Boolean.valueOf(i > 0);
                                    if (i == 0) {
                                        return;
                                    }
                                    break;
                                case 2:
                                    this.flyToOpen = Boolean.valueOf(i > 0);
                                    this.flyToClose = Boolean.valueOf(i < 0);
                                    if (i == 0) {
                                        return;
                                    }
                                    break;
                                default:
                                    this.flyToClose = false;
                                    this.flyToOpen = false;
                                    break;
                            }
                        }
                    } else {
                        this.flyToOpen = Boolean.valueOf(i2 < 0);
                        this.flyToClose = Boolean.valueOf(i2 > 0);
                        if (i2 == 0) {
                            return;
                        }
                    }
                }
                if (this.mNestedFlyConsumed) {
                    return;
                }
                this.mHelper.nestedScrollingDrag(-i, -i2, iArr, true);
                if ((!this.flyToOpen.booleanValue() || swipeConsumer.getProgress() < overSwipeFactor) && (!this.flyToClose.booleanValue() || swipeConsumer.getProgress() > 0.0f)) {
                    return;
                }
                this.mNestedFlyConsumed = true;
                this.mHelper.nestedScrollingRelease();
                return;
            }
            this.mHelper.nestedScrollingDrag(-i, -i2, iArr, false);
            if (swipeConsumer.getProgress() >= overSwipeFactor || swipeConsumer.getProgress() <= 0.0f) {
                this.mHelper = null;
                return;
            }
            return;
        }
        for (SwipeHelper swipeHelper2 : this.mHelpers) {
            if (swipeHelper2 != null) {
                if (swipeHelper2.nestedScrollingTrySwipe(-i, -i2, i3 == 1)) {
                    this.mHelper = swipeHelper2;
                    return;
                }
            }
        }
    }

    protected void helperOnNestedScrollAccepted(View view, View view2, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onNestedScrollAccepted(view, view2, i);
        }
    }

    protected void helperOnNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onNestedPreScroll(view, i, i2, iArr);
        }
    }

    protected void helperOnNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 21) {
            dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        }
    }

    protected void helperOnStopNestedScroll(View view, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onStopNestedScroll(view);
        }
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return startNestedScroll(i, 0);
    }

    public boolean startNestedScroll(int i, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.startNestedScroll(i);
        }
        return false;
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.stopNestedScroll();
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.hasNestedScrollingParent();
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return dispatchNestedScroll(i, i2, i3, i4, iArr, 0);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.dispatchNestedScroll(i, i2, i3, i4, iArr);
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.dispatchNestedFling(f, f2, z);
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.dispatchNestedPreFling(f, f2);
        }
        return false;
    }
}
