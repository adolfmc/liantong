package com.baidu.rtc;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.CommonDefine;
import com.baidu.rtc.ErrorInfo;
import com.baidu.rtc.config.Constraints;
import com.baidu.rtc.internal.BaiduRtcRoomImp;
import com.baidu.rtc.logreport.ErrorInfoReport;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.webrtc.IceCandidate;
import com.webrtc.Logging;
import com.webrtc.SessionDescription;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import okhttp3.ConnectionPool;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WebSocketChannel {
    private static final int NO_SET_VALID_ROOM_ID = -1160725808;
    private static final String TAG = "WebSocketChannel";
    static OkHttpClient.Builder httpclientBuilder = new OkHttpClient.Builder().connectionPool(new ConnectionPool(5, 1, TimeUnit.MINUTES)).addInterceptor(new Interceptor() { // from class: com.baidu.rtc.WebSocketChannel.1
        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder newBuilder = chain.request().newBuilder();
            newBuilder.addHeader("Sec-WebSocket-Protocol", "janus-protocol");
            try {
                return chain.proceed(newBuilder.build());
            } catch (Exception e) {
                throw new IOException("BRTC Connection error" + e);
            }
        }
    });
    static int kKeepAliveTimeMs = 25000;
    private JanusRTCInterface delegate;
    private Handler keepAliveTimer;
    private BigInteger mSessionId;
    private WebSocket mWebSocket;
    private ConcurrentHashMap<String, JanusTransaction> transactions = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BigInteger, JanusHandle> handles = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BigInteger, JanusHandle> feeds = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BigInteger, CopyOnWriteArrayList<CommonDefine.StreamInfo>> feedMidMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> commingFeedMidMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, JanusTransaction> ackTransactions = new ConcurrentHashMap<>();
    private long mUserId = (Build.SERIAL.hashCode() % 100000) + 78657895;
    private String mDisplayName = "Android-rtc";
    private String mRoomName = "";
    private String mVideoCodec = "h264";
    private String mAudioCodec = "opus";
    private boolean mAsPublisher = true;
    private boolean mAsListener = true;
    private boolean mHasVideo = true;
    private boolean mHasRemoteVideo = true;
    private boolean mHasAudio = true;
    private boolean mHasRemoteAudio = true;
    private boolean mHasData = false;
    private long mRoomId = -1160725808;
    private String mAppId = "";
    private String mTokenStr = "";
    private String mLiveStreamingServerURL = "";
    private String mLiveStreamingRole = "";
    private String mLiveStreamingMixTemplate = "";
    private String mMixLayoutPosition = "";
    private String mSDKTag = "";
    private BaiduRtcRoom.RtcLiveTransferMode mLiveStreamingTransferMode = BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION;
    private boolean mLiveStreamingMix = false;
    private boolean mLiveStreamingRecording = false;
    private String mLiveStreamingServerURLSecond = "";
    private String mLiveStreamingMixTemplateSecond = "";
    private BaiduRtcRoom.RtcLiveTransferMode mLiveStreamingTransferModeSecond = BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION;
    private boolean mLiveStreamingMixSecond = false;
    private boolean mLiveStreamingRecordingSecond = false;
    private int mConnectionTimeoutMs = 10000;
    private int mReadTimeoutMs = 10000;
    private int serverAckTimeout = 5000;
    private int pingInterval = 5000;
    public boolean mAutoPublish = true;
    public boolean mAutoSubScribe = true;
    private String mRtcServerUrl = "";
    private String mMediaServerIP = "";
    private boolean mIsKcpMode = false;
    private boolean mIsEnableErrorInfoMonitor = true;
    volatile BaiduRtcRoom.UserList mUserList = null;
    private boolean mSubscriberJoined = false;
    public BigInteger mSubscriberHandleId = null;
    private CommonDefine.StreamActionQueue pendsActionQueue = new CommonDefine.StreamActionQueue();
    private boolean isInSubscribeSdpConfigureIng = false;
    private ConcurrentHashMap<BigInteger, BaiduRtcRoom.RtcRoomUserInfo> mUserInfoList = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BigInteger, Boolean> mInSubscribingMap = new ConcurrentHashMap<>();
    private boolean isPartSdpMode = true;
    private String subscribePartialSdp = "";
    private String completeSdp = "";
    private boolean isAudioLevelEnable = true;
    private int audioLevelTopCount = 3;
    private boolean enableMultistream = true;
    private BaiduRtcRoom.KeyAgreementProtocol keyAgreementProtocol = BaiduRtcRoom.KeyAgreementProtocol.BRTC_DLTS;
    private BaiduRtcRoom.BdRtcRoomMode mRoomMode = BaiduRtcRoom.BdRtcRoomMode.BDRTC_ROOM_NORMAL;
    private Runnable fireKeepAlive = new Runnable() { // from class: com.baidu.rtc.WebSocketChannel.57
        @Override // java.lang.Runnable
        public void run() {
            WebSocketChannel.this.keepAlive();
            WebSocketChannel.this.mHandler.postDelayed(WebSocketChannel.this.fireKeepAlive, WebSocketChannel.kKeepAliveTimeMs);
        }
    };
    private Runnable serverKeepAliveTimeout = new Runnable() { // from class: com.baidu.rtc.WebSocketChannel.58
        @Override // java.lang.Runnable
        public void run() {
            if (WebSocketChannel.this.delegate != null) {
                WebSocketChannel.this.delegate.onServerAckTimeout();
            }
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public WebSocketChannel() {
        this.keepAliveTimer = null;
        this.keepAliveTimer = new Handler(Looper.getMainLooper());
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void setRoomName(String str) {
        this.mRoomName = str;
    }

    public void setVideoCodec(String str) {
        this.mVideoCodec = str.toLowerCase();
    }

    public void setAudioCodec(String str) {
        this.mAudioCodec = str;
    }

    public void setAsPublisher(boolean z) {
        this.mAsPublisher = z;
    }

    public void setAsListener(boolean z) {
        this.mAsListener = z;
    }

    public void setHasVideo(boolean z) {
        this.mHasVideo = z;
    }

    public void setHasRemoteVideo(boolean z) {
        this.mHasRemoteVideo = z;
    }

    public void setHasAudio(boolean z) {
        this.mHasAudio = z;
    }

    public void setHasRemoteAudio(boolean z) {
        this.mHasRemoteAudio = z;
    }

    public void setHasData(boolean z) {
        this.mHasData = z;
    }

    public void setRoomId(int i) {
        this.mRoomId = i;
    }

    public long getRoomId() {
        return this.mRoomId;
    }

    public void setAppId(String str) {
        this.mAppId = str;
    }

    public void setLssURL(String str) {
        this.mLiveStreamingServerURL = str;
    }

    public void setLssMixTemplate(String str) {
        this.mLiveStreamingMixTemplate = str;
    }

    public void setLssTransferMode(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        this.mLiveStreamingTransferMode = rtcLiveTransferMode;
    }

    public void setMixing(boolean z) {
        this.mLiveStreamingMix = z;
    }

    public void setRecording(boolean z) {
        this.mLiveStreamingRecording = z;
    }

    public void setLssRole(String str) {
        this.mLiveStreamingRole = str;
    }

    public void setLssConfigSecond(String str, boolean z, boolean z2, String str2, BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        this.mLiveStreamingServerURLSecond = str;
        this.mLiveStreamingMixSecond = z;
        this.mLiveStreamingRecordingSecond = z2;
        this.mLiveStreamingMixTemplateSecond = str2;
        this.mLiveStreamingTransferModeSecond = rtcLiveTransferMode;
    }

    public void setMixLayoutPosition(String str) {
        this.mMixLayoutPosition = str;
    }

    public void setSDK(String str) {
        this.mSDKTag = str;
    }

    public void setMediaServerIP(String str) {
        this.mMediaServerIP = str;
    }

    public void setIsKcpMode(boolean z) {
        this.mIsKcpMode = z;
    }

    public void setConnectionTimeoutMs(int i) {
        this.mConnectionTimeoutMs = i;
    }

    public void setReadTimeoutMs(int i) {
        this.mReadTimeoutMs = i;
    }

    public void setAutoPublish(boolean z) {
        this.mAutoPublish = z;
    }

    public void setAutoSubScribe(boolean z) {
        this.mAutoSubScribe = z;
    }

    public void setEnableErrorInfoMonitor(boolean z) {
        this.mIsEnableErrorInfoMonitor = z;
    }

    public void setEnableAudioLevel(boolean z) {
        this.isAudioLevelEnable = z;
    }

    public void setAudioLevelTopCount(int i) {
        this.audioLevelTopCount = i;
    }

    public void setEnableMultistream(boolean z) {
        this.enableMultistream = z;
    }

    public void setKeyAgreementProtocol(BaiduRtcRoom.KeyAgreementProtocol keyAgreementProtocol) {
        this.keyAgreementProtocol = keyAgreementProtocol;
    }

    public void setRoomMode(BaiduRtcRoom.BdRtcRoomMode bdRtcRoomMode) {
        this.mRoomMode = bdRtcRoomMode;
    }

    public boolean isBigRoomMode() {
        return this.mRoomMode == BaiduRtcRoom.BdRtcRoomMode.BDRTC_ROOM_BIG_ROOM;
    }

    public boolean getIsInSubscribing(BigInteger bigInteger) {
        return this.mInSubscribingMap.contains(bigInteger) && this.mInSubscribingMap.get(bigInteger).booleanValue();
    }

    public void setTokenStr(String str) {
        this.mTokenStr = str;
    }

    public void finalize() {
        this.mInSubscribingMap.clear();
        this.mHandler.removeCallbacks(this.fireKeepAlive);
        this.keepAliveTimer.removeCallbacks(this.serverKeepAliveTimeout);
        this.delegate = null;
        WebSocket webSocket = this.mWebSocket;
        if (webSocket != null) {
            webSocket.close(1001, "going away");
        }
    }

    public static String encodeURIComponent(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public void initConnection(String str, boolean z) {
        this.mRtcServerUrl = str;
        String str2 = str + "?appid=" + this.mAppId + "&roomname=" + encodeURIComponent(this.mRoomName) + "&uid=" + this.mUserId + "&token=" + this.mTokenStr;
        if (this.enableMultistream) {
            str2 = str2 + "&enable_multistream=true";
        }
        if (this.mIsKcpMode) {
            str2 = str2 + "&kcptun=true";
        }
        ErrorInfoReport.getInstance().setUserId(this.mUserId);
        if (z) {
            str2 = str2 + "&compulsive=true";
        }
        if (isBigRoomMode()) {
            str2 = str2 + "&largeroom=true";
        }
        if (BaiduRtcRoomImp.mbEnableDebugLog) {
            Logging.m5305d("WebSocketChannel", "connect url: " + str2);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.CONNECT_WS_SERVER, "initConnection", str2);
        OkHttpClient.Builder dns = httpclientBuilder.connectTimeout(this.mConnectionTimeoutMs, TimeUnit.MILLISECONDS).readTimeout(this.mReadTimeoutMs, TimeUnit.MILLISECONDS).writeTimeout(this.mReadTimeoutMs, TimeUnit.MILLISECONDS).pingInterval(this.pingInterval, TimeUnit.MILLISECONDS).dns(new Dns() { // from class: com.baidu.rtc.WebSocketChannel.2
            @Override // okhttp3.Dns
            public List<InetAddress> lookup(final String str3) {
                if ("mytestrtc.exp.bcelive.com".equals(str3)) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        arrayList.add(InetAddress.getByName("10.99.206.86"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return arrayList;
                }
                try {
                    FutureTask futureTask = new FutureTask(new Callable<List<InetAddress>>() { // from class: com.baidu.rtc.WebSocketChannel.2.1
                        @Override // java.util.concurrent.Callable
                        public List<InetAddress> call() throws Exception {
                            return Arrays.asList(InetAddress.getAllByName(str3));
                        }
                    });
                    new Thread(futureTask).start();
                    return (List) futureTask.get(WebSocketChannel.this.mConnectionTimeoutMs, TimeUnit.MILLISECONDS);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        });
        this.mWebSocket = (!(dns instanceof OkHttpClient.Builder) ? dns.build() : NBSOkHttp3Instrumentation.builderInit(dns)).newWebSocket(new Request.Builder().url(str2).build(), new WebSocketListener() { // from class: com.baidu.rtc.WebSocketChannel.3
            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, ByteString byteString) {
            }

            @Override // okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, Response response) {
                if (BaiduRtcRoomImp.mbEnableDebugLog) {
                    Logging.m5305d("WebSocketChannel", "onWebSocketOpened");
                }
                if (WebSocketChannel.this.delegate != null) {
                    WebSocketChannel.this.delegate.onWebSocketOpened();
                }
                WebSocketChannel.this.mHandler.postDelayed(WebSocketChannel.this.fireKeepAlive, WebSocketChannel.kKeepAliveTimeMs);
                if (WebSocketChannel.this.enableMultistream) {
                    if (WebSocketChannel.this.mAsPublisher) {
                        WebSocketChannel.this.publisherJoinRoomMultistream();
                    } else {
                        WebSocketChannel.this.subscribeFeedStreamMultistream(new ConcurrentHashMap());
                    }
                } else {
                    WebSocketChannel.this.createSession();
                }
                RtcLogCapturer.reportLog(RtcLogEvent.CONNECT_WS_SERVER_SUCCESS, "WebSocket-onOpen");
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str3) {
                WebSocketChannel.this.onMessage(str3);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, int i, String str3) {
                if (BaiduRtcRoomImp.mbEnableDebugLog) {
                    Logging.m5305d("WebSocketChannel", "onClosing code:" + i + " reason:" + str3);
                }
                if (WebSocketChannel.this.delegate != null) {
                    if (i == 3000 || i == 3001 || i == 1001) {
                        WebSocketChannel.this.delegate.onWebSocketClosing(str3);
                    } else {
                        WebSocketChannel.this.delegate.onWebSocketConnectError(str3);
                    }
                }
            }

            @Override // okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, int i, String str3) {
                if (BaiduRtcRoomImp.mbEnableDebugLog) {
                    Logging.m5305d("WebSocketChannel", "onClosed code:" + i + " reason:" + str3);
                }
                if (WebSocketChannel.this.delegate != null) {
                    WebSocketChannel.this.delegate.onConnectClosed();
                }
            }

            @Override // okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                if (th != null) {
                    Logging.m5304e("WebSocketChannel", "onFailure:" + th.toString());
                    th.printStackTrace();
                } else {
                    Logging.m5304e("WebSocketChannel", "onFailure");
                }
                if (WebSocketChannel.this.delegate == null) {
                    return;
                }
                if (th instanceof SocketTimeoutException) {
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onLoginTimeout();
                    }
                } else if (th instanceof NumberFormatException) {
                    Logging.m5304e("WebSocketChannel", "onFailure: java.lang.NumberFormatException");
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onWebSocketConnectError("onFailure: java.lang.NumberFormatException");
                    }
                } else if (th instanceof NullPointerException) {
                    Logging.m5304e("WebSocketChannel", "onFailure: java.lang.NullPointerException");
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onWebSocketConnectError("onFailure: java.lang.NullPointerException");
                    }
                } else if (!(th instanceof SSLException)) {
                    if (WebSocketChannel.this.mIsEnableErrorInfoMonitor) {
                        ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.SIGNAL_CHANNEL_CONNECTION_LOST);
                    }
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onLoginError();
                    }
                } else {
                    Logging.m5304e("WebSocketChannel", "onFailure: SSLException");
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onWebSocketConnectError("onFailure: SSLException");
                    }
                }
                RtcLogCapturer.reportLog(RtcLogEvent.CONNECT_WS_SERVER_FAIL, "WebSocket-onFailure", "-1", RtcLogCapturer.getErrorInfo(th != null ? th.toString() : ""));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMessage(String str) {
        JSONObject optJSONObject;
        BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode;
        long optLong;
        JSONArray optJSONArray;
        if (BaiduRtcRoomImp.mbEnableDebugLog) {
            Logging.m5305d("WebSocketChannel", "onMessage[" + str.length() + "]: " + str);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("janus");
            if (optString.equals("success")) {
                String optString2 = jSONObject.optString("transaction");
                JanusTransaction janusTransaction = this.transactions.get(optString2);
                if (janusTransaction != null && janusTransaction.success != null) {
                    janusTransaction.success.success(jSONObject);
                }
                this.transactions.remove(optString2);
                return;
            }
            if (optString.equals("error")) {
                String optString3 = jSONObject.optString("transaction");
                JanusTransaction janusTransaction2 = this.transactions.get(optString3);
                if (janusTransaction2 != null && janusTransaction2.error != null) {
                    janusTransaction2.error.error(jSONObject);
                }
                this.transactions.remove(optString3);
                if (jSONObject.has("error")) {
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("error");
                    int optInt = optJSONObject2.optInt("code");
                    String optString4 = optJSONObject2.optString("reason");
                    if (optString4.contains("Room is disbanded")) {
                        if (this.delegate != null) {
                            this.delegate.onRoomDisbanded();
                        }
                    } else if (optString4.contains("User is kick out") && this.delegate != null) {
                        this.delegate.onUserKickOff(this.mUserId);
                    }
                    RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_ERROR_EVENT, "Websocket-error", Integer.valueOf(optInt), optString4);
                }
            } else if (optString.equals("ack")) {
                String optString5 = jSONObject.optString("transaction");
                JanusTransaction janusTransaction3 = this.ackTransactions.get(optString5);
                if (janusTransaction3 != null && janusTransaction3.success != null) {
                    janusTransaction3.success.success(jSONObject);
                }
                this.ackTransactions.remove(optString5);
            } else if (optString.equals("timeout")) {
                Logging.m5304e("WebSocketChannel", "KeepAlvie Timeout");
                this.mHandler.removeCallbacks(this.fireKeepAlive);
                this.keepAliveTimer.removeCallbacks(this.serverKeepAliveTimeout);
                if (this.mIsEnableErrorInfoMonitor) {
                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.KEEPALIVE_TIMEOUT);
                }
                if (this.delegate != null) {
                    this.delegate.onWebSocketConnectError("timeoout");
                }
                RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_HEART_TIMEOUT, "WebSocket-timeout");
            } else if (optString.equals("slowlink")) {
                this.delegate.onSlowLink(jSONObject.optBoolean("uplink"), jSONObject.optInt("nacks"));
            } else if (optString.equals("sessionevent")) {
                if (jSONObject.has("recvdata")) {
                    JSONObject optJSONObject3 = jSONObject.optJSONObject("recvdata");
                    if (optJSONObject3.optBoolean("internal")) {
                        BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo = this.mUserInfoList.get(BigInteger.valueOf(optJSONObject3.optLong("from")));
                        if (rtcRoomUserInfo != null) {
                            rtcRoomUserInfo.attribute = optJSONObject3.optString("data");
                        }
                        this.delegate.onAttribute(new BigInteger(optJSONObject3.optString("from")), optJSONObject3.optString("data"));
                        return;
                    }
                    this.delegate.onMessage(new BigInteger(optJSONObject3.optString("from")), optJSONObject3.optString("data"));
                } else if (jSONObject.has("userevent")) {
                    JSONObject optJSONObject4 = jSONObject.optJSONObject("userevent");
                    if (optJSONObject4.has("joined")) {
                        BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo2 = new BaiduRtcRoom.RtcRoomUserInfo();
                        rtcRoomUserInfo2.userId = optJSONObject4.optLong("joined");
                        rtcRoomUserInfo2.userName = optJSONObject4.optString("display");
                        rtcRoomUserInfo2.attribute = optJSONObject4.optString("attribute");
                        this.mUserInfoList.put(BigInteger.valueOf(rtcRoomUserInfo2.userId), rtcRoomUserInfo2);
                        this.delegate.onUserJoinedRoom(new BigInteger(optJSONObject4.optString("joined")), optJSONObject4.optString("display"), "");
                    } else if (optJSONObject4.has("leaving")) {
                        this.mUserInfoList.remove(BigInteger.valueOf(optJSONObject4.optLong("leaving")));
                        this.delegate.onUserLeavingRoom(new BigInteger(optJSONObject4.optString("leaving")));
                    } else if (optJSONObject4.has("users") && (optJSONArray = optJSONObject4.optJSONArray("users")) != null && optJSONArray.length() > 0) {
                        int length = optJSONArray.length();
                        while (r3 <= length - 1) {
                            JSONObject optJSONObject5 = optJSONArray.optJSONObject(r3);
                            BigInteger bigInteger = new BigInteger(optJSONObject5.optString("id"));
                            String optString6 = optJSONObject5.optString("display");
                            BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo3 = new BaiduRtcRoom.RtcRoomUserInfo();
                            rtcRoomUserInfo3.userId = optJSONObject5.optLong("id");
                            rtcRoomUserInfo3.userName = optJSONObject5.optString("display");
                            rtcRoomUserInfo3.attribute = optJSONObject5.optString("attribute");
                            this.mUserInfoList.put(bigInteger, rtcRoomUserInfo3);
                            this.delegate.onUserJoinedRoom(bigInteger, optString6, "");
                            String optString7 = optJSONObject5.optString("attribute");
                            if (!optString7.isEmpty()) {
                                this.delegate.onAttribute(bigInteger, optString7);
                            }
                            r3++;
                        }
                    }
                } else if (!jSONObject.has("detached")) {
                    if (jSONObject.has("forwardconfigure")) {
                        JSONObject optJSONObject6 = jSONObject.optJSONObject("forwardconfigure");
                        if (Boolean.valueOf(optJSONObject6.optBoolean("self")).booleanValue()) {
                            optLong = this.mUserId;
                        } else {
                            optLong = optJSONObject6.optLong("id");
                        }
                        Boolean valueOf = Boolean.valueOf(optJSONObject6.optBoolean("video"));
                        Boolean valueOf2 = Boolean.valueOf(optJSONObject6.optBoolean("audio"));
                        if (valueOf.booleanValue() && valueOf2.booleanValue()) {
                            this.delegate.onUserDisShutUp(optLong);
                        } else if (!valueOf.booleanValue() && !valueOf2.booleanValue()) {
                            this.delegate.onUserShutUp(optLong);
                        }
                    } else if (jSONObject.has("userkickout")) {
                        this.delegate.onUserKickOff(jSONObject.optJSONObject("userkickout").optLong("id"));
                    } else if (jSONObject.has("bypass_event")) {
                        JSONObject optJSONObject7 = jSONObject.optJSONObject("bypass_event");
                        if (optJSONObject7.optString("level").contains("room")) {
                            rtcLiveTransferMode = BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION;
                        } else {
                            rtcLiveTransferMode = BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION;
                        }
                        String str2 = "";
                        if (optJSONObject7.has("rtmp")) {
                            JSONObject optJSONObject8 = optJSONObject7.optJSONObject("rtmp");
                            if (optJSONObject8.has("url")) {
                                str2 = optJSONObject8.optString("url");
                            }
                        }
                        String optString8 = optJSONObject7.optString("bypassStatus");
                        if (optString8.contains("success")) {
                            this.delegate.onLivePublishSucceed(rtcLiveTransferMode, str2);
                        } else if (optString8.contains("failure")) {
                            this.delegate.onLivePublishFailed(rtcLiveTransferMode, str2);
                        } else if (optString8.contains("break")) {
                            this.delegate.onLivePublishInterrupted(rtcLiveTransferMode, str2);
                        }
                    } else if (jSONObject.has("data")) {
                        onSessionEventDataMessage(jSONObject);
                    } else if (jSONObject.has("servercontrol") && (optJSONObject = jSONObject.optJSONObject("servercontrol")) != null) {
                        RtcLogCapturer.setSdkReportEnable(optJSONObject.optBoolean("enableTraceLog", false));
                    }
                }
            } else if (jSONObject.has("sender")) {
                JanusHandle janusHandle = this.handles.get(new BigInteger(jSONObject.optString("sender")));
                if (janusHandle == null) {
                    Logging.m5304e("WebSocketChannel", "missing handle");
                } else if (optString.equals("event")) {
                    JSONObject optJSONObject9 = jSONObject.optJSONObject("plugindata").optJSONObject("data");
                    if (optJSONObject9.optString("videoroom").equals("joined")) {
                        janusHandle.onJoined.onJoined(janusHandle);
                    } else if (optJSONObject9.optString("videoroom").equals("event") && optJSONObject9.has("error_code")) {
                        dealSignalError(jSONObject);
                        if (optJSONObject9.optInt("error_code") == 436) {
                            Logging.m5304e("WebSocketChannel", "error_code 436， so try again。");
                            this.mHandler.postDelayed(new Runnable() { // from class: com.baidu.rtc.WebSocketChannel.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    WebSocketChannel.this.publisherJoinRoomMultistream();
                                }
                            }, 2000L);
                        }
                    }
                    JSONArray optJSONArray2 = optJSONObject9.optJSONArray("publishers");
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        int length2 = optJSONArray2.length();
                        for (int i = 0; i <= length2 - 1; i++) {
                            JSONObject optJSONObject10 = optJSONArray2.optJSONObject(i);
                            BigInteger bigInteger2 = new BigInteger(optJSONObject10.optString("id"));
                            String optString9 = optJSONObject10.optString("display");
                            this.delegate.onComing(bigInteger2, optString9);
                            if (this.mAsListener && this.mAutoSubScribe) {
                                subscriberCreateHandle(bigInteger2, optString9);
                            }
                        }
                    }
                    String optString10 = optJSONObject9.optString("leaving");
                    if (!TextUtils.isEmpty(optString10) && !optString10.equals("ok")) {
                        this.delegate.onLeaving(janusHandle.handleId, new BigInteger(optString10));
                        JanusHandle janusHandle2 = this.feeds.get(new BigInteger(optString10));
                        if (janusHandle2 != null && janusHandle2.onLeaving != null) {
                            janusHandle2.onLeaving.onJoined(janusHandle2);
                        }
                    }
                    String optString11 = optJSONObject9.optString("unpublished");
                    if (!TextUtils.isEmpty(optString11)) {
                        if (!optString11.equals("ok") && !optString11.equals("self")) {
                            this.delegate.onLeaving(janusHandle.handleId, new BigInteger(optString11));
                            JanusHandle janusHandle3 = this.feeds.get(new BigInteger(optString11));
                            if (janusHandle3 != null && janusHandle3.onLeaving != null) {
                                janusHandle3.onLeaving.onJoined(janusHandle3);
                            }
                        } else if (optString11.equals("self") && optJSONObject9.has("servertrigger")) {
                            this.delegate.onHangUp(janusHandle.handleId);
                        }
                    }
                    String optString12 = optJSONObject9.optString("subscribed");
                    if (!TextUtils.isEmpty(optString12) && optString12.equals("ok")) {
                        updateSubscribeStreamInfos(optJSONObject9.optJSONArray("streams"));
                        ArrayList<BigInteger> parseFeedIdFromStream = parseFeedIdFromStream(optJSONObject9.optJSONArray("streams"));
                        for (int i2 = 0; i2 < parseFeedIdFromStream.size(); i2++) {
                            this.feeds.put(parseFeedIdFromStream.get(i2), janusHandle);
                        }
                        String optString13 = jSONObject.optString("transaction");
                        JanusTransaction janusTransaction4 = this.transactions.get(optString13);
                        if (janusTransaction4 != null && janusTransaction4.success != null) {
                            janusTransaction4.success.success(jSONObject);
                        }
                        this.transactions.remove(optString13);
                    }
                    String optString14 = optJSONObject9.optString("unsubscribed");
                    if (!TextUtils.isEmpty(optString14) && optString14.equals("ok")) {
                        String optString15 = jSONObject.optString("transaction");
                        JanusTransaction janusTransaction5 = this.transactions.get(optString15);
                        if (janusTransaction5 != null && janusTransaction5.success != null) {
                            janusTransaction5.success.success(jSONObject);
                        }
                        this.transactions.remove(optString15);
                    }
                    String optString16 = optJSONObject9.optString("started");
                    if (!optString16.isEmpty()) {
                        this.isInSubscribeSdpConfigureIng = false;
                        dealPendingStreamAction();
                        RtcLogCapturer.reportEvent(RtcLogEvent.WS_RECV_SUBSCRIBER_ANSWER_SUCCESS, getLongValue(janusHandle.feedId), "WebSocket-recv-started", optString16);
                    }
                    JSONObject optJSONObject11 = jSONObject.optJSONObject("jsep");
                    if (optJSONObject11 != null) {
                        janusHandle.onRemoteJsep.onRemoteJsep(janusHandle, optJSONObject11);
                    }
                } else if (optString.equals("detached")) {
                    janusHandle.onLeaving.onJoined(janusHandle);
                    RtcLogCapturer.reportEvent(RtcLogEvent.WS_RECV_DETACHED, getLongValue(janusHandle.feedId), "Websocket-detached", janusHandle.handleId);
                } else if (optString.equals("webrtcup")) {
                    String optString17 = jSONObject.optString("clientUdpIp");
                    if (TextUtils.isEmpty(optString17)) {
                        optString17 = jSONObject.optString("clientTcpIp");
                    }
                    this.delegate.onWebrtcUp(janusHandle.handleId, optString17);
                } else if (optString.equals("media")) {
                    boolean z = jSONObject.getBoolean("receiving");
                    String string = jSONObject.getString("type");
                    if (this.delegate != null) {
                        this.delegate.onMediaStreamingEvent(janusHandle.handleId, string.contains("video") ? 1 : 0, z);
                    }
                } else if (optString.equals("hangup")) {
                    if (this.delegate != null) {
                        this.delegate.onHangUp(janusHandle.handleId);
                        RtcLogCapturer.reportEvent(RtcLogEvent.WS_RECV_HANGUP, getLongValue(janusHandle.feedId), "onHangUp", janusHandle.handleId);
                    }
                } else if ("stream_leave".equals(optString) && this.delegate != null) {
                    this.delegate.onSubscribeUserStreamLeave(new BigInteger(jSONObject.optString("feed")));
                }
            } else if (optString.equals("setuped")) {
                onSetupedMessage(jSONObject);
            } else if ("event".equals(optString)) {
                dealSignalError(jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void dealSignalError(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (jSONObject.has("plugindata") && (optJSONObject = jSONObject.optJSONObject("plugindata")) != null && optJSONObject.has("data") && (optJSONObject2 = optJSONObject.optJSONObject("data")) != null && optJSONObject2.has("error")) {
            String optString = jSONObject.optString("transaction");
            JanusTransaction janusTransaction = this.transactions.get(optString);
            if (janusTransaction != null && janusTransaction.eventError != null) {
                janusTransaction.eventError.error(optJSONObject2);
            }
            this.transactions.remove(optString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealPendingStreamAction() {
        CommonDefine.StreamAction popAction = this.pendsActionQueue.popAction();
        if (popAction != null) {
            if (popAction.type == CommonDefine.StreamActionType.kSubscribe) {
                subscribeFeedStreamMultistream(popAction.streamInfos);
            } else if (popAction.type == CommonDefine.StreamActionType.kUnSubscribe) {
                unsubscribeFeedMultistream(popAction.streamInfos);
            }
        }
    }

    private void updateSubscribeStreamInfos(JSONArray jSONArray) {
        boolean z;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            CommonDefine.StreamInfo streamInfo = new CommonDefine.StreamInfo();
            streamInfo.mid = optJSONObject.optString("mid");
            streamInfo.type = optJSONObject.optString("type");
            streamInfo.active = optJSONObject.optBoolean("active");
            streamInfo.ready = optJSONObject.optBoolean("ready");
            streamInfo.description = optJSONObject.optString("description");
            if (TextUtils.isEmpty(streamInfo.description)) {
                streamInfo.description = optJSONObject.optString("feed_stream_description");
            }
            streamInfo.feedId = new BigInteger(optJSONObject.optString("feed_id"));
            streamInfo.feedMid = optJSONObject.optString("feed_mid");
            streamInfo.feedDisplay = optJSONObject.optString("feed_display");
            if (this.feedMidMap.containsKey(streamInfo.feedId)) {
                CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList = this.feedMidMap.get(streamInfo.feedId);
                int i2 = 0;
                while (true) {
                    if (i2 >= copyOnWriteArrayList.size()) {
                        z = false;
                        break;
                    } else if (copyOnWriteArrayList.get(i2).mid.equals(streamInfo.mid)) {
                        copyOnWriteArrayList.set(i2, streamInfo);
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    copyOnWriteArrayList.add(streamInfo);
                }
            } else {
                CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
                copyOnWriteArrayList2.add(streamInfo);
                this.feedMidMap.put(streamInfo.feedId, copyOnWriteArrayList2);
            }
        }
    }

    private void onSetupedMessage(JSONObject jSONObject) {
        String optString = jSONObject.optJSONObject("data").optString("ptype");
        if ("subscriber".equals(optString)) {
            onSubscriberSetuped(jSONObject);
        } else if ("publisher".equals(optString)) {
            onPublisherSetuped(jSONObject);
        }
    }

    private void onPublisherSetuped(JSONObject jSONObject) {
        JSONArray jSONArray;
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.mSessionId = new BigInteger(optJSONObject.optString("session_id"));
        ErrorInfoReport.getInstance().setSessionId(getLongValue(this.mSessionId));
        BigInteger bigInteger = new BigInteger(optJSONObject.optString("handle_id"));
        this.mRoomId = optJSONObject.optLong("room");
        ErrorInfoReport.getInstance().setRoomId(this.mRoomId);
        RtcLogCapturer.setSdkReportEnable(optJSONObject.optBoolean("enableTraceLog", false));
        JanusHandle janusHandle = new JanusHandle();
        janusHandle.handleId = bigInteger;
        janusHandle.onLeaving = new OnJoined() { // from class: com.baidu.rtc.WebSocketChannel.5
            @Override // com.baidu.rtc.OnJoined
            public void onJoined(JanusHandle janusHandle2) {
            }
        };
        janusHandle.onRemoteJsep = new OnRemoteJsep() { // from class: com.baidu.rtc.WebSocketChannel.6
            @Override // com.baidu.rtc.OnRemoteJsep
            public void onRemoteJsep(JanusHandle janusHandle2, JSONObject jSONObject2) {
                WebSocketChannel.this.delegate.onPublisherRemoteJsep(janusHandle2.handleId, jSONObject2);
                RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_PUBLISHER_ANSWER, "onPublisherSetuped-onRemoteJsep");
            }
        };
        this.handles.put(janusHandle.handleId, janusHandle);
        this.delegate.onPublisherJoined(janusHandle.handleId, this.mRoomId);
        if (optJSONObject.has("publishers")) {
            jSONArray = optJSONObject.optJSONArray("publishers");
            onPublisherMessage(jSONArray);
        } else {
            jSONArray = null;
        }
        RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_PUBLISHER_SETUPED, "onPublisherSetuped", this.mSessionId, bigInteger, Long.valueOf(this.mRoomId), jSONArray);
    }

    private ArrayList<BigInteger> parseFeedIdFromStream(JSONArray jSONArray) {
        ArrayList<BigInteger> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            BigInteger bigInteger = new BigInteger(jSONArray.optJSONObject(i).optString("feed_id"));
            if (!arrayList.contains(bigInteger)) {
                arrayList.add(bigInteger);
            }
        }
        return arrayList;
    }

    private void onSubscriberSetuped(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        BigInteger bigInteger = new BigInteger(optJSONObject.optString("handle_id"));
        this.mSubscriberHandleId = bigInteger;
        JSONArray optJSONArray = optJSONObject.optJSONArray("streams");
        if (optJSONArray != null) {
            updateSubscribeStreamInfos(optJSONArray);
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("publishers");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            onPublisherMessage(optJSONObject.optJSONArray("publishers"));
        }
        RtcLogCapturer.setSdkReportEnable(optJSONObject.optBoolean("enableTraceLog", false));
        JanusHandle janusHandle = new JanusHandle();
        janusHandle.handleId = bigInteger;
        janusHandle.onRemoteJsep = new OnRemoteJsep() { // from class: com.baidu.rtc.WebSocketChannel.7
            @Override // com.baidu.rtc.OnRemoteJsep
            public void onRemoteJsep(JanusHandle janusHandle2, JSONObject jSONObject2) {
                boolean optBoolean = jSONObject2.optBoolean("partial");
                if (WebSocketChannel.this.isPartSdpMode) {
                    if (optBoolean) {
                        WebSocketChannel.this.subscribePartialSdp = jSONObject2.optString("sdp");
                    } else {
                        WebSocketChannel.this.subscribePartialSdp = "";
                    }
                    if (TextUtils.isEmpty(WebSocketChannel.this.completeSdp)) {
                        WebSocketChannel.this.completeSdp = jSONObject2.optString("sdp");
                    } else {
                        WebSocketChannel webSocketChannel = WebSocketChannel.this;
                        webSocketChannel.completeSdp = SdpPrefer.partialSdpCombine(webSocketChannel.completeSdp, jSONObject2.optString("sdp"));
                    }
                    try {
                        jSONObject2.put("sdp", WebSocketChannel.this.completeSdp);
                    } catch (JSONException e) {
                        Logging.m5304e("WebSocketChannel", "onRemoteJsep error : " + e.getMessage());
                    }
                }
                WebSocketChannel.this.delegate.subscriberHandleRemoteJsep(janusHandle2.handleId, jSONObject2);
                RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_SUBSCRIBER_OFFER, "onSubscriberSetuped-onRemoteJsep", Boolean.valueOf(optBoolean));
            }
        };
        janusHandle.onLeaving = new OnJoined() { // from class: com.baidu.rtc.WebSocketChannel.8
            @Override // com.baidu.rtc.OnJoined
            public void onJoined(JanusHandle janusHandle2) {
            }
        };
        this.handles.put(janusHandle.handleId, janusHandle);
        if (optJSONArray != null) {
            ArrayList<BigInteger> parseFeedIdFromStream = parseFeedIdFromStream(optJSONArray);
            for (int i = 0; i < parseFeedIdFromStream.size(); i++) {
                this.feeds.put(parseFeedIdFromStream.get(i), janusHandle);
                JanusRTCInterface janusRTCInterface = this.delegate;
                if (janusRTCInterface != null) {
                    janusRTCInterface.onSubscribeUser(this.mSubscriberHandleId, parseFeedIdFromStream.get(i));
                }
            }
        }
        try {
            if (jSONObject.has("jsep")) {
                if (this.isPartSdpMode) {
                    this.completeSdp = jSONObject.getJSONObject("jsep").optString("sdp");
                }
                if (this.delegate != null) {
                    this.delegate.subscriberHandleRemoteJsep(janusHandle.handleId, jSONObject.getJSONObject("jsep"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_SUBSCRIBER_SETUPED, "onSubscriberSetuped", bigInteger, optJSONArray);
    }

    private void onPublisherMessage(JSONArray jSONArray) {
        ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap = new ConcurrentHashMap<>();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                BigInteger bigInteger = new BigInteger(optJSONObject.optString("id"));
                ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
                ArrayList<CommonDefine.StreamInfo> arrayList2 = new ArrayList<>();
                concurrentHashMap.put(bigInteger, arrayList2);
                String optString = optJSONObject.optString("display");
                if (optJSONObject.has("streams")) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("streams");
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        CommonDefine.StreamInfo streamInfo = new CommonDefine.StreamInfo();
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        streamInfo.mid = optJSONObject2.optString("mid");
                        streamInfo.type = optJSONObject2.optString("type");
                        streamInfo.description = optJSONObject2.optString("description");
                        streamInfo.active = optJSONObject2.optBoolean("active");
                        arrayList.add(streamInfo);
                        if (this.mHasRemoteAudio && "audio".equals(streamInfo.type)) {
                            arrayList2.add(streamInfo);
                        }
                        if (this.mHasRemoteVideo && "video".equals(streamInfo.type)) {
                            arrayList2.add(streamInfo);
                        }
                    }
                    if (arrayList2.size() == 0) {
                        concurrentHashMap.remove(bigInteger);
                    }
                }
                this.commingFeedMidMap.put(bigInteger, arrayList);
                this.delegate.onComing(bigInteger, optString);
            }
        }
        if (this.mAsListener && this.mAutoSubScribe) {
            subscribeFeedStreamMultistream(concurrentHashMap);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_PUBLISHEREVENT_JOINED, "onPublisherMessage", jSONArray);
    }

    public ArrayList<CommonDefine.StreamInfo> getComingStreams(BigInteger bigInteger) {
        return this.commingFeedMidMap.get(bigInteger);
    }

    private void subscribeFeedStreamMultistream(ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap, boolean z) {
        BigInteger bigInteger;
        if (concurrentHashMap == null || !concurrentHashMap.isEmpty()) {
            if (this.mSubscriberJoined) {
                if (!this.isInSubscribeSdpConfigureIng && (bigInteger = this.mSubscriberHandleId) != null) {
                    this.isInSubscribeSdpConfigureIng = true;
                    subscriberStreamMultistream(bigInteger, concurrentHashMap, z);
                    return;
                }
                this.pendsActionQueue.addAction(CommonDefine.StreamActionType.kSubscribe, concurrentHashMap);
                return;
            }
            this.mSubscriberJoined = true;
            this.isInSubscribeSdpConfigureIng = true;
            subscriberCreateHandleMultistream(concurrentHashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscribeFeedStreamMultistream(ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap) {
        subscribeFeedStreamMultistream(concurrentHashMap, false);
    }

    private void onStreamEventMessage(JSONObject jSONObject) {
        BigInteger bigInteger = new BigInteger(jSONObject.optString("id"));
        jSONObject.optString("display");
        jSONObject.optString("attribute");
        try {
            if (jSONObject.has("added")) {
                subscribeFeedStreamMultistream(parseFeedStreamInfo(bigInteger, jSONObject.getJSONArray("added")), true);
            }
            jSONObject.has("updated");
            if (jSONObject.has("removed")) {
                unsubscribeFeedMultistream(parseFeedStreamInfo(bigInteger, jSONObject.getJSONArray("removed")));
            }
        } catch (JSONException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("onStreamEventMessage error format");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            Logging.m5304e("WebSocketChannel", sb.toString());
        }
    }

    private ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> parseFeedStreamInfo(BigInteger bigInteger, JSONArray jSONArray) {
        ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap = new ConcurrentHashMap<>();
        ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString("mid");
                String optString2 = jSONObject.optString("type");
                String optString3 = jSONObject.optString("description");
                boolean optBoolean = jSONObject.optBoolean("active");
                CommonDefine.StreamInfo streamInfo = new CommonDefine.StreamInfo();
                streamInfo.mid = optString;
                streamInfo.type = optString2;
                streamInfo.description = optString3;
                streamInfo.active = optBoolean;
                arrayList.add(streamInfo);
            } catch (JSONException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("updateFeedStreamInfo error format");
                sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
                sb.append(" ");
                sb.append(bigInteger);
                Logging.m5304e("WebSocketChannel", sb.toString());
            }
        }
        concurrentHashMap.put(bigInteger, arrayList);
        return concurrentHashMap;
    }

    private void onSessionEventDataMessage(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        String optString = optJSONObject.optString("videoroom");
        if (optString.equals("publisherevent")) {
            if (optJSONObject.has("publishers")) {
                onPublisherMessage(optJSONObject.optJSONArray("publishers"));
            } else if (optJSONObject.has("leaving")) {
                BigInteger bigInteger = new BigInteger(optJSONObject.optJSONObject("leaving").optString("id"));
                if (this.commingFeedMidMap.containsKey(bigInteger)) {
                    this.commingFeedMidMap.remove(bigInteger);
                }
                if (this.mSubscriberHandleId != null && this.feeds.get(bigInteger) != null) {
                    subscriberOnLeavingMultistream(bigInteger);
                }
                RtcLogCapturer.reportEvent(RtcLogEvent.WS_RECV_PUBLISHEREVENT_LEAVING, getLongValue(bigInteger), "onSessionEventDataMessage", bigInteger);
            } else if (optJSONObject.has("unpublished")) {
                BigInteger bigInteger2 = new BigInteger(optJSONObject.optJSONObject("unpublished").optString("id"));
                if (this.commingFeedMidMap.containsKey(bigInteger2)) {
                    this.commingFeedMidMap.remove(bigInteger2);
                }
                if (this.mSubscriberHandleId != null && this.feeds.get(bigInteger2) != null) {
                    subscriberOnLeavingMultistream(bigInteger2);
                }
                RtcLogCapturer.reportEvent(RtcLogEvent.WS_RECV_PUBLISHEREVENT_UNPUBLISHED, getLongValue(bigInteger2), "onSessionEventDataMessage", bigInteger2);
            }
        } else if (optString.equals("streamevent")) {
            onStreamEventMessage(optJSONObject);
        }
    }

    public void publisherCreateOfferMultistream(BigInteger bigInteger, SessionDescription sessionDescription, ArrayList<CommonDefine.StreamInfo> arrayList) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = null;
        try {
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("session_id", this.mSessionId);
            jSONObject.putOpt("handle_id", bigInteger);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("request", "configure");
            jSONObject2.putOpt("display", this.mDisplayName);
            if (arrayList.size() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < arrayList.size(); i++) {
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        CommonDefine.StreamInfo streamInfo = arrayList.get(i);
                        jSONObject3.put("mid", streamInfo.mid);
                        jSONObject3.put("description", streamInfo.description);
                        jSONArray2.put(jSONObject3);
                    } catch (JSONException e) {
                        e = e;
                        jSONArray = jSONArray2;
                        e.printStackTrace();
                        sendMessage(jSONObject);
                        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_PUBLISHER_CONFIGURE, "publisherCreateOfferMultistream", jSONArray, "offer");
                    }
                }
                jSONObject2.put("streams", jSONArray2);
                jSONArray = jSONArray2;
            }
            jSONObject.putOpt("body", jSONObject2);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.putOpt("type", "offer");
            jSONObject4.putOpt("sdp", sessionDescription.description);
            jSONObject.putOpt("jsep", jSONObject4);
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.9
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject5) {
                    int optInt = jSONObject5.optInt("error_code");
                    String optString = jSONObject5.optString("error");
                    long optLong = jSONObject5.optLong("sender", 0L);
                    if (WebSocketChannel.this.delegate != null) {
                        if (optInt == 457) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 452, optString);
                            WebSocketChannel.this.mHandler.postDelayed(new Runnable() { // from class: com.baidu.rtc.WebSocketChannel.9.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    WebSocketChannel.this.publisherJoinRoomMultistream();
                                }
                            }, 1000L);
                        } else if (optInt == 434) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 453, optString);
                        } else if (optInt == 429) {
                        } else {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                        }
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            jSONObject.putOpt("transaction", randomString);
        } catch (JSONException e2) {
            e = e2;
        }
        sendMessage(jSONObject);
        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_PUBLISHER_CONFIGURE, "publisherCreateOfferMultistream", jSONArray, "offer");
    }

    private void unSubscribeMultistream(final BigInteger bigInteger, final ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        final boolean z = true;
        for (Map.Entry<BigInteger, ArrayList<CommonDefine.StreamInfo>> entry : concurrentHashMap.entrySet()) {
            BigInteger key = entry.getKey();
            if (this.feedMidMap.containsKey(key)) {
                CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList = this.feedMidMap.get(key);
                CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
                if (copyOnWriteArrayList != null) {
                    Iterator<CommonDefine.StreamInfo> it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        CommonDefine.StreamInfo next = it.next();
                        if (!next.removed) {
                            copyOnWriteArrayList2.add(next);
                        }
                    }
                    ArrayList<CommonDefine.StreamInfo> value = entry.getValue();
                    if (value.size() > 0 && value.size() < copyOnWriteArrayList2.size()) {
                        z = false;
                    }
                }
            }
            if (z) {
                this.feeds.remove(entry.getKey());
            }
        }
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.10
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject2) {
                if (z) {
                    for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
                        WebSocketChannel.this.delegate.onLeaving(WebSocketChannel.this.mSubscriberHandleId, (BigInteger) entry2.getKey());
                        WebSocketChannel.this.feeds.remove(entry2.getKey());
                        WebSocketChannel.this.removeUnsubscriberByFeedId((BigInteger) entry2.getKey());
                    }
                } else {
                    for (Map.Entry entry3 : concurrentHashMap.entrySet()) {
                        WebSocketChannel.this.delegate.onRemovedStreams(WebSocketChannel.this.mSubscriberHandleId, (BigInteger) entry3.getKey(), (ArrayList) entry3.getValue());
                        CopyOnWriteArrayList copyOnWriteArrayList3 = (CopyOnWriteArrayList) WebSocketChannel.this.feedMidMap.get((BigInteger) entry3.getKey());
                        if (copyOnWriteArrayList3 != null) {
                            Iterator it2 = copyOnWriteArrayList3.iterator();
                            while (it2.hasNext()) {
                                CommonDefine.StreamInfo streamInfo = (CommonDefine.StreamInfo) it2.next();
                                Iterator it3 = ((ArrayList) entry3.getValue()).iterator();
                                while (it3.hasNext()) {
                                    if (TextUtils.equals(streamInfo.feedMid, ((CommonDefine.StreamInfo) it3.next()).mid)) {
                                        streamInfo.removed = true;
                                        Logging.m5305d("WebSocketChannel", "unsubscribe onRemoved = streamInfo.mid : " + streamInfo.mid + " feedMid : " + streamInfo.feedMid + " type : " + streamInfo.type);
                                    }
                                }
                            }
                        }
                    }
                }
                RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_UN_SUBSCRIBE_SUCCESS, "unSubscribeMultistream-success", "ok");
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.11
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject2) {
                WebSocketChannel.this.isInSubscribeSdpConfigureIng = false;
                WebSocketChannel.this.dealPendingStreamAction();
                JSONObject optJSONObject = jSONObject2.optJSONObject("error");
                String optString = optJSONObject.optString("reason");
                int optInt = optJSONObject.optInt("code");
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.handleId = bigInteger.intValue();
                ArrayList arrayList = new ArrayList();
                ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
                if (concurrentHashMap2 != null) {
                    for (Map.Entry entry2 : concurrentHashMap2.entrySet()) {
                        arrayList.add(Long.valueOf(((BigInteger) entry2.getKey()).longValue()));
                    }
                }
                errorInfo.feedIds = arrayList;
                errorInfo.statusCode = optInt;
                errorInfo.msg = optString;
                errorInfo.errorType = ErrorInfo.ErrorType.ERROR_USER_UNSUBSCRIBE;
                WebSocketChannel.this.dealError(errorInfo);
                RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_UN_SUBSCRIBE_FAIL, "unSubscribeMultistream-error", Integer.valueOf(optInt), optString);
            }
        };
        janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.12
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject2) {
                WebSocketChannel.this.isInSubscribeSdpConfigureIng = false;
                WebSocketChannel.this.dealPendingStreamAction();
                int optInt = jSONObject2.optInt("error_code");
                String optString = jSONObject2.optString("error");
                long optLong = jSONObject2.optLong("sender", 0L);
                if (WebSocketChannel.this.delegate != null) {
                    if (optInt == 457) {
                        WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 457, optString);
                    } else if (optInt == 429) {
                        WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 458, optString);
                    } else if (optInt == 471) {
                        WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 459, optString);
                    } else {
                        WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                    }
                }
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
            jSONObject = new JSONObject();
            jSONObject.putOpt("request", "unsubscribe");
            jSONArray = new JSONArray();
        } catch (JSONException e) {
            e = e;
            jSONArray = null;
        }
        try {
            for (Map.Entry<BigInteger, ArrayList<CommonDefine.StreamInfo>> entry2 : concurrentHashMap.entrySet()) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.putOpt("id", entry2.getKey());
                if (entry2.getValue().size() > 0) {
                    JSONArray jSONArray3 = new JSONArray();
                    for (int i = 0; i < entry2.getValue().size(); i++) {
                        jSONArray3.put(i, entry2.getValue().get(i).mid);
                    }
                    jSONObject3.putOpt("mids", jSONArray3);
                }
                jSONArray.put(jSONObject3);
            }
            jSONObject.putOpt("feeds", jSONArray);
            jSONObject2.putOpt("body", jSONObject);
            jSONArray2 = jSONArray;
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            jSONArray2 = jSONArray;
            sendMessage(jSONObject2);
            RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_UN_SUBSCRIBE, "unSubscribeMultistream", jSONArray2);
        }
        sendMessage(jSONObject2);
        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_UN_SUBSCRIBE, "unSubscribeMultistream", jSONArray2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSession() {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.13
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                WebSocketChannel.this.mSessionId = new BigInteger(jSONObject.optJSONObject("data").optString("id"));
                ErrorInfoReport errorInfoReport = ErrorInfoReport.getInstance();
                WebSocketChannel webSocketChannel = WebSocketChannel.this;
                errorInfoReport.setSessionId(webSocketChannel.getLongValue(webSocketChannel.mSessionId));
                WebSocketChannel.this.mHandler.postDelayed(WebSocketChannel.this.fireKeepAlive, WebSocketChannel.kKeepAliveTimeMs);
                WebSocketChannel.this.publisherCreateHandle();
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.14
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("janus", "create");
            jSONObject.putOpt("transaction", randomString);
            if (!this.mSDKTag.isEmpty()) {
                jSONObject.putOpt("sdktag", this.mSDKTag);
            }
            if (!TextUtils.isEmpty(this.mMediaServerIP)) {
                jSONObject.putOpt("janusIp", this.mMediaServerIP);
            }
            jSONObject.putOpt("uri", this.mRtcServerUrl);
            jSONObject.putOpt("sessionevent", true);
            jSONObject.putOpt("userevent", true);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("media_antiweak", true);
            jSONObject2.putOpt("signal_antiweak", Boolean.valueOf(this.mIsKcpMode));
            jSONObject2.putOpt("version", Constraints.sdkVersion());
            jSONObject.putOpt("client_config_map", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publisherCreateHandle() {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.15
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                JanusHandle janusHandle = new JanusHandle();
                janusHandle.handleId = new BigInteger(jSONObject.optJSONObject("data").optString("id"));
                janusHandle.onJoined = new OnJoined() { // from class: com.baidu.rtc.WebSocketChannel.15.1
                    @Override // com.baidu.rtc.OnJoined
                    public void onJoined(JanusHandle janusHandle2) {
                        if (WebSocketChannel.this.delegate != null) {
                            WebSocketChannel.this.delegate.onPublisherJoined(janusHandle2.handleId, WebSocketChannel.this.mRoomId);
                        }
                    }
                };
                janusHandle.onRemoteJsep = new OnRemoteJsep() { // from class: com.baidu.rtc.WebSocketChannel.15.2
                    @Override // com.baidu.rtc.OnRemoteJsep
                    public void onRemoteJsep(JanusHandle janusHandle2, JSONObject jSONObject2) {
                        if (WebSocketChannel.this.delegate != null) {
                            WebSocketChannel.this.delegate.onPublisherRemoteJsep(janusHandle2.handleId, jSONObject2);
                        }
                    }
                };
                WebSocketChannel.this.handles.put(janusHandle.handleId, janusHandle);
                if (WebSocketChannel.this.delegate == null) {
                    return;
                }
                WebSocketChannel.this.delegate.onCreatedHandle(janusHandle.handleId, WebSocketChannel.this.mRoomId);
                if (WebSocketChannel.this.mRoomId != -1160725808) {
                    ErrorInfoReport.getInstance().setRoomId(WebSocketChannel.this.mRoomId);
                    WebSocketChannel webSocketChannel = WebSocketChannel.this;
                    webSocketChannel.createRoom(webSocketChannel.mRoomId, WebSocketChannel.this.mRoomName, janusHandle);
                    return;
                }
                WebSocketChannel.this.createRoomWithoutRoomID(janusHandle);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.16
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("janus", "attach");
            jSONObject.putOpt("plugin", "janus.plugin.videoroom");
            jSONObject.putOpt("transaction", randomString);
            jSONObject.putOpt("session_id", this.mSessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
    }

    private void sendMessage(JSONObject jSONObject) {
        WebSocket webSocket = this.mWebSocket;
        if (webSocket != null) {
            webSocket.send(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
        if (BaiduRtcRoomImp.mbEnableDebugLog) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            boolean z = jSONObject instanceof JSONObject;
            sb.append((!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).length());
            sb.append("]:");
            sb.append(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            Logging.m5305d("WebSocketChannel", sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publisherJoinRoomMultistream() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("version", "brtc_v2");
            jSONObject.putOpt("enable_multistream", true);
            jSONObject.putOpt("janus", "setup");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("plugin", "janus.plugin.videoroom");
            jSONObject2.putOpt("app_id", this.mAppId);
            jSONObject2.putOpt("room_name", this.mRoomName);
            jSONObject2.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject2.putOpt("display", this.mDisplayName);
            jSONObject2.putOpt("token", "no_token");
            jSONObject2.putOpt("ptype", "publisher");
            if (this.keyAgreementProtocol == BaiduRtcRoom.KeyAgreementProtocol.BRTC_SDES) {
                jSONObject2.putOpt("encryptmode", "sdes");
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("media_antiweak", true);
            jSONObject3.putOpt("signal_antiweak", Boolean.valueOf(this.mIsKcpMode));
            jSONObject3.putOpt("version", Constraints.sdkVersion());
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.putOpt("userevent", true);
            jSONObject4.putOpt("sessionevent", true);
            if (this.isPartSdpMode) {
                jSONObject4.putOpt("partialsdp", true);
            }
            if (!TextUtils.isEmpty(this.mMediaServerIP)) {
                jSONObject4.putOpt("janusIp", this.mMediaServerIP);
            }
            jSONObject4.putOpt("client_config_map", jSONObject3);
            jSONObject2.putOpt("session_config", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.putOpt("publishers", 1000);
            jSONObject5.putOpt("is_private", false);
            jSONObject5.putOpt("videocodec", "H264");
            jSONObject5.putOpt("audiocodec", this.mAudioCodec);
            jSONObject5.putOpt("playoutdelay_ext", true);
            JSONObject jSONObject6 = new JSONObject();
            JSONObject jSONObject7 = new JSONObject();
            if (!this.mLiveStreamingServerURL.isEmpty() && this.mLiveStreamingTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION) {
                jSONObject6.putOpt("url", this.mLiveStreamingServerURL);
                if (!this.mLiveStreamingMixTemplate.isEmpty()) {
                    jSONObject6.putOpt("mixTemplate", this.mLiveStreamingMixTemplate);
                }
                jSONObject5.putOpt("rtmp", jSONObject6);
            } else if (!this.mLiveStreamingServerURLSecond.isEmpty() && this.mLiveStreamingTransferModeSecond == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION) {
                jSONObject6.putOpt("url", this.mLiveStreamingServerURLSecond);
                if (!this.mLiveStreamingMixTemplateSecond.isEmpty()) {
                    jSONObject6.putOpt("mixTemplate", this.mLiveStreamingMixTemplateSecond);
                }
                jSONObject5.putOpt("rtmp", jSONObject6);
            }
            if (this.mLiveStreamingRecording) {
                jSONObject7.putOpt("rec", Boolean.valueOf(this.mLiveStreamingRecording));
                jSONObject5.putOpt("recording", jSONObject7);
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.putOpt("enable", Boolean.valueOf(this.isAudioLevelEnable));
            jSONObject8.putOpt("top_count", Integer.valueOf(this.audioLevelTopCount));
            jSONObject5.putOpt("audiolevel_config", jSONObject8);
            jSONObject2.putOpt("room_config", jSONObject5);
            if (!this.mLiveStreamingServerURL.isEmpty() && this.mLiveStreamingTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION) {
                jSONObject6.putOpt("url", this.mLiveStreamingServerURL);
                if (!this.mLiveStreamingMixTemplate.isEmpty()) {
                    jSONObject6.putOpt("mixTemplate", this.mLiveStreamingMixTemplate);
                }
                jSONObject6.putOpt("mix", Boolean.valueOf(this.mLiveStreamingMix));
                jSONObject2.putOpt("rtmp", jSONObject6);
            } else if (!this.mLiveStreamingServerURLSecond.isEmpty() && this.mLiveStreamingTransferModeSecond == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION) {
                jSONObject6.putOpt("url", this.mLiveStreamingServerURLSecond);
                if (!this.mLiveStreamingMixTemplateSecond.isEmpty()) {
                    jSONObject6.putOpt("mixTemplate", this.mLiveStreamingMixTemplateSecond);
                }
                jSONObject6.putOpt("mix", Boolean.valueOf(this.mLiveStreamingMixSecond));
                jSONObject2.putOpt("rtmp", jSONObject6);
            }
            if (this.mLiveStreamingRecording) {
                jSONObject7.putOpt("rec", Boolean.valueOf(this.mLiveStreamingRecording));
                jSONObject2.putOpt("recording", jSONObject7);
            }
            if (!this.mMixLayoutPosition.isEmpty()) {
                jSONObject2.putOpt("window_id_in_bypass", this.mMixLayoutPosition);
            }
            jSONObject.putOpt("body", jSONObject2);
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.17
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject9) {
                    int optInt = jSONObject9.optInt("error_code");
                    String optString = jSONObject9.optString("error");
                    long optLong = jSONObject9.optLong("sender", 0L);
                    if (WebSocketChannel.this.delegate != null) {
                        if (optInt == 502) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 450, optString);
                        } else if (optInt == 503) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 449, optString);
                        } else if (optInt == 451) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 436, optString);
                        } else if (optInt == 450) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 451, optString);
                        } else {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                        }
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            jSONObject.putOpt("transaction", randomString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_PUBLISHER_SETUP, "publisherJoinRoomMultistream", true, true, Boolean.valueOf(this.isPartSdpMode), "H264", "opus", Boolean.valueOf(this.isAudioLevelEnable), Integer.valueOf(this.audioLevelTopCount));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publisherJoinRoom(JanusHandle janusHandle) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject2.putOpt("request", "join");
            jSONObject2.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject2.putOpt("ptype", "publisher");
            jSONObject2.putOpt("display", this.mDisplayName);
            jSONObject2.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject2.putOpt("app_id", this.mAppId);
            jSONObject2.putOpt("room_name", this.mRoomName);
            jSONObject2.putOpt("role", "publisher");
            jSONObject2.putOpt("token", "no_token");
            if (this.keyAgreementProtocol == BaiduRtcRoom.KeyAgreementProtocol.BRTC_SDES) {
                jSONObject2.putOpt("encryptmode", "sdes");
            }
            if (!this.mMixLayoutPosition.isEmpty()) {
                jSONObject2.putOpt("window_id_in_bypass", this.mMixLayoutPosition);
            }
            if (!this.mLiveStreamingServerURL.isEmpty() && this.mLiveStreamingTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION) {
                jSONObject3.putOpt("url", this.mLiveStreamingServerURL);
                if (!this.mLiveStreamingMixTemplate.isEmpty()) {
                    jSONObject3.putOpt("mixTemplate", this.mLiveStreamingMixTemplate);
                }
                jSONObject3.putOpt("mix", Boolean.valueOf(this.mLiveStreamingMix));
                jSONObject2.putOpt("rtmp", jSONObject3);
            } else if (!this.mLiveStreamingServerURLSecond.isEmpty() && this.mLiveStreamingTransferModeSecond == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION) {
                jSONObject3.putOpt("url", this.mLiveStreamingServerURLSecond);
                if (!this.mLiveStreamingMixTemplateSecond.isEmpty()) {
                    jSONObject3.putOpt("mixTemplate", this.mLiveStreamingMixTemplateSecond);
                }
                jSONObject3.putOpt("mix", Boolean.valueOf(this.mLiveStreamingMixSecond));
                jSONObject2.putOpt("rtmp", jSONObject3);
            }
            if (this.mLiveStreamingRecording) {
                jSONObject4.putOpt("rec", Boolean.valueOf(this.mLiveStreamingRecording));
                jSONObject2.putOpt("recording", jSONObject4);
            }
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("body", jSONObject2);
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.mSessionId);
            jSONObject.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
    }

    public void publisherCreateOffer(BigInteger bigInteger, SessionDescription sessionDescription) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("request", "configure");
            jSONObject.putOpt("audio", Boolean.valueOf(this.mHasAudio));
            jSONObject.putOpt("video", Boolean.valueOf(this.mHasVideo));
            if (this.mHasData) {
                jSONObject.putOpt("data", Boolean.valueOf(this.mHasData));
            }
            jSONObject2.putOpt("type", sessionDescription.type);
            jSONObject2.putOpt("sdp", sessionDescription.description);
            jSONObject3.putOpt("janus", "message");
            jSONObject3.putOpt("body", jSONObject);
            jSONObject3.putOpt("jsep", jSONObject2);
            jSONObject3.putOpt("transaction", randomString(12));
            jSONObject3.putOpt("session_id", this.mSessionId);
            jSONObject3.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject3);
    }

    public void subscriberCreateAnswer(BigInteger bigInteger, SessionDescription sessionDescription) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("request", "start");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject2.putOpt("type", sessionDescription.type);
            if (!TextUtils.isEmpty(this.subscribePartialSdp)) {
                jSONObject2.putOpt("sdp", SdpPrefer.partialLocalSdp(sessionDescription.description, this.subscribePartialSdp));
                jSONObject2.putOpt("partial", true);
            } else {
                jSONObject2.putOpt("sdp", sessionDescription.description);
            }
            jSONObject3.putOpt("janus", "message");
            jSONObject3.putOpt("body", jSONObject);
            jSONObject3.putOpt("jsep", jSONObject2);
            jSONObject3.putOpt("session_id", this.mSessionId);
            jSONObject3.putOpt("handle_id", bigInteger);
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.18
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject4) {
                    int optInt = jSONObject4.optInt("error_code");
                    String optString = jSONObject4.optString("error");
                    long optLong = jSONObject4.optLong("sender", 0L);
                    if (WebSocketChannel.this.delegate != null) {
                        if (optInt == 457) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 454, optString);
                        } else if (optInt == 458) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 460, optString);
                        } else {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                        }
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            jSONObject3.putOpt("transaction", randomString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject3);
        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_SUBSCRIBER_ANSWER, "subscriberCreateAnswer");
    }

    public void trickleCandidate(BigInteger bigInteger, IceCandidate iceCandidate) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("candidate", iceCandidate.sdp);
            jSONObject.putOpt("sdpMid", iceCandidate.sdpMid);
            jSONObject.putOpt("sdpMLineIndex", Integer.valueOf(iceCandidate.sdpMLineIndex));
            jSONObject2.putOpt("janus", "trickle");
            jSONObject2.putOpt("candidate", jSONObject);
            jSONObject2.putOpt("transaction", randomString(12));
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void trickleCandidateComplete(BigInteger bigInteger) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("completed", true);
            jSONObject2.putOpt("janus", "trickle");
            jSONObject2.putOpt("candidate", jSONObject);
            jSONObject2.putOpt("transaction", randomString(12));
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void subscribeStreamingMultistream(BigInteger bigInteger, boolean z, boolean z2) {
        ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap = new ConcurrentHashMap<>();
        if (isBigRoomMode()) {
            concurrentHashMap.put(bigInteger, new ArrayList<>());
        } else if (this.commingFeedMidMap.get(bigInteger) != null) {
            ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
            Iterator<CommonDefine.StreamInfo> it = this.commingFeedMidMap.get(bigInteger).iterator();
            while (it.hasNext()) {
                CommonDefine.StreamInfo next = it.next();
                if (z2 && "audio".equals(next.type)) {
                    arrayList.add(next);
                }
                if (z && "video".equals(next.type)) {
                    arrayList.add(next);
                }
            }
            concurrentHashMap.put(bigInteger, arrayList);
        } else {
            Logging.m5305d("WebSocketChannel", "cannot find this feedId " + bigInteger);
            return;
        }
        Logging.m5305d("WebSocketChannel", "subscribeStreamingMultistream hasVideo = " + z + " hasAudio = " + z2);
        subscribeFeedStreamMultistream(concurrentHashMap, true);
    }

    public void subscribeAllRemoteStreamMultistream(boolean z, boolean z2) {
        ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap = new ConcurrentHashMap<>();
        for (Map.Entry<BigInteger, ArrayList<CommonDefine.StreamInfo>> entry : this.commingFeedMidMap.entrySet()) {
            BigInteger key = entry.getKey();
            if (this.feedMidMap.get(key) != null) {
                boolean z3 = false;
                Iterator<CommonDefine.StreamInfo> it = this.feedMidMap.get(key).iterator();
                while (it.hasNext()) {
                    CommonDefine.StreamInfo next = it.next();
                    if (!next.removed) {
                        if (z && "video".equals(next.type)) {
                            z3 = true;
                        }
                        if (z2 && "audio".equals(next.type)) {
                            z3 = true;
                        }
                    }
                }
                if (z3) {
                }
            }
            ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
            Iterator<CommonDefine.StreamInfo> it2 = entry.getValue().iterator();
            while (it2.hasNext()) {
                CommonDefine.StreamInfo next2 = it2.next();
                if (z && "video".equals(next2.type)) {
                    arrayList.add(next2);
                }
                if (z2 && "audio".equals(next2.type)) {
                    arrayList.add(next2);
                }
            }
            concurrentHashMap.put(key, arrayList);
        }
        if (concurrentHashMap.isEmpty()) {
            Logging.m5305d("WebSocketChannel", "subscribeAllRemoteStreamMultistream empty!!! video : " + z + " audio : " + z2);
            return;
        }
        Logging.m5305d("WebSocketChannel", "subscribeAllRemoteVideoStreamMultistream video : " + z + " audio : " + z2);
        subscribeFeedStreamMultistream(concurrentHashMap, true);
    }

    public void unsubscribeAllRemoteStreamMultistream(boolean z, boolean z2) {
        ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap = new ConcurrentHashMap<>();
        for (Map.Entry<BigInteger, ArrayList<CommonDefine.StreamInfo>> entry : this.commingFeedMidMap.entrySet()) {
            BigInteger key = entry.getKey();
            ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
            Iterator<CommonDefine.StreamInfo> it = entry.getValue().iterator();
            while (it.hasNext()) {
                CommonDefine.StreamInfo next = it.next();
                if (z2 && "audio".equals(next.type)) {
                    arrayList.add(next);
                }
                if (z && "video".equals(next.type)) {
                    arrayList.add(next);
                }
            }
            concurrentHashMap.put(key, arrayList);
        }
        if (concurrentHashMap.isEmpty()) {
            Logging.m5305d("WebSocketChannel", "unsubscribeAllRemoteStreamMultistream empty!!! removeVideo = " + z + " removeAudio = " + z2);
            return;
        }
        Logging.m5305d("WebSocketChannel", "unsubscribeAllRemoteStreamMultistream removeVideo = " + z + " removeAudio = " + z2);
        unsubscribeFeedMultistream(concurrentHashMap);
    }

    public void subscriberStreamMultistream(final BigInteger bigInteger, final ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap, final boolean z) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = null;
        try {
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.19
                @Override // com.baidu.rtc.TransactionCallbackSuccess
                public void success(JSONObject jSONObject2) {
                    if (WebSocketChannel.this.delegate == null || !z) {
                        return;
                    }
                    for (Map.Entry entry : concurrentHashMap.entrySet()) {
                        WebSocketChannel.this.delegate.onAddedStreams((BigInteger) entry.getKey(), (ArrayList) entry.getValue());
                    }
                }
            };
            janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.20
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject2) {
                    WebSocketChannel.this.isInSubscribeSdpConfigureIng = false;
                    WebSocketChannel.this.dealPendingStreamAction();
                    JSONObject optJSONObject = jSONObject2.optJSONObject("error");
                    String optString = optJSONObject.optString("reason");
                    int optInt = optJSONObject.optInt("code");
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.handleId = bigInteger.intValue();
                    ArrayList arrayList = new ArrayList();
                    ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
                    if (concurrentHashMap2 != null) {
                        for (Map.Entry entry : concurrentHashMap2.entrySet()) {
                            arrayList.add(Long.valueOf(((BigInteger) entry.getKey()).longValue()));
                        }
                    }
                    errorInfo.feedIds = arrayList;
                    errorInfo.statusCode = optInt;
                    errorInfo.msg = optString;
                    errorInfo.errorType = ErrorInfo.ErrorType.ERROR_USER_SUBSCRIBE;
                    WebSocketChannel.this.dealError(errorInfo);
                    RtcLogCapturer.reportLog(RtcLogEvent.WS_RECV_SUBSCRIBER_ANSWER_FAIL, "subscriberStreamMultistream-error", Integer.valueOf(optInt), optString);
                }
            };
            janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.21
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject2) {
                    WebSocketChannel.this.isInSubscribeSdpConfigureIng = false;
                    WebSocketChannel.this.dealPendingStreamAction();
                    int optInt = jSONObject2.optInt("error_code");
                    String optString = jSONObject2.optString("error");
                    long optLong = jSONObject2.optLong("sender", 0L);
                    if (WebSocketChannel.this.delegate != null) {
                        if (optInt == 457) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 454, optString);
                        } else if (optInt == 429) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 455, optString);
                        } else if (optInt == 470) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 456, optString);
                        } else {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                        }
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("transaction", randomString);
            jSONObject.putOpt("session_id", this.mSessionId);
            jSONObject.putOpt("handle_id", bigInteger);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("request", "subscribe");
            if (concurrentHashMap != null) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    for (Map.Entry<BigInteger, ArrayList<CommonDefine.StreamInfo>> entry : concurrentHashMap.entrySet()) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.putOpt("id", entry.getKey());
                        JSONArray jSONArray3 = new JSONArray();
                        for (int i = 0; i < entry.getValue().size(); i++) {
                            jSONArray3.put(i, entry.getValue().get(i).mid);
                        }
                        if (jSONArray3.length() > 0) {
                            jSONObject3.putOpt("mids", jSONArray3);
                        }
                        jSONArray2.put(jSONObject3);
                        if (this.delegate != null) {
                            this.delegate.onSubscribeUser(this.mSubscriberHandleId, entry.getKey());
                        }
                    }
                    jSONObject2.putOpt("feeds", jSONArray2);
                    jSONArray = jSONArray2;
                } catch (JSONException e) {
                    e = e;
                    jSONArray = jSONArray2;
                    e.printStackTrace();
                    sendMessage(jSONObject);
                    RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_SUBSCRIBE_USER, "subscriberStreamMultistream", jSONArray);
                }
            }
            jSONObject.putOpt("body", jSONObject2);
        } catch (JSONException e2) {
            e = e2;
        }
        sendMessage(jSONObject);
        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_SUBSCRIBE_USER, "subscriberStreamMultistream", jSONArray);
    }

    public void subscriberCreateHandleMultistream(final ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        this.subscribePartialSdp = "";
        JSONObject jSONObject2 = new JSONObject();
        try {
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.22
                @Override // com.baidu.rtc.TransactionCallbackSuccess
                public void success(JSONObject jSONObject3) {
                }
            };
            janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.23
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject3) {
                    WebSocketChannel.this.isInSubscribeSdpConfigureIng = false;
                    WebSocketChannel.this.dealPendingStreamAction();
                    JSONObject optJSONObject = jSONObject3.optJSONObject("error");
                    String optString = optJSONObject.optString("reason");
                    int optInt = optJSONObject.optInt("code");
                    ErrorInfo errorInfo = new ErrorInfo();
                    ArrayList arrayList = new ArrayList();
                    ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
                    if (concurrentHashMap2 != null) {
                        for (Map.Entry entry : concurrentHashMap2.entrySet()) {
                            arrayList.add(Long.valueOf(((BigInteger) entry.getKey()).longValue()));
                        }
                    }
                    errorInfo.feedIds = arrayList;
                    errorInfo.statusCode = optInt;
                    errorInfo.msg = optString;
                    errorInfo.errorType = ErrorInfo.ErrorType.ERROR_SUBSCRIBER_JOIN_ROOM;
                    WebSocketChannel.this.dealError(errorInfo);
                }
            };
            janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.24
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject3) {
                    WebSocketChannel.this.mSubscriberJoined = false;
                    WebSocketChannel.this.isInSubscribeSdpConfigureIng = false;
                    WebSocketChannel.this.dealPendingStreamAction();
                    if (WebSocketChannel.this.delegate != null) {
                        int optInt = jSONObject3.optInt("error_code");
                        String optString = jSONObject3.optString("error");
                        long optLong = jSONObject3.optLong("sender", 0L);
                        if (optInt == 502) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 450, optString);
                        } else if (optInt == 503) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 449, optString);
                        } else if (optInt == 451) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 436, optString);
                        } else if (optInt == 450) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 451, optString);
                        } else {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                        }
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            jSONObject2.putOpt("version", "brtc_v2");
            jSONObject2.putOpt("enable_multistream", true);
            jSONObject2.putOpt("janus", "setup");
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject = new JSONObject();
            jSONObject.putOpt("plugin", "janus.plugin.videoroom");
            jSONObject.putOpt("app_id", this.mAppId);
            jSONObject.putOpt("room_name", this.mRoomName);
            jSONObject.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject.putOpt("display", this.mDisplayName);
            jSONObject.putOpt("token", this.mTokenStr);
            jSONObject.putOpt("ptype", "subscriber");
            if (this.keyAgreementProtocol == BaiduRtcRoom.KeyAgreementProtocol.BRTC_SDES) {
                jSONObject.putOpt("encryptmode", "sdes");
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("media_antiweak", true);
            jSONObject3.putOpt("signal_antiweak", Boolean.valueOf(this.mIsKcpMode));
            jSONObject3.putOpt("version", Constraints.sdkVersion());
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.putOpt("userevent", true);
            jSONObject4.putOpt("sessionevent", true);
            if (this.isPartSdpMode) {
                jSONObject4.putOpt("partialsdp", true);
            }
            if (!TextUtils.isEmpty(this.mMediaServerIP)) {
                jSONObject4.putOpt("janusIp", this.mMediaServerIP);
            }
            jSONObject4.putOpt("client_config_map", jSONObject3);
            jSONObject.putOpt("session_config", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.putOpt("publishers", 1000);
            jSONObject5.putOpt("is_private", false);
            jSONObject5.putOpt("videocodec", "H264");
            jSONObject5.putOpt("audiocodec", this.mAudioCodec);
            jSONObject5.putOpt("playoutdelay_ext", true);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.putOpt("enable", Boolean.valueOf(this.isAudioLevelEnable));
            jSONObject6.putOpt("top_count", Integer.valueOf(this.audioLevelTopCount));
            jSONObject5.putOpt("audiolevel_config", jSONObject6);
            jSONObject.putOpt("room_config", jSONObject5);
            jSONArray = new JSONArray();
        } catch (JSONException e) {
            e = e;
            jSONArray = null;
        }
        try {
            for (Map.Entry<BigInteger, ArrayList<CommonDefine.StreamInfo>> entry : concurrentHashMap.entrySet()) {
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.putOpt("id", entry.getKey());
                JSONArray jSONArray3 = new JSONArray();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    jSONArray3.put(i, entry.getValue().get(i).mid);
                }
                if (jSONArray3.length() > 0) {
                    jSONObject7.putOpt("mids", jSONArray3);
                }
                jSONArray.put(jSONObject7);
            }
            jSONObject.putOpt("feeds", jSONArray);
            jSONObject2.putOpt("body", jSONObject);
            jSONArray2 = jSONArray;
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            jSONArray2 = jSONArray;
            sendMessage(jSONObject2);
            RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_SUBSCRIBER_SETUP, "subscriberCreateHandleMultistream", jSONArray2);
        }
        sendMessage(jSONObject2);
        RtcLogCapturer.reportLog(RtcLogEvent.WS_SEND_SUBSCRIBER_SETUP, "subscriberCreateHandleMultistream", jSONArray2);
    }

    public void subscriberCreateHandle(final BigInteger bigInteger, final String str) {
        this.mInSubscribingMap.put(bigInteger, true);
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.25
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                JanusHandle janusHandle = new JanusHandle();
                janusHandle.handleId = new BigInteger(jSONObject.optJSONObject("data").optString("id"));
                janusHandle.feedId = bigInteger;
                janusHandle.display = str;
                janusHandle.onRemoteJsep = new OnRemoteJsep() { // from class: com.baidu.rtc.WebSocketChannel.25.1
                    @Override // com.baidu.rtc.OnRemoteJsep
                    public void onRemoteJsep(JanusHandle janusHandle2, JSONObject jSONObject2) {
                        WebSocketChannel.this.delegate.subscriberHandleRemoteJsep(janusHandle2.handleId, jSONObject2);
                    }
                };
                janusHandle.onLeaving = new OnJoined() { // from class: com.baidu.rtc.WebSocketChannel.25.2
                    @Override // com.baidu.rtc.OnJoined
                    public void onJoined(JanusHandle janusHandle2) {
                        WebSocketChannel.this.subscriberOnLeaving(janusHandle2);
                    }
                };
                WebSocketChannel.this.handles.put(janusHandle.handleId, janusHandle);
                WebSocketChannel.this.feeds.put(janusHandle.feedId, janusHandle);
                WebSocketChannel.this.subscriberJoinRoom(janusHandle);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.26
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("janus", "attach");
            jSONObject.putOpt("plugin", "janus.plugin.videoroom");
            jSONObject.putOpt("transaction", randomString);
            jSONObject.putOpt("session_id", this.mSessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscriberJoinRoom(JanusHandle janusHandle) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("request", "join");
            jSONObject2.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject2.putOpt("ptype", "listener");
            jSONObject2.putOpt("feed", janusHandle.feedId);
            jSONObject2.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject2.putOpt("app_id", this.mAppId);
            jSONObject2.putOpt("room_name", this.mRoomName);
            jSONObject2.putOpt("token", this.mTokenStr);
            jSONObject2.putOpt("role", "listener");
            if (this.keyAgreementProtocol == BaiduRtcRoom.KeyAgreementProtocol.BRTC_SDES) {
                jSONObject2.putOpt("encryptmode", "sdes");
            }
            if (!this.mMixLayoutPosition.isEmpty()) {
                jSONObject2.putOpt("window_id_in_bypass", this.mMixLayoutPosition);
            }
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("body", jSONObject2);
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.mSessionId);
            jSONObject.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
    }

    private void unsubscribeFeedMultistream(ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap) {
        if (!this.isInSubscribeSdpConfigureIng) {
            this.isInSubscribeSdpConfigureIng = true;
            unSubscribeMultistream(this.mSubscriberHandleId, concurrentHashMap);
            return;
        }
        this.pendsActionQueue.addAction(CommonDefine.StreamActionType.kUnSubscribe, concurrentHashMap);
    }

    public void subscriberOnLeavingMultistream(BigInteger bigInteger) {
        subscriberOnLeavingMultistream(bigInteger, true, true);
    }

    public void subscriberOnLeavingMultistream(BigInteger bigInteger, boolean z, boolean z2) {
        ConcurrentHashMap<BigInteger, ArrayList<CommonDefine.StreamInfo>> concurrentHashMap = new ConcurrentHashMap<>();
        ArrayList<CommonDefine.StreamInfo> arrayList = new ArrayList<>();
        if (this.commingFeedMidMap.get(bigInteger) != null) {
            Iterator<CommonDefine.StreamInfo> it = this.commingFeedMidMap.get(bigInteger).iterator();
            while (it.hasNext()) {
                CommonDefine.StreamInfo next = it.next();
                if (z2 && "audio".equals(next.type)) {
                    arrayList.add(next);
                }
                if (z && "video".equals(next.type)) {
                    arrayList.add(next);
                }
            }
        }
        concurrentHashMap.put(bigInteger, arrayList);
        Logging.m5305d("WebSocketChannel", "subscriberOnLeavingMultistream removeVideo = " + z + " removeAudio = " + z2);
        unsubscribeFeedMultistream(concurrentHashMap);
    }

    public void removeUnsubscriberByFeedId(BigInteger bigInteger) {
        if (bigInteger == null || !this.feedMidMap.containsKey(bigInteger)) {
            return;
        }
        this.feedMidMap.remove(bigInteger);
    }

    public void subscriberOnLeaving(final JanusHandle janusHandle) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.27
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                WebSocketChannel.this.delegate.onLeaving(janusHandle.handleId, janusHandle.feedId);
                WebSocketChannel.this.delegate.onRemoteGone(janusHandle.handleId);
                WebSocketChannel.this.handles.remove(janusHandle.handleId);
                WebSocketChannel.this.feeds.remove(janusHandle.feedId);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.28
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("janus", "detach");
            jSONObject.putOpt("transaction", randomString);
            jSONObject.putOpt("session_id", this.mSessionId);
            jSONObject.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject);
        this.mInSubscribingMap.remove(janusHandle.feedId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keepAlive() {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.29
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                WebSocketChannel.this.keepAliveTimer.removeCallbacks(WebSocketChannel.this.serverKeepAliveTimeout);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.30
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
                WebSocketChannel.this.keepAliveTimer.removeCallbacks(WebSocketChannel.this.serverKeepAliveTimeout);
            }
        };
        this.ackTransactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("janus", "keepalive");
            jSONObject.putOpt("session_id", this.mSessionId);
            jSONObject.putOpt("transaction", randomString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.keepAliveTimer.removeCallbacks(this.serverKeepAliveTimeout);
        this.keepAliveTimer.postDelayed(this.serverKeepAliveTimeout, this.serverAckTimeout);
        sendMessage(jSONObject);
    }

    public void createRoom(long j, String str, final JanusHandle janusHandle) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.31
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                WebSocketChannel.this.publisherJoinRoom(janusHandle);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.32
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
                WebSocketChannel.this.publisherJoinRoom(janusHandle);
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject.putOpt("request", "create");
            jSONObject.putOpt("room", Long.valueOf(j));
            jSONObject.putOpt("description", str);
            jSONObject.putOpt("publishers", 1000);
            jSONObject.putOpt("is_private", false);
            jSONObject.putOpt("videocodec", this.mVideoCodec);
            jSONObject.putOpt("playoutdelay_ext", true);
            if (!this.mLiveStreamingServerURL.isEmpty() && this.mLiveStreamingTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION) {
                jSONObject3.putOpt("url", this.mLiveStreamingServerURL);
                if (!this.mLiveStreamingMixTemplate.isEmpty()) {
                    jSONObject3.putOpt("mixTemplate", this.mLiveStreamingMixTemplate);
                }
                jSONObject.putOpt("rtmp", jSONObject3);
            } else if (!this.mLiveStreamingServerURLSecond.isEmpty() && this.mLiveStreamingTransferModeSecond == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION) {
                jSONObject3.putOpt("url", this.mLiveStreamingServerURLSecond);
                if (!this.mLiveStreamingMixTemplateSecond.isEmpty()) {
                    jSONObject3.putOpt("mixTemplate", this.mLiveStreamingMixTemplateSecond);
                }
                jSONObject.putOpt("rtmp", jSONObject3);
            }
            if (this.mLiveStreamingRecording) {
                jSONObject4.putOpt("rec", Boolean.valueOf(this.mLiveStreamingRecording));
                jSONObject.putOpt("recording", jSONObject4);
            }
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void createRoomWithoutRoomID(final JanusHandle janusHandle) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.33
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                WebSocketChannel.this.mRoomId = jSONObject.optJSONObject("plugindata").optJSONObject("data").optLong("room");
                Logging.m5302v("createRoomWithoutRoomID", "Got RoomID:" + WebSocketChannel.this.mRoomId);
                ErrorInfoReport.getInstance().setRoomId(WebSocketChannel.this.mRoomId);
                WebSocketChannel.this.publisherJoinRoom(janusHandle);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.34
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
                WebSocketChannel.this.publisherJoinRoom(janusHandle);
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject.putOpt("request", "create");
            jSONObject.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject.putOpt("app_id", this.mAppId);
            jSONObject.putOpt("room_name", this.mRoomName);
            jSONObject.putOpt("description", this.mRoomName);
            jSONObject.putOpt("publishers", 1000);
            jSONObject.putOpt("is_private", false);
            jSONObject.putOpt("videocodec", this.mVideoCodec);
            jSONObject.putOpt("playoutdelay_ext", true);
            if (!this.mLiveStreamingServerURL.isEmpty() && this.mLiveStreamingTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION) {
                jSONObject3.putOpt("url", this.mLiveStreamingServerURL);
                jSONObject3.putOpt("mixTemplate", this.mLiveStreamingMixTemplate);
                jSONObject.putOpt("rtmp", jSONObject3);
            } else if (!this.mLiveStreamingServerURLSecond.isEmpty() && this.mLiveStreamingTransferModeSecond == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION) {
                jSONObject3.putOpt("url", this.mLiveStreamingServerURLSecond);
                if (!this.mLiveStreamingMixTemplateSecond.isEmpty()) {
                    jSONObject3.putOpt("mixTemplate", this.mLiveStreamingMixTemplateSecond);
                }
                jSONObject.putOpt("rtmp", jSONObject3);
            }
            if (this.mLiveStreamingRecording) {
                jSONObject4.putOpt("rec", Boolean.valueOf(this.mLiveStreamingRecording));
                jSONObject.putOpt("recording", jSONObject4);
            }
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doLeaveRoom(JanusHandle janusHandle) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "leave");
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString(12));
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public BaiduRtcRoom.UserList UserListOfRoom() {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.35
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                JSONArray optJSONArray = jSONObject.optJSONObject("plugindata").optJSONObject("data").optJSONArray("publishers");
                JSONArray optJSONArray2 = jSONObject.optJSONObject("plugindata").optJSONObject("data").optJSONArray("listeners");
                if (optJSONArray != null) {
                    BaiduRtcRoom.UserList userList = new BaiduRtcRoom.UserList(optJSONArray.length(), optJSONArray2.length());
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        userList.publishers[i] = optJSONArray.optJSONObject(i).optLong("clientUserId");
                    }
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        userList.listeners[i2] = optJSONArray2.optJSONObject(i2).optLong("clientUserId");
                    }
                    WebSocketChannel.this.mUserList = userList;
                }
                synchronized (WebSocketChannel.this) {
                    try {
                        WebSocketChannel.this.notifyAll();
                    } catch (Exception unused) {
                    }
                }
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.36
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "listparticipants");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", ((JanusHandle) this.handles.values().toArray()[0]).handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
        synchronized (this) {
            try {
                wait(1000L);
            } catch (Exception unused) {
            }
        }
        return this.mUserList;
    }

    public BaiduRtcRoom.RtcRoomUserInfo[] UserInfoList() {
        if (this.mUserInfoList.size() < 1) {
            return null;
        }
        BaiduRtcRoom.RtcRoomUserInfo[] rtcRoomUserInfoArr = new BaiduRtcRoom.RtcRoomUserInfo[this.mUserInfoList.size()];
        int i = 0;
        for (BaiduRtcRoom.RtcRoomUserInfo rtcRoomUserInfo : this.mUserInfoList.values()) {
            rtcRoomUserInfoArr[i] = new BaiduRtcRoom.RtcRoomUserInfo();
            rtcRoomUserInfoArr[i].userId = rtcRoomUserInfo.userId;
            rtcRoomUserInfoArr[i].userName = rtcRoomUserInfo.userName;
            rtcRoomUserInfoArr[i].attribute = rtcRoomUserInfo.attribute;
            i++;
        }
        return rtcRoomUserInfoArr;
    }

    public void leaveRoom() {
        this.mHandler.post(new Runnable() { // from class: com.baidu.rtc.WebSocketChannel.37
            @Override // java.lang.Runnable
            public void run() {
                int size = WebSocketChannel.this.handles.size();
                for (int i = 0; i < size; i++) {
                    WebSocketChannel webSocketChannel = WebSocketChannel.this;
                    webSocketChannel.doLeaveRoom((JanusHandle) webSocketChannel.handles.values().toArray()[i]);
                    try {
                        Thread.sleep(50L);
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    public JanusHandle getHandleByFeed(BigInteger bigInteger) {
        return this.feeds.get(bigInteger);
    }

    public String getNickNameByFeed(BigInteger bigInteger) {
        ConcurrentHashMap<BigInteger, BaiduRtcRoom.RtcRoomUserInfo> concurrentHashMap;
        if (bigInteger == null || (concurrentHashMap = this.mUserInfoList) == null || concurrentHashMap.get(bigInteger) == null) {
            return null;
        }
        return this.mUserInfoList.get(bigInteger).userName;
    }

    public BigInteger getFeedByHandle(BigInteger bigInteger) {
        if (bigInteger == null || this.handles.get(bigInteger) == null) {
            return BigInteger.valueOf(0L);
        }
        return this.handles.get(bigInteger).feedId;
    }

    public BigInteger getFeedByMid(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Map.Entry<BigInteger, CopyOnWriteArrayList<CommonDefine.StreamInfo>> entry : this.feedMidMap.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (entry.getValue().get(i).mid.equals(str)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public ConcurrentHashMap<BigInteger, CopyOnWriteArrayList<CommonDefine.StreamInfo>> getFeedMidMap() {
        return this.feedMidMap;
    }

    public String getVideoMidByFeed(BigInteger bigInteger) {
        if (bigInteger != null && this.feedMidMap.containsKey(bigInteger)) {
            for (int i = 0; i < this.feedMidMap.get(bigInteger).size(); i++) {
                if (this.feedMidMap.get(bigInteger).get(i).type.equals("video")) {
                    return this.feedMidMap.get(bigInteger).get(i).mid;
                }
            }
            return "";
        }
        return "";
    }

    public String getAudioMidByFeed(BigInteger bigInteger) {
        if (bigInteger != null && this.feedMidMap.containsKey(bigInteger)) {
            for (int i = 0; i < this.feedMidMap.get(bigInteger).size(); i++) {
                if (this.feedMidMap.get(bigInteger).get(i).type.equals("audio")) {
                    return this.feedMidMap.get(bigInteger).get(i).mid;
                }
            }
            return "";
        }
        return "";
    }

    public void unpublishHandle(BigInteger bigInteger) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "unpublish");
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.eventError = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.38
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject3) {
                    int optInt = jSONObject3.optInt("error_code");
                    String optString = jSONObject3.optString("error");
                    long optLong = jSONObject3.optLong("sender", 0L);
                    if (WebSocketChannel.this.delegate != null) {
                        if (optInt == 457) {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 461, optString);
                        } else {
                            WebSocketChannel.this.delegate.onSignalErrorInfo(optLong, 403, optString);
                        }
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            jSONObject2.putOpt("transaction", randomString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void setRemoteMultiStreamPlayState(Boolean bool, Boolean bool2, long j) {
        JanusHandle janusHandle = this.feeds.get(BigInteger.valueOf(j));
        if (janusHandle == null) {
            Logging.m5304e("WebSocketChannel", "wrong userID");
            return;
        }
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.39
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.40
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        CopyOnWriteArrayList<CommonDefine.StreamInfo> copyOnWriteArrayList = this.feedMidMap.get(BigInteger.valueOf(j));
        String str = "";
        String str2 = "";
        if (copyOnWriteArrayList != null) {
            for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
                CommonDefine.StreamInfo streamInfo = copyOnWriteArrayList.get(i);
                if ("video".equals(streamInfo.type)) {
                    str = streamInfo.mid;
                } else if ("audio".equals(streamInfo.type)) {
                    str2 = streamInfo.mid;
                }
            }
        }
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject.putOpt("type", "audio");
            jSONObject.putOpt("mid", str2);
            jSONObject.putOpt("receive", bool2);
            jSONObject2.putOpt("type", "video");
            jSONObject2.putOpt("mid", str);
            jSONObject2.putOpt("receive", bool);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            jSONArray.put(jSONObject2);
            jSONObject3.putOpt("request", "configure");
            jSONObject3.putOpt("streams", jSONArray);
            jSONObject4.putOpt("janus", "message");
            jSONObject4.putOpt("body", jSONObject3);
            jSONObject4.putOpt("transaction", randomString);
            jSONObject4.putOpt("session_id", this.mSessionId);
            jSONObject4.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("setRemoteMultiStreamPlayState = ");
        sb.append(!(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
        Logging.m5305d("WebSocketChannel", sb.toString());
        sendMessage(jSONObject4);
    }

    public void setRemoteStreamPlayState(Boolean bool, Boolean bool2, long j) {
        JanusHandle janusHandle = this.feeds.get(BigInteger.valueOf(j));
        if (janusHandle == null) {
            Logging.m5304e("WebSocketChannel", "wrong userID");
            return;
        }
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.41
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.42
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "configure");
            jSONObject.putOpt("audio", bool2);
            jSONObject.putOpt("video", bool);
            jSONObject.putOpt("data", true);
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", janusHandle.handleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void sendMessageToUser(String str, long j) {
        sendMessageToUser(str, j, false);
    }

    public void sendMessageToUser(String str, long j, boolean z) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.43
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.44
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "senddata");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject.putOpt("to", Long.valueOf(j));
            jSONObject.putOpt("data", str);
            jSONObject.putOpt("internal", Boolean.valueOf(z));
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("sendMessageToUser = ");
        sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        Logging.m5305d("WebSocketChannel", sb.toString());
        sendMessage(jSONObject2);
    }

    public void setUserAttribute(final String str) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.45
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                WebSocketChannel.this.sendMessageToUser(str, 0L, true);
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.46
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "setuserattribute");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("id", Long.valueOf(this.mUserId));
            jSONObject.putOpt("userattribute", str);
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void getUserAttribute(final long j) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.47
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject) {
                if (jSONObject.has("data")) {
                    String optString = jSONObject.optJSONObject("data").optString("userattribute");
                    if (optString.isEmpty()) {
                        return;
                    }
                    WebSocketChannel.this.delegate.onAttribute(BigInteger.valueOf(j), optString);
                }
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.48
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("request", "getuserattribute");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("id", Long.valueOf(j));
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void Send(JSONObject jSONObject) {
        String randomString = randomString(12);
        JanusTransaction janusTransaction = new JanusTransaction();
        janusTransaction.tid = randomString;
        janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.49
            @Override // com.baidu.rtc.TransactionCallbackSuccess
            public void success(JSONObject jSONObject2) {
            }
        };
        janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.50
            @Override // com.baidu.rtc.TransactionCallbackError
            public void error(JSONObject jSONObject2) {
            }
        };
        this.transactions.put(randomString, janusTransaction);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendMessage(jSONObject2);
    }

    public void kickOffUserWithId(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("request", "kickoutuser");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("target", Long.valueOf(j));
            Send(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void shutUpUserWithId(long j, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("request", "forwardconfigure");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("target", Long.valueOf(j));
            boolean z2 = true;
            jSONObject.putOpt("audio", Boolean.valueOf(!z));
            jSONObject.putOpt("video", Boolean.valueOf(!z));
            if (z) {
                z2 = false;
            }
            jSONObject.putOpt("data", Boolean.valueOf(z2));
            Send(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void disbandRoom() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("request", "disbandroom");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            Send(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startPublishLiveStream(String str, boolean z, boolean z2, String str2, BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.putOpt("request", "startbypass");
            jSONObject.putOpt("level", rtcLiveTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION ? "anchor" : "room");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("id", Long.valueOf(this.mUserId));
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("url", str);
            jSONObject3.putOpt("mix", Boolean.valueOf(z));
            jSONObject3.putOpt("mixTemplate", str2);
            jSONObject.putOpt("rtmp", jSONObject3);
            if (z2) {
                jSONObject2.putOpt("rec", Boolean.valueOf(z2));
                jSONObject.putOpt("recording", jSONObject2);
            }
            Send(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void stopPublishLiveStream(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("request", "stopbypass");
            jSONObject.putOpt("level", rtcLiveTransferMode == BaiduRtcRoom.RtcLiveTransferMode.RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION ? "anchor" : "room");
            jSONObject.putOpt("room", Long.valueOf(this.mRoomId));
            jSONObject.putOpt("id", Long.valueOf(this.mUserId));
            Send(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startRoomMediaRelay(final String str, final long j, String str2, BigInteger bigInteger) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("request", "start_media_relay");
            jSONObject.putOpt("dest_room_name", str);
            jSONObject.putOpt("dest_id", Long.valueOf(j));
            jSONObject.putOpt("dest_token", str2);
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.51
                @Override // com.baidu.rtc.TransactionCallbackSuccess
                public void success(JSONObject jSONObject2) {
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onStartMediaRelaySucceed(str, j);
                    }
                }
            };
            janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.52
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject2) {
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onStartMediaRelayFailed(str, j);
                    }
                }
            };
            this.transactions.put(randomString, janusTransaction);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
            sendMessage(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void stopRoomMediaRelay(String str, long j, BigInteger bigInteger) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("request", "stop_media_relay");
            jSONObject.putOpt("dest_room_name", str);
            jSONObject.putOpt("dest_id", Long.valueOf(j));
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.53
                @Override // com.baidu.rtc.TransactionCallbackSuccess
                public void success(JSONObject jSONObject2) {
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onStopMediaRelaySucceed();
                    }
                }
            };
            janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.54
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject2) {
                }
            };
            this.transactions.put(randomString, janusTransaction);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("handle_id", bigInteger);
            jSONObject2.putOpt("session_id", this.mSessionId);
            sendMessage(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void stopRoomMediaRelayAll(BigInteger bigInteger) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("request", "stop_media_relay");
            jSONObject.putOpt("stopall", true);
            String randomString = randomString(12);
            JanusTransaction janusTransaction = new JanusTransaction();
            janusTransaction.tid = randomString;
            janusTransaction.success = new TransactionCallbackSuccess() { // from class: com.baidu.rtc.WebSocketChannel.55
                @Override // com.baidu.rtc.TransactionCallbackSuccess
                public void success(JSONObject jSONObject2) {
                    if (WebSocketChannel.this.delegate != null) {
                        WebSocketChannel.this.delegate.onStopMediaRelaySucceed();
                    }
                }
            };
            janusTransaction.error = new TransactionCallbackError() { // from class: com.baidu.rtc.WebSocketChannel.56
                @Override // com.baidu.rtc.TransactionCallbackError
                public void error(JSONObject jSONObject2) {
                }
            };
            this.transactions.put(randomString, janusTransaction);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("janus", "message");
            jSONObject2.putOpt("body", jSONObject);
            jSONObject2.putOpt("transaction", randomString);
            jSONObject2.putOpt("session_id", this.mSessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
            sendMessage(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setDelegate(JanusRTCInterface janusRTCInterface) {
        this.delegate = janusRTCInterface;
    }

    private String randomString(Integer num) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(num.intValue());
        for (int i = 0; i < num.intValue(); i++) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealError(ErrorInfo errorInfo) {
        JanusRTCInterface janusRTCInterface = this.delegate;
        if (janusRTCInterface != null) {
            janusRTCInterface.onError(errorInfo);
        }
    }

    public long getLongValue(BigInteger bigInteger) {
        if (bigInteger != null) {
            return bigInteger.longValue();
        }
        return 0L;
    }
}
