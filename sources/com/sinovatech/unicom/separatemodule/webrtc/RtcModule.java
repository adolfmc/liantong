package com.sinovatech.unicom.separatemodule.webrtc;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.RTCVideoView;
import com.baidu.rtc.RtcParameterSettings;
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
public class RtcModule implements BaiduRtcRoom.BaiduRtcRoomDelegate {
    private static final String TAG = "RtcModule";
    private static final int mBitrate = 300000;
    private static final int mFps = 24;
    private static final int mHeight = 1920;
    private static final int mScreenOrientation = 1;
    private static final int mWidth = 1080;
    private Activity activityContext;
    private View alertView;
    private String beautyLevel;
    private CallBack callBack;
    double clickX;
    double clickY;

    /* renamed from: group  reason: collision with root package name */
    private ViewGroup f27862group;
    private boolean isCalling;
    private boolean isMove;
    public boolean isWindowCreate;
    private ImageView ivCameraSwitch;
    private ImageView ivCancel;
    private ImageView ivEndCall;
    private ImageView ivRtcBg;
    private ImageView ivSure;
    private ImageView ivZoom;
    double lastY;
    double lastx;
    private LinearLayout llFunction;
    private MediaPlayer mMediaPlayer;
    private RTCVideoView mRecordPreviewContainer;
    private RTCVideoView mRtcRemotePreview;
    private BaiduRtcRoom mVideoRoom;
    private WindowManager mWindowManager;
    private RelativeLayout rlLocalParent;
    private RelativeLayout rlLocalShade;
    private RelativeLayout rlMiniWindow;
    private RelativeLayout rlMove;
    private RelativeLayout rlParentSecond;
    private RelativeLayout rlRemoteShade;
    private RelativeLayout rlRoot;
    private String role;
    private String roomId;
    private View root;
    private Disposable timer;
    private TextView tvCameraSwitch;
    private TextView tvName;
    private TextView tvTips;
    private int type;
    private View vShade;
    private Vibrator vibrator;
    private WindowManager.LayoutParams windowLayoutParams;
    private String mVideoResolution = "640x480-800kbps";
    private boolean rtccalling = true;
    boolean isWaiting = true;
    private int mAudioFrequency = 48000;
    private int mInputAudioChannel = 1;
    private int mOutputAudioChannel = 1;
    boolean mAutoPublish = true;
    boolean mAutoSubscribe = false;
    private int mWeakNetworkPolicy = 1;
    private boolean isSwitch = true;
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

