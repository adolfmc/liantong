package com.baidu.cloud.framecapture.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.baidu.cloud.framecapture.CapturerObserver;
import com.baidu.cloud.framecapture.Logging;
import com.baidu.cloud.framecapture.camera.CameraSession;
import com.baidu.cloud.framecapture.camera.CameraVideoCapturer;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
abstract class CameraCapturer implements SurfaceTexture.OnFrameAvailableListener, CameraVideoCapturer {
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    private Context applicationContext;
    private final CameraEnumerator cameraEnumerator;
    private String cameraName;
    @Nullable
    private CameraVideoCapturer.CameraStatistics cameraStatistics;
    private Handler cameraThreadHandler;
    private CapturerObserver capturerObserver;
    @Nullable
    private CameraSession currentSession;
    private EGLContext eglContext;
    private final CameraVideoCapturer.CameraEventsHandler eventsHandler;
    private boolean firstFrameObserved;
    private int framerate;
    boolean hasPendingTexture;
    private int height;
    private int oesTextureId;
    private int openAttemptsRemaining;
    private String pendingCameraName;
    private boolean sessionOpening;
    private SurfaceTexture surfaceTexture;
    private final Handler uiThreadHandler;
    private int width;
    @Nullable
    private final CameraSession.CreateSessionCallback createSessionCallback = new CameraSession.CreateSessionCallback() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.1
        @Override // com.baidu.cloud.framecapture.camera.CameraSession.CreateSessionCallback
        public void onDone(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            Logging.m20092d("CameraCapturer", "Create session done. Switch state: " + CameraCapturer.this.switchState);
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(true);
                CameraCapturer.this.sessionOpening = false;
                CameraCapturer.this.currentSession = cameraSession;
                CameraCapturer.this.cameraStatistics = new CameraVideoCapturer.CameraStatistics(CameraCapturer.this.cameraThreadHandler, CameraCapturer.this.eventsHandler);
                CameraCapturer.this.firstFrameObserved = false;
                CameraCapturer.this.stateLock.notifyAll();
                if (CameraCapturer.this.switchState != SwitchState.IN_PROGRESS) {
                    if (CameraCapturer.this.switchState == SwitchState.PENDING) {
                        String str = CameraCapturer.this.pendingCameraName;
                        CameraCapturer.this.pendingCameraName = null;
                        CameraCapturer.this.switchState = SwitchState.IDLE;
                        CameraCapturer.this.switchCameraInternal(str);
                    }
                } else {
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                    CameraCapturer.this.eventsHandler.onCameraSwitchDone(CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                }
            }
        }

