package com.king.zxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.support.annotation.FloatRange;
import android.support.p083v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.king.zxing.camera.CameraManager;
import com.king.zxing.camera.FrontLightMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CaptureHelper implements SurfaceHolder.Callback, CaptureLifecycle, CaptureManager, CaptureTouchEvent {
    private static final int DEVIATION = 6;
    public static final String TAG = "CaptureHelper";
    private Activity activity;
    private AmbientLightManager ambientLightManager;
    private BeepManager beepManager;
    private float brightEnoughLux;
    private CameraManager cameraManager;
    private CaptureHandler captureHandler;
    private String characterSet;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, Object> decodeHints;
    private int framingRectHorizontalOffset;
    private float framingRectRatio;
    private int framingRectVerticalOffset;
    private boolean hasCameraFlash;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private boolean isAutoRestartPreviewAndDecode;
    private boolean isContinuousScan;
    private boolean isFullScreenScan;
    private boolean isPlayBeep;
    private boolean isReturnBitmap;
    private boolean isSupportAutoZoom;
    private boolean isSupportLuminanceInvert;
    private boolean isSupportVerticalCode;
    private boolean isSupportZoom;
    private boolean isVibrate;
    private View ivTorch;
    private float oldDistance;
    private OnCaptureCallback onCaptureCallback;
    private OnCaptureListener onCaptureListener;
    private SurfaceHolder surfaceHolder;
    private float tooDarkLux;
    private ViewfinderView viewfinderView;

    private int clamp(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Deprecated
    public CaptureHelper(Fragment fragment, SurfaceView surfaceView, ViewfinderView viewfinderView) {
        this(fragment, surfaceView, viewfinderView, (View) null);
    }

    public CaptureHelper(Fragment fragment, SurfaceView surfaceView, ViewfinderView viewfinderView, View view) {
        this(fragment.getActivity(), surfaceView, viewfinderView, view);
    }

    @Deprecated
    public CaptureHelper(Activity activity, SurfaceView surfaceView, ViewfinderView viewfinderView) {
        this(activity, surfaceView, viewfinderView, (View) null);
    }

    public CaptureHelper(Activity activity, SurfaceView surfaceView, ViewfinderView viewfinderView, View view) {
        this.isSupportZoom = true;
        this.isSupportAutoZoom = true;
        this.isSupportLuminanceInvert = false;
        this.isContinuousScan = false;
        this.isAutoRestartPreviewAndDecode = true;
        this.framingRectRatio = 0.9f;
        this.tooDarkLux = 45.0f;
        this.brightEnoughLux = 100.0f;
        this.activity = activity;
        this.viewfinderView = viewfinderView;
        this.ivTorch = view;
        this.surfaceHolder = surfaceView.getHolder();
        this.hasSurface = false;
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onCreate() {
        this.inactivityTimer = new InactivityTimer(this.activity);
        this.beepManager = new BeepManager(this.activity);
        this.ambientLightManager = new AmbientLightManager(this.activity);
        this.hasCameraFlash = this.activity.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        initCameraManager();
        this.onCaptureListener = new OnCaptureListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$M1LKX0hZL5VGLrV8hfodXcHppF8
            @Override // com.king.zxing.OnCaptureListener
            public final void onHandleDecode(Result result, Bitmap bitmap, float f) {
                CaptureHelper.lambda$onCreate$0(CaptureHelper.this, result, bitmap, f);
            }
        };
        this.beepManager.setPlayBeep(this.isPlayBeep);
        this.beepManager.setVibrate(this.isVibrate);
        this.ambientLightManager.setTooDarkLux(this.tooDarkLux);
        this.ambientLightManager.setBrightEnoughLux(this.brightEnoughLux);
    }

    public static /* synthetic */ void lambda$onCreate$0(CaptureHelper captureHelper, Result result, Bitmap bitmap, float f) {
        captureHelper.inactivityTimer.onActivity();
        captureHelper.beepManager.playBeepSoundAndVibrate();
        captureHelper.onResult(result, bitmap, f);
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onResume() {
        this.beepManager.updatePrefs();
        this.inactivityTimer.onResume();
        if (this.hasSurface) {
            initCamera(this.surfaceHolder);
        } else {
            this.surfaceHolder.addCallback(this);
        }
        this.ambientLightManager.start(this.cameraManager);
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onPause() {
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.quitSynchronously();
            this.captureHandler = null;
        }
        this.inactivityTimer.onPause();
        this.ambientLightManager.stop();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (this.hasSurface) {
            return;
        }
        this.surfaceHolder.removeCallback(this);
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onDestroy() {
        this.inactivityTimer.shutdown();
    }

    @Override // com.king.zxing.CaptureTouchEvent
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Camera camera;
        if (!this.isSupportZoom || !this.cameraManager.isOpen() || (camera = this.cameraManager.getOpenCamera().getCamera()) == null || motionEvent.getPointerCount() <= 1) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 2) {
            float calcFingerSpacing = calcFingerSpacing(motionEvent);
            float f = this.oldDistance;
            if (calcFingerSpacing > f + 6.0f) {
                handleZoom(true, camera);
            } else if (calcFingerSpacing < f - 6.0f) {
                handleZoom(false, camera);
            }
            this.oldDistance = calcFingerSpacing;
        } else if (action == 5) {
            this.oldDistance = calcFingerSpacing(motionEvent);
        }
        return true;
    }

    private void initCameraManager() {
        this.cameraManager = new CameraManager(this.activity);
        this.cameraManager.setFullScreenScan(this.isFullScreenScan);
        this.cameraManager.setFramingRectRatio(this.framingRectRatio);
        this.cameraManager.setFramingRectVerticalOffset(this.framingRectVerticalOffset);
        this.cameraManager.setFramingRectHorizontalOffset(this.framingRectHorizontalOffset);
        View view = this.ivTorch;
        if (view == null || !this.hasCameraFlash) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$i8cvNEWL6OlZjVbzRDtr3lazGZc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CaptureHelper.lambda$initCameraManager$1(CaptureHelper.this, view2);
            }
        });
        this.cameraManager.setOnSensorListener(new CameraManager.OnSensorListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$qZVOqVqKk6calUaIsNPo5S2Svww
            @Override // com.king.zxing.camera.CameraManager.OnSensorListener
            public final void onSensorChanged(boolean z, boolean z2, float f) {
                CaptureHelper.lambda$initCameraManager$2(CaptureHelper.this, z, z2, f);
            }
        });
        this.cameraManager.setOnTorchListener(new CameraManager.OnTorchListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$rpoAt43hjil8ox87CgThrHqtLBQ
            @Override // com.king.zxing.camera.CameraManager.OnTorchListener
            public final void onTorchChanged(boolean z) {
                CaptureHelper.this.ivTorch.setSelected(z);
            }
        });
    }

    public static /* synthetic */ void lambda$initCameraManager$1(CaptureHelper captureHelper, View view) {
        CameraManager cameraManager = captureHelper.cameraManager;
        if (cameraManager != null) {
            cameraManager.setTorch(!captureHelper.ivTorch.isSelected());
        }
    }

    public static /* synthetic */ void lambda$initCameraManager$2(CaptureHelper captureHelper, boolean z, boolean z2, float f) {
        if (z2) {
            if (captureHelper.ivTorch.getVisibility() != 0) {
                captureHelper.ivTorch.setVisibility(4);
            }
        } else if (z || captureHelper.ivTorch.getVisibility() != 0) {
        } else {
            captureHelper.ivTorch.setVisibility(4);
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            this.cameraManager.openDriver(surfaceHolder);
            if (this.captureHandler == null) {
                this.captureHandler = new CaptureHandler(this.activity, this.viewfinderView, this.onCaptureListener, this.decodeFormats, this.decodeHints, this.characterSet, this.cameraManager);
                this.captureHandler.setSupportVerticalCode(this.isSupportVerticalCode);
                this.captureHandler.setReturnBitmap(this.isReturnBitmap);
                this.captureHandler.setSupportAutoZoom(this.isSupportAutoZoom);
                this.captureHandler.setSupportLuminanceInvert(this.isSupportLuminanceInvert);
            }
        } catch (IOException e) {
            Log.w(TAG, e);
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected error initializing camera", e2);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }

    private void handleZoom(boolean z, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        if (parameters.isZoomSupported()) {
            int maxZoom = parameters.getMaxZoom();
            int zoom = parameters.getZoom();
            if (z && zoom < maxZoom) {
                zoom++;
            } else if (zoom > 0) {
                zoom--;
            }
            parameters.setZoom(zoom);
            camera.setParameters(parameters);
            return;
        }
        Log.i(TAG, "zoom not supported");
    }

    @Deprecated
    private void focusOnTouch(MotionEvent motionEvent, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size previewSize = parameters.getPreviewSize();
        Rect calcTapArea = calcTapArea(motionEvent.getRawX(), motionEvent.getRawY(), 1.0f, previewSize);
        Rect calcTapArea2 = calcTapArea(motionEvent.getRawX(), motionEvent.getRawY(), 1.5f, previewSize);
        Camera.Parameters parameters2 = camera.getParameters();
        if (parameters2.getMaxNumFocusAreas() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(calcTapArea, 600));
            parameters2.setFocusAreas(arrayList);
        }
        if (parameters2.getMaxNumMeteringAreas() > 0) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new Camera.Area(calcTapArea2, 600));
            parameters2.setMeteringAreas(arrayList2);
        }
        final String focusMode = parameters.getFocusMode();
        parameters.setFocusMode("macro");
        camera.setParameters(parameters);
        camera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$ylDXELNLTSPjWjiWiDjfyhgXJhc
            @Override // android.hardware.Camera.AutoFocusCallback
            public final void onAutoFocus(boolean z, Camera camera2) {
                CaptureHelper.lambda$focusOnTouch$4(focusMode, z, camera2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$focusOnTouch$4(String str, boolean z, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFocusMode(str);
        camera.setParameters(parameters);
    }

    private float calcFingerSpacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private Rect calcTapArea(float f, float f2, float f3, Camera.Size size) {
        int intValue = Float.valueOf(f3 * 200.0f).intValue();
        int i = intValue / 2;
        int clamp = clamp(((int) (((f / size.width) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        int clamp2 = clamp(((int) (((f2 / size.height) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        RectF rectF = new RectF(clamp, clamp2, clamp + intValue, clamp2 + intValue);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void restartPreviewAndDecode() {
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.restartPreviewAndDecode();
        }
    }

    public void onResult(Result result, Bitmap bitmap, float f) {
        onResult(result);
    }

    public void onResult(Result result) {
        CaptureHandler captureHandler;
        final String text = result.getText();
        if (this.isContinuousScan) {
            OnCaptureCallback onCaptureCallback = this.onCaptureCallback;
            if (onCaptureCallback != null) {
                onCaptureCallback.onResultCallback(text);
            }
            if (this.isAutoRestartPreviewAndDecode) {
                restartPreviewAndDecode();
            }
        } else if (this.isPlayBeep && (captureHandler = this.captureHandler) != null) {
            captureHandler.postDelayed(new Runnable() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$qeCs8VHWSPAGjlauoPkYu9qs5NM
                @Override // java.lang.Runnable
                public final void run() {
                    CaptureHelper.lambda$onResult$5(CaptureHelper.this, text);
                }
            }, 100L);
        } else {
            OnCaptureCallback onCaptureCallback2 = this.onCaptureCallback;
            if (onCaptureCallback2 == null || !onCaptureCallback2.onResultCallback(text)) {
                Intent intent = new Intent();
                intent.putExtra("SCAN_RESULT", text);
                this.activity.setResult(-1, intent);
                this.activity.finish();
            }
        }
    }

    public static /* synthetic */ void lambda$onResult$5(CaptureHelper captureHelper, String str) {
        OnCaptureCallback onCaptureCallback = captureHelper.onCaptureCallback;
        if (onCaptureCallback == null || !onCaptureCallback.onResultCallback(str)) {
            Intent intent = new Intent();
            intent.putExtra("SCAN_RESULT", str);
            captureHelper.activity.setResult(-1, intent);
            captureHelper.activity.finish();
        }
    }

    public CaptureHelper continuousScan(boolean z) {
        this.isContinuousScan = z;
        return this;
    }

    public CaptureHelper autoRestartPreviewAndDecode(boolean z) {
        this.isAutoRestartPreviewAndDecode = z;
        return this;
    }

    public CaptureHelper playBeep(boolean z) {
        this.isPlayBeep = z;
        BeepManager beepManager = this.beepManager;
        if (beepManager != null) {
            beepManager.setPlayBeep(z);
        }
        return this;
    }

    public CaptureHelper vibrate(boolean z) {
        this.isVibrate = z;
        BeepManager beepManager = this.beepManager;
        if (beepManager != null) {
            beepManager.setVibrate(z);
        }
        return this;
    }

    public CaptureHelper supportZoom(boolean z) {
        this.isSupportZoom = z;
        return this;
    }

    public CaptureHelper decodeFormats(Collection<BarcodeFormat> collection) {
        this.decodeFormats = collection;
        return this;
    }

    public CaptureHelper decodeHints(Map<DecodeHintType, Object> map) {
        this.decodeHints = map;
        return this;
    }

    public CaptureHelper decodeHint(DecodeHintType decodeHintType, Object obj) {
        if (this.decodeHints == null) {
            this.decodeHints = new EnumMap(DecodeHintType.class);
        }
        this.decodeHints.put(decodeHintType, obj);
        return this;
    }

    public CaptureHelper characterSet(String str) {
        this.characterSet = str;
        return this;
    }

    public CaptureHelper supportVerticalCode(boolean z) {
        this.isSupportVerticalCode = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setSupportVerticalCode(this.isSupportVerticalCode);
        }
        return this;
    }

    public CaptureHelper frontLightMode(FrontLightMode frontLightMode) {
        FrontLightMode.put(this.activity, frontLightMode);
        if (this.ivTorch != null && frontLightMode != FrontLightMode.AUTO) {
            this.ivTorch.setVisibility(4);
        }
        return this;
    }

    public CaptureHelper tooDarkLux(float f) {
        this.tooDarkLux = f;
        AmbientLightManager ambientLightManager = this.ambientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setTooDarkLux(f);
        }
        return this;
    }

    public CaptureHelper brightEnoughLux(float f) {
        this.brightEnoughLux = f;
        AmbientLightManager ambientLightManager = this.ambientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setTooDarkLux(this.tooDarkLux);
        }
        return this;
    }

    public CaptureHelper returnBitmap(boolean z) {
        this.isReturnBitmap = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setReturnBitmap(this.isReturnBitmap);
        }
        return this;
    }

    public CaptureHelper supportAutoZoom(boolean z) {
        this.isSupportAutoZoom = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setSupportAutoZoom(this.isSupportAutoZoom);
        }
        return this;
    }

    public CaptureHelper supportLuminanceInvert(boolean z) {
        this.isSupportLuminanceInvert = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setSupportLuminanceInvert(this.isSupportLuminanceInvert);
        }
        return this;
    }

    public CaptureHelper fullScreenScan(boolean z) {
        this.isFullScreenScan = z;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFullScreenScan(this.isFullScreenScan);
        }
        return this;
    }

    public CaptureHelper framingRectRatio(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        this.framingRectRatio = f;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFramingRectRatio(f);
        }
        return this;
    }

    public CaptureHelper framingRectVerticalOffset(int i) {
        this.framingRectVerticalOffset = i;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFramingRectVerticalOffset(i);
        }
        return this;
    }

    public CaptureHelper framingRectHorizontalOffset(int i) {
        this.framingRectHorizontalOffset = i;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFramingRectHorizontalOffset(i);
        }
        return this;
    }

    public CaptureHelper setOnCaptureCallback(OnCaptureCallback onCaptureCallback) {
        this.onCaptureCallback = onCaptureCallback;
        return this;
    }

    @Override // com.king.zxing.CaptureManager
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    @Override // com.king.zxing.CaptureManager
    public BeepManager getBeepManager() {
        return this.beepManager;
    }

    @Override // com.king.zxing.CaptureManager
    public AmbientLightManager getAmbientLightManager() {
        return this.ambientLightManager;
    }

    @Override // com.king.zxing.CaptureManager
    public InactivityTimer getInactivityTimer() {
        return this.inactivityTimer;
    }
}
