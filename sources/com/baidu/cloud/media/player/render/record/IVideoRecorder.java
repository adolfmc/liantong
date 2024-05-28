package com.baidu.cloud.media.player.render.record;

import android.graphics.SurfaceTexture;
import com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IVideoRecorder {
    int getVideoHeight();

    String getVideoPath();

    int getVideoWidth();

    void onRecord();

    void setOnEncoderStatusUpdateListener(TextureMovieEncoder.OnEncoderStatusUpdateListener onEncoderStatusUpdateListener);

    void setRecordFrameListener(IOnRecordFrameListener iOnRecordFrameListener);

    void setRecordSize(int i, int i2);

    void setSurfaceTexture(SurfaceTexture surfaceTexture);

    void startRecord(String str);

    String stopRecord();

    void updateExternalAudioData(byte[] bArr, int i);
}
