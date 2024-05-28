package com.baidu.cloud.media.player.render.record;

import android.graphics.SurfaceTexture;
import com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IOnRecordFrameListener {
    void onRecord(TextureMovieEncoder textureMovieEncoder, SurfaceTexture surfaceTexture);
}
