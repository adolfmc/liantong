package com.baidu.p120ar.p121vo.detector;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.detector.FrameDetector;
import com.baidu.p120ar.p121vo.caseconfig.VOConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.detector.VODetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VODetector extends FrameDetector {
    private static final String TAG = "VODetector";
    private AlgoHandler mAlgoHandler;
    private HandlerThread mAlgoThread;
    private IVOAlgoTrack mTracker;

    public VODetector(VOConfig vOConfig, IVOAlgoTrack iVOAlgoTrack) {
        this.mTracker = iVOAlgoTrack;
        this.mReadParams = new PixelReadParams(PixelType.NV21);
        this.mReadParams.setOutputWidth(1280);
        this.mReadParams.setOutputHeight(720);
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        this.mAlgoThread = new HandlerThread(TAG);
        this.mAlgoThread.start();
        this.mAlgoHandler = new AlgoHandler(this.mAlgoThread.getLooper());
        this.mAlgoHandler.sendMessage(1001, new Runnable() { // from class: com.baidu.ar.vo.detector.VODetector.1
            @Override // java.lang.Runnable
            public void run() {
                VODetector.this.mTracker.init();
            }
        });
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean detectFrame(final FramePixels framePixels) {
        if (this.mTracker != null) {
            this.mAlgoHandler.sendMessage(1002, new Runnable() { // from class: com.baidu.ar.vo.detector.VODetector.2
                @Override // java.lang.Runnable
                public void run() {
                    VODetector.this.trackFrame(framePixels);
                }
            });
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackFrame(FramePixels framePixels) {
        IVOAlgoTrack iVOAlgoTrack = this.mTracker;
        if (iVOAlgoTrack != null) {
            iVOAlgoTrack.track(framePixels, new ICallbackWith<VOResult>() { // from class: com.baidu.ar.vo.detector.VODetector.3
                @Override // com.baidu.p120ar.callback.ICallbackWith
                public void run(VOResult vOResult) {
                    if (VODetector.this.mDetectorCallback == null || VODetector.this.mTracker == null) {
                        return;
                    }
                    if (vOResult != null) {
                        vOResult.setDetectorName(VODetector.this.getName());
                    }
                    VODetector.this.mDetectorCallback.onDetected(vOResult);
                }
            });
        }
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        AlgoHandler algoHandler = this.mAlgoHandler;
        if (algoHandler != null) {
            algoHandler.release();
            this.mAlgoHandler = null;
        }
        if (this.mTracker != null) {
            this.mTracker = null;
        }
        HandlerThread handlerThread = this.mAlgoThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mAlgoThread = null;
        }
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.vo.detector.VODetector$AlgoHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class AlgoHandler extends Handler {
        private static final int MSG_SLAM_RELEASE = 1003;
        private static final int MSG_SLAM_SETUP = 1001;
        private static final int MSG_SLAM_TRACK = 1002;

        public AlgoHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Runnable runnable = (Runnable) message.obj;
            if (runnable != null) {
                runnable.run();
            }
        }

        public void sendMessage(int i, Runnable runnable) {
            removeMessages(i);
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = runnable;
            sendMessage(obtain);
        }

        public void release() {
            removeMessages(1001);
            removeMessages(1002);
            Thread.currentThread().interrupt();
        }
    }
}
