package com.baidu.p120ar.face.detector;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.armdl.detector.MdlDetector;
import com.baidu.p120ar.armdl.task.MdlDestroyTask;
import com.baidu.p120ar.armdl.task.MdlInitTask;
import com.baidu.p120ar.armdl.task.MdlPredictTask;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.arrender.RenderFaceData;
import com.baidu.p120ar.async.ARTask;
import com.baidu.p120ar.async.AsyncTaskManager;
import com.baidu.p120ar.bus.CallBack;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.face.FaceDefaultConfig;
import com.baidu.p120ar.face.FaceUtil;
import com.baidu.p120ar.face.models.FaceModelConfig;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceDetector extends MdlDetector {
    public static final String TAG = "FaceDetector";
    private FaceAlgoLoader mFaceAlgoLoader;
    private FaceParams mFaceParams;
    private PixelReadParams mPixelReadParams;
    private int mMdlType = 11;
    private boolean mEnableSyncRender = false;
    private boolean mIsVideoProcess = false;
    private boolean mSingleFrame = false;
    private int repeatTrackTimes = 3;
    private FaceDetectorCallback faceDetectorCallback = new FaceDetectorCallback();
    private String mAbilityName = null;
    private int mDetectTimes = 0;
    private long lastFrameTimestamp = 0;

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return "FaceDetector";
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return null;
    }

    public FaceDetector() {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mPixelReadParams = new PixelReadParams(PixelType.BGR);
        if (this.mFaceAlgoLoader == null) {
            this.mFaceAlgoLoader = new FaceAlgoLoader();
        }
    }

    public void setFaceParams(FaceParams faceParams) {
        this.mFaceParams = faceParams;
        this.mPixelReadParams.setOutputWidth(this.mFaceParams.getFrameWidth());
        this.mPixelReadParams.setOutputHeight(this.mFaceParams.getFrameHeight());
        this.mPixelReadParams.setIsPortrait(true);
    }

    public void setSingleFrame() {
        this.mSingleFrame = true;
        this.repeatTrackTimes = 3;
    }

    public void setSeqFrame() {
        this.mSingleFrame = false;
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public PixelReadParams getReadParams() {
        return this.mPixelReadParams;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        if (this.mFaceParams == null) {
            ARLog.m20419e("FaceDetector", "setupFrameDetector mFaceParams is NULLLL");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.face.detector.FaceDetector$FaceDetectorCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class FaceDetectorCallback {
        protected FaceDetectorCallback() {
        }

        public void onDetected(FaceResultModel faceResultModel) {
            AsyncTaskManager.getInstance().postTask(new AdditionalTask(faceResultModel));
        }
    }

    private void callBackFaceAR(FaceResultModel faceResultModel) {
        if (this.mDetectorCallback == null || faceResultModel == null) {
            return;
        }
        FaceResult faceResult = new FaceResult(getName(), faceResultModel);
        faceResult.setResultHandle(faceResultModel.getDataHandle());
        if (this.mAbilityName != null && faceResultModel.getFaceHandle() > 0) {
            RenderFaceData renderFaceData = new RenderFaceData();
            renderFaceData.setAbilityName(this.mAbilityName);
            renderFaceData.setFaceHandle(faceResultModel.getFaceHandle());
            renderFaceData.setSyncWithCamera(true);
            renderFaceData.setFrontCameraData(faceResultModel.isFrontCamera());
            renderFaceData.setTimestamp(faceResultModel.getTimestamp());
            renderFaceData.setAlgoInputWidth(this.mPixelReadParams.getOutputWidth());
            renderFaceData.setAlgoInputHeight(this.mPixelReadParams.getOutputHeight());
            faceResult.setResultData(renderFaceData);
        }
        if (faceResultModel.getTimestamp() < 0 || Math.abs(this.lastFrameTimestamp - faceResultModel.getTimestamp()) > 99999999) {
            ARLog.m20421d("FaceDetector", "time interval between framepixels is larger than 33 * 3ms");
        } else if (this.lastFrameTimestamp >= faceResultModel.getTimestamp()) {
            return;
        }
        ARLog.m20421d("FaceDetector", "time_interval: " + String.valueOf(faceResultModel.getTimestamp() - this.lastFrameTimestamp));
        this.lastFrameTimestamp = faceResultModel.getTimestamp();
        this.mDetectorCallback.onDetected(faceResult);
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public boolean isDetectEnable() {
        return (!this.mFaceAlgoLoader.checkUpdateFrameStatus() || ARMdlManager.getInstance().getMdlStateByType(13) || ARMdlManager.getInstance().getMdlStateByType(12)) ? false : true;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public boolean detectFrame(FramePixels framePixels) {
        boolean checkMdlInit = ARMdlManager.getInstance().checkMdlInit(getMdlType());
        if (!checkMdlInit || framePixels == null) {
            ARLog.m20419e("FaceDetector", "detect_frame detect frame failed isInitTaskFinished: " + checkMdlInit);
            if (framePixels != null) {
                callBackFaceAR(new FaceResultModel(framePixels.getTimestamp()));
                return true;
            }
            return false;
        }
        this.mFaceAlgoLoader.updateFrameTimeStamp();
        if (!this.mSingleFrame || this.repeatTrackTimes >= 3) {
            DetectTask detectTask = new DetectTask(framePixels.getPixelsAddress(), framePixels.getWidth(), framePixels.getHeight(), framePixels.getTimestamp(), framePixels.isFrontCamera(), this.mIsVideoProcess ? 2 : framePixels.getOrientation().getDegree(), this.mEnableSyncRender);
            detectTask.setCallback(this.faceDetectorCallback);
            detectTask.setFaceAlgoLoader(this.mFaceAlgoLoader);
            return AsyncTaskManager.getInstance().postTask(detectTask);
        }
        return false;
    }

    private boolean appendToAdditionalThread(ARTask aRTask, boolean z) {
        if (z) {
            if (checkAdditionalThreadBusy()) {
                return false;
            }
            if (aRTask instanceof DetectTask) {
                ((DetectTask) aRTask).setThreadTag("additional_thread");
                return true;
            } else if (aRTask instanceof TrackTask) {
                ((TrackTask) aRTask).setThreadTag("additional_thread");
                return true;
            } else if (aRTask instanceof AnimateTask) {
                ((AnimateTask) aRTask).setThreadTag("additional_thread");
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkAdditionalThreadBusy() {
        boolean mdlStateByType = ARMdlManager.getInstance().getMdlStateByType(11);
        this.mDetectTimes++;
        if (mdlStateByType && this.mDetectTimes > 1) {
            this.mDetectTimes = 0;
            return mdlStateByType;
        }
        return false;
    }

    @CallBack
    public void onDetectResult(DetectJniExecutor detectJniExecutor) {
        TrackTask trackTask = new TrackTask(detectJniExecutor);
        trackTask.setFaceDetectorCallback(this.faceDetectorCallback);
        AsyncTaskManager.getInstance().postTask(trackTask);
    }

    @CallBack
    public void onTrackResult(TrackJniExecutor trackJniExecutor) {
        if (this.mSingleFrame) {
            int i = this.repeatTrackTimes;
            if (i == 0) {
                this.faceDetectorCallback.onDetected(FaceUtil.formFaceModel(trackJniExecutor.faceAlgoData, trackJniExecutor.costTime, trackJniExecutor.faceHandle, trackJniExecutor.dataHandle, trackJniExecutor.cameraData, trackJniExecutor.timestamp, trackJniExecutor.frontCamera));
                return;
            } else if (i > 0) {
                this.repeatTrackTimes = i - 1;
                TrackTask trackTask = new TrackTask(trackJniExecutor);
                trackTask.setFaceDetectorCallback(this.faceDetectorCallback);
                AsyncTaskManager.getInstance().postTask(trackTask);
                return;
            } else {
                return;
            }
        }
        boolean mdlStateByType = ARMdlManager.getInstance().getMdlStateByType(14);
        AnimateTask animateTask = new AnimateTask(trackJniExecutor);
        if (appendToAdditionalThread(animateTask, mdlStateByType)) {
            AsyncTaskManager.getInstance().postTask(animateTask);
        }
    }

    @CallBack
    public void onAnimateResult(AnimateJniExecutor animateJniExecutor) {
        AsyncTaskManager.getInstance().postTask(new AdditionalTask(animateJniExecutor));
    }

    @CallBack
    public void onFaceAlgoResult(FaceResultModel faceResultModel) {
        callBackFaceAR(faceResultModel);
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public void setAlgoHandleController(AlgoHandleController algoHandleController) {
        this.mFaceAlgoLoader.setAlgoHandleController(algoHandleController);
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return this.mMdlType;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(Bundle bundle) {
        ARLog.m20413w("FaceDetector", "detect_frame onCreateInitTask");
        MdlInitTask mdlInitTask = new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.face.detector.FaceDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return null;
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                FaceDetector.this.mFaceAlgoLoader.init(FaceDetector.this.mFaceParams).loadFaceModel();
                if (FaceDetector.this.mDetectorCallback != null && FaceDetector.this.mFaceAlgoLoader.isModelLoadedSuccess()) {
                    FaceDetector.this.mDetectorCallback.onSetup(new ResultModel(FaceDetector.this.getName(), true, 10));
                }
                return FaceDetector.this.mFaceAlgoLoader.isModelLoadedSuccess() ? 0 : -1;
            }
        };
        mdlInitTask.setPriority(10);
        return mdlInitTask;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.face.detector.FaceDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return null;
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                FaceDetector.this.mFaceAlgoLoader.release();
                return 0;
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
        disableMdl();
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onRelease(new ResultModel(getName(), true));
        }
        ARLog.m20413w("FaceDetector", "releaseFrameDetector");
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public void destroyHandle(long j) {
        this.mFaceAlgoLoader.destroyHandle(j);
    }

    public void destroyDataHandle(long j) {
        this.mFaceAlgoLoader.destroyDataHandle(j);
    }

    public FaceAlgoLoader getmFaceAlgoLoader() {
        return this.mFaceAlgoLoader;
    }

    public void setSyncStatus(boolean z) {
        this.mEnableSyncRender = z;
    }

    public void setAbilityName(String str) {
        this.mAbilityName = str;
    }

    public void setIsVideoProcess(boolean z) {
        this.mIsVideoProcess = z;
    }

    public void setAlgoLoaderContext(Context context) {
        this.mFaceAlgoLoader.setContext(context);
    }

    public void configFaceAlgo(FaceModelConfig.DeviceModel deviceModel, int i, FaceDefaultConfig faceDefaultConfig, FaceModelConfig.ModelConfig modelConfig, String str) {
        this.mFaceAlgoLoader.setTrackMode(i);
        if (deviceModel != null) {
            this.mFaceAlgoLoader.setTrackingSmooth(Float.parseFloat(deviceModel.mTrackingSmoothAlpha), Float.parseFloat(deviceModel.mTrackingSmoothThreshold));
            this.mFaceAlgoLoader.setMouthThreshold(Float.parseFloat(deviceModel.mTrackingMouthThreshold));
        }
        this.mFaceAlgoLoader.setEyeMode(faceDefaultConfig.isNeedEye());
        this.mFaceAlgoLoader.setMouthMode(faceDefaultConfig.isNeedMouth());
        this.mFaceAlgoLoader.setAnimojiMode(faceDefaultConfig.isNeedAnimoji());
        this.mFaceAlgoLoader.setAnimateMode(faceDefaultConfig.isNeedHeadPose(), faceDefaultConfig.isNeedSkeleton(), faceDefaultConfig.isNeedTriggers());
        this.mFaceAlgoLoader.setExpressionMode(faceDefaultConfig.isNeedExpression());
        int maxFaceSupportNum = faceDefaultConfig.getMaxFaceSupportNum();
        if (!TextUtils.isEmpty(str)) {
            maxFaceSupportNum = faceDefaultConfig.getMaxFaceNumNoSticker() < faceDefaultConfig.getMaxFaceSupportNum() ? faceDefaultConfig.getMaxFaceNumNoSticker() : faceDefaultConfig.getMaxFaceSupportNum();
        }
        this.mFaceAlgoLoader.setTargetDetectFaceNum(maxFaceSupportNum);
        if (modelConfig == null || modelConfig.mDetectRate == null) {
            return;
        }
        this.mFaceAlgoLoader.setDetectRate(modelConfig.mDetectRate);
    }
}
