package com.baidu.cloud.mediaprocess.gles;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WindowSurface extends EglSurfaceBase {

    /* renamed from: d */
    public Surface f4832d;

    /* renamed from: e */
    public boolean f4833e;

    public WindowSurface(EglCore eglCore, SurfaceTexture surfaceTexture) {
        super(eglCore);
        createWindowSurface(surfaceTexture);
    }

    public WindowSurface(EglCore eglCore, Surface surface, boolean z) {
        super(eglCore);
        createWindowSurface(surface);
        this.f4832d = surface;
        this.f4833e = z;
    }

    public void recreate(EglCore eglCore) {
        Surface surface = this.f4832d;
        if (surface == null) {
            throw new RuntimeException("not yet implemented for SurfaceTexture");
        }
        this.mEglCore = eglCore;
        createWindowSurface(surface);
    }

    public void release() {
        releaseEglSurface();
        Surface surface = this.f4832d;
        if (surface != null) {
            if (this.f4833e) {
                surface.release();
            }
            this.f4832d = null;
        }
    }
}
