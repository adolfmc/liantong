package com.sinovatech.unicom.separatemodule.webrtc;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.RTCVideoView;
import com.baidu.rtc.RtcParameterSettings;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.live.config.Constraints;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcVoiceModule implements BaiduRtcRoom.BaiduRtcRoomDelegate {
    private static final String TAG = "RtcVoiceModule";
    private static final int mBitrate = 300000;
    private static final int mFps = 24;
    private static final int mHeight = 1920;
    private static final int mScreenOrientation = 1;
    private static final int mWidth = 1080;
    private Activity activityContext;
    private View alertView;
    private String beautyLevel;
    private SurfaceView bgSurfaceView;
    FrameLayout.LayoutParams bigParams;
    private CallBack callBack;
    double clickX;
    double clickY;
    private FrameLayout flMove;

    /* renamed from: group  reason: collision with root package name */
    private ViewGroup f27864group;
    boolean isMiniVideo;
    private boolean isMove;
    boolean isVoice;
    public boolean isWindowCreate;
    private ImageView ivAnswer;
    private ImageView ivCameraSwitch;
    private ImageView ivHangup;
    private ImageView ivMiniIcon;
    private ImageView ivMute;
    private ImageView ivResufe;
    private ImageView ivReversal;
    private ImageView ivRtcBg;
    private ImageView ivToVoice;
    private ImageView ivZoom;
    double lastX;
    double lastY;
    private LinearLayout llAnswer;
    private LinearLayout llFunction;
    private LinearLayout llHangup;
    private LinearLayout llResufe;
    private boolean mIsChangeScreen;
    private MediaPlayer mMediaPlayer;
    private RTCVideoView mRecordPreviewContainer;
    private RTCVideoView mRtcRemotePreview;
    private BaiduRtcRoom mVideoRoom;
    private WindowManager mWindowManager;
    private Chronometer miniTimer;
    private boolean remoteCamera;
    private RelativeLayout rlLocalParent;
    private RelativeLayout rlLocalShade;
    private RelativeLayout rlMiniWindow;
    private RelativeLayout rlRemoteParent;
    private RelativeLayout rlRemoteShade;
    private RelativeLayout rlVoiceParent;
    private RelativeLayout rlVoiceRoot;
    private String roomId;
    private View root;
    private Chronometer rtcTimer;
    FrameLayout.LayoutParams smallParams;
    private Disposable timer;
    private TextView tvCameraSwitch;
    private TextView tvMiniTimer;
    private TextView tvMute;
    private TextView tvName;
    private TextView tvTips;
    private int type;
    private View vShade;
    private Vibrator vibrator;
    private WindowManager.LayoutParams windowLayoutParams;
    boolean isWaiting = true;
    boolean isMute = false;
    boolean isRemoteViewBelow = true;
    private int hideSize = 10;
    private String mVideoResolution = "640x480-800kbps";
    private boolean localCamera = true;
    public boolean disableBack = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CallBack {
        void callback(int i, int i2);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onEngineStatisticsInfo(BigInteger bigInteger, Object obj) {
    }

    public void pauseStreaming() {
    }

    public void resumeStreaming() {
    }

    public RtcVoiceModule(Activity activity, ViewGroup viewGroup, String str, int i, String str2, CallBack callBack) {
        this.activityContext = activity;
        this.f27864group = viewGroup;
        this.beautyLevel = str2;
        this.callBack = callBack;
        this.roomId = TextUtils.isEmpty(str) ? getRoomId() : str;
        this.type = i;
        if (this.root == null) {
            this.root = LayoutInflater.from(activity).inflate(2131493547, (ViewGroup) null);
        }
        this.vibrator = (Vibrator) activity.getSystemService("vibrator");
        initPreviewContainer(this.root);
        initView(this.root);
        setWindows(this.root);
        this.root.setVisibility(8);
        initBaiduRtc(activity);
        if (callBack != null) {
            callBack.callback(i, 0);
        }
    }

    private void setWindows(View view) {
        this.windowLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.windowLayoutParams.type = 2038;
        }
        WindowManager.LayoutParams layoutParams = this.windowLayoutParams;
        layoutParams.flags = 32;
        layoutParams.format = 1;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.x = 0;
        layoutParams.y = 0;
        this.mWindowManager = (WindowManager) this.activityContext.getSystemService("window");
        this.mWindowManager.addView(view, this.windowLayoutParams);
        this.isWindowCreate = true;
    }

    private void initBaiduRtc(Context context) {
        this.mVideoRoom = BaiduRtcRoom.initWithAppID(App.getInstance().getApplicationContext(), Constraints.DEFAULT_RTC_APPID, Constraints.DEFAULT_RTC_TOKEN);
        BaiduRtcRoom.setVerbose(true);
        this.mVideoRoom.enableStatsToServer(true, "online");
        this.mVideoRoom.setBaiduRtcRoomDelegate(this);
        this.mVideoRoom.setMediaServerURL(Constraints.DEFAULT_RTC_MEDIA_SERVER);
        this.mVideoRoom.setLocalDisplay(this.mRecordPreviewContainer);
        RtcParameterSettings defaultSettings = RtcParameterSettings.getDefaultSettings();
        defaultSettings.VideoResolution = this.mVideoResolution;
        defaultSettings.VideoFps = 30;
        defaultSettings.AutoPublish = true;
        defaultSettings.AutoSubScribe = true;
        this.mVideoRoom.setParamSettings(defaultSettings, RtcParameterSettings.RtcParamSettingType.RTC_PARAM_SETTINGS_ALL);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initView(final View view) {
        this.rlVoiceRoot = (RelativeLayout) view.findViewById(2131298381);
        this.vShade = view.findViewById(2131299528);
        this.flMove = (FrameLayout) view.findViewById(2131297009);
        this.rlVoiceParent = (RelativeLayout) view.findViewById(2131296441);
        this.rlRemoteParent = (RelativeLayout) view.findViewById(2131298373);
        this.rlLocalParent = (RelativeLayout) view.findViewById(2131298357);
        this.rlRemoteShade = (RelativeLayout) view.findViewById(2131298374);
        this.rlLocalShade = (RelativeLayout) view.findViewById(2131298358);
        this.rlMiniWindow = (RelativeLayout) view.findViewById(2131298378);
        this.ivMiniIcon = (ImageView) view.findViewById(2131297479);
        this.tvMiniTimer = (TextView) view.findViewById(2131299067);
        this.miniTimer = (Chronometer) view.findViewById(2131296617);
        this.ivRtcBg = (ImageView) view.findViewById(2131297476);
        this.tvName = (TextView) view.findViewById(2131299068);
        this.tvTips = (TextView) view.findViewById(2131299105);
        this.rtcTimer = (Chronometer) view.findViewById(2131296618);
        this.ivResufe = (ImageView) view.findViewById(2131297485);
        this.ivAnswer = (ImageView) view.findViewById(2131297481);
        this.ivHangup = (ImageView) view.findViewById(2131297483);
        this.ivCameraSwitch = (ImageView) view.findViewById(2131297482);
        this.tvCameraSwitch = (TextView) view.findViewById(2131299069);
        this.llResufe = (LinearLayout) view.findViewById(2131297773);
        this.llAnswer = (LinearLayout) view.findViewById(2131297770);
        this.llHangup = (LinearLayout) view.findViewById(2131297772);
        this.llFunction = (LinearLayout) view.findViewById(2131297771);
        this.ivReversal = (ImageView) view.findViewById(2131297486);
        this.ivToVoice = (ImageView) view.findViewById(2131297487);
        this.ivMute = (ImageView) view.findViewById(2131297484);
        this.tvMute = (TextView) view.findViewById(2131299070);
        this.ivZoom = (ImageView) view.findViewById(2131297531);
        this.llResufe.setVisibility(this.type != 2 ? 8 : 0);
        this.llAnswer.setVisibility(this.type != 2 ? 8 : 0);
        this.llHangup.setVisibility(this.type != 2 ? 0 : 8);
        this.rtcTimer.setBase(SystemClock.elapsedRealtime());
        this.miniTimer.setBase(SystemClock.elapsedRealtime());
        this.rtcTimer.setFormat("%s");
        this.miniTimer.setFormat("%s");
        this.bigParams = (FrameLayout.LayoutParams) this.rlRemoteParent.getLayoutParams();
        this.smallParams = (FrameLayout.LayoutParams) this.rlLocalParent.getLayoutParams();
        this.mRecordPreviewContainer.setZOrderOnTop(true);
        this.mRecordPreviewContainer.setZOrderMediaOverlay(true);
        this.ivHangup.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$zaEObj6hm0LEEOsQPs_8OZ4-1w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$0(RtcVoiceModule.this, view2);
            }
        });
        this.ivZoom.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$996A5C85-u-xswDweQkUdKRzjIg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.this.zoomView(true);
            }
        });
        this.ivCameraSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$9CeL91yY4tmsa9u6KD6SAUzz6Dk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$2(RtcVoiceModule.this, view2);
            }
        });
        this.ivAnswer.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$WKeHljjytu0YypxsHF0yn1GKt2c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$3(RtcVoiceModule.this, view2);
            }
        });
        this.ivResufe.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$7jx9xTD4aKYnbK1dJjaH_ZQtaK4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$4(RtcVoiceModule.this, view2);
            }
        });
        this.ivReversal.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$hfyR1JfQP50FxJyrcvEeZbXw8lQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.this.mVideoRoom.switchCamera();
            }
        });
        this.ivToVoice.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$VD4XG7WatyAC9q6bvPCK5YdB4dc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$6(RtcVoiceModule.this, view2);
            }
        });
        this.ivMute.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$7Eu02fIEQEcJ8r_2eYBw13gC_84
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$7(RtcVoiceModule.this, view2);
            }
        });
        this.rlMiniWindow.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$3B-pi6AOtQ1KKJZbT-HsjGnLYaM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.this.zoomView(false);
            }
        });
        this.rlMiniWindow.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.1
            private int mMoveX;
            private int mMoveY;
            private int mTouchCurrentX;
            private int mTouchCurrentY;
            private int mTouchStartX;
            private int mTouchStartY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.mTouchStartX = (int) motionEvent.getRawX();
                    this.mTouchStartY = (int) motionEvent.getRawY();
                    this.mMoveX = 0;
                    this.mMoveY = 0;
                } else if (motionEvent.getAction() == 2) {
                    this.mTouchCurrentX = (int) motionEvent.getRawX();
                    this.mTouchCurrentY = (int) motionEvent.getRawY();
                    int i = this.mTouchCurrentX;
                    this.mMoveX = i - this.mTouchStartX;
                    int i2 = this.mTouchCurrentY;
                    this.mMoveY = i2 - this.mTouchStartY;
                    this.mTouchStartX = i;
                    this.mTouchStartY = i2;
                    RtcVoiceModule.this.windowLayoutParams.x += this.mMoveX;
                    RtcVoiceModule.this.windowLayoutParams.y += this.mMoveY;
                    RtcVoiceModule.this.mWindowManager.updateViewLayout(view, RtcVoiceModule.this.windowLayoutParams);
                } else if (motionEvent.getAction() == 1) {
                    RtcVoiceModule.this.mWindowManager.updateViewLayout(view, RtcVoiceModule.this.windowLayoutParams);
                    if (motionEvent.getEventTime() - motionEvent.getDownTime() < 150) {
                        Log.d(RtcVoiceModule.TAG, "按下抬起小于100 按点击事件处理");
                        view2.performClick();
                    }
                }
                return true;
            }
        });
        this.rlRemoteParent.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$Ns4xsB1PzV4UrugdFukLeMlpPus
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcVoiceModule.lambda$initView$9(RtcVoiceModule.this, view2);
            }
        });
        this.rlLocalParent.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                UIUtils.logD(RtcVoiceModule.TAG, "mRecordPreviewContainer onViewClick");
                if (!RtcVoiceModule.this.isMiniVideo) {
                    if (RtcVoiceModule.this.isRemoteViewBelow) {
                        RtcVoiceModule.this.cutRemoteViewLay(2);
                    }
                } else {
                    RtcVoiceModule.this.zoomView(false);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.rlRemoteParent.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.3
            private int mMoveX;
            private int mMoveY;
            private int mTouchCurrentX;
            private int mTouchCurrentY;
            private int mTouchStartX;
            private int mTouchStartY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (RtcVoiceModule.this.isMove) {
                    if (motionEvent.getAction() == 0) {
                        this.mTouchStartX = (int) motionEvent.getRawX();
                        this.mTouchStartY = (int) motionEvent.getRawY();
                        this.mMoveX = 0;
                        this.mMoveY = 0;
                    } else if (motionEvent.getAction() == 2) {
                        this.mTouchCurrentX = (int) motionEvent.getRawX();
                        this.mTouchCurrentY = (int) motionEvent.getRawY();
                        int i = this.mTouchCurrentX;
                        this.mMoveX = i - this.mTouchStartX;
                        int i2 = this.mTouchCurrentY;
                        this.mMoveY = i2 - this.mTouchStartY;
                        this.mTouchStartX = i;
                        this.mTouchStartY = i2;
                        RtcVoiceModule.this.windowLayoutParams.x += this.mMoveX;
                        RtcVoiceModule.this.windowLayoutParams.y += this.mMoveY;
                        RtcVoiceModule.this.mWindowManager.updateViewLayout(view, RtcVoiceModule.this.windowLayoutParams);
                    } else if (motionEvent.getAction() == 1) {
                        RtcVoiceModule.this.mWindowManager.updateViewLayout(view, RtcVoiceModule.this.windowLayoutParams);
                        if (motionEvent.getEventTime() - motionEvent.getDownTime() < 150) {
                            Log.d(RtcVoiceModule.TAG, "mRtcRemotePreview按下抬起小于100 按点击事件处理");
                            view2.performClick();
                        }
                    }
                    return true;
                }
                return false;
            }
        });
        this.rlLocalParent.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.4
            private int mMoveX;
            private int mMoveY;
            private int mTouchCurrentX;
            private int mTouchCurrentY;
            private int mTouchStartX;
            private int mTouchStartY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (RtcVoiceModule.this.isMove) {
                    if (motionEvent.getAction() == 0) {
                        this.mTouchStartX = (int) motionEvent.getRawX();
                        this.mTouchStartY = (int) motionEvent.getRawY();
                        this.mMoveX = 0;
                        this.mMoveY = 0;
                    } else if (motionEvent.getAction() == 2) {
                        this.mTouchCurrentX = (int) motionEvent.getRawX();
                        this.mTouchCurrentY = (int) motionEvent.getRawY();
                        int i = this.mTouchCurrentX;
                        this.mMoveX = i - this.mTouchStartX;
                        int i2 = this.mTouchCurrentY;
                        this.mMoveY = i2 - this.mTouchStartY;
                        this.mTouchStartX = i;
                        this.mTouchStartY = i2;
                        RtcVoiceModule.this.windowLayoutParams.x += this.mMoveX;
                        RtcVoiceModule.this.windowLayoutParams.y += this.mMoveY;
                        RtcVoiceModule.this.mWindowManager.updateViewLayout(view, RtcVoiceModule.this.windowLayoutParams);
                    } else if (motionEvent.getAction() == 1) {
                        RtcVoiceModule.this.mWindowManager.updateViewLayout(view, RtcVoiceModule.this.windowLayoutParams);
                        if (motionEvent.getEventTime() - motionEvent.getDownTime() < 150) {
                            Log.d(RtcVoiceModule.TAG, "mRecordPreviewContainer按下抬起小于100 按点击事件处理");
                            view2.performClick();
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public static /* synthetic */ void lambda$initView$0(RtcVoiceModule rtcVoiceModule, View view) {
        CallBack callBack = rtcVoiceModule.callBack;
        if (callBack != null) {
            callBack.callback(rtcVoiceModule.type, 12);
        }
    }

    public static /* synthetic */ void lambda$initView$2(RtcVoiceModule rtcVoiceModule, View view) {
        CallBack callBack = rtcVoiceModule.callBack;
        if (callBack != null) {
            callBack.callback(1, rtcVoiceModule.localCamera ? 57 : 58);
        }
        if (rtcVoiceModule.localCamera) {
            rtcVoiceModule.ivCameraSwitch.setImageResource(2131231594);
            rtcVoiceModule.tvCameraSwitch.setText("摄像头已关");
            rtcVoiceModule.localCamera = false;
            rtcVoiceModule.mVideoRoom.muteCamera(true);
            rtcVoiceModule.rlLocalShade.setVisibility(0);
            rtcVoiceModule.mRecordPreviewContainer.setVisibility(8);
            return;
        }
        rtcVoiceModule.ivCameraSwitch.setImageResource(2131231595);
        rtcVoiceModule.tvCameraSwitch.setText("摄像头已开");
        rtcVoiceModule.localCamera = true;
        rtcVoiceModule.mVideoRoom.muteCamera(false);
        rtcVoiceModule.rlLocalShade.setVisibility(8);
        rtcVoiceModule.mRecordPreviewContainer.setVisibility(0);
    }

    public static /* synthetic */ void lambda$initView$3(RtcVoiceModule rtcVoiceModule, View view) {
        String substring;
        rtcVoiceModule.llResufe.setVisibility(8);
        rtcVoiceModule.llAnswer.setVisibility(8);
        rtcVoiceModule.llHangup.setVisibility(0);
        rtcVoiceModule.tvTips.setText("视频接通中，请稍后...");
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if ("0".equals(currentPhoneNumber)) {
            substring = String.valueOf((int) (((Math.random() * 9.0d) + 1.0d) * 10000.0d));
        } else {
            substring = currentPhoneNumber.substring(3);
        }
        rtcVoiceModule.mVideoRoom.loginRtcRoomWithRoomName(rtcVoiceModule.roomId, Long.parseLong(substring), substring);
    }

    public static /* synthetic */ void lambda$initView$4(RtcVoiceModule rtcVoiceModule, View view) {
        CallBack callBack = rtcVoiceModule.callBack;
        if (callBack != null) {
            callBack.callback(2, 12);
        }
    }

    public static /* synthetic */ void lambda$initView$6(RtcVoiceModule rtcVoiceModule, View view) {
        CallBack callBack = rtcVoiceModule.callBack;
        if (callBack != null) {
            callBack.callback(rtcVoiceModule.type, 14);
        }
        rtcVoiceModule.toVoice();
    }

    public static /* synthetic */ void lambda$initView$7(RtcVoiceModule rtcVoiceModule, View view) {
        if (rtcVoiceModule.isMute) {
            rtcVoiceModule.tvMute.setText("麦克风已开");
            rtcVoiceModule.ivMute.setImageResource(2131231600);
            rtcVoiceModule.isMute = false;
            rtcVoiceModule.mVideoRoom.muteMicphone(false);
            return;
        }
        rtcVoiceModule.tvMute.setText("麦克风已关");
        rtcVoiceModule.ivMute.setImageResource(2131231601);
        rtcVoiceModule.isMute = true;
        rtcVoiceModule.mVideoRoom.muteMicphone(true);
    }

    public static /* synthetic */ void lambda$initView$9(RtcVoiceModule rtcVoiceModule, View view) {
        UIUtils.logD(TAG, "mRtcRemotePreview onclick");
        if (!rtcVoiceModule.isMiniVideo) {
            if (rtcVoiceModule.isRemoteViewBelow) {
                return;
            }
            rtcVoiceModule.cutRemoteViewLay(1);
            return;
        }
        rtcVoiceModule.zoomView(false);
    }

    public void toVoice() {
        BaiduRtcRoom baiduRtcRoom = this.mVideoRoom;
        if (baiduRtcRoom != null) {
            baiduRtcRoom.muteCamera(true);
            this.isVoice = true;
            this.mRecordPreviewContainer.setVisibility(8);
            this.mRtcRemotePreview.setVisibility(8);
            this.ivRtcBg.setVisibility(0);
            this.tvName.setVisibility(0);
            this.tvTips.setVisibility(8);
            this.llResufe.setVisibility(8);
            this.llAnswer.setVisibility(8);
            this.llFunction.setVisibility(8);
            this.ivReversal.setVisibility(8);
            this.llHangup.setVisibility(0);
            this.rtcTimer.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWait() {
        this.ivRtcBg.setVisibility(8);
        this.tvTips.setVisibility(8);
        this.tvName.setVisibility(8);
        this.llResufe.setVisibility(8);
        this.llAnswer.setVisibility(8);
        this.llFunction.setVisibility(0);
        this.ivReversal.setVisibility(0);
        this.llHangup.setVisibility(0);
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.cancel();
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
        }
        if (this.isMove) {
            zoomView(false);
        }
    }

    private void initPreviewContainer(View view) {
        this.mRtcRemotePreview = (RTCVideoView) view.findViewById(2131298422);
        this.mRecordPreviewContainer = (RTCVideoView) view.findViewById(2131299170);
        this.bgSurfaceView = (SurfaceView) view.findViewById(2131296513);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onRoomEventUpdate(int i, final long j, String str) {
        Log.i(TAG, " roomEvents: " + i + " extra_info :" + str);
        if (i != 118) {
            if (i == 200) {
                return;
            }
            if (i != 436) {
                if (i != 2002) {
                    switch (i) {
                        case 100:
                            return;
                        case 101:
                        case 102:
                        case 103:
                            break;
                        case 104:
                            this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.7
                                @Override // java.lang.Runnable
                                public void run() {
                                    RtcVoiceModule rtcVoiceModule = RtcVoiceModule.this;
                                    rtcVoiceModule.isWaiting = false;
                                    rtcVoiceModule.mVideoRoom.setRemoteDisplay(RtcVoiceModule.this.mRtcRemotePreview, j);
                                }
                            });
                            return;
                        default:
                            switch (i) {
                                case 300:
                                    Log.i(TAG, "用户加入房间==" + str);
                                    this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.8
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            RtcVoiceModule rtcVoiceModule = RtcVoiceModule.this;
                                            rtcVoiceModule.isWaiting = false;
                                            rtcVoiceModule.clearWait();
                                            RtcVoiceModule.this.rtcTimer.start();
                                            RtcVoiceModule.this.miniTimer.start();
                                            if (RtcVoiceModule.this.callBack != null) {
                                                RtcVoiceModule.this.callBack.callback(1, 15);
                                            }
                                            if (RtcVoiceModule.this.callBack != null) {
                                                RtcVoiceModule.this.callBack.callback(1, 11);
                                            }
                                        }
                                    });
                                    return;
                                case 301:
                                    Log.i(TAG, "用户离开房间==" + str);
                                    this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (RtcVoiceModule.this.callBack != null) {
                                                RtcVoiceModule.this.callBack.callback(RtcVoiceModule.this.isPingSuccess(), 13);
                                            }
                                        }
                                    });
                                    return;
                                default:
                                    return;
                            }
                    }
                } else {
                    return;
                }
            }
        }
        this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.5
            @Override // java.lang.Runnable
            public void run() {
                if (RtcVoiceModule.this.callBack != null) {
                    RtcVoiceModule.this.callBack.callback(RtcVoiceModule.this.isPingSuccess(), 13);
                }
            }
        });
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onPeerConnectStateUpdate(int i) {
        Log.d(TAG, "onPeerConnectStateUpdate connecStates: " + i);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onStreamInfoUpdate(String[] strArr) {
        Log.d(TAG, "onStreamInfoUpdate streamId: " + strArr);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onErrorInfoUpdate(int i) {
        Log.d(TAG, "onErrorInfoUpdate errorInfo: " + i);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onRoomDataMessage(ByteBuffer byteBuffer) {
        Log.d(TAG, "onRoomDataMessage ...");
    }

    public void recycleResource() {
        this.disableBack = false;
        Chronometer chronometer = this.rtcTimer;
        if (chronometer != null) {
            chronometer.stop();
        }
        Chronometer chronometer2 = this.miniTimer;
        if (chronometer2 != null) {
            chronometer2.stop();
        }
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeView(this.root);
        }
        this.isWindowCreate = false;
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.cancel();
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.release();
        }
        this.timer = Observable.timer(300L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcVoiceModule$FT8u3bRp5M08d8LDvuuEWyMCzUQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RtcVoiceModule.lambda$recycleResource$10(RtcVoiceModule.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$recycleResource$10(RtcVoiceModule rtcVoiceModule, Long l) throws Exception {
        rtcVoiceModule.mVideoRoom.logoutRtcRoom();
        rtcVoiceModule.mVideoRoom.setRemoteAudioSamplesCallback(null);
        rtcVoiceModule.mVideoRoom.setMixedAudioSamplesCallback(null);
        rtcVoiceModule.mVideoRoom.destroy();
        UIUtils.logD(TAG, "销毁房间、驱动");
        Disposable disposable = rtcVoiceModule.timer;
        if (disposable != null) {
            disposable.dispose();
            rtcVoiceModule.timer = null;
        }
    }

    public String getRoomId() {
        return DeviceHelper.getDeviceID(false);
    }

    public void zoomView(boolean z) {
        if (z) {
            this.isMove = true;
            if (this.isWaiting) {
                this.ivMiniIcon.setImageResource(2131231599);
                this.tvMiniTimer.setText("等待接听");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.width = 1;
                layoutParams.height = 1;
                this.rlVoiceParent.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.width = UIUtils.dip2px(this.activityContext, 68.0f);
                layoutParams2.height = UIUtils.dip2px(this.activityContext, 68.0f);
                layoutParams2.addRule(11);
                layoutParams2.setMargins(0, 0, UIUtils.dip2px(this.activityContext, 7.0f), 0);
                this.rlMiniWindow.setLayoutParams(layoutParams2);
                this.rlMiniWindow.setVisibility(0);
                this.windowLayoutParams.x = UIUtils.getScreenWidth(this.activityContext) - UIUtils.dip2px(this.activityContext, 7.0f);
                this.windowLayoutParams.width = UIUtils.dip2px(this.activityContext, 68.0f);
                this.windowLayoutParams.height = UIUtils.dip2px(this.activityContext, 68.0f);
                this.mWindowManager.updateViewLayout(this.root, this.windowLayoutParams);
                return;
            } else if (this.isVoice) {
                this.ivMiniIcon.setImageResource(2131231598);
                this.tvMiniTimer.setVisibility(8);
                this.miniTimer.setVisibility(0);
                this.rlVoiceParent.setVisibility(8);
                this.rlMiniWindow.setVisibility(0);
                WindowManager.LayoutParams layoutParams3 = this.windowLayoutParams;
                layoutParams3.format = 1;
                layoutParams3.x = UIUtils.getScreenWidth(this.activityContext) - 60;
                this.windowLayoutParams.width = UIUtils.dip2px(this.activityContext, 60.0f);
                this.windowLayoutParams.height = UIUtils.dip2px(this.activityContext, 60.0f);
                this.mWindowManager.updateViewLayout(this.root, this.windowLayoutParams);
                return;
            } else {
                this.isMiniVideo = true;
                this.rlMiniWindow.setVisibility(8);
                if (!this.isRemoteViewBelow) {
                    this.rlRemoteParent.setVisibility(8);
                    int dip2px = UIUtils.dip2px(this.activityContext, 4.0f);
                    FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) this.rlLocalParent.getLayoutParams();
                    layoutParams4.setMargins(dip2px, dip2px, dip2px, dip2px);
                    this.rlLocalParent.setLayoutParams(layoutParams4);
                } else {
                    this.rlLocalParent.setVisibility(8);
                    int dip2px2 = UIUtils.dip2px(this.activityContext, 4.0f);
                    FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) this.rlRemoteParent.getLayoutParams();
                    layoutParams5.setMargins(dip2px2, dip2px2, dip2px2, dip2px2);
                    this.rlRemoteParent.setLayoutParams(layoutParams5);
                }
                RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams6.width = UIUtils.dip2px(this.activityContext, 60.0f);
                layoutParams6.height = UIUtils.dip2px(this.activityContext, 60.0f);
                layoutParams6.addRule(11);
                layoutParams6.addRule(15);
                layoutParams6.rightMargin = UIUtils.dip2px(this.activityContext, 7.0f);
                this.flMove.setLayoutParams(layoutParams6);
                this.vShade.setVisibility(0);
                this.bgSurfaceView.setVisibility(8);
                this.ivZoom.setVisibility(8);
                this.llHangup.setVisibility(8);
                this.llFunction.setVisibility(8);
                this.ivReversal.setVisibility(8);
                WindowManager.LayoutParams layoutParams7 = this.windowLayoutParams;
                layoutParams7.format = 1;
                layoutParams7.x = UIUtils.getScreenWidth(this.activityContext) - 60;
                this.windowLayoutParams.width = UIUtils.dip2px(this.activityContext, 60.0f);
                this.windowLayoutParams.height = UIUtils.dip2px(this.activityContext, 60.0f);
                this.mWindowManager.updateViewLayout(this.root, this.windowLayoutParams);
                return;
            }
        }
        this.isMove = false;
        this.rlMiniWindow.setVisibility(8);
        if (this.isWaiting) {
            this.rlVoiceParent.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.rlVoiceParent.setVisibility(0);
            WindowManager.LayoutParams layoutParams8 = this.windowLayoutParams;
            layoutParams8.width = -1;
            layoutParams8.height = -1;
            layoutParams8.x = 0;
            layoutParams8.y = 0;
            this.mWindowManager.updateViewLayout(this.root, layoutParams8);
        } else if (this.isVoice) {
            this.rlVoiceParent.setVisibility(0);
            WindowManager.LayoutParams layoutParams9 = this.windowLayoutParams;
            layoutParams9.width = -1;
            layoutParams9.height = -1;
            layoutParams9.x = 0;
            layoutParams9.y = 0;
            this.mWindowManager.updateViewLayout(this.root, layoutParams9);
        } else {
            this.isMiniVideo = false;
            this.rlVoiceParent.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.rlVoiceParent.setVisibility(0);
            this.rlRemoteParent.setVisibility(0);
            this.rlLocalParent.setVisibility(0);
            if (!this.isRemoteViewBelow) {
                keepRemoteBelow(false);
            } else {
                keepRemoteBelow(true);
            }
            this.bgSurfaceView.setVisibility(0);
            this.flMove.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.flMove.setTranslationX(0.0f);
            this.flMove.setTranslationY(0.0f);
            this.vShade.setVisibility(8);
            this.ivZoom.setVisibility(0);
            this.llHangup.setVisibility(0);
            this.llFunction.setVisibility(0);
            this.ivReversal.setVisibility(0);
            WindowManager.LayoutParams layoutParams10 = this.windowLayoutParams;
            layoutParams10.width = -1;
            layoutParams10.height = -1;
            layoutParams10.x = 0;
            layoutParams10.y = 0;
            this.mWindowManager.updateViewLayout(this.root, layoutParams10);
        }
    }

    public void connectRtc(String str, String str2, CallBack callBack) {
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.vibrate(new long[]{1000, 1000, 2000, 50}, 0);
        }
        this.mMediaPlayer = MediaPlayer.create(this.activityContext, 2131820553);
        this.mMediaPlayer.setLooping(true);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        this.type = 2;
        this.ivZoom.setVisibility(0);
        this.tvTips.setText("邀请您视频通话...");
        this.llResufe.setVisibility(this.type != 2 ? 8 : 0);
        this.llAnswer.setVisibility(this.type != 2 ? 8 : 0);
        this.llHangup.setVisibility(8);
        this.llFunction.setVisibility(8);
        this.ivReversal.setVisibility(8);
        this.root.setVisibility(0);
        TextView textView = this.tvName;
        if (textView != null) {
            textView.setText(str2);
        }
        this.roomId = str;
        if (callBack != null) {
            this.callBack = callBack;
        }
    }

    public void setUserNick(String str) {
        TextView textView = this.tvName;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void monitorChangeAudio(CallBack callBack) {
        if (callBack != null) {
            this.callBack = callBack;
        }
    }

    public void connectRtc(CallBack callBack) {
        String substring;
        this.mMediaPlayer = MediaPlayer.create(this.activityContext, 2131820545);
        this.mMediaPlayer.setLooping(true);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        this.type = 1;
        this.tvTips.setText("等待对方接受邀请...");
        this.llResufe.setVisibility(this.type != 2 ? 8 : 0);
        this.llAnswer.setVisibility(this.type == 2 ? 0 : 8);
        this.root.setVisibility(0);
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if ("0".equals(currentPhoneNumber)) {
            substring = String.valueOf((int) (((Math.random() * 9.0d) + 1.0d) * 10000.0d));
        } else {
            substring = currentPhoneNumber.substring(3);
        }
        this.mVideoRoom.loginRtcRoomWithRoomName(this.roomId, Long.parseLong(substring), substring);
        if (callBack != null) {
            this.callBack = callBack;
            callBack.callback(this.type, 50);
        }
    }

    public void setType() {
        this.type = 0;
        CallBack callBack = this.callBack;
        if (callBack != null) {
            callBack.callback(this.type, 0);
        }
    }

    public int isPingSuccess() {
        Process exec;
        int waitFor;
        String readLine;
        try {
            exec = Runtime.getRuntime().exec("ping -c 1 -w 2 www.baidu.com");
            waitFor = exec.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (waitFor == 0) {
            UIUtils.logD("ping", "ping成功");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return 56;
                }
            } while (!readLine.contains("time="));
            String substring = readLine.substring(readLine.indexOf("time=") + 5, readLine.indexOf("ms"));
            UIUtils.logD("ping", substring.trim());
            return Float.valueOf(substring).floatValue() < 500.0f ? 55 : 56;
        }
        UIUtils.logD("ping", "ping失败：" + waitFor);
        return 56;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cutRemoteViewLay(int i) {
        if (i == 1) {
            this.isRemoteViewBelow = true;
            keepRemoteBelow(true);
        } else if (i == 2) {
            this.isRemoteViewBelow = false;
            keepRemoteBelow(false);
        }
    }

    private FrameLayout.LayoutParams getSmallLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.width = UIUtils.dip2px(this.activityContext, 120.0f);
        layoutParams.height = UIUtils.dip2px(this.activityContext, 180.0f);
        layoutParams.topMargin = UIUtils.dip2px(this.activityContext, 50.0f);
        layoutParams.leftMargin = (UIUtils.getScreenWidth(this.activityContext) - layoutParams.width) - UIUtils.dip2px(this.activityContext, 7.0f);
        return layoutParams;
    }

    private FrameLayout.LayoutParams getNormalLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    private void keepRemoteBelow(boolean z) {
        this.bigParams.setMargins(0, 0, 0, 0);
        this.rlRemoteParent.removeView(this.mRtcRemotePreview);
        this.rlLocalParent.removeView(this.mRecordPreviewContainer);
        if (z) {
            this.rlRemoteParent.setZ(0.0f);
            this.rlLocalParent.setZ(100.0f);
            this.mRtcRemotePreview.setZ(0.0f);
            this.mRecordPreviewContainer.setZ(100.0f);
            this.mRecordPreviewContainer.setZOrderOnTop(true);
            this.mRecordPreviewContainer.setZOrderMediaOverlay(true);
            this.mRtcRemotePreview.setZOrderOnTop(false);
            this.mRtcRemotePreview.setZOrderMediaOverlay(false);
            this.rlRemoteParent.setLayoutParams(this.bigParams);
            this.rlLocalParent.setLayoutParams(this.smallParams);
            this.rlRemoteParent.addView(this.mRtcRemotePreview);
            this.rlLocalParent.addView(this.mRecordPreviewContainer);
            return;
        }
        this.rlLocalParent.setZ(0.0f);
        this.rlRemoteParent.setZ(100.0f);
        this.mRecordPreviewContainer.setZ(0.0f);
        this.mRtcRemotePreview.setZ(100.0f);
        this.mRtcRemotePreview.setZOrderOnTop(true);
        this.mRtcRemotePreview.setZOrderMediaOverlay(true);
        this.mRecordPreviewContainer.setZOrderOnTop(false);
        this.mRecordPreviewContainer.setZOrderMediaOverlay(false);
        this.rlLocalParent.setLayoutParams(this.bigParams);
        this.rlRemoteParent.setLayoutParams(this.smallParams);
        this.rlLocalParent.addView(this.mRecordPreviewContainer);
        this.rlRemoteParent.addView(this.mRtcRemotePreview);
    }

    private void touchMove(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.lastX = (int) motionEvent.getRawX();
                this.lastY = (int) motionEvent.getRawY();
                this.clickX = motionEvent.getRawX();
                this.clickY = motionEvent.getRawY();
                return;
            case 1:
                Log.d(TAG, "移动距离: xxx==" + Math.abs(this.clickX - motionEvent.getX()) + ",yyy==" + Math.abs(this.clickY - motionEvent.getY()));
                StringBuilder sb = new StringBuilder();
                sb.append("Time==: ");
                sb.append(motionEvent.getEventTime() - motionEvent.getDownTime());
                Log.d(TAG, sb.toString());
                if (motionEvent.getEventTime() - motionEvent.getDownTime() < 150) {
                    Log.d(TAG, "按下抬起小于100 按点击事件处理");
                    view.performClick();
                    return;
                }
                Log.d(TAG, "吸附两侧");
                int width = (((RelativeLayout) view.getParent()).getWidth() - view.getWidth()) / 2;
                if (view.getLeft() < width) {
                    int left = view.getLeft();
                    int i = this.hideSize;
                    animSlide(view, view.getLeft(), -this.hideSize, ((left + i) * 500) / (width + i));
                    return;
                }
                animSlide(view, view.getLeft(), (((RelativeLayout) view.getParent()).getWidth() - view.getWidth()) + this.hideSize, (((((RelativeLayout) view.getParent()).getWidth() + this.hideSize) - view.getRight()) * 500) / (width + this.hideSize));
                return;
            case 2:
                int top = view.getTop();
                int left2 = view.getLeft();
                int bottom = view.getBottom();
                int right = view.getRight();
                int rawX = (int) (motionEvent.getRawX() - this.lastX);
                int rawY = (int) (motionEvent.getRawY() - this.lastY);
                if (top < 0) {
                    top = 0;
                    bottom = view.getHeight();
                }
                int height = ((RelativeLayout) view.getParent()).getHeight();
                int width2 = ((RelativeLayout) view.getParent()).getWidth();
                if (bottom > height) {
                    top = height - view.getHeight();
                    bottom = height;
                }
                int i2 = this.hideSize;
                if (left2 < (-i2)) {
                    left2 = -i2;
                    right = view.getWidth() - this.hideSize;
                }
                int i3 = this.hideSize;
                if (right > width2 + i3) {
                    right = width2 + i3;
                    left2 = this.hideSize + (width2 - view.getWidth());
                }
                view.layout(left2 + rawX, top + rawY, right + rawX, bottom + rawY);
                this.lastX = (int) motionEvent.getRawX();
                this.lastY = (int) motionEvent.getRawY();
                return;
            default:
                return;
        }
    }

    private void animSlide(final View view, int i, int i2, int i3) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                View view2 = view;
                view2.layout(intValue, view2.getTop(), view.getWidth() + intValue, view.getBottom());
            }
        });
        ofInt.setDuration(i3 < 0 ? 0L : i3);
        ofInt.start();
    }

    public boolean isDisableBack() {
        return this.disableBack;
    }

    public void setDisableBack(boolean z) {
        this.disableBack = z;
    }

    public void switchCamera(boolean z) {
        this.remoteCamera = z;
        if (z) {
            this.rlRemoteShade.setZ(150.0f);
            this.rlRemoteShade.setVisibility(0);
            return;
        }
        this.rlRemoteShade.setZ(0.0f);
        this.rlRemoteShade.setVisibility(8);
    }

    public void hideOrResumeWindows(boolean z) {
        if (z) {
            if (this.isWindowCreate) {
                zoomView(true);
                this.root.setVisibility(8);
            }
        } else if (this.isWindowCreate) {
            this.root.setVisibility(0);
        }
    }

    public void setTips(String str) {
        TextView textView;
        if (TextUtils.isEmpty(str) || "null".equals(str) || (textView = this.tvTips) == null) {
            return;
        }
        textView.setText(str);
    }

    public boolean getRtcStatus() {
        return this.isWindowCreate;
    }

    public void addAlertView(View view) {
        if (this.rlVoiceRoot != null) {
            this.alertView = view;
            if (this.isMove) {
                WindowManager.LayoutParams layoutParams = this.windowLayoutParams;
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.x = 0;
                layoutParams.y = 0;
                this.mWindowManager.updateViewLayout(this.root, layoutParams);
            }
            this.rlVoiceRoot.addView(this.alertView);
            Log.d(TAG, "addAlertView");
        }
    }

    public void removeAlertView() {
        View view;
        RelativeLayout relativeLayout = this.rlVoiceRoot;
        if (relativeLayout == null || (view = this.alertView) == null) {
            return;
        }
        relativeLayout.removeView(view);
        Log.d(TAG, "removeAlertView");
    }

    public void hiddenWebRtcView(boolean z) {
        if (z) {
            WindowManager.LayoutParams layoutParams = this.windowLayoutParams;
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.x = 0;
            layoutParams.y = 0;
            this.mWindowManager.updateViewLayout(this.root, layoutParams);
        } else if (this.isMove) {
            zoomView(true);
        } else {
            zoomView(false);
        }
    }
}
