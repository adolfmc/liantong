package com.baidu.p120ar.arrender;

import com.baidu.p120ar.ability.AbilityData;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.RenderFaceData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RenderFaceData extends AbilityData {
    private int mAlgoInputHeight;
    private int mAlgoInputWidth;
    private long mFaceHandle;

    public long getFaceHandle() {
        return this.mFaceHandle;
    }

    public void setFaceHandle(long j) {
        this.mFaceHandle = j;
    }

    public int getAlgoInputWidth() {
        return this.mAlgoInputWidth;
    }

    public void setAlgoInputWidth(int i) {
        this.mAlgoInputWidth = i;
    }

    public int getAlgoInputHeight() {
        return this.mAlgoInputHeight;
    }

    public void setAlgoInputHeight(int i) {
        this.mAlgoInputHeight = i;
    }
}
