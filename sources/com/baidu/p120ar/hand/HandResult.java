package com.baidu.p120ar.hand;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.hand.HandResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandResult extends DetectResult {
    public HandResult(String str, long j, long j2) {
        setResultHandle(j);
        setDetectorName(str);
        setTimestamp(j2);
    }
}
