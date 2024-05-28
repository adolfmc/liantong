package com.billy.android.swipe.consumer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.internal.SwipeHelper;
import com.billy.android.swipe.internal.ViewCompat;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BezierBackConsumer extends SwipeConsumer {
    protected int mArrowSize;
    protected boolean mCenter;
    protected int mColor;
    protected float mLastThickness;
    protected int mSize;
    protected float mThickness;
    protected final Paint mPaint = new Paint();
    protected final Path mPath = new Path();
    protected final PointF mPathStart = new PointF();
    protected final PointF mPathControl1 = new PointF();
    protected final PointF mPathControl2 = new PointF();
    protected final PointF mPathControl = new PointF();
    protected final PointF mPathControl3 = new PointF();
    protected final PointF mPathControl4 = new PointF();
    protected final PointF mPathEnd = new PointF();
    protected final Paint mPaintArrow = new Paint();
    protected final Path mPathArrow = new Path();
    protected Rect mDrawRect = new Rect();
    protected int mArrowColor = -855310;

    public BezierBackConsumer() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaintArrow.setAntiAlias(true);
        this.mPaintArrow.setStyle(Paint.Style.STROKE);
        this.mPaintArrow.setColor(this.mArrowColor);
        this.mPaintArrow.setStrokeWidth(4.0f);
        this.mPaintArrow.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onAttachToWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeHelper swipeHelper) {
        Context context = smartSwipeWrapper.getContext();
        if (this.mSize == 0) {
            this.mSize = SmartSwipe.dp2px(200, context);
        }
        if (this.mArrowSize == 0) {
            this.mArrowSize = SmartSwipe.dp2px(4, context);
        }
        if (this.mOpenDistance == 0) {
            this.mOpenDistance = SmartSwipe.dp2px(30, context);
        }
        super.onAttachToWrapper(smartSwipeWrapper, swipeHelper);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x011a  */
    @Override // com.billy.android.swipe.SwipeConsumer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSwipeAccepted(int r10, boolean r11, float r12, float r13) {
        /*
            Method dump skipped, instructions count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.billy.android.swipe.consumer.BezierBackConsumer.onSwipeAccepted(int, boolean, float, float):void");
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        if ((this.mDirection & 3) != 0) {
            this.mThickness = Math.abs(i);
        } else if ((this.mDirection & 12) == 0) {
            return;
        } else {
            this.mThickness = Math.abs(i2);
        }
        if (this.mThickness != this.mLastThickness) {
            ViewCompat.postInvalidateOnAnimation(this.mWrapper);
        }
        this.mLastThickness = this.mThickness;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void dispatchDraw(Canvas canvas) {
        int i = this.mDirection;
        if (i == 4) {
            PointF pointF = this.mPathControl3;
            PointF pointF2 = this.mPathControl2;
            PointF pointF3 = this.mPathControl;
            float f = this.mThickness;
            pointF3.y = f;
            pointF2.y = f;
            pointF.y = f;
        } else if (i != 8) {
            switch (i) {
                case 1:
                    PointF pointF4 = this.mPathControl3;
                    PointF pointF5 = this.mPathControl2;
                    PointF pointF6 = this.mPathControl;
                    float f2 = this.mThickness;
                    pointF6.x = f2;
                    pointF5.x = f2;
                    pointF4.x = f2;
                    break;
                case 2:
                    PointF pointF7 = this.mPathControl3;
                    PointF pointF8 = this.mPathControl2;
                    PointF pointF9 = this.mPathControl;
                    float f3 = this.mWidth - this.mThickness;
                    pointF9.x = f3;
                    pointF8.x = f3;
                    pointF7.x = f3;
                    break;
            }
        } else {
            PointF pointF10 = this.mPathControl3;
            PointF pointF11 = this.mPathControl2;
            PointF pointF12 = this.mPathControl;
            float f4 = this.mHeight - this.mThickness;
            pointF12.y = f4;
            pointF11.y = f4;
            pointF10.y = f4;
        }
        float progress = getProgress();
        this.mPaint.setAlpha((int) (SmartSwipe.ensureBetween(progress, 0.2f, 0.8f) * 255.0f));
        this.mPath.reset();
        this.mPath.moveTo(this.mPathStart.x, this.mPathStart.y);
        this.mPath.cubicTo(this.mPathControl1.x, this.mPathControl1.y, this.mPathControl2.x, this.mPathControl2.y, this.mPathControl.x, this.mPathControl.y);
        this.mPath.cubicTo(this.mPathControl3.x, this.mPathControl3.y, this.mPathControl4.x, this.mPathControl4.y, this.mPathEnd.x, this.mPathEnd.y);
        canvas.drawPath(this.mPath, this.mPaint);
        drawArrow(canvas, this.mPaintArrow, this.mThickness, progress);
    }

    protected void drawArrow(Canvas canvas, Paint paint, float f, float f2) {
        boolean z;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float ensureBetween = SmartSwipe.ensureBetween(f2, 0.0f, 1.0f);
        float f8 = ((double) ensureBetween) >= 0.5d ? (ensureBetween - 0.5f) * this.mArrowSize * 2.0f : 0.0f;
        float f9 = f / 2.0f;
        int i = this.mDirection;
        if (i != 4 && i != 8) {
            switch (i) {
                case 1:
                case 2:
                    z = this.mDirection == 1;
                    if (!z) {
                        f9 = this.mWidth - f9;
                    }
                    f3 = this.mPathControl.y;
                    f5 = (f8 * (z ? 1 : -1)) + f9;
                    f4 = this.mPathControl.y - this.mArrowSize;
                    f7 = this.mPathControl.y + this.mArrowSize;
                    f6 = f5;
                    break;
                default:
                    return;
            }
        } else {
            z = this.mDirection == 4;
            if (!z) {
                f9 = this.mHeight - f9;
            }
            f3 = f9;
            f9 = this.mPathControl.x;
            f4 = f3 + (f8 * (z ? 1 : -1));
            f5 = this.mPathControl.x - this.mArrowSize;
            f6 = this.mPathControl.x + this.mArrowSize;
            f7 = f4;
        }
        this.mPaintArrow.setAlpha((int) (ensureBetween * 255.0f));
        this.mPathArrow.reset();
        this.mPathArrow.moveTo(f5, f4);
        this.mPathArrow.lineTo(f9, f3);
        this.mPathArrow.lineTo(f6, f7);
        canvas.drawPath(this.mPathArrow, paint);
    }

    public BezierBackConsumer setSize(int i) {
        this.mSize = i;
        return this;
    }

    protected int getSize() {
        return this.mSize;
    }

    public int getColor() {
        return this.mColor;
    }

    public BezierBackConsumer setColor(int i) {
        this.mColor = i;
        this.mPaint.setColor(i);
        return this;
    }

    public int getArrowColor() {
        return this.mArrowColor;
    }

    public BezierBackConsumer setArrowColor(int i) {
        this.mArrowColor = i;
        this.mPaintArrow.setColor(i);
        return this;
    }

    public boolean isCenter() {
        return this.mCenter;
    }

    public BezierBackConsumer setCenter(boolean z) {
        this.mCenter = z;
        return this;
    }
}
