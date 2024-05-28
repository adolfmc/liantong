package com.baidu.p120ar.arrender;

import com.baidu.p120ar.ability.AbilityData;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.RenderNodeData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RenderNodeData extends AbilityData {
    private int mHeight;
    private byte[] mMaskData;
    private int mWidth;

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

    public byte[] getMaskData() {
        return this.mMaskData;
    }

    public void setMaskData(byte[] bArr) {
        this.mMaskData = bArr;
    }
}
