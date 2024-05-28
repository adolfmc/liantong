package com.baidu.p120ar.arrender;

import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.IGLRenderer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IGLRenderer extends IRenderer {
    void bindTargetSurface(Surface surface);

    Texture createTexture(int i, int i2, int i3);

    void destroyTexture(Texture texture);

    void runSyncOnIOContext(Runnable runnable);

    void setInputTexture(int i, int i2, int i3, int i4);

    void swapBuffer();
}
