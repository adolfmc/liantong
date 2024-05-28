package com.sinovatech.unicom.basic.view.decklayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.view.NestedScrollingChild2;
import android.support.p083v4.view.NestedScrollingChildHelper;
import android.support.p083v4.view.NestedScrollingParent2;
import android.support.p083v4.view.NestedScrollingParentHelper;
import android.support.p083v4.view.ScrollingView;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.widget.EdgeEffectCompat;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.C9718R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ConsecutiveScrollerLayout extends ViewGroup implements NestedScrollingChild2, NestedScrollingParent2, ScrollingView {
    private static final int MAX_CYCLE_COUNT = 1000;
    private static final int SCROLL_HORIZONTAL = 2;
    private static final int SCROLL_NONE = 0;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int SCROLL_VERTICAL = 1;
    static final Interpolator sQuinticInterpolator = new Interpolator() { // from class: com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private int SCROLL_ORIENTATION;
    private String TAG;
    protected Runnable animationRunnable;
    private boolean disableChildHorizontalScroll;
    private boolean isBrake;
    private boolean isDisallowInterceptTouchEvent;
    private boolean isIntercept;
    private boolean isPermanent;
    private boolean isTouchNotTriggerScrollStick;
    private int mActivePointerId;
    private int mAdjust;
    private int mAdjustHeightOffset;
    private VelocityTracker mAdjustVelocityTracker;
    private int mAdjustYVelocity;
    private boolean mAutoAdjustHeightAtBottomView;
    private NestedScrollingChildHelper mChildHelper;
    private View mCurrentStickyView;
    private final List<View> mCurrentStickyViews;
    protected int mCurrentVelocity;
    private int mCycleCount;
    private final int[] mDownLocation;
    protected float mDragRate;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private int mEventX;
    private int mEventY;
    private HashMap<Integer, Float> mFixedYMap;
    protected Handler mHandler;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private int mOldScrollY;
    private OnPermanentStickyChangeListener mOnPermanentStickyChangeListener;
    protected OnScrollChangeListener mOnScrollChangeListener;
    private OnStickyChangeListener mOnStickyChangeListener;
    private NestedScrollingParentHelper mParentHelper;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected int mScreenHeightPixels;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    int mScrollRange;
    private int mScrollState;
    private int mScrollToIndex;
    private int mScrollToIndexWithOffset;
    private View mScrollToTopView;
    private OverScroller mScroller;
    private int mSecondScrollY;
    private int mSmoothScrollOffset;
    private int mStickyOffset;
    private final List<View> mTempStickyViews;
    private int mTouchSlop;
    private int mTouchY;
    private boolean mTouching;
    private VelocityTracker mVelocityTracker;
    private final List<View> mViews;
    private int overDragMaxDistanceOfBottom;
    private int overDragMaxDistanceOfTop;
    private boolean overDragMode;
    protected ValueAnimator reboundAnimator;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnPermanentStickyChangeListener {
        void onStickyChange(@NonNull List<View> list);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnScrollChangeListener {
        void onScrollChange(View view, int i, int i2, int i3);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnStickyChangeListener {
        void onStickyChange(@Nullable View view, @Nullable View view2);
    }

    void scrollChildContentToTop(View view) {
    }

    @Override // android.view.View
    @Deprecated
    public void setOnScrollChangeListener(View.OnScrollChangeListener onScrollChangeListener) {
    }

    public ConsecutiveScrollerLayout(Context context) {
        this(context, null);
    }

    public ConsecutiveScrollerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ConsecutiveScrollerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDragRate = 0.5f;
        this.mReboundDuration = 300;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mFixedYMap = new HashMap<>();
        this.mDownLocation = new int[2];
        this.mTouching = false;
        this.SCROLL_ORIENTATION = 0;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mScrollToIndex = -1;
        this.mSmoothScrollOffset = 0;
        this.mScrollToIndexWithOffset = 0;
        this.mCycleCount = 0;
        this.mAdjustHeightOffset = 0;
        this.mStickyOffset = 0;
        this.mCurrentStickyViews = new ArrayList();
        this.mTempStickyViews = new ArrayList();
        this.mOldScrollY = 0;
        this.mViews = new ArrayList();
        this.mNestedYOffset = 0;
        this.mScrollState = 0;
        this.isTouchNotTriggerScrollStick = false;
        this.isIntercept = false;
        this.isBrake = false;
        this.isDisallowInterceptTouchEvent = false;
        this.TAG = getClass().getName();
        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attributeSet, C9718R.styleable.ConsecutiveScrollerLayout);
            if (typedArray.hasValue(6)) {
                this.overDragMode = typedArray.getBoolean(6, false);
                if (this.overDragMode) {
                    int dip2px = UIUtils.dip2px(180.0f);
                    this.overDragMaxDistanceOfTop = typedArray.getDimensionPixelOffset(5, dip2px);
                    this.overDragMaxDistanceOfBottom = typedArray.getDimensionPixelOffset(4, dip2px);
                }
            }
            this.isPermanent = typedArray.getBoolean(3, false);
            this.disableChildHorizontalScroll = typedArray.getBoolean(2, false);
            this.mStickyOffset = typedArray.getDimensionPixelOffset(8, 0);
            this.mAutoAdjustHeightAtBottomView = typedArray.getBoolean(1, false);
            this.mAdjustHeightOffset = typedArray.getDimensionPixelOffset(0, 0);
            this.mScroller = new OverScroller(getContext(), sQuinticInterpolator);
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mTouchSlop = ViewConfiguration.getTouchSlop();
            setWillNotDraw(false);
            setVerticalScrollBarEnabled(true);
            this.mParentHelper = new NestedScrollingParentHelper(this);
            this.mChildHelper = new NestedScrollingChildHelper(this);
            setNestedScrollingEnabled(true);
            setChildrenDrawingOrderEnabled(true);
            setMotionEventSplittingEnabled(false);
            this.mReboundInterpolator = new OverScrollInterpolator(OverScrollInterpolator.INTERPOLATOR_VISCOUS_FLUID);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        List<View> scrolledViews;
        if (layoutParams instanceof LayoutParams) {
            LayoutParamsUtils.invalidTopAndBottomMargin((LayoutParams) layoutParams);
        }
        super.addView(view, i, layoutParams);
        if (ScrollUtils.isConsecutiveScrollerChild(view)) {
            View scrollChild = ScrollUtils.getScrollChild(view);
            disableChildScroll(scrollChild);
            if ((scrollChild instanceof IConsecutiveScroller) && (scrolledViews = ((IConsecutiveScroller) scrollChild).getScrolledViews()) != null && !scrolledViews.isEmpty()) {
                int size = scrolledViews.size();
                for (int i2 = 0; i2 < size; i2++) {
                    disableChildScroll(scrolledViews.get(i2));
                }
            }
        }
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).setClipToPadding(false);
        }
    }

    private void disableChildScroll(View view) {
        view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);
        view.setOverScrollMode(2);
        ViewCompat.setNestedScrollingEnabled(view, false);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        resetScrollToTopView();
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            View view = nonGoneChildren.get(i5);
            measureChildWithMargins(view, i, 0, i2, getAdjustHeightForChild(view));
            i3 = Math.max(i3, getContentWidth(view));
            i4 += view.getMeasuredHeight();
        }
        setMeasuredDimension(measureSize(i, i3 + getPaddingLeft() + getPaddingRight()), measureSize(i2, i4 + getPaddingTop() + getPaddingBottom()));
    }

    private int getAdjustHeightForChild(View view) {
        if (this.mAutoAdjustHeightAtBottomView && view == getChildAt(getChildCount() - 1)) {
            return getAdjustHeight();
        }
        return 0;
    }

    private int getAdjustHeight() {
        List<View> stickyChildren = getStickyChildren();
        int i = this.mAdjustHeightOffset;
        int size = stickyChildren.size();
        if (this.isPermanent) {
            for (int i2 = 0; i2 < size; i2++) {
                View view = stickyChildren.get(i2);
                if (!isSink(view)) {
                    i += view.getMeasuredHeight();
                }
            }
            return i;
        }
        for (int i3 = size - 1; i3 >= 0; i3--) {
            View view2 = stickyChildren.get(i3);
            if (!isSink(view2)) {
                return i + view2.getMeasuredHeight();
            }
        }
        return i;
    }

    private int getContentWidth(View view) {
        int measuredWidth = view.getMeasuredWidth();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    private int measureSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            i2 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i2 = Math.min(i2, size);
        }
        return resolveSizeAndState(Math.max(i2, getSuggestedMinimumWidth()), i, 0);
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        try {
            LayoutParamsUtils.invalidTopAndBottomMargin((LayoutParams) view.getLayoutParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mScreenHeightPixels = getResources().getDisplayMetrics().heightPixels;
        this.mScrollRange = 0;
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int measuredWidth = getMeasuredWidth();
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        int i5 = paddingTop;
        int i6 = 0;
        while (i6 < size) {
            View view = nonGoneChildren.get(i6);
            int measuredHeight = view.getMeasuredHeight() + i5;
            int childLeft = getChildLeft(view, measuredWidth, paddingLeft, paddingRight);
            view.layout(childLeft, i5, view.getMeasuredWidth() + childLeft, measuredHeight);
            this.mScrollRange += view.getHeight();
            i6++;
            i5 = measuredHeight;
        }
        this.mScrollRange -= (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        if (this.mScrollRange < 0) {
            this.mScrollRange = 0;
        }
        checkLayoutChange(z, false);
        sortViews();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
    }

    private void sortViews() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (!isStickyView(childAt) || isSink(childAt)) {
                arrayList.add(childAt);
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt2 = getChildAt(i2);
            if (isStickyView(childAt2) && !isSink(childAt2)) {
                arrayList.add(childAt2);
            }
        }
        this.mViews.clear();
        this.mViews.addAll(arrayList);
    }

    private int getChildLeft(View view, int i, int i2, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        switch (layoutParams.align) {
            case RIGHT:
                return ((i - view.getMeasuredWidth()) - i3) - layoutParams.rightMargin;
            case CENTER:
                return layoutParams.leftMargin + i2 + ((((((i - view.getMeasuredWidth()) - i2) - layoutParams.leftMargin) - i3) - layoutParams.rightMargin) / 2);
            default:
                return i2 + layoutParams.leftMargin;
        }
    }

    private void resetScrollToTopView() {
        this.mScrollToTopView = findFirstVisibleView();
        if (this.mScrollToTopView != null) {
            this.mAdjust = getScrollY() - this.mScrollToTopView.getTop();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.isDisallowInterceptTouchEvent = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        int i;
        int actionIndex = motionEvent.getActionIndex();
        if (this.SCROLL_ORIENTATION == 2 && (i = this.mActivePointerId) != -1 && this.mFixedYMap.get(Integer.valueOf(i)) != null) {
            int findPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
            if (findPointerIndex2 < 0 || findPointerIndex2 >= motionEvent.getPointerCount()) {
                return false;
            }
            motionEvent.offsetLocation(0.0f, this.mFixedYMap.get(Integer.valueOf(this.mActivePointerId)).floatValue() - motionEvent.getY(findPointerIndex2));
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        if (obtain.getActionMasked() == 0) {
            this.mNestedYOffset = 0;
        }
        obtain.offsetLocation(0.0f, this.mNestedYOffset);
        interceptAnimatorByAction(obtain.getAction());
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.mCurrentVelocity = 0;
                this.isBrake = this.mScrollState == 2;
                stopScroll();
                this.mTouching = true;
                checkTargetsScroll(false, false);
                this.SCROLL_ORIENTATION = 0;
                this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                this.mFixedYMap.put(Integer.valueOf(this.mActivePointerId), Float.valueOf(motionEvent.getY(actionIndex)));
                this.mEventY = (int) motionEvent.getY(actionIndex);
                this.mEventX = (int) motionEvent.getX(actionIndex);
                initOrResetAdjustVelocityTracker();
                this.mAdjustVelocityTracker.addMovement(obtain);
                startNestedScroll(2, 0);
                this.mDownLocation[0] = ScrollUtils.getRawX(this, motionEvent, actionIndex);
                this.mDownLocation[1] = ScrollUtils.getRawY(this, motionEvent, actionIndex);
                int[] iArr = this.mDownLocation;
                this.isIntercept = isIntercept(iArr[0], iArr[1]);
                int[] iArr2 = this.mDownLocation;
                this.isTouchNotTriggerScrollStick = ScrollUtils.isTouchNotTriggerScrollStick(this, iArr2[0], iArr2[1]);
                break;
            case 1:
            case 3:
                VelocityTracker velocityTracker = this.mAdjustVelocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.addMovement(obtain);
                    this.mAdjustVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                    int yVelocity = (int) this.mAdjustVelocityTracker.getYVelocity();
                    this.mCurrentVelocity = yVelocity;
                    int i2 = this.mMaximumVelocity;
                    this.mAdjustYVelocity = Math.max(-i2, Math.min(yVelocity, i2));
                    recycleAdjustVelocityTracker();
                    int rawX = ScrollUtils.getRawX(this, motionEvent, actionIndex);
                    int rawY = ScrollUtils.getRawY(this, motionEvent, actionIndex);
                    boolean canScrollVertically = ScrollUtils.canScrollVertically(getTouchTarget(rawX, rawY));
                    boolean isHorizontalScroll = ScrollUtils.isHorizontalScroll(this, rawX, rawY);
                    if (this.SCROLL_ORIENTATION != 1 && canScrollVertically && Math.abs(yVelocity) >= this.mMinimumVelocity && !isHorizontalScroll) {
                        motionEvent.setAction(3);
                    }
                    if (this.SCROLL_ORIENTATION != 1 && !ScrollUtils.isConsecutiveScrollParent(this) && isIntercept(motionEvent) && Math.abs(yVelocity) >= this.mMinimumVelocity && (this.SCROLL_ORIENTATION == 0 || !isHorizontalScroll)) {
                        fling(-this.mAdjustYVelocity);
                    }
                }
                this.mEventY = 0;
                this.mEventX = 0;
                this.mTouching = false;
                int[] iArr3 = this.mDownLocation;
                iArr3[0] = 0;
                iArr3[1] = 0;
                this.isTouchNotTriggerScrollStick = false;
                this.isIntercept = false;
                overSpinner();
                break;
            case 2:
                int findPointerIndex3 = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex3 >= 0 && findPointerIndex3 < motionEvent.getPointerCount()) {
                    initAdjustVelocityTrackerIfNotExists();
                    this.mAdjustVelocityTracker.addMovement(obtain);
                    int y = ((int) motionEvent.getY(findPointerIndex3)) - this.mEventY;
                    int x = ((int) motionEvent.getX(findPointerIndex3)) - this.mEventX;
                    if (this.SCROLL_ORIENTATION == 0 && (this.isIntercept || isIntercept(motionEvent))) {
                        if (this.disableChildHorizontalScroll) {
                            if (Math.abs(y) >= this.mTouchSlop) {
                                this.SCROLL_ORIENTATION = 1;
                            }
                        } else if (Math.abs(x) > Math.abs(y)) {
                            if (Math.abs(x) >= this.mTouchSlop) {
                                this.SCROLL_ORIENTATION = 2;
                                int i3 = this.mActivePointerId;
                                if (i3 != -1 && this.mFixedYMap.get(Integer.valueOf(i3)) != null && (findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId)) >= 0 && findPointerIndex3 < motionEvent.getPointerCount()) {
                                    motionEvent.offsetLocation(0.0f, this.mFixedYMap.get(Integer.valueOf(this.mActivePointerId)).floatValue() - motionEvent.getY(findPointerIndex));
                                }
                            }
                        } else if (Math.abs(y) >= this.mTouchSlop) {
                            this.SCROLL_ORIENTATION = 1;
                        }
                        if (this.SCROLL_ORIENTATION == 0) {
                            return true;
                        }
                    }
                    this.mEventY = (int) motionEvent.getY(findPointerIndex3);
                    this.mEventX = (int) motionEvent.getX(findPointerIndex3);
                    break;
                } else {
                    return false;
                }
                break;
            case 5:
                this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                this.mFixedYMap.put(Integer.valueOf(this.mActivePointerId), Float.valueOf(motionEvent.getY(actionIndex)));
                this.mEventY = (int) motionEvent.getY(actionIndex);
                this.mEventX = (int) motionEvent.getX(actionIndex);
                if (!this.isDisallowInterceptTouchEvent) {
                    requestDisallowInterceptTouchEvent(false);
                }
                this.mDownLocation[0] = ScrollUtils.getRawX(this, motionEvent, actionIndex);
                this.mDownLocation[1] = ScrollUtils.getRawY(this, motionEvent, actionIndex);
                int[] iArr4 = this.mDownLocation;
                this.isIntercept = isIntercept(iArr4[0], iArr4[1]);
                int[] iArr5 = this.mDownLocation;
                this.isTouchNotTriggerScrollStick = ScrollUtils.isTouchNotTriggerScrollStick(this, iArr5[0], iArr5[1]);
                initAdjustVelocityTrackerIfNotExists();
                this.mAdjustVelocityTracker.addMovement(obtain);
                break;
            case 6:
                this.mFixedYMap.remove(Integer.valueOf(motionEvent.getPointerId(actionIndex)));
                if (this.mActivePointerId == motionEvent.getPointerId(actionIndex)) {
                    int i4 = actionIndex == 0 ? 1 : 0;
                    this.mActivePointerId = motionEvent.getPointerId(i4);
                    this.mFixedYMap.put(Integer.valueOf(this.mActivePointerId), Float.valueOf(motionEvent.getY(i4)));
                    this.mEventY = (int) motionEvent.getY(i4);
                    this.mEventX = (int) motionEvent.getX(i4);
                    this.mDownLocation[0] = ScrollUtils.getRawX(this, motionEvent, i4);
                    this.mDownLocation[1] = ScrollUtils.getRawY(this, motionEvent, i4);
                    int[] iArr6 = this.mDownLocation;
                    this.isIntercept = isIntercept(iArr6[0], iArr6[1]);
                    int[] iArr7 = this.mDownLocation;
                    this.isTouchNotTriggerScrollStick = ScrollUtils.isTouchNotTriggerScrollStick(this, iArr7[0], iArr7[1]);
                }
                initAdjustVelocityTrackerIfNotExists();
                this.mAdjustVelocityTracker.addMovement(obtain);
                break;
        }
        obtain.recycle();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            this.SCROLL_ORIENTATION = 0;
            this.mAdjustYVelocity = 0;
            this.mFixedYMap.clear();
            this.mActivePointerId = -1;
            if (this.mScroller.isFinished()) {
                setScrollState(0);
            }
        }
        return dispatchTouchEvent;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(motionEvent);
                break;
            case 1:
            case 3:
                stopNestedScroll(0);
                if (this.isBrake && this.SCROLL_ORIENTATION == 0) {
                    return true;
                }
                break;
            case 2:
                if (this.SCROLL_ORIENTATION != 2 && (this.isIntercept || isIntercept(motionEvent))) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        if (ScrollUtils.isConsecutiveScrollParent(this) || this.isTouchNotTriggerScrollStick) {
            return super.onTouchEvent(motionEvent);
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        boolean z = false;
        if (motionEvent.getActionMasked() == 0) {
            this.mNestedYOffset = 0;
        }
        obtain.offsetLocation(0.0f, this.mNestedYOffset);
        int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
        if (findPointerIndex < 0 || findPointerIndex >= motionEvent.getPointerCount()) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                startNestedScroll(2, 0);
                this.mTouchY = (int) motionEvent.getY(findPointerIndex);
                break;
            case 1:
                endDrag();
                this.mTouchY = 0;
                VelocityTracker velocityTracker = this.mVelocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.addMovement(obtain);
                    this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                    int i2 = this.mMaximumVelocity;
                    int max = Math.max(-i2, Math.min((int) this.mVelocityTracker.getYVelocity(), i2));
                    if (max == 0 && (i = this.mAdjustYVelocity) != 0) {
                        max = i;
                    }
                    fling(-max);
                    recycleVelocityTracker();
                    break;
                }
                break;
            case 2:
                if (this.mTouchY == 0) {
                    this.mTouchY = (int) motionEvent.getY(findPointerIndex);
                    return true;
                }
                this.mScrollConsumed[1] = 0;
                int y = (int) motionEvent.getY(findPointerIndex);
                int i3 = this.mTouchY - y;
                this.mTouchY = y;
                if (dispatchNestedPreScroll(0, i3, this.mScrollConsumed, this.mScrollOffset, 0)) {
                    i3 -= this.mScrollConsumed[1];
                    motionEvent.offsetLocation(0.0f, this.mScrollOffset[1]);
                    int i4 = this.mNestedYOffset;
                    int[] iArr = this.mScrollOffset;
                    this.mNestedYOffset = i4 + iArr[1];
                    this.mTouchY -= iArr[1];
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                int i5 = this.mSecondScrollY;
                if (this.mScrollState != 1) {
                    if (canScrollVertically() && Math.abs(i3) > 0) {
                        setScrollState(1);
                    }
                }
                if (this.mScrollState == 1) {
                    dispatchScroll(i3);
                }
                int i6 = this.mSecondScrollY - i5;
                if (i6 != 0) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                int i7 = i3 - i6;
                if (dispatchNestedScroll(0, i6, 0, i7, this.mScrollOffset, 0)) {
                    int[] iArr2 = this.mScrollOffset;
                    i7 += iArr2[1];
                    this.mTouchY -= iArr2[1];
                    this.mNestedYOffset += iArr2[1];
                    motionEvent.offsetLocation(0.0f, iArr2[1]);
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                int scrollRange = getScrollRange();
                int overScrollMode = getOverScrollMode();
                if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                    z = true;
                }
                if (z) {
                    ensureGlows();
                    int i8 = i5 + i7;
                    if (i8 < 0 && this.overDragMaxDistanceOfTop <= 0) {
                        EdgeEffectCompat.onPull(this.mEdgeGlowTop, i7 / getHeight(), motionEvent.getX(findPointerIndex) / getWidth());
                        if (!this.mEdgeGlowBottom.isFinished()) {
                            this.mEdgeGlowBottom.onRelease();
                        }
                    } else if (i8 > scrollRange && this.overDragMaxDistanceOfBottom <= 0) {
                        EdgeEffectCompat.onPull(this.mEdgeGlowBottom, i7 / getHeight(), 1.0f - (motionEvent.getX(findPointerIndex) / getWidth()));
                        if (!this.mEdgeGlowTop.isFinished()) {
                            this.mEdgeGlowTop.onRelease();
                        }
                    }
                    EdgeEffect edgeEffect = this.mEdgeGlowTop;
                    if (edgeEffect != null && (!edgeEffect.isFinished() || !this.mEdgeGlowBottom.isFinished())) {
                        ViewCompat.postInvalidateOnAnimation(this);
                        break;
                    }
                }
                break;
            case 3:
                endDrag();
                this.mTouchY = 0;
                recycleVelocityTracker();
                setScrollState(0);
                break;
            case 5:
            case 6:
                this.mTouchY = (int) motionEvent.getY(findPointerIndex);
                break;
        }
        VelocityTracker velocityTracker2 = this.mVelocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    private boolean canScrollVertically() {
        return (isScrollTop() && isScrollBottom() && !this.overDragMode) ? false : true;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        int indexOfChild;
        return (this.mViews.size() <= i2 || (indexOfChild = indexOfChild(this.mViews.get(i2))) == -1) ? super.getChildDrawingOrder(i, i2) : indexOfChild;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDrawingPosition(View view) {
        return this.mViews.indexOf(view);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        int i;
        super.draw(canvas);
        if (this.mOldScrollY != getScrollY()) {
            this.mOldScrollY = getScrollY();
            resetSticky();
        }
        if (this.mEdgeGlowTop != null) {
            int scrollY = getScrollY();
            int i2 = 0;
            if (!this.mEdgeGlowTop.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (Build.VERSION.SDK_INT < 21 || !getClipToPadding()) {
                    i = scrollY;
                } else {
                    height -= getPaddingTop() + getPaddingBottom();
                    i = getPaddingTop() + scrollY;
                }
                canvas.translate(paddingLeft, i);
                this.mEdgeGlowTop.setSize(width, height);
                if (this.mEdgeGlowTop.draw(canvas)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount(save);
            }
            if (this.mEdgeGlowBottom.isFinished()) {
                return;
            }
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int i3 = scrollY + height2;
            if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                i2 = 0 + getPaddingLeft();
            }
            if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                i3 -= getPaddingBottom();
            }
            canvas.translate(i2 - width2, i3);
            canvas.rotate(180.0f, width2, 0.0f);
            this.mEdgeGlowBottom.setSize(width2, height2);
            if (this.mEdgeGlowBottom.draw(canvas)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            canvas.restoreToCount(save2);
        }
    }

    private int getScrollRange() {
        if (getChildCount() > 0) {
            return Math.max(0, computeVerticalScrollRange() - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
        }
        return 0;
    }

    private void fling(int i) {
        if (Math.abs(i) > this.mMinimumVelocity) {
            float f = i;
            if (dispatchNestedPreFling(0.0f, f)) {
                return;
            }
            dispatchNestedFling(0.0f, f, (i < 0 && !isScrollTop()) || (i > 0 && !isScrollBottom()));
            this.mScroller.fling(0, this.mSecondScrollY, 1, i, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            startNestedScroll(2, 1);
            setScrollState(2);
            this.mLastScrollerY = this.mSecondScrollY;
            invalidate();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        int i;
        if (this.mScrollToIndex != -1 && (i = this.mSmoothScrollOffset) != 0) {
            if (i > 0 && i < 200) {
                this.mSmoothScrollOffset = i + 5;
            }
            int i2 = this.mSmoothScrollOffset;
            if (i2 < 0 && i2 > -200) {
                this.mSmoothScrollOffset = i2 - 5;
            }
            dispatchScroll(this.mSmoothScrollOffset);
            this.mCycleCount++;
            invalidate();
            return;
        }
        if (this.mScroller.computeScrollOffset()) {
            int currY = this.mScroller.getCurrY();
            int i3 = currY - this.mLastScrollerY;
            this.mLastScrollerY = currY;
            int[] iArr = this.mScrollConsumed;
            iArr[1] = 0;
            dispatchNestedPreScroll(0, i3, iArr, null, 1);
            int i4 = i3 - this.mScrollConsumed[1];
            int i5 = this.mSecondScrollY;
            dispatchScroll(i4);
            int i6 = this.mSecondScrollY - i5;
            int i7 = i4 - i6;
            if ((i7 < 0 && isScrollTop()) || (i7 > 0 && isScrollBottom())) {
                dispatchNestedScroll(0, i6, 0, i7, this.mScrollOffset, 1);
                i7 += this.mScrollOffset[1];
            }
            if ((i7 < 0 && isScrollTop()) || (i7 > 0 && isScrollBottom())) {
                if (this.overDragMode) {
                    animSpinnerBounce(this.mScroller.getFinalY() > 0 ? this.mScroller.getCurrVelocity() : -this.mScroller.getCurrVelocity());
                    this.mScroller.forceFinished(true);
                } else {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) {
                        ensureGlows();
                        if (i7 < 0) {
                            if (this.mEdgeGlowTop.isFinished()) {
                                this.mEdgeGlowTop.onAbsorb((int) this.mScroller.getCurrVelocity());
                            }
                        } else if (this.mEdgeGlowBottom.isFinished()) {
                            this.mEdgeGlowBottom.onAbsorb((int) this.mScroller.getCurrVelocity());
                        }
                    }
                    stopScroll();
                }
            }
            invalidate();
        }
        if (this.mScrollState == 2 && this.mScroller.isFinished()) {
            stopNestedScroll(1);
            setScrollState(0);
        }
    }

    protected boolean interceptAnimatorByAction(int i) {
        if (i == 0) {
            ValueAnimator valueAnimator = this.reboundAnimator;
            if (valueAnimator != null) {
                valueAnimator.setDuration(0L);
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        return this.reboundAnimator != null;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BounceRunnable implements Runnable {
        int mSmoothDistance;
        float mVelocity;
        int mFrame = 0;
        int mFrameDelay = 10;
        float mOffset = 0.0f;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();

        BounceRunnable(float f, int i) {
            this.mVelocity = f;
            this.mSmoothDistance = i;
            ConsecutiveScrollerLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            if (ConsecutiveScrollerLayout.this.animationRunnable == this) {
                this.mFrame = this.mFrame + 1;
                this.mVelocity = (float) (this.mVelocity * Math.pow(0.8500000238418579d, i * 2));
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
                if (Math.abs(f) >= 1.0f) {
                    this.mLastTime = currentAnimationTimeMillis;
                    this.mOffset += f;
                    int scrollY = ConsecutiveScrollerLayout.this.getScrollY();
                    ConsecutiveScrollerLayout.this.moveSpinnerInfinitely(this.mOffset);
                    if (scrollY != ConsecutiveScrollerLayout.this.mSecondScrollY) {
                        ConsecutiveScrollerLayout consecutiveScrollerLayout = ConsecutiveScrollerLayout.this;
                        consecutiveScrollerLayout.scrollChange(consecutiveScrollerLayout.mSecondScrollY, scrollY);
                    }
                    ConsecutiveScrollerLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
                    return;
                }
                ConsecutiveScrollerLayout consecutiveScrollerLayout2 = ConsecutiveScrollerLayout.this;
                consecutiveScrollerLayout2.animationRunnable = null;
                int scrollY2 = consecutiveScrollerLayout2.getScrollY();
                ConsecutiveScrollerLayout consecutiveScrollerLayout3 = ConsecutiveScrollerLayout.this;
                consecutiveScrollerLayout3.animSpinner(scrollY2, this.mSmoothDistance, 0, consecutiveScrollerLayout3.mReboundInterpolator, Math.min(Math.max(UIUtils.px2dp(Math.abs(scrollY2 - this.mSmoothDistance)), 30), 100) * 10);
            }
        }
    }

    protected void moveSpinnerInfinitely(float f) {
        double d;
        double max = Math.max(this.mScreenHeightPixels / 2, getHeight());
        if (f > 0.0f) {
            double max2 = Math.max(0.0f, this.mDragRate * f);
            double d2 = -max2;
            if (max == 0.0d) {
                max = 1.0d;
            }
            d = Math.min(r10 * (1.0d - Math.pow(100.0d, d2 / max)), max2);
        } else {
            double d3 = -Math.min(0.0f, this.mDragRate * f);
            double d4 = -d3;
            if (max == 0.0d) {
                max = 1.0d;
            }
            d = -Math.min(r10 * (1.0d - Math.pow(100.0d, d4 / max)), d3);
        }
        int i = (int) d;
        if (Math.abs(f) >= 1.0f && i == 0) {
            i = (int) f;
        }
        this.mSecondScrollY += i;
        scrollSelf(getScrollY() + i);
    }

    protected ValueAnimator animSpinner(int i, int i2, int i3, Interpolator interpolator, int i4) {
        if (i != i2) {
            ValueAnimator valueAnimator = this.reboundAnimator;
            if (valueAnimator != null) {
                valueAnimator.setDuration(0L);
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
            this.reboundAnimator = ValueAnimator.ofInt(i, i2);
            this.reboundAnimator.setDuration(i4);
            this.reboundAnimator.setInterpolator(interpolator);
            this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (animator == null || animator.getDuration() != 0) {
                        ConsecutiveScrollerLayout consecutiveScrollerLayout = ConsecutiveScrollerLayout.this;
                        consecutiveScrollerLayout.reboundAnimator = null;
                        consecutiveScrollerLayout.checkTargetsScroll(false, false);
                    }
                }
            });
            this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.basic.view.decklayout.-$$Lambda$ConsecutiveScrollerLayout$gyluhlH_GBNhIbDNkTSdRRXMmqY
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    ConsecutiveScrollerLayout.lambda$animSpinner$0(ConsecutiveScrollerLayout.this, valueAnimator2);
                }
            });
            this.reboundAnimator.setStartDelay(i3);
            this.reboundAnimator.start();
            return this.reboundAnimator;
        }
        return null;
    }

    public static /* synthetic */ void lambda$animSpinner$0(ConsecutiveScrollerLayout consecutiveScrollerLayout, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        int computeVerticalScrollOffset = consecutiveScrollerLayout.computeVerticalScrollOffset();
        consecutiveScrollerLayout.scrollSelf(intValue);
        consecutiveScrollerLayout.mSecondScrollY = consecutiveScrollerLayout.computeVerticalScrollOffset();
        int i = consecutiveScrollerLayout.mSecondScrollY;
        if (computeVerticalScrollOffset != i) {
            consecutiveScrollerLayout.scrollChange(i, computeVerticalScrollOffset);
        }
    }

    protected void animSpinnerBounce(float f) {
        if (this.reboundAnimator == null) {
            if (f < 0.0f && this.overDragMaxDistanceOfTop > 0) {
                this.animationRunnable = new BounceRunnable(f, 0);
            } else if (f <= 0.0f || this.overDragMaxDistanceOfBottom <= 0) {
            } else {
                this.animationRunnable = new BounceRunnable(f, this.mScrollRange);
            }
        }
    }

    protected void overSpinner() {
        int scrollY = getScrollY();
        if (scrollY < 0) {
            if (this.reboundAnimator == null) {
                animSpinner(scrollY, 0, 0, this.mReboundInterpolator, this.mReboundDuration);
                return;
            }
            return;
        }
        int i = this.mScrollRange;
        if (scrollY <= i || this.reboundAnimator != null) {
            return;
        }
        animSpinner(scrollY, i, 0, this.mReboundInterpolator, this.mReboundDuration);
    }

    private void endDrag() {
        EdgeEffect edgeEffect = this.mEdgeGlowTop;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
    }

    private void ensureGlows() {
        if (getOverScrollMode() != 2) {
            if (this.mEdgeGlowTop == null) {
                Context context = getContext();
                this.mEdgeGlowTop = new EdgeEffect(context);
                this.mEdgeGlowBottom = new EdgeEffect(context);
                return;
            }
            return;
        }
        this.mEdgeGlowTop = null;
        this.mEdgeGlowBottom = null;
    }

    private void dispatchScroll(int i) {
        if (i > 0) {
            scrollUp(i);
        } else if (i < 0) {
            scrollDown(i);
        }
    }

    private void scrollUp(int i) {
        int i2;
        int i3;
        View bottomView;
        int min;
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        do {
            int i4 = this.mScrollToIndex;
            int i5 = 0;
            if (i4 != -1) {
                View childAt = getChildAt(i4);
                i2 = (childAt.getTop() - this.mScrollToIndexWithOffset) - getAdjustHeightForChild(childAt);
                i3 = this.mScrollToIndexWithOffset < 0 ? getViewsScrollOffset(this.mScrollToIndex) : 0;
                if (this.mCycleCount >= 1000 || getScrollY() + getPaddingTop() + i3 >= i2 || isScrollBottom()) {
                    this.mScrollToIndex = -1;
                    this.mSmoothScrollOffset = 0;
                    this.mScrollToIndexWithOffset = 0;
                    this.mCycleCount = 0;
                    setScrollState(0);
                    break;
                }
            } else {
                i2 = 0;
                i3 = 0;
            }
            int scrollY = getScrollY();
            if (!isScrollBottom() && scrollY >= 0) {
                if (getScrollY() < this.mScrollRange) {
                    bottomView = findFirstVisibleView();
                } else {
                    bottomView = getBottomView();
                }
                if (bottomView != null) {
                    awakenScrollBars();
                    int scrollBottomOffset = ScrollUtils.getScrollBottomOffset(bottomView);
                    if (scrollBottomOffset > 0) {
                        int min2 = Math.min(i, scrollBottomOffset);
                        if (this.mScrollToIndex != -1) {
                            min2 = Math.min(min2, i2 - ((getScrollY() + getPaddingTop()) + i3));
                        }
                        scrollChild(bottomView, min2);
                        min = min2;
                    } else {
                        int min3 = Math.min(i, (bottomView.getBottom() - getPaddingTop()) - getScrollY());
                        min = this.mScrollToIndex != -1 ? Math.min(min3, i2 - ((getScrollY() + getPaddingTop()) + i3)) : min3;
                        scrollSelf(scrollY + min);
                    }
                    this.mSecondScrollY += min;
                    i -= min;
                    i5 = min;
                }
            } else if (this.mTouching) {
                if (scrollY < 0 && i > Math.abs(scrollY)) {
                    i5 = i - Math.abs(scrollY);
                    i -= i5;
                    moveSpinnerInfinitely(i5);
                } else {
                    dispatchNestedScroll(0, 0, 0, i, this.mScrollOffset, 0);
                    if (this.mScrollOffset[1] == 0 && this.overDragMode && this.overDragMaxDistanceOfBottom >= 0) {
                        moveSpinnerInfinitely(i);
                    }
                    i = 0;
                }
            } else if (!this.mScroller.isFinished() && this.mScroller.getFinalY() > 0 && scrollY < 0) {
                if (this.reboundAnimator != null) {
                    interceptAnimatorByAction(0);
                }
                if (i > Math.abs(scrollY)) {
                    int abs = i - Math.abs(scrollY);
                    i -= abs;
                    i5 = abs;
                } else {
                    i5 = i;
                    i = 0;
                }
                this.mSecondScrollY += i5;
                scrollSelf(scrollY + i5);
            } else if (scrollY < 0) {
                this.mScroller.forceFinished(true);
            }
            if (i5 <= 0) {
                break;
            }
        } while (i > 0);
        int computeVerticalScrollOffset2 = computeVerticalScrollOffset();
        if (computeVerticalScrollOffset != computeVerticalScrollOffset2) {
            scrollChange(computeVerticalScrollOffset2, computeVerticalScrollOffset);
        }
    }

    private void scrollDown(int i) {
        int i2;
        int i3;
        int i4;
        View bottomView;
        int max;
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        do {
            int i5 = this.mScrollToIndex;
            int i6 = 0;
            if (i5 != -1) {
                View childAt = getChildAt(i5);
                i2 = (childAt.getTop() - this.mScrollToIndexWithOffset) - getAdjustHeightForChild(childAt);
                i3 = getViewsScrollOffset(this.mScrollToIndex);
                if (this.mCycleCount >= 1000 || getScrollY() + getPaddingTop() + i3 <= i2 || isScrollTop()) {
                    this.mScrollToIndex = -1;
                    this.mSmoothScrollOffset = 0;
                    this.mScrollToIndexWithOffset = 0;
                    this.mCycleCount = 0;
                    setScrollState(0);
                    break;
                }
            } else {
                i2 = 0;
                i3 = 0;
            }
            int scrollY = getScrollY();
            if (!isScrollTop() && scrollY <= (i4 = this.mScrollRange) && scrollY >= 0) {
                if (scrollY < i4) {
                    bottomView = findLastVisibleView();
                } else {
                    bottomView = getBottomView();
                }
                if (bottomView != null) {
                    awakenScrollBars();
                    int scrollTopOffset = ScrollUtils.getScrollTopOffset(bottomView);
                    if (scrollTopOffset < 0) {
                        int max2 = Math.max(i, scrollTopOffset);
                        if (this.mScrollToIndex != -1) {
                            max2 = Math.max(max2, i2 - ((getScrollY() + getPaddingTop()) + i3));
                        }
                        scrollChild(bottomView, max2);
                        max = max2;
                    } else {
                        int max3 = Math.max(Math.max(i, ((bottomView.getTop() + getPaddingBottom()) - scrollY) - getHeight()), -scrollY);
                        max = this.mScrollToIndex != -1 ? Math.max(max3, i2 - ((getScrollY() + getPaddingTop()) + i3)) : max3;
                        scrollSelf(scrollY + max);
                    }
                    this.mSecondScrollY += max;
                    i -= max;
                    i6 = max;
                }
            } else if (this.mTouching) {
                int i7 = this.mScrollRange;
                int i8 = scrollY - i7;
                if (scrollY > i7 && Math.abs(i) > i8) {
                    i6 = -i8;
                    i -= i6;
                    moveSpinnerInfinitely(i6);
                } else {
                    dispatchNestedScroll(0, 0, 0, i, this.mScrollOffset, 0);
                    int i9 = i + this.mScrollOffset[1];
                    if (i9 != 0) {
                        moveSpinnerInfinitely(i9);
                    }
                    i = 0;
                }
            } else {
                if (!this.mScroller.isFinished()) {
                    int finalY = this.mScroller.getFinalY();
                    int i10 = this.mScrollRange;
                    if (finalY < i10 && scrollY > i10) {
                        if (this.reboundAnimator != null) {
                            interceptAnimatorByAction(0);
                        }
                        int i11 = this.mScrollRange - scrollY;
                        if (i < i11) {
                            i -= i11;
                            i6 = i11;
                        } else {
                            i6 = i;
                            i = 0;
                        }
                        this.mSecondScrollY += i6;
                        scrollSelf(scrollY + i6);
                    }
                }
                if (scrollY > this.mScrollRange) {
                    this.mScroller.forceFinished(true);
                }
            }
            if (i6 >= 0) {
                break;
            }
        } while (i < 0);
        int computeVerticalScrollOffset2 = computeVerticalScrollOffset();
        if (computeVerticalScrollOffset != computeVerticalScrollOffset2) {
            scrollChange(computeVerticalScrollOffset2, computeVerticalScrollOffset);
        }
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        scrollTo(0, this.mSecondScrollY + i2);
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        dispatchScroll(i2 - this.mSecondScrollY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollChange(int i, int i2) {
        OnScrollChangeListener onScrollChangeListener = this.mOnScrollChangeListener;
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChange(this, i, i2, this.mScrollState);
        }
    }

    private void stickyChange(View view, View view2) {
        OnStickyChangeListener onStickyChangeListener = this.mOnStickyChangeListener;
        if (onStickyChangeListener != null) {
            onStickyChangeListener.onStickyChange(view, view2);
        }
    }

    private void permanentStickyChange(List<View> list) {
        OnPermanentStickyChangeListener onPermanentStickyChangeListener = this.mOnPermanentStickyChangeListener;
        if (onPermanentStickyChangeListener != null) {
            onPermanentStickyChangeListener.onStickyChange(list);
        }
    }

    private void scrollSelf(int i) {
        if (i < 0 && Math.abs(i) > Math.abs(this.overDragMaxDistanceOfTop)) {
            int i2 = this.overDragMaxDistanceOfTop;
            i = i2 <= 0 ? 0 : -i2;
        } else {
            int i3 = this.mScrollRange;
            if (i > i3 && i > i3 + Math.abs(this.overDragMaxDistanceOfBottom)) {
                int i4 = this.overDragMaxDistanceOfBottom;
                i = i4 <= 0 ? this.mScrollRange : this.mScrollRange + i4;
            }
        }
        super.scrollTo(0, i);
    }

    private void scrollChild(View view, int i) {
        View scrolledView = ScrollUtils.getScrolledView(view);
        if (scrolledView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) scrolledView;
            if (Build.VERSION.SDK_INT >= 19) {
                absListView.scrollListBy(i);
                return;
            }
            return;
        }
        boolean startInterceptRequestLayout = scrolledView instanceof RecyclerView ? ScrollUtils.startInterceptRequestLayout((RecyclerView) scrolledView) : false;
        scrolledView.scrollBy(0, i);
        if (startInterceptRequestLayout) {
            final RecyclerView recyclerView = (RecyclerView) scrolledView;
            recyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout.3
                @Override // java.lang.Runnable
                public void run() {
                    ScrollUtils.stopInterceptRequestLayout(recyclerView);
                }
            }, 0L);
        }
    }

    public void checkLayoutChange() {
        postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout.4
            @Override // java.lang.Runnable
            public void run() {
                ConsecutiveScrollerLayout.this.checkLayoutChange(false, true);
            }
        }, 20L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLayoutChange(boolean z, boolean z2) {
        int i = this.mSecondScrollY;
        View view = this.mScrollToTopView;
        if (view != null && z) {
            if (indexOfChild(view) != -1) {
                scrollSelf(this.mScrollToTopView.getTop() + this.mAdjust);
            }
        } else {
            scrollSelf(getScrollY());
        }
        checkTargetsScroll(true, z2);
        if (i != this.mSecondScrollY && this.mScrollToTopView != findFirstVisibleView()) {
            scrollTo(0, i);
        }
        this.mScrollToTopView = null;
        this.mAdjust = 0;
        resetSticky();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTargetsScroll(boolean z, boolean z2) {
        int computeVerticalScrollOffset;
        if (z2 || (!this.mTouching && this.mScroller.isFinished() && this.mScrollToIndex == -1)) {
            int computeVerticalScrollOffset2 = computeVerticalScrollOffset();
            View findFirstVisibleView = findFirstVisibleView();
            if (findFirstVisibleView == null) {
                return;
            }
            int indexOfChild = indexOfChild(findFirstVisibleView);
            if (z) {
                while (true) {
                    int scrollBottomOffset = ScrollUtils.getScrollBottomOffset(findFirstVisibleView);
                    int top = findFirstVisibleView.getTop() - getScrollY();
                    if (scrollBottomOffset <= 0 || top >= 0) {
                        break;
                    }
                    int min = Math.min(scrollBottomOffset, -top);
                    scrollSelf(getScrollY() - min);
                    scrollChild(findFirstVisibleView, min);
                }
            }
            for (int i = 0; i < indexOfChild; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8 && ScrollUtils.isConsecutiveScrollerChild(childAt)) {
                    View scrollChild = ScrollUtils.getScrollChild(childAt);
                    if (scrollChild instanceof IConsecutiveScroller) {
                        List<View> scrolledViews = ((IConsecutiveScroller) scrollChild).getScrolledViews();
                        if (scrolledViews != null && !scrolledViews.isEmpty()) {
                            int size = scrolledViews.size();
                            for (int i2 = 0; i2 < size; i2++) {
                                scrollChildContentToBottom(scrolledViews.get(i2));
                            }
                        }
                    } else {
                        scrollChildContentToBottom(scrollChild);
                    }
                }
            }
            while (true) {
                indexOfChild++;
                if (indexOfChild >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(indexOfChild);
                if (childAt2.getVisibility() != 8 && ScrollUtils.isConsecutiveScrollerChild(childAt2) && (indexOfChild != getChildCount() - 1 || childAt2.getHeight() >= getHeight() || getScrollY() < this.mScrollRange)) {
                    View scrollChild2 = ScrollUtils.getScrollChild(childAt2);
                    if (scrollChild2 instanceof IConsecutiveScroller) {
                        List<View> scrolledViews2 = ((IConsecutiveScroller) scrollChild2).getScrolledViews();
                        if (scrolledViews2 != null && !scrolledViews2.isEmpty()) {
                            int size2 = scrolledViews2.size();
                            for (int i3 = 0; i3 < size2; i3++) {
                                scrollChildContentToTop(scrolledViews2.get(i3));
                            }
                        }
                    } else {
                        scrollChildContentToTop(scrollChild2);
                    }
                }
            }
            computeOwnScrollOffset();
            if (z && computeVerticalScrollOffset2 != (computeVerticalScrollOffset = computeVerticalScrollOffset())) {
                scrollChange(computeVerticalScrollOffset, computeVerticalScrollOffset2);
            }
            resetSticky();
        }
    }

    void scrollChildContentToBottom(View view) {
        int i;
        do {
            i = 0;
            int scrollBottomOffset = ScrollUtils.getScrollBottomOffset(view);
            if (scrollBottomOffset > 0) {
                int computeVerticalScrollOffset = ScrollUtils.computeVerticalScrollOffset(view);
                scrollChild(view, scrollBottomOffset);
                i = computeVerticalScrollOffset - ScrollUtils.computeVerticalScrollOffset(view);
                continue;
            }
        } while (i != 0);
    }

    private void computeOwnScrollOffset() {
        this.mSecondScrollY = computeVerticalScrollOffset();
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void initOrResetAdjustVelocityTracker() {
        VelocityTracker velocityTracker = this.mAdjustVelocityTracker;
        if (velocityTracker == null) {
            this.mAdjustVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initAdjustVelocityTrackerIfNotExists() {
        if (this.mAdjustVelocityTracker == null) {
            this.mAdjustVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleAdjustVelocityTracker() {
        VelocityTracker velocityTracker = this.mAdjustVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mAdjustVelocityTracker = null;
        }
    }

    public void stopScroll() {
        if (this.mScroller.isFinished()) {
            return;
        }
        this.mScroller.abortAnimation();
        stopNestedScroll(1);
        if (this.mScrollToIndex == -1) {
            setScrollState(0);
        }
    }

    private View getBottomView() {
        List<View> effectiveChildren = getEffectiveChildren();
        if (effectiveChildren.isEmpty()) {
            return null;
        }
        return effectiveChildren.get(effectiveChildren.size() - 1);
    }

    private List<View> getNonGoneChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    private List<View> getEffectiveChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && childAt.getHeight() > 0) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    private List<View> getStickyChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && isStickyView(childAt)) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    public boolean isStickyView(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            return ((LayoutParams) layoutParams).isSticky;
        }
        return false;
    }

    public boolean isSink(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            return ((LayoutParams) layoutParams).isSink;
        }
        return false;
    }

    private void resetTranslationYOffSticky(View view) {
        view.setTranslationY(0.0f);
    }

    private void resetTranslationYOffSticky(List<View> list, List<View> list2) {
        for (View view : list2) {
            if (!list.contains(view)) {
                resetTranslationYOffSticky(view);
            }
        }
    }

    private void resetSticky() {
        View view;
        View view2;
        List<View> stickyChildren = getStickyChildren();
        if (!stickyChildren.isEmpty()) {
            int size = stickyChildren.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                stickyChildren.get(i2).setTranslationY(0.0f);
            }
            if (this.isPermanent) {
                clearCurrentStickyView();
                permanentStickyChild(stickyChildren);
                return;
            }
            clearCurrentStickyViews();
            int i3 = size - 1;
            int i4 = i3;
            while (true) {
                view = null;
                if (i4 < 0) {
                    view2 = null;
                    break;
                }
                view2 = stickyChildren.get(i4);
                int scrollY = getScrollY();
                if ((scrollY < 0 && view2.getTop() + scrollY <= getStickyY()) || view2.getTop() <= getStickyY()) {
                    break;
                }
                i4--;
            }
            if (i4 != i3) {
                view = stickyChildren.get(i4 + 1);
            }
            View view3 = this.mCurrentStickyView;
            if (view2 != null) {
                if (view != null && !isSink(view2)) {
                    i = Math.max(0, view2.getHeight() - (view.getTop() - getStickyY()));
                }
                stickyChild(view2, i);
            }
            if (view3 != view2) {
                this.mCurrentStickyView = view2;
                if (view3 != null) {
                    resetTranslationYOffSticky(view3);
                }
                stickyChange(view3, view2);
                return;
            }
            return;
        }
        clearCurrentStickyView();
        clearCurrentStickyViews();
    }

    private void clearCurrentStickyView() {
        View view = this.mCurrentStickyView;
        if (view != null) {
            this.mCurrentStickyView = null;
            resetTranslationYOffSticky(view);
            stickyChange(view, null);
        }
    }

    private void clearCurrentStickyViews() {
        if (this.mCurrentStickyViews.isEmpty()) {
            return;
        }
        for (View view : this.mCurrentStickyViews) {
            resetTranslationYOffSticky(view);
        }
        this.mCurrentStickyViews.clear();
        permanentStickyChange(this.mCurrentStickyViews);
    }

    private void stickyChild(View view, int i) {
        view.setY(getStickyY() - i);
        view.setClickable(true);
    }

    private int getStickyY() {
        return getScrollY() + getPaddingTop() + this.mStickyOffset;
    }

    private void permanentStickyChild(List<View> list) {
        this.mTempStickyViews.clear();
        for (int i = 0; i < list.size(); i++) {
            View view = list.get(i);
            int permanentHeight = getPermanentHeight(list, i);
            if (view.getTop() <= getStickyY() + permanentHeight) {
                view.setY(getStickyY() + permanentHeight);
                view.setClickable(true);
                this.mTempStickyViews.add(view);
            }
        }
        resetTranslationYOffSticky(this.mTempStickyViews, this.mCurrentStickyViews);
        if (isListEqual()) {
            return;
        }
        this.mCurrentStickyViews.clear();
        this.mCurrentStickyViews.addAll(this.mTempStickyViews);
        this.mTempStickyViews.clear();
        permanentStickyChange(this.mCurrentStickyViews);
    }

    private int getPermanentHeight(List<View> list, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            View view = list.get(i3);
            if (!isSink(view)) {
                i2 += view.getMeasuredHeight();
            }
        }
        return i2;
    }

    private boolean isListEqual() {
        if (this.mTempStickyViews.size() == this.mCurrentStickyViews.size()) {
            int size = this.mTempStickyViews.size();
            for (int i = 0; i < size; i++) {
                if (this.mTempStickyViews.get(i) != this.mCurrentStickyViews.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    void setScrollState(int i) {
        if (i == this.mScrollState) {
            return;
        }
        this.mScrollState = i;
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        scrollChange(computeVerticalScrollOffset, computeVerticalScrollOffset);
    }

    public int getOwnScrollY() {
        return computeVerticalScrollOffset();
    }

    public View findFirstVisibleView() {
        int scrollY = getScrollY() + getPaddingTop();
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        for (int i = 0; i < size; i++) {
            View view = effectiveChildren.get(i);
            if (view.getTop() <= scrollY && view.getBottom() > scrollY) {
                return view;
            }
        }
        return null;
    }

    public View findLastVisibleView() {
        int height = (getHeight() - getPaddingBottom()) + getScrollY();
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        for (int i = 0; i < size; i++) {
            View view = effectiveChildren.get(i);
            if (view.getTop() < height && view.getBottom() >= height) {
                return view;
            }
        }
        return null;
    }

    public boolean isScrollTop() {
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        if (size > 0) {
            boolean z = getScrollY() <= 0 && !ScrollUtils.canScrollVertically(effectiveChildren.get(0), -1);
            if (z) {
                for (int i = size - 1; i >= 0; i--) {
                    View view = effectiveChildren.get(i);
                    if (ScrollUtils.isConsecutiveScrollerChild(view) && ScrollUtils.canScrollVertically(view, -1)) {
                        return false;
                    }
                }
            }
            return z;
        }
        return true;
    }

    public boolean isScrollBottom() {
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        if (size > 0) {
            boolean z = getScrollY() >= this.mScrollRange && !ScrollUtils.canScrollVertically(effectiveChildren.get(effectiveChildren.size() - 1), 1);
            if (z) {
                for (int i = size - 1; i >= 0; i--) {
                    View view = effectiveChildren.get(i);
                    if (ScrollUtils.isConsecutiveScrollerChild(view) && ScrollUtils.canScrollVertically(view, 1)) {
                        return false;
                    }
                }
            }
            return z;
        }
        return true;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        if (i > 0) {
            return !isScrollBottom();
        }
        return !isScrollTop();
    }

    public void setOnVerticalScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.mOnScrollChangeListener = onScrollChangeListener;
    }

    public OnScrollChangeListener getOnVerticalScrollChangeListener() {
        return this.mOnScrollChangeListener;
    }

    @Override // android.view.View, android.support.p083v4.view.ScrollingView
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View, android.support.p083v4.view.ScrollingView
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View, android.support.p083v4.view.ScrollingView
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View, android.support.p083v4.view.ScrollingView
    public int computeVerticalScrollRange() {
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            View view = nonGoneChildren.get(i2);
            if (!ScrollUtils.isConsecutiveScrollerChild(view)) {
                i += view.getHeight();
            } else if (ScrollUtils.canScrollVertically(view)) {
                View scrolledView = ScrollUtils.getScrolledView(view);
                i += ScrollUtils.computeVerticalScrollRange(scrolledView) + scrolledView.getPaddingTop() + scrolledView.getPaddingBottom();
            } else {
                i += view.getHeight();
            }
        }
        return i;
    }

    @Override // android.view.View, android.support.p083v4.view.ScrollingView
    public int computeVerticalScrollOffset() {
        int scrollY = getScrollY();
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        for (int i = 0; i < size; i++) {
            View view = nonGoneChildren.get(i);
            if (ScrollUtils.isConsecutiveScrollerChild(view)) {
                scrollY += ScrollUtils.computeVerticalScrollOffset(view);
            }
        }
        return scrollY;
    }

    @Override // android.view.View, android.support.p083v4.view.ScrollingView
    public int computeVerticalScrollExtent() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private View getTouchTarget(int i, int i2) {
        for (View view : getNonGoneChildren()) {
            if (ScrollUtils.isTouchPointInView(view, i, i2)) {
                return view;
            }
        }
        return null;
    }

    private boolean isIntercept(MotionEvent motionEvent) {
        int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
        if (findPointerIndex < 0 || findPointerIndex >= motionEvent.getPointerCount()) {
            return true;
        }
        return isIntercept(ScrollUtils.getRawX(this, motionEvent, findPointerIndex), ScrollUtils.getRawY(this, motionEvent, findPointerIndex));
    }

    private boolean isIntercept(int i, int i2) {
        View touchTarget = getTouchTarget(i, i2);
        if (touchTarget != null) {
            return ScrollUtils.isConsecutiveScrollerChild(touchTarget);
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public Align align;
        public boolean isConsecutive;
        public boolean isNestedScroll;
        public boolean isSink;
        public boolean isSticky;
        public boolean isTriggerScroll;
        public int scrollChild;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum Align {
            LEFT(1),
            RIGHT(2),
            CENTER(3);
            
            int value;

            Align(int i) {
                this.value = i;
            }

            static Align get(int i) {
                switch (i) {
                    case 1:
                        return LEFT;
                    case 2:
                        return RIGHT;
                    case 3:
                        return CENTER;
                    default:
                        return LEFT;
                }
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.isConsecutive = true;
            this.isNestedScroll = true;
            this.isSticky = false;
            this.isTriggerScroll = false;
            this.isSink = false;
            this.scrollChild = -1;
            this.align = Align.LEFT;
            TypedArray typedArray = null;
            try {
                try {
                    typedArray = context.obtainStyledAttributes(attributeSet, C9718R.styleable.ConsecutiveScrollerLayout_Layout);
                    this.isConsecutive = typedArray.getBoolean(1, true);
                    this.isNestedScroll = typedArray.getBoolean(2, true);
                    this.isSticky = typedArray.getBoolean(4, false);
                    this.isTriggerScroll = typedArray.getBoolean(5, false);
                    this.isSink = typedArray.getBoolean(3, false);
                    this.align = Align.get(typedArray.getInt(0, 1));
                    this.scrollChild = typedArray.getResourceId(6, -1);
                    if (typedArray == null) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (typedArray == null) {
                        return;
                    }
                }
                typedArray.recycle();
            } catch (Throwable th) {
                if (typedArray != null) {
                    typedArray.recycle();
                }
                throw th;
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.isConsecutive = true;
            this.isNestedScroll = true;
            this.isSticky = false;
            this.isTriggerScroll = false;
            this.isSink = false;
            this.scrollChild = -1;
            this.align = Align.LEFT;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.isConsecutive = true;
            this.isNestedScroll = true;
            this.isSticky = false;
            this.isTriggerScroll = false;
            this.isSink = false;
            this.scrollChild = -1;
            this.align = Align.LEFT;
        }
    }

    public void scrollToChild(View view) {
        scrollToChildWithOffset(view, 0);
    }

    public void scrollToChildWithOffset(View view, int i) {
        int indexOfChild = indexOfChild(view);
        if (indexOfChild != -1) {
            int top = (view.getTop() - i) - getAdjustHeightForChild(view);
            char c = 0;
            if (i >= 0) {
                if (getScrollY() + getPaddingTop() > top) {
                    c = 65535;
                } else if (getScrollY() + getPaddingTop() < top) {
                    c = 1;
                } else if (ScrollUtils.canScrollVertically(view, -1)) {
                    c = 65535;
                }
            } else {
                int viewsScrollOffset = getViewsScrollOffset(indexOfChild);
                if (getScrollY() + getPaddingTop() + viewsScrollOffset > top) {
                    c = 65535;
                } else if (getScrollY() + getPaddingTop() + viewsScrollOffset < top) {
                    c = 1;
                }
            }
            if (c != 0) {
                this.mScrollToIndex = indexOfChild;
                stopScroll();
                this.mScrollToIndexWithOffset = i;
                setScrollState(2);
                do {
                    if (c < 0) {
                        dispatchScroll(-200);
                    } else {
                        dispatchScroll(200);
                    }
                    this.mCycleCount++;
                } while (this.mScrollToIndex != -1);
            }
        }
    }

    public void smoothScrollToChild(View view) {
        smoothScrollToChildWithOffset(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (com.sinovatech.unicom.basic.view.decklayout.ScrollUtils.canScrollVertically(r8, -1) != false) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void smoothScrollToChildWithOffset(android.view.View r8, int r9) {
        /*
            r7 = this;
            int r0 = r7.indexOfChild(r8)
            r1 = -1
            if (r0 == r1) goto L70
            int r2 = r8.getTop()
            int r2 = r2 - r9
            int r3 = r7.getAdjustHeightForChild(r8)
            int r2 = r2 - r3
            r3 = 0
            r4 = 1
            if (r9 < 0) goto L35
            int r5 = r7.getScrollY()
            int r6 = r7.getPaddingTop()
            int r5 = r5 + r6
            if (r5 <= r2) goto L21
            goto L55
        L21:
            int r5 = r7.getScrollY()
            int r6 = r7.getPaddingTop()
            int r5 = r5 + r6
            if (r5 >= r2) goto L2e
            r1 = r4
            goto L55
        L2e:
            boolean r8 = com.sinovatech.unicom.basic.view.decklayout.ScrollUtils.canScrollVertically(r8, r1)
            if (r8 == 0) goto L54
            goto L55
        L35:
            int r8 = r7.getViewsScrollOffset(r0)
            int r5 = r7.getScrollY()
            int r6 = r7.getPaddingTop()
            int r5 = r5 + r6
            int r5 = r5 + r8
            if (r5 <= r2) goto L46
            goto L55
        L46:
            int r1 = r7.getScrollY()
            int r5 = r7.getPaddingTop()
            int r1 = r1 + r5
            int r1 = r1 + r8
            if (r1 >= r2) goto L54
            r1 = r4
            goto L55
        L54:
            r1 = r3
        L55:
            if (r1 == 0) goto L70
            r7.mScrollToIndex = r0
            r7.stopScroll()
            r7.mScrollToIndexWithOffset = r9
            r8 = 2
            r7.setScrollState(r8)
            if (r1 >= 0) goto L69
            r8 = -50
            r7.mSmoothScrollOffset = r8
            goto L6d
        L69:
            r8 = 50
            r7.mSmoothScrollOffset = r8
        L6d:
            r7.invalidate()
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout.smoothScrollToChildWithOffset(android.view.View, int):void");
    }

    private int getViewsScrollOffset(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && ScrollUtils.isConsecutiveScrollerChild(childAt)) {
                i2 += ScrollUtils.computeVerticalScrollOffset(childAt);
            }
            i++;
        }
        return i2;
    }

    public boolean isAutoAdjustHeightAtBottomView() {
        return this.mAutoAdjustHeightAtBottomView;
    }

    public void setAutoAdjustHeightAtBottomView(boolean z) {
        if (this.mAutoAdjustHeightAtBottomView != z) {
            this.mAutoAdjustHeightAtBottomView = z;
            requestLayout();
        }
    }

    public int getAdjustHeightOffset() {
        return this.mAdjustHeightOffset;
    }

    public void setAdjustHeightOffset(int i) {
        if (this.mAdjustHeightOffset != i) {
            this.mAdjustHeightOffset = i;
            requestLayout();
        }
    }

    public void setPermanent(boolean z) {
        if (this.isPermanent != z) {
            this.isPermanent = z;
            if (this.mAutoAdjustHeightAtBottomView) {
                requestLayout();
            } else {
                resetSticky();
            }
        }
    }

    public boolean isPermanent() {
        return this.isPermanent;
    }

    public boolean isDisableChildHorizontalScroll() {
        return this.disableChildHorizontalScroll;
    }

    public void setDisableChildHorizontalScroll(boolean z) {
        this.disableChildHorizontalScroll = z;
    }

    public void setStickyOffset(int i) {
        if (this.mStickyOffset != i) {
            this.mStickyOffset = i;
            resetSticky();
        }
    }

    public int getStickyOffset() {
        return this.mStickyOffset;
    }

    public View getCurrentStickyView() {
        return this.mCurrentStickyView;
    }

    public List<View> getCurrentStickyViews() {
        return this.mCurrentStickyViews;
    }

    public boolean theChildIsStick(View view) {
        return (!this.isPermanent && this.mCurrentStickyView == view) || (this.isPermanent && this.mCurrentStickyViews.contains(view));
    }

    public OnStickyChangeListener getOnStickyChangeListener() {
        return this.mOnStickyChangeListener;
    }

    public void setOnStickyChangeListener(OnStickyChangeListener onStickyChangeListener) {
        this.mOnStickyChangeListener = onStickyChangeListener;
    }

    public OnPermanentStickyChangeListener getOnPermanentStickyChangeListener() {
        return this.mOnPermanentStickyChangeListener;
    }

    public void setOnPermanentStickyChangeListener(OnPermanentStickyChangeListener onPermanentStickyChangeListener) {
        this.mOnPermanentStickyChangeListener = onPermanentStickyChangeListener;
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean startNestedScroll(int i, int i2) {
        return this.mChildHelper.startNestedScroll(i, i2);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public void stopNestedScroll(int i) {
        this.mChildHelper.stopNestedScroll(i);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i) {
        return this.mChildHelper.hasNestedScrollingParent(i);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    @Override // android.view.View, android.support.p083v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    @Override // android.support.p083v4.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2, int i3) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    @Override // android.support.p083v4.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return (layoutParams instanceof LayoutParams ? ((LayoutParams) layoutParams).isNestedScroll : false) && (i & 2) != 0;
    }

    @Override // android.support.p083v4.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i, int i2) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i, i2);
        checkTargetsScroll(false, false);
        startNestedScroll(2, i2);
        interceptAnimatorByAction(0);
    }

    @Override // android.support.p083v4.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i) {
        this.mParentHelper.onStopNestedScroll(view, i);
        stopNestedScroll(i);
        overSpinner();
    }

    @Override // android.support.p083v4.view.NestedScrollingParent2
    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, int i5) {
        onNestedScrollInternal(i4, i5);
    }

    private void onNestedScrollInternal(int i, int i2) {
        int i3 = this.mSecondScrollY;
        dispatchScroll(i);
        int i4 = this.mSecondScrollY - i3;
        this.mChildHelper.dispatchNestedScroll(0, i4, 0, i - i4, null, i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return onStartNestedScroll(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        onNestedScrollAccepted(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        onNestedScrollInternal(i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        onNestedPreScroll(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.support.p083v4.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    @Override // android.support.p083v4.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr, int i3) {
        dispatchNestedPreScroll(i, i2, iArr, null, i3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f2, true);
        fling((int) f2);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public boolean isEnableOverDragMode() {
        return this.overDragMode || this.overDragMaxDistanceOfTop > 0 || this.overDragMaxDistanceOfBottom > 0;
    }

    public void enableOverDragMode(boolean z) {
        this.overDragMode = z;
        if (z) {
            int dip2px = UIUtils.dip2px(180.0f);
            if (this.overDragMaxDistanceOfTop <= 0) {
                this.overDragMaxDistanceOfTop = dip2px;
            }
            if (this.overDragMaxDistanceOfBottom <= 0) {
                this.overDragMaxDistanceOfBottom = dip2px;
                return;
            }
            return;
        }
        this.overDragMaxDistanceOfTop = 0;
        this.overDragMaxDistanceOfBottom = 0;
    }

    public void enableOverDragMode(boolean z, int i, int i2) {
        this.overDragMode = z;
        if (z) {
            this.overDragMaxDistanceOfTop = i;
            this.overDragMaxDistanceOfBottom = i2;
            return;
        }
        this.overDragMaxDistanceOfTop = 0;
        this.overDragMaxDistanceOfBottom = 0;
    }

    public void setOverDragMaxDistanceOfTop(int i) {
        if (!isEnableOverDragMode()) {
            enableOverDragMode(true, i, this.overDragMaxDistanceOfBottom);
        } else {
            this.overDragMaxDistanceOfTop = i;
        }
    }

    public void setOverDragMaxDistanceOfBottom(int i) {
        if (!isEnableOverDragMode()) {
            enableOverDragMode(true, this.overDragMaxDistanceOfTop, i);
        } else {
            this.overDragMaxDistanceOfBottom = i;
        }
    }

    public void setOverDragRate(float f) {
        this.mDragRate = f;
    }
}
