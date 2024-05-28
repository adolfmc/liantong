package com.baidu.cloud.media.player.render.texturerender;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLUtils;
import com.baidu.cloud.media.player.render.p134a.EglCore;
import com.baidu.cloud.media.player.render.p135b.Logger;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.texturerender.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbsTextureSurfaceRender {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final String LOG_TAG = "AbsTextureSurfaceRender";
    private EglCore eglCore;
    private int frames;
    protected int height;
    protected List<IMediaRenderCallback> mRenderList;
    protected final SurfaceTexture surfaceTexture;
    protected int textureId;
    protected SurfaceTexture videoTexture;
    protected int width;
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;
    private EGLDisplay eglDisplay = EGL14.EGL_NO_DISPLAY;
    private EGLContext eglContext = EGL14.EGL_NO_CONTEXT;
    private long lastFpsOutput = 0;
    private boolean running = true;

    protected abstract void deInitGLComponents();

    protected abstract void initGLComponents();

    public AbsTextureSurfaceRender(SurfaceTexture surfaceTexture, int i, int i2) {
        this.surfaceTexture = surfaceTexture;
        this.width = i;
        this.height = i2;
    }

    public void setRenderList(List<IMediaRenderCallback> list) {
        this.mRenderList = list;
    }

    public void onInitCreate() {
        initGL();
        initGLComponents();
    }

    public void onDestroy() {
        this.running = false;
        Logger.m20030b("cccc", "destroy texture render");
        deInitGLComponents();
        deInitGL();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void swapBuffers() {
        EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
    }

    private void pingFps() {
        if (this.lastFpsOutput == 0) {
            this.lastFpsOutput = System.currentTimeMillis();
        }
        this.frames++;
        if (System.currentTimeMillis() - this.lastFpsOutput > 1000) {
            Logger.m20031a("AbsTextureSurfaceRender", "FPS: " + this.frames);
            this.lastFpsOutput = System.currentTimeMillis();
            this.frames = 0;
        }
    }

    private void onPause() {
        this.running = false;
    }

    private void initGL() {
        this.eglDisplay = EGL14.eglGetDisplay(0);
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(this.eglDisplay, iArr, 0, iArr, 1)) {
            this.eglDisplay = null;
            throw new RuntimeException("unable to initialize EGL14");
        }
        EGLConfig chooseEglConfig = chooseEglConfig();
        this.eglContext = createContext(this.eglDisplay, chooseEglConfig, EGL14.eglGetCurrentContext());
        this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, chooseEglConfig, this.surfaceTexture, new int[]{12344}, 0);
        EGLSurface eGLSurface = this.eglSurface;
        if (eGLSurface == null || eGLSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("GL Error: " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        }
        EGLDisplay eGLDisplay = this.eglDisplay;
        EGLSurface eGLSurface2 = this.eglSurface;
        if (EGL14.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.eglContext)) {
            return;
        }
        throw new RuntimeException("GL Make current error: " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
    }

    private void deInitGL() {
        EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
        EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
        EGL14.eglTerminate(this.eglDisplay);
        Logger.m20031a("AbsTextureSurfaceRender", "OpenGL deinit OK.");
    }

    private EGLContext createContext(EGLDisplay eGLDisplay, EGLConfig eGLConfig, EGLContext eGLContext) {
        return EGL14.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, new int[]{12440, 2, 12344}, 0);
    }

    private EGLConfig chooseEglConfig() {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.eglDisplay, getConfig(), 0, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            throw new IllegalArgumentException("Failed to choose config: " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        }
        return eGLConfigArr[0];
    }

    private int[] getConfig() {
        return new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    }
}
