package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.face.algo.FaceAlgoData;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceAlgoResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceAlgoResult {
    private ByteBuffer cameraData;
    private long dataHandle;
    private FaceAlgoData faceAlgoData;
    private long handle;
    private boolean isFrontCamera;
    private long timestamp;
    private long trackStartTime;

    public FaceAlgoResult(FaceAlgoData faceAlgoData, long j, long j2, long j3, ByteBuffer byteBuffer, boolean z) {
        this.faceAlgoData = faceAlgoData;
        this.handle = j;
        this.timestamp = j2;
        this.trackStartTime = j3;
        this.cameraData = byteBuffer;
        this.isFrontCamera = z;
    }

    public FaceAlgoResult(FaceAlgoData faceAlgoData, long j, long j2, long j3, ByteBuffer byteBuffer, boolean z, long j4) {
        this.faceAlgoData = faceAlgoData;
        this.handle = j;
        this.timestamp = j2;
        this.trackStartTime = j3;
        this.cameraData = byteBuffer;
        this.isFrontCamera = z;
        this.dataHandle = j4;
    }

    public ByteBuffer getCameraData() {
        return this.cameraData;
    }

    public FaceAlgoData getFaceAlgoData() {
        return this.faceAlgoData;
    }

    public long getHandle() {
        return this.handle;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public long getTrackStartTime() {
        return this.trackStartTime;
    }

    public boolean isFrontCamera() {
        return this.isFrontCamera;
    }

    public long getDataHandle() {
        return this.dataHandle;
    }
}
