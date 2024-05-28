package com.baidu.p120ar.arplay.core.engine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.ARPEngineParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPEngineParams {
    private float mDensity;
    private int mInputHeight;
    private int mInputWidth;
    private boolean mIsFrontCamera = true;
    private int mOutputHeight;
    private int mOutputWidth;
    private String mShaderDBPath;

    public int getInputWidth() {
        return this.mInputWidth;
    }

    public void setInputWidth(int i) {
        this.mInputWidth = i;
    }

    public int getInputHeight() {
        return this.mInputHeight;
    }

    public void setInputHeight(int i) {
        this.mInputHeight = i;
    }

    public int getOutputWidth() {
        return this.mOutputWidth;
    }

    public void setOutputWidth(int i) {
        this.mOutputWidth = i;
    }

    public int getOutputHeight() {
        return this.mOutputHeight;
    }

    public void setOutputHeight(int i) {
        this.mOutputHeight = i;
    }

    public float getDensity() {
        return this.mDensity;
    }

    public void setDensity(float f) {
        this.mDensity = f;
    }

    public boolean isIsFrontCamera() {
        return this.mIsFrontCamera;
    }

    public void setIsFrontCamera(boolean z) {
        this.mIsFrontCamera = z;
    }

    public void setShaderDBPath(String str) {
        this.mShaderDBPath = str;
    }

    public String getShaderDBPath() {
        return this.mShaderDBPath;
    }
}