        @Override // com.baidu.cloud.framecapture.camera.CameraSession.CreateSessionCallback
        public void onFailure(CameraSession.FailureType failureType, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(false);
                CameraCapturer.access$1610(CameraCapturer.this);
                if (CameraCapturer.this.openAttemptsRemaining <= 0) {
                    Logging.m20088w("CameraCapturer", "Opening camera failed, passing: " + str);
                    CameraCapturer.this.sessionOpening = false;
                    CameraCapturer.this.stateLock.notifyAll();
                    if (CameraCapturer.this.switchState != SwitchState.IDLE) {
                        CameraCapturer.this.eventsHandler.onCameraSwitchError(str);
                        CameraCapturer.this.switchState = SwitchState.IDLE;
                    }
                    if (failureType == CameraSession.FailureType.DISCONNECTED) {
                        CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    } else {
                        CameraCapturer.this.eventsHandler.onCameraError(str);
                    }
                } else {
                    Logging.m20088w("CameraCapturer", "Opening camera failed, retry: " + str);
                    CameraCapturer.this.createSessionInternal(500);
                }
            }
        }
    };
    @Nullable
    private final CameraSession.Events cameraSessionEventsHandler = new CameraSession.Events() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.2
        @Override // com.baidu.cloud.framecapture.camera.CameraSession.Events
        public void onCameraOpening() {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraOpening(CameraCapturer.this.cameraName, CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                } else {
                    Logging.m20088w("CameraCapturer", "onCameraOpening while session was open.");
                }
            }
        }

        @Override // com.baidu.cloud.framecapture.camera.CameraSession.Events
        public void onCameraError(CameraSession cameraSession, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    CameraCapturer.this.eventsHandler.onCameraError(str);
                    CameraCapturer.this.stopCapture();
                    return;
                }
                Logging.m20088w("CameraCapturer", "onCameraError from another session: " + str);
            }
        }

        @Override // com.baidu.cloud.framecapture.camera.CameraSession.Events
        public void onCameraDisconnected(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    CameraCapturer.this.stopCapture();
                    return;
                }
                Logging.m20088w("CameraCapturer", "onCameraDisconnected from another session.");
            }
        }

        @Override // com.baidu.cloud.framecapture.camera.CameraSession.Events
        public void onCameraClosed(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession || CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraClosed();
                } else {
                    Logging.m20092d("CameraCapturer", "onCameraClosed from another session.");
                }
            }
        }

        @Override // com.baidu.cloud.framecapture.camera.CameraSession.Events
        public void onFrameCaptured(CameraSession cameraSession, VideoFrameBuffer videoFrameBuffer) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    if (!CameraCapturer.this.firstFrameObserved) {
                        CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                        CameraCapturer.this.firstFrameObserved = true;
                    }
                    CameraCapturer.this.cameraStatistics.addFrame();
                    CameraCapturer.this.capturerObserver.onFrameCaptured(videoFrameBuffer);
                    return;
                }
                Logging.m20088w("CameraCapturer", "onFrameCaptured from another session.");
            }
        }
    };
    private final Runnable openCameraTimeoutRunnable = new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.3
        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer.this.eventsHandler.onCameraError("Camera failed to start within timeout.");
        }
    };
    private final Object stateLock = new Object();
    private SwitchState switchState = SwitchState.IDLE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum SwitchState {
        IDLE,
        PENDING,
        IN_PROGRESS
    }

    protected abstract void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTexture surfaceTexture, int i, String str, int i2, int i3, int i4);

    static /* synthetic */ int access$1610(CameraCapturer cameraCapturer) {
        int i = cameraCapturer.openAttemptsRemaining;
        cameraCapturer.openAttemptsRemaining = i - 1;
        return i;
    }

    public CameraCapturer(String str, @Nullable CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, CameraEnumerator cameraEnumerator) {
        this.eventsHandler = cameraEventsHandler == null ? new CameraVideoCapturer.CameraEventsHandler() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.4
            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraClosed() {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraDisconnected() {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraError(String str2) {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraFreezed(String str2) {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str2, boolean z) {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraSwitchDone(boolean z) {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onCameraSwitchError(String str2) {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void onFirstFrameAvailable() {
            }

            @Override // com.baidu.cloud.framecapture.camera.CameraVideoCapturer.CameraEventsHandler
            public void syncUpdateTexImage(SurfaceTexture surfaceTexture) {
            }
        } : cameraEventsHandler;
        this.cameraEnumerator = cameraEnumerator;
        this.cameraName = str;
        List asList = Arrays.asList(cameraEnumerator.getDeviceNames());
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        if (asList.isEmpty()) {
            throw new RuntimeException("No cameras attached.");
        }
        if (asList.contains(this.cameraName)) {
            return;
        }
        throw new IllegalArgumentException("Camera name " + this.cameraName + " does not match any known camera device.");
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.hasPendingTexture = true;
        tryDeliverTextureFrame();
    }

    private void tryDeliverTextureFrame() {
        checkIsOnCameraThread();
        synchronized (this.stateLock) {
            if (this.hasPendingTexture) {
                this.hasPendingTexture = false;
                this.eventsHandler.syncUpdateTexImage(this.surfaceTexture);
                if (this.currentSession != null && this.currentSession.listening) {
                    float[] fArr = new float[16];
                    this.surfaceTexture.getTransformMatrix(fArr);
                    this.currentSession.tryDeliverTextureFrameInternal(this.surfaceTexture.getTimestamp(), fArr);
                    return;
                }
                Logging.m20092d("CameraCapturer", "tryDeliverTextureFrame currentSession : " + this.currentSession);
            }
        }
    }

    public void initialize(SurfaceTexture surfaceTexture, int i, Handler handler, Context context, CapturerObserver capturerObserver) {
        this.applicationContext = context;
        this.capturerObserver = capturerObserver;
        this.surfaceTexture = surfaceTexture;
        this.oesTextureId = i;
        this.cameraThreadHandler = handler;
        if (Build.VERSION.SDK_INT >= 21) {
            surfaceTexture.setOnFrameAvailableListener(this, handler);
        } else {
            surfaceTexture.setOnFrameAvailableListener(this);
        }
    }

    public void startCapture(int i, int i2, int i3) {
        Logging.m20092d("CameraCapturer", "startCapture: " + i + "x" + i2 + "@" + i3);
        if (this.applicationContext == null) {
            throw new RuntimeException("CameraCapturer must be initialized before calling startCapture.");
        }
        synchronized (this.stateLock) {
            if (!this.sessionOpening && this.currentSession == null) {
                this.width = i;
                this.height = i2;
                this.framerate = i3;
                this.sessionOpening = true;
                this.openAttemptsRemaining = 3;
                createSessionInternal(0);
                return;
            }
            Logging.m20088w("CameraCapturer", "Session already open");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSessionInternal(int i) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, i + 10000);
        this.cameraThreadHandler.postDelayed(new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.5
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer cameraCapturer = CameraCapturer.this;
                cameraCapturer.createCameraSession(cameraCapturer.createSessionCallback, CameraCapturer.this.cameraSessionEventsHandler, CameraCapturer.this.applicationContext, CameraCapturer.this.surfaceTexture, CameraCapturer.this.oesTextureId, CameraCapturer.this.cameraName, CameraCapturer.this.width, CameraCapturer.this.height, CameraCapturer.this.framerate);
            }
        }, i);
    }

    public void stopCapture() {
        Logging.m20092d("CameraCapturer", "Stop capture");
        synchronized (this.stateLock) {
            while (this.sessionOpening) {
                Logging.m20092d("CameraCapturer", "Stop capture: Waiting for session to open");
                try {
                    this.stateLock.wait();
                } catch (InterruptedException unused) {
                    Logging.m20088w("CameraCapturer", "Stop capture interrupted while waiting for the session to open.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (this.currentSession != null) {
                Logging.m20092d("CameraCapturer", "Stop capture: Nulling session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession cameraSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.6
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraSession.stop();
                    }
                });
                this.currentSession = null;
                this.capturerObserver.onCapturerStopped();
            } else {
                Logging.m20092d("CameraCapturer", "Stop capture: No session open");
            }
        }
        Logging.m20092d("CameraCapturer", "Stop capture done");
    }

    public void changeCaptureFormat(int i, int i2, int i3) {
        Logging.m20092d("CameraCapturer", "changeCaptureFormat: " + i + "x" + i2 + "@" + i3);
        synchronized (this.stateLock) {
            stopCapture();
            startCapture(i, i2, i3);
        }
    }

    public void dispose() {
        Logging.m20092d("CameraCapturer", "dispose");
        stopCapture();
    }

    public boolean isFrontCamera(int i) {
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        if (i <= 0 || i >= deviceNames.length) {
            return false;
        }
        return this.cameraEnumerator.isFrontFacing(deviceNames[i]);
    }

    public void switchCamera() {
        Logging.m20092d("CameraCapturer", "switchCamera");
        this.cameraThreadHandler.post(new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.7
            @Override // java.lang.Runnable
            public void run() {
                List asList = Arrays.asList(CameraCapturer.this.cameraEnumerator.getDeviceNames());
                if (asList.size() >= 2) {
                    boolean z = !CameraCapturer.this.cameraEnumerator.isBackFacing(CameraCapturer.this.cameraName);
                    Iterator it = asList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String str = (String) it.next();
                        if (z) {
                            if (CameraCapturer.this.cameraEnumerator.isBackFacing(str)) {
                                CameraCapturer.this.cameraName = str;
                                break;
                            }
                        } else if (CameraCapturer.this.cameraEnumerator.isFrontFacing(str)) {
                            CameraCapturer.this.cameraName = str;
                            break;
                        }
                    }
                    CameraCapturer cameraCapturer = CameraCapturer.this;
                    cameraCapturer.switchCameraInternal(cameraCapturer.cameraName);
                    return;
                }
                CameraCapturer cameraCapturer2 = CameraCapturer.this;
                cameraCapturer2.reportCameraSwitchError("No camera to switch to.", cameraCapturer2.eventsHandler);
            }
        });
    }

    public void switchCamera(final String str) {
        Logging.m20092d("CameraCapturer", "switchCamera");
        this.cameraThreadHandler.post(new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.8
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer.this.switchCameraInternal(str);
            }
        });
    }

    public void printStackTrace() {
        Handler handler = this.cameraThreadHandler;
        Thread thread = handler != null ? handler.getLooper().getThread() : null;
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.m20092d("CameraCapturer", "CameraCapturer stack trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.m20092d("CameraCapturer", stackTraceElement.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportCameraSwitchError(String str, @Nullable CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        Logging.m20091e("CameraCapturer", str);
        if (cameraEventsHandler != null) {
            cameraEventsHandler.onCameraSwitchError(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCameraInternal(String str) {
        Logging.m20092d("CameraCapturer", "switchCamera internal");
        if (!Arrays.asList(this.cameraEnumerator.getDeviceNames()).contains(str)) {
            reportCameraSwitchError("Attempted to switch to unknown camera device " + str, this.eventsHandler);
            return;
        }
        synchronized (this.stateLock) {
            if (this.switchState != SwitchState.IDLE) {
                reportCameraSwitchError("Camera switch already in progress.", this.eventsHandler);
            } else if (!this.sessionOpening && this.currentSession == null) {
                reportCameraSwitchError("switchCamera: camera is not running.", this.eventsHandler);
            } else if (this.sessionOpening) {
                this.switchState = SwitchState.PENDING;
                this.pendingCameraName = str;
            } else {
                this.switchState = SwitchState.IN_PROGRESS;
                Logging.m20092d("CameraCapturer", "switchCamera: Stopping session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession cameraSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() { // from class: com.baidu.cloud.framecapture.camera.CameraCapturer.9
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraSession.stop();
                    }
                });
                this.currentSession = null;
                this.cameraName = str;
                this.sessionOpening = true;
                this.openAttemptsRemaining = 1;
                createSessionInternal(0);
                Logging.m20092d("CameraCapturer", "switchCamera done");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread()) {
            return;
        }
        Logging.m20091e("CameraCapturer", "Check is on camera thread failed.");
        throw new RuntimeException("Not on camera thread.");
    }

    protected String getCameraName() {
        String str;
        synchronized (this.stateLock) {
            str = this.cameraName;
        }
        return str;
    }
}
