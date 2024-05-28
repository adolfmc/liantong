package com.baidu.p120ar.seg;

import android.os.Bundle;
import com.baidu.p120ar.armdl.detector.MdlDetector;
import com.baidu.p120ar.armdl.task.MdlDestroyTask;
import com.baidu.p120ar.armdl.task.MdlInitTask;
import com.baidu.p120ar.armdl.task.MdlPredictTask;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.arrender.RenderNodeData;
import com.baidu.p120ar.async.AsyncTaskManager;
import com.baidu.p120ar.bus.CallBack;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.mdl.MdlConfig;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.seg.SegDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class SegDetector extends MdlDetector {
    private static final String TAG = "SegDetector";
    protected JSONObject mAbilityScheme;
    protected boolean mIsFrontCamera;
    protected int mOrientationDegree;
    protected float mMaskThreshold = 0.0f;
    protected boolean mEnableSyncRender = true;
    protected boolean mUsePaasHandle = true;

    abstract SegResult executeSegAlgo(FramePixels framePixels);

    abstract String getAbilityName();

    abstract int getAlgoType();

    abstract int getSegInputOrientation();

    abstract int initSegAlgo(MdlConfig mdlConfig);

    abstract void releaseSegAlgo();

    abstract void updateReadParams();

    public SegDetector() {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mReadParams = new PixelReadParams(PixelType.BGR);
        updateReadParams();
    }

    public void setMaskThreshold(float f) {
        this.mMaskThreshold = f;
    }

    public void setEnableSyncRender(boolean z) {
        this.mEnableSyncRender = z;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(Bundle bundle) {
        return new MdlInitTask(getMdlType()) { // from class: com.baidu.ar.seg.SegDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "seg";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                return SegDetector.this.initSegAlgo(mdlConfig);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(getMdlType()) { // from class: com.baidu.ar.seg.SegDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "seg";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                SegDetector.this.releaseSegAlgo();
                return 0;
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return new MdlPredictTask(getMdlType(), framePixels) { // from class: com.baidu.ar.seg.SegDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "seg";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            public SegResult predict(FramePixels framePixels2) {
                SegDetector.this.mIsFrontCamera = framePixels2.isFrontCamera();
                SegDetector.this.mOrientationDegree = framePixels2.getOrientation().getDegree();
                return SegDetector.this.executeSegAlgo(framePixels2);
            }
        };
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    @CallBack
    public void onMdlResult(SegResult segResult) {
        int width = segResult.getModel().getWidth();
        int height = segResult.getModel().getHeight();
        byte[] result = segResult.getModel().getResult();
        int orientation = segResult.getModel().getOrientation();
        if (width > 0 && height > 0 && result != null && result.length > 0) {
            String abilityName = getAbilityName();
            if (getMdlType() == 4 && (orientation == 0 || orientation == 180 || orientation == 2)) {
                height = width;
                width = height;
            }
            RenderNodeData renderNodeData = new RenderNodeData();
            renderNodeData.setAbilityName(abilityName);
            renderNodeData.setWidth(width);
            renderNodeData.setHeight(height);
            renderNodeData.setMaskData(result);
            segResult.setResultData(renderNodeData);
        }
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(segResult);
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onSetup(new ResultModel(getName(), true, getAlgoType()));
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onRelease(new ResultModel(getName(), true, getAlgoType()));
        }
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }

    public void setAbilityScheme(JSONObject jSONObject) {
        this.mAbilityScheme = jSONObject;
    }
}
