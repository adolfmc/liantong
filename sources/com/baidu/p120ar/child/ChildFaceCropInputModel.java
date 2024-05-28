package com.baidu.p120ar.child;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.ChildFaceCropInputModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildFaceCropInputModel {
    private int angle;
    private byte[] cameraData;
    private float[] faceBoxList;
    private float frameIndex;
    private long handle;
    private int height;
    private boolean isFront;
    private float[] trackingPoints;
    private int width;

    public float getFrameIndex() {
        return this.frameIndex;
    }

    public void setFrameIndex(float f) {
        this.frameIndex = f;
    }

    public float[] getFaceBoxList() {
        return this.faceBoxList;
    }

    public void setFaceBoxList(float[] fArr) {
        this.faceBoxList = fArr;
    }

    public float[] getTrackingPoints() {
        return this.trackingPoints;
    }

    public void setTrackingPoints(float[] fArr) {
        this.trackingPoints = fArr;
    }

    public boolean isFront() {
        return this.isFront;
    }

    public void setFront(boolean z) {
        this.isFront = z;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int i) {
        this.angle = i;
    }

    public long getHandle() {
        return this.handle;
    }

    public void setHandle(long j) {
        this.handle = j;
    }

    public byte[] getCameraData() {
        return this.cameraData;
    }

    public void setCameraData(byte[] bArr) {
        this.cameraData = bArr;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }
}
