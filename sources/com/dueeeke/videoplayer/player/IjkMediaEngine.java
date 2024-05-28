package com.dueeeke.videoplayer.player;

import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IjkMediaEngine extends BaseMediaEngine {
    private boolean isLooping;
    protected IjkMediaPlayer mMediaPlayer;
    private IMediaPlayer.OnErrorListener onErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.2
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (IjkMediaEngine.this.mMediaEngineInterface != null) {
                IjkMediaEngine.this.mMediaEngineInterface.onError();
                return true;
            }
            return true;
        }
    };
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.3
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            if (IjkMediaEngine.this.mMediaEngineInterface != null) {
                IjkMediaEngine.this.mMediaEngineInterface.onCompletion();
            }
        }
    };
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.4
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (IjkMediaEngine.this.mMediaEngineInterface != null) {
                IjkMediaEngine.this.mMediaEngineInterface.onInfo(i, i2);
                return true;
            }
            return true;
        }
    };
    private IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.5
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            if (IjkMediaEngine.this.mMediaEngineInterface != null) {
                IjkMediaEngine.this.mMediaEngineInterface.onBufferingUpdate(i);
            }
        }
    };
    private IMediaPlayer.OnPreparedListener onPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.6
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            if (IjkMediaEngine.this.mMediaEngineInterface != null) {
                IjkMediaEngine.this.mMediaEngineInterface.onPrepared();
            }
        }
    };
    private IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.7
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            int videoWidth = iMediaPlayer.getVideoWidth();
            int videoHeight = iMediaPlayer.getVideoHeight();
            if (videoWidth == 0 || videoHeight == 0 || IjkMediaEngine.this.mMediaEngineInterface == null) {
                return;
            }
            IjkMediaEngine.this.mMediaEngineInterface.onVideoSizeChanged(videoWidth, videoHeight);
        }
    };

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setOptions() {
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void start() {
        this.mMediaPlayer.start();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void initPlayer() {
        this.mMediaPlayer = new IjkMediaPlayer();
        setOptions();
        this.mMediaPlayer.setAudioStreamType(3);
        this.mMediaPlayer.setOnErrorListener(this.onErrorListener);
        this.mMediaPlayer.setOnCompletionListener(this.onCompletionListener);
        this.mMediaPlayer.setOnInfoListener(this.onInfoListener);
        this.mMediaPlayer.setOnBufferingUpdateListener(this.onBufferingUpdateListener);
        this.mMediaPlayer.setOnPreparedListener(this.onPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
        this.mMediaPlayer.setOnNativeInvokeListener(new IjkMediaPlayer.OnNativeInvokeListener() { // from class: com.dueeeke.videoplayer.player.IjkMediaEngine.1
            @Override // tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener
            public boolean onNativeInvoke(int i, Bundle bundle) {
                return true;
            }
        });
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mMediaPlayer.setDataSource(str);
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void pause() {
        this.mMediaPlayer.pause();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void stop() {
        this.mMediaPlayer.stop();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void prepareAsync() {
        this.mMediaPlayer.prepareAsync();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void reset() {
        this.mMediaPlayer.reset();
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
        this.mMediaPlayer.setLooping(this.isLooping);
        setOptions();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public boolean isPlaying() {
        return this.mMediaPlayer.isPlaying();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void seekTo(long j) {
        this.mMediaPlayer.seekTo((int) j);
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void release() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.release();
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public long getCurrentPosition() {
        return this.mMediaPlayer.getCurrentPosition();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public long getDuration() {
        return this.mMediaPlayer.getDuration();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setSurface(Surface surface) {
        this.mMediaPlayer.setSurface(surface);
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mMediaPlayer.setDisplay(surfaceHolder);
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setVolume(int i, int i2) {
        this.mMediaPlayer.setVolume(i, i2);
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setLooping(boolean z) {
        this.isLooping = z;
        this.mMediaPlayer.setLooping(z);
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setEnableMediaCodec(boolean z) {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        long j = z ? 1L : 0L;
        ijkMediaPlayer.setOption(4, "mediacodec", j);
        this.mMediaPlayer.setOption(4, "mediacodec-auto-rotate", j);
        this.mMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", j);
    }
}
