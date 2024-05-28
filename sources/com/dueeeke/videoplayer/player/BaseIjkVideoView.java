package com.dueeeke.videoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.FrameLayout;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.dueeeke.videoplayer.C4202R;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.listener.MediaEngineInterface;
import com.dueeeke.videoplayer.listener.MediaPlayerControl;
import com.dueeeke.videoplayer.listener.VideoListener;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.dueeeke.videoplayer.util.WindowUtil;
import java.io.File;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class BaseIjkVideoView extends FrameLayout implements MediaEngineInterface, MediaPlayerControl {
    protected static final int LANDSCAPE = 2;
    public static final int PLAYER_FULL_SCREEN = 11;
    public static final int PLAYER_NORMAL = 10;
    protected static final int PORTRAIT = 1;
    protected static final int REVERSE_LANDSCAPE = 3;
    public static final int STATE_BUFFERED = 7;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_ERROR = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYBACK_COMPLETED = 5;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    private boolean autoAudioFocus;
    protected int bufferPercentage;
    private CacheListener cacheListener;
    protected int currentOrientation;
    protected boolean isLockFullScreen;
    protected boolean isMute;
    protected VideoListener listener;
    @NonNull
    protected AudioFocusHelper mAudioFocusHelper;
    protected AudioManager mAudioManager;
    protected int mCurrentPlayState;
    protected int mCurrentPlayerState;
    protected int mCurrentPosition;
    protected String mCurrentTitle;
    protected String mCurrentUrl;
    protected BaseMediaEngine mMediaPlayer;
    protected PlayerConfig mPlayerConfig;
    @Nullable
    protected BaseVideoController mVideoController;
    protected OrientationEventListener orientationEventListener;

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public boolean isFullScreen() {
        return false;
    }

    protected abstract void setPlayState(int i);

    protected abstract void setPlayerState(int i);

    protected void onOrientationPortrait(Activity activity) {
        int i;
        if (this.isLockFullScreen || !this.mPlayerConfig.mAutoRotate || (i = this.currentOrientation) == 1) {
            return;
        }
        if ((i == 2 || i == 3) && !isFullScreen()) {
            this.currentOrientation = 1;
            return;
        }
        this.currentOrientation = 1;
        activity.setRequestedOrientation(1);
        stopFullScreen();
    }

    protected void onOrientationLandscape(Activity activity) {
        int i = this.currentOrientation;
        if (i == 2) {
            return;
        }
        if (i == 1 && isFullScreen()) {
            this.currentOrientation = 2;
            return;
        }
        this.currentOrientation = 2;
        if (!isFullScreen()) {
            startFullScreen();
        }
        activity.setRequestedOrientation(0);
    }

    protected void onOrientationReverseLandscape(Activity activity) {
        int i = this.currentOrientation;
        if (i == 3) {
            return;
        }
        if (i == 1 && isFullScreen()) {
            this.currentOrientation = 3;
            return;
        }
        this.currentOrientation = 3;
        if (!isFullScreen()) {
            startFullScreen();
        }
        activity.setRequestedOrientation(8);
    }

    public BaseIjkVideoView(@NonNull Context context) {
        this(context, null);
    }

    public BaseIjkVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseIjkVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentTitle = "";
        this.mCurrentPlayState = 0;
        this.mCurrentPlayerState = 10;
        this.mAudioFocusHelper = new AudioFocusHelper();
        this.currentOrientation = 0;
        this.autoAudioFocus = true;
        this.orientationEventListener = new OrientationEventListener(getContext()) { // from class: com.dueeeke.videoplayer.player.BaseIjkVideoView.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i2) {
                Activity scanForActivity;
                if (BaseIjkVideoView.this.mVideoController == null || (scanForActivity = WindowUtil.scanForActivity(BaseIjkVideoView.this.mVideoController.getContext())) == null) {
                    return;
                }
                if (i2 >= 340) {
                    BaseIjkVideoView.this.onOrientationPortrait(scanForActivity);
                } else if (i2 >= 260 && i2 <= 280) {
                    BaseIjkVideoView.this.onOrientationLandscape(scanForActivity);
                } else if (i2 < 70 || i2 > 90) {
                } else {
                    BaseIjkVideoView.this.onOrientationReverseLandscape(scanForActivity);
                }
            }
        };
        this.cacheListener = new CacheListener() { // from class: com.dueeeke.videoplayer.player.BaseIjkVideoView.3
            @Override // com.danikula.videocache.CacheListener
            public void onCacheAvailable(File file, String str, int i2) {
                BaseIjkVideoView.this.bufferPercentage = i2;
            }
        };
        try {
            this.autoAudioFocus = context.obtainStyledAttributes(attributeSet, C4202R.styleable.UnicomIjkVideoView).getBoolean(C4202R.styleable.UnicomIjkVideoView_autoAudioFocus, true);
        } catch (Exception e) {
            Log.e("BaseIjkVideoView", "BaseIjkVideoView: ", e);
        }
        this.mAudioManager = (AudioManager) getContext().getApplicationContext().getSystemService("audio");
        this.mPlayerConfig = new PlayerConfig.Builder().build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initPlayer() {
        if (this.mMediaPlayer == null) {
            if (this.mPlayerConfig.mBaseMediaEngine != null) {
                this.mMediaPlayer = this.mPlayerConfig.mBaseMediaEngine;
            } else if (this.mPlayerConfig.usingAndroidMediaPlayer) {
                this.mMediaPlayer = new AndroidMediaEngine();
            } else {
                this.mMediaPlayer = new IjkMediaEngine();
            }
            this.mMediaPlayer.setMediaEngineInterface(this);
            this.mMediaPlayer.initPlayer();
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startPrepare(boolean z) {
        String str = this.mCurrentUrl;
        if (str == null || str.trim().equals("")) {
            return;
        }
        if (z) {
            this.mMediaPlayer.reset();
        }
        try {
            if (this.mPlayerConfig.isCache) {
                HttpProxyCacheServer cacheServer = getCacheServer();
                String proxyUrl = cacheServer.getProxyUrl(this.mCurrentUrl);
                cacheServer.registerCacheListener(this.cacheListener, this.mCurrentUrl);
                if (cacheServer.isCached(this.mCurrentUrl)) {
                    this.bufferPercentage = 100;
                }
                this.mMediaPlayer.setDataSource(proxyUrl);
            } else {
                this.mMediaPlayer.setDataSource(this.mCurrentUrl);
            }
            this.mMediaPlayer.prepareAsync();
            this.mCurrentPlayState = 1;
            setPlayState(this.mCurrentPlayState);
            this.mCurrentPlayerState = isFullScreen() ? 11 : 10;
            setPlayerState(this.mCurrentPlayerState);
        } catch (Exception e) {
            onError();
            e.printStackTrace();
        }
    }

    private HttpProxyCacheServer getCacheServer() {
        return VideoCacheManager.getProxy(getContext().getApplicationContext());
    }

    public void setCache2(String str) {
        HttpProxyCacheServer cacheServer = getCacheServer();
        cacheServer.getProxyUrl(str);
        cacheServer.registerCacheListener(new CacheListener() { // from class: com.dueeeke.videoplayer.player.BaseIjkVideoView.2
            @Override // com.danikula.videocache.CacheListener
            public void onCacheAvailable(File file, String str2, int i) {
            }
        }, str);
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void start() {
        if (this.mCurrentPlayState == 0) {
            startPlay();
        } else if (isInPlaybackState()) {
            startInPlaybackState();
        }
        setKeepScreenOn(true);
        this.mAudioFocusHelper.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startPlay() {
        if (this.mPlayerConfig.mAutoRotate) {
            this.orientationEventListener.enable();
        }
        initPlayer();
        startPrepare(false);
    }

    protected void startInPlaybackState() {
        this.mMediaPlayer.start();
        this.mCurrentPlayState = 3;
        setPlayState(this.mCurrentPlayState);
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void pause() {
        try {
            if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
                this.mCurrentPlayState = 4;
                setPlayState(this.mCurrentPlayState);
                setKeepScreenOn(false);
                this.mAudioFocusHelper.abandonFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            if (!isInPlaybackState() || this.mMediaPlayer.isPlaying() || this.mCurrentPlayState == 5) {
                return;
            }
            this.mMediaPlayer.start();
            this.mCurrentPlayState = 3;
            setPlayState(this.mCurrentPlayState);
            this.mAudioFocusHelper.requestFocus();
            setKeepScreenOn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPlayback() {
        BaseMediaEngine baseMediaEngine = this.mMediaPlayer;
        if (baseMediaEngine != null) {
            baseMediaEngine.stop();
            this.mCurrentPlayState = 0;
            setPlayState(this.mCurrentPlayState);
            this.mAudioFocusHelper.abandonFocus();
            setKeepScreenOn(false);
        }
        this.orientationEventListener.disable();
        if (this.mPlayerConfig.isCache) {
            getCacheServer().unregisterCacheListener(this.cacheListener);
        }
        this.isLockFullScreen = false;
        this.mCurrentPosition = 0;
    }

    public void release() {
        BaseMediaEngine baseMediaEngine = this.mMediaPlayer;
        if (baseMediaEngine != null) {
            baseMediaEngine.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentPlayState = 0;
            setPlayState(this.mCurrentPlayState);
            this.mAudioFocusHelper.abandonFocus();
            setKeepScreenOn(false);
        }
        this.orientationEventListener.disable();
        if (this.mPlayerConfig.isCache) {
            getCacheServer().unregisterCacheListener(this.cacheListener);
        }
        this.isLockFullScreen = false;
        this.mCurrentPosition = 0;
    }

    public void setVideoListener(VideoListener videoListener) {
        this.listener = videoListener;
    }

    protected boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentPlayState) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return 0;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            this.mCurrentPosition = (int) this.mMediaPlayer.getCurrentPosition();
            return this.mCurrentPosition;
        }
        return 0;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
        }
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public boolean isPlaying() {
        BaseMediaEngine baseMediaEngine = this.mMediaPlayer;
        return baseMediaEngine != null && baseMediaEngine.isPlaying();
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.bufferPercentage;
        }
        return 0;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void setMute() {
        if (this.isMute) {
            this.mMediaPlayer.setVolume(1, 1);
            this.isMute = false;
            return;
        }
        this.mMediaPlayer.setVolume(0, 0);
        this.isMute = true;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public boolean isMute() {
        return this.isMute;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public void setLock(boolean z) {
        this.isLockFullScreen = z;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaPlayerControl
    public String getTitle() {
        return this.mCurrentTitle;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onError() {
        this.mCurrentPlayState = -1;
        VideoListener videoListener = this.listener;
        if (videoListener != null) {
            videoListener.onError();
        }
        setPlayState(this.mCurrentPlayState);
        this.mCurrentPosition = getCurrentPosition();
    }

    @Override // com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onCompletion() {
        this.mCurrentPlayState = 5;
        VideoListener videoListener = this.listener;
        if (videoListener != null) {
            videoListener.onComplete();
        }
        setPlayState(this.mCurrentPlayState);
        setKeepScreenOn(false);
    }

    @Override // com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onInfo(int i, int i2) {
        VideoListener videoListener = this.listener;
        if (videoListener != null) {
            videoListener.onInfo(i, i2);
        }
        if (i != 3) {
            switch (i) {
                case 701:
                    this.mCurrentPlayState = 6;
                    setPlayState(this.mCurrentPlayState);
                    return;
                case 702:
                    this.mCurrentPlayState = 7;
                    setPlayState(this.mCurrentPlayState);
                    return;
                default:
                    return;
            }
        }
        this.mCurrentPlayState = 3;
        setPlayState(this.mCurrentPlayState);
        if (getWindowVisibility() != 0) {
            pause();
        }
    }

    @Override // com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onBufferingUpdate(int i) {
        if (this.mPlayerConfig.isCache) {
            return;
        }
        this.bufferPercentage = i;
    }

    @Override // com.dueeeke.videoplayer.listener.MediaEngineInterface
    public void onPrepared() {
        this.mCurrentPlayState = 2;
        VideoListener videoListener = this.listener;
        if (videoListener != null) {
            videoListener.onPrepared();
        }
        setPlayState(this.mCurrentPlayState);
        int i = this.mCurrentPosition;
        if (i > 0) {
            seekTo(i);
        }
    }

    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.mPlayerConfig = playerConfig;
    }

    public int getCurrentPlayerState() {
        return this.mCurrentPlayerState;
    }

    public int getCurrentPlayState() {
        return this.mCurrentPlayState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AudioFocusHelper implements AudioManager.OnAudioFocusChangeListener {
        int currentFocus;
        boolean pausedForLoss;
        boolean startRequested;

        private AudioFocusHelper() {
            this.startRequested = false;
            this.pausedForLoss = false;
            this.currentFocus = 0;
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (this.currentFocus == i) {
                return;
            }
            this.currentFocus = i;
            switch (i) {
                case -3:
                case -2:
                    if (BaseIjkVideoView.this.isPlaying()) {
                        this.pausedForLoss = true;
                        BaseIjkVideoView.this.pause();
                        return;
                    }
                    return;
                case -1:
                    if (BaseIjkVideoView.this.isPlaying()) {
                        this.pausedForLoss = true;
                        BaseIjkVideoView.this.pause();
                        return;
                    }
                    return;
                case 0:
                default:
                    return;
                case 1:
                case 2:
                    if (this.startRequested || this.pausedForLoss) {
                        BaseIjkVideoView.this.start();
                        this.startRequested = false;
                        this.pausedForLoss = false;
                        return;
                    }
                    return;
            }
        }

        boolean requestFocus() {
            if (this.currentFocus == 1) {
                return true;
            }
            if (BaseIjkVideoView.this.mAudioManager == null) {
                return false;
            }
            if (1 == BaseIjkVideoView.this.mAudioManager.requestAudioFocus(this, 3, BaseIjkVideoView.this.autoAudioFocus ? 1 : -3)) {
                this.currentFocus = 1;
                return true;
            }
            this.startRequested = true;
            return false;
        }

        boolean abandonFocus() {
            if (BaseIjkVideoView.this.mAudioManager == null) {
                return false;
            }
            this.startRequested = false;
            return 1 == BaseIjkVideoView.this.mAudioManager.abandonAudioFocus(this);
        }
    }
}
