package com.baidu.p120ar.capture;

import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.detector.FrameDetector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.CaptureDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CaptureDetector extends FrameDetector {
    private static final String TAG = "CaptureDetector";
    private volatile boolean mNeedData = false;

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
    }

    public CaptureDetector(String str, int i, int i2) {
        this.mReadParams = new PixelReadParams(PixelType.RGBA);
        this.mReadParams.setPreFilterID(str);
        this.mReadParams.setOutputWidth(i);
        this.mReadParams.setOutputHeight(i2);
        if ("camera".equals(str)) {
            this.mReadParams.setIsPortrait(true);
        }
    }

    public String getPreFilter() {
        return this.mReadParams.getPreFilterID();
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean detectFrame(FramePixels framePixels) {
        if (!this.mNeedData || this.mDetectorCallback == null) {
            return true;
        }
        CaptureDetectResult captureDetectResult = new CaptureDetectResult();
        captureDetectResult.setDetectorName(TAG);
        captureDetectResult.setTimestamp(framePixels.getTimestamp());
        captureDetectResult.setData(framePixels.getPixelData());
        this.mDetectorCallback.onDetected(captureDetectResult);
        return true;
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        this.mNeedData = false;
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    public void setNeedData(boolean z) {
        this.mNeedData = z;
    }
}
