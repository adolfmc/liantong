package com.baidu.p120ar.child.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.detector.FaceResultModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceResultModel {
    private long faceHandle;
    private float[] faceList;
    private float[] trackingPoints;

    public long getFaceHandle() {
        return this.faceHandle;
    }

    public void setFaceHandle(long j) {
        this.faceHandle = j;
    }

    public float[] getFaceList() {
        return this.faceList;
    }

    public void setFaceList(float[] fArr) {
        this.faceList = fArr;
    }

    public float[] getTrackingPoints() {
        return this.trackingPoints;
    }

    public void setTrackingPoints(float[] fArr) {
        this.trackingPoints = fArr;
    }
}
