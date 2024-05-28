package com.baidu.p120ar.seg;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.seg.SegResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SegResult extends DetectResult {
    private SegModel mModel;

    public SegModel getModel() {
        return this.mModel;
    }

    public void setModel(SegModel segModel) {
        this.mModel = segModel;
    }
}
