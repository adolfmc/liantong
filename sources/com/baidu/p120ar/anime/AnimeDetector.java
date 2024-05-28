package com.baidu.p120ar.anime;

import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.detector.FrameDetector;
import com.baidu.p120ar.detector.ResultModel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.anime.AnimeDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AnimeDetector extends FrameDetector {
    private static final String TAG = "AnimeDetector";
    private int mInputImageWidth = 720;
    private int mInputImageHeight = 1280;
    private boolean mUpdateAvailable = true;
    private PixelCallback mPixelCallback = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.anime.AnimeDetector$PixelCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface PixelCallback {
        void onFramePixelRead(FramePixels framePixels);
    }

    public AnimeDetector() {
        this.mReadParams = new PixelReadParams(PixelType.RGBA);
        this.mReadParams.setOutputWidth(this.mInputImageWidth);
        this.mReadParams.setOutputHeight(this.mInputImageHeight);
    }

    public void setOrientationLandscapeOutput() {
        this.mReadParams.setOutputWidth(this.mInputImageHeight);
        this.mReadParams.setOutputHeight(this.mInputImageWidth);
        this.mReadParams.setPixelRotate(PixelRotation.RotateRightFlipHorizontal);
    }

    public void setPixelCallback(PixelCallback pixelCallback) {
        this.mPixelCallback = pixelCallback;
    }

    public void setReadParamFilterId(String str) {
        this.mReadParams.setPreFilterID(str);
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onSetup(new ResultModel(getName(), true));
        }
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean isDetectEnable() {
        return this.mUpdateAvailable;
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public final boolean detectFrame(FramePixels framePixels) {
        if (this.mUpdateAvailable) {
            this.mUpdateAvailable = false;
            PixelCallback pixelCallback = this.mPixelCallback;
            if (pixelCallback != null) {
                pixelCallback.onFramePixelRead(framePixels);
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onRelease(new ResultModel(getName(), true));
        }
    }
}
