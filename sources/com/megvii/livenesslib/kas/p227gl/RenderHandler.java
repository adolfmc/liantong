package com.megvii.livenesslib.kas.p227gl;

import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.opengl.Matrix;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.megvii.livenesslib.kas.p227gl.EGLBase;

/* renamed from: com.megvii.livenesslib.kas.gl.RenderHandler */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class RenderHandler implements Runnable {
    private static final boolean DEBUG = false;
    private static final String TAG = "RenderHandler";
    private GLDrawer2D mDrawer;
    private EGLBase mEgl;
    private EGLBase.EglSurface mInputSurface;
    private boolean mIsRecordable;
    private int mRequestDraw;
    private boolean mRequestRelease;
    private boolean mRequestSetEglContext;
    private EGLContext mShard_context;
    private Object mSurface;
    private final Object mSync = new Object();
    private int mTexId = -1;
    private float[] mMatrix = new float[32];

    public static final RenderHandler createHandler(String str) {
        RenderHandler renderHandler = new RenderHandler();
        synchronized (renderHandler.mSync) {
            if (TextUtils.isEmpty(str)) {
                str = TAG;
            }
            new Thread(renderHandler, str).start();
            try {
                renderHandler.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
        return renderHandler;
    }

    public final void setEglContext(EGLContext eGLContext, int i, Object obj, boolean z) {
        if (!(obj instanceof Surface) && !(obj instanceof SurfaceTexture) && !(obj instanceof SurfaceHolder)) {
            throw new RuntimeException("unsupported window type:" + obj);
        }
        synchronized (this.mSync) {
            if (this.mRequestRelease) {
                return;
            }
            this.mShard_context = eGLContext;
            this.mTexId = i;
            this.mSurface = obj;
            this.mIsRecordable = z;
            this.mRequestSetEglContext = true;
            Matrix.setIdentityM(this.mMatrix, 0);
            Matrix.setIdentityM(this.mMatrix, 16);
            this.mSync.notifyAll();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public final void draw() {
        draw(this.mTexId, this.mMatrix, null);
    }

    public final void draw(int i) {
        draw(i, this.mMatrix, null);
    }

    public final void draw(float[] fArr) {
        draw(this.mTexId, fArr, null);
    }

    public final void draw(float[] fArr, float[] fArr2) {
        draw(this.mTexId, fArr, fArr2);
    }

    public final void draw(int i, float[] fArr) {
        draw(i, fArr, null);
    }

    public final void draw(int i, float[] fArr, float[] fArr2) {
        synchronized (this.mSync) {
            if (this.mRequestRelease) {
                return;
            }
            this.mTexId = i;
            if (fArr != null && fArr.length >= 16) {
                System.arraycopy(fArr, 0, this.mMatrix, 0, 16);
            } else {
                Matrix.setIdentityM(this.mMatrix, 0);
            }
            if (fArr2 != null && fArr2.length >= 16) {
                System.arraycopy(fArr2, 0, this.mMatrix, 16, 16);
            } else {
                Matrix.setIdentityM(this.mMatrix, 16);
            }
            this.mRequestDraw++;
            this.mSync.notifyAll();
        }
    }

    public boolean isValid() {
        boolean z;
        synchronized (this.mSync) {
            z = !(this.mSurface instanceof Surface) || ((Surface) this.mSurface).isValid();
        }
        return z;
    }

    public final void release() {
        synchronized (this.mSync) {
            if (this.mRequestRelease) {
                return;
            }
            this.mRequestRelease = true;
            this.mSync.notifyAll();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
        if (r0 == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0036, code lost:
        if (r5.mEgl == null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003a, code lost:
        if (r5.mTexId < 0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003c, code lost:
        r5.mInputSurface.makeCurrent();
        android.opengl.GLES20.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
        android.opengl.GLES20.glClear(16384);
        r5.mDrawer.setMatrix(r5.mMatrix, 16);
        r5.mDrawer.draw(r5.mTexId, r5.mMatrix);
        r5.mInputSurface.swap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:
        r0 = r5.mSync;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:
        monitor-enter(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
        r5.mSync.wait();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
        monitor-exit(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006e, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0084, code lost:
        throw r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mSync
            monitor-enter(r0)
            r1 = 0
            r5.mRequestRelease = r1     // Catch: java.lang.Throwable -> L88
            r5.mRequestSetEglContext = r1     // Catch: java.lang.Throwable -> L88
            r5.mRequestDraw = r1     // Catch: java.lang.Throwable -> L88
            java.lang.Object r2 = r5.mSync     // Catch: java.lang.Throwable -> L88
            r2.notifyAll()     // Catch: java.lang.Throwable -> L88
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
        L10:
            java.lang.Object r2 = r5.mSync
            monitor-enter(r2)
            boolean r0 = r5.mRequestRelease     // Catch: java.lang.Throwable -> L85
            r3 = 1
            if (r0 == 0) goto L1a
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L85
            goto L71
        L1a:
            boolean r0 = r5.mRequestSetEglContext     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L23
            r5.mRequestSetEglContext = r1     // Catch: java.lang.Throwable -> L85
            r5.internalPrepare()     // Catch: java.lang.Throwable -> L85
        L23:
            int r0 = r5.mRequestDraw     // Catch: java.lang.Throwable -> L85
            if (r0 <= 0) goto L29
            r0 = r3
            goto L2a
        L29:
            r0 = r1
        L2a:
            if (r0 == 0) goto L31
            int r4 = r5.mRequestDraw     // Catch: java.lang.Throwable -> L85
            int r4 = r4 - r3
            r5.mRequestDraw = r4     // Catch: java.lang.Throwable -> L85
        L31:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L64
            com.megvii.livenesslib.kas.gl.EGLBase r0 = r5.mEgl
            if (r0 == 0) goto L10
            int r0 = r5.mTexId
            if (r0 < 0) goto L10
            com.megvii.livenesslib.kas.gl.EGLBase$EglSurface r0 = r5.mInputSurface
            r0.makeCurrent()
            r0 = 0
            r2 = 1065353216(0x3f800000, float:1.0)
            android.opengl.GLES20.glClearColor(r2, r2, r0, r2)
            r0 = 16384(0x4000, float:2.2959E-41)
            android.opengl.GLES20.glClear(r0)
            com.megvii.livenesslib.kas.gl.GLDrawer2D r0 = r5.mDrawer
            float[] r2 = r5.mMatrix
            r3 = 16
            r0.setMatrix(r2, r3)
            com.megvii.livenesslib.kas.gl.GLDrawer2D r0 = r5.mDrawer
            int r2 = r5.mTexId
            float[] r3 = r5.mMatrix
            r0.draw(r2, r3)
            com.megvii.livenesslib.kas.gl.EGLBase$EglSurface r0 = r5.mInputSurface
            r0.swap()
            goto L10
        L64:
            java.lang.Object r0 = r5.mSync
            monitor-enter(r0)
            java.lang.Object r2 = r5.mSync     // Catch: java.lang.Throwable -> L6e java.lang.InterruptedException -> L70
            r2.wait()     // Catch: java.lang.Throwable -> L6e java.lang.InterruptedException -> L70
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
            goto L10
        L6e:
            r1 = move-exception
            goto L83
        L70:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
        L71:
            java.lang.Object r1 = r5.mSync
            monitor-enter(r1)
            r5.mRequestRelease = r3     // Catch: java.lang.Throwable -> L80
            r5.internalRelease()     // Catch: java.lang.Throwable -> L80
            java.lang.Object r0 = r5.mSync     // Catch: java.lang.Throwable -> L80
            r0.notifyAll()     // Catch: java.lang.Throwable -> L80
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L80
            return
        L80:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L80
            throw r0
        L83:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
            throw r1
        L85:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L85
            throw r0
        L88:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenesslib.kas.p227gl.RenderHandler.run():void");
    }

    private final void internalPrepare() {
        internalRelease();
        this.mEgl = new EGLBase(this.mShard_context, false, this.mIsRecordable);
        this.mInputSurface = this.mEgl.createFromSurface(this.mSurface);
        this.mInputSurface.makeCurrent();
        this.mDrawer = new GLDrawer2D();
        this.mSurface = null;
        this.mSync.notifyAll();
    }

    private final void internalRelease() {
        EGLBase.EglSurface eglSurface = this.mInputSurface;
        if (eglSurface != null) {
            eglSurface.release();
            this.mInputSurface = null;
        }
        GLDrawer2D gLDrawer2D = this.mDrawer;
        if (gLDrawer2D != null) {
            gLDrawer2D.release();
            this.mDrawer = null;
        }
        EGLBase eGLBase = this.mEgl;
        if (eGLBase != null) {
            eGLBase.release();
            this.mEgl = null;
        }
    }
}
