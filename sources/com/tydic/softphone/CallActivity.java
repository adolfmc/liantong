package com.tydic.softphone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hms.api.FailedBinderCallBack;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.tydic.softphone.entity.Publisher;
import com.tydic.softphone.entity.Room;
import com.tydic.softphone.janus.C10467JanusClient;
import com.tydic.softphone.tydic_tracker.app.TYApplication;
import com.tydic.softphone.utils.NetBroadcastReceiver;
import com.tydic.softphone.utils.RomUtil;
import com.tydic.softphone.utils.Sm4Util;
import com.tydic.softphone.utils.Util;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.CalledByNative;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CandidatePairChangeEvent;
import org.webrtc.DataChannel;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.DtmfSender;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoTrack;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CallActivity extends Activity implements NetBroadcastReceiver.NetEvevt {
    static final int FORCE_HEADPHONES = 2;
    static final int FORCE_NONE = 0;
    static final int FORCE_SPEAKER = 1;
    static final int FOR_MEDIA = 1;
    public static String JANUS_URL = "wss://webrtccall.10010.com:8989";
    private static final String TAG = "tydic";
    static int cnt = 0;
    static String countTime = "00:00";
    public static boolean isFinishCall;
    static C10467JanusClient janusClient;
    static PeerConnection peerConnection;
    static PeerConnectionFactory peerConnectionFactory;
    static Timer timerBroadCast;
    public NBSTraceUnit _nbs_trace;
    AudioSource audioSource;
    private AudioTrack audioTrack;
    private String busiEntrance;
    private String callMode;
    private LinearLayout call_layout;
    private ImageView call_no1;
    private ImageView call_no10;
    private ImageView call_no11;
    private ImageView call_no12;
    private ImageView call_no2;
    private ImageView call_no3;
    private ImageView call_no4;
    private ImageView call_no5;
    private ImageView call_no6;
    private ImageView call_no7;
    private ImageView call_no8;
    private ImageView call_no9;
    private ImageView call_number;
    private TextView call_number_value;
    private String callerShowNumber;
    private TextView cancleVoice;
    public String channelId;
    private String customerGroupType;
    private String dockingBusiSys;
    EglBase.Context eglBaseContext;
    public String fouseCall;
    private String fouseCallName;
    public String fouseCallStr;
    public String isShowCalleeNumber;
    private String isSubstitution;
    ImageView iv_dismiss;
    private ImageView iv_hangup;
    private TextView iv_voice;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothHeadset mBluetoothHeadset;
    private LinearLayout no_call_layout;
    private ImageView no_voice;
    private String orderId;
    private String originalFouseCall;
    private String phoneNumber;
    private String roleCode;
    private String sendUserName;
    private String starRating;
    Timer timer1;
    Timer timer2;
    private TextView tv_gb;
    private TextView tv_hangup;
    private TextView tv_mobile;
    private TextView tv_name;
    private TextView tv_state;
    private Chronometer tv_time;
    private TextView tv_tt;
    private ImageButton tv_wf;
    TYApplication tyapp;
    private Util util;
    BigInteger videoRoomHandlerId;
    private ImageView voice;
    private Boolean no_voice_state = false;
    private Boolean voice_state = false;
    private Boolean mIsClose = false;
    Boolean isVoice = false;
    private int isHangupWho = 1;
    private int connectCount = 0;
    public String authUser = "";
    public String proxy = "";
    public String userName = "";
    public String secret = "";
    Room room = new Room(1234);
    private boolean isFrontCamera = true;
    private String callId = "";
    private String hangupCallId = "";
    private String isother = "0";
    private String timestamp = "";
    private int code = 1;
    private com.tydic.softphone.utils.NetBroadcastReceiver networkChangeReceiver = null;
    private BroadcastReceiver headsetPlugReceiver = null;
    public Boolean isTimerRunning = true;
    public Boolean isCallSucc = false;
    public Boolean isHEADSET_PLUG = false;
    JSONObject voiceCall = null;
    private Boolean isBluetoothHeadsetConnected = false;
    private Boolean isCallNumberClick = false;
    private String dialSwitch = "1";
    private long startTalkTime = 0;
    private long endTalkTime = 0;
    private long durationTalkTime = 0;
    private BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.tydic.softphone.CallActivity.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            Log.i("tydic111:", "mProfileListener:" + i);
            Log.i("tydic111:", "BluetoothProfile.HEADSET:1");
            if (i == 1) {
                CallActivity.this.mBluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
                List<BluetoothDevice> connectedDevices = CallActivity.this.mBluetoothHeadset.getConnectedDevices();
                Log.i("tydic111:", "devices:" + connectedDevices.size());
                if (connectedDevices.size() > 0) {
                    CallActivity.this.mBluetoothDevice = connectedDevices.get(0);
                    int connectionState = CallActivity.this.mBluetoothHeadset.getConnectionState(CallActivity.this.mBluetoothDevice);
                    Log.e("tydic111:==============", "headset state:" + connectionState);
                    if (connectionState == 2) {
                        Log.e("=================", "bluetooth headset connected");
                        Log.i("tydic112", "蓝牙耳机插上了");
                        CallActivity.this.isHEADSET_PLUG = true;
                        CallActivity.this.isBluetoothHeadsetConnected = true;
                        CallActivity.this.setSpeakerphoneOn(false);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 1) {
                CallActivity.this.mBluetoothHeadset = null;
                Log.i("tydic112", "蓝牙耳机断开了");
                AudioManager audioManager = (AudioManager) CallActivity.this.getSystemService("audio");
                audioManager.setBluetoothScoOn(false);
                audioManager.stopBluetoothSco();
                CallActivity.this.isHEADSET_PLUG = false;
                CallActivity.this.setSpeakerphoneOn(true);
            }
        }
    };
    private int call_no_number = 0;
    @SuppressLint({"HandlerLeak"})
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.tydic.softphone.CallActivity.23
        /* JADX WARN: Type inference failed for: r5v13, types: [com.tydic.softphone.CallActivity$23$1] */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    message.getData();
                    Log.i(CallActivity.TAG, "error");
                    CallActivity.this.code = 5;
                    CallActivity.this.sendCallid();
                    CallActivity.this.finishActivity(2000, "网络异常，请稍后再试！");
                    return;
                case 2:
                    Bundle data = message.getData();
                    CallActivity.this.secret = data.getString("registerToken");
                    CallActivity callActivity = CallActivity.this;
                    callActivity.proxy = "sip:" + data.getString("sipip") + ":" + data.getString("sipport");
                    CallActivity callActivity2 = CallActivity.this;
                    callActivity2.userName = "sip:" + data.getString("phoneNumber") + "@" + data.getString("sipip") + ":" + data.getString("sipport");
                    CallActivity.this.authUser = data.getString("phoneNumber");
                    CallActivity callActivity3 = CallActivity.this;
                    callActivity3.fouseCallStr = "sip:" + CallActivity.this.channelId + CallActivity.this.fouseCall + "@" + data.getString("sipip") + ":" + data.getString("sipport");
                    StringBuilder sb = new StringBuilder();
                    sb.append("secret:");
                    sb.append(CallActivity.this.secret);
                    Log.i(CallActivity.TAG, sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("proxy:");
                    sb2.append(CallActivity.this.proxy);
                    Log.i(CallActivity.TAG, sb2.toString());
                    Log.i(CallActivity.TAG, "userName:" + CallActivity.this.userName);
                    Log.i(CallActivity.TAG, "authUser:" + CallActivity.this.authUser);
                    Log.i(CallActivity.TAG, "fouseCallStr:" + CallActivity.this.fouseCallStr);
                    new Thread() { // from class: com.tydic.softphone.CallActivity.23.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            super.run();
                            try {
                                TYApplication.exceptionSoft("开启延迟请求webrtc网络连接");
                                Thread.sleep(20L);
                                CallActivity.this.connectJanus();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                TYApplication.exceptionSoft(e.getMessage());
                            }
                        }
                    }.start();
                    Log.i(CallActivity.TAG, "success");
                    return;
                case 3:
                case 6:
                default:
                    return;
                case 4:
                    Log.i(CallActivity.TAG, "success");
                    return;
                case 5:
                    CallActivity.this.initDefaultPeer();
                    return;
                case 7:
                    Log.i("tydic222", "q1111");
                    CallActivity.this.setPhoneNumber(0);
                    return;
                case 8:
                    Log.i("tydic222", "q2221");
                    CallActivity.this.setPhoneNumber(1);
                    return;
                case 9:
                    Bundle data2 = message.getData();
                    Log.i(CallActivity.TAG, "error");
                    CallActivity.this.code = 5;
                    CallActivity.this.sendCallid();
                    CallActivity callActivity4 = CallActivity.this;
                    callActivity4.finishActivity(2000, data2.getString("message") + "");
                    return;
            }
        }
    };
    private C10467JanusClient.JanusCallback janusCallback = new C1042625();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface CreateAnswerCallback {
        void onSetAnswerFailed(String str);

        void onSetAnswerSuccess(SessionDescription sessionDescription);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface CreateOfferCallback {
        void onCreateFailed(String str);

        void onCreateOfferSuccess(SessionDescription sessionDescription);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface CreatePeerConnectionCallback {
        void onAddStream(MediaStream mediaStream);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceGatheringComplete();

        void onRemoveStream(MediaStream mediaStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeWindow() {
    }

    private void initTime() {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void initAudio(boolean z, JSONObject jSONObject) {
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    static /* synthetic */ int access$1902(CallActivity callActivity, int i) {
        callActivity.code = i;
        return i;
    }

    static /* synthetic */ void access$2000(CallActivity callActivity) {
        callActivity.sendCallid();
    }

    static /* synthetic */ void access$2700(CallActivity callActivity, PeerConnection peerConnection2, CreateOfferCallback createOfferCallback) {
        callActivity.createOffer(peerConnection2, createOfferCallback);
    }

    static /* synthetic */ void access$2800(CallActivity callActivity, JSONArray jSONArray) {
        callActivity.handleNewPublishers(jSONArray);
    }

    static /* synthetic */ String access$3100(CallActivity callActivity) {
        return callActivity.callId;
    }

    static /* synthetic */ String access$3102(CallActivity callActivity, String str) {
        callActivity.callId = str;
        return str;
    }

    static /* synthetic */ String access$3200(CallActivity callActivity) {
        return callActivity.dockingBusiSys;
    }

    static /* synthetic */ String access$3300(CallActivity callActivity) {
        return callActivity.callMode;
    }

    static /* synthetic */ String access$3400(CallActivity callActivity) {
        return callActivity.roleCode;
    }

    static /* synthetic */ String access$3500(CallActivity callActivity) {
        return callActivity.starRating;
    }

    static /* synthetic */ String access$3600(CallActivity callActivity) {
        return callActivity.customerGroupType;
    }

    static /* synthetic */ String access$3700(CallActivity callActivity) {
        return callActivity.isSubstitution;
    }

    static /* synthetic */ String access$3800(CallActivity callActivity) {
        return callActivity.originalFouseCall;
    }

    static /* synthetic */ String access$3900(CallActivity callActivity) {
        return callActivity.callerShowNumber;
    }

    static /* synthetic */ String access$4000(CallActivity callActivity) {
        return callActivity.phoneNumber;
    }

    static /* synthetic */ String access$4100(CallActivity callActivity) {
        return callActivity.orderId;
    }

    static /* synthetic */ String access$4200(CallActivity callActivity) {
        return callActivity.busiEntrance;
    }

    static /* synthetic */ String access$4300(CallActivity callActivity) {
        return callActivity.sendUserName;
    }

    static /* synthetic */ Util access$4400(CallActivity callActivity) {
        return callActivity.util;
    }

    static /* synthetic */ void access$4500(CallActivity callActivity, JSONObject jSONObject) {
        callActivity.handleCallAnswer(jSONObject);
    }

    static /* synthetic */ String access$4600(CallActivity callActivity) {
        return callActivity.hangupCallId;
    }

    static /* synthetic */ String access$4602(CallActivity callActivity, String str) {
        callActivity.hangupCallId = str;
        return str;
    }

    static /* synthetic */ C10467JanusClient.JanusCallback access$4700(CallActivity callActivity) {
        return callActivity.janusCallback;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C10458R.C10462layout.tydic_softphone_call_main);
        setStatusBarMode(this, true, true);
        isFinishCall = false;
        Log.i("tydic3221", "test-->");
        Log.e("tydic4", "isFinishCall--->" + isFinishCall);
        try {
            String stringExtra = getIntent().getStringExtra("voiceCall");
            if (stringExtra != null) {
                saveInstance(stringExtra);
                this.voiceCall = new JSONObject(stringExtra);
                this.channelId = this.voiceCall.getString("channelId");
                this.phoneNumber = this.voiceCall.getString("phoneNumber");
                this.callMode = this.voiceCall.getString("callMode");
                this.dockingBusiSys = this.voiceCall.getString("dockingBusiSys");
                this.busiEntrance = this.voiceCall.getString("busiEntrance");
                this.fouseCall = this.voiceCall.getString("fouseCall");
                this.fouseCallName = this.voiceCall.getString("fouseCallName");
                this.orderId = this.voiceCall.getString("orderId");
                this.sendUserName = this.voiceCall.getString("userName");
                if (this.voiceCall.has("customerGroupType")) {
                    this.customerGroupType = this.voiceCall.getString("customerGroupType");
                } else {
                    this.customerGroupType = "";
                }
                if (this.voiceCall.has("isShowCalleeNumber")) {
                    this.isShowCalleeNumber = this.voiceCall.getString("isShowCalleeNumber");
                } else {
                    this.isShowCalleeNumber = "";
                }
                if (this.voiceCall.has("isSubstitution")) {
                    this.isSubstitution = this.voiceCall.getString("isSubstitution");
                } else {
                    this.isSubstitution = "";
                }
                if (this.voiceCall.has("originalFouseCall")) {
                    this.originalFouseCall = this.voiceCall.getString("originalFouseCall");
                } else {
                    this.originalFouseCall = "";
                }
                if (this.voiceCall.has("callerShowNumber")) {
                    this.callerShowNumber = this.voiceCall.getString("callerShowNumber");
                } else {
                    this.callerShowNumber = "";
                }
                if (this.voiceCall.has("starRating")) {
                    this.starRating = this.voiceCall.getString("starRating");
                } else {
                    this.starRating = "";
                }
                if (this.voiceCall.has("roleCode")) {
                    this.roleCode = this.voiceCall.getString("roleCode");
                } else {
                    this.roleCode = "01";
                }
                if (!this.channelId.equals("") && !this.phoneNumber.equals("") && !this.callMode.equals("") && !this.dockingBusiSys.equals("") && !this.busiEntrance.equals("") && !this.fouseCall.equals("") && !this.fouseCallName.equals("") && !this.sendUserName.equals("")) {
                    if (this.voiceCall.has("dialSwitch")) {
                        String string = this.voiceCall.getString("dialSwitch");
                        if (string.equals("1")) {
                            this.dialSwitch = "1";
                        } else {
                            this.dialSwitch = "0";
                        }
                        Log.i("tydic333", string);
                    } else {
                        Log.i("tydic333", "dialSwitch is null");
                    }
                    Log.i("tydic3221", "-->");
                    Log.i("tydic3221", String.valueOf(this.headsetPlugReceiver));
                    initCallPhone();
                }
                this.code = 7;
                sendCallid();
                Log.i("tydic123", "参数有为空的");
                Configs.showFloatToast(this, "参数有为空的，请检查参数稍后再试");
                finish();
            } else {
                this.code = 7;
                sendCallid();
                Configs.showFloatToast(this, "参数有误，请检查参数稍后再试");
                finish();
            }
            this.tyapp = new TYApplication(this, "通话页面", this.phoneNumber, this.fouseCall, this.callMode);
            TYApplication.ViewTracker("通话页面", "SFP_01");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("tydic334", e.toString());
            this.code = 7;
            sendCallid();
            TYApplication.exceptionSoft("参数有误，请检查参数稍后再试");
            finishActivity2(1000, "参数有误，请检查参数稍后再试");
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void saveInstance(String str) {
        SharedPreferences sharedPreferences = getSharedPreferences("tydic_softphone_voicecall", 0);
        Log.i("tydic_voicecallsave", str);
        sharedPreferences.edit().putString("voiceCallStr", str).commit();
        Log.i("tydic_voicecallsav1e", sharedPreferences.getString("voiceCallStr", ""));
    }

    private void initCallPhone() {
        initView();
        this.headsetPlugReceiver = new BroadcastReceiver() { // from class: com.tydic.softphone.CallActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.i("tydic112", "headsetPlugReceiver");
                Log.i("tydic112", action);
                if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                    Log.i("tydic112", "--->");
                    if (defaultAdapter.getProfileConnectionState(1) == 0) {
                        Log.i("tydic112", "蓝牙耳机挂断");
                        CallActivity.this.isHEADSET_PLUG = false;
                        CallActivity.this.setSpeakerphoneOn(true);
                        return;
                    }
                    Log.i("tydic112", "蓝牙耳机插上了");
                    CallActivity.this.isHEADSET_PLUG = true;
                    CallActivity.this.isBluetoothHeadsetConnected = true;
                    CallActivity.this.setSpeakerphoneOn(false);
                } else if ("android.intent.action.HEADSET_PLUG".equals(action) && intent.hasExtra("state")) {
                    Log.i("tydic112", String.valueOf(intent.getIntExtra("state", 0)));
                    if (intent.getIntExtra("state", 0) == 0) {
                        Log.i("tydic112", "普通耳机挂断");
                        if (CallActivity.this.isBluetoothHeadsetConnected.booleanValue()) {
                            Log.i("tydic112", "11>" + String.valueOf(CallActivity.this.isBluetoothHeadsetConnected));
                            return;
                        }
                        CallActivity.this.isHEADSET_PLUG = false;
                        CallActivity.this.setSpeakerphoneOn(true);
                        Log.i("tydic112", "22>" + String.valueOf(CallActivity.this.isBluetoothHeadsetConnected));
                        return;
                    }
                    Log.i("tydic112", "普通耳机插上了");
                    CallActivity.this.isHEADSET_PLUG = true;
                    CallActivity.this.isBluetoothHeadsetConnected = false;
                    CallActivity.this.setSpeakerphoneOn(false);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.networkChangeReceiver = new com.tydic.softphone.utils.NetBroadcastReceiver();
        getApplicationContext().registerReceiver(this.networkChangeReceiver, intentFilter);
        com.tydic.softphone.utils.NetBroadcastReceiver.setEvevt(this);
        VoiceFloatingService.isFirstInto = true;
        Log.i("tydic331", "s33333333333");
        viewState(0);
        finishActivity2(60000, "暂时无人接听，请稍后再试");
        Log.i("tydic331", "s1111111");
        this.userName = getIntent().getStringExtra("userName");
        this.eglBaseContext = EglBase.CC.create().getEglBaseContext();
        this.util = new Util(getApplicationContext(), this.mHandler);
        try {
            this.util.sendRequest(this.channelId, this.phoneNumber, this.callMode, this.dockingBusiSys, this.busiEntrance, this.isother, this.timestamp, this.fouseCall);
            if (this.callMode.equals("2")) {
                this.util.requestSendIsNumber(this.phoneNumber, this.timestamp, this.fouseCall);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        setSpeakerphoneOn(true);
        registerHeadsetPlugReceiver();
    }

    private void registerHeadsetPlugReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        getApplicationContext().registerReceiver(this.headsetPlugReceiver, intentFilter);
        getApplicationContext().registerReceiver(this.headsetPlugReceiver, new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.isBluetoothHeadsetConnected = Boolean.valueOf(defaultAdapter != null && defaultAdapter.isEnabled() && defaultAdapter.getProfileConnectionState(1) == 2);
        Log.i("tydic112", "------------>");
        Log.i("tydic112", this.isBluetoothHeadsetConnected + "");
        Log.i("tydic112", "-------test001----->");
        if (this.isBluetoothHeadsetConnected.booleanValue()) {
            Log.i("tydic112", this.isBluetoothHeadsetConnected + "   sleep");
            this.isHEADSET_PLUG = true;
            setSpeakerphoneOn(false);
            this.isBluetoothHeadsetConnected = true;
        }
    }

    public static void setStatusBarMode(Activity activity, boolean z, boolean z2) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                if (z && Build.VERSION.SDK_INT >= 23) {
                    Window window = activity.getWindow();
                    window.clearFlags(67108864);
                    window.getDecorView().setSystemUiVisibility(9472);
                    window.addFlags(Integer.MIN_VALUE);
                    window.setStatusBarColor(0);
                    return;
                }
                Window window2 = activity.getWindow();
                window2.clearFlags(67108864);
                window2.getDecorView().setSystemUiVisibility(1280);
                window2.addFlags(Integer.MIN_VALUE);
                if (z2) {
                    window2.setStatusBarColor(0);
                } else {
                    window2.setStatusBarColor(-16777216);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpeakerphoneOn(boolean z) {
        Log.i("tydic_softphone_voice", "关闭麦克风" + z);
        this.isVoice = Boolean.valueOf(z);
        this.voice_state = Boolean.valueOf(z);
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        audioManager.getMode();
        if (z) {
            Log.i("tydic_softphone_voice", "打开麦克风");
            audioManager.setSpeakerphoneOn(true);
            if (Build.VERSION.SDK_INT >= 11) {
                audioManager.setMode(3);
            } else {
                audioManager.setMode(2);
            }
            setVolumeControlStream(3);
            this.voice.setImageResource(C10458R.mipmap.tydic_softphone_voice);
            this.iv_voice.setText("扬声器已开");
            return;
        }
        Log.i("tydic_softphone_voice", "关闭麦克风");
        this.voice.setImageResource(C10458R.mipmap.tydic_softphone_voice_press);
        this.iv_voice.setText("扬声器已关");
        if (this.isBluetoothHeadsetConnected.booleanValue()) {
            if (RomUtil.isVivo()) {
                Log.i(TAG, "switchSpeaker: This is vivo phone");
                try {
                    Class.forName("android.media.AudioSystem").getMethod("setForceUse", Integer.TYPE, Integer.TYPE).invoke(null, 1, 2);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            Log.i("tydic112", "我在使用蓝牙耳机播放");
            audioManager.setMode(3);
            audioManager.startBluetoothSco();
            audioManager.setBluetoothScoOn(true);
            audioManager.setSpeakerphoneOn(false);
        } else if (RomUtil.isVivo()) {
            Log.i(TAG, "switchSpeaker: This is vivo phone");
            try {
                Class.forName("android.media.AudioSystem").getMethod("setForceUse", Integer.TYPE, Integer.TYPE).invoke(null, 1, 2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            Log.i(TAG, "switchSpeaker: others");
            audioManager.setMode(3);
            audioManager.stopBluetoothSco();
            audioManager.setBluetoothScoOn(false);
            audioManager.setSpeakerphoneOn(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallid() {
        String str = "获取麦克风权限异常";
        switch (this.code) {
            case 1:
                str = "获取麦克风权限异常";
                break;
            case 2:
                str = "主叫挂断";
                break;
            case 3:
                str = "被叫挂断";
                break;
            case 4:
                str = "注册失败";
                break;
            case 5:
                str = "网络异常";
                break;
            case 6:
                str = "通话中";
                break;
            case 7:
                str = "参数异常";
                break;
            case 8:
                str = "用户未接通电话";
                break;
        }
        Intent intent = new Intent();
        intent.setAction("com.tydic.softphone.callid");
        intent.putExtra(FailedBinderCallBack.CALLER_ID, this.callId);
        intent.putExtra("code", this.code);
        String valueOf = String.valueOf(this.durationTalkTime);
        if (valueOf.length() > 3) {
            valueOf = "0";
        }
        intent.putExtra("durationTalk", Integer.parseInt(String.valueOf(valueOf)));
        intent.putExtra("msg", str);
        sendBroadcast(intent);
        Log.i("tydic222", "Intent");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallNumberValue(String str) {
        String charSequence = this.call_number_value.getText().toString();
        this.call_number_value.setText(charSequence + str);
        this.call_number_value.post(new Runnable() { // from class: com.tydic.softphone.CallActivity.3
            @Override // java.lang.Runnable
            public void run() {
                int lineEnd;
                Log.i("tydic333", String.valueOf(CallActivity.this.call_number_value.getLineCount()));
                if (CallActivity.this.call_number_value.getLineCount() > 1) {
                    if (CallActivity.this.call_no_number > 0) {
                        Log.i("tydic333", String.valueOf(CallActivity.this.call_number_value.getLayout().getLineEnd(0)));
                        Log.i("tydic333", "--->");
                        CallActivity.this.call_number_value.setText((((Object) CallActivity.this.call_number_value.getText().toString().subSequence(0, (CallActivity.this.call_number_value.length() / 2) - 3)) + "...") + (((Object) CallActivity.this.call_number_value.getText().toString().subSequence(lineEnd - 10, lineEnd + 1)) + ""));
                        return;
                    }
                    CallActivity callActivity = CallActivity.this;
                    callActivity.call_no_number = callActivity.call_number_value.length();
                    Log.i("tydic333", String.valueOf(CallActivity.this.call_number_value.getLayout().getLineEnd(0)));
                    Log.i("tydic333", "--->");
                    CallActivity.this.call_number_value.setText((((Object) CallActivity.this.call_number_value.getText().toString().subSequence(0, (CallActivity.this.call_number_value.length() / 2) - 3)) + "...") + (((Object) CallActivity.this.call_number_value.getText().toString().subSequence(CallActivity.this.call_no_number - 10, CallActivity.this.call_no_number)) + ""));
                }
            }
        });
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(FailedBinderCallBack.CALLER_ID, this.callId);
            jSONObject.put("dialingNumber", str);
            jSONObject.put("dockingBusiSys", this.dockingBusiSys);
            jSONObject.put("callMode", this.callMode);
            jSONObject.put("callerNumber", Sm4Util.encryptEcb("c27aa727b678ab002df558ed22f3311c", this.phoneNumber));
            jSONObject.put("calleeNumber", Sm4Util.encryptEcb("c27aa727b678ab002df558ed22f3311c", this.fouseCall));
            jSONObject.put("busiEntrance", this.busiEntrance);
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
            Log.i(TAG, format);
            jSONObject.put("clickTime", format);
            jSONObject.put("token", this.secret);
            Util util = this.util;
            Util.sendRecordDial(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            DtmfSender dtmfSender = getDtmfSender();
            if (dtmfSender == null || !dtmfSender.canInsertDtmf()) {
                return;
            }
            dtmfSender.insertDtmf(str, 200, 50);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public DtmfSender getDtmfSender() {
        for (RtpSender rtpSender : peerConnection.getSenders()) {
            if (rtpSender.track() != null && rtpSender.dtmf() != null) {
                return rtpSender.dtmf();
            }
        }
        return null;
    }

    public static String getSDKVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.tydic.softphone", 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initCallNumber() {
        this.call_no1 = (ImageView) findViewById(C10458R.C10461id.no1);
        this.call_no1.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("1");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no2 = (ImageView) findViewById(C10458R.C10461id.no2);
        this.call_no2.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("2");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no3 = (ImageView) findViewById(C10458R.C10461id.no3);
        this.call_no3.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("3");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no4 = (ImageView) findViewById(C10458R.C10461id.no4);
        this.call_no4.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("4");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no5 = (ImageView) findViewById(C10458R.C10461id.no5);
        this.call_no5.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("5");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no6 = (ImageView) findViewById(C10458R.C10461id.no6);
        this.call_no6.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("6");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no7 = (ImageView) findViewById(C10458R.C10461id.no7);
        this.call_no7.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("7");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no8 = (ImageView) findViewById(C10458R.C10461id.no8);
        this.call_no8.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("8");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no9 = (ImageView) findViewById(C10458R.C10461id.no9);
        this.call_no9.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("9");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no10 = (ImageView) findViewById(C10458R.C10461id.no10);
        this.call_no10.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("*");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no11 = (ImageView) findViewById(C10458R.C10461id.no11);
        this.call_no11.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("0");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.call_no12 = (ImageView) findViewById(C10458R.C10461id.no12);
        this.call_no12.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.CallActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CallActivity.this.setCallNumberValue("#");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPhoneNumber(int i) {
        Log.i("tydic22221", "------------>");
        Log.i("tydic22221", i + "------------" + this.fouseCall);
        if (i == 1) {
            this.tv_mobile.setText("10016");
        } else {
            this.tv_mobile.setText(this.fouseCall);
        }
        if (this.isShowCalleeNumber.equals("0")) {
            this.tv_mobile.setVisibility(8);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0111, code lost:
        if (r0.equals("2") != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initView() {
        /*
            Method dump skipped, instructions count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tydic.softphone.CallActivity.initView():void");
    }

    public void showFloatView() {
        boolean canDrawOverlays = Build.VERSION.SDK_INT >= 23 ? Settings.canDrawOverlays(this) : false;
        Log.e("PermissionRequest", "111 open = " + canDrawOverlays);
        if (Build.VERSION.SDK_INT >= 25) {
            if (Settings.canDrawOverlays(this)) {
                showFloatingView();
                moveTaskToBack(true);
                return;
            }
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }
        showFloatingView();
        moveTaskToBack(true);
    }

    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 27) {
            return Settings.canDrawOverlays(context);
        }
        if (Settings.canDrawOverlays(context)) {
            return true;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            View view = new View(context);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(0, 0, Build.VERSION.SDK_INT >= 26 ? 2038 : 2003, 24, -2);
            view.setLayoutParams(layoutParams);
            windowManager.addView(view, layoutParams);
            windowManager.removeView(view);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showFloatingView() {
        if (VoiceFloatingService.isStart.booleanValue()) {
            Intent intent = new Intent();
            intent.setAction(VoiceFloatingService.ACTION_SHOW_FLOATING);
            sendBroadcast(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setClass(this, VoiceFloatingService.class);
        startService(intent2);
    }

    public void finishActivity(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            showToast(str);
        }
        this.timer1 = new Timer();
        this.timer1.schedule(new TimerTask() { // from class: com.tydic.softphone.CallActivity.21
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Log.i("finishActivity", "closeWindow");
                CallActivity.this.closeWindow();
                CallActivity.this.finish();
            }
        }, i);
    }

    public void finishActivity2(int i, final String str) {
        this.timer2 = new Timer();
        this.timer2.schedule(new TimerTask() { // from class: com.tydic.softphone.CallActivity.22
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Log.e(CallActivity.TAG, "测试是否");
                if (CallActivity.this.isCallSucc.booleanValue()) {
                    Log.e("tydic3221", "接通成功");
                    return;
                }
                Log.e("tydic3221", "未接通，将挂断电话");
                if (!TextUtils.isEmpty(str)) {
                    CallActivity.this.showToast(str);
                }
                CallActivity.this.code = 8;
                CallActivity.this.sendCallid();
                CallActivity.this.finish();
            }
        }, i);
    }

    public void connectJanus() {
        if (this.connectCount > 1) {
            finishActivity(1500, "网络不稳定，请稍后再试！");
        } else {
            janusClient = new C10467JanusClient(JANUS_URL, getApplicationContext(), this.mHandler);
            C10467JanusClient c10467JanusClient = janusClient;
            c10467JanusClient.authUser = this.authUser;
            c10467JanusClient.proxy = this.proxy;
            c10467JanusClient.userName = this.userName;
            c10467JanusClient.secret = this.secret;
            c10467JanusClient.setJanusCallback(this.janusCallback);
            janusClient.connect();
        }
        this.connectCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDefaultPeer() {
        peerConnectionFactory = createPeerConnectionFactory();
        this.audioSource = peerConnectionFactory.createAudioSource(createMediaConstraints());
        this.audioTrack = peerConnectionFactory.createAudioTrack("101", this.audioSource);
        peerConnection = createPeerConnection(new CreatePeerConnectionCallback() { // from class: com.tydic.softphone.CallActivity.24
            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onAddStream(MediaStream mediaStream) {
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onRemoveStream(MediaStream mediaStream) {
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onIceGatheringComplete() {
                CallActivity.janusClient.trickleCandidateComplete(CallActivity.this.videoRoomHandlerId);
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onIceCandidate(IceCandidate iceCandidate) {
                CallActivity.janusClient.trickleCandidate(CallActivity.this.videoRoomHandlerId, iceCandidate);
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
                CallActivity.peerConnection.removeIceCandidates(iceCandidateArr);
            }
        });
        LogUtils.log("show video room activity:" + peerConnection + " " + this.audioTrack + " ");
        if (peerConnection != null) {
            Log.i("tydic331", "noaudio");
            try {
                peerConnection.addTrack(this.audioTrack);
            } catch (Exception e) {
                Log.i("tydic331", "noaudio" + e.toString());
                TYApplication.exceptionSoft("peerConnection==null");
                finishActivity2(2000, "请检查下麦克风权限是否开启");
            }
        }
    }

    private MediaConstraints createMediaConstraints() {
        MediaConstraints mediaConstraints = new MediaConstraints();
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googEchoCancellation", "true"));
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googNoiseSuppression", "true"));
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googAutoGainControl", "false"));
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googHighpassFilter", "true"));
        return mediaConstraints;
    }

    private void stopUpdates() {
        Log.e(TAG, "---->");
        Log.e(TAG, this.isTimerRunning.toString());
        if (this.timer2 != null) {
            Log.e(TAG, "---->1111");
            this.timer2.cancel();
            this.timer2.purge();
            this.timer2 = null;
            this.isTimerRunning = false;
        } else {
            Log.e(TAG, "---->222");
            this.timer2 = null;
            this.isTimerRunning = false;
        }
        Log.i("timerBroadCast", "timerBroadCast");
        Log.i("timerBroadCast", String.valueOf(timerBroadCast));
        Timer timer = timerBroadCast;
        if (timer != null) {
            timer.cancel();
            timerBroadCast.purge();
            timerBroadCast = null;
            return;
        }
        timerBroadCast = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i("tydickey", i + "");
        Log.i("tydickey", "4");
        if (i == 4) {
            showFloatView();
            return true;
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        showFloatView();
        super.onUserLeaveHint();
        Log.i("tydickey", "onUserLeaveHint: 按下home键返回桌面");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Log.e("tydic1", "onDestroy--->");
        Log.e("tydic1", "VoiceFloatingService.isStart--->" + VoiceFloatingService.isStart);
        Log.e("tydic1", "audioSource--->" + this.audioSource);
        Log.e("tydic1", "audioTrack--->" + this.audioTrack);
        Log.e("tydic1", "peerConnection--->" + peerConnection);
        Log.e("tydic1", "janusClient--->" + janusClient);
        cnt = 0;
        isFinishCall = true;
        Log.i(TAG, "cnt--->" + cnt);
        AudioSource audioSource = this.audioSource;
        if (audioSource != null) {
            audioSource.dispose();
            this.audioSource = null;
        }
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.dispose();
            this.audioTrack = null;
        }
        PeerConnection peerConnection2 = peerConnection;
        if (peerConnection2 != null) {
            peerConnection2.close();
            peerConnection = null;
        }
        stopUpdates();
        C10467JanusClient c10467JanusClient = janusClient;
        if (c10467JanusClient != null) {
            c10467JanusClient.setWebSocketChannelNull();
            janusClient.disConnect();
        }
        if (this.networkChangeReceiver != null) {
            getApplicationContext().unregisterReceiver(this.networkChangeReceiver);
        }
        if (this.headsetPlugReceiver != null) {
            getApplicationContext().unregisterReceiver(this.headsetPlugReceiver);
        }
        if (this.isHangupWho == 1) {
            Log.i("onDestroy", "onDestroy:通话中断：");
            Intent intent = new Intent();
            intent.putExtra("time", Configs.TALK_CLOSE);
            intent.setAction(VoiceFloatingService.ACTION_SHOW_TALK);
            sendBroadcast(intent);
        }
        super.onDestroy();
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    @NBSInstrumented
    /* renamed from: com.tydic.softphone.CallActivity$25 */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C1042625 implements C10467JanusClient.JanusCallback {
        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onDestroySession(BigInteger bigInteger) {
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onIceCandidate(BigInteger bigInteger, JSONObject jSONObject) {
        }

        /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
            jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: CAST (r9 I:long) = (long) (r10 I:int), expected to be less than 8
            	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
            	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
            */
        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onMessage(java.math.BigInteger r4, java.math.BigInteger r5, org.json.JSONObject r6, org.json.JSONObject r7) {
            /*
                Method dump skipped, instructions count: 1070
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tydic.softphone.CallActivity.C1042625.onMessage(java.math.BigInteger, java.math.BigInteger, org.json.JSONObject, org.json.JSONObject):void");
        }

        C1042625() {
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onCreateSession(BigInteger bigInteger) {
            CallActivity.janusClient.attachPlugin("janus.plugin.sip");
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onAttached(BigInteger bigInteger) {
            Log.d(CallActivity.TAG, "onAttached" + bigInteger);
            CallActivity.this.videoRoomHandlerId = bigInteger;
            CallActivity.janusClient.registerUser(bigInteger, CallActivity.janusClient.proxy, CallActivity.janusClient.authUser, CallActivity.janusClient.userName, CallActivity.janusClient.secret);
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onSubscribeAttached(BigInteger bigInteger, BigInteger bigInteger2) {
            Publisher findPublisherById = CallActivity.this.room.findPublisherById(bigInteger2);
            if (findPublisherById != null) {
                findPublisherById.setHandleId(bigInteger);
                LogUtils.log("on sub scrib attached : " + bigInteger + " " + bigInteger2);
                CallActivity.janusClient.subscribe(bigInteger, CallActivity.this.room.getId(), bigInteger2);
            }
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onDetached(BigInteger bigInteger) {
            CallActivity.this.videoRoomHandlerId = null;
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onHangup(BigInteger bigInteger) {
            LogUtils.log("on hangup ");
            CallActivity.this.runOnUiThread(new Runnable() { // from class: com.tydic.softphone.CallActivity.25.1
                @Override // java.lang.Runnable
                public void run() {
                    CallActivity.isFinishCall = true;
                    if (CallActivity.this.code == 2 || CallActivity.this.code == 8) {
                        return;
                    }
                    if (CallActivity.janusClient != null) {
                        CallActivity.janusClient.disConnect();
                    }
                    CallActivity.this.code = 3;
                    CallActivity.this.endTalkTime = System.currentTimeMillis();
                    Log.i("tydic_broadcast", String.valueOf(CallActivity.this.endTalkTime));
                    CallActivity.this.durationTalkTime = (CallActivity.this.endTalkTime - CallActivity.this.startTalkTime) / 1000;
                    Log.i("tydic_broadcast", String.valueOf(CallActivity.this.durationTalkTime));
                    CallActivity.this.sendCallid();
                    Log.i("tydic_broadcast", "isHangupWho:" + CallActivity.this.isHangupWho);
                    CallActivity.this.finishActivity(200, "对方已挂断。");
                    Intent intent = new Intent();
                    intent.setAction(VoiceFloatingService.ACTION_DISMISS_FLOATING);
                    CallActivity.this.sendBroadcast(intent);
                }
            });
        }

        /* renamed from: com.tydic.softphone.CallActivity$25$7 */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        class C104347 implements CreatePeerConnectionCallback {
            final /* synthetic */ BigInteger val$sender;

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onRemoveStream(MediaStream mediaStream) {
            }

            C104347(BigInteger bigInteger) {
                this.val$sender = bigInteger;
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onIceGatheringComplete() {
                CallActivity.janusClient.trickleCandidateComplete(this.val$sender);
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onIceCandidate(IceCandidate iceCandidate) {
                CallActivity.janusClient.trickleCandidate(this.val$sender, iceCandidate);
            }

            @Override // com.tydic.softphone.CallActivity.CreatePeerConnectionCallback
            public void onAddStream(MediaStream mediaStream) {
                if (mediaStream.videoTracks.size() > 0) {
                    CallActivity.this.runOnUiThread(new Runnable() { // from class: com.tydic.softphone.CallActivity.25.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                        }
                    });
                }
            }
        }

        /* renamed from: com.tydic.softphone.CallActivity$25$8 */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        class C104368 implements SdpObserver {
            final /* synthetic */ PeerConnection val$peerConnection;
            final /* synthetic */ Publisher val$publisher;

            C104368(PeerConnection peerConnection, Publisher publisher) {
                this.val$peerConnection = peerConnection;
                this.val$publisher = publisher;
            }

            @Override // org.webrtc.SdpObserver
            public void onCreateSuccess(SessionDescription sessionDescription) {
                Log.d(CallActivity.TAG, "setRemoteDescription onCreateSuccess");
            }

            @Override // org.webrtc.SdpObserver
            public void onSetSuccess() {
                Log.d(CallActivity.TAG, "setRemoteDescription onSetSuccess");
                CallActivity.this.createAnswer(this.val$peerConnection, new CreateAnswerCallback() { // from class: com.tydic.softphone.CallActivity.25.8.1
                    @Override // com.tydic.softphone.CallActivity.CreateAnswerCallback
                    public void onSetAnswerFailed(String str) {
                    }

                    @Override // com.tydic.softphone.CallActivity.CreateAnswerCallback
                    public void onSetAnswerSuccess(SessionDescription sessionDescription) {
                        CallActivity.janusClient.subscriptionStart(C104368.this.val$publisher.getHandleId(), CallActivity.this.room.getId(), sessionDescription);
                    }
                });
            }

            @Override // org.webrtc.SdpObserver
            public void onCreateFailure(String str) {
                Log.d(CallActivity.TAG, "setRemoteDescription onCreateFailure " + str);
            }

            @Override // org.webrtc.SdpObserver
            public void onSetFailure(String str) {
                Log.d(CallActivity.TAG, "setRemoteDescription onSetFailure " + str);
            }
        }

        @Override // com.tydic.softphone.janus.C10467JanusClient.JanusCallback
        public void onError(String str) {
            Log.e("-=-", "error=" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewPublishers(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                BigInteger bigInteger = new BigInteger(jSONObject.getString("id"));
                String string = jSONObject.getString("display");
                janusClient.subscribeAttach(bigInteger);
                this.room.addPublisher(new Publisher(bigInteger, string));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallAnswer(JSONObject jSONObject) {
        try {
            peerConnection.setRemoteDescription(new SdpObserver() { // from class: com.tydic.softphone.CallActivity.26
                @Override // org.webrtc.SdpObserver
                public void onCreateSuccess(SessionDescription sessionDescription) {
                    Log.d(CallActivity.TAG, "setRemoteDescription onCreateSuccess");
                }

                @Override // org.webrtc.SdpObserver
                public void onSetSuccess() {
                    Log.d(CallActivity.TAG, "setRemoteDescription onSetSuccess");
                    CallActivity.this.runOnUiThread(new Runnable() { // from class: com.tydic.softphone.CallActivity.26.1
                        @Override // java.lang.Runnable
                        public void run() {
                        }
                    });
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateFailure(String str) {
                    Log.d(CallActivity.TAG, "setRemoteDescription onCreateFailure " + str);
                }

                @Override // org.webrtc.SdpObserver
                public void onSetFailure(String str) {
                    Log.d(CallActivity.TAG, "setRemoteDescription onSetFailure " + str);
                }
            }, new SessionDescription(SessionDescription.Type.ANSWER, jSONObject.getString("sdp")));
        } catch (Exception unused) {
        }
    }

    private VideoCapturer createVideoCapturer(boolean z) {
        if (Camera2Enumerator.isSupported(this)) {
            return createCameraCapturer(new Camera2Enumerator(this), z);
        }
        return createCameraCapturer(new Camera1Enumerator(true), z);
    }

    private VideoCapturer createCameraCapturer(CameraEnumerator cameraEnumerator, boolean z) {
        CameraVideoCapturer createCapturer;
        CameraVideoCapturer createCapturer2;
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        int i = 0;
        if (z) {
            int length = deviceNames.length;
            while (i < length) {
                String str = deviceNames[i];
                if (cameraEnumerator.isFrontFacing(str) && (createCapturer2 = cameraEnumerator.createCapturer(str, null)) != null) {
                    return createCapturer2;
                }
                i++;
            }
        } else {
            int length2 = deviceNames.length;
            while (i < length2) {
                String str2 = deviceNames[i];
                if (!cameraEnumerator.isFrontFacing(str2) && (createCapturer = cameraEnumerator.createCapturer(str2, null)) != null) {
                    return createCapturer;
                }
                i++;
            }
        }
        return null;
    }

    private PeerConnectionFactory createPeerConnectionFactory() {
        DefaultVideoEncoderFactory defaultVideoEncoderFactory = new DefaultVideoEncoderFactory(this.eglBaseContext, false, true);
        DefaultVideoDecoderFactory defaultVideoDecoderFactory = new DefaultVideoDecoderFactory(this.eglBaseContext);
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(this).setEnableInternalTracer(true).createInitializationOptions());
        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        PeerConnectionFactory.Builder videoDecoderFactory = PeerConnectionFactory.builder().setVideoEncoderFactory(defaultVideoEncoderFactory).setVideoDecoderFactory(defaultVideoDecoderFactory);
        videoDecoderFactory.setOptions(options);
        return videoDecoderFactory.createPeerConnectionFactory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PeerConnection createPeerConnection(final CreatePeerConnectionCallback createPeerConnectionCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PeerConnection.IceServer.builder(Configs.TURN_URI).setUsername(Configs.TURN_USER_NAME).setPassword(Configs.TURN_USER_PASSWORD).createIceServer());
        PeerConnection.Observer observer = new PeerConnection.Observer() { // from class: com.tydic.softphone.CallActivity.27
            @Override // org.webrtc.PeerConnection.Observer
            @CalledByNative("Observer")
            public /* synthetic */ void onConnectionChange(PeerConnection.PeerConnectionState peerConnectionState) {
                PeerConnection.Observer.CC.$default$onConnectionChange(this, peerConnectionState);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onDataChannel(DataChannel dataChannel) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onRenegotiationNeeded() {
            }

            @Override // org.webrtc.PeerConnection.Observer
            @CalledByNative("Observer")
            public /* synthetic */ void onSelectedCandidatePairChanged(CandidatePairChangeEvent candidatePairChangeEvent) {
                PeerConnection.Observer.CC.$default$onSelectedCandidatePairChanged(this, candidatePairChangeEvent);
            }

            @Override // org.webrtc.PeerConnection.Observer
            @CalledByNative("Observer")
            public /* synthetic */ void onStandardizedIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
                PeerConnection.Observer.CC.$default$onStandardizedIceConnectionChange(this, iceConnectionState);
            }

            @Override // org.webrtc.PeerConnection.Observer
            @CalledByNative("Observer")
            public /* synthetic */ void onTrack(RtpTransceiver rtpTransceiver) {
                PeerConnection.Observer.CC.$default$onTrack(this, rtpTransceiver);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onSignalingChange(PeerConnection.SignalingState signalingState) {
                Log.d(CallActivity.TAG, "onSignalingChange " + signalingState);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
                Log.d(CallActivity.TAG, "onIceConnectionChange " + iceConnectionState);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceConnectionReceivingChange(boolean z) {
                Log.d(CallActivity.TAG, "onIceConnectionReceivingChange " + z);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
                CreatePeerConnectionCallback createPeerConnectionCallback2;
                Log.d(CallActivity.TAG, "onIceGatheringChange " + iceGatheringState);
                if (iceGatheringState != PeerConnection.IceGatheringState.COMPLETE || (createPeerConnectionCallback2 = createPeerConnectionCallback) == null) {
                    return;
                }
                createPeerConnectionCallback2.onIceGatheringComplete();
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceCandidate(IceCandidate iceCandidate) {
                Log.d(CallActivity.TAG, "onIceCandidate");
                CreatePeerConnectionCallback createPeerConnectionCallback2 = createPeerConnectionCallback;
                if (createPeerConnectionCallback2 != null) {
                    createPeerConnectionCallback2.onIceCandidate(iceCandidate);
                }
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
                Log.d(CallActivity.TAG, "onIceCandidatesRemoved");
                CreatePeerConnectionCallback createPeerConnectionCallback2 = createPeerConnectionCallback;
                if (createPeerConnectionCallback2 != null) {
                    createPeerConnectionCallback2.onIceCandidatesRemoved(iceCandidateArr);
                }
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onAddStream(MediaStream mediaStream) {
                Log.d(CallActivity.TAG, "onAddStream");
                CreatePeerConnectionCallback createPeerConnectionCallback2 = createPeerConnectionCallback;
                if (createPeerConnectionCallback2 != null) {
                    createPeerConnectionCallback2.onAddStream(mediaStream);
                }
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onRemoveStream(MediaStream mediaStream) {
                Log.d(CallActivity.TAG, "onRemoveStream");
                CreatePeerConnectionCallback createPeerConnectionCallback2 = createPeerConnectionCallback;
                if (createPeerConnectionCallback2 != null) {
                    createPeerConnectionCallback2.onRemoveStream(mediaStream);
                }
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
                Log.d(CallActivity.TAG, "onAddTrack ");
            }
        };
        peerConnectionFactory.toString();
        PeerConnection peerConnection2 = null;
        try {
            if (peerConnectionFactory != null) {
                peerConnection2 = peerConnectionFactory.createPeerConnection(arrayList, observer);
                LogUtils.log("create peer internal : " + peerConnection2);
                return peerConnection2;
            }
            return null;
        } catch (Exception unused) {
            return peerConnection2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createOffer(final PeerConnection peerConnection2, final CreateOfferCallback createOfferCallback) {
        MediaConstraints mediaConstraints = new MediaConstraints();
        if (peerConnection2 == null) {
            finishActivity(1500, "网络不稳定，请稍后再试！");
            Intent intent = new Intent();
            intent.setAction(VoiceFloatingService.ACTION_DISMISS_FLOATING);
            sendBroadcast(intent);
            return;
        }
        peerConnection2.createOffer(new SdpObserver() { // from class: com.tydic.softphone.CallActivity.28
            @Override // org.webrtc.SdpObserver
            public void onCreateSuccess(SessionDescription sessionDescription) {
                Log.d(CallActivity.TAG, "createOffer onCreateSuccess " + sessionDescription.toString());
                peerConnection2.setLocalDescription(new SdpObserver() { // from class: com.tydic.softphone.CallActivity.28.1
                    @Override // org.webrtc.SdpObserver
                    public void onCreateSuccess(SessionDescription sessionDescription2) {
                        Log.d(CallActivity.TAG, "setLocalDescription onCreateSuccess");
                    }

                    @Override // org.webrtc.SdpObserver
                    public void onSetSuccess() {
                        Log.d(CallActivity.TAG, "setLocalDescription onSetSuccess");
                    }

                    @Override // org.webrtc.SdpObserver
                    public void onCreateFailure(String str) {
                        TYApplication.exceptionSoft(str);
                        Log.d(CallActivity.TAG, "setLocalDescription onCreateFailure " + str);
                    }

                    @Override // org.webrtc.SdpObserver
                    public void onSetFailure(String str) {
                        TYApplication.exceptionSoft(str);
                        Log.d(CallActivity.TAG, "setLocalDescription onSetFailure " + str);
                    }
                }, sessionDescription);
                CreateOfferCallback createOfferCallback2 = createOfferCallback;
                if (createOfferCallback2 != null) {
                    createOfferCallback2.onCreateOfferSuccess(sessionDescription);
                }
            }

            @Override // org.webrtc.SdpObserver
            public void onSetSuccess() {
                Log.d(CallActivity.TAG, "createOffer onSetSuccess");
            }

            @Override // org.webrtc.SdpObserver
            public void onCreateFailure(String str) {
                TYApplication.exceptionSoft(str);
                CreateOfferCallback createOfferCallback2 = createOfferCallback;
                if (createOfferCallback2 != null) {
                    createOfferCallback2.onCreateFailed(str);
                }
            }

            @Override // org.webrtc.SdpObserver
            public void onSetFailure(String str) {
                TYApplication.exceptionSoft(str);
                Log.d(CallActivity.TAG, "createOffer onSetFailure " + str);
                CreateOfferCallback createOfferCallback2 = createOfferCallback;
                if (createOfferCallback2 != null) {
                    createOfferCallback2.onCreateFailed(str);
                }
            }
        }, mediaConstraints);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAnswer(final PeerConnection peerConnection2, final CreateAnswerCallback createAnswerCallback) {
        peerConnection2.createAnswer(new SdpObserver() { // from class: com.tydic.softphone.CallActivity.29
            @Override // org.webrtc.SdpObserver
            public void onCreateSuccess(final SessionDescription sessionDescription) {
                peerConnection2.setLocalDescription(new SdpObserver() { // from class: com.tydic.softphone.CallActivity.29.1
                    @Override // org.webrtc.SdpObserver
                    public void onCreateSuccess(SessionDescription sessionDescription2) {
                    }

                    @Override // org.webrtc.SdpObserver
                    public void onSetSuccess() {
                        Log.d(CallActivity.TAG, "createAnswer setLocalDescription onSetSuccess");
                        if (createAnswerCallback != null) {
                            createAnswerCallback.onSetAnswerSuccess(sessionDescription);
                        }
                    }

                    @Override // org.webrtc.SdpObserver
                    public void onCreateFailure(String str) {
                        Log.d(CallActivity.TAG, "createAnswer setLocalDescription onCreateFailure " + str);
                        TYApplication.exceptionSoft(str);
                        if (createAnswerCallback != null) {
                            createAnswerCallback.onSetAnswerFailed(str);
                        }
                    }

                    @Override // org.webrtc.SdpObserver
                    public void onSetFailure(String str) {
                        TYApplication.exceptionSoft(str);
                        Log.d(CallActivity.TAG, "createAnswer setLocalDescription onSetFailure " + str);
                        if (createAnswerCallback != null) {
                            createAnswerCallback.onSetAnswerFailed(str);
                        }
                    }
                }, sessionDescription);
            }

            @Override // org.webrtc.SdpObserver
            public void onSetSuccess() {
                Log.d(CallActivity.TAG, "createAnswer onSetSuccess");
            }

            @Override // org.webrtc.SdpObserver
            public void onCreateFailure(String str) {
                TYApplication.exceptionSoft(str);
                Log.d(CallActivity.TAG, "createAnswer onCreateFailure " + str);
            }

            @Override // org.webrtc.SdpObserver
            public void onSetFailure(String str) {
                TYApplication.exceptionSoft(str);
                Log.d(CallActivity.TAG, "createAnswer onSetFailure " + str);
            }
        }, new MediaConstraints());
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(final String str) {
        runOnUiThread(new Runnable() { // from class: com.tydic.softphone.CallActivity.30
            @Override // java.lang.Runnable
            public void run() {
                Configs.showFloatToast(CallActivity.this, str);
            }
        });
    }

    private void showSlowToast(final String str) {
        runOnUiThread(new Runnable() { // from class: com.tydic.softphone.CallActivity.31
            @Override // java.lang.Runnable
            public void run() {
                Configs.showFloatToast(CallActivity.this, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hangUp() {
        this.isHangupWho = 2;
        Log.i("tydic321111", "hangUp:" + this.isHangupWho);
        runOnUiThread(new Runnable() { // from class: com.tydic.softphone.CallActivity.32
            @Override // java.lang.Runnable
            public void run() {
                CallActivity.this.endTalkTime = System.currentTimeMillis();
                Log.i("tydic321", String.valueOf(CallActivity.this.startTalkTime));
                Log.i("tydic321", String.valueOf(CallActivity.this.endTalkTime));
                CallActivity callActivity = CallActivity.this;
                callActivity.durationTalkTime = (callActivity.endTalkTime - CallActivity.this.startTalkTime) / 1000;
                Log.i("tydic321", String.valueOf(CallActivity.this.durationTalkTime));
                CallActivity.isFinishCall = true;
                CallActivity.this.code = 2;
                CallActivity.this.sendCallid();
                C10467JanusClient c10467JanusClient = CallActivity.janusClient;
                CallActivity.this.tv_time.stop();
                VoiceFloatingService.isStart = false;
                CallActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewState(int i) {
        switch (i) {
            case 0:
                this.tv_state.setText("正在呼叫用户...");
                this.tv_time.setVisibility(4);
                return;
            case 1:
                this.tv_state.setText("通话中...");
                this.isCallSucc = true;
                this.tv_state.setVisibility(8);
                this.tv_time.setVisibility(0);
                this.tv_time.setBase(SystemClock.elapsedRealtime());
                int elapsedRealtime = (int) (((SystemClock.elapsedRealtime() - this.tv_time.getBase()) / 1000) / 60);
                Log.e(TAG, String.valueOf(elapsedRealtime));
                Log.e(TAG, "--->");
                Log.e(TAG, "0" + String.valueOf(elapsedRealtime) + ":%s");
                this.tv_time.setFormat("%s");
                this.tv_time.start();
                timerBroadCast = new Timer();
                timerBroadCast.schedule(new TimerTask() { // from class: com.tydic.softphone.CallActivity.33
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        CallActivity callActivity = CallActivity.this;
                        int i2 = CallActivity.cnt;
                        CallActivity.cnt = i2 + 1;
                        String stringTime = callActivity.getStringTime(i2);
                        CallActivity.countTime = stringTime;
                        Intent intent = new Intent();
                        intent.putExtra("time", stringTime);
                        intent.setAction(VoiceFloatingService.ACTION_SHOW_TALK);
                        CallActivity.this.sendBroadcast(intent);
                    }
                }, 0L, 1000L);
                return;
            case 2:
                this.isCallSucc = true;
                this.tv_state.setVisibility(8);
                this.tv_time.setVisibility(0);
                this.tv_time.setBase(SystemClock.elapsedRealtime() - (cnt * 1000));
                this.tv_time.setFormat("%s");
                this.tv_time.start();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStringTime(int i) {
        return String.format(Locale.CHINA, "%02d:%02d", Integer.valueOf((i % 3600) / 60), Integer.valueOf(i % 60));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStartCall() {
        createOffer(peerConnection, new CreateOfferCallback() { // from class: com.tydic.softphone.CallActivity.34
            @Override // com.tydic.softphone.CallActivity.CreateOfferCallback
            public void onCreateOfferSuccess(SessionDescription sessionDescription) {
                LogUtils.log("create offer success : " + sessionDescription + " videoRoomHandlerId = " + CallActivity.this.videoRoomHandlerId);
                if (CallActivity.this.videoRoomHandlerId != null) {
                    CallActivity.janusClient.call(CallActivity.this.videoRoomHandlerId, sessionDescription, CallActivity.this.fouseCallStr);
                    Log.e("-=-", "发起通话请求");
                }
            }

            @Override // com.tydic.softphone.CallActivity.CreateOfferCallback
            public void onCreateFailed(String str) {
                TYApplication.exceptionSoft(str);
                Log.e("-=-", "拨号失败：" + str);
            }
        });
    }

    @Override // com.tydic.softphone.utils.NetBroadcastReceiver.NetEvevt
    public void onNetChange(int i) {
        Log.i("tydic443", "netMobile:" + String.valueOf(i));
        if (i == -1) {
            Log.e("网络", "网络连接失败  NetUtil.NETWORK_NONE");
            finishActivity(2000, "网络异常，请稍后再试！");
            TYApplication.exceptionSoft("网络异常，请稍后再试！");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class VideoItem {
        String display;
        PeerConnection peerConnection;
        SurfaceViewRenderer surfaceViewRenderer;
        BigInteger userId;
        VideoTrack videoTrack;

        VideoItem() {
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        dismissFloatingView();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onpause");
        if (VoiceFloatingService.isStart.booleanValue()) {
            showFloatingView();
        }
    }

    public void dismissFloatingView() {
        if (VoiceFloatingService.isStart.booleanValue()) {
            Intent intent = new Intent();
            intent.setAction(VoiceFloatingService.ACTION_DISMISS_FLOATING);
            sendBroadcast(intent);
        }
    }
}
