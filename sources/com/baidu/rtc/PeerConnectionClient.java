package com.baidu.rtc;

import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.CommonDefine;
import com.baidu.rtc.RtcParameterSettings;
import com.baidu.rtc.SdpPrefer;
import com.baidu.rtc.camera.CameraCapturer;
import com.baidu.rtc.config.BRTCScreenShareParams;
import com.baidu.rtc.internal.BaiduRtcRoomImp;
import com.baidu.rtc.logreport.ErrorInfoReport;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.baidu.rtc.logreport.SLIReportInterface;
import com.baidu.rtc.recorder.RtcAudioRecordListener;
import com.webrtc.AudioSource;
import com.webrtc.AudioTrack;
import com.webrtc.CalledByNative;
import com.webrtc.CandidatePairChangeEvent;
import com.webrtc.CapturerObserver;
import com.webrtc.DataChannel;
import com.webrtc.DefaultVideoDecoderFactory;
import com.webrtc.DefaultVideoEncoderFactory;
import com.webrtc.EglBase;
import com.webrtc.IceCandidate;
import com.webrtc.Loggable;
import com.webrtc.Logging;
import com.webrtc.MediaConstraints;
import com.webrtc.MediaStream;
import com.webrtc.MediaStreamTrack;
import com.webrtc.PeerConnection;
import com.webrtc.PeerConnectionFactory;
import com.webrtc.RtpParameters;
import com.webrtc.RtpReceiver;
import com.webrtc.RtpSender;
import com.webrtc.RtpTransceiver;
import com.webrtc.SdpObserver;
import com.webrtc.SessionDescription;
import com.webrtc.SoftwareVideoDecoderFactory;
import com.webrtc.SoftwareVideoEncoderFactory;
import com.webrtc.StatsObserver;
import com.webrtc.StatsReport;
import com.webrtc.SurfaceTextureHelper;
import com.webrtc.VideoCapturer;
import com.webrtc.VideoDecoder;
import com.webrtc.VideoDecoderFactory;
import com.webrtc.VideoEncoderFactory;
import com.webrtc.VideoProcessor;
import com.webrtc.VideoSink;
import com.webrtc.VideoSource;
import com.webrtc.VideoTrack;
import com.webrtc.audio.AudioDeviceModule;
import com.webrtc.audio.JavaAudioDeviceModule;
import com.webrtc.voiceengine.WebRtcAudioManager;
import com.webrtc.voiceengine.WebRtcAudioUtils;
import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PeerConnectionClient implements DataChannel.Observer {
    private static final String AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT = "googAutoGainControl";
    private static final String AUDIO_ECHO_CANCELLATION_CONSTRAINT = "googEchoCancellation";
    private static final String AUDIO_HIGH_PASS_FILTER_CONSTRAINT = "googHighpassFilter";
    private static final String AUDIO_NOISE_SUPPRESSION_CONSTRAINT = "googNoiseSuppression";
    public static final String AUDIO_TRACK_ID = "ARDAMSa0";
    private static final int BPS_IN_KBPS = 1000;
    private static final String DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT = "DtlsSrtpKeyAgreement";
    private static final int HD_VIDEO_HEIGHT = 720;
    private static final int HD_VIDEO_WIDTH = 1280;
    private static final String TAG = "PCRTCClient";
    private static final String VIDEO_CODEC_H264 = "H264";
    private static final String VIDEO_CODEC_H264_HIGH = "H264 High";
    private static final String VIDEO_CODEC_VP8 = "VP8";
    private static final String VIDEO_CODEC_VP9 = "VP9";
    public static final String VIDEO_SHARE_SCREEN_TRACK_ID = "ARDAMSv1";
    public static final String VIDEO_TRACK_ID = "ARDAMSv0";
    public static final String VIDEO_TRACK_TYPE = "video";
    private static final PeerConnectionClient instance = null;
    private ParcelFileDescriptor aecDumpFileDescriptor;
    private MediaConstraints answer_sdpMediaConstraints;
    private MediaConstraints audioConstraints;
    private AudioSource audioSource;
    private Context context;
    private boolean enableAudio;
    private PeerConnectionEvents events;
    private JavaAudioDeviceModule.ExternalSamplesReadyCallback externalSamplesCallback;
    private PeerConnectionFactory factory;
    private JavaAudioDeviceModule.SamplesReadyCallback innerAecSamplesReadyCallback;
    private JavaAudioDeviceModule.SamplesReadyCallback innersamplesReadyCallback;
    private boolean isError;
    private AudioTrack localAudioTrack;
    private VideoSink localRender;
    private VideoSink localScreenRender;
    private SurfaceTextureHelper localTextureHelper;
    private RtpSender localVideoSender;
    private VideoTrack localVideoTrack;
    private boolean mEnableAudioEffect;
    private boolean mExternalAudioRecord;
    private Loggable mLoggable;
    private Logging.Severity mLoggableSeverity;
    private JavaAudioDeviceModule.RemoteSamplesReadyCallback mRemoteSamplesReadyCallback;
    @BaiduRtcRoomImp.STATE_VIDEO_CAPTURER
    private int mVideoCapturerState;
    private MediaStream mediaStream;
    private MediaConstraints pcConstraints;
    private PeerConnectionParameters peerConnectionParameters;
    private String preferredVideoCodec;
    private AudioTrack remoteAudioTrack;
    private VideoTrack remoteVideoTrack;
    private boolean renderVideo;
    private EglBase rootEglBase;
    private ScreenCapturerAndroid screenCapturer;
    private MediaConstraints sdpMediaConstraints;
    private VideoSource shareScreenSource;
    private SurfaceTextureHelper shareScreenTextureHelper;
    private VideoTrack shareScreenTrack;
    private Timer statsTimer;
    private VideoCapturer videoCapturer;
    private boolean videoCapturerStopped;
    private int videoFps;
    private int videoHeight;
    private VideoProcessor videoProcessor;
    private VideoSource videoSource;
    private int videoWidth;
    private int videokbps;
    PeerConnectionFactory.Options options = null;
    private boolean screenCaptureStopped = true;
    private Map<BigInteger, TimerTask> timerTaskGetVolumeMap = new ConcurrentHashMap();
    private Map<BigInteger, TimerTask> timerTaskGetQualityMap = new ConcurrentHashMap();
    private Map<BigInteger, TimerTask> timerTaskGetSLIMap = new ConcurrentHashMap();
    private Map<BigInteger, TimerTask> timerTaskAntiWeakMap = new ConcurrentHashMap();
    private RtpSender shareScreenSender = null;
    private boolean mHasVideo = true;
    private boolean mHasAudio = true;
    private boolean mHasData = false;
    private int mAudioFreguency = 48000;
    private int mInputAudioChannel = 1;
    private int mOutputAudioChannle = 1;
    private boolean isEnableFEC = true;
    private DataChannel dcPublisher = null;
    Map<BigInteger, Integer> remoteLevels = new ConcurrentHashMap();
    private SLIReportInterface mStuckEvent = null;
    private boolean mbEnableDebugLog = false;
    private JavaAudioDeviceModule adm = null;
    private boolean mHasAudioSend = true;
    private boolean mHasVideoSend = true;
    private boolean mHasScreenSend = false;
    private boolean mHasDataSend = false;
    private boolean mHasAudioRecv = true;
    private boolean mHasVideoRecv = true;
    private boolean mHasDataRecv = false;
    private boolean mIsPeerConnectionFactoryReady = false;
    List<RtcAudioRecordListener> audioRecordListeners = new ArrayList();
    RtcAudioRecordListener audioRecordListener = new RtcAudioRecordListener() { // from class: com.baidu.rtc.PeerConnectionClient.1
        @Override // com.baidu.rtc.recorder.RtcAudioRecordListener
        public void onAudioRecord(byte[] bArr, int i) {
            for (RtcAudioRecordListener rtcAudioRecordListener : PeerConnectionClient.this.audioRecordListeners) {
                rtcAudioRecordListener.onAudioRecord(bArr, i);
            }
        }
    };
    PeerConnectionInit publisherPeerConnection = null;
    PeerConnectionInit subscribePeerConnection = null;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService executorSubscribe = Executors.newSingleThreadScheduledExecutor();
    private ConcurrentHashMap<BigInteger, JanusConnection> peerConnectionMap = new ConcurrentHashMap<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface PeerConnectionEvents {
        void onAudioRecordStartError(int i, String str);

        void onIceCandidate(IceCandidate iceCandidate, BigInteger bigInteger);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceConnected();

        void onIceDisconnected();

        void onIceFailed();

        void onLocalDescription(SessionDescription sessionDescription, BigInteger bigInteger, boolean z);

        void onPeerClosed(BigInteger bigInteger);

        void onPeerConnectionClosed();

        void onPeerConnectionError(String str);

        void onPeerConnectionStatsReady(StatsReport[] statsReportArr, BigInteger bigInteger, StatsEventsType statsEventsType);

        void onRemoteData(ByteBuffer byteBuffer);

        void onRemoteDescription(SessionDescription sessionDescription, BigInteger bigInteger);

        void onRemoteRender(JanusConnection janusConnection, String str);

        void onRemoteStreamStats(Boolean bool, Boolean bool2, BigInteger bigInteger);

        void onRemoteStreamStats(String str, String str2);

        void onSEIRecv(ByteBuffer byteBuffer);

        void onShareScreenStart(int i, String str);

        void onShareScreenStop(int i, String str);

        void onSubscribSetLocalDescriptionSuccess(BigInteger bigInteger);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum StatsEventsType {
        GET_AUDIOLEVEL_EVENT,
        GET_QUALITY_MONITOR_EVENT,
        GET_BWE_EVENT,
        GET_SLI_EVENT,
        GET_ANTI_WEAK_EVENT
    }

    @Override // com.webrtc.DataChannel.Observer
    public void onBufferedAmountChange(long j) {
    }

    @Override // com.webrtc.DataChannel.Observer
    public void onStateChange() {
    }

    public void setAudioStreamState(boolean z) {
    }

    public void setIsPeerConnectionFactoryReady(boolean z) {
        this.mIsPeerConnectionFactoryReady = z;
    }

    public boolean isIsPeerConnectionFactoryReady() {
        return this.mIsPeerConnectionFactoryReady;
    }

    public void setHasAudioSend(boolean z) {
        this.mHasAudioSend = z;
    }

    public void setHasVideoSend(boolean z) {
        this.mHasVideoSend = z;
    }

    public void setHasScreenSend(boolean z) {
        this.mHasScreenSend = z;
    }

    public void setHasDataSend(boolean z) {
        this.mHasDataSend = z;
    }

    public void setHasAudioRecv(boolean z) {
        this.mHasAudioRecv = z;
    }

    public void setHasVideoRecv(boolean z) {
        this.mHasVideoRecv = z;
    }

    public void setHasDataRecv(boolean z) {
        this.mHasDataRecv = z;
    }

    public void setEnableDebugLog(boolean z) {
        this.mbEnableDebugLog = z;
    }

    public void setExternalAudioRecord(boolean z) {
        this.mExternalAudioRecord = z;
    }

    public void setEnableAudioEffect(boolean z) {
        this.mEnableAudioEffect = z;
    }

    public void setAudioFreguency(int i) {
        this.mAudioFreguency = i;
    }

    public void setInputAudioChannel(int i) {
        this.mInputAudioChannel = i;
    }

    public void setOutputAudioChannel(int i) {
        this.mOutputAudioChannle = i;
    }

    public void injectLoggable(Loggable loggable, Logging.Severity severity) {
        this.mLoggable = loggable;
        this.mLoggableSeverity = severity;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class PeerConnectionParameters {
        public RtcParameterSettings.RtcAudioBitrateMode audioBitrateMode;
        public final int audioBufferPackets;
        public final String audioCodec;
        public final int audioCodecComplex;
        public final int audioContentType;
        public final int audioMaxkbps;
        public final int audioPlayoutDelay;
        public final int audioSource;
        public final int audioStartBitrate;
        public final boolean cameraMuted;
        public final boolean enableAACCodec;
        public final boolean enableAutoPublish;
        public final boolean enableAutoSubscribe;
        public final boolean enableBdAEC;
        public final boolean enableBdAGC;
        public final boolean enableBdNS;
        public final boolean enableDtlsEncrypt;
        public final boolean enableFEC;
        public final boolean enableFixedResolution;
        public final boolean enableHardwareAEC;
        public final boolean enableHardwareNS;
        public final boolean enableHighProfile;
        public final boolean enableHisiH264HW;
        public final boolean enableJitterRetransmission;
        public final boolean enableMTKH264Decode;
        public boolean enableMultistream;
        public final boolean enableRequiredResolutionAligment32;
        public final boolean enableWebRtcAEC;
        public final boolean enableWebRtcAGC;
        public final boolean enableWebRtcNS;
        public final int encodeBitrateMode;
        public final boolean micPhoneMuted;
        public final boolean noAudioProcessing;
        public BRTCScreenShareParams screenShareParams;
        public final boolean tracing;
        public RtcParameterSettings.RtcAudioChannel transportAudioChannel;
        public final boolean useOpenSLES;
        public final String videoCodec;
        public final boolean videoCodecHwAcceleration;
        public final int videoFps;
        public final int videoHeight;
        public final int videoMaxkbps;
        public final int videoMinkbps;
        public final int videoWidth;
        public final int weakNetworkPolicy;
        public BaiduRtcRoom.KeyAgreementProtocol keyAgreementProtocol = BaiduRtcRoom.KeyAgreementProtocol.BRTC_DLTS;
        public boolean forceSoftwareEncoder = false;
        public boolean forceSoftwareDecoder = false;
        public boolean enableAECRecordData = false;
        public boolean enableLowLatencyMode = false;
        public boolean disableDropFrame = false;
        public boolean enableReportAudioLevel = true;

        public PeerConnectionParameters(boolean z, int i, int i2, int i3, String str, boolean z2, int i4, String str2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, int i5, int i6, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, int i7, RtcParameterSettings.RtcAudioBitrateMode rtcAudioBitrateMode, RtcParameterSettings.RtcAudioChannel rtcAudioChannel, int i8, boolean z20, boolean z21, int i9, int i10, int i11, int i12, boolean z22, int i13, int i14, boolean z23, boolean z24, boolean z25) {
            this.tracing = z;
            this.videoWidth = i;
            this.videoHeight = i2;
            this.videoFps = i3;
            this.videoCodec = str;
            this.videoCodecHwAcceleration = z2;
            this.audioStartBitrate = i4;
            this.audioCodec = str2;
            this.noAudioProcessing = z3;
            this.useOpenSLES = z4;
            this.enableWebRtcAEC = z5;
            this.enableWebRtcAGC = z6;
            this.enableWebRtcNS = z7;
            this.enableBdAEC = z8;
            this.enableBdAGC = z9;
            this.enableBdNS = z10;
            this.enableHardwareAEC = z11;
            this.enableHardwareNS = z12;
            this.videoMaxkbps = i5;
            this.videoMinkbps = i6;
            this.micPhoneMuted = z13;
            this.cameraMuted = z14;
            this.enableFEC = z15;
            this.enableFixedResolution = z16;
            this.enableJitterRetransmission = z17;
            this.enableRequiredResolutionAligment32 = z18;
            this.enableHighProfile = z19;
            this.audioMaxkbps = i7;
            this.audioBitrateMode = rtcAudioBitrateMode;
            this.transportAudioChannel = rtcAudioChannel;
            this.encodeBitrateMode = i8;
            this.enableHisiH264HW = z20;
            this.enableMTKH264Decode = z21;
            this.audioSource = i9;
            this.audioBufferPackets = i10;
            this.audioPlayoutDelay = i11;
            this.audioCodecComplex = i12;
            this.enableAACCodec = z22;
            this.audioContentType = i13;
            this.enableDtlsEncrypt = z23;
            this.weakNetworkPolicy = i14;
            this.enableAutoPublish = z24;
            this.enableAutoSubscribe = z25;
        }

        public void setEnableMultistream(boolean z) {
            this.enableMultistream = z;
        }

        public void setForceSoftwareEncoder(boolean z) {
            this.forceSoftwareEncoder = z;
        }

        public void setForceSoftwareDecoder(boolean z) {
            this.forceSoftwareDecoder = z;
        }

        public void setEnableLowLatencyMode(boolean z) {
            this.enableLowLatencyMode = z;
        }

        public void setEnableAECARecordData(boolean z) {
            this.enableAECRecordData = z;
        }

        public void setDisableDropFrame(boolean z) {
            this.disableDropFrame = z;
        }

        public void setEnableReportAudioLevel(boolean z) {
            this.enableReportAudioLevel = z;
        }

        public void setKeyAgreementProtocol(BaiduRtcRoom.KeyAgreementProtocol keyAgreementProtocol) {
            this.keyAgreementProtocol = keyAgreementProtocol;
        }
    }

    private PeerConnectionClient() {
    }

    public static synchronized PeerConnectionClient getInstance() {
        PeerConnectionClient peerConnectionClient;
        synchronized (PeerConnectionClient.class) {
            peerConnectionClient = new PeerConnectionClient();
        }
        return peerConnectionClient;
    }

    public void setPeerConnectionFactoryOptions(PeerConnectionFactory.Options options) {
        this.options = options;
    }

    public void createPeerConnectionFactory(final Context context, EglBase eglBase, PeerConnectionParameters peerConnectionParameters, PeerConnectionEvents peerConnectionEvents) {
        this.peerConnectionParameters = peerConnectionParameters;
        this.events = peerConnectionEvents;
        this.context = null;
        this.rootEglBase = eglBase;
        this.factory = null;
        this.videoCapturerStopped = false;
        this.isError = false;
        this.mediaStream = null;
        this.videoCapturer = null;
        this.renderVideo = !peerConnectionParameters.cameraMuted;
        this.localVideoTrack = null;
        this.remoteVideoTrack = null;
        this.localVideoSender = null;
        this.enableAudio = !peerConnectionParameters.micPhoneMuted;
        this.localAudioTrack = null;
        this.remoteAudioTrack = null;
        this.statsTimer = new Timer();
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
            return;
        }
        RtcLogCapturer.reportLog(RtcLogEvent.CREATE_PEERCONNECTION_FACTORY_START, "createPeerConnectionFactory");
        this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.2
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionClient.this.createPeerConnectionFactoryInternal(context);
            }
        });
    }

    public void createPeerConnectionExternal(final EglBase.Context context, VideoSink videoSink, VideoSink videoSink2, VideoCapturer videoCapturer, ScreenCapturerAndroid screenCapturerAndroid, final BigInteger bigInteger) {
        if (this.peerConnectionParameters == null) {
            Logging.m5304e("PCRTCClient", "Creating peer connection without initializing factory.");
            return;
        }
        this.localRender = videoSink;
        this.localScreenRender = videoSink2;
        this.videoCapturer = videoCapturer;
        this.screenCapturer = screenCapturerAndroid;
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PeerConnectionClient.this.createMediaConstraintsInternal();
                        PeerConnectionClient.this.createPeerConnectionInternal(context, bigInteger);
                    } catch (Exception e) {
                        e.printStackTrace();
                        PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                        peerConnectionClient.reportError("Failed to create peer connection: " + e.getMessage());
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.PEERCONNECTION_CREATE_ERROR);
                        throw e;
                    }
                }
            });
        }
    }

    public void setVideoCapturerState(@BaiduRtcRoomImp.STATE_VIDEO_CAPTURER int i) {
        this.mVideoCapturerState = i;
    }

    public void createPeerConnection(final EglBase.Context context, VideoSink videoSink, VideoSink videoSink2, VideoCapturer videoCapturer, ScreenCapturerAndroid screenCapturerAndroid, VideoProcessor videoProcessor, final BigInteger bigInteger) {
        if (this.peerConnectionParameters == null) {
            Logging.m5304e("PCRTCClient", "Creating peer connection without initializing factory.");
            return;
        }
        this.localRender = videoSink;
        this.localScreenRender = videoSink2;
        this.videoCapturer = videoCapturer;
        this.screenCapturer = screenCapturerAndroid;
        this.videoProcessor = videoProcessor;
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PeerConnectionClient.this.createMediaConstraintsInternal();
                        PeerConnectionClient.this.createPeerConnectionInternal(context, bigInteger);
                    } catch (Exception e) {
                        e.printStackTrace();
                        PeerConnectionClient peerConnectionClient = PeerConnectionClient.this;
                        peerConnectionClient.reportError("Failed to create peer connection: " + e.getMessage());
                        RtcLogCapturer.reportLog(RtcLogEvent.CREATE_PUBLISHER_PEERCONNECTION_FAILED, "createPeerConnection-catch", -1, RtcLogCapturer.getErrorInfo(e.getMessage()));
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.PEERCONNECTION_CREATE_ERROR);
                        throw e;
                    }
                }
            });
        }
    }

    public void addRemoteRender(VideoSink videoSink) {
        VideoTrack videoTrack;
        StringBuilder sb = new StringBuilder();
        sb.append("client addRemoteRender  ");
        sb.append(videoSink.hashCode());
        sb.append(" remoteVideoTrack is null = ");
        sb.append(this.remoteVideoTrack == null);
        Logging.m5305d("PCRTCClient", sb.toString());
        if (videoSink == null || (videoTrack = this.remoteVideoTrack) == null) {
            return;
        }
        videoTrack.addSink(videoSink);
    }

    public void removeRemoteRender(RTCVideoView rTCVideoView) {
        VideoTrack videoTrack;
        if (rTCVideoView == null || (videoTrack = this.remoteVideoTrack) == null) {
            return;
        }
        videoTrack.removeSink(rTCVideoView);
    }

    public void close() {
        try {
            disableAllStatsEvents();
            if (this.statsTimer != null) {
                this.statsTimer.cancel();
                this.statsTimer = null;
            }
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.5
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.closeInternal();
                }
            });
            this.executor.shutdown();
            this.executor.awaitTermination(200L, TimeUnit.MILLISECONDS);
            this.executorSubscribe.shutdown();
            this.executorSubscribe.awaitTermination(200L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Logging.m5305d("PCRTCClient", "pc close interrupted exception:" + e.getMessage());
        } catch (Exception e2) {
            Logging.m5305d("PCRTCClient", "pc close exception:" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPeerConnectionFactoryInternal(Context context) {
        boolean z;
        VideoEncoderFactory softwareVideoEncoderFactory;
        VideoDecoderFactory softwareVideoDecoderFactory;
        EglBase eglBase;
        if (this.peerConnectionParameters.tracing) {
            PeerConnectionFactory.startInternalTracingCapture(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "webrtc-trace.txt");
        }
        Logging.m5305d("PCRTCClient", "Create peer connection factory. Use video: true");
        this.isError = false;
        this.preferredVideoCodec = "VP8";
        if (this.peerConnectionParameters.videoCodec != null) {
            if (this.peerConnectionParameters.videoCodec.equals("VP9")) {
                this.preferredVideoCodec = "VP9";
            } else if (this.peerConnectionParameters.videoCodec.equals("H264")) {
                this.preferredVideoCodec = "H264";
            }
        }
        Logging.m5305d("PCRTCClient", "Pereferred video codec: " + this.preferredVideoCodec);
        if (!this.peerConnectionParameters.useOpenSLES) {
            Logging.m5305d("PCRTCClient", "Disable OpenSL ES audio even if device supports it");
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(true);
        } else {
            Logging.m5305d("PCRTCClient", "Allow OpenSL ES audio if device supports it");
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(false);
        }
        if (this.peerConnectionParameters.enableWebRtcAEC) {
            Logging.m5305d("PCRTCClient", "Disable built-in AEC even if device supports it");
            WebRtcAudioUtils.setWebRtcBasedAcousticEchoCanceler(true);
        } else {
            Logging.m5305d("PCRTCClient", "Enable built-in AEC if device supports it");
            WebRtcAudioUtils.setWebRtcBasedAcousticEchoCanceler(false);
        }
        if (this.peerConnectionParameters.enableWebRtcAGC) {
            Logging.m5305d("PCRTCClient", "Disable built-in AGC even if device supports it");
            WebRtcAudioUtils.setWebRtcBasedAutomaticGainControl(true);
        } else {
            Logging.m5305d("PCRTCClient", "Enable built-in AGC if device supports it");
            WebRtcAudioUtils.setWebRtcBasedAutomaticGainControl(false);
        }
        if (this.peerConnectionParameters.enableWebRtcNS) {
            Logging.m5305d("PCRTCClient", "Disable built-in NS even if device supports it");
            WebRtcAudioUtils.setWebRtcBasedNoiseSuppressor(true);
        } else {
            Logging.m5305d("PCRTCClient", "Enable built-in NS if device supports it");
            WebRtcAudioUtils.setWebRtcBasedNoiseSuppressor(false);
        }
        this.context = context;
        this.answer_sdpMediaConstraints = new MediaConstraints();
        this.answer_sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        this.answer_sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        String str = this.peerConnectionParameters.enableFEC ? "WebRTC-FlexFEC-03-Advertised/Enabled/WebRTC-FlexFEC-03/Enabled/" : "";
        if (this.peerConnectionParameters.enableFixedResolution) {
            str = str + "BRTC.Fixed.Resolution/Enabled/";
        }
        if (this.peerConnectionParameters.encodeBitrateMode == 0 || this.peerConnectionParameters.encodeBitrateMode == 1 || this.peerConnectionParameters.encodeBitrateMode == 2) {
            str = str + "BRTC-Encoder-BitrateMode/" + this.peerConnectionParameters.encodeBitrateMode + "/";
        }
        if (this.peerConnectionParameters.enableJitterRetransmission) {
            str = str + "BRTC.Jitter.Retransmission/Enabled/";
        }
        if (this.peerConnectionParameters.enableHighProfile) {
            str = (str + "WebRTC-H264HighProfile/Enabled/") + "WebRTC-MediaTekH264/Enabled/";
        }
        if (this.peerConnectionParameters.enableHisiH264HW) {
            str = str + "BRTC.HisiH264HW/Enabled/";
        }
        if (this.peerConnectionParameters.enableMTKH264Decode) {
            str = str + "BRTC.MTK.H264.Decode/Enabled/";
        }
        if (this.peerConnectionParameters.enableAECRecordData) {
            str = str + "BRTC.AECRecordData/Enabled/";
        }
        if (this.peerConnectionParameters.videoWidth == 176 && this.peerConnectionParameters.videoHeight == 144 && this.peerConnectionParameters.videoCodec.equalsIgnoreCase("H263")) {
            str = str + "BRTC.ForceCaptureScale/2/";
        }
        if (this.peerConnectionParameters.enableFixedResolution) {
            str = ((str + "BRTC.ForceVideoWidth/" + this.peerConnectionParameters.videoWidth + "/") + "BRTC.ForceVideoHeight/" + this.peerConnectionParameters.videoHeight + "/") + "BRTC.ForceFps/" + this.peerConnectionParameters.videoFps + "/";
        }
        if (this.peerConnectionParameters.disableDropFrame) {
            str = str + "WebRTC-FrameDropper/Disabled/";
        }
        if (this.peerConnectionParameters.enableMTKH264Decode) {
            str = str + "BRTC.MTK.H264.Decode/Enabled/";
        }
        if (this.peerConnectionParameters.audioPlayoutDelay >= 0) {
            str = str + "BRTC.Playout.Delay.AudioOnly/" + this.peerConnectionParameters.audioPlayoutDelay + "/";
        }
        if (this.peerConnectionParameters.audioCodecComplex >= 0) {
            str = str + "BRTC.Opus.Complexity/" + this.peerConnectionParameters.audioCodecComplex + "/";
        }
        if (this.peerConnectionParameters.enableAACCodec) {
            str = str + "BRTC.Aac.Codec/Enabled/";
        }
        if (this.peerConnectionParameters.forceSoftwareDecoder) {
            str = str + "BRTC.ForceSoftwareDecoder/Enabled/";
        }
        if (this.peerConnectionParameters.forceSoftwareEncoder) {
            str = str + "BRTC.ForceSoftwareEncoder/Enabled/";
        }
        if (this.peerConnectionParameters.enableLowLatencyMode) {
            str = str + "BRTC.LowLatencyMode/Enabled/";
        }
        if (this.peerConnectionParameters.weakNetworkPolicy >= 0) {
            str = str + "BRTC.Network.Detection.Mode/" + this.peerConnectionParameters.weakNetworkPolicy + "/";
        }
        int i = 0;
        while (true) {
            if (i >= MediaCodecList.getCodecCount()) {
                z = false;
                break;
            }
            MediaCodecInfo mediaCodecInfo = null;
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.m5303e("PCRTCClient", "Cannot retrieve encoder codec info", e);
            }
            if (mediaCodecInfo != null && mediaCodecInfo.isEncoder() && mediaCodecInfo.getName().startsWith("OMX.hisi.")) {
                z = true;
                break;
            }
            i++;
        }
        if (this.peerConnectionParameters.enableRequiredResolutionAligment32 || z) {
            str = str + "BRTC.Required.Resolustion.Aligment32/Enabled/";
        }
        RtcLogCapturer.reportLog(RtcLogEvent.SET_PEERECONNECTION_FACTORY_PARAM, "createPeerConnectionFactoryInternal", Boolean.valueOf(this.isEnableFEC));
        long currentTimeMillis = System.currentTimeMillis();
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(context).setFieldTrials(str).setInjectableLogger(this.mLoggable, this.mLoggableSeverity).createInitializationOptions());
        Logging.m5305d("PCRTCClient", "peerconnection initialize@" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        AudioDeviceModule createJavaAudioDevice = createJavaAudioDevice();
        this.adm = (JavaAudioDeviceModule) createJavaAudioDevice;
        boolean equals = "H264 High".equals(this.peerConnectionParameters.videoCodec);
        if (this.peerConnectionParameters.videoCodecHwAcceleration && (eglBase = this.rootEglBase) != null) {
            softwareVideoEncoderFactory = new DefaultVideoEncoderFactory(eglBase.getEglBaseContext(), true, equals);
            softwareVideoDecoderFactory = new DefaultVideoDecoderFactory(this.rootEglBase.getEglBaseContext());
            ((DefaultVideoDecoderFactory) softwareVideoDecoderFactory).setVideoDecoderCallback(new VideoDecoderObserver(this.events));
        } else {
            softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();
            softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
        }
        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        options.disableEncryption = !this.peerConnectionParameters.enableDtlsEncrypt;
        long currentTimeMillis2 = System.currentTimeMillis();
        this.factory = PeerConnectionFactory.builder().setOptions(options).setAudioDeviceModule(createJavaAudioDevice).setVideoDecoderFactory(softwareVideoDecoderFactory).setVideoEncoderFactory(softwareVideoEncoderFactory).createPeerConnectionFactory();
        Logging.m5305d("PCRTCClient", "Peer connection factory created@" + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
        createJavaAudioDevice.release();
        if (this.mbEnableDebugLog) {
            Logging.enableLogThreads();
            Logging.enableLogTimeStamps();
            Logging.enableLogToDebugOutput(Logging.Severity.LS_VERBOSE);
        } else {
            Logging.enableLogToDebugOutput(Logging.Severity.LS_ERROR);
        }
        if (this.factory != null) {
            RtcLogCapturer.reportLog(RtcLogEvent.CREATE_PEERCONNECTION_FACTORY_SUCCESS, "createPeerConnectionFactoryInternal");
        } else {
            RtcLogCapturer.reportLog(RtcLogEvent.CREATE_PEERCONNECTION_FACTORY_FAILED, "createPeerConnectionFactoryInternal", -1, "create PeerConnectionFactory is null.");
        }
        if (this.peerConnectionParameters.enableAutoPublish) {
            this.publisherPeerConnection = new PeerConnectionInit(true);
        }
        if (this.peerConnectionParameters.enableAutoSubscribe) {
            this.subscribePeerConnection = new PeerConnectionInit(false);
        }
    }

    public void stopCaptureAudio() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.6
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.enableAudio = false;
                    if (PeerConnectionClient.this.localAudioTrack != null) {
                        PeerConnectionClient.this.localAudioTrack.setEnabled(false);
                    }
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.stopCaptureAudio();
                    }
                }
            });
        }
    }

    public void reStartCaptureAudio() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.7
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.enableAudio = true;
                    if (PeerConnectionClient.this.localAudioTrack != null) {
                        PeerConnectionClient.this.localAudioTrack.setEnabled(true);
                    }
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.reStartCaptureAudio();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class VideoDecoderObserver implements VideoDecoder.VideoDecoderCallback {
        PeerConnectionEvents events;

        public VideoDecoderObserver(PeerConnectionEvents peerConnectionEvents) {
            this.events = peerConnectionEvents;
        }

        @Override // com.webrtc.VideoDecoder.VideoDecoderCallback
        public void onSEIRecv(ByteBuffer byteBuffer) {
            PeerConnectionEvents peerConnectionEvents = this.events;
            if (peerConnectionEvents != null) {
                peerConnectionEvents.onSEIRecv(byteBuffer);
            }
        }
    }

    AudioDeviceModule createJavaAudioDevice() {
        AudioDeviceModule audioDeviceModule;
        try {
            audioDeviceModule = JavaAudioDeviceModule.builder(this.context).setSamplesReadyCallback(this.innersamplesReadyCallback).setAecSamplesReadyCallback(this.innerAecSamplesReadyCallback).setRemoteSamplesReadyCallback(this.mRemoteSamplesReadyCallback).setExternalAudioRecord(this.mExternalAudioRecord).setEnableAudioEffect(this.mEnableAudioEffect).setUseHardwareAcousticEchoCanceler(this.peerConnectionParameters.enableHardwareAEC).setUseHardwareNoiseSuppressor(this.peerConnectionParameters.enableHardwareNS).setAudioRecordErrorCallback(new JavaAudioDeviceModule.AudioRecordErrorCallback() { // from class: com.baidu.rtc.PeerConnectionClient.8
                @Override // com.webrtc.audio.JavaAudioDeviceModule.AudioRecordErrorCallback
                public void onWebRtcAudioRecordInitError(String str) {
                    Logging.m5304e("PCRTCClient", "onWebRtcAudioRecordInitError: " + str);
                    PeerConnectionClient.this.reportError(str);
                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.PEERCONNECTION_CREATE_ERROR);
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.AudioRecordErrorCallback
                public void onWebRtcAudioRecordStartError(JavaAudioDeviceModule.AudioRecordStartErrorCode audioRecordStartErrorCode, String str) {
                    Logging.m5304e("PCRTCClient", "onWebRtcAudioRecordStartError: " + audioRecordStartErrorCode + ". " + str);
                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_RECORD_START_ERROR);
                    if (PeerConnectionClient.this.events != null) {
                        PeerConnectionClient.this.events.onAudioRecordStartError(audioRecordStartErrorCode.ordinal(), str);
                    }
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.AudioRecordErrorCallback
                public void onWebRtcAudioRecordError(String str) {
                    Logging.m5304e("PCRTCClient", "onWebRtcAudioRecordError: " + str);
                    PeerConnectionClient.this.reportError(str);
                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_RECORD_ERROR);
                }
            }).setAudioTrackErrorCallback(new JavaAudioDeviceModule.AudioTrackErrorCallback() { // from class: com.baidu.rtc.PeerConnectionClient.9
                @Override // com.webrtc.audio.JavaAudioDeviceModule.AudioTrackErrorCallback
                public void onWebRtcAudioTrackInitError(String str) {
                    Logging.m5304e("PCRTCClient", "onWebRtcAudioTrackInitError: " + str);
                    PeerConnectionClient.this.reportError(str);
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.AudioTrackErrorCallback
                public void onWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode, String str) {
                    Logging.m5304e("PCRTCClient", "onWebRtcAudioTrackStartError: " + audioTrackStartErrorCode + ". " + str);
                    PeerConnectionClient.this.reportError(str);
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.AudioTrackErrorCallback
                public void onWebRtcAudioTrackError(String str) {
                    Logging.m5304e("PCRTCClient", "onWebRtcAudioTrackError: " + str);
                    PeerConnectionClient.this.reportError(str);
                }
            }).setSampleRate(this.mAudioFreguency).setUseStereoInput(this.mInputAudioChannel > 1).setUseStereoOutput(this.mOutputAudioChannle > 1).setAudioSource(this.peerConnectionParameters.audioSource).setAudioContentType(this.peerConnectionParameters.audioContentType).setAudioRecordListener(this.audioRecordListener).createAudioDeviceModule();
        } catch (Exception e) {
            e.printStackTrace();
            RtcLogCapturer.reportLog(RtcLogEvent.INIT_ADM_FAILED, "PCRTCClient-createJavaAudioDevice", RtcLogCapturer.getErrorInfo(e.getMessage()));
            audioDeviceModule = null;
        }
        if (audioDeviceModule != null) {
            RtcLogCapturer.reportLog(RtcLogEvent.INIT_ADM_SUCCESS, "PCRTCClient-createJavaAudioDevice");
        }
        if ((this.mEnableAudioEffect || this.mExternalAudioRecord) && (audioDeviceModule instanceof JavaAudioDeviceModule)) {
            this.externalSamplesCallback = ((JavaAudioDeviceModule) audioDeviceModule).getExternalSamplesReadyCallback();
        }
        return audioDeviceModule;
    }

    public JavaAudioDeviceModule.ExternalSamplesReadyCallback getExternalSamplesCallback() {
        return this.externalSamplesCallback;
    }

    public void setAudioSamplesReadyCallback(JavaAudioDeviceModule.SamplesReadyCallback samplesReadyCallback) {
        this.innersamplesReadyCallback = samplesReadyCallback;
    }

    public void setAudioAecSamplesReadyCallback(JavaAudioDeviceModule.SamplesReadyCallback samplesReadyCallback) {
        this.innerAecSamplesReadyCallback = samplesReadyCallback;
    }

    public void setRemoteAudioSamplesReadyCallback(final JavaAudioDeviceModule.RemoteSamplesReadyCallback remoteSamplesReadyCallback) {
        if (this.adm == null) {
            this.mRemoteSamplesReadyCallback = remoteSamplesReadyCallback;
        } else if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.10
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.setRemoteSamplesReadyCallback(remoteSamplesReadyCallback);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMediaConstraintsInternal() {
        this.pcConstraints = new MediaConstraints();
        this.pcConstraints.optional.add(new MediaConstraints.KeyValuePair("DtlsSrtpKeyAgreement", "true"));
        this.videoWidth = this.peerConnectionParameters.videoWidth;
        this.videoHeight = this.peerConnectionParameters.videoHeight;
        this.videoFps = this.peerConnectionParameters.videoFps;
        if (this.videoWidth == 176 && this.videoHeight == 144) {
            this.videoWidth = 352;
            this.videoHeight = 288;
        }
        if (this.videoWidth == 0 || this.videoHeight == 0) {
            this.videoWidth = 1280;
            this.videoHeight = 720;
        }
        if (this.videoFps == 0) {
            this.videoFps = 30;
        }
        Logging.m5305d("PCRTCClient", "Capturing format: " + this.videoWidth + "x" + this.videoHeight + "@" + this.videoFps);
        this.audioConstraints = new MediaConstraints();
        if (this.peerConnectionParameters.noAudioProcessing) {
            Logging.m5305d("PCRTCClient", "Disabling audio processing");
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googEchoCancellation", "false"));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googAutoGainControl", "false"));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googHighpassFilter", "false"));
            this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googNoiseSuppression", "false"));
        }
        this.sdpMediaConstraints = new MediaConstraints();
        if (this.mHasAudioRecv) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        }
        if (this.mHasVideoRecv) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        }
        if (this.mHasDataRecv) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("data", "true"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class PeerConnectionInit {
        PeerConnection.RTCConfiguration config;
        PeerConnection connection;
        PCObserver observer;

        PeerConnectionInit(boolean z) {
            ArrayList arrayList = new ArrayList();
            PeerConnection.IceServer.builder("stun:180.149.142.139:3478").createIceServer();
            this.config = new PeerConnection.RTCConfiguration(arrayList);
            if (PeerConnectionClient.this.peerConnectionParameters.enableMultistream) {
                this.config.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
            }
            if (!PeerConnectionClient.this.mHasAudioSend && !PeerConnectionClient.this.mHasVideoSend) {
                this.config.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
            }
            this.config.audioJitterBufferMaxPackets = PeerConnectionClient.this.peerConnectionParameters.audioBufferPackets;
            this.config.enableDtlsSrtp = Boolean.valueOf(PeerConnectionClient.this.peerConnectionParameters.enableDtlsEncrypt && PeerConnectionClient.this.peerConnectionParameters.keyAgreementProtocol == BaiduRtcRoom.KeyAgreementProtocol.BRTC_DLTS);
            this.observer = new PCObserver();
            long currentTimeMillis = System.currentTimeMillis();
            this.connection = PeerConnectionClient.this.factory.createPeerConnection(this.config, this.observer);
            Logging.m5305d("PCRTCClient", "factory.createPeerConnection[" + z + "]@" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PeerConnection createPeerConnection(BigInteger bigInteger, boolean z) {
        PeerConnectionInit peerConnectionInit;
        Logging.m5305d("PCRTCClient", "Create peer connection.");
        if (z) {
            peerConnectionInit = this.peerConnectionParameters.enableAutoPublish ? this.publisherPeerConnection : new PeerConnectionInit(z);
        } else {
            peerConnectionInit = this.peerConnectionParameters.enableAutoSubscribe ? this.subscribePeerConnection : new PeerConnectionInit(z);
        }
        if (z) {
            RtcLogCapturer.reportLog(RtcLogEvent.CREATE_PUBLISHER_PEERCONNECTION_START, "createPeerConnection", "true");
        }
        SDPObserver sDPObserver = new SDPObserver();
        JanusConnection janusConnection = new JanusConnection();
        janusConnection.handleId = bigInteger;
        janusConnection.sdpObserver = sDPObserver;
        janusConnection.pcObserver = peerConnectionInit.observer;
        janusConnection.peerConnection = peerConnectionInit.connection;
        janusConnection.type = z;
        if (!this.peerConnectionParameters.enableMultistream) {
            janusConnection.videoview_tag = -1;
        }
        janusConnection.connectionParam = this.peerConnectionParameters;
        this.peerConnectionMap.put(bigInteger, janusConnection);
        peerConnectionInit.observer.setConnection(janusConnection);
        sDPObserver.setConnection(janusConnection);
        DataChannel.Init init = new DataChannel.Init();
        if (this.mHasDataSend && z) {
            this.dcPublisher = peerConnectionInit.connection.createDataChannel("JanusDataChannel", init);
        }
        Logging.m5305d("PCRTCClient", "Peer connection created.");
        if (z) {
            RtcLogCapturer.reportLog(RtcLogEvent.CREATE_PUBLISHER_PEERCONNECTION_SUCCESS, "createPeerConnection", peerConnectionInit.config.sdpSemantics);
        } else {
            RtcLogCapturer.reportLog(RtcLogEvent.CREATE_SUBSCRIBER_PEERCONNECTION_SUCCESS, "createPeerConnection", peerConnectionInit.config.sdpSemantics);
        }
        return peerConnectionInit.connection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPeerConnectionInternal(EglBase.Context context, BigInteger bigInteger) {
        boolean z;
        if (this.factory == null || this.isError) {
            Logging.m5304e("PCRTCClient", "Peerconnection factory is not created");
            return;
        }
        setIsPeerConnectionFactoryReady(true);
        Logging.m5305d("PCRTCClient", "PCConstraints: " + this.pcConstraints.toString());
        Logging.m5305d("PCRTCClient", "EGLContext: " + context);
        PeerConnection createPeerConnection = createPeerConnection(bigInteger, true);
        boolean z2 = this.mHasAudioSend;
        if (!z2 && !(z = this.mHasVideoSend) && !this.mHasScreenSend) {
            if (z2 || z) {
                return;
            }
            createPeerConnection.addTransceiver(MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO, new RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.RECV_ONLY));
            createPeerConnection.addTransceiver(MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO, new RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.RECV_ONLY));
            return;
        }
        if (this.peerConnectionParameters.enableMultistream) {
            List<String> singletonList = Collections.singletonList("ARDAMS");
            if (this.mHasVideoSend) {
                Logging.m5305d("PCRTCClient", "createPeerConnectionInternal  ");
                createPeerConnection.addTrack(createVideoTrack(context, this.videoCapturer), singletonList);
            }
            if (this.mHasScreenSend) {
                createShareScreenTrack(context, this.screenCapturer);
                this.shareScreenSender = createPeerConnection.addTransceiver(this.shareScreenTrack, new RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.SEND_ONLY, singletonList)).getSender();
                if (!this.mHasAudioSend) {
                    createPeerConnection.addTrack(createAudioTrack(), singletonList);
                }
            }
            if (this.mHasAudioSend) {
                createPeerConnection.addTrack(createAudioTrack(), singletonList);
            }
        } else {
            this.mediaStream = this.factory.createLocalMediaStream("ARDAMS");
            if (this.mHasVideoSend) {
                this.mediaStream.addTrack(createVideoTrack(context, this.videoCapturer));
            }
            if (this.mHasAudioSend) {
                this.mediaStream.addTrack(createAudioTrack());
            }
            if (this.mHasScreenSend) {
                this.mediaStream.addTrack(createShareScreenTrack(context, this.screenCapturer));
                if (!this.mHasAudioSend) {
                    createPeerConnection.addTrack(createAudioTrack());
                }
            }
            createPeerConnection.addStream(this.mediaStream);
        }
        findVideoSender(bigInteger);
    }

    public void closePeer(final BigInteger bigInteger) {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.11
            @Override // java.lang.Runnable
            public void run() {
                JanusConnection janusConnection;
                if (PeerConnectionClient.this.peerConnectionMap == null || (janusConnection = (JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger)) == null) {
                    return;
                }
                if (janusConnection.peerConnection != null) {
                    janusConnection.peerConnection.close();
                    janusConnection.peerConnection.dispose();
                    janusConnection.peerConnection = null;
                }
                if (janusConnection.sdpObserver != null) {
                    janusConnection.sdpObserver.close();
                    janusConnection.sdpObserver = null;
                }
                if (janusConnection.pcObserver != null) {
                    janusConnection.pcObserver.close();
                    janusConnection.pcObserver = null;
                }
                PeerConnectionClient.this.peerConnectionMap.remove(bigInteger);
                Logging.m5305d("PCRTCClient", "Closing peer connection done.");
                PeerConnectionClient.this.events.onPeerClosed(bigInteger);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInternal() {
        Logging.m5305d("PCRTCClient", "Closing peer connection.");
        ConcurrentHashMap<BigInteger, JanusConnection> concurrentHashMap = this.peerConnectionMap;
        if (concurrentHashMap != null) {
            int size = concurrentHashMap.size();
            for (int i = 0; i < size; i++) {
                JanusConnection janusConnection = (JanusConnection) this.peerConnectionMap.values().toArray()[i];
                if (janusConnection.peerConnection != null) {
                    janusConnection.peerConnection.close();
                    janusConnection.peerConnection.dispose();
                    janusConnection.peerConnection = null;
                }
                if (janusConnection.sdpObserver != null) {
                    janusConnection.sdpObserver.close();
                    janusConnection.sdpObserver = null;
                }
                if (janusConnection.pcObserver != null) {
                    janusConnection.pcObserver.close();
                    janusConnection.pcObserver = null;
                }
            }
        }
        Logging.m5305d("PCRTCClient", "Closing audio source.");
        AudioSource audioSource = this.audioSource;
        if (audioSource != null) {
            audioSource.dispose();
            this.audioSource = null;
        }
        Logging.m5305d("PCRTCClient", "Stopping capture.");
        VideoCapturer videoCapturer = this.videoCapturer;
        if (videoCapturer != null) {
            try {
                videoCapturer.stopCapture();
                this.videoCapturerStopped = true;
                this.videoCapturer.dispose();
                this.videoCapturer = null;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Logging.m5305d("PCRTCClient", "Closing video source.");
        VideoSource videoSource = this.videoSource;
        if (videoSource != null) {
            videoSource.dispose();
            this.videoSource = null;
        }
        SurfaceTextureHelper surfaceTextureHelper = this.localTextureHelper;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.dispose();
            this.localTextureHelper = null;
        }
        ScreenCapturerAndroid screenCapturerAndroid = this.screenCapturer;
        if (screenCapturerAndroid != null) {
            screenCapturerAndroid.stopCapture();
            this.screenCaptureStopped = true;
            this.screenCapturer.dispose();
            this.screenCapturer = null;
        }
        VideoSource videoSource2 = this.shareScreenSource;
        if (videoSource2 != null) {
            videoSource2.dispose();
            this.shareScreenSource = null;
        }
        SurfaceTextureHelper surfaceTextureHelper2 = this.shareScreenTextureHelper;
        if (surfaceTextureHelper2 != null) {
            surfaceTextureHelper2.dispose();
            this.shareScreenTextureHelper = null;
        }
        if (this.localRender != null) {
            this.localRender = null;
        }
        if (this.localScreenRender != null) {
            this.localScreenRender = null;
        }
        Logging.m5305d("PCRTCClient", "Closing peer connection factory.");
        PeerConnectionFactory peerConnectionFactory = this.factory;
        if (peerConnectionFactory != null) {
            peerConnectionFactory.dispose();
            this.factory = null;
        }
        this.options = null;
        Logging.m5305d("PCRTCClient", "Closing peer connection done.");
        this.events.onPeerConnectionClosed();
        PeerConnectionFactory.stopInternalTracingCapture();
        PeerConnectionFactory.shutdownInternalTracer();
    }

    public boolean isHDVideo() {
        return this.videoWidth * this.videoHeight >= 921600;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getStats(final BigInteger bigInteger, final StatsEventsType statsEventsType) {
        PeerConnection peerConnection;
        ConcurrentHashMap<BigInteger, JanusConnection> concurrentHashMap = this.peerConnectionMap;
        if (concurrentHashMap == null || concurrentHashMap.get(bigInteger) == null || this.peerConnectionMap.get(bigInteger).peerConnection == null || (peerConnection = this.peerConnectionMap.get(bigInteger).peerConnection) == null || this.statsTimer == null || peerConnection.getStats(new StatsObserver() { // from class: com.baidu.rtc.PeerConnectionClient.12
            @Override // com.webrtc.StatsObserver
            public void onComplete(StatsReport[] statsReportArr) {
                if (PeerConnectionClient.this.statsTimer != null) {
                    PeerConnectionClient.this.events.onPeerConnectionStatsReady(statsReportArr, bigInteger, statsEventsType);
                }
            }
        }, null)) {
            return;
        }
        Logging.m5304e("PCRTCClient", "getStats() returns false!");
    }

    public void enableStatsEvents(boolean z, int i, final BigInteger bigInteger, final StatsEventsType statsEventsType) {
        TimerTask timerTask = null;
        if (z) {
            try {
                if (statsEventsType == StatsEventsType.GET_AUDIOLEVEL_EVENT) {
                    if (!this.peerConnectionParameters.enableReportAudioLevel) {
                        return;
                    }
                    timerTask = this.timerTaskGetVolumeMap.get(bigInteger);
                } else if (statsEventsType == StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
                    timerTask = this.timerTaskGetQualityMap.get(bigInteger);
                } else if (statsEventsType == StatsEventsType.GET_SLI_EVENT) {
                    timerTask = this.timerTaskGetSLIMap.get(bigInteger);
                } else if (statsEventsType == StatsEventsType.GET_ANTI_WEAK_EVENT) {
                    timerTask = this.timerTaskAntiWeakMap.get(bigInteger);
                }
                if (timerTask != null) {
                    timerTask.cancel();
                }
                TimerTask timerTask2 = new TimerTask() { // from class: com.baidu.rtc.PeerConnectionClient.13
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (PeerConnectionClient.this.executor.isShutdown() || PeerConnectionClient.this.statsTimer == null) {
                            return;
                        }
                        PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.13.1
                            @Override // java.lang.Runnable
                            public void run() {
                                PeerConnectionClient.this.getStats(bigInteger, statsEventsType);
                            }
                        });
                    }
                };
                if (statsEventsType == StatsEventsType.GET_AUDIOLEVEL_EVENT) {
                    this.timerTaskGetVolumeMap.put(bigInteger, timerTask2);
                } else if (statsEventsType == StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
                    this.timerTaskGetQualityMap.put(bigInteger, timerTask2);
                } else if (statsEventsType == StatsEventsType.GET_SLI_EVENT) {
                    this.timerTaskGetSLIMap.put(bigInteger, timerTask2);
                } else if (statsEventsType == StatsEventsType.GET_ANTI_WEAK_EVENT) {
                    this.timerTaskAntiWeakMap.put(bigInteger, timerTask2);
                }
                if (this.statsTimer != null) {
                    this.statsTimer.schedule(timerTask2, 0L, i);
                    return;
                }
                return;
            } catch (Exception e) {
                Logging.m5303e("PCRTCClient", "Can not schedule statistics timer", e);
                return;
            }
        }
        if (statsEventsType == StatsEventsType.GET_AUDIOLEVEL_EVENT) {
            if (!this.peerConnectionParameters.enableReportAudioLevel) {
                return;
            }
            timerTask = this.timerTaskGetVolumeMap.get(bigInteger);
        } else if (statsEventsType == StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
            timerTask = this.timerTaskGetQualityMap.get(bigInteger);
        } else if (statsEventsType == StatsEventsType.GET_SLI_EVENT) {
            timerTask = this.timerTaskGetSLIMap.get(bigInteger);
        } else if (statsEventsType == StatsEventsType.GET_ANTI_WEAK_EVENT) {
            timerTask = this.timerTaskAntiWeakMap.get(bigInteger);
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    public void disableAllStatsEvents() {
        disableStatsEvents(StatsEventsType.GET_AUDIOLEVEL_EVENT);
        disableStatsEvents(StatsEventsType.GET_QUALITY_MONITOR_EVENT);
        disableStatsEvents(StatsEventsType.GET_SLI_EVENT);
        disableStatsEvents(StatsEventsType.GET_ANTI_WEAK_EVENT);
    }

    private void disableStatsEvents(StatsEventsType statsEventsType) {
        Map<BigInteger, TimerTask> map;
        if (statsEventsType == StatsEventsType.GET_AUDIOLEVEL_EVENT) {
            map = this.timerTaskGetVolumeMap;
        } else if (statsEventsType == StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
            map = this.timerTaskGetQualityMap;
        } else if (statsEventsType == StatsEventsType.GET_SLI_EVENT) {
            map = this.timerTaskGetSLIMap;
        } else {
            map = statsEventsType == StatsEventsType.GET_ANTI_WEAK_EVENT ? this.timerTaskAntiWeakMap : null;
        }
        if (map == null || map.isEmpty()) {
            return;
        }
        for (TimerTask timerTask : map.values()) {
            if (timerTask != null) {
                timerTask.cancel();
            }
        }
    }

    public void setAudioEnabled(final boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.14
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.enableAudio = z;
                    if (PeerConnectionClient.this.localAudioTrack != null) {
                        PeerConnectionClient.this.localAudioTrack.setEnabled(PeerConnectionClient.this.enableAudio);
                    }
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.setMicrophoneMute(!z);
                    }
                }
            });
        }
    }

    public void setVideoEnabled(final boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.15
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.renderVideo = z;
                    if (PeerConnectionClient.this.localVideoTrack != null) {
                        PeerConnectionClient.this.localVideoTrack.setEnabled(PeerConnectionClient.this.renderVideo);
                    }
                }
            });
        }
    }

    public void setVideoStreamState(boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.16
                @Override // java.lang.Runnable
                public void run() {
                }
            });
        }
    }

    public void createOffer(final BigInteger bigInteger, final boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.17
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.createOfferInternal(bigInteger, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createOfferInternal(BigInteger bigInteger, boolean z) {
        JanusConnection janusConnection = this.peerConnectionMap.get(bigInteger);
        janusConnection.isPlayerMode = z;
        PeerConnection peerConnection = janusConnection.peerConnection;
        if (peerConnection == null || this.isError) {
            return;
        }
        Logging.m5305d("PCRTCClient", "PC Create OFFER");
        RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATER_OFFER_START, "createOffer-Executor-run");
        peerConnection.createOffer(janusConnection.sdpObserver, this.sdpMediaConstraints);
    }

    public void setRemoteDescription(final BigInteger bigInteger, final SessionDescription sessionDescription) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
            RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_FAILED, "onPublisherRemoteJsep", -1, "Executor is null");
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.18
            @Override // java.lang.Runnable
            public void run() {
                PeerConnection peerConnection = ((JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger)).peerConnection;
                SDPObserver sDPObserver = ((JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger)).sdpObserver;
                if (peerConnection == null || PeerConnectionClient.this.isError) {
                    RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_FAILED, "onPublisherRemoteJsep-Executor-run", -2, "peerConnection is null. or isError=true");
                    return;
                }
                String str = sessionDescription.description;
                SdpPrefer.AudioSdpAttribute audioSdpAttribute = new SdpPrefer.AudioSdpAttribute();
                audioSdpAttribute.cbr = PeerConnectionClient.this.peerConnectionParameters.audioBitrateMode == RtcParameterSettings.RtcAudioBitrateMode.RTC_AUDIO_BITRATE_CBR;
                audioSdpAttribute.maxaveragebitrate = PeerConnectionClient.this.peerConnectionParameters.audioMaxkbps;
                audioSdpAttribute.stereo = PeerConnectionClient.this.peerConnectionParameters.transportAudioChannel == RtcParameterSettings.RtcAudioChannel.RTC_AUDIO_STEREO;
                String audioAttributes = SdpPrefer.setAudioAttributes(PeerConnectionClient.this.peerConnectionParameters.audioCodec, str, audioSdpAttribute);
                SessionDescription sessionDescription2 = new SessionDescription(sessionDescription.type, audioAttributes);
                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_SUCCESS, "PCRTCClient-onPublisherRemoteJsep-Executor-run", "PUBLISHER_SET_REMOTE_SDP_START");
                JanusConnection janusConnection = (JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger);
                Logging.m5305d("PCRTCClient", "[" + janusConnection.type + "] PC Set Remote Description");
                long currentTimeMillis = System.currentTimeMillis();
                peerConnection.setRemoteDescription(sDPObserver, sessionDescription2);
                Logging.m5305d("PCRTCClient", "[" + janusConnection.type + "] PC Set Remote Description@" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                ErrorInfoReport.getInstance().putServerMap(bigInteger, SdpPrefer.parseRemoteMediaIp(audioAttributes));
            }
        });
    }

    public void subscriberHandleRemoteJsep(final BigInteger bigInteger, final SessionDescription sessionDescription) {
        ScheduledExecutorService scheduledExecutorService = this.factory != null ? this.executorSubscribe : this.executor;
        if (scheduledExecutorService.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
            RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBER_CREATER_OFFER_FAILED, "PeerConnectionClient-subscriberHandleRemoteJsep", -1, "executor is shutdown.");
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        scheduledExecutorService.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.19
            @Override // java.lang.Runnable
            public void run() {
                Logging.m5305d("PCRTCClient", "Remote Description Wait Execute@" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                try {
                    RtcLogCapturer.reportLog(RtcLogEvent.CREATE_SUBSCRIBER_PEERCONNECTION_START, "PeerConnectionClient-Executor-subscriberHandleRemoteJsep");
                    PeerConnection createPeerConnection = !PeerConnectionClient.this.peerConnectionParameters.enableMultistream ? PeerConnectionClient.this.createPeerConnection(bigInteger, false) : !PeerConnectionClient.this.peerConnectionMap.containsKey(bigInteger) ? PeerConnectionClient.this.createPeerConnection(bigInteger, false) : ((JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger)).peerConnection;
                    SDPObserver sDPObserver = ((JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger)).sdpObserver;
                    if (createPeerConnection != null && !PeerConnectionClient.this.isError) {
                        JanusConnection janusConnection = (JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger);
                        sDPObserver.isSetOffer = false;
                        RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_SET_REMOTE_SDP_START, "PCRTCClient-subscriberHandleRemoteJsep-Executor-run");
                        long currentTimeMillis2 = System.currentTimeMillis();
                        Logging.m5305d("PCRTCClient", "[" + janusConnection.type + "] PC Set Remote Description duration start");
                        createPeerConnection.setRemoteDescription(sDPObserver, sessionDescription);
                        ErrorInfoReport.getInstance().putServerMap(bigInteger, SdpPrefer.parseRemoteMediaIp(sessionDescription.description));
                        Logging.m5305d("PCRTCClient", "[" + janusConnection.type + "] PC Set Remote Description@" + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        sb.append(janusConnection.type);
                        sb.append("] PC create ANSWER:");
                        Logging.m5305d("PCRTCClient", sb.toString());
                        RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBER_CREATER_ANSWER_START, "PeerConnectionClient-Executor-subscriberHandleRemoteJsep");
                        createPeerConnection.createAnswer(janusConnection.sdpObserver, PeerConnectionClient.this.answer_sdpMediaConstraints);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    RtcLogCapturer.reportLog(RtcLogEvent.CREATE_SUBSCRIBER_PEERCONNECTION_FAILED, "subscriberHandleRemoteJsep-Executor-run-Exception", "-1", RtcLogCapturer.getErrorInfo(e.getMessage()));
                }
            }
        });
    }

    public void stopVideoSource() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.20
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.videoCapturer == null || PeerConnectionClient.this.videoCapturerStopped) {
                        return;
                    }
                    Logging.m5305d("PCRTCClient", "Stop video source.");
                    try {
                        PeerConnectionClient.this.videoCapturer.stopCapture();
                    } catch (InterruptedException unused) {
                    }
                    PeerConnectionClient.this.videoCapturerStopped = true;
                }
            });
        }
    }

    public void stopScreenSource() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.21
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.screenCapturer == null || PeerConnectionClient.this.screenCaptureStopped) {
                        return;
                    }
                    Logging.m5305d("PCRTCClient", "Stop video source.");
                    PeerConnectionClient.this.screenCapturer.stopCapture();
                    PeerConnectionClient.this.screenCaptureStopped = true;
                }
            });
        }
    }

    public void startVideoSource() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.22
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.videoCapturer == null || !PeerConnectionClient.this.videoCapturerStopped) {
                        return;
                    }
                    Logging.m5305d("PCRTCClient", "Restart video source.");
                    RtcLogCapturer.reportLog(RtcLogEvent.OPEN_CAMERA_START, "PCRTCClient-createVideoTrack", Integer.valueOf(PeerConnectionClient.this.videoFps), Integer.valueOf(PeerConnectionClient.this.videoWidth), Integer.valueOf(PeerConnectionClient.this.videoHeight));
                    PeerConnectionClient.this.videoCapturer.startCapture(PeerConnectionClient.this.videoWidth, PeerConnectionClient.this.videoHeight, PeerConnectionClient.this.videoFps);
                    PeerConnectionClient.this.videoCapturerStopped = false;
                }
            });
        }
    }

    public void startShareScreen(final ScreenCapturerAndroid screenCapturerAndroid, final BigInteger bigInteger, final EglBase.Context context) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
            return;
        }
        this.screenCapturer = screenCapturerAndroid;
        this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.23
            @Override // java.lang.Runnable
            public void run() {
                if (screenCapturerAndroid == null || !PeerConnectionClient.this.screenCaptureStopped || PeerConnectionClient.this.peerConnectionMap == null) {
                    return;
                }
                JanusConnection janusConnection = (JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger);
                if (janusConnection == null) {
                    Logging.m5304e("PCRTCClient", "startShareScreen handle : " + bigInteger + " janusConnection is null");
                } else if (janusConnection.offerAnswering) {
                    String str = "startShareScreen handle : " + bigInteger + " offerAnswering";
                    Logging.m5304e("PCRTCClient", str);
                    if (PeerConnectionClient.this.events != null) {
                        PeerConnectionClient.this.events.onShareScreenStart(-1, str);
                    }
                } else {
                    PeerConnection peerConnection = janusConnection.peerConnection;
                    RtpTransceiver screenTransceiver = PeerConnectionClient.this.getScreenTransceiver(peerConnection);
                    if (screenTransceiver != null) {
                        PeerConnectionClient.this.createShareScreenTrack(context, screenCapturerAndroid);
                        screenTransceiver.getSender().setTrack(PeerConnectionClient.this.shareScreenTrack, true);
                        screenTransceiver.setDirection(RtpTransceiver.RtpTransceiverDirection.SEND_ONLY);
                    } else {
                        List singletonList = Collections.singletonList("ARDAMS");
                        PeerConnectionClient.this.createShareScreenTrack(context, screenCapturerAndroid);
                        screenTransceiver = peerConnection.addTransceiver(PeerConnectionClient.this.shareScreenTrack, new RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.SEND_ONLY, singletonList));
                    }
                    PeerConnectionClient.this.shareScreenSender = screenTransceiver.getSender();
                    Logging.m5305d("PCRTCClient", "start screen source. ");
                    if (!PeerConnectionClient.this.mHasAudioSend && PeerConnectionClient.this.peerConnectionParameters.screenShareParams != null && PeerConnectionClient.this.peerConnectionParameters.screenShareParams.mEnableAudioCapture && PeerConnectionClient.this.localAudioTrack != null) {
                        PeerConnectionClient.this.localAudioTrack.setEnabled(true);
                    }
                    janusConnection.offerAnswering = true;
                    janusConnection.offerAnswerOwner = 1;
                    PeerConnectionClient.this.createOfferInternal(bigInteger, false);
                    PeerConnectionClient.this.screenCaptureStopped = false;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RtpTransceiver getScreenTransceiver(PeerConnection peerConnection) {
        for (RtpTransceiver rtpTransceiver : peerConnection.getTransceivers()) {
            if (rtpTransceiver.getSender() != null && rtpTransceiver.getMediaType() == MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO && "ARDAMSv1".equals(rtpTransceiver.getSender().m5296id())) {
                this.shareScreenSender = rtpTransceiver.getSender();
                return rtpTransceiver;
            }
        }
        return null;
    }

    public void stopShareScreen(final BigInteger bigInteger) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.24
                /* JADX WARN: Type inference failed for: r4v0, types: [com.webrtc.VideoSource, com.webrtc.SurfaceTextureHelper] */
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.screenCapturer == null || PeerConnectionClient.this.screenCaptureStopped || PeerConnectionClient.this.peerConnectionMap == null) {
                        return;
                    }
                    JanusConnection janusConnection = (JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger);
                    if (janusConnection == null) {
                        Logging.m5304e("PCRTCClient", "stopShareScreen handle : " + bigInteger + " janusConnection is null");
                    } else if (janusConnection.offerAnswering) {
                        String str = "stopShareScreen handle : " + bigInteger + " offerAnswering";
                        Logging.m5304e("PCRTCClient", str);
                        if (PeerConnectionClient.this.events != null) {
                            PeerConnectionClient.this.events.onShareScreenStop(-1, str);
                        }
                    } else {
                        Logging.m5305d("PCRTCClient", "stop share screen");
                        PeerConnectionClient.this.screenCapturer.stopCapture();
                        if (!PeerConnectionClient.this.mHasAudioSend && PeerConnectionClient.this.peerConnectionParameters.screenShareParams != null && PeerConnectionClient.this.peerConnectionParameters.screenShareParams.mEnableAudioCapture && PeerConnectionClient.this.localAudioTrack != null) {
                            PeerConnectionClient.this.localAudioTrack.setEnabled(false);
                        }
                        RtpTransceiver screenTransceiver = PeerConnectionClient.this.getScreenTransceiver(janusConnection.peerConnection);
                        if (screenTransceiver != null) {
                            screenTransceiver.getSender().setTrack(null, true);
                            screenTransceiver.setDirection(RtpTransceiver.RtpTransceiverDirection.INACTIVE);
                            PeerConnectionClient.this.shareScreenSender = screenTransceiver.getSender();
                        }
                        try {
                            if (PeerConnectionClient.this.shareScreenSource != null) {
                                try {
                                    PeerConnectionClient.this.shareScreenSource.dispose();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                if (PeerConnectionClient.this.shareScreenTextureHelper != null) {
                                    try {
                                        PeerConnectionClient.this.shareScreenTextureHelper.dispose();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                janusConnection.offerAnswering = true;
                                janusConnection.offerAnswerOwner = 2;
                                PeerConnectionClient.this.createOfferInternal(bigInteger, false);
                                PeerConnectionClient.this.screenCaptureStopped = true;
                            } finally {
                                PeerConnectionClient.this.shareScreenTextureHelper = null;
                            }
                        } finally {
                            PeerConnectionClient.this.shareScreenSource = null;
                        }
                    }
                }
            });
        }
    }

    public void stopAudioSource() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.25
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.audioSource != null) {
                        PeerConnectionClient.this.audioSource.dispose();
                        PeerConnectionClient.this.audioSource = null;
                    }
                }
            });
        }
    }

    public void setAudioRecording(final BigInteger bigInteger, final boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.26
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnection peerConnection = ((JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger)).peerConnection;
                    if (peerConnection != null) {
                        peerConnection.setAudioRecording(z);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(final String str) {
        Logging.m5304e("PCRTCClient", "Peerconnection error: " + str);
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.27
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.isError) {
                        return;
                    }
                    PeerConnectionClient.this.events.onPeerConnectionError(str);
                    PeerConnectionClient.this.isError = true;
                }
            });
        }
    }

    private AudioTrack createAudioTrack() {
        this.audioSource = this.factory.createAudioSource(this.audioConstraints);
        this.localAudioTrack = this.factory.createAudioTrack("ARDAMSa0", this.audioSource);
        if (this.peerConnectionParameters.screenShareParams != null) {
            this.localAudioTrack.setEnabled(this.peerConnectionParameters.screenShareParams.mEnableAudioCapture);
        } else {
            this.localAudioTrack.setEnabled(this.enableAudio);
        }
        return this.localAudioTrack;
    }

    private VideoTrack createVideoTrack(EglBase.Context context, VideoCapturer videoCapturer) {
        this.videoSource = this.factory.createVideoSource(false);
        this.videoSource.setVideoProcessor(this.videoProcessor);
        if (this.mVideoCapturerState == 0) {
            this.localTextureHelper = SurfaceTextureHelper.create("video renderer", context, false);
            videoCapturer.initialize(this.localTextureHelper, this.context, this.videoSource.getCapturerObserver());
        }
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_CAMERA_START, "PCRTCClient-createVideoTrack", Integer.valueOf(this.videoFps), Integer.valueOf(this.videoWidth), Integer.valueOf(this.videoHeight));
        int i = this.mVideoCapturerState;
        if (i == 0 || i == 1) {
            videoCapturer.startCapture(this.videoWidth, this.videoHeight, this.videoFps);
        }
        this.localVideoTrack = this.factory.createVideoTrack("ARDAMSv0", this.videoSource);
        this.localVideoTrack.setEnabled(this.renderVideo);
        VideoSink videoSink = this.localRender;
        if (videoSink != null) {
            this.localVideoTrack.addSink(videoSink);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("createVideoTrack is null ");
        sb.append(this.localAudioTrack == null);
        Logging.m5305d("PCRTCClient", sb.toString());
        return this.localVideoTrack;
    }

    public CapturerObserver getCapturerObserver() {
        VideoSource videoSource = this.videoSource;
        if (videoSource != null) {
            return videoSource.getCapturerObserver();
        }
        return null;
    }

    public void addLocalSink(VideoSink videoSink) {
        this.localRender = videoSink;
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.28
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.localVideoTrack == null) {
                        return;
                    }
                    PeerConnectionClient.this.localVideoTrack.addSink(PeerConnectionClient.this.localRender);
                }
            });
        }
    }

    public void registerLocalSink(final VideoSink videoSink) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.29
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.localVideoTrack == null) {
                        return;
                    }
                    PeerConnectionClient.this.localVideoTrack.addSink(videoSink);
                }
            });
        }
    }

    public void unregisterLocalSink(final VideoSink videoSink) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.30
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.localVideoTrack == null) {
                        return;
                    }
                    PeerConnectionClient.this.localVideoTrack.removeSink(videoSink);
                }
            });
        }
    }

    public void registerAudioRecord(final RtcAudioRecordListener rtcAudioRecordListener) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.31
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.audioRecordListeners.contains(rtcAudioRecordListener)) {
                        return;
                    }
                    PeerConnectionClient.this.audioRecordListeners.add(rtcAudioRecordListener);
                }
            });
        }
    }

    public void unregisterAudioRecord(final RtcAudioRecordListener rtcAudioRecordListener) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.32
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.audioRecordListeners.remove(rtcAudioRecordListener);
                }
            });
        }
    }

    public VideoTrack getLocalVideoTrack() {
        return this.localVideoTrack;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VideoTrack createShareScreenTrack(EglBase.Context context, ScreenCapturerAndroid screenCapturerAndroid) {
        this.shareScreenSource = this.factory.createVideoSource(true);
        this.shareScreenTextureHelper = SurfaceTextureHelper.create("share screen renderer", context);
        screenCapturerAndroid.initialize(this.shareScreenTextureHelper, this.context, this.shareScreenSource.getCapturerObserver());
        screenCapturerAndroid.startCapture(this.peerConnectionParameters.screenShareParams);
        this.shareScreenTrack = this.factory.createVideoTrack("ARDAMSv1", this.shareScreenSource);
        if (this.peerConnectionParameters.screenShareParams != null) {
            this.shareScreenTrack.setEnabled(this.peerConnectionParameters.screenShareParams.mEnableVideoCapture);
        } else {
            this.shareScreenTrack.setEnabled(true);
        }
        return this.shareScreenTrack;
    }

    public void updateScreenShareParams(final BRTCScreenShareParams bRTCScreenShareParams) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.33
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.screenCapturer == null || PeerConnectionClient.this.peerConnectionParameters.screenShareParams == null) {
                        PeerConnectionClient.this.peerConnectionParameters.screenShareParams = bRTCScreenShareParams;
                        return;
                    }
                    BRTCScreenShareParams bRTCScreenShareParams2 = PeerConnectionClient.this.peerConnectionParameters.screenShareParams;
                    if (PeerConnectionClient.this.localAudioTrack != null && bRTCScreenShareParams2.mEnableAudioCapture != bRTCScreenShareParams.mEnableAudioCapture) {
                        PeerConnectionClient.this.localAudioTrack.setEnabled(bRTCScreenShareParams.mEnableAudioCapture);
                    }
                    if (PeerConnectionClient.this.shareScreenTrack != null && bRTCScreenShareParams2.mEnableVideoCapture != bRTCScreenShareParams.mEnableVideoCapture) {
                        PeerConnectionClient.this.shareScreenTrack.setEnabled(bRTCScreenShareParams.mEnableVideoCapture);
                    }
                    PeerConnectionClient.this.screenCapturer.updateScreenCaptureParameters(bRTCScreenShareParams);
                    if (bRTCScreenShareParams.mEnableVideoCapture && (!bRTCScreenShareParams2.mEnableVideoCapture || bRTCScreenShareParams2.mVideoCaptureParams.videoMaxkbps != bRTCScreenShareParams.mVideoCaptureParams.videoMaxkbps || bRTCScreenShareParams2.mVideoCaptureParams.videoMinkbps != bRTCScreenShareParams.mVideoCaptureParams.videoMinkbps)) {
                        PeerConnectionClient.this.setScreenBitrate(Integer.valueOf(bRTCScreenShareParams.mVideoCaptureParams.videoMinkbps), Integer.valueOf(bRTCScreenShareParams.mVideoCaptureParams.videoMaxkbps));
                        Logging.m5305d("PCRTCClient", "updateScreenShareParams Bitrate ");
                    }
                    PeerConnectionClient.this.peerConnectionParameters.screenShareParams = bRTCScreenShareParams;
                }
            });
        }
    }

    private void findVideoSender(BigInteger bigInteger) {
        for (RtpSender rtpSender : this.peerConnectionMap.get(bigInteger).peerConnection.getSenders()) {
            if (rtpSender.track() != null && rtpSender.track().kind().equals("video")) {
                Logging.m5305d("PCRTCClient", "Found video sender.");
                this.localVideoSender = rtpSender;
            }
        }
    }

    public void setVideoBitrate(Integer num, Integer num2) {
        setBitrateInternal(this.localVideoSender, num, num2);
    }

    public void setScreenBitrate(Integer num, Integer num2) {
        setBitrateInternal(this.shareScreenSender, num, num2);
    }

    private void setBitrateInternal(final RtpSender rtpSender, final Integer num, final Integer num2) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.-$$Lambda$PeerConnectionClient$JJVL4BAMZIYfU1qYQ4qKZaEG0tI
                @Override // java.lang.Runnable
                public final void run() {
                    PeerConnectionClient.lambda$setBitrateInternal$0(RtpSender.this, num2, num);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setBitrateInternal$0(RtpSender rtpSender, Integer num, Integer num2) {
        if (rtpSender == null) {
            return;
        }
        Logging.m5305d("PCRTCClient", "Requested max video bitrate: " + num);
        if (rtpSender == null) {
            Logging.m5301w("PCRTCClient", "Sender is not ready.");
            return;
        }
        RtpParameters parameters = rtpSender.getParameters();
        if (parameters.encodings.size() == 0) {
            Logging.m5301w("PCRTCClient", "RtpParameters are not ready.");
            return;
        }
        for (RtpParameters.Encoding encoding : parameters.encodings) {
            Integer num3 = null;
            encoding.maxBitrateBps = num == null ? null : Integer.valueOf(num.intValue() * 1000);
            if (num2 != null) {
                num3 = Integer.valueOf(num2.intValue() * 1000);
            }
            encoding.minBitrateBps = num3;
        }
        if (!rtpSender.setParameters(parameters)) {
            Logging.m5304e("PCRTCClient", "RtpSender.setParameters failed.");
        }
        Logging.m5305d("PCRTCClient", "Configured max video bitrate to: " + num2 + " -" + num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCameraInternal() {
        if (this.videoCapturer instanceof CameraCapturer) {
            Logging.m5305d("PCRTCClient", "Switch camera");
            ((CameraCapturer) this.videoCapturer).switchCamera();
            return;
        }
        Logging.m5305d("PCRTCClient", "Will not switch camera, video caputurer is not a camera");
    }

    public void switchCamera() {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.34
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.switchCameraInternal();
                }
            });
        }
    }

    public void cameraFocusWithPoint(float f, float f2) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.35
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.videoCapturer instanceof CameraCapturer) {
                        Logging.m5305d("PCRTCClient", "cameraFocusWithPoint");
                        CameraCapturer cameraCapturer = (CameraCapturer) PeerConnectionClient.this.videoCapturer;
                        return;
                    }
                    Logging.m5305d("PCRTCClient", "Will not cameraFocusWithPoint, video caputurer is not a camera");
                }
            });
        }
    }

    public boolean isFocusSupported() {
        if (this.videoCapturer instanceof CameraCapturer) {
            Logging.m5305d("PCRTCClient", "isFocusSupported");
            CameraCapturer cameraCapturer = (CameraCapturer) this.videoCapturer;
            return false;
        }
        Logging.m5305d("PCRTCClient", "Will not set camera zoom, video caputurer is not a camera");
        return false;
    }

    public void setCameraZoom(float f) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.36
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.videoCapturer instanceof CameraCapturer) {
                        Logging.m5305d("PCRTCClient", "setCameraZoom");
                        CameraCapturer cameraCapturer = (CameraCapturer) PeerConnectionClient.this.videoCapturer;
                        return;
                    }
                    Logging.m5305d("PCRTCClient", "Will not set camera zoom, video caputurer is not a camera");
                }
            });
        }
    }

    public float getMaxCameraZoom() {
        if (this.videoCapturer instanceof CameraCapturer) {
            Logging.m5305d("PCRTCClient", "getMaxCameraZoom");
            CameraCapturer cameraCapturer = (CameraCapturer) this.videoCapturer;
            return 1.0f;
        }
        Logging.m5305d("PCRTCClient", "Will not getMaxCameraZoom, video caputurer is not a camera");
        return 1.0f;
    }

    public boolean isCameraZoomSupported() {
        if (this.videoCapturer instanceof CameraCapturer) {
            Logging.m5305d("PCRTCClient", "isCameraZoomSupported");
            CameraCapturer cameraCapturer = (CameraCapturer) this.videoCapturer;
            return false;
        }
        Logging.m5305d("PCRTCClient", "Will not isCameraZoomSupported, video caputurer is not a camera");
        return false;
    }

    public void changeCaptureFormat(final int i, final int i2, final int i3) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.37
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.changeCaptureFormatInternal(i, i2, i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCaptureFormatInternal(int i, int i2, int i3) {
        if (this.isError || this.videoCapturer == null) {
            Logging.m5304e("PCRTCClient", "Failed to change capture format. Video: true. Error : " + this.isError);
            return;
        }
        Logging.m5305d("PCRTCClient", "changeCaptureFormat: " + i + "x" + i2 + "@" + i3);
        this.videoSource.adaptOutputFormat(i, i2, i3);
    }

    public JanusConnection getJanusConnectionByHandleID(BigInteger bigInteger) {
        return this.peerConnectionMap.get(bigInteger);
    }

    public void setSpeakerMute(final boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.38
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.setSpeakerMute(z);
                    }
                }
            });
        }
    }

    public void setStuckEventListener(final SLIReportInterface sLIReportInterface) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.39
                @Override // java.lang.Runnable
                public void run() {
                    PeerConnectionClient.this.mStuckEvent = sLIReportInterface;
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.setStuckEventListener(sLIReportInterface);
                    }
                }
            });
        }
    }

    public void setEnableSLIReport(final boolean z) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.40
                @Override // java.lang.Runnable
                public void run() {
                    if (PeerConnectionClient.this.adm != null) {
                        PeerConnectionClient.this.adm.setEnableSLIReport(z);
                    }
                }
            });
        }
    }

    public String getSsrcByMid(BigInteger bigInteger, String str) {
        JanusConnection janusConnection = this.peerConnectionMap.get(bigInteger);
        return janusConnection.midSsrcMap.containsKey(str) ? janusConnection.midSsrcMap.get(str) : "";
    }

    public String getMidBySsrc(BigInteger bigInteger, String str) {
        for (Map.Entry<String, String> entry : this.peerConnectionMap.get(bigInteger).midSsrcMap.entrySet()) {
            if (entry.getValue().equals(str)) {
                return entry.getKey();
            }
        }
        return "";
    }

    public void setAudioTrackVolume(BigInteger bigInteger, String str, int i) {
        JanusConnection janusConnection = this.peerConnectionMap.get(bigInteger);
        if (janusConnection != null) {
            for (Map.Entry<String, AudioTrack> entry : janusConnection.audioTracks.entrySet()) {
                if (entry.getKey().equals(str)) {
                    entry.getValue().setVolume((i * 1.0d) / 100.0d);
                }
            }
        }
    }

    public void setAudioTrackVolume(final BigInteger bigInteger, final int i) {
        if (this.executor.isShutdown()) {
            Logging.m5301w("PCRTCClient", "executor is already shutdown");
        } else {
            this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.41
                @Override // java.lang.Runnable
                public void run() {
                    JanusConnection janusConnection = (JanusConnection) PeerConnectionClient.this.peerConnectionMap.get(bigInteger);
                    if (janusConnection == null || janusConnection.audioTrack == null) {
                        return;
                    }
                    janusConnection.audioTrack.setVolume((i * 1.0d) / 100.0d);
                }
            });
        }
    }

    public ArrayList<CommonDefine.StreamInfo> getPublisherStreamInfo(BigInteger bigInteger) {
        PeerConnection peerConnection;
        ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
        if (this.peerConnectionMap.get(bigInteger) != null && (peerConnection = this.peerConnectionMap.get(bigInteger).peerConnection) != null) {
            for (RtpTransceiver rtpTransceiver : peerConnection.getTransceivers()) {
                String mid = rtpTransceiver.getMid();
                String str = "";
                if (rtpTransceiver.getMediaType() == MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO) {
                    str = "audio";
                } else if (rtpTransceiver.getMediaType() == MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO) {
                    boolean z = false;
                    if (rtpTransceiver.getSender() != null && "ARDAMSv1".equals(rtpTransceiver.getSender().m5296id())) {
                        z = true;
                    }
                    if (z) {
                        this.shareScreenSender = rtpTransceiver.getSender();
                        str = "video_screen";
                    } else {
                        str = "video";
                    }
                }
                CommonDefine.StreamInfo streamInfo = new CommonDefine.StreamInfo();
                streamInfo.mid = mid;
                streamInfo.description = str;
                arrayList.add(streamInfo);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class PCObserver implements PeerConnection.Observer {
        private JanusConnection connection;
        private PeerConnection peerConnection;

        @Override // com.webrtc.PeerConnection.Observer
        @CalledByNative("Observer")
        public /* synthetic */ void onConnectionChange(PeerConnection.PeerConnectionState peerConnectionState) {
            PeerConnection.Observer.CC.$default$onConnectionChange(this, peerConnectionState);
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onRenegotiationNeeded() {
        }

        @Override // com.webrtc.PeerConnection.Observer
        @CalledByNative("Observer")
        public /* synthetic */ void onSelectedCandidatePairChanged(CandidatePairChangeEvent candidatePairChangeEvent) {
            PeerConnection.Observer.CC.$default$onSelectedCandidatePairChanged(this, candidatePairChangeEvent);
        }

        @Override // com.webrtc.PeerConnection.Observer
        @CalledByNative("Observer")
        public /* synthetic */ void onStandardizedIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            PeerConnection.Observer.CC.$default$onStandardizedIceConnectionChange(this, iceConnectionState);
        }

        public PCObserver() {
        }

        public void setConnection(JanusConnection janusConnection) {
            this.connection = janusConnection;
            this.peerConnection = janusConnection.peerConnection;
        }

        public void close() {
            this.peerConnection = null;
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
            Logging.m5305d("PCRTCClient", "[" + this.connection.type + "] testing ... add track");
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onTrack(final RtpTransceiver rtpTransceiver) {
            Logging.m5305d("PCRTCClient", "[" + this.connection.type + "]  ... onTrack");
            if (PeerConnectionClient.this.peerConnectionParameters.enableMultistream) {
                if (!PeerConnectionClient.this.executor.isShutdown()) {
                    PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.PCObserver.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PCObserver.this.peerConnection == null || PeerConnectionClient.this.isError) {
                                return;
                            }
                            if (PCObserver.this.connection.isPlayerMode || !PCObserver.this.connection.type) {
                                Logging.m5305d("PCRTCClient", "[" + PCObserver.this.connection.type + "] =========== onTrack ==========");
                                RtpParameters parameters = rtpTransceiver.getReceiver().getParameters();
                                if (parameters.encodings.size() > 0) {
                                    PCObserver.this.connection.midSsrcMap.put(rtpTransceiver.getMid(), String.valueOf(parameters.encodings.get(0).ssrc));
                                }
                                if (rtpTransceiver.getMediaType() == MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO) {
                                    PCObserver.this.connection.videoTracks.put(rtpTransceiver.getMid(), (VideoTrack) rtpTransceiver.getReceiver().track());
                                    PeerConnectionClient.this.events.onRemoteRender(PCObserver.this.connection, rtpTransceiver.getMid());
                                    PeerConnectionClient.this.events.onRemoteStreamStats("video", rtpTransceiver.getMid());
                                } else if (rtpTransceiver.getMediaType() == MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO) {
                                    PCObserver.this.connection.audioTracks.put(rtpTransceiver.getMid(), (AudioTrack) rtpTransceiver.getReceiver().track());
                                    PeerConnectionClient.this.events.onRemoteStreamStats("audio", rtpTransceiver.getMid());
                                }
                            }
                        }
                    });
                    return;
                }
                Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
            }
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onIceCandidate(final IceCandidate iceCandidate) {
            if (!PeerConnectionClient.this.executor.isShutdown()) {
                PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.PCObserver.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PeerConnectionClient.this.events.onIceCandidate(iceCandidate, PCObserver.this.connection.handleId);
                    }
                });
                return;
            }
            Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onIceCandidatesRemoved(final IceCandidate[] iceCandidateArr) {
            if (!PeerConnectionClient.this.executor.isShutdown()) {
                PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.PCObserver.3
                    @Override // java.lang.Runnable
                    public void run() {
                        PeerConnectionClient.this.events.onIceCandidatesRemoved(iceCandidateArr);
                    }
                });
                return;
            }
            Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onSignalingChange(PeerConnection.SignalingState signalingState) {
            Logging.m5305d("PCRTCClient", "[" + this.connection.type + "] SignalingState: " + signalingState);
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onIceConnectionChange(final PeerConnection.IceConnectionState iceConnectionState) {
            if (!PeerConnectionClient.this.executor.isShutdown()) {
                PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.PCObserver.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Logging.m5305d("PCRTCClient", "[" + PCObserver.this.connection.type + "] IceConnectionState: " + iceConnectionState);
                        boolean z = PCObserver.this.connection.type;
                        if (iceConnectionState == PeerConnection.IceConnectionState.CONNECTED) {
                            PeerConnectionClient.this.events.onIceConnected();
                            if (z) {
                                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_ICE_CONNECTED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            } else {
                                RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_ICE_CONNECTED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            }
                        } else if (iceConnectionState == PeerConnection.IceConnectionState.DISCONNECTED) {
                            PeerConnectionClient.this.events.onIceDisconnected();
                            if (z) {
                                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_ICE_DISCONNECTED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            } else {
                                RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_ICE_DISCONNECTED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            }
                        } else if (iceConnectionState == PeerConnection.IceConnectionState.FAILED) {
                            PeerConnectionClient.this.events.onIceFailed();
                            if (z) {
                                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_ICE_FAILED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            } else {
                                RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_ICE_FAILED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            }
                        } else if (iceConnectionState == PeerConnection.IceConnectionState.CLOSED) {
                            if (z) {
                                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_ICE_CLOSED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            } else {
                                RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_ICE_CLOSED, "PCObserver-onIceConnectionChange", PCObserver.this.connection.handleId);
                            }
                        }
                    }
                });
                return;
            }
            Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
            Logging.m5305d("PCRTCClient", "[" + this.connection.type + "] IceGatheringState: " + iceGatheringState);
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onIceConnectionReceivingChange(boolean z) {
            Logging.m5305d("PCRTCClient", "[" + this.connection.type + "] IceConnectionReceiving changed to " + z);
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onAddStream(final MediaStream mediaStream) {
            if (!PeerConnectionClient.this.executor.isShutdown()) {
                if (PeerConnectionClient.this.peerConnectionParameters.enableMultistream) {
                    return;
                }
                PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.PCObserver.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PCObserver.this.peerConnection == null || PeerConnectionClient.this.isError) {
                            return;
                        }
                        Logging.m5305d("PCRTCClient", "[" + PCObserver.this.connection.type + "] =========== onAddStream ==========");
                        if (mediaStream.audioTracks.size() == 1) {
                            PeerConnectionClient.this.remoteAudioTrack = mediaStream.audioTracks.get(0);
                            PCObserver.this.connection.audioTrack = PeerConnectionClient.this.remoteAudioTrack;
                        }
                        if (mediaStream.videoTracks.size() == 1) {
                            PeerConnectionClient.this.remoteVideoTrack = mediaStream.videoTracks.get(0);
                            PeerConnectionClient.this.remoteVideoTrack.setEnabled(true);
                            PCObserver.this.connection.videoTrack = PeerConnectionClient.this.remoteVideoTrack;
                            PeerConnectionClient.this.events.onRemoteRender(PCObserver.this.connection, "");
                        }
                        boolean z = false;
                        boolean z2 = false;
                        if (mediaStream.videoTracks.size() != 0) {
                            z = true;
                        }
                        if (mediaStream.audioTracks.size() != 0) {
                            z2 = true;
                        }
                        PeerConnectionClient.this.events.onRemoteStreamStats(z, z2, PCObserver.this.connection.handleId);
                    }
                });
                return;
            }
            Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onRemoveStream(MediaStream mediaStream) {
            if (!PeerConnectionClient.this.executor.isShutdown()) {
                PeerConnectionClient.this.executor.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.PCObserver.6
                    @Override // java.lang.Runnable
                    public void run() {
                        PeerConnectionClient.this.remoteVideoTrack = null;
                        PeerConnectionClient.this.remoteAudioTrack = null;
                    }
                });
                return;
            }
            Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
        }

        @Override // com.webrtc.PeerConnection.Observer
        public void onDataChannel(DataChannel dataChannel) {
            Logging.m5305d("PCRTCClient", "[" + this.connection.type + "] New Data channel " + dataChannel.label());
            dataChannel.registerObserver(PeerConnectionClient.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class SDPObserver implements SdpObserver {
        private JanusConnection connection;
        private PeerConnectionParameters connectionParam;
        private ScheduledExecutorService executorSdp;
        private BigInteger handleId;
        private boolean isSetOffer = false;
        private volatile boolean isUpdateOffer;
        private SessionDescription localSdp;
        private PeerConnection peerConnection;
        private SDPObserver sdpObserver;
        private long setPublishLocalSdpTs;
        private long setSubscribeLocalSdpTs;
        private boolean type;

        SDPObserver() {
        }

        public void setConnection(JanusConnection janusConnection) {
            this.connection = janusConnection;
            this.peerConnection = janusConnection.peerConnection;
            this.sdpObserver = janusConnection.sdpObserver;
            this.handleId = janusConnection.handleId;
            this.type = janusConnection.type;
            this.connectionParam = janusConnection.connectionParam;
            if (this.type) {
                this.executorSdp = PeerConnectionClient.this.executor;
            } else {
                this.executorSdp = PeerConnectionClient.this.executorSubscribe;
            }
        }

        public void close() {
            this.peerConnection = null;
        }

        @Override // com.webrtc.SdpObserver
        public void onCreateSuccess(SessionDescription sessionDescription) {
            Logging.m5304e("PCRTCClient", "[" + this.connection.type + "] SDP on create success");
            if (this.type) {
                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATER_OFFER_SUCCESS, "SDPObserver-onCreateSuccess");
            } else {
                RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBER_CREATER_ANSWER_SUCCESS, "SDPObserver-onCreateSuccess");
            }
            final SessionDescription sessionDescription2 = new SessionDescription(sessionDescription.type, sessionDescription.description);
            if (this.localSdp != null) {
                this.isUpdateOffer = true;
            }
            this.localSdp = sessionDescription2;
            if (this.executorSdp.isShutdown()) {
                Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            this.executorSdp.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.SDPObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SDPObserver.this.peerConnection == null || PeerConnectionClient.this.isError) {
                        return;
                    }
                    SDPObserver.this.isSetOffer = true;
                    if (SDPObserver.this.type) {
                        SDPObserver.this.setPublishLocalSdpTs = System.currentTimeMillis();
                        RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_START, "SDPObserver-onCreateSuccess-Executor-run");
                    } else {
                        PeerConnectionClient.this.events.onRemoteDescription(SDPObserver.this.localSdp, SDPObserver.this.handleId);
                        SDPObserver.this.setSubscribeLocalSdpTs = System.currentTimeMillis();
                        RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_SET_LOCAL_SDP_START, "SDPObserver-onCreateSuccess-Executor-run");
                    }
                    Logging.m5305d("PCRTCClient", "[" + SDPObserver.this.connection.type + "] Set local SDP from " + sessionDescription2.type + " id:" + SDPObserver.this.handleId + "@" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                    SDPObserver.this.peerConnection.setLocalDescription(SDPObserver.this.sdpObserver, sessionDescription2);
                }
            });
        }

        @Override // com.webrtc.SdpObserver
        public void onSetSuccess() {
            if (this.executorSdp.isShutdown()) {
                Logging.m5301w("PCRTCClient", "[" + this.connection.type + "] executor is already shutdown");
                return;
            }
            this.executorSdp.execute(new Runnable() { // from class: com.baidu.rtc.PeerConnectionClient.SDPObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (SDPObserver.this.peerConnection == null || PeerConnectionClient.this.isError) {
                        return;
                    }
                    if (SDPObserver.this.type) {
                        if (SDPObserver.this.peerConnection.getRemoteDescription() != null) {
                            if (SDPObserver.this.isUpdateOffer) {
                                Logging.m5305d("PCRTCClient", "[" + SDPObserver.this.connection.type + "] Local SDP update succesfully");
                                PeerConnectionClient.this.events.onLocalDescription(SDPObserver.this.localSdp, SDPObserver.this.handleId, SDPObserver.this.localSdp.description.contains("profile-level-id=640c1f"));
                                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_SUCCESS, "SDPObserver-onSetSuccess-Executor-run");
                                SDPObserver.this.isUpdateOffer = false;
                            } else {
                                Logging.m5305d("PCRTCClient", "[" + SDPObserver.this.connection.type + "] Remote SDP set succesfully");
                                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_SET_REMOTE_SDP_SUCCESS, "SDPObserver-onSetSuccess-Executor-run");
                            }
                        } else {
                            Logging.m5305d("PCRTCClient", "[" + SDPObserver.this.connection.type + "] publish Local SDP set succesfully@" + (System.currentTimeMillis() - SDPObserver.this.setPublishLocalSdpTs) + "ms");
                            PeerConnectionClient.this.events.onLocalDescription(SDPObserver.this.localSdp, SDPObserver.this.handleId, SDPObserver.this.localSdp.description.contains("profile-level-id=640c1f"));
                            RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_SUCCESS, "SDPObserver-onSetSuccess-Executor-run");
                        }
                    } else if (SDPObserver.this.peerConnection.getLocalDescription() != null && SDPObserver.this.isSetOffer) {
                        Logging.m5305d("PCRTCClient", "[" + SDPObserver.this.connection.type + "] subscribe Local SDP set succesfully@" + (System.currentTimeMillis() - SDPObserver.this.setSubscribeLocalSdpTs) + "ms");
                        PeerConnectionClient.this.events.onSubscribSetLocalDescriptionSuccess(SDPObserver.this.handleId);
                        RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_SET_LOCAL_SDP_SUCCESS, "SDPObserver-onSetSuccess-Executor-run");
                    } else {
                        Logging.m5305d("PCRTCClient", "[" + SDPObserver.this.connection.type + "] answer Remote SDP set succesfully");
                        RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_SET_REMOTE_SDP_SUCCESS, "SDPObserver-onSetSuccess-Executor-run");
                    }
                    if (PeerConnectionClient.this.events != null) {
                        if (SDPObserver.this.connection.offerAnswerOwner == 1) {
                            PeerConnectionClient.this.events.onShareScreenStart(0, "");
                        } else if (SDPObserver.this.connection.offerAnswerOwner == 2) {
                            PeerConnectionClient.this.events.onShareScreenStop(0, "");
                        }
                    }
                    SDPObserver.this.connection.offerAnswerOwner = 0;
                    SDPObserver.this.connection.offerAnswering = false;
                }
            });
        }

        @Override // com.webrtc.SdpObserver
        public void onCreateFailure(String str) {
            PeerConnectionClient.this.reportError("createSDP error: " + str);
            if (this.type) {
                RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_CREATER_OFFER_FAILED, "SDPObserver-onCreateFailure", -1, RtcLogCapturer.getErrorInfo(str));
            } else {
                RtcLogCapturer.reportLog(RtcLogEvent.SUBSCRIBER_CREATER_ANSWER_FAILED, "SDPObserver-onCreateFailure", -1, RtcLogCapturer.getErrorInfo(str));
            }
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.PEERCONNECTION_CREATE_ERROR);
            if (PeerConnectionClient.this.events != null) {
                String str2 = "onCreateFailure : " + str;
                if (this.connection.offerAnswerOwner == 1) {
                    PeerConnectionClient.this.events.onShareScreenStart(-2, str2);
                } else if (this.connection.offerAnswerOwner == 2) {
                    PeerConnectionClient.this.events.onShareScreenStop(-2, str2);
                }
            }
        }

        @Override // com.webrtc.SdpObserver
        public void onSetFailure(String str) {
            PeerConnectionClient.this.reportError("setSDP error: " + str);
            if (this.type) {
                if (this.peerConnection.getRemoteDescription() == null) {
                    RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_FAILED, "SDPObserver-onSetFailure", -1, RtcLogCapturer.getErrorInfo(str));
                } else {
                    RtcLogCapturer.reportLog(RtcLogEvent.PUBLISHER_SET_REMOTE_SDP_FAILED, "SDPObserver-onSetFailure", -1, RtcLogCapturer.getErrorInfo(str));
                }
            } else if (this.peerConnection.getRemoteDescription() == null) {
                RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_SET_LOCAL_SDP_FAILED, "SDPObserver-onSetFailure", -1, RtcLogCapturer.getErrorInfo(str));
            } else {
                RtcLogCapturer.reportLog(RtcLogEvent.SUBCRIBER_SET_REMOTE_SDP_FAILED, "SDPObserver-onSetFailure", -1, RtcLogCapturer.getErrorInfo(str));
            }
            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.PEERCONNECTION_CREATE_ERROR);
            if (PeerConnectionClient.this.events != null) {
                String str2 = "setSDP error : " + str;
                if (this.connection.offerAnswerOwner == 1) {
                    PeerConnectionClient.this.events.onShareScreenStart(-3, str2);
                } else if (this.connection.offerAnswerOwner == 2) {
                    PeerConnectionClient.this.events.onShareScreenStop(-3, str2);
                }
            }
        }
    }

    public void sendData(ByteBuffer byteBuffer) {
        if (this.dcPublisher == null) {
            return;
        }
        this.dcPublisher.send(new DataChannel.Buffer(byteBuffer, true));
    }

    @Override // com.webrtc.DataChannel.Observer
    public void onMessage(DataChannel.Buffer buffer) {
        this.events.onRemoteData(buffer.data);
    }
}
