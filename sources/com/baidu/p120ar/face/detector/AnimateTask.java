package com.baidu.p120ar.face.detector;

import android.util.Log;
import com.baidu.p120ar.async.ARTask;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.AnimateTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AnimateTask extends ARTask {
    private static final String TAG = "AnimateTask";
    AbstractJniExecutor abstractJniExecutor;
    private String threadTag = "face_animate";

    public AnimateTask(TrackJniExecutor trackJniExecutor) {
        this.abstractJniExecutor = trackJniExecutor;
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
    public AnimateJniExecutor execute() {
        Log.d(TAG, "detect_frame animate task before execute");
        AnimateJniExecutor animateJniExecutor = new AnimateJniExecutor();
        animateJniExecutor.setFaceAlgoData(this.abstractJniExecutor.faceAlgoData);
        animateJniExecutor.setFaceHandle(this.abstractJniExecutor.faceHandle);
        animateJniExecutor.dataHandle = this.abstractJniExecutor.dataHandle;
        animateJniExecutor.setFaceAlgoLoader(this.abstractJniExecutor.faceAlgoLoader);
        animateJniExecutor.timestamp = this.abstractJniExecutor.timestamp;
        animateJniExecutor.frontCamera = this.abstractJniExecutor.frontCamera;
        animateJniExecutor.cameraData = this.abstractJniExecutor.cameraData;
        animateJniExecutor.threadTag = this.threadTag;
        animateJniExecutor.configure();
        animateJniExecutor.executeJni();
        Log.d(TAG, "detect_frame animate task after execute");
        animateJniExecutor.faceAlgoLoader.getDetectSkiper().setmCurrentFaceNum(animateJniExecutor.getCurrentFaceNum());
        if (animateJniExecutor.faceAlgoData != null) {
            return animateJniExecutor;
        }
        return null;
    }
}
