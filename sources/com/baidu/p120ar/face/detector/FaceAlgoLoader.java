package com.baidu.p120ar.face.detector;

import android.content.Context;
import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.face.FaceUtil;
import com.baidu.p120ar.face.algo.FaceAlgoConfig;
import com.baidu.p120ar.face.algo.FaceJniClient;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ARSDKInfo;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceAlgoLoader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceAlgoLoader {
    private static final String ASSETS_PATH = "file:///android_asset/";
    private static final boolean DEBUG_LOG = true;
    private static final String TAG = "bdar-face";
    private String anakinDetectModelPath;
    private String anakinTrackCorePath0;
    private String[] anakinTrackCorePath1;
    private String anakinTrackCorePath2;
    private String anakinTrackCorePath3;
    private String expressionModelPath;
    private String imbinModelPath;
    private AlgoHandleController mAlgoHandleController;
    private long mResultCallbackTimeStamp;
    private long mUpdateFrameTimeStamp;
    private String mouthModelPath;
    private long mDetectHandle = 0;
    private long mTrackHandle = 0;
    private long mAnimateHandle = 0;
    private boolean mCameraDataFrontCamera = true;
    private FacePerfStaticUtil mFacePerfStaticUtil = new FacePerfStaticUtil();
    private FaceAlgoConfig mFaceAlgoConfig = new FaceAlgoConfig(180, 5, 0.03f, 1.0f);
    private boolean mUsePaasHandle = true;
    private boolean modelLoadedSuccess = false;
    private DetectSkiper mDetectSkiper = new DetectSkiper();

    public FaceAlgoLoader() {
        ARLog.m20419e("algo", "environment version= " + ARSDKInfo.getVersionCode() + ", face = " + FaceJniClient.getFaceAlgoVersion());
    }

    public FaceAlgoLoader init(FaceParams faceParams) {
        this.imbinModelPath = faceParams.getImbinModelPath();
        this.anakinDetectModelPath = faceParams.getAnakinDetectModelPath();
        this.anakinTrackCorePath0 = faceParams.getAnakinTrackCorePath0();
        this.anakinTrackCorePath1 = new String[]{faceParams.getAnakinTrackCorePath1Heavy(), faceParams.getAnakinTrackCorePath1Medium(), faceParams.getAnakinTrackCorePath1Lite()};
        this.anakinTrackCorePath2 = faceParams.getAnakinTrackCorePath2();
        this.anakinTrackCorePath3 = faceParams.getAnakinTrackCorePath3();
        this.expressionModelPath = faceParams.getExpressionModelPath();
        this.mouthModelPath = faceParams.getMouthModelPath();
        return this;
    }

    public FaceAlgoLoader loadFaceModel() {
        long createDetectCore;
        long createTrackCore;
        long createAnimateCore;
        if (FaceUtil.checkModelPathIllegal(this.imbinModelPath, this.anakinDetectModelPath, this.anakinTrackCorePath0, this.anakinTrackCorePath1, this.anakinTrackCorePath2, this.anakinTrackCorePath3, this.expressionModelPath, this.mouthModelPath)) {
            ARLog.m20419e("bdar-face", "init error! check face model!");
            return null;
        }
        ARLog.m20421d("bdar-face", "detect_frame load facemodel");
        setAutoCalibrate(true);
        ARLog.m20421d("bdar-face", "imbin:" + this.imbinModelPath + "\nDetect:" + this.anakinDetectModelPath + "\nTrack0:" + this.anakinTrackCorePath0 + "\nTrack1:" + Arrays.toString(this.anakinTrackCorePath1) + "\nTrack2:" + this.anakinTrackCorePath2 + "\nTrack3:" + this.anakinTrackCorePath3 + "\nexpression:" + this.expressionModelPath + "\nmouth:" + this.mouthModelPath);
        String[] strArr = {"detect", pathRidOfAssetsPrefix(this.anakinDetectModelPath)};
        String[] strArr2 = {"angle", pathRidOfAssetsPrefix(this.anakinTrackCorePath0), "heavy", pathRidOfAssetsPrefix(this.anakinTrackCorePath1[0]), "medium", pathRidOfAssetsPrefix(this.anakinTrackCorePath1[1]), "lite", pathRidOfAssetsPrefix(this.anakinTrackCorePath1[2]), "mouth", pathRidOfAssetsPrefix(this.mouthModelPath), "eyes", pathRidOfAssetsPrefix(this.anakinTrackCorePath2), "iris", pathRidOfAssetsPrefix(this.anakinTrackCorePath3)};
        String[] strArr3 = {"animate", pathRidOfAssetsPrefix(this.imbinModelPath), "expression", pathRidOfAssetsPrefix(this.expressionModelPath)};
        if (this.anakinDetectModelPath.startsWith("file:///android_asset/")) {
            createDetectCore = FaceJniClient.createDetectCoreFromAssetDir(strArr);
        } else {
            createDetectCore = FaceJniClient.createDetectCore(strArr);
        }
        this.mDetectHandle = createDetectCore;
        if (this.anakinTrackCorePath0.startsWith("file:///android_asset/")) {
            createTrackCore = FaceJniClient.createTrackCoreFromAssetDir(strArr2);
        } else {
            createTrackCore = FaceJniClient.createTrackCore(strArr2);
        }
        this.mTrackHandle = createTrackCore;
        if (this.imbinModelPath.startsWith("file:///android_asset/")) {
            createAnimateCore = FaceJniClient.createAnimateCoreFromAssetDir(strArr3);
        } else {
            createAnimateCore = FaceJniClient.createAnimateCore(strArr3);
        }
        this.mAnimateHandle = createAnimateCore;
        ARLog.m20421d("bdar-face", "face init mDetectHandle:" + this.mDetectHandle + " mTrackHandle:" + this.mTrackHandle + " mAnimateHandle:" + this.mAnimateHandle);
        this.modelLoadedSuccess = (((this.mDetectHandle > 0L ? 1 : (this.mDetectHandle == 0L ? 0 : -1)) <= 0 || (this.mTrackHandle > 0L ? 1 : (this.mTrackHandle == 0L ? 0 : -1)) <= 0 || (this.mAnimateHandle > 0L ? 1 : (this.mAnimateHandle == 0L ? 0 : -1)) <= 0) ? 'h' : 'd') == 'd';
        return this;
    }

    private String pathRidOfAssetsPrefix(String str) {
        return str.startsWith("file:///android_asset/") ? str.replace("file:///android_asset/", "") : str;
    }

    public boolean isModelLoadedSuccess() {
        return this.modelLoadedSuccess;
    }

    public void setContext(Context context) {
        Context context2 = (Context) new WeakReference(context).get();
        if (context2 != null) {
            FaceJniClient.setAssetManager(context2.getApplicationContext().getAssets());
        }
    }

    public DetectSkiper getDetectSkiper() {
        return this.mDetectSkiper;
    }

    public void setAlgoHandleController(AlgoHandleController algoHandleController) {
        this.mAlgoHandleController = algoHandleController;
    }

    public AlgoHandleController getmAlgoHandleController() {
        return this.mAlgoHandleController;
    }

    public long getmDetectHandle() {
        return this.mDetectHandle;
    }

    public long getmTrackHandle() {
        return this.mTrackHandle;
    }

    public long getmAnimateHandle() {
        return this.mAnimateHandle;
    }

    public FaceAlgoConfig getmFaceAlgoConfig() {
        return this.mFaceAlgoConfig;
    }

    public FacePerfStaticUtil getmFacePerfStaticUtil() {
        return this.mFacePerfStaticUtil;
    }

    public boolean ismUsePaasHandle() {
        return this.mUsePaasHandle;
    }

    public void setTrackMode(int i) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "trackMode:" + i);
            this.mFaceAlgoConfig.setRunningMode(i);
        }
    }

    public void setAnimateMode(boolean z, boolean z2, boolean z3) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setAnimateMode needHeadPose:" + z + " needSkeleton:" + z2 + " needTriggers:" + z3);
            this.mFaceAlgoConfig.setAnimateRunningMode(z, z2, z3);
        }
    }

    public void setNeedHeadPose(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setNeedHeadPose:" + z);
            this.mFaceAlgoConfig.setNeedHeadPose(z);
        }
    }

    public void setNeedSkeleton(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setNeedSkeleton:" + z);
            this.mFaceAlgoConfig.setNeedSkeleton(z);
        }
    }

    public void setNeedTriggers(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setNeedTriggers:" + z);
            this.mFaceAlgoConfig.setNeedTriggers(z);
        }
    }

    public void setTargetDetectFaceNum(int i) {
        this.mDetectSkiper.setmTargetDetectFaceNum(i);
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setMaxTrackingFace:" + i);
            this.mFaceAlgoConfig.setMaxTrackingFace(i);
        }
    }

    public void setDetectRate(int[] iArr) {
        DetectSkiper detectSkiper = this.mDetectSkiper;
        if (detectSkiper != null) {
            detectSkiper.setmDetectRate(iArr);
        } else {
            ARLog.m20419e("bdar-face", "mDetectSkiper is null when being configured");
        }
    }

    public void setEyeMode(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setNeedRefineEyes:" + z);
            this.mFaceAlgoConfig.setNeedRefineEyes(z);
        }
    }

    public void setAnimojiMode(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            if (z && !ARAuth.checkFeatureAuth(1230)) {
                ARLog.m20421d("bdar-face", "setAnimojiMode(true) hasn't auth");
                return;
            }
            ARLog.m20421d("bdar-face", "setAnimojiMode:" + z);
            this.mFaceAlgoConfig.setIsAnimojiMode(z);
        }
    }

    public void setMouthMode(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setNeedRefineMouth:" + z);
            this.mFaceAlgoConfig.setNeedRefineMouth(z);
        }
    }

    public void setExpressionMode(boolean z) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "setNeedExpression:" + z);
            this.mFaceAlgoConfig.setNeedExpression(z);
        }
    }

    private void setAutoCalibrate(boolean z) {
        FaceAlgoConfig faceAlgoConfig = this.mFaceAlgoConfig;
        if (faceAlgoConfig != null) {
            faceAlgoConfig.setAutoCalibrate(z);
        }
    }

    public void setTrackingSmooth(float f, float f2) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "mTrackingSmoothAlpha:" + f + " mTrackingSmoothThreshold:" + f2);
            this.mFaceAlgoConfig.setTrackingSmoothAlpha(f);
            this.mFaceAlgoConfig.setTrackingSmoothThreshold(f2);
        }
    }

    public void setMouthThreshold(float f) {
        if (this.mFaceAlgoConfig != null) {
            ARLog.m20421d("bdar-face", "mTrackingMouthThreshold:" + f);
            this.mFaceAlgoConfig.setTrackingMouthThreshold(f);
        }
    }

    public boolean checkNotPrepared(boolean z, int i) {
        if (!this.modelLoadedSuccess) {
            ARLog.m20417i("bdar-face", "detect_frame track task executing modelLoad failed");
            return true;
        }
        if ((!this.mCameraDataFrontCamera) == z) {
            this.mFaceAlgoConfig.setForceLost(true);
            this.mCameraDataFrontCamera = z;
        } else {
            this.mFaceAlgoConfig.setForceLost(false);
        }
        this.mFaceAlgoConfig.setMirror(false);
        this.mFaceAlgoConfig.setTrackingRT(FaceUtil.getOrientation(i));
        return false;
    }

    public FaceAlgoLoader updateFrameTimeStamp() {
        FacePerfStaticUtil facePerfStaticUtil;
        if (this.mUpdateFrameTimeStamp > 0 && (facePerfStaticUtil = this.mFacePerfStaticUtil) != null) {
            facePerfStaticUtil.calculateCameraCostTime(System.currentTimeMillis() - this.mUpdateFrameTimeStamp);
        }
        this.mUpdateFrameTimeStamp = System.currentTimeMillis();
        return this;
    }

    public FaceAlgoLoader updateCallbackTimeStamp() {
        FacePerfStaticUtil facePerfStaticUtil;
        if (this.mResultCallbackTimeStamp > 0 && (facePerfStaticUtil = this.mFacePerfStaticUtil) != null) {
            facePerfStaticUtil.calculateResultCostTime(System.currentTimeMillis() - this.mResultCallbackTimeStamp);
        }
        this.mResultCallbackTimeStamp = System.currentTimeMillis();
        return this;
    }

    public void destroyHandle(long j) {
        ARLog.m20415v("bdar-face", "[FaceHandlerThread] destroy handle:" + j);
        FaceJniClient.destoryFrame(j);
    }

    public void destroyDataHandle(long j) {
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.destroyHandle(j);
        }
    }

    public boolean checkUpdateFrameStatus() {
        return isModelLoadedSuccess();
    }

    public boolean release() {
        doFaceRelease();
        FacePerfStaticUtil facePerfStaticUtil = this.mFacePerfStaticUtil;
        if (facePerfStaticUtil != null) {
            facePerfStaticUtil.resetTimes();
            this.mFacePerfStaticUtil = null;
            return true;
        }
        return true;
    }

    private void doFaceRelease() {
        try {
            if (this.mDetectHandle > 0) {
                FaceJniClient.releaseDetectCore(this.mDetectHandle);
            }
            if (this.mTrackHandle > 0) {
                FaceJniClient.releaseTrackCore(this.mTrackHandle);
            }
            if (this.mAnimateHandle > 0) {
                FaceJniClient.releaseAnimateCore(this.mAnimateHandle);
            }
            this.mDetectHandle = 0L;
            this.mTrackHandle = 0L;
            this.mAnimateHandle = 0L;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
