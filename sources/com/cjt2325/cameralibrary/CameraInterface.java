package com.cjt2325.cameralibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.widget.ImageView;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.util.AngleUtil;
import com.cjt2325.cameralibrary.util.CameraParamUtil;
import com.cjt2325.cameralibrary.util.CheckPermission;
import com.cjt2325.cameralibrary.util.DeviceUtil;
import com.cjt2325.cameralibrary.util.LogUtil;
import com.cjt2325.cameralibrary.util.ScreenUtils;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CameraInterface implements Camera.PreviewCallback {
    private static final String TAG = "CJT";
    public static final int TYPE_CAPTURE = 145;
    public static final int TYPE_RECORDER = 144;
    private static volatile CameraInterface mCameraInterface;
    private int SELECTED_CAMERA;
    private ErrorListener errorLisenter;
    private byte[] firstFrame_data;
    private Camera mCamera;
    private ImageView mFlashLamp;
    private Camera.Parameters mParams;
    private ImageView mSwitchView;
    private MediaRecorder mediaRecorder;
    private int nowAngle;
    private int preview_height;
    private int preview_width;
    private String saveVideoPath;
    private String videoFileAbsPath;
    private String videoFileName;
    private boolean isPreviewing = false;
    private int CAMERA_POST_POSITION = -1;
    private int CAMERA_FRONT_POSITION = -1;
    private SurfaceHolder mHolder = null;
    private float screenProp = -1.0f;
    private boolean isRecorder = false;
    private Bitmap videoFirstFrame = null;
    private int angle = 0;
    private int cameraAngle = 90;
    private int rotation = 0;
    private int nowScaleRate = 0;
    private int recordScleRate = 0;
    private int mediaQuality = 1600000;

    /* renamed from: sm */
    private SensorManager f9713sm = null;
    private boolean isHuoTi = false;
    private SensorEventListener sensorEventListener = new SensorEventListener() { // from class: com.cjt2325.cameralibrary.CameraInterface.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (1 != sensorEvent.sensor.getType()) {
                return;
            }
            float[] fArr = sensorEvent.values;
            CameraInterface.this.angle = AngleUtil.getSensorAngle(fArr[0], fArr[1]);
            CameraInterface.this.rotationAnimation();
        }
    };
    int handlerTime = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CameraOpenOverCallback {
        void cameraHasOpened();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ErrorCallback {
        void onError();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface FocusCallback {
        void focusSuccess();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface StopRecordCallback {
        void recordResult(String str, Bitmap bitmap);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface TakePictureCallback {
        void captureResult(Bitmap bitmap, boolean z);
    }

    private static int clamp(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public static void destroyCameraInterface() {
        if (mCameraInterface != null) {
            mCameraInterface = null;
        }
    }

    public void setHuoTi(boolean z) {
        this.isHuoTi = z;
    }

    public static synchronized CameraInterface getInstance() {
        CameraInterface cameraInterface;
        synchronized (CameraInterface.class) {
            if (mCameraInterface == null) {
                synchronized (CameraInterface.class) {
                    if (mCameraInterface == null) {
                        mCameraInterface = new CameraInterface();
                    }
                }
            }
            cameraInterface = mCameraInterface;
        }
        return cameraInterface;
    }

    public void setSwitchView(ImageView imageView, ImageView imageView2) {
        this.mSwitchView = imageView;
        this.mFlashLamp = imageView2;
        if (imageView != null) {
            this.cameraAngle = CameraParamUtil.getInstance().getCameraDisplayOrientation(imageView.getContext(), this.SELECTED_CAMERA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rotationAnimation() {
        int i;
        int i2;
        if (this.mSwitchView == null || (i = this.rotation) == (i2 = this.angle)) {
            return;
        }
        int i3 = SubsamplingScaleImageView.ORIENTATION_270;
        int i4 = 180;
        int i5 = 90;
        if (i == 0) {
            i4 = i2 != 90 ? i2 != 270 ? 0 : 90 : -90;
            i5 = 0;
        } else if (i == 90) {
            i4 = i2 != 0 ? i2 != 180 ? 0 : -180 : 0;
            i5 = -90;
        } else if (i == 180) {
            if (i2 != 90) {
                i3 = i2 != 270 ? 0 : 90;
            }
            i5 = 180;
            i4 = i3;
        } else if (i != 270) {
            i4 = 0;
            i5 = 0;
        } else if (i2 == 0) {
            i4 = 0;
        } else if (i2 != 180) {
            i4 = 0;
        }
        float f = i5;
        float f2 = i4;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mSwitchView, "rotation", f, f2);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mFlashLamp, "rotation", f, f2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(500L);
        animatorSet.start();
        this.rotation = this.angle;
    }

    public void setSaveVideoPath(String str) {
        this.saveVideoPath = str;
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public void setZoom(float f, int i) {
        int i2;
        Camera camera = this.mCamera;
        if (camera == null) {
            return;
        }
        if (this.mParams == null) {
            this.mParams = camera.getParameters();
        }
        if (this.mParams.isZoomSupported() && this.mParams.isSmoothZoomSupported()) {
            switch (i) {
                case 144:
                    if (this.isRecorder && f >= 0.0f && (i2 = (int) (f / 40.0f)) <= this.mParams.getMaxZoom() && i2 >= this.nowScaleRate && this.recordScleRate != i2) {
                        this.mParams.setZoom(i2);
                        this.mCamera.setParameters(this.mParams);
                        this.recordScleRate = i2;
                        return;
                    }
                    return;
                case TYPE_CAPTURE /* 145 */:
                    if (this.isRecorder) {
                        return;
                    }
                    int i3 = (int) (f / 50.0f);
                    if (i3 < this.mParams.getMaxZoom()) {
                        this.nowScaleRate += i3;
                        int i4 = this.nowScaleRate;
                        if (i4 < 0) {
                            this.nowScaleRate = 0;
                        } else if (i4 > this.mParams.getMaxZoom()) {
                            this.nowScaleRate = this.mParams.getMaxZoom();
                        }
                        this.mParams.setZoom(this.nowScaleRate);
                        this.mCamera.setParameters(this.mParams);
                    }
                    LogUtil.m16331i("setZoom = " + this.nowScaleRate);
                    return;
                default:
                    return;
            }
        }
    }

    public void setMediaQuality(int i) {
        this.mediaQuality = i;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.firstFrame_data = bArr;
    }

    public void setFlashMode(String str) {
        try {
            if (this.mCamera == null) {
                return;
            }
            Camera.Parameters parameters = this.mCamera.getParameters();
            parameters.setFlashMode(str);
            this.mCamera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CameraInterface() {
        this.SELECTED_CAMERA = -1;
        findAvailableCameras();
        this.SELECTED_CAMERA = this.CAMERA_FRONT_POSITION;
        this.saveVideoPath = "";
    }

    public void doOpenCamera(CameraOpenOverCallback cameraOpenOverCallback) {
        ErrorListener errorListener;
        if (Build.VERSION.SDK_INT < 23 && !CheckPermission.isCameraUseable(this.SELECTED_CAMERA) && (errorListener = this.errorLisenter) != null) {
            errorListener.onError();
            return;
        }
        if (this.mCamera == null) {
            openCamera(this.SELECTED_CAMERA);
        }
        cameraOpenOverCallback.cameraHasOpened();
    }

    private void setFlashModel() {
        this.mParams = this.mCamera.getParameters();
        this.mParams.setFlashMode("torch");
        this.mCamera.setParameters(this.mParams);
    }

    private synchronized void openCamera(int i) {
        try {
            this.mCamera = Camera.open(i);
        } catch (Exception e) {
            e.printStackTrace();
            if (this.errorLisenter != null) {
                this.errorLisenter.onError();
            }
        }
        if (Build.VERSION.SDK_INT > 17 && this.mCamera != null) {
            try {
                this.mCamera.enableShutterSound(false);
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.e(TAG, "enable shutter sound faild");
            }
        }
    }

    public synchronized void switchCamera(SurfaceHolder surfaceHolder, float f) {
        if (this.SELECTED_CAMERA == this.CAMERA_POST_POSITION) {
            this.SELECTED_CAMERA = this.CAMERA_FRONT_POSITION;
        } else {
            this.SELECTED_CAMERA = this.CAMERA_POST_POSITION;
        }
        doDestroyCamera();
        LogUtil.m16331i("open start");
        openCamera(this.SELECTED_CAMERA);
        if (Build.VERSION.SDK_INT > 17 && this.mCamera != null) {
            try {
                this.mCamera.enableShutterSound(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LogUtil.m16331i("open end");
        doStartPreview(surfaceHolder, f);
    }

    public void doStartPreview(SurfaceHolder surfaceHolder, float f) {
        if (this.isPreviewing) {
            LogUtil.m16331i("doStartPreview isPreviewing");
        }
        if (this.screenProp < 0.0f) {
            this.screenProp = f;
        }
        if (surfaceHolder == null) {
            return;
        }
        this.mHolder = surfaceHolder;
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                this.mParams = camera.getParameters();
                Camera.Size previewSize = CameraParamUtil.getInstance().getPreviewSize(this.mParams.getSupportedPreviewSizes(), 1000, f);
                Camera.Size pictureSize = CameraParamUtil.getInstance().getPictureSize(this.mParams.getSupportedPictureSizes(), 1200, f);
                this.mParams.setPreviewSize(previewSize.width, previewSize.height);
                this.preview_width = previewSize.width;
                this.preview_height = previewSize.height;
                this.mParams.setPictureSize(pictureSize.width, pictureSize.height);
                if (CameraParamUtil.getInstance().isSupportedFocusMode(this.mParams.getSupportedFocusModes(), "auto")) {
                    this.mParams.setFocusMode("auto");
                }
                if (CameraParamUtil.getInstance().isSupportedPictureFormats(this.mParams.getSupportedPictureFormats(), 256)) {
                    this.mParams.setPictureFormat(256);
                    this.mParams.setJpegQuality(100);
                }
                this.mCamera.setParameters(this.mParams);
                this.mParams = this.mCamera.getParameters();
                this.mCamera.setPreviewDisplay(surfaceHolder);
                this.mCamera.setDisplayOrientation(this.cameraAngle);
                this.mCamera.setPreviewCallback(this);
                this.mCamera.startPreview();
                this.isPreviewing = true;
                Log.i(TAG, "=== Start Preview ===");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doStopPreview() {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.mCamera.stopPreview();
                this.mCamera.setPreviewDisplay(null);
                this.isPreviewing = false;
                Log.i(TAG, "=== Stop Preview ===");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doDestroyCamera() {
        this.errorLisenter = null;
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.mSwitchView = null;
                this.mFlashLamp = null;
                this.mCamera.stopPreview();
                this.mCamera.setPreviewDisplay(null);
                this.mHolder = null;
                this.isPreviewing = false;
                this.mCamera.release();
                this.mCamera = null;
                Log.i(TAG, "=== Destroy Camera ===");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.i(TAG, "=== Camera  Null===");
    }

    public void takePicture(final TakePictureCallback takePictureCallback) {
        if (this.mCamera == null) {
            return;
        }
        int i = this.cameraAngle;
        if (i == 90) {
            this.nowAngle = Math.abs(this.angle + i) % 360;
        } else if (i == 270) {
            this.nowAngle = Math.abs(i - this.angle);
        }
        Log.i(TAG, this.angle + " = " + this.cameraAngle + " = " + this.nowAngle);
        this.mCamera.takePicture(null, null, new Camera.PictureCallback() { // from class: com.cjt2325.cameralibrary.CameraInterface.2
            @Override // android.hardware.Camera.PictureCallback
            public void onPictureTaken(byte[] bArr, Camera camera) {
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                Matrix matrix = new Matrix();
                if (CameraInterface.this.SELECTED_CAMERA == CameraInterface.this.CAMERA_POST_POSITION) {
                    matrix.setRotate(CameraInterface.this.nowAngle);
                } else if (CameraInterface.this.SELECTED_CAMERA == CameraInterface.this.CAMERA_FRONT_POSITION) {
                    matrix.setRotate(360 - CameraInterface.this.nowAngle);
                    matrix.postScale(-1.0f, 1.0f);
                }
                Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
                if (takePictureCallback != null) {
                    if (CameraInterface.this.nowAngle == 90 || CameraInterface.this.nowAngle == 270) {
                        takePictureCallback.captureResult(createBitmap, true);
                    } else {
                        takePictureCallback.captureResult(createBitmap, false);
                    }
                }
            }
        });
    }

    public void startRecord(Surface surface, float f, ErrorCallback errorCallback) {
        Camera.Size previewSize;
        this.mCamera.setPreviewCallback(null);
        int i = (this.angle + 90) % 360;
        Camera.Parameters parameters = this.mCamera.getParameters();
        int i2 = parameters.getPreviewSize().width;
        int i3 = parameters.getPreviewSize().height;
        YuvImage yuvImage = new YuvImage(this.firstFrame_data, parameters.getPreviewFormat(), i2, i3, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        this.videoFirstFrame = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Matrix matrix = new Matrix();
        int i4 = this.SELECTED_CAMERA;
        if (i4 == this.CAMERA_POST_POSITION) {
            matrix.setRotate(i);
        } else if (i4 == this.CAMERA_FRONT_POSITION) {
            matrix.setRotate(270.0f);
        }
        Bitmap bitmap = this.videoFirstFrame;
        this.videoFirstFrame = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.videoFirstFrame.getHeight(), matrix, true);
        if (this.isRecorder) {
            return;
        }
        if (this.mCamera == null) {
            openCamera(this.SELECTED_CAMERA);
        }
        if (this.mediaRecorder == null) {
            this.mediaRecorder = new MediaRecorder();
        }
        if (this.mParams == null) {
            this.mParams = this.mCamera.getParameters();
        }
        if (this.mParams.getSupportedFocusModes().contains("continuous-video")) {
            this.mParams.setFocusMode("continuous-video");
        }
        this.mCamera.setParameters(this.mParams);
        this.mCamera.unlock();
        this.mediaRecorder.reset();
        this.mediaRecorder.setCamera(this.mCamera);
        this.mediaRecorder.setVideoSource(1);
        this.mediaRecorder.setAudioSource(1);
        this.mediaRecorder.setOutputFormat(2);
        this.mediaRecorder.setVideoEncoder(2);
        this.mediaRecorder.setAudioEncoder(3);
        if (this.mParams.getSupportedVideoSizes() == null) {
            previewSize = CameraParamUtil.getInstance().getPreviewSize(this.mParams.getSupportedPreviewSizes(), 600, f);
        } else {
            previewSize = CameraParamUtil.getInstance().getPreviewSize(this.mParams.getSupportedVideoSizes(), 600, f);
        }
        Log.i(TAG, "setVideoSize    width = " + previewSize.width + "height = " + previewSize.height);
        Log.d(TAG, "startRecord: ===================默认================");
        if (previewSize.width == previewSize.height) {
            this.mediaRecorder.setVideoSize(this.preview_width, this.preview_height);
        } else {
            this.mediaRecorder.setVideoSize(previewSize.width, previewSize.height);
        }
        if (this.SELECTED_CAMERA == this.CAMERA_FRONT_POSITION) {
            if (this.cameraAngle == 270) {
                if (i == 0) {
                    this.mediaRecorder.setOrientationHint(180);
                } else if (i == 270) {
                    this.mediaRecorder.setOrientationHint(SubsamplingScaleImageView.ORIENTATION_270);
                } else {
                    this.mediaRecorder.setOrientationHint(90);
                }
            } else if (i == 90) {
                this.mediaRecorder.setOrientationHint(SubsamplingScaleImageView.ORIENTATION_270);
            } else if (i == 270) {
                this.mediaRecorder.setOrientationHint(90);
            } else {
                this.mediaRecorder.setOrientationHint(i);
            }
        } else {
            this.mediaRecorder.setOrientationHint(i);
        }
        if (DeviceUtil.isHuaWeiRongyao()) {
            this.mediaRecorder.setVideoEncodingBitRate(400000);
        } else {
            Log.d(TAG, "startRecord: mediaQuality = " + this.mediaQuality);
            if (this.isHuoTi) {
                this.mediaRecorder.setVideoEncodingBitRate(10000000);
            } else {
                this.mediaRecorder.setVideoEncodingBitRate(this.mediaQuality);
            }
        }
        this.mediaRecorder.setPreviewDisplay(surface);
        this.videoFileName = "video_" + System.currentTimeMillis() + ".mp4";
        if (this.saveVideoPath.equals("")) {
            this.saveVideoPath = Environment.getExternalStorageDirectory().getPath();
        }
        this.videoFileAbsPath = this.saveVideoPath + this.videoFileName;
        this.mediaRecorder.setOutputFile(this.videoFileAbsPath);
        try {
            this.mediaRecorder.prepare();
            this.mediaRecorder.start();
            this.isRecorder = true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "startRecord IOException");
            ErrorListener errorListener = this.errorLisenter;
            if (errorListener != null) {
                errorListener.onError();
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            Log.i(TAG, "startRecord IllegalStateException");
            ErrorListener errorListener2 = this.errorLisenter;
            if (errorListener2 != null) {
                errorListener2.onError();
            }
        } catch (RuntimeException unused) {
            Log.i(TAG, "startRecord RuntimeException");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
        if (r2 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r4 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0045, code lost:
        if (com.cjt2325.cameralibrary.util.FileUtil.deleteFile(r3.videoFileAbsPath) == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        r5.recordResult(null, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004b, code lost:
        doStopPreview();
        r5.recordResult(r3.saveVideoPath + java.io.File.separator + r3.videoFileName, r3.videoFirstFrame);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void stopRecord(boolean r4, com.cjt2325.cameralibrary.CameraInterface.StopRecordCallback r5) {
        /*
            r3 = this;
            boolean r0 = r3.isRecorder
            if (r0 != 0) goto L5
            return
        L5:
            android.media.MediaRecorder r0 = r3.mediaRecorder
            if (r0 == 0) goto L78
            r1 = 0
            r0.setOnErrorListener(r1)
            android.media.MediaRecorder r0 = r3.mediaRecorder
            r0.setOnInfoListener(r1)
            android.media.MediaRecorder r0 = r3.mediaRecorder
            r0.setPreviewDisplay(r1)
            r0 = 0
            android.media.MediaRecorder r2 = r3.mediaRecorder     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2b
            r2.stop()     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2b
            android.media.MediaRecorder r2 = r3.mediaRecorder
            if (r2 == 0) goto L24
        L21:
            r2.release()
        L24:
            r3.mediaRecorder = r1
            r3.isRecorder = r0
            goto L3d
        L29:
            r4 = move-exception
            goto L6c
        L2b:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L29
            r3.mediaRecorder = r1     // Catch: java.lang.Throwable -> L29
            android.media.MediaRecorder r2 = new android.media.MediaRecorder     // Catch: java.lang.Throwable -> L29
            r2.<init>()     // Catch: java.lang.Throwable -> L29
            r3.mediaRecorder = r2     // Catch: java.lang.Throwable -> L29
            android.media.MediaRecorder r2 = r3.mediaRecorder
            if (r2 == 0) goto L24
            goto L21
        L3d:
            if (r4 == 0) goto L4b
            java.lang.String r4 = r3.videoFileAbsPath
            boolean r4 = com.cjt2325.cameralibrary.util.FileUtil.deleteFile(r4)
            if (r4 == 0) goto L4a
            r5.recordResult(r1, r1)
        L4a:
            return
        L4b:
            r3.doStopPreview()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = r3.saveVideoPath
            r4.append(r0)
            java.lang.String r0 = java.io.File.separator
            r4.append(r0)
            java.lang.String r0 = r3.videoFileName
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            android.graphics.Bitmap r0 = r3.videoFirstFrame
            r5.recordResult(r4, r0)
            goto L78
        L6c:
            android.media.MediaRecorder r5 = r3.mediaRecorder
            if (r5 == 0) goto L73
            r5.release()
        L73:
            r3.mediaRecorder = r1
            r3.isRecorder = r0
            throw r4
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cjt2325.cameralibrary.CameraInterface.stopRecord(boolean, com.cjt2325.cameralibrary.CameraInterface$StopRecordCallback):void");
    }

    private void findAvailableCameras() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            switch (cameraInfo.facing) {
                case 0:
                    this.CAMERA_POST_POSITION = cameraInfo.facing;
                    break;
                case 1:
                    this.CAMERA_FRONT_POSITION = cameraInfo.facing;
                    break;
            }
        }
    }

    public int getSELECTED_CAMERA() {
        return this.SELECTED_CAMERA;
    }

    public void handleFocus(final Context context, final float f, final float f2, final FocusCallback focusCallback) {
        Camera camera = this.mCamera;
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        Rect calculateTapArea = calculateTapArea(f, f2, 1.0f, context);
        this.mCamera.cancelAutoFocus();
        if (parameters.getMaxNumFocusAreas() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(calculateTapArea, 800));
            parameters.setFocusAreas(arrayList);
            final String focusMode = parameters.getFocusMode();
            try {
                parameters.setFocusMode("auto");
                this.mCamera.setParameters(parameters);
                this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.cjt2325.cameralibrary.CameraInterface.3
                    @Override // android.hardware.Camera.AutoFocusCallback
                    public void onAutoFocus(boolean z, Camera camera2) {
                        if (z || CameraInterface.this.handlerTime > 10) {
                            Camera.Parameters parameters2 = camera2.getParameters();
                            parameters2.setFocusMode(focusMode);
                            camera2.setParameters(parameters2);
                            CameraInterface.this.handlerTime = 0;
                            focusCallback.focusSuccess();
                            return;
                        }
                        CameraInterface.this.handlerTime++;
                        CameraInterface.this.handleFocus(context, f, f2, focusCallback);
                    }
                });
                return;
            } catch (Exception unused) {
                Log.e(TAG, "autoFocus failer");
                return;
            }
        }
        Log.i(TAG, "focus areas not supported");
        focusCallback.focusSuccess();
    }

    private static Rect calculateTapArea(float f, float f2, float f3, Context context) {
        int intValue = Float.valueOf(f3 * 300.0f).intValue();
        int i = intValue / 2;
        int clamp = clamp(((int) (((f / ScreenUtils.getScreenWidth(context)) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        int clamp2 = clamp(((int) (((f2 / ScreenUtils.getScreenHeight(context)) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        RectF rectF = new RectF(clamp, clamp2, clamp + intValue, clamp2 + intValue);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void setErrorLinsenter(ErrorListener errorListener) {
        this.errorLisenter = errorListener;
    }

    public void registerSensorManager(Context context) {
        if (this.f9713sm == null) {
            this.f9713sm = (SensorManager) context.getSystemService("sensor");
        }
        SensorManager sensorManager = this.f9713sm;
        sensorManager.registerListener(this.sensorEventListener, sensorManager.getDefaultSensor(1), 3);
    }

    public void unregisterSensorManager(Context context) {
        if (this.f9713sm == null) {
            this.f9713sm = (SensorManager) context.getSystemService("sensor");
        }
        this.f9713sm.unregisterListener(this.sensorEventListener);
    }

    public void isPreview(boolean z) {
        this.isPreviewing = z;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class CameraSizeComparator implements Comparator<Camera.Size> {
        private CameraSizeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Camera.Size size, Camera.Size size2) {
            if (size.width == size2.width) {
                return 0;
            }
            return size.width > size2.width ? 1 : -1;
        }
    }
}
