package com.baidu.p120ar.p121vo;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.arplay.core.engine.ARPDataInteraction;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.imu.Coordinate;
import com.baidu.p120ar.imu.ImuInfo;
import com.baidu.p120ar.imu.ImuListener;
import com.baidu.p120ar.imu.ImuParams;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.p121vo.caseconfig.ConfigReader;
import com.baidu.p120ar.p121vo.caseconfig.VOConfig;
import com.baidu.p120ar.p121vo.detector.VOAlgoController;
import com.baidu.p120ar.p121vo.detector.VOAlgoSetting;
import com.baidu.p120ar.p121vo.detector.VODetector;
import com.baidu.p120ar.p121vo.detector.VOResult;
import com.baidu.p120ar.p121vo.interact.GestureInteractionInfo;
import com.baidu.p120ar.p121vo.interact.VOActionImpl;
import com.baidu.p120ar.utils.UiThreadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.VOAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOAR extends AbstractAR {
    private static final int ALGO_PREVIEW_HEIGHT = 720;
    private static final int ALGO_PREVIEW_WIDTH = 1280;
    private static final String TAG = "VOAR";
    private DetectorCallback mAlgoCallback;
    private VOAlgoController mAlgoController;
    private VOController mController;
    private VODetector mDetector;
    private EngineMsgListener mEngineMsgListener;
    private ImuInfo mImuInfo;
    private ImuListener mImuListener;
    private VOConfig mVOConfig;

    @Override // com.baidu.p120ar.AbstractAR
    public void onCaseDestroy() {
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        this.mVOConfig = ConfigReader.read(getRenderer().getCurrentCasePath());
        VOAlgoSetting createAlgoSetting = createAlgoSetting();
        this.mAlgoController = new VOAlgoController(createAlgoSetting);
        this.mDetector = new VODetector(this.mVOConfig, this.mAlgoController);
        this.mAlgoCallback = new DetectorCallback() { // from class: com.baidu.ar.vo.VOAR.1
            private int mFrameCount = 0;

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                this.mFrameCount = 0;
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (VOAR.this.mController == null || detectResult == null || !(detectResult instanceof VOResult)) {
                    return;
                }
                int i = this.mFrameCount;
                if (i >= 3) {
                    VOAR.this.mController.update((VOResult) detectResult, VOAR.this.getImuMatrix());
                    return;
                }
                this.mFrameCount = i + 1;
            }
        };
        this.mController = new VOController(createAction(createAlgoSetting), this.mVOConfig, this.mAlgoController, createAlgoSetting);
        this.mEngineMsgListener = createLuaListener();
        addEngineMsgListener(this.mEngineMsgListener);
    }

    private VOAlgoSetting createAlgoSetting() {
        VOAlgoSetting vOAlgoSetting = new VOAlgoSetting();
        vOAlgoSetting.frameWidth = 1280;
        vOAlgoSetting.frameHeight = 720;
        vOAlgoSetting.imuProvider = new VOAlgoSetting.ImuProvider() { // from class: com.baidu.ar.vo.VOAR.2
            private float angle;
            private float[] matrix;

            @Override // com.baidu.p120ar.p121vo.detector.VOAlgoSetting.ImuProvider
            public float[] getImuMatrix() {
                return VOAR.this.getImuMatrix();
            }

            @Override // com.baidu.p120ar.p121vo.detector.VOAlgoSetting.ImuProvider
            public float getAngle() {
                return VOAR.this.getImuAngle();
            }
        };
        return vOAlgoSetting;
    }

    private VOActionImpl createAction(VOAlgoSetting vOAlgoSetting) {
        VOActionImpl vOActionImpl = new VOActionImpl(getRenderer(), this.mVOConfig, this.mAlgoController, new IEngineMsgHandler() { // from class: com.baidu.ar.vo.VOAR.3
            @Override // com.baidu.p120ar.p121vo.IEngineMsgHandler
            public void sendMsg2Engine(int i, HashMap<String, Object> hashMap) {
                VOAR.this.sendMsg2Engine(i, hashMap);
            }

            @Override // com.baidu.p120ar.p121vo.IEngineMsgHandler
            public void sendMsg2Lua(HashMap<String, Object> hashMap) {
                VOAR.this.sendMsg2Lua(hashMap);
            }
        });
        vOActionImpl.setPreviewSize(vOAlgoSetting.frameWidth, vOAlgoSetting.frameHeight);
        return vOActionImpl;
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onCaseCreate(String str) {
        startImu();
        getRenderer().set3DModelVisible(true);
        getRenderer().setTouchEnable(true);
        getRenderer().setFieldOfView(56.144978f);
        getRenderer().initWorldAxis();
        getRenderer().setInteractionCallback(new ARPDataInteraction.IInteraction() { // from class: com.baidu.ar.vo.VOAR.4
            @Override // com.baidu.p120ar.arplay.core.engine.ARPDataInteraction.IInteraction
            public void onFinish(float f, float f2, float f3) {
                if (VOAR.this.mController != null) {
                    VOAR.this.mController.resetToOrigin(f, f2, f3);
                }
            }
        });
        addDetector(this.mDetector, this.mAlgoCallback);
    }

    private void releaseSelf() {
        removeDetector(this.mDetector);
        EngineMsgListener engineMsgListener = this.mEngineMsgListener;
        if (engineMsgListener != null) {
            removeEngineMsgListener(engineMsgListener);
            this.mEngineMsgListener = null;
        }
        ImuListener imuListener = this.mImuListener;
        if (imuListener != null) {
            removeImuListener(imuListener);
            this.mImuListener = null;
        }
        this.mDetector = null;
        this.mAlgoCallback = null;
        VOController vOController = this.mController;
        if (vOController != null) {
            vOController.release();
            this.mController = null;
        }
        VOAlgoController vOAlgoController = this.mAlgoController;
        if (vOAlgoController != null) {
            vOAlgoController.release();
            this.mAlgoController = null;
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        releaseSelf();
        super.release();
    }

    private EngineMsgListener createLuaListener() {
        return new EngineMsgListener() { // from class: com.baidu.ar.vo.VOAR.5
            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public List<Integer> getMsgTypesListened() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(401);
                arrayList.add(4100);
                arrayList.add(1901);
                return arrayList;
            }

            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public void onEngineMessage(final int i, final int i2, final HashMap<String, Object> hashMap) {
                UiThreadUtils.runOnUiThread(new Runnable() { // from class: com.baidu.ar.vo.VOAR.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VOAR.this.processLuaMessage(i, i2, hashMap);
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processLuaMessage(int i, int i2, HashMap<String, Object> hashMap) {
        VOController vOController = this.mController;
        if (vOController == null) {
            return;
        }
        if (i == 401) {
            if (vOController == null || hashMap == null || !(hashMap.get("app_type") instanceof String)) {
                return;
            }
            this.mController.setForbidden("None".equals((String) hashMap.get("app_type")));
        } else if (i != 1901) {
            if (i != 4100) {
                return;
            }
            GestureInteractionInfo parseGestureInteraction = parseGestureInteraction(hashMap);
            parseGestureInteraction.isFromLua = false;
            this.mController.handleGestureInteraction(parseGestureInteraction);
        } else if (hashMap != null) {
            int obj2Int = MsgParamsUtil.obj2Int(hashMap.get("id"), -1);
            if (4100 == obj2Int) {
                GestureInteractionInfo parseGestureInteraction2 = parseGestureInteraction(hashMap);
                parseGestureInteraction2.isFromLua = true;
                this.mController.handleGestureInteraction(parseGestureInteraction2);
            } else if (4200 == obj2Int) {
                this.mController.start();
            }
        }
    }

    private GestureInteractionInfo parseGestureInteraction(HashMap<String, Object> hashMap) {
        GestureInteractionInfo gestureInteractionInfo = new GestureInteractionInfo();
        gestureInteractionInfo.f4097x = ((Float) hashMap.get("x")).floatValue();
        gestureInteractionInfo.f4098y = ((Float) hashMap.get("y")).floatValue();
        gestureInteractionInfo.type = ((Integer) hashMap.get("type")).intValue();
        gestureInteractionInfo.distance = ((Float) hashMap.get("distance")).floatValue();
        gestureInteractionInfo.isFromLua = true;
        return gestureInteractionInfo;
    }

    private void startImu() {
        ImuParams imuParams = new ImuParams();
        imuParams.setCoordinate(Coordinate.WORLD);
        imuParams.setInitPosition(0);
        imuParams.setResumeOriginalPosition(false);
        imuParams.setNeedImuAngle(true);
        this.mImuListener = new ImuListener() { // from class: com.baidu.ar.vo.VOAR.6
            @Override // com.baidu.p120ar.imu.ImuListener
            public void onImuUpdate(ImuInfo imuInfo) {
                VOAR.this.mImuInfo = imuInfo;
            }
        };
        addImuListener(imuParams, this.mImuListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float[] getImuMatrix() {
        ImuInfo imuInfo = this.mImuInfo;
        if (imuInfo == null) {
            return null;
        }
        return imuInfo.getMatrix();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImuAngle() {
        ImuInfo imuInfo = this.mImuInfo;
        return (imuInfo == null ? null : Float.valueOf(imuInfo.getAngle())).floatValue();
    }
}
