package com.baidu.p120ar.seg;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.seg.SegModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SegModel {
    private int height;
    private boolean isFrontCamera;
    private int orientation;
    private byte[] result;
    private int width;

    public SegModel(byte[] bArr, int i, int i2, int i3, boolean z) {
        this.result = bArr;
        this.width = i;
        this.height = i2;
        this.orientation = i3;
        this.isFrontCamera = z;
    }

    public byte[] getResult() {
        return this.result;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isFrontCamera() {
        return this.isFrontCamera;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }
}
