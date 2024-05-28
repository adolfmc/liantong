package com.dueeeke.videoplayer.player;

import android.view.Surface;
import android.view.SurfaceHolder;
import com.dueeeke.videoplayer.listener.MediaEngineInterface;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class BaseMediaEngine {
    protected MediaEngineInterface mMediaEngineInterface;

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract void initPlayer();

    public abstract boolean isPlaying();

    public abstract void pause();

    public abstract void prepareAsync();

    public abstract void release();

    public abstract void reset();

    public abstract void seekTo(long j);

    public abstract void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    public abstract void setDisplay(SurfaceHolder surfaceHolder);

    public abstract void setEnableMediaCodec(boolean z);

    public abstract void setLooping(boolean z);

    public abstract void setOptions();

    public abstract void setSurface(Surface surface);

    public abstract void setVolume(int i, int i2);

    public abstract void start();

    public abstract void stop();

    public void setMediaEngineInterface(MediaEngineInterface mediaEngineInterface) {
        this.mMediaEngineInterface = mediaEngineInterface;
    }
}
