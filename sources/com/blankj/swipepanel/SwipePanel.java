package com.blankj.swipepanel;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SwipePanel extends FrameLayout {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    private static final Object LOCK = new Object();
    public static final int RIGHT = 2;
    private static final String TAG = "SwipePanel";
    public static final int TOP = 1;
    private static final float TRIGGER_PROGRESS = 0.95f;
    private static TypedValue sTempValue;
    private float curPathX;
    private float curPathY;
    private float halfSize;
    private float mCurrentX;
    private float mCurrentY;
    private float[] mDown;
    private float mDownX;
    private float mDownY;
    private Drawable[] mDrawables;
    private int[] mEdgeSizes;
    private boolean[] mEnabled;
    private int mHeight;
    private boolean[] mIsCenter;
    private boolean mIsEdgeStart;
    private boolean[] mIsStart;
    private int mLimit;
    private OnFullSwipeListener mListener;
    private Paint mPaint;
    private int[] mPaintColor;
    private Path[] mPath;
    private OnProgressChangedListener mProgressListener;
    private Rect mRect;
    private int mStartDirection;
    private int mTouchSlop;
    private int mWidth;
    private float[] preProgresses;
    private float[] progresses;
    private float unit;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface Direction {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnFullSwipeListener {
        void onFullSwipe(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnProgressChangedListener {
        void onProgressChanged(int i, float f, boolean z);
    }

    public SwipePanel(@NonNull Context context) {
        this(context, null);
    }

    public SwipePanel(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPath = new Path[4];
        this.mPaintColor = new int[4];
        this.mEdgeSizes = new int[4];
        this.mDrawables = new Drawable[4];
        this.mIsStart = new boolean[4];
        this.mDown = new float[4];
        this.progresses = new float[4];
        this.preProgresses = new float[4];
        this.mIsCenter = new boolean[4];
        this.mEnabled = new boolean[]{true, true, true, true};
        this.mRect = new Rect();
        this.mStartDirection = -1;
        int scaledEdgeSlop = ViewConfiguration.get(context).getScaledEdgeSlop();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mPaint = new Paint(5);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.halfSize = dp2px(72.0f);
        this.unit = this.halfSize / 16.0f;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3367R.styleable.SwipePanel);
            setLeftSwipeColor(obtainStyledAttributes.getColor(C3367R.styleable.SwipePanel_leftSwipeColor, -16777216));
            setTopSwipeColor(obtainStyledAttributes.getColor(C3367R.styleable.SwipePanel_topSwipeColor, -16777216));
            setRightSwipeColor(obtainStyledAttributes.getColor(C3367R.styleable.SwipePanel_rightSwipeColor, -16777216));
            setBottomSwipeColor(obtainStyledAttributes.getColor(C3367R.styleable.SwipePanel_bottomSwipeColor, -16777216));
            setLeftEdgeSize(obtainStyledAttributes.getDimensionPixelSize(C3367R.styleable.SwipePanel_leftEdgeSize, scaledEdgeSlop));
            setTopEdgeSize(obtainStyledAttributes.getDimensionPixelSize(C3367R.styleable.SwipePanel_topEdgeSize, scaledEdgeSlop));
            setRightEdgeSize(obtainStyledAttributes.getDimensionPixelSize(C3367R.styleable.SwipePanel_rightEdgeSize, scaledEdgeSlop));
            setBottomEdgeSize(obtainStyledAttributes.getDimensionPixelSize(C3367R.styleable.SwipePanel_bottomEdgeSize, scaledEdgeSlop));
            setLeftDrawable(obtainStyledAttributes.getDrawable(C3367R.styleable.SwipePanel_leftDrawable));
            setTopDrawable(obtainStyledAttributes.getDrawable(C3367R.styleable.SwipePanel_topDrawable));
            setRightDrawable(obtainStyledAttributes.getDrawable(C3367R.styleable.SwipePanel_rightDrawable));
            setBottomDrawable(obtainStyledAttributes.getDrawable(C3367R.styleable.SwipePanel_bottomDrawable));
            setLeftCenter(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isLeftCenter, false));
            setTopCenter(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isTopCenter, false));
            setRightCenter(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isRightCenter, false));
            setBottomCenter(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isBottomCenter, false));
            setLeftEnabled(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isLeftEnabled, true));
            setTopEnabled(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isTopEnabled, true));
            setRightEnabled(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isRightEnabled, true));
            setBottomEnabled(obtainStyledAttributes.getBoolean(C3367R.styleable.SwipePanel_isBottomEnabled, true));
            obtainStyledAttributes.recycle();
        }
    }

    public void setLeftSwipeColor(int i) {
        setSwipeColor(i, 0);
    }

    public void setTopSwipeColor(int i) {
        setSwipeColor(i, 1);
    }

    public void setRightSwipeColor(int i) {
        setSwipeColor(i, 2);
    }

    public void setBottomSwipeColor(int i) {
        setSwipeColor(i, 3);
    }

    private void setSwipeColor(int i, int i2) {
        this.mPaintColor[i2] = i;
    }

    public void setLeftEdgeSize(int i) {
        this.mEdgeSizes[0] = i;
    }

    public void setTopEdgeSize(int i) {
        this.mEdgeSizes[1] = i;
    }

    public void setRightEdgeSize(int i) {
        this.mEdgeSizes[2] = i;
    }

    public void setBottomEdgeSize(int i) {
        this.mEdgeSizes[3] = i;
    }

    public void setLeftDrawable(@DrawableRes int i) {
        setDrawable(i, 0);
    }

    public void setTopDrawable(@DrawableRes int i) {
        setDrawable(i, 1);
    }

    public void setRightDrawable(@DrawableRes int i) {
        setDrawable(i, 2);
    }

    public void setBottomDrawable(@DrawableRes int i) {
        setDrawable(i, 3);
    }

    private void setDrawable(int i, int i2) {
        this.mDrawables[i2] = getDrawable(getContext(), i);
    }

    public void setLeftDrawable(Drawable drawable) {
        setDrawable(drawable, 0);
    }

    public void setTopDrawable(Drawable drawable) {
        setDrawable(drawable, 1);
    }

    public void setRightDrawable(Drawable drawable) {
        setDrawable(drawable, 2);
    }

    public void setBottomDrawable(Drawable drawable) {
        setDrawable(drawable, 3);
    }

    private void setDrawable(Drawable drawable, int i) {
        this.mDrawables[i] = drawable;
    }

    public Drawable getLeftDrawable() {
        return this.mDrawables[0];
    }

    public Drawable getTopDrawable() {
        return this.mDrawables[1];
    }

    public Drawable getRightDrawable() {
        return this.mDrawables[2];
    }

    public Drawable getBottomDrawable() {
        return this.mDrawables[3];
    }

    public void setLeftCenter(boolean z) {
        setCenter(z, 0);
    }

    public void setTopCenter(boolean z) {
        setCenter(z, 1);
    }

    public void setRightCenter(boolean z) {
        setCenter(z, 2);
    }

    public void setBottomCenter(boolean z) {
        setCenter(z, 3);
    }

    private void setCenter(boolean z, int i) {
        this.mIsCenter[i] = z;
    }

    public void setLeftEnabled(boolean z) {
        setEnabled(z, 0);
    }

    public void setTopEnabled(boolean z) {
        setEnabled(z, 1);
    }

    public void setRightEnabled(boolean z) {
        setEnabled(z, 2);
    }

    public void setBottomEnabled(boolean z) {
        setEnabled(z, 3);
    }

    private void setEnabled(boolean z, int i) {
        this.mEnabled[i] = z;
    }

    public void wrapView(@NonNull View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            int indexOfChild = viewGroup.indexOfChild(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            viewGroup.removeViewAt(indexOfChild);
            viewGroup.addView(this, indexOfChild, layoutParams);
            addView(view, -1, -1);
            return;
        }
        addView(view, -1, -1);
    }

    public void setOnFullSwipeListener(OnFullSwipeListener onFullSwipeListener) {
        this.mListener = onFullSwipeListener;
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.mProgressListener = onProgressChangedListener;
    }

    public boolean isOpen(int i) {
        return this.progresses[i] >= TRIGGER_PROGRESS;
    }

    public void close() {
        close(true);
    }

    public void close(int i) {
        close(i, true);
    }

    public void close(boolean z) {
        if (z) {
            animClose();
            return;
        }
        float[] fArr = this.progresses;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        postInvalidate();
    }

    public void close(int i, boolean z) {
        if (z) {
            animClose(i);
            return;
        }
        this.progresses[i] = 0.0f;
        postInvalidate();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = getMeasuredWidth();
        this.mHeight = getMeasuredHeight();
        this.mLimit = Math.min(this.mWidth, this.mHeight) / 3;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
        drawPath(canvas, 0);
        drawPath(canvas, 1);
        drawPath(canvas, 2);
        drawPath(canvas, 3);
    }

    private void drawPath(Canvas canvas, int i) {
        if (this.mPath[i] == null || this.progresses[i] <= 0.0f) {
            return;
        }
        updatePaint(i);
        canvas.drawPath(getPath(i), this.mPaint);
        drawIcon(canvas, i);
    }

    private Path getPath(int i) {
        if (this.preProgresses[i] != this.progresses[i]) {
            this.mPath[i].reset();
            float f = this.mDown[i];
            int i2 = -1;
            float f2 = 0.0f;
            if (i == 0) {
                i2 = 1;
            } else if (i == 1) {
                i2 = 1;
            } else if (i == 2) {
                f2 = this.mWidth;
            } else {
                f2 = this.mHeight;
            }
            if (i == 0 || i == 2) {
                this.curPathX = f2;
                this.curPathY = f - this.halfSize;
            } else {
                this.curPathX = f - this.halfSize;
                this.curPathY = f2;
            }
            this.mPath[i].moveTo(this.curPathX, this.curPathY);
            quad(f2, f - this.halfSize, i);
            float f3 = this.progresses[i];
            float f4 = this.unit;
            float f5 = i2;
            quad((f3 * f4 * f5) + f2, (f - this.halfSize) + (f4 * 5.0f), i);
            quad((this.progresses[i] * 10.0f * this.unit * f5) + f2, f, i);
            float f6 = this.progresses[i];
            float f7 = this.unit;
            quad((f6 * f7 * f5) + f2, (this.halfSize + f) - (f7 * 5.0f), i);
            quad(f2, this.halfSize + f, i);
            quad(f2, f + this.halfSize, i);
        }
        return this.mPath[i];
    }

    private void drawIcon(Canvas canvas, int i) {
        int i2;
        int i3;
        int i4;
        Drawable[] drawableArr = this.mDrawables;
        if (drawableArr[i] == null) {
            return;
        }
        int intrinsicWidth = drawableArr[i].getIntrinsicWidth();
        int intrinsicHeight = this.mDrawables[i].getIntrinsicHeight();
        int i5 = (int) (this.progresses[i] * 5.0f * this.unit);
        if (intrinsicWidth >= intrinsicHeight) {
            i4 = (intrinsicHeight * i5) / intrinsicWidth;
            i3 = i5 - i4;
            i2 = 0;
        } else {
            int i6 = (intrinsicWidth * i5) / intrinsicHeight;
            i2 = i5 - i6;
            i3 = 0;
            i5 = i6;
            i4 = i5;
        }
        if (i == 0) {
            Rect rect = this.mRect;
            rect.left = (int) ((this.progresses[i] * this.unit * 1.0f) + 0.0f + ((i2 / 2) * 1));
            rect.top = (int) (this.mDown[0] - (i4 / 2));
            rect.right = rect.left + i5;
            Rect rect2 = this.mRect;
            rect2.bottom = rect2.top + i4;
        } else if (i == 2) {
            Rect rect3 = this.mRect;
            rect3.right = (int) (this.mWidth + (this.progresses[i] * this.unit * (-1.0f)) + ((i2 / 2) * (-1)));
            rect3.top = (int) (this.mDown[2] - (i4 / 2.0f));
            rect3.left = rect3.right - i5;
            Rect rect4 = this.mRect;
            rect4.bottom = rect4.top + i4;
        } else if (i == 1) {
            Rect rect5 = this.mRect;
            rect5.left = (int) (this.mDown[1] - (i5 / 2));
            rect5.top = (int) ((this.progresses[i] * this.unit * 1.0f) + 0.0f + ((i3 / 2) * 1));
            rect5.right = rect5.left + i5;
            Rect rect6 = this.mRect;
            rect6.bottom = rect6.top + i4;
        } else {
            Rect rect7 = this.mRect;
            rect7.left = (int) (this.mDown[3] - (i5 / 2));
            rect7.bottom = (int) (this.mHeight + (this.progresses[i] * this.unit * (-1.0f)) + ((i3 / 2) * (-1)));
            rect7.top = rect7.bottom - i4;
            Rect rect8 = this.mRect;
            rect8.right = rect8.left + i5;
        }
        this.mDrawables[i].setBounds(this.mRect);
        this.mDrawables[i].draw(canvas);
    }

    private void quad(float f, float f2, int i) {
        float f3 = this.curPathX;
        float f4 = this.curPathY;
        if (i == 0 || i == 2) {
            this.curPathX = f;
            this.curPathY = f2;
        } else {
            this.curPathX = f2;
            this.curPathY = f;
        }
        this.mPath[i].quadTo(f3, f4, (this.curPathX + f3) / 2.0f, (this.curPathY + f4) / 2.0f);
    }

    private void updatePaint(int i) {
        this.mPaint.setColor(this.mPaintColor[i]);
        float f = this.progresses[i];
        if (f < 0.25f) {
            f = 0.25f;
        } else if (f > 0.75f) {
            f = 0.75f;
        }
        this.mPaint.setAlpha((int) (f * 255.0f));
    }

    private void animClose() {
        animClose(0);
        animClose(1);
        animClose(2);
        animClose(3);
    }

    private void animClose(final int i) {
        float[] fArr = this.progresses;
        if (fArr[i] > 0.0f) {
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr[i], 0.0f);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.blankj.swipepanel.SwipePanel.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SwipePanel.this.progresses[i] = ((Float) ofFloat.getAnimatedValue()).floatValue();
                    if (SwipePanel.this.mProgressListener != null) {
                        SwipePanel.this.mProgressListener.onProgressChanged(i, SwipePanel.this.progresses[i], false);
                    }
                    SwipePanel.this.postInvalidate();
                }
            });
            ofFloat.setDuration(100L).start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @SuppressLint({"WrongConstant"})
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        boolean z = false;
        if (action == 0) {
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
            this.mIsStart[0] = this.mEnabled[0] && this.mDrawables[0] != null && !isOpen(0) && this.mDownX <= ((float) this.mEdgeSizes[0]);
            this.mIsStart[1] = this.mEnabled[1] && this.mDrawables[1] != null && !isOpen(1) && this.mDownY <= ((float) this.mEdgeSizes[1]);
            this.mIsStart[2] = this.mEnabled[2] && this.mDrawables[2] != null && !isOpen(2) && this.mDownX >= ((float) (getWidth() - this.mEdgeSizes[2]));
            this.mIsStart[3] = this.mEnabled[3] && this.mDrawables[3] != null && !isOpen(3) && this.mDownY >= ((float) (getHeight() - this.mEdgeSizes[3]));
            boolean[] zArr = this.mIsStart;
            if (zArr[0] || zArr[1] || zArr[2] || zArr[3]) {
                z = true;
            }
            this.mIsEdgeStart = z;
            if (this.mIsEdgeStart) {
                this.mStartDirection = -1;
            }
            return true;
        }
        if (this.mIsEdgeStart) {
            if (action == 2) {
                this.mCurrentX = motionEvent.getX();
                this.mCurrentY = motionEvent.getY();
                if (this.mStartDirection == -1) {
                    float f = this.mCurrentX - this.mDownX;
                    float f2 = this.mCurrentY - this.mDownY;
                    float abs = Math.abs(f);
                    float abs2 = Math.abs(f2);
                    int i = this.mTouchSlop;
                    if (abs > i || abs2 > i) {
                        if (abs >= abs2) {
                            if (this.mIsStart[0] && f > 0.0f) {
                                decideDirection(0);
                            } else if (this.mIsStart[2] && f < 0.0f) {
                                decideDirection(2);
                            }
                        } else if (this.mIsStart[1] && f2 > 0.0f) {
                            decideDirection(1);
                        } else if (this.mIsStart[3] && f2 < 0.0f) {
                            decideDirection(3);
                        }
                    }
                }
                int i2 = this.mStartDirection;
                if (i2 != -1) {
                    float[] fArr = this.preProgresses;
                    float f3 = fArr[i2];
                    float[] fArr2 = this.progresses;
                    fArr[i2] = fArr2[i2];
                    fArr2[i2] = calculateProgress();
                    if (Math.abs(f3 - this.progresses[this.mStartDirection]) > 0.01d) {
                        postInvalidate();
                        OnProgressChangedListener onProgressChangedListener = this.mProgressListener;
                        if (onProgressChangedListener != null) {
                            int i3 = this.mStartDirection;
                            onProgressChangedListener.onProgressChanged(i3, this.progresses[i3], true);
                        }
                    } else {
                        this.preProgresses[this.mStartDirection] = f3;
                    }
                }
            } else if ((action == 1 || action == 3) && this.mStartDirection != -1) {
                this.mCurrentX = motionEvent.getX();
                this.mCurrentY = motionEvent.getY();
                this.progresses[this.mStartDirection] = calculateProgress();
                if (isOpen(this.mStartDirection)) {
                    OnFullSwipeListener onFullSwipeListener = this.mListener;
                    if (onFullSwipeListener != null) {
                        onFullSwipeListener.onFullSwipe(this.mStartDirection);
                    }
                } else {
                    close(this.mStartDirection, true);
                }
            }
        }
        return true;
    }

    private void decideDirection(int i) {
        if (i == 0 || i == 2) {
            if (this.mIsCenter[i]) {
                this.mDown[i] = this.mHeight / 2.0f;
            } else {
                float f = this.mDownY;
                float f2 = this.halfSize;
                if (f < f2) {
                    this.mDown[i] = f2;
                } else {
                    int i2 = this.mHeight;
                    if (f >= i2 - f2) {
                        this.mDown[i] = i2 - f2;
                    } else {
                        this.mDown[i] = f;
                    }
                }
            }
        } else if (this.mIsCenter[i]) {
            this.mDown[i] = this.mWidth / 2.0f;
        } else {
            float f3 = this.mDownX;
            float f4 = this.halfSize;
            if (f3 < f4) {
                this.mDown[i] = f4;
            } else {
                int i3 = this.mWidth;
                if (f3 >= i3 - f4) {
                    this.mDown[i] = i3 - f4;
                } else {
                    this.mDown[i] = f3;
                }
            }
        }
        this.mStartDirection = i;
        Path[] pathArr = this.mPath;
        if (pathArr[i] == null) {
            pathArr[i] = new Path();
        }
        this.preProgresses[i] = 0.0f;
        cancelChildViewTouch();
        requestDisallowInterceptTouchEvent(true);
    }

    private float calculateProgress() {
        int i = this.mStartDirection;
        if (i == 0) {
            float f = this.mCurrentX - this.mDownX;
            if (f <= 0.0f) {
                return 0.0f;
            }
            return Math.min(f / this.mLimit, 1.0f);
        } else if (i == 1) {
            float f2 = this.mCurrentY - this.mDownY;
            if (f2 <= 0.0f) {
                return 0.0f;
            }
            return Math.min(f2 / this.mLimit, 1.0f);
        } else if (i == 2) {
            float f3 = this.mCurrentX - this.mDownX;
            if (f3 >= 0.0f) {
                return 0.0f;
            }
            return Math.min((-f3) / this.mLimit, 1.0f);
        } else {
            float f4 = this.mCurrentY - this.mDownY;
            if (f4 >= 0.0f) {
                return 0.0f;
            }
            return Math.min((-f4) / this.mLimit, 1.0f);
        }
    }

    private void cancelChildViewTouch() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).dispatchTouchEvent(obtain);
        }
        obtain.recycle();
    }

    private static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    private static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        int i2;
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return context.getResources().getDrawable(i);
        }
        synchronized (LOCK) {
            if (sTempValue == null) {
                sTempValue = new TypedValue();
            }
            context.getResources().getValue(i, sTempValue, true);
            i2 = sTempValue.resourceId;
        }
        return context.getResources().getDrawable(i2);
    }
}
