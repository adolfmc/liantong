package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.async.ARTask;
import com.baidu.p120ar.face.FaceUtil;
import com.baidu.p120ar.face.detector.FaceDetector;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.DetectTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectTask extends ARTask<DetectJniExecutor> {
    private ByteBuffer cameraData;
    private FaceDetector.FaceDetectorCallback faceDetectorCallback;
    private boolean frontCamera;
    private int height;
    private boolean mEnableSyncRender;
    private FaceAlgoLoader mFaceAlgoLoader;
    private int orientation;
    private String threadTag = "face_detect";
    private long timestamp;
    private int width;

    public DetectTask(ByteBuffer byteBuffer, int i, int i2, long j, boolean z, int i3, boolean z2) {
        this.cameraData = byteBuffer;
        this.width = i;
        this.height = i2;
        this.timestamp = j;
        this.frontCamera = z;
        this.orientation = i3;
        this.mEnableSyncRender = z2;
        setPriority(10);
    }

    public void setThreadTag(String str) {
        this.threadTag = str;
    }

    @Override // com.baidu.p120ar.async.ARTask
    public String getTag() {
        return this.threadTag;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.baidu.p120ar.async.ARTask
    public DetectJniExecutor execute() {
        if (this.mFaceAlgoLoader.checkNotPrepared(this.frontCamera, this.orientation)) {
            return null;
        }
        ARLog.m20421d(getTag(), "detect_frame track task executed");
        CreateJniExecutor executeCreateJni = executeCreateJni();
        if (executeCreateJni == null) {
            ARLog.m20419e(getTag(), "create faceHandle failed");
            callback(null);
            return null;
        }
        DetectJniExecutor detectJniExecutor = new DetectJniExecutor(this.cameraData);
        detectJniExecutor.setFaceAlgoLoader(this.mFaceAlgoLoader);
        detectJniExecutor.setFaceAlgoData(executeCreateJni.faceAlgoData);
        detectJniExecutor.setFaceHandle(executeCreateJni.getFaceHandle());
        detectJniExecutor.setDataHandle(executeCreateJni.getDataHandle());
        detectJniExecutor.setFrontCamera(this.frontCamera);
        detectJniExecutor.setTimestamp(this.timestamp);
        detectJniExecutor.threadTag = this.threadTag;
        detectJniExecutor.configure();
        if (this.mFaceAlgoLoader.getDetectSkiper().checkShouldDetect()) {
            ARLog.m20413w("hjm", "check_skip detect");
            detectJniExecutor.executeJni();
            if (this.mFaceAlgoLoader.getDetectSkiper().checkNoFaceInData(detectJniExecutor)) {
                callback(detectJniExecutor);
                return null;
            }
        } else {
            ARLog.m20413w("hjm", "check_skip skip");
        }
        return detectJniExecutor;
    }

    private CreateJniExecutor executeCreateJni() {
        CreateJniExecutor createJniExecutor = new CreateJniExecutor(this.cameraData);
        createJniExecutor.setFaceAlgoLoader(this.mFaceAlgoLoader);
        createJniExecutor.configure(this.width, this.height, this.frontCamera, this.orientation, this.mEnableSyncRender, this.timestamp);
        createJniExecutor.executeJni();
        if (createJniExecutor.faceHandle <= 0) {
            String tag = getTag();
            ARLog.m20419e(tag, "create handle illegal:" + createJniExecutor.faceHandle);
            return null;
        }
        return createJniExecutor;
    }

    protected void callback(DetectJniExecutor detectJniExecutor) {
        if (detectJniExecutor == null) {
            this.faceDetectorCallback.onDetected(null);
        }
        this.faceDetectorCallback.onDetected(FaceUtil.formFaceModel(detectJniExecutor.faceAlgoData, detectJniExecutor.costTime, detectJniExecutor.faceHandle, detectJniExecutor.dataHandle, detectJniExecutor.cameraData, this.timestamp, this.frontCamera));
    }

    public void setCallback(FaceDetector.FaceDetectorCallback faceDetectorCallback) {
        this.faceDetectorCallback = faceDetectorCallback;
    }

    public void setFaceAlgoLoader(FaceAlgoLoader faceAlgoLoader) {
        this.mFaceAlgoLoader = faceAlgoLoader;
    }
}
