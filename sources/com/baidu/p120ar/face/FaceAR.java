package com.baidu.p120ar.face;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.face.algo.FAUPoint2D;
import com.baidu.p120ar.face.algo.FaceFrame;
import com.baidu.p120ar.face.attributes.FaceAttributesManager;
import com.baidu.p120ar.face.detector.FaceDetector;
import com.baidu.p120ar.face.detector.FaceParams;
import com.baidu.p120ar.face.detector.FaceResultModel;
import com.baidu.p120ar.face.models.FaceModelConfig;
import com.baidu.p120ar.filter.ARFilterManager;
import com.baidu.p120ar.filter.FilterNode;
import com.baidu.p120ar.filter.FilterParam;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARFileUtils;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.FileUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.face.FaceAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceAR extends AbstractAR implements IFace {
    private static final String TAG = "FaceAR";
    private DetectorCallback mDetectorCallback;
    private FaceModelConfig.DeviceModel mDeviceModel;
    private FaceAttributesManager mFaceAttributesManager;
    private FaceDetector mFaceDetector;
    private FaceListener mFaceListener;
    private int[] mLastFaceIDList;
    private LuaMsgListener mLuaMsgListener;
    private List<String> mTriggers = new ArrayList();
    private String currentTrigger = null;
    private String mCurrentSticker = null;
    private int mDetectedFaceNum = 0;
    private int mDeviceModelLevel = 0;
    FaceDefaultConfig mFaceDefaultConfig = new FaceDefaultConfig();
    private FaceModelConfig.ModelConfig mModelConfig = null;
    private AlgoHandleController mAlgoHandleController = null;
    private int mAlgoType = -1;
    private boolean isSetup = false;
    private Object mLock = new Object();

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x013e  */
    @Override // com.baidu.p120ar.AbstractAR
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setup(java.util.HashMap<java.lang.String, java.lang.Object> r10) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.face.FaceAR.setup(java.util.HashMap):void");
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void beforMakeUpOpen() {
        if (isNotLiteModel() || this.mFaceDefaultConfig.isEnableSyncRender()) {
            return;
        }
        FaceUtil.forceHighLevelMode(this.mFaceDetector.getmFaceAlgoLoader());
        configSyncStatus(true);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void afterMakeUpOff() {
        if (!isNotLiteModel() && this.mFaceDefaultConfig.isEnableSyncRender()) {
            FaceUtil.forceLowLevelMode(this.mFaceDetector.getmFaceAlgoLoader());
            configSyncStatus(false);
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void adjust(HashMap<String, Object> hashMap) {
        super.adjust(hashMap);
        updateAbility();
    }

    private void updateAbility() {
        if (this.mFaceDetector != null) {
            if (getEnabledAbilitys().contains("ability_face_model")) {
                this.mFaceDetector.setAbilityName("ability_face_model");
            } else if (getEnabledAbilitys().contains("ability_makeup_filter")) {
                this.mFaceDetector.setAbilityName("ability_makeup_filter");
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onCaseCreate(String str) {
        ARLog.m20421d(TAG, "onCaseCreate start!!!");
        this.mCurrentSticker = str;
        boolean parseFaceJson = parseFaceJson(str);
        if (!parseFaceJson) {
            this.mCurrentSticker = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.set3DModelVisible(true);
            renderer.setFieldOfView(calculateFov());
        }
        if (TextUtils.isEmpty(this.mCurrentSticker)) {
            this.mFaceDefaultConfig.setDefaultFaceConfig(this.mDeviceModelLevel);
        } else {
            this.mFaceDefaultConfig.setOrFaceConfig(this.mDeviceModelLevel);
        }
        if (!parseFaceJson) {
            this.mFaceDefaultConfig.setSingleFace();
        }
        this.mFaceDetector.configFaceAlgo(this.mDeviceModel, this.mDeviceModelLevel, this.mFaceDefaultConfig, this.mModelConfig, this.mCurrentSticker);
        List<String> list = this.mTriggers;
        if (list != null && list.size() > 0) {
            List<String> list2 = this.mTriggers;
            this.currentTrigger = list2.get(list2.size() - 1);
        }
        FaceListener faceListener = this.mFaceListener;
        if (faceListener != null) {
            faceListener.onStickerLoadingFinished(this.mTriggers);
        }
        if (this.mFaceDefaultConfig.getMaxFaceSupportNum() > 1) {
            setMakeupEnable(false);
        } else {
            setMakeupEnable(true);
        }
        updateAbility();
        FaceAttributesManager faceAttributesManager = this.mFaceAttributesManager;
        if (faceAttributesManager != null) {
            faceAttributesManager.reset();
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onCaseDestroy() {
        ARLog.m20421d(TAG, "onCaseDestroy!!!");
        this.mCurrentSticker = null;
        this.currentTrigger = null;
        this.mDetectedFaceNum = 0;
        configSyncStatus(isNotLiteModel());
        statisticFaceShowHide(null);
        this.mFaceDefaultConfig.setDefaultFaceConfig(this.mDeviceModelLevel);
        this.mFaceDetector.configFaceAlgo(this.mDeviceModel, this.mDeviceModelLevel, this.mFaceDefaultConfig, this.mModelConfig, this.mCurrentSticker);
        updateAbility();
    }

    private boolean isNotLiteModel() {
        int i = this.mDeviceModelLevel;
        return i == 2 || i == 1;
    }

    @Override // com.baidu.p120ar.face.IFace
    public void setFaceListener(FaceListener faceListener) {
        this.mFaceListener = faceListener;
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void resume() {
        super.resume();
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void pause() {
        super.pause();
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        ARLog.m20421d(TAG, "release");
        synchronized (this.mLock) {
            if (this.isSetup) {
                this.isSetup = false;
                configSyncStatus(false);
                statisticFaceShowHide(null);
                setMakeupEnable(false);
                this.mFaceDefaultConfig.setNeedCheckMakeup(false);
                FaceDetector faceDetector = this.mFaceDetector;
                if (faceDetector != null) {
                    faceDetector.setAlgoHandleController(null);
                }
                removeDetector(this.mFaceDetector);
                AlgoHandleController algoHandleController = this.mAlgoHandleController;
                if (algoHandleController != null) {
                    algoHandleController.release();
                    this.mAlgoHandleController = null;
                }
                FaceAttributesManager faceAttributesManager = this.mFaceAttributesManager;
                if (faceAttributesManager != null) {
                    faceAttributesManager.release();
                }
                IARRenderer renderer = getRenderer();
                if (renderer != null) {
                    renderer.removeAlgoCache(10);
                }
                super.release();
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null) {
            return;
        }
        try {
            if (algoHandleController.getHandleType(j) != 10 || this.mFaceDetector == null) {
                return;
            }
            long handleFaceHandle = AlgoHandleAdapter.getHandleFaceHandle(j);
            if (handleFaceHandle > 0) {
                AlgoHandleAdapter.setHandleFaceHandle(j, 0L);
                if (this.mFaceDetector != null) {
                    this.mFaceDetector.destroyHandle(handleFaceHandle);
                }
            }
            this.mFaceDetector.destroyDataHandle(j);
        } catch (Exception e) {
            Log.e("FaceAR", "Destory algoHandle failed.  " + e.getMessage());
        }
    }

    @Override // com.baidu.p120ar.AbstractAR, com.baidu.p120ar.arrender.ARRenderer.InputSizeChangeListener
    public void onInputSizeChange(int i, int i2) {
        super.onInputSizeChange(i, i2);
    }

    private boolean parseFaceJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            String readFileText = FileUtils.readFileText(new File(ARFileUtils.getFaceJsonPath(str)));
            if (TextUtils.isEmpty(readFileText)) {
                return false;
            }
            this.mFaceDefaultConfig.parseFaceParamsFromJson(readFileText, this.mDeviceModelLevel);
            try {
                JSONObject jSONObject = new JSONObject(readFileText);
                if (jSONObject.has("mainTriggers")) {
                    this.mTriggers.clear();
                    this.mTriggers.add(jSONObject.getString("mainTriggers"));
                    return true;
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    private void recalculateAlgoImageSize() {
        if (this.mIsCameraInput) {
            this.mFaceDefaultConfig.resetSize();
            return;
        }
        int i = 180;
        int i2 = 320;
        int i3 = this.mInputWidth;
        int i4 = this.mInputHeight;
        if (this.mInputDegree == 90 || this.mInputDegree == 270) {
            i3 = this.mInputHeight;
            i4 = this.mInputWidth;
        }
        float f = i3;
        float f2 = i4;
        float f3 = 180;
        if (Float.compare((f * 1.0f) / f2, (1.0f * f3) / 320) != 0) {
            if (i3 > i4) {
                i2 = 180;
                i = Math.round(f * (f3 / f2));
            } else {
                i2 = Math.round(f2 * (f3 / f));
            }
        }
        this.mFaceDefaultConfig.setAlgoImageWidth(i);
        this.mFaceDefaultConfig.setAlgoImageHeight(i2);
    }

    private FaceParams createFaceParams() {
        recalculateAlgoImageSize();
        FaceParams faceParams = new FaceParams();
        faceParams.setFrameWidth(this.mFaceDefaultConfig.getAlgoImageWidth());
        faceParams.setFrameHeight(this.mFaceDefaultConfig.getAlgoImageHeight());
        FaceModelConfig.ModelConfig modelConfig = this.mModelConfig;
        if (modelConfig == null) {
            return faceParams;
        }
        faceParams.setImbinModelPath(modelConfig.mImbin);
        faceParams.setAnakinDetectModelPath(this.mModelConfig.mDetectModel);
        faceParams.setExpressionModelPath(this.mModelConfig.mExpression);
        faceParams.setMouthModelPath(this.mModelConfig.mMouth);
        String str = this.mModelConfig.mTrack1Heavy;
        String str2 = this.mModelConfig.mTrack1Medium;
        String str3 = this.mModelConfig.mTrack1Lite;
        String str4 = TAG;
        ARLog.m20421d(str4, "classification result：" + this.mModelConfig.mCurrentDeviceLevel);
        FaceModelConfig.DeviceModel configLevel = this.mFaceDefaultConfig.configLevel(this.mModelConfig);
        if (configLevel != null) {
            this.mDeviceModel = configLevel;
            this.mDeviceModelLevel = FaceUtil.getDeviceModelLevel(configLevel.mTrackParam1, str, str2, str3);
            String str5 = TAG;
            ARLog.m20421d(str5, "createFaceParams() mDeviceModelLevel = " + this.mDeviceModelLevel);
            faceParams.setAnakinTrackCorePath0(configLevel.mTrackParam0);
            faceParams.setAnakinTrackCorePath1Heavy(str);
            faceParams.setAnakinTrackCorePath1Medium(str2);
            faceParams.setAnakinTrackCorePath1Lite(str3);
            faceParams.setAnakinTrackCorePath2(configLevel.mTrackParam2);
            faceParams.setAnakinTrackCorePath3(configLevel.mTrackParam3);
            faceParams.setTrackingSmoothAlpha(Float.parseFloat(configLevel.mTrackingSmoothAlpha));
            faceParams.setTrackingSmoothThreshold(Float.parseFloat(configLevel.mTrackingSmoothThreshold));
            faceParams.setTrackingMouthThreshold(Float.parseFloat(configLevel.mTrackingMouthThreshold));
        }
        return faceParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRenderFaceHandle(long j) {
        IARRenderer renderer = getRenderer();
        if (j <= 0 || renderer == null || getEnabledAbilitys() == null) {
            return;
        }
        try {
            if (getEnabledAbilitys().size() > 0) {
                renderer.setAlgoHandleData(j, getEnabledAbilitys().get(0));
            }
        } catch (IndexOutOfBoundsException unused) {
            ARLog.m20419e(TAG, "updateRenderFaceHandle IndexOutOfBoundsException!!!!");
        } catch (NullPointerException unused2) {
            ARLog.m20419e(TAG, "updateRenderFaceHandle NullPointerException!!!!");
        }
        onAlgoHandleDestory(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkMakeupAbility() {
        if (this.mFaceDefaultConfig.isNeedCheckMakeup()) {
            setMakeupEnable(true);
            this.mFaceDefaultConfig.setNeedCheckMakeup(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTriggerFired(FaceResultModel faceResultModel) {
        if (faceResultModel.getFaceAlgoData() == null || faceResultModel.getFaceAlgoData().getFaceFrame() == null) {
            return;
        }
        FaceFrame faceFrame = faceResultModel.getFaceAlgoData().getFaceFrame();
        if (faceFrame.getTriggersList() == null || faceFrame.getTriggersList().size() <= 0) {
            return;
        }
        for (String[] strArr : faceFrame.getTriggersList()) {
            if (strArr != null && strArr.length > 0) {
                for (String str : strArr) {
                    String str2 = this.currentTrigger;
                    if (str2 != null && str2.contains(str)) {
                        FaceListener faceListener = this.mFaceListener;
                        if (faceListener != null) {
                            faceListener.onTriggerFired(str);
                        }
                        StatisticApi.onEvent("event_face_expression");
                    }
                }
            }
        }
    }

    public void configSyncStatus(boolean z) {
        this.mFaceDefaultConfig.setEnableSyncRender(z);
        FaceDetector faceDetector = this.mFaceDetector;
        if (faceDetector != null) {
            faceDetector.setSyncStatus(z);
        }
        setDetectSync(z);
        if (getRenderer() != null) {
            getRenderer().enableSyncFaceLandmark(z);
        }
        this.mFaceDefaultConfig.setNeedCheckMakeup(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFaceNumber(FaceResultModel faceResultModel) {
        if (faceResultModel.getFaceAlgoData() == null || faceResultModel.getFaceAlgoData().getFaceFrame() == null) {
            return;
        }
        FaceFrame faceFrame = faceResultModel.getFaceAlgoData().getFaceFrame();
        if (faceFrame.getTrackedPointsList() != null && faceFrame.getTrackedPointsList().size() > 0) {
            FAUPoint2D[] fAUPoint2DArr = faceFrame.getTrackedPointsList().get(0);
            int size = faceFrame.getTrackedPointsList().size();
            if (TextUtils.isEmpty(this.mCurrentSticker) || fAUPoint2DArr.length <= 0) {
                return;
            }
            if (faceFrame.getFaceIDList() != null && this.mDetectedFaceNum != size) {
                statisticFaceShowHide(faceFrame.getFaceIDList());
            }
            this.mDetectedFaceNum = size;
            return;
        }
        this.mDetectedFaceNum = 0;
        if (TextUtils.isEmpty(this.mCurrentSticker) || this.mLastFaceIDList == null) {
            return;
        }
        statisticFaceShowHide(null);
    }

    private void statisticFaceShowHide(int[] iArr) {
        int[] iArr2;
        try {
            iArr2 = FaceUtil.getDiffList(this.mLastFaceIDList, iArr);
        } catch (Exception e) {
            e.printStackTrace();
            iArr2 = null;
        }
        int i = 0;
        if (iArr2 != null && iArr != null && iArr.length > this.mDetectedFaceNum) {
            int length = iArr2.length;
            while (i < length) {
                int i2 = iArr2[i];
                HashMap hashMap = new HashMap();
                hashMap.put("param_algo_faceid", String.valueOf(i2));
                StatisticApi.onEvent("event_face_masks_on", hashMap);
                i++;
            }
        } else if (iArr2 != null && (iArr == null || iArr.length < this.mDetectedFaceNum)) {
            int length2 = iArr2.length;
            while (i < length2) {
                int i3 = iArr2[i];
                HashMap hashMap2 = new HashMap();
                hashMap2.put("param_algo_faceid", String.valueOf(i3));
                StatisticApi.onEvent("event_face_masks_off", hashMap2);
                i++;
            }
        }
        this.mLastFaceIDList = iArr;
    }

    private float calculateFov() {
        int i;
        if (this.mInputWidth == 0 || this.mInputHeight == 0) {
            return 56.144978f;
        }
        int i2 = this.mInputDegree;
        if (i2 == 90 || i2 == 270) {
            i = this.mInputWidth;
        } else {
            i = this.mInputHeight;
        }
        return (float) (((Math.atan2(i * 0.5f, Math.max(this.mInputWidth, this.mInputHeight) * 0.94375f) * 2.0d) * 180.0d) / 3.141592653589793d);
    }

    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v8 */
    private void setMakeupEnable(boolean z) {
        ARFilterManager filterManager = getFilterManager();
        if (filterManager != null) {
            ?? r3 = (z && this.mFaceDefaultConfig.isEnableSyncRender() && getEnabledAbilitys().contains("ability_makeup_filter")) ? 1 : 0;
            filterManager.updateAbilityState(FilterNode.makeupFilter, r3);
            filterManager.updateFilter(FilterParam.MakeupFilter.beautyMakeupFilter, Integer.valueOf((int) r3));
            filterManager.callbackFilterStates();
        }
    }
}
