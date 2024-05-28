package com.baidu.p120ar.arplay.webview;

import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.webview.SurfaceTextureHolder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SurfaceTextureHolder {
    private static final int DEFAULT_TEXTURE_HEIGHT = 500;
    private static final int DEFAULT_TEXTURE_WIDTH = 500;
    private static final String TAG = "SurfaceTextureHolder";
    private Surface mSurface;
    private Canvas mSurfaceCanvas;
    private SurfaceTexture mSurfaceTexture;
    public int mTextureId;
    private int mTextureWidth = 500;
    private int mTextureHeight = 500;
    private boolean isAttatched = true;

    public Surface createSurface(int i, int i2, int i3) {
        this.mTextureId = i;
        this.mSurfaceTexture = new SurfaceTexture(i);
        updateSurfaceSize(i2, i3);
        this.mSurface = new Surface(this.mSurfaceTexture);
        return this.mSurface;
    }

    public void updateSurfaceSize(int i, int i2) {
        this.mTextureWidth = i;
        this.mTextureHeight = i2;
        this.mSurfaceTexture.setDefaultBufferSize(this.mTextureWidth, this.mTextureHeight);
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.mSurfaceTexture;
    }

    public void update() {
        try {
            this.mSurfaceTexture.updateTexImage();
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "error while update view to gl: " + e);
        }
    }

    public Canvas lockCanvas() {
        this.mSurfaceCanvas = null;
        Surface surface = this.mSurface;
        if (surface != null) {
            try {
                this.mSurfaceCanvas = surface.lockCanvas(null);
            } catch (Exception e) {
                String str = TAG;
                Log.e(str, "error while rendering view to gl: " + e);
            }
        }
        return this.mSurfaceCanvas;
    }

    public void unlockCanvas() {
        Canvas canvas = this.mSurfaceCanvas;
        if (canvas != null) {
            this.mSurface.unlockCanvasAndPost(canvas);
        }
        this.mSurfaceCanvas = null;
    }

    public void release() {
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }

    public void updateViewToGlSurfaceView() {
        this.mSurfaceTexture.updateTexImage();
    }

    public int getTextureWidth() {
        return this.mTextureWidth;
    }

    public void setTextureWidth(int i) {
        this.mTextureWidth = i;
    }

    public int getTextureHeight() {
        return this.mTextureHeight;
    }

    public void setTextureHeight(int i) {
        this.mTextureHeight = i;
    }
}
