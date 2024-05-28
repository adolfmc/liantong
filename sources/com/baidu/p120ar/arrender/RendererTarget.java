package com.baidu.p120ar.arrender;

import android.view.Surface;
import com.baidu.p120ar.DuMixOutput;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.RendererTarget */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RendererTarget {
    private DuMixOutput mDuMixOutput;
    private Surface mSurface;
    private String mSurfaceHandle;

    public RendererTarget(DuMixOutput duMixOutput) {
        this.mDuMixOutput = duMixOutput;
    }

    public DuMixOutput getDuMixOutput() {
        return this.mDuMixOutput;
    }

    public void setDuMixOutput(DuMixOutput duMixOutput) {
        this.mDuMixOutput = duMixOutput;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void setSurface(Surface surface) {
        this.mSurface = surface;
    }

    public String getSurfaceHandle() {
        return this.mSurfaceHandle;
    }

    public void setSurfaceHandle(String str) {
        this.mSurfaceHandle = str;
    }
}
