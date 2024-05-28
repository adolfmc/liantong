package com.baidu.cloud.media.player.render.p134a;

import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WindowSurface extends EglSurfaceBase {

    /* renamed from: b */
    private Surface f4399b;

    /* renamed from: c */
    private boolean f4400c;

    public WindowSurface(EglCore eglCore, Surface surface, boolean z) {
        super(eglCore);
        m20044a(surface);
        this.f4399b = surface;
        this.f4400c = z;
    }

    /* renamed from: d */
    public void m20032d() {
        m20046a();
        Surface surface = this.f4399b;
        if (surface != null) {
            if (this.f4400c) {
                surface.release();
            }
            this.f4399b = null;
        }
    }
}
