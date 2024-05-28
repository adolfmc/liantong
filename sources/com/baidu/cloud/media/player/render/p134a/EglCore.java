package com.baidu.cloud.media.player.render.p134a;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.util.Log;
import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(18)
/* renamed from: com.baidu.cloud.media.player.render.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class EglCore {

    /* renamed from: a */
    private EGLDisplay f4373a;

    /* renamed from: b */
    private EGLContext f4374b;

    /* renamed from: c */
    private EGLConfig f4375c;

    /* renamed from: d */
    private int f4376d;

    public EglCore() {
        this(null, 0);
    }

    public EglCore(EGLContext eGLContext, int i) {
        EGLConfig m20053a;
        this.f4373a = EGL14.EGL_NO_DISPLAY;
        this.f4374b = EGL14.EGL_NO_CONTEXT;
        this.f4375c = null;
        this.f4376d = -1;
        if (this.f4373a != EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("EGL already set up");
        }
        eGLContext = eGLContext == null ? EGL14.EGL_NO_CONTEXT : eGLContext;
        this.f4373a = EGL14.eglGetDisplay(0);
        if (this.f4373a == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(this.f4373a, iArr, 0, iArr, 1)) {
            this.f4373a = null;
            throw new RuntimeException("unable to initialize EGL14");
        }
        if ((i & 2) != 0 && (m20053a = m20053a(i, 3)) != null) {
            EGLContext eglCreateContext = EGL14.eglCreateContext(this.f4373a, m20053a, eGLContext, new int[]{12440, 3, 12344}, 0);
            if (EGL14.eglGetError() == 12288) {
                this.f4375c = m20053a;
                this.f4374b = eglCreateContext;
                this.f4376d = 3;
            }
        }
        if (this.f4374b == EGL14.EGL_NO_CONTEXT) {
            EGLConfig m20053a2 = m20053a(i, 2);
            if (m20053a2 == null) {
                throw new RuntimeException("Unable to find a suitable EGLConfig");
            }
            EGLContext eglCreateContext2 = EGL14.eglCreateContext(this.f4373a, m20053a2, eGLContext, new int[]{12440, 2, 12344}, 0);
            m20049a("eglCreateContext");
            this.f4375c = m20053a2;
            this.f4374b = eglCreateContext2;
            this.f4376d = 2;
        }
        int[] iArr2 = new int[1];
        EGL14.eglQueryContext(this.f4373a, this.f4374b, 12440, iArr2, 0);
        Log.d("Grafika", "EGLContext created, client version " + iArr2[0]);
    }

    /* renamed from: a */
    private EGLConfig m20053a(int i, int i2) {
        int i3 = i2 >= 3 ? 68 : 4;
        int[] iArr = new int[13];
        iArr[0] = 12324;
        iArr[1] = 8;
        iArr[2] = 12323;
        iArr[3] = 8;
        iArr[4] = 12322;
        iArr[5] = 8;
        iArr[6] = 12321;
        iArr[7] = 8;
        iArr[8] = 12352;
        iArr[9] = i3;
        iArr[10] = 12344;
        iArr[11] = 0;
        iArr[12] = 12344;
        if ((i & 1) != 0) {
            iArr[iArr.length - 3] = 12610;
            iArr[iArr.length - 2] = 1;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.f4373a, iArr, 0, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            Log.w("Grafika", "unable to find RGB8888 / " + i2 + " EGLConfig");
            return null;
        }
        return eGLConfigArr[0];
    }

    /* renamed from: a */
    public void m20054a() {
        if (this.f4373a != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglMakeCurrent(this.f4373a, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroyContext(this.f4373a, this.f4374b);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f4373a);
        }
        this.f4373a = EGL14.EGL_NO_DISPLAY;
        this.f4374b = EGL14.EGL_NO_CONTEXT;
        this.f4375c = null;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f4373a != EGL14.EGL_NO_DISPLAY) {
                Log.w("Grafika", "WARNING: EglCore was not explicitly released -- state may be leaked");
                m20054a();
            }
        } finally {
            super.finalize();
        }
    }

    /* renamed from: a */
    public void m20052a(EGLSurface eGLSurface) {
        EGL14.eglDestroySurface(this.f4373a, eGLSurface);
    }

    /* renamed from: a */
    public EGLSurface m20050a(Object obj) {
        if (!(obj instanceof Surface) && !(obj instanceof SurfaceTexture)) {
            throw new RuntimeException("invalid surface: " + obj);
        }
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.f4373a, this.f4375c, obj, new int[]{12344}, 0);
        m20049a("eglCreateWindowSurface");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new RuntimeException("surface was null");
    }

    /* renamed from: b */
    public void m20048b(EGLSurface eGLSurface) {
        if (this.f4373a == EGL14.EGL_NO_DISPLAY) {
            Log.d("Grafika", "NOTE: makeCurrent w/o display");
        }
        if (!EGL14.eglMakeCurrent(this.f4373a, eGLSurface, eGLSurface, this.f4374b)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    /* renamed from: c */
    public boolean m20047c(EGLSurface eGLSurface) {
        return EGL14.eglSwapBuffers(this.f4373a, eGLSurface);
    }

    /* renamed from: a */
    public void m20051a(EGLSurface eGLSurface, long j) {
        EGLExt.eglPresentationTimeANDROID(this.f4373a, eGLSurface, j);
    }

    /* renamed from: a */
    private void m20049a(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == 12288) {
            return;
        }
        throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
    }
}
