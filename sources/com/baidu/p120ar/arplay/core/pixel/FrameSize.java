package com.baidu.p120ar.arplay.core.pixel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.pixel.FrameSize */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FrameSize {
    private int mHeight;
    private int mWidth;

    public FrameSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public FrameSize(FrameSize frameSize) {
        this.mWidth = frameSize.getWidth();
        this.mHeight = frameSize.getHeight();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }
}
