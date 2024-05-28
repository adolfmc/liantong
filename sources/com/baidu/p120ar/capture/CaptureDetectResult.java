package com.baidu.p120ar.capture;

import com.baidu.p120ar.detector.DetectResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.CaptureDetectResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaptureDetectResult extends DetectResult {
    private byte[] mData;

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    public byte[] getData() {
        return this.mData;
    }
}
