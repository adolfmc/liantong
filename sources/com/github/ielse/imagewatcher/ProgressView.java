package com.github.ielse.imagewatcher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ProgressView extends View {
    private AnimationDrawable mDrawable;
    private float mScale;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScale = 1.0f;
        initView(new MaterialProgressDrawable(getContext(), this));
    }

    private void initView(AnimationDrawable animationDrawable) {
        this.mDrawable = animationDrawable;
        this.mDrawable.setAlpha(255);
        this.mDrawable.setCallback(this);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.mDrawable) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    public boolean isRunning() {
        return this.mDrawable.isRunning();
    }

    public void start() {
        this.mDrawable.start();
    }

    public void stop() {
        this.mDrawable.stop();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.mDrawable.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom(), 1073741824));
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
        this.mDrawable.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = this.mDrawable.getBounds();
        canvas.translate(getPaddingLeft() + ((getMeasuredWidth() - this.mDrawable.getIntrinsicWidth()) / 2), getPaddingTop());
        float f = this.mScale;
        canvas.scale(f, f, bounds.exactCenterX(), bounds.exactCenterY());
        this.mDrawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class MaterialProgressDrawable extends AnimationDrawable {
        private static final int ANIMATION_DURATION = 1333;
        private static final float CENTER_RADIUS = 8.0f;
        private static final int CIRCLE_DIAMETER = 30;
        private static final float MAX_PROGRESS_ARC = 0.8f;
        private static final float NUM_POINTS = 5.0f;
        private static final float STROKE_WIDTH = 2.0f;
        private Animation mAnimation;
        private Animation mFinishAnimation;
        private double mHeight;
        private View mParent;
        private Resources mResources;
        private float mRotation;
        private float mRotationCount;
        private double mWidth;
        private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
        private static final Interpolator END_CURVE_INTERPOLATOR = new EndCurveInterpolator();
        private static final Interpolator START_CURVE_INTERPOLATOR = new StartCurveInterpolator();
        private static final Interpolator EASE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
        private final int[] COLORS = {-1, -1, -1, -1};
        private final ArrayList<Animation> mAnimators = new ArrayList<>();
        private final Drawable.Callback mCallback = new Drawable.Callback() { // from class: com.github.ielse.imagewatcher.ProgressView.MaterialProgressDrawable.1
            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                MaterialProgressDrawable.this.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
                MaterialProgressDrawable.this.scheduleSelf(runnable, j);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                MaterialProgressDrawable.this.unscheduleSelf(runnable);
            }
        };
        private final Ring mRing = new Ring(this.mCallback);

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        public MaterialProgressDrawable(Context context, View view) {
            this.mParent = view;
            this.mResources = context.getResources();
            this.mRing.setColors(this.COLORS);
            setSizeParameters(30.0d, 30.0d, 8.0d, 2.0d);
            setupAnimators();
        }

        private void setSizeParameters(double d, double d2, double d3, double d4) {
            Ring ring = this.mRing;
            float f = this.mResources.getDisplayMetrics().density;
            double d5 = f;
            this.mWidth = d * d5;
            this.mHeight = d2 * d5;
            ring.setStrokeWidth(((float) d4) * f);
            ring.setCenterRadius(d3 * d5);
            ring.setColorIndex(0);
            ring.setInsets((int) this.mWidth, (int) this.mHeight);
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return (int) this.mHeight;
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) this.mWidth;
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            int save = canvas.save();
            canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
            this.mRing.draw(canvas, bounds);
            canvas.restoreToCount(save);
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.mRing.getAlpha();
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mRing.setAlpha(i);
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.mRing.setColorFilter(colorFilter);
        }

        void setRotation(float f) {
            this.mRotation = f;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
        public boolean isRunning() {
            ArrayList<Animation> arrayList = this.mAnimators;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Animation animation = arrayList.get(i);
                if (animation.hasStarted() && !animation.hasEnded()) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
        public void start() {
            this.mAnimation.reset();
            this.mRing.storeOriginals();
            if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
                this.mParent.startAnimation(this.mFinishAnimation);
                return;
            }
            this.mRing.setColorIndex(0);
            this.mRing.resetOriginals();
            this.mParent.startAnimation(this.mAnimation);
        }

        @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
        public void stop() {
            this.mParent.clearAnimation();
            setRotation(0.0f);
            this.mRing.setShowArrow(false);
            this.mRing.setColorIndex(0);
            this.mRing.resetOriginals();
        }

        private void setupAnimators() {
            final Ring ring = this.mRing;
            Animation animation = new Animation() { // from class: com.github.ielse.imagewatcher.ProgressView.MaterialProgressDrawable.2
                @Override // android.view.animation.Animation
                public void applyTransformation(float f, Transformation transformation) {
                    float floor = (float) (Math.floor(ring.getStartingRotation() / 0.8f) + 1.0d);
                    ring.setStartTrim(ring.getStartingStartTrim() + ((ring.getStartingEndTrim() - ring.getStartingStartTrim()) * f));
                    ring.setRotation(ring.getStartingRotation() + ((floor - ring.getStartingRotation()) * f));
                    ring.setArrowScale(1.0f - f);
                }
            };
            animation.setInterpolator(EASE_INTERPOLATOR);
            animation.setDuration(666L);
            animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.github.ielse.imagewatcher.ProgressView.MaterialProgressDrawable.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    ring.goToNextColor();
                    ring.storeOriginals();
                    ring.setShowArrow(false);
                    MaterialProgressDrawable.this.mParent.startAnimation(MaterialProgressDrawable.this.mAnimation);
                }
            });
            Animation animation2 = new Animation() { // from class: com.github.ielse.imagewatcher.ProgressView.MaterialProgressDrawable.4
                @Override // android.view.animation.Animation
                public void applyTransformation(float f, Transformation transformation) {
                    float radians = (float) Math.toRadians(ring.getStrokeWidth() / (ring.getCenterRadius() * 6.283185307179586d));
                    float startingEndTrim = ring.getStartingEndTrim();
                    float startingStartTrim = ring.getStartingStartTrim();
                    float startingRotation = ring.getStartingRotation();
                    ring.setEndTrim(startingEndTrim + ((0.8f - radians) * MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(f)));
                    ring.setStartTrim(startingStartTrim + (MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(f) * 0.8f));
                    ring.setRotation(startingRotation + (0.25f * f));
                    MaterialProgressDrawable.this.setRotation((f * 144.0f) + ((MaterialProgressDrawable.this.mRotationCount / 5.0f) * 720.0f));
                }
            };
            animation2.setRepeatCount(-1);
            animation2.setRepeatMode(1);
            animation2.setInterpolator(LINEAR_INTERPOLATOR);
            animation2.setDuration(1333L);
            animation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.github.ielse.imagewatcher.ProgressView.MaterialProgressDrawable.5
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation3) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation3) {
                    MaterialProgressDrawable.this.mRotationCount = 0.0f;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation3) {
                    ring.storeOriginals();
                    ring.goToNextColor();
                    Ring ring2 = ring;
                    ring2.setStartTrim(ring2.getEndTrim());
                    MaterialProgressDrawable materialProgressDrawable = MaterialProgressDrawable.this;
                    materialProgressDrawable.mRotationCount = (materialProgressDrawable.mRotationCount + 1.0f) % 5.0f;
                }
            });
            this.mFinishAnimation = animation;
            this.mAnimation = animation2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class Ring {
            private int mAlpha;
            private float mArrowScale;
            private int mColorIndex;
            private int[] mColors;
            private final Drawable.Callback mRingCallback;
            private double mRingCenterRadius;
            private boolean mShowArrow;
            private float mStartingEndTrim;
            private float mStartingRotation;
            private float mStartingStartTrim;
            private final RectF mTempBounds = new RectF();
            private final Paint mArcPaint = new Paint();
            private final Paint mArrowPaint = new Paint();
            private final Paint mCirclePaint = new Paint();
            private float mStartTrim = 0.0f;
            private float mEndTrim = 0.0f;
            private float mRotation = 0.0f;
            private float mStrokeWidth = 5.0f;
            private float mStrokeInset = 2.5f;

            public Ring(Drawable.Callback callback) {
                this.mRingCallback = callback;
                this.mArcPaint.setStrokeCap(Paint.Cap.SQUARE);
                this.mArcPaint.setAntiAlias(true);
                this.mArcPaint.setStyle(Paint.Style.STROKE);
                this.mArrowPaint.setStyle(Paint.Style.FILL);
                this.mArrowPaint.setAntiAlias(true);
                this.mCirclePaint.setAntiAlias(true);
            }

            public void draw(Canvas canvas, Rect rect) {
                RectF rectF = this.mTempBounds;
                rectF.set(rect);
                float f = this.mStrokeInset;
                rectF.inset(f, f);
                float f2 = this.mStartTrim;
                float f3 = this.mRotation;
                float f4 = (f2 + f3) * 360.0f;
                this.mArcPaint.setColor(this.mColors[this.mColorIndex]);
                this.mArcPaint.setAlpha(this.mAlpha);
                canvas.drawArc(rectF, f4, ((this.mEndTrim + f3) * 360.0f) - f4, false, this.mArcPaint);
            }

            public void setColors(int[] iArr) {
                this.mColors = iArr;
                setColorIndex(0);
            }

            public void setColorIndex(int i) {
                this.mColorIndex = i;
            }

            public void goToNextColor() {
                this.mColorIndex = (this.mColorIndex + 1) % this.mColors.length;
            }

            public void setColorFilter(ColorFilter colorFilter) {
                this.mArcPaint.setColorFilter(colorFilter);
                invalidateSelf();
            }

            public int getAlpha() {
                return this.mAlpha;
            }

            public void setAlpha(int i) {
                this.mAlpha = i;
            }

            public float getStrokeWidth() {
                return this.mStrokeWidth;
            }

            public void setStrokeWidth(float f) {
                this.mStrokeWidth = f;
                this.mArcPaint.setStrokeWidth(f);
                invalidateSelf();
            }

            public float getStartTrim() {
                return this.mStartTrim;
            }

            public void setStartTrim(float f) {
                this.mStartTrim = f;
                invalidateSelf();
            }

            public float getStartingStartTrim() {
                return this.mStartingStartTrim;
            }

            public float getStartingEndTrim() {
                return this.mStartingEndTrim;
            }

            public float getEndTrim() {
                return this.mEndTrim;
            }

            public void setEndTrim(float f) {
                this.mEndTrim = f;
                invalidateSelf();
            }

            public void setRotation(float f) {
                this.mRotation = f;
                invalidateSelf();
            }

            public void setInsets(int i, int i2) {
                float min = Math.min(i, i2);
                double d = this.mRingCenterRadius;
                this.mStrokeInset = (d <= 0.0d || min < 0.0f) ? (float) Math.ceil(this.mStrokeWidth / 2.0f) : (float) ((min / 2.0f) - d);
            }

            public float getInsets() {
                return this.mStrokeInset;
            }

            public double getCenterRadius() {
                return this.mRingCenterRadius;
            }

            public void setCenterRadius(double d) {
                this.mRingCenterRadius = d;
            }

            public void setShowArrow(boolean z) {
                if (this.mShowArrow != z) {
                    this.mShowArrow = z;
                    invalidateSelf();
                }
            }

            public void setArrowScale(float f) {
                if (f != this.mArrowScale) {
                    this.mArrowScale = f;
                    invalidateSelf();
                }
            }

            public float getStartingRotation() {
                return this.mStartingRotation;
            }

            public void storeOriginals() {
                this.mStartingStartTrim = this.mStartTrim;
                this.mStartingEndTrim = this.mEndTrim;
                this.mStartingRotation = this.mRotation;
            }

            public void resetOriginals() {
                this.mStartingStartTrim = 0.0f;
                this.mStartingEndTrim = 0.0f;
                this.mStartingRotation = 0.0f;
                setStartTrim(0.0f);
                setEndTrim(0.0f);
                setRotation(0.0f);
            }

            private void invalidateSelf() {
                this.mRingCallback.invalidateDrawable(null);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
            private EndCurveInterpolator() {
            }

            @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return super.getInterpolation(Math.max(0.0f, (f - 0.5f) * 2.0f));
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
            private StartCurveInterpolator() {
            }

            @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return super.getInterpolation(Math.min(1.0f, f * 2.0f));
            }
        }
    }
}
