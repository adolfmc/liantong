package com.megvii.lv5;

import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.text.TextUtils;
import com.megvii.lv5.C5494l1;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.n1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class RunnableC5516n1 implements Runnable {

    /* renamed from: a */
    public final Object f13048a = new Object();

    /* renamed from: b */
    public float[] f13049b = new float[32];

    /* renamed from: c */
    public boolean f13050c;

    /* renamed from: d */
    public int f13051d;

    /* renamed from: e */
    public C5494l1 f13052e;

    /* renamed from: f */
    public C5494l1.C5495a f13053f;

    /* renamed from: g */
    public C5507m1 f13054g;

    /* renamed from: a */
    public static final RunnableC5516n1 m13281a(String str) {
        RunnableC5516n1 runnableC5516n1 = new RunnableC5516n1();
        synchronized (runnableC5516n1.f13048a) {
            if (TextUtils.isEmpty(str)) {
                str = "RenderHandler";
            }
            new Thread(runnableC5516n1, str).start();
            try {
                runnableC5516n1.f13048a.wait();
            } catch (InterruptedException unused) {
            }
        }
        return runnableC5516n1;
    }

    /* renamed from: a */
    public final void m13282a() {
        C5494l1.C5495a c5495a = this.f13053f;
        if (c5495a != null) {
            c5495a.f12859a.m13442a();
            C5494l1 c5494l1 = c5495a.f12859a;
            EGLSurface eGLSurface = c5495a.f12860b;
            c5494l1.getClass();
            EGLSurface eGLSurface2 = EGL14.EGL_NO_SURFACE;
            if (eGLSurface != eGLSurface2) {
                EGL14.eglMakeCurrent(c5494l1.f12857b, eGLSurface2, eGLSurface2, EGL14.EGL_NO_CONTEXT);
                EGL14.eglDestroySurface(c5494l1.f12857b, eGLSurface);
            }
            c5495a.f12860b = EGL14.EGL_NO_SURFACE;
            this.f13053f = null;
        }
        C5507m1 c5507m1 = this.f13054g;
        if (c5507m1 != null) {
            int i = c5507m1.f12885a;
            if (i >= 0) {
                GLES20.glDeleteProgram(i);
            }
            c5507m1.f12885a = -1;
            this.f13054g = null;
        }
        C5494l1 c5494l12 = this.f13052e;
        if (c5494l12 != null) {
            EGLDisplay eGLDisplay = c5494l12.f12857b;
            if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
                if (!EGL14.eglDestroyContext(eGLDisplay, c5494l12.f12856a)) {
                    String str = "display:" + c5494l12.f12857b + " context: " + c5494l12.f12856a;
                    String str2 = "eglDestroyContex:" + EGL14.eglGetError();
                }
                EGLContext eGLContext = EGL14.EGL_NO_CONTEXT;
                c5494l12.f12856a = eGLContext;
                EGLContext eGLContext2 = c5494l12.f12858c;
                if (eGLContext2 != eGLContext) {
                    if (!EGL14.eglDestroyContext(c5494l12.f12857b, eGLContext2)) {
                        String str3 = "display:" + c5494l12.f12857b + " context: " + c5494l12.f12858c;
                        String str4 = "eglDestroyContex:" + EGL14.eglGetError();
                    }
                    c5494l12.f12858c = EGL14.EGL_NO_CONTEXT;
                }
                EGL14.eglTerminate(c5494l12.f12857b);
                EGL14.eglReleaseThread();
            }
            c5494l12.f12857b = EGL14.EGL_NO_DISPLAY;
            c5494l12.f12856a = EGL14.EGL_NO_CONTEXT;
            this.f13052e = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0026, code lost:
        if (r4 == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0029, code lost:
        r0 = r5.f13048a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002b, code lost:
        monitor-enter(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x002c, code lost:
        r5.f13048a.wait();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0031, code lost:
        monitor-exit(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0033, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0049, code lost:
        throw r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.f13048a
            monitor-enter(r0)
            r1 = 0
            r5.f13050c = r1     // Catch: java.lang.Throwable -> L4d
            r5.f13051d = r1     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r2 = r5.f13048a     // Catch: java.lang.Throwable -> L4d
            r2.notifyAll()     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4d
        Le:
            java.lang.Object r2 = r5.f13048a
            monitor-enter(r2)
            boolean r0 = r5.f13050c     // Catch: java.lang.Throwable -> L4a
            r3 = 1
            if (r0 == 0) goto L18
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4a
            goto L36
        L18:
            int r0 = r5.f13051d     // Catch: java.lang.Throwable -> L4a
            if (r0 <= 0) goto L1e
            r4 = r3
            goto L1f
        L1e:
            r4 = r1
        L1f:
            if (r4 == 0) goto L25
            int r0 = r0 + (-1)
            r5.f13051d = r0     // Catch: java.lang.Throwable -> L4a
        L25:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4a
            if (r4 == 0) goto L29
            goto Le
        L29:
            java.lang.Object r0 = r5.f13048a
            monitor-enter(r0)
            java.lang.Object r2 = r5.f13048a     // Catch: java.lang.Throwable -> L33 java.lang.InterruptedException -> L35
            r2.wait()     // Catch: java.lang.Throwable -> L33 java.lang.InterruptedException -> L35
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L33
            goto Le
        L33:
            r1 = move-exception
            goto L48
        L35:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L33
        L36:
            java.lang.Object r1 = r5.f13048a
            monitor-enter(r1)
            r5.f13050c = r3     // Catch: java.lang.Throwable -> L45
            r5.m13282a()     // Catch: java.lang.Throwable -> L45
            java.lang.Object r0 = r5.f13048a     // Catch: java.lang.Throwable -> L45
            r0.notifyAll()     // Catch: java.lang.Throwable -> L45
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L45
            return
        L45:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L45
            throw r0
        L48:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L33
            throw r1
        L4a:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4a
            throw r0
        L4d:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4d
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.RunnableC5516n1.run():void");
    }
}
