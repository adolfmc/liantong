package com.mabeijianxi.smallvideorecord2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SurfaceVideoView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int HANDLER_MESSAGE_LOOP = 1;
    private static final int HANDLER_MESSAGE_PARSE = 0;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_RELEASED = 5;
    private MediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentState;
    private int mDuration;
    private MediaPlayer.OnErrorListener mErrorListener;
    MediaPlayer.OnInfoListener mInfoListener;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnInfoListener mOnInfoListener;
    private OnPlayStateListener mOnPlayStateListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    private MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    private MediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    MediaPlayer.OnPreparedListener mPreparedListener;
    private MediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private SurfaceHolder mSurfaceHolder;
    private int mTargetState;
    private Uri mUri;
    private Handler mVideoHandler;
    private int mVideoHeight;
    MediaPlayer.OnVideoSizeChangedListener mVideoSizeChangedListener;
    private int mVideoWidth;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnPlayStateListener {
        void onStateChanged(boolean z);
    }

    public SurfaceVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMediaPlayer = null;
        this.mSurfaceHolder = null;
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                SurfaceVideoView.this.mCurrentState = 5;
                if (SurfaceVideoView.this.mOnCompletionListener != null) {
                    SurfaceVideoView.this.mOnCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (SurfaceVideoView.this.mCurrentState == 1) {
                    SurfaceVideoView.this.mCurrentState = 2;
                    try {
                        SurfaceVideoView.this.mDuration = mediaPlayer.getDuration();
                    } catch (IllegalStateException unused) {
                    }
                    try {
                        SurfaceVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                        SurfaceVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                    } catch (IllegalStateException unused2) {
                    }
                    switch (SurfaceVideoView.this.mTargetState) {
                        case 2:
                            if (SurfaceVideoView.this.mOnPreparedListener != null) {
                                SurfaceVideoView.this.mOnPreparedListener.onPrepared(SurfaceVideoView.this.mMediaPlayer);
                                return;
                            }
                            return;
                        case 3:
                            SurfaceVideoView.this.start();
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.3
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                SurfaceVideoView.this.mVideoWidth = i2;
                SurfaceVideoView.this.mVideoHeight = i3;
                if (SurfaceVideoView.this.mOnVideoSizeChangedListener != null) {
                    SurfaceVideoView.this.mOnVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.4
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (SurfaceVideoView.this.mOnInfoListener != null) {
                    SurfaceVideoView.this.mOnInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.5
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (SurfaceVideoView.this.mOnSeekCompleteListener != null) {
                    SurfaceVideoView.this.mOnSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                SurfaceVideoView.this.mCurrentState = -1;
                if (SurfaceVideoView.this.mOnErrorListener != null) {
                    SurfaceVideoView.this.mOnErrorListener.onError(mediaPlayer, i2, i3);
                    return true;
                }
                return true;
            }
        };
        this.mVideoHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.7
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        SurfaceVideoView.this.pause();
                        break;
                    case 1:
                        if (SurfaceVideoView.this.isPlaying()) {
                            SurfaceVideoView.this.seekTo(message.arg1);
                            sendMessageDelayed(SurfaceVideoView.this.mVideoHandler.obtainMessage(1, message.arg1, message.arg2), message.arg2);
                            break;
                        }
                        break;
                }
                super.handleMessage(message);
            }
        };
        initVideoView();
    }

    public SurfaceVideoView(Context context) {
        super(context);
        this.mMediaPlayer = null;
        this.mSurfaceHolder = null;
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                SurfaceVideoView.this.mCurrentState = 5;
                if (SurfaceVideoView.this.mOnCompletionListener != null) {
                    SurfaceVideoView.this.mOnCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (SurfaceVideoView.this.mCurrentState == 1) {
                    SurfaceVideoView.this.mCurrentState = 2;
                    try {
                        SurfaceVideoView.this.mDuration = mediaPlayer.getDuration();
                    } catch (IllegalStateException unused) {
                    }
                    try {
                        SurfaceVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                        SurfaceVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                    } catch (IllegalStateException unused2) {
                    }
                    switch (SurfaceVideoView.this.mTargetState) {
                        case 2:
                            if (SurfaceVideoView.this.mOnPreparedListener != null) {
                                SurfaceVideoView.this.mOnPreparedListener.onPrepared(SurfaceVideoView.this.mMediaPlayer);
                                return;
                            }
                            return;
                        case 3:
                            SurfaceVideoView.this.start();
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.3
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                SurfaceVideoView.this.mVideoWidth = i2;
                SurfaceVideoView.this.mVideoHeight = i3;
                if (SurfaceVideoView.this.mOnVideoSizeChangedListener != null) {
                    SurfaceVideoView.this.mOnVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.4
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (SurfaceVideoView.this.mOnInfoListener != null) {
                    SurfaceVideoView.this.mOnInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.5
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (SurfaceVideoView.this.mOnSeekCompleteListener != null) {
                    SurfaceVideoView.this.mOnSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                SurfaceVideoView.this.mCurrentState = -1;
                if (SurfaceVideoView.this.mOnErrorListener != null) {
                    SurfaceVideoView.this.mOnErrorListener.onError(mediaPlayer, i2, i3);
                    return true;
                }
                return true;
            }
        };
        this.mVideoHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.7
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        SurfaceVideoView.this.pause();
                        break;
                    case 1:
                        if (SurfaceVideoView.this.isPlaying()) {
                            SurfaceVideoView.this.seekTo(message.arg1);
                            sendMessageDelayed(SurfaceVideoView.this.mVideoHandler.obtainMessage(1, message.arg1, message.arg2), message.arg2);
                            break;
                        }
                        break;
                }
                super.handleMessage(message);
            }
        };
        initVideoView();
    }

    public SurfaceVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMediaPlayer = null;
        this.mSurfaceHolder = null;
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                SurfaceVideoView.this.mCurrentState = 5;
                if (SurfaceVideoView.this.mOnCompletionListener != null) {
                    SurfaceVideoView.this.mOnCompletionListener.onCompletion(mediaPlayer);
                }
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (SurfaceVideoView.this.mCurrentState == 1) {
                    SurfaceVideoView.this.mCurrentState = 2;
                    try {
                        SurfaceVideoView.this.mDuration = mediaPlayer.getDuration();
                    } catch (IllegalStateException unused) {
                    }
                    try {
                        SurfaceVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                        SurfaceVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                    } catch (IllegalStateException unused2) {
                    }
                    switch (SurfaceVideoView.this.mTargetState) {
                        case 2:
                            if (SurfaceVideoView.this.mOnPreparedListener != null) {
                                SurfaceVideoView.this.mOnPreparedListener.onPrepared(SurfaceVideoView.this.mMediaPlayer);
                                return;
                            }
                            return;
                        case 3:
                            SurfaceVideoView.this.start();
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.mVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.3
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                SurfaceVideoView.this.mVideoWidth = i2;
                SurfaceVideoView.this.mVideoHeight = i3;
                if (SurfaceVideoView.this.mOnVideoSizeChangedListener != null) {
                    SurfaceVideoView.this.mOnVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i2, i3);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.4
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (SurfaceVideoView.this.mOnInfoListener != null) {
                    SurfaceVideoView.this.mOnInfoListener.onInfo(mediaPlayer, i2, i3);
                    return false;
                }
                return false;
            }
        };
        this.mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.5
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (SurfaceVideoView.this.mOnSeekCompleteListener != null) {
                    SurfaceVideoView.this.mOnSeekCompleteListener.onSeekComplete(mediaPlayer);
                }
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                SurfaceVideoView.this.mCurrentState = -1;
                if (SurfaceVideoView.this.mOnErrorListener != null) {
                    SurfaceVideoView.this.mOnErrorListener.onError(mediaPlayer, i2, i3);
                    return true;
                }
                return true;
            }
        };
        this.mVideoHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.SurfaceVideoView.7
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        SurfaceVideoView.this.pause();
                        break;
                    case 1:
                        if (SurfaceVideoView.this.isPlaying()) {
                            SurfaceVideoView.this.seekTo(message.arg1);
                            sendMessageDelayed(SurfaceVideoView.this.mVideoHandler.obtainMessage(1, message.arg1, message.arg2), message.arg2);
                            break;
                        }
                        break;
                }
                super.handleMessage(message);
            }
        };
        initVideoView();
    }

    protected void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        getHolder().setFormat(1);
        getHolder().addCallback(this);
        if (Build.VERSION.SDK_INT < 11) {
            getHolder().setType(3);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public static float getSystemVolumn(Context context) {
        if (context != null) {
            try {
                AudioManager audioManager = (AudioManager) context.getSystemService("audio");
                return (audioManager.getStreamVolume(3) * 1.0f) / audioManager.getStreamMaxVolume(3);
            } catch (UnsupportedOperationException unused) {
                return 0.5f;
            }
        }
        return 0.5f;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnPlayStateListener(OnPlayStateListener onPlayStateListener) {
        this.mOnPlayStateListener = onPlayStateListener;
    }

    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.mOnSeekCompleteListener = onSeekCompleteListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setVideoPath(String str) {
        if (StringUtils.isNotEmpty(str)) {
            this.mTargetState = 2;
            openVideo(Uri.parse(str));
        }
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public void reOpen() {
        this.mTargetState = 2;
        openVideo(this.mUri);
    }

    public int getDuration() {
        return this.mDuration;
    }

    private void tryAgain(Exception exc) {
        this.mCurrentState = -1;
        openVideo(this.mUri);
    }

    public void start() {
        this.mTargetState = 3;
        if (this.mMediaPlayer != null) {
            int i = this.mCurrentState;
            if (i == 2 || i == 4 || i == 3 || i == 5) {
                try {
                    if (!isPlaying()) {
                        this.mMediaPlayer.start();
                    }
                    this.mCurrentState = 3;
                    if (this.mOnPlayStateListener != null) {
                        this.mOnPlayStateListener.onStateChanged(true);
                    }
                } catch (IllegalStateException e) {
                    tryAgain(e);
                } catch (Exception e2) {
                    tryAgain(e2);
                }
            }
        }
    }

    public void pause() {
        this.mTargetState = 4;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || this.mCurrentState != 3) {
            return;
        }
        try {
            mediaPlayer.pause();
            this.mCurrentState = 4;
            if (this.mOnPlayStateListener != null) {
                this.mOnPlayStateListener.onStateChanged(false);
            }
        } catch (IllegalStateException e) {
            tryAgain(e);
        } catch (Exception e2) {
            tryAgain(e2);
        }
    }

    public void dispatchKeyEvent(Context context, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 24:
            case 25:
                setVolume(getSystemVolumn(context));
                return;
            default:
                return;
        }
    }

    public void setVolume(float f) {
        if (this.mMediaPlayer != null) {
            int i = this.mCurrentState;
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                try {
                    this.mMediaPlayer.setVolume(f, f);
                } catch (Exception unused) {
                }
            }
        }
    }

    public void setLooping(boolean z) {
        if (this.mMediaPlayer != null) {
            int i = this.mCurrentState;
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                try {
                    this.mMediaPlayer.setLooping(z);
                } catch (Exception unused) {
                }
            }
        }
    }

    public void seekTo(int i) {
        if (this.mMediaPlayer != null) {
            int i2 = this.mCurrentState;
            if (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) {
                if (i < 0) {
                    i = 0;
                }
                try {
                    this.mMediaPlayer.seekTo(i);
                } catch (IllegalStateException | Exception unused) {
                }
            }
        }
    }

    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            switch (this.mCurrentState) {
                case 3:
                case 4:
                    try {
                        return mediaPlayer.getCurrentPosition();
                    } catch (IllegalStateException | Exception unused) {
                        break;
                    }
                case 5:
                    return getDuration();
            }
        }
        return 0;
    }

    public boolean isPlaying() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || this.mCurrentState != 3) {
            return false;
        }
        try {
            return mediaPlayer.isPlaying();
        } catch (IllegalStateException | Exception unused) {
            return false;
        }
    }

    public void release() {
        this.mTargetState = 5;
        this.mCurrentState = 5;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (IllegalStateException | Exception unused) {
            }
            this.mMediaPlayer = null;
        }
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceHolder;
    }

    public void openVideo(Uri uri) {
        if (uri == null || this.mSurfaceHolder == null || getContext() == null) {
            if (this.mSurfaceHolder != null || uri == null) {
                return;
            }
            this.mUri = uri;
            return;
        }
        this.mUri = uri;
        this.mDuration = 0;
        IOException iOException = null;
        try {
            if (this.mMediaPlayer == null) {
                this.mMediaPlayer = new MediaPlayer();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mVideoSizeChangedListener);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
            } else {
                this.mMediaPlayer.reset();
            }
            this.mMediaPlayer.setDataSource(getContext(), uri);
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 1;
        } catch (IOException e) {
            iOException = e;
        } catch (IllegalArgumentException e2) {
            iOException = e2;
        } catch (Exception e3) {
            iOException = e3;
        }
        if (iOException != null) {
            this.mCurrentState = -1;
            MediaPlayer.OnErrorListener onErrorListener = this.mErrorListener;
            if (onErrorListener != null) {
                onErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        }
    }

    public boolean isPrepared() {
        return this.mMediaPlayer != null && this.mCurrentState == 2;
    }

    public boolean isComplate() {
        return this.mMediaPlayer != null && this.mCurrentState == 5;
    }

    public boolean isRelease() {
        int i;
        return this.mMediaPlayer == null || (i = this.mCurrentState) == 0 || i == -1 || i == 5;
    }

    public void pauseDelayed(int i) {
        if (this.mVideoHandler.hasMessages(0)) {
            this.mVideoHandler.removeMessages(0);
        }
        this.mVideoHandler.sendEmptyMessageDelayed(0, i);
    }

    public void pauseClearDelayed() {
        pause();
        if (this.mVideoHandler.hasMessages(0)) {
            this.mVideoHandler.removeMessages(0);
        }
        if (this.mVideoHandler.hasMessages(1)) {
            this.mVideoHandler.removeMessages(1);
        }
    }

    public void loopDelayed(int i, int i2) {
        if (this.mVideoHandler.hasMessages(0)) {
            this.mVideoHandler.removeMessages(0);
        }
        if (this.mVideoHandler.hasMessages(1)) {
            this.mVideoHandler.removeMessages(1);
        }
        int i3 = i2 - i;
        seekTo(i);
        if (!isPlaying()) {
            start();
        }
        if (this.mVideoHandler.hasMessages(1)) {
            this.mVideoHandler.removeMessages(1);
        }
        Handler handler = this.mVideoHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, getCurrentPosition(), i3), i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        boolean z = this.mSurfaceHolder == null;
        this.mSurfaceHolder = surfaceHolder;
        if (z) {
            reOpen();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mSurfaceHolder = surfaceHolder;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = null;
        release();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
