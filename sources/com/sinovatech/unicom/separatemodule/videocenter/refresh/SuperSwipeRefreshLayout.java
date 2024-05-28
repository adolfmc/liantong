package com.sinovatech.unicom.separatemodule.videocenter.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.support.p083v4.view.MotionEventCompat;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.widget.NestedScrollView;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

@SuppressLint({"ClickableViewAccessibility"})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SuperSwipeRefreshLayout extends ViewGroup {
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final float DRAG_RATE = 0.5f;
    private static final int HEADER_VIEW_HEIGHT = 50;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS = {16842766};
    private static final String LOG_TAG = "SwipeRefreshLayout";
    private static final int SCALE_DOWN_DURATION = 150;
    private CircleProgressView defaultProgressView;
    private float density;
    private boolean isProgressEnable;
    private int mActivePointerId;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    private RelativeLayout mFooterViewContainer;
    private int mFooterViewHeight;
    private int mFooterViewIndex;
    private int mFooterViewWidth;
    protected int mFrom;
    private HeadViewContainer mHeadViewContainer;
    private int mHeaderViewHeight;
    private int mHeaderViewIndex;
    private int mHeaderViewWidth;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private OnPullRefreshListener mListener;
    private boolean mLoadMore;
    private int mMediumAnimationDuration;
    private boolean mNotify;
    private OnPushLoadMoreListener mOnPushLoadMoreListener;
    private boolean mOriginalOffsetCalculated;
    protected int mOriginalOffsetTop;
    private Animation.AnimationListener mRefreshListener;
    private boolean mRefreshing;
    private boolean mReturningToStart;
    private boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    private float mSpinnerFinalOffset;
    private float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private int mTouchSlop;
    private boolean mUsingCustomStart;
    private int pushDistance;
    private boolean targetScrollWithLayout;
    private boolean usingDefaultHeader;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnPullRefreshListener {
        void onPullDistance(int i);

        void onPullEnable(boolean z);

        void onRefresh();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnPushLoadMoreListener {
        void onLoadMore();

        void onPushDistance(int i);

        void onPushEnable(boolean z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListenerCallBack() {
        int height = this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getHeight();
        OnPullRefreshListener onPullRefreshListener = this.mListener;
        if (onPullRefreshListener != null) {
            onPullRefreshListener.onPullDistance(height);
        }
        if (this.usingDefaultHeader && this.isProgressEnable) {
            this.defaultProgressView.setPullDistance(height);
        }
    }

    public void setHeaderView(View view) {
        HeadViewContainer headViewContainer;
        if (view == null || (headViewContainer = this.mHeadViewContainer) == null) {
            return;
        }
        this.usingDefaultHeader = false;
        headViewContainer.removeAllViews();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mHeaderViewWidth, this.mHeaderViewHeight);
        layoutParams.addRule(12);
        this.mHeadViewContainer.addView(view, layoutParams);
    }

    public void setFooterView(View view) {
        RelativeLayout relativeLayout;
        if (view == null || (relativeLayout = this.mFooterViewContainer) == null) {
            return;
        }
        relativeLayout.removeAllViews();
        this.mFooterViewContainer.addView(view, new RelativeLayout.LayoutParams(this.mFooterViewWidth, this.mFooterViewHeight));
    }

    public SuperSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SuperSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mLoadMore = false;
        this.mTotalDragDistance = -1.0f;
        this.mOriginalOffsetCalculated = false;
        this.mActivePointerId = -1;
        this.mHeaderViewIndex = -1;
        this.mFooterViewIndex = -1;
        this.targetScrollWithLayout = true;
        this.pushDistance = 0;
        this.defaultProgressView = null;
        this.usingDefaultHeader = true;
        this.density = 1.0f;
        this.isProgressEnable = true;
        this.mRefreshListener = new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                SuperSwipeRefreshLayout.this.isProgressEnable = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SuperSwipeRefreshLayout.this.isProgressEnable = true;
                if (SuperSwipeRefreshLayout.this.mRefreshing) {
                    if (SuperSwipeRefreshLayout.this.mNotify) {
                        if (SuperSwipeRefreshLayout.this.usingDefaultHeader) {
                            ViewCompat.setAlpha(SuperSwipeRefreshLayout.this.defaultProgressView, 1.0f);
                            SuperSwipeRefreshLayout.this.defaultProgressView.setOnDraw(true);
                            new Thread(SuperSwipeRefreshLayout.this.defaultProgressView).start();
                        }
                        if (SuperSwipeRefreshLayout.this.mListener != null) {
                            SuperSwipeRefreshLayout.this.mListener.onRefresh();
                        }
                    }
                } else {
                    SuperSwipeRefreshLayout.this.mHeadViewContainer.setVisibility(8);
                    if (SuperSwipeRefreshLayout.this.mScale) {
                        SuperSwipeRefreshLayout.this.setAnimationProgress(0.0f);
                    } else {
                        SuperSwipeRefreshLayout superSwipeRefreshLayout = SuperSwipeRefreshLayout.this;
                        superSwipeRefreshLayout.setTargetOffsetTopAndBottom(superSwipeRefreshLayout.mOriginalOffsetTop - SuperSwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
                    }
                }
                SuperSwipeRefreshLayout superSwipeRefreshLayout2 = SuperSwipeRefreshLayout.this;
                superSwipeRefreshLayout2.mCurrentTargetOffsetTop = superSwipeRefreshLayout2.mHeadViewContainer.getTop();
                SuperSwipeRefreshLayout.this.updateListenerCallBack();
            }
        };
        this.mAnimateToCorrectPosition = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.8
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                int i;
                if (SuperSwipeRefreshLayout.this.mUsingCustomStart) {
                    i = (int) SuperSwipeRefreshLayout.this.mSpinnerFinalOffset;
                } else {
                    i = (int) (SuperSwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SuperSwipeRefreshLayout.this.mOriginalOffsetTop));
                }
                SuperSwipeRefreshLayout.this.setTargetOffsetTopAndBottom((SuperSwipeRefreshLayout.this.mFrom + ((int) ((i - SuperSwipeRefreshLayout.this.mFrom) * f))) - SuperSwipeRefreshLayout.this.mHeadViewContainer.getTop(), false);
            }

            @Override // android.view.animation.Animation
            public void setAnimationListener(Animation.AnimationListener animationListener) {
                super.setAnimationListener(animationListener);
            }
        };
        this.mAnimateToStartPosition = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.9
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mHeaderViewWidth = defaultDisplay.getWidth();
        this.mFooterViewWidth = defaultDisplay.getWidth();
        this.mHeaderViewHeight = (int) (displayMetrics.density * 50.0f);
        this.mFooterViewHeight = (int) (displayMetrics.density * 50.0f);
        this.defaultProgressView = new CircleProgressView(getContext());
        createHeaderViewContainer();
        createFooterViewContainer();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.mSpinnerFinalOffset = displayMetrics.density * 64.0f;
        this.density = displayMetrics.density;
        this.mTotalDragDistance = this.mSpinnerFinalOffset;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        if (this.mHeaderViewIndex >= 0 || this.mFooterViewIndex >= 0) {
            if (i2 == i - 2) {
                return this.mHeaderViewIndex;
            }
            if (i2 == i - 1) {
                return this.mFooterViewIndex;
            }
            int i3 = this.mFooterViewIndex;
            int i4 = this.mHeaderViewIndex;
            if (i3 <= i4) {
                i3 = i4;
            }
            int i5 = this.mFooterViewIndex;
            int i6 = this.mHeaderViewIndex;
            if (i5 >= i6) {
                i5 = i6;
            }
            return (i2 < i5 || i2 >= i3 + (-1)) ? (i2 >= i3 || i2 == i3 + (-1)) ? i2 + 2 : i2 : i2 + 1;
        }
        return i2;
    }

    private void createHeaderViewContainer() {
        int i = this.mHeaderViewHeight;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (i * 0.8d), (int) (i * 0.8d));
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        this.mHeadViewContainer = new HeadViewContainer(getContext());
        this.mHeadViewContainer.setVisibility(8);
        this.defaultProgressView.setVisibility(0);
        this.defaultProgressView.setOnDraw(false);
        this.mHeadViewContainer.addView(this.defaultProgressView, layoutParams);
        addView(this.mHeadViewContainer);
    }

    private void createFooterViewContainer() {
        this.mFooterViewContainer = new RelativeLayout(getContext());
        this.mFooterViewContainer.setVisibility(8);
        addView(this.mFooterViewContainer);
    }

    public void setOnPullRefreshListener(OnPullRefreshListener onPullRefreshListener) {
        this.mListener = onPullRefreshListener;
    }

    public void setHeaderViewBackgroundColor(int i) {
        this.mHeadViewContainer.setBackgroundColor(i);
    }

    public void setOnPushLoadMoreListener(OnPushLoadMoreListener onPushLoadMoreListener) {
        this.mOnPushLoadMoreListener = onPushLoadMoreListener;
    }

    public void setRefreshing(boolean z) {
        int i;
        if (z && this.mRefreshing != z) {
            this.mRefreshing = z;
            if (!this.mUsingCustomStart) {
                i = (int) (this.mSpinnerFinalOffset + this.mOriginalOffsetTop);
            } else {
                i = (int) this.mSpinnerFinalOffset;
            }
            setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
            this.mNotify = false;
            startScaleUpAnimation(this.mRefreshListener);
            return;
        }
        setRefreshing(z, false);
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setOnDraw(false);
        }
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        this.mHeadViewContainer.setVisibility(0);
        this.mScaleAnimation = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.2
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.mScaleAnimation.setDuration(this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mHeadViewContainer.setAnimationListener(animationListener);
        }
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mScaleAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimationProgress(float f) {
        if (!this.usingDefaultHeader) {
            f = 1.0f;
        }
        ViewCompat.setScaleX(this.mHeadViewContainer, f);
        ViewCompat.setScaleY(this.mHeadViewContainer, f);
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (this.mRefreshing) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        this.mScaleDownAnimation = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.3
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation.setDuration(150L);
        this.mHeadViewContainer.setAnimationListener(animationListener);
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mScaleDownAnimation);
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.mHeadViewContainer) && !childAt.equals(this.mFooterViewContainer)) {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.mTotalDragDistance = i;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.mTarget == null) {
            ensureTarget();
        }
        if (this.mTarget == null) {
            return;
        }
        int measuredHeight2 = this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getMeasuredHeight();
        if (!this.targetScrollWithLayout) {
            measuredHeight2 = 0;
        }
        View view = this.mTarget;
        int paddingLeft = getPaddingLeft();
        int paddingTop = (getPaddingTop() + measuredHeight2) - this.pushDistance;
        int paddingLeft2 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop2 = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        Log.d(LOG_TAG, "debug:onLayout childHeight = " + paddingTop2);
        view.layout(paddingLeft, paddingTop, paddingLeft2 + paddingLeft, paddingTop2 + paddingTop);
        int measuredWidth2 = this.mHeadViewContainer.getMeasuredWidth();
        int measuredHeight3 = this.mHeadViewContainer.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.mCurrentTargetOffsetTop;
        this.mHeadViewContainer.layout(i5 - i6, i7, i6 + i5, measuredHeight3 + i7);
        int measuredWidth3 = this.mFooterViewContainer.getMeasuredWidth();
        int measuredHeight4 = this.mFooterViewContainer.getMeasuredHeight();
        int i8 = measuredWidth3 / 2;
        int i9 = this.pushDistance;
        this.mFooterViewContainer.layout(i5 - i8, measuredHeight - i9, i5 + i8, (measuredHeight + measuredHeight4) - i9);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.mHeadViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mHeaderViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mHeaderViewHeight * 3, 1073741824));
        this.mFooterViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mFooterViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mFooterViewHeight, 1073741824));
        if (!this.mUsingCustomStart && !this.mOriginalOffsetCalculated) {
            this.mOriginalOffsetCalculated = true;
            int i3 = -this.mHeadViewContainer.getMeasuredHeight();
            this.mOriginalOffsetTop = i3;
            this.mCurrentTargetOffsetTop = i3;
            updateListenerCallBack();
        }
        this.mHeaderViewIndex = -1;
        int i4 = 0;
        while (true) {
            if (i4 >= getChildCount()) {
                break;
            } else if (getChildAt(i4) == this.mHeadViewContainer) {
                this.mHeaderViewIndex = i4;
                break;
            } else {
                i4++;
            }
        }
        this.mFooterViewIndex = -1;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            if (getChildAt(i5) == this.mFooterViewContainer) {
                this.mFooterViewIndex = i5;
                return;
            }
        }
    }

    public boolean isChildScrollToTop() {
        if (Build.VERSION.SDK_INT < 14) {
            View view = this.mTarget;
            if (!(view instanceof AbsListView)) {
                return view.getScrollY() <= 0;
            }
            AbsListView absListView = (AbsListView) view;
            if (absListView.getChildCount() > 0) {
                return absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop();
            }
            return true;
        }
        return !ViewCompat.canScrollVertically(this.mTarget, -1);
    }

    public boolean isChildScrollToBottom() {
        int lastVisiblePosition;
        if (isChildScrollToTop()) {
            return false;
        }
        View view = this.mTarget;
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int itemCount = recyclerView.getAdapter().getItemCount();
            if ((layoutManager instanceof LinearLayoutManager) && itemCount > 0) {
                if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] iArr = new int[2];
                ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(iArr);
                if (Math.max(iArr[0], iArr[1]) == itemCount - 1) {
                    return true;
                }
            }
            return false;
        } else if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            int count = ((ListAdapter) absListView.getAdapter()).getCount();
            return (absListView.getFirstVisiblePosition() != 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop()) && (lastVisiblePosition = absListView.getLastVisiblePosition()) > 0 && count > 0 && lastVisiblePosition == count - 1;
        } else {
            if (view instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) view;
                View childAt = scrollView.getChildAt(scrollView.getChildCount() - 1);
                if (childAt != null && childAt.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()) == 0) {
                    return true;
                }
            } else if (view instanceof NestedScrollView) {
                NestedScrollView nestedScrollView = (NestedScrollView) view;
                View childAt2 = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                if (childAt2 != null && childAt2.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()) == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r6.ensureTarget()
            int r0 = android.support.p083v4.view.MotionEventCompat.getActionMasked(r7)
            boolean r1 = r6.mReturningToStart
            r2 = 0
            if (r1 == 0) goto L10
            if (r0 != 0) goto L10
            r6.mReturningToStart = r2
        L10:
            boolean r1 = r6.isEnabled()
            if (r1 == 0) goto La4
            boolean r1 = r6.mReturningToStart
            if (r1 != 0) goto La4
            boolean r1 = r6.mRefreshing
            if (r1 != 0) goto La4
            boolean r1 = r6.mLoadMore
            if (r1 != 0) goto La4
            boolean r1 = r6.isChildScrollToTop()
            if (r1 != 0) goto L30
            boolean r1 = r6.isChildScrollToBottom()
            if (r1 != 0) goto L30
            goto La4
        L30:
            r1 = 6
            if (r0 == r1) goto L9e
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3 = -1
            r4 = 1
            switch(r0) {
                case 0: goto L40;
                case 1: goto L3b;
                case 2: goto L61;
                case 3: goto L3b;
                default: goto L3a;
            }
        L3a:
            goto La1
        L3b:
            r6.mIsBeingDragged = r2
            r6.mActivePointerId = r3
            goto La1
        L40:
            int r0 = r6.mOriginalOffsetTop
            com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout$HeadViewContainer r5 = r6.mHeadViewContainer
            int r5 = r5.getTop()
            int r0 = r0 - r5
            r6.setTargetOffsetTopAndBottom(r0, r4)
            int r0 = android.support.p083v4.view.MotionEventCompat.getPointerId(r7, r2)
            r6.mActivePointerId = r0
            r6.mIsBeingDragged = r2
            int r0 = r6.mActivePointerId
            float r0 = r6.getMotionEventY(r7, r0)
            int r5 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r5 != 0) goto L5f
            return r2
        L5f:
            r6.mInitialMotionY = r0
        L61:
            int r0 = r6.mActivePointerId
            if (r0 != r3) goto L6d
            java.lang.String r7 = "SwipeRefreshLayout"
            java.lang.String r0 = "Got ACTION_MOVE event but don't have an active pointer id."
            android.util.Log.e(r7, r0)
            return r2
        L6d:
            float r7 = r6.getMotionEventY(r7, r0)
            int r0 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r0 != 0) goto L76
            return r2
        L76:
            boolean r0 = r6.isChildScrollToBottom()
            if (r0 == 0) goto L8d
            float r0 = r6.mInitialMotionY
            float r0 = r0 - r7
            int r7 = r6.mTouchSlop
            float r7 = (float) r7
            int r7 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r7 <= 0) goto La1
            boolean r7 = r6.mIsBeingDragged
            if (r7 != 0) goto La1
            r6.mIsBeingDragged = r4
            goto La1
        L8d:
            float r0 = r6.mInitialMotionY
            float r7 = r7 - r0
            int r0 = r6.mTouchSlop
            float r0 = (float) r0
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 <= 0) goto La1
            boolean r7 = r6.mIsBeingDragged
            if (r7 != 0) goto La1
            r6.mIsBeingDragged = r4
            goto La1
        L9e:
            r6.onSecondaryPointerUp(r7)
        La1:
            boolean r7 = r6.mIsBeingDragged
            return r7
        La4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private float getMotionEventY(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, findPointerIndex);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (isEnabled() && !this.mReturningToStart && (isChildScrollToTop() || isChildScrollToBottom())) {
            if (isChildScrollToBottom()) {
                return handlerPushTouchEvent(motionEvent, actionMasked);
            }
            return handlerPullTouchEvent(motionEvent, actionMasked);
        }
        return false;
    }

    private boolean handlerPullTouchEvent(MotionEvent motionEvent, int i) {
        switch (i) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                break;
            case 1:
            case 3:
                int i2 = this.mActivePointerId;
                if (i2 == -1) {
                    if (i == 1) {
                        Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                this.mIsBeingDragged = false;
                if ((MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, i2)) - this.mInitialMotionY) * 0.5f > this.mTotalDragDistance) {
                    setRefreshing(true, true);
                } else {
                    this.mRefreshing = false;
                    animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, this.mScale ? null : new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation) {
                            if (SuperSwipeRefreshLayout.this.mScale) {
                                return;
                            }
                            SuperSwipeRefreshLayout.this.startScaleDownAnimation(null);
                        }
                    });
                }
                this.mActivePointerId = -1;
                return false;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y = (MotionEventCompat.getY(motionEvent, findPointerIndex) - this.mInitialMotionY) * 0.5f;
                if (this.mIsBeingDragged) {
                    float f = y / this.mTotalDragDistance;
                    if (f >= 0.0f) {
                        float min = Math.min(1.0f, Math.abs(f));
                        float abs = Math.abs(y) - this.mTotalDragDistance;
                        float f2 = this.mUsingCustomStart ? this.mSpinnerFinalOffset - this.mOriginalOffsetTop : this.mSpinnerFinalOffset;
                        double max = Math.max(0.0f, Math.min(abs, f2 * DECELERATE_INTERPOLATION_FACTOR) / f2) / 4.0f;
                        int pow = this.mOriginalOffsetTop + ((int) ((f2 * min) + (((float) (max - Math.pow(max, 2.0d))) * DECELERATE_INTERPOLATION_FACTOR * f2 * DECELERATE_INTERPOLATION_FACTOR)));
                        if (this.mHeadViewContainer.getVisibility() != 0) {
                            this.mHeadViewContainer.setVisibility(0);
                        }
                        if (!this.mScale) {
                            ViewCompat.setScaleX(this.mHeadViewContainer, 1.0f);
                            ViewCompat.setScaleY(this.mHeadViewContainer, 1.0f);
                        }
                        if (this.usingDefaultHeader) {
                            float f3 = y / this.mTotalDragDistance;
                            if (f3 >= 1.0f) {
                                f3 = 1.0f;
                            }
                            ViewCompat.setScaleX(this.defaultProgressView, f3);
                            ViewCompat.setScaleY(this.defaultProgressView, f3);
                            ViewCompat.setAlpha(this.defaultProgressView, f3);
                        }
                        float f4 = this.mTotalDragDistance;
                        if (y < f4) {
                            if (this.mScale) {
                                setAnimationProgress(y / f4);
                            }
                            OnPullRefreshListener onPullRefreshListener = this.mListener;
                            if (onPullRefreshListener != null) {
                                onPullRefreshListener.onPullEnable(false);
                            }
                        } else {
                            OnPullRefreshListener onPullRefreshListener2 = this.mListener;
                            if (onPullRefreshListener2 != null) {
                                onPullRefreshListener2.onPullEnable(true);
                            }
                        }
                        setTargetOffsetTopAndBottom(pow - this.mCurrentTargetOffsetTop, true);
                        break;
                    } else {
                        return false;
                    }
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

    private boolean handlerPushTouchEvent(MotionEvent motionEvent, int i) {
        OnPushLoadMoreListener onPushLoadMoreListener;
        switch (i) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                Log.d(LOG_TAG, "debug:onTouchEvent ACTION_DOWN");
                break;
            case 1:
            case 3:
                int i2 = this.mActivePointerId;
                if (i2 == -1) {
                    if (i == 1) {
                        Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                float y = (this.mInitialMotionY - MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, i2))) * 0.5f;
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                int i3 = this.mFooterViewHeight;
                if (y < i3 || this.mOnPushLoadMoreListener == null) {
                    this.pushDistance = 0;
                } else {
                    this.pushDistance = i3;
                }
                if (Build.VERSION.SDK_INT < 11) {
                    updateFooterViewPosition();
                    if (this.pushDistance == this.mFooterViewHeight && (onPushLoadMoreListener = this.mOnPushLoadMoreListener) != null) {
                        this.mLoadMore = true;
                        onPushLoadMoreListener.onLoadMore();
                    }
                } else {
                    animatorFooterToBottom((int) y, this.pushDistance);
                }
                return false;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = (this.mInitialMotionY - MotionEventCompat.getY(motionEvent, findPointerIndex)) * 0.5f;
                if (this.mIsBeingDragged) {
                    this.pushDistance = (int) y2;
                    updateFooterViewPosition();
                    OnPushLoadMoreListener onPushLoadMoreListener2 = this.mOnPushLoadMoreListener;
                    if (onPushLoadMoreListener2 != null) {
                        onPushLoadMoreListener2.onPushEnable(this.pushDistance >= this.mFooterViewHeight);
                        break;
                    }
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

    @TargetApi(11)
    private void animatorFooterToBottom(int i, final int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.setDuration(150L);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SuperSwipeRefreshLayout.this.pushDistance = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SuperSwipeRefreshLayout.this.updateFooterViewPosition();
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (i2 > 0 && SuperSwipeRefreshLayout.this.mOnPushLoadMoreListener != null) {
                    SuperSwipeRefreshLayout.this.mLoadMore = true;
                    SuperSwipeRefreshLayout.this.mOnPushLoadMoreListener.onLoadMore();
                    return;
                }
                SuperSwipeRefreshLayout.this.resetTargetLayout();
                SuperSwipeRefreshLayout.this.mLoadMore = false;
            }
        });
        ofInt.setInterpolator(this.mDecelerateInterpolator);
        ofInt.start();
    }

    public void setLoadMore(boolean z) {
        if (z || !this.mLoadMore) {
            return;
        }
        if (Build.VERSION.SDK_INT < 11) {
            this.mLoadMore = false;
            this.pushDistance = 0;
            updateFooterViewPosition();
            return;
        }
        animatorFooterToBottom(this.mFooterViewHeight, 0);
    }

    private void animateOffsetToCorrectPosition(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200L);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mHeadViewContainer.setAnimationListener(animationListener);
        }
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i, Animation.AnimationListener animationListener) {
        if (this.mScale) {
            startScaleDownReturnToStartAnimation(i, animationListener);
        } else {
            this.mFrom = i;
            this.mAnimateToStartPosition.reset();
            this.mAnimateToStartPosition.setDuration(200L);
            this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
            if (animationListener != null) {
                this.mHeadViewContainer.setAnimationListener(animationListener);
            }
            this.mHeadViewContainer.clearAnimation();
            this.mHeadViewContainer.startAnimation(this.mAnimateToStartPosition);
        }
        resetTargetLayoutDelay(200);
    }

    public void resetTargetLayoutDelay(int i) {
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.7
            @Override // java.lang.Runnable
            public void run() {
                SuperSwipeRefreshLayout.this.resetTargetLayout();
            }
        }, i);
    }

    public void resetTargetLayout() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        View view = this.mTarget;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((view.getWidth() - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((view.getHeight() - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.mHeadViewContainer.getMeasuredWidth();
        int i = measuredWidth / 2;
        int i2 = measuredWidth2 / 2;
        this.mHeadViewContainer.layout(i - i2, -this.mHeadViewContainer.getMeasuredHeight(), i2 + i, 0);
        int measuredWidth3 = this.mFooterViewContainer.getMeasuredWidth();
        int i3 = measuredWidth3 / 2;
        this.mFooterViewContainer.layout(i - i3, measuredHeight, i + i3, this.mFooterViewContainer.getMeasuredHeight() + measuredHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToStart(float f) {
        int i = this.mFrom;
        setTargetOffsetTopAndBottom((i + ((int) ((this.mOriginalOffsetTop - i) * f))) - this.mHeadViewContainer.getTop(), false);
    }

    private void startScaleDownReturnToStartAnimation(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mStartingScale = ViewCompat.getScaleX(this.mHeadViewContainer);
        this.mScaleDownToStartAnimation = new Animation() { // from class: com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.10
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.setAnimationProgress(SuperSwipeRefreshLayout.this.mStartingScale + ((-SuperSwipeRefreshLayout.this.mStartingScale) * f));
                SuperSwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mScaleDownToStartAnimation.setDuration(150L);
        if (animationListener != null) {
            this.mHeadViewContainer.setAnimationListener(animationListener);
        }
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mScaleDownToStartAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetOffsetTopAndBottom(int i, boolean z) {
        this.mHeadViewContainer.bringToFront();
        this.mHeadViewContainer.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mHeadViewContainer.getTop();
        if (z && Build.VERSION.SDK_INT < 11) {
            invalidate();
        }
        updateListenerCallBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFooterViewPosition() {
        this.mFooterViewContainer.setVisibility(0);
        this.mFooterViewContainer.bringToFront();
        if (Build.VERSION.SDK_INT < 19) {
            this.mFooterViewContainer.getParent().requestLayout();
        }
        this.mFooterViewContainer.offsetTopAndBottom(-this.pushDistance);
        updatePushDistanceListener();
    }

    private void updatePushDistanceListener() {
        OnPushLoadMoreListener onPushLoadMoreListener = this.mOnPushLoadMoreListener;
        if (onPushLoadMoreListener != null) {
            onPushLoadMoreListener.onPushDistance(this.pushDistance);
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class HeadViewContainer extends RelativeLayout {
        private Animation.AnimationListener mListener;

        public HeadViewContainer(Context context) {
            super(context);
        }

        public void setAnimationListener(Animation.AnimationListener animationListener) {
            this.mListener = animationListener;
        }

        @Override // android.view.View
        public void onAnimationStart() {
            super.onAnimationStart();
            Animation.AnimationListener animationListener = this.mListener;
            if (animationListener != null) {
                animationListener.onAnimationStart(getAnimation());
            }
        }

        @Override // android.view.View
        public void onAnimationEnd() {
            super.onAnimationEnd();
            Animation.AnimationListener animationListener = this.mListener;
            if (animationListener != null) {
                animationListener.onAnimationEnd(getAnimation());
            }
        }
    }

    public boolean isTargetScrollWithLayout() {
        return this.targetScrollWithLayout;
    }

    public void setTargetScrollWithLayout(boolean z) {
        this.targetScrollWithLayout = z;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class OnPullRefreshListenerAdapter implements OnPullRefreshListener {
        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPullRefreshListener
        public void onPullDistance(int i) {
        }

        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPullRefreshListener
        public void onPullEnable(boolean z) {
        }

        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPullRefreshListener
        public void onRefresh() {
        }

        public OnPullRefreshListenerAdapter() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class OnPushLoadMoreListenerAdapter implements OnPushLoadMoreListener {
        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPushLoadMoreListener
        public void onLoadMore() {
        }

        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPushLoadMoreListener
        public void onPushDistance(int i) {
        }

        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPushLoadMoreListener
        public void onPushEnable(boolean z) {
        }

        public OnPushLoadMoreListenerAdapter() {
        }
    }

    public void setDefaultCircleProgressColor(int i) {
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setProgressColor(i);
        }
    }

    public void setDefaultCircleBackgroundColor(int i) {
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setCircleBackgroundColor(i);
        }
    }

    public void setDefaultCircleShadowColor(int i) {
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setShadowColor(i);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class CircleProgressView extends View implements Runnable {
        private static final int PEROID = 16;
        private Paint bgPaint;
        private RectF bgRect;
        private int circleBackgroundColor;
        private int height;
        private boolean isOnDraw;
        private boolean isRunning;
        private RectF ovalRect;
        private int progressColor;
        private Paint progressPaint;
        private int shadowColor;
        private int speed;
        private int startAngle;
        private int swipeAngle;
        private int width;

        public CircleProgressView(Context context) {
            super(context);
            this.isOnDraw = false;
            this.isRunning = false;
            this.startAngle = 0;
            this.speed = 8;
            this.ovalRect = null;
            this.bgRect = null;
            this.progressColor = -3355444;
            this.circleBackgroundColor = -1;
            this.shadowColor = -6710887;
        }

        public CircleProgressView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.isOnDraw = false;
            this.isRunning = false;
            this.startAngle = 0;
            this.speed = 8;
            this.ovalRect = null;
            this.bgRect = null;
            this.progressColor = -3355444;
            this.circleBackgroundColor = -1;
            this.shadowColor = -6710887;
        }

        public CircleProgressView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.isOnDraw = false;
            this.isRunning = false;
            this.startAngle = 0;
            this.speed = 8;
            this.ovalRect = null;
            this.bgRect = null;
            this.progressColor = -3355444;
            this.circleBackgroundColor = -1;
            this.shadowColor = -6710887;
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(getBgRect(), 0.0f, 360.0f, false, createBgPaint());
            int i = this.startAngle;
            if ((i / 360) % 2 == 0) {
                this.swipeAngle = (i % 720) / 2;
            } else {
                this.swipeAngle = 360 - ((i % 720) / 2);
            }
            canvas.drawArc(getOvalRect(), this.startAngle, this.swipeAngle, false, createPaint());
        }

        private RectF getBgRect() {
            int i;
            this.width = getWidth();
            this.height = getHeight();
            if (this.bgRect == null) {
                float f = (int) (SuperSwipeRefreshLayout.this.density * SuperSwipeRefreshLayout.DECELERATE_INTERPOLATION_FACTOR);
                this.bgRect = new RectF(f, f, this.width - i, this.height - i);
            }
            return this.bgRect;
        }

        private RectF getOvalRect() {
            int i;
            this.width = getWidth();
            this.height = getHeight();
            if (this.ovalRect == null) {
                float f = (int) (SuperSwipeRefreshLayout.this.density * 8.0f);
                this.ovalRect = new RectF(f, f, this.width - i, this.height - i);
            }
            return this.ovalRect;
        }

        public void setProgressColor(int i) {
            this.progressColor = i;
        }

        public void setCircleBackgroundColor(int i) {
            this.circleBackgroundColor = i;
        }

        public void setShadowColor(int i) {
            this.shadowColor = i;
        }

        private Paint createPaint() {
            if (this.progressPaint == null) {
                this.progressPaint = new Paint();
                this.progressPaint.setStrokeWidth((int) (SuperSwipeRefreshLayout.this.density * 3.0f));
                this.progressPaint.setStyle(Paint.Style.STROKE);
                this.progressPaint.setAntiAlias(true);
            }
            this.progressPaint.setColor(this.progressColor);
            return this.progressPaint;
        }

        private Paint createBgPaint() {
            if (this.bgPaint == null) {
                this.bgPaint = new Paint();
                this.bgPaint.setColor(this.circleBackgroundColor);
                this.bgPaint.setStyle(Paint.Style.FILL);
                this.bgPaint.setAntiAlias(true);
                if (Build.VERSION.SDK_INT >= 11) {
                    setLayerType(1, this.bgPaint);
                }
                this.bgPaint.setShadowLayer(4.0f, 0.0f, SuperSwipeRefreshLayout.DECELERATE_INTERPOLATION_FACTOR, this.shadowColor);
            }
            return this.bgPaint;
        }

        public void setPullDistance(int i) {
            this.startAngle = i * 2;
            postInvalidate();
        }

        @Override // java.lang.Runnable
        public void run() {
            while (this.isOnDraw) {
                this.isRunning = true;
                long currentTimeMillis = System.currentTimeMillis();
                this.startAngle += this.speed;
                postInvalidate();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (currentTimeMillis2 < 16) {
                    try {
                        Thread.sleep(16 - currentTimeMillis2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void setOnDraw(boolean z) {
            this.isOnDraw = z;
        }

        public void setSpeed(int i) {
            this.speed = i;
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        @Override // android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }

        @Override // android.view.View
        protected void onDetachedFromWindow() {
            this.isOnDraw = false;
            super.onDetachedFromWindow();
        }
    }
}
