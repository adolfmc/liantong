package com.baidu.rtc.player;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import com.baidu.rtc.JanusConnection;
import com.baidu.rtc.PeerConnectionClient;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.RTCCommStatesReport;
import com.baidu.rtc.RTCLoadManager;
import com.baidu.rtc.RTCVideoView;
import com.baidu.rtc.RemoteAudioSamplesInterceptor;
import com.baidu.rtc.RtcParameterSettings;
import com.baidu.rtc.config.Constraints;
import com.baidu.rtc.logreport.HUDStatistics;
import com.baidu.rtc.logreport.RtcReportHandle;
import com.baidu.rtc.player.AsyncHttpRequest;
import com.baidu.rtc.player.BRTCPlayer;
import com.baidu.rtc.player.PlayTimeStatistician;
import com.baidu.rtc.record.MediaEncodeParams;
import com.baidu.rtc.record.RTCMediaRecorderImpl;
import com.baidu.rtc.record.RecorderCallback;
import com.baidu.rtc.signalling.play.RemoteSdpRequest;
import com.baidu.rtc.signalling.play.RemoteSdpResponse;
import com.baidu.rtc.snapshot.SnapShotCallback;
import com.baidu.rtc.snapshot.SnapShotHelper;
import com.baidu.rtc.utils.CommonUtils;
import com.webrtc.AudioTrack;
import com.webrtc.EglBase;
import com.webrtc.IceCandidate;
import com.webrtc.Logging;
import com.webrtc.RendererCommon;
import com.webrtc.SessionDescription;
import com.webrtc.StatsReport;
import com.webrtc.VideoTrack;
import com.webrtc.audio.JavaAudioDeviceModule;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BRTCPlayerImpl implements PeerConnectionClient.PeerConnectionEvents, BRTCPlayer {
    public static final int DEFAULT_AUDIO_BUFFER_MAINTAINABLE_MS = 50;
    public static final int DEFAULT_AUDIO_BUFFER_UNDER_LOAD_MS = 20;
    public static final int DEFAULT_BUFFERING_DETECT_MS = 200;
    private static final int DEFAULT_BUFFERING_DETECT_PERIOD = 1;
    private static final int DEFAULT_STATS_REPORT_PERIOD = 5;
    private static final int DEFAULT_STREAMING_INTERRUPT_PERIOD = 30;
    private static final String STREAM_URL_PREFIX = "webrtc://";
    private static final String TAG = "BRTCPlayer";
    private String mAppId;
    private AudioTrack mAudioTrack;
    private boolean mAutoPlay;
    private volatile int mBufferingDetectCount;
    private final Context mContext;
    private BRTCPlayerEvents mEventObserver;
    private AsyncHttpRequest mHttpConnection;
    private boolean mIsLibraryLoaded;
    private boolean mIsRenderViewVisible;
    private String mMediaServerIp;
    private BRTCPlayerParameters mPlayerParameters;
    private String mPullUrl;
    private String mRemoteHandleId;
    private RemoteAudioSamplesInterceptor mRemoteSamplesInterceptor;
    private String mRemoteSdp;
    private RTCMediaRecorderImpl mRemoteVideoRecorder;
    private RTCVideoView mRenderView;
    private ViewTreeObserver mRenderViewTree;
    private RtcReportHandle mReportHandler;
    private String mRoomId;
    private String mRoomName;
    private volatile int mStatsReportCount;
    private String mStreamUrl;
    private volatile int mStreamingValidityDetectCount;
    private PlayTimeStatistician mTimeStatistician;
    private String mUserId;
    private VideoTrack mVideoTrack;
    private int mStreamingInterruptDetectPeriod = 30;
    private int mStatsReportPeriod = 5;
    private int mBufferingDetectPeriod = 5;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private volatile PeerConnectionClient peerConnectionClient = null;
    private PeerConnectionClient.PeerConnectionParameters peerConnectionParameters = null;
    private RtcParameterSettings mDefaultSettings = new RtcParameterSettings();
    BigInteger mPlayTransactionId = null;
    private volatile BRTCPlayer.PlayerState mCurrentState = BRTCPlayer.PlayerState.STATE_IDLE;
    private long mLocalUserId = -1;
    private volatile boolean mIsBuffing = false;
    private int mAudioBufferUnderLoadLevel = 20;
    private int mAudioBufferMaintainableLevel = 50;
    private boolean mIsEnableErrorInfoMonitor = true;
    private boolean mEnablePullQualityMonitor = true;
    private ViewTreeObserver.OnGlobalLayoutListener mRenderViewVisibleListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.2
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (BRTCPlayerImpl.this.mRenderView == null || !BRTCPlayerImpl.this.mRenderView.isFullVisible()) {
                return;
            }
            BRTCPlayerImpl.this.logD("got render view visible and remove global layout update listener.");
            if (!BRTCPlayerImpl.this.mIsRenderViewVisible) {
                BRTCPlayerImpl.this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_RENDER_VIEW_VISIBLE);
                BRTCPlayerImpl.this.mIsRenderViewVisible = true;
            }
            BRTCPlayerImpl.this.removeRenderViewTreeListener();
        }
    };
    RendererCommon.RendererEvents mRendererEvents = new RendererCommon.RendererEvents() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.3
        @Override // com.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            BRTCPlayerImpl.this.setCurrentState(BRTCPlayer.PlayerState.STATE_PLAYING);
            BRTCPlayerImpl.this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_FIRST_FRAME_RENDERED);
            if (BRTCPlayerImpl.this.mEventObserver != null) {
                BRTCPlayerImpl.this.mEventObserver.onInfoUpdated(1008, BRTCPlayerImpl.this.mTimeStatistician);
                BRTCPlayerImpl.this.mEventObserver.onFirstFrameRendered();
            }
            BRTCPlayerImpl.this.mReportHandler.reportFirstFrameConsume(BRTCPlayerImpl.this.mTimeStatistician.getRealFirstFrameTime());
            BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
            bRTCPlayerImpl.logD("first frame rendered time statistic :" + BRTCPlayerImpl.this.mTimeStatistician.toString());
        }

        @Override // com.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int i, int i2, int i3) {
            if (BRTCPlayerImpl.this.mEventObserver != null) {
                BRTCPlayerImpl.this.mEventObserver.onResolutionChanged(i, i2);
            }
        }
    };
    private RTCLoadManager.LoadListener mLoadListener = new RTCLoadManager.LoadListener() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.4
        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLoadError(int i, String str) {
            BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
            bRTCPlayerImpl.reportError(10010, i + " / " + str);
        }

        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLibsDownloadCompleted() {
            BRTCPlayerImpl.this.logD("libs download completed.");
            BRTCPlayerImpl.this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_LIBRARY_DOWNLOADED);
            if (BRTCPlayerImpl.this.mEventObserver != null) {
                BRTCPlayerImpl.this.mEventObserver.onInfoUpdated(1011, "libs download completed.");
            }
        }

        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLoadSuccess() {
            BRTCPlayerImpl.this.logD("after libs loaded, replay.");
            BRTCPlayerImpl.this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_LIBRARY_LOADED);
            if (BRTCPlayerImpl.this.mEventObserver != null) {
                BRTCPlayerImpl.this.mEventObserver.onInfoUpdated(1012, "libs load success.");
            }
            if (BRTCPlayerImpl.this.getPlayerState() != BRTCPlayer.PlayerState.STATE_IDLE) {
                BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
                bRTCPlayerImpl.initPlayerInternal(bRTCPlayerImpl.mPlayerParameters, BRTCPlayerImpl.this.mEventObserver);
                BRTCPlayerImpl.this.prepareAsyncInternal();
            }
        }

        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLoadProgress(float f) {
            BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
            bRTCPlayerImpl.logD("loading so progress " + f);
        }
    };
    AsyncHttpRequest.AsyncHttpEvents mHttpEvents = new AsyncHttpRequest.AsyncHttpEvents() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.7
        @Override // com.baidu.rtc.player.AsyncHttpRequest.AsyncHttpEvents
        public void onConnectionOpened() {
            BRTCPlayerImpl.this.logD("http connection opened.");
            BRTCPlayerImpl.this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_HTTP_CONNECTION_OPENED);
        }

        @Override // com.baidu.rtc.player.AsyncHttpRequest.AsyncHttpEvents
        public void onHttpError(String str) {
            BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
            bRTCPlayerImpl.logE("connection error: " + str);
            BRTCPlayerImpl.this.reportError(10006, str);
        }

        @Override // com.baidu.rtc.player.AsyncHttpRequest.AsyncHttpEvents
        public void onHttpComplete(String str) {
            BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
            bRTCPlayerImpl.logD("play response from signaling server: " + str);
            BRTCPlayerImpl.this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_REMOTE_SDP_ACQUIRED);
            if (BRTCPlayerImpl.this.mCurrentState != BRTCPlayer.PlayerState.STATE_PREPARING) {
                BRTCPlayerImpl bRTCPlayerImpl2 = BRTCPlayerImpl.this;
                bRTCPlayerImpl2.logE("Set remote sdp in invalid state: " + BRTCPlayerImpl.this.mCurrentState);
                BRTCPlayerImpl bRTCPlayerImpl3 = BRTCPlayerImpl.this;
                bRTCPlayerImpl3.reportError(10007, "Set remote sdp in invalid state: " + BRTCPlayerImpl.this.mCurrentState);
                return;
            }
            RemoteSdpResponse newFromJsonStr = RemoteSdpResponse.newFromJsonStr(str);
            String sdp = newFromJsonStr.getSdp();
            BRTCPlayerImpl.this.mRemoteHandleId = newFromJsonStr.getRemoteHandleId();
            String sessionId = newFromJsonStr.getSessionId();
            BRTCPlayerImpl.this.mAppId = newFromJsonStr.getAppId();
            BRTCPlayerImpl.this.mRoomId = newFromJsonStr.getRoomId();
            newFromJsonStr.getRoomName();
            BRTCPlayerImpl.this.mUserId = newFromJsonStr.getUserId();
            if (BRTCPlayerImpl.this.mLocalUserId > 0) {
                BRTCPlayerImpl bRTCPlayerImpl4 = BRTCPlayerImpl.this;
                bRTCPlayerImpl4.mUserId = String.valueOf(bRTCPlayerImpl4.mLocalUserId);
            }
            String feed = newFromJsonStr.getFeed();
            if (sdp == null || sdp.length() <= 0) {
                BRTCPlayerImpl.this.reportError(10007, "remote sdp parsing error.");
                return;
            }
            BRTCPlayerImpl bRTCPlayerImpl5 = BRTCPlayerImpl.this;
            bRTCPlayerImpl5.logD("obtain answer sdp : " + sdp);
            BRTCPlayerImpl.this.mRemoteSdp = sdp;
            BRTCPlayerImpl.this.mReportHandler.updateRoomInfo(BRTCPlayerImpl.this.mAppId, BRTCPlayerImpl.this.mRoomId, BRTCPlayerImpl.this.mUserId, feed, BRTCPlayerImpl.this.mRemoteHandleId, sessionId);
            BRTCPlayerImpl.this.setCurrentState(BRTCPlayer.PlayerState.STATE_PREPARED);
            if (BRTCPlayerImpl.this.mEventObserver != null) {
                BRTCPlayerImpl.this.mEventObserver.onPrepared();
            }
            if (BRTCPlayerImpl.this.mAutoPlay) {
                BRTCPlayerImpl.this.startPlay();
            }
        }
    };
    JavaAudioDeviceModule.RemoteSamplesReadyCallback mRemoteSamplesInternalCallback = new JavaAudioDeviceModule.RemoteSamplesReadyCallback() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.8
        @Override // com.webrtc.audio.JavaAudioDeviceModule.RemoteSamplesReadyCallback
        public void onWebRtcAudioRemoteSamplesReady(JavaAudioDeviceModule.AudioSamples audioSamples) {
            if (audioSamples == null || BRTCPlayerImpl.this.mRemoteSamplesInterceptor == null) {
                return;
            }
            BRTCPlayerImpl.this.mRemoteSamplesInterceptor.onRtcAudioRemoteSamplesReady(new RTCAudioSamples(audioSamples.getAudioFormat(), audioSamples.getChannelCount(), audioSamples.getSampleRate(), audioSamples.getData()));
        }
    };
    private EglBase mRootEglBase = EglBase.CC.create();

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onAudioRecordStartError(int i, String str) {
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onShareScreenStart(int i, String str) {
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onShareScreenStop(int i, String str) {
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onSubscribSetLocalDescriptionSuccess(BigInteger bigInteger) {
    }

    public BRTCPlayerImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.mReportHandler = RtcReportHandle.getInstance(this.mContext);
        RtcReportHandle.enableMonitor(true, this.mEnablePullQualityMonitor, this.mIsEnableErrorInfoMonitor, false);
        this.mTimeStatistician = new PlayTimeStatistician("");
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public long initPlayer(@NonNull BRTCPlayerParameters bRTCPlayerParameters, @NonNull BRTCPlayerEvents bRTCPlayerEvents) {
        initPlayerInternal(bRTCPlayerParameters, bRTCPlayerEvents);
        setCurrentState(BRTCPlayer.PlayerState.STATE_INITIALIZED);
        return this.mLocalUserId;
    }

    public void initPlayerInternal(@NonNull BRTCPlayerParameters bRTCPlayerParameters, @NonNull BRTCPlayerEvents bRTCPlayerEvents) {
        this.mEventObserver = bRTCPlayerEvents;
        this.mPlayerParameters = bRTCPlayerParameters;
        if (this.mPlayerParameters == null) {
            this.mPlayerParameters = new BRTCPlayerParameters();
            Logging.m5305d("BRTCPlayer", "Use default player parameters.");
        }
        Logging.m5305d("BRTCPlayer", "user set parameter:" + this.mPlayerParameters);
        if (this.mPlayerParameters.isEnableSoLaterLoad()) {
            this.mIsLibraryLoaded = RTCLoadManager.loadLibrary(this.mContext, this.mPlayerParameters.getCpuType());
        }
        this.mPullUrl = this.mPlayerParameters.getPullUrl();
        this.mAutoPlay = this.mPlayerParameters.isAutoPlay();
        if (this.mPlayerParameters.getUserId() > 0) {
            this.mLocalUserId = this.mPlayerParameters.getUserId();
        }
        this.mReportHandler.setUserId(this.mLocalUserId);
        this.mStreamingInterruptDetectPeriod = this.mPlayerParameters.getStreamingInterruptDetectInterval() / 200;
        if (this.mStreamingInterruptDetectPeriod <= 0) {
            this.mStreamingInterruptDetectPeriod = 30;
        }
        this.mStatsReportPeriod = this.mPlayerParameters.getStatsReportIntervalMs() / 200;
        if (this.mStatsReportPeriod <= 0) {
            this.mStatsReportPeriod = 5;
        }
        this.mBufferingDetectPeriod = this.mPlayerParameters.getBufferingDetectIntervalMs() / 200;
        if (this.mBufferingDetectPeriod <= 0) {
            this.mBufferingDetectPeriod = 1;
        }
        this.mAudioBufferUnderLoadLevel = this.mPlayerParameters.getAudioBufferUnderLoadLevel();
        if (this.mAudioBufferUnderLoadLevel < 0) {
            this.mAudioBufferUnderLoadLevel = 20;
        }
        this.mAudioBufferMaintainableLevel = this.mPlayerParameters.getAudioBufferMaintainableLevel();
        if (this.mAudioBufferMaintainableLevel < 0) {
            this.mAudioBufferMaintainableLevel = 50;
        }
        this.mIsBuffing = false;
        this.mIsRenderViewVisible = false;
        if (this.mHttpConnection == null) {
            this.mHttpConnection = new AsyncHttpRequest(this.mPullUrl, this.mHttpEvents);
            this.mHttpConnection.open();
        }
        initPeerConnectionClient();
    }

    public boolean initPeerConnectionClient() {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.close();
            this.peerConnectionClient = null;
        }
        this.peerConnectionClient = PeerConnectionClient.getInstance();
        this.peerConnectionClient.setHasAudioSend(false);
        this.peerConnectionClient.setHasVideoSend(false);
        this.peerConnectionClient.setHasDataSend(false);
        this.peerConnectionClient.setHasAudioRecv(true);
        this.peerConnectionClient.setHasVideoRecv(true);
        this.peerConnectionClient.setHasDataRecv(true);
        if (this.mPlayerParameters != null) {
            this.peerConnectionClient.setEnableDebugLog(this.mPlayerParameters.getEnableDebug());
        }
        this.mRemoteSamplesInterceptor = new RemoteAudioSamplesInterceptor();
        WeakReference weakReference = new WeakReference(this.mContext);
        int i = this.mDefaultSettings.VideoFps;
        String upperCase = this.mDefaultSettings.VideoCodec.toUpperCase();
        int i2 = this.mDefaultSettings.VideoMaxkbps;
        int i3 = this.mDefaultSettings.VideoMinkbps;
        boolean z = this.mDefaultSettings.MicPhoneMuted;
        boolean z2 = this.mDefaultSettings.CameraMuted;
        boolean z3 = this.mDefaultSettings.EnableFixedResolution;
        boolean z4 = this.mDefaultSettings.enableJitterRetransmission;
        boolean z5 = this.mDefaultSettings.EnableRequiredResolutionAligment32;
        boolean z6 = this.mDefaultSettings.EnableHighProfile;
        int i4 = this.mDefaultSettings.AudioMaxkbps;
        RtcParameterSettings.RtcAudioBitrateMode rtcAudioBitrateMode = this.mDefaultSettings.audioBitrateMode;
        RtcParameterSettings.RtcAudioChannel rtcAudioChannel = this.mDefaultSettings.transportAudioChannel;
        int i5 = this.mDefaultSettings.EncodeBitrateMode;
        boolean z7 = this.mDefaultSettings.EnableHisiH264HW;
        boolean z8 = this.mDefaultSettings.EnableMTKH264Decode;
        int i6 = this.mDefaultSettings.AudioBufferPackets;
        int i7 = this.mDefaultSettings.AudioPlayoutDelay;
        int i8 = this.mDefaultSettings.AudioCodecComplex;
        int i9 = this.mDefaultSettings.weakNetworkPolicy;
        BRTCPlayerParameters bRTCPlayerParameters = this.mPlayerParameters;
        this.peerConnectionParameters = new PeerConnectionClient.PeerConnectionParameters(false, -1, -1, i, upperCase, true, 0, "", false, false, false, false, false, false, false, false, true, true, i2, i3, z, z2, true, z3, z4, z5, z6, i4, rtcAudioBitrateMode, rtcAudioChannel, i5, z7, z8, 0, i6, i7, i8, true, 2, i9, bRTCPlayerParameters == null ? false : bRTCPlayerParameters.isEnableEncrypt(), this.mDefaultSettings.AutoPublish, this.mDefaultSettings.AutoSubScribe);
        this.peerConnectionParameters.setEnableMultistream(this.mDefaultSettings.EnableMultistream);
        this.peerConnectionClient.createPeerConnectionFactory((Context) weakReference.get(), this.mRootEglBase, this.peerConnectionParameters, this);
        PeerConnectionClient peerConnectionClient = this.peerConnectionClient;
        RtcReportHandle rtcReportHandle = this.mReportHandler;
        rtcReportHandle.getClass();
        peerConnectionClient.setStuckEventListener(new RtcReportHandle.AudioStuckEvent());
        this.peerConnectionClient.setEnableSLIReport(this.mEnablePullQualityMonitor);
        this.mReportHandler.setEnableMultistream(this.mDefaultSettings.EnableMultistream);
        Logging.m5305d("BRTCPlayer", "Rtc Sdk Version  " + Constraints.sdkVersion());
        return true;
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setEventObserver(BRTCPlayerEvents bRTCPlayerEvents) {
        this.mEventObserver = bRTCPlayerEvents;
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setSurfaceView(RTCVideoView rTCVideoView) {
        RTCVideoView rTCVideoView2 = this.mRenderView;
        if (rTCVideoView2 != null) {
            rTCVideoView2.release();
            this.mRenderView = null;
        }
        this.mRenderView = rTCVideoView;
        this.mIsRenderViewVisible = false;
        this.mRenderView.setRoomName(this.mRoomName);
        this.mRenderViewTree = this.mRenderView.getViewTreeObserver();
        if (this.mRenderView.getViewTreeObserver() != null && this.mRenderView.getViewTreeObserver().isAlive()) {
            this.mRenderView.getViewTreeObserver().addOnGlobalLayoutListener(this.mRenderViewVisibleListener);
        }
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.1
            @Override // java.lang.Runnable
            public void run() {
                if (BRTCPlayerImpl.this.mRenderView != null) {
                    try {
                        BRTCPlayerImpl.this.mRenderView.init(BRTCPlayerImpl.this.mRootEglBase.getEglBaseContext(), BRTCPlayerImpl.this.mRendererEvents);
                    } catch (Exception e) {
                        BRTCPlayerImpl bRTCPlayerImpl = BRTCPlayerImpl.this;
                        bRTCPlayerImpl.logE("init render view fail:" + e.getStackTrace());
                    }
                    BRTCPlayerImpl.this.mRenderView.setEnableHardwareScaler(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRenderViewTreeListener() {
        RTCVideoView rTCVideoView = this.mRenderView;
        if (rTCVideoView == null || rTCVideoView.getViewTreeObserver() == null || !this.mRenderView.getViewTreeObserver().isAlive()) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                this.mRenderView.getViewTreeObserver().removeOnGlobalLayoutListener(this.mRenderViewVisibleListener);
            } else {
                this.mRenderView.getViewTreeObserver().removeGlobalOnLayoutListener(this.mRenderViewVisibleListener);
            }
        } catch (IllegalStateException e) {
            logE("remove on globalLayout listener fail: " + e.getMessage());
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public synchronized void prepareAsync() {
        if (!TextUtils.isEmpty(this.mStreamUrl) && this.mStreamUrl.startsWith("webrtc://")) {
            if (this.mCurrentState != BRTCPlayer.PlayerState.STATE_INITIALIZED && this.mCurrentState != BRTCPlayer.PlayerState.STATE_STOPPED) {
                if (this.mEventObserver != null) {
                    BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
                    bRTCPlayerEvents.onError(10008, "Can not invoke prepare in illegal state " + this.mCurrentState.toString() + " url :" + this.mStreamUrl);
                }
                return;
            }
            setCurrentState(BRTCPlayer.PlayerState.STATE_PREPARING);
            this.mTimeStatistician.setRoomName(this.mRoomName);
            this.mTimeStatistician.setStartTime();
            if (this.mPlayerParameters.isEnableSoLaterLoad() && !this.mIsLibraryLoaded && !RTCLoadManager.getInstance(this.mContext).isLoadCompleted()) {
                RTCLoadManager.getInstance(this.mContext).loadLibraries(this.mPlayerParameters.getSoLaterLoadUrl(), this.mPlayerParameters.getCpuType(), this.mLoadListener);
                return;
            } else {
                prepareAsyncInternal();
                return;
            }
        }
        logD("Prepare failed invalid stream url format with " + this.mStreamUrl);
        if (this.mEventObserver != null) {
            BRTCPlayerEvents bRTCPlayerEvents2 = this.mEventObserver;
            bRTCPlayerEvents2.onError(10000, "Prepare failed invalid stream url format with " + this.mStreamUrl);
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setPlayWhenReady(boolean z) {
        this.mAutoPlay = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareAsyncInternal() {
        this.mPlayTransactionId = createPlayerHandler();
        logD("Create play transactionId:" + this.mPlayTransactionId);
        offerPeerConnection(this.mPlayTransactionId);
        this.mIsBuffing = false;
        this.mBufferingDetectCount = 0;
        this.mStreamingValidityDetectCount = 0;
        this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_PREPARE_ASYNC);
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void playWithRemoteSdp(String str) {
        SessionDescription sessionDescription = new SessionDescription(SessionDescription.Type.ANSWER, str);
        if (this.peerConnectionClient != null) {
            logD("set remote sdp with transaction id " + this.mPlayTransactionId);
            this.peerConnectionClient.setRemoteDescription(this.mPlayTransactionId, sessionDescription);
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public synchronized void startPlay() {
        if (this.mCurrentState != BRTCPlayer.PlayerState.STATE_PREPARED) {
            if (this.mEventObserver != null) {
                BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
                bRTCPlayerEvents.onError(10008, "Can not invoke start in illegal state " + this.mCurrentState.toString() + " url :" + this.mStreamUrl);
            }
            return;
        }
        setCurrentState(BRTCPlayer.PlayerState.STATE_STARTED);
        this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_START);
        if (!TextUtils.isEmpty(this.mRemoteSdp)) {
            playWithRemoteSdp(this.mRemoteSdp);
            if (!TextUtils.isEmpty(this.mRemoteHandleId)) {
                this.mReportHandler.startPeerPullReport(this.mPlayTransactionId, this.peerConnectionClient);
                this.mReportHandler.startDeviceInfoReport();
            }
        } else {
            reportError(10006, "Remote sdp is null, url" + this.mStreamUrl);
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void pausePlay() {
        if (getPlayerState() == BRTCPlayer.PlayerState.STATE_PLAYING || getPlayerState() == BRTCPlayer.PlayerState.STATE_ERROR) {
            pauseAudio();
            if (this.mRenderView != null && hasVideo()) {
                this.mRenderView.pauseVideo();
            }
            setCurrentState(BRTCPlayer.PlayerState.STATE_PAUSED);
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void resumePlay() {
        if (getPlayerState() == BRTCPlayer.PlayerState.STATE_PAUSED) {
            if (this.mRenderView != null && hasVideo()) {
                this.mRenderView.disableFpsReduction();
            }
            resumeAudio();
            setCurrentState(BRTCPlayer.PlayerState.STATE_PLAYING);
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void stopPlay() {
        if (getPlayerState() == BRTCPlayer.PlayerState.STATE_IDLE || getPlayerState() == BRTCPlayer.PlayerState.STATE_INITIALIZED || getPlayerState() == BRTCPlayer.PlayerState.STATE_STOPPED) {
            return;
        }
        closePeer(this.mPlayTransactionId);
        this.mPlayTransactionId = null;
        this.mStreamingValidityDetectCount = 0;
        this.mIsBuffing = false;
        this.mBufferingDetectCount = 0;
        this.mIsRenderViewVisible = false;
        setCurrentState(BRTCPlayer.PlayerState.STATE_STOPPED);
    }

    private void closePeer(final BigInteger bigInteger) {
        if (bigInteger != null) {
            this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    if (BRTCPlayerImpl.this.peerConnectionClient != null) {
                        BRTCPlayerImpl.this.peerConnectionClient.closePeer(bigInteger);
                    }
                }
            });
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public boolean hasVideo() {
        return this.mVideoTrack != null;
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public boolean hasAudio() {
        return this.mAudioTrack != null;
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public BRTCPlayer.PlayerState getPlayerState() {
        return this.mCurrentState;
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setVolume(double d) {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.setVolume(d);
        }
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setScalingType(RTCVideoView.ScalingType scalingType) {
        RTCVideoView rTCVideoView = this.mRenderView;
        if (rTCVideoView == null) {
            return;
        }
        rTCVideoView.setScalingType(RendererCommon.ScalingType.values()[scalingType.ordinal()]);
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setStreamUri(String str) {
        this.mStreamUrl = str;
        if (TextUtils.isEmpty(this.mStreamUrl)) {
            return;
        }
        this.mRoomName = Uri.parse(this.mStreamUrl).getLastPathSegment();
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void setMediaServerIp(String str) {
        this.mMediaServerIp = str;
    }

    @Override // com.baidu.rtc.player.BRTCPlayer
    public void releasePlayer() {
        BigInteger bigInteger;
        RTCLoadManager.getInstance(this.mContext).release();
        RTCMediaRecorderImpl rTCMediaRecorderImpl = this.mRemoteVideoRecorder;
        if (rTCMediaRecorderImpl != null && rTCMediaRecorderImpl.isRecording()) {
            this.mRemoteVideoRecorder.stopRecording();
        }
        if (this.mRenderView != null) {
            ViewTreeObserver viewTreeObserver = this.mRenderViewTree;
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                this.mRenderViewTree.removeOnGlobalLayoutListener(this.mRenderViewVisibleListener);
            }
            this.mRenderView.release();
            this.mRenderView = null;
        }
        EglBase eglBase = this.mRootEglBase;
        if (eglBase != null) {
            eglBase.release();
        }
        AsyncHttpRequest asyncHttpRequest = this.mHttpConnection;
        if (asyncHttpRequest != null) {
            asyncHttpRequest.close();
        }
        RtcReportHandle rtcReportHandle = this.mReportHandler;
        if (rtcReportHandle != null && (bigInteger = this.mPlayTransactionId) != null) {
            rtcReportHandle.stopPeerReport(bigInteger, this.peerConnectionClient);
        }
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setAudioEnabled(false);
            this.peerConnectionClient.setVideoEnabled(false);
            this.peerConnectionClient.close();
            this.peerConnectionClient = null;
        }
        this.mPlayTransactionId = null;
        this.mAudioTrack = null;
        this.mVideoTrack = null;
        setCurrentState(BRTCPlayer.PlayerState.STATE_IDLE);
        this.mEventObserver = null;
        System.gc();
    }

    private boolean isCanPlayState() {
        return (this.mCurrentState == BRTCPlayer.PlayerState.STATE_IDLE || this.mCurrentState == BRTCPlayer.PlayerState.STATE_STARTED || this.mCurrentState == BRTCPlayer.PlayerState.STATE_PREPARED) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentState(BRTCPlayer.PlayerState playerState) {
        if (this.mCurrentState != playerState) {
            this.mCurrentState = playerState;
            BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
            if (bRTCPlayerEvents != null) {
                bRTCPlayerEvents.onPlayerStateChanged(this.mCurrentState);
            }
        }
    }

    private void offerPeerConnection(final BigInteger bigInteger) {
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.player.BRTCPlayerImpl.6
            @Override // java.lang.Runnable
            public void run() {
                if (BRTCPlayerImpl.this.peerConnectionClient != null) {
                    BRTCPlayerImpl.this.peerConnectionClient.createPeerConnection(BRTCPlayerImpl.this.mRootEglBase.getEglBaseContext(), null, null, null, null, null, bigInteger);
                    BRTCPlayerImpl.this.peerConnectionClient.createOffer(bigInteger, true);
                }
            }
        });
    }

    private BigInteger createPlayerHandler() {
        return BigInteger.valueOf(Long.valueOf(CommonUtils.randomNumber(14)).longValue());
    }

    public void pauseAudio() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.setEnabled(false);
        }
    }

    public void resumeAudio() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.setEnabled(true);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onLocalDescription(SessionDescription sessionDescription, BigInteger bigInteger, boolean z) {
        this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_LOCAL_SDP_SET);
        logD(bigInteger + " :" + sessionDescription.type.toString() + " is \n " + sessionDescription.description);
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1009, sessionDescription.description);
        }
        prepareSdp(bigInteger.longValue(), sessionDescription.description);
    }

    public void prepareSdp(long j, String str) {
        RemoteSdpRequest remoteSdpRequest = new RemoteSdpRequest(str, j, this.mStreamUrl, this.mMediaServerIp);
        logD("Connecting to signaling server: " + this.mPullUrl + "\n offer sdp:" + str);
        if (this.mCurrentState == BRTCPlayer.PlayerState.STATE_PREPARING) {
            if (this.mHttpConnection.isInvalid()) {
                this.mHttpConnection.open();
            }
            this.mHttpConnection.request(remoteSdpRequest.toJSONString());
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteDescription(SessionDescription sessionDescription, BigInteger bigInteger) {
        logD(bigInteger + " :" + sessionDescription.type.toString() + " is \n" + sessionDescription.description);
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1010, "remote sdp has set.");
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionError(String str) {
        reportError(10003, str);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceCandidate(IceCandidate iceCandidate, BigInteger bigInteger) {
        if (iceCandidate != null) {
            logD("onIceCandidate " + iceCandidate.toString());
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
        if (iceCandidateArr != null) {
            for (IceCandidate iceCandidate : iceCandidateArr) {
                logD("onIceCandidatesRemoved " + iceCandidate.toString());
            }
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceConnected() {
        this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_ICE_CONNECTED);
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1001, "ice connected");
        }
        logD("ice connected.");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceDisconnected() {
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1006, "ice disconnected");
        }
        logD("ice disconnected.");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceFailed() {
        reportError(10001, "Ice negotiate failed");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionClosed() {
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1002, "peer connection closed");
        }
        logD("peer connection closed.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(int i, String str) {
        this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_ERROR);
        logD("report error: " + str + " time statistic:" + this.mTimeStatistician.toString());
        if (getPlayerState() == BRTCPlayer.PlayerState.STATE_ERROR || getPlayerState() == BRTCPlayer.PlayerState.STATE_IDLE) {
            logD("Is current state " + getPlayerState() + " skip error report.");
            return;
        }
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1008, this.mTimeStatistician);
            this.mEventObserver.onError(i, str);
        }
        if (this.mIsEnableErrorInfoMonitor) {
            this.mReportHandler.reportError(i, str);
        }
        setCurrentState(BRTCPlayer.PlayerState.STATE_ERROR);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionStatsReady(StatsReport[] statsReportArr, BigInteger bigInteger, PeerConnectionClient.StatsEventsType statsEventsType) {
        HUDStatistics updateStatsData;
        RtcReportHandle rtcReportHandle = this.mReportHandler;
        if (rtcReportHandle == null || bigInteger == null || (updateStatsData = rtcReportHandle.updateStatsData(statsReportArr, bigInteger)) == null) {
            return;
        }
        if (statsEventsType == PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT) {
            if (this.mEventObserver != null) {
                int i = updateStatsData.mAudioJitterBufferMs;
                if (i < this.mAudioBufferUnderLoadLevel) {
                    this.mBufferingDetectCount++;
                    if (this.mBufferingDetectCount >= this.mBufferingDetectPeriod && !this.mIsBuffing) {
                        this.mIsBuffing = true;
                        this.mBufferingDetectCount = 0;
                        this.mEventObserver.onInfoUpdated(1004, "Buffering start with buffer capacity " + i);
                    } else {
                        logD("Audio jitter buffer underflow " + i + " ms times " + this.mBufferingDetectCount);
                    }
                } else if (i > this.mAudioBufferMaintainableLevel) {
                    if (this.mIsBuffing) {
                        this.mIsBuffing = false;
                        this.mEventObserver.onInfoUpdated(1005, "Buffering end with buffer capacity " + i);
                    }
                    this.mBufferingDetectCount = 0;
                }
                int i2 = this.mStatsReportCount + 1;
                this.mStatsReportCount = i2;
                if (i2 >= this.mStatsReportPeriod) {
                    RTCCommStatesReport rTCCommStatesReport = new RTCCommStatesReport(this.mPlayTransactionId, this.mRoomId, this.mUserId, this.mRemoteHandleId, updateStatsData);
                    rTCCommStatesReport.setDebugFlag(19);
                    this.mEventObserver.onInfoUpdated(1003, rTCCommStatesReport);
                    this.mStatsReportCount = 0;
                }
            }
            if (!this.mReportHandler.streamingValidityDetect(bigInteger)) {
                BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
                if (bRTCPlayerEvents != null) {
                    bRTCPlayerEvents.onInfoUpdated(1007, "No streaming detected count " + this.mStreamingValidityDetectCount);
                }
                int i3 = this.mStreamingValidityDetectCount + 1;
                this.mStreamingValidityDetectCount = i3;
                if (i3 != this.mStreamingInterruptDetectPeriod || getPlayerState() == BRTCPlayer.PlayerState.STATE_ERROR || getPlayerState() == BRTCPlayer.PlayerState.STATE_STOPPED) {
                    return;
                }
                logD("streaming interrupt error appeared!");
                stopPlay();
                reportError(10009, "streaming interrupted.");
                this.mStreamingValidityDetectCount = 0;
                return;
            }
            this.mStreamingValidityDetectCount = 0;
        } else if (statsEventsType == PeerConnectionClient.StatsEventsType.GET_SLI_EVENT || statsEventsType == PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
            this.mReportHandler.onPeerStatisticsReport(statsEventsType);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteRender(JanusConnection janusConnection, String str) {
        VideoTrack videoTrack;
        AudioTrack audioTrack;
        RTCVideoView rTCVideoView;
        if (janusConnection == null) {
            return;
        }
        if (this.mDefaultSettings.EnableMultistream) {
            videoTrack = janusConnection.videoTracks.get(str);
            audioTrack = janusConnection.audioTracks.get(str);
        } else {
            videoTrack = janusConnection.videoTrack;
            audioTrack = janusConnection.audioTrack;
        }
        this.mTimeStatistician.updateStepTime(PlayTimeStatistician.PlayStep.PLAY_ADD_STREAM);
        if (videoTrack != null && (rTCVideoView = this.mRenderView) != null) {
            videoTrack.addSink(rTCVideoView);
            this.mRenderView.attach();
            if (this.mVideoTrack != null) {
                this.mRenderView.setVideoTrackChanged(true);
            }
            this.mVideoTrack = janusConnection.videoTrack;
            RTCVideoView rTCVideoView2 = this.mRenderView;
            RtcReportHandle rtcReportHandle = this.mReportHandler;
            rtcReportHandle.getClass();
            rTCVideoView2.setStuckEventListener(new RtcReportHandle.VideoStuckEvent(janusConnection.handleId));
            this.mRenderView.setEnableSLIDataReport(this.mEnablePullQualityMonitor);
        }
        if (audioTrack != null) {
            this.mAudioTrack = audioTrack;
        }
        logD("add remote stream handleId: " + janusConnection.handleId);
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onInfoUpdated(1000, "remote video start rendering");
        }
        this.mStreamingValidityDetectCount = 0;
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteStreamStats(Boolean bool, Boolean bool2, BigInteger bigInteger) {
        this.mEventObserver.onRemoteStreamStats(bool.booleanValue(), bool2.booleanValue(), bigInteger);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteStreamStats(String str, String str2) {
        if (this.mEventObserver != null) {
            Logging.m5305d("BRTCPlayer", str + " " + str2);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteData(ByteBuffer byteBuffer) {
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onRemoteData(byteBuffer);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerClosed(BigInteger bigInteger) {
        RtcReportHandle rtcReportHandle = this.mReportHandler;
        if (rtcReportHandle == null || bigInteger == null) {
            return;
        }
        rtcReportHandle.stopPeerReport(bigInteger, this.peerConnectionClient);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onSEIRecv(ByteBuffer byteBuffer) {
        BRTCPlayerEvents bRTCPlayerEvents = this.mEventObserver;
        if (bRTCPlayerEvents != null) {
            bRTCPlayerEvents.onSEIRecv(byteBuffer);
        }
    }

    @Override // com.baidu.rtc.record.IMediaRecord
    public void startRecording(String str, MediaEncodeParams mediaEncodeParams, RecorderCallback recorderCallback) {
        if (getPlayerState() != BRTCPlayer.PlayerState.STATE_PLAYING || TextUtils.isEmpty(str)) {
            if (recorderCallback != null) {
                recorderCallback.onRecordCompleted(false, "Invalid state");
                return;
            }
            return;
        }
        if (this.mRemoteVideoRecorder == null) {
            if (this.mRemoteSamplesInterceptor == null) {
                this.mRemoteSamplesInterceptor = new RemoteAudioSamplesInterceptor();
            }
            this.mRemoteVideoRecorder = new RTCMediaRecorderImpl(this.mVideoTrack, this.mRemoteSamplesInterceptor);
        }
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setRemoteAudioSamplesReadyCallback(this.mRemoteSamplesInternalCallback);
        }
        if (mediaEncodeParams == null) {
            try {
                mediaEncodeParams = new MediaEncodeParams();
                logD("Use default encode params!");
            } catch (Exception e) {
                logD(e.toString());
                return;
            }
        }
        this.mRemoteVideoRecorder.startRecording(str, mediaEncodeParams, recorderCallback);
    }

    @Override // com.baidu.rtc.record.IMediaRecord
    public void stopRecording() {
        RemoteAudioSamplesInterceptor remoteAudioSamplesInterceptor = this.mRemoteSamplesInterceptor;
        if (remoteAudioSamplesInterceptor != null) {
            remoteAudioSamplesInterceptor.detachCallback();
            this.mRemoteSamplesInterceptor = null;
        }
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setRemoteAudioSamplesReadyCallback(null);
        }
        RTCMediaRecorderImpl rTCMediaRecorderImpl = this.mRemoteVideoRecorder;
        if (rTCMediaRecorderImpl != null) {
            rTCMediaRecorderImpl.stopRecording();
            this.mRemoteVideoRecorder = null;
        }
    }

    @Override // com.baidu.rtc.snapshot.ISnapShot
    public void takeSnapShot(String str, SnapShotCallback snapShotCallback) {
        if ((getPlayerState() == BRTCPlayer.PlayerState.STATE_PLAYING || getPlayerState() == BRTCPlayer.PlayerState.STATE_PAUSED) && !TextUtils.isEmpty(str)) {
            new SnapShotHelper(this.mRenderView, this.mHandler).takeSnapShot(str, snapShotCallback);
        } else if (snapShotCallback != null) {
            snapShotCallback.onSnapShotTake(false, "Invalid state with file path " + str);
        }
    }

    public static String version() {
        return Constraints.version();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logD(String str) {
        Logging.m5305d("BRTCPlayer", this.mRoomName + ": " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logE(String str) {
        Logging.m5304e("BRTCPlayer", this.mRoomName + ": " + str);
    }
}
