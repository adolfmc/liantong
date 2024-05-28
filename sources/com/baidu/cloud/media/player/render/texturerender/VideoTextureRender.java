package com.baidu.cloud.media.player.render.texturerender;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import com.baidu.cloud.media.player.render.p134a.FullFrameRect;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoTextureRender extends AbsTextureSurfaceRender implements SurfaceTexture.OnFrameAvailableListener, Runnable {
    private boolean adjustViewport;
    private boolean frameAvailable;
    private boolean isReleaseTexture;
    private FullFrameRect mFullFrameRect;
    private final Handler mHandler;
    private int[] textures;
    private int videoHeight;
    private float[] videoTextureTransform;
    private int videoWidth;

    public VideoTextureRender(SurfaceTexture surfaceTexture, int i, int i2) {
        super(surfaceTexture, i, i2);
        this.frameAvailable = false;
        this.adjustViewport = false;
        this.isReleaseTexture = false;
        this.textures = new int[1];
        this.mHandler = new Handler();
        this.videoTextureTransform = new float[16];
        this.mFullFrameRect = new FullFrameRect();
    }

    public void setVideoSize(int i, int i2) {
        this.videoWidth = i;
        this.videoHeight = i2;
        this.adjustViewport = true;
    }

    public SurfaceTexture getVideoTexture() {
        return this.videoTexture;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.mHandler.post(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        draw();
    }

    private void draw() {
        if (this.videoTexture != null && !this.isReleaseTexture) {
            this.videoTexture.updateTexImage();
            this.videoTexture.getTransformMatrix(this.videoTextureTransform);
        }
        drawFrame();
        swapBuffers();
        if (this.mRenderList != null) {
            for (int i = 0; i < this.mRenderList.size(); i++) {
                this.mRenderList.get(i).onDrawFrame(this.mFullFrameRect, this.textureId, this.videoTextureTransform);
            }
        }
    }

    private void drawFrame() {
        adjustViewport();
        this.mFullFrameRect.m20040a(this.textureId, this.videoTextureTransform);
    }

    private void adjustViewport() {
        GLES20.glViewport(0, 0, this.videoWidth, this.videoHeight);
        this.adjustViewport = false;
    }

    @Override // com.baidu.cloud.media.player.render.texturerender.AbsTextureSurfaceRender
    protected void initGLComponents() {
        this.mFullFrameRect.m20037b();
        this.textureId = this.mFullFrameRect.m20038a(this.textures);
        this.videoTexture = new SurfaceTexture(this.textureId);
        this.videoTexture.setOnFrameAvailableListener(this);
        this.mFullFrameRect.m20041a();
    }

    @Override // com.baidu.cloud.media.player.render.texturerender.AbsTextureSurfaceRender
    protected void deInitGLComponents() {
        this.isReleaseTexture = true;
        this.mFullFrameRect.m20036b(this.textures);
        if (this.videoTexture != null) {
            this.videoTexture.release();
            this.videoTexture.setOnFrameAvailableListener(null);
        }
    }
}
