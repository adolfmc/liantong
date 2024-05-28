package com.king.zxing.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.king.zxing.camera.open.CameraFacing;
import com.king.zxing.camera.open.OpenCamera;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class CameraConfigurationManager {
    private static final String TAG = "CameraConfiguration";
    private Point bestPreviewSize;
    private Point cameraResolution;
    private final Context context;
    private int cwNeededRotation;
    private int cwRotationFromDisplayToCamera;
    private Point previewSizeOnScreen;
    private Point screenResolution;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraConfigurationManager(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initFromCameraParameters(OpenCamera openCamera) {
        int i;
        Camera.Parameters parameters = openCamera.getCamera().getParameters();
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        switch (rotation) {
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
                if (rotation % 90 == 0) {
                    i = (rotation + 360) % 360;
                    break;
                } else {
                    throw new IllegalArgumentException("Bad rotation: " + rotation);
                }
        }
        Log.i(TAG, "Display at: " + i);
        int orientation = openCamera.getOrientation();
        Log.i(TAG, "Camera at: " + orientation);
        if (openCamera.getFacing() == CameraFacing.FRONT) {
            orientation = (360 - orientation) % 360;
            Log.i(TAG, "Front camera overriden to: " + orientation);
        }
        this.cwRotationFromDisplayToCamera = ((orientation + 360) - i) % 360;
        Log.i(TAG, "Final display orientation: " + this.cwRotationFromDisplayToCamera);
        if (openCamera.getFacing() == CameraFacing.FRONT) {
            Log.i(TAG, "Compensating rotation for front camera");
            this.cwNeededRotation = (360 - this.cwRotationFromDisplayToCamera) % 360;
        } else {
            this.cwNeededRotation = this.cwRotationFromDisplayToCamera;
        }
        Log.i(TAG, "Clockwise rotation from display to camera: " + this.cwNeededRotation);
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.screenResolution = point;
        Log.i(TAG, "Screen resolution in current orientation: " + this.screenResolution);
        this.cameraResolution = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, this.screenResolution);
        Log.i(TAG, "Camera resolution: " + this.cameraResolution);
        this.bestPreviewSize = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, this.screenResolution);
        Log.i(TAG, "Best available preview size: " + this.bestPreviewSize);
        if ((this.screenResolution.x < this.screenResolution.y) == (this.bestPreviewSize.x < this.bestPreviewSize.y)) {
            this.previewSizeOnScreen = this.bestPreviewSize;
        } else {
            this.previewSizeOnScreen = new Point(this.bestPreviewSize.y, this.bestPreviewSize.x);
        }
        Log.i(TAG, "Preview size on screen: " + this.previewSizeOnScreen);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDesiredCameraParameters(OpenCamera openCamera, boolean z) {
        Camera camera = openCamera.getCamera();
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        if (parameters.isZoomSupported()) {
            parameters.setZoom(parameters.getMaxZoom() / 10);
        }
        initializeTorch(parameters, defaultSharedPreferences, z);
        CameraConfigurationUtils.setFocus(parameters, defaultSharedPreferences.getBoolean("preferences_auto_focus", true), defaultSharedPreferences.getBoolean("preferences_disable_continuous_focus", true), z);
        if (!z) {
            if (defaultSharedPreferences.getBoolean("preferences_invert_scan", false)) {
                CameraConfigurationUtils.setInvertColor(parameters);
            }
            if (!defaultSharedPreferences.getBoolean("preferences_disable_barcode_scene_mode", true)) {
                CameraConfigurationUtils.setBarcodeSceneMode(parameters);
            }
            if (!defaultSharedPreferences.getBoolean("preferences_disable_metering", true)) {
                CameraConfigurationUtils.setVideoStabilization(parameters);
                CameraConfigurationUtils.setFocusArea(parameters);
                CameraConfigurationUtils.setMetering(parameters);
            }
            parameters.setRecordingHint(true);
        }
        parameters.setPreviewSize(this.bestPreviewSize.x, this.bestPreviewSize.y);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(this.cwRotationFromDisplayToCamera);
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (previewSize != null) {
            if (this.bestPreviewSize.x == previewSize.width && this.bestPreviewSize.y == previewSize.height) {
                return;
            }
            Log.w(TAG, "Camera said it supported preview size " + this.bestPreviewSize.x + 'x' + this.bestPreviewSize.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            this.bestPreviewSize.x = previewSize.width;
            this.bestPreviewSize.y = previewSize.height;
        }
    }

    Point getBestPreviewSize() {
        return this.bestPreviewSize;
    }

    Point getPreviewSizeOnScreen() {
        return this.previewSizeOnScreen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Point getScreenResolution() {
        return this.screenResolution;
    }

    int getCWNeededRotation() {
        return this.cwNeededRotation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getTorchState(Camera camera) {
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                if (parameters != null) {
                    String flashMode = parameters.getFlashMode();
                    if (!"on".equals(flashMode)) {
                        if (!"torch".equals(flashMode)) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTorch(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        doSetTorch(parameters, z, false);
        camera.setParameters(parameters);
    }

    private void initializeTorch(Camera.Parameters parameters, SharedPreferences sharedPreferences, boolean z) {
        doSetTorch(parameters, FrontLightMode.readPref(sharedPreferences) == FrontLightMode.ON, z);
    }

    private void doSetTorch(Camera.Parameters parameters, boolean z, boolean z2) {
        CameraConfigurationUtils.setTorch(parameters, z);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        if (z2 || defaultSharedPreferences.getBoolean("preferences_disable_exposure", true)) {
            return;
        }
        CameraConfigurationUtils.setBestExposure(parameters, z);
    }
}
