package com.baidu.p120ar.stretch;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.stretch.StretchResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StretchResult extends DetectResult {
    private float[] mPoses;

    public float[] getPoses() {
        return this.mPoses;
    }

    public void setPoses(float[] fArr) {
        this.mPoses = fArr;
    }
}
