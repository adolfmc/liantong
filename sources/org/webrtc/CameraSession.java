package org.webrtc;

import android.content.Context;
import android.graphics.Matrix;
import android.view.WindowManager;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import org.webrtc.VideoFrame;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface CameraSession {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface Events {
        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpening();

        void onFrameCaptured(CameraSession cameraSession, VideoFrame videoFrame);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum FailureType {
        ERROR,
        DISCONNECTED
    }

    void stop();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.webrtc.CameraSession$-CC  reason: invalid class name */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final /* synthetic */ class CC {
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

        public static VideoFrame.TextureBuffer createTextureBufferWithModifiedTransformMatrix(TextureBufferImpl textureBufferImpl, boolean z, int i) {
            Matrix matrix = new Matrix();
            matrix.preTranslate(0.5f, 0.5f);
            if (z) {
                matrix.preScale(-1.0f, 1.0f);
            }
            matrix.preRotate(i);
            matrix.preTranslate(-0.5f, -0.5f);
            return textureBufferImpl.applyTransformMatrix(matrix, textureBufferImpl.getWidth(), textureBufferImpl.getHeight());
        }
    }
}
