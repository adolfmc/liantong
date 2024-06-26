package com.megvii.kas.livenessdetection;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.megvii.kas.livenessdetection.bean.FaceInfo;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class DetectionFrame {

    /* renamed from: a */
    private FrameType f12283a = FrameType.NONE;
    protected FaceInfo mFaceInfo;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum FrameType {
        NONE,
        WAITINGNORMAL
    }

    public abstract byte[] getCroppedFaceImageData();

    public abstract byte[] getCroppedFaceImageData(int i);

    public abstract byte[] getCroppedFaceImageData(int i, Rect rect);

    public abstract byte[] getCroppedFaceImageData(Rect rect);

    public abstract byte[] getEncodedFaceImageData(int i, int i2, Rect rect);

    public abstract byte[] getImageData(Rect rect, boolean z, int i, int i2, boolean z2, boolean z3, int i3);

    public abstract int getImageHeight();

    public abstract int getImageWidth();

    public abstract int getRotation();

    public abstract byte[] getYUVData();

    public FrameType getFrameType() {
        return this.f12283a;
    }

    public void setFrameType(FrameType frameType) {
        this.f12283a = frameType;
    }

    public FaceInfo getFaceInfo() {
        return this.mFaceInfo;
    }

    @Deprecated
    public float getWearGlass() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.wearGlass;
    }

    public byte[] getEncodedFaceImageData(int i, Rect rect) {
        return getEncodedFaceImageData(i, -1, rect);
    }

    public byte[] getEncodedFaceImageData(int i, int i2) {
        return getEncodedFaceImageData(i, i2, null);
    }

    public byte[] getEncodedFaceImageData(int i) {
        return getEncodedFaceImageData(i, -1, null);
    }

    @Deprecated
    public synchronized Rect getFaceSize() {
        if (this.mFaceInfo == null) {
            return null;
        }
        return this.mFaceInfo.faceSize;
    }

    @Deprecated
    public RectF getFacePos() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return null;
        }
        return faceInfo.position;
    }

    @Deprecated
    public float getYawAngle() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.yaw;
    }

    @Deprecated
    public float getPitchAngle() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.pitch;
    }

    @Deprecated
    public float getGaussianBlur() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.gaussianBlur;
    }

    @Deprecated
    public float getMotionBlur() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.motionBlur;
    }

    @Deprecated
    public float getBrightness() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.brightness;
    }

    @Deprecated
    public float getFaceQuality() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.faceQuality;
    }

    public boolean hasFace() {
        return this.mFaceInfo != null;
    }

    @Deprecated
    public float getLeftEyeHwratio() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.leftEyeHWRatio;
    }

    @Deprecated
    public float getRightEyeHwratio() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.rightEyeHWRatio;
    }

    @Deprecated
    public float getMouthHwratio() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return -1.0f;
        }
        return faceInfo.mouthHWRatio;
    }

    public static PointF get2DPoint(float f, float f2, float f3, float f4, float f5, float f6) {
        PointF pointF = new PointF();
        pointF.x = ((f6 < 0.0f ? f6 / f3 : (-f6) / f4) * 0.5f) + 0.5f;
        pointF.y = ((f5 < 0.0f ? (-f5) / f : f5 / f2) * 0.5f) + 0.5f;
        return pointF;
    }

    public PointF get2DPoint(float f, float f2, float f3, float f4) {
        if (hasFace()) {
            return get2DPoint(f, f2, f3, f4, this.mFaceInfo.smoothPitch, this.mFaceInfo.smoothYaw);
        }
        return null;
    }

    public PointF get2DPoint() {
        return get2DPoint(-0.17f, 0.17f, -0.22f, 0.22f);
    }

    public static boolean isValid2DPoint(PointF pointF) {
        return pointF.x >= 0.0f && pointF.x <= 1.0f && pointF.y >= 0.0f && pointF.y <= 1.0f;
    }
}
