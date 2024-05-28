package com.baidu.p120ar.child.detector;

import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.detector.FrameDetector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.detector.ChildCameraDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildCameraDetector extends FrameDetector {
    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return "ChildCameraDetector";
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
    }

    public ChildCameraDetector() {
        this.mReadParams = new PixelReadParams(PixelType.BGR);
        this.mReadParams.setOutputWidth(1280);
        this.mReadParams.setOutputHeight(720);
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean detectFrame(FramePixels framePixels) {
        if (this.mDetectorCallback != null) {
            ChildCameraDetectResult childCameraDetectResult = new ChildCameraDetectResult();
            childCameraDetectResult.setTimestamp(framePixels.getTimestamp());
            childCameraDetectResult.setDatas(framePixels.getPixelData());
            childCameraDetectResult.setDegree(framePixels.getOrientation().getDegree());
            childCameraDetectResult.setFront(framePixels.isFrontCamera());
            this.mDetectorCallback.onDetected(childCameraDetectResult);
            return false;
        }
        return false;
    }
}
