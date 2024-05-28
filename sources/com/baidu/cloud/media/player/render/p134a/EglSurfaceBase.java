package com.baidu.cloud.media.player.render.p134a;

import android.opengl.EGL14;
import android.opengl.EGLSurface;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EglSurfaceBase {

    /* renamed from: a */
    protected EglCore f4377a;

    /* renamed from: b */
    private EGLSurface f4378b = EGL14.EGL_NO_SURFACE;

    /* renamed from: c */
    private int f4379c = -1;

    /* renamed from: d */
    private int f4380d = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public EglSurfaceBase(EglCore eglCore) {
        this.f4377a = eglCore;
    }

    /* renamed from: a */
    public void m20044a(Object obj) {
        if (this.f4378b != EGL14.EGL_NO_SURFACE) {
            throw new IllegalStateException("surface already created");
        }
        this.f4378b = this.f4377a.m20050a(obj);
    }

    /* renamed from: a */
    public void m20046a() {
        this.f4377a.m20052a(this.f4378b);
        this.f4378b = EGL14.EGL_NO_SURFACE;
        this.f4380d = -1;
        this.f4379c = -1;
    }

    /* renamed from: b */
    public void m20043b() {
        this.f4377a.m20048b(this.f4378b);
    }

    /* renamed from: c */
    public boolean m20042c() {
        boolean m20047c = this.f4377a.m20047c(this.f4378b);
        if (!m20047c) {
            Log.d("Grafika", "WARNING: swapBuffers() failed");
        }
        return m20047c;
    }

    /* renamed from: a */
    public void m20045a(long j) {
        this.f4377a.m20051a(this.f4378b, j);
    }
}
