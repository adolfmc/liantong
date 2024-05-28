package com.baidu.p120ar.p121vo.detector;

import android.opengl.Matrix;
import com.baidu.p120ar.algo.PreviewInfo;
import com.baidu.p120ar.algovo.ARVOJniClient;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.slam.TrackModel;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.detector.VOAlgoController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOAlgoController implements IVOAlgoInteraction, IVOAlgoTrack {
    private static final float[] DEFAULT_ROTATE_MATRIX = {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final String TAG = "VOAlgoController";
    private float mCameraDistanceToO;
    private float[] mLastImuData;
    private PreviewInfo mPreviewInfo;
    private VOAlgoSetting mSetting;
    private boolean mIsInited = false;
    private float mImuAngle = 0.0f;
    private boolean mIsModelInserted = false;

    public VOAlgoController(VOAlgoSetting vOAlgoSetting) {
        this.mSetting = vOAlgoSetting;
    }

    @Override // com.baidu.p120ar.p121vo.detector.IVOAlgoTrack
    public void init() {
        this.mPreviewInfo = PreviewInfo.getInstance(this.mSetting.frameWidth, this.mSetting.frameHeight, false);
        try {
            LibLoader.require("ardatabasic");
            LibLoader.require("module_basic");
            LibLoader.require("module_vo");
            this.mIsInited = ARVOJniClient.start(this.mPreviewInfo.width, this.mPreviewInfo.height, this.mPreviewInfo.getCamIntrinsicPara(), this.mPreviewInfo.getCamUndistortPara());
        } catch (UnsatisfiedLinkError e) {
            String str = TAG;
            ARLog.m20419e(str, "slam algo UnsatisfiedLinkError " + e.getMessage());
        }
    }

    @Override // com.baidu.p120ar.p121vo.detector.IVOAlgoTrack
    public void track(FramePixels framePixels, ICallbackWith<VOResult> iCallbackWith) {
        VOAlgoSetting vOAlgoSetting;
        float[] imuMatrix;
        if (framePixels == null || !this.mIsInited || (vOAlgoSetting = this.mSetting) == null || (imuMatrix = vOAlgoSetting.imuProvider.getImuMatrix()) == null || imuMatrix.length == 0) {
            return;
        }
        if (this.mIsModelInserted) {
            this.mLastImuData = imuMatrix;
            ARVOJniClient.track(framePixels.getPixelsAddress(), calculateCameraPose(imuMatrix));
            ArrayList<TrackModel> fetchModelPose = ARVOJniClient.fetchModelPose();
            Iterator<TrackModel> it = fetchModelPose.iterator();
            while (it.hasNext()) {
                convertModelPose(it.next().pose, this.mLastImuData);
            }
            VOTrackResult vOTrackResult = new VOTrackResult(framePixels.getTimestamp());
            vOTrackResult.setResult(true);
            vOTrackResult.setTracked(true);
            vOTrackResult.setTrackModels(fetchModelPose);
            iCallbackWith.run(new VOResult(vOTrackResult));
            return;
        }
        iCallbackWith.run(new VOResult(framePixels.getTimestamp()));
    }

    private float[] calculateCameraPose(float[] fArr) {
        if (this.mImuAngle == 0.0f) {
            this.mImuAngle = this.mSetting.imuProvider.getAngle();
        }
        float[] fArr2 = new float[16];
        float[] fArr3 = new float[16];
        Matrix.invertM(fArr2, 0, fArr, 0);
        Matrix.multiplyMM(fArr3, 0, DEFAULT_ROTATE_MATRIX, 0, fArr2, 0);
        return new float[]{-fArr3[4], -fArr3[5], -fArr3[6], -fArr3[0], -fArr3[1], -fArr3[2], -fArr3[8], -fArr3[9], -fArr3[10], 0.0f, 0.0f, 0.0f};
    }

    private void convertModelPose(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[16];
        float[] fArr4 = new float[16];
        Matrix.invertM(fArr3, 0, fArr2, 0);
        Matrix.multiplyMM(fArr4, 0, DEFAULT_ROTATE_MATRIX, 0, fArr3, 0);
        Matrix.invertM(fArr3, 0, fArr4, 0);
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[1];
        fArr[2] = fArr3[2];
        fArr[4] = fArr3[4];
        fArr[5] = fArr3[5];
        fArr[6] = fArr3[6];
        fArr[8] = fArr3[8];
        fArr[9] = fArr3[9];
        fArr[10] = fArr3[10];
        float scale = this.mPreviewInfo.getScale();
        float f = fArr[12];
        float f2 = this.mCameraDistanceToO;
        fArr[12] = f * f2 * scale;
        fArr[13] = fArr[13] * f2 * scale;
        fArr[14] = fArr[14] * f2 * scale;
        Matrix.rotateM(fArr, 0, this.mImuAngle - 90.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override // com.baidu.p120ar.p121vo.detector.IVOAlgoTrack
    public void release() {
        if (this.mIsInited) {
            ARVOJniClient.stop();
            this.mIsInited = false;
        }
        this.mSetting = null;
    }

    @Override // com.baidu.p120ar.p121vo.detector.IVOAlgoInteraction
    public int insertModel(String str, int i, int i2, float[] fArr, float f) {
        if (this.mIsInited) {
            this.mCameraDistanceToO = f;
            if (fArr == null) {
                fArr = DEFAULT_ROTATE_MATRIX;
            }
            int insertModel = ARVOJniClient.insertModel(str, i, i2, fArr, 1.0f);
            this.mIsModelInserted = true;
            return insertModel;
        }
        return -1;
    }

    @Override // com.baidu.p120ar.p121vo.detector.IVOAlgoInteraction
    public void removeAllModel() {
        if (this.mIsInited && this.mIsModelInserted) {
            ARVOJniClient.removeAllModel();
            this.mIsModelInserted = false;
        }
    }

    @Override // com.baidu.p120ar.p121vo.detector.IVOAlgoInteraction
    public float[] calcModelPosition(float f, float[] fArr) {
        if (this.mIsInited) {
            float[] fArr2 = new float[2];
            return new float[]{ARVOJniClient.calModelPosition(this.mPreviewInfo.getCamIntrinsicPara(), f, calculateCameraPose(fArr), fArr2), fArr2[0], fArr2[1]};
        }
        return null;
    }
}
