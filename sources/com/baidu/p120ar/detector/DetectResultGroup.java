package com.baidu.p120ar.detector;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectResultGroup */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectResultGroup extends DetectResult {
    private ArrayList<DetectResult> mDetectorResults;

    public DetectResultGroup() {
        setDetectorName("DetectorGroup");
    }

    public void addDetectResult(DetectResult detectResult) {
        if (this.mDetectorResults == null) {
            this.mDetectorResults = new ArrayList<>();
        }
        this.mDetectorResults.add(detectResult);
    }

    public ArrayList<DetectResult> getDetectResults() {
        return this.mDetectorResults;
    }

    public boolean isDetectResultContain(String str) {
        Iterator<DetectResult> it = this.mDetectorResults.iterator();
        while (it.hasNext()) {
            if (it.next().getDetectorName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
