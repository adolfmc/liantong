package com.baidu.p120ar.arrender;

import android.content.Context;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import com.baidu.p120ar.lua.EngineMsgBridge;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.ARRenderer2 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARRenderer2 extends ARRenderer implements IGLRenderer {
    private static final String CPU_632 = "sdm632";
    private static final String CPU_MSM8953 = "msm8953";
    private static final String QCOM = "qcom";
    private static final String TAG = "ARRenderer2";
    private String mBoard;
    private String mHardware;

    public ARRenderer2(Context context, Looper looper, EngineMsgBridge engineMsgBridge, EGLContext eGLContext, String str) {
        super(context, looper, engineMsgBridge, eGLContext, str);
        this.mHardware = "";
        this.mBoard = "";
        this.mHardware = Build.HARDWARE.toLowerCase();
        this.mBoard = Build.BOARD.toLowerCase();
    }

    @Override // com.baidu.p120ar.arrender.ARRenderer, com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void release() {
        super.release();
    }

    @Override // com.baidu.p120ar.arrender.IGLRenderer
    public void runSyncOnIOContext(Runnable runnable) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || runnable == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().runSyncOnIOContext(runnable);
    }

    @Override // com.baidu.p120ar.arrender.IGLRenderer
    public Texture createTexture(int i, int i2, int i3) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return null;
        }
        Texture texture = new Texture();
        long createTexture = this.mARPEngine.getARPRenderer().createTexture(i, i2, i3);
        texture.setHandle(createTexture);
        texture.setId(this.mARPEngine.getARPRenderer().getTextureId(createTexture));
        texture.setType(i);
        return texture;
    }

    @Override // com.baidu.p120ar.arrender.IGLRenderer
    public void destroyTexture(Texture texture) {
        if (texture == null || this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().destroyTexture(texture.getHandle());
    }

    @Override // com.baidu.p120ar.arrender.IGLRenderer
    public void setInputTexture(int i, int i2, int i3, int i4) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().setInputTexture(i, i2, i3, i4);
    }

    @Override // com.baidu.p120ar.arrender.IGLRenderer
    public void bindTargetSurface(Surface surface) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().bindTargetSurface(surface);
    }

    @Override // com.baidu.p120ar.arrender.IGLRenderer
    public void swapBuffer() {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().swapBuffer();
    }

    @Override // com.baidu.p120ar.arrender.ARRenderer, com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arplay.core.renderer.OnRenderFinishedListener
    public void onRenderFinished(long j) {
        super.onRenderFinished(j);
        if (TextUtils.isEmpty(this.mHardware) || !this.mHardware.contains("qcom") || TextUtils.isEmpty(this.mBoard)) {
            return;
        }
        if (this.mBoard.contains("msm8953") || this.mBoard.contains("sdm632")) {
            GLES20.glFinish();
        }
    }
}
