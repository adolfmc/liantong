package com.baidu.p120ar.seg;

import android.os.SystemClock;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.statistic.StatisticApi;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.seg.HairSegDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HairSegDetector extends SegDetector {
    private int mMaskWidth = 128;
    private int mMaskHeight = 224;

    @Override // com.baidu.p120ar.seg.SegDetector
    String getAbilityName() {
        return "ability_hair_segmentation";
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    int getAlgoType() {
        return 13;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return 4;
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    void updateReadParams() {
        this.mReadParams.setPixelType(PixelType.NV21);
        this.mReadParams.setOutputWidth(256);
        this.mReadParams.setOutputHeight(144);
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    int initSegAlgo(MdlConfig mdlConfig) {
        String str = mdlConfig.modelPaths[0];
        if (mdlConfig.isFromAsset) {
            return ARMdlInterfaceJNI.initHairSegFromAssetDir(str);
        }
        return ARMdlInterfaceJNI.initHairSeg(str);
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    void releaseSegAlgo() {
        ARMdlInterfaceJNI.releaseHairSeg();
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    SegResult executeSegAlgo(FramePixels framePixels) {
        byte[] newWayPredict;
        SegResult segResult = new SegResult();
        segResult.setTimestamp(framePixels.getTimestamp());
        int segInputOrientation = getSegInputOrientation();
        byte[] bArr = new byte[this.mMaskWidth * this.mMaskHeight];
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!this.mUsePaasHandle) {
            oldWayPredict(framePixels, segInputOrientation, bArr);
        } else if (this.mAlgoHandleController != null) {
            newWayPredict = newWayPredict(framePixels, segInputOrientation, segResult, bArr);
            statisticsAlgoTimeCost(elapsedRealtime);
            segResult.setModel(new SegModel(newWayPredict, this.mMaskWidth, this.mMaskHeight, segInputOrientation, framePixels.isFrontCamera()));
            segResult.setDetectorName(getName());
            return segResult;
        }
        newWayPredict = bArr;
        statisticsAlgoTimeCost(elapsedRealtime);
        segResult.setModel(new SegModel(newWayPredict, this.mMaskWidth, this.mMaskHeight, segInputOrientation, framePixels.isFrontCamera()));
        segResult.setDetectorName(getName());
        return segResult;
    }

    private void oldWayPredict(FramePixels framePixels, int i, byte[] bArr) {
        ARMdlInterfaceJNI.predictHairSeg(framePixels.getPixelsAddress(), framePixels.getWidth(), framePixels.getHeight(), this.mMaskWidth, this.mMaskHeight, 1, i, false, bArr, 0L);
    }

    private byte[] newWayPredict(FramePixels framePixels, int i, SegResult segResult, byte[] bArr) {
        byte[] bArr2;
        try {
            long createHandle = this.mAlgoHandleController.createHandle();
            this.mAlgoHandleController.setHandleInput(createHandle, 13, framePixels.getTimestamp(), 0, framePixels.getWidth(), framePixels.getHeight(), framePixels.isFrontCamera(), framePixels.getSegOrientation().getValue(), this.mEnableSyncRender, framePixels.getPixelsAddress());
            this.mAlgoHandleController.setUsingHandle(createHandle);
            this.mAlgoHandleController.setHandleMaskThreshold(createHandle, this.mMaskThreshold);
            ARMdlInterfaceJNI.predictHairSeg(null, framePixels.getWidth(), framePixels.getHeight(), this.mMaskWidth, this.mMaskHeight, 1, i, false, null, createHandle);
            if (this.mAlgoHandleController != null) {
                this.mAlgoHandleController.setUsingHandle(0L);
                bArr2 = this.mAlgoHandleController.getHandleMaskData(createHandle);
                try {
                    segResult.setResultHandle(createHandle);
                    return bArr2;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return bArr2;
                }
            }
            AlgoHandleAdapter.destroyHandle(createHandle);
            return bArr;
        } catch (Exception e2) {
            e = e2;
            bArr2 = bArr;
        }
    }

    private void statisticsAlgoTimeCost(long j) {
        StatisticApi.getPerformanceApi().recordAlgoTimeCost("sky_seg", "predict", SystemClock.elapsedRealtime() - j, 0);
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    int getSegInputOrientation() {
        int i = this.mOrientationDegree;
        if (i != -90) {
            if (i != 90) {
                if (i == 180) {
                    if (this.mIsFrontCamera) {
                        return 90;
                    }
                    return SubsamplingScaleImageView.ORIENTATION_270;
                } else if (this.mIsFrontCamera) {
                    return SubsamplingScaleImageView.ORIENTATION_270;
                } else {
                    return 90;
                }
            }
            return 180;
        }
        return 0;
    }
}
