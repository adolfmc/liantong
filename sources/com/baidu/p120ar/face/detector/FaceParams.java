package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.detector.DetectorParams;
import com.baidu.p120ar.detector.DetectorType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceParams extends DetectorParams {
    private static final int DEFAULT_CACHE_SIZE = 5;
    private String mAnakinDetectModelPath;
    private String mAnakinTrackCorePath0;
    private String mAnakinTrackCorePath1Heavy;
    private String mAnakinTrackCorePath1Lite;
    private String mAnakinTrackCorePath1Medium;
    private String mAnakinTrackCorePath2;
    private String mAnakinTrackCorePath3;
    private int mCacheSize;
    private String mExpressionModelPath;
    private int mFrameHeight;
    private int mFrameWidth;
    private String mImbinModelPath;
    private String mMouthModelPath;
    private float mTrackingMouthThreshold;
    private float mTrackingSmoothAlpha;
    private float mTrackingSmoothThreshold;

    public FaceParams() {
        super(DetectorType.FACE);
        this.mTrackingSmoothAlpha = 0.03f;
        this.mTrackingSmoothThreshold = 1.0f;
        this.mTrackingMouthThreshold = -1.0f;
        this.mCacheSize = 5;
    }

    public String getImbinModelPath() {
        return this.mImbinModelPath;
    }

    public void setImbinModelPath(String str) {
        this.mImbinModelPath = str;
    }

    public String getAnakinDetectModelPath() {
        return this.mAnakinDetectModelPath;
    }

    public void setAnakinDetectModelPath(String str) {
        this.mAnakinDetectModelPath = str;
    }

    public String getAnakinTrackCorePath0() {
        return this.mAnakinTrackCorePath0;
    }

    public void setAnakinTrackCorePath0(String str) {
        this.mAnakinTrackCorePath0 = str;
    }

    public String getAnakinTrackCorePath1Heavy() {
        return this.mAnakinTrackCorePath1Heavy;
    }

    public void setAnakinTrackCorePath1Heavy(String str) {
        this.mAnakinTrackCorePath1Heavy = str;
    }

    public String getAnakinTrackCorePath1Medium() {
        return this.mAnakinTrackCorePath1Medium;
    }

    public void setAnakinTrackCorePath1Medium(String str) {
        this.mAnakinTrackCorePath1Medium = str;
    }

    public String getAnakinTrackCorePath1Lite() {
        return this.mAnakinTrackCorePath1Lite;
    }

    public void setAnakinTrackCorePath1Lite(String str) {
        this.mAnakinTrackCorePath1Lite = str;
    }

    public String getAnakinTrackCorePath2() {
        return this.mAnakinTrackCorePath2;
    }

    public void setAnakinTrackCorePath2(String str) {
        this.mAnakinTrackCorePath2 = str;
    }

    public String getAnakinTrackCorePath3() {
        return this.mAnakinTrackCorePath3;
    }

    public void setAnakinTrackCorePath3(String str) {
        this.mAnakinTrackCorePath3 = str;
    }

    public String getExpressionModelPath() {
        return this.mExpressionModelPath;
    }

    public void setExpressionModelPath(String str) {
        this.mExpressionModelPath = str;
    }

    public String getMouthModelPath() {
        return this.mMouthModelPath;
    }

    public void setMouthModelPath(String str) {
        this.mMouthModelPath = str;
    }

    public int getFrameWidth() {
        return this.mFrameWidth;
    }

    public void setFrameWidth(int i) {
        this.mFrameWidth = i;
    }

    public int getFrameHeight() {
        return this.mFrameHeight;
    }

    public void setFrameHeight(int i) {
        this.mFrameHeight = i;
    }

    public int getCacheSize() {
        return this.mCacheSize;
    }

    public void setCacheSize(int i) {
        this.mCacheSize = i;
    }

    public float getTrackingSmoothAlpha() {
        return this.mTrackingSmoothAlpha;
    }

    public void setTrackingSmoothAlpha(float f) {
        this.mTrackingSmoothAlpha = f;
    }

    public float getTrackingSmoothThreshold() {
        return this.mTrackingSmoothThreshold;
    }

    public void setTrackingSmoothThreshold(float f) {
        this.mTrackingSmoothThreshold = f;
    }

    public float getTrackingMouthThreshold() {
        return this.mTrackingMouthThreshold;
    }

    public void setTrackingMouthThreshold(float f) {
        this.mTrackingMouthThreshold = f;
    }
}
