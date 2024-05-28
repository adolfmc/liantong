package com.baidu.cloud.framecapture.camera;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.view.WindowManager;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class CameraSession {
    Handler cameraThreadHandler;
    public boolean listening;
    int oesTextureId;
    SurfaceTexture surfaceTexture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Events {
        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpening();

        void onFrameCaptured(CameraSession cameraSession, VideoFrameBuffer videoFrameBuffer);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    enum FailureType {
        ERROR,
        DISCONNECTED
    }

    abstract int getFrameOrientation();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stop();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void tryDeliverTextureFrameInternal(long j, float[] fArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDeviceOrientation(Context context) {
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return SubsamplingScaleImageView.ORIENTATION_270;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float[] applyTransformMatrix(float[] fArr, boolean z, int i) {
        Matrix matrix = new Matrix();
        matrix.preTranslate(0.5f, 0.5f);
        if (z) {
            matrix.preScale(-1.0f, 1.0f);
        }
        matrix.preRotate(i);
        matrix.preTranslate(-0.5f, -0.5f);
        Matrix matrix2 = new Matrix(convertMatrixToAndroidGraphicsMatrix(fArr));
        matrix2.preConcat(matrix);
        return convertMatrixFromAndroidGraphicsMatrix(matrix2);
    }

    public static Matrix convertMatrixToAndroidGraphicsMatrix(float[] fArr) {
        float[] fArr2 = {fArr[0], fArr[4], fArr[12], fArr[1], fArr[5], fArr[13], fArr[3], fArr[7], fArr[15]};
        Matrix matrix = new Matrix();
        matrix.setValues(fArr2);
        return matrix;
    }

    public static float[] convertMatrixFromAndroidGraphicsMatrix(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return new float[]{fArr[0], fArr[3], 0.0f, fArr[6], fArr[1], fArr[4], 0.0f, fArr[7], 0.0f, 0.0f, 1.0f, 0.0f, fArr[2], fArr[5], 0.0f, fArr[8]};
    }
}
