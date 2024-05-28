package com.megvii.livenesslib.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.widget.RelativeLayout;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ICamera {
    public int cameraHeight;
    private int cameraId = 1;
    public int cameraWidth;
    public Camera mCamera;

    public Camera openCamera(Activity activity) {
        try {
            this.mCamera = Camera.open(this.cameraId);
            Camera.getCameraInfo(this.cameraId, new Camera.CameraInfo());
            Camera.Parameters parameters = this.mCamera.getParameters();
            Camera.Size calBestPreviewSize = calBestPreviewSize(this.mCamera.getParameters(), 640, 480);
            this.cameraWidth = calBestPreviewSize.width;
            this.cameraHeight = calBestPreviewSize.height;
            parameters.setPreviewSize(this.cameraWidth, this.cameraHeight);
            this.mCamera.setDisplayOrientation(getCameraAngle(activity));
            this.mCamera.setParameters(parameters);
            return this.mCamera;
        } catch (Exception unused) {
            return null;
        }
    }

    public RelativeLayout.LayoutParams getLayoutParam() {
        Camera.Size previewSize = this.mCamera.getParameters().getPreviewSize();
        float min = Math.min((Screen.mWidth * 1.0f) / previewSize.height, (Screen.mHeight * 1.0f) / previewSize.width);
        return new RelativeLayout.LayoutParams((int) (previewSize.height * min), (int) (min * previewSize.width));
    }

    public void actionDetect(Camera.PreviewCallback previewCallback) {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.setPreviewCallback(previewCallback);
        }
    }

    public void startPreview(SurfaceTexture surfaceTexture) {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewTexture(surfaceTexture);
                this.mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeCamera() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
            this.mCamera.setPreviewCallback(null);
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    private Camera.Size calBestPreviewSize(Camera.Parameters parameters, final int i, final int i2) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            if (size.width > size.height) {
                arrayList.add(size);
            }
        }
        Collections.sort(arrayList, new Comparator<Camera.Size>() { // from class: com.megvii.livenesslib.util.ICamera.1
            @Override // java.util.Comparator
            public int compare(Camera.Size size2, Camera.Size size3) {
                return Math.abs((size2.width * size2.height) - (i * i2)) - Math.abs((size3.width * size3.height) - (i * i2));
            }
        });
        return (Camera.Size) arrayList.get(0);
    }

    public Camera getCameraSafely(int i) {
        try {
            return Camera.open(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public RelativeLayout.LayoutParams getParams(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size calBestPreviewSize = calBestPreviewSize(parameters, Screen.mWidth, Screen.mHeight);
        this.cameraWidth = calBestPreviewSize.width;
        this.cameraHeight = calBestPreviewSize.height;
        parameters.setPreviewSize(this.cameraWidth, this.cameraHeight);
        camera.setParameters(parameters);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(calBestPreviewSize.width, (int) (calBestPreviewSize.width / (calBestPreviewSize.width / calBestPreviewSize.height)));
        layoutParams.addRule(14);
        return layoutParams;
    }

    public Bitmap getBitMap(byte[] bArr, Camera camera, boolean z) {
        int i = camera.getParameters().getPreviewSize().width;
        int i2 = camera.getParameters().getPreviewSize().height;
        YuvImage yuvImage = new YuvImage(bArr, camera.getParameters().getPreviewFormat(), i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Matrix matrix = new Matrix();
        matrix.reset();
        if (z) {
            matrix.setRotate(-90.0f);
        } else {
            matrix.setRotate(90.0f);
        }
        Bitmap copy = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true).copy(Bitmap.Config.ARGB_8888, true);
        float height = (copy.getHeight() > copy.getWidth() ? copy.getHeight() : copy.getWidth()) / 800.0f;
        return height > 1.0f ? Bitmap.createScaledBitmap(copy, (int) (copy.getWidth() / height), (int) (copy.getHeight() / height), false) : copy;
    }

    public int getCameraAngle(Activity activity) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.cameraId, cameraInfo);
        int i = 0;
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i = 90;
                break;
            case 2:
                i = 180;
                break;
            case 3:
                i = SubsamplingScaleImageView.ORIENTATION_270;
                break;
        }
        if (cameraInfo.facing == 1) {
            return (360 - ((cameraInfo.orientation + i) % 360)) % 360;
        }
        return ((cameraInfo.orientation - i) + 360) % 360;
    }
}
