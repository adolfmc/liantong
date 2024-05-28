package com.baidu.cloud.framecapture.camera;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.os.SystemClock;
import com.baidu.cloud.framecapture.Logging;
import com.baidu.cloud.framecapture.Size;
import com.baidu.cloud.framecapture.camera.CameraEnumerationAndroid;
import com.baidu.cloud.framecapture.camera.CameraSession;
import com.baidu.cloud.framework.frame.TextureBuffer;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class Camera1Session extends CameraSession {
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final String TAG = "Camera1Session";
    private final Context applicationContext;
    private final Camera camera;
    private final int cameraId;
    private final CameraEnumerationAndroid.CaptureFormat captureFormat;
    private final boolean captureToTexture;
    private final long constructionTimeNs;
    private final CameraSession.Events events;
    private boolean firstFrameReported;
    private final Camera.CameraInfo info;
    private SessionState state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum SessionState {
        RUNNING,
        STOPPED
    }

    public static void create(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, boolean z, Context context, SurfaceTexture surfaceTexture, int i, int i2, int i3, int i4, int i5) {
        long nanoTime = System.nanoTime();
        Logging.m20092d("Camera1Session", "Open camera " + i2);
        events.onCameraOpening();
        try {
            Camera open = Camera.open(i2);
            if (open == null) {
                createSessionCallback.onFailure(CameraSession.FailureType.ERROR, "android.hardware.Camera.open returned null for camera id = " + i2);
                return;
            }
            try {
                open.setPreviewTexture(surfaceTexture);
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i2, cameraInfo);
                try {
                    Camera.Parameters parameters = open.getParameters();
                    CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat = findClosestCaptureFormat(parameters, i3, i4, i5);
                    updateCameraParameters(open, parameters, findClosestCaptureFormat, findClosestPictureSize(parameters, i3, i4), z);
                    if (!z) {
                        int frameSize = findClosestCaptureFormat.frameSize();
                        for (int i6 = 0; i6 < 3; i6++) {
                            open.addCallbackBuffer(ByteBuffer.allocateDirect(frameSize).array());
                        }
                    }
                    open.setDisplayOrientation(0);
                    createSessionCallback.onDone(new Camera1Session(events, z, context, surfaceTexture, i, i2, open, cameraInfo, findClosestCaptureFormat, nanoTime));
                } catch (RuntimeException e) {
                    open.release();
                    createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
                }
            } catch (IOException | RuntimeException e2) {
                open.release();
                createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e2.getMessage());
            }
        } catch (RuntimeException e3) {
            createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e3.getMessage());
        }
    }

    private static void updateCameraParameters(Camera camera, Camera.Parameters parameters, CameraEnumerationAndroid.CaptureFormat captureFormat, Size size, boolean z) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        parameters.setPreviewFpsRange(captureFormat.framerate.min, captureFormat.framerate.max);
        parameters.setPreviewSize(captureFormat.width, captureFormat.height);
        parameters.setPictureSize(size.width, size.height);
        if (!z) {
            captureFormat.getClass();
            parameters.setPreviewFormat(17);
        }
        if (parameters.isVideoStabilizationSupported()) {
            parameters.setVideoStabilization(true);
        }
        if (supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        camera.setParameters(parameters);
    }

    private static CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat(Camera.Parameters parameters, int i, int i2, int i3) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates = Camera1Enumerator.convertFramerates(parameters.getSupportedPreviewFpsRange());
        Logging.m20092d("Camera1Session", "Available fps ranges: " + convertFramerates);
        CameraEnumerationAndroid.CaptureFormat.FramerateRange closestSupportedFramerateRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(convertFramerates, i3);
        Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPreviewSizes()), i, i2);
        return new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, closestSupportedFramerateRange);
    }

    private static Size findClosestPictureSize(Camera.Parameters parameters, int i, int i2) {
        return CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPictureSizes()), i, i2);
    }

    private Camera1Session(CameraSession.Events events, boolean z, Context context, SurfaceTexture surfaceTexture, int i, int i2, Camera camera, Camera.CameraInfo cameraInfo, CameraEnumerationAndroid.CaptureFormat captureFormat, long j) {
        Logging.m20092d("Camera1Session", "Create new camera1 session on camera " + i2);
        this.cameraThreadHandler = new Handler();
        this.events = events;
        this.captureToTexture = z;
        this.applicationContext = context;
        this.surfaceTexture = surfaceTexture;
        this.oesTextureId = i;
        this.cameraId = i2;
        this.camera = camera;
        this.info = cameraInfo;
        this.captureFormat = captureFormat;
        this.constructionTimeNs = j;
        surfaceTexture.setDefaultBufferSize(captureFormat.width, captureFormat.height);
        startCapturing();
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraSession
    public void stop() {
        Logging.m20092d("Camera1Session", "Stop camera1 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            stopInternal();
        }
    }

    private void startCapturing() {
        Logging.m20092d("Camera1Session", "Start capturing");
        checkIsOnCameraThread();
        this.state = SessionState.RUNNING;
        this.camera.setErrorCallback(new Camera.ErrorCallback() { // from class: com.baidu.cloud.framecapture.camera.Camera1Session.1
            @Override // android.hardware.Camera.ErrorCallback
            public void onError(int i, Camera camera) {
                String str;
                if (i == 100) {
                    str = "Camera server died!";
                } else {
                    str = "Camera error: " + i;
                }
                Logging.m20091e("Camera1Session", str);
                Camera1Session.this.stopInternal();
                if (i == 2) {
                    Camera1Session.this.events.onCameraDisconnected(Camera1Session.this);
                } else {
                    Camera1Session.this.events.onCameraError(Camera1Session.this, str);
                }
            }
        });
        if (this.captureToTexture) {
            listenForTextureFrames();
        } else {
            listenForBytebufferFrames();
        }
        try {
            this.camera.startPreview();
        } catch (RuntimeException e) {
            stopInternal();
            this.events.onCameraError(this, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopInternal() {
        Logging.m20092d("Camera1Session", "Stop internal");
        checkIsOnCameraThread();
        if (this.state == SessionState.STOPPED) {
            Logging.m20092d("Camera1Session", "Camera is already stopped");
            return;
        }
        this.state = SessionState.STOPPED;
        this.listening = false;
        this.camera.stopPreview();
        this.camera.release();
        this.events.onCameraClosed(this);
        Logging.m20092d("Camera1Session", "Stop done");
    }

    private void listenForTextureFrames() {
        this.listening = true;
    }

    private void listenForBytebufferFrames() {
        this.camera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() { // from class: com.baidu.cloud.framecapture.camera.Camera1Session.2
            @Override // android.hardware.Camera.PreviewCallback
            public void onPreviewFrame(byte[] bArr, Camera camera) {
                Camera1Session.this.checkIsOnCameraThread();
                if (camera == Camera1Session.this.camera) {
                    if (Camera1Session.this.state != SessionState.RUNNING) {
                        Logging.m20092d("Camera1Session", "Bytebuffer frame captured but camera is no longer running.");
                        return;
                    }
                    long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                    if (!Camera1Session.this.firstFrameReported) {
                        Camera1Session.this.firstFrameReported = true;
                    }
                    VideoFrameBuffer videoFrameBuffer = new VideoFrameBuffer();
                    videoFrameBuffer.width = Camera1Session.this.captureFormat.width;
                    videoFrameBuffer.height = Camera1Session.this.captureFormat.height;
                    videoFrameBuffer.rotation = Camera1Session.this.getFrameOrientation();
                    videoFrameBuffer.timestampNs = nanos;
                    videoFrameBuffer.pixelFormat = VideoFrameBuffer.PIXEL_FORMAT.YUV;
                    videoFrameBuffer.data = bArr;
                    Camera1Session.this.events.onFrameCaptured(Camera1Session.this, videoFrameBuffer);
                    if (Camera1Session.this.state == SessionState.RUNNING) {
                        Camera1Session.this.camera.addCallbackBuffer(bArr);
                        return;
                    }
                    return;
                }
                Logging.m20091e("Camera1Session", "Callback from a different camera. This should never happen.");
            }
        });
    }

    private byte[] rotateYUVDegree270AndMirror(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[(i3 * 3) / 2];
        int i4 = i - 1;
        int i5 = i4;
        int i6 = 0;
        while (i5 >= 0) {
            int i7 = ((i2 - 1) * i) + (i5 * 2);
            int i8 = i6;
            for (int i9 = 0; i9 < i2; i9++) {
                bArr2[i8] = bArr[i7 - ((i9 * i) + i5)];
                i8++;
            }
            i5--;
            i6 = i8;
        }
        int i10 = i3;
        while (i4 > 0) {
            int i11 = i2 / 2;
            int i12 = ((i11 - 1) * i) + (i4 * 2) + i3;
            int i13 = i10;
            for (int i14 = 0; i14 < i11; i14++) {
                int i15 = (i14 * i) + i4;
                bArr2[i13] = bArr[(i12 - 2) - (i15 - 1)];
                int i16 = i13 + 1;
                bArr2[i16] = bArr[i12 - i15];
                i13 = i16 + 1;
            }
            i4 -= 2;
            i10 = i13;
        }
        return bArr2;
    }

    private byte[] rotateYUVMirror(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[(i3 * 3) / 2];
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                bArr2[(i5 * i) + i4] = bArr[(((i2 - 1) - i5) * i) + i4];
            }
        }
        int i6 = i2 / 2;
        for (int i7 = 0; i7 < i; i7 += 2) {
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = (i8 * i) + i3 + i7;
                int i10 = (((i6 - 1) - i8) * i) + i3 + i7;
                bArr2[i9] = bArr[i10];
                bArr2[i9 + 1] = bArr[i10 + 1];
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.cloud.framecapture.camera.CameraSession
    public void tryDeliverTextureFrameInternal(long j, float[] fArr) {
        if (this.state != SessionState.RUNNING) {
            Logging.m20092d("Camera1Session", "Texture frame captured but camera is no longer running.");
            return;
        }
        if (!this.firstFrameReported) {
            this.firstFrameReported = true;
        }
        VideoFrameBuffer videoFrameBuffer = new VideoFrameBuffer();
        videoFrameBuffer.width = this.captureFormat.width;
        videoFrameBuffer.height = this.captureFormat.height;
        videoFrameBuffer.rotation = getFrameOrientation();
        videoFrameBuffer.timestampNs = j;
        videoFrameBuffer.pixelFormat = VideoFrameBuffer.PIXEL_FORMAT.TEXTURE;
        videoFrameBuffer.textureBuffer = new TextureBuffer(this.oesTextureId, TextureBuffer.Type.OES);
        videoFrameBuffer.transformMatrix = applyTransformMatrix(fArr, this.info.facing == 1, 0);
        this.events.onFrameCaptured(this, videoFrameBuffer);
    }

    float[] applyTransformMatrix(float[] fArr, boolean z) {
        Matrix matrix = new Matrix();
        matrix.preTranslate(0.5f, 0.5f);
        if (z) {
            matrix.preScale(-1.0f, -1.0f);
        }
        matrix.preTranslate(-0.5f, -0.5f);
        Matrix matrix2 = new Matrix(convertMatrixToAndroidGraphicsMatrix(fArr));
        matrix2.preConcat(matrix);
        return convertMatrixFromAndroidGraphicsMatrix(matrix2);
    }

    @Override // com.baidu.cloud.framecapture.camera.CameraSession
    int getFrameOrientation() {
        int deviceOrientation = getDeviceOrientation(this.applicationContext);
        if (this.info.facing == 0) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (this.info.orientation + deviceOrientation) % 360;
    }
}
