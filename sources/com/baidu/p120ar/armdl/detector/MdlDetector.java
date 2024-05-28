package com.baidu.p120ar.armdl.detector;

import android.os.Bundle;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.armdl.task.MdlDestroyTask;
import com.baidu.p120ar.armdl.task.MdlInitTask;
import com.baidu.p120ar.armdl.task.MdlPredictTask;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.async.AsyncTaskManager;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.FrameDetector;
import com.baidu.p120ar.detector.ResultModel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.armdl.detector.MdlDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class MdlDetector extends FrameDetector {
    public static final int LANDSCAPE = 90;
    public static final int LANDSCAPE_REVERSE = -90;
    public static final int PORTRAIT = 0;
    public static final int PORTRAIT_REVERSE = 180;
    public AlgoHandleController mAlgoHandleController = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getMdlType();

    public abstract MdlDestroyTask onCreateDestroyTask();

    public abstract MdlInitTask onCreateInitTask(Bundle bundle);

    public abstract MdlPredictTask onMdlExecute(FramePixels framePixels);

    public void setAlgoHandleController(AlgoHandleController algoHandleController) {
        this.mAlgoHandleController = algoHandleController;
    }

    public void destroyHandle(long j) {
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.destroyHandle(j);
        }
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onSetup(new ResultModel(getName(), true));
        }
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean isDetectEnable() {
        int mdlType = getMdlType();
        if (ARMdlManager.getInstance().checkMdlInit(mdlType)) {
            return !ARMdlManager.getInstance().getMdlStateByType(mdlType);
        }
        return false;
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public boolean detectFrame(FramePixels framePixels) {
        MdlPredictTask onMdlExecute;
        if (!ARMdlManager.getInstance().checkMdlInit(getMdlType()) || framePixels == null || (onMdlExecute = onMdlExecute(framePixels)) == null) {
            return false;
        }
        return AsyncTaskManager.getInstance().postTask(onMdlExecute);
    }

    @Override // com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onRelease(new ResultModel(getName(), true));
        }
    }

    public final void enableMdl(Bundle bundle) {
        MdlInitTask onCreateInitTask = onCreateInitTask(bundle);
        if (onCreateInitTask == null) {
            return;
        }
        AsyncTaskManager.getInstance().postTask(onCreateInitTask);
    }

    public void disableMdl() {
        MdlDestroyTask onCreateDestroyTask = onCreateDestroyTask();
        if (onCreateDestroyTask == null) {
            return;
        }
        AsyncTaskManager.getInstance().removeTasks(onCreateDestroyTask.getTag());
        AsyncTaskManager.getInstance().postTask(onCreateDestroyTask);
    }
}
