package com.billy.android.swipe.consumer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DoorConsumer extends ShuttersConsumer {
    private static final int LEAVES_COUNT = 2;

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer
    public ShuttersConsumer setLeavesCount(int i) {
        return this;
    }

    public DoorConsumer() {
        this.mLeavesCount = 2;
        setMaxSettleDuration(1000);
    }

    @Override // com.billy.android.swipe.consumer.ShuttersConsumer, com.billy.android.swipe.SwipeConsumer
    public void dispatchDraw(Canvas canvas) {
        Bitmap[] bitmapArr = this.mScreenshots;
        if (this.mDirection == 0 || bitmapArr == null || bitmapArr.length != 2 || bitmapArr[0] == null || bitmapArr[0].isRecycled() || bitmapArr[1] == null || bitmapArr[1].isRecycled()) {
            return;
        }
        int i = this.mWidth >> 1;
        int i2 = this.mHeight >> 1;
        if (this.mScrimColor != 0 && this.mBaseAlpha != 0) {
            if (this.mHorizontalSwiping) {
                float f = i;
                canvas.drawRect((1.0f - this.mProgress) * f, 0.0f, (this.mProgress + 1.0f) * f, this.mHeight, this.mPaint);
            } else {
                float f2 = i2;
                canvas.drawRect(0.0f, (1.0f - this.mProgress) * f2, this.mWidth, (this.mProgress + 1.0f) * f2, this.mPaint);
            }
        }
        canvas.save();
        if (this.mHorizontalSwiping) {
            canvas.translate((-i) * this.mProgress, 0.0f);
            canvas.drawBitmap(bitmapArr[0], 0.0f, 0.0f, (Paint) null);
            canvas.restore();
            canvas.save();
            canvas.translate(i * (this.mProgress + 1.0f), 0.0f);
            canvas.drawBitmap(bitmapArr[1], 0.0f, 0.0f, (Paint) null);
        } else {
            canvas.translate(0.0f, (-i2) * this.mProgress);
            canvas.drawBitmap(bitmapArr[0], 0.0f, 0.0f, (Paint) null);
            canvas.restore();
            canvas.save();
            canvas.translate(0.0f, i2 * (this.mProgress + 1.0f));
            canvas.drawBitmap(bitmapArr[1], 0.0f, 0.0f, (Paint) null);
        }
        canvas.restore();
    }
}
