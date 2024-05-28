package com.baidu.cloud.mediaprocess.gles;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EglSurfaceBase {
    public static final String TAG = "Grafika";

    /* renamed from: a */
    public EGLSurface f4829a = EGL14.EGL_NO_SURFACE;

    /* renamed from: b */
    public int f4830b = -1;

    /* renamed from: c */
    public int f4831c = -1;
    public EglCore mEglCore;

    public EglSurfaceBase(EglCore eglCore) {
        this.mEglCore = eglCore;
    }

    public void createOffscreenSurface(int i, int i2) {
        if (this.f4829a != EGL14.EGL_NO_SURFACE) {
            throw new IllegalStateException("surface already created");
        }
        this.f4829a = this.mEglCore.createOffscreenSurface(i, i2);
        this.f4830b = i;
        this.f4831c = i2;
    }

    public void createWindowSurface(Object obj) {
        if (this.f4829a != EGL14.EGL_NO_SURFACE) {
            throw new IllegalStateException("surface already created");
        }
        this.f4829a = this.mEglCore.createWindowSurface(obj);
    }

    public int getHeight() {
        int i = this.f4831c;
        return i < 0 ? this.mEglCore.querySurface(this.f4829a, 12374) : i;
    }

    public int getWidth() {
        int i = this.f4830b;
        return i < 0 ? this.mEglCore.querySurface(this.f4829a, 12375) : i;
    }

    public void makeCurrent() {
        this.mEglCore.makeCurrent(this.f4829a);
    }

    public void makeCurrentReadFrom(EglSurfaceBase eglSurfaceBase) {
        this.mEglCore.makeCurrent(this.f4829a, eglSurfaceBase.f4829a);
    }

    public void releaseEglSurface() {
        this.mEglCore.releaseSurface(this.f4829a);
        this.f4829a = EGL14.EGL_NO_SURFACE;
        this.f4831c = -1;
        this.f4830b = -1;
    }

    public void saveFrame(File file) {
        BufferedOutputStream bufferedOutputStream;
        if (!this.mEglCore.isCurrent(this.f4829a)) {
            throw new RuntimeException("Expected EGL context/surface is not current");
        }
        String file2 = file.toString();
        int width = getWidth();
        int height = getHeight();
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(width * height * 4);
        allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        GLES20.glReadPixels(0, 0, width, height, 6408, 5121, allocateDirect);
        GlUtil.checkGlError("glReadPixels");
        allocateDirect.rewind();
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(allocateDirect);
            createBitmap.compress(Bitmap.CompressFormat.PNG, 90, bufferedOutputStream);
            createBitmap.recycle();
            bufferedOutputStream.close();
            Log.d("Grafika", "Saved " + width + "x" + height + " frame as '" + file2 + "'");
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            throw th;
        }
    }

    public void setPresentationTime(long j) {
        this.mEglCore.setPresentationTime(this.f4829a, j);
    }

    public boolean swapBuffers() {
        boolean swapBuffers = this.mEglCore.swapBuffers(this.f4829a);
        if (!swapBuffers) {
            Log.d("Grafika", "WARNING: swapBuffers() failed");
        }
        return swapBuffers;
    }
}
