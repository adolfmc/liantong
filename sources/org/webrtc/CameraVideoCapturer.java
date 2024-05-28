package org.webrtc;

import android.media.MediaRecorder;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface CameraVideoCapturer extends VideoCapturer {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraDisconnected();

        void onCameraError(String str);

        void onCameraFreezed(String str);

        void onCameraOpening(String str);

        void onFirstFrameAvailable();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface CameraSwitchHandler {
        void onCameraSwitchDone(boolean z);

        void onCameraSwitchError(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface MediaRecorderHandler {
        void onMediaRecorderError(String str);

        void onMediaRecorderSuccess();
    }

    @Deprecated
    void addMediaRecorderToCamera(MediaRecorder mediaRecorder, MediaRecorderHandler mediaRecorderHandler);

    @Deprecated
    void removeMediaRecorderFromCamera(MediaRecorderHandler mediaRecorderHandler);

    void switchCamera(CameraSwitchHandler cameraSwitchHandler);

    void switchCamera(CameraSwitchHandler cameraSwitchHandler, String str);

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.webrtc.CameraVideoCapturer$-CC  reason: invalid class name */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        @Deprecated
        public static void $default$addMediaRecorderToCamera(CameraVideoCapturer cameraVideoCapturer, MediaRecorder mediaRecorder, MediaRecorderHandler mediaRecorderHandler) {
            throw new UnsupportedOperationException("Deprecated and not implemented.");
        }

        @Deprecated
        public static void $default$removeMediaRecorderFromCamera(CameraVideoCapturer cameraVideoCapturer, MediaRecorderHandler mediaRecorderHandler) {
            throw new UnsupportedOperationException("Deprecated and not implemented.");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class CameraStatistics {
        private static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 4000;
        private static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
        private static final String TAG = "CameraStatistics";
        private final Runnable cameraObserver = new Runnable() { // from class: org.webrtc.CameraVideoCapturer.CameraStatistics.1
            @Override // java.lang.Runnable
            public void run() {
                int round = Math.round((CameraStatistics.this.frameCount * 1000.0f) / 2000.0f);
                Logging.m206d("CameraStatistics", "Camera fps: " + round + ".");
                if (CameraStatistics.this.frameCount != 0) {
                    CameraStatistics.this.freezePeriodCount = 0;
                } else {
                    CameraStatistics.access$104(CameraStatistics.this);
                    if (CameraStatistics.this.freezePeriodCount * 2000 >= 4000 && CameraStatistics.this.eventsHandler != null) {
                        Logging.m205e("CameraStatistics", "Camera freezed.");
                        if (CameraStatistics.this.surfaceTextureHelper.isTextureInUse()) {
                            CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure. Client must return video buffers.");
                            return;
                        } else {
                            CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure.");
                            return;
                        }
                    }
                }
                CameraStatistics.this.frameCount = 0;
                CameraStatistics.this.surfaceTextureHelper.getHandler().postDelayed(this, 2000L);
            }
        };
        private final CameraEventsHandler eventsHandler;
        private int frameCount;
        private int freezePeriodCount;
        private final SurfaceTextureHelper surfaceTextureHelper;

        static /* synthetic */ int access$104(CameraStatistics cameraStatistics) {
            int i = cameraStatistics.freezePeriodCount + 1;
            cameraStatistics.freezePeriodCount = i;
            return i;
        }

        public CameraStatistics(SurfaceTextureHelper surfaceTextureHelper, CameraEventsHandler cameraEventsHandler) {
            if (surfaceTextureHelper == null) {
                throw new IllegalArgumentException("SurfaceTextureHelper is null");
            }
            this.surfaceTextureHelper = surfaceTextureHelper;
            this.eventsHandler = cameraEventsHandler;
            this.frameCount = 0;
            this.freezePeriodCount = 0;
            surfaceTextureHelper.getHandler().postDelayed(this.cameraObserver, 2000L);
        }

        private void checkThread() {
            if (Thread.currentThread() != this.surfaceTextureHelper.getHandler().getLooper().getThread()) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void addFrame() {
            checkThread();
            this.frameCount++;
        }

        public void release() {
            this.surfaceTextureHelper.getHandler().removeCallbacks(this.cameraObserver);
        }
    }
}
