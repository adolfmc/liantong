package com.mahc.custombottomsheetbehavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.C0884R;
import android.support.design.widget.CoordinatorLayout;
import android.support.p083v4.view.NestedScrollingChild;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Vector;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BottomSheetBehaviorGoogleMapsLike<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final int DEFAULT_ANCHOR_POINT = 700;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int STATE_ANCHOR_POINT = 3;
    public static final int STATE_COLLAPSED = 5;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 4;
    public static final int STATE_HIDDEN = 6;
    public static final int STATE_SETTLING = 2;
    private int mActivePointerId;
    private int mAnchorPoint;
    private Vector<BottomSheetCallback> mCallback;
    private boolean mCollapsible;
    private final ViewDragHelper.Callback mDragCallback;
    private boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastStableState;
    private int mMaxOffset;
    private int mMinOffset;
    private float mMinimumVelocity;
    private boolean mNestedScrolled;
    private WeakReference<View> mNestedScrollingChildRef;
    private int mParentHeight;
    private int mPeekHeight;
    private BottomSheetBehaviorGoogleMapsLike<V>.ScrollVelocityTracker mScrollVelocityTracker;
    private int mState;
    private boolean mTouchingScrollingChild;
    private ViewDragHelper mViewDragHelper;
    private WeakReference<V> mViewRef;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface State {
    }

    public BottomSheetBehaviorGoogleMapsLike() {
        this.mState = 3;
        this.mLastStableState = 3;
        this.mScrollVelocityTracker = new ScrollVelocityTracker();
        this.mDragCallback = new ViewDragHelper.Callback() { // from class: com.mahc.custombottomsheetbehavior.BottomSheetBehaviorGoogleMapsLike.1
            int constrain(int i, int i2, int i3) {
                return i < i2 ? i2 : i > i3 ? i3 : i;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                View view2;
                if (BottomSheetBehaviorGoogleMapsLike.this.mState == 1 || BottomSheetBehaviorGoogleMapsLike.this.mTouchingScrollingChild) {
                    return false;
                }
                if (BottomSheetBehaviorGoogleMapsLike.this.mState == 4 && BottomSheetBehaviorGoogleMapsLike.this.mActivePointerId == i && (view2 = (View) BottomSheetBehaviorGoogleMapsLike.this.mNestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) {
                    return false;
                }
                return BottomSheetBehaviorGoogleMapsLike.this.mViewRef != null && BottomSheetBehaviorGoogleMapsLike.this.mViewRef.get() == view;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehaviorGoogleMapsLike.this.dispatchOnSlide(i2);
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(1);
                }
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                int i;
                int i2 = 4;
                if (f2 < 0.0f) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mMinOffset;
                } else if (BottomSheetBehaviorGoogleMapsLike.this.mHideable && BottomSheetBehaviorGoogleMapsLike.this.shouldHide(view, f2)) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mParentHeight;
                    i2 = 6;
                } else if (f2 != 0.0f) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset;
                    i2 = 5;
                } else {
                    int top = view.getTop();
                    if (Math.abs(top - BottomSheetBehaviorGoogleMapsLike.this.mMinOffset) < Math.abs(top - BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset)) {
                        i = BottomSheetBehaviorGoogleMapsLike.this.mMinOffset;
                    } else {
                        i = BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset;
                        i2 = 5;
                    }
                }
                if (!BottomSheetBehaviorGoogleMapsLike.this.mCollapsible && i2 == 5) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mAnchorPoint;
                    i2 = 3;
                }
                if (BottomSheetBehaviorGoogleMapsLike.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), i)) {
                    BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(2);
                    ViewCompat.postOnAnimation(view, new SettleRunnable(view, i2));
                    return;
                }
                BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(i2);
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return constrain(i, BottomSheetBehaviorGoogleMapsLike.this.mMinOffset, BottomSheetBehaviorGoogleMapsLike.this.mHideable ? BottomSheetBehaviorGoogleMapsLike.this.mParentHeight : BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset);
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                return BottomSheetBehaviorGoogleMapsLike.this.mHideable ? BottomSheetBehaviorGoogleMapsLike.this.mParentHeight - BottomSheetBehaviorGoogleMapsLike.this.mMinOffset : BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset - BottomSheetBehaviorGoogleMapsLike.this.mMinOffset;
            }
        };
    }

    public BottomSheetBehaviorGoogleMapsLike(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 3;
        this.mLastStableState = 3;
        this.mScrollVelocityTracker = new ScrollVelocityTracker();
        this.mDragCallback = new ViewDragHelper.Callback() { // from class: com.mahc.custombottomsheetbehavior.BottomSheetBehaviorGoogleMapsLike.1
            int constrain(int i, int i2, int i3) {
                return i < i2 ? i2 : i > i3 ? i3 : i;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                View view2;
                if (BottomSheetBehaviorGoogleMapsLike.this.mState == 1 || BottomSheetBehaviorGoogleMapsLike.this.mTouchingScrollingChild) {
                    return false;
                }
                if (BottomSheetBehaviorGoogleMapsLike.this.mState == 4 && BottomSheetBehaviorGoogleMapsLike.this.mActivePointerId == i && (view2 = (View) BottomSheetBehaviorGoogleMapsLike.this.mNestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) {
                    return false;
                }
                return BottomSheetBehaviorGoogleMapsLike.this.mViewRef != null && BottomSheetBehaviorGoogleMapsLike.this.mViewRef.get() == view;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehaviorGoogleMapsLike.this.dispatchOnSlide(i2);
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(1);
                }
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                int i;
                int i2 = 4;
                if (f2 < 0.0f) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mMinOffset;
                } else if (BottomSheetBehaviorGoogleMapsLike.this.mHideable && BottomSheetBehaviorGoogleMapsLike.this.shouldHide(view, f2)) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mParentHeight;
                    i2 = 6;
                } else if (f2 != 0.0f) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset;
                    i2 = 5;
                } else {
                    int top = view.getTop();
                    if (Math.abs(top - BottomSheetBehaviorGoogleMapsLike.this.mMinOffset) < Math.abs(top - BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset)) {
                        i = BottomSheetBehaviorGoogleMapsLike.this.mMinOffset;
                    } else {
                        i = BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset;
                        i2 = 5;
                    }
                }
                if (!BottomSheetBehaviorGoogleMapsLike.this.mCollapsible && i2 == 5) {
                    i = BottomSheetBehaviorGoogleMapsLike.this.mAnchorPoint;
                    i2 = 3;
                }
                if (BottomSheetBehaviorGoogleMapsLike.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), i)) {
                    BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(2);
                    ViewCompat.postOnAnimation(view, new SettleRunnable(view, i2));
                    return;
                }
                BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(i2);
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return constrain(i, BottomSheetBehaviorGoogleMapsLike.this.mMinOffset, BottomSheetBehaviorGoogleMapsLike.this.mHideable ? BottomSheetBehaviorGoogleMapsLike.this.mParentHeight : BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset);
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                return BottomSheetBehaviorGoogleMapsLike.this.mHideable ? BottomSheetBehaviorGoogleMapsLike.this.mParentHeight - BottomSheetBehaviorGoogleMapsLike.this.mMinOffset : BottomSheetBehaviorGoogleMapsLike.this.mMaxOffset - BottomSheetBehaviorGoogleMapsLike.this.mMinOffset;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0884R.styleable.BottomSheetBehavior_Layout);
        setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(C0884R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, 0));
        setHideable(obtainStyledAttributes.getBoolean(C0884R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        obtainStyledAttributes.recycle();
        this.mAnchorPoint = 700;
        this.mCollapsible = true;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, C5284R.styleable.CustomBottomSheetBehavior);
        if (attributeSet != null) {
            this.mAnchorPoint = (int) obtainStyledAttributes2.getDimension(C5284R.styleable.CustomBottomSheetBehavior_anchorPoint, 0.0f);
            this.mState = obtainStyledAttributes2.getInt(C5284R.styleable.CustomBottomSheetBehavior_defaultState, 3);
        }
        obtainStyledAttributes2.recycle();
        this.mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.mState);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state == 1 || savedState.state == 2) {
            this.mState = 5;
        } else {
            this.mState = savedState.state;
        }
        this.mLastStableState = this.mState;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        int i2 = this.mState;
        if (i2 != 1 && i2 != 2) {
            if (coordinatorLayout.getFitsSystemWindows() && !v.getFitsSystemWindows()) {
                v.setFitsSystemWindows(true);
            }
            coordinatorLayout.onLayoutChild(v, i);
        }
        this.mParentHeight = coordinatorLayout.getHeight();
        this.mMinOffset = Math.max(0, this.mParentHeight - v.getHeight());
        this.mMaxOffset = Math.max(this.mParentHeight - this.mPeekHeight, this.mMinOffset);
        int i3 = this.mState;
        if (i3 == 3) {
            ViewCompat.offsetTopAndBottom(v, this.mAnchorPoint);
        } else if (i3 == 4) {
            ViewCompat.offsetTopAndBottom(v, this.mMinOffset);
        } else if (this.mHideable && i3 == 6) {
            ViewCompat.offsetTopAndBottom(v, this.mParentHeight);
        } else if (this.mState == 5) {
            ViewCompat.offsetTopAndBottom(v, this.mMaxOffset);
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference<>(v);
        this.mNestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a6 A[ADDED_TO_REGION] */
    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout r9, V r10, android.view.MotionEvent r11) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mahc.custombottomsheetbehavior.BottomSheetBehaviorGoogleMapsLike.onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (v.isShown()) {
            int actionMasked = motionEvent.getActionMasked();
            if (this.mState == 1 && actionMasked == 0) {
                return true;
            }
            if (this.mLastStableState == 3 && actionMasked == 2 && motionEvent.getY() > this.mInitialY && !this.mCollapsible) {
                reset();
                return false;
            }
            if (this.mViewDragHelper == null) {
                this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
            }
            this.mViewDragHelper.processTouchEvent(motionEvent);
            if (actionMasked == 0) {
                reset();
            }
            if (actionMasked == 2 && !this.mIgnoreEvents && Math.abs(this.mInitialY - motionEvent.getY()) > this.mViewDragHelper.getTouchSlop()) {
                setState(3);
            }
            return !this.mIgnoreEvents;
        }
        return false;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.mNestedScrolled = false;
        return (i & 2) != 0;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class ScrollVelocityTracker {
        private long mPreviousScrollTime;
        private float mScrollVelocity;

        private ScrollVelocityTracker() {
            this.mPreviousScrollTime = 0L;
            this.mScrollVelocity = 0.0f;
        }

        public void recordScroll(int i) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.mPreviousScrollTime;
            if (j != 0) {
                this.mScrollVelocity = (i / ((float) (currentTimeMillis - j))) * 1000.0f;
            }
            this.mPreviousScrollTime = currentTimeMillis;
        }

        public void clear() {
            this.mPreviousScrollTime = 0L;
            this.mScrollVelocity = 0.0f;
        }

        public float getScrollVelocity() {
            return this.mScrollVelocity;
        }
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (view != this.mNestedScrollingChildRef.get()) {
            return;
        }
        this.mScrollVelocityTracker.recordScroll(i2);
        int top = v.getTop();
        int i4 = top - i2;
        if ((this.mLastStableState == 5 && i4 < this.mAnchorPoint) || (this.mLastStableState == 4 && i4 > this.mAnchorPoint)) {
            iArr[1] = i2;
            ViewCompat.offsetTopAndBottom(v, this.mAnchorPoint - top);
            dispatchOnSlide(v.getTop());
            this.mNestedScrolled = true;
            return;
        }
        if (i2 > 0) {
            int i5 = this.mMinOffset;
            if (i4 < i5) {
                iArr[1] = top - i5;
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                setStateInternal(4);
            } else {
                iArr[1] = i2;
                ViewCompat.offsetTopAndBottom(v, -i2);
                setStateInternal(1);
            }
        } else if (i2 < 0 && !ViewCompat.canScrollVertically(view, -1)) {
            int i6 = this.mMaxOffset;
            if (i4 <= i6 || this.mHideable) {
                boolean z = this.mCollapsible;
                if (z || (!z && this.mAnchorPoint - i4 >= 0)) {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                }
            } else {
                iArr[1] = top - i6;
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                setStateInternal(5);
            }
        }
        dispatchOnSlide(v.getTop());
        this.mNestedScrolled = true;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        int i2;
        if (v.getTop() == this.mMinOffset) {
            setStateInternal(4);
            this.mLastStableState = 4;
        } else if (view == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
            float scrollVelocity = this.mScrollVelocityTracker.getScrollVelocity();
            float f = this.mMinimumVelocity;
            int i3 = 3;
            if (scrollVelocity > f) {
                int i4 = this.mLastStableState;
                if (i4 == 5) {
                    i2 = this.mAnchorPoint;
                } else if (i4 == 3) {
                    i2 = this.mMinOffset;
                    i3 = 4;
                } else {
                    i2 = this.mMinOffset;
                    i3 = 4;
                }
            } else if (scrollVelocity < (-f)) {
                int i5 = this.mLastStableState;
                if (i5 == 4) {
                    i2 = this.mAnchorPoint;
                } else if (!this.mCollapsible) {
                    i2 = this.mAnchorPoint;
                } else if (i5 == 3) {
                    i2 = this.mMaxOffset;
                    i3 = 5;
                } else {
                    i2 = this.mMaxOffset;
                    i3 = 5;
                }
            } else {
                double top = v.getTop();
                if (top > this.mAnchorPoint * 1.25d && this.mCollapsible) {
                    i2 = this.mMaxOffset;
                    i3 = 5;
                } else {
                    i2 = this.mAnchorPoint;
                    if (top < i2 * 0.5d) {
                        i2 = this.mMinOffset;
                        i3 = 4;
                    }
                }
            }
            this.mLastStableState = i3;
            if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), i2)) {
                setStateInternal(2);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, i3));
            } else {
                setStateInternal(i3);
            }
            this.mNestedScrolled = false;
        }
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.mNestedScrollingChildRef.get() && (this.mState != 4 || super.onNestedPreFling(coordinatorLayout, v, view, f, f2));
    }

    public final void setPeekHeight(int i) {
        this.mPeekHeight = Math.max(0, i);
        this.mMaxOffset = this.mParentHeight - i;
    }

    public final int getPeekHeight() {
        return this.mPeekHeight;
    }

    public void setAnchorPoint(int i) {
        this.mAnchorPoint = i;
    }

    public int getAnchorPoint() {
        return this.mAnchorPoint;
    }

    public void setHideable(boolean z) {
        this.mHideable = z;
    }

    public boolean isHideable() {
        return this.mHideable;
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
    }

    public boolean isCollapsible() {
        return this.mCollapsible;
    }

    public void addBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        if (this.mCallback == null) {
            this.mCallback = new Vector<>();
        }
        this.mCallback.add(bottomSheetCallback);
    }

    public final void setState(int i) {
        int i2;
        if (i == this.mState) {
            return;
        }
        if (i == 5 || i == 4 || i == 3 || (this.mHideable && i == 6)) {
            this.mState = i;
            this.mLastStableState = i;
        }
        WeakReference<V> weakReference = this.mViewRef;
        V v = weakReference == null ? null : weakReference.get();
        if (v == null) {
            return;
        }
        if (i == 5) {
            i2 = this.mMaxOffset;
        } else if (i == 3) {
            i2 = this.mAnchorPoint;
        } else if (i == 4) {
            i2 = this.mMinOffset;
        } else if (this.mHideable && i == 6) {
            i2 = this.mParentHeight;
        } else {
            throw new IllegalArgumentException("Illegal state argument: " + i);
        }
        setStateInternal(2);
        if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), i2)) {
            ViewCompat.postOnAnimation(v, new SettleRunnable(v, i));
        }
    }

    public final int getState() {
        return this.mState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStateInternal(int i) {
        if (this.mState == i) {
            return;
        }
        this.mState = i;
        V v = this.mViewRef.get();
        if (v == null || this.mCallback == null) {
            return;
        }
        notifyStateChangedToListeners(v, i);
    }

    private void notifyStateChangedToListeners(@NonNull View view, int i) {
        Iterator<BottomSheetCallback> it = this.mCallback.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(view, i);
        }
    }

    private void notifyOnSlideToListeners(@NonNull View view, float f) {
        Iterator<BottomSheetCallback> it = this.mCallback.iterator();
        while (it.hasNext()) {
            it.next().onSlide(view, f);
        }
    }

    private void reset() {
        this.mActivePointerId = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldHide(View view, float f) {
        return view.getTop() >= this.mMaxOffset && Math.abs((((float) view.getTop()) + (f * HIDE_FRICTION)) - ((float) this.mMaxOffset)) / ((float) this.mPeekHeight) > 0.5f;
    }

    private View findScrollingChild(View view) {
        if (view instanceof NestedScrollingChild) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnSlide(int i) {
        V v = this.mViewRef.get();
        if (v == null || this.mCallback == null) {
            return;
        }
        int i2 = this.mMaxOffset;
        if (i > i2) {
            notifyOnSlideToListeners(v, (i2 - i) / this.mPeekHeight);
        } else {
            notifyOnSlideToListeners(v, (i2 - i) / (i2 - this.mMinOffset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class SettleRunnable implements Runnable {
        private final int mTargetState;
        private final View mView;

        SettleRunnable(View view, int i) {
            this.mView = view;
            this.mTargetState = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BottomSheetBehaviorGoogleMapsLike.this.mViewDragHelper == null || !BottomSheetBehaviorGoogleMapsLike.this.mViewDragHelper.continueSettling(true)) {
                BottomSheetBehaviorGoogleMapsLike.this.setStateInternal(this.mTargetState);
            } else {
                ViewCompat.postOnAnimation(this.mView, this);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.mahc.custombottomsheetbehavior.BottomSheetBehaviorGoogleMapsLike.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final int state;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    public static <V extends View> BottomSheetBehaviorGoogleMapsLike<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (!(behavior instanceof BottomSheetBehaviorGoogleMapsLike)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehaviorGoogleMapsLike");
        }
        return (BottomSheetBehaviorGoogleMapsLike) behavior;
    }
}
