package com.baidu.p120ar.seg;

import android.os.SystemClock;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.ReserveHandleData;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.statistic.StatisticApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.seg.ImgSegDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImgSegDetector extends SegDetector {
    private boolean mIsSmallModel;

    @Override // com.baidu.p120ar.seg.SegDetector
    String getAbilityName() {
        return "ability_image_segmentation";
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    int getAlgoType() {
        return 11;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return 2;
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    void updateReadParams() {
        this.mReadParams.setPixelType(PixelType.BGR);
        this.mReadParams.setOutputWidth(192);
        this.mReadParams.setOutputHeight(256);
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    int initSegAlgo(MdlConfig mdlConfig) {
        int[] iArr = new int[2];
        this.mIsSmallModel = getIsSmallModel(mdlConfig);
        String str = mdlConfig.modelPaths[0];
        if (mdlConfig.isFromAsset) {
            return ARMdlInterfaceJNI.initHumanSegFromAssetDir(str, 1, iArr, this.mIsSmallModel);
        }
        return ARMdlInterfaceJNI.initHumanSeg(str, 1, iArr, this.mIsSmallModel);
    }

    private boolean getIsSmallModel(MdlConfig mdlConfig) {
        return mdlConfig.getDeviceLevel(this.mAbilityScheme) != 2;
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    void releaseSegAlgo() {
        ARMdlInterfaceJNI.releaseHumanSeg();
    }

    @Override // com.baidu.p120ar.seg.SegDetector
    SegResult executeSegAlgo(FramePixels framePixels) {
        byte[] newWayPredict;
        SegResult segResult = new SegResult();
        segResult.setTimestamp(framePixels.getTimestamp());
        int segInputOrientation = getSegInputOrientation();
        int i = this.mIsSmallModel ? 160 : 192;
        int i2 = this.mIsSmallModel ? 160 : 192;
        byte[] bArr = new byte[i * i2];
        int[] iArr = new int[2];
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!this.mUsePaasHandle) {
            oldWayPredict(framePixels, segInputOrientation, bArr, iArr);
        } else if (this.mAlgoHandleController != null) {
            newWayPredict = newWayPredict(framePixels, segInputOrientation, segResult, bArr, iArr);
            statisticsAlgoTimeCost(elapsedRealtime);
            segResult.setModel(new SegModel(newWayPredict, i, i2, segInputOrientation, framePixels.isFrontCamera()));
            segResult.setDetectorName(getName());
            return segResult;
        }
        newWayPredict = bArr;
        statisticsAlgoTimeCost(elapsedRealtime);
        segResult.setModel(new SegModel(newWayPredict, i, i2, segInputOrientation, framePixels.isFrontCamera()));
        segResult.setDetectorName(getName());
        return segResult;
    }

    private void oldWayPredict(FramePixels framePixels, int i, byte[] bArr, int[] iArr) {
        ARMdlInterfaceJNI.predictHumanSeg(framePixels.getPixelsAddress(), framePixels.getWidth(), framePixels.getHeight(), i, framePixels.isFrontCamera(), bArr, iArr, 0L);
    }

    private byte[] newWayPredict(FramePixels framePixels, int i, SegResult segResult, byte[] bArr, int[] iArr) {
        byte[] bArr2;
        SegResult segResult2;
        try {
            long createHandle = this.mAlgoHandleController.createHandle();
            this.mAlgoHandleController.setHandleInput(createHandle, 11, framePixels.getTimestamp(), 2, framePixels.getWidth(), framePixels.getHeight(), framePixels.isFrontCamera(), framePixels.getSegOrientation().getValue(), this.mEnableSyncRender, framePixels.getPixelsAddress());
            this.mAlgoHandleController.setUsingHandle(createHandle);
            ARMdlInterfaceJNI.predictHumanSeg(null, framePixels.getWidth(), framePixels.getHeight(), 0, framePixels.isFrontCamera(), null, iArr, createHandle);
            if (this.mAlgoHandleController != null) {
                this.mAlgoHandleController.setUsingHandle(0L);
                ReserveHandleData reserveHandleData = new ReserveHandleData();
                this.mAlgoHandleController.getHandleReserveData(createHandle, reserveHandleData);
                if (reserveHandleData.getByteArrayListData() == null || reserveHandleData.getByteArrayListData().size() <= 0) {
                    segResult2 = segResult;
                    bArr2 = bArr;
                } else {
                    bArr2 = reserveHandleData.getByteArrayListData().get(0);
                    segResult2 = segResult;
                }
                try {
                    segResult2.setResultHandle(createHandle);
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
                return i != 180 ? this.mIsFrontCamera ? 3 : 1 : this.mIsFrontCamera ? 1 : 3;
            }
            return 2;
        }
        return 0;
    }
}
