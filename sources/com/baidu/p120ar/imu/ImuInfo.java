package com.baidu.p120ar.imu;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.ImuInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImuInfo {
    private float mAngle;
    private float[] mGravityValues;
    private int mInitPos;
    private float[] mMatrix;

    public float[] getMatrix() {
        return this.mMatrix;
    }

    public void setMatrix(float[] fArr) {
        this.mMatrix = fArr;
    }

    public int getInitPos() {
        return this.mInitPos;
    }

    public void setInitPos(int i) {
        this.mInitPos = i;
    }

    public float getAngle() {
        return this.mAngle;
    }

    public void setAngle(float f) {
        this.mAngle = f;
    }

    public float[] getGravityValues() {
        return this.mGravityValues;
    }

    public void setGravityValues(float[] fArr) {
        this.mGravityValues = fArr;
    }
}
