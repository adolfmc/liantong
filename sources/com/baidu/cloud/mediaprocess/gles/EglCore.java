package com.baidu.cloud.mediaprocess.gles;

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
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class EglCore {
    public static final int FLAG_RECORDABLE = 1;
    public static final int FLAG_TRY_GLES3 = 2;

    /* renamed from: a */
    public EGLDisplay f4825a;

    /* renamed from: b */
    public EGLContext f4826b;

    /* renamed from: c */
    public EGLConfig f4827c;

    /* renamed from: d */
    public int f4828d;

    public EglCore() {
        this(null, 0);
    }

    public EglCore(EGLContext eGLContext, int i) {
        EGLConfig m19855a;
        this.f4825a = EGL14.EGL_NO_DISPLAY;
        this.f4826b = EGL14.EGL_NO_CONTEXT;
        this.f4827c = null;
        this.f4828d = -1;
        if (this.f4825a != EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("EGL already set up");
        }
        eGLContext = eGLContext == null ? EGL14.EGL_NO_CONTEXT : eGLContext;
        this.f4825a = EGL14.eglGetDisplay(0);
        EGLDisplay eGLDisplay = this.f4825a;
        if (eGLDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(eGLDisplay, iArr, 0, iArr, 1)) {
            this.f4825a = null;
            throw new RuntimeException("unable to initialize EGL14");
        }
        if ((i & 2) != 0 && (m19855a = m19855a(i, 3)) != null) {
            EGLContext eglCreateContext = EGL14.eglCreateContext(this.f4825a, m19855a, eGLContext, new int[]{12440, 3, 12344}, 0);
            if (EGL14.eglGetError() == 12288) {
                this.f4827c = m19855a;
                this.f4826b = eglCreateContext;
                this.f4828d = 3;
            }
        }
        if (this.f4826b == EGL14.EGL_NO_CONTEXT) {
            EGLConfig m19855a2 = m19855a(i, 2);
            if (m19855a2 == null) {
                throw new RuntimeException("Unable to find a suitable EGLConfig");
            }
            EGLContext eglCreateContext2 = EGL14.eglCreateContext(this.f4825a, m19855a2, eGLContext, new int[]{12440, 2, 12344}, 0);
            m19854a("eglCreateContext");
            this.f4827c = m19855a2;
            this.f4826b = eglCreateContext2;
            this.f4828d = 2;
        }
        int[] iArr2 = new int[1];
        EGL14.eglQueryContext(this.f4825a, this.f4826b, 12440, iArr2, 0);
        Log.d("Grafika", "EGLContext created, client version " + iArr2[0]);
    }

    public static void logCurrent(String str) {
        EGLDisplay eglGetCurrentDisplay = EGL14.eglGetCurrentDisplay();
        EGLContext eglGetCurrentContext = EGL14.eglGetCurrentContext();
        EGLSurface eglGetCurrentSurface = EGL14.eglGetCurrentSurface(12377);
        Log.i("Grafika", "Current EGL (" + str + "): display=" + eglGetCurrentDisplay + ", context=" + eglGetCurrentContext + ", surface=" + eglGetCurrentSurface);
    }

    /* renamed from: a */
    public final EGLConfig m19855a(int i, int i2) {
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
        if (EGL14.eglChooseConfig(this.f4825a, iArr, 0, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        Log.w("Grafika", "unable to find RGB8888 / " + i2 + " EGLConfig");
        return null;
    }

    /* renamed from: a */
    public final void m19854a(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == 12288) {
            return;
        }
        throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
    }

    public EGLSurface createOffscreenSurface(int i, int i2) {
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(this.f4825a, this.f4827c, new int[]{12375, i, 12374, i2, 12344}, 0);
        m19854a("eglCreatePbufferSurface");
        if (eglCreatePbufferSurface != null) {
            return eglCreatePbufferSurface;
        }
        throw new RuntimeException("surface was null");
    }

    public EGLSurface createWindowSurface(Object obj) {
        if (!(obj instanceof Surface) && !(obj instanceof SurfaceTexture)) {
            throw new RuntimeException("invalid surface: " + obj);
        }
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.f4825a, this.f4827c, obj, new int[]{12344}, 0);
        m19854a("eglCreateWindowSurface");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new RuntimeException("surface was null");
    }

    public void finalize() {
        try {
            if (this.f4825a != EGL14.EGL_NO_DISPLAY) {
                Log.w("Grafika", "WARNING: EglCore was not explicitly released -- state may be leaked");
                release();
            }
        } finally {
            super.finalize();
        }
    }

    public int getGlVersion() {
        return this.f4828d;
    }

    public boolean isCurrent(EGLSurface eGLSurface) {
        return this.f4826b.equals(EGL14.eglGetCurrentContext()) && eGLSurface.equals(EGL14.eglGetCurrentSurface(12377));
    }

    public void makeCurrent(EGLSurface eGLSurface) {
        if (this.f4825a == EGL14.EGL_NO_DISPLAY) {
            Log.d("Grafika", "NOTE: makeCurrent w/o display");
        }
        if (!EGL14.eglMakeCurrent(this.f4825a, eGLSurface, eGLSurface, this.f4826b)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public void makeCurrent(EGLSurface eGLSurface, EGLSurface eGLSurface2) {
        if (this.f4825a == EGL14.EGL_NO_DISPLAY) {
            Log.d("Grafika", "NOTE: makeCurrent w/o display");
        }
        if (!EGL14.eglMakeCurrent(this.f4825a, eGLSurface, eGLSurface2, this.f4826b)) {
            throw new RuntimeException("eglMakeCurrent(draw,read) failed");
        }
    }

    public void makeNothingCurrent() {
        EGLDisplay eGLDisplay = this.f4825a;
        EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public String queryString(int i) {
        return EGL14.eglQueryString(this.f4825a, i);
    }

    public int querySurface(EGLSurface eGLSurface, int i) {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.f4825a, eGLSurface, i, iArr, 0);
        return iArr[0];
    }

    public void release() {
        EGLDisplay eGLDisplay = this.f4825a;
        if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
            EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroyContext(this.f4825a, this.f4826b);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f4825a);
        }
        this.f4825a = EGL14.EGL_NO_DISPLAY;
        this.f4826b = EGL14.EGL_NO_CONTEXT;
        this.f4827c = null;
    }

    public void releaseSurface(EGLSurface eGLSurface) {
        EGL14.eglDestroySurface(this.f4825a, eGLSurface);
    }

    public void setPresentationTime(EGLSurface eGLSurface, long j) {
        EGLExt.eglPresentationTimeANDROID(this.f4825a, eGLSurface, j);
    }

    public boolean swapBuffers(EGLSurface eGLSurface) {
        return EGL14.eglSwapBuffers(this.f4825a, eGLSurface);
    }
}
