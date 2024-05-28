package com.baidu.cloud.cloudplayer.videoview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.IRenderView;
import com.baidu.cloud.media.player.BDCloudMediaPlayer;
import com.baidu.cloud.media.player.BDTimedText;
import com.baidu.cloud.media.player.IMediaPlayer;
import com.baidu.cloud.videocache.CacheListener;
import com.baidu.cloud.videocache.IVideoCache;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.common.UIUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDCloudVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    private static final int MESSAGE_CHANGE_CACHING = 1;
    private static final int MESSAGE_SWITCH_STATE_CHANGED = 2;
    private static final String TAG = "BDCloudVideoView";
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT = 1;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING = 2;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_MATCH_PARENT = 3;
    private static boolean mProxyCacheEnable;
    private RelativeLayout cachingHintViewRl;
    private ProgressBar cachingProgressBar;
    private TextView cachingProgressHint;
    private String imgUrl;
    public boolean isInWebView;
    private boolean isTryToPlaying;
    private Context mAppContext;
    private int mBufferSizeInBytes;
    private IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private int mCacheTimeInMilliSeconds;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private IMediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentAspectRatio;
    private int mCurrentBufferPercentage;
    private PlayerState mCurrentState;
    private int mDecodeMode;
    private String mDrmToken;
    private IMediaPlayer.OnErrorListener mErrorListener;
    private int mFrameChasing;
    private Map<String, String> mHeaders;
    private IMediaPlayer.OnInfoListener mInfoListener;
    private long mInitPlayPositionInMilliSec;
    private float mLeftVolume;
    private boolean mLogEnabled;
    private boolean mLooping;
    private int mMaxCacheDurationInMs;
    private int mMaxCacheSizeInBytes;
    private int mMaxProbeSizeInBytes;
    private int mMaxProbeTimeInMs;
    private String[] mMediaItems;
    private BDCloudMediaPlayer mMediaPlayer;
    private IMediaPlayer.OnMetadataListener mMetadataListener;
    private String[] mMutilMediasDsc;
    private IMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnMetadataListener mOnMetadataListener;
    private OnPlayerStateListener mOnPlayerStateListener;
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    private IMediaPlayer.OnTimedTextListener mOnTimedTextListener;
    private int mPlayType;
    IMediaPlayer.OnPreparedListener mPreparedListener;
    private IVideoCache mProxyCacheManager;
    private IRenderView mRenderView;
    private float mRightVolume;
    IRenderView.IRenderCallback mSHCallback;
    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private boolean mShowSourceSwitchInfo;
    IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private float mSpeed;
    private int mSurfaceHeight;
    private IRenderView.ISurfaceHolder mSurfaceHolder;
    private int mSurfaceWidth;
    private Uri mUri;
    private boolean mUseTextureViewFirst;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private int mWakeMode;
    private Handler mainThreadHandler;
    private boolean mbShowCacheInfo;
    private Object playList;
    private FrameLayout renderRootView;
    private TextView subtitleDisplay;
    private int switchMode;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnPlayerStateListener {
        void onPlayerStateChanged(PlayerState playerState);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public void setVideoProxy(IVideoCache iVideoCache) {
    }

    public void setPlayList(Object obj) {
        this.playList = obj;
    }

    public Object getPlayList() {
        return this.playList;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum PlayerState {
        STATE_ERROR(-1),
        STATE_IDLE(0),
        STATE_PREPARING(1),
        STATE_PREPARED(2),
        STATE_PLAYING(3),
        STATE_PAUSED(4),
        STATE_PLAYBACK_COMPLETED(5),
        STATE_VIDEOSIZE_CHANGED(6);
        
        private int code;

        PlayerState(int i) {
            this.code = i;
        }
    }

    public PlayerState getCurrentPlayerState() {
        return this.mCurrentState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentState(PlayerState playerState) {
        if (this.mCurrentState != playerState) {
            this.mCurrentState = playerState;
            OnPlayerStateListener onPlayerStateListener = this.mOnPlayerStateListener;
            if (onPlayerStateListener != null) {
                onPlayerStateListener.onPlayerStateChanged(this.mCurrentState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentState(PlayerState playerState, boolean z) {
        if (z || this.mCurrentState != playerState) {
            this.mCurrentState = playerState;
            OnPlayerStateListener onPlayerStateListener = this.mOnPlayerStateListener;
            if (onPlayerStateListener != null) {
                onPlayerStateListener.onPlayerStateChanged(this.mCurrentState);
            }
        }
    }

    public void setOnPlayerStateListener(OnPlayerStateListener onPlayerStateListener) {
        this.mOnPlayerStateListener = onPlayerStateListener;
    }

    public BDCloudVideoView(Context context) {
        super(context);
        this.mUseTextureViewFirst = true;
        this.switchMode = 0;
        this.mPlayType = 0;
        this.mShowSourceSwitchInfo = true;
        this.mCurrentState = PlayerState.STATE_IDLE;
        this.isTryToPlaying = false;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mDrmToken = null;
        this.mCurrentAspectRatio = 0;
        this.mCacheTimeInMilliSeconds = 0;
        this.mbShowCacheInfo = true;
        this.mDecodeMode = 0;
        this.mLogEnabled = false;
        this.mInitPlayPositionInMilliSec = 0L;
        this.mWakeMode = 0;
        this.mLeftVolume = -1.0f;
        this.mRightVolume = -1.0f;
        this.mMaxProbeTimeInMs = -1;
        this.mMaxProbeSizeInBytes = 0;
        this.mMaxCacheSizeInBytes = 0;
        this.mMaxCacheDurationInMs = -1;
        this.mLooping = false;
        this.mBufferSizeInBytes = 0;
        this.mFrameChasing = -1;
        this.mSpeed = 1.0f;
        this.cachingHintViewRl = null;
        this.cachingProgressBar = null;
        this.cachingProgressHint = null;
        this.renderRootView = null;
        this.mainThreadHandler = new Handler(Looper.getMainLooper()) { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    return;
                }
                if (message.what == 10005) {
                    UIUtils.toast("清晰度切换中...");
                } else if (message.what == 10006) {
                    if (message.arg2 < 0) {
                        UIUtils.toast("清晰度切换失败!");
                    } else {
                        UIUtils.toast("清晰度切换成功！");
                    }
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.2
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                Log.d("BDCloudVideoView", "onVideoSizeChanged width=" + i + ";height=" + i2 + ";sarNum=" + i3 + ";sarDen=" + i4);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                BDCloudVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                BDCloudVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (BDCloudVideoView.this.mVideoWidth != 0 && BDCloudVideoView.this.mVideoHeight != 0) {
                    if (BDCloudVideoView.this.mRenderView != null) {
                        BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                        BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    }
                    BDCloudVideoView.this.requestLayout();
                }
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_VIDEOSIZE_CHANGED, true);
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.3
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onPrepared");
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PREPARED);
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                if (BDCloudVideoView.this.mOnPreparedListener != null) {
                    BDCloudVideoView.this.mOnPreparedListener.onPrepared(BDCloudVideoView.this.mMediaPlayer);
                }
                Log.d("BDCloudVideoView", "onPrepared: mVideoWidth=" + BDCloudVideoView.this.mVideoWidth + ";mVideoHeight=" + BDCloudVideoView.this.mVideoHeight + ";mSurfaceWidth=" + BDCloudVideoView.this.mSurfaceWidth + ";mSurfaceHeight=" + BDCloudVideoView.this.mSurfaceHeight);
                if (BDCloudVideoView.this.mVideoWidth == 0 || BDCloudVideoView.this.mVideoHeight == 0) {
                    if (BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                } else if (BDCloudVideoView.this.mRenderView != null) {
                    BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                    BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    if ((!BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mSurfaceWidth == BDCloudVideoView.this.mVideoWidth && BDCloudVideoView.this.mSurfaceHeight == BDCloudVideoView.this.mVideoHeight)) && BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.4
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onCompletion");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PLAYBACK_COMPLETED);
                BDCloudVideoView.this.isTryToPlaying = false;
                if (BDCloudVideoView.this.mOnCompletionListener != null) {
                    BDCloudVideoView.this.mOnCompletionListener.onCompletion(BDCloudVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.5
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("BDCloudVideoView", "onInfo: arg1=" + i + "; arg2=" + i2);
                if (BDCloudVideoView.this.mOnInfoListener != null) {
                    BDCloudVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                switch (i) {
                    case 3:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_RENDERING_START:");
                        break;
                    case 700:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_START:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(true);
                        break;
                    case 702:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_END:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                        break;
                    case 703:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        break;
                    case 800:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BAD_INTERLEAVING:");
                        break;
                    case 801:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NOT_SEEKABLE:");
                        break;
                    case 802:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_METADATA_UPDATE:");
                        break;
                    case 901:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                        break;
                    case 902:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                        break;
                    case 10001:
                        BDCloudVideoView.this.mVideoRotationDegree = i2;
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                        if (BDCloudVideoView.this.mRenderView != null) {
                            BDCloudVideoView.this.mRenderView.setVideoRotation(i2);
                            break;
                        }
                        break;
                    case 10002:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_AUDIO_RENDERING_START:");
                        break;
                    case 10005:
                        Log.i("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_START:");
                        BDCloudVideoView.this.showSourceSwitchState(i, 0);
                        break;
                    case 10006:
                        BDCloudVideoView.this.showSourceSwitchState(i, i2);
                        Log.d("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_END:");
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.6
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("BDCloudVideoView", "onError: " + i + "," + i2);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_ERROR);
                BDCloudVideoView.this.isTryToPlaying = false;
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                return (BDCloudVideoView.this.mOnErrorListener == null || BDCloudVideoView.this.mOnErrorListener.onError(BDCloudVideoView.this.mMediaPlayer, i, i2)) ? true : true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.7
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                BDCloudVideoView.this.mCurrentBufferPercentage = i;
                if (BDCloudVideoView.this.mOnBufferingUpdateListener != null) {
                    BDCloudVideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.8
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onSeekComplete");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                if (BDCloudVideoView.this.mOnSeekCompleteListener != null) {
                    BDCloudVideoView.this.mOnSeekCompleteListener.onSeekComplete(iMediaPlayer);
                }
            }
        };
        this.mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.9
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnTimedTextListener
            public void onTimedText(IMediaPlayer iMediaPlayer, BDTimedText bDTimedText) {
                Log.d("BDCloudVideoView", "onTimedText text=" + bDTimedText.getText());
                if (bDTimedText != null) {
                    BDCloudVideoView.this.subtitleDisplay.setText(bDTimedText.getText());
                }
            }
        };
        this.mMetadataListener = new IMediaPlayer.OnMetadataListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.10
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnMetadataListener
            public void onMetadata(IMediaPlayer iMediaPlayer, Bundle bundle) {
                for (String str : bundle.keySet()) {
                    Log.d("BDCloudVideoView", "onMetadata: key = " + str + ", value = " + bundle.getString(str));
                }
                if (BDCloudVideoView.this.mOnMetadataListener != null) {
                    BDCloudVideoView.this.mOnMetadataListener.onMetadata(iMediaPlayer, bundle);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.11
            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                Log.d("BDCloudVideoView", "mSHCallback onSurfaceChanged");
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceWidth = i2;
                    BDCloudVideoView.this.mSurfaceHeight = i3;
                    boolean z = BDCloudVideoView.this.isTryToPlaying;
                    boolean z2 = !BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mVideoWidth == i2 && BDCloudVideoView.this.mVideoHeight == i3);
                    if (BDCloudVideoView.this.mMediaPlayer != null && z && z2) {
                        BDCloudVideoView.this.start();
                        return;
                    }
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceChanged: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = iSurfaceHolder;
                    if (BDCloudVideoView.this.mMediaPlayer == null) {
                        BDCloudVideoView.this.openVideo();
                        return;
                    }
                    BDCloudVideoView bDCloudVideoView = BDCloudVideoView.this;
                    bDCloudVideoView.bindSurfaceHolder(bDCloudVideoView.mMediaPlayer, iSurfaceHolder);
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceCreated: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = null;
                    BDCloudVideoView.this.releaseWithoutStop();
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceDestroyed: unmatched render callback\n");
            }
        };
        initVideoView(context);
    }

    public BDCloudVideoView(Context context, boolean z) {
        super(context);
        this.mUseTextureViewFirst = true;
        this.switchMode = 0;
        this.mPlayType = 0;
        this.mShowSourceSwitchInfo = true;
        this.mCurrentState = PlayerState.STATE_IDLE;
        this.isTryToPlaying = false;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mDrmToken = null;
        this.mCurrentAspectRatio = 0;
        this.mCacheTimeInMilliSeconds = 0;
        this.mbShowCacheInfo = true;
        this.mDecodeMode = 0;
        this.mLogEnabled = false;
        this.mInitPlayPositionInMilliSec = 0L;
        this.mWakeMode = 0;
        this.mLeftVolume = -1.0f;
        this.mRightVolume = -1.0f;
        this.mMaxProbeTimeInMs = -1;
        this.mMaxProbeSizeInBytes = 0;
        this.mMaxCacheSizeInBytes = 0;
        this.mMaxCacheDurationInMs = -1;
        this.mLooping = false;
        this.mBufferSizeInBytes = 0;
        this.mFrameChasing = -1;
        this.mSpeed = 1.0f;
        this.cachingHintViewRl = null;
        this.cachingProgressBar = null;
        this.cachingProgressHint = null;
        this.renderRootView = null;
        this.mainThreadHandler = new Handler(Looper.getMainLooper()) { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    return;
                }
                if (message.what == 10005) {
                    UIUtils.toast("清晰度切换中...");
                } else if (message.what == 10006) {
                    if (message.arg2 < 0) {
                        UIUtils.toast("清晰度切换失败!");
                    } else {
                        UIUtils.toast("清晰度切换成功！");
                    }
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.2
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                Log.d("BDCloudVideoView", "onVideoSizeChanged width=" + i + ";height=" + i2 + ";sarNum=" + i3 + ";sarDen=" + i4);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                BDCloudVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                BDCloudVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (BDCloudVideoView.this.mVideoWidth != 0 && BDCloudVideoView.this.mVideoHeight != 0) {
                    if (BDCloudVideoView.this.mRenderView != null) {
                        BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                        BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    }
                    BDCloudVideoView.this.requestLayout();
                }
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_VIDEOSIZE_CHANGED, true);
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.3
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onPrepared");
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PREPARED);
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                if (BDCloudVideoView.this.mOnPreparedListener != null) {
                    BDCloudVideoView.this.mOnPreparedListener.onPrepared(BDCloudVideoView.this.mMediaPlayer);
                }
                Log.d("BDCloudVideoView", "onPrepared: mVideoWidth=" + BDCloudVideoView.this.mVideoWidth + ";mVideoHeight=" + BDCloudVideoView.this.mVideoHeight + ";mSurfaceWidth=" + BDCloudVideoView.this.mSurfaceWidth + ";mSurfaceHeight=" + BDCloudVideoView.this.mSurfaceHeight);
                if (BDCloudVideoView.this.mVideoWidth == 0 || BDCloudVideoView.this.mVideoHeight == 0) {
                    if (BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                } else if (BDCloudVideoView.this.mRenderView != null) {
                    BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                    BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    if ((!BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mSurfaceWidth == BDCloudVideoView.this.mVideoWidth && BDCloudVideoView.this.mSurfaceHeight == BDCloudVideoView.this.mVideoHeight)) && BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.4
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onCompletion");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PLAYBACK_COMPLETED);
                BDCloudVideoView.this.isTryToPlaying = false;
                if (BDCloudVideoView.this.mOnCompletionListener != null) {
                    BDCloudVideoView.this.mOnCompletionListener.onCompletion(BDCloudVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.5
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("BDCloudVideoView", "onInfo: arg1=" + i + "; arg2=" + i2);
                if (BDCloudVideoView.this.mOnInfoListener != null) {
                    BDCloudVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                switch (i) {
                    case 3:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_RENDERING_START:");
                        break;
                    case 700:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_START:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(true);
                        break;
                    case 702:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_END:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                        break;
                    case 703:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        break;
                    case 800:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BAD_INTERLEAVING:");
                        break;
                    case 801:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NOT_SEEKABLE:");
                        break;
                    case 802:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_METADATA_UPDATE:");
                        break;
                    case 901:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                        break;
                    case 902:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                        break;
                    case 10001:
                        BDCloudVideoView.this.mVideoRotationDegree = i2;
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                        if (BDCloudVideoView.this.mRenderView != null) {
                            BDCloudVideoView.this.mRenderView.setVideoRotation(i2);
                            break;
                        }
                        break;
                    case 10002:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_AUDIO_RENDERING_START:");
                        break;
                    case 10005:
                        Log.i("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_START:");
                        BDCloudVideoView.this.showSourceSwitchState(i, 0);
                        break;
                    case 10006:
                        BDCloudVideoView.this.showSourceSwitchState(i, i2);
                        Log.d("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_END:");
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.6
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("BDCloudVideoView", "onError: " + i + "," + i2);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_ERROR);
                BDCloudVideoView.this.isTryToPlaying = false;
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                return (BDCloudVideoView.this.mOnErrorListener == null || BDCloudVideoView.this.mOnErrorListener.onError(BDCloudVideoView.this.mMediaPlayer, i, i2)) ? true : true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.7
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                BDCloudVideoView.this.mCurrentBufferPercentage = i;
                if (BDCloudVideoView.this.mOnBufferingUpdateListener != null) {
                    BDCloudVideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.8
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onSeekComplete");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                if (BDCloudVideoView.this.mOnSeekCompleteListener != null) {
                    BDCloudVideoView.this.mOnSeekCompleteListener.onSeekComplete(iMediaPlayer);
                }
            }
        };
        this.mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.9
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnTimedTextListener
            public void onTimedText(IMediaPlayer iMediaPlayer, BDTimedText bDTimedText) {
                Log.d("BDCloudVideoView", "onTimedText text=" + bDTimedText.getText());
                if (bDTimedText != null) {
                    BDCloudVideoView.this.subtitleDisplay.setText(bDTimedText.getText());
                }
            }
        };
        this.mMetadataListener = new IMediaPlayer.OnMetadataListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.10
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnMetadataListener
            public void onMetadata(IMediaPlayer iMediaPlayer, Bundle bundle) {
                for (String str : bundle.keySet()) {
                    Log.d("BDCloudVideoView", "onMetadata: key = " + str + ", value = " + bundle.getString(str));
                }
                if (BDCloudVideoView.this.mOnMetadataListener != null) {
                    BDCloudVideoView.this.mOnMetadataListener.onMetadata(iMediaPlayer, bundle);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.11
            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                Log.d("BDCloudVideoView", "mSHCallback onSurfaceChanged");
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceWidth = i2;
                    BDCloudVideoView.this.mSurfaceHeight = i3;
                    boolean z2 = BDCloudVideoView.this.isTryToPlaying;
                    boolean z22 = !BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mVideoWidth == i2 && BDCloudVideoView.this.mVideoHeight == i3);
                    if (BDCloudVideoView.this.mMediaPlayer != null && z2 && z22) {
                        BDCloudVideoView.this.start();
                        return;
                    }
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceChanged: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = iSurfaceHolder;
                    if (BDCloudVideoView.this.mMediaPlayer == null) {
                        BDCloudVideoView.this.openVideo();
                        return;
                    }
                    BDCloudVideoView bDCloudVideoView = BDCloudVideoView.this;
                    bDCloudVideoView.bindSurfaceHolder(bDCloudVideoView.mMediaPlayer, iSurfaceHolder);
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceCreated: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = null;
                    BDCloudVideoView.this.releaseWithoutStop();
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceDestroyed: unmatched render callback\n");
            }
        };
        this.mUseTextureViewFirst = z;
        initVideoView(context);
    }

    public BDCloudVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUseTextureViewFirst = true;
        this.switchMode = 0;
        this.mPlayType = 0;
        this.mShowSourceSwitchInfo = true;
        this.mCurrentState = PlayerState.STATE_IDLE;
        this.isTryToPlaying = false;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mDrmToken = null;
        this.mCurrentAspectRatio = 0;
        this.mCacheTimeInMilliSeconds = 0;
        this.mbShowCacheInfo = true;
        this.mDecodeMode = 0;
        this.mLogEnabled = false;
        this.mInitPlayPositionInMilliSec = 0L;
        this.mWakeMode = 0;
        this.mLeftVolume = -1.0f;
        this.mRightVolume = -1.0f;
        this.mMaxProbeTimeInMs = -1;
        this.mMaxProbeSizeInBytes = 0;
        this.mMaxCacheSizeInBytes = 0;
        this.mMaxCacheDurationInMs = -1;
        this.mLooping = false;
        this.mBufferSizeInBytes = 0;
        this.mFrameChasing = -1;
        this.mSpeed = 1.0f;
        this.cachingHintViewRl = null;
        this.cachingProgressBar = null;
        this.cachingProgressHint = null;
        this.renderRootView = null;
        this.mainThreadHandler = new Handler(Looper.getMainLooper()) { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    return;
                }
                if (message.what == 10005) {
                    UIUtils.toast("清晰度切换中...");
                } else if (message.what == 10006) {
                    if (message.arg2 < 0) {
                        UIUtils.toast("清晰度切换失败!");
                    } else {
                        UIUtils.toast("清晰度切换成功！");
                    }
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.2
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                Log.d("BDCloudVideoView", "onVideoSizeChanged width=" + i + ";height=" + i2 + ";sarNum=" + i3 + ";sarDen=" + i4);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                BDCloudVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                BDCloudVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (BDCloudVideoView.this.mVideoWidth != 0 && BDCloudVideoView.this.mVideoHeight != 0) {
                    if (BDCloudVideoView.this.mRenderView != null) {
                        BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                        BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    }
                    BDCloudVideoView.this.requestLayout();
                }
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_VIDEOSIZE_CHANGED, true);
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.3
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onPrepared");
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PREPARED);
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                if (BDCloudVideoView.this.mOnPreparedListener != null) {
                    BDCloudVideoView.this.mOnPreparedListener.onPrepared(BDCloudVideoView.this.mMediaPlayer);
                }
                Log.d("BDCloudVideoView", "onPrepared: mVideoWidth=" + BDCloudVideoView.this.mVideoWidth + ";mVideoHeight=" + BDCloudVideoView.this.mVideoHeight + ";mSurfaceWidth=" + BDCloudVideoView.this.mSurfaceWidth + ";mSurfaceHeight=" + BDCloudVideoView.this.mSurfaceHeight);
                if (BDCloudVideoView.this.mVideoWidth == 0 || BDCloudVideoView.this.mVideoHeight == 0) {
                    if (BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                } else if (BDCloudVideoView.this.mRenderView != null) {
                    BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                    BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    if ((!BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mSurfaceWidth == BDCloudVideoView.this.mVideoWidth && BDCloudVideoView.this.mSurfaceHeight == BDCloudVideoView.this.mVideoHeight)) && BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.4
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onCompletion");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PLAYBACK_COMPLETED);
                BDCloudVideoView.this.isTryToPlaying = false;
                if (BDCloudVideoView.this.mOnCompletionListener != null) {
                    BDCloudVideoView.this.mOnCompletionListener.onCompletion(BDCloudVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.5
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("BDCloudVideoView", "onInfo: arg1=" + i + "; arg2=" + i2);
                if (BDCloudVideoView.this.mOnInfoListener != null) {
                    BDCloudVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                switch (i) {
                    case 3:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_RENDERING_START:");
                        break;
                    case 700:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_START:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(true);
                        break;
                    case 702:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_END:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                        break;
                    case 703:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        break;
                    case 800:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BAD_INTERLEAVING:");
                        break;
                    case 801:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NOT_SEEKABLE:");
                        break;
                    case 802:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_METADATA_UPDATE:");
                        break;
                    case 901:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                        break;
                    case 902:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                        break;
                    case 10001:
                        BDCloudVideoView.this.mVideoRotationDegree = i2;
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                        if (BDCloudVideoView.this.mRenderView != null) {
                            BDCloudVideoView.this.mRenderView.setVideoRotation(i2);
                            break;
                        }
                        break;
                    case 10002:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_AUDIO_RENDERING_START:");
                        break;
                    case 10005:
                        Log.i("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_START:");
                        BDCloudVideoView.this.showSourceSwitchState(i, 0);
                        break;
                    case 10006:
                        BDCloudVideoView.this.showSourceSwitchState(i, i2);
                        Log.d("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_END:");
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.6
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("BDCloudVideoView", "onError: " + i + "," + i2);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_ERROR);
                BDCloudVideoView.this.isTryToPlaying = false;
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                return (BDCloudVideoView.this.mOnErrorListener == null || BDCloudVideoView.this.mOnErrorListener.onError(BDCloudVideoView.this.mMediaPlayer, i, i2)) ? true : true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.7
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                BDCloudVideoView.this.mCurrentBufferPercentage = i;
                if (BDCloudVideoView.this.mOnBufferingUpdateListener != null) {
                    BDCloudVideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.8
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onSeekComplete");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                if (BDCloudVideoView.this.mOnSeekCompleteListener != null) {
                    BDCloudVideoView.this.mOnSeekCompleteListener.onSeekComplete(iMediaPlayer);
                }
            }
        };
        this.mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.9
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnTimedTextListener
            public void onTimedText(IMediaPlayer iMediaPlayer, BDTimedText bDTimedText) {
                Log.d("BDCloudVideoView", "onTimedText text=" + bDTimedText.getText());
                if (bDTimedText != null) {
                    BDCloudVideoView.this.subtitleDisplay.setText(bDTimedText.getText());
                }
            }
        };
        this.mMetadataListener = new IMediaPlayer.OnMetadataListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.10
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnMetadataListener
            public void onMetadata(IMediaPlayer iMediaPlayer, Bundle bundle) {
                for (String str : bundle.keySet()) {
                    Log.d("BDCloudVideoView", "onMetadata: key = " + str + ", value = " + bundle.getString(str));
                }
                if (BDCloudVideoView.this.mOnMetadataListener != null) {
                    BDCloudVideoView.this.mOnMetadataListener.onMetadata(iMediaPlayer, bundle);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.11
            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                Log.d("BDCloudVideoView", "mSHCallback onSurfaceChanged");
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceWidth = i2;
                    BDCloudVideoView.this.mSurfaceHeight = i3;
                    boolean z2 = BDCloudVideoView.this.isTryToPlaying;
                    boolean z22 = !BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mVideoWidth == i2 && BDCloudVideoView.this.mVideoHeight == i3);
                    if (BDCloudVideoView.this.mMediaPlayer != null && z2 && z22) {
                        BDCloudVideoView.this.start();
                        return;
                    }
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceChanged: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = iSurfaceHolder;
                    if (BDCloudVideoView.this.mMediaPlayer == null) {
                        BDCloudVideoView.this.openVideo();
                        return;
                    }
                    BDCloudVideoView bDCloudVideoView = BDCloudVideoView.this;
                    bDCloudVideoView.bindSurfaceHolder(bDCloudVideoView.mMediaPlayer, iSurfaceHolder);
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceCreated: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = null;
                    BDCloudVideoView.this.releaseWithoutStop();
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceDestroyed: unmatched render callback\n");
            }
        };
        initVideoView(context);
    }

    public BDCloudVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUseTextureViewFirst = true;
        this.switchMode = 0;
        this.mPlayType = 0;
        this.mShowSourceSwitchInfo = true;
        this.mCurrentState = PlayerState.STATE_IDLE;
        this.isTryToPlaying = false;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mDrmToken = null;
        this.mCurrentAspectRatio = 0;
        this.mCacheTimeInMilliSeconds = 0;
        this.mbShowCacheInfo = true;
        this.mDecodeMode = 0;
        this.mLogEnabled = false;
        this.mInitPlayPositionInMilliSec = 0L;
        this.mWakeMode = 0;
        this.mLeftVolume = -1.0f;
        this.mRightVolume = -1.0f;
        this.mMaxProbeTimeInMs = -1;
        this.mMaxProbeSizeInBytes = 0;
        this.mMaxCacheSizeInBytes = 0;
        this.mMaxCacheDurationInMs = -1;
        this.mLooping = false;
        this.mBufferSizeInBytes = 0;
        this.mFrameChasing = -1;
        this.mSpeed = 1.0f;
        this.cachingHintViewRl = null;
        this.cachingProgressBar = null;
        this.cachingProgressHint = null;
        this.renderRootView = null;
        this.mainThreadHandler = new Handler(Looper.getMainLooper()) { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    return;
                }
                if (message.what == 10005) {
                    UIUtils.toast("清晰度切换中...");
                } else if (message.what == 10006) {
                    if (message.arg2 < 0) {
                        UIUtils.toast("清晰度切换失败!");
                    } else {
                        UIUtils.toast("清晰度切换成功！");
                    }
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.2
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i22, int i3, int i4) {
                Log.d("BDCloudVideoView", "onVideoSizeChanged width=" + i2 + ";height=" + i22 + ";sarNum=" + i3 + ";sarDen=" + i4);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                BDCloudVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                BDCloudVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (BDCloudVideoView.this.mVideoWidth != 0 && BDCloudVideoView.this.mVideoHeight != 0) {
                    if (BDCloudVideoView.this.mRenderView != null) {
                        BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                        BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    }
                    BDCloudVideoView.this.requestLayout();
                }
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_VIDEOSIZE_CHANGED, true);
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.3
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onPrepared");
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PREPARED);
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                if (BDCloudVideoView.this.mOnPreparedListener != null) {
                    BDCloudVideoView.this.mOnPreparedListener.onPrepared(BDCloudVideoView.this.mMediaPlayer);
                }
                Log.d("BDCloudVideoView", "onPrepared: mVideoWidth=" + BDCloudVideoView.this.mVideoWidth + ";mVideoHeight=" + BDCloudVideoView.this.mVideoHeight + ";mSurfaceWidth=" + BDCloudVideoView.this.mSurfaceWidth + ";mSurfaceHeight=" + BDCloudVideoView.this.mSurfaceHeight);
                if (BDCloudVideoView.this.mVideoWidth == 0 || BDCloudVideoView.this.mVideoHeight == 0) {
                    if (BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                } else if (BDCloudVideoView.this.mRenderView != null) {
                    BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                    BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    if ((!BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mSurfaceWidth == BDCloudVideoView.this.mVideoWidth && BDCloudVideoView.this.mSurfaceHeight == BDCloudVideoView.this.mVideoHeight)) && BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.4
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onCompletion");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PLAYBACK_COMPLETED);
                BDCloudVideoView.this.isTryToPlaying = false;
                if (BDCloudVideoView.this.mOnCompletionListener != null) {
                    BDCloudVideoView.this.mOnCompletionListener.onCompletion(BDCloudVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.5
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i22) {
                Log.d("BDCloudVideoView", "onInfo: arg1=" + i2 + "; arg2=" + i22);
                if (BDCloudVideoView.this.mOnInfoListener != null) {
                    BDCloudVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i2, i22);
                }
                switch (i2) {
                    case 3:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_RENDERING_START:");
                        break;
                    case 700:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_START:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(true);
                        break;
                    case 702:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_END:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                        break;
                    case 703:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NETWORK_BANDWIDTH: " + i22);
                        break;
                    case 800:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BAD_INTERLEAVING:");
                        break;
                    case 801:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NOT_SEEKABLE:");
                        break;
                    case 802:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_METADATA_UPDATE:");
                        break;
                    case 901:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                        break;
                    case 902:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                        break;
                    case 10001:
                        BDCloudVideoView.this.mVideoRotationDegree = i22;
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i22);
                        if (BDCloudVideoView.this.mRenderView != null) {
                            BDCloudVideoView.this.mRenderView.setVideoRotation(i22);
                            break;
                        }
                        break;
                    case 10002:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_AUDIO_RENDERING_START:");
                        break;
                    case 10005:
                        Log.i("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_START:");
                        BDCloudVideoView.this.showSourceSwitchState(i2, 0);
                        break;
                    case 10006:
                        BDCloudVideoView.this.showSourceSwitchState(i2, i22);
                        Log.d("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_END:");
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.6
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i22) {
                Log.d("BDCloudVideoView", "onError: " + i2 + "," + i22);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_ERROR);
                BDCloudVideoView.this.isTryToPlaying = false;
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                return (BDCloudVideoView.this.mOnErrorListener == null || BDCloudVideoView.this.mOnErrorListener.onError(BDCloudVideoView.this.mMediaPlayer, i2, i22)) ? true : true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.7
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                BDCloudVideoView.this.mCurrentBufferPercentage = i2;
                if (BDCloudVideoView.this.mOnBufferingUpdateListener != null) {
                    BDCloudVideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i2);
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.8
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onSeekComplete");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                if (BDCloudVideoView.this.mOnSeekCompleteListener != null) {
                    BDCloudVideoView.this.mOnSeekCompleteListener.onSeekComplete(iMediaPlayer);
                }
            }
        };
        this.mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.9
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnTimedTextListener
            public void onTimedText(IMediaPlayer iMediaPlayer, BDTimedText bDTimedText) {
                Log.d("BDCloudVideoView", "onTimedText text=" + bDTimedText.getText());
                if (bDTimedText != null) {
                    BDCloudVideoView.this.subtitleDisplay.setText(bDTimedText.getText());
                }
            }
        };
        this.mMetadataListener = new IMediaPlayer.OnMetadataListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.10
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnMetadataListener
            public void onMetadata(IMediaPlayer iMediaPlayer, Bundle bundle) {
                for (String str : bundle.keySet()) {
                    Log.d("BDCloudVideoView", "onMetadata: key = " + str + ", value = " + bundle.getString(str));
                }
                if (BDCloudVideoView.this.mOnMetadataListener != null) {
                    BDCloudVideoView.this.mOnMetadataListener.onMetadata(iMediaPlayer, bundle);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.11
            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i22, int i3) {
                Log.d("BDCloudVideoView", "mSHCallback onSurfaceChanged");
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceWidth = i22;
                    BDCloudVideoView.this.mSurfaceHeight = i3;
                    boolean z2 = BDCloudVideoView.this.isTryToPlaying;
                    boolean z22 = !BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mVideoWidth == i22 && BDCloudVideoView.this.mVideoHeight == i3);
                    if (BDCloudVideoView.this.mMediaPlayer != null && z2 && z22) {
                        BDCloudVideoView.this.start();
                        return;
                    }
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceChanged: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i22) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = iSurfaceHolder;
                    if (BDCloudVideoView.this.mMediaPlayer == null) {
                        BDCloudVideoView.this.openVideo();
                        return;
                    }
                    BDCloudVideoView bDCloudVideoView = BDCloudVideoView.this;
                    bDCloudVideoView.bindSurfaceHolder(bDCloudVideoView.mMediaPlayer, iSurfaceHolder);
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceCreated: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = null;
                    BDCloudVideoView.this.releaseWithoutStop();
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceDestroyed: unmatched render callback\n");
            }
        };
        initVideoView(context);
    }

    @TargetApi(21)
    public BDCloudVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUseTextureViewFirst = true;
        this.switchMode = 0;
        this.mPlayType = 0;
        this.mShowSourceSwitchInfo = true;
        this.mCurrentState = PlayerState.STATE_IDLE;
        this.isTryToPlaying = false;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mDrmToken = null;
        this.mCurrentAspectRatio = 0;
        this.mCacheTimeInMilliSeconds = 0;
        this.mbShowCacheInfo = true;
        this.mDecodeMode = 0;
        this.mLogEnabled = false;
        this.mInitPlayPositionInMilliSec = 0L;
        this.mWakeMode = 0;
        this.mLeftVolume = -1.0f;
        this.mRightVolume = -1.0f;
        this.mMaxProbeTimeInMs = -1;
        this.mMaxProbeSizeInBytes = 0;
        this.mMaxCacheSizeInBytes = 0;
        this.mMaxCacheDurationInMs = -1;
        this.mLooping = false;
        this.mBufferSizeInBytes = 0;
        this.mFrameChasing = -1;
        this.mSpeed = 1.0f;
        this.cachingHintViewRl = null;
        this.cachingProgressBar = null;
        this.cachingProgressHint = null;
        this.renderRootView = null;
        this.mainThreadHandler = new Handler(Looper.getMainLooper()) { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    return;
                }
                if (message.what == 10005) {
                    UIUtils.toast("清晰度切换中...");
                } else if (message.what == 10006) {
                    if (message.arg2 < 0) {
                        UIUtils.toast("清晰度切换失败!");
                    } else {
                        UIUtils.toast("清晰度切换成功！");
                    }
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.2
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i22, int i222, int i3, int i4) {
                Log.d("BDCloudVideoView", "onVideoSizeChanged width=" + i22 + ";height=" + i222 + ";sarNum=" + i3 + ";sarDen=" + i4);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                BDCloudVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                BDCloudVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (BDCloudVideoView.this.mVideoWidth != 0 && BDCloudVideoView.this.mVideoHeight != 0) {
                    if (BDCloudVideoView.this.mRenderView != null) {
                        BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                        BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    }
                    BDCloudVideoView.this.requestLayout();
                }
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_VIDEOSIZE_CHANGED, true);
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.3
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onPrepared");
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PREPARED);
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                BDCloudVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                if (BDCloudVideoView.this.mOnPreparedListener != null) {
                    BDCloudVideoView.this.mOnPreparedListener.onPrepared(BDCloudVideoView.this.mMediaPlayer);
                }
                Log.d("BDCloudVideoView", "onPrepared: mVideoWidth=" + BDCloudVideoView.this.mVideoWidth + ";mVideoHeight=" + BDCloudVideoView.this.mVideoHeight + ";mSurfaceWidth=" + BDCloudVideoView.this.mSurfaceWidth + ";mSurfaceHeight=" + BDCloudVideoView.this.mSurfaceHeight);
                if (BDCloudVideoView.this.mVideoWidth == 0 || BDCloudVideoView.this.mVideoHeight == 0) {
                    if (BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                } else if (BDCloudVideoView.this.mRenderView != null) {
                    BDCloudVideoView.this.mRenderView.setVideoSize(BDCloudVideoView.this.mVideoWidth, BDCloudVideoView.this.mVideoHeight);
                    BDCloudVideoView.this.mRenderView.setVideoSampleAspectRatio(BDCloudVideoView.this.mVideoSarNum, BDCloudVideoView.this.mVideoSarDen);
                    if ((!BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mSurfaceWidth == BDCloudVideoView.this.mVideoWidth && BDCloudVideoView.this.mSurfaceHeight == BDCloudVideoView.this.mVideoHeight)) && BDCloudVideoView.this.isTryToPlaying) {
                        BDCloudVideoView.this.start();
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.4
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onCompletion");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_PLAYBACK_COMPLETED);
                BDCloudVideoView.this.isTryToPlaying = false;
                if (BDCloudVideoView.this.mOnCompletionListener != null) {
                    BDCloudVideoView.this.mOnCompletionListener.onCompletion(BDCloudVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.5
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i22, int i222) {
                Log.d("BDCloudVideoView", "onInfo: arg1=" + i22 + "; arg2=" + i222);
                if (BDCloudVideoView.this.mOnInfoListener != null) {
                    BDCloudVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i22, i222);
                }
                switch (i22) {
                    case 3:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_RENDERING_START:");
                        break;
                    case 700:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_START:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(true);
                        break;
                    case 702:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_END:");
                        BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                        break;
                    case 703:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NETWORK_BANDWIDTH: " + i222);
                        break;
                    case 800:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_BAD_INTERLEAVING:");
                        break;
                    case 801:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_NOT_SEEKABLE:");
                        break;
                    case 802:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_METADATA_UPDATE:");
                        break;
                    case 901:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                        break;
                    case 902:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                        break;
                    case 10001:
                        BDCloudVideoView.this.mVideoRotationDegree = i222;
                        Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i222);
                        if (BDCloudVideoView.this.mRenderView != null) {
                            BDCloudVideoView.this.mRenderView.setVideoRotation(i222);
                            break;
                        }
                        break;
                    case 10002:
                        Log.d("BDCloudVideoView", "MEDIA_INFO_AUDIO_RENDERING_START:");
                        break;
                    case 10005:
                        Log.i("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_START:");
                        BDCloudVideoView.this.showSourceSwitchState(i22, 0);
                        break;
                    case 10006:
                        BDCloudVideoView.this.showSourceSwitchState(i22, i222);
                        Log.d("BDCloudVideoView", " MEDIA_INFO_MEDIA_CHANGE_END:");
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.6
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i22, int i222) {
                Log.d("BDCloudVideoView", "onError: " + i22 + "," + i222);
                BDCloudVideoView.this.setCurrentState(PlayerState.STATE_ERROR);
                BDCloudVideoView.this.isTryToPlaying = false;
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                return (BDCloudVideoView.this.mOnErrorListener == null || BDCloudVideoView.this.mOnErrorListener.onError(BDCloudVideoView.this.mMediaPlayer, i22, i222)) ? true : true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.7
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i22) {
                BDCloudVideoView.this.mCurrentBufferPercentage = i22;
                if (BDCloudVideoView.this.mOnBufferingUpdateListener != null) {
                    BDCloudVideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i22);
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.8
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("BDCloudVideoView", "onSeekComplete");
                BDCloudVideoView.this.sendCachingHintViewVisibilityMessage(false);
                if (BDCloudVideoView.this.mOnSeekCompleteListener != null) {
                    BDCloudVideoView.this.mOnSeekCompleteListener.onSeekComplete(iMediaPlayer);
                }
            }
        };
        this.mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.9
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnTimedTextListener
            public void onTimedText(IMediaPlayer iMediaPlayer, BDTimedText bDTimedText) {
                Log.d("BDCloudVideoView", "onTimedText text=" + bDTimedText.getText());
                if (bDTimedText != null) {
                    BDCloudVideoView.this.subtitleDisplay.setText(bDTimedText.getText());
                }
            }
        };
        this.mMetadataListener = new IMediaPlayer.OnMetadataListener() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.10
            @Override // com.baidu.cloud.media.player.IMediaPlayer.OnMetadataListener
            public void onMetadata(IMediaPlayer iMediaPlayer, Bundle bundle) {
                for (String str : bundle.keySet()) {
                    Log.d("BDCloudVideoView", "onMetadata: key = " + str + ", value = " + bundle.getString(str));
                }
                if (BDCloudVideoView.this.mOnMetadataListener != null) {
                    BDCloudVideoView.this.mOnMetadataListener.onMetadata(iMediaPlayer, bundle);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.11
            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i222, int i3) {
                Log.d("BDCloudVideoView", "mSHCallback onSurfaceChanged");
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceWidth = i222;
                    BDCloudVideoView.this.mSurfaceHeight = i3;
                    boolean z2 = BDCloudVideoView.this.isTryToPlaying;
                    boolean z22 = !BDCloudVideoView.this.mRenderView.shouldWaitForResize() || (BDCloudVideoView.this.mVideoWidth == i222 && BDCloudVideoView.this.mVideoHeight == i3);
                    if (BDCloudVideoView.this.mMediaPlayer != null && z2 && z22) {
                        BDCloudVideoView.this.start();
                        return;
                    }
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceChanged: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i222) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = iSurfaceHolder;
                    if (BDCloudVideoView.this.mMediaPlayer == null) {
                        BDCloudVideoView.this.openVideo();
                        return;
                    }
                    BDCloudVideoView bDCloudVideoView = BDCloudVideoView.this;
                    bDCloudVideoView.bindSurfaceHolder(bDCloudVideoView.mMediaPlayer, iSurfaceHolder);
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceCreated: unmatched render callback\n");
            }

            @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() == BDCloudVideoView.this.mRenderView) {
                    BDCloudVideoView.this.mSurfaceHolder = null;
                    BDCloudVideoView.this.releaseWithoutStop();
                    return;
                }
                Log.e("BDCloudVideoView", "onSurfaceDestroyed: unmatched render callback\n");
            }
        };
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        this.renderRootView = new FrameLayout(context);
        addView(this.renderRootView, new FrameLayout.LayoutParams(-1, -1));
        reSetRender();
        addSubtitleView();
        addCachingHintView();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        setCurrentState(PlayerState.STATE_IDLE);
    }

    private void addSubtitleView() {
        this.subtitleDisplay = new TextView(getContext());
        this.subtitleDisplay.setTextSize(24.0f);
        this.subtitleDisplay.setGravity(17);
        addView(this.subtitleDisplay, new FrameLayout.LayoutParams(-1, -2, 80));
    }

    private void addCachingHintView() {
        this.cachingHintViewRl = new RelativeLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.cachingHintViewRl.setVisibility(8);
        addView(this.cachingHintViewRl, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.cachingProgressBar = new ProgressBar(getContext());
        this.cachingProgressBar.setId(16908308);
        this.cachingProgressBar.setMax(100);
        this.cachingProgressBar.setProgress(10);
        this.cachingProgressBar.setSecondaryProgress(100);
        this.cachingHintViewRl.addView(this.cachingProgressBar, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(9);
        layoutParams3.addRule(3, 16908308);
        this.cachingProgressHint = new TextView(getContext());
        this.cachingProgressHint.setTextColor(-1);
        this.cachingProgressHint.setText("正在加载中");
        this.cachingProgressHint.setGravity(1);
        this.cachingHintViewRl.addView(this.cachingProgressHint, layoutParams3);
    }

    public void setCachingHintViewVisibility(boolean z) {
        if (z) {
            this.cachingHintViewRl.setVisibility(0);
        } else {
            this.cachingHintViewRl.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCachingHintViewVisibilityMessage(boolean z) {
        if (this.mbShowCacheInfo) {
            Message obtainMessage = this.mainThreadHandler.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = z ? 1 : 0;
            this.mainThreadHandler.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSourceSwitchState(int i, int i2) {
        if (this.mShowSourceSwitchInfo) {
            Message obtainMessage = this.mainThreadHandler.obtainMessage();
            obtainMessage.what = i;
            obtainMessage.arg1 = i2;
            this.mainThreadHandler.sendMessage(obtainMessage);
        }
    }

    protected void setRenderView(IRenderView iRenderView) {
        int i;
        int i2;
        if (this.mRenderView != null) {
            BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
            if (bDCloudMediaPlayer != null) {
                bDCloudMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView.release();
            this.mRenderView = null;
            this.mSurfaceHolder = null;
            this.renderRootView.removeView(view);
        }
        if (iRenderView == null) {
            return;
        }
        this.mRenderView = iRenderView;
        iRenderView.setAspectRatio(this.mCurrentAspectRatio);
        int i3 = this.mVideoWidth;
        if (i3 > 0 && (i2 = this.mVideoHeight) > 0) {
            iRenderView.setVideoSize(i3, i2);
        }
        int i4 = this.mVideoSarNum;
        if (i4 > 0 && (i = this.mVideoSarDen) > 0) {
            iRenderView.setVideoSampleAspectRatio(i4, i);
        }
        View view2 = this.mRenderView.getView();
        view2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        this.renderRootView.addView(view2);
        this.mRenderView.addRenderCallback(this.mSHCallback);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    public void reSetRender() {
        if (this.mUseTextureViewFirst && Build.VERSION.SDK_INT >= 16) {
            TextureRenderView textureRenderView = new TextureRenderView(getContext());
            if (this.mMediaPlayer != null) {
                textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                textureRenderView.setAspectRatio(this.mCurrentAspectRatio);
            }
            setRenderView(textureRenderView);
            return;
        }
        setRenderView(new SurfaceRenderView(getContext()));
    }

    public void setVideoPath(String str) {
        setVideoPathWithToken(str, null);
    }

    @SuppressLint({"CheckResult"})
    public void setVideoPath(final String str, boolean z) {
        Observable.create(new ObservableOnSubscribe() { // from class: com.baidu.cloud.cloudplayer.videoview.-$$Lambda$BDCloudVideoView$jBlA8HJQqEHP24z6jXxZh04qHmU
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                BDCloudVideoView.lambda$setVideoPath$0(str, observableEmitter);
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.baidu.cloud.cloudplayer.videoview.-$$Lambda$BDCloudVideoView$2biujUXaOl71Iub1-LDlNSAvAWo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BDCloudVideoView.lambda$setVideoPath$1(BDCloudVideoView.this, (String) obj);
            }
        }, new Consumer() { // from class: com.baidu.cloud.cloudplayer.videoview.-$$Lambda$BDCloudVideoView$Isl1SqAK_QPns48NTEHN_Sxc0pQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BDCloudVideoView.lambda$setVideoPath$2(BDCloudVideoView.this, str, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setVideoPath$0(String str, ObservableEmitter observableEmitter) throws Exception {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str).openConnection());
            httpURLConnection.setInstanceFollowRedirects(false);
            if (302 == httpURLConnection.getResponseCode()) {
                str = httpURLConnection.getHeaderField("Location");
            }
            httpURLConnection.disconnect();
            observableEmitter.onNext(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setVideoPath$1(BDCloudVideoView bDCloudVideoView, String str) throws Exception {
        UIUtils.logD("xcy", "重定向成功开始播放");
        bDCloudVideoView.setVideoPathWithToken(str, null);
    }

    public static /* synthetic */ void lambda$setVideoPath$2(BDCloudVideoView bDCloudVideoView, String str, Throwable th) throws Exception {
        UIUtils.logD("xcy", "重定向失败");
        bDCloudVideoView.setVideoPathWithToken(str, null);
    }

    public void setVideoPathWithToken(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mDrmToken = str2;
        if (this.mProxyCacheManager != null && str.startsWith("http") && str.contains(".mp4")) {
            UIUtils.logD("videoCache", "原始地址：" + str);
            String proxyUrl = this.mProxyCacheManager.getProxyUrl(str);
            UIUtils.logD("videoCache", "缓存地址：" + str);
            this.mProxyCacheManager.registerCacheListener(new VideoCacheListener(), str);
            str = proxyUrl;
        }
        Log.w("BDCloudVideoView", "Video Uri: " + str);
        Log.w("videoCache", "Video Uri: " + str);
        setVideoURI(Uri.parse(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class VideoCacheListener implements CacheListener {
        VideoCacheListener() {
        }

        @Override // com.baidu.cloud.videocache.CacheListener
        public void onCacheAvailable(File file, String str, int i) {
            Log.d("videoCache", "url:" + str + " cache percents: " + i);
        }
    }

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
    }

    private void setVideoURI(Uri uri) {
        this.mUri = uri;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.stop();
            release(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(14)
    public void openVideo() {
        if (this.mUri == null || this.mSurfaceHolder == null) {
            return;
        }
        release(false);
        ((AudioManager) this.mAppContext.getSystemService("audio")).requestAudioFocus(null, 3, 1);
        try {
            try {
                this.mMediaPlayer = createPlayer();
                if (!TextUtils.isEmpty(this.mDrmToken)) {
                    this.mMediaPlayer.setDecryptTokenForHLS(this.mDrmToken);
                }
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
                this.mMediaPlayer.setOnTimedTextListener(this.mOnTimedTextListener);
                this.mMediaPlayer.setOnMetadataListener(this.mOnMetadataListener);
                this.mCurrentBufferPercentage = 0;
                this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mMediaPlayer.setTimeoutInUs(15000000);
                this.mMediaPlayer.prepareAsync();
                sendCachingHintViewVisibilityMessage(true);
                setCurrentState(PlayerState.STATE_PREPARING);
            } catch (IllegalArgumentException e) {
                Log.w("BDCloudVideoView", "Unable to open content: " + this.mUri, e);
                setCurrentState(PlayerState.STATE_ERROR);
                this.isTryToPlaying = false;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        } catch (IOException e2) {
            Log.w("BDCloudVideoView", "Unable to open content: " + this.mUri, e2);
            setCurrentState(PlayerState.STATE_ERROR);
            this.isTryToPlaying = false;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
    }

    public BDCloudMediaPlayer createPlayer() {
        BDCloudMediaPlayer bDCloudMediaPlayer = new BDCloudMediaPlayer(getContext());
        bDCloudMediaPlayer.setLogEnabled(this.mLogEnabled);
        bDCloudMediaPlayer.setDecodeMode(this.mDecodeMode);
        long j = this.mInitPlayPositionInMilliSec;
        if (j > -1) {
            bDCloudMediaPlayer.setInitPlayPosition(j);
            this.mInitPlayPositionInMilliSec = -1L;
        }
        if (this.mWakeMode > 0) {
            bDCloudMediaPlayer.setWakeMode(getContext(), this.mWakeMode);
        }
        float f = this.mLeftVolume;
        if (f > -1.0f) {
            float f2 = this.mRightVolume;
            if (f2 > -1.0f) {
                bDCloudMediaPlayer.setVolume(f, f2);
            }
        }
        int i = this.mCacheTimeInMilliSeconds;
        if (i > 0) {
            bDCloudMediaPlayer.setBufferTimeInMs(i);
        }
        int i2 = this.mMaxProbeTimeInMs;
        if (i2 >= 0) {
            bDCloudMediaPlayer.setMaxProbeTime(i2);
        }
        int i3 = this.mMaxProbeSizeInBytes;
        if (i3 > 0) {
            bDCloudMediaPlayer.setMaxProbeSize(i3);
        }
        int i4 = this.mMaxCacheSizeInBytes;
        if (i4 > 0) {
            bDCloudMediaPlayer.setMaxCacheSizeInBytes(i4);
        }
        boolean z = this.mLooping;
        if (z) {
            bDCloudMediaPlayer.setLooping(z);
        }
        int i5 = this.mBufferSizeInBytes;
        if (i5 > 0) {
            bDCloudMediaPlayer.setBufferSizeInBytes(i5);
        }
        int i6 = this.mFrameChasing;
        if (i6 >= 0) {
            bDCloudMediaPlayer.toggleFrameChasing(i6 == 1);
        }
        String[] strArr = this.mMediaItems;
        if (strArr != null) {
            bDCloudMediaPlayer.setMediaItems(strArr);
        }
        int i7 = this.mMaxCacheDurationInMs;
        if (i7 >= 0) {
            bDCloudMediaPlayer.setMaxCacheDuration(i7);
        }
        bDCloudMediaPlayer.setMediaInputType(this.switchMode);
        bDCloudMediaPlayer.setPlayType(this.mPlayType);
        bDCloudMediaPlayer.setSpeed(this.mSpeed);
        return bDCloudMediaPlayer;
    }

    public void setBufferTimeInMs(int i) {
        this.mCacheTimeInMilliSeconds = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setBufferTimeInMs(this.mCacheTimeInMilliSeconds);
        }
    }

    public void setBufferSizeInBytes(int i) {
        this.mBufferSizeInBytes = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setBufferSizeInBytes(this.mBufferSizeInBytes);
        }
    }

    public void setLooping(boolean z) {
        this.mLooping = z;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setLooping(this.mLooping);
        }
    }

    public void setMaxCacheSizeInBytes(int i) {
        this.mMaxCacheSizeInBytes = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setMaxCacheSizeInBytes(this.mMaxCacheSizeInBytes);
        }
    }

    public void setMaxProbeSize(int i) {
        this.mMaxProbeSizeInBytes = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setMaxProbeSize(this.mMaxProbeSizeInBytes);
        }
    }

    public void setMaxProbeTime(int i) {
        this.mMaxProbeTimeInMs = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setMaxProbeTime(this.mMaxProbeTimeInMs);
        }
    }

    public void setTimeoutInUs(int i) {
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setTimeoutInUs(i);
        }
    }

    public void setVolume(float f, float f2) {
        this.mLeftVolume = f;
        this.mRightVolume = f2;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void setSpeed(float f) {
        this.mSpeed = f;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setSpeed(this.mSpeed);
        }
    }

    public void setWakeMode(Context context, int i) {
        this.mWakeMode = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setWakeMode(context, this.mWakeMode);
        }
    }

    public void setInitPlayPosition(long j) {
        this.mInitPlayPositionInMilliSec = j;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setInitPlayPosition(this.mInitPlayPositionInMilliSec);
            this.mInitPlayPositionInMilliSec = -1L;
        }
    }

    public void setLogEnabled(boolean z) {
        this.mLogEnabled = z;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setLogEnabled(this.mLogEnabled);
        }
    }

    public void setDecodeMode(int i) {
        this.mDecodeMode = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setDecodeMode(this.mDecodeMode);
        }
    }

    public void toggleFrameChasing(boolean z) {
        this.mFrameChasing = z ? 1 : 0;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.toggleFrameChasing(z);
        }
    }

    public void showCacheInfo(boolean z) {
        this.mbShowCacheInfo = z;
    }

    public IMediaPlayer getCurrentMediaPlayer() {
        return this.mMediaPlayer;
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public void setOnBufferingUpdateListener(IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
    }

    public void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.mOnSeekCompleteListener = onSeekCompleteListener;
    }

    public void setOnMetadataListener(IMediaPlayer.OnMetadataListener onMetadataListener) {
        this.mOnMetadataListener = onMetadataListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSurfaceHolder(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer == null) {
            return;
        }
        if (iSurfaceHolder == null) {
            iMediaPlayer.setDisplay(null);
        } else {
            iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseWithoutStop() {
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer == null || !(this.mRenderView instanceof SurfaceRenderView)) {
            return;
        }
        bDCloudMediaPlayer.setDisplay(null);
    }

    public void release() {
        release(true);
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.release();
        }
    }

    private void release(boolean z) {
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.reset();
            this.mMediaPlayer.setDisplay(null);
            synchronized (this.mMediaPlayer) {
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
            }
            setCurrentState(PlayerState.STATE_IDLE);
            if (z) {
                this.isTryToPlaying = false;
            }
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (this.mMediaPlayer != null) {
            if (this.mCurrentState == PlayerState.STATE_ERROR || this.mCurrentState == PlayerState.STATE_PLAYBACK_COMPLETED) {
                if (this.mCurrentState == PlayerState.STATE_PLAYBACK_COMPLETED) {
                    this.mMediaPlayer.stop();
                }
                this.mMediaPlayer.prepareAsync();
                sendCachingHintViewVisibilityMessage(true);
                setCurrentState(PlayerState.STATE_PREPARING);
            } else if (isInPlaybackState()) {
                this.mMediaPlayer.start();
                setCurrentState(PlayerState.STATE_PLAYING);
            }
        }
        this.isTryToPlaying = true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            setCurrentState(PlayerState.STATE_PAUSED);
        }
        this.isTryToPlaying = false;
    }

    public String getCurrentPlayingUrl() {
        Uri uri = this.mUri;
        if (uri != null) {
            return uri.toString();
        }
        return null;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
            sendCachingHintViewVisibilityMessage(true);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == PlayerState.STATE_ERROR || this.mCurrentState == PlayerState.STATE_IDLE || this.mCurrentState == PlayerState.STATE_PREPARING) ? false : true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.mCanPause;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public void setVideoScalingMode(int i) {
        if (i == 1 || i == 2 || i == 3) {
            if (i == 1) {
                this.mCurrentAspectRatio = 0;
            } else if (i == 2) {
                this.mCurrentAspectRatio = 1;
            } else {
                this.mCurrentAspectRatio = 3;
            }
            IRenderView iRenderView = this.mRenderView;
            if (iRenderView != null) {
                iRenderView.setAspectRatio(this.mCurrentAspectRatio);
                return;
            }
            return;
        }
        Log.e("BDCloudVideoView", "setVideoScalingMode: param should be VID");
    }

    public void setMediaItems(String[] strArr) {
        this.mMediaItems = strArr;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setMediaItems(strArr);
        }
    }

    public void setMediaItems(String[] strArr, String[] strArr2) {
        this.mMutilMediasDsc = strArr2;
        setMediaItems(strArr);
    }

    public String[] getMutilMediasDsc() {
        return this.mMutilMediasDsc;
    }

    public String[] getVariantInfo() {
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            int i = this.switchMode;
            if (i == 1 || i == 2) {
                return this.mMediaPlayer.getMediaItems();
            }
            return bDCloudMediaPlayer.getVariantInfo();
        }
        return null;
    }

    public long getDownloadSpeed() {
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            return bDCloudMediaPlayer.getDownloadSpeed();
        }
        return 0L;
    }

    public void setMediaInputType(int i) {
        this.switchMode = i;
        if (i != 0) {
            this.mMaxCacheDurationInMs = 5000;
        }
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setMediaInputType(i);
            if (i != 0) {
                this.mMediaPlayer.setMaxCacheDuration(this.mMaxCacheDurationInMs);
            }
        }
    }

    public int getMediaInputType() {
        return this.switchMode;
    }

    public void setPlayType(int i) {
        this.mPlayType = i;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.mMediaPlayer;
        if (bDCloudMediaPlayer != null) {
            bDCloudMediaPlayer.setPlayType(i);
        }
    }

    public int getPlayType() {
        return this.mPlayType;
    }

    public boolean selectResolutionByIndex(int i) {
        boolean z;
        if (this.mMediaPlayer != null) {
            sendCachingHintViewVisibilityMessage(true);
            int i2 = this.switchMode;
            if (i2 == 1 || i2 == 2) {
                this.mMediaPlayer.selectMediaByIndex(i);
                z = false;
            } else {
                z = this.mMediaPlayer.selectResolutionByIndex(i);
            }
            if (z) {
                return z;
            }
            sendCachingHintViewVisibilityMessage(false);
            return z;
        }
        return false;
    }

    public Bitmap getBitmap() {
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            return iRenderView.getBitmap();
        }
        return null;
    }

    public static void setAK(String str) {
        BDCloudMediaPlayer.setAK(str);
    }

    public static void setCuid(String str) {
        BDCloudMediaPlayer.setCuid(str);
    }

    public void enterBackground() {
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView == null || (iRenderView instanceof SurfaceRenderView)) {
            return;
        }
        this.renderRootView.removeView(iRenderView.getView());
    }

    public void enterForeground() {
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView == null || (iRenderView instanceof SurfaceRenderView)) {
            return;
        }
        View view = iRenderView.getView();
        if (view.getParent() == null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
            this.renderRootView.addView(view);
            return;
        }
        Log.d("BDCloudVideoView", "enterForeground; but getParent() is not null");
    }
}