    public RtcModule(Activity activity, ViewGroup viewGroup, String str, int i, String str2, String str3, CallBack callBack) {
        this.activityContext = activity;
        this.f27862group = viewGroup;
        this.beautyLevel = str2;
        this.callBack = callBack;
        this.role = str3;
        this.roomId = TextUtils.isEmpty(str) ? getRoomId() : str;
        this.type = i;
        if (this.root == null) {
            this.root = LayoutInflater.from(activity).inflate(2131492939, (ViewGroup) null);
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
        this.mVideoRoom.muteCamera(true);
        RtcParameterSettings defaultSettings = RtcParameterSettings.getDefaultSettings();
        defaultSettings.VideoResolution = this.mVideoResolution;
        defaultSettings.VideoFps = 30;
        defaultSettings.AutoPublish = true;
        defaultSettings.AutoSubScribe = true;
        this.mVideoRoom.setParamSettings(defaultSettings, RtcParameterSettings.RtcParamSettingType.RTC_PARAM_SETTINGS_ALL);
    }

    private void initView(View view) {
        this.ivRtcBg = (ImageView) view.findViewById(2131297476);
        this.rlRoot = (RelativeLayout) view.findViewById(2131296441);
        this.rlMiniWindow = (RelativeLayout) view.findViewById(2131298378);
        this.rlParentSecond = (RelativeLayout) view.findViewById(2131298379);
        this.ivCameraSwitch = (ImageView) view.findViewById(2131297477);
        this.tvCameraSwitch = (TextView) view.findViewById(2131299062);
        this.llFunction = (LinearLayout) view.findViewById(2131297769);
        this.vShade = view.findViewById(2131299528);
        this.rlMove = (RelativeLayout) view.findViewById(2131298380);
        this.rlRemoteShade = (RelativeLayout) view.findViewById(2131298329);
        this.rlLocalParent = (RelativeLayout) view.findViewById(2131298390);
        this.rlLocalShade = (RelativeLayout) view.findViewById(2131298330);
        this.tvName = (TextView) view.findViewById(2131299068);
        this.tvTips = (TextView) view.findViewById(2131299105);
        this.ivCancel = (ImageView) view.findViewById(2131297478);
        this.ivEndCall = (ImageView) view.findViewById(2131297381);
        this.ivSure = (ImageView) view.findViewById(2131297480);
        this.ivZoom = (ImageView) view.findViewById(2131297531);
        this.ivCancel.setVisibility(this.type != 2 ? 8 : 0);
        this.ivSure.setVisibility(this.type != 2 ? 8 : 0);
        this.ivEndCall.setVisibility(this.type != 2 ? 0 : 8);
        this.ivEndCall.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$QJfrXcf4iY5ho1O5PhLbzuEPxDg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcModule.lambda$initView$0(RtcModule.this, view2);
            }
        });
        this.ivZoom.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$7QMzFy_DnhMGSg6u9Ufe7Y4Q4SE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcModule.this.zoomView(true);
            }
        });
        this.ivCameraSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$NAa3eHmcqMxD7LD-xVPu8DW2_Wo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcModule.lambda$initView$2(RtcModule.this, view2);
            }
        });
        this.ivSure.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$cd0D8FTptNclBMTzVLDKmNx2SUE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcModule.lambda$initView$3(RtcModule.this, view2);
            }
        });
        this.ivCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$t26x3oJ184IL0pzvFLJYiUZe8-I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcModule.lambda$initView$4(RtcModule.this, view2);
            }
        });
        this.root.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$4xmsclcrp3bnP7PPGQACSQQ0LkM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RtcModule.lambda$initView$5(RtcModule.this, view2);
            }
        });
        this.root.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcModule.1
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
                    RtcModule.this.windowLayoutParams.x += this.mMoveX;
                    RtcModule.this.windowLayoutParams.y += this.mMoveY;
                    RtcModule.this.mWindowManager.updateViewLayout(RtcModule.this.root, RtcModule.this.windowLayoutParams);
                } else if (motionEvent.getAction() == 1) {
                    RtcModule.this.mWindowManager.updateViewLayout(RtcModule.this.root, RtcModule.this.windowLayoutParams);
                    if (motionEvent.getEventTime() - motionEvent.getDownTime() < 150) {
                        Log.d(RtcModule.TAG, "按下抬起小于100 按点击事件处理");
                        RtcModule.this.root.performClick();
                    }
                }
                return true;
            }
        });
    }

    public static /* synthetic */ void lambda$initView$0(RtcModule rtcModule, View view) {
        Log.d(TAG, "挂断点击了");
        CallBack callBack = rtcModule.callBack;
        if (callBack != null) {
            callBack.callback(1, 12);
        }
    }

    public static /* synthetic */ void lambda$initView$2(RtcModule rtcModule, View view) {
        CallBack callBack = rtcModule.callBack;
        if (callBack != null) {
            callBack.callback(1, rtcModule.isSwitch ? 57 : 58);
        }
        if (rtcModule.isSwitch) {
            rtcModule.ivCameraSwitch.setImageResource(2131231594);
            rtcModule.tvCameraSwitch.setText("摄像头已关");
            rtcModule.isSwitch = false;
            rtcModule.mVideoRoom.muteCamera(true);
            rtcModule.rlLocalShade.setVisibility(0);
            return;
        }
        rtcModule.ivCameraSwitch.setImageResource(2131231595);
        rtcModule.tvCameraSwitch.setText("摄像头已开");
        rtcModule.isSwitch = true;
        rtcModule.mVideoRoom.muteCamera(false);
        rtcModule.rlLocalShade.setVisibility(8);
    }

    public static /* synthetic */ void lambda$initView$3(RtcModule rtcModule, View view) {
        String substring;
        rtcModule.ivCancel.setVisibility(8);
        rtcModule.ivSure.setVisibility(8);
        rtcModule.ivEndCall.setVisibility(0);
        rtcModule.tvTips.setText("视频接通中，请稍后...");
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if ("0".equals(currentPhoneNumber)) {
            substring = String.valueOf((int) (((Math.random() * 9.0d) + 1.0d) * 10000.0d));
        } else {
            substring = currentPhoneNumber.substring(3);
        }
        rtcModule.mVideoRoom.loginRtcRoomWithRoomName(rtcModule.roomId, Long.parseLong(substring), substring);
    }

    public static /* synthetic */ void lambda$initView$4(RtcModule rtcModule, View view) {
        CallBack callBack = rtcModule.callBack;
        if (callBack != null) {
            callBack.callback(2, 12);
        }
    }

    public static /* synthetic */ void lambda$initView$5(RtcModule rtcModule, View view) {
        if (rtcModule.isMove) {
            rtcModule.zoomView(false);
        }
    }

    private void moveMethod2(double d, double d2) {
        RelativeLayout relativeLayout = this.rlMove;
        relativeLayout.setTranslationX((float) (relativeLayout.getTranslationX() + d));
        RelativeLayout relativeLayout2 = this.rlMove;
        relativeLayout2.setTranslationY((float) (relativeLayout2.getTranslationY() + d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWait() {
        this.ivRtcBg.setVisibility(8);
        this.tvName.setVisibility(8);
        this.tvTips.setVisibility(8);
        this.ivCancel.setVisibility(8);
        this.ivSure.setVisibility(8);
        this.ivEndCall.setVisibility(0);
        this.llFunction.setVisibility(0);
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.cancel();
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
        }
        if ("1".equals(this.role)) {
            zoomView(true);
        } else if (this.isMove) {
            zoomView(false);
        }
    }

    private void initPreviewContainer(View view) {
        this.mRtcRemotePreview = (RTCVideoView) view.findViewById(2131298422);
        this.mRecordPreviewContainer = (RTCVideoView) view.findViewById(2131299170);
    }

    @Override // com.baidu.rtc.BaiduRtcRoom.BaiduRtcRoomDelegate
    public void onRoomEventUpdate(int i, final long j, String str) {
        Log.d(TAG, " roomEvents: " + i + " extra_info :" + str);
        if (i != 118) {
            if (i == 200) {
                if (this.isCalling && this.rtccalling) {
                    this.rtccalling = false;
                    return;
                }
                return;
            } else if (i != 436) {
                if (i != 2002) {
                    switch (i) {
                        case 100:
                            return;
                        case 101:
                        case 102:
                        case 103:
                            break;
                        case 104:
                            this.isCalling = true;
                            this.isWaiting = false;
                            this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcModule.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    RtcModule.this.mVideoRoom.setRemoteDisplay(RtcModule.this.mRtcRemotePreview, j);
                                    RtcModule.this.mRecordPreviewContainer.setVisibility(0);
                                    RtcModule.this.clearWait();
                                    if (RtcModule.this.callBack != null) {
                                        RtcModule.this.callBack.callback(1, 11);
                                    }
                                }
                            });
                            return;
                        default:
                            switch (i) {
                                case 300:
                                    Log.i(TAG, "用户加入房间==" + str);
                                    this.isCalling = true;
                                    this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcModule.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            RtcModule rtcModule = RtcModule.this;
                                            rtcModule.isWaiting = false;
                                            if (rtcModule.callBack != null) {
                                                RtcModule.this.callBack.callback(1, 15);
                                            }
                                        }
                                    });
                                    return;
                                case 301:
                                    Log.i(TAG, "用户离开房间==" + str);
                                    this.rtccalling = false;
                                    this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcModule.3
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            Log.d(RtcModule.TAG, "隐藏相关布局");
                                            if (RtcModule.this.callBack != null) {
                                                RtcModule.this.callBack.callback(RtcModule.this.isPingSuccess(), 13);
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
        this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcModule.2
            @Override // java.lang.Runnable
            public void run() {
                if (RtcModule.this.callBack != null) {
                    RtcModule.this.callBack.callback(RtcModule.this.isPingSuccess(), 13);
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

    public boolean isDisableBack() {
        return this.disableBack;
    }

    public void setDisableBack(boolean z) {
        this.disableBack = z;
    }

    public void recycleResource() {
        Log.d(TAG, "recycleResource销毁");
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
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeView(this.root);
        }
        this.disableBack = false;
        this.rtccalling = false;
        this.isWindowCreate = false;
        this.isWaiting = true;
        this.timer = Observable.timer(300L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$RtcModule$-aWfbCfh1yBI7T9uZ7Q0FL_wkaQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RtcModule.lambda$recycleResource$6(RtcModule.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$recycleResource$6(RtcModule rtcModule, Long l) throws Exception {
        rtcModule.mVideoRoom.logoutRtcRoom();
        rtcModule.mVideoRoom.setRemoteAudioSamplesCallback(null);
        rtcModule.mVideoRoom.setMixedAudioSamplesCallback(null);
        rtcModule.mVideoRoom.destroy();
        UIUtils.logD(TAG, "销毁房间、驱动");
        Disposable disposable = rtcModule.timer;
        if (disposable != null) {
            disposable.dispose();
            rtcModule.timer = null;
        }
    }

    public String getRoomId() {
        return DeviceHelper.getDeviceID(false);
    }

    public void zoomView(boolean z) {
        if (z) {
            this.isMove = true;
            if (this.isWaiting) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.width = 1;
                layoutParams.height = 1;
                this.rlParentSecond.setLayoutParams(layoutParams);
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
            }
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.width = UIUtils.dip2px(this.activityContext, 60.0f);
            layoutParams3.height = UIUtils.dip2px(this.activityContext, 90.0f);
            layoutParams3.addRule(11);
            layoutParams3.addRule(15);
            this.rlMove.setLayoutParams(layoutParams3);
            int dip2px = UIUtils.dip2px(this.activityContext, 4.0f);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mRtcRemotePreview.getLayoutParams();
            layoutParams4.setMargins(dip2px, dip2px, dip2px, dip2px);
            this.mRtcRemotePreview.setLayoutParams(layoutParams4);
            this.vShade.setVisibility(0);
            this.ivZoom.setVisibility(8);
            this.ivEndCall.setVisibility(8);
            this.llFunction.setVisibility(8);
            this.mRecordPreviewContainer.setVisibility(8);
            this.windowLayoutParams.x = UIUtils.getScreenWidth(this.activityContext) - 60;
            this.windowLayoutParams.width = UIUtils.dip2px(this.activityContext, 60.0f);
            this.windowLayoutParams.height = UIUtils.dip2px(this.activityContext, 90.0f);
            this.mWindowManager.updateViewLayout(this.root, this.windowLayoutParams);
            return;
        }
        this.isMove = false;
        this.rlParentSecond.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.rlParentSecond.setVisibility(0);
        this.rlMiniWindow.setVisibility(4);
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.rlMove.getLayoutParams();
        layoutParams5.width = -1;
        layoutParams5.height = -1;
        this.rlMove.setLayoutParams(layoutParams5);
        this.ivZoom.setVisibility(0);
        if (!this.isWaiting) {
            this.ivEndCall.setVisibility(0);
            this.llFunction.setVisibility(0);
        }
        this.mRecordPreviewContainer.setVisibility(0);
        this.mRtcRemotePreview.setZOrderMediaOverlay(false);
        this.mRecordPreviewContainer.setZOrderMediaOverlay(true);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams6.setMargins(0, 0, 0, 0);
        this.mRtcRemotePreview.setLayoutParams(layoutParams6);
        this.vShade.setVisibility(8);
        this.rlMove.setTranslationX(0.0f);
        this.rlMove.setTranslationY(0.0f);
        WindowManager.LayoutParams layoutParams7 = this.windowLayoutParams;
        layoutParams7.width = -1;
        layoutParams7.height = -1;
        layoutParams7.x = 0;
        layoutParams7.y = 0;
        this.mWindowManager.updateViewLayout(this.root, layoutParams7);
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
        this.ivCancel.setVisibility(this.type != 2 ? 8 : 0);
        this.ivSure.setVisibility(this.type != 2 ? 8 : 0);
        this.ivEndCall.setVisibility(8);
        this.root.setVisibility(0);
        this.roomId = str;
        TextView textView = this.tvName;
        if (textView != null) {
            textView.setText(str2);
        }
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
        this.ivCancel.setVisibility(this.type != 2 ? 8 : 0);
        this.ivSure.setVisibility(this.type == 2 ? 0 : 8);
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

    public void setUserNick(String str) {
        TextView textView = this.tvName;
        if (textView != null) {
            textView.setText(str);
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

    private void DoLogin() {
        String substring;
        Log.i(TAG, "DoLogin");
        RtcParameterSettings defaultSettings = RtcParameterSettings.getDefaultSettings();
        String str = this.mVideoResolution;
        defaultSettings.VideoResolution = str;
        defaultSettings.MediaServerIP = "";
        defaultSettings.VideoFps = 15;
        if (str.contains("128kbps")) {
            defaultSettings.VideoMaxkbps = 128;
        } else if (this.mVideoResolution.contains("256kbps")) {
            defaultSettings.VideoMaxkbps = 256;
        } else if (this.mVideoResolution.contains("350kbps")) {
            defaultSettings.VideoMaxkbps = 350;
        } else if (this.mVideoResolution.contains("500kbps")) {
            defaultSettings.VideoMaxkbps = 500;
        } else if (this.mVideoResolution.contains("800kbps")) {
            defaultSettings.VideoMaxkbps = 800;
        } else if (this.mVideoResolution.contains("1000kbps")) {
            defaultSettings.VideoMaxkbps = 1000;
        } else if (this.mVideoResolution.contains("1.5Mbps")) {
            defaultSettings.VideoMaxkbps = 1500;
        } else if (this.mVideoResolution.contains("2Mbps")) {
            defaultSettings.VideoMaxkbps = 2000;
        } else if (this.mVideoResolution.contains("3Mbps")) {
            defaultSettings.VideoMaxkbps = 3000;
        } else if (this.mVideoResolution.contains("5Mbps")) {
            defaultSettings.VideoMaxkbps = 5000;
        } else if (this.mVideoResolution.contains("12Mbps")) {
            defaultSettings.VideoMaxkbps = 12000;
        } else if (this.mVideoResolution.contains("40Mbps")) {
            defaultSettings.VideoMaxkbps = 40000;
        } else {
            defaultSettings.VideoMaxkbps = 1000;
        }
        defaultSettings.HasData = false;
        defaultSettings.ConnectionTimeoutMs = 5000;
        defaultSettings.ReadTimeoutMs = 5000;
        if (Build.MANUFACTURER.contains("Ainemo") || Build.MODEL.contains("NV6001") || Build.MODEL.contains("NV6101") || Build.MODEL.contains("NV2001") || Build.MODEL.contains("NV5001")) {
            defaultSettings.AudioFrequency = 16000;
            defaultSettings.inputAudioChannel = 2;
            defaultSettings.outputAudioChannel = 2;
            defaultSettings.audioContentType = 2;
        }
        if (Build.MODEL.contains("ONEPLUS")) {
            defaultSettings.AudioSource = 0;
        }
        defaultSettings.inputAudioChannel = this.mInputAudioChannel;
        defaultSettings.outputAudioChannel = this.mOutputAudioChannel;
        defaultSettings.AudioFrequency = this.mAudioFrequency;
        defaultSettings.EnableMultistream = true;
        defaultSettings.AutoPublish = this.mAutoPublish;
        Log.i(TAG, "cfg.AutoPublish = " + this.mAutoPublish);
        defaultSettings.AutoSubScribe = this.mAutoSubscribe;
        Log.i(TAG, "cfg.AutoSubScribe = " + this.mAutoSubscribe);
        defaultSettings.weakNetworkPolicy = this.mWeakNetworkPolicy;
        defaultSettings.enableJitterRetransmission = true;
        defaultSettings.EnableFixedResolution = true;
        this.mVideoRoom.setParamSettings(defaultSettings, RtcParameterSettings.RtcParamSettingType.RTC_PARAM_SETTINGS_ALL);
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if ("0".equals(currentPhoneNumber)) {
            substring = String.valueOf((int) (((Math.random() * 9.0d) + 1.0d) * 10000.0d));
        } else {
            substring = currentPhoneNumber.substring(3);
        }
        this.mVideoRoom.loginRtcRoomWithRoomName(this.roomId, Long.parseLong(substring), substring);
    }

    public void remoteCameraStaus(boolean z) {
        RelativeLayout relativeLayout = this.rlRemoteShade;
        if (relativeLayout != null) {
            if (z) {
                relativeLayout.setVisibility(0);
            } else {
                relativeLayout.setVisibility(8);
            }
        }
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
        if (this.rlRoot != null) {
            this.alertView = view;
            if (this.isMove) {
                WindowManager.LayoutParams layoutParams = this.windowLayoutParams;
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.x = 0;
                layoutParams.y = 0;
                this.mWindowManager.updateViewLayout(this.root, layoutParams);
            }
            this.rlRoot.addView(this.alertView);
            Log.d(TAG, "addAlertView");
        }
    }

    public void removeAlertView() {
        View view;
        RelativeLayout relativeLayout = this.rlRoot;
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
