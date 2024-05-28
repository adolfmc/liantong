package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.face.algo.FaceAlgoData;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceResultModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceResultModel {
    private boolean isFrontCamera;
    private ByteBuffer mCameraData;
    private long mDataHandle = 0;
    private FaceAlgoData mFaceAlgoData;
    private long mFaceHandle;
    private boolean mResult;
    private long mTimeCost;
    private long mTimestamp;
    private boolean mTracked;

    public FaceResultModel(long j) {
        this.mTimestamp = j;
    }

    public void setCameraData(ByteBuffer byteBuffer) {
        this.mCameraData = byteBuffer;
    }

    public ByteBuffer getCameraData() {
        return this.mCameraData;
    }

    public boolean isResult() {
        return this.mResult;
    }

    public void setResult(boolean z) {
        this.mResult = z;
    }

    public boolean isTracked() {
        return this.mTracked;
    }

    public void setTracked(boolean z) {
        this.mTracked = z;
    }

    public void setFrontCamera(boolean z) {
        this.isFrontCamera = z;
    }

    public void setDataHandle(long j) {
        this.mDataHandle = j;
    }

    public long getTimeCost() {
        return this.mTimeCost;
    }

    public void setTimeCost(long j) {
        this.mTimeCost = j;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public FaceAlgoData getFaceAlgoData() {
        return this.mFaceAlgoData;
    }

    public void setFaceAlgoData(FaceAlgoData faceAlgoData) {
        this.mFaceAlgoData = faceAlgoData;
    }

    public void setFaceHandle(long j) {
        this.mFaceHandle = j;
    }

    public long getFaceHandle() {
        return this.mFaceHandle;
    }

    public boolean isFrontCamera() {
        return this.isFrontCamera;
    }

    public long getDataHandle() {
        return this.mDataHandle;
    }
}
