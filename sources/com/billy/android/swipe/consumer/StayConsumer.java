package com.billy.android.swipe.consumer;

import com.billy.android.swipe.SwipeConsumer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StayConsumer extends SwipeConsumer {
    private int mMinVelocity = 1000;

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
    }

    public StayConsumer() {
        setOpenDistance(Integer.MAX_VALUE).setMaxSettleDuration(0);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onSwipeReleased(float f, float f2) {
        if (Math.abs(f) > Math.abs(f2)) {
            if ((this.mDirection == 1 && f >= this.mMinVelocity) || (this.mDirection == 2 && f <= (-this.mMinVelocity))) {
                this.mCurSwipeDistanceX = getSwipeOpenDistance();
                this.mProgress = 1.0f;
            }
        } else if ((this.mDirection == 4 && f2 >= this.mMinVelocity) || (this.mDirection == 8 && f2 <= (-this.mMinVelocity))) {
            this.mCurSwipeDistanceY = getSwipeOpenDistance();
            this.mProgress = 1.0f;
        }
        super.onSwipeReleased(f, f2);
    }

    public int getMinVelocity() {
        return this.mMinVelocity;
    }

    public StayConsumer setMinVelocity(int i) {
        if (i > 0) {
            this.mMinVelocity = i;
        }
        return this;
    }
}
