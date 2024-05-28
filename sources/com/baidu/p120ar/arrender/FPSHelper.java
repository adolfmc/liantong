package com.baidu.p120ar.arrender;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.FPSHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FPSHelper {
    private static final int ONE_SECONED_MS = 1000;
    private int mFpsLimit = 0;
    private long mFpsStart = 0;
    private int mFrameDur;

    public FPSHelper(int i) {
        this.mFrameDur = 33;
        if (i > 0) {
            this.mFrameDur = 1000 / i;
        }
    }

    public boolean needDraw() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mFpsStart == 0) {
            this.mFpsStart = currentTimeMillis;
        }
        long j = currentTimeMillis % 1000;
        if (currentTimeMillis / 1000 != this.mFpsStart / 1000) {
            this.mFpsStart = currentTimeMillis;
            this.mFpsLimit = 0;
        }
        int i = this.mFpsLimit;
        if (this.mFrameDur * i < j) {
            this.mFpsLimit = i + 1;
            return true;
        }
        return false;
    }
}
