package com.scwang.smartrefresh.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.view.NestedScrollingChildHelper;
import android.support.p083v4.view.NestedScrollingParent;
import android.support.p083v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshContent;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.constant.DimensionStatus;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.impl.RefreshContentWrapper;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.util.SmartUtil;

@SuppressLint({"RestrictedApi"})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmartRefreshLayout extends ViewGroup implements NestedScrollingParent, RefreshLayout {
    protected static ViewGroup.MarginLayoutParams sDefaultMarginLP = new ViewGroup.MarginLayoutParams(-1, -1);
    protected static DefaultRefreshFooterCreator sFooterCreator;
    protected static DefaultRefreshHeaderCreator sHeaderCreator;
    protected static DefaultRefreshInitializer sRefreshInitializer;
    protected Runnable animationRunnable;
    protected boolean mAttachedToWindow;
    protected int mCurrentVelocity;
    protected boolean mDisableContentWhenLoading;
    protected boolean mDisableContentWhenRefresh;
    protected char mDragDirection;
    protected float mDragRate;
    protected boolean mEnableAutoLoadMore;
    protected boolean mEnableClipFooterWhenFixedBehind;
    protected boolean mEnableClipHeaderWhenFixedBehind;
    protected boolean mEnableDisallowIntercept;
    protected boolean mEnableFooterFollowWhenNoMoreData;
    protected boolean mEnableFooterTranslationContent;
    protected boolean mEnableHeaderTranslationContent;
    protected boolean mEnableLoadMore;
    protected boolean mEnableLoadMoreWhenContentNotFull;
    protected boolean mEnableNestedScrolling;
    protected boolean mEnableOverScrollBounce;
    protected boolean mEnableOverScrollDrag;
    protected boolean mEnablePreviewInEditMode;
    protected boolean mEnablePureScrollMode;
    protected boolean mEnableRefresh;
    protected boolean mEnableScrollContentWhenLoaded;
    protected boolean mEnableScrollContentWhenRefreshed;
    protected MotionEvent mFalsifyEvent;
    protected int mFixedFooterViewId;
    protected int mFixedHeaderViewId;
    protected int mFloorDuration;
    protected int mFooterBackgroundColor;
    protected int mFooterHeight;
    protected DimensionStatus mFooterHeightStatus;
    protected int mFooterInsetStart;
    protected boolean mFooterLocked;
    protected float mFooterMaxDragRate;
    protected boolean mFooterNeedTouchEventWhenLoading;
    protected boolean mFooterNoMoreData;
    protected boolean mFooterNoMoreDataEffective;
    protected int mFooterTranslationViewId;
    protected float mFooterTriggerRate;
    protected Handler mHandler;
    protected int mHeaderBackgroundColor;
    protected int mHeaderHeight;
    protected DimensionStatus mHeaderHeightStatus;
    protected int mHeaderInsetStart;
    protected float mHeaderMaxDragRate;
    protected boolean mHeaderNeedTouchEventWhenRefreshing;
    protected int mHeaderTranslationViewId;
    protected float mHeaderTriggerRate;
    protected boolean mIsBeingDragged;
    protected RefreshKernel mKernel;
    protected long mLastOpenTime;
    protected int mLastSpinner;
    protected float mLastTouchX;
    protected float mLastTouchY;
    protected OnLoadMoreListener mLoadMoreListener;
    protected boolean mManualFooterTranslationContent;
    protected boolean mManualHeaderTranslationContent;
    protected boolean mManualLoadMore;
    protected int mMaximumVelocity;
    protected int mMinimumVelocity;
    protected NestedScrollingChildHelper mNestedChild;
    protected boolean mNestedInProgress;
    protected NestedScrollingParentHelper mNestedParent;
    protected OnMultiPurposeListener mOnMultiPurposeListener;
    protected Paint mPaint;
    protected int[] mParentOffsetInWindow;
    protected int[] mPrimaryColors;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected RefreshContent mRefreshContent;
    protected RefreshInternal mRefreshFooter;
    protected RefreshInternal mRefreshHeader;
    protected OnRefreshListener mRefreshListener;
    protected int mScreenHeightPixels;
    protected ScrollBoundaryDecider mScrollBoundaryDecider;
    protected Scroller mScroller;
    protected int mSpinner;
    protected RefreshState mState;
    protected boolean mSuperDispatchTouchEvent;
    protected int mTotalUnconsumed;
    protected int mTouchSlop;
    protected int mTouchSpinner;
    protected float mTouchX;
    protected float mTouchY;
    protected VelocityTracker mVelocityTracker;
    protected boolean mVerticalPermit;
    protected RefreshState mViceState;
    protected ValueAnimator reboundAnimator;

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    @NonNull
    public ViewGroup getLayout() {
        return this;
    }

    public SmartRefreshLayout(Context context) {
        this(context, null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFloorDuration = 250;
        this.mReboundDuration = 250;
        this.mDragRate = 0.5f;
        this.mDragDirection = 'n';
        this.mFixedHeaderViewId = -1;
        this.mFixedFooterViewId = -1;
        this.mHeaderTranslationViewId = -1;
        this.mFooterTranslationViewId = -1;
        this.mEnableRefresh = true;
        this.mEnableLoadMore = false;
        this.mEnableClipHeaderWhenFixedBehind = true;
        this.mEnableClipFooterWhenFixedBehind = true;
        this.mEnableHeaderTranslationContent = true;
        this.mEnableFooterTranslationContent = true;
        this.mEnableFooterFollowWhenNoMoreData = false;
        this.mEnablePreviewInEditMode = true;
        this.mEnableOverScrollBounce = true;
        this.mEnableOverScrollDrag = false;
        this.mEnableAutoLoadMore = true;
        this.mEnablePureScrollMode = false;
        this.mEnableScrollContentWhenLoaded = true;
        this.mEnableScrollContentWhenRefreshed = true;
        this.mEnableLoadMoreWhenContentNotFull = true;
        this.mEnableNestedScrolling = true;
        this.mDisableContentWhenRefresh = false;
        this.mDisableContentWhenLoading = false;
        this.mFooterNoMoreData = false;
        this.mFooterNoMoreDataEffective = false;
        this.mManualLoadMore = false;
        this.mManualHeaderTranslationContent = false;
        this.mManualFooterTranslationContent = false;
        this.mParentOffsetInWindow = new int[2];
        this.mNestedChild = new NestedScrollingChildHelper(this);
        this.mNestedParent = new NestedScrollingParentHelper(this);
        this.mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mHeaderMaxDragRate = 2.5f;
        this.mFooterMaxDragRate = 2.5f;
        this.mHeaderTriggerRate = 1.0f;
        this.mFooterTriggerRate = 1.0f;
        this.mKernel = new RefreshKernelImpl();
        this.mState = RefreshState.None;
        this.mViceState = RefreshState.None;
        this.mLastOpenTime = 0L;
        this.mHeaderBackgroundColor = 0;
        this.mFooterBackgroundColor = 0;
        this.mFooterLocked = false;
        this.mVerticalPermit = false;
        this.mFalsifyEvent = null;
        super.setClipToPadding(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mHandler = new Handler();
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mScreenHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        this.mReboundInterpolator = new SmartUtil();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mFooterHeight = SmartUtil.dp2px(60.0f);
        this.mHeaderHeight = SmartUtil.dp2px(100.0f);
        DefaultRefreshInitializer defaultRefreshInitializer = sRefreshInitializer;
        if (defaultRefreshInitializer != null) {
            defaultRefreshInitializer.initialize(context, this);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6929R.styleable.SmartRefreshLayout);
        this.mDragRate = obtainStyledAttributes.getFloat(C6929R.styleable.SmartRefreshLayout_srlDragRate, this.mDragRate);
        this.mHeaderMaxDragRate = obtainStyledAttributes.getFloat(C6929R.styleable.SmartRefreshLayout_srlHeaderMaxDragRate, this.mHeaderMaxDragRate);
        this.mFooterMaxDragRate = obtainStyledAttributes.getFloat(C6929R.styleable.SmartRefreshLayout_srlFooterMaxDragRate, this.mFooterMaxDragRate);
        this.mHeaderTriggerRate = obtainStyledAttributes.getFloat(C6929R.styleable.SmartRefreshLayout_srlHeaderTriggerRate, this.mHeaderTriggerRate);
        this.mFooterTriggerRate = obtainStyledAttributes.getFloat(C6929R.styleable.SmartRefreshLayout_srlFooterTriggerRate, this.mFooterTriggerRate);
        this.mEnableRefresh = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableRefresh, this.mEnableRefresh);
        this.mReboundDuration = obtainStyledAttributes.getInt(C6929R.styleable.SmartRefreshLayout_srlReboundDuration, this.mReboundDuration);
        this.mEnableLoadMore = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableLoadMore, this.mEnableLoadMore);
        this.mHeaderHeight = obtainStyledAttributes.getDimensionPixelOffset(C6929R.styleable.SmartRefreshLayout_srlHeaderHeight, this.mHeaderHeight);
        this.mFooterHeight = obtainStyledAttributes.getDimensionPixelOffset(C6929R.styleable.SmartRefreshLayout_srlFooterHeight, this.mFooterHeight);
        this.mHeaderInsetStart = obtainStyledAttributes.getDimensionPixelOffset(C6929R.styleable.SmartRefreshLayout_srlHeaderInsetStart, this.mHeaderInsetStart);
        this.mFooterInsetStart = obtainStyledAttributes.getDimensionPixelOffset(C6929R.styleable.SmartRefreshLayout_srlFooterInsetStart, this.mFooterInsetStart);
        this.mDisableContentWhenRefresh = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlDisableContentWhenRefresh, this.mDisableContentWhenRefresh);
        this.mDisableContentWhenLoading = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlDisableContentWhenLoading, this.mDisableContentWhenLoading);
        this.mEnableHeaderTranslationContent = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent, this.mEnableHeaderTranslationContent);
        this.mEnableFooterTranslationContent = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent, this.mEnableFooterTranslationContent);
        this.mEnablePreviewInEditMode = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnablePreviewInEditMode, this.mEnablePreviewInEditMode);
        this.mEnableAutoLoadMore = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableAutoLoadMore, this.mEnableAutoLoadMore);
        this.mEnableOverScrollBounce = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableOverScrollBounce, this.mEnableOverScrollBounce);
        this.mEnablePureScrollMode = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnablePureScrollMode, this.mEnablePureScrollMode);
        this.mEnableScrollContentWhenLoaded = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, this.mEnableScrollContentWhenLoaded);
        this.mEnableScrollContentWhenRefreshed = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, this.mEnableScrollContentWhenRefreshed);
        this.mEnableLoadMoreWhenContentNotFull = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableLoadMoreWhenContentNotFull, this.mEnableLoadMoreWhenContentNotFull);
        this.mEnableFooterFollowWhenNoMoreData = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableFooterFollowWhenNoMoreData = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenNoMoreData, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableClipHeaderWhenFixedBehind = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableClipHeaderWhenFixedBehind, this.mEnableClipHeaderWhenFixedBehind);
        this.mEnableClipFooterWhenFixedBehind = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableClipFooterWhenFixedBehind, this.mEnableClipFooterWhenFixedBehind);
        this.mEnableOverScrollDrag = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag, this.mEnableOverScrollDrag);
        this.mFixedHeaderViewId = obtainStyledAttributes.getResourceId(C6929R.styleable.SmartRefreshLayout_srlFixedHeaderViewId, this.mFixedHeaderViewId);
        this.mFixedFooterViewId = obtainStyledAttributes.getResourceId(C6929R.styleable.SmartRefreshLayout_srlFixedFooterViewId, this.mFixedFooterViewId);
        this.mHeaderTranslationViewId = obtainStyledAttributes.getResourceId(C6929R.styleable.SmartRefreshLayout_srlHeaderTranslationViewId, this.mHeaderTranslationViewId);
        this.mFooterTranslationViewId = obtainStyledAttributes.getResourceId(C6929R.styleable.SmartRefreshLayout_srlFooterTranslationViewId, this.mFooterTranslationViewId);
        this.mEnableNestedScrolling = obtainStyledAttributes.getBoolean(C6929R.styleable.SmartRefreshLayout_srlEnableNestedScrolling, this.mEnableNestedScrolling);
        this.mNestedChild.setNestedScrollingEnabled(this.mEnableNestedScrolling);
        this.mManualLoadMore = this.mManualLoadMore || obtainStyledAttributes.hasValue(C6929R.styleable.SmartRefreshLayout_srlEnableLoadMore);
        this.mManualHeaderTranslationContent = this.mManualHeaderTranslationContent || obtainStyledAttributes.hasValue(C6929R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent);
        this.mManualFooterTranslationContent = this.mManualFooterTranslationContent || obtainStyledAttributes.hasValue(C6929R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent);
        this.mHeaderHeightStatus = obtainStyledAttributes.hasValue(C6929R.styleable.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mHeaderHeightStatus;
        this.mFooterHeightStatus = obtainStyledAttributes.hasValue(C6929R.styleable.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mFooterHeightStatus;
        int color = obtainStyledAttributes.getColor(C6929R.styleable.SmartRefreshLayout_srlAccentColor, 0);
        int color2 = obtainStyledAttributes.getColor(C6929R.styleable.SmartRefreshLayout_srlPrimaryColor, 0);
        if (color2 != 0) {
            if (color != 0) {
                this.mPrimaryColors = new int[]{color2, color};
            } else {
                this.mPrimaryColors = new int[]{color2};
            }
        } else if (color != 0) {
            this.mPrimaryColors = new int[]{0, color};
        }
        if (this.mEnablePureScrollMode && !this.mManualLoadMore && !this.mEnableLoadMore) {
            this.mEnableLoadMore = true;
        }
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onFinishInflate() {
        /*
            r11 = this;
            super.onFinishInflate()
            int r0 = super.getChildCount()
            r1 = 3
            if (r0 > r1) goto L9f
            r2 = -1
            r3 = 0
            r5 = r2
            r4 = r3
            r6 = r4
        Lf:
            r7 = 2
            r8 = 1
            if (r4 >= r0) goto L33
            android.view.View r9 = super.getChildAt(r4)
            boolean r10 = com.scwang.smartrefresh.layout.util.SmartUtil.isContentView(r9)
            if (r10 == 0) goto L24
            if (r6 < r7) goto L21
            if (r4 != r8) goto L24
        L21:
            r5 = r4
            r6 = r7
            goto L30
        L24:
            boolean r7 = r9 instanceof com.scwang.smartrefresh.layout.api.RefreshInternal
            if (r7 != 0) goto L30
            if (r6 >= r8) goto L30
            if (r4 <= 0) goto L2d
            goto L2e
        L2d:
            r8 = r3
        L2e:
            r5 = r4
            r6 = r8
        L30:
            int r4 = r4 + 1
            goto Lf
        L33:
            if (r5 < 0) goto L4e
            com.scwang.smartrefresh.layout.impl.RefreshContentWrapper r4 = new com.scwang.smartrefresh.layout.impl.RefreshContentWrapper
            android.view.View r6 = super.getChildAt(r5)
            r4.<init>(r6)
            r11.mRefreshContent = r4
            if (r5 != r8) goto L49
            if (r0 != r1) goto L46
            r1 = r3
            goto L50
        L46:
            r7 = r2
            r1 = r3
            goto L50
        L49:
            if (r0 != r7) goto L4e
            r1 = r2
            r7 = r8
            goto L50
        L4e:
            r1 = r2
            r7 = r1
        L50:
            r4 = r3
        L51:
            if (r4 >= r0) goto L9e
            android.view.View r5 = super.getChildAt(r4)
            if (r4 == r1) goto L8c
            if (r4 == r7) goto L66
            if (r1 != r2) goto L66
            com.scwang.smartrefresh.layout.api.RefreshInternal r6 = r11.mRefreshHeader
            if (r6 != 0) goto L66
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
            if (r6 == 0) goto L66
            goto L8c
        L66:
            if (r4 == r7) goto L6e
            if (r7 != r2) goto L9b
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
            if (r6 == 0) goto L9b
        L6e:
            boolean r6 = r11.mEnableLoadMore
            if (r6 != 0) goto L79
            boolean r6 = r11.mManualLoadMore
            if (r6 != 0) goto L77
            goto L79
        L77:
            r6 = r3
            goto L7a
        L79:
            r6 = r8
        L7a:
            r11.mEnableLoadMore = r6
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
            if (r6 == 0) goto L83
            com.scwang.smartrefresh.layout.api.RefreshFooter r5 = (com.scwang.smartrefresh.layout.api.RefreshFooter) r5
            goto L89
        L83:
            com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper r6 = new com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper
            r6.<init>(r5)
            r5 = r6
        L89:
            r11.mRefreshFooter = r5
            goto L9b
        L8c:
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
            if (r6 == 0) goto L93
            com.scwang.smartrefresh.layout.api.RefreshHeader r5 = (com.scwang.smartrefresh.layout.api.RefreshHeader) r5
            goto L99
        L93:
            com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper r6 = new com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper
            r6.<init>(r5)
            r5 = r6
        L99:
            r11.mRefreshHeader = r5
        L9b:
            int r4 = r4 + 1
            goto L51
        L9e:
            return
        L9f:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "最多只支持3个子View，Most only support three sub view"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.onFinishInflate():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        RefreshInternal refreshInternal;
        super.onAttachedToWindow();
        boolean z = true;
        this.mAttachedToWindow = true;
        if (!isInEditMode()) {
            if (this.mRefreshHeader == null) {
                DefaultRefreshHeaderCreator defaultRefreshHeaderCreator = sHeaderCreator;
                if (defaultRefreshHeaderCreator != null) {
                    setRefreshHeader(defaultRefreshHeaderCreator.createRefreshHeader(getContext(), this));
                } else {
                    setRefreshHeader(new BezierRadarHeader(getContext()));
                }
            }
            if (this.mRefreshFooter == null) {
                DefaultRefreshFooterCreator defaultRefreshFooterCreator = sFooterCreator;
                if (defaultRefreshFooterCreator != null) {
                    setRefreshFooter(defaultRefreshFooterCreator.createRefreshFooter(getContext(), this));
                } else {
                    boolean z2 = this.mEnableLoadMore;
                    setRefreshFooter(new BallPulseFooter(getContext()));
                    this.mEnableLoadMore = z2;
                }
            } else {
                if (!this.mEnableLoadMore && this.mManualLoadMore) {
                    z = false;
                }
                this.mEnableLoadMore = z;
            }
            if (this.mRefreshContent == null) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    RefreshInternal refreshInternal2 = this.mRefreshHeader;
                    if ((refreshInternal2 == null || childAt != refreshInternal2.getView()) && ((refreshInternal = this.mRefreshFooter) == null || childAt != refreshInternal.getView())) {
                        this.mRefreshContent = new RefreshContentWrapper(childAt);
                    }
                }
            }
            if (this.mRefreshContent == null) {
                int dp2px = SmartUtil.dp2px(20.0f);
                TextView textView = new TextView(getContext());
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText(C6929R.string.srl_content_empty);
                super.addView(textView, -1, -1);
                this.mRefreshContent = new RefreshContentWrapper(textView);
                this.mRefreshContent.getView().setPadding(dp2px, dp2px, dp2px, dp2px);
            }
            int i2 = this.mFixedHeaderViewId;
            View findViewById = i2 > 0 ? findViewById(i2) : null;
            int i3 = this.mFixedFooterViewId;
            View findViewById2 = i3 > 0 ? findViewById(i3) : null;
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, findViewById, findViewById2);
            if (this.mSpinner != 0) {
                notifyStateChanged(RefreshState.None);
                RefreshContent refreshContent = this.mRefreshContent;
                this.mSpinner = 0;
                refreshContent.moveSpinner(0, this.mHeaderTranslationViewId, this.mFooterTranslationViewId);
            }
        }
        int[] iArr = this.mPrimaryColors;
        if (iArr != null) {
            RefreshInternal refreshInternal3 = this.mRefreshHeader;
            if (refreshInternal3 != null) {
                refreshInternal3.setPrimaryColors(iArr);
            }
            RefreshInternal refreshInternal4 = this.mRefreshFooter;
            if (refreshInternal4 != null) {
                refreshInternal4.setPrimaryColors(this.mPrimaryColors);
            }
        }
        RefreshContent refreshContent2 = this.mRefreshContent;
        if (refreshContent2 != null) {
            super.bringChildToFront(refreshContent2.getView());
        }
        RefreshInternal refreshInternal5 = this.mRefreshHeader;
        if (refreshInternal5 != null && refreshInternal5.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
            super.bringChildToFront(this.mRefreshHeader.getView());
        }
        RefreshInternal refreshInternal6 = this.mRefreshFooter;
        if (refreshInternal6 == null || refreshInternal6.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            return;
        }
        super.bringChildToFront(this.mRefreshFooter.getView());
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z = isInEditMode() && this.mEnablePreviewInEditMode;
        int childCount = super.getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = super.getChildAt(i8);
            if (childAt.getVisibility() != 8 && childAt.getTag(C6929R.string.srl_component_falsify) != childAt) {
                RefreshInternal refreshInternal = this.mRefreshHeader;
                if (refreshInternal != null && refreshInternal.getView() == childAt) {
                    View view = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, layoutParams.width);
                    int i9 = this.mHeaderHeight;
                    if (this.mHeaderHeightStatus.ordinal < DimensionStatus.XmlLayoutUnNotify.ordinal) {
                        if (layoutParams.height > 0) {
                            i9 = layoutParams.height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                            if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mHeaderHeight = layoutParams.height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                                this.mHeaderHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                        } else if (layoutParams.height == -2 && (this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mHeaderHeightStatus.notified)) {
                            int max = Math.max((View.MeasureSpec.getSize(i2) - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0);
                            view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(max, Integer.MIN_VALUE));
                            int measuredHeight = view.getMeasuredHeight();
                            if (measuredHeight > 0) {
                                if (measuredHeight != max && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mHeaderHeight = measuredHeight + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                                    this.mHeaderHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                i9 = -1;
                            }
                        }
                    }
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        i9 = View.MeasureSpec.getSize(i2);
                        i6 = -1;
                        i5 = 0;
                    } else if (this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.Scale || z) {
                        i5 = 0;
                        i6 = -1;
                    } else {
                        i5 = 0;
                        i9 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableRefresh) ? this.mSpinner : 0);
                        i6 = -1;
                    }
                    if (i9 != i6) {
                        view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((i9 - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, i5), 1073741824));
                    }
                    if (!this.mHeaderHeightStatus.notified) {
                        this.mHeaderHeightStatus = this.mHeaderHeightStatus.notified();
                        RefreshInternal refreshInternal2 = this.mRefreshHeader;
                        RefreshKernel refreshKernel = this.mKernel;
                        int i10 = this.mHeaderHeight;
                        refreshInternal2.onInitialized(refreshKernel, i10, (int) (this.mHeaderMaxDragRate * i10));
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
                        i7 += view.getMeasuredHeight();
                    }
                }
                RefreshInternal refreshInternal3 = this.mRefreshFooter;
                if (refreshInternal3 == null || refreshInternal3.getView() != childAt) {
                    i3 = 0;
                } else {
                    View view2 = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : sDefaultMarginLP;
                    int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin, layoutParams2.width);
                    int i11 = this.mFooterHeight;
                    if (this.mFooterHeightStatus.ordinal < DimensionStatus.XmlLayoutUnNotify.ordinal) {
                        if (layoutParams2.height > 0) {
                            i11 = layoutParams2.height + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                            if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mFooterHeight = layoutParams2.height + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                                this.mFooterHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                        } else if (layoutParams2.height == -2 && (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mFooterHeightStatus.notified)) {
                            int max2 = Math.max((View.MeasureSpec.getSize(i2) - marginLayoutParams2.bottomMargin) - marginLayoutParams2.topMargin, 0);
                            view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(max2, Integer.MIN_VALUE));
                            int measuredHeight2 = view2.getMeasuredHeight();
                            if (measuredHeight2 > 0) {
                                if (measuredHeight2 != max2 && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mFooterHeight = measuredHeight2 + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                                    this.mFooterHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                i11 = -1;
                            }
                        }
                    }
                    if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        i11 = View.MeasureSpec.getSize(i2);
                        i4 = -1;
                        i3 = 0;
                    } else if (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.Scale || z) {
                        i3 = 0;
                        i4 = -1;
                    } else {
                        i3 = 0;
                        i11 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0);
                        i4 = -1;
                    }
                    if (i11 != i4) {
                        view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((i11 - marginLayoutParams2.bottomMargin) - marginLayoutParams2.topMargin, i3), 1073741824));
                    }
                    if (!this.mFooterHeightStatus.notified) {
                        this.mFooterHeightStatus = this.mFooterHeightStatus.notified();
                        RefreshInternal refreshInternal4 = this.mRefreshFooter;
                        RefreshKernel refreshKernel2 = this.mKernel;
                        int i12 = this.mFooterHeight;
                        refreshInternal4.onInitialized(refreshKernel2, i12, (int) (this.mFooterMaxDragRate * i12));
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        i7 += view2.getMeasuredHeight();
                    }
                }
                RefreshContent refreshContent = this.mRefreshContent;
                if (refreshContent != null && refreshContent.getView() == childAt) {
                    View view3 = this.mRefreshContent.getView();
                    ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : sDefaultMarginLP;
                    view3.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams3.leftMargin + marginLayoutParams3.rightMargin, layoutParams3.width), ViewGroup.getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams3.topMargin + marginLayoutParams3.bottomMargin + ((!z || ((this.mRefreshHeader == null || !isEnableRefreshOrLoadMore(this.mEnableRefresh) || !isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) ? i3 : 1) == 0) ? i3 : this.mHeaderHeight) + ((!z || ((this.mRefreshFooter == null || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || !isEnableTranslationContent(this.mEnableFooterTranslationContent, this.mRefreshFooter)) ? i3 : 1) == 0) ? i3 : this.mFooterHeight), layoutParams3.height));
                    i7 += view3.getMeasuredHeight();
                }
            }
        }
        super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), View.resolveSize(i7, i2));
        this.mLastTouchX = getMeasuredWidth() / 2.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = super.getChildAt(i5);
            if (childAt.getVisibility() != 8 && childAt.getTag(C6929R.string.srl_component_falsify) != childAt) {
                RefreshContent refreshContent = this.mRefreshContent;
                boolean z2 = true;
                if (refreshContent != null && refreshContent.getView() == childAt) {
                    boolean z3 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh) && this.mRefreshHeader != null;
                    View view = this.mRefreshContent.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int i6 = marginLayoutParams.leftMargin + paddingLeft;
                    int i7 = marginLayoutParams.topMargin + paddingTop;
                    int measuredWidth = view.getMeasuredWidth() + i6;
                    int measuredHeight = view.getMeasuredHeight() + i7;
                    if (z3 && isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) {
                        int i8 = this.mHeaderHeight;
                        i7 += i8;
                        measuredHeight += i8;
                    }
                    view.layout(i6, i7, measuredWidth, measuredHeight);
                }
                RefreshInternal refreshInternal = this.mRefreshHeader;
                if (refreshInternal != null && refreshInternal.getView() == childAt) {
                    boolean z4 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh);
                    View view2 = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : sDefaultMarginLP;
                    int i9 = marginLayoutParams2.leftMargin;
                    int i10 = marginLayoutParams2.topMargin + this.mHeaderInsetStart;
                    int measuredWidth2 = view2.getMeasuredWidth() + i9;
                    int measuredHeight2 = view2.getMeasuredHeight() + i10;
                    if (!z4 && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        int i11 = this.mHeaderHeight;
                        i10 -= i11;
                        measuredHeight2 -= i11;
                    }
                    view2.layout(i9, i10, measuredWidth2, measuredHeight2);
                }
                RefreshInternal refreshInternal2 = this.mRefreshFooter;
                if (refreshInternal2 != null && refreshInternal2.getView() == childAt) {
                    z2 = (isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) ? false : false;
                    View view3 = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : sDefaultMarginLP;
                    SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                    int i12 = marginLayoutParams3.leftMargin;
                    int measuredHeight3 = (marginLayoutParams3.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                    if (this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && this.mEnableFooterFollowWhenNoMoreData && this.mRefreshContent != null && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        View view4 = this.mRefreshContent.getView();
                        ViewGroup.LayoutParams layoutParams4 = view4.getLayoutParams();
                        measuredHeight3 = view4.getMeasuredHeight() + paddingTop + paddingTop + (layoutParams4 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin : 0);
                    }
                    if (spinnerStyle == SpinnerStyle.MatchLayout) {
                        measuredHeight3 = marginLayoutParams3.topMargin - this.mFooterInsetStart;
                    } else if (z2 || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                        measuredHeight3 -= this.mFooterHeight;
                    } else if (spinnerStyle == SpinnerStyle.Scale && this.mSpinner < 0) {
                        measuredHeight3 -= Math.max(isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0, 0);
                    }
                    view3.layout(i12, measuredHeight3, view3.getMeasuredWidth() + i12, view3.getMeasuredHeight() + measuredHeight3);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mKernel.moveSpinner(0, true);
        notifyStateChanged(RefreshState.None);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mManualLoadMore = true;
        this.animationRunnable = null;
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.reboundAnimator.removeAllUpdateListeners();
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
        this.mFooterLocked = false;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        Paint paint;
        Paint paint2;
        RefreshContent refreshContent = this.mRefreshContent;
        View view2 = refreshContent != null ? refreshContent.getView() : null;
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null && refreshInternal.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableRefresh) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int max = Math.max(view2.getTop() + view2.getPaddingTop() + this.mSpinner, view.getTop());
                int i = this.mHeaderBackgroundColor;
                if (i != 0 && (paint2 = this.mPaint) != null) {
                    paint2.setColor(i);
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale) {
                        max = view.getBottom();
                    } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        max = view.getBottom() + this.mSpinner;
                    }
                    canvas.drawRect(0.0f, view.getTop(), getWidth(), max, this.mPaint);
                }
                if (this.mEnableClipHeaderWhenFixedBehind && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), max);
                    boolean drawChild = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild;
                }
            }
        }
        RefreshInternal refreshInternal2 = this.mRefreshFooter;
        if (refreshInternal2 != null && refreshInternal2.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableLoadMore) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int min = Math.min((view2.getBottom() - view2.getPaddingBottom()) + this.mSpinner, view.getBottom());
                int i2 = this.mFooterBackgroundColor;
                if (i2 != 0 && (paint = this.mPaint) != null) {
                    paint.setColor(i2);
                    if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale) {
                        min = view.getTop();
                    } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                        min = view.getTop() + this.mSpinner;
                    }
                    canvas.drawRect(0.0f, min, getWidth(), view.getBottom(), this.mPaint);
                }
                if (this.mEnableClipFooterWhenFixedBehind && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), min, view.getRight(), view.getBottom());
                    boolean drawChild2 = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild2;
                }
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.View
    public void computeScroll() {
        float currY;
        this.mScroller.getCurrY();
        if (this.mScroller.computeScrollOffset()) {
            int finalY = this.mScroller.getFinalY();
            if ((finalY < 0 && ((this.mEnableRefresh || this.mEnableOverScrollDrag) && this.mRefreshContent.canRefresh())) || (finalY > 0 && ((this.mEnableLoadMore || this.mEnableOverScrollDrag) && this.mRefreshContent.canLoadMore()))) {
                if (this.mVerticalPermit) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        currY = finalY > 0 ? -this.mScroller.getCurrVelocity() : this.mScroller.getCurrVelocity();
                    } else {
                        currY = ((this.mScroller.getCurrY() - finalY) * 1.0f) / Math.max(this.mScroller.getDuration() - this.mScroller.timePassed(), 1);
                    }
                    animSpinnerBounce(currY);
                }
                this.mScroller.forceFinished(true);
                return;
            }
            this.mVerticalPermit = true;
            invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:221:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0320  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r23) {
        /*
            Method dump skipped, instructions count: 890
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    protected boolean startFlingIfNeed(float f) {
        if (f == 0.0f) {
            f = this.mCurrentVelocity;
        }
        if (Math.abs(f) > this.mMinimumVelocity) {
            if (this.mSpinner * f < 0.0f) {
                if (this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || (this.mSpinner < 0 && this.mFooterNoMoreData)) {
                    this.animationRunnable = new FlingRunnable(f).start();
                    return true;
                } else if (this.mState.isReleaseToOpening) {
                    return true;
                }
            }
            if ((f < 0.0f && ((this.mEnableOverScrollBounce && (this.mEnableLoadMore || this.mEnableOverScrollDrag)) || ((this.mState == RefreshState.Loading && this.mSpinner >= 0) || (this.mEnableAutoLoadMore && isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) || (f > 0.0f && ((this.mEnableOverScrollBounce && this.mEnableRefresh) || this.mEnableOverScrollDrag || (this.mState == RefreshState.Refreshing && this.mSpinner <= 0)))) {
                this.mVerticalPermit = false;
                this.mScroller.fling(0, 0, 0, (int) (-f), 0, 0, -2147483647, Integer.MAX_VALUE);
                this.mScroller.computeScrollOffset();
                invalidate();
            }
        }
        return false;
    }

    protected boolean interceptAnimatorByAction(int i) {
        if (i == 0) {
            if (this.reboundAnimator != null) {
                if (this.mState.isFinishing || this.mState == RefreshState.TwoLevelReleased) {
                    return true;
                }
                if (this.mState == RefreshState.PullDownCanceled) {
                    this.mKernel.setState(RefreshState.PullDownToRefresh);
                } else if (this.mState == RefreshState.PullUpCanceled) {
                    this.mKernel.setState(RefreshState.PullUpToLoad);
                }
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        return this.reboundAnimator != null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        this.mEnableDisallowIntercept = z;
        super.requestDisallowInterceptTouchEvent(z);
    }

    protected void notifyStateChanged(RefreshState refreshState) {
        RefreshState refreshState2 = this.mState;
        if (refreshState2 != refreshState) {
            this.mState = refreshState;
            this.mViceState = refreshState;
            RefreshInternal refreshInternal = this.mRefreshHeader;
            RefreshInternal refreshInternal2 = this.mRefreshFooter;
            OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
            if (refreshInternal != null) {
                refreshInternal.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshInternal2 != null) {
                refreshInternal2.onStateChanged(this, refreshState2, refreshState);
            }
            if (onMultiPurposeListener != null) {
                onMultiPurposeListener.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshState == RefreshState.LoadFinish) {
                this.mFooterLocked = false;
            }
        } else if (this.mViceState != refreshState2) {
            this.mViceState = refreshState2;
        }
    }

    protected void setStateDirectLoading(boolean z) {
        if (this.mState != RefreshState.Loading) {
            this.mLastOpenTime = System.currentTimeMillis();
            this.mFooterLocked = true;
            notifyStateChanged(RefreshState.Loading);
            OnLoadMoreListener onLoadMoreListener = this.mLoadMoreListener;
            if (onLoadMoreListener != null) {
                if (z) {
                    onLoadMoreListener.onLoadMore(this);
                }
            } else if (this.mOnMultiPurposeListener == null) {
                finishLoadMore(2000);
            }
            RefreshInternal refreshInternal = this.mRefreshFooter;
            if (refreshInternal != null) {
                int i = this.mFooterHeight;
                refreshInternal.onStartAnimator(this, i, (int) (this.mFooterMaxDragRate * i));
            }
            OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
            if (onMultiPurposeListener == null || !(this.mRefreshFooter instanceof RefreshFooter)) {
                return;
            }
            if (z) {
                onMultiPurposeListener.onLoadMore(this);
            }
            int i2 = this.mFooterHeight;
            this.mOnMultiPurposeListener.onFooterStartAnimator((RefreshFooter) this.mRefreshFooter, i2, (int) (this.mFooterMaxDragRate * i2));
        }
    }

    protected void setStateLoading(final boolean z) {
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.setStateDirectLoading(z);
            }
        };
        notifyStateChanged(RefreshState.LoadReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(-this.mFooterHeight);
        if (animSpinner != null) {
            animSpinner.addListener(animatorListenerAdapter);
        }
        RefreshInternal refreshInternal = this.mRefreshFooter;
        if (refreshInternal != null) {
            int i = this.mFooterHeight;
            refreshInternal.onReleased(this, i, (int) (this.mFooterMaxDragRate * i));
        }
        OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
        if (onMultiPurposeListener != null) {
            RefreshInternal refreshInternal2 = this.mRefreshFooter;
            if (refreshInternal2 instanceof RefreshFooter) {
                int i2 = this.mFooterHeight;
                onMultiPurposeListener.onFooterReleased((RefreshFooter) refreshInternal2, i2, (int) (this.mFooterMaxDragRate * i2));
            }
        }
        if (animSpinner == null) {
            animatorListenerAdapter.onAnimationEnd(null);
        }
    }

    protected void setStateRefreshing(final boolean z) {
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.mLastOpenTime = System.currentTimeMillis();
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.Refreshing);
                if (SmartRefreshLayout.this.mRefreshListener != null) {
                    if (z) {
                        SmartRefreshLayout.this.mRefreshListener.onRefresh(SmartRefreshLayout.this);
                    }
                } else if (SmartRefreshLayout.this.mOnMultiPurposeListener == null) {
                    SmartRefreshLayout.this.finishRefresh(3000);
                }
                if (SmartRefreshLayout.this.mRefreshHeader != null) {
                    RefreshInternal refreshInternal = SmartRefreshLayout.this.mRefreshHeader;
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    refreshInternal.onStartAnimator(smartRefreshLayout, smartRefreshLayout.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * SmartRefreshLayout.this.mHeaderHeight));
                }
                if (SmartRefreshLayout.this.mOnMultiPurposeListener == null || !(SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                    return;
                }
                if (z) {
                    SmartRefreshLayout.this.mOnMultiPurposeListener.onRefresh(SmartRefreshLayout.this);
                }
                SmartRefreshLayout.this.mOnMultiPurposeListener.onHeaderStartAnimator((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * SmartRefreshLayout.this.mHeaderHeight));
            }
        };
        notifyStateChanged(RefreshState.RefreshReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(this.mHeaderHeight);
        if (animSpinner != null) {
            animSpinner.addListener(animatorListenerAdapter);
        }
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null) {
            int i = this.mHeaderHeight;
            refreshInternal.onReleased(this, i, (int) (this.mHeaderMaxDragRate * i));
        }
        OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
        if (onMultiPurposeListener != null) {
            RefreshInternal refreshInternal2 = this.mRefreshHeader;
            if (refreshInternal2 instanceof RefreshHeader) {
                int i2 = this.mHeaderHeight;
                onMultiPurposeListener.onHeaderReleased((RefreshHeader) refreshInternal2, i2, (int) (this.mHeaderMaxDragRate * i2));
            }
        }
        if (animSpinner == null) {
            animatorListenerAdapter.onAnimationEnd(null);
        }
    }

    protected void setViceState(RefreshState refreshState) {
        if (this.mState.isDragging && this.mState.isHeader != refreshState.isHeader) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mViceState != refreshState) {
            this.mViceState = refreshState;
        }
    }

    protected boolean isEnableTranslationContent(boolean z, RefreshInternal refreshInternal) {
        return z || this.mEnablePureScrollMode || refreshInternal == null || refreshInternal.getSpinnerStyle() == SpinnerStyle.FixedBehind;
    }

    protected boolean isEnableRefreshOrLoadMore(boolean z) {
        return z && !this.mEnablePureScrollMode;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class FlingRunnable implements Runnable {
        int mOffset;
        float mVelocity;
        int mFrame = 0;
        int mFrameDelay = 10;
        float mDamping = 0.98f;
        long mStartTime = 0;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();

        FlingRunnable(float f) {
            this.mVelocity = f;
            this.mOffset = SmartRefreshLayout.this.mSpinner;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
            if (r0.isEnableRefreshOrLoadMore(r0.mEnableLoadMore) != false) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0056, code lost:
            if (r0.isEnableRefreshOrLoadMore(r0.mEnableLoadMore) != false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
            if (r10.this$0.mSpinner >= (-r10.this$0.mFooterHeight)) goto L46;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0073, code lost:
            if (r10.this$0.mSpinner > r10.this$0.mHeaderHeight) goto L17;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Runnable start() {
            /*
                Method dump skipped, instructions count: 229
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.FlingRunnable.start():java.lang.Runnable");
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SmartRefreshLayout.this.animationRunnable != this || SmartRefreshLayout.this.mState.isFinishing) {
                return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mVelocity = (float) (this.mVelocity * Math.pow(this.mDamping, ((float) (currentAnimationTimeMillis - this.mStartTime)) / (1000.0f / this.mFrameDelay)));
            float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
            if (Math.abs(f) > 1.0f) {
                this.mLastTime = currentAnimationTimeMillis;
                this.mOffset = (int) (this.mOffset + f);
                if (SmartRefreshLayout.this.mSpinner * this.mOffset > 0) {
                    SmartRefreshLayout.this.mKernel.moveSpinner(this.mOffset, true);
                    SmartRefreshLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
                    return;
                }
                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                smartRefreshLayout.animationRunnable = null;
                smartRefreshLayout.mKernel.moveSpinner(0, true);
                SmartUtil.fling(SmartRefreshLayout.this.mRefreshContent.getScrollableView(), (int) (-this.mVelocity));
                if (!SmartRefreshLayout.this.mFooterLocked || f <= 0.0f) {
                    return;
                }
                SmartRefreshLayout.this.mFooterLocked = false;
                return;
            }
            SmartRefreshLayout.this.animationRunnable = null;
        }
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
            SmartRefreshLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
            if (f > 0.0f) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
            } else {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            int i3;
            if (SmartRefreshLayout.this.animationRunnable != this || SmartRefreshLayout.this.mState.isFinishing) {
                return;
            }
            if (Math.abs(SmartRefreshLayout.this.mSpinner) >= Math.abs(this.mSmoothDistance)) {
                if (this.mSmoothDistance != 0) {
                    this.mFrame = this.mFrame + 1;
                    this.mVelocity = (float) (this.mVelocity * Math.pow(0.44999998807907104d, i3 * 2));
                } else {
                    this.mFrame = this.mFrame + 1;
                    this.mVelocity = (float) (this.mVelocity * Math.pow(0.8500000238418579d, i2 * 2));
                }
            } else {
                this.mFrame = this.mFrame + 1;
                this.mVelocity = (float) (this.mVelocity * Math.pow(0.949999988079071d, i * 2));
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
            if (Math.abs(f) >= 1.0f) {
                this.mLastTime = currentAnimationTimeMillis;
                this.mOffset += f;
                SmartRefreshLayout.this.moveSpinnerInfinitely(this.mOffset);
                SmartRefreshLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
                return;
            }
            if (SmartRefreshLayout.this.mViceState.isDragging && SmartRefreshLayout.this.mViceState.isHeader) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownCanceled);
            } else if (SmartRefreshLayout.this.mViceState.isDragging && SmartRefreshLayout.this.mViceState.isFooter) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpCanceled);
            }
            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
            smartRefreshLayout.animationRunnable = null;
            if (Math.abs(smartRefreshLayout.mSpinner) >= Math.abs(this.mSmoothDistance)) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.animSpinner(this.mSmoothDistance, 0, smartRefreshLayout2.mReboundInterpolator, Math.min(Math.max((int) SmartUtil.px2dp(Math.abs(SmartRefreshLayout.this.mSpinner - this.mSmoothDistance)), 30), 100) * 10);
            }
        }
    }

    protected ValueAnimator animSpinner(int i, int i2, Interpolator interpolator, int i3) {
        if (this.mSpinner != i) {
            ValueAnimator valueAnimator = this.reboundAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.animationRunnable = null;
            this.reboundAnimator = ValueAnimator.ofInt(this.mSpinner, i);
            this.reboundAnimator.setDuration(i3);
            this.reboundAnimator.setInterpolator(interpolator);
            this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.reboundAnimator = null;
                    if (smartRefreshLayout.mSpinner == 0 && SmartRefreshLayout.this.mState != RefreshState.None && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isDragging) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                    } else if (SmartRefreshLayout.this.mState != SmartRefreshLayout.this.mViceState) {
                        SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                        smartRefreshLayout2.setViceState(smartRefreshLayout2.mState);
                    }
                }
            });
            this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator2.getAnimatedValue()).intValue(), false);
                }
            });
            this.reboundAnimator.setStartDelay(i2);
            this.reboundAnimator.start();
            return this.reboundAnimator;
        }
        return null;
    }

    protected void animSpinnerBounce(float f) {
        if (this.reboundAnimator == null) {
            if (f > 0.0f && (this.mState == RefreshState.Refreshing || this.mState == RefreshState.TwoLevel)) {
                this.animationRunnable = new BounceRunnable(f, this.mHeaderHeight);
            } else if (f < 0.0f && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && this.mState != RefreshState.Refreshing)))) {
                this.animationRunnable = new BounceRunnable(f, -this.mFooterHeight);
            } else if (this.mSpinner == 0 && this.mEnableOverScrollBounce) {
                this.animationRunnable = new BounceRunnable(f, 0);
            }
        }
    }

    protected void overSpinner() {
        if (this.mState == RefreshState.TwoLevel) {
            if (this.mCurrentVelocity > -1000 && this.mSpinner > getMeasuredHeight() / 2) {
                ValueAnimator animSpinner = this.mKernel.animSpinner(getMeasuredHeight());
                if (animSpinner != null) {
                    animSpinner.setDuration(this.mFloorDuration);
                }
            } else if (this.mIsBeingDragged) {
                this.mKernel.finishTwoLevel();
            }
        } else if (this.mState == RefreshState.Loading || (this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && this.mSpinner < 0 && isEnableRefreshOrLoadMore(this.mEnableLoadMore))) {
            int i = this.mSpinner;
            int i2 = this.mFooterHeight;
            if (i < (-i2)) {
                this.mKernel.animSpinner(-i2);
            } else if (i > 0) {
                this.mKernel.animSpinner(0);
            }
        } else if (this.mState == RefreshState.Refreshing) {
            int i3 = this.mSpinner;
            int i4 = this.mHeaderHeight;
            if (i3 > i4) {
                this.mKernel.animSpinner(i4);
            } else if (i3 < 0) {
                this.mKernel.animSpinner(0);
            }
        } else if (this.mState == RefreshState.PullDownToRefresh) {
            this.mKernel.setState(RefreshState.PullDownCanceled);
        } else if (this.mState == RefreshState.PullUpToLoad) {
            this.mKernel.setState(RefreshState.PullUpCanceled);
        } else if (this.mState == RefreshState.ReleaseToRefresh) {
            this.mKernel.setState(RefreshState.Refreshing);
        } else if (this.mState == RefreshState.ReleaseToLoad) {
            this.mKernel.setState(RefreshState.Loading);
        } else if (this.mState == RefreshState.ReleaseToTwoLevel) {
            this.mKernel.setState(RefreshState.TwoLevelReleased);
        } else if (this.mState == RefreshState.RefreshReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(this.mHeaderHeight);
            }
        } else if (this.mState == RefreshState.LoadReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(-this.mFooterHeight);
            }
        } else if (this.mSpinner != 0) {
            this.mKernel.animSpinner(0);
        }
    }

    protected void moveSpinnerInfinitely(float f) {
        float f2;
        boolean z;
        float f3 = (!this.mNestedInProgress || this.mEnableLoadMoreWhenContentNotFull || f >= 0.0f || this.mRefreshContent.canLoadMore()) ? f : 0.0f;
        if (f3 > this.mScreenHeightPixels * 3 && getTag() == null) {
            Toast.makeText(getContext(), "你这么死拉，臣妾做不到啊！", 0).show();
            setTag("你这么死拉，臣妾做不到啊！");
        }
        if (this.mState == RefreshState.TwoLevel && f3 > 0.0f) {
            this.mKernel.moveSpinner(Math.min((int) f3, getMeasuredHeight()), true);
        } else if (this.mState != RefreshState.Refreshing || f3 < 0.0f) {
            if (f3 < 0.0f) {
                if (this.mState != RefreshState.Loading && (!this.mEnableFooterFollowWhenNoMoreData || !this.mFooterNoMoreData || !this.mFooterNoMoreDataEffective || !isEnableRefreshOrLoadMore(this.mEnableLoadMore))) {
                    if (!this.mEnableAutoLoadMore || this.mFooterNoMoreData) {
                        f2 = 0.0f;
                    } else if (!isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        f2 = 0.0f;
                    }
                }
                int i = this.mFooterHeight;
                if (f3 > (-i)) {
                    this.mKernel.moveSpinner((int) f3, true);
                } else {
                    double d = (this.mFooterMaxDragRate - 1.0f) * i;
                    int max = Math.max((this.mScreenHeightPixels * 4) / 3, getHeight());
                    int i2 = this.mFooterHeight;
                    double d2 = max - i2;
                    double d3 = -Math.min(0.0f, (i2 + f3) * this.mDragRate);
                    double d4 = -d3;
                    if (d2 == 0.0d) {
                        d2 = 1.0d;
                    }
                    this.mKernel.moveSpinner(((int) (-Math.min(d * (1.0d - Math.pow(100.0d, d4 / d2)), d3))) - this.mFooterHeight, true);
                }
            } else {
                f2 = 0.0f;
            }
            if (f3 >= f2) {
                double d5 = this.mHeaderMaxDragRate * this.mHeaderHeight;
                double max2 = Math.max(this.mScreenHeightPixels / 2, getHeight());
                double max3 = Math.max(0.0f, this.mDragRate * f3);
                double d6 = -max3;
                if (max2 == 0.0d) {
                    max2 = 1.0d;
                }
                this.mKernel.moveSpinner((int) Math.min(d5 * (1.0d - Math.pow(100.0d, d6 / max2)), max3), true);
            } else {
                double d7 = this.mFooterMaxDragRate * this.mFooterHeight;
                double max4 = Math.max(this.mScreenHeightPixels / 2, getHeight());
                double d8 = -Math.min(0.0f, this.mDragRate * f3);
                double d9 = -d8;
                if (max4 == 0.0d) {
                    max4 = 1.0d;
                }
                this.mKernel.moveSpinner((int) (-Math.min(d7 * (1.0d - Math.pow(100.0d, d9 / max4)), d8)), true);
            }
        } else {
            int i3 = this.mHeaderHeight;
            if (f3 < i3) {
                this.mKernel.moveSpinner((int) f3, true);
            } else {
                double d10 = (this.mHeaderMaxDragRate - 1.0f) * i3;
                int max5 = Math.max((this.mScreenHeightPixels * 4) / 3, getHeight());
                int i4 = this.mHeaderHeight;
                double d11 = max5 - i4;
                double max6 = Math.max(0.0f, (f3 - i4) * this.mDragRate);
                double d12 = -max6;
                if (d11 == 0.0d) {
                    d11 = 1.0d;
                }
                this.mKernel.moveSpinner(((int) Math.min(d10 * (1.0d - Math.pow(100.0d, d12 / d11)), max6)) + this.mHeaderHeight, true);
            }
        }
        if (!this.mEnableAutoLoadMore || this.mFooterNoMoreData || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || f3 >= 0.0f || this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || this.mState == RefreshState.LoadFinish) {
            return;
        }
        if (this.mDisableContentWhenLoading) {
            this.animationRunnable = null;
            this.mKernel.animSpinner(-this.mFooterHeight);
            z = false;
        } else {
            z = false;
        }
        setStateDirectLoading(z);
        this.mHandler.postDelayed(new Runnable() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.5
            @Override // java.lang.Runnable
            public void run() {
                if (SmartRefreshLayout.this.mLoadMoreListener != null) {
                    SmartRefreshLayout.this.mLoadMoreListener.onLoadMore(SmartRefreshLayout.this);
                } else if (SmartRefreshLayout.this.mOnMultiPurposeListener == null) {
                    SmartRefreshLayout.this.finishLoadMore(2000);
                }
                OnMultiPurposeListener onMultiPurposeListener = SmartRefreshLayout.this.mOnMultiPurposeListener;
                if (onMultiPurposeListener != null) {
                    onMultiPurposeListener.onLoadMore(SmartRefreshLayout.this);
                }
            }
        }, this.mReboundDuration);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int backgroundColor;
        public SpinnerStyle spinnerStyle;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.backgroundColor = 0;
            this.spinnerStyle = null;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6929R.styleable.SmartRefreshLayout_Layout);
            this.backgroundColor = obtainStyledAttributes.getColor(C6929R.styleable.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.backgroundColor);
            if (obtainStyledAttributes.hasValue(C6929R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.spinnerStyle = SpinnerStyle.values[obtainStyledAttributes.getInt(C6929R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal)];
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.backgroundColor = 0;
            this.spinnerStyle = null;
        }
    }

    @Override // android.view.ViewGroup, android.support.p083v4.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedParent.getNestedScrollAxes();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        return (isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) && (this.mEnableOverScrollDrag || this.mEnableRefresh || this.mEnableLoadMore);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.mNestedParent.onNestedScrollAccepted(view, view2, i);
        this.mNestedChild.startNestedScroll(i & 2);
        this.mTotalUnconsumed = this.mSpinner;
        this.mNestedInProgress = true;
        interceptAnimatorByAction(0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        int i3;
        int i4 = this.mTotalUnconsumed;
        if (i2 * i4 > 0) {
            if (Math.abs(i2) > Math.abs(this.mTotalUnconsumed)) {
                i3 = this.mTotalUnconsumed;
                this.mTotalUnconsumed = 0;
            } else {
                this.mTotalUnconsumed -= i2;
                i3 = i2;
            }
            moveSpinnerInfinitely(this.mTotalUnconsumed);
        } else if (i2 <= 0 || !this.mFooterLocked) {
            i3 = 0;
        } else {
            this.mTotalUnconsumed = i4 - i2;
            moveSpinnerInfinitely(this.mTotalUnconsumed);
            i3 = i2;
        }
        this.mNestedChild.dispatchNestedPreScroll(i, i2 - i3, iArr, null);
        iArr[1] = iArr[1] + i3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        this.mNestedChild.dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        int i5 = i4 + this.mParentOffsetInWindow[1];
        if ((i5 < 0 && (this.mEnableRefresh || this.mEnableOverScrollDrag)) || (i5 > 0 && (this.mEnableLoadMore || this.mEnableOverScrollDrag))) {
            if (this.mViceState == RefreshState.None || this.mViceState.isOpening) {
                this.mKernel.setState(i5 > 0 ? RefreshState.PullUpToLoad : RefreshState.PullDownToRefresh);
            }
            int i6 = this.mTotalUnconsumed - i5;
            this.mTotalUnconsumed = i6;
            moveSpinnerInfinitely(i6);
        }
        if (!this.mFooterLocked || i2 >= 0) {
            return;
        }
        this.mFooterLocked = false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return (this.mFooterLocked && f2 > 0.0f) || startFlingIfNeed(-f2) || this.mNestedChild.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return this.mNestedChild.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.p083v4.view.NestedScrollingParent
    public void onStopNestedScroll(@NonNull View view) {
        this.mNestedParent.onStopNestedScroll(view);
        this.mNestedInProgress = false;
        this.mTotalUnconsumed = 0;
        overSpinner();
        this.mNestedChild.stopNestedScroll();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.mEnableNestedScrolling = z;
        this.mNestedChild.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mEnableNestedScrolling;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setFooterHeight(float f) {
        if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mFooterHeight = SmartUtil.dp2px(f);
            this.mFooterHeightStatus = DimensionStatus.CodeExactUnNotify;
            RefreshInternal refreshInternal = this.mRefreshFooter;
            if (refreshInternal != null) {
                refreshInternal.getView().requestLayout();
            }
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setHeaderHeight(float f) {
        if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mHeaderHeight = SmartUtil.dp2px(f);
            this.mHeaderHeightStatus = DimensionStatus.CodeExactUnNotify;
            RefreshInternal refreshInternal = this.mRefreshHeader;
            if (refreshInternal != null) {
                refreshInternal.getView().requestLayout();
            }
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setHeaderInsetStart(float f) {
        this.mHeaderInsetStart = SmartUtil.dp2px(f);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setFooterInsetStart(float f) {
        this.mFooterInsetStart = SmartUtil.dp2px(f);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setDragRate(float f) {
        this.mDragRate = f;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setHeaderMaxDragRate(float f) {
        this.mHeaderMaxDragRate = f;
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null && this.mAttachedToWindow) {
            RefreshKernel refreshKernel = this.mKernel;
            int i = this.mHeaderHeight;
            refreshInternal.onInitialized(refreshKernel, i, (int) (this.mHeaderMaxDragRate * i));
        } else {
            this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setFooterMaxDragRate(float f) {
        this.mFooterMaxDragRate = f;
        RefreshInternal refreshInternal = this.mRefreshFooter;
        if (refreshInternal != null && this.mAttachedToWindow) {
            RefreshKernel refreshKernel = this.mKernel;
            int i = this.mFooterHeight;
            refreshInternal.onInitialized(refreshKernel, i, (int) (i * this.mFooterMaxDragRate));
        } else {
            this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setHeaderTriggerRate(float f) {
        this.mHeaderTriggerRate = f;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setFooterTriggerRate(float f) {
        this.mFooterTriggerRate = f;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator) {
        this.mReboundInterpolator = interpolator;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setReboundDuration(int i) {
        this.mReboundDuration = i;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableLoadMore(boolean z) {
        this.mManualLoadMore = true;
        this.mEnableLoadMore = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableRefresh(boolean z) {
        this.mEnableRefresh = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableHeaderTranslationContent(boolean z) {
        this.mEnableHeaderTranslationContent = z;
        this.mManualHeaderTranslationContent = true;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableFooterTranslationContent(boolean z) {
        this.mEnableFooterTranslationContent = z;
        this.mManualFooterTranslationContent = true;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableAutoLoadMore(boolean z) {
        this.mEnableAutoLoadMore = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableOverScrollBounce(boolean z) {
        this.mEnableOverScrollBounce = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnablePureScrollMode(boolean z) {
        this.mEnablePureScrollMode = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableScrollContentWhenLoaded(boolean z) {
        this.mEnableScrollContentWhenLoaded = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableScrollContentWhenRefreshed(boolean z) {
        this.mEnableScrollContentWhenRefreshed = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mEnableLoadMoreWhenContentNotFull = z;
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            refreshContent.setEnableLoadMoreWhenContentNotFull(z);
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableOverScrollDrag(boolean z) {
        this.mEnableOverScrollDrag = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    @Deprecated
    public RefreshLayout setEnableFooterFollowWhenLoadFinished(boolean z) {
        this.mEnableFooterFollowWhenNoMoreData = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableFooterFollowWhenNoMoreData(boolean z) {
        this.mEnableFooterFollowWhenNoMoreData = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z) {
        this.mEnableClipHeaderWhenFixedBehind = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableClipFooterWhenFixedBehind(boolean z) {
        this.mEnableClipFooterWhenFixedBehind = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setEnableNestedScroll(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setDisableContentWhenRefresh(boolean z) {
        this.mDisableContentWhenRefresh = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setDisableContentWhenLoading(boolean z) {
        this.mDisableContentWhenLoading = z;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, -1, -2);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader, int i, int i2) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null) {
            super.removeView(refreshInternal.getView());
        }
        this.mRefreshHeader = refreshHeader;
        this.mHeaderBackgroundColor = 0;
        this.mHeaderNeedTouchEventWhenRefreshing = false;
        this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.addView(this.mRefreshHeader.getView(), 0, new LayoutParams(i, i2));
        } else {
            super.addView(this.mRefreshHeader.getView(), i, i2);
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter) {
        return setRefreshFooter(refreshFooter, -1, -2);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter, int i, int i2) {
        RefreshInternal refreshInternal = this.mRefreshFooter;
        if (refreshInternal != null) {
            super.removeView(refreshInternal.getView());
        }
        this.mRefreshFooter = refreshFooter;
        this.mFooterLocked = false;
        this.mFooterBackgroundColor = 0;
        this.mFooterNoMoreDataEffective = false;
        this.mFooterNeedTouchEventWhenLoading = false;
        this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        this.mEnableLoadMore = !this.mManualLoadMore || this.mEnableLoadMore;
        if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.addView(this.mRefreshFooter.getView(), 0, new LayoutParams(i, i2));
        } else {
            super.addView(this.mRefreshFooter.getView(), i, i2);
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshContent(@NonNull View view) {
        return setRefreshContent(view, -1, -1);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshContent(@NonNull View view, int i, int i2) {
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            super.removeView(refreshContent.getView());
        }
        super.addView(view, 0, new LayoutParams(i, i2));
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null && refreshInternal.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.bringChildToFront(view);
            RefreshInternal refreshInternal2 = this.mRefreshFooter;
            if (refreshInternal2 != null && refreshInternal2.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
                super.bringChildToFront(this.mRefreshFooter.getView());
            }
        } else {
            RefreshInternal refreshInternal3 = this.mRefreshFooter;
            if (refreshInternal3 != null && refreshInternal3.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                super.bringChildToFront(view);
                RefreshInternal refreshInternal4 = this.mRefreshHeader;
                if (refreshInternal4 != null && refreshInternal4.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    super.bringChildToFront(this.mRefreshHeader.getView());
                }
            }
        }
        this.mRefreshContent = new RefreshContentWrapper(view);
        if (this.mAttachedToWindow) {
            int i3 = this.mFixedHeaderViewId;
            View findViewById = i3 > 0 ? findViewById(i3) : null;
            int i4 = this.mFixedFooterViewId;
            View findViewById2 = i4 > 0 ? findViewById(i4) : null;
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, findViewById, findViewById2);
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    @Nullable
    public RefreshFooter getRefreshFooter() {
        RefreshInternal refreshInternal = this.mRefreshFooter;
        if (refreshInternal instanceof RefreshFooter) {
            return (RefreshFooter) refreshInternal;
        }
        return null;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    @Nullable
    public RefreshHeader getRefreshHeader() {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal instanceof RefreshHeader) {
            return (RefreshHeader) refreshInternal;
        }
        return null;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    @NonNull
    public RefreshState getState() {
        return this.mState;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || !(this.mManualLoadMore || onLoadMoreListener == null);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.mRefreshListener = onRefreshLoadMoreListener;
        this.mLoadMoreListener = onRefreshLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || !(this.mManualLoadMore || onRefreshLoadMoreListener == null);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener) {
        this.mOnMultiPurposeListener = onMultiPurposeListener;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setPrimaryColors(@ColorInt int... iArr) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null) {
            refreshInternal.setPrimaryColors(iArr);
        }
        RefreshInternal refreshInternal2 = this.mRefreshFooter;
        if (refreshInternal2 != null) {
            refreshInternal2.setPrimaryColors(iArr);
        }
        this.mPrimaryColors = iArr;
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setPrimaryColorsId(@ColorRes int... iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = ContextCompat.getColor(getContext(), iArr[i]);
        }
        setPrimaryColors(iArr2);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        this.mScrollBoundaryDecider = scrollBoundaryDecider;
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            refreshContent.setScrollBoundaryDecider(scrollBoundaryDecider);
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setNoMoreData(boolean z) {
        if (this.mState == RefreshState.Loading && z) {
            finishLoadMoreWithNoMoreData();
            return this;
        }
        if (this.mFooterNoMoreData != z) {
            this.mFooterNoMoreData = z;
            RefreshInternal refreshInternal = this.mRefreshFooter;
            if (refreshInternal instanceof RefreshFooter) {
                if (((RefreshFooter) refreshInternal).setNoMoreData(z)) {
                    this.mFooterNoMoreDataEffective = true;
                    if (this.mFooterNoMoreData && this.mEnableFooterFollowWhenNoMoreData && this.mSpinner > 0 && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && isEnableTranslationContent(this.mEnableRefresh, this.mRefreshHeader)) {
                        this.mRefreshFooter.getView().setTranslationY(this.mSpinner);
                    }
                } else {
                    this.mFooterNoMoreDataEffective = false;
                    new RuntimeException("Footer:" + this.mRefreshFooter + " NoMoreData is not supported.(不支持NoMoreData，请使用ClassicsFooter或者自定义)").printStackTrace();
                }
            }
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout resetNoMoreData() {
        return setNoMoreData(false);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishRefresh() {
        return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300));
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishLoadMore() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300));
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishRefresh(int i) {
        return finishRefresh(i, true, Boolean.FALSE);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishRefresh(boolean z) {
        return finishRefresh(z ? Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) : 0, z, !z ? Boolean.FALSE : null);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishRefresh(int i, final boolean z, final Boolean bool) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.6
            @Override // java.lang.Runnable
            public void run() {
                if (SmartRefreshLayout.this.mState == RefreshState.Refreshing && SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    Boolean bool2 = bool;
                    if (bool2 != null) {
                        SmartRefreshLayout.this.setNoMoreData(bool2 == Boolean.TRUE);
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                    int onFinish = SmartRefreshLayout.this.mRefreshHeader.onFinish(SmartRefreshLayout.this, z);
                    if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.onHeaderFinish((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z);
                    }
                    if (onFinish < Integer.MAX_VALUE) {
                        if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (SmartRefreshLayout.this.mIsBeingDragged) {
                                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                                smartRefreshLayout.mTouchY = smartRefreshLayout.mLastTouchY;
                                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                                smartRefreshLayout2.mTouchSpinner = 0;
                                smartRefreshLayout2.mIsBeingDragged = false;
                                SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, smartRefreshLayout2.mLastTouchX, (SmartRefreshLayout.this.mLastTouchY + SmartRefreshLayout.this.mSpinner) - (SmartRefreshLayout.this.mTouchSlop * 2), 0));
                                SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                                SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 2, smartRefreshLayout3.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + SmartRefreshLayout.this.mSpinner, 0));
                            }
                            if (SmartRefreshLayout.this.mNestedInProgress) {
                                SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                                smartRefreshLayout4.mTotalUnconsumed = 0;
                                SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 1, smartRefreshLayout4.mLastTouchX, SmartRefreshLayout.this.mLastTouchY, 0));
                                SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                                smartRefreshLayout5.mNestedInProgress = false;
                                smartRefreshLayout5.mTouchSpinner = 0;
                            }
                        }
                        if (SmartRefreshLayout.this.mSpinner > 0) {
                            SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                            ValueAnimator animSpinner = smartRefreshLayout6.animSpinner(0, onFinish, smartRefreshLayout6.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                            ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished = SmartRefreshLayout.this.mEnableScrollContentWhenRefreshed ? SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner) : null;
                            if (animSpinner == null || scrollContentWhenFinished == null) {
                                return;
                            }
                            animSpinner.addUpdateListener(scrollContentWhenFinished);
                        } else if (SmartRefreshLayout.this.mSpinner < 0) {
                            SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                            smartRefreshLayout7.animSpinner(0, onFinish, smartRefreshLayout7.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                        } else {
                            SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                        }
                    }
                } else if (SmartRefreshLayout.this.mState == RefreshState.None && SmartRefreshLayout.this.mViceState == RefreshState.Refreshing) {
                    SmartRefreshLayout.this.mViceState = RefreshState.None;
                } else if (SmartRefreshLayout.this.reboundAnimator == null || !SmartRefreshLayout.this.mState.isHeader) {
                } else {
                    if (SmartRefreshLayout.this.mState.isDragging || SmartRefreshLayout.this.mState == RefreshState.RefreshReleased) {
                        ValueAnimator valueAnimator = SmartRefreshLayout.this.reboundAnimator;
                        SmartRefreshLayout.this.reboundAnimator = null;
                        valueAnimator.cancel();
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                    }
                }
            }
        }, i <= 0 ? 1L : i);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishRefreshWithNoMoreData() {
        return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300), true, Boolean.TRUE);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishLoadMore(int i) {
        return finishLoadMore(i, true, false);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishLoadMore(boolean z) {
        return finishLoadMore(z ? Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) : 0, z, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.scwang.smartrefresh.layout.SmartRefreshLayout$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC69397 implements Runnable {
        final /* synthetic */ boolean val$noMoreData;
        final /* synthetic */ boolean val$success;

        RunnableC69397(boolean z, boolean z2) {
            this.val$success = z;
            this.val$noMoreData = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = true;
            if (SmartRefreshLayout.this.mState == RefreshState.Loading && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshContent != null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                int onFinish = SmartRefreshLayout.this.mRefreshFooter.onFinish(SmartRefreshLayout.this, this.val$success);
                if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter)) {
                    SmartRefreshLayout.this.mOnMultiPurposeListener.onFooterFinish((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, this.val$success);
                }
                if (onFinish < Integer.MAX_VALUE) {
                    if (!this.val$noMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || SmartRefreshLayout.this.mSpinner >= 0 || !SmartRefreshLayout.this.mRefreshContent.canLoadMore()) {
                        z = false;
                    }
                    final int max = SmartRefreshLayout.this.mSpinner - (z ? Math.max(SmartRefreshLayout.this.mSpinner, -SmartRefreshLayout.this.mFooterHeight) : 0);
                    if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (SmartRefreshLayout.this.mIsBeingDragged) {
                            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                            smartRefreshLayout.mTouchY = smartRefreshLayout.mLastTouchY;
                            SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                            smartRefreshLayout2.mTouchSpinner = smartRefreshLayout2.mSpinner - max;
                            SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                            smartRefreshLayout3.mIsBeingDragged = false;
                            int i = smartRefreshLayout3.mEnableFooterTranslationContent ? max : 0;
                            SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                            float f = i;
                            SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, smartRefreshLayout4.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f + (SmartRefreshLayout.this.mTouchSlop * 2), 0));
                            SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                            SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 2, smartRefreshLayout5.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f, 0));
                        }
                        if (SmartRefreshLayout.this.mNestedInProgress) {
                            SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                            smartRefreshLayout6.mTotalUnconsumed = 0;
                            SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 1, smartRefreshLayout6.mLastTouchX, SmartRefreshLayout.this.mLastTouchY, 0));
                            SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                            smartRefreshLayout7.mNestedInProgress = false;
                            smartRefreshLayout7.mTouchSpinner = 0;
                        }
                    }
                    SmartRefreshLayout.this.mHandler.postDelayed(new Runnable() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ValueAnimator valueAnimator;
                            ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished = (!SmartRefreshLayout.this.mEnableScrollContentWhenLoaded || max >= 0) ? null : SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner);
                            if (scrollContentWhenFinished != null) {
                                scrollContentWhenFinished.onAnimationUpdate(ValueAnimator.ofInt(0, 0));
                            }
                            AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.7.1.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    SmartRefreshLayout.this.mFooterLocked = false;
                                    if (RunnableC69397.this.val$noMoreData) {
                                        SmartRefreshLayout.this.setNoMoreData(true);
                                    }
                                    if (SmartRefreshLayout.this.mState == RefreshState.LoadFinish) {
                                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                    }
                                }
                            };
                            if (SmartRefreshLayout.this.mSpinner > 0) {
                                valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(0);
                            } else {
                                if (scrollContentWhenFinished != null || SmartRefreshLayout.this.mSpinner == 0) {
                                    if (SmartRefreshLayout.this.reboundAnimator != null) {
                                        SmartRefreshLayout.this.reboundAnimator.cancel();
                                        SmartRefreshLayout.this.reboundAnimator = null;
                                    }
                                    SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                                    SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                                } else if (RunnableC69397.this.val$noMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData) {
                                    if (SmartRefreshLayout.this.mSpinner >= (-SmartRefreshLayout.this.mFooterHeight)) {
                                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                    } else {
                                        valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(-SmartRefreshLayout.this.mFooterHeight);
                                    }
                                } else {
                                    valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(0);
                                }
                                valueAnimator = null;
                            }
                            if (valueAnimator != null) {
                                valueAnimator.addListener(animatorListenerAdapter);
                            } else {
                                animatorListenerAdapter.onAnimationEnd(null);
                            }
                        }
                    }, SmartRefreshLayout.this.mSpinner < 0 ? onFinish : 0L);
                    return;
                }
                return;
            }
            if (SmartRefreshLayout.this.mState == RefreshState.None && SmartRefreshLayout.this.mViceState == RefreshState.Loading) {
                SmartRefreshLayout.this.mViceState = RefreshState.None;
            } else if (SmartRefreshLayout.this.reboundAnimator != null && ((SmartRefreshLayout.this.mState.isDragging || SmartRefreshLayout.this.mState == RefreshState.LoadReleased) && SmartRefreshLayout.this.mState.isFooter)) {
                ValueAnimator valueAnimator = SmartRefreshLayout.this.reboundAnimator;
                SmartRefreshLayout.this.reboundAnimator = null;
                valueAnimator.cancel();
                SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
            }
            if (this.val$noMoreData) {
                SmartRefreshLayout.this.setNoMoreData(true);
            }
        }
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishLoadMore(int i, boolean z, boolean z2) {
        this.mHandler.postDelayed(new RunnableC69397(z, z2), i <= 0 ? 1L : i);
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout finishLoadMoreWithNoMoreData() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300), true, true);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout closeHeaderOrFooter() {
        if (this.mState == RefreshState.Refreshing) {
            finishRefresh();
        } else if (this.mState == RefreshState.Loading) {
            finishLoadMore();
        } else if (this.mSpinner != 0) {
            animSpinner(0, 0, this.mReboundInterpolator, this.mReboundDuration);
        }
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public boolean autoRefresh() {
        int i = this.mAttachedToWindow ? 0 : 400;
        int i2 = this.mReboundDuration;
        int i3 = this.mHeaderHeight;
        float f = ((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * i3 * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoRefresh(i, i2, f / i3, false);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    @Deprecated
    public boolean autoRefresh(int i) {
        int i2 = this.mReboundDuration;
        int i3 = this.mHeaderHeight;
        float f = ((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * i3 * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoRefresh(i, i2, f / i3, false);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public boolean autoRefreshAnimationOnly() {
        int i = this.mAttachedToWindow ? 0 : 400;
        int i2 = this.mReboundDuration;
        int i3 = this.mHeaderHeight;
        float f = ((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * i3 * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoRefresh(i, i2, f / i3, true);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public boolean autoRefresh(int i, final int i2, final float f, final boolean z) {
        if (this.mState == RefreshState.None && isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
            Runnable runnable = new Runnable() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.8
                @Override // java.lang.Runnable
                public void run() {
                    if (SmartRefreshLayout.this.mViceState != RefreshState.Refreshing) {
                        return;
                    }
                    if (SmartRefreshLayout.this.reboundAnimator != null) {
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                    }
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mLastTouchX = smartRefreshLayout.getMeasuredWidth() / 2.0f;
                    SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    smartRefreshLayout2.reboundAnimator = ValueAnimator.ofInt(smartRefreshLayout2.mSpinner, (int) (SmartRefreshLayout.this.mHeaderHeight * f));
                    SmartRefreshLayout.this.reboundAnimator.setDuration(i2);
                    SmartRefreshLayout.this.reboundAnimator.setInterpolator(new SmartUtil());
                    SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.8.1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if (SmartRefreshLayout.this.reboundAnimator != null) {
                                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.8.2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            if (SmartRefreshLayout.this.reboundAnimator != null) {
                                SmartRefreshLayout.this.reboundAnimator = null;
                                if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToRefresh) {
                                    SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToRefresh);
                                }
                                SmartRefreshLayout.this.setStateRefreshing(!z);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.start();
                }
            };
            setViceState(RefreshState.Refreshing);
            if (i > 0) {
                this.mHandler.postDelayed(runnable, i);
                return true;
            }
            runnable.run();
            return true;
        }
        return false;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public boolean autoLoadMore() {
        int i = this.mReboundDuration;
        int i2 = this.mFooterHeight;
        float f = i2 * ((this.mFooterMaxDragRate / 2.0f) + 0.5f) * 1.0f;
        if (i2 == 0) {
            i2 = 1;
        }
        return autoLoadMore(0, i, f / i2, false);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public boolean autoLoadMoreAnimationOnly() {
        int i = this.mReboundDuration;
        int i2 = this.mFooterHeight;
        float f = i2 * ((this.mFooterMaxDragRate / 2.0f) + 0.5f) * 1.0f;
        if (i2 == 0) {
            i2 = 1;
        }
        return autoLoadMore(0, i, f / i2, true);
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshLayout
    public boolean autoLoadMore(int i, final int i2, final float f, final boolean z) {
        if (this.mState == RefreshState.None && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && !this.mFooterNoMoreData) {
            Runnable runnable = new Runnable() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.9
                @Override // java.lang.Runnable
                public void run() {
                    if (SmartRefreshLayout.this.mViceState != RefreshState.Loading) {
                        return;
                    }
                    if (SmartRefreshLayout.this.reboundAnimator != null) {
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                    }
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mLastTouchX = smartRefreshLayout.getMeasuredWidth() / 2.0f;
                    SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    smartRefreshLayout2.reboundAnimator = ValueAnimator.ofInt(smartRefreshLayout2.mSpinner, -((int) (SmartRefreshLayout.this.mFooterHeight * f)));
                    SmartRefreshLayout.this.reboundAnimator.setDuration(i2);
                    SmartRefreshLayout.this.reboundAnimator.setInterpolator(new SmartUtil());
                    SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.9.1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if (SmartRefreshLayout.this.reboundAnimator != null) {
                                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.9.2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            if (SmartRefreshLayout.this.reboundAnimator != null) {
                                SmartRefreshLayout.this.reboundAnimator = null;
                                if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToLoad) {
                                    SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToLoad);
                                }
                                SmartRefreshLayout.this.setStateLoading(!z);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.start();
                }
            };
            setViceState(RefreshState.Loading);
            if (i > 0) {
                this.mHandler.postDelayed(runnable, i);
                return true;
            }
            runnable.run();
            return true;
        }
        return false;
    }

    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        sHeaderCreator = defaultRefreshHeaderCreator;
    }

    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        sFooterCreator = defaultRefreshFooterCreator;
    }

    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer defaultRefreshInitializer) {
        sRefreshInitializer = defaultRefreshInitializer;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RefreshKernelImpl implements RefreshKernel {
        public RefreshKernelImpl() {
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        @NonNull
        public RefreshLayout getRefreshLayout() {
            return SmartRefreshLayout.this;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        @NonNull
        public RefreshContent getRefreshContent() {
            return SmartRefreshLayout.this.mRefreshContent;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel setState(@NonNull RefreshState refreshState) {
            switch (refreshState) {
                case None:
                    if (SmartRefreshLayout.this.mState != RefreshState.None && SmartRefreshLayout.this.mSpinner == 0) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                        return null;
                    } else if (SmartRefreshLayout.this.mSpinner != 0) {
                        animSpinner(0);
                        return null;
                    } else {
                        return null;
                    }
                case PullDownToRefresh:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        if (smartRefreshLayout.isEnableRefreshOrLoadMore(smartRefreshLayout.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownToRefresh);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullDownToRefresh);
                    return null;
                case PullUpToLoad:
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    if (smartRefreshLayout2.isEnableRefreshOrLoadMore(smartRefreshLayout2.mEnableLoadMore) && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isFinishing && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullUpToLoad);
                    return null;
                case PullDownCanceled:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                        if (smartRefreshLayout3.isEnableRefreshOrLoadMore(smartRefreshLayout3.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                            setState(RefreshState.None);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullDownCanceled);
                    return null;
                case PullUpCanceled:
                    SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                    if (smartRefreshLayout4.isEnableRefreshOrLoadMore(smartRefreshLayout4.mEnableLoadMore) && !SmartRefreshLayout.this.mState.isOpening && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                        setState(RefreshState.None);
                        return null;
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullUpCanceled);
                    return null;
                case ReleaseToRefresh:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                        if (smartRefreshLayout5.isEnableRefreshOrLoadMore(smartRefreshLayout5.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToRefresh);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToRefresh);
                    return null;
                case ReleaseToLoad:
                    SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                    if (smartRefreshLayout6.isEnableRefreshOrLoadMore(smartRefreshLayout6.mEnableLoadMore) && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isFinishing && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToLoad);
                    return null;
                case ReleaseToTwoLevel:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                        if (smartRefreshLayout7.isEnableRefreshOrLoadMore(smartRefreshLayout7.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToTwoLevel);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToTwoLevel);
                    return null;
                case RefreshReleased:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                        if (smartRefreshLayout8.isEnableRefreshOrLoadMore(smartRefreshLayout8.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshReleased);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.RefreshReleased);
                    return null;
                case LoadReleased:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout9 = SmartRefreshLayout.this;
                        if (smartRefreshLayout9.isEnableRefreshOrLoadMore(smartRefreshLayout9.mEnableLoadMore)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadReleased);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.LoadReleased);
                    return null;
                case Refreshing:
                    SmartRefreshLayout.this.setStateRefreshing(true);
                    return null;
                case Loading:
                    SmartRefreshLayout.this.setStateLoading(true);
                    return null;
                case RefreshFinish:
                    if (SmartRefreshLayout.this.mState == RefreshState.Refreshing) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                        return null;
                    }
                    return null;
                case LoadFinish:
                    if (SmartRefreshLayout.this.mState == RefreshState.Loading) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                        return null;
                    }
                    return null;
                case TwoLevelReleased:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevelReleased);
                    return null;
                case TwoLevelFinish:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevelFinish);
                    return null;
                case TwoLevel:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevel);
                    return null;
                default:
                    return null;
            }
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel startTwoLevel(boolean z) {
            if (z) {
                AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.scwang.smartrefresh.layout.SmartRefreshLayout.RefreshKernelImpl.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevel);
                    }
                };
                ValueAnimator animSpinner = animSpinner(SmartRefreshLayout.this.getMeasuredHeight());
                if (animSpinner != null && animSpinner == SmartRefreshLayout.this.reboundAnimator) {
                    animSpinner.setDuration(SmartRefreshLayout.this.mFloorDuration);
                    animSpinner.addListener(animatorListenerAdapter);
                } else {
                    animatorListenerAdapter.onAnimationEnd(null);
                }
            } else if (animSpinner(0) == null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
            }
            return this;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel finishTwoLevel() {
            if (SmartRefreshLayout.this.mState == RefreshState.TwoLevel) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevelFinish);
                if (SmartRefreshLayout.this.mSpinner == 0) {
                    moveSpinner(0, false);
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                } else {
                    animSpinner(0).setDuration(SmartRefreshLayout.this.mFloorDuration);
                }
            }
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x00ef  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00f2  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00f8  */
        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.scwang.smartrefresh.layout.api.RefreshKernel moveSpinner(int r18, boolean r19) {
            /*
                Method dump skipped, instructions count: 913
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.RefreshKernelImpl.moveSpinner(int, boolean):com.scwang.smartrefresh.layout.api.RefreshKernel");
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public ValueAnimator animSpinner(int i) {
            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
            return smartRefreshLayout.animSpinner(i, 0, smartRefreshLayout.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel requestDrawBackgroundFor(@NonNull RefreshInternal refreshInternal, int i) {
            if (SmartRefreshLayout.this.mPaint == null && i != 0) {
                SmartRefreshLayout.this.mPaint = new Paint();
            }
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderBackgroundColor = i;
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterBackgroundColor = i;
            }
            return this;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel requestNeedTouchEventFor(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderNeedTouchEventWhenRefreshing = z;
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterNeedTouchEventWhenLoading = z;
            }
            return this;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (!SmartRefreshLayout.this.mManualHeaderTranslationContent) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mManualHeaderTranslationContent = true;
                    smartRefreshLayout.mEnableHeaderTranslationContent = z;
                }
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter) && !SmartRefreshLayout.this.mManualFooterTranslationContent) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.mManualFooterTranslationContent = true;
                smartRefreshLayout2.mEnableFooterTranslationContent = z;
            }
            return this;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel requestRemeasureHeightFor(@NonNull RefreshInternal refreshInternal) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (SmartRefreshLayout.this.mHeaderHeightStatus.notified) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mHeaderHeightStatus = smartRefreshLayout.mHeaderHeightStatus.unNotify();
                }
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter) && SmartRefreshLayout.this.mFooterHeightStatus.notified) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.mFooterHeightStatus = smartRefreshLayout2.mFooterHeightStatus.unNotify();
            }
            return this;
        }

        @Override // com.scwang.smartrefresh.layout.api.RefreshKernel
        public RefreshKernel requestFloorDuration(int i) {
            SmartRefreshLayout.this.mFloorDuration = i;
            return this;
        }
    }
}
