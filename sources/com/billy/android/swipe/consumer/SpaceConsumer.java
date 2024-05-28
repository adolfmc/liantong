package com.billy.android.swipe.consumer;

import android.view.View;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.calculator.ScaledCalculator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SpaceConsumer extends SwipeConsumer {
    public SpaceConsumer() {
        setSwipeDistanceCalculator(new ScaledCalculator(0.5f));
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        super.onDetachFromWrapper();
        View contentView = this.mWrapper.getContentView();
        if (contentView != null) {
            contentView.setTranslationX(0.0f);
            contentView.setTranslationY(0.0f);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        View contentView = this.mWrapper.getContentView();
        if (contentView != null) {
            if ((i >= 0 && isLeftEnable()) || (i <= 0 && isRightEnable())) {
                contentView.setTranslationX(i);
            }
            if ((i2 < 0 || !isTopEnable()) && (i2 > 0 || !isBottomEnable())) {
                return;
            }
            contentView.setTranslationY(i2);
        }
    }
}
