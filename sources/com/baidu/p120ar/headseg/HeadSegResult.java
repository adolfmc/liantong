package com.baidu.p120ar.headseg;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.headseg.HeadSegResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HeadSegResult extends DetectResult {
    public HeadSegResult(String str, long j, long j2) {
        setResultHandle(j);
        setDetectorName(str);
        setTimestamp(j2);
    }

    public HeadSegResult() {
    }
}
