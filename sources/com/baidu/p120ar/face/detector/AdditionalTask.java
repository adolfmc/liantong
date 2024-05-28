package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.async.ARTask;
import com.baidu.p120ar.face.FaceUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.AdditionalTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AdditionalTask extends ARTask {
    private static final String TAG = AnimateTask.class.getSimpleName();
    private AnimateJniExecutor animateJniExecutor;
    private FaceResultModel mFaceResultModel;
    private String threadTag = "additional_thread";

    public AdditionalTask(AnimateJniExecutor animateJniExecutor) {
        this.animateJniExecutor = animateJniExecutor;
        setPriority(10);
    }

    public AdditionalTask(FaceResultModel faceResultModel) {
        this.mFaceResultModel = faceResultModel;
        setPriority(10);
    }

    @Override // com.baidu.p120ar.async.ARTask
    public String getTag() {
        return this.threadTag;
    }

    public void setThreadTag(String str) {
        this.threadTag = str;
    }

    @Override // com.baidu.p120ar.async.ARTask
    public FaceResultModel execute() {
        FaceResultModel faceResultModel = this.mFaceResultModel;
        return faceResultModel != null ? faceResultModel : FaceUtil.formFaceModel(this.animateJniExecutor.faceAlgoData, this.animateJniExecutor.costTime, this.animateJniExecutor.faceHandle, this.animateJniExecutor.dataHandle, this.animateJniExecutor.cameraData, this.animateJniExecutor.timestamp, this.animateJniExecutor.frontCamera);
    }
}
