package com.billy.android.swipe.consumer;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.internal.SwipeUtil;
import java.util.concurrent.CountDownLatch;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ShuttersConsumer extends SwipeConsumer {
    private long lastRefreshTime;
    protected int mBaseAlpha;
    protected final Camera mCamera;
    protected boolean mHorizontalSwiping;
    protected Paint mPaint;
    protected volatile boolean mRefreshing;
    protected volatile Bitmap[] mScreenshots;
    protected int mScrimColor;
    protected int lastScreenDirection = 0;
    protected int mLeavesCount = 5;
    protected volatile boolean mRefreshable = true;
    protected boolean mWaitForScreenshot = true;
    protected int refreshDelay = 33;
    private Runnable refreshWrapperRunnable = new Runnable() { // from class: com.billy.android.swipe.consumer.ShuttersConsumer.1
        @Override // java.lang.Runnable
        public void run() {
            ShuttersConsumer.this.layoutChildren();
            ShuttersConsumer.this.mWrapper.postInvalidate();
        }
    };
    private Runnable refreshBitmapRunnable = new Runnable() { // from class: com.billy.android.swipe.consumer.ShuttersConsumer.2
        @Override // java.lang.Runnable
        public void run() {
            ShuttersConsumer.this.refreshBitmap();
        }
    };

    public ShuttersConsumer() {
        setReleaseMode(3);
        this.mCamera = new Camera();
        this.mCamera.setLocation(0.0f, 0.0f, -20.0f);
        this.mPaint = new Paint();
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        super.onDetachFromWrapper();
        setRefreshing(false);
        recycleScreenshots();
        layoutChildren();
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onSwipeAccepted(int i, boolean z, float f, float f2) {
        if (this.lastScreenDirection != this.mDirection) {
            recycleScreenshots();
        }
        this.lastScreenDirection = this.mDirection;
        this.lastRefreshTime = 0L;
        if (this.mCurSwipeDistanceX == 0 && this.mCurSwipeDistanceY == 0) {
            int i2 = this.mWidth >> 1;
            int i3 = this.mHeight >> 1;
            this.mHorizontalSwiping = isHorizontalDirection();
            if (!this.mOpenDistanceSpecified) {
                if (this.mHorizontalSwiping) {
                    this.mOpenDistance = i2;
                } else {
                    this.mOpenDistance = i3;
                }
            }
        }
        super.onSwipeAccepted(i, z, f, f2);
        layoutChildren();
        if (this.mRefreshing) {
            return;
        }
        setRefreshing(true);
        SwipeUtil.runInThreadPool(this.refreshBitmapRunnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ScreenshotCreateRunnable implements Runnable {
        Bitmap[] array;
        int height;
        int index;
        CountDownLatch latch;
        int scrollX;
        int scrollY;
        View srcView;
        int width;

        ScreenshotCreateRunnable(int i, int i2, int i3, Bitmap[] bitmapArr, CountDownLatch countDownLatch, View view, int i4, int i5) {
            this.width = i;
            this.height = i2;
            this.index = i3;
            this.array = bitmapArr;
            this.latch = countDownLatch;
            this.srcView = view;
            this.scrollX = i4;
            this.scrollY = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = false;
            try {
                Bitmap bitmap = this.array[this.index];
                if (bitmap == null || bitmap.isRecycled() || bitmap.getWidth() != this.width || bitmap.getHeight() != this.height) {
                    bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
                }
                Canvas canvas = new Canvas(bitmap);
                canvas.translate((-this.srcView.getScrollX()) - this.scrollX, (-this.srcView.getScrollY()) - this.scrollY);
                Drawable background = this.srcView.getBackground();
                if (background != null) {
                    background.draw(canvas);
                }
                try {
                    this.srcView.draw(canvas);
                    this.array[this.index] = bitmap;
                } catch (Exception unused) {
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        z = true;
                        this.srcView.post(this);
                    }
                }
            } finally {
                if (!z) {
                    this.latch.countDown();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    protected void refreshBitmap() {
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        CountDownLatch countDownLatch;
        Bitmap[] bitmapArr;
        int i4;
        if (this.lastRefreshTime == 0) {
            this.lastRefreshTime = SystemClock.elapsedRealtime();
        }
        View contentView = this.mWrapper.getContentView();
        int i5 = this.mLeavesCount;
        int i6 = (int) (((this.mWidth * 1.0f) / (this.mHorizontalSwiping ? i5 : 1)) + 0.5f);
        int i7 = (int) (((this.mHeight * 1.0f) / (this.mHorizontalSwiping ? 1 : i5)) + 0.5f);
        int i8 = this.mHorizontalSwiping ? this.mWidth - ((i5 - 1) * i6) : i6;
        int i9 = this.mHorizontalSwiping ? i7 : this.mHeight - ((i5 - 1) * i7);
        Bitmap[] bitmapArr2 = new Bitmap[i5];
        CountDownLatch countDownLatch2 = new CountDownLatch(i5);
        boolean z3 = false;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i12 < i5) {
            if (this.mHorizontalSwiping) {
                i = i6 * i12;
                i2 = i11;
            } else {
                i = i10;
                i2 = i7 * i12;
            }
            if (i12 != i5 - 1) {
                i3 = i12;
                z2 = z3;
                countDownLatch = countDownLatch2;
                bitmapArr = bitmapArr2;
                i4 = i7;
                SwipeUtil.runInThreadPool(new ScreenshotCreateRunnable(i6, i4, i3, bitmapArr, countDownLatch, contentView, i, i2));
            } else if (i8 <= 0 || i9 <= 0) {
                i3 = i12;
                z2 = z3;
                countDownLatch = countDownLatch2;
                bitmapArr = bitmapArr2;
                i4 = i7;
                countDownLatch.countDown();
            } else {
                i3 = i12;
                z2 = z3;
                countDownLatch = countDownLatch2;
                bitmapArr = bitmapArr2;
                i4 = i7;
                SwipeUtil.runInThreadPool(new ScreenshotCreateRunnable(i8, i9, i12, bitmapArr2, countDownLatch2, contentView, i, i2));
            }
            i12 = i3 + 1;
            z3 = z2;
            i10 = i;
            i11 = i2;
            countDownLatch2 = countDownLatch;
            bitmapArr2 = bitmapArr;
            i7 = i4;
        }
        boolean z4 = z3;
        Bitmap[] bitmapArr3 = bitmapArr2;
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!this.mSwiping && (this.mProgress <= 0.0f || this.mProgress >= 1.0f)) {
            setRefreshing(z4);
        }
        if (this.mRefreshing) {
            int length = bitmapArr3.length;
            int i13 = z4;
            while (true) {
                if (i13 >= length) {
                    z = z4;
                    break;
                } else if (bitmapArr3[i13] == null) {
                    z = true;
                    break;
                } else {
                    i13++;
                }
            }
            if (!z) {
                this.mScreenshots = bitmapArr3;
            }
            contentView.post(this.refreshWrapperRunnable);
            if (this.mRefreshable) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.lastRefreshTime;
                this.lastRefreshTime = SystemClock.elapsedRealtime();
                if (elapsedRealtime < this.refreshDelay) {
                    contentView.postDelayed(new Runnable() { // from class: com.billy.android.swipe.consumer.ShuttersConsumer.3
                        @Override // java.lang.Runnable
                        public void run() {
                            SwipeUtil.runInThreadPool(ShuttersConsumer.this.refreshBitmapRunnable);
                        }
                    }, this.refreshDelay - elapsedRealtime);
                    return;
                } else {
                    SwipeUtil.runInThreadPool(this.refreshBitmapRunnable);
                    return;
                }
            }
            setRefreshing(z4);
        }
    }

    private void setRefreshing(boolean z) {
        this.mRefreshing = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void layoutChildren() {
        View contentView = this.mWrapper.getContentView();
        if (this.mDirection == 0 || (this.mScreenshots == null && this.mWaitForScreenshot)) {
            contentView.layout(0, 0, this.mWidth, this.mHeight);
            contentView.setVisibility(0);
        } else if (this.mRefreshable) {
            contentView.layout(-9999, -9999, this.mWidth - 9999, this.mHeight - 9999);
        } else {
            contentView.setVisibility(8);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void dispatchDraw(Canvas canvas) {
        Bitmap[] bitmapArr = this.mScreenshots;
        if (this.mDirection == 0 || bitmapArr == null || bitmapArr.length == 0) {
            return;
        }
        if (this.mScrimColor != 0 && this.mBaseAlpha != 0) {
            canvas.drawRect(0.0f, 0.0f, this.mWidth, this.mHeight, this.mPaint);
        }
        int i = 1;
        int i2 = this.mWidth >> 1;
        int i3 = this.mHeight >> 1;
        int length = (int) ((((this.mHorizontalSwiping ? this.mWidth : this.mHeight) * 1.0f) / bitmapArr.length) + 0.5f);
        int i4 = length >> 1;
        if (this.mDirection != 1 && this.mDirection != 8) {
            i = -1;
        }
        for (int i5 = 0; i5 < bitmapArr.length; i5++) {
            Bitmap bitmap = bitmapArr[i5];
            if (bitmap != null && !bitmap.isRecycled()) {
                canvas.save();
                this.mCamera.save();
                if (this.mHorizontalSwiping) {
                    canvas.translate((length * i5) + i4, i3);
                    this.mCamera.rotateY(i * 90 * this.mProgress);
                    this.mCamera.applyToCanvas(canvas);
                    canvas.translate(-i4, 0.0f);
                    canvas.drawBitmap(bitmap, 0.0f, -i3, (Paint) null);
                } else {
                    canvas.translate(i2, (length * i5) + i4);
                    this.mCamera.rotateX(i * 90 * this.mProgress);
                    this.mCamera.applyToCanvas(canvas);
                    canvas.translate(0.0f, -i4);
                    canvas.drawBitmap(bitmap, -i2, 0.0f, (Paint) null);
                }
                this.mCamera.restore();
                canvas.restore();
            }
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public boolean onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutChildren();
        return true;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onClosed() {
        super.onClosed();
        recycleScreenshots();
        setRefreshing(false);
        layoutChildren();
    }

    protected void recycleScreenshots() {
        this.lastScreenDirection = 0;
        this.mScreenshots = null;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        if (this.mScrimColor != 0 && this.mBaseAlpha != 0) {
            this.mPaint.setAlpha((int) (this.mBaseAlpha * (1.0f - SmartSwipe.ensureBetween(this.mProgress, 0.0f, 1.0f))));
        }
        if (this.mRefreshable) {
            return;
        }
        this.mWrapper.postInvalidate();
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public int clampDistanceHorizontal(int i, int i2) {
        if (this.mScreenshots == null && this.mWaitForScreenshot) {
            return 0;
        }
        return super.clampDistanceHorizontal(i, i2);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public int clampDistanceVertical(int i, int i2) {
        if (this.mScreenshots == null && this.mWaitForScreenshot) {
            return 0;
        }
        return super.clampDistanceVertical(i, i2);
    }

    public int getScrimColor() {
        return this.mScrimColor;
    }

    public ShuttersConsumer setScrimColor(int i) {
        this.mScrimColor = i;
        this.mPaint.setColor(i);
        this.mBaseAlpha = (this.mScrimColor & (-16777216)) >>> 24;
        return this;
    }

    public int getLeavesCount() {
        return this.mLeavesCount;
    }

    public ShuttersConsumer setLeavesCount(int i) {
        int ensureBetween = SmartSwipe.ensureBetween(i, 1, 100);
        if (ensureBetween != this.mLeavesCount) {
            this.mLeavesCount = ensureBetween;
            recycleScreenshots();
        }
        return this;
    }

    public boolean isRefreshable() {
        return this.mRefreshable;
    }

    public ShuttersConsumer setRefreshable(boolean z) {
        this.mRefreshable = z;
        return this;
    }

    public boolean isWaitForScreenshot() {
        return this.mWaitForScreenshot;
    }

    public ShuttersConsumer setWaitForScreenshot(boolean z) {
        this.mWaitForScreenshot = z;
        return this;
    }

    public ShuttersConsumer setRefreshFrameRate(int i) {
        this.refreshDelay = 1000 / SmartSwipe.ensureBetween(i, 1, 60);
        return this;
    }
}
