package com.megvii.livenesslib.kas.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.megvii.livenesslib.kas.encoder.MediaVideoEncoder;
import com.megvii.livenesslib.kas.p227gl.GLDrawer2D;
import com.megvii.livenesslib.util.Screen;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class CameraGLView extends GLSurfaceView {
    private static final int CAMERA_ID = 1;
    private static final boolean DEBUG = true;
    private static final int SCALE_CROP_CENTER = 3;
    private static final int SCALE_KEEP_ASPECT = 2;
    private static final int SCALE_KEEP_ASPECT_VIEWPORT = 1;
    private static final int SCALE_STRETCH_FIT = 0;
    private static final String TAG = "CameraGLView";
    public static double biLi = 0.75d;
    private Camera.PreviewCallback cbOnPreviewFrame;
    private CameraGLSurfaceCallback cbOnSurfaceCreated;
    private CameraHandler mCameraHandler;
    private boolean mHasSurface;
    private final CameraSurfaceRenderer mRenderer;
    private int mRotation;
    private int mScaleMode;
    private int mVideoHeight;
    private int mVideoWidth;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CameraGLSurfaceCallback {
        void onSurfaceCreated();
    }

    public CameraGLView(Context context) {
        this(context, null, 0);
    }

    public CameraGLView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CameraGLView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mCameraHandler = null;
        this.mScaleMode = 3;
        this.cbOnPreviewFrame = null;
        this.cbOnSurfaceCreated = null;
        Log.v(TAG, "CameraGLView:");
        this.mRenderer = new CameraSurfaceRenderer(this);
        setEGLContextClientVersion(2);
        setRenderer(this.mRenderer);
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        Log.v(TAG, "onResume:");
        super.onResume();
        if (this.mHasSurface && this.mCameraHandler == null) {
            Log.v(TAG, "surface already exist");
            startPreview(getWidth(), getHeight());
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        Log.v(TAG, "onPause:");
        CameraHandler cameraHandler = this.mCameraHandler;
        if (cameraHandler != null) {
            cameraHandler.stopPreview(false);
        }
        getHolder().getSurface().release();
        super.onPause();
    }

    public void setScaleMode(int i) {
        if (this.mScaleMode != i) {
            this.mScaleMode = i;
            queueEvent(new Runnable() { // from class: com.megvii.livenesslib.kas.view.CameraGLView.1
                @Override // java.lang.Runnable
                public void run() {
                    CameraGLView.this.mRenderer.updateViewport();
                }
            });
        }
    }

    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.cbOnPreviewFrame = previewCallback;
    }

    public void setCameraGLSurfaceCallback(CameraGLSurfaceCallback cameraGLSurfaceCallback) {
        this.cbOnSurfaceCreated = cameraGLSurfaceCallback;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setVideoSize(int i, int i2) {
        float f = i2;
        float f2 = i;
        float min = Math.min((Screen.mWidth * 1.0f) / f, (Screen.mHeight * 1.0f) / f2);
        int i3 = (int) (f * min);
        int i4 = (int) (min * f2);
        biLi = i3 / i4;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        try {
            if (Screen.mScreenWidth / Screen.mScreenHeight > 0.75d) {
                layoutParams.width = (int) ((Screen.mScreenHeight / 2) * biLi);
                layoutParams.height = Screen.mScreenHeight / 2;
                layoutParams.leftMargin = (int) ((Screen.mScreenWidth - ((Screen.mScreenHeight / 2) * biLi)) / 2.0d);
                i = (int) ((Screen.mScreenHeight / 2) * biLi);
                i2 = Screen.mScreenHeight / 2;
            } else {
                layoutParams.width = (int) (Screen.mScreenWidth * biLi);
                layoutParams.height = Screen.mScreenWidth;
                layoutParams.leftMargin = (int) ((Screen.mScreenWidth - (Screen.mScreenWidth * biLi)) / 2.0d);
                i = (int) (Screen.mScreenWidth * biLi);
                i2 = Screen.mScreenWidth;
            }
        } catch (Exception unused) {
        }
        setLayoutParams(layoutParams);
        if (this.mRotation % 180 == 0) {
            this.mVideoWidth = i;
            this.mVideoHeight = i2;
        } else {
            this.mVideoWidth = i2;
            this.mVideoHeight = i;
        }
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        queueEvent(new Runnable() { // from class: com.megvii.livenesslib.kas.view.CameraGLView.2
            @Override // java.lang.Runnable
            public void run() {
                CameraGLView.this.mRenderer.updateViewport();
            }
        });
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public SurfaceTexture getSurfaceTexture() {
        Log.v(TAG, "getSurfaceTexture:");
        CameraSurfaceRenderer cameraSurfaceRenderer = this.mRenderer;
        if (cameraSurfaceRenderer != null) {
            return cameraSurfaceRenderer.mSTexture;
        }
        return null;
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.v(TAG, "surfaceDestroyed:");
        CameraHandler cameraHandler = this.mCameraHandler;
        if (cameraHandler != null) {
            cameraHandler.stopPreview(true);
        }
        this.mCameraHandler = null;
        this.mHasSurface = false;
        this.mRenderer.onSurfaceDestroyed();
        super.surfaceDestroyed(surfaceHolder);
    }

    public void setVideoEncoder(final MediaVideoEncoder mediaVideoEncoder) {
        Log.v(TAG, "setVideoEncoder:tex_id=" + this.mRenderer.hTex + ",encoder=" + mediaVideoEncoder);
        queueEvent(new Runnable() { // from class: com.megvii.livenesslib.kas.view.CameraGLView.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (CameraGLView.this.mRenderer) {
                    if (mediaVideoEncoder != null) {
                        mediaVideoEncoder.setEglContext(EGL14.eglGetCurrentContext(), CameraGLView.this.mRenderer.hTex);
                    }
                    CameraGLView.this.mRenderer.mVideoEncoder = mediaVideoEncoder;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startPreview(int i, int i2) {
        if (this.mCameraHandler == null) {
            CameraThread cameraThread = new CameraThread(this);
            cameraThread.start();
            this.mCameraHandler = cameraThread.getHandler();
        }
        this.mCameraHandler.startPreview(640, 480);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class CameraSurfaceRenderer implements SurfaceTexture.OnFrameAvailableListener, GLSurfaceView.Renderer {
        private int hTex;
        private GLDrawer2D mDrawer;
        private SurfaceTexture mSTexture;
        private MediaVideoEncoder mVideoEncoder;
        private final WeakReference<CameraGLView> mWeakParent;
        private final float[] mStMatrix = new float[16];
        private final float[] mMvpMatrix = new float[16];
        private volatile boolean requesrUpdateTex = false;
        private boolean flip = true;

        public CameraSurfaceRenderer(CameraGLView cameraGLView) {
            Log.v(CameraGLView.TAG, "CameraSurfaceRenderer:");
            this.mWeakParent = new WeakReference<>(cameraGLView);
            Matrix.setIdentityM(this.mMvpMatrix, 0);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            Log.v(CameraGLView.TAG, "onSurfaceCreated:");
            if (!GLES20.glGetString(7939).contains("OES_EGL_image_external")) {
                throw new RuntimeException("This system does not support OES_EGL_image_external.");
            }
            this.hTex = GLDrawer2D.initTex();
            this.mSTexture = new SurfaceTexture(this.hTex);
            this.mSTexture.setOnFrameAvailableListener(this);
            GLES20.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
            CameraGLView cameraGLView = this.mWeakParent.get();
            if (cameraGLView != null) {
                cameraGLView.mHasSurface = true;
            }
            this.mDrawer = new GLDrawer2D();
            this.mDrawer.setMatrix(this.mMvpMatrix, 0);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
            Log.v(CameraGLView.TAG, String.format("onSurfaceChanged:(%d,%d)", Integer.valueOf(i), Integer.valueOf(i2)));
            if (i == 0 || i2 == 0) {
                return;
            }
            updateViewport();
            CameraGLView cameraGLView = this.mWeakParent.get();
            if (cameraGLView != null) {
                cameraGLView.startPreview(i, i2);
            }
        }

        public void onSurfaceDestroyed() {
            Log.v(CameraGLView.TAG, "onSurfaceDestroyed:");
            GLDrawer2D gLDrawer2D = this.mDrawer;
            if (gLDrawer2D != null) {
                gLDrawer2D.release();
                this.mDrawer = null;
            }
            SurfaceTexture surfaceTexture = this.mSTexture;
            if (surfaceTexture != null) {
                surfaceTexture.release();
                this.mSTexture = null;
            }
            GLDrawer2D.deleteTex(this.hTex);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateViewport() {
            CameraSurfaceRenderer cameraSurfaceRenderer;
            int i;
            int i2;
            int i3;
            int i4;
            CameraGLView cameraGLView = this.mWeakParent.get();
            if (cameraGLView != null) {
                int width = cameraGLView.getWidth();
                int height = cameraGLView.getHeight();
                GLES20.glViewport(0, 0, width, height);
                GLES20.glClear(16384);
                double d = cameraGLView.mVideoWidth;
                double d2 = cameraGLView.mVideoHeight;
                if (d == 0.0d || d2 == 0.0d) {
                    return;
                }
                Matrix.setIdentityM(this.mMvpMatrix, 0);
                double d3 = width;
                double d4 = height;
                double d5 = d3 / d4;
                Log.i(CameraGLView.TAG, String.format("view(%d,%d)%f,video(%1.0f,%1.0f)", Integer.valueOf(width), Integer.valueOf(height), Double.valueOf(d5), Double.valueOf(d), Double.valueOf(d2)));
                switch (cameraGLView.mScaleMode) {
                    case 0:
                        cameraSurfaceRenderer = this;
                        break;
                    case 1:
                        cameraSurfaceRenderer = this;
                        double d6 = d / d2;
                        if (d5 > d6) {
                            i3 = (int) (d6 * d4);
                            i4 = (width - i3) / 2;
                            i2 = height;
                            i = 0;
                        } else {
                            int i5 = (int) (d3 / d6);
                            i = (height - i5) / 2;
                            i2 = i5;
                            i3 = width;
                            i4 = 0;
                        }
                        Log.v(CameraGLView.TAG, String.format("xy(%d,%d),size(%d,%d)", Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i2)));
                        GLES20.glViewport(i4, i, i3, i2);
                        break;
                    case 2:
                    case 3:
                        double d7 = d3 / d;
                        double d8 = d4 / d2;
                        double max = cameraGLView.mScaleMode == 3 ? Math.max(d7, d8) : Math.min(d7, d8);
                        double d9 = d * max;
                        double d10 = max * d2;
                        double d11 = d9 / d3;
                        double d12 = d10 / d4;
                        Log.v(CameraGLView.TAG, String.format("size(%1.0f,%1.0f),scale(%f,%f),mat(%f,%f)", Double.valueOf(d9), Double.valueOf(d10), Double.valueOf(d7), Double.valueOf(d8), Double.valueOf(d11), Double.valueOf(d12)));
                        cameraSurfaceRenderer = this;
                        Matrix.scaleM(cameraSurfaceRenderer.mMvpMatrix, 0, (float) d11, (float) d12, 1.0f);
                        break;
                    default:
                        cameraSurfaceRenderer = this;
                        break;
                }
                GLDrawer2D gLDrawer2D = cameraSurfaceRenderer.mDrawer;
                if (gLDrawer2D != null) {
                    gLDrawer2D.setMatrix(cameraSurfaceRenderer.mMvpMatrix, 0);
                }
            }
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl10) {
            GLES20.glClear(16384);
            if (this.requesrUpdateTex) {
                this.requesrUpdateTex = false;
                this.mSTexture.updateTexImage();
                this.mSTexture.getTransformMatrix(this.mStMatrix);
            }
            this.mDrawer.draw(this.hTex, this.mStMatrix);
            this.flip = !this.flip;
            if (this.flip) {
                synchronized (this) {
                    if (this.mVideoEncoder != null) {
                        this.mVideoEncoder.frameAvailableSoon(this.mStMatrix, this.mMvpMatrix);
                    }
                }
            }
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.requesrUpdateTex = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class CameraHandler extends Handler {
        private static final int MSG_PREVIEW_START = 1;
        private static final int MSG_PREVIEW_STOP = 2;
        private CameraThread mThread;

        public CameraHandler(CameraThread cameraThread) {
            this.mThread = cameraThread;
        }

        public void startPreview(int i, int i2) {
            sendMessage(obtainMessage(1, i, i2));
        }

        public void stopPreview(boolean z) {
            synchronized (this) {
                sendEmptyMessage(2);
                if (z && this.mThread.mIsRunning) {
                    try {
                        Log.d(CameraGLView.TAG, "wait for terminating of camera thread");
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.mThread.startPreview(message.arg1, message.arg2);
                    return;
                case 2:
                    this.mThread.stopPreview();
                    synchronized (this) {
                        notifyAll();
                    }
                    Looper.myLooper().quit();
                    this.mThread = null;
                    return;
                default:
                    throw new RuntimeException("unknown message:what=" + message.what);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class CameraThread extends Thread {
        private Camera mCamera;
        private CameraHandler mHandler;
        private boolean mIsFrontFace;
        private volatile boolean mIsRunning;
        private final Object mReadyFence;
        private final WeakReference<CameraGLView> mWeakParent;

        public CameraThread(CameraGLView cameraGLView) {
            super("Camera thread");
            this.mReadyFence = new Object();
            this.mIsRunning = false;
            this.mWeakParent = new WeakReference<>(cameraGLView);
        }

        public CameraHandler getHandler() {
            synchronized (this.mReadyFence) {
                try {
                    this.mReadyFence.wait();
                } catch (InterruptedException unused) {
                }
            }
            return this.mHandler;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.d(CameraGLView.TAG, "Camera thread start");
            Looper.prepare();
            synchronized (this.mReadyFence) {
                this.mHandler = new CameraHandler(this);
                this.mIsRunning = true;
                this.mReadyFence.notify();
            }
            Looper.loop();
            Log.d(CameraGLView.TAG, "Camera thread finish");
            synchronized (this.mReadyFence) {
                this.mHandler = null;
                this.mIsRunning = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void startPreview(int i, int i2) {
            Log.v(CameraGLView.TAG, "startPreview:");
            final CameraGLView cameraGLView = this.mWeakParent.get();
            if (cameraGLView == null || this.mCamera != null) {
                return;
            }
            try {
                this.mCamera = Camera.open(1);
                Camera.Parameters parameters = this.mCamera.getParameters();
                List<String> supportedFocusModes = parameters.getSupportedFocusModes();
                if (supportedFocusModes.contains("continuous-video")) {
                    parameters.setFocusMode("continuous-video");
                } else if (supportedFocusModes.contains("auto")) {
                    parameters.setFocusMode("auto");
                } else {
                    Log.i(CameraGLView.TAG, "Camera does not support autofocus");
                }
                List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
                int[] iArr = supportedPreviewFpsRange.get(supportedPreviewFpsRange.size() - 1);
                Log.i(CameraGLView.TAG, String.format("fps:%d-%d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1])));
                parameters.setPreviewFpsRange(iArr[0], iArr[1]);
                parameters.setRecordingHint(true);
                Camera.Size closestSupportedSize = getClosestSupportedSize(parameters.getSupportedPreviewSizes(), i, i2);
                parameters.setPreviewSize(closestSupportedSize.width, closestSupportedSize.height);
                Camera.Size closestSupportedSize2 = getClosestSupportedSize(parameters.getSupportedPictureSizes(), i, i2);
                parameters.setPictureSize(closestSupportedSize2.width, closestSupportedSize2.height);
                setRotation(parameters);
                this.mCamera.setParameters(parameters);
                final Camera.Size previewSize = this.mCamera.getParameters().getPreviewSize();
                Log.i(CameraGLView.TAG, String.format("previewSize(%d, %d)", Integer.valueOf(previewSize.width), Integer.valueOf(previewSize.height)));
                cameraGLView.post(new Runnable() { // from class: com.megvii.livenesslib.kas.view.CameraGLView.CameraThread.1
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraGLView.setVideoSize(previewSize.width, previewSize.height);
                    }
                });
                SurfaceTexture surfaceTexture = cameraGLView.getSurfaceTexture();
                surfaceTexture.setDefaultBufferSize(previewSize.width, previewSize.height);
                this.mCamera.setPreviewTexture(surfaceTexture);
            } catch (IOException e) {
                Log.e(CameraGLView.TAG, "startPreview:", e);
                Camera camera = this.mCamera;
                if (camera != null) {
                    camera.release();
                    this.mCamera = null;
                }
            } catch (RuntimeException e2) {
                Log.e(CameraGLView.TAG, "startPreview:", e2);
                Camera camera2 = this.mCamera;
                if (camera2 != null) {
                    camera2.release();
                    this.mCamera = null;
                }
            }
            if (this.mCamera != null) {
                if (cameraGLView.cbOnPreviewFrame != null) {
                    this.mCamera.setPreviewCallback(cameraGLView.cbOnPreviewFrame);
                }
                this.mCamera.startPreview();
            }
        }

        private static Camera.Size getClosestSupportedSize(List<Camera.Size> list, final int i, final int i2) {
            return (Camera.Size) Collections.min(list, new Comparator<Camera.Size>() { // from class: com.megvii.livenesslib.kas.view.CameraGLView.CameraThread.2
                private int diff(Camera.Size size) {
                    return Math.abs(i - size.width) + Math.abs(i2 - size.height);
                }

                @Override // java.util.Comparator
                public int compare(Camera.Size size, Camera.Size size2) {
                    return diff(size) - diff(size2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopPreview() {
            Log.v(CameraGLView.TAG, "stopPreview:");
            Camera camera = this.mCamera;
            if (camera != null) {
                camera.stopPreview();
                this.mCamera.setPreviewCallback(null);
                this.mCamera.release();
                this.mCamera = null;
            }
            CameraGLView cameraGLView = this.mWeakParent.get();
            if (cameraGLView == null) {
                return;
            }
            cameraGLView.mCameraHandler = null;
        }

        private final void setRotation(Camera.Parameters parameters) {
            int i;
            int i2;
            Log.v(CameraGLView.TAG, "setRotation:");
            CameraGLView cameraGLView = this.mWeakParent.get();
            if (cameraGLView == null) {
                return;
            }
            switch (((WindowManager) cameraGLView.getContext().getSystemService("window")).getDefaultDisplay().getRotation()) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 90;
                    break;
                case 2:
                    i = 180;
                    break;
                case 3:
                    i = SubsamplingScaleImageView.ORIENTATION_270;
                    break;
                default:
                    i = 0;
                    break;
            }
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(1, cameraInfo);
            this.mIsFrontFace = cameraInfo.facing == 1;
            if (this.mIsFrontFace) {
                i2 = (360 - ((cameraInfo.orientation + i) % 360)) % 360;
            } else {
                i2 = ((cameraInfo.orientation - i) + 360) % 360;
            }
            this.mCamera.setDisplayOrientation(i2);
            cameraGLView.mRotation = i2;
        }
    }

    public int getmRotation() {
        Log.d(TAG, "mRotation = " + this.mRotation);
        return this.mRotation;
    }
}
