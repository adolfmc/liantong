package com.sinovatech.unicom.separatemodule.videocenter.refresh;

import android.content.Context;
import android.os.Build;
import android.support.p083v4.view.MotionEventCompat;
import android.support.p083v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ImageView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PullRefreshLayout extends ViewGroup {
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int DRAG_MAX_DISTANCE = 64;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    private boolean down;
    private int mActivePointerId;
    private final Animation mAnimateLoadToCorrectPosition;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCurrentOffsetTop;
    private Interpolator mDecelerateInterpolator;
    private boolean mDispatchTargetTouchDown;
    private float mDragPercent;
    public int mDurationToCorrectPosition;
    public int mDurationToStartPosition;
    private int mFrom;
    private float mInitialMotionY;
    private int mInitialOffsetTop;
    private boolean mIsBeingDragged;
    private RefreshMode mLastDirection;
    private RefreshDrawable mLoadDrawable;
    private OnLoadListener mLoadLisener;
    private ImageView mLoadView;
    private boolean mLoading;
    private RefreshMode mMode;
    private boolean mNotify;
    private Animation.AnimationListener mRefreshAnimationListener;
    private RefreshDrawable mRefreshDrawable;
    private OnRefreshListener mRefreshListener;
    private Animation.AnimationListener mRefreshLoadAnimationListener;
    private ImageView mRefreshView;
    private boolean mRefreshing;
    private int mSpinnerFinalOffset;
    private float mStartPoint;
    private View mTarget;
    private Animation.AnimationListener mToStartListener;
    private int mTotalDragDistance;
    private int mTouchSlop;

    /* renamed from: up */
    private boolean f18638up;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnLoadListener {
        void onLoad();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnRefreshListener {
        void onRefresh();
    }

    public PullRefreshLayout(Context context) {
        this(context, null);
    }

    public PullRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = RefreshMode.getDefault();
        this.mLastDirection = RefreshMode.DISABLED;
        this.mAnimateToCorrectPosition = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.PullRefreshLayout.1
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                PullRefreshLayout.this.setTargetOffsetTop((PullRefreshLayout.this.mFrom + ((int) ((PullRefreshLayout.this.mSpinnerFinalOffset - PullRefreshLayout.this.mFrom) * f))) - PullRefreshLayout.this.mTarget.getTop(), false);
            }
        };
        this.mAnimateLoadToCorrectPosition = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.PullRefreshLayout.2
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                PullRefreshLayout.this.setTargetOffsetTop((PullRefreshLayout.this.mFrom + ((int) (((-PullRefreshLayout.this.mSpinnerFinalOffset) - PullRefreshLayout.this.mFrom) * f))) - PullRefreshLayout.this.mTarget.getTop(), false);
            }
        };
        this.mAnimateToStartPosition = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.PullRefreshLayout.3
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                PullRefreshLayout.this.moveToStart(f);
            }
        };
        this.mRefreshAnimationListener = new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.PullRefreshLayout.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                PullRefreshLayout.this.mRefreshView.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (PullRefreshLayout.this.mRefreshing) {
                    PullRefreshLayout.this.mRefreshDrawable.start();
                    if (PullRefreshLayout.this.mNotify && PullRefreshLayout.this.mRefreshListener != null) {
                        PullRefreshLayout.this.mRefreshListener.onRefresh();
                    }
                } else {
                    PullRefreshLayout.this.mRefreshDrawable.stop();
                    PullRefreshLayout.this.mRefreshView.setVisibility(8);
                    PullRefreshLayout.this.animateOffsetToStartPosition();
                }
                PullRefreshLayout pullRefreshLayout = PullRefreshLayout.this;
                pullRefreshLayout.mCurrentOffsetTop = pullRefreshLayout.mTarget.getTop();
                PullRefreshLayout.this.mLastDirection = RefreshMode.DISABLED;
            }
        };
        this.mRefreshLoadAnimationListener = new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.PullRefreshLayout.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                PullRefreshLayout.this.mLoadView.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (PullRefreshLayout.this.mLoading) {
                    PullRefreshLayout.this.mLoadDrawable.start();
                    if (PullRefreshLayout.this.mLoadLisener != null) {
                        PullRefreshLayout.this.mLoadLisener.onLoad();
                    }
                } else {
                    PullRefreshLayout.this.mLoadDrawable.stop();
                    PullRefreshLayout.this.mLoadView.setVisibility(8);
                    PullRefreshLayout.this.animateOffsetToStartPosition();
                }
                PullRefreshLayout pullRefreshLayout = PullRefreshLayout.this;
                pullRefreshLayout.mCurrentOffsetTop = pullRefreshLayout.mTarget.getTop();
                PullRefreshLayout.this.mLastDirection = RefreshMode.DISABLED;
            }
        };
        this.mToStartListener = new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.PullRefreshLayout.6
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                PullRefreshLayout.this.mRefreshDrawable.stop();
                PullRefreshLayout.this.mLoadDrawable.stop();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                PullRefreshLayout.this.mRefreshView.setVisibility(8);
                PullRefreshLayout.this.mLoadView.setVisibility(8);
                PullRefreshLayout pullRefreshLayout = PullRefreshLayout.this;
                pullRefreshLayout.mCurrentOffsetTop = pullRefreshLayout.mTarget.getTop();
            }
        };
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        int integer = getResources().getInteger(17694721);
        this.mDurationToStartPosition = integer;
        this.mDurationToCorrectPosition = integer;
        int dp2px = dp2px(64);
        this.mTotalDragDistance = dp2px;
        this.mSpinnerFinalOffset = dp2px;
        this.mRefreshView = new ImageView(context);
        setRefreshDrawable(new PlaneDrawable(getContext(), this));
        this.mRefreshView.setVisibility(8);
        addView(this.mRefreshView, 0);
        this.mLoadView = new ImageView(context);
        setLoadDrawable(new PlaneLoadDrawable(getContext(), this));
        this.mLoadView.setVisibility(8);
        addView(this.mLoadView, 0);
        setWillNotDraw(false);
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    }

    public void setRefreshDrawable(RefreshDrawable refreshDrawable) {
        setRefreshing(false);
        this.mRefreshDrawable = refreshDrawable;
        this.mRefreshView.setImageDrawable(this.mRefreshDrawable);
    }

    public void setLoadDrawable(RefreshDrawable refreshDrawable) {
        setLoading(false);
        this.mLoadDrawable = refreshDrawable;
        this.mLoadView.setImageDrawable(this.mLoadDrawable);
    }

    public int getFinalOffset() {
        return this.mSpinnerFinalOffset;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ensureTarget();
        if (this.mTarget == null) {
            return;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingRight()) - getPaddingLeft(), 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824);
        this.mTarget.measure(makeMeasureSpec, makeMeasureSpec2);
        this.mRefreshView.measure(makeMeasureSpec, makeMeasureSpec2);
        this.mLoadView.measure(makeMeasureSpec, makeMeasureSpec2);
    }

    private void ensureTarget() {
        if (this.mTarget == null && getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt != this.mRefreshView && childAt != this.mLoadView) {
                    this.mTarget = childAt;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || (canChildScrollUp() && canChildScrollDown() && !this.mRefreshing)) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    if (!this.mRefreshing || !this.mLoading) {
                        setTargetOffsetTop(0, true);
                    }
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.mIsBeingDragged = false;
                    float motionEventY = getMotionEventY(motionEvent, this.mActivePointerId);
                    if (motionEventY != -1.0f) {
                        this.mInitialMotionY = motionEventY;
                        this.mInitialOffsetTop = this.mCurrentOffsetTop;
                        this.mDispatchTargetTouchDown = false;
                        this.mDragPercent = 0.0f;
                        this.mStartPoint = this.mInitialMotionY;
                        this.f18638up = canChildScrollUp();
                        this.down = canChildScrollDown();
                        break;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    this.mLastDirection = RefreshMode.DISABLED;
                    break;
                case 2:
                    int i = this.mActivePointerId;
                    if (i == -1) {
                        return false;
                    }
                    float motionEventY2 = getMotionEventY(motionEvent, i);
                    if (motionEventY2 == -1.0f) {
                        return false;
                    }
                    float f = motionEventY2 - this.mStartPoint;
                    if ((this.mLastDirection == RefreshMode.PULL_FROM_START && f < 0.0f) || (this.mLastDirection == RefreshMode.PULL_FROM_END && f > 0.0f)) {
                        return false;
                    }
                    if ((canChildScrollUp() && f > 0.0f) || (canChildScrollDown() && f < 0.0f)) {
                        this.mStartPoint = motionEventY2;
                    }
                    int i2 = this.mTouchSlop;
                    if (f > i2) {
                        if (canChildScrollUp() || this.mMode == RefreshMode.PULL_FROM_END) {
                            this.mIsBeingDragged = false;
                            return false;
                        } else if (this.mMode == RefreshMode.PULL_FROM_START || this.mMode == RefreshMode.BOTH) {
                            this.mIsBeingDragged = true;
                            this.mLastDirection = RefreshMode.PULL_FROM_START;
                            break;
                        }
                    } else if ((-f) > i2) {
                        if (canChildScrollDown() || this.mMode == RefreshMode.PULL_FROM_START) {
                            this.mIsBeingDragged = false;
                            return false;
                        } else if (!this.f18638up && !this.down) {
                            this.mIsBeingDragged = false;
                            return false;
                        } else if (this.mMode == RefreshMode.PULL_FROM_END || this.mMode == RefreshMode.BOTH) {
                            this.mIsBeingDragged = true;
                            this.mLastDirection = RefreshMode.PULL_FROM_END;
                            break;
                        }
                    }
                    break;
            }
        } else {
            onSecondaryPointerUp(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsBeingDragged) {
            return super.onTouchEvent(motionEvent);
        }
        int i = -1;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                this.mInitialMotionY = motionEvent.getY();
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                this.mDragPercent = 0.0f;
                this.mStartPoint = this.mInitialMotionY;
                this.f18638up = canChildScrollUp();
                this.down = canChildScrollDown();
                break;
            case 1:
            case 3:
                int i2 = this.mActivePointerId;
                if (i2 == -1) {
                    return false;
                }
                if (this.mRefreshing || this.mLoading) {
                    if (this.mDispatchTargetTouchDown) {
                        this.mTarget.dispatchTouchEvent(motionEvent);
                        this.mDispatchTargetTouchDown = false;
                    }
                    return false;
                }
                float y = (MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, i2)) - this.mInitialMotionY) * 0.5f;
                this.mIsBeingDragged = false;
                int i3 = this.mTotalDragDistance;
                if (y > i3 && this.mCurrentOffsetTop > i3) {
                    setRefreshing(true, true);
                    this.mLastDirection = RefreshMode.PULL_FROM_START;
                } else {
                    float abs = Math.abs(y);
                    int i4 = this.mTotalDragDistance;
                    if (abs > i4 && this.mCurrentOffsetTop < (-i4)) {
                        setLoading(true);
                        this.mLastDirection = RefreshMode.PULL_FROM_END;
                    } else {
                        this.mRefreshing = false;
                        animateOffsetToStartPosition();
                    }
                }
                this.mActivePointerId = -1;
                this.mLastDirection = RefreshMode.DISABLED;
                return false;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex < 0) {
                    return false;
                }
                float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                float f = y2 - this.mStartPoint;
                if ((this.mLastDirection != RefreshMode.PULL_FROM_START || f >= 0.0f) && (this.mLastDirection != RefreshMode.PULL_FROM_END || f <= 0.0f)) {
                    if ((!this.mIsBeingDragged && f > 0.0f && this.mLastDirection == RefreshMode.PULL_FROM_START) || (f < 0.0f && this.mLastDirection == RefreshMode.PULL_FROM_END)) {
                        this.mIsBeingDragged = true;
                    }
                    if (this.mRefreshing || this.mLoading) {
                        int i5 = (int) (this.mInitialOffsetTop + f);
                        if ((this.mRefreshing && canChildScrollUp()) || (this.mLoading && canChildScrollDown())) {
                            this.mInitialMotionY = y2;
                            this.mInitialOffsetTop = 0;
                            if (this.mDispatchTargetTouchDown) {
                                this.mTarget.dispatchTouchEvent(motionEvent);
                            } else {
                                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                                obtain.setAction(0);
                                this.mDispatchTargetTouchDown = true;
                                this.mTarget.dispatchTouchEvent(obtain);
                            }
                        } else if (i5 < 0) {
                            if (this.mDispatchTargetTouchDown) {
                                this.mTarget.dispatchTouchEvent(motionEvent);
                            } else {
                                MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
                                obtain2.setAction(0);
                                this.mDispatchTargetTouchDown = true;
                                this.mTarget.dispatchTouchEvent(obtain2);
                            }
                            i = 0;
                        } else {
                            i = this.mTotalDragDistance;
                            if (i5 <= i) {
                                if (this.mDispatchTargetTouchDown) {
                                    MotionEvent obtain3 = MotionEvent.obtain(motionEvent);
                                    obtain3.setAction(3);
                                    this.mDispatchTargetTouchDown = false;
                                    this.mTarget.dispatchTouchEvent(obtain3);
                                }
                                i = i5;
                            }
                        }
                        setTargetOffsetTop(i - this.mCurrentOffsetTop, true);
                        break;
                    } else {
                        float f2 = f * 0.5f;
                        float f3 = f2 / this.mTotalDragDistance;
                        this.mDragPercent = Math.min(1.0f, Math.abs(f3));
                        float abs2 = Math.abs(f2) - this.mTotalDragDistance;
                        float f4 = this.mSpinnerFinalOffset;
                        double max = Math.max(0.0f, Math.min(abs2, f4 * DECELERATE_INTERPOLATION_FACTOR) / f4) / 4.0f;
                        float pow = ((float) (max - Math.pow(max, 2.0d))) * DECELERATE_INTERPOLATION_FACTOR * f4 * DECELERATE_INTERPOLATION_FACTOR;
                        float f5 = this.mDragPercent;
                        int i6 = (int) ((f4 * f5) + pow);
                        if (f3 < 0.0f) {
                            if (this.mLoadView.getVisibility() != 0) {
                                this.mLoadView.setVisibility(0);
                            }
                            if (Math.abs(f2) < this.mTotalDragDistance) {
                                this.mLoadDrawable.setPercent(this.mDragPercent);
                            }
                            setTargetOffsetTop((-i6) - this.mCurrentOffsetTop, true);
                            break;
                        } else {
                            int i7 = (int) ((f4 * f5) + pow);
                            if (this.mRefreshView.getVisibility() != 0) {
                                this.mRefreshView.setVisibility(0);
                            }
                            if (f2 < this.mTotalDragDistance) {
                                this.mRefreshDrawable.setPercent(this.mDragPercent);
                            }
                            setTargetOffsetTop(i7 - this.mCurrentOffsetTop, true);
                            break;
                        }
                    }
                } else {
                    return true;
                }
                break;
            case 5:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
            case 6:
                onSecondaryPointerUp(motionEvent);
                break;
        }
        return true;
    }

    public void setDurations(int i, int i2) {
        this.mDurationToStartPosition = i;
        this.mDurationToCorrectPosition = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateOffsetToStartPosition() {
        this.mFrom = this.mCurrentOffsetTop;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(this.mDurationToStartPosition);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        this.mAnimateToStartPosition.setAnimationListener(this.mToStartListener);
        this.mRefreshView.clearAnimation();
        this.mRefreshView.startAnimation(this.mAnimateToStartPosition);
    }

    private void animateOffsetToCorrectPosition() {
        this.mFrom = this.mCurrentOffsetTop;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(this.mDurationToCorrectPosition);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        this.mAnimateToCorrectPosition.setAnimationListener(this.mRefreshAnimationListener);
        this.mRefreshView.clearAnimation();
        this.mRefreshView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateLoadOffsetToCorrectPosition() {
        this.mFrom = this.mCurrentOffsetTop;
        this.mAnimateLoadToCorrectPosition.reset();
        this.mAnimateLoadToCorrectPosition.setDuration(this.mDurationToCorrectPosition);
        this.mAnimateLoadToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        this.mAnimateLoadToCorrectPosition.setAnimationListener(this.mRefreshLoadAnimationListener);
        this.mLoadView.clearAnimation();
        this.mLoadView.startAnimation(this.mAnimateLoadToCorrectPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToStart(float f) {
        int i = this.mFrom;
        int top = (i - ((int) (i * f))) - this.mTarget.getTop();
        setTargetOffsetTop(top, false);
        if (top > 0) {
            this.mRefreshDrawable.setPercent(this.mDragPercent * (1.0f - f));
        } else {
            this.mLoadDrawable.setPercent(this.mDragPercent * (1.0f - f));
        }
    }

    public void setRefreshing(boolean z) {
        if (this.mRefreshing != z) {
            setRefreshing(z, false);
        }
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (this.mRefreshing) {
                this.mRefreshDrawable.setPercent(1.0f);
                animateOffsetToCorrectPosition();
                return;
            }
            this.mLastDirection = RefreshMode.DISABLED;
            animateOffsetToStartPosition();
        }
    }

    public void setLoading(boolean z) {
        if (this.mLoading != z) {
            ensureTarget();
            this.mLoading = z;
            if (this.mLoading) {
                this.mLoadDrawable.setPercent(1.0f);
                animateLoadOffsetToCorrectPosition();
                return;
            }
            this.mLastDirection = RefreshMode.DISABLED;
            animateOffsetToStartPosition();
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
    }

    private float getMotionEventY(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, findPointerIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetOffsetTop(int i, boolean z) {
        this.mTarget.offsetTopAndBottom(i);
        this.mCurrentOffsetTop = this.mTarget.getTop();
        this.mRefreshDrawable.offsetTopAndBottom(i);
        this.mLoadDrawable.offsetTopAndBottom(i);
        if (!z || Build.VERSION.SDK_INT >= 11) {
            return;
        }
        invalidate();
    }

    private boolean canChildScrollUp() {
        if (Build.VERSION.SDK_INT < 14) {
            View view = this.mTarget;
            if (!(view instanceof AbsListView)) {
                return view.getScrollY() > 0;
            }
            AbsListView absListView = (AbsListView) view;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        }
        return ViewCompat.canScrollVertically(this.mTarget, -1);
    }

    public boolean canChildScrollDown() {
        if (Build.VERSION.SDK_INT < 14) {
            View view = this.mTarget;
            if (!(view instanceof AbsListView)) {
                return view.getHeight() - this.mTarget.getScrollY() > 0;
            }
            AbsListView absListView = (AbsListView) view;
            View childAt = absListView.getChildAt(absListView.getChildCount() - 1);
            if (childAt != null) {
                return absListView.getLastVisiblePosition() == absListView.getCount() - 1 && childAt.getBottom() > absListView.getPaddingBottom();
            }
            return false;
        }
        return ViewCompat.canScrollVertically(this.mTarget, 1);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ensureTarget();
        if (this.mTarget == null) {
            return;
        }
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        View view = this.mTarget;
        int i5 = (measuredWidth + paddingLeft) - paddingRight;
        int i6 = (measuredHeight + paddingTop) - paddingBottom;
        view.layout(paddingLeft, view.getTop() + paddingTop, i5, this.mTarget.getTop() + i6);
        this.mRefreshView.layout(paddingLeft, paddingTop, i5, i6);
        this.mLoadView.layout(paddingLeft, paddingTop, i5, i6);
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getContext().getResources().getDisplayMetrics());
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.mLoadLisener = onLoadListener;
    }
}
