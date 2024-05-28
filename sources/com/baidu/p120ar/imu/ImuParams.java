package com.baidu.p120ar.imu;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.ImuParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImuParams {
    private Coordinate mCoordinate = Coordinate.WORLD;
    private int mInitPosition = 0;
    private boolean mResumeOriginalPosition = false;
    private boolean mNeedProcessMatrix = false;
    private boolean mNeedImuAngle = false;
    private boolean mNeedGravity = false;

    public Coordinate getCoordinate() {
        return this.mCoordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.mCoordinate = coordinate;
    }

    public int getInitPosition() {
        return this.mInitPosition;
    }

    public void setInitPosition(int i) {
        this.mInitPosition = i;
    }

    public boolean isResumeOriginalPosition() {
        return this.mResumeOriginalPosition;
    }

    public void setResumeOriginalPosition(boolean z) {
        this.mResumeOriginalPosition = z;
    }

    public boolean isNeedProcessMatrix() {
        return this.mNeedProcessMatrix;
    }

    public void setNeedProcessMatrix(boolean z) {
        this.mNeedProcessMatrix = z;
    }

    public boolean isNeedImuAngle() {
        return this.mNeedImuAngle;
    }

    public void setNeedImuAngle(boolean z) {
        this.mNeedImuAngle = z;
    }

    public boolean isNeedGravity() {
        return this.mNeedGravity;
    }

    public void setNeedGravity(boolean z) {
        this.mNeedGravity = z;
    }
}
