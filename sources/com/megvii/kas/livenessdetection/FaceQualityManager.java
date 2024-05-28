package com.megvii.kas.livenessdetection;

import android.graphics.RectF;
import com.megvii.kas.livenessdetection.bean.FaceInfo;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FaceQualityManager {

    /* renamed from: a */
    private float f12328a;

    /* renamed from: b */
    private float f12329b;
    public float pitchThreshold = 0.17f;
    public float yawThreshold = 0.17f;
    public float integrityThreshold = 0.99f;
    public float minBrightnessThreshold = 70.0f;
    public float maxBrightnessThreshold = 230.0f;
    public float faceWidthThreshold = 150.0f;
    public float motionBlurThreshold = 0.2f;
    public float gaussianBlurThreshold = 0.15f;
    public int needHolding = 3;
    public boolean LimitRect = true;
    public float eyeLeftOcclusion = 0.5f;
    public float mouthOcclusion = 0.5f;

    /* renamed from: c */
    private int f12330c = 0;
    public float faceMaxSizeRatioThreshold = 0.4f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum FaceQualityErrorType {
        NONE,
        FRAME_NEED_HOLDING,
        FACE_NOT_FOUND,
        FACE_POS_DEVIATED,
        FACE_NONINTEGRITY,
        FACE_TOO_DARK,
        FACE_TOO_BRIGHT,
        FACE_TOO_SMALL,
        FACE_TOO_LARGE,
        FACE_TOO_BLURRY,
        FACE_OUT_OF_RECT,
        FACE_EYE_OCCLUSIVE,
        FACE_MOUTH_OCCLUSIVE
    }

    public FaceQualityManager(float f, float f2) {
        this.f12328a = 0.5f;
        this.f12329b = 0.5f;
        this.f12328a = f;
        this.f12329b = f2;
    }

    public synchronized List<FaceQualityErrorType> feedFrame(DetectionFrame detectionFrame) {
        LinkedList linkedList;
        linkedList = new LinkedList();
        if (detectionFrame == null) {
            throw new InvalidParameterException("detectionFrame could not be null");
        }
        int imageWidth = detectionFrame.getRotation() % 180 == 0 ? detectionFrame.getImageWidth() : detectionFrame.getImageHeight();
        int imageHeight = detectionFrame.getRotation() % 180 == 0 ? detectionFrame.getImageHeight() : detectionFrame.getImageWidth();
        FaceInfo faceInfo = detectionFrame.getFaceInfo();
        if (faceInfo == null) {
            linkedList.add(FaceQualityErrorType.FACE_NOT_FOUND);
        } else {
            RectF facePos = detectionFrame.getFacePos();
            float sqrt = (float) Math.sqrt(Math.pow((facePos.centerX() - this.f12328a) * imageWidth, 2.0d) + Math.pow((facePos.centerY() - this.f12329b) * imageHeight, 2.0d));
            if (this.LimitRect && sqrt / faceInfo.faceSize.width() > 0.5f) {
                linkedList.add(FaceQualityErrorType.FACE_OUT_OF_RECT);
            }
            if (Math.abs(faceInfo.yaw) > this.yawThreshold || Math.abs(faceInfo.pitch) > this.pitchThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_POS_DEVIATED);
            }
            if (faceInfo.integrity < this.integrityThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_NONINTEGRITY);
            }
            if (faceInfo.brightness < this.minBrightnessThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_TOO_DARK);
            }
            if (faceInfo.brightness > this.maxBrightnessThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_TOO_BRIGHT);
            }
            if (faceInfo.faceSize.width() < this.faceWidthThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_TOO_SMALL);
            }
            if (facePos.width() > this.faceMaxSizeRatioThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_TOO_LARGE);
            }
            if (faceInfo.motionBlur > this.motionBlurThreshold || faceInfo.gaussianBlur > this.gaussianBlurThreshold) {
                linkedList.add(FaceQualityErrorType.FACE_TOO_BLURRY);
            }
            if (faceInfo.eyeLeftOcclusion > this.eyeLeftOcclusion || faceInfo.eyeRightOcclusion > this.eyeLeftOcclusion) {
                linkedList.add(FaceQualityErrorType.FACE_EYE_OCCLUSIVE);
            }
            if (faceInfo.mouthOcclusion > this.mouthOcclusion) {
                linkedList.add(FaceQualityErrorType.FACE_MOUTH_OCCLUSIVE);
            }
        }
        if (linkedList.size() == 0) {
            int i = this.f12330c;
            this.f12330c = i + 1;
            if (i < this.needHolding) {
                linkedList.add(FaceQualityErrorType.FRAME_NEED_HOLDING);
            }
        } else {
            this.f12330c = 0;
        }
        return new LinkedList(linkedList);
    }
}
