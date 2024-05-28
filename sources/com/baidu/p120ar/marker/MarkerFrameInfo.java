package com.baidu.p120ar.marker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.marker.MarkerFrameInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MarkerFrameInfo {
    private float[] distort;
    private int fps;
    private byte[] image;
    private int inputHeight;
    private int inputWidth;
    private float[] intrinsics;
    private double[] magnet;
    private float[] poseMat;
    private int trackingState;
    private float yScale;

    public int getInputWidth() {
        return this.inputWidth;
    }

    public void setInputWidth(int i) {
        this.inputWidth = i;
    }

    public int getInputHeight() {
        return this.inputHeight;
    }

    public void setInputHeight(int i) {
        this.inputHeight = i;
    }

    public double[] getMagnet() {
        return this.magnet;
    }

    public void setMagnet(double[] dArr) {
        this.magnet = dArr;
    }

    public float[] getIntrinsics() {
        return this.intrinsics;
    }

    public void setIntrinsics(float[] fArr) {
        this.intrinsics = fArr;
    }

    public float[] getDistort() {
        return this.distort;
    }

    public void setDistort(float[] fArr) {
        this.distort = fArr;
    }

    public int getTrackingState() {
        return this.trackingState;
    }

    public void setTrackingState(int i) {
        this.trackingState = i;
    }

    public int getFps() {
        return this.fps;
    }

    public void setFps(int i) {
        this.fps = i;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] bArr) {
        this.image = bArr;
    }

    public float[] getPoseMat() {
        return this.poseMat;
    }

    public void setPoseMat(float[] fArr) {
        this.poseMat = fArr;
    }

    public float getScaleY() {
        return this.yScale;
    }

    public void setScaleY(float f) {
        this.yScale = f;
    }
}
