package com.billy.android.swipe.consumer;

import android.view.View;
import com.billy.android.swipe.SwipeConsumer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StretchConsumer extends SwipeConsumer {
    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        super.onDetachFromWrapper();
        View contentView = this.mWrapper.getContentView();
        if (contentView != null) {
            contentView.setScaleX(1.0f);
            contentView.setScaleY(1.0f);
            contentView.setTranslationX(0.0f);
            contentView.setTranslationY(0.0f);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        View contentView = this.mWrapper.getContentView();
        if (contentView != null) {
            if ((i >= 0 && isLeftEnable()) || (i <= 0 && isRightEnable())) {
                float f = i;
                contentView.setScaleX((Math.abs(f) / this.mWidth) + 1.0f);
                contentView.setTranslationX(f / 2.0f);
            }
            if ((i2 < 0 || !isTopEnable()) && (i2 > 0 || !isBottomEnable())) {
                return;
            }
            float f2 = i2;
            contentView.setScaleY((Math.abs(f2) / this.mHeight) + 1.0f);
            contentView.setTranslationY(f2 / 2.0f);
        }
    }
}
