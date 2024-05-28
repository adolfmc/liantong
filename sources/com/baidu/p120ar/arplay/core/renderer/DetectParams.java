package com.baidu.p120ar.arplay.core.renderer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.renderer.DetectParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class DetectParams {
    private long mFrameBufferAddr;
    private long mTimestamp;

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public void setFrameBufferAddr(long j) {
        this.mFrameBufferAddr = j;
    }

    public long getFrameBufferAddr() {
        return this.mFrameBufferAddr;
    }
}
