package com.baidu.rtc.internal;

import android.content.Context;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.cloud.rtcbridge.framecapture.IRtcFrameCapture;
import com.baidu.cloud.rtcbridge.framecapture.RtcFrameCaptureImpl;
import com.baidu.cloud.rtcbridge.framecapture.RtcFrameCapturerObserver;
import com.baidu.cloud.rtcbridge.frameprocessor.BRTCEffectParams;
import com.baidu.cloud.rtcbridge.frameprocessor.BRTCWatermarkParams;
import com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager;
import com.baidu.cloud.rtcbridge.frameprocessor.IRtcFrameProcessor;
import com.baidu.cloud.rtcbridge.frameprocessor.IRtcFrameProcessorManager;
import com.baidu.cloud.rtcbridge.frameprocessor.InnerCameraVideoProcessor;
import com.baidu.cloud.rtcbridge.frameprocessor.RtcFrameProcessorManager;
import com.baidu.rtc.BaiduRtcInterface;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.CommonDefine;
import com.baidu.rtc.ErrorInfo;
import com.baidu.rtc.IRTCVideoSink;
import com.baidu.rtc.JanusConnection;
import com.baidu.rtc.JanusHandle;
import com.baidu.rtc.JanusRTCInterface;
import com.baidu.rtc.KcpClient;
import com.baidu.rtc.PeerConnectionClient;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.RTCLoadManager;
import com.baidu.rtc.RTCVideoExternalRender;
import com.baidu.rtc.RTCVideoExternalRenderImp;
import com.baidu.rtc.RTCVideoView;
import com.baidu.rtc.RtcParameterSettings;
import com.baidu.rtc.ScreenCapturerAndroid;
import com.baidu.rtc.SdpPrefer;
import com.baidu.rtc.WebSocketChannel;
import com.baidu.rtc.audio.BRTCAudioProfileParams;
import com.baidu.rtc.audio.BRTCAudioProfileType;
import com.baidu.rtc.audio.BRTCAudioScenario;
import com.baidu.rtc.audio.BdRTCAudioManager;
import com.baidu.rtc.camera.CameraEventCallback;
import com.baidu.rtc.camera.CameraEventsHandlerAdapter;
import com.baidu.rtc.config.BRTCScreenShareParams;
import com.baidu.rtc.config.Constraints;
import com.baidu.rtc.logreport.CpuMonitor;
import com.baidu.rtc.logreport.ErrorInfoReport;
import com.baidu.rtc.logreport.HUDStatistics;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.baidu.rtc.logreport.RtcLogReport;
import com.baidu.rtc.logreport.SLIReportInterface;
import com.baidu.rtc.recorder.BRTCMediaRecorder;
import com.baidu.rtc.recorder.IRtcMediaRecorder;
import com.baidu.rtc.recorder.RtcAudioRecordListener;
import com.baidu.rtc.utils.BRtcPhoneStateManager;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.webrtc.CapturerObserver;
import com.webrtc.EglBase;
import com.webrtc.IceCandidate;
import com.webrtc.Loggable;
import com.webrtc.Logging;
import com.webrtc.SessionDescription;
import com.webrtc.StatsReport;
import com.webrtc.SurfaceTextureHelper;
import com.webrtc.VideoCapturer;
import com.webrtc.VideoFrame;
import com.webrtc.VideoProcessor;
import com.webrtc.VideoSink;
import com.webrtc.VideoTrack;
import com.webrtc.audio.JavaAudioDeviceModule;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaiduRtcRoomImp extends BaiduRtcRoom implements JanusRTCInterface, PeerConnectionClient.PeerConnectionEvents, BRtcPhoneStateManager.IPhoneStateChangeListener {
    private static final int ANTI_WEAK_REPORT_INTERVAL = 2000;
    public static final String BRTC_SDK_VERSION_PREFIX = "BRTC.Android.SDK V";
    private static final int COMMUNICATION_REPORT_INTERVAL = 2000;
    private static final int DEFAULT_CAMERA_ID = -1;
    private static final int DEVICEINFO_REPORT_INTERVAL = 300000;
    private static final String KCP_LOCAL_DOMAIN = "rtc-local.exp.bcelive.com";
    private static final int KCP_SERVER_DNS_TIMEOUT_MS = 2000;
    private static final int MAX_VIDEOVIEW_CNT = 30;
    private static final int SLI_REPORT_INTERVAL = 5000;
    public static final int STATE_VIDEO_CAPTURER_INIT = 1;
    public static final int STATE_VIDEO_CAPTURER_NONE = 0;
    public static final int STATE_VIDEO_CAPTURING = 2;
    private static final String TAG = "BaiduRtcRoomImp";
    private static final int TIME_LIMIT_NOT_RECEIVE_MEIDA_OK = 5000;
    private static VideoSink dummyStaticVideoSink = new VideoSink() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.24
        @Override // com.webrtc.VideoSink
        public void onFrame(VideoFrame videoFrame) {
        }
    };
    private static int mConfigAudioContenttype = 1;
    private static int mKcpLocalPort = 10201;
    private static String mKcpServerIp = "";
    public static boolean mbEnableDebugLog;
    private BRtcPhoneStateManager bRtcPhoneStateManager;
    private BdRTCAudioManager bdRTCAudioManager;
    private SurfaceTextureHelper localTextureHelper;
    private String mAppId;
    private BRTCAudioProfileParams mAudioProfile;
    private WeakReference<Context> mContext;
    private CpuMonitor mCpuMonitor;
    private String mCpuType;
    private String mDisplayName;
    private long mInitSdkDoneTs;
    private boolean mIsEnableSoLaterLoad;
    private boolean mIsMuteMic;
    private boolean mIsMuteSpeaker;
    private BaiduRtcRoom.RtcLiveTransferMode mLastLssMode;
    private Loggable mLoggable;
    private Logging.Severity mLoggableSeverity;
    private long mLoginSuccessTime;
    private Map<BigInteger, ArrayList<CommonDefine.StreamInfo>> mOperatingFeedStreams;
    private long mRoomId;
    private String mRoomName;
    private BRTCMediaRecorder mRtcRecorder;
    private long mSetLocalSdpTime;
    private long mSetRemoteSdpTime;
    private Context mSoContext;
    private long mStartConnectTime;
    private long mStartLoginTime;
    private long mSubscribeTime;
    private long mUserId;
    private long mUserJoinedTime;
    private boolean mVideoCapturerObserverStarted;
    private VideoSink mVideoSink;
    private EglBase rootEglBase;
    private IRtcFrameCapture rtcFrameCapture;
    private RtcLogReport rtcLogReport;
    private boolean mIsEnableExternalVideoCapturer = false;
    private volatile PeerConnectionClient peerConnectionClient = null;
    private PeerConnectionClient.PeerConnectionParameters peerConnectionParameters = null;
    private RTCVideoView mLocalRender = null;
    private RTCVideoView mLocalScreenRender = null;
    private RTCVideoView[] mRemoteRenderList = null;
    private VideoCapturer videoCapturer = null;
    @STATE_VIDEO_CAPTURER
    private int mVideoCapturerState = 0;
    private VideoProcessor localVideoProcessor = null;
    private VideoProcessor innerVideoProcessor = null;
    private VideoProcessor externalVideoProcessor = null;
    private String mJanusServerURL = "wss://rtc.exp.bcelive.com/janus";
    private String mKcpServerUrl = "rtc-kcp.exp.bcelive.com";
    private int mKcpServerPort = 10030;
    private boolean mKcpEnableCrypto = true;
    private BaiduRtcRoom.BdRtcRoomMode mRoomMode = BaiduRtcRoom.BdRtcRoomMode.BDRTC_ROOM_NORMAL;
    private int mRemoteVideoCnt = 0;
    private boolean mUsingFrontCamera = true;
    private boolean[] mHasVideoView = new boolean[30];
    private BaiduRtcRoom.BaiduRtcRoomDelegate mBaiduRtcRoomDelegate = null;
    private ConcurrentHashMap<Long, RTCVideoExternalRender> mUserIdVideoRendererMap = new ConcurrentHashMap<>();
    private IdentityHashMap<Long, RTCVideoExternalRender> mPendingRemoveRendererMap = new IdentityHashMap<>();
    private int mCameraID = -1;
    private RtcParameterSettings mParamSettings = new RtcParameterSettings();
    private RtcFrameProcessorManager mFrameProcessorManager = new RtcFrameProcessorManager();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private BigInteger mPublisherHandle = null;
    private BigInteger mSubscriberHandle = null;
    private volatile long mLastLeavingId = 0;
    private volatile long mLastGoneId = 0;
    private ConcurrentHashMap<BigInteger, BigInteger> mFeedToViewMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BigInteger, Map<String, RTCVideoView>> mRemoteRenderViewsMap = new ConcurrentHashMap<>();
    private boolean mbOnStatistics = true;
    private boolean mIsEnablePushQualityMonitor = true;
    private boolean mIsEnablePullQualityMonitor = true;
    private boolean mIsEnableErrorInfoMonitor = true;
    private boolean mIsUseDefaultErrorInfoMonitor = true;
    private String mErrorInfoMonitorEnv = "online";
    private String mQualityMonitorEnv = "online";
    private ConcurrentHashMap<BigInteger, HUDStatistics> mHUDStatisticsMap = new ConcurrentHashMap<>();
    private boolean mbStartReportDI = false;
    private boolean mIsAsListener = true;
    private boolean mIsAsPublisher = true;
    private boolean mIsCompulsive = false;
    private boolean mIsStreamPublish = false;
    private boolean enableWebRtcAGC = false;
    private boolean enableWebRtcANS = false;
    private boolean enableWebRtcAEC = false;
    private boolean enableBdAGC = true;
    private boolean enableBdANS = true;
    private boolean enableBdAEC = true;
    private boolean enableHardwareScaler = true;
    private String mLastLssURL = "";
    private boolean isEnableHighProfileConfirm = false;
    private BaiduRtcRoom.KeyAgreementProtocol keyAgreementProtocol = BaiduRtcRoom.KeyAgreementProtocol.BRTC_DLTS;
    private long mWssOpenedTime = 0;
    private boolean mIsLoginSuccess = false;
    private boolean isPublisherSendSuccess = true;
    private Runnable mediaListenerRunnable = new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.1
        @Override // java.lang.Runnable
        public void run() {
            if (BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate == null || !BaiduRtcRoomImp.this.isPublisherSendSuccess) {
                return;
            }
            BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate.onPeerConnectStateUpdate(2006);
        }
    };
    private ConcurrentHashMap<BigInteger, StreamStats> mRemoteStreamStats = new ConcurrentHashMap<>();
    private String mSoLaterLoadUrl = "";
    private KcpClient mKcpClient = null;
    private boolean mIsKcpDisconnected = false;
    private ConcurrentMap<Long, BaiduRtcRoom.RtcRoomUserInfo> userInfoMap = new ConcurrentHashMap();
    private RTCLoadManager.LoadListener mLoadListener = new RTCLoadManager.LoadListener() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.2
        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLoadError(int i, String str) {
            Logging.m5305d("BaiduRtcRoomImp", "Failed to download so.");
            if (BaiduRtcRoomImp.this.mIsEnableErrorInfoMonitor) {
                ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.SO_LATER_DOWNLOADING_FAIL);
            }
            if (BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate != null) {
                BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate.onErrorInfoUpdate(400);
            }
        }

        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLibsDownloadCompleted() {
            Logging.m5305d("BaiduRtcRoomImp", "libs download completed.");
        }

        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLoadSuccess() {
            Logging.m5305d("BaiduRtcRoomImp", "after loaded so, start to login room.");
            BaiduRtcRoomImp baiduRtcRoomImp = BaiduRtcRoomImp.this;
            baiduRtcRoomImp.loginRtcRoomWithRoomName(baiduRtcRoomImp.mRoomName, BaiduRtcRoomImp.this.mUserId, BaiduRtcRoomImp.this.mDisplayName, BaiduRtcRoomImp.this.mIsAsPublisher, BaiduRtcRoomImp.this.mIsAsListener);
        }

        @Override // com.baidu.rtc.RTCLoadManager.LoadListener
        public void onLoadProgress(float f) {
            Logging.m5305d("BaiduRtcRoomImp", "loaded so progress " + f);
        }
    };
    RTCAudioSamples.RTCExternalSamplesReadyCallback screenAudioSamplesReadyCallback = new RTCAudioSamples.RTCExternalSamplesReadyCallback() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.4
        @Override // com.baidu.rtc.RTCAudioSamples.RTCExternalSamplesReadyCallback
        public void onRtcAudioExternalSamplesReady(RTCAudioSamples rTCAudioSamples) {
            JavaAudioDeviceModule.ExternalSamplesReadyCallback externalSamplesCallback;
            if (BaiduRtcRoomImp.this.peerConnectionClient == null || (externalSamplesCallback = BaiduRtcRoomImp.this.peerConnectionClient.getExternalSamplesCallback()) == null) {
                return;
            }
            externalSamplesCallback.onWebRtcAudioExternalSamplesReady(rTCAudioSamples);
        }
    };
    private Runnable reportDeviceInfoRun = new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.25
        @Override // java.lang.Runnable
        public void run() {
            if (BaiduRtcRoomImp.this.rtcLogReport != null && (BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor)) {
                BaiduRtcRoomImp.this.reportDeviceInfo();
            }
            BaiduRtcRoomImp.this.mHandler.postDelayed(BaiduRtcRoomImp.this.reportDeviceInfoRun, 300000L);
        }
    };
    private long mInitSdkTs = System.currentTimeMillis();
    private WebSocketChannel mWebSocketChannel = new WebSocketChannel();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @IntRange(from = 0, m22209to = 2)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface STATE_VIDEO_CAPTURER {
    }

    public static void setUseTestEnv(boolean z) {
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void cameraFocusWithPoint(int i, int i2) {
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableLaterLoadSo(String str) {
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerClosed(BigInteger bigInteger) {
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onSEIRecv(ByteBuffer byteBuffer) {
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setBuffingFactor(float f) {
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRedenFactor(float f) {
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setWhitenFactor(float f) {
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void upLoadLog() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StreamStats {
        public BigInteger handleId;
        public Boolean hasAudio;
        public Boolean hasVideo;
        public String nickName;
        public long userId;
        public int volume = -1;

        public StreamStats(BigInteger bigInteger, long j, Boolean bool, Boolean bool2, String str) {
            this.nickName = "";
            this.handleId = bigInteger;
            this.userId = j;
            this.hasAudio = bool2;
            this.hasVideo = bool;
            this.nickName = str;
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setSoLaterLoadUrl(String str) {
        this.mSoLaterLoadUrl = str;
    }

    public void doDestroy() {
        RTCLoadManager.getInstance(this.mSoContext).release();
        logoutRtcRoom();
        RtcLogCapturer.release();
    }

    public BaiduRtcRoomImp(Context context, String str, String str2, String str3, boolean z, boolean z2) {
        this.mCpuType = "armeabi-v7a";
        this.mIsEnableSoLaterLoad = z;
        this.mSoContext = context;
        this.mContext = new WeakReference<>(context);
        this.mCpuType = str3;
        this.mWebSocketChannel.setAppId(str);
        this.mWebSocketChannel.setTokenStr(str2);
        this.mWebSocketChannel.setSDK(Constraints.sdkVersion());
        this.mAppId = str;
        if (z2) {
            this.bdRTCAudioManager = new BdRTCAudioManager(context);
        }
        this.bRtcPhoneStateManager = new BRtcPhoneStateManager();
        this.bRtcPhoneStateManager.listenPhoneState(context.getApplicationContext(), this);
        this.mWebSocketChannel.setDelegate(this);
        this.rootEglBase = EglBase.CC.create();
        int i = 0;
        while (true) {
            boolean[] zArr = this.mHasVideoView;
            if (i >= zArr.length) {
                break;
            }
            zArr[i] = false;
            i++;
        }
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setSpeakerPhoneState(false);
        }
        if (this.mIsEnablePushQualityMonitor || this.mIsEnablePullQualityMonitor || this.mIsEnableErrorInfoMonitor) {
            this.mCpuMonitor = new CpuMonitor(context);
            this.rtcLogReport = RtcLogReport.getInstance();
        }
        ErrorInfoReport.getInstance().setEnv(this.mErrorInfoMonitorEnv);
        ErrorInfoReport.getInstance().setVersion(Constraints.sdkVersion());
        ErrorInfoReport.getInstance().setAppId(this.mAppId);
        this.mInitSdkDoneTs = System.currentTimeMillis();
    }

    public static String version() {
        return Constraints.version();
    }

    public static void setVerbose(boolean z) {
        mbEnableDebugLog = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setBaiduRtcAppID(String str, String str2) {
        this.mWebSocketChannel.setAppId(str);
        this.mAppId = str;
        this.mWebSocketChannel.setTokenStr(str2);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean setLiveStreamingURL(String str) {
        this.mWebSocketChannel.setLssURL(str);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean setLiveStreamingRole(String str) {
        this.mWebSocketChannel.setLssRole(str);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean setLiveStreamingMix(boolean z) {
        this.mWebSocketChannel.setMixing(z);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean configLiveServerWithUrl(String str, boolean z, boolean z2, String str2, BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        if (!this.mLastLssURL.isEmpty() && this.mLastLssMode != rtcLiveTransferMode) {
            this.mWebSocketChannel.setLssConfigSecond(str, z, z2, str2, rtcLiveTransferMode);
            return true;
        }
        this.mLastLssURL = str;
        this.mLastLssMode = rtcLiveTransferMode;
        this.mWebSocketChannel.setRecording(z2);
        this.mWebSocketChannel.setLssURL(str);
        this.mWebSocketChannel.setMixing(z);
        this.mWebSocketChannel.setLssMixTemplate(str2);
        this.mWebSocketChannel.setLssTransferMode(rtcLiveTransferMode);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean startLiveServerStreaming(String str, boolean z, boolean z2, String str2, BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        this.mWebSocketChannel.startPublishLiveStream(str, z, z2, str2, rtcLiveTransferMode);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean stopLiveServerStreaming(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        this.mWebSocketChannel.stopPublishLiveStream(rtcLiveTransferMode);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean setMixLayoutPositionIndex(String str) {
        this.mWebSocketChannel.setMixLayoutPosition(str);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setExternalMixAudio(ByteBuffer byteBuffer, int i) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setExternalMixAudio(byteBuffer, i);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void startRoomMediaRelay(String str, long j, String str2) {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.startRoomMediaRelay(str, j, str2, this.mPublisherHandle);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.START_ROOM_MEDIA_RELAY, "startRoomMediaRelay", str, Long.valueOf(j));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopRoomMediaRelay(String str, long j) {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.stopRoomMediaRelay(str, j, this.mPublisherHandle);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.STOP_ROOM_MEDIA_RELAY, "stopRoomMediaRelay", str, Long.valueOf(j));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopRoomMediaRelayAll() {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.stopRoomMediaRelayAll(this.mPublisherHandle);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.STOP_ROOM_MEDIA_RELAY_ALL, "stopRoomMediaRelayAll");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setUserPlaybackVolume(long j, int i) {
        JanusHandle handleByFeed;
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null) {
            if (this.mParamSettings.EnableMultistream) {
                String audioMidByFeed = this.mWebSocketChannel.getAudioMidByFeed(BigInteger.valueOf(j));
                if (audioMidByFeed.isEmpty()) {
                    return;
                }
                this.peerConnectionClient.setAudioTrackVolume(this.mSubscriberHandle, audioMidByFeed, i);
            }
        } else if (webSocketChannel == null || (handleByFeed = webSocketChannel.getHandleByFeed(BigInteger.valueOf(j))) == null || this.peerConnectionClient == null) {
        } else {
            this.peerConnectionClient.setAudioTrackVolume(handleByFeed.handleId, i);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRoomMode(BaiduRtcRoom.BdRtcRoomMode bdRtcRoomMode) {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.setRoomMode(bdRtcRoomMode);
        }
        this.mRoomMode = bdRtcRoomMode;
    }

    public boolean isBigRoomMode() {
        return this.mRoomMode == BaiduRtcRoom.BdRtcRoomMode.BDRTC_ROOM_BIG_ROOM;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean startShareScreen() {
        if (this.peerConnectionClient == null) {
            onShareScreenStart(-4, "peerConnectionClient is null");
            return false;
        }
        this.peerConnectionClient.startShareScreen(createScreenCapturer(), this.mPublisherHandle, this.rootEglBase.getEglBaseContext());
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean stopShareScreen() {
        if (this.peerConnectionClient == null) {
            onShareScreenStop(-4, "peerConnectionClient is null");
            return false;
        }
        this.peerConnectionClient.stopShareScreen(this.mPublisherHandle);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean setRecording(boolean z) {
        this.mWebSocketChannel.setRecording(z);
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean setBaiduRtcRoomDelegate(BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate) {
        this.mBaiduRtcRoomDelegate = baiduRtcRoomDelegate;
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setParamSettings(final RtcParameterSettings rtcParameterSettings, RtcParameterSettings.RtcParamSettingType rtcParamSettingType) {
        Handler handler;
        RtcParameterSettings.RtcVideoEncodeParams rtcVideoEncodeParams;
        switch (rtcParamSettingType) {
            case RTC_PARAM_SETTINGS_ALL:
                if (rtcParameterSettings != null) {
                    this.mParamSettings = rtcParameterSettings;
                    mConfigAudioContenttype = this.mParamSettings.audioContentType;
                    break;
                }
                break;
            case RTC_VIDEO_PARAM_SETTINGS_BITRATE:
                this.mParamSettings.VideoMaxkbps = rtcParameterSettings.VideoMaxkbps;
                this.mParamSettings.VideoMinkbps = rtcParameterSettings.VideoMinkbps;
                if (this.peerConnectionClient != null && (handler = this.mHandler) != null) {
                    handler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (BaiduRtcRoomImp.this.peerConnectionClient == null || BaiduRtcRoomImp.this.mParamSettings.VideoMaxkbps <= 0 || rtcParameterSettings.VideoMinkbps <= 0) {
                                return;
                            }
                            Logging.m5305d("BaiduRtcRoomImp", "Set video maximum bitrate: " + BaiduRtcRoomImp.this.mParamSettings.VideoMinkbps + "-" + BaiduRtcRoomImp.this.mParamSettings.VideoMaxkbps);
                            BaiduRtcRoomImp.this.peerConnectionClient.setVideoBitrate(Integer.valueOf(BaiduRtcRoomImp.this.mParamSettings.VideoMinkbps), Integer.valueOf(BaiduRtcRoomImp.this.mParamSettings.VideoMaxkbps));
                        }
                    });
                    break;
                }
                break;
            case RTC_VIDEO_PARAM_SETTINGS_RENDER_MODE:
                this.mParamSettings.VideoRenderMode = rtcParameterSettings.VideoRenderMode;
                break;
            case RTC_PARAM_SETTINGS_SHARE_SCREEN:
                if (rtcParameterSettings.screenIntentData != null) {
                    this.mParamSettings.screenIntentData = rtcParameterSettings.screenIntentData;
                }
                this.mParamSettings.screenShareParams = rtcParameterSettings.screenShareParams;
                if (this.mParamSettings.videoEncodeParams == null) {
                    this.mParamSettings.videoEncodeParams = rtcParameterSettings.videoEncodeParams;
                } else if (rtcParameterSettings.videoEncodeParams != null && (rtcVideoEncodeParams = rtcParameterSettings.videoEncodeParams.get("video_screen")) != null) {
                    this.mParamSettings.videoEncodeParams.put("video_screen", rtcVideoEncodeParams);
                }
                if (this.peerConnectionParameters != null) {
                    if (this.mParamSettings.screenShareParams != null) {
                        this.peerConnectionParameters.screenShareParams = this.mParamSettings.screenShareParams;
                        break;
                    } else if (this.mParamSettings.videoEncodeParams != null && this.mParamSettings.videoEncodeParams.get("video_screen") != null) {
                        this.peerConnectionParameters.screenShareParams = new BRTCScreenShareParams();
                        this.peerConnectionParameters.screenShareParams.mVideoCaptureParams = (BRTCScreenShareParams.BRTCScreenShareVideoParams) this.mParamSettings.videoEncodeParams.get("video_screen");
                        break;
                    }
                }
                break;
        }
        if (this.mParamSettings != null) {
            RtcLogCapturer.reportLog(RtcLogEvent.SET_CONFIG, "setParamSettings", Boolean.valueOf(this.mParamSettings.AutoPublish), Boolean.valueOf(this.mParamSettings.AutoSubScribe), Boolean.valueOf(this.mParamSettings.EnableMultistream), Integer.valueOf(this.mParamSettings.VideoMaxkbps), this.mParamSettings.VideoRenderMode);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public int setAudioProfile(BRTCAudioProfileType bRTCAudioProfileType, BRTCAudioScenario bRTCAudioScenario) {
        if (this.mAudioProfile == null) {
            this.mAudioProfile = new BRTCAudioProfileParams();
        }
        this.mAudioProfile.setAudioProfileType(bRTCAudioProfileType);
        this.mAudioProfile.setScenarioType(bRTCAudioScenario);
        int i = 0;
        switch (bRTCAudioScenario) {
            case BRTC_AUDIO_SCENARIO_DEFAULT:
            case BRTC_AUDIO_SCENARIO_SPEECH:
                this.mAudioProfile.setAudioSource(7);
                this.mAudioProfile.setAudioContentType(1);
                this.mAudioProfile.setDisableBluetoothScoMode(false);
                break;
            case BRTC_AUDIO_SCENARIO_MUSIC:
                this.mAudioProfile.setAudioSource(0);
                this.mAudioProfile.setAudioContentType(2);
                this.mAudioProfile.setDisableBluetoothScoMode(false);
                break;
            case BRTC_AUDIO_SCENARIO_METAVERSE:
                this.mAudioProfile.setAudioSource(7);
                this.mAudioProfile.setAudioContentType(2);
                this.mAudioProfile.setDisableBluetoothScoMode(true);
                break;
            default:
                Logging.m5305d("BaiduRtcRoomImp", "invalid scenario to use default.");
                this.mAudioProfile.setAudioSource(7);
                this.mAudioProfile.setAudioContentType(1);
                this.mAudioProfile.setDisableBluetoothScoMode(false);
                i = -1;
                break;
        }
        return i + setupAudioProfile(bRTCAudioProfileType, this.mAudioProfile);
    }

    private int setupAudioProfile(BRTCAudioProfileType bRTCAudioProfileType, BRTCAudioProfileParams bRTCAudioProfileParams) {
        if (bRTCAudioProfileParams == null) {
            Logging.m5305d("BaiduRtcRoomImp", "audio profile params is null.");
            return -1;
        }
        switch (bRTCAudioProfileType) {
            case BRTC_AUDIO_PROFILE_LOW_QUALITY:
                bRTCAudioProfileParams.setAudioSampleRate(8000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setOutputAudioChannel(1);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(12);
                return 0;
            case BRTC_AUDIO_PROFILE_STANDARD:
                bRTCAudioProfileParams.setAudioSampleRate(16000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setOutputAudioChannel(1);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(32);
                return 0;
            case BRTC_AUDIO_PROFILE_DEFAULT:
            case BRTC_AUDIO_PROFILE_HIGH_QUALITY:
                bRTCAudioProfileParams.setAudioSampleRate(48000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setOutputAudioChannel(1);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(48);
                return 0;
            case BRTC_AUDIO_PROFILE_STEREO_HIGH_QUALITY:
                bRTCAudioProfileParams.setAudioSampleRate(48000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setAudioChannelForEncode(RtcParameterSettings.RtcAudioChannel.RTC_AUDIO_STEREO);
                bRTCAudioProfileParams.setOutputAudioChannel(2);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(80);
                return 0;
            case BRTC_AUDIO_PROFILE_SUPER_QUALITY:
                bRTCAudioProfileParams.setAudioSampleRate(48000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setOutputAudioChannel(1);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(96);
                return 0;
            case BRTC_AUDIO_PROFILE_STEREO_SUPER_QUALITY:
                bRTCAudioProfileParams.setAudioSampleRate(48000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setAudioChannelForEncode(RtcParameterSettings.RtcAudioChannel.RTC_AUDIO_STEREO);
                bRTCAudioProfileParams.setOutputAudioChannel(2);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(128);
                return 0;
            default:
                Logging.m5305d("BaiduRtcRoomImp", "invalid audio profile to use default.");
                bRTCAudioProfileParams.setAudioSampleRate(48000);
                bRTCAudioProfileParams.setInputAudioChannel(1);
                bRTCAudioProfileParams.setOutputAudioChannel(1);
                bRTCAudioProfileParams.setAudioMaxBitrateInKbps(48);
                return -1;
        }
    }

    private void setupAudioProfile(BRTCAudioProfileParams bRTCAudioProfileParams, RtcParameterSettings rtcParameterSettings) {
        if (bRTCAudioProfileParams == null || rtcParameterSettings == null) {
            return;
        }
        bRTCAudioProfileParams.setAudioSampleRate(rtcParameterSettings.AudioFrequency);
        bRTCAudioProfileParams.setInputAudioChannel(rtcParameterSettings.inputAudioChannel);
        bRTCAudioProfileParams.setOutputAudioChannel(rtcParameterSettings.outputAudioChannel);
        bRTCAudioProfileParams.setAudioCodec(rtcParameterSettings.AudioCodec);
        bRTCAudioProfileParams.setAudioMaxBitrateInKbps(rtcParameterSettings.AudioMaxkbps);
        bRTCAudioProfileParams.setAudioChannelForEncode(rtcParameterSettings.transportAudioChannel);
        bRTCAudioProfileParams.setAudioSource(rtcParameterSettings.AudioSource);
        bRTCAudioProfileParams.setAudioContentType(rtcParameterSettings.audioContentType);
        bRTCAudioProfileParams.setDisableBluetoothScoMode(rtcParameterSettings.disableBluetoothSocMode);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setEngineStateStatistics(boolean z) {
        this.mbOnStatistics = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableStatsToServer(boolean z, String str) {
        enableStatsToServer(z, z, str);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableStatsToServer(boolean z, boolean z2, String str) {
        this.mIsEnablePushQualityMonitor = z;
        this.mIsEnablePullQualityMonitor = z2;
        this.mQualityMonitorEnv = str;
        if (z || z2) {
            if (this.mCpuMonitor == null) {
                this.mCpuMonitor = new CpuMonitor(this.mSoContext);
            }
            if (this.rtcLogReport == null) {
                this.rtcLogReport = RtcLogReport.getInstance();
                return;
            }
            return;
        }
        CpuMonitor cpuMonitor = this.mCpuMonitor;
        if (cpuMonitor != null) {
            cpuMonitor.pause();
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableErrorInfoToServer(boolean z, String str) {
        this.mIsEnableErrorInfoMonitor = z;
        this.mIsUseDefaultErrorInfoMonitor = false;
        this.mErrorInfoMonitorEnv = str;
        ErrorInfoReport.getInstance().setEnv(str);
        if (z && this.rtcLogReport == null) {
            this.rtcLogReport = RtcLogReport.getInstance();
        }
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.setEnableErrorInfoMonitor(z);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableTraceInfoToServer(boolean z, String str) {
        RtcLogCapturer.setUserReportEnable(z);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableVoiceChange(boolean z) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.enableVoiceChange(z);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setVoiceChangeType(BaiduRtcRoom.BdRTCVoiceChangeType bdRTCVoiceChangeType) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setVoiceChangeType(bdRTCVoiceChangeType);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableWatermark(boolean z, BRTCWatermarkParams bRTCWatermarkParams) {
        getRtcFrameProcessorManager().enableWatermark(z, bRTCWatermarkParams);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableHumanSeg(boolean z, BRTCEffectParams bRTCEffectParams) {
        getRtcFrameProcessorManager().enableHumanSeg(z, bRTCEffectParams);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean loginRtcRoomWithRoomName(String str, long j, String str2) {
        return loginRtcRoomWithRoomName(str, j, str2, true, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean loginRtcRoomWithRoomName(String str, long j, String str2, boolean z) {
        Logging.m5305d("BaiduRtcRoomImp", "enable force login");
        this.mIsCompulsive = z;
        return loginRtcRoomWithRoomName(str, j, str2, true, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean loginRtcRoomWithRoomName(String str, long j, String str2, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        RtcParameterSettings rtcParameterSettings;
        this.mDisplayName = str2;
        this.mRoomName = str;
        this.mUserId = j;
        this.mIsAsPublisher = z;
        this.mIsAsListener = z2;
        RtcLogCapturer.setUserId(j);
        RtcLogCapturer.setParams(new RtcLogCapturer.LogParams(this.mAppId, 0L, this.mRoomName));
        RtcLogCapturer.setConsoleEnable(mbEnableDebugLog);
        RtcLogCapturer.reportEvent(RtcLogEvent.LOGIN_RTC_ROOM, j, "loginRtcRoomWithRoomName", str, Long.valueOf(j));
        if (this.mIsEnableSoLaterLoad && !RTCLoadManager.getInstance(this.mSoContext).isLoadCompleted()) {
            RTCLoadManager.getInstance(this.mSoContext).loadLibraries(this.mSoLaterLoadUrl, this.mCpuType, this.mLoadListener);
            return true;
        } else if (j == 0) {
            throw new InvalidParameterException("loginRtcRoomWithRoomName: Param Error,userId cann't be zero. zero is reserved.");
        } else {
            if (this.mAudioProfile == null) {
                this.mAudioProfile = new BRTCAudioProfileParams();
                setupAudioProfile(this.mAudioProfile, this.mParamSettings);
            }
            Logging.m5305d("BaiduRtcRoomImp", "use audio profile:" + this.mAudioProfile.toString());
            this.mWebSocketChannel.setRoomName(str);
            this.mWebSocketChannel.setUserId(j);
            if (str2 != null && !str2.isEmpty()) {
                this.mWebSocketChannel.setDisplayName(str2);
            }
            this.mWebSocketChannel.setVideoCodec(this.mParamSettings.VideoCodec);
            this.mWebSocketChannel.setAudioCodec(this.mAudioProfile.getAudioCodec());
            this.mWebSocketChannel.setAsPublisher(z);
            this.mWebSocketChannel.setAsListener(z2);
            Size videoSize = getVideoSize();
            int width = videoSize.getWidth();
            int height = videoSize.getHeight();
            boolean z5 = this.enableBdAEC;
            boolean z6 = this.enableBdANS;
            if (Build.MODEL.contains("Redmi 6 Pro")) {
                this.enableWebRtcAEC = true;
                this.enableWebRtcANS = true;
                z5 = false;
                z6 = false;
            }
            if (Build.MANUFACTURER.trim().toLowerCase().contains("samsung")) {
                this.enableWebRtcAEC = true;
                this.enableWebRtcANS = true;
                z3 = false;
                z4 = false;
            } else {
                z3 = z5;
                z4 = z6;
            }
            if (Build.HARDWARE.contains("mt6768")) {
                RtcParameterSettings rtcParameterSettings2 = this.mParamSettings;
                rtcParameterSettings2.EnableRequiredResolutionAligment32 = true;
                rtcParameterSettings2.EnableMTKH264Decode = false;
            }
            if (Build.MODEL.contains("Mi Note 2") || Build.MODEL.contains("V1809")) {
                this.mParamSettings.EncodeBitrateMode = 1;
            }
            if (this.mParamSettings.VideoCodec.equalsIgnoreCase("H263")) {
                if (Build.MANUFACTURER.contains("rockchip")) {
                    Logging.m5305d("BaiduRtcRoomImp", "current type is rockchip,use internal encoder.");
                    RtcParameterSettings rtcParameterSettings3 = this.mParamSettings;
                    rtcParameterSettings3.forceSoftwareEncoder = false;
                    rtcParameterSettings3.forceSoftwareDecoder = true;
                    rtcParameterSettings3.EncodeBitrateMode = 1;
                }
                if (Build.MANUFACTURER.contains("Ainemo")) {
                    Logging.m5305d("BaiduRtcRoomImp", "current type is Ainemo,use internal encoder.");
                    RtcParameterSettings rtcParameterSettings4 = this.mParamSettings;
                    rtcParameterSettings4.forceSoftwareEncoder = false;
                    rtcParameterSettings4.forceSoftwareDecoder = true;
                }
                if (Build.MODEL.contains("XDH-") || Build.MODEL.contains("XD-SDD10-2002") || Build.MODEL.contains("NV2001") || Build.MODEL.contains("NV6131A") || Build.MODEL.contains("NV7001a") || Build.MODEL.contains("NV5001") || Build.MODEL.contains("NV2101") || Build.MODEL.contains("NV6101") || Build.MODEL.contains("NV6001")) {
                    Logging.m5305d("BaiduRtcRoomImp", "current type is XDH- or NV or XD,use internal encoder.");
                    RtcParameterSettings rtcParameterSettings5 = this.mParamSettings;
                    rtcParameterSettings5.forceSoftwareEncoder = false;
                    rtcParameterSettings5.forceSoftwareDecoder = true;
                }
            }
            this.peerConnectionParameters = new PeerConnectionClient.PeerConnectionParameters(false, width, height, this.mParamSettings.VideoFps, this.mParamSettings.VideoCodec.toUpperCase(), true, 0, this.mAudioProfile.getAudioCodec(), false, false, this.enableWebRtcAEC, this.enableWebRtcAGC, this.enableWebRtcANS, this.enableBdAEC, this.enableBdAGC, this.enableBdANS, z3, z4, this.mParamSettings.VideoMaxkbps, this.mParamSettings.VideoMinkbps, this.mParamSettings.MicPhoneMuted, this.mParamSettings.CameraMuted, true, this.mParamSettings.EnableFixedResolution, this.mParamSettings.enableJitterRetransmission, this.mParamSettings.EnableRequiredResolutionAligment32, this.mParamSettings.EnableHighProfile, this.mAudioProfile.getAudioMaxBitrateInKbps(), this.mParamSettings.audioBitrateMode, this.mAudioProfile.getAudioChannelForEncode(), this.mParamSettings.EncodeBitrateMode, this.mParamSettings.EnableHisiH264HW, this.mParamSettings.EnableMTKH264Decode, this.mAudioProfile.getAudioSource(), this.mParamSettings.AudioBufferPackets, this.mParamSettings.AudioPlayoutDelay, this.mParamSettings.AudioCodecComplex, false, this.mAudioProfile.getAudioContentType(), this.mParamSettings.weakNetworkPolicy, this.mParamSettings.enableEncrypt, this.mParamSettings.AutoPublish, this.mParamSettings.AutoSubScribe);
            this.peerConnectionParameters.setForceSoftwareEncoder(this.mParamSettings.forceSoftwareEncoder);
            this.peerConnectionParameters.setForceSoftwareDecoder(this.mParamSettings.forceSoftwareDecoder);
            this.peerConnectionParameters.setEnableMultistream(this.mParamSettings.EnableMultistream);
            this.peerConnectionParameters.setEnableLowLatencyMode(this.mParamSettings.enableLowLatencyMode);
            this.peerConnectionParameters.setDisableDropFrame(this.mParamSettings.disableDropFrame);
            this.peerConnectionParameters.setEnableReportAudioLevel(this.mParamSettings.enableReportAudioLevel);
            this.peerConnectionParameters.setKeyAgreementProtocol(this.keyAgreementProtocol);
            if (this.mParamSettings.screenShareParams == null && this.mParamSettings.videoEncodeParams != null && this.mParamSettings.videoEncodeParams.get("video_screen") != null) {
                this.mParamSettings.screenShareParams = new BRTCScreenShareParams();
                this.mParamSettings.screenShareParams.mVideoCaptureParams = (BRTCScreenShareParams.BRTCScreenShareVideoParams) this.mParamSettings.videoEncodeParams.get("video_screen");
            }
            this.peerConnectionParameters.screenShareParams = this.mParamSettings.screenShareParams;
            if (!this.mParamSettings.HasAudio && this.peerConnectionParameters.screenShareParams.mAudioCaptureParams != null) {
                this.mAudioProfile.setInputAudioChannel(this.peerConnectionParameters.screenShareParams.mAudioCaptureParams.channel);
                this.mAudioProfile.setAudioSampleRate(this.peerConnectionParameters.screenShareParams.mAudioCaptureParams.sampleRate);
            }
            this.peerConnectionClient = PeerConnectionClient.getInstance();
            BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
            if (bdRTCAudioManager != null) {
                bdRTCAudioManager.initAudioMix(this.peerConnectionClient, this.mAudioProfile);
                this.bdRTCAudioManager.disableBluetoothSocMode(this.mAudioProfile.isDisableBluetoothScoMode());
                this.bdRTCAudioManager.setHeadSetObserver();
                this.bdRTCAudioManager.presetAudioDevice();
            }
            RtcParameterSettings rtcParameterSettings6 = this.mParamSettings;
            if (rtcParameterSettings6 != null) {
                this.mWebSocketChannel.setHasAudio(rtcParameterSettings6.HasAudio || this.mParamSettings.HasScreen);
                if (this.mParamSettings.EnableMultistream) {
                    this.mWebSocketChannel.setHasRemoteAudio(this.mParamSettings.HasRemoteAudio);
                }
                this.peerConnectionClient.setHasAudioSend(this.mParamSettings.HasAudio);
                this.peerConnectionClient.setHasAudioRecv(this.mParamSettings.HasAudio);
                this.mWebSocketChannel.setHasVideo(this.mParamSettings.HasVideo || this.mParamSettings.HasScreen);
                if (this.mParamSettings.EnableMultistream) {
                    this.mWebSocketChannel.setHasRemoteVideo(this.mParamSettings.HasRemoteVideo);
                }
                this.peerConnectionClient.setHasVideoSend(this.mParamSettings.HasVideo);
                this.peerConnectionClient.setHasVideoRecv(this.mParamSettings.HasVideo);
                this.peerConnectionClient.setHasScreenSend(this.mParamSettings.HasScreen);
                this.mWebSocketChannel.setHasData(this.mParamSettings.HasData);
                this.peerConnectionClient.setHasDataSend(this.mParamSettings.HasData);
                this.peerConnectionClient.setHasDataRecv(this.mParamSettings.HasData);
                this.peerConnectionClient.setAudioFreguency(this.mAudioProfile.getAudioSampleRate());
                this.peerConnectionClient.setInputAudioChannel(this.mAudioProfile.getInputAudioChannel());
                this.peerConnectionClient.setOutputAudioChannel(this.mAudioProfile.getOutputAudioChannel());
                this.mWebSocketChannel.setConnectionTimeoutMs(this.mParamSettings.ConnectionTimeoutMs);
                this.mWebSocketChannel.setReadTimeoutMs(this.mParamSettings.ReadTimeoutMs);
                this.mWebSocketChannel.setAutoPublish(this.mParamSettings.AutoPublish);
                this.mWebSocketChannel.setAutoSubScribe(this.mParamSettings.AutoSubScribe);
                this.mWebSocketChannel.setMediaServerIP(this.mParamSettings.MediaServerIP);
                this.mWebSocketChannel.setEnableAudioLevel(this.mParamSettings.EnableAudioLevel);
                this.mWebSocketChannel.setAudioLevelTopCount(this.mParamSettings.AudioLevelTopCount);
                this.mWebSocketChannel.setEnableMultistream(this.mParamSettings.EnableMultistream);
                this.mWebSocketChannel.setKeyAgreementProtocol(this.keyAgreementProtocol);
            }
            PeerConnectionClient peerConnectionClient = this.peerConnectionClient;
            BdRTCAudioManager bdRTCAudioManager2 = this.bdRTCAudioManager;
            peerConnectionClient.setExternalAudioRecord((bdRTCAudioManager2 != null && bdRTCAudioManager2.isEnableExternalAudioRecord()) || ((rtcParameterSettings = this.mParamSettings) != null && rtcParameterSettings.HasScreen));
            PeerConnectionClient peerConnectionClient2 = this.peerConnectionClient;
            BdRTCAudioManager bdRTCAudioManager3 = this.bdRTCAudioManager;
            peerConnectionClient2.setEnableAudioEffect(bdRTCAudioManager3 != null && bdRTCAudioManager3.isEnableAudioProcess());
            this.peerConnectionClient.setEnableDebugLog(mbEnableDebugLog);
            this.peerConnectionClient.createPeerConnectionFactory(this.mContext.get(), this.rootEglBase, this.peerConnectionParameters, this);
            this.peerConnectionClient.setStuckEventListener(new AudioStuckEvent());
            this.peerConnectionClient.setEnableSLIReport(this.mIsEnablePullQualityMonitor);
            this.peerConnectionClient.injectLoggable(this.mLoggable, this.mLoggableSeverity);
            RtcParameterSettings rtcParameterSettings7 = this.mParamSettings;
            if (rtcParameterSettings7 != null && rtcParameterSettings7.signalChannelMode == RtcParameterSettings.RtcSignalChannelMode.RTC_SIGNAL_CHANNEL_MODE_KCP) {
                if (this.mParamSettings.kcpServerDomain.length() > 0) {
                    this.mKcpServerUrl = this.mParamSettings.kcpServerDomain;
                    this.mKcpServerPort = this.mParamSettings.kcpServerPort;
                    this.mKcpEnableCrypto = this.mParamSettings.kcpEnableCrypto;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                try {
                    FutureTask futureTask = new FutureTask(new Callable() { // from class: com.baidu.rtc.internal.-$$Lambda$BaiduRtcRoomImp$d4u2sKTWmMVg5KWhyHivtLh8Fuw
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            List asList;
                            asList = Arrays.asList(InetAddress.getAllByName(BaiduRtcRoomImp.this.mKcpServerUrl));
                            return asList;
                        }
                    });
                    new Thread(futureTask).start();
                    List list = (List) futureTask.get(2000L, TimeUnit.MILLISECONDS);
                    if (list.size() > 0) {
                        mKcpServerIp = ((InetAddress) list.get(0)).getHostAddress();
                    }
                } catch (Exception e) {
                    mKcpServerIp = "";
                    e.printStackTrace();
                }
                if (mKcpServerIp.length() > 0) {
                    String str3 = this.mKcpEnableCrypto ? "wss://" : "ws://";
                    this.mJanusServerURL = str3 + "rtc-local.exp.bcelive.com:" + mKcpLocalPort + "/janus";
                    Log.d("BaiduRtcRoomImp", "kcp server host:" + this.mKcpServerUrl + " ip:" + mKcpServerIp + " port:" + this.mKcpServerPort + " crypto:" + str3);
                    createKcpClient();
                    this.mKcpClient.exit();
                    new Thread(new Runnable() { // from class: com.baidu.rtc.internal.-$$Lambda$BaiduRtcRoomImp$vcGBJL4nfP0gOPeNCbyZSFbT3CE
                        @Override // java.lang.Runnable
                        public final void run() {
                            BaiduRtcRoomImp.lambda$loginRtcRoomWithRoomName$1(BaiduRtcRoomImp.this, currentTimeMillis);
                        }
                    }).start();
                } else {
                    Log.d("BaiduRtcRoomImp", "kcp get server ip fail, change to tcp mode");
                    this.mStartConnectTime = System.currentTimeMillis();
                    this.mWebSocketChannel.initConnection(this.mJanusServerURL, this.mIsCompulsive);
                }
            } else {
                this.mStartConnectTime = System.currentTimeMillis();
                this.mWebSocketChannel.initConnection(this.mJanusServerURL, this.mIsCompulsive);
            }
            if (this.mIsUseDefaultErrorInfoMonitor) {
                this.mIsEnableErrorInfoMonitor = this.mJanusServerURL.contains("bcelive.com");
            }
            Logging.m5305d("BRTC", "loginRtcRoomWithRoomName: this " + this + " version: " + version());
            RtcLogCapturer.reportLog(RtcLogEvent.ENABLE_AUDIO_3A, "BaiduRtcRoomImp-loginRtcRoomWithRoomName", Boolean.valueOf(this.enableWebRtcAGC), Boolean.valueOf(this.enableWebRtcAEC), Boolean.valueOf(this.enableWebRtcANS), Boolean.valueOf(this.enableBdAGC), Boolean.valueOf(this.enableBdAEC), Boolean.valueOf(this.enableBdANS));
            return true;
        }
    }

    public static /* synthetic */ void lambda$loginRtcRoomWithRoomName$1(BaiduRtcRoomImp baiduRtcRoomImp, long j) {
        Log.d("BaiduRtcRoomImp", "convert kcp host to ip cost " + (System.currentTimeMillis() - j) + "ms");
        baiduRtcRoomImp.mStartConnectTime = System.currentTimeMillis();
        baiduRtcRoomImp.mWebSocketChannel.setIsKcpMode(true);
        baiduRtcRoomImp.mWebSocketChannel.initConnection(baiduRtcRoomImp.mJanusServerURL, baiduRtcRoomImp.mIsCompulsive);
        baiduRtcRoomImp.mKcpClient.main(mKcpServerIp, baiduRtcRoomImp.mKcpServerPort, mKcpLocalPort);
    }

    private Size getVideoSize() {
        int i = this.mParamSettings.VideoWidth;
        int i2 = this.mParamSettings.VideoHeight;
        String lowerCase = this.mParamSettings.VideoResolution.toLowerCase();
        int i3 = 640;
        if (lowerCase.contains("192x144")) {
            i3 = 192;
            i = 144;
        } else if (lowerCase.contains("320x180")) {
            i = 180;
            i3 = 320;
        } else if (lowerCase.contains("352x288")) {
            i = 288;
            i3 = 352;
        } else if (lowerCase.contains("480x320")) {
            i = 320;
            i3 = 480;
        } else if (lowerCase.contains("320x480")) {
            i3 = 320;
            i = 480;
        } else if (lowerCase.contains("480x360")) {
            i = 360;
            i3 = 480;
        } else if (lowerCase.contains("640x360")) {
            i = 360;
        } else if (lowerCase.contains("640x480")) {
            i = 480;
        } else if (lowerCase.contains("960x540")) {
            i3 = 960;
            i = 540;
        } else if (lowerCase.contains("1280x720")) {
            i3 = 1280;
            i = 720;
        } else if (lowerCase.contains("1920x1080")) {
            i3 = 1920;
            i = 1080;
        } else if (lowerCase.contains("3840x2160")) {
            i3 = 3840;
            i = 2160;
        } else if (lowerCase.contains("4096x2160")) {
            i3 = 4096;
            i = 2160;
        } else if (lowerCase.contains("7680x4320")) {
            i3 = 7680;
            i = 4320;
        } else if (lowerCase.contains("8192x4320")) {
            i3 = 8192;
            i = 4320;
        } else if (lowerCase.contains("128x96")) {
            i3 = 128;
            i = 96;
        } else if (lowerCase.contains("176x144")) {
            i3 = 176;
            i = 144;
        } else if (lowerCase.contains("352x288")) {
            i = 288;
            i3 = 352;
        } else if (lowerCase.contains("704x576")) {
            i3 = 704;
            i = 576;
        } else if (lowerCase.contains("1408x1152")) {
            i3 = 1408;
            i = 1152;
        } else {
            try {
                String[] split = lowerCase.split("\\(")[0].split("x");
                i2 = Integer.valueOf(split[0]).intValue();
                i = Integer.valueOf(split[1]).intValue();
                i3 = i2;
            } catch (Exception e) {
                i3 = i2;
                e.printStackTrace();
            }
        }
        if (i3 > 8192 || i > 4320) {
            i3 = 8192;
            i = 4320;
        }
        return new Size(i, i3);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public RTCAudioSamples.RTCExternalSamplesReadyCallback getExternalAudioSamplesCallback() {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            return bdRTCAudioManager.getExternalSamplesReadyCallback();
        }
        return null;
    }

    private void createKcpClient() {
        this.mIsKcpDisconnected = false;
        this.mKcpClient = new KcpClient(new BaiduRtcInterface() { // from class: com.baidu.rtc.internal.-$$Lambda$BaiduRtcRoomImp$Q_NKRS37krdRDvhV5Xtw_EEeR5o
            @Override // com.baidu.rtc.BaiduRtcInterface
            public final void onEvent(int i, byte[] bArr) {
                BaiduRtcRoomImp.lambda$createKcpClient$2(BaiduRtcRoomImp.this, i, bArr);
            }
        });
    }

    public static /* synthetic */ void lambda$createKcpClient$2(BaiduRtcRoomImp baiduRtcRoomImp, int i, byte[] bArr) {
        HUDStatistics hUDStatistics;
        if (i == 101) {
            if (baiduRtcRoomImp.mIsKcpDisconnected) {
                return;
            }
            baiduRtcRoomImp.mIsKcpDisconnected = true;
            baiduRtcRoomImp.onLoginError();
        } else if (i != 103 || baiduRtcRoomImp.mWebSocketChannel == null || (hUDStatistics = baiduRtcRoomImp.mHUDStatisticsMap.get(baiduRtcRoomImp.mPublisherHandle)) == null) {
        } else {
            KcpClient.PGatewayRtt pGatewayRtt = new KcpClient.PGatewayRtt();
            pGatewayRtt.unmarshall(bArr);
            if (pGatewayRtt.type == 0) {
                hUDStatistics.mWifiRtt = pGatewayRtt.rtt;
            } else {
                hUDStatistics.mWanRtt = pGatewayRtt.rtt;
            }
            Logging.m5305d("BaiduRtcRoomImp", "ping result type:" + pGatewayRtt.type + " rtt:" + pGatewayRtt.rtt);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean logoutRtcRoom() {
        int i = 0;
        RtcLogCapturer.reportLog(RtcLogEvent.LOGOUT_RTC_ROOM, "logoutRtcRoom");
        BRTCMediaRecorder bRTCMediaRecorder = this.mRtcRecorder;
        if (bRTCMediaRecorder != null) {
            bRTCMediaRecorder.release();
            this.mRtcRecorder = null;
        }
        BRtcPhoneStateManager bRtcPhoneStateManager = this.bRtcPhoneStateManager;
        if (bRtcPhoneStateManager != null) {
            bRtcPhoneStateManager.release();
            this.bRtcPhoneStateManager = null;
        }
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.release();
        }
        if (this.peerConnectionClient == null) {
            return true;
        }
        this.peerConnectionClient.setAudioEnabled(false);
        this.peerConnectionClient.setVideoEnabled(false);
        stopPreview();
        this.peerConnectionClient.stopScreenSource();
        this.mWebSocketChannel.leaveRoom();
        this.mWebSocketChannel.finalize();
        this.peerConnectionClient.close();
        IRtcFrameCapture iRtcFrameCapture = this.rtcFrameCapture;
        if (iRtcFrameCapture != null) {
            iRtcFrameCapture.unregisterCapturerObserverAll();
            this.rtcFrameCapture = null;
        }
        SurfaceTextureHelper surfaceTextureHelper = this.localTextureHelper;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.dispose();
            this.localTextureHelper = null;
        }
        this.localVideoProcessor = null;
        this.innerVideoProcessor = null;
        this.mVideoCapturerState = 0;
        this.mVideoCapturerObserverStarted = false;
        CpuMonitor cpuMonitor = this.mCpuMonitor;
        if (cpuMonitor != null) {
            cpuMonitor.pause();
        }
        ErrorInfoReport.getInstance().resetParam();
        this.mHUDStatisticsMap.clear();
        Map<BigInteger, ArrayList<CommonDefine.StreamInfo>> map = this.mOperatingFeedStreams;
        if (map != null) {
            map.clear();
            this.mOperatingFeedStreams = null;
        }
        ConcurrentMap<Long, BaiduRtcRoom.RtcRoomUserInfo> concurrentMap = this.userInfoMap;
        if (concurrentMap != null) {
            concurrentMap.clear();
        }
        this.peerConnectionClient = null;
        this.mWebSocketChannel = null;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        RTCVideoView rTCVideoView = this.mLocalRender;
        if (rTCVideoView != null) {
            rTCVideoView.release();
            this.mLocalRender = null;
        }
        RTCVideoView rTCVideoView2 = this.mLocalScreenRender;
        if (rTCVideoView2 != null) {
            rTCVideoView2.release();
            this.mLocalScreenRender = null;
        }
        if (this.mVideoSink != null) {
            this.mVideoSink = null;
        }
        if (this.mRemoteRenderList != null) {
            while (true) {
                RTCVideoView[] rTCVideoViewArr = this.mRemoteRenderList;
                if (i >= rTCVideoViewArr.length) {
                    break;
                }
                if (rTCVideoViewArr[i] != null) {
                    rTCVideoViewArr[i].release();
                    this.mRemoteRenderList[i] = null;
                }
                i++;
            }
        }
        disposeRemoteRenderViews();
        disposeExternalRenderers();
        disposePendingRenderers();
        EglBase eglBase = this.rootEglBase;
        if (eglBase != null) {
            eglBase.release();
        }
        KcpClient kcpClient = this.mKcpClient;
        if (kcpClient != null) {
            kcpClient.stopPingRtt();
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        if (this.mBaiduRtcRoomDelegate != null) {
            this.mBaiduRtcRoomDelegate = null;
        }
        System.gc();
        return true;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void startPublish() {
        offerPeerConnection(this.mPublisherHandle);
        RtcLogCapturer.reportLog(RtcLogEvent.START_PUBLISH, "startPublish", this.mPublisherHandle);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void publishStreaming() {
        offerPeerConnection(this.mPublisherHandle);
        RtcLogCapturer.reportLog(RtcLogEvent.START_PUBLISH, "publishStreaming", this.mPublisherHandle);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopPublish() {
        if (this.mWebSocketChannel == null) {
            return;
        }
        RtcLogCapturer.reportLog(RtcLogEvent.STOP_PUBLISH, "stopPublish");
        this.mWebSocketChannel.unpublishHandle(this.mPublisherHandle);
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.stopAudioSource();
            stopPreview();
            this.peerConnectionClient.stopScreenSource();
            this.peerConnectionClient.closePeer(this.mPublisherHandle);
            if (this.mLocalRender != null) {
                new Handler().postDelayed(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.5
                    @Override // java.lang.Runnable
                    public void run() {
                        BaiduRtcRoomImp.this.mLocalRender.clearImage();
                    }
                }, 100L);
            }
            if (!this.mIsEnableExternalVideoCapturer) {
                VideoCapturer videoCapturer = this.videoCapturer;
                if (videoCapturer != null) {
                    videoCapturer.dispose();
                    this.videoCapturer = null;
                }
                IRtcFrameCapture iRtcFrameCapture = this.rtcFrameCapture;
                if (iRtcFrameCapture != null) {
                    iRtcFrameCapture.unregisterCapturerObserverAll();
                    this.rtcFrameCapture = null;
                }
                SurfaceTextureHelper surfaceTextureHelper = this.localTextureHelper;
                if (surfaceTextureHelper != null) {
                    surfaceTextureHelper.dispose();
                    this.localTextureHelper = null;
                }
                this.localVideoProcessor = null;
                this.innerVideoProcessor = null;
                this.mVideoCapturerState = 0;
                this.mVideoCapturerObserverStarted = false;
            }
        }
        this.mIsStreamPublish = false;
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setStopPublish(true);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeStreaming(int i, long j) {
        Logging.m5305d("BaiduRtcRoomImp", "subscribeStreaming  videoViewIdx = " + i + " feedId = " + j);
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel == null || webSocketChannel.getIsInSubscribing(BigInteger.valueOf(j))) {
            return;
        }
        if (i >= 0 && i < 30 && this.mRemoteRenderList != null) {
            this.mFeedToViewMap.put(BigInteger.valueOf(j), BigInteger.valueOf(i));
            this.mHasVideoView[i] = true;
        }
        subscribeStreamingInternal(j, true, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeStreaming(long j) {
        subscribeStreamingInternal(j, true, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeStreaming(long j, float f, float f2, float f3, int i, int i2) {
        subscribeStreaming(j);
        BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo = new BaiduRtcRoom.RtcRoomUserInfo();
        rtcRoomUserInfo.userId = j;
        rtcRoomUserInfo.horiAngle = f;
        rtcRoomUserInfo.vertAngle = f2;
        rtcRoomUserInfo.distance = f3;
        rtcRoomUserInfo.nearVol = i;
        rtcRoomUserInfo.mixerVol = i2;
        this.userInfoMap.put(Long.valueOf(j), rtcRoomUserInfo);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeStreaming(int i, long j, float f, float f2, float f3, int i2, int i3) {
        subscribeStreaming(i, j);
        BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo = new BaiduRtcRoom.RtcRoomUserInfo();
        rtcRoomUserInfo.userId = j;
        rtcRoomUserInfo.horiAngle = f;
        rtcRoomUserInfo.vertAngle = f2;
        rtcRoomUserInfo.distance = f3;
        rtcRoomUserInfo.nearVol = i2;
        rtcRoomUserInfo.mixerVol = i3;
        this.userInfoMap.put(Long.valueOf(j), rtcRoomUserInfo);
    }

    private void subscribeStreamingInternal(long j, boolean z, boolean z2) {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel == null || webSocketChannel.getIsInSubscribing(BigInteger.valueOf(j))) {
            return;
        }
        if (this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.subscribeStreamingMultistream(BigInteger.valueOf(j), z, z2);
        } else if (z && z2) {
            this.mWebSocketChannel.subscriberCreateHandle(BigInteger.valueOf(j), "out");
        }
        RtcLogCapturer.reportEvent(RtcLogEvent.SUBSCRIBE_STREAM, j, "subscribeStreaming", RtcLogCapturer.getNewJSONArray(RtcLogCapturer.getNewJSONObj("userId", Long.valueOf(j))));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeVideoStreaming(long j) {
        subscribeStreamingInternal(j, true, false);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeAudioStreaming(long j) {
        subscribeStreamingInternal(j, false, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopSubscribeVideoStreaming(long j) {
        stopSubscribeStreamingInternal(j, true, false);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopSubscribeAudioStreaming(long j) {
        stopSubscribeStreamingInternal(j, false, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeAllRemoteVideoStreams() {
        if (this.mWebSocketChannel != null && this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.setHasRemoteVideo(true);
            this.mWebSocketChannel.subscribeAllRemoteStreamMultistream(true, false);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void subscribeAllRemoteAudioStreams() {
        if (this.mWebSocketChannel != null && this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.setHasRemoteAudio(true);
            this.mWebSocketChannel.subscribeAllRemoteStreamMultistream(false, true);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopSubscribeAllRemoteVideoStreams() {
        if (this.mWebSocketChannel != null && this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.setHasRemoteVideo(false);
            this.mWebSocketChannel.unsubscribeAllRemoteStreamMultistream(true, false);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopSubscribeAllRemoteAudioStreams() {
        if (this.mWebSocketChannel != null && this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.setHasRemoteAudio(false);
            this.mWebSocketChannel.unsubscribeAllRemoteStreamMultistream(false, true);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void updateStreaming(long j, float f, float f2, float f3, int i, int i2) {
        BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo = new BaiduRtcRoom.RtcRoomUserInfo();
        rtcRoomUserInfo.userId = j;
        rtcRoomUserInfo.horiAngle = f;
        rtcRoomUserInfo.vertAngle = f2;
        rtcRoomUserInfo.distance = f3;
        rtcRoomUserInfo.nearVol = i;
        rtcRoomUserInfo.mixerVol = i2;
        if (this.userInfoMap.get(Long.valueOf(j)) != null && !TextUtils.isEmpty(this.userInfoMap.get(Long.valueOf(j)).ssrc)) {
            rtcRoomUserInfo.ssrc = this.userInfoMap.get(Long.valueOf(j)).ssrc;
        } else {
            RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBED_USER_SET_POSITION, "subscriberHandleRemoteJsep", Long.valueOf(j));
        }
        this.userInfoMap.put(Long.valueOf(j), rtcRoomUserInfo);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopSubscribeStreaming(long j) {
        stopSubscribeStreamingInternal(j, true, true);
    }

    private void stopSubscribeStreamingInternal(long j, boolean z, boolean z2) {
        try {
            if (this.mParamSettings.EnableMultistream) {
                this.mWebSocketChannel.subscriberOnLeavingMultistream(BigInteger.valueOf(j), z, z2);
            } else if (z && z2) {
                this.mWebSocketChannel.subscriberOnLeaving(this.mWebSocketChannel.getHandleByFeed(BigInteger.valueOf(j)));
            }
            if (this.userInfoMap != null && z && z2) {
                this.userInfoMap.remove(Long.valueOf(j));
            }
            RtcLogCapturer.reportEvent(RtcLogEvent.STOP_SUBSCRIBE, j, "stopSubscribeStreaming", RtcLogCapturer.getNewJSONArray(RtcLogCapturer.getNewJSONObj("userId", Long.valueOf(j))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void kickOffUserWithId(long j) {
        WebSocketChannel webSocketChannel;
        if (isBigRoomMode() || (webSocketChannel = this.mWebSocketChannel) == null) {
            return;
        }
        webSocketChannel.kickOffUserWithId(j);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void shutUpUserWithId(long j) {
        shutUpUserWithId(j, true);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void shutUpUserWithId(long j, boolean z) {
        WebSocketChannel webSocketChannel;
        if (isBigRoomMode() || (webSocketChannel = this.mWebSocketChannel) == null) {
            return;
        }
        webSocketChannel.shutUpUserWithId(j, z);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void disbandRoom() {
        WebSocketChannel webSocketChannel;
        if (isBigRoomMode() || (webSocketChannel = this.mWebSocketChannel) == null) {
            return;
        }
        webSocketChannel.disbandRoom();
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void startPreview() {
        Logging.m5305d("BaiduRtcRoomImp", "start to preview.");
        RtcLogCapturer.reportLog(RtcLogEvent.START_PREVIEW, "startPreview");
        if (this.peerConnectionClient != null && this.videoCapturer != null && this.mVideoCapturerState == 0) {
            this.peerConnectionClient.startVideoSource();
        } else {
            startVideoCapture();
        }
    }

    private void startVideoCapture() {
        int i = this.mVideoCapturerState;
        if (i == 2) {
            return;
        }
        if (i == 0) {
            this.videoCapturer = createVideoCapturer();
            this.localVideoProcessor = createVideoProcessor();
            this.localTextureHelper = SurfaceTextureHelper.create("video renderer", this.rootEglBase.getEglBaseContext(), false);
            this.videoCapturer.initialize(this.localTextureHelper, this.mContext.get(), new CapturerObserver() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.6
                @Override // com.webrtc.CapturerObserver
                public void onCapturerStarted(boolean z) {
                    if (BaiduRtcRoomImp.this.peerConnectionClient == null || BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver() == null) {
                        return;
                    }
                    BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver().onCapturerStarted(z);
                    BaiduRtcRoomImp.this.mVideoCapturerObserverStarted = true;
                }

                @Override // com.webrtc.CapturerObserver
                public void onCapturerStopped() {
                    if (BaiduRtcRoomImp.this.peerConnectionClient == null || BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver() == null) {
                        return;
                    }
                    BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver().onCapturerStopped();
                }

                @Override // com.webrtc.CapturerObserver
                public void onFrameCaptured(VideoFrame videoFrame) {
                    if (BaiduRtcRoomImp.this.peerConnectionClient == null || BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver() == null) {
                        if (BaiduRtcRoomImp.this.mLocalRender != null) {
                            BaiduRtcRoomImp.this.mLocalRender.onFrame(videoFrame);
                            return;
                        }
                        return;
                    }
                    if (!BaiduRtcRoomImp.this.mVideoCapturerObserverStarted) {
                        BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver().onCapturerStarted(true);
                        BaiduRtcRoomImp.this.mVideoCapturerObserverStarted = true;
                    }
                    BaiduRtcRoomImp.this.peerConnectionClient.getCapturerObserver().onFrameCaptured(videoFrame);
                }
            });
            this.mVideoCapturerState = 1;
        }
        Size videoSize = getVideoSize();
        this.videoCapturer.startCapture(videoSize.getWidth(), videoSize.getHeight(), this.mParamSettings.VideoFps);
        this.mVideoCapturerState = 2;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void stopPreview() {
        Logging.m5305d("BaiduRtcRoomImp", "stop preview.");
        RtcLogCapturer.reportLog(RtcLogEvent.STOP_PREVIEW, "stopPreview");
        if (this.peerConnectionClient != null && this.videoCapturer != null && this.mVideoCapturerState == 0) {
            this.peerConnectionClient.stopVideoSource();
        } else {
            stopVideoCapture();
        }
    }

    private void stopVideoCapture() {
        VideoCapturer videoCapturer;
        if (this.mVideoCapturerState != 2 || (videoCapturer = this.videoCapturer) == null) {
            return;
        }
        try {
            videoCapturer.stopCapture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mVideoCapturerState = 1;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setCameraID(int i) {
        this.mCameraID = i;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void switchCamera() {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.switchCamera();
        }
        RtcLogCapturer.reportLog(RtcLogEvent.SWITCH_CAMERA, "switchCamera");
        reportRoomEventInfo("SWITCH_CAMERA");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void muteCamera(boolean z) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setVideoEnabled(!z);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.MUTE_CAMERA, "muteCamera", Boolean.valueOf(z));
        reportRoomEventInfo("MUTE_CAMERA");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void cameraFocusWithPoint(float f, float f2) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.cameraFocusWithPoint(f, f2);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean isFocusSupported() {
        if (this.peerConnectionClient != null) {
            return this.peerConnectionClient.isFocusSupported();
        }
        return false;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setCameraZoom(float f) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setCameraZoom(f);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public float getMaxCameraZoom() {
        if (this.peerConnectionClient != null) {
            return this.peerConnectionClient.getMaxCameraZoom();
        }
        return 1.0f;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean isCameraZoomSupported() {
        if (this.peerConnectionClient != null) {
            return this.peerConnectionClient.isCameraZoomSupported();
        }
        return false;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void muteMicphone(boolean z) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setAudioEnabled(!z);
        }
        this.mIsMuteMic = z;
        RtcLogCapturer.reportLog(RtcLogEvent.MUTE_MICRO, "muteMicphone", Boolean.valueOf(z));
        reportRoomEventInfo("MUTE_MICPHONE");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableMicCapture(boolean z) {
        this.mIsMuteMic = !z;
        if (z) {
            if (this.peerConnectionClient != null) {
                this.peerConnectionClient.reStartCaptureAudio();
            }
        } else if (this.peerConnectionClient != null) {
            this.peerConnectionClient.stopCaptureAudio();
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void switchLoundSpeaker() {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.switchLoundSpeaker();
        }
        reportRoomEventInfo("SWITCH_LOUDSPEAKER");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void presetLoudSpeaker(boolean z) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.presetLoudSpeaker(z);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.PRESET_LOUD_SPEAKER, "presetLoudSpeaker", Boolean.valueOf(z));
        reportRoomEventInfo("PRESETLOUDSPEAKER");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean isSpeakerOn() {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            return bdRTCAudioManager.isSpeakerOn();
        }
        return false;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setLocalDisplay(final RTCVideoView rTCVideoView) {
        removeLocalDisplay();
        this.mLocalRender = rTCVideoView;
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.7
            @Override // java.lang.Runnable
            public void run() {
                if (BaiduRtcRoomImp.this.mLocalRender != null) {
                    try {
                        BaiduRtcRoomImp.this.mLocalRender.setUserId("");
                        BaiduRtcRoomImp.this.mLocalRender.init(BaiduRtcRoomImp.this.rootEglBase.getEglBaseContext(), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (BaiduRtcRoomImp.this.enableHardwareScaler) {
                        BaiduRtcRoomImp.this.mLocalRender.setEnableHardwareScaler(true);
                    }
                    BaiduRtcRoomImp.this.mLocalRender.attach();
                    if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                        BaiduRtcRoomImp.this.peerConnectionClient.addLocalSink(rTCVideoView);
                    }
                }
            }
        });
        RtcLogCapturer.reportLog(RtcLogEvent.SET_LOCAL_DISPLAY, "setLocalDisplay", Integer.valueOf(rTCVideoView.getId()));
    }

    public void setLocalScreenDisplay(RTCVideoView rTCVideoView) {
        this.mLocalScreenRender = rTCVideoView;
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.8
            @Override // java.lang.Runnable
            public void run() {
                if (BaiduRtcRoomImp.this.mLocalScreenRender != null) {
                    try {
                        BaiduRtcRoomImp.this.mLocalScreenRender.init(BaiduRtcRoomImp.this.rootEglBase.getEglBaseContext(), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (BaiduRtcRoomImp.this.enableHardwareScaler) {
                        BaiduRtcRoomImp.this.mLocalScreenRender.setEnableHardwareScaler(true);
                    }
                }
            }
        });
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteDisplay(RTCVideoView rTCVideoView) {
        setRemoteDisplayGroup(new RTCVideoView[]{rTCVideoView});
        RtcLogCapturer.reportLog(RtcLogEvent.SET_REMOTE_DISPLAY, "setRemoteDisplay", "NoUserId");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteDisplay(RTCVideoView rTCVideoView, long j) {
        if (rTCVideoView == null || j <= 0) {
            Logging.m5304e("BaiduRtcRoomImp", "set remote display fail cause illegality params.");
        } else {
            setRemoteDisplay(rTCVideoView, j, "video");
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteDisplay(final RTCVideoView rTCVideoView, final long j, String str) {
        JanusConnection janusConnectionByFeedId;
        CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList;
        if (rTCVideoView == null || j <= 0) {
            Logging.m5304e("BaiduRtcRoomImp", "set remote display fail cause illegality params.");
            return;
        }
        BigInteger valueOf = BigInteger.valueOf(j);
        processTargetView(rTCVideoView, str, valueOf);
        runOnMainThread(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RTCVideoView rTCVideoView2 = rTCVideoView;
                    rTCVideoView2.setUserId(j + "");
                    rTCVideoView.init(BaiduRtcRoomImp.this.rootEglBase.getEglBaseContext(), null);
                    if (BaiduRtcRoomImp.this.enableHardwareScaler) {
                        rTCVideoView.setEnableHardwareScaler(true);
                    }
                } catch (Exception e) {
                    Logging.m5304e("BaiduRtcRoomImp", "init render view fault: " + e.getMessage());
                }
            }
        });
        boolean z = false;
        RtcLogCapturer.reportEvent(RtcLogEvent.SET_REMOTE_DISPLAY, j, "setRemoteDisplay", Long.valueOf(j));
        if (this.mWebSocketChannel == null || this.peerConnectionClient == null || (janusConnectionByFeedId = getJanusConnectionByFeedId(valueOf)) == null || (copyOnWriteArrayList = this.mWebSocketChannel.getFeedMidMap().get(valueOf)) == null) {
            return;
        }
        Iterator<CommonDefine.StreamInfo> it = copyOnWriteArrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CommonDefine.StreamInfo next = it.next();
            if (TextUtils.equals(str, next.description)) {
                onRemoteRender(janusConnectionByFeedId, next.mid);
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        onRemoteRender(janusConnectionByFeedId, this.mWebSocketChannel.getVideoMidByFeed(valueOf));
    }

    private void processTargetView(RTCVideoView rTCVideoView, String str, BigInteger bigInteger) {
        CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList;
        ConcurrentHashMap<BigInteger, Map<String, RTCVideoView>> concurrentHashMap = this.mRemoteRenderViewsMap;
        if (concurrentHashMap == null) {
            return;
        }
        Map<String, RTCVideoView> map = concurrentHashMap.get(bigInteger);
        if (map == null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str, rTCVideoView);
            this.mRemoteRenderViewsMap.put(bigInteger, hashMap);
            return;
        }
        JanusConnection janusConnectionByFeedId = getJanusConnectionByFeedId(bigInteger);
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null && (copyOnWriteArrayList = webSocketChannel.getFeedMidMap().get(bigInteger)) != null) {
            Iterator<Map.Entry<String, RTCVideoView>> it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, RTCVideoView> next = it.next();
                String key = next.getKey();
                RTCVideoView value = next.getValue();
                if (TextUtils.equals(next.getKey(), str)) {
                    String str2 = "";
                    Iterator<CommonDefine.StreamInfo> it2 = copyOnWriteArrayList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        CommonDefine.StreamInfo next2 = it2.next();
                        if (TextUtils.equals(key, next2.description)) {
                            str2 = next2.mid;
                            break;
                        }
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = this.mWebSocketChannel.getVideoMidByFeed(bigInteger);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        detachRenderView(getVideoTrack(janusConnectionByFeedId, str2), value);
                        value.release();
                        break;
                    }
                }
            }
        }
        map.remove(str);
        map.put(str, rTCVideoView);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public IRtcMediaRecorder getBRTCMediaRecorder() {
        BRTCMediaRecorder bRTCMediaRecorder = this.mRtcRecorder;
        if (bRTCMediaRecorder == null || bRTCMediaRecorder.isReleased()) {
            this.mRtcRecorder = new BRTCMediaRecorder(this, this.rootEglBase.getEglBaseContext());
        }
        return this.mRtcRecorder;
    }

    public void registerLocalSink(VideoSink videoSink) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.registerLocalSink(videoSink);
        }
    }

    public void unregisterLocalSink(VideoSink videoSink) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.unregisterLocalSink(videoSink);
        }
    }

    public void registerAudioRecord(RtcAudioRecordListener rtcAudioRecordListener) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.registerAudioRecord(rtcAudioRecordListener);
        }
    }

    public void unregisterAudioRecord(RtcAudioRecordListener rtcAudioRecordListener) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.unregisterAudioRecord(rtcAudioRecordListener);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteDisplayGroup(RTCVideoView[] rTCVideoViewArr) {
        this.mRemoteRenderList = rTCVideoViewArr;
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.10
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < BaiduRtcRoomImp.this.mRemoteRenderList.length; i++) {
                    if (BaiduRtcRoomImp.this.mRemoteRenderList[i] != null) {
                        try {
                            BaiduRtcRoomImp.this.mRemoteRenderList[i].init(BaiduRtcRoomImp.this.rootEglBase.getEglBaseContext(), null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (BaiduRtcRoomImp.this.enableHardwareScaler) {
                            BaiduRtcRoomImp.this.mRemoteRenderList[i].setEnableHardwareScaler(true);
                        }
                    }
                }
            }
        });
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void removeRemoteDisplay(long j) {
        BigInteger valueOf = BigInteger.valueOf(j);
        JanusConnection janusConnectionByFeedId = getJanusConnectionByFeedId(valueOf);
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        removeDynamicRenderViewInternal(valueOf, janusConnectionByFeedId, webSocketChannel != null ? webSocketChannel.getFeedMidMap().get(valueOf) : null);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void removeRemoteDisplay(long j, String str) {
        CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList;
        BigInteger valueOf = BigInteger.valueOf(j);
        if (this.mRemoteRenderViewsMap.get(valueOf) == null) {
            return;
        }
        JanusConnection janusConnectionByFeedId = getJanusConnectionByFeedId(valueOf);
        CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel != null && (copyOnWriteArrayList = webSocketChannel.getFeedMidMap().get(valueOf)) != null) {
            Iterator<CommonDefine.StreamInfo> it = copyOnWriteArrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CommonDefine.StreamInfo next = it.next();
                if (TextUtils.equals(str, next.description)) {
                    copyOnWriteArrayList2.add(next);
                    break;
                }
            }
        }
        removeDynamicRenderViewInternal(valueOf, janusConnectionByFeedId, copyOnWriteArrayList2);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void removeLocalDisplay() {
        if (this.mLocalRender == null) {
            return;
        }
        detachRenderView(this.peerConnectionClient != null ? this.peerConnectionClient.getLocalVideoTrack() : null, this.mLocalRender);
        this.mLocalRender.release();
        this.mLocalRender = null;
    }

    private JanusConnection getJanusConnectionByFeedId(BigInteger bigInteger) {
        WebSocketChannel webSocketChannel;
        JanusHandle handleByFeed;
        if (bigInteger == null || this.peerConnectionClient == null || (webSocketChannel = this.mWebSocketChannel) == null || (handleByFeed = webSocketChannel.getHandleByFeed(bigInteger)) == null) {
            return null;
        }
        return this.peerConnectionClient.getJanusConnectionByHandleID(handleByFeed.handleId);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public BaiduRtcRoom.UserList queryUserListOfRoom() {
        if (this.mWebSocketChannel == null || isBigRoomMode()) {
            return null;
        }
        return this.mWebSocketChannel.UserListOfRoom();
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public BaiduRtcRoom.RtcRoomUserInfo[] getUserListOfRoom() {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel == null) {
            return null;
        }
        return webSocketChannel.UserInfoList();
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public HUDStatistics queryEngineStatisticsInfo(long j) {
        JanusHandle handleByFeed;
        ConcurrentHashMap<BigInteger, HUDStatistics> concurrentHashMap = this.mHUDStatisticsMap;
        if (concurrentHashMap == null) {
            return null;
        }
        if (j == this.mUserId) {
            return concurrentHashMap.get(this.mPublisherHandle);
        }
        if (this.mParamSettings.EnableMultistream) {
            if (this.mSubscriberHandle != null) {
                return this.mHUDStatisticsMap.get(BigInteger.valueOf(j));
            }
        } else {
            WebSocketChannel webSocketChannel = this.mWebSocketChannel;
            if (webSocketChannel != null && (handleByFeed = webSocketChannel.getHandleByFeed(BigInteger.valueOf(j))) != null && handleByFeed.handleId != null) {
                return this.mHUDStatisticsMap.get(handleByFeed.handleId);
            }
        }
        return null;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public BaiduRtcRoom.RtcRoomVideoDimension getRemoteVideoDimension(long j) {
        RTCVideoExternalRenderImp rTCVideoExternalRenderImp = (RTCVideoExternalRenderImp) this.mUserIdVideoRendererMap.get(Long.valueOf(j));
        BaiduRtcRoom.RtcRoomVideoDimension rtcRoomVideoDimension = new BaiduRtcRoom.RtcRoomVideoDimension();
        if (rTCVideoExternalRenderImp != null) {
            rtcRoomVideoDimension.videoWidth = rTCVideoExternalRenderImp.getVideoWidth();
            rtcRoomVideoDimension.videoHeight = rTCVideoExternalRenderImp.getVideoHeight();
            rtcRoomVideoDimension.videoRotation = rTCVideoExternalRenderImp.getVideoRotation();
        }
        return rtcRoomVideoDimension;
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onWebSocketOpened() {
        this.mWssOpenedTime = System.currentTimeMillis();
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onPublisherJoined(final BigInteger bigInteger, long j) {
        RtcLogCapturer.setParams(new RtcLogCapturer.LogParams(this.mAppId, j, this.mRoomName));
        this.mIsLoginSuccess = false;
        if (this.mIsEnablePushQualityMonitor) {
            reportSLILoginEvent("ENTER_BEGIN", this.mWebSocketChannel.getRoomId());
        }
        this.mPublisherHandle = bigInteger;
        RtcLogCapturer.setPublisherHandleId(this.mPublisherHandle.longValue());
        ErrorInfoReport.getInstance().setPublishHandleId(this.mPublisherHandle.longValue());
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onRoomEventUpdate(100, j, "ok");
            RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_LOGIN_OK, "onPublisherJoined", Long.valueOf(j), bigInteger);
        }
        if (this.mParamSettings.AutoPublish) {
            offerPeerConnection(bigInteger);
        }
        if (this.mbOnStatistics && this.peerConnectionClient != null) {
            this.peerConnectionClient.enableStatsEvents(this.mbOnStatistics, 5000, this.mPublisherHandle, PeerConnectionClient.StatsEventsType.GET_BWE_EVENT);
        }
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.11
            @Override // java.lang.Runnable
            public void run() {
                if ((BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor) && BaiduRtcRoomImp.this.peerConnectionClient != null) {
                    if (BaiduRtcRoomImp.this.mHUDStatisticsMap.get(bigInteger) != null) {
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
                        BaiduRtcRoomImp.this.mHUDStatisticsMap.remove(bigInteger);
                    }
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor, 2000, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor, 5000, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor, 2000, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
                    HUDStatistics hUDStatistics = new HUDStatistics();
                    hUDStatistics.setEnableMultistream(BaiduRtcRoomImp.this.mParamSettings.EnableMultistream);
                    hUDStatistics.setHandlerId(bigInteger);
                    hUDStatistics.setPublisher(true);
                    BaiduRtcRoomImp.this.mHUDStatisticsMap.put(bigInteger, hUDStatistics);
                    if (BaiduRtcRoomImp.this.rtcLogReport != null && !BaiduRtcRoomImp.this.mbStartReportDI) {
                        BaiduRtcRoomImp.this.mbStartReportDI = true;
                        BaiduRtcRoomImp.this.mHandler.post(BaiduRtcRoomImp.this.reportDeviceInfoRun);
                    }
                    BaiduRtcRoomImp baiduRtcRoomImp = BaiduRtcRoomImp.this;
                    baiduRtcRoomImp.reportRoomEventInfo("RTCROOM_LOGIN_OK_" + BaiduRtcRoomImp.this.mUserId);
                }
                StreamStats streamStats = (StreamStats) BaiduRtcRoomImp.this.mRemoteStreamStats.get(BigInteger.valueOf(BaiduRtcRoomImp.this.mUserId));
                if (streamStats != null) {
                    if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, streamStats.handleId, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
                    }
                    streamStats.hasVideo = Boolean.valueOf(BaiduRtcRoomImp.this.mParamSettings.HasVideo);
                    streamStats.hasAudio = Boolean.valueOf(BaiduRtcRoomImp.this.mParamSettings.HasAudio);
                    streamStats.handleId = bigInteger;
                    streamStats.nickName = BaiduRtcRoomImp.this.mDisplayName;
                } else {
                    BaiduRtcRoomImp.this.mRemoteStreamStats.put(BigInteger.valueOf(BaiduRtcRoomImp.this.mUserId), new StreamStats(bigInteger, BaiduRtcRoomImp.this.mUserId, Boolean.valueOf(BaiduRtcRoomImp.this.mParamSettings.HasVideo), Boolean.valueOf(BaiduRtcRoomImp.this.mParamSettings.HasAudio), BaiduRtcRoomImp.this.mDisplayName));
                }
                if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(true, 200, bigInteger, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
                }
            }
        });
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onPublisherRemoteJsep(BigInteger bigInteger, JSONObject jSONObject) {
        SessionDescription.Type fromCanonicalForm = SessionDescription.Type.fromCanonicalForm(jSONObject.optString("type"));
        String optString = jSONObject.optString("sdp");
        if (this.mParamSettings.EnableHighProfile && this.isEnableHighProfileConfirm && optString.contains("profile-level-id=42e01f")) {
            optString = optString.replace("profile-level-id=42e01f", "profile-level-id=640c1f");
            Logging.m5305d("BaiduRtcRoomImp", "enable high profile, remote jsep changed: " + optString);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_START, "onPublisherRemoteJsep");
        this.peerConnectionClient.setRemoteDescription(bigInteger, new SessionDescription(fromCanonicalForm, optString));
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onCreatedHandle(BigInteger bigInteger, long j) {
        this.mIsLoginSuccess = false;
        this.mRoomId = j;
        Logging.m5305d("BaiduRtcRoomImp", "onCreatedHandle: " + j);
        if (this.mIsEnablePushQualityMonitor) {
            reportSLILoginEvent("ENTER_BEGIN", j);
        }
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void subscriberHandleRemoteJsep(final BigInteger bigInteger, JSONObject jSONObject) {
        char c;
        RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBER_CREATER_OFFER_START, "BaiduRtcRoomImp-subscriberHandleRemoteJsep");
        SessionDescription.Type fromCanonicalForm = SessionDescription.Type.fromCanonicalForm(jSONObject.optString("type"));
        String optString = jSONObject.optString("sdp");
        this.peerConnectionClient.subscriberHandleRemoteJsep(bigInteger, new SessionDescription(fromCanonicalForm, optString));
        RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBER_CREATER_OFFER_SUCCESS, "BaiduRtcRoomImp-subscriberHandleRemoteJsep");
        this.mSubscribeTime = System.currentTimeMillis();
        Logging.m5305d("BaiduRtcRoomImp", "start subscribing@" + (this.mSubscribeTime - this.mWssOpenedTime) + "ms from wssed");
        SdpPrefer.SdpStruct parseSdpStruct = SdpPrefer.parseSdpStruct(optString);
        long j = -1;
        if (parseSdpStruct.midSsrcMap.size() > 0) {
            c = 0;
            for (String str : parseSdpStruct.midSsrcMap.keySet()) {
                SdpPrefer.SdpStruct.SsrcInfo ssrcInfo = parseSdpStruct.midSsrcMap.get(str);
                if (ssrcInfo == null || ssrcInfo.type != 0) {
                    c = 2;
                } else {
                    BigInteger feedByMid = this.mWebSocketChannel.getFeedByMid(str);
                    if (feedByMid != null) {
                        j = feedByMid.longValue();
                        BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo = this.userInfoMap.get(Long.valueOf(feedByMid.longValue()));
                        if (rtcRoomUserInfo != null) {
                            rtcRoomUserInfo.ssrc = ssrcInfo.ssrc;
                        }
                    } else {
                        c = 1;
                    }
                }
            }
        } else {
            c = 3;
        }
        if (c != 0) {
            RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBED_USER_SET_POSITION, "subscriberHandleRemoteJsep", Long.valueOf(j));
        }
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.12
            @Override // java.lang.Runnable
            public void run() {
                if (!BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor || bigInteger.equals(BaiduRtcRoomImp.this.mPublisherHandle) || BaiduRtcRoomImp.this.peerConnectionClient == null) {
                    return;
                }
                if (BaiduRtcRoomImp.this.mHUDStatisticsMap.get(bigInteger) != null) {
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                    BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
                    BaiduRtcRoomImp.this.mHUDStatisticsMap.remove(bigInteger);
                }
                BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor, 2000, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor, 5000, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor, 2000, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
                if (BaiduRtcRoomImp.this.mParamSettings.EnableMultistream) {
                    return;
                }
                HUDStatistics hUDStatistics = new HUDStatistics();
                hUDStatistics.setEnableMultistream(BaiduRtcRoomImp.this.mParamSettings.EnableMultistream);
                BaiduRtcRoomImp.this.mHUDStatisticsMap.put(bigInteger, hUDStatistics);
                hUDStatistics.setHandlerId(bigInteger);
                hUDStatistics.setPublisher(false);
                hUDStatistics.setRequestSubscribeTime(System.currentTimeMillis());
            }
        });
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onSubscribeUser(BigInteger bigInteger, BigInteger bigInteger2) {
        this.mSubscriberHandle = bigInteger;
        if (bigInteger != null) {
            RtcLogCapturer.setSubscriberHandleId(bigInteger.longValue());
        }
        if (!this.mHUDStatisticsMap.containsKey(bigInteger2)) {
            HUDStatistics hUDStatistics = new HUDStatistics();
            hUDStatistics.setEnableMultistream(this.mParamSettings.EnableMultistream);
            hUDStatistics.setRequestSubscribeTime(System.currentTimeMillis());
            hUDStatistics.setPublisher(false);
            hUDStatistics.setHandlerId(bigInteger2);
            this.mHUDStatisticsMap.put(bigInteger2, hUDStatistics);
        } else if (this.mHUDStatisticsMap.get(bigInteger2) != null) {
            this.mHUDStatisticsMap.get(bigInteger2).setRequestSubscribeTime(System.currentTimeMillis());
        }
        if (this.mHUDStatisticsMap.containsKey(this.mSubscriberHandle)) {
            return;
        }
        HUDStatistics hUDStatistics2 = new HUDStatistics();
        hUDStatistics2.setEnableMultistream(this.mParamSettings.EnableMultistream);
        hUDStatistics2.setPublisher(false);
        this.mHUDStatisticsMap.put(this.mSubscriberHandle, hUDStatistics2);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onLeaving(BigInteger bigInteger, final BigInteger bigInteger2) {
        Logging.m5305d("BaiduRtcRoomImp", "==================onLeaving=======================");
        if (bigInteger2 == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.13
            @Override // java.lang.Runnable
            public void run() {
                JanusHandle handleByFeed;
                BaiduRtcRoomImp.this.mRemoteStreamStats.remove(bigInteger2);
                if (BaiduRtcRoomImp.this.mParamSettings.EnableMultistream || BaiduRtcRoomImp.this.mWebSocketChannel == null || (handleByFeed = BaiduRtcRoomImp.this.mWebSocketChannel.getHandleByFeed(bigInteger2)) == null) {
                    return;
                }
                BigInteger bigInteger3 = handleByFeed.handleId;
                if (BaiduRtcRoomImp.this.mHUDStatisticsMap.get(bigInteger3) != null) {
                    BaiduRtcRoomImp.this.mHUDStatisticsMap.remove(bigInteger3);
                    if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger3, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger3, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger3, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
                    }
                }
            }
        });
        String videoMidByFeed = this.mParamSettings.EnableMultistream ? this.mWebSocketChannel.getVideoMidByFeed(bigInteger2) : "";
        JanusConnection janusConnectionByHandleID = this.peerConnectionClient != null ? this.peerConnectionClient.getJanusConnectionByHandleID(bigInteger) : null;
        if (this.peerConnectionClient != null && janusConnectionByHandleID != null && janusConnectionByHandleID.audioTrack != null) {
            this.mBaiduRtcRoomDelegate.onRoomEventUpdate(109, bigInteger2.longValue(), "remote audio gone");
            janusConnectionByHandleID.audioTrack = null;
            RtcLogCapturer.reportEvent(RtcLogEvent.RTC_CALLBACK_USER_AUDIO_REMOVED, bigInteger2.longValue(), "onLeavingMain", bigInteger2, videoMidByFeed, this.peerConnectionClient.getSsrcByMid(bigInteger, videoMidByFeed));
        }
        this.mFeedToViewMap.remove(bigInteger2);
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onRoomEventUpdate(105, bigInteger2.longValue(), "leaving");
        }
        if (this.mParamSettings.VideoRenderMode == RtcParameterSettings.RtcVideoRenderMode.RTC_VIDEO_RENDER_MODE_EXTERNAL) {
            RTCVideoExternalRender rTCVideoExternalRender = this.mUserIdVideoRendererMap.get(Long.valueOf(bigInteger2.longValue()));
            if (rTCVideoExternalRender != null && janusConnectionByHandleID != null) {
                Logging.m5305d("BaiduRtcRoomImp", "remove external video sink of user " + bigInteger2);
                detachExternalRenderView(getVideoTrack(janusConnectionByHandleID, videoMidByFeed), rTCVideoExternalRender);
                userRenderMove2PendingMap(bigInteger2.longValue());
            } else {
                Logging.m5305d("BaiduRtcRoomImp", "No external render has found!");
            }
        } else if (this.mRemoteRenderViewsMap.containsKey(bigInteger2)) {
            removeDynamicRenderViewInternal(bigInteger2, janusConnectionByHandleID, this.mWebSocketChannel.getFeedMidMap().get(bigInteger2));
        } else if (janusConnectionByHandleID != null) {
            int i = -1;
            if (this.mParamSettings.EnableMultistream) {
                if (janusConnectionByHandleID.videoviewTags.containsKey(videoMidByFeed)) {
                    i = janusConnectionByHandleID.videoviewTags.get(videoMidByFeed).intValue();
                }
            } else {
                i = janusConnectionByHandleID.videoview_tag;
            }
            if (i >= 0) {
                boolean[] zArr = this.mHasVideoView;
                if (i < zArr.length) {
                    zArr[i] = false;
                    this.mRemoteVideoCnt--;
                    RTCVideoView rTCVideoView = this.mRemoteRenderList[i];
                    if (rTCVideoView != null) {
                        detachRenderView(getVideoTrack(janusConnectionByHandleID, videoMidByFeed), rTCVideoView);
                    }
                    if (this.mParamSettings.EnableMultistream) {
                        janusConnectionByHandleID.videoviewTags.remove(videoMidByFeed);
                    }
                }
            }
        }
        if (this.mBaiduRtcRoomDelegate != null) {
            if (this.mLastGoneId == bigInteger2.longValue()) {
                return;
            }
            this.mBaiduRtcRoomDelegate.onRoomEventUpdate(107, bigInteger2.longValue(), "gone");
            this.mLastGoneId = bigInteger2.longValue();
            return;
        }
        reportRoomEventInfo("REMOTE_LEAVING_" + bigInteger2 + "_" + bigInteger);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onRemovedStreams(BigInteger bigInteger, BigInteger bigInteger2, ArrayList<CommonDefine.StreamInfo> arrayList) {
        if (bigInteger2 == null || arrayList == null || !this.mParamSettings.EnableMultistream) {
            return;
        }
        if (this.mOperatingFeedStreams == null) {
            this.mOperatingFeedStreams = new HashMap();
        }
        this.mOperatingFeedStreams.put(bigInteger2, arrayList);
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onRoomEventUpdate(124, bigInteger2.longValue(), "leaving streams");
        }
        reportRoomEventInfo("REMOTE_REMOVED_STREAMS_" + bigInteger2 + "_" + bigInteger + "_" + arrayList.size());
    }

    private VideoTrack getVideoTrack(JanusConnection janusConnection, String str) {
        if (janusConnection != null) {
            if (this.mParamSettings.EnableMultistream) {
                if (!TextUtils.isEmpty(str)) {
                    return janusConnection.videoTracks.get(str);
                }
            } else {
                return janusConnection.videoTrack;
            }
        }
        return null;
    }

    private void detachRenderView(VideoTrack videoTrack, RTCVideoView rTCVideoView) {
        if (videoTrack == null || rTCVideoView == null) {
            return;
        }
        rTCVideoView.deatach();
        rTCVideoView.clearImage();
        videoTrack.removeSink(rTCVideoView);
    }

    private synchronized void removeDynamicRenderViewInternal(BigInteger bigInteger, JanusConnection janusConnection, CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList) {
        if (this.mRemoteRenderViewsMap != null && !this.mRemoteRenderViewsMap.isEmpty() && this.mRemoteRenderViewsMap.containsKey(bigInteger) && copyOnWriteArrayList != null) {
            Map<String, RTCVideoView> map = this.mRemoteRenderViewsMap.get(bigInteger);
            if (map != null) {
                Iterator<CommonDefine.StreamInfo> it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    CommonDefine.StreamInfo next = it.next();
                    Iterator<Map.Entry<String, RTCVideoView>> it2 = map.entrySet().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Map.Entry<String, RTCVideoView> next2 = it2.next();
                            String str = TextUtils.equals(next2.getKey(), next.description) ? next.mid : "";
                            if (TextUtils.isEmpty(str) && this.mWebSocketChannel != null) {
                                str = this.mWebSocketChannel.getVideoMidByFeed(bigInteger);
                            }
                            if (!TextUtils.isEmpty(str)) {
                                RTCVideoView value = next2.getValue();
                                detachRenderView(getVideoTrack(janusConnection, str), value);
                                value.release();
                                it2.remove();
                                break;
                            }
                        }
                    }
                }
                if (map.isEmpty()) {
                    this.mRemoteRenderViewsMap.remove(bigInteger);
                }
            }
        }
    }

    private void detachExternalRenderView(VideoTrack videoTrack, RTCVideoExternalRender rTCVideoExternalRender) {
        if (videoTrack == null || rTCVideoExternalRender == null) {
            return;
        }
        rTCVideoExternalRender.deatach();
        rTCVideoExternalRender.clearImage();
        videoTrack.removeSink(rTCVideoExternalRender);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onRemoteGone(BigInteger bigInteger) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.closePeer(bigInteger);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onLocalDescription(SessionDescription sessionDescription, BigInteger bigInteger, boolean z) {
        Logging.m5305d("BaiduRtcRoomImp", sessionDescription.type.toString());
        if (this.mWebSocketChannel == null) {
            return;
        }
        this.isEnableHighProfileConfirm = z;
        if (this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.publisherCreateOfferMultistream(bigInteger, sessionDescription, this.peerConnectionClient.getPublisherStreamInfo(this.mPublisherHandle));
        } else {
            this.mWebSocketChannel.publisherCreateOffer(bigInteger, sessionDescription);
        }
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.14
            @Override // java.lang.Runnable
            public void run() {
                if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                    if (BaiduRtcRoomImp.this.peerConnectionParameters.videoMaxkbps > 0 && BaiduRtcRoomImp.this.peerConnectionParameters.videoMinkbps > 0) {
                        Logging.m5305d("BaiduRtcRoomImp", "Set video maximum bitrate: " + BaiduRtcRoomImp.this.peerConnectionParameters.videoMaxkbps + " - " + BaiduRtcRoomImp.this.peerConnectionParameters.videoMaxkbps);
                        BaiduRtcRoomImp.this.peerConnectionClient.setVideoBitrate(Integer.valueOf(BaiduRtcRoomImp.this.peerConnectionParameters.videoMinkbps), Integer.valueOf(BaiduRtcRoomImp.this.peerConnectionParameters.videoMaxkbps));
                    }
                    if (BaiduRtcRoomImp.this.peerConnectionParameters.screenShareParams != null) {
                        int i = BaiduRtcRoomImp.this.peerConnectionParameters.screenShareParams.mVideoCaptureParams.videoMaxkbps;
                        int i2 = BaiduRtcRoomImp.this.peerConnectionParameters.screenShareParams.mVideoCaptureParams.videoMinkbps;
                        if (i <= 0 || i2 <= 0) {
                            return;
                        }
                        Logging.m5305d("BaiduRtcRoomImp", "Set screen maximum bitrate: " + i + " - " + i2);
                        BaiduRtcRoomImp.this.peerConnectionClient.setScreenBitrate(Integer.valueOf(i2), Integer.valueOf(i));
                    }
                }
            }
        });
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteDescription(SessionDescription sessionDescription, BigInteger bigInteger) {
        Logging.m5305d("BaiduRtcRoomImp", sessionDescription.type.toString());
        this.mWebSocketChannel.subscriberCreateAnswer(bigInteger, sessionDescription);
        this.mSetRemoteSdpTime = System.currentTimeMillis();
        Logging.m5305d("BaiduRtcRoomImp", "remote sdp done@" + (this.mSetRemoteSdpTime - this.mSubscribeTime) + "ms from subscribe");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onSubscribSetLocalDescriptionSuccess(BigInteger bigInteger) {
        this.mSetLocalSdpTime = System.currentTimeMillis();
        Logging.m5305d("BaiduRtcRoomImp", "local sdp done@" + (this.mSetLocalSdpTime - this.mSetRemoteSdpTime) + "ms from setRemoteSdp");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceCandidate(IceCandidate iceCandidate, BigInteger bigInteger) {
        Logging.m5305d("BaiduRtcRoomImp", "=========onIceCandidate========");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceConnected() {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onPeerConnectStateUpdate(2004);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceDisconnected() {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onPeerConnectStateUpdate(2005);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onIceFailed() {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.MEDIA_CHANNEL_CONNECTION_LOST);
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_FAILED);
        }
        if (this.mIsEnablePushQualityMonitor && !this.mIsLoginSuccess) {
            reportSLILoginEvent("ENTER_FAILED");
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onPeerConnectStateUpdate(2003);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionClosed() {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onPeerConnectStateUpdate(2008);
        }
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionStatsReady(final StatsReport[] statsReportArr, final BigInteger bigInteger, final PeerConnectionClient.StatsEventsType statsEventsType) {
        if (bigInteger.equals(this.mPublisherHandle) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_BWE_EVENT && this.mbOnStatistics) {
            updateBweStatistics(statsReportArr);
        }
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.15
            @Override // java.lang.Runnable
            public void run() {
                if (statsEventsType == PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT) {
                    BaiduRtcRoomImp.this.parseAudioLeves(statsReportArr, bigInteger);
                }
                if (BaiduRtcRoomImp.this.mParamSettings.EnableMultistream) {
                    if (bigInteger.equals(BaiduRtcRoomImp.this.mPublisherHandle)) {
                        HUDStatistics hUDStatistics = (HUDStatistics) BaiduRtcRoomImp.this.mHUDStatisticsMap.get(bigInteger);
                        if (hUDStatistics != null) {
                            hUDStatistics.updateEncoderStatistics(statsReportArr);
                        }
                    } else {
                        BaiduRtcRoomImp.this.updateEncoderStatistics(statsReportArr);
                    }
                } else {
                    HUDStatistics hUDStatistics2 = (HUDStatistics) BaiduRtcRoomImp.this.mHUDStatisticsMap.get(bigInteger);
                    if (hUDStatistics2 != null) {
                        hUDStatistics2.updateEncoderStatistics(statsReportArr);
                    }
                }
                if ((BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT && bigInteger.equals(BaiduRtcRoomImp.this.mPublisherHandle) && BaiduRtcRoomImp.this.rtcLogReport != null) {
                    BaiduRtcRoomImp.this.reportCommunicationQualityInfo();
                }
                if ((BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_SLI_EVENT && bigInteger.equals(BaiduRtcRoomImp.this.mPublisherHandle) && BaiduRtcRoomImp.this.rtcLogReport != null) {
                    BaiduRtcRoomImp.this.reportSLIStuckInfo();
                }
                if ((BaiduRtcRoomImp.this.mIsEnablePushQualityMonitor || BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT && bigInteger.equals(BaiduRtcRoomImp.this.mPublisherHandle) && BaiduRtcRoomImp.this.rtcLogReport != null) {
                    BaiduRtcRoomImp.this.reportAntiWeakInfo();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateEncoderStatistics(com.webrtc.StatsReport[] r11) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.rtc.internal.BaiduRtcRoomImp.updateEncoderStatistics(com.webrtc.StatsReport[]):void");
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onPeerConnectionError(String str) {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.PEERCONNECTION_CREATE_ERROR);
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_FAILED);
        }
        if (this.mIsEnablePushQualityMonitor && !this.mIsLoginSuccess) {
            reportSLILoginEvent("ENTER_FAILED");
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onErrorInfoUpdate(10000);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_PEER_CONNECTION_ERROR, "onPeerConnectionError", -1, RtcLogCapturer.getErrorInfo(str));
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteStreamStats(final Boolean bool, final Boolean bool2, final BigInteger bigInteger) {
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.16
            @Override // java.lang.Runnable
            public void run() {
                BigInteger feedByHandle;
                if (BaiduRtcRoomImp.this.mWebSocketChannel == null || (feedByHandle = BaiduRtcRoomImp.this.mWebSocketChannel.getFeedByHandle(bigInteger)) == null) {
                    return;
                }
                String nickNameByFeed = BaiduRtcRoomImp.this.mWebSocketChannel.getNickNameByFeed(feedByHandle);
                StreamStats streamStats = (StreamStats) BaiduRtcRoomImp.this.mRemoteStreamStats.get(feedByHandle);
                if (streamStats == null) {
                    streamStats = new StreamStats(bigInteger, feedByHandle.longValue(), bool, bool2, nickNameByFeed);
                    BaiduRtcRoomImp.this.mRemoteStreamStats.put(feedByHandle, streamStats);
                } else {
                    streamStats.hasVideo = bool;
                    streamStats.hasAudio = bool2;
                    streamStats.handleId = bigInteger;
                    streamStats.nickName = nickNameByFeed;
                }
                if (!bool2.booleanValue() || BaiduRtcRoomImp.this.peerConnectionClient == null) {
                    return;
                }
                BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(true, 200, streamStats.handleId, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
            }
        });
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteStreamStats(final String str, final String str2) {
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.17
            @Override // java.lang.Runnable
            public void run() {
                BigInteger feedByMid;
                if (BaiduRtcRoomImp.this.mWebSocketChannel == null || (feedByMid = BaiduRtcRoomImp.this.mWebSocketChannel.getFeedByMid(str2)) == null) {
                    return;
                }
                String nickNameByFeed = BaiduRtcRoomImp.this.mWebSocketChannel.getNickNameByFeed(feedByMid);
                StreamStats streamStats = (StreamStats) BaiduRtcRoomImp.this.mRemoteStreamStats.get(feedByMid);
                if (streamStats == null) {
                    streamStats = new StreamStats(BaiduRtcRoomImp.this.mSubscriberHandle, feedByMid.longValue(), false, false, nickNameByFeed);
                    if (str.equals("audio")) {
                        streamStats.hasAudio = true;
                    }
                    if (str.equals("video")) {
                        streamStats.hasVideo = true;
                    }
                    BaiduRtcRoomImp.this.mRemoteStreamStats.put(feedByMid, streamStats);
                } else {
                    if (str.equals("audio")) {
                        streamStats.hasAudio = true;
                    }
                    if (str.equals("video")) {
                        streamStats.hasVideo = true;
                    }
                    streamStats.handleId = BaiduRtcRoomImp.this.mSubscriberHandle;
                    streamStats.nickName = nickNameByFeed;
                }
                if ("audio".equals(str)) {
                    if (BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate != null) {
                        BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate.onRoomEventUpdate(108, feedByMid.longValue(), "");
                    }
                    RtcLogCapturer.reportEvent(RtcLogEvent.RTC_CALLBACK_USER_AUDIO_ARRIVED, feedByMid.longValue(), "onRemoteStreamStats", feedByMid, str2, BaiduRtcRoomImp.this.peerConnectionClient.getSsrcByMid(BaiduRtcRoomImp.this.mSubscriberHandle, str2));
                    if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                        BaiduRtcRoomImp.this.peerConnectionClient.enableStatsEvents(true, 200, streamStats.handleId, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
                    }
                }
            }
        });
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteRender(JanusConnection janusConnection, String str) {
        BigInteger feedByHandle;
        VideoTrack videoTrack;
        int i;
        RTCVideoView[] rTCVideoViewArr;
        Logging.m5305d("BaiduRtcRoomImp", "=========onRemoteRender========" + str);
        String str2 = "video";
        if (this.mParamSettings.EnableMultistream) {
            videoTrack = janusConnection.videoTracks.get(str);
            feedByHandle = null;
            for (Map.Entry<BigInteger, CopyOnWriteArrayList<CommonDefine.StreamInfo>> entry : this.mWebSocketChannel.getFeedMidMap().entrySet()) {
                Iterator<CommonDefine.StreamInfo> it = entry.getValue().iterator();
                while (true) {
                    if (it.hasNext()) {
                        CommonDefine.StreamInfo next = it.next();
                        if (next.mid.equals(str)) {
                            feedByHandle = entry.getKey();
                            str2 = next.description;
                            continue;
                            break;
                        }
                    }
                }
                if (feedByHandle != null) {
                    break;
                }
            }
        } else {
            feedByHandle = this.mWebSocketChannel.getFeedByHandle(janusConnection.handleId);
            videoTrack = janusConnection.videoTrack;
        }
        if (feedByHandle == null || feedByHandle.longValue() == 0) {
            Logging.m5304e("BaiduRtcRoomImp", "onRemoteRender Userid is 0.");
            return;
        }
        int i2 = 0;
        RtcLogCapturer.reportEvent(RtcLogEvent.RTC_CALLBACK_USER_VIDEO_ARRIVED, feedByHandle.longValue(), "onRemoteRenderMain", feedByHandle, str, this.peerConnectionClient.getSsrcByMid(janusConnection.handleId, str));
        if (this.mParamSettings.VideoRenderMode == RtcParameterSettings.RtcVideoRenderMode.RTC_VIDEO_RENDER_MODE_EXTERNAL) {
            RTCVideoExternalRender externalRender = getExternalRender(feedByHandle.longValue());
            Logging.m5305d("BaiduRtcRoomImp", "enable external video render mode with videoRenderer -> " + externalRender);
            if (videoTrack != null && externalRender != null) {
                initVideoSink(externalRender, janusConnection, feedByHandle, str);
                videoTrack.addSink(externalRender);
                return;
            }
            Logging.m5305d("BaiduRtcRoomImp", "Not found external render for user " + feedByHandle);
            return;
        }
        Logging.m5305d("BaiduRtcRoomImp", "internal video render mode.");
        if (this.mRemoteRenderViewsMap.containsKey(feedByHandle)) {
            Map<String, RTCVideoView> map = this.mRemoteRenderViewsMap.get(feedByHandle);
            if (videoTrack == null || map == null) {
                return;
            }
            RTCVideoView rTCVideoView = map.get(str2);
            if (rTCVideoView == null && !TextUtils.equals(str2, "video")) {
                rTCVideoView = map.get("video");
            }
            if (rTCVideoView == null && !this.mParamSettings.EnableMultistream && map.size() == 1) {
                for (Map.Entry<String, RTCVideoView> entry2 : map.entrySet()) {
                    rTCVideoView = entry2.getValue();
                }
            }
            if (rTCVideoView != null) {
                initVideoSink(rTCVideoView, janusConnection, feedByHandle, str);
                videoTrack.addSink(rTCVideoView);
                Logging.m5305d("BaiduRtcRoomImp", feedByHandle + " use dynamic render view. ");
                return;
            }
            Logging.m5305d("BaiduRtcRoomImp", feedByHandle + " render view not found. ");
            return;
        }
        int i3 = -1;
        try {
            if (this.mParamSettings.EnableMultistream) {
                BigInteger feedByMid = this.mWebSocketChannel.getFeedByMid(str);
                i = (feedByMid == null || this.mFeedToViewMap.size() <= 0) ? -1 : this.mFeedToViewMap.get(feedByMid).intValue();
            } else {
                i = this.mFeedToViewMap.size() > 0 ? this.mFeedToViewMap.get(this.mWebSocketChannel.getFeedByHandle(janusConnection.handleId)).intValue() : -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }
        if (i >= 0 && i < 30 && (rTCVideoViewArr = this.mRemoteRenderList) != null && i < rTCVideoViewArr.length) {
            RTCVideoView rTCVideoView2 = rTCVideoViewArr[i];
            if (videoTrack != null && rTCVideoView2 != null) {
                initVideoSink(rTCVideoView2, janusConnection, feedByHandle, str);
                videoTrack.addSink(rTCVideoView2);
            }
            if (this.mParamSettings.EnableMultistream) {
                janusConnection.videoviewTags.put(str, Integer.valueOf(i));
            } else {
                janusConnection.videoview_tag = i;
            }
            Logging.m5305d("BaiduRtcRoomImp", "use mark render view " + rTCVideoView2);
        } else if (this.mRemoteRenderList != null) {
            while (true) {
                boolean[] zArr = this.mHasVideoView;
                if (i2 >= zArr.length) {
                    break;
                } else if (!zArr[i2]) {
                    zArr[i2] = true;
                    i3 = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i3 >= 0) {
                RTCVideoView rTCVideoView3 = this.mRemoteRenderList[i3];
                if (rTCVideoView3 != null) {
                    initVideoSink(rTCVideoView3, janusConnection, feedByHandle, str);
                    if (videoTrack != null) {
                        videoTrack.addSink(rTCVideoView3);
                    }
                }
                if (this.mParamSettings.EnableMultistream) {
                    janusConnection.videoviewTags.put(str, Integer.valueOf(i3));
                } else {
                    janusConnection.videoview_tag = i3;
                }
                Logging.m5305d("BaiduRtcRoomImp", "use idle render view " + rTCVideoView3);
            }
            this.mRemoteVideoCnt++;
        }
    }

    private void initVideoSink(IRTCVideoSink iRTCVideoSink, final JanusConnection janusConnection, final BigInteger bigInteger, final String str) {
        if (iRTCVideoSink == null) {
            return;
        }
        iRTCVideoSink.setFirstFrameEventListener(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.18
            @Override // java.lang.Runnable
            public void run() {
                if (BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate != null && bigInteger != null) {
                    BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate.onRoomEventUpdate(106, bigInteger.longValue(), "");
                    RtcLogCapturer.reportEvent(RtcLogEvent.RTC_CALLBACK_USER_VIDEO_RENDERING, bigInteger.longValue(), "onRemoteStreamStats", bigInteger, str, BaiduRtcRoomImp.this.peerConnectionClient.getSsrcByMid(janusConnection.handleId, str));
                }
                BaiduRtcRoomImp.this.onFfDelayChange(janusConnection, bigInteger);
            }
        });
        if (this.mParamSettings.EnableMultistream) {
            iRTCVideoSink.setStuckEventListener(new VideoStuckEvent(this.mWebSocketChannel.getFeedByMid(str)));
        } else {
            iRTCVideoSink.setStuckEventListener(new VideoStuckEvent(janusConnection.handleId));
        }
        iRTCVideoSink.setEnableSLIDataReport(this.mIsEnablePullQualityMonitor);
        iRTCVideoSink.attach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFfDelayChange(JanusConnection janusConnection, BigInteger bigInteger) {
        HUDStatistics hUDStatistics;
        if (this.mIsEnablePullQualityMonitor) {
            if (this.mParamSettings.EnableMultistream) {
                hUDStatistics = this.mHUDStatisticsMap.get(bigInteger);
            } else {
                hUDStatistics = this.mHUDStatisticsMap.get(janusConnection.handleId);
            }
            if (hUDStatistics != null && bigInteger != null) {
                hUDStatistics.setFirstFrameTime(System.currentTimeMillis());
                reportSLIFfDelay(bigInteger.longValue(), hUDStatistics.getFirstFrameTime() - hUDStatistics.getRequestSubscribeTime());
            }
            HUDStatistics hUDStatistics2 = this.mHUDStatisticsMap.get(this.mPublisherHandle);
            if (hUDStatistics2 != null) {
                hUDStatistics2.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_FIRST_FRAME.ordinal(), 0, System.currentTimeMillis() - this.mInitSdkTs);
                this.mBaiduRtcRoomDelegate.onRoomEventUpdate(202, 0L, null);
            }
        }
    }

    public static BaiduRtcRoom.RoomInfo getRoomInfofromPlatformServer(String str, String str2, String str3, String str4) {
        BaiduRtcRoom.RoomInfo roomInfo = new BaiduRtcRoom.RoomInfo();
        try {
            MediaType parse = MediaType.parse("application/json;charset=utf-8");
            OkHttpClient.Builder readTimeout = new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.19
                @Override // okhttp3.Interceptor
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    return chain.proceed(chain.request().newBuilder().build());
                }
            }).connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS);
            OkHttpClient build = !(readTimeout instanceof OkHttpClient.Builder) ? readTimeout.build() : NBSOkHttp3Instrumentation.builderInit(readTimeout);
            try {
                String string = build.newCall(new Request.Builder().url(str4).post(RequestBody.create(parse, "{\"appId\":\"" + str + "\",\"roomName\":\"" + str2 + "\",\"uId\":\"" + str3 + "\"}")).build()).execute().body().string();
                Logging.m5305d("BaiduRtcRoomImp", string);
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    String optString = jSONObject.optString("mediaServerIP");
                    jSONObject.optString("mediaServerPort");
                    String optString2 = jSONObject.optString("roomId");
                    if (!optString.isEmpty()) {
                        roomInfo.MediaServerURL = "ws://" + optString + ":8188/janus";
                        roomInfo.RoomID = optString2;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return roomInfo;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean isIsPeerConnectionFactoryReady() {
        if (this.peerConnectionClient != null) {
            return this.peerConnectionClient.isIsPeerConnectionFactoryReady();
        }
        return false;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setMediaServerURL(String str) {
        this.mJanusServerURL = str;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setVideoCapture(VideoCapturer videoCapturer) {
        this.videoCapturer = videoCapturer;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setVideoProcessor(VideoProcessor videoProcessor) {
        this.externalVideoProcessor = videoProcessor;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setAuidoSamplesReadyCallback(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setSamplesReadyCallback(rTCSamplesReadyCallback);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setAudioRecordDelegate(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setSamplesReadyCallback(rTCSamplesReadyCallback);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteAudioSamplesCallback(RTCAudioSamples.RTCRemoteSamplesReadyCallback rTCRemoteSamplesReadyCallback) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setRemoteSamplesCallback(rTCRemoteSamplesReadyCallback);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setMixedAudioSamplesCallback(RTCAudioSamples.RTCMixedSamplesReadyCallback rTCMixedSamplesReadyCallback) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setMixedSamplesCallback(rTCMixedSamplesReadyCallback);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setAudioSampleStatusCallback(RTCAudioSamples.RTCSampleStatusCallback rTCSampleStatusCallback) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setAudioSampleStatusCallback(rTCSampleStatusCallback);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setAudioAecSamplesReadyCallback(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setAudioAecSamplesReadyCallback(rTCSamplesReadyCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VideoProcessor createVideoProcessor() {
        IRtcFrameProcessor iRtcFrameProcessor;
        VideoProcessor videoProcessor = this.externalVideoProcessor;
        if (videoProcessor != null) {
            return videoProcessor;
        }
        try {
            iRtcFrameProcessor = (IRtcFrameProcessor) Class.forName("com.baidu.cloud.rtcbridge.frameprocessor.RtcFrameProcessorImpl").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            iRtcFrameProcessor = null;
        }
        if (iRtcFrameProcessor != null) {
            boolean z = this.rtcFrameCapture != null;
            RtcParameterSettings rtcParameterSettings = this.mParamSettings;
            if (rtcParameterSettings != null && rtcParameterSettings.enableBeauty) {
                this.mFrameProcessorManager.params.enableBeauty = true;
            }
            final VideoProcessor createVideoProcessor = iRtcFrameProcessor.createVideoProcessor(this.mContext.get(), this.mFrameProcessorManager.params, z);
            if (z) {
                this.rtcFrameCapture.registerCapturerObserver(new RtcFrameCapturerObserver() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.20
                    @Override // com.webrtc.CapturerObserver
                    public void onFrameCaptured(VideoFrame videoFrame) {
                    }

                    @Override // com.baidu.cloud.rtcbridge.framecapture.RtcFrameCapturerObserver
                    public VideoFrameBuffer onFrameProcessor(VideoFrameBuffer videoFrameBuffer) {
                        VideoProcessor videoProcessor2 = createVideoProcessor;
                        if (videoProcessor2 instanceof InnerCameraVideoProcessor) {
                            return ((InnerCameraVideoProcessor) videoProcessor2).onFrameProcessor(videoFrameBuffer);
                        }
                        return null;
                    }

                    @Override // com.webrtc.CapturerObserver
                    public void onCapturerStarted(boolean z2) {
                        VideoProcessor videoProcessor2 = createVideoProcessor;
                        if (videoProcessor2 != null) {
                            videoProcessor2.onCapturerStarted(z2);
                        }
                    }

                    @Override // com.webrtc.CapturerObserver
                    public void onCapturerStopped() {
                        VideoProcessor videoProcessor2 = createVideoProcessor;
                        if (videoProcessor2 != null) {
                            videoProcessor2.onCapturerStopped();
                        }
                    }
                });
            }
            this.innerVideoProcessor = createVideoProcessor;
            if (z) {
                return null;
            }
            return createVideoProcessor;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VideoCapturer createVideoCapturer() {
        VideoCapturer videoCapturer;
        VideoCapturer videoCapturer2 = this.videoCapturer;
        if (videoCapturer2 != null) {
            return videoCapturer2;
        }
        try {
            if (this.rtcFrameCapture == null) {
                this.rtcFrameCapture = new RtcFrameCaptureImpl();
            }
            CameraEventCallback cameraEventCallback = new CameraEventCallback();
            cameraEventCallback.setCameraEventsHandler(new CameraEventsHandlerAdapter() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.21
                @Override // com.baidu.rtc.camera.CameraEventsHandlerAdapter, com.baidu.rtc.camera.CameraEventsHandler
                public void onCameraClosed() {
                    if (BaiduRtcRoomImp.this.mLocalRender != null) {
                        BaiduRtcRoomImp.this.mLocalRender.clearImage();
                    }
                }

                @Override // com.baidu.rtc.camera.CameraEventsHandlerAdapter, com.baidu.rtc.camera.CameraEventsHandler
                public void onCameraError(String str) {
                    BaiduRtcRoomImp.this.stopPreview();
                }

                @Override // com.baidu.rtc.camera.CameraEventsHandlerAdapter, com.baidu.rtc.camera.CameraEventsHandler
                public void onCameraDisconnected() {
                    BaiduRtcRoomImp.this.stopPreview();
                }

                @Override // com.baidu.rtc.camera.CameraEventsHandlerAdapter, com.baidu.rtc.camera.CameraEventsHandler
                public void onCameraOpening(String str, boolean z) {
                    BaiduRtcRoomImp.this.mUsingFrontCamera = z;
                    if (BaiduRtcRoomImp.this.mLocalRender != null) {
                        BaiduRtcRoomImp.this.mLocalRender.setMirror(BaiduRtcRoomImp.this.mUsingFrontCamera);
                    }
                }
            });
            videoCapturer = this.rtcFrameCapture.createCameraCapturer(this.mContext.get(), this.mCameraID, cameraEventCallback);
        } catch (Exception e) {
            e.printStackTrace();
            videoCapturer = null;
        }
        if (videoCapturer == null) {
            Logging.m5304e("BaiduRtcRoomImp", "Failed to create camera");
            RtcLogCapturer.reportLog(RtcLogEvent.INIT_CAMERA_FAILED, "BaiduRtcRoomImp-createVideoCapturer", -1, "Failed to open camera");
            return null;
        }
        RtcLogCapturer.reportLog(RtcLogEvent.INIT_CAMERA_SUCCESS, "BaiduRtcRoomImp-createVideoCapturer", Integer.valueOf(this.mCameraID));
        return videoCapturer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ScreenCapturerAndroid createScreenCapturer() {
        RtcParameterSettings rtcParameterSettings = this.mParamSettings;
        if (rtcParameterSettings == null || rtcParameterSettings.screenIntentData == null) {
            Logging.m5304e("BaiduRtcRoomImp", "mParamSettings is null or mParamSettings.screenIntentData is null");
            return null;
        }
        return new ScreenCapturerAndroid(this.mParamSettings.screenIntentData, new MediaProjection.Callback() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.22
            @Override // android.media.projection.MediaProjection.Callback
            public void onStop() {
                Logging.m5301w("BaiduRtcRoomImp", "User revoked permission to capture the screen.");
                if (BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate != null) {
                    BaiduRtcRoomImp.this.mBaiduRtcRoomDelegate.onRoomEventUpdate(50000, 0L, "User revoked permission to capture the screen.");
                }
            }
        }, this.screenAudioSamplesReadyCallback);
    }

    private void offerPeerConnection(final BigInteger bigInteger) {
        if (this.mIsStreamPublish) {
            return;
        }
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setStopPublish(false);
        }
        this.mIsStreamPublish = true;
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.internal.BaiduRtcRoomImp.23
            @Override // java.lang.Runnable
            public void run() {
                if (BaiduRtcRoomImp.this.mParamSettings.HasVideo) {
                    BaiduRtcRoomImp baiduRtcRoomImp = BaiduRtcRoomImp.this;
                    baiduRtcRoomImp.videoCapturer = baiduRtcRoomImp.createVideoCapturer();
                    if (BaiduRtcRoomImp.this.mVideoCapturerState == 0) {
                        BaiduRtcRoomImp baiduRtcRoomImp2 = BaiduRtcRoomImp.this;
                        baiduRtcRoomImp2.localVideoProcessor = baiduRtcRoomImp2.createVideoProcessor();
                    }
                }
                ScreenCapturerAndroid createScreenCapturer = (BaiduRtcRoomImp.this.mParamSettings.screenIntentData == null || !BaiduRtcRoomImp.this.mParamSettings.HasScreen) ? null : BaiduRtcRoomImp.this.createScreenCapturer();
                if (BaiduRtcRoomImp.this.peerConnectionClient != null) {
                    BaiduRtcRoomImp.this.peerConnectionClient.setVideoCapturerState(BaiduRtcRoomImp.this.mVideoCapturerState);
                    if (!BaiduRtcRoomImp.this.mIsEnableExternalVideoCapturer) {
                        if (BaiduRtcRoomImp.this.mLocalRender != null) {
                            BaiduRtcRoomImp.this.mLocalRender.attach();
                        }
                        BaiduRtcRoomImp.this.peerConnectionClient.createPeerConnection(BaiduRtcRoomImp.this.rootEglBase.getEglBaseContext(), BaiduRtcRoomImp.this.mLocalRender, BaiduRtcRoomImp.this.mLocalScreenRender, BaiduRtcRoomImp.this.videoCapturer, createScreenCapturer, BaiduRtcRoomImp.this.localVideoProcessor, bigInteger);
                    } else {
                        BaiduRtcRoomImp.this.peerConnectionClient.createPeerConnection(BaiduRtcRoomImp.this.rootEglBase.getEglBaseContext(), BaiduRtcRoomImp.this.mVideoSink, BaiduRtcRoomImp.this.mVideoSink, BaiduRtcRoomImp.this.videoCapturer, createScreenCapturer, BaiduRtcRoomImp.this.localVideoProcessor, bigInteger);
                    }
                    BaiduRtcRoomImp.this.mStartLoginTime = System.currentTimeMillis();
                    BaiduRtcRoomImp.this.peerConnectionClient.createOffer(bigInteger, false);
                    return;
                }
                Logging.m5304e("BaiduRtcRoomImp", "peerConnectionClient is null!");
            }
        });
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onWebrtcUp(BigInteger bigInteger, String str) {
        if (this.mIsEnablePushQualityMonitor) {
            if (bigInteger.equals(this.mPublisherHandle)) {
                this.mIsLoginSuccess = true;
                this.mLoginSuccessTime = System.currentTimeMillis();
                if (this.mLoginSuccessTime - this.mStartLoginTime > 5000) {
                    if (this.mIsEnableErrorInfoMonitor) {
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_TIMEOUT);
                    }
                    reportSLILoginEvent("ENTER_TIMEOUT");
                }
                if (this.mKcpClient == null) {
                    createKcpClient();
                }
                this.mKcpClient.startPingRtt(this.mContext.get(), str);
            } else {
                HUDStatistics hUDStatistics = this.mHUDStatisticsMap.get(this.mPublisherHandle);
                if (hUDStatistics != null) {
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_SDK.ordinal(), 0, this.mInitSdkDoneTs - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_SETUPD.ordinal(), 0, this.mStartConnectTime - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_WEBSOCKET_OPENED.ordinal(), 0, this.mWssOpenedTime - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_USER_JOINED.ordinal(), 0, this.mUserJoinedTime - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_SUBSCRIBE.ordinal(), 0, this.mSubscribeTime - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_SET_REMOTE_DESCRIPTION.ordinal(), 0, this.mSetRemoteSdpTime - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_SET_LOCAL_DESCRIPTION.ordinal(), 0, this.mSetLocalSdpTime - this.mInitSdkTs);
                    hUDStatistics.setDeltaStateMs(HUDStatistics.RtcRenderStep.RTC_RENDER_STEP_RWEBRTCUP.ordinal(), 0, System.currentTimeMillis() - this.mInitSdkTs);
                    this.mBaiduRtcRoomDelegate.onRoomEventUpdate(202, 0L, null);
                }
            }
        }
        RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_WEBRTCUP, "onWebrtcUp", bigInteger);
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onPeerConnectStateUpdate(2000);
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_STREAM_UP, "onWebrtcUp", bigInteger);
        StringBuilder sb = new StringBuilder();
        sb.append("WEBRTC_UP_");
        sb.append(bigInteger != null ? Long.valueOf(bigInteger.longValue()) : "");
        reportRoomEventInfo(sb.toString());
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onMediaStreamingEvent(BigInteger bigInteger, int i, boolean z) {
        if (this.mIsEnableErrorInfoMonitor) {
            if (i == 1) {
                if (z) {
                    RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_SENDING_VIDEO_OK, "onMediaStreamingEvent", bigInteger);
                } else {
                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.VIDEO_SENDING_MEDIA_FAILED);
                    RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_SENDING_VIDEO_DOWN, "onMediaStreamingEvent", bigInteger);
                }
            } else if (i == 0) {
                if (z) {
                    RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_SENDING_AUDIO_OK, "onMediaStreamingEvent", bigInteger);
                } else {
                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_SENDING_MEDIA_FAILED);
                    RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_SENDING_AUDIO_DOWN, "onMediaStreamingEvent", bigInteger);
                }
            }
        }
        if (this.mBaiduRtcRoomDelegate == null) {
            return;
        }
        if (!z) {
            this.isPublisherSendSuccess = false;
            this.mHandler.postDelayed(this.mediaListenerRunnable, 5000L);
        } else {
            this.isPublisherSendSuccess = true;
        }
        this.mBaiduRtcRoomDelegate.onPeerConnectStateUpdate(z ? 2001 : 2002);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onHangUp(BigInteger bigInteger) {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.HANG_UP);
        }
        if (this.mBaiduRtcRoomDelegate == null) {
            return;
        }
        boolean z = false;
        BigInteger bigInteger2 = this.mPublisherHandle;
        if (bigInteger2 != null && bigInteger != null && bigInteger2.longValue() == bigInteger.longValue()) {
            z = true;
        }
        int i = z ? 2006 : 2007;
        this.mBaiduRtcRoomDelegate.onPeerConnectStateUpdate(2003);
        this.mBaiduRtcRoomDelegate.onPeerConnectStateUpdate(i);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onLoginTimeout() {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.LOGIN_TIMEOUT);
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_FAILED);
        }
        if (this.mIsEnablePushQualityMonitor && !this.mIsLoginSuccess) {
            reportSLILoginEvent("ENTER_FAILED");
        }
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_LOGIN_TIMEOUT, "onLoginTimeout", "-1", "login timeout");
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(101, 0L, "time out");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onLoginError() {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.LOGIN_ERROR);
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_FAILED);
        }
        if (this.mIsEnablePushQualityMonitor && !this.mIsLoginSuccess) {
            reportSLILoginEvent("ENTER_FAILED");
        }
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_LOGIN_ERROR, "onLoginError", "-1", "login error");
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(102, 0L, "error");
        reportRoomEventInfo("LOGIN_ERROR");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onWebSocketConnectError(String str) {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_FAILED);
        }
        if (this.mIsEnablePushQualityMonitor && !this.mIsLoginSuccess) {
            reportSLILoginEvent("ENTER_FAILED");
        }
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_CONNECTION_LOST, "onWebSocketConnectError", "-1", RtcLogCapturer.getErrorInfo(str));
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(103, 0L, str);
        reportRoomEventInfo("CONNECTION_LOST [" + str + "]");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onWebSocketClosing(String str) {
        if (this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ENTER_FAILED);
        }
        if (this.mIsEnablePushQualityMonitor && !this.mIsLoginSuccess) {
            reportSLILoginEvent("ENTER_FAILED");
        }
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_CONNECTION_LOST, "onWebSocketConnectError", "-1", RtcLogCapturer.getErrorInfo(str));
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(20001, 0L, str);
        reportRoomEventInfo("CONNECTION_LOST [" + str + "]");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onConnectClosed() {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(20000, 0L, "connection closed");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onUserShutUp(long j) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(113, j, "");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onUserDisShutUp(long j) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(114, j, "");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onRoomDisbanded() {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(112, 0L, "");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onUserKickOff(long j) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(115, j, "");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onLivePublishSucceed(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode, String str) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(116, rtcLiveTransferMode.ordinal(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onLivePublishFailed(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode, String str) {
        if (this.mIsEnableErrorInfoMonitor) {
            if (rtcLiveTransferMode.ordinal() == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION.ordinal()) {
                ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ROOM_LIVE_PUBLISH_FAIL);
            } else {
                ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ANCHOR_LIVE_PUBLISH_FAIL);
            }
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(117, rtcLiveTransferMode.ordinal(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onLivePublishInterrupted(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode, String str) {
        if (this.mIsEnableErrorInfoMonitor) {
            if (rtcLiveTransferMode.ordinal() == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION.ordinal()) {
                ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ROOM_LIVE_INTRERRUPT);
            } else {
                ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.ANCHOR_LIVE_INTRERRUPT);
            }
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(118, rtcLiveTransferMode.ordinal(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onSignalErrorInfo(long j, int i, String str) {
        if (this.mBaiduRtcRoomDelegate == null) {
            return;
        }
        if (i == 436 && this.mIsEnableErrorInfoMonitor) {
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.USR_ALREADY_EXIST);
        }
        this.mBaiduRtcRoomDelegate.onErrorInfoUpdate(i);
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_SIGNAL_ERROR, "onSignalErrorInfo", Integer.valueOf(i), RtcLogCapturer.getErrorInfo(str));
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onServerAckTimeout() {
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_KEEPALIVE_TIMEOUT_ERROR, "onServerAckTimeout", -1, "");
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onErrorInfoUpdate(402);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onStartMediaRelaySucceed(String str, long j) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(119, 0L, "");
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_ROOM_MEDIA_RELAY_SUCCESS, "onStartMediaRelaySucceed", str, Long.valueOf(j));
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onStartMediaRelayFailed(String str, long j) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(120, 0L, "");
        RtcLogCapturer.reportLog(RtcLogEvent.RTC_CALLBACK_ROOM_MEDIA_RELAY_ERROR, "onStartMediaRelayFailed", str, Long.valueOf(j), -1, "");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onStopMediaRelaySucceed() {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(121, 0L, "");
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onError(ErrorInfo errorInfo) {
        if (this.mBaiduRtcRoomDelegate == null) {
        }
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onSubscribeUserStreamLeave(BigInteger bigInteger) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null || bigInteger == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(30000, bigInteger.longValue(), "");
        RtcLogCapturer.reportEvent(RtcLogEvent.SUBSCRIBED_USER_STREAM_LEAVE, bigInteger.longValue(), "user streamleave", Long.valueOf(bigInteger.longValue()));
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onComing(BigInteger bigInteger, String str) {
        if (bigInteger == null) {
            return;
        }
        if (this.mLastLeavingId == bigInteger.longValue()) {
            this.mLastLeavingId = 0L;
        }
        if (this.mLastGoneId == bigInteger.longValue()) {
            this.mLastGoneId = 0L;
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(104, bigInteger.longValue(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onAddedStreams(BigInteger bigInteger, ArrayList<CommonDefine.StreamInfo> arrayList) {
        if (bigInteger == null || arrayList == null || !this.mParamSettings.EnableMultistream) {
            return;
        }
        if (this.mOperatingFeedStreams == null) {
            this.mOperatingFeedStreams = new HashMap();
        }
        this.mOperatingFeedStreams.put(bigInteger, arrayList);
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onRoomEventUpdate(123, bigInteger.longValue(), "coming streams");
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public ArrayList<CommonDefine.StreamInfo> getComingStreams(long j) {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        if (webSocketChannel == null) {
            return null;
        }
        return webSocketChannel.getComingStreams(BigInteger.valueOf(j));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public ArrayList<CommonDefine.StreamInfo> getOperatingStreams(long j) {
        Map<BigInteger, ArrayList<CommonDefine.StreamInfo>> map = this.mOperatingFeedStreams;
        if (map == null) {
            return null;
        }
        return map.get(BigInteger.valueOf(j));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public IRtcBeautyManager getRtcBeautyManager() {
        VideoProcessor videoProcessor = this.innerVideoProcessor;
        if (videoProcessor != null && (videoProcessor instanceof IRtcBeautyManager)) {
            return (IRtcBeautyManager) videoProcessor;
        }
        return this.mFrameProcessorManager;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public IRtcFrameProcessorManager getRtcFrameProcessorManager() {
        VideoProcessor videoProcessor = this.innerVideoProcessor;
        if (videoProcessor != null && (videoProcessor instanceof IRtcFrameProcessorManager)) {
            return (IRtcFrameProcessorManager) videoProcessor;
        }
        return this.mFrameProcessorManager;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void updateScreenShareParams(BRTCScreenShareParams bRTCScreenShareParams) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.updateScreenShareParams(bRTCScreenShareParams);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setKeyAgreementProtocol(BaiduRtcRoom.KeyAgreementProtocol keyAgreementProtocol) {
        this.keyAgreementProtocol = keyAgreementProtocol;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setDisplayEnableHardwareScaler(boolean z) {
        this.enableHardwareScaler = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void injectLoggable(Loggable loggable, Logging.Severity severity) {
        this.mLoggable = loggable;
        this.mLoggableSeverity = severity;
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onSlowLink(boolean z, int i) {
        if (this.mBaiduRtcRoomDelegate == null) {
            return;
        }
        this.mBaiduRtcRoomDelegate.onPeerConnectStateUpdate(i < 10 ? 2100 : i < 15 ? 2101 : i < 25 ? 2102 : i < 40 ? 2103 : i < 60 ? 2104 : i < 90 ? 2105 : i < 120 ? 2106 : i < 180 ? 2107 : i < 250 ? 2108 : 2109);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onMessage(BigInteger bigInteger, String str) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null || bigInteger == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(302, bigInteger.longValue(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onAttribute(BigInteger bigInteger, String str) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null || bigInteger == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(303, bigInteger.longValue(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onUserJoinedRoom(BigInteger bigInteger, String str, String str2) {
        if (this.mBaiduRtcRoomDelegate == null || bigInteger == null) {
            return;
        }
        this.mUserJoinedTime = System.currentTimeMillis();
        RtcLogCapturer.reportEvent(RtcLogEvent.RTC_CALLBACK_USER_JOINED, bigInteger.longValue(), "onUserJoinedRoom", Long.valueOf(bigInteger.longValue()), str);
        this.mBaiduRtcRoomDelegate.onRoomEventUpdate(300, bigInteger.longValue(), str);
    }

    @Override // com.baidu.rtc.JanusRTCInterface
    public void onUserLeavingRoom(BigInteger bigInteger) {
        if (this.mBaiduRtcRoomDelegate == null || bigInteger == null) {
            return;
        }
        RtcLogCapturer.reportEvent(RtcLogEvent.RTC_CALLBACK_USER_LEAVING, bigInteger.longValue(), "onUserLeavingRoom", Long.valueOf(bigInteger.longValue()), "");
        this.mBaiduRtcRoomDelegate.onRoomEventUpdate(301, bigInteger.longValue(), "");
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void sendData(ByteBuffer byteBuffer) {
        if (this.peerConnectionClient == null) {
            return;
        }
        this.peerConnectionClient.sendData(byteBuffer);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void sendMessageToUser(String str, long j) {
        if (this.mWebSocketChannel == null || isBigRoomMode()) {
            return;
        }
        this.mWebSocketChannel.sendMessageToUser(str, j);
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setUserAttribute(String str) {
        if (this.mWebSocketChannel == null || isBigRoomMode()) {
            return;
        }
        this.mWebSocketChannel.setUserAttribute(str);
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void getUserAttribute(long j) {
        if (this.mWebSocketChannel == null || isBigRoomMode()) {
            return;
        }
        this.mWebSocketChannel.getUserAttribute(j);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onRemoteData(ByteBuffer byteBuffer) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomDataMessage(byteBuffer);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onAudioRecordStartError(int i, String str) {
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(40000, i, str);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onShareScreenStart(int i, String str) {
        if (i == 0) {
            this.mParamSettings.HasScreen = true;
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(125, i, str);
    }

    @Override // com.baidu.rtc.PeerConnectionClient.PeerConnectionEvents
    public void onShareScreenStop(int i, String str) {
        if (i == 0) {
            this.mParamSettings.HasScreen = false;
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate == null) {
            return;
        }
        baiduRtcRoomDelegate.onRoomEventUpdate(126, i, str);
    }

    public void updateBweStatistics(StatsReport[] statsReportArr) {
        StatsReport.Value[] valueArr;
        if (this.mBaiduRtcRoomDelegate == null) {
            return;
        }
        for (StatsReport statsReport : statsReportArr) {
            if (statsReport.f21266id.equals("bweforvideo")) {
                for (StatsReport.Value value : statsReport.values) {
                    if (value.name.equals("googAvailableSendBandwidth")) {
                        double parseDouble = Double.parseDouble(value.value);
                        if (parseDouble > 100.0d) {
                            this.mBaiduRtcRoomDelegate.onRoomEventUpdate(200, (long) parseDouble, statsReport.toString());
                        }
                    }
                }
                return;
            }
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableExternalVideoCapturer(boolean z) {
        this.mIsEnableExternalVideoCapturer = z;
        this.mVideoSink = dummyStaticVideoSink;
        RtcLogCapturer.reportLog(RtcLogEvent.SET_VIDEO_EXTERNAL_CAPTURE, "enableExternalVideoCapturer", Boolean.valueOf(z));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableExternalAudioRecord(boolean z) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.enableExternalAudioRecord(z);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.SET_AUDIO_EXTERNAL_CAPTURE, "enableExternalAudioRecord", Boolean.valueOf(z));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableAudioMix(boolean z) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.enableAudioMix(z);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public boolean isExternalAudioRecord() {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            return bdRTCAudioManager.isEnableExternalAudioRecord();
        }
        return false;
    }

    private synchronized void userRenderMove2PendingMap(long j) {
        if (this.mUserIdVideoRendererMap.containsKey(Long.valueOf(j))) {
            this.mPendingRemoveRendererMap.put(Long.valueOf(j), this.mUserIdVideoRendererMap.get(Long.valueOf(j)));
            this.mUserIdVideoRendererMap.remove(Long.valueOf(j));
        }
    }

    public synchronized RTCVideoExternalRender getExternalRender(long j) {
        Logging.m5305d("BaiduRtcRoomImp", j + " : Get external renderer.");
        if (this.mUserIdVideoRendererMap.containsKey(Long.valueOf(j))) {
            return this.mUserIdVideoRendererMap.get(Long.valueOf(j));
        }
        RTCVideoExternalRenderImp rTCVideoExternalRenderImp = new RTCVideoExternalRenderImp(this.mBaiduRtcRoomDelegate, j);
        Logging.m5305d("BaiduRtcRoomImp", j + " : Create external renderer" + rTCVideoExternalRenderImp);
        this.mUserIdVideoRendererMap.put(Long.valueOf(j), rTCVideoExternalRenderImp);
        return rTCVideoExternalRenderImp;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void addExternalRenderer(long j, RTCVideoExternalRender rTCVideoExternalRender) {
        removeExternalRenderer(j);
        this.mUserIdVideoRendererMap.put(Long.valueOf(j), rTCVideoExternalRender);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void removeExternalRenderer(long j) {
        Logging.m5305d("BaiduRtcRoomImp", j + " : Remove external renderer ...");
        if (!this.mUserIdVideoRendererMap.containsKey(Long.valueOf(j))) {
            Logging.m5305d("BaiduRtcRoomImp", "External render not found to  remove surface for " + j);
            return;
        }
        RTCVideoExternalRender rTCVideoExternalRender = this.mUserIdVideoRendererMap.get(Long.valueOf(j));
        if (rTCVideoExternalRender != null) {
            rTCVideoExternalRender.release();
        }
        this.mUserIdVideoRendererMap.remove(Long.valueOf(j));
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setExternalSurface(long j, Surface surface) {
        RTCVideoExternalRender externalRender = getExternalRender(j);
        if (externalRender != null) {
            if (!externalRender.isRenderInited()) {
                externalRender.init();
                externalRender.setRenderInited(true);
            }
            for (Long l : this.mUserIdVideoRendererMap.keySet()) {
                RTCVideoExternalRender rTCVideoExternalRender = this.mUserIdVideoRendererMap.get(l);
                if (rTCVideoExternalRender != null && rTCVideoExternalRender.hasSurface() && surface == rTCVideoExternalRender.getSurface() && this.mBaiduRtcRoomDelegate != null) {
                    Logging.m5305d("BaiduRtcRoomImp", "Set surface repeat with userId " + j);
                    if (this.mIsEnableErrorInfoMonitor) {
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.SET_EXTERNAL_SURFACE_ERROR);
                    }
                    this.mBaiduRtcRoomDelegate.onErrorInfoUpdate(600);
                    return;
                }
            }
            externalRender.setSurface(surface);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void changeSurfaceSize(long j, int i, int i2) {
        if (!this.mUserIdVideoRendererMap.containsKey(Long.valueOf(j))) {
            Logging.m5305d("BaiduRtcRoomImp", "External render not found to  change surface size for " + j);
            return;
        }
        RTCVideoExternalRender rTCVideoExternalRender = this.mUserIdVideoRendererMap.get(Long.valueOf(j));
        if (rTCVideoExternalRender != null) {
            rTCVideoExternalRender.changeSurfaceSize(i, i2);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void destroyExternalSurface(long j, Surface surface) {
        RTCVideoExternalRender rTCVideoExternalRender;
        if (this.mUserIdVideoRendererMap.containsKey(Long.valueOf(j)) && (rTCVideoExternalRender = this.mUserIdVideoRendererMap.get(Long.valueOf(j))) != null && surface == rTCVideoExternalRender.getSurface()) {
            rTCVideoExternalRender.releaseSurface();
        }
        disposePendingRenderers();
    }

    private void disposeExternalRenderers() {
        if (this.mUserIdVideoRendererMap.isEmpty()) {
            return;
        }
        for (Long l : this.mUserIdVideoRendererMap.keySet()) {
            RTCVideoExternalRender rTCVideoExternalRender = this.mUserIdVideoRendererMap.get(l);
            if (rTCVideoExternalRender != null) {
                if (rTCVideoExternalRender.getSurface() != null) {
                    rTCVideoExternalRender.releaseSurface();
                }
                rTCVideoExternalRender.release();
            }
            this.mUserIdVideoRendererMap.remove(l);
        }
    }

    private synchronized void disposePendingRenderers() {
        if (this.mPendingRemoveRendererMap.isEmpty()) {
            return;
        }
        for (RTCVideoExternalRender rTCVideoExternalRender : this.mPendingRemoveRendererMap.values()) {
            if (rTCVideoExternalRender != null) {
                if (rTCVideoExternalRender.getSurface() != null) {
                    rTCVideoExternalRender.releaseSurface();
                }
                rTCVideoExternalRender.release();
            }
        }
        this.mPendingRemoveRendererMap.clear();
    }

    private void disposeRemoteRenderViews() {
        if (this.mRemoteRenderViewsMap.isEmpty()) {
            return;
        }
        for (BigInteger bigInteger : this.mRemoteRenderViewsMap.keySet()) {
            Map<String, RTCVideoView> map = this.mRemoteRenderViewsMap.get(bigInteger);
            if (map != null) {
                for (Map.Entry<String, RTCVideoView> entry : map.entrySet()) {
                    RTCVideoView value = entry.getValue();
                    value.deatach();
                    value.release();
                }
            }
            this.mRemoteRenderViewsMap.remove(bigInteger);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteVideoPlayState(boolean z, long j) {
        StreamStats streamStats = this.mRemoteStreamStats.get(BigInteger.valueOf(j));
        if (streamStats == null || streamStats.hasVideo.booleanValue() == z) {
            return;
        }
        streamStats.hasVideo = Boolean.valueOf(z);
        if (this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.setRemoteMultiStreamPlayState(Boolean.valueOf(z), streamStats.hasAudio, j);
        } else {
            this.mWebSocketChannel.setRemoteStreamPlayState(Boolean.valueOf(z), streamStats.hasAudio, j);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setRemoteAudioPlayState(boolean z, long j) {
        StreamStats streamStats = this.mRemoteStreamStats.get(BigInteger.valueOf(j));
        if (streamStats == null || streamStats.hasAudio.booleanValue() == z) {
            return;
        }
        streamStats.hasAudio = Boolean.valueOf(z);
        if (this.mParamSettings.EnableMultistream) {
            this.mWebSocketChannel.setRemoteMultiStreamPlayState(streamStats.hasVideo, Boolean.valueOf(z), j);
        } else {
            this.mWebSocketChannel.setRemoteStreamPlayState(streamStats.hasVideo, Boolean.valueOf(z), j);
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableAgc(boolean z) {
        this.enableWebRtcAGC = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableAns(boolean z) {
        this.enableWebRtcANS = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableAec(boolean z) {
        this.enableWebRtcAEC = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableBdAGC(boolean z) {
        this.enableBdAGC = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableBdAEC(boolean z) {
        this.enableBdAEC = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void enableBdANS(boolean z) {
        this.enableBdANS = z;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void setSoundMod(BaiduRtcRoom.RtcSoundMode rtcSoundMode) {
        BdRTCAudioManager bdRTCAudioManager = this.bdRTCAudioManager;
        if (bdRTCAudioManager != null) {
            bdRTCAudioManager.setSoundMod(rtcSoundMode);
        }
    }

    @Override // com.baidu.rtc.utils.BRtcPhoneStateManager.IPhoneStateChangeListener
    public void onPhoneStateChanged(int i) {
        switch (i) {
            case 0:
                if (!this.mIsMuteMic) {
                    muteMicphone(false);
                }
                if (!this.mIsMuteSpeaker) {
                    muteSpeaker(false);
                    break;
                }
                break;
            case 1:
                if (this.peerConnectionClient != null) {
                    this.peerConnectionClient.setAudioEnabled(false);
                    this.peerConnectionClient.setSpeakerMute(true);
                    break;
                }
                break;
        }
        BaiduRtcRoom.BaiduRtcRoomDelegate baiduRtcRoomDelegate = this.mBaiduRtcRoomDelegate;
        if (baiduRtcRoomDelegate != null) {
            baiduRtcRoomDelegate.onRoomEventUpdate(60000, i, "");
        }
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public BaiduRtcRoom.RtcRoomAudioLevel[] getRemoteAudioLevels() {
        int i = 0;
        int i2 = 0;
        for (BigInteger bigInteger : this.mRemoteStreamStats.keySet()) {
            StreamStats streamStats = this.mRemoteStreamStats.get(bigInteger);
            if (streamStats != null && streamStats.hasAudio.booleanValue() && streamStats.volume != -1) {
                i2++;
            }
        }
        BaiduRtcRoom.RtcRoomAudioLevel[] rtcRoomAudioLevelArr = new BaiduRtcRoom.RtcRoomAudioLevel[i2];
        for (BigInteger bigInteger2 : this.mRemoteStreamStats.keySet()) {
            StreamStats streamStats2 = this.mRemoteStreamStats.get(bigInteger2);
            if (streamStats2 != null && streamStats2.volume != -1 && streamStats2.hasAudio.booleanValue()) {
                if (i >= i2) {
                    break;
                }
                rtcRoomAudioLevelArr[i] = new BaiduRtcRoom.RtcRoomAudioLevel();
                rtcRoomAudioLevelArr[i].userId = streamStats2.userId;
                rtcRoomAudioLevelArr[i].volumeLevel = streamStats2.volume;
                rtcRoomAudioLevelArr[i].nicName = streamStats2.nickName;
                i++;
            }
        }
        return rtcRoomAudioLevelArr;
    }

    @Override // com.baidu.rtc.BaiduRtcRoom
    public void muteSpeaker(boolean z) {
        if (this.peerConnectionClient != null) {
            this.peerConnectionClient.setSpeakerMute(z);
        }
        this.mIsMuteSpeaker = z;
        RtcLogCapturer.reportLog(RtcLogEvent.MUTE_SPEAKER, "muteSpeaker", Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAudioLeves(StatsReport[] statsReportArr, BigInteger bigInteger) {
        BigInteger bigInteger2;
        StatsReport.Value[] valueArr;
        StreamStats streamStats;
        StatsReport[] statsReportArr2 = statsReportArr;
        if (this.mWebSocketChannel == null) {
            return;
        }
        StreamStats streamStats2 = null;
        int i = 0;
        if (bigInteger.equals(this.mPublisherHandle)) {
            bigInteger2 = BigInteger.valueOf(this.mUserId);
            if (this.mParamSettings.EnableMultistream && this.mRemoteStreamStats.get(bigInteger2) == null) {
                if (this.peerConnectionClient != null) {
                    this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
                    return;
                }
                return;
            }
        } else if (this.mParamSettings.EnableMultistream) {
            bigInteger2 = null;
        } else {
            bigInteger2 = this.mWebSocketChannel.getFeedByHandle(bigInteger);
            if (bigInteger2 == null) {
                return;
            }
        }
        if (!this.mParamSettings.EnableMultistream && (streamStats2 = this.mRemoteStreamStats.get(bigInteger2)) == null) {
            if (this.peerConnectionClient != null) {
                this.peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
                return;
            }
            return;
        }
        int length = statsReportArr2.length;
        BigInteger bigInteger3 = bigInteger2;
        StreamStats streamStats3 = streamStats2;
        int i2 = 0;
        while (i2 < length) {
            StatsReport statsReport = statsReportArr2[i2];
            if (bigInteger.equals(this.mPublisherHandle)) {
                if (this.mParamSettings.EnableMultistream) {
                    streamStats3 = this.mRemoteStreamStats.get(bigInteger3);
                }
                if (statsReport.type.equals("ssrc") && statsReport.f21266id.contains("ssrc") && statsReport.f21266id.contains("send")) {
                    StatsReport.Value[] valueArr2 = statsReport.values;
                    int length2 = valueArr2.length;
                    for (int i3 = i; i3 < length2; i3++) {
                        StatsReport.Value value = valueArr2[i3];
                        if (value.name.contains("audioInputLevel") && streamStats3 != null) {
                            try {
                                streamStats3.volume = Integer.valueOf(value.value).intValue();
                            } catch (NumberFormatException e) {
                                Logging.m5304e("BaiduRtcRoomImp", "parse string num exception: " + e);
                            }
                        }
                    }
                }
            } else {
                int i4 = (statsReport.type.equals("ssrc") && statsReport.f21266id.contains("ssrc") && statsReport.f21266id.contains("recv")) ? 1 : i;
                if (this.mParamSettings.EnableMultistream) {
                    if (i4 != 0) {
                        String str = "";
                        StatsReport.Value[] valueArr3 = statsReport.values;
                        int length3 = valueArr3.length;
                        int i5 = -1;
                        for (int i6 = i; i6 < length3; i6++) {
                            StatsReport.Value value2 = valueArr3[i6];
                            if (value2.name.contains("audioOutputLevel")) {
                                try {
                                    i5 = Integer.valueOf(value2.value).intValue();
                                } catch (NumberFormatException e2) {
                                    Logging.m5304e("BaiduRtcRoomImp", "parse string num exception: " + e2);
                                }
                            }
                            if (value2.name.contains("ssrc")) {
                                str = value2.value;
                            }
                        }
                        if (!str.isEmpty() && i5 >= 0) {
                            String midBySsrc = this.peerConnectionClient.getMidBySsrc(this.mSubscriberHandle, str);
                            if (!midBySsrc.isEmpty() && (bigInteger3 = this.mWebSocketChannel.getFeedByMid(midBySsrc)) != null && (streamStats = this.mRemoteStreamStats.get(bigInteger3)) != null) {
                                streamStats.volume = i5;
                            }
                        }
                    }
                } else if (i4 != 0) {
                    for (StatsReport.Value value3 : statsReport.values) {
                        if (value3.name.contains("audioOutputLevel") && streamStats3 != null) {
                            try {
                                streamStats3.volume = Integer.valueOf(value3.value).intValue();
                            } catch (NumberFormatException e3) {
                                Logging.m5304e("BaiduRtcRoomImp", "parse string num exception: " + e3);
                            }
                        }
                    }
                }
            }
            i2++;
            statsReportArr2 = statsReportArr;
            i = 0;
        }
    }

    private void onPacketLossEvent(Map<String, Integer> map) {
        long intValue = map.get("packetloss_s") != null ? map.get("packetloss_s").intValue() : 0L;
        long intValue2 = map.get("packetlost") != null ? map.get("packetlost").intValue() : 0L;
        long intValue3 = map.get("packesend") != null ? map.get("packesend").intValue() : 0L;
        if (this.mBaiduRtcRoomDelegate != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packetsLost", intValue2);
                jSONObject.put("packetsSent", intValue3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mBaiduRtcRoomDelegate.onRoomEventUpdate(201, intValue, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAntiWeakInfo() {
        HUDStatistics hUDStatistics;
        if (this.mWebSocketChannel == null) {
            return;
        }
        HUDStatistics hUDStatistics2 = this.mHUDStatisticsMap.get(this.mPublisherHandle);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdInfo", hUDStatistics2.getSentBweInfoJson());
            jSONObject2.put("sysInfo", hUDStatistics2.getSysInfoJson());
            JSONArray jSONArray = new JSONArray();
            for (BigInteger bigInteger : this.mHUDStatisticsMap.keySet()) {
                if (!bigInteger.equals(this.mPublisherHandle) && (hUDStatistics = this.mHUDStatisticsMap.get(bigInteger)) != null) {
                    jSONArray.put(hUDStatistics.getRecvBweInfoJson(this.mWebSocketChannel.getFeedByHandle(bigInteger)));
                }
            }
            jSONObject2.put("recvInfo", jSONArray);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            if (this.mWebSocketChannel != null) {
                jSONObject.put("roomId", this.mWebSocketChannel.getRoomId());
            }
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject2);
        } catch (JSONException e) {
            Logging.m5304e("BaiduRtcRoomImp", "Caught error on reportAntiWeakInfo: " + e);
        }
        this.rtcLogReport.report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportCommunicationQualityInfo() {
        boolean z;
        boolean z2;
        HUDStatistics hUDStatistics;
        if (this.mWebSocketChannel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        HUDStatistics hUDStatistics2 = this.mHUDStatisticsMap.get(this.mPublisherHandle);
        if (hUDStatistics2 == null) {
            z = false;
        } else {
            hUDStatistics2.getStatsSendInfo(hashMap);
            z = true;
        }
        if (hashMap.size() == 0) {
            z = false;
        }
        onPacketLossEvent(hashMap);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("duration", 2);
            JSONObject jSONObject3 = new JSONObject();
            if (this.mCpuMonitor != null) {
                jSONObject3.put("sysCpuUsage", this.mCpuMonitor.getFrequencyScaleAverage());
            }
            jSONObject3.put("appCpuUsage", 0);
            jSONObject2.put("resourceUsageInfo", jSONObject3);
            if (this.mIsEnablePushQualityMonitor) {
                JSONObject jSONObject4 = new JSONObject();
                if (z) {
                    jSONObject4.put("bitrate", hashMap.get("bitrate_s"));
                    jSONObject4.put("abps", hashMap.get("bitrate_audio_s"));
                    jSONObject4.put("audioEnergy", hashMap.get("audio_energy_s"));
                    jSONObject4.put("audioLevel", hashMap.get("audio_input_level_s"));
                    jSONObject4.put("packetloss", hashMap.get("packetloss_s"));
                    jSONObject4.put("cfps", hashMap.get("fps_s"));
                    jSONObject4.put("fps", hashMap.get("fps_i"));
                    if (hUDStatistics2 == null) {
                        jSONObject4.put("resolution", "");
                    } else {
                        jSONObject4.put("resolution", hUDStatistics2.getSendResolution());
                    }
                } else {
                    jSONObject4.put("bitrate", 0);
                    jSONObject4.put("packetloss", 0);
                    jSONObject4.put("fps", 0);
                    jSONObject4.put("resolution", "");
                }
                jSONObject2.put("senderQualityInfo", jSONObject4);
                this.mBaiduRtcRoomDelegate.onEngineStatisticsInfo(this.mPublisherHandle, hUDStatistics2);
            }
            if (this.mIsEnablePullQualityMonitor) {
                JSONArray jSONArray = new JSONArray();
                for (BigInteger bigInteger : this.mHUDStatisticsMap.keySet()) {
                    if (this.mParamSettings.EnableMultistream) {
                        z2 = (bigInteger.equals(this.mPublisherHandle) || bigInteger.equals(this.mSubscriberHandle)) ? false : true;
                    } else {
                        z2 = !bigInteger.equals(this.mPublisherHandle);
                    }
                    if (z2 && (hUDStatistics = this.mHUDStatisticsMap.get(bigInteger)) != null) {
                        this.mBaiduRtcRoomDelegate.onEngineStatisticsInfo(bigInteger, hUDStatistics);
                        HashMap hashMap2 = new HashMap();
                        hUDStatistics.getStatsRecvInfo(hashMap2);
                        if (hashMap2.size() != 0) {
                            JSONObject jSONObject5 = new JSONObject();
                            if (this.mWebSocketChannel != null) {
                                if (this.mParamSettings.EnableMultistream) {
                                    jSONObject5.put("feedId", bigInteger);
                                } else {
                                    jSONObject5.put("feedId", this.mWebSocketChannel.getFeedByHandle(bigInteger));
                                }
                            }
                            jSONObject5.put("bitrate", hashMap2.get("bitrate_r"));
                            jSONObject5.put("abps", hashMap2.get("bitrate_audio_r"));
                            jSONObject5.put("audioEnergy", hashMap2.get("audio_energy_r"));
                            jSONObject5.put("audioLevel", hashMap2.get("audio_input_level_r"));
                            jSONObject5.put("packetloss", hashMap2.get("packetloss_r"));
                            jSONObject5.put("fps", hashMap2.get("fps_r"));
                            jSONObject5.put("resolution", hUDStatistics.getRecvResolution());
                            jSONArray.put(jSONObject5);
                        }
                    }
                }
                jSONObject2.put("receiverQualityInfoList", jSONArray);
            }
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("communicationQualityInfo", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            if (this.mWebSocketChannel != null) {
                jSONObject.put("roomId", this.mWebSocketChannel.getRoomId());
            }
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject6);
        } catch (JSONException e) {
            Logging.m5304e("BaiduRtcRoomImp", "Caught error on reportCommunicationQualityInfo: " + e);
        }
        this.rtcLogReport.report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c3 A[Catch: JSONException -> 0x0146, LOOP:2: B:43:0x00bd->B:45:0x00c3, LOOP_END, TryCatch #0 {JSONException -> 0x0146, blocks: (B:24:0x0053, B:26:0x0063, B:28:0x0074, B:30:0x0090, B:34:0x0098, B:36:0x009e, B:38:0x00ab, B:40:0x00b6, B:43:0x00bd, B:45:0x00c3, B:47:0x00d0, B:49:0x00fd, B:50:0x010b, B:52:0x0123, B:53:0x012e, B:46:0x00cd, B:37:0x00a8, B:27:0x0069), top: B:76:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fd A[Catch: JSONException -> 0x0146, TryCatch #0 {JSONException -> 0x0146, blocks: (B:24:0x0053, B:26:0x0063, B:28:0x0074, B:30:0x0090, B:34:0x0098, B:36:0x009e, B:38:0x00ab, B:40:0x00b6, B:43:0x00bd, B:45:0x00c3, B:47:0x00d0, B:49:0x00fd, B:50:0x010b, B:52:0x0123, B:53:0x012e, B:46:0x00cd, B:37:0x00a8, B:27:0x0069), top: B:76:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0123 A[Catch: JSONException -> 0x0146, TryCatch #0 {JSONException -> 0x0146, blocks: (B:24:0x0053, B:26:0x0063, B:28:0x0074, B:30:0x0090, B:34:0x0098, B:36:0x009e, B:38:0x00ab, B:40:0x00b6, B:43:0x00bd, B:45:0x00c3, B:47:0x00d0, B:49:0x00fd, B:50:0x010b, B:52:0x0123, B:53:0x012e, B:46:0x00cd, B:37:0x00a8, B:27:0x0069), top: B:76:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0176 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x018a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0013 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reportSLIStuckInfo() {
        /*
            Method dump skipped, instructions count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.rtc.internal.BaiduRtcRoomImp.reportSLIStuckInfo():void");
    }

    private void reportSLILoginEvent(String str, long j) {
        if (this.mWebSocketChannel == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            new JSONObject();
            jSONObject2.put("feedId", 0);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("type", str);
            jSONObject2.put("loginEvent", jSONObject4);
            jSONObject3.put("sli", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            if (j > 0) {
                jSONObject.put("roomId", j);
            }
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject3);
        } catch (JSONException e) {
            Logging.m5304e("BaiduRtcRoomImp", "Caught error on reportDeviceInfo: " + e);
        }
        this.rtcLogReport.report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 3);
    }

    private void reportSLILoginEvent(String str) {
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        reportSLILoginEvent(str, webSocketChannel != null ? webSocketChannel.getRoomId() : -1L);
    }

    private void reportSLIFfDelay(long j, long j2) {
        if (this.mWebSocketChannel == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject2.put("feedId", j);
            jSONObject4.put("duration", j2);
            jSONObject2.put("ffDelay", jSONObject4);
            jSONObject3.put("sli", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            if (this.mWebSocketChannel != null) {
                jSONObject.put("roomId", this.mWebSocketChannel.getRoomId());
            }
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject3);
        } catch (JSONException e) {
            Logging.m5304e("BaiduRtcRoomImp", "Caught error on reportDeviceInfo: " + e);
        }
        this.rtcLogReport.report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 3);
        if (!this.mIsEnableErrorInfoMonitor || j2 <= 3000) {
            return;
        }
        if (this.mParamSettings.EnableMultistream) {
            if (this.mWebSocketChannel != null) {
                ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.BAD_FIRST_FRAME_TIME, j2, this.mSubscriberHandle, BigInteger.valueOf(j));
                return;
            }
            return;
        }
        BigInteger valueOf = BigInteger.valueOf(0L);
        WebSocketChannel webSocketChannel = this.mWebSocketChannel;
        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.BAD_FIRST_FRAME_TIME, j2, (webSocketChannel == null || webSocketChannel.getHandleByFeed(BigInteger.valueOf(j)) == null) ? valueOf : this.mWebSocketChannel.getHandleByFeed(BigInteger.valueOf(j)).handleId, BigInteger.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportDeviceInfo() {
        if (this.mWebSocketChannel == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkVersion", Constraints.sdkVersion());
            jSONObject2.put("networkType", RtcLogReport.getNetworkType(this.mContext.get()));
            jSONObject2.put("device", RtcLogReport.getDeviceModel());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("deviceInfo", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            if (this.mWebSocketChannel != null) {
                jSONObject.put("roomId", this.mWebSocketChannel.getRoomId());
            }
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject3);
        } catch (JSONException e) {
            Logging.m5304e("BaiduRtcRoomImp", "Caught error on reportDeviceInfo: " + e);
        }
        this.rtcLogReport.report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportRoomEventInfo(String str) {
        if ((this.mIsEnablePushQualityMonitor || this.mIsEnablePullQualityMonitor) && this.mWebSocketChannel != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("userName", this.mDisplayName);
                jSONObject2.put("eventDescription", str);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("roomEvent", jSONObject2);
                jSONObject.put("env", this.mQualityMonitorEnv);
                jSONObject.put("appId", this.mAppId);
                if (this.mWebSocketChannel != null) {
                    jSONObject.put("roomId", this.mWebSocketChannel.getRoomId());
                }
                jSONObject.put("timestamp", System.currentTimeMillis());
                jSONObject.put("userId", this.mUserId);
                jSONObject.put("message", jSONObject3);
            } catch (JSONException e) {
                Logging.m5304e("BaiduRtcRoomImp", "Caught error on reportRoomEventInfo: " + e);
            }
            this.rtcLogReport.report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioStuckEvent implements SLIReportInterface {
        private AudioStuckEvent() {
        }

        @Override // com.baidu.rtc.logreport.SLIReportInterface
        public void onStuckData(long j, long j2) {
            if (BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor) {
                for (Map.Entry entry : BaiduRtcRoomImp.this.mHUDStatisticsMap.entrySet()) {
                    boolean z = true;
                    if (BaiduRtcRoomImp.this.mParamSettings.EnableMultistream) {
                        if (((BigInteger) entry.getKey()).equals(BaiduRtcRoomImp.this.mPublisherHandle) || ((BigInteger) entry.getKey()).equals(BaiduRtcRoomImp.this.mSubscriberHandle)) {
                            z = false;
                        }
                    } else {
                        z = true ^ ((BigInteger) entry.getKey()).equals(BaiduRtcRoomImp.this.mPublisherHandle);
                    }
                    if (z && entry.getValue() != null) {
                        ((HUDStatistics) entry.getValue()).addAudioStuckData(j, j2);
                        if (BaiduRtcRoomImp.this.mIsEnableErrorInfoMonitor) {
                            long j3 = j2 - j;
                            if (j3 > 200) {
                                BigInteger valueOf = BigInteger.valueOf(0L);
                                if (!BaiduRtcRoomImp.this.mParamSettings.EnableMultistream) {
                                    if (BaiduRtcRoomImp.this.mWebSocketChannel != null) {
                                        valueOf = BaiduRtcRoomImp.this.mWebSocketChannel.getFeedByHandle((BigInteger) entry.getKey());
                                    }
                                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_STUCK, j3, (BigInteger) entry.getKey(), valueOf);
                                } else {
                                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_STUCK, j3, BaiduRtcRoomImp.this.mSubscriberHandle, (BigInteger) entry.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class VideoStuckEvent implements SLIReportInterface {
        private BigInteger handleId;

        VideoStuckEvent(BigInteger bigInteger) {
            this.handleId = bigInteger;
        }

        @Override // com.baidu.rtc.logreport.SLIReportInterface
        public void onStuckData(long j, long j2) {
            HUDStatistics hUDStatistics;
            if (!BaiduRtcRoomImp.this.mIsEnablePullQualityMonitor || (hUDStatistics = (HUDStatistics) BaiduRtcRoomImp.this.mHUDStatisticsMap.get(this.handleId)) == null) {
                return;
            }
            hUDStatistics.addVideoStuckData(j, j2);
            if (BaiduRtcRoomImp.this.mIsEnableErrorInfoMonitor) {
                long j3 = j2 - j;
                if (j3 > 600) {
                    BigInteger valueOf = BigInteger.valueOf(0L);
                    if (BaiduRtcRoomImp.this.mParamSettings.EnableMultistream) {
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.VIDEO_STUCK, j3, BaiduRtcRoomImp.this.mSubscriberHandle, this.handleId);
                    } else {
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.VIDEO_STUCK, j3, this.handleId, BaiduRtcRoomImp.this.mWebSocketChannel != null ? BaiduRtcRoomImp.this.mWebSocketChannel.getFeedByHandle(this.handleId) : valueOf);
                    }
                }
            }
        }
    }

    private void runOnMainThread(Runnable runnable) {
        Handler handler = this.mHandler;
        if (handler != null) {
            if (handler.getLooper() == Looper.myLooper()) {
                runnable.run();
                return;
            } else {
                handler.post(runnable);
                return;
            }
        }
        runnable.run();
    }
}
