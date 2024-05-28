package com.baidu.p120ar.child.detector;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.detector.ChildCameraDetectResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildCameraDetectResult extends DetectResult {
    private byte[] datas;
    private int degree;
    private boolean isFront;

    public byte[] getDatas() {
        return this.datas;
    }

    public boolean isFront() {
        return this.isFront;
    }

    public void setFront(boolean z) {
        this.isFront = z;
    }

    public void setDatas(byte[] bArr) {
        this.datas = bArr;
    }

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int i) {
        this.degree = i;
    }
}
