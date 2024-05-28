package com.yalantis.ucrop.util;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RotationGestureDetector {
    private static final int INVALID_POINTER_INDEX = -1;

    /* renamed from: fX */
    private float f23818fX;

    /* renamed from: fY */
    private float f23819fY;
    private float mAngle;
    private boolean mIsFirstTouch;
    private OnRotationGestureListener mListener;
    private int mPointerIndex1 = -1;
    private int mPointerIndex2 = -1;

    /* renamed from: sX */
    private float f23820sX;

    /* renamed from: sY */
    private float f23821sY;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface OnRotationGestureListener {
        boolean onRotation(RotationGestureDetector rotationGestureDetector);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class SimpleOnRotationGestureListener implements OnRotationGestureListener {
        @Override // com.yalantis.ucrop.util.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotation(RotationGestureDetector rotationGestureDetector) {
            return false;
        }
    }

    public RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.mListener = onRotationGestureListener;
    }

    public float getAngle() {
        return this.mAngle;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.f23820sX = motionEvent.getX();
                this.f23821sY = motionEvent.getY();
                this.mPointerIndex1 = motionEvent.findPointerIndex(motionEvent.getPointerId(0));
                this.mAngle = 0.0f;
                this.mIsFirstTouch = true;
                break;
            case 1:
                this.mPointerIndex1 = -1;
                break;
            case 2:
                if (this.mPointerIndex1 != -1 && this.mPointerIndex2 != -1 && motionEvent.getPointerCount() > this.mPointerIndex2) {
                    float x = motionEvent.getX(this.mPointerIndex1);
                    float y = motionEvent.getY(this.mPointerIndex1);
                    float x2 = motionEvent.getX(this.mPointerIndex2);
                    float y2 = motionEvent.getY(this.mPointerIndex2);
                    if (this.mIsFirstTouch) {
                        this.mAngle = 0.0f;
                        this.mIsFirstTouch = false;
                    } else {
                        calculateAngleBetweenLines(this.f23818fX, this.f23819fY, this.f23820sX, this.f23821sY, x2, y2, x, y);
                    }
                    OnRotationGestureListener onRotationGestureListener = this.mListener;
                    if (onRotationGestureListener != null) {
                        onRotationGestureListener.onRotation(this);
                    }
                    this.f23818fX = x2;
                    this.f23819fY = y2;
                    this.f23820sX = x;
                    this.f23821sY = y;
                    break;
                }
                break;
            case 5:
                this.f23818fX = motionEvent.getX();
                this.f23819fY = motionEvent.getY();
                this.mPointerIndex2 = motionEvent.findPointerIndex(motionEvent.getPointerId(motionEvent.getActionIndex()));
                this.mAngle = 0.0f;
                this.mIsFirstTouch = true;
                break;
            case 6:
                this.mPointerIndex2 = -1;
                break;
        }
        return true;
    }

    private float calculateAngleBetweenLines(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return calculateAngleDelta((float) Math.toDegrees((float) Math.atan2(f2 - f4, f - f3)), (float) Math.toDegrees((float) Math.atan2(f6 - f8, f5 - f7)));
    }

    private float calculateAngleDelta(float f, float f2) {
        this.mAngle = (f2 % 360.0f) - (f % 360.0f);
        float f3 = this.mAngle;
        if (f3 < -180.0f) {
            this.mAngle = f3 + 360.0f;
        } else if (f3 > 180.0f) {
            this.mAngle = f3 - 360.0f;
        }
        return this.mAngle;
    }
}
