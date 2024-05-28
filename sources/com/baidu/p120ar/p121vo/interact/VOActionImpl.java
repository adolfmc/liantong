package com.baidu.p120ar.p121vo.interact;

import android.text.TextUtils;
import com.baidu.p120ar.arplay.representation.Matrix;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.arrender.RenderCameraData;
import com.baidu.p120ar.p121vo.IEngineMsgHandler;
import com.baidu.p120ar.p121vo.caseconfig.VOConfig;
import com.baidu.p120ar.p121vo.detector.IVOAlgoInteraction;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.interact.VOActionImpl */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOActionImpl implements IVOAction {
    private static final String DEFAULT_SLAM_MODEL_ID = "slam_model_1";
    private IVOAlgoInteraction mAlgo;
    private VOConfig mConfig;
    private IEngineMsgHandler mEngineMsgHandler;
    private boolean mIsFirstUpdate = true;
    private int mNeedTransformWorldCoordTag = 0;
    private int mPreviewHeight;
    private int mPreviewWidth;
    private IARRenderer mRenderer;
    private volatile Runnable mRunAtUpdate;

    public VOActionImpl(IARRenderer iARRenderer, VOConfig vOConfig, IVOAlgoInteraction iVOAlgoInteraction, IEngineMsgHandler iEngineMsgHandler) {
        this.mRenderer = iARRenderer;
        this.mConfig = vOConfig;
        this.mAlgo = iVOAlgoInteraction;
        this.mEngineMsgHandler = iEngineMsgHandler;
    }

    public void setPreviewSize(int i, int i2) {
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public String getVOId(VOConfig vOConfig) {
        return (vOConfig == null || TextUtils.isEmpty(vOConfig.getId())) ? "slam_model_1" : vOConfig.getId();
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void showGuideDown() {
        sendSlamPlaceGuideToLua(1, 0);
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void showGuideUp() {
        sendSlamPlaceGuideToLua(1, 1);
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void hideGuide() {
        sendSlamPlaceGuideToLua(0, 1);
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void enableOffScreenGuide() {
        this.mRenderer.setOffScreenGuideWork(true);
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void updateRT(float[] fArr) {
        if (this.mRenderer == null || fArr == null || fArr.length != 16) {
            return;
        }
        if (this.mRunAtUpdate != null) {
            this.mRunAtUpdate.run();
            this.mRunAtUpdate = null;
        }
        this.mRenderer.setEnvironmentDataPipKV("slam", fArr);
        if (this.mIsFirstUpdate) {
            this.mIsFirstUpdate = false;
            this.mEngineMsgHandler.sendMsg2Engine(7001, null);
        }
        if (isNeedTransformWorldCoord()) {
            fArr = transformWorldCoordinate(fArr);
        }
        RenderCameraData renderCameraData = new RenderCameraData();
        renderCameraData.setAbilityName("ability_vo");
        renderCameraData.setMatrix(fArr);
        this.mRenderer.updateRenderCameraData(renderCameraData);
    }

    private boolean isNeedTransformWorldCoord() {
        if (this.mNeedTransformWorldCoordTag == 0) {
            this.mNeedTransformWorldCoordTag = this.mRenderer.isDriverdByARPVersion() ? 1 : -1;
        }
        return this.mNeedTransformWorldCoordTag == 1;
    }

    private float[] transformWorldCoordinate(float[] fArr) {
        if (fArr == null || fArr.length != 16) {
            return null;
        }
        Matrixf4x4 matrixf4x4 = new Matrixf4x4();
        Matrixf4x4 matrixf4x42 = new Matrixf4x4();
        Matrix.invertM(matrixf4x4.getMatrix(), 0, fArr, 0);
        matrixf4x42.setMatrixValues(matrixf4x4.getMatrix());
        Quaternion quaternion = new Quaternion();
        quaternion.setAxisAngle(new Vector3f(1.0f, 0.0f, 0.0f), -90.0f);
        Matrix.multiplyMM(matrixf4x4.getMatrix(), quaternion.getMatrix4x4().getMatrix(), matrixf4x42.getMatrix());
        Matrixf4x4 matrixf4x43 = new Matrixf4x4();
        Matrix.invertM(matrixf4x43.getMatrix(), 0, matrixf4x4.getMatrix(), 0);
        return matrixf4x43.getMatrix();
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public int[] getXYByNormalizedCoordinate(float f, float f2) {
        int[] iArr = new int[2];
        if (this.mPreviewWidth != 0) {
            iArr[1] = this.mPreviewHeight - ((int) (f * 720.0f));
            iArr[0] = (int) (f2 * 1280.0f);
        } else {
            iArr[1] = 640;
            iArr[0] = 360;
        }
        return iArr;
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void insertModel(String str, int i, int i2, float[] fArr, float f) {
        IVOAlgoInteraction iVOAlgoInteraction = this.mAlgo;
        if (iVOAlgoInteraction != null) {
            iVOAlgoInteraction.insertModel(str, i, i2, fArr, f);
        }
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void handleGestureInteraction(GestureInteractionInfo gestureInteractionInfo) {
        float f;
        float f2;
        if (gestureInteractionInfo == null) {
            return;
        }
        if (gestureInteractionInfo.isFromLua) {
            int[] xYByNormalizedCoordinate = getXYByNormalizedCoordinate(gestureInteractionInfo.f4097x, gestureInteractionInfo.f4098y);
            f = xYByNormalizedCoordinate[0];
            f2 = xYByNormalizedCoordinate[1];
        } else {
            f = gestureInteractionInfo.f4097x;
            f2 = gestureInteractionInfo.f4098y;
        }
        String vOId = getVOId(this.mConfig);
        if (gestureInteractionInfo.type == 3) {
            this.mAlgo.removeAllModel();
            insertModel(vOId, (int) f, (int) f2, null, (int) gestureInteractionInfo.distance);
            this.mRunAtUpdate = new Runnable() { // from class: com.baidu.ar.vo.interact.VOActionImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (VOActionImpl.this.mRenderer != null) {
                        VOActionImpl.this.mRenderer.sceneRotateToCamera();
                    }
                }
            };
        } else if (gestureInteractionInfo.type == 2) {
            this.mAlgo.removeAllModel();
            insertModel(vOId, (int) f, (int) f2, null, (int) gestureInteractionInfo.distance);
            this.mRunAtUpdate = new Runnable() { // from class: com.baidu.ar.vo.interact.VOActionImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    if (VOActionImpl.this.mRenderer != null) {
                        VOActionImpl.this.mRenderer.sceneWorldPositionToOrigin();
                    }
                }
            };
        }
    }

    @Override // com.baidu.p120ar.p121vo.interact.IVOAction
    public void release() {
        this.mAlgo = null;
        this.mRunAtUpdate = null;
        this.mRenderer = null;
        this.mEngineMsgHandler = null;
    }

    private void sendSlamPlaceGuideToLua(int i, int i2) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 4101);
        hashMap.put("guideDirection", Integer.valueOf(i2));
        hashMap.put("switchGuide", Integer.valueOf(i));
        this.mEngineMsgHandler.sendMsg2Lua(hashMap);
    }
}
