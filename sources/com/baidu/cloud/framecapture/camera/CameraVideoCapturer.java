package com.baidu.cloud.framecapture.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import com.baidu.cloud.framecapture.CapturerObserver;
import com.baidu.cloud.framecapture.Logging;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface CameraVideoCapturer {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraDisconnected();

        void onCameraError(String str);

        void onCameraFreezed(String str);

        void onCameraOpening(String str, boolean z);

        void onCameraSwitchDone(boolean z);

        void onCameraSwitchError(String str);

        void onFirstFrameAvailable();

        void syncUpdateTexImage(SurfaceTexture surfaceTexture);
    }

    void changeCaptureFormat(int i, int i2, int i3);

    void dispose();

    void initialize(SurfaceTexture surfaceTexture, int i, Handler handler, Context context, CapturerObserver capturerObserver);

    boolean isFrontCamera(int i);

    void startCapture(int i, int i2, int i3);

    void stopCapture() throws InterruptedException;

    void switchCamera();

    void switchCamera(String str);

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class CameraStatistics {
        private static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 4000;
        private static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
        private static final String TAG = "CameraStatistics";
        private final Runnable cameraObserver = new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraStatistics.1
            @Override // java.lang.Runnable
            public void run() {
                int round = Math.round((CameraStatistics.this.frameCount * 1000.0f) / 2000.0f);
                Logging.m20092d("CameraStatistics", "Camera fps: " + round + ".");
                if (CameraStatistics.this.frameCount != 0) {
                    CameraStatistics.this.freezePeriodCount = 0;
                } else {
                    CameraStatistics.access$104(CameraStatistics.this);
                    if (CameraStatistics.this.freezePeriodCount * 2000 >= 4000 && CameraStatistics.this.eventsHandler != null) {
                        Logging.m20091e("CameraStatistics", "Camera freezed.");
                        CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure.");
                        return;
                    }
                }
                CameraStatistics.this.frameCount = 0;
                CameraStatistics.this.cameraThreadHandler.postDelayed(this, 2000L);
            }
        };
        private final Handler cameraThreadHandler;
        private final CameraEventsHandler eventsHandler;
        private int frameCount;
        private int freezePeriodCount;

        static /* synthetic */ int access$104(CameraStatistics cameraStatistics) {
            int i = cameraStatistics.freezePeriodCount + 1;
            cameraStatistics.freezePeriodCount = i;
            return i;
        }

        public CameraStatistics(Handler handler, CameraEventsHandler cameraEventsHandler) {
            if (handler == null) {
                throw new IllegalArgumentException("CameraThreadHandler is null");
            }
            this.cameraThreadHandler = handler;
            this.eventsHandler = cameraEventsHandler;
            this.frameCount = 0;
            this.freezePeriodCount = 0;
            handler.postDelayed(this.cameraObserver, 2000L);
        }

        private void checkThread() {
            if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void addFrame() {
            checkThread();
            this.frameCount++;
        }

        public void release() {
            this.cameraThreadHandler.removeCallbacks(this.cameraObserver);
        }
    }
}
