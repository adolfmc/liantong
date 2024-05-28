package com.billy.android.swipe.internal;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import com.billy.android.swipe.SwipeConsumer;
import java.util.Arrays;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SwipeHelper {
    public static final int INVALID_POINTER = -1;
    public static final int POINTER_NESTED_FLY = -3;
    public static final int POINTER_NESTED_SCROLL = -2;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_NONE_TOUCH = 3;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "SwipeHelper";
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.billy.android.swipe.internal.SwipeHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private int mClampedDistanceX;
    private int mClampedDistanceY;
    private int mDragState;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final SwipeConsumer mSwipeConsumer;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private final ViewConfiguration viewConfiguration;
    private int maxSettleDuration = 600;
    private int mActivePointerId = -1;

    public static SwipeHelper create(ViewGroup viewGroup, SwipeConsumer swipeConsumer, Interpolator interpolator) {
        return new SwipeHelper(viewGroup.getContext(), viewGroup, swipeConsumer, interpolator);
    }

    public static SwipeHelper create(ViewGroup viewGroup, SwipeConsumer swipeConsumer) {
        return create(viewGroup, swipeConsumer, (Interpolator) null);
    }

    public static SwipeHelper create(ViewGroup viewGroup, float f, SwipeConsumer swipeConsumer, Interpolator interpolator) {
        SwipeHelper create = create(viewGroup, swipeConsumer, interpolator);
        create.mTouchSlop = (int) (create.mTouchSlop * (1.0f / f));
        return create;
    }

    public static SwipeHelper create(ViewGroup viewGroup, float f, SwipeConsumer swipeConsumer) {
        return create(viewGroup, f, swipeConsumer, null);
    }

    public void setSensitivity(float f) {
        this.mTouchSlop = (int) (this.viewConfiguration.getScaledTouchSlop() * (1.0f / f));
    }

    private SwipeHelper(Context context, ViewGroup viewGroup, SwipeConsumer swipeConsumer, Interpolator interpolator) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (swipeConsumer == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup;
        this.mSwipeConsumer = swipeConsumer;
        this.viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = this.viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = this.viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = this.viewConfiguration.getScaledMinimumFlingVelocity();
        setInterpolator(context, interpolator);
    }

    public void setInterpolator(Context context, Interpolator interpolator) {
        if (interpolator == null) {
            interpolator = sInterpolator;
        }
        if (this.mScroller != null) {
            abort();
            this.mScroller = null;
        }
        this.mScroller = new OverScroller(context, interpolator);
    }

    public SwipeHelper setMinVelocity(float f) {
        this.mMinVelocity = f;
        return this;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getDragState() {
        return this.mDragState;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        cancel();
        int i = this.mDragState;
        if (i == 2 || i == 3) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int currX2 = this.mScroller.getCurrX();
            int currY2 = this.mScroller.getCurrY();
            this.mSwipeConsumer.onSwipeDistanceChanged(currX2, currY2, currX2 - currX, currY2 - currY);
        }
        setDragState(0);
    }

    public boolean smoothSlideTo(int i, int i2, int i3, int i4) {
        this.mClampedDistanceX = i;
        this.mClampedDistanceY = i2;
        return smoothSlideTo(i3, i4);
    }

    public boolean smoothSlideTo(int i, int i2) {
        boolean smoothSettleCapturedViewTo;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            smoothSettleCapturedViewTo = smoothSettleCapturedViewTo(i, i2, (int) velocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        } else {
            smoothSettleCapturedViewTo = smoothSettleCapturedViewTo(i, i2, 0, 0);
        }
        this.mActivePointerId = -1;
        return smoothSettleCapturedViewTo;
    }

    public boolean settleCapturedViewAt(int i, int i2) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return smoothSettleCapturedViewTo(i, i2, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
    }

    private boolean smoothSettleCapturedViewTo(int i, int i2, int i3, int i4) {
        int i5 = this.mClampedDistanceX;
        int i6 = this.mClampedDistanceY;
        int i7 = i - i5;
        int i8 = i2 - i6;
        this.mScroller.abortAnimation();
        if (i7 == 0 && i8 == 0) {
            setDragState(2);
            this.mSwipeConsumer.onSwipeDistanceChanged(i, i2, i7, i8);
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(i5, i6, i7, i8, computeSettleDuration(i7, i8, i3, i4));
        setDragState(2);
        return true;
    }

    private int computeSettleDuration(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int clampMag = clampMag(i3, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int clampMag2 = clampMag(i4, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(clampMag);
        int abs4 = Math.abs(clampMag2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        if (clampMag != 0) {
            f = abs3;
            f2 = i5;
        } else {
            f = abs;
            f2 = i6;
        }
        float f5 = f / f2;
        if (clampMag2 != 0) {
            f3 = abs4;
            f4 = i5;
        } else {
            f3 = abs2;
            f4 = i6;
        }
        float f6 = i;
        float f7 = i2;
        return (int) ((computeAxisDuration(i, clampMag, this.mSwipeConsumer.getHorizontalRange(f6, f7)) * f5) + (computeAxisDuration(i2, clampMag2, this.mSwipeConsumer.getVerticalRange(f6, f7)) * (f3 / f4)));
    }

    private int computeAxisDuration(int i, int i2, int i3) {
        int abs;
        if (i == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        float f = width >> 1;
        float distanceInfluenceForSnapDuration = f + (distanceInfluenceForSnapDuration(Math.min(1.0f, Math.abs(i) / width)) * f);
        int abs2 = Math.abs(i2);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(distanceInfluenceForSnapDuration / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) ((Math.abs(i) / i3) * this.maxSettleDuration);
        }
        return Math.min(abs, this.maxSettleDuration);
    }

    private int clampMag(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        return abs > i3 ? i > 0 ? i3 : -i3 : i;
    }

    private float clampMag(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        return abs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    public boolean continueSettling() {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int i = currX - this.mClampedDistanceX;
            int i2 = currY - this.mClampedDistanceY;
            if (i != 0) {
                this.mClampedDistanceX = currX;
            }
            if (i2 != 0) {
                this.mClampedDistanceY = currY;
            }
            if (i != 0 || i2 != 0) {
                this.mSwipeConsumer.onSwipeDistanceChanged(currX, currY, i, i2);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                setDragState(0);
            }
        }
        return this.mDragState == 2;
    }

    public void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mSwipeConsumer.onSwipeReleased(f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private void clearMotionHistory() {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.mInitialMotionY, 0.0f);
        Arrays.fill(this.mLastMotionX, 0.0f);
        Arrays.fill(this.mLastMotionY, 0.0f);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int i) {
        if (this.mInitialMotionX == null || !isPointerDown(i)) {
            return;
        }
        this.mInitialMotionX[i] = 0.0f;
        this.mInitialMotionY[i] = 0.0f;
        this.mLastMotionX[i] = 0.0f;
        this.mLastMotionY[i] = 0.0f;
        this.mPointersDown = (~(1 << i)) & this.mPointersDown;
    }

    private void ensureMotionHistorySizeForId(int i) {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            float[] fArr6 = this.mInitialMotionX;
            if (fArr6 != null) {
                System.arraycopy(fArr6, 0, fArr2, 0, fArr6.length);
                float[] fArr7 = this.mInitialMotionY;
                System.arraycopy(fArr7, 0, fArr3, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionX;
                System.arraycopy(fArr8, 0, fArr4, 0, fArr8.length);
                float[] fArr9 = this.mLastMotionY;
                System.arraycopy(fArr9, 0, fArr5, 0, fArr9.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
        }
    }

    private void saveInitialMotion(float f, float f2, int i) {
        ensureMotionHistorySizeForId(i);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[i] = f;
        fArr[i] = f;
        float[] fArr2 = this.mInitialMotionY;
        this.mLastMotionY[i] = f2;
        fArr2[i] = f2;
        this.mPointersDown |= 1 << i;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (isValidPointerForActionMove(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public boolean isPointerDown(int i) {
        return ((1 << i) & this.mPointersDown) != 0;
    }

    void setDragState(int i) {
        if (this.mDragState != i) {
            this.mDragState = i;
            this.mSwipeConsumer.onStateChanged(i);
        }
    }

    private boolean trySwipe(int i, boolean z, float f, float f2, float f3, float f4) {
        return trySwipe(i, z, f, f2, f3, f4, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean trySwipe(int r11, boolean r12, float r13, float r14, float r15, float r16, boolean r17) {
        /*
            r10 = this;
            r0 = r10
            r7 = r11
            r8 = r12
            int r1 = r0.mActivePointerId
            r9 = 1
            if (r1 != r7) goto L9
            return r9
        L9:
            if (r8 != 0) goto L1e
            int r1 = r0.mDragState
            r2 = 2
            if (r1 != r2) goto L11
            goto L1e
        L11:
            com.billy.android.swipe.SwipeConsumer r1 = r0.mSwipeConsumer
            r2 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            boolean r1 = r1.tryAcceptMoving(r2, r3, r4, r5, r6)
            goto L26
        L1e:
            com.billy.android.swipe.SwipeConsumer r1 = r0.mSwipeConsumer
            r2 = r13
            r3 = r14
            boolean r1 = r1.tryAcceptSettling(r11, r13, r14)
        L26:
            r2 = 0
            if (r1 == 0) goto L5c
            r0.mActivePointerId = r7
            r1 = 0
            if (r7 < 0) goto L3d
            float[] r3 = r0.mInitialMotionX
            int r4 = r3.length
            if (r7 >= r4) goto L3d
            float[] r4 = r0.mInitialMotionY
            int r5 = r4.length
            if (r7 >= r5) goto L3d
            r1 = r3[r7]
            r3 = r4[r7]
            goto L3e
        L3d:
            r3 = r1
        L3e:
            com.billy.android.swipe.SwipeConsumer r4 = r0.mSwipeConsumer
            r4.onSwipeAccepted(r11, r12, r1, r3)
            com.billy.android.swipe.SwipeConsumer r1 = r0.mSwipeConsumer
            int r1 = r1.clampDistanceHorizontal(r2, r2)
            r0.mClampedDistanceX = r1
            com.billy.android.swipe.SwipeConsumer r1 = r0.mSwipeConsumer
            int r1 = r1.clampDistanceVertical(r2, r2)
            r0.mClampedDistanceY = r1
            if (r17 == 0) goto L57
            r1 = r9
            goto L58
        L57:
            r1 = 3
        L58:
            r10.setDragState(r1)
            return r9
        L5c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.billy.android.swipe.internal.SwipeHelper.trySwipe(int, boolean, float, float, float, float, boolean):boolean");
    }

    public boolean shouldInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (actionMasked) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                int pointerId = motionEvent.getPointerId(0);
                saveInitialMotion(x, y, pointerId);
                int i = this.mDragState;
                if (i == 2 || i == 3) {
                    trySwipe(pointerId, true, x, y, 0.0f, 0.0f);
                    break;
                }
            case 1:
            case 3:
                cancel();
                break;
            case 2:
                if (this.mInitialMotionX != null && this.mInitialMotionY != null) {
                    int pointerCount = motionEvent.getPointerCount();
                    for (int i2 = 0; i2 < pointerCount; i2++) {
                        int pointerId2 = motionEvent.getPointerId(i2);
                        if (isValidPointerForActionMove(pointerId2)) {
                            float x2 = motionEvent.getX(i2);
                            float y2 = motionEvent.getY(i2);
                            float f = this.mInitialMotionX[pointerId2];
                            float f2 = this.mInitialMotionY[pointerId2];
                            float f3 = x2 - f;
                            float f4 = y2 - f2;
                            boolean checkTouchSlop = checkTouchSlop(f3, f4);
                            if (checkTouchSlop) {
                                int horizontalRange = this.mSwipeConsumer.getHorizontalRange(f3, f4);
                                int verticalRange = this.mSwipeConsumer.getVerticalRange(f3, f4);
                                if (horizontalRange == 0 && verticalRange == 0) {
                                }
                            }
                            if (checkTouchSlop && trySwipe(pointerId2, false, f, f2, f3, f4)) {
                                saveLastMotion(motionEvent);
                                break;
                            }
                        }
                    }
                    saveLastMotion(motionEvent);
                }
                break;
            case 5:
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                float x3 = motionEvent.getX(actionIndex);
                float y3 = motionEvent.getY(actionIndex);
                saveInitialMotion(x3, y3, pointerId3);
                int i3 = this.mDragState;
                if (i3 == 2 || i3 == 3) {
                    trySwipe(pointerId3, true, x3, y3, 0.0f, 0.0f);
                    break;
                }
            case 6:
                clearMotionHistory(motionEvent.getPointerId(actionIndex));
                break;
        }
        return this.mDragState == 1;
    }

    public void processTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0 && this.mDragState != 1) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i2 = 0;
        switch (actionMasked) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                int pointerId = motionEvent.getPointerId(0);
                saveInitialMotion(x, y, pointerId);
                int i3 = this.mDragState;
                if (i3 != 1) {
                    trySwipe(pointerId, i3 == 2 || i3 == 3, x, y, 0.0f, 0.0f);
                    return;
                }
                return;
            case 1:
                if (this.mDragState == 1) {
                    releaseViewForPointerUp();
                }
                cancel();
                return;
            case 2:
                if (this.mDragState == 1) {
                    if (isValidPointerForActionMove(this.mActivePointerId) && (findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId)) >= 0) {
                        float x2 = motionEvent.getX(findPointerIndex);
                        float y2 = motionEvent.getY(findPointerIndex);
                        float[] fArr = this.mLastMotionX;
                        int i4 = this.mActivePointerId;
                        int i5 = (int) (x2 - fArr[i4]);
                        int i6 = (int) (y2 - this.mLastMotionY[i4]);
                        dragTo(this.mClampedDistanceX + i5, this.mClampedDistanceY + i6, i5, i6);
                        saveLastMotion(motionEvent);
                        return;
                    }
                    return;
                }
                int pointerCount = motionEvent.getPointerCount();
                while (i2 < pointerCount) {
                    int pointerId2 = motionEvent.getPointerId(i2);
                    if (isValidPointerForActionMove(pointerId2)) {
                        float x3 = motionEvent.getX(i2);
                        float y3 = motionEvent.getY(i2);
                        float f = this.mInitialMotionX[pointerId2];
                        float f2 = this.mInitialMotionY[pointerId2];
                        float f3 = x3 - f;
                        float f4 = y3 - f2;
                        if (checkTouchSlop(f3, f4) && trySwipe(pointerId2, false, f, f2, f3, f4)) {
                            saveLastMotion(motionEvent);
                            return;
                        }
                    }
                    i2++;
                }
                saveLastMotion(motionEvent);
                return;
            case 3:
                if (this.mDragState == 1) {
                    dispatchViewReleased(0.0f, 0.0f);
                }
                cancel();
                return;
            case 4:
            default:
                return;
            case 5:
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                float x4 = motionEvent.getX(actionIndex);
                float y4 = motionEvent.getY(actionIndex);
                saveInitialMotion(x4, y4, pointerId3);
                if (this.mDragState == 1) {
                    trySwipe(pointerId3, true, x4, y4, 0.0f, 0.0f);
                    return;
                }
                return;
            case 6:
                int pointerId4 = motionEvent.getPointerId(actionIndex);
                if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
                    int pointerCount2 = motionEvent.getPointerCount();
                    while (true) {
                        if (i2 < pointerCount2) {
                            int pointerId5 = motionEvent.getPointerId(i2);
                            if (pointerId5 != this.mActivePointerId && isValidPointerForActionMove(pointerId5)) {
                                float[] fArr2 = this.mInitialMotionX;
                                if (trySwipe(pointerId5, true, fArr2[pointerId5], fArr2[pointerId5], 0.0f, 0.0f)) {
                                    i = this.mActivePointerId;
                                }
                            }
                            i2++;
                        } else {
                            i = -1;
                        }
                    }
                    if (i == -1) {
                        releaseViewForPointerUp();
                    }
                }
                clearMotionHistory(pointerId4);
                return;
        }
    }

    private boolean checkTouchSlop(float f, float f2) {
        boolean z = this.mSwipeConsumer.getHorizontalRange(f, f2) > 0;
        boolean z2 = this.mSwipeConsumer.getVerticalRange(f, f2) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.mTouchSlop) : z2 && Math.abs(f2) > ((float) this.mTouchSlop);
        }
        float f3 = (f * f) + (f2 * f2);
        int i = this.mTouchSlop;
        return f3 > ((float) (i * i));
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    public boolean nestedScrollingTrySwipe(int i, int i2, boolean z) {
        return trySwipe(z ? -3 : -2, false, 0.0f, 0.0f, i, i2, false);
    }

    public boolean nestedScrollingDrag(int i, int i2, int[] iArr, boolean z) {
        int clampDistanceHorizontal;
        int clampDistanceVertical;
        if (this.mDragState == 0) {
            return nestedScrollingTrySwipe(i, i2, z);
        }
        if (this.mClampedDistanceX == 0 && i == 0) {
            clampDistanceHorizontal = 0;
        } else {
            clampDistanceHorizontal = this.mSwipeConsumer.clampDistanceHorizontal(this.mClampedDistanceX + i, i);
            iArr[0] = clampDistanceHorizontal - this.mClampedDistanceX;
        }
        if (this.mClampedDistanceY == 0 && i2 == 0) {
            clampDistanceVertical = 0;
        } else {
            clampDistanceVertical = this.mSwipeConsumer.clampDistanceVertical(this.mClampedDistanceY + i2, i2);
            iArr[1] = clampDistanceVertical - this.mClampedDistanceY;
        }
        if (this.mClampedDistanceX == 0 && this.mClampedDistanceY == 0 && iArr[0] == 0 && iArr[1] == 0) {
            this.mActivePointerId = -1;
            setDragState(0);
            return false;
        }
        dragTo(clampDistanceHorizontal, clampDistanceVertical, iArr[0], iArr[1]);
        return true;
    }

    public void nestedScrollingRelease() {
        if (this.mDragState == 3) {
            dispatchViewReleased(0.0f, 0.0f);
        }
    }

    private void dragTo(int i, int i2, int i3, int i4) {
        int i5 = this.mClampedDistanceX;
        int i6 = this.mClampedDistanceY;
        if (i3 != 0) {
            i = this.mSwipeConsumer.clampDistanceHorizontal(i, i3);
            this.mClampedDistanceX = i;
        }
        if (i4 != 0) {
            i2 = this.mSwipeConsumer.clampDistanceVertical(i2, i4);
            this.mClampedDistanceY = i2;
        }
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.mSwipeConsumer.onSwipeDistanceChanged(i, i2, i - i5, i2 - i6);
    }

    public SwipeConsumer getSwipeConsumer() {
        return this.mSwipeConsumer;
    }

    private boolean isValidPointerForActionMove(int i) {
        if (isPointerDown(i)) {
            return true;
        }
        Log.e(TAG, "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  SwipeHelper did not receive all the events in the event stream.");
        return false;
    }

    public int getMaxSettleDuration() {
        return this.maxSettleDuration;
    }

    public void setMaxSettleDuration(int i) {
        this.maxSettleDuration = i;
    }
}
