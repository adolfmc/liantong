package com.baidu.p120ar.p121vo.detector;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.detector.VOResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOResult extends DetectResult {
    private VOTrackResult mTrackResult;

    public VOResult(VOTrackResult vOTrackResult) {
        this.mTrackResult = vOTrackResult;
        if (vOTrackResult != null) {
            setTimestamp(vOTrackResult.getTimestamp());
        }
    }

    public VOResult(long j) {
        this.mTrackResult = null;
        setTimestamp(j);
    }

    public VOTrackResult getTrackResult() {
        return this.mTrackResult;
    }
}
