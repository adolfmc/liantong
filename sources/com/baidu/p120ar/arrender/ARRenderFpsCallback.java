package com.baidu.p120ar.arrender;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.ARRenderFpsCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ARRenderFpsCallback {
    public static final int LISTEN_RENDER_END = 2;
    public static final int LISTEN_RENDER_FPS = 4;
    public static final int LISTEN_RENDER_START = 1;

    int listenType();

    void onRenderFinished();

    void onRenderStarted();

    void renderFps(int i);
}
