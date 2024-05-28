package com.baidu.p120ar.face.detector;

import android.os.SystemClock;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.face.algo.FaceAlgoData;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.AbstractJniExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractJniExecutor {
    protected long agloBeginTimestamp;
    protected ByteBuffer cameraData;
    protected long costTime;
    protected long dataHandle;
    protected FaceAlgoData faceAlgoData;
    protected FaceAlgoLoader faceAlgoLoader;
    protected long faceHandle;
    protected FacePerfStaticUtil facePerfStaticUtil;
    protected boolean frontCamera;
    protected String threadTag = "";
    protected long timestamp;

    abstract void invokeJni();

    public AbstractJniExecutor() {
    }

    public AbstractJniExecutor(ByteBuffer byteBuffer) {
        this.cameraData = byteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineRecordVar() {
        this.agloBeginTimestamp = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordExeTime() {
        this.costTime = SystemClock.elapsedRealtime() - this.agloBeginTimestamp;
    }

    public void executeJni() {
        if ("additional_thread".equals(this.threadTag)) {
            ARMdlManager.getInstance().setMdlState(11, true);
        }
        defineRecordVar();
        invokeJni();
        recordExeTime();
        if ("additional_thread".equals(this.threadTag)) {
            ARMdlManager.getInstance().setMdlState(11, false);
        }
    }

    public void setFaceAlgoData(FaceAlgoData faceAlgoData) {
        this.faceAlgoData = faceAlgoData;
    }

    public long getFaceHandle() {
        return this.faceHandle;
    }

    public void setFaceHandle(long j) {
        this.faceHandle = j;
    }

    public FaceAlgoLoader getFaceAlgoLoader() {
        return this.faceAlgoLoader;
    }

    public void setFaceAlgoLoader(FaceAlgoLoader faceAlgoLoader) {
        this.faceAlgoLoader = faceAlgoLoader;
    }

    public void configure() {
        this.facePerfStaticUtil = this.faceAlgoLoader.getmFacePerfStaticUtil();
    }
}
