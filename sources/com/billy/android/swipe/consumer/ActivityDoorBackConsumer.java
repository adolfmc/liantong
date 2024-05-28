package com.billy.android.swipe.consumer;

import android.app.Activity;
import com.billy.android.swipe.C3336R;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.internal.ActivityTranslucentUtil;
import com.billy.android.swipe.internal.SwipeHelper;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ActivityDoorBackConsumer extends DoorConsumer {
    protected Activity mActivity;
    protected ActivityTranslucentUtil mActivityTranslucentUtil;

    public ActivityDoorBackConsumer(Activity activity) {
        this.mActivity = activity;
        this.mActivityTranslucentUtil = new ActivityTranslucentUtil(activity);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onAttachToWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeHelper swipeHelper) {
        super.onAttachToWrapper(smartSwipeWrapper, swipeHelper);
        ActivityTranslucentUtil.convertWindowToTranslucent(this.mActivity);
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public void onSwipeAccepted(int i, boolean z, float f, float f2) {
        if (!this.mActivityTranslucentUtil.isTranslucent()) {
            this.mActivityTranslucentUtil.convertActivityToTranslucent();
        }
        super.onSwipeAccepted(i, z, f, f2);
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        if (this.mActivityTranslucentUtil.isTranslucent()) {
            super.onDisplayDistanceChanged(i, i2, i3, i4);
        }
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        super.onDetachFromWrapper();
        this.mActivityTranslucentUtil.convertActivityFromTranslucent();
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onOpened() {
        Activity activity;
        super.onOpened();
        if ((this.mListeners == null || this.mListeners.isEmpty()) && (activity = this.mActivity) != null) {
            activity.finish();
            this.mActivity.overridePendingTransition(C3336R.anim.anim_none, C3336R.anim.anim_none);
        }
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public void onClosed() {
        super.onClosed();
        this.mActivityTranslucentUtil.convertActivityFromTranslucent();
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public int clampDistanceVertical(int i, int i2) {
        if (this.mActivityTranslucentUtil.isTranslucent()) {
            return super.clampDistanceVertical(i, i2);
        }
        return 0;
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public int clampDistanceHorizontal(int i, int i2) {
        if (this.mActivityTranslucentUtil.isTranslucent()) {
            return super.clampDistanceHorizontal(i, i2);
        }
        return 0;
    }
}
