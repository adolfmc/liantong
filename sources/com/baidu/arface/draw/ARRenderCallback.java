package com.baidu.arface.draw;

import android.graphics.SurfaceTexture;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ARRenderCallback {
    void onARDrawerChanged(SurfaceTexture surfaceTexture, int i, int i2);

    void onARDrawerCreated(SurfaceTexture surfaceTexture, SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener, int i, int i2);

    void onCameraDrawerCreated(SurfaceTexture surfaceTexture, int i, int i2);
}
