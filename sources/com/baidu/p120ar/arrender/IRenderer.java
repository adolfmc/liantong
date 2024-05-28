package com.baidu.p120ar.arrender;

import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.DuMixStateListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.IRenderer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRenderer {
    void addFrameRenderListener(FrameRenderListener frameRenderListener);

    void addOutputSurface(DuMixOutput duMixOutput);

    void cancelAysncRenderTask(Runnable runnable);

    void removeFrameRenderListener(FrameRenderListener frameRenderListener);

    void removeOutputSurface(DuMixOutput duMixOutput);

    void render();

    void runAsyncOnRenderContext(Runnable runnable);

    void runSyncOnRenderContext(Runnable runnable);

    void setCameraSwitchListener(CameraSwitchListener cameraSwitchListener);

    void setDefaultPipeLine(String str);

    void setInputMatrix(float[] fArr);

    void setStateListener(DuMixStateListener duMixStateListener);
}
