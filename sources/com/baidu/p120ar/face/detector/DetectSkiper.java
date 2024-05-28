package com.baidu.p120ar.face.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.DetectSkiper */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectSkiper {
    private int detectTimes;
    private int[] mDetectRate = {3, 10, 10, 10, 10};
    private int mTargetDetectFaceNum = 4;
    private int mCurrentFaceNum = 0;
    private boolean directTrack = false;

    public boolean checkShouldDetect() {
        int i = this.detectTimes;
        if (i == 0) {
            this.detectTimes = i + 1;
            return true;
        } else if (this.directTrack) {
            return false;
        } else {
            this.detectTimes = i + 1;
            int i2 = this.detectTimes - 1;
            int[] iArr = this.mDetectRate;
            int i3 = this.mCurrentFaceNum;
            int i4 = this.mTargetDetectFaceNum;
            if (i3 > i4) {
                i3 = i4;
            }
            if (i2 > iArr[i3]) {
                this.detectTimes = 0;
                return true;
            }
            return false;
        }
    }

    public boolean checkNoFaceInData(DetectJniExecutor detectJniExecutor) {
        if (detectJniExecutor.faceAlgoData == null || detectJniExecutor.faceAlgoData.getFaceFrame() == null || detectJniExecutor.faceAlgoData.getFaceFrame().getProcessResult() == 200 || getmCurrentFaceNum() > 0) {
            return false;
        }
        setmCurrentFaceNum(0);
        return true;
    }

    public void notifyFaceNumExceed() {
        this.directTrack = true;
    }

    public void notifyFaceNumNormal() {
        this.directTrack = false;
    }

    public int getmTargetDetectFaceNum() {
        return this.mTargetDetectFaceNum;
    }

    public int getmCurrentFaceNum() {
        return this.mCurrentFaceNum;
    }

    public void setmCurrentFaceNum(int i) {
        this.mCurrentFaceNum = i;
    }

    public void setmTargetDetectFaceNum(int i) {
        this.mTargetDetectFaceNum = i;
    }

    public void setmDetectRate(int[] iArr) {
        this.mDetectRate = iArr;
    }
}
