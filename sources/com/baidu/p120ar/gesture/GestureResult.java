package com.baidu.p120ar.gesture;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.gesture.GestureResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GestureResult extends DetectResult {
    private float[] mGesture;

    public GestureResult(String str, float[] fArr, long j) {
        this.mGesture = fArr;
        setDetectorName(str);
        setTimestamp(j);
    }

    public float[] getGesture() {
        return this.mGesture;
    }

    public void setGesture(float[] fArr) {
        this.mGesture = fArr;
    }
}
