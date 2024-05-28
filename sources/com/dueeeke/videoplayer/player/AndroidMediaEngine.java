package com.dueeeke.videoplayer.player;

import android.media.MediaPlayer;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AndroidMediaEngine extends BaseMediaEngine {
    private boolean isLooping;
    protected MediaPlayer mMediaPlayer;
    private MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.dueeeke.videoplayer.player.AndroidMediaEngine.1
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            if (AndroidMediaEngine.this.mMediaEngineInterface != null) {
                AndroidMediaEngine.this.mMediaEngineInterface.onError();
                return true;
            }
            return true;
        }
    };
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.dueeeke.videoplayer.player.AndroidMediaEngine.2
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (AndroidMediaEngine.this.mMediaEngineInterface != null) {
                AndroidMediaEngine.this.mMediaEngineInterface.onCompletion();
            }
        }
    };
    private MediaPlayer.OnInfoListener onInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.dueeeke.videoplayer.player.AndroidMediaEngine.3
        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (AndroidMediaEngine.this.mMediaEngineInterface != null) {
                AndroidMediaEngine.this.mMediaEngineInterface.onInfo(i, i2);
                return true;
            }
            return true;
        }
    };
    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.dueeeke.videoplayer.player.AndroidMediaEngine.4
        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            if (AndroidMediaEngine.this.mMediaEngineInterface != null) {
                AndroidMediaEngine.this.mMediaEngineInterface.onBufferingUpdate(i);
            }
        }
    };
    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.dueeeke.videoplayer.player.AndroidMediaEngine.5
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            if (AndroidMediaEngine.this.mMediaEngineInterface != null) {
                AndroidMediaEngine.this.mMediaEngineInterface.onPrepared();
            }
        }
    };
    private MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.dueeeke.videoplayer.player.AndroidMediaEngine.6
        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            int videoWidth = mediaPlayer.getVideoWidth();
            int videoHeight = mediaPlayer.getVideoHeight();
            if (videoWidth == 0 || videoHeight == 0 || AndroidMediaEngine.this.mMediaEngineInterface == null) {
                return;
            }
            AndroidMediaEngine.this.mMediaEngineInterface.onVideoSizeChanged(videoWidth, videoHeight);
        }
    };

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setEnableMediaCodec(boolean z) {
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void setOptions() {
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void start() {
        this.mMediaPlayer.start();
    }

    @Override // com.dueeeke.videoplayer.player.BaseMediaEngine
    public void initPlayer() {
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setAudioStreamType(3);
        this.mMediaPlayer.setOnErrorListener(this.onErrorListener);
        this.mMediaPlayer.setOnCompletionListener(this.onCompletionListener);
        this.mMediaPlayer.setOnInfoListener(this.onInfoListener);
        this.mMediaPlayer.setOnBufferingUpdateListener(this.onBufferingUpdateListener);
        this.mMediaPlayer.setOnPreparedListener(this.onPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
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
        this.mMediaPlayer.setVolume(1.0f, 1.0f);
        this.mMediaPlayer.reset();
        this.mMediaPlayer.setLooping(this.isLooping);
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
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
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
}
