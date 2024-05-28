package com.baidu.p120ar.detector;

import android.os.Handler;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.FrameDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class FrameDetector extends AbstractDetector implements PixelReadListener {
    private static final String TAG = "FrameDetector";
    private static volatile boolean sNeedDetect = true;
    private boolean mDetectSync = false;
    private Handler mHandler;
    protected PixelReadParams mReadParams;

    @Override // com.baidu.p120ar.detector.IDetector
    public final void detect(DetectParams detectParams) {
    }

    protected abstract boolean detectFrame(FramePixels framePixels);

    public boolean isDetectEnable() {
        return true;
    }

    protected abstract void releaseFrameDetector();

    protected abstract void setupFrameDetector();

    @Override // com.baidu.p120ar.detector.AbstractDetector, com.baidu.p120ar.detector.IDetector
    public final void setup(DetectorCallback detectorCallback) {
        StringBuilder sb = new StringBuilder();
        sb.append("setup callback is ");
        sb.append(detectorCallback != null);
        ARLog.m20421d("FrameDetector", sb.toString());
        super.setup(detectorCallback);
        setupFrameDetector();
    }

    @Override // com.baidu.p120ar.detector.AbstractDetector, com.baidu.p120ar.detector.IDetector
    public final void release() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        releaseFrameDetector();
        super.release();
    }

    @Override // com.baidu.p120ar.arplay.core.pixel.PixelReadListener
    public boolean onPixelRead(final FramePixels framePixels) {
        if (!this.mDetectSync || sNeedDetect) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.baidu.ar.detector.FrameDetector.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FrameDetector.this.detectFrame(framePixels);
                    }
                });
                return true;
            }
            detectFrame(framePixels);
            return true;
        }
        return false;
    }

    public PixelReadParams getReadParams() {
        return this.mReadParams;
    }

    public final void setControlHandler(Handler handler) {
        this.mHandler = handler;
    }

    public boolean isDetectSync() {
        return this.mDetectSync;
    }

    public void setDetectSync(boolean z) {
        this.mDetectSync = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setNeedDetect(boolean z) {
        sNeedDetect = z;
    }
}
