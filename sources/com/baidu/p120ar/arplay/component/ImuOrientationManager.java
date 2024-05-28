package com.baidu.p120ar.arplay.component;

import android.content.Context;
import android.view.OrientationEventListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.ImuOrientationManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImuOrientationManager extends OrientationEventListener {
    private boolean checkDefalutOrientation;
    private int mCalibrationAngle;
    private TouchOrientation mDefaultTouchOrientation;
    private int mImuType;
    private TouchOrientation mTouchOrientation;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.ImuOrientationManager$TouchOrientation */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TouchOrientation {
        SCREEN_ORIENTATION_PORTRAIT,
        SCREEN_ORIENTATION_LANDSCAPE,
        SCREEN_ORIENTATION_REVERSE_PORTRAIT,
        SCREEN_ORIENTATION_REVERSE_LANDSCAPE,
        SCREEN_ORIENTATION_NOT_DEFINED
    }

    public ImuOrientationManager(Context context) {
        super(context);
        this.checkDefalutOrientation = false;
        this.mImuType = 0;
    }

    public void start() {
        try {
            enable();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        if (i < 0) {
            this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_NOT_DEFINED;
            return;
        }
        int i2 = this.mImuType;
        if (i2 != 1) {
            if (i2 == 0) {
                if (i <= 45 || i > 315) {
                    this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_PORTRAIT;
                    return;
                } else if (i > 45 && i <= 135) {
                    this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    return;
                } else if (i > 135 && i <= 225) {
                    this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    return;
                } else if (i <= 225 || i > 315) {
                    return;
                } else {
                    this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_LANDSCAPE;
                    return;
                }
            }
            return;
        }
        int i3 = ((i + 360) + this.mCalibrationAngle) % 360;
        if (i3 <= 45 || i3 > 315) {
            this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_PORTRAIT;
        } else if (i3 > 45 && i3 <= 135) {
            this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        } else if (i3 > 135 && i3 <= 225) {
            this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
        } else if (i3 > 225 && i3 <= 315) {
            this.mTouchOrientation = TouchOrientation.SCREEN_ORIENTATION_LANDSCAPE;
        }
        if (this.checkDefalutOrientation || this.mImuType != 1) {
            return;
        }
        this.checkDefalutOrientation = true;
        this.mDefaultTouchOrientation = this.mTouchOrientation;
        calibrationTouchAngle();
    }

    public void calibrationTouchAngle() {
        this.mCalibrationAngle = getImuCalibrationAngle();
    }

    private int getImuCalibrationAngle() {
        if (this.mDefaultTouchOrientation == TouchOrientation.SCREEN_ORIENTATION_LANDSCAPE) {
            return 90;
        }
        if (this.mDefaultTouchOrientation == TouchOrientation.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
            return -90;
        }
        return this.mDefaultTouchOrientation == TouchOrientation.SCREEN_ORIENTATION_REVERSE_PORTRAIT ? 180 : 0;
    }

    public void release() {
        try {
            disable();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        this.mImuType = 0;
    }

    public int getImuType() {
        return this.mImuType;
    }

    public void setImuType(int i) {
        this.mImuType = i;
    }
}
