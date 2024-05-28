package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.async.ARTask;
import com.baidu.p120ar.face.FaceUtil;
import com.baidu.p120ar.face.algo.FaceAlgoData;
import com.baidu.p120ar.face.detector.FaceDetector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.TrackTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TrackTask extends ARTask<TrackJniExecutor> {
    private AbstractJniExecutor abstractJniExecutor;
    private FaceDetector.FaceDetectorCallback faceDetectorCallback;
    private String threadTag = "face_track";

    public TrackTask(DetectJniExecutor detectJniExecutor) {
        this.abstractJniExecutor = detectJniExecutor;
        setPriority(10);
    }

    public TrackTask(TrackJniExecutor trackJniExecutor) {
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.baidu.p120ar.async.ARTask
    public TrackJniExecutor execute() {
        TrackJniExecutor trackJniExecutor = new TrackJniExecutor(this.abstractJniExecutor.cameraData);
        trackJniExecutor.setFaceAlgoData(this.abstractJniExecutor.faceAlgoData);
        trackJniExecutor.setFaceHandle(this.abstractJniExecutor.faceHandle);
        trackJniExecutor.setDataHandle(this.abstractJniExecutor.dataHandle);
        trackJniExecutor.setTimestamp(this.abstractJniExecutor.timestamp);
        trackJniExecutor.setFrontCamera(this.abstractJniExecutor.frontCamera);
        trackJniExecutor.setFaceAlgoLoader(this.abstractJniExecutor.faceAlgoLoader);
        trackJniExecutor.threadTag = this.threadTag;
        trackJniExecutor.configure();
        trackJniExecutor.executeJni();
        passDetectIfOverFourFace(trackJniExecutor.faceAlgoData);
        if (trackJniExecutor.faceAlgoData != null) {
            return trackJniExecutor;
        }
        callback(this.abstractJniExecutor);
        return null;
    }

    private void passDetectIfOverFourFace(FaceAlgoData faceAlgoData) {
        if (faceAlgoData != null && faceAlgoData.getFaceFrame() != null && faceAlgoData.getFaceFrame().getProcessResult() == 200 && faceAlgoData.getFaceFrame().getTrackedPointsList().size() >= this.abstractJniExecutor.getFaceAlgoLoader().getDetectSkiper().getmTargetDetectFaceNum()) {
            this.abstractJniExecutor.getFaceAlgoLoader().getDetectSkiper().notifyFaceNumExceed();
        } else {
            this.abstractJniExecutor.getFaceAlgoLoader().getDetectSkiper().notifyFaceNumNormal();
        }
    }

    protected void callback(AbstractJniExecutor abstractJniExecutor) {
        this.faceDetectorCallback.onDetected(FaceUtil.formFaceModel(abstractJniExecutor.faceAlgoData, abstractJniExecutor.costTime, abstractJniExecutor.faceHandle, abstractJniExecutor.dataHandle, abstractJniExecutor.cameraData, abstractJniExecutor.timestamp, abstractJniExecutor.frontCamera));
    }

    public void setFaceDetectorCallback(FaceDetector.FaceDetectorCallback faceDetectorCallback) {
        this.faceDetectorCallback = faceDetectorCallback;
    }
}
