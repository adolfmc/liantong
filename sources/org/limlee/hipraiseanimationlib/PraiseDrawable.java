package org.limlee.hipraiseanimationlib;

import android.animation.FloatEvaluator;
import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.Random;
import org.limlee.hipraiseanimationlib.base.IDrawable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PraiseDrawable implements IDrawable {
    private static final long MIN_END_POINTY = 16;
    private static final String TAG = "PraiseDrawable";
    private float alpha;
    private Bitmap bitmap;
    private int bitmapHeight;
    private int bitmapWidth;
    private int canvasHeight;
    private int canvasWidth;
    private PointF curPoint;
    private long delayAphaAnimTime;
    private long duration;
    private long endFrameTime;
    private PointF endPoint;
    private float endYPercentage;
    private boolean isFinished;
    private boolean isStarted;
    private float scale;
    private long startAplhaAnimTime;
    private long startDelay;
    private long startFrameTime;
    private PointF startPoint;
    private BezierEvaluator xFrameEvaluator;
    private Matrix mMatrix = new Matrix();
    private Paint mPaint = new Paint();
    private FloatEvaluator mScaleEvaluator = new FloatEvaluator();
    private FloatEvaluator mAlphaEvaluator = new FloatEvaluator();
    private TimeInterpolator mScaleTimeInterpolator = new LinearInterpolator();
    private TimeInterpolator mFrameTimeInterpolator = new AccelerateInterpolator(0.8f);
    private TimeInterpolator mAlphaTimeInterpolator = new DecelerateInterpolator(0.5f);

    public PraiseDrawable(@NonNull Bitmap bitmap, float f, float f2, long j, long j2, long j3, float f3) {
        this.bitmap = bitmap;
        this.endYPercentage = Math.min(Math.max(0.0f, f3), 1.0f);
        this.scale = f;
        this.alpha = f2;
        this.duration = j;
        this.startDelay = j2;
        this.delayAphaAnimTime = j3;
        this.bitmapWidth = (int) (bitmap.getWidth() * f);
        this.bitmapHeight = (int) (bitmap.getHeight() * f);
    }

    private PointF genStartPoint() {
        float f = this.canvasWidth / 2;
        int i = this.bitmapHeight;
        int i2 = this.canvasHeight;
        return new PointF(f, i > i2 ? i2 : i2 - i);
    }

    private PointF genEndPoint(PointF pointF, PointF pointF2) {
        return new PointF((pointF.x + pointF2.x) / 2.0f, (float) getEndPointY());
    }

    private long getEndPointY() {
        long j = this.startPoint.y * this.endYPercentage;
        if (j < 16) {
            return 16L;
        }
        return j;
    }

    private PointF genRandomPoint2() {
        float nextInt;
        long endPointY = getEndPointY();
        float f = this.startPoint.x;
        int i = this.bitmapWidth;
        float f2 = i / 2;
        int i2 = this.canvasWidth + (i / 2);
        float f3 = (float) endPointY;
        float f4 = ((this.startPoint.y - f3) / 3.0f) + f3;
        int i3 = (int) ((((this.startPoint.y - f3) / 3.0f) * 2.0f) + f3);
        do {
            nextInt = (new Random().nextInt(i2) % ((i2 - f2) + 1.0f)) + f2;
        } while (nextInt == f);
        return new PointF(nextInt, (new Random().nextInt(i3) % ((i3 - f4) + 1.0f)) + f4);
    }

    private PointF genRandomPoint1(PointF pointF) {
        float nextInt;
        long endPointY = getEndPointY();
        float f = this.startPoint.x;
        int i = this.bitmapWidth;
        float f2 = i / 2;
        int i2 = this.canvasWidth + (i / 2);
        float f3 = (float) endPointY;
        int i3 = (int) (((this.startPoint.y - f3) / 3.0f) + f3);
        if (pointF.x > f) {
            do {
                nextInt = (new Random().nextInt(i2) % ((i2 - f2) + 1.0f)) + f2;
            } while (nextInt >= f);
        } else {
            do {
                nextInt = (new Random().nextInt(i2) % ((i2 - f2) + 1.0f)) + f2;
            } while (nextInt <= f);
        }
        return new PointF(nextInt, (new Random().nextInt(i3) % ((i3 - f3) + 1.0f)) + f3);
    }

    @Override // org.limlee.hipraiseanimationlib.base.IDrawable
    public boolean isFinished() {
        return this.isFinished;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r6.startDelay < r2) goto L11;
     */
    @Override // org.limlee.hipraiseanimationlib.base.IDrawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r7, long r8) {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.limlee.hipraiseanimationlib.PraiseDrawable.draw(android.graphics.Canvas, long):void");
    }

    private float drawScale(float f) {
        float f2 = f * 4.5f;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return this.mScaleEvaluator.evaluate(this.mScaleTimeInterpolator.getInterpolation(f2), (Number) Float.valueOf(0.0f), (Number) Float.valueOf(this.scale)).floatValue();
    }

    private float drawAlpah(float f) {
        return this.mAlphaEvaluator.evaluate(this.mAlphaTimeInterpolator.getInterpolation(f), (Number) Float.valueOf(this.alpha), (Number) Float.valueOf(0.0f)).floatValue();
    }

    private PointF drawFrame(float f, PointF pointF, PointF pointF2) {
        return this.xFrameEvaluator.evaluate(this.mFrameTimeInterpolator.getInterpolation(f), pointF, pointF2);
    }
}
