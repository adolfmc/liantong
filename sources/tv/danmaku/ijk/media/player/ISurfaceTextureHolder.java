package tv.danmaku.ijk.media.player;

import android.graphics.SurfaceTexture;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ISurfaceTextureHolder {
    SurfaceTexture getSurfaceTexture();

    void setSurfaceTexture(SurfaceTexture surfaceTexture);

    void setSurfaceTextureHost(ISurfaceTextureHost iSurfaceTextureHost);
}
