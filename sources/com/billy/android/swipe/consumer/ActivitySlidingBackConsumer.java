package com.billy.android.swipe.consumer;

import android.app.Activity;
import android.view.View;
import com.billy.android.swipe.C3336R;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeBack;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.internal.ActivityTranslucentUtil;
import com.billy.android.swipe.internal.SwipeHelper;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ActivitySlidingBackConsumer extends TranslucentSlidingConsumer {
    protected int initTranslation = 0;
    protected Activity mActivity;
    protected final ActivityTranslucentUtil mActivityTranslucentUtil;
    protected boolean mHorizontalSwiping;
    protected View mPreviousActivityContentView;

    @Override // com.billy.android.swipe.consumer.TranslucentSlidingConsumer, com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void initChildrenFormXml() {
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public boolean tryAcceptSettling(int i, float f, float f2) {
        return false;
    }

    public ActivitySlidingBackConsumer(Activity activity) {
        this.mActivity = activity;
        this.mActivityTranslucentUtil = new ActivityTranslucentUtil(activity);
        showScrimAndShadowOutsideContentView();
        setShadowColor(Integer.MIN_VALUE);
        setShadowSize(SmartSwipe.dp2px(10, activity));
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public boolean tryAcceptMoving(int i, float f, float f2, float f3, float f4) {
        return super.tryAcceptMoving(i, f, f2, f3, f4);
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onAttachToWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeHelper swipeHelper) {
        super.onAttachToWrapper(smartSwipeWrapper, swipeHelper);
        ActivityTranslucentUtil.convertWindowToTranslucent(this.mActivity);
    }

    @Override // com.billy.android.swipe.consumer.SlidingConsumer, com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        super.onDetachFromWrapper();
        this.mActivityTranslucentUtil.convertActivityFromTranslucent();
        resetPreviousActivityContentView();
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onOpened() {
        Activity activity;
        super.onOpened();
        if ((this.mListeners == null || this.mListeners.isEmpty()) && (activity = this.mActivity) != null) {
            activity.finish();
            this.mActivity.overridePendingTransition(C3336R.anim.anim_none, C3336R.anim.anim_none);
        }
        resetPreviousActivityContentView();
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onClosed() {
        super.onClosed();
        this.mActivityTranslucentUtil.convertActivityFromTranslucent();
        resetPreviousActivityContentView();
    }

    private void resetPreviousActivityContentView() {
        View view = this.mPreviousActivityContentView;
        if (view != null) {
            view.setTranslationX(0.0f);
            this.mPreviousActivityContentView.setTranslationY(0.0f);
            this.mPreviousActivityContentView = null;
        }
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onSwipeAccepted(int i, boolean z, float f, float f2) {
        if (!this.mActivityTranslucentUtil.isTranslucent()) {
            this.mActivityTranslucentUtil.convertActivityToTranslucent();
        }
        if (this.mRelativeMoveFactor > 0.0f) {
            this.mHorizontalSwiping = (this.mDirection & 3) > 0;
            Activity findPreviousActivity = SmartSwipeBack.findPreviousActivity(this.mActivity);
            if (findPreviousActivity != null) {
                this.mPreviousActivityContentView = findPreviousActivity.getWindow().getDecorView();
                int i2 = this.mDirection;
                if (i2 == 4) {
                    this.initTranslation = -((int) (this.mHeight * this.mRelativeMoveFactor));
                } else if (i2 != 8) {
                    switch (i2) {
                        case 1:
                            this.initTranslation = -((int) (this.mWidth * this.mRelativeMoveFactor));
                            break;
                        case 2:
                            this.initTranslation = (int) (this.mWidth * this.mRelativeMoveFactor);
                            break;
                    }
                } else {
                    this.initTranslation = (int) (this.mHeight * this.mRelativeMoveFactor);
                }
                movePreviousActivityContentView(this.initTranslation);
            }
        }
        super.onSwipeAccepted(i, z, f, f2);
    }

    private void movePreviousActivityContentView(int i) {
        if (this.mPreviousActivityContentView == null || !this.mActivityTranslucentUtil.isTranslucent()) {
            return;
        }
        if (this.mHorizontalSwiping) {
            this.mPreviousActivityContentView.setTranslationX(i);
        } else {
            this.mPreviousActivityContentView.setTranslationY(i);
        }
    }

    @Override // com.billy.android.swipe.consumer.SlidingConsumer, com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        int i5;
        if (this.mActivityTranslucentUtil.isTranslucent()) {
            if (this.mPreviousActivityContentView != null) {
                int i6 = this.mDirection;
                if (i6 == 4) {
                    i5 = this.initTranslation + ((int) (this.mHeight * this.mProgress * this.mRelativeMoveFactor));
                } else if (i6 != 8) {
                    switch (i6) {
                        case 1:
                            i5 = this.initTranslation + ((int) (this.mWidth * this.mProgress * this.mRelativeMoveFactor));
                            break;
                        case 2:
                            i5 = this.initTranslation - ((int) ((this.mWidth * this.mProgress) * this.mRelativeMoveFactor));
                            break;
                        default:
                            i5 = 0;
                            break;
                    }
                } else {
                    i5 = this.initTranslation - ((int) ((this.mHeight * this.mProgress) * this.mRelativeMoveFactor));
                }
                movePreviousActivityContentView(i5);
            }
            boolean z = (this.mDirection & 3) > 0;
            View contentView = this.mWrapper.getContentView();
            if (contentView != null) {
                if (z) {
                    contentView.setTranslationX(i);
                } else {
                    contentView.setTranslationY(i2);
                }
            }
            layoutScrimView();
        }
    }

    @Override // com.billy.android.swipe.consumer.SlidingConsumer, com.billy.android.swipe.consumer.DrawerConsumer
    protected void layoutContentView(View view) {
        if (view != null) {
            view.layout(0, 0, this.mWidth, this.mHeight);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public int clampDistanceVertical(int i, int i2) {
        if (this.mActivityTranslucentUtil.isTranslucent()) {
            return super.clampDistanceVertical(i, i2);
        }
        return 0;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public int clampDistanceHorizontal(int i, int i2) {
        if (this.mActivityTranslucentUtil.isTranslucent()) {
            return super.clampDistanceHorizontal(i, i2);
        }
        return 0;
    }
}
