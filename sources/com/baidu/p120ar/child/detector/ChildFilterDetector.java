package com.baidu.p120ar.child.detector;

import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.detector.FrameDetector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.detector.ChildFilterDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildFilterDetector extends FrameDetector {
    private boolean isFront = true;

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return "ChildFilterDetector";
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
    }

    public ChildFilterDetector() {
        this.mReadParams = new PixelReadParams(PixelType.RGBA);
        this.mReadParams.setOutputWidth(720);
        this.mReadParams.setOutputHeight(1280);
    }

    public void setReadParamFilterId(String str) {
        if (str.equals("camera")) {
            this.mReadParams.setIsPortrait(true);
            if (!this.isFront) {
                this.mReadParams.setPixelRotate(PixelRotation.RotateRight);
            }
        }
        this.mReadParams.setPreFilterID(str);
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean detectFrame(FramePixels framePixels) {
        if (this.mDetectorCallback != null) {
            ChildFilterDetectResult childFilterDetectResult = new ChildFilterDetectResult();
            childFilterDetectResult.setTimestamp(framePixels.getTimestamp());
            childFilterDetectResult.setDatas(framePixels.getPixelData());
            this.isFront = framePixels.isFrontCamera();
            this.mDetectorCallback.onDetected(childFilterDetectResult);
            return false;
        }
        return false;
    }
}
