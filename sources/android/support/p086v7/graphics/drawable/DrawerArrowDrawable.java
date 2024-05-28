package android.support.p086v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo;
import android.support.p083v4.graphics.drawable.DrawableCompat;
import android.support.p086v7.appcompat.C1276R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.graphics.drawable.DrawerArrowDrawable */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float ARROW_HEAD_ANGLE = (float) Math.toRadians(45.0d);
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private float mMaxCutForBarSize;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private final Paint mPaint = new Paint();
    private final Path mPath = new Path();
    private boolean mVerticalMirror = false;
    private int mDirection = 2;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.graphics.drawable.DrawerArrowDrawable$ArrowDirection */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface ArrowDirection {
    }

    private static float lerp(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public DrawerArrowDrawable(Context context) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.MITER);
        this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        this.mPaint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, C1276R.styleable.DrawerArrowToggle, C1276R.attr.drawerArrowStyle, C1276R.C1281style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(obtainStyledAttributes.getColor(C1276R.styleable.DrawerArrowToggle_color, 0));
        setBarThickness(obtainStyledAttributes.getDimension(C1276R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(obtainStyledAttributes.getBoolean(C1276R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize(Math.round(obtainStyledAttributes.getDimension(C1276R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.mSize = obtainStyledAttributes.getDimensionPixelSize(C1276R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarLength = Math.round(obtainStyledAttributes.getDimension(C1276R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.mArrowHeadLength = Math.round(obtainStyledAttributes.getDimension(C1276R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.mArrowShaftLength = obtainStyledAttributes.getDimension(C1276R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public void setArrowHeadLength(float f) {
        if (this.mArrowHeadLength != f) {
            this.mArrowHeadLength = f;
            invalidateSelf();
        }
    }

    public float getArrowHeadLength() {
        return this.mArrowHeadLength;
    }

    public void setArrowShaftLength(float f) {
        if (this.mArrowShaftLength != f) {
            this.mArrowShaftLength = f;
            invalidateSelf();
        }
    }

    public float getArrowShaftLength() {
        return this.mArrowShaftLength;
    }

    public float getBarLength() {
        return this.mBarLength;
    }

    public void setBarLength(float f) {
        if (this.mBarLength != f) {
            this.mBarLength = f;
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i) {
        if (i != this.mPaint.getColor()) {
            this.mPaint.setColor(i);
            invalidateSelf();
        }
    }

    @ColorInt
    public int getColor() {
        return this.mPaint.getColor();
    }

    public void setBarThickness(float f) {
        if (this.mPaint.getStrokeWidth() != f) {
            this.mPaint.setStrokeWidth(f);
            this.mMaxCutForBarSize = (float) ((f / 2.0f) * Math.cos(ARROW_HEAD_ANGLE));
            invalidateSelf();
        }
    }

    public float getBarThickness() {
        return this.mPaint.getStrokeWidth();
    }

    public float getGapSize() {
        return this.mBarGap;
    }

    public void setGapSize(float f) {
        if (f != this.mBarGap) {
            this.mBarGap = f;
            invalidateSelf();
        }
    }

    public void setDirection(int i) {
        if (i != this.mDirection) {
            this.mDirection = i;
            invalidateSelf();
        }
    }

    public boolean isSpinEnabled() {
        return this.mSpin;
    }

    public void setSpinEnabled(boolean z) {
        if (this.mSpin != z) {
            this.mSpin = z;
            invalidateSelf();
        }
    }

    public int getDirection() {
        return this.mDirection;
    }

    public void setVerticalMirror(boolean z) {
        if (this.mVerticalMirror != z) {
            this.mVerticalMirror = z;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f;
        Rect bounds = getBounds();
        int i = this.mDirection;
        boolean z = false;
        if (i == 3) {
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                z = true;
            }
        } else {
            switch (i) {
                case 0:
                    break;
                case 1:
                    z = true;
                    break;
                default:
                    if (DrawableCompat.getLayoutDirection(this) == 1) {
                        z = true;
                        break;
                    }
                    break;
            }
        }
        float f2 = this.mArrowHeadLength;
        float lerp = lerp(this.mBarLength, (float) Math.sqrt(f2 * f2 * 2.0f), this.mProgress);
        float lerp2 = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
        float round = Math.round(lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        float lerp3 = lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        float lerp4 = lerp(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.mProgress);
        double d = lerp;
        double d2 = lerp3;
        float round2 = (float) Math.round(Math.cos(d2) * d);
        float round3 = (float) Math.round(d * Math.sin(d2));
        this.mPath.rewind();
        float lerp5 = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
        float f3 = (-lerp2) / 2.0f;
        this.mPath.moveTo(f3 + round, 0.0f);
        this.mPath.rLineTo(lerp2 - (round * 2.0f), 0.0f);
        this.mPath.moveTo(f3, lerp5);
        this.mPath.rLineTo(round2, round3);
        this.mPath.moveTo(f3, -lerp5);
        this.mPath.rLineTo(round2, -round3);
        this.mPath.close();
        canvas.save();
        float strokeWidth = this.mPaint.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), ((((int) (height - (2.0f * f))) / 4) * 2) + (strokeWidth * 1.5f) + this.mBarGap);
        if (this.mSpin) {
            canvas.rotate(lerp4 * (this.mVerticalMirror ^ z ? -1 : 1));
        } else if (z) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mSize;
    }

    @FloatRange(from = 0.0d, m22210to = 1.0d)
    public float getProgress() {
        return this.mProgress;
    }

    public void setProgress(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        if (this.mProgress != f) {
            this.mProgress = f;
            invalidateSelf();
        }
    }

    public final Paint getPaint() {
        return this.mPaint;
    }
}
