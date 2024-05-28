package com.example.asus.detectionandalign;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asus.detectionandalign.utils.CountDownTimerC4299j;
import com.example.asus.detectionandalign.utils.HandlerThreadC4297i;
import com.example.asus.detectionandalign.utils.LogUtils;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import comp.android.app.face.p381sz.camera.JCameraView;
import comp.android.app.face.p381sz.camera.listener.ErrorListener;
import comp.android.app.face.p381sz.camera.listener.JCameraListener;
import comp.android.app.face.p381sz.camera.listener.OnPreviewCallback;
import java.io.File;
import java.io.Serializable;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StartLivenessNew extends BaseActivity implements ErrorListener, JCameraListener, OnPreviewCallback {

    /* renamed from: A */
    private File f9849A;

    /* renamed from: B */
    private LogUtils f9850B;

    /* renamed from: C */
    private CountDownTimerC4299j f9851C;

    /* renamed from: D */
    private ProgressDialog f9852D;

    /* renamed from: E */
    private String f9853E;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: d */
    public int f9855d;

    /* renamed from: e */
    public int f9856e;

    /* renamed from: f */
    public int f9857f;

    /* renamed from: g */
    public float f9858g;

    /* renamed from: h */
    public float f9859h;

    /* renamed from: l */
    public int f9863l;

    /* renamed from: m */
    public DetectionAuthentic f9864m;

    /* renamed from: n */
    private boolean f9865n;

    /* renamed from: r */
    private TextView f9869r;

    /* renamed from: s */
    private TextView f9870s;

    /* renamed from: t */
    private TextView f9871t;

    /* renamed from: u */
    private TextView f9872u;

    /* renamed from: v */
    private TextView f9873v;

    /* renamed from: w */
    private TextView f9874w;

    /* renamed from: x */
    private JCameraView f9875x;

    /* renamed from: i */
    public boolean f9860i = false;

    /* renamed from: j */
    public boolean f9861j = false;

    /* renamed from: k */
    public boolean f9862k = true;

    /* renamed from: o */
    private int f9866o = 10;

    /* renamed from: p */
    private boolean f9867p = false;

    /* renamed from: q */
    private boolean f9868q = true;

    /* renamed from: y */
    private boolean f9876y = false;

    /* renamed from: z */
    private boolean f9877z = false;

    /* renamed from: F */
    private Handler f9854F = new Handler() { // from class: com.example.asus.detectionandalign.StartLivenessNew.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ProgressDialog progressDialog;
            DialogInterface.OnKeyListener onKeyListener;
            TextView textView;
            String str;
            TextView textView2;
            StringBuilder sb;
            String str2;
            TextView textView3;
            String str3;
            TextView textView4;
            StartLivenessNew startLivenessNew;
            int i;
            int i2 = message.what;
            if (i2 != 519) {
                if (i2 != 8705) {
                    if (i2 == 8756) {
                        StartLivenessNew.this.finish();
                        return;
                    }
                    if (i2 == 8773) {
                        textView2 = StartLivenessNew.this.f9873v;
                        sb = new StringBuilder();
                        sb.append(message.obj);
                        str2 = "";
                    } else if (i2 == 8790) {
                        if (StartLivenessNew.this.f9851C != null) {
                            StartLivenessNew.this.f9851C.cancel();
                            return;
                        }
                        return;
                    } else if (i2 == 8807) {
                        if (StartLivenessNew.this.f9852D != null) {
                            StartLivenessNew.this.f9852D.dismiss();
                            StartLivenessNew.this.f9852D = null;
                            return;
                        }
                        return;
                    } else {
                        switch (i2) {
                            case DetectionAuthentic.GENERATED_START_RECORD /* 528 */:
                                StartLivenessNew.this.f9850B.m15967a("Record", "case10 startRecord");
                                StartLivenessNew.this.f9876y = true;
                                StartLivenessNew.this.f9875x.recordFrame();
                                return;
                            case DetectionAuthentic.GENERATED_FAILED /* 529 */:
                                if (StartLivenessNew.this.f9864m != null) {
                                    StartLivenessNew.this.f9864m.onSDKUsingFail("活体检测失败，请重试", "2002");
                                    return;
                                }
                                return;
                            default:
                                switch (i2) {
                                    case DetectionAuthentic.GENERATED_STOP_RECORD /* 531 */:
                                        StartLivenessNew.this.f9850B.m15967a("Record", "case13 stop");
                                        StartLivenessNew.this.f9877z = true;
                                        StartLivenessNew.this.f9876y = false;
                                        StartLivenessNew.this.f9875x.stopRecordFrame();
                                        return;
                                    case DetectionAuthentic.DETECT_RESET_COUNTDOWN /* 532 */:
                                        textView3 = StartLivenessNew.this.f9873v;
                                        str3 = "...";
                                        textView3.setText(str3);
                                        return;
                                    case DetectionAuthentic.DETECT_KEEP_ON /* 533 */:
                                        StartLivenessNew.this.f9874w.setText(StartLivenessNew.this.getString(C4243R.string.living_step_hint_focus));
                                        textView = StartLivenessNew.this.f9874w;
                                        str = "#87CEEB";
                                        textView.setTextColor(Color.parseColor(str));
                                        return;
                                    case DetectionAuthentic.DETECT_NON_LIVING_BODY /* 534 */:
                                        StartLivenessNew.this.f9874w.setText(StartLivenessNew.this.getString(C4243R.string.living_step_hint_unsatisfied));
                                        StartLivenessNew.this.f9873v.setText("...");
                                        break;
                                    case DetectionAuthentic.DETECT_LOG /* 535 */:
                                        String[] split = ((String) message.obj).split("##");
                                        String str4 = "0";
                                        String str5 = "0";
                                        String str6 = "0";
                                        String str7 = "";
                                        if (split.length > 0) {
                                            str4 = split[0];
                                            if (split.length > 1) {
                                                str5 = split[1];
                                                if (split.length > 2) {
                                                    str6 = split[2];
                                                    if (split.length > 3) {
                                                        str7 = split[3];
                                                    }
                                                }
                                            }
                                        }
                                        StartLivenessNew.this.f9870s.setText("当前获取帧数：" + str4 + "   " + str7);
                                        StartLivenessNew.this.f9869r.setText("检测通过帧数：" + str5);
                                        textView3 = StartLivenessNew.this.f9871t;
                                        str3 = "当前轮数：" + str6;
                                        textView3.setText(str3);
                                        return;
                                    case DetectionAuthentic.DETECT_ABNORMAL /* 536 */:
                                        str2 = message.getData().getString("error");
                                        textView2 = StartLivenessNew.this.f9872u;
                                        sb = new StringBuilder();
                                        sb.append("检测异常：");
                                        break;
                                    case DetectionAuthentic.DETECT_CHECKING /* 537 */:
                                        if (!StartLivenessNew.this.isFinishing()) {
                                            StartLivenessNew startLivenessNew2 = StartLivenessNew.this;
                                            startLivenessNew2.f9852D = ProgressDialog.show(startLivenessNew2, null, startLivenessNew2.getResources().getString(C4243R.string.testing_please_wait), true, false);
                                            progressDialog = StartLivenessNew.this.f9852D;
                                            onKeyListener = new DialogInterface.OnKeyListener() { // from class: com.example.asus.detectionandalign.StartLivenessNew.1.2
                                                @Override // android.content.DialogInterface.OnKeyListener
                                                public boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent keyEvent) {
                                                    if (i3 == 4) {
                                                        StartLivenessNew.this.f9852D.dismiss();
                                                        StartLivenessNew.this.onBackPressed();
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                            };
                                            break;
                                        } else {
                                            return;
                                        }
                                    default:
                                        switch (i2) {
                                            case 544:
                                                StartLivenessNew.this.f9874w.setText(C4243R.string.living_step_hint_camera_aperture);
                                                StartLivenessNew.this.f9873v.setText("...");
                                                break;
                                            case DetectionAuthentic.DETECT_NON_LIVING_BODY2 /* 545 */:
                                                textView4 = StartLivenessNew.this.f9874w;
                                                startLivenessNew = StartLivenessNew.this;
                                                i = C4243R.string.living_step_hint_unsatisfied;
                                                textView4.setText(startLivenessNew.getString(i));
                                                textView = StartLivenessNew.this.f9874w;
                                                str = "#FFFF00";
                                                break;
                                            case 546:
                                                textView4 = StartLivenessNew.this.f9874w;
                                                startLivenessNew = StartLivenessNew.this;
                                                i = C4243R.string.living_step_hint_preview;
                                                textView4.setText(startLivenessNew.getString(i));
                                                textView = StartLivenessNew.this.f9874w;
                                                str = "#FFFF00";
                                                break;
                                            default:
                                                switch (i2) {
                                                    case DetectionAuthentic.GENERATED_FINISH_RECORD /* 8497 */:
                                                        StartLivenessNew.this.f9850B.m15967a("Record", "case13 finish");
                                                        StartLivenessNew.this.f9876y = false;
                                                        StartLivenessNew.this.f9875x.stopRecordFrame();
                                                        return;
                                                    case DetectionAuthentic.GENERATED_ANEW_RECORD /* 8498 */:
                                                    default:
                                                        return;
                                                }
                                        }
                                        textView.setTextColor(Color.parseColor(str));
                                        return;
                                }
                        }
                    }
                    sb.append(str2);
                    textView2.setText(sb.toString());
                    return;
                }
                StartLivenessNew.this.f9874w.setText(C4243R.string.living_step_hint_camera_aperture);
                textView = StartLivenessNew.this.f9874w;
                str = "#FFF44747";
                textView.setTextColor(Color.parseColor(str));
                return;
            }
            StartLivenessNew.this.f9852D = null;
            if (StartLivenessNew.this.isFinishing()) {
                return;
            }
            StartLivenessNew startLivenessNew3 = StartLivenessNew.this;
            startLivenessNew3.f9852D = ProgressDialog.show(startLivenessNew3, null, startLivenessNew3.getResources().getString(C4243R.string.generated_packet), true, false);
            progressDialog = StartLivenessNew.this.f9852D;
            onKeyListener = new DialogInterface.OnKeyListener() { // from class: com.example.asus.detectionandalign.StartLivenessNew.1.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent keyEvent) {
                    if (i3 == 4) {
                        StartLivenessNew.this.f9852D.dismiss();
                        StartLivenessNew.this.onBackPressed();
                        return false;
                    }
                    return false;
                }
            };
            progressDialog.setOnKeyListener(onKeyListener);
        }
    };

    /* renamed from: b */
    private void m16257b() {
        this.f9856e = getIntent().getIntExtra("TotalActions", 3);
        this.f9855d = getIntent().getIntExtra("TimeoutMs", 5);
        this.f9857f = getIntent().getIntExtra("TotalPictures", 3);
        this.f9866o = getIntent().getIntExtra("countDown", 10);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (defaultSharedPreferences.getBoolean("pref_liveness_debug_mode", false)) {
            this.f9856e = Integer.valueOf(defaultSharedPreferences.getString("pref_frame_counts_list", "3")).intValue();
            this.f9855d = Integer.valueOf(defaultSharedPreferences.getString("pref_liveness_detection_overtime_list", "5")).intValue();
            this.f9857f = Integer.valueOf(defaultSharedPreferences.getString("pref_picture_package_counts_list", "3")).intValue();
            this.f9858g = Float.valueOf(defaultSharedPreferences.getString("pref_liveness_param", "0.5")).floatValue();
            this.f9859h = Float.valueOf(defaultSharedPreferences.getString("pref_enfilade_param", "0.6")).floatValue();
            this.f9860i = defaultSharedPreferences.getBoolean("pref_return_type", false);
            this.f9862k = defaultSharedPreferences.getBoolean("pre_inspection_countdown", true);
            this.f9865n = defaultSharedPreferences.getBoolean("pre_is_preposition", true);
            this.f9866o = Integer.valueOf(defaultSharedPreferences.getString("pre_inspection_countdown_time_list", "5")).intValue();
            this.f9867p = defaultSharedPreferences.getBoolean("pre_is_cache_failed_liveness", false);
            this.f9868q = defaultSharedPreferences.getBoolean("pre_kjnova_clipper", false);
            if (this.f9865n) {
                this.f9863l = 1;
            } else {
                this.f9863l = 0;
            }
            this.f9850B.m15967a(this.f9737a, "this is DebugMode!");
            LogUtils logUtils = this.f9850B;
            String str = this.f9737a;
            logUtils.m15967a(str, "totalActions：" + String.valueOf(this.f9856e));
            LogUtils logUtils2 = this.f9850B;
            String str2 = this.f9737a;
            logUtils2.m15967a(str2, "timeoutMS：" + String.valueOf(this.f9855d));
            LogUtils logUtils3 = this.f9850B;
            String str3 = this.f9737a;
            logUtils3.m15967a(str3, "totalPictures：" + String.valueOf(this.f9857f));
            LogUtils logUtils4 = this.f9850B;
            String str4 = this.f9737a;
            logUtils4.m15967a(str4, "livenessParam：" + String.valueOf(this.f9858g));
            if (defaultSharedPreferences.getBoolean("pref_liveness_log", false)) {
                this.f9870s.setVisibility(0);
                this.f9869r.setVisibility(0);
                this.f9871t.setVisibility(0);
                this.f9872u.setVisibility(0);
            }
        } else {
            Serializable serializableExtra = getIntent().getSerializableExtra("LivenessConfiguration");
            if (serializableExtra instanceof LivenessConfig) {
                LivenessConfig livenessConfig = (LivenessConfig) serializableExtra;
                this.f9858g = livenessConfig.getLivenessParam();
                this.f9860i = livenessConfig.getReturnType();
                this.f9861j = livenessConfig.isTailor4_3();
                this.f9868q = livenessConfig.getIsTailor();
                this.f9859h = livenessConfig.getEnfiladeParam();
                this.f9862k = livenessConfig.getIsCountDown();
                this.f9865n = livenessConfig.getIsPreposition();
            } else {
                DetectionAuthentic detectionAuthentic = this.f9864m;
                if (detectionAuthentic != null) {
                    detectionAuthentic.onSDKUsingFail("初始化参数错误", "3000");
                }
                finish();
            }
        }
        if (this.f9865n) {
            this.f9863l = 1;
        } else {
            this.f9863l = 0;
        }
        if (this.f9860i) {
            this.f9856e = this.f9855d * 2;
        }
        LogUtils logUtils5 = this.f9850B;
        String str5 = this.f9737a;
        logUtils5.m15967a(str5, "returnType：数据类型是否是视频" + String.valueOf(this.f9860i));
    }

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void AudioPermissionError() {
        Toast.makeText(this, "给点录音权限可以?", 0).show();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void captureSuccess(byte[] bArr) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.example.asus.detectionandalign.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        this.f9850B = LogUtils.m15966a(true);
        this.f9850B.m15964c(this.f9737a, "####################################################################################");
        HandlerThreadC4297i.m15943a();
        setContentView(C4243R.C4247layout.activity_start_liveness_new);
        this.f9875x = (JCameraView) findViewById(C4243R.C4246id.surfaceView);
        this.f9870s = (TextView) findViewById(C4243R.C4246id.frameDetected);
        this.f9869r = (TextView) findViewById(C4243R.C4246id.livenessScore);
        this.f9871t = (TextView) findViewById(C4243R.C4246id.round);
        this.f9872u = (TextView) findViewById(C4243R.C4246id.error);
        this.f9873v = (TextView) findViewById(C4243R.C4246id.count_time_textview);
        this.f9874w = (TextView) findViewById(C4243R.C4246id.oliveapp_detected_hint_text);
        setLayoutParams((RelativeLayout) findViewById(C4243R.C4246id.relative));
        setLayoutParams1((RelativeLayout) findViewById(C4243R.C4246id.prompt_message));
        this.f9864m = DetectionAuthentic.getInstance(this, null);
        this.f9853E = m16293a((Context) this) + "/Media";
        m16257b();
        LogUtils logUtils = this.f9850B;
        logUtils.m15967a("camera_direct", "" + this.f9863l);
        this.f9875x.start(this.f9863l);
        this.f9875x.setSaveVideoPath(this.f9853E);
        this.f9875x.setPlayVideo(false);
        this.f9875x.setFeatures(258);
        this.f9875x.setMediaQuality(1600000);
        this.f9875x.setCameraSettings(false);
        this.f9875x.setOnPreviewCallback(this);
        this.f9875x.setErrorLisenter(this);
        this.f9875x.setJCameraLisenter(this);
        this.f9851C = new CountDownTimerC4299j(this.f9866o * 1000, 1000L, new CountDownTimerC4299j.InterfaceC4300a() { // from class: com.example.asus.detectionandalign.StartLivenessNew.2
            @Override // com.example.asus.detectionandalign.utils.CountDownTimerC4299j.InterfaceC4300a
            /* renamed from: a */
            public void mo15939a() {
                if (StartLivenessNew.this.f9864m != null) {
                    StartLivenessNew.this.f9864m.onSDKUsingFail("检测超时，请重试", "2007");
                }
                StartLivenessNew.this.finish();
            }

            @Override // com.example.asus.detectionandalign.utils.CountDownTimerC4299j.InterfaceC4300a
            /* renamed from: a */
            public void mo15938a(long j) {
                TextView textView = StartLivenessNew.this.f9873v;
                textView.setText((j / 1000) + "");
            }
        });
        if (this.f9862k) {
            this.f9851C.start();
        }
        m16294a();
        DetectionAuthentic detectionAuthentic = this.f9864m;
        detectionAuthentic.getLivenessInstance(this, this.f9854F, detectionAuthentic, this.f9863l, this.f9856e, this.f9855d, this.f9857f);
        this.f9864m.setLivenessCachePathFailedLiveness(this.f9867p);
        this.f9864m.setLivenessPreKjnovaClipper(this.f9868q);
        this.f9864m.setLivenessReturnType(this.f9860i);
        this.f9864m.setLivenessIsTailor4_3(this.f9861j);
        this.f9864m.setLivenessParam(this.f9858g);
        this.f9864m.setLivenessEnfiladeParam(this.f9859h);
        this.f9864m.setCachePathMedia(this.f9853E);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.example.asus.detectionandalign.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f9875x.onDestroy();
        this.f9864m.onLivenessDestroy();
        CountDownTimerC4299j countDownTimerC4299j = this.f9851C;
        if (countDownTimerC4299j != null) {
            countDownTimerC4299j.cancel();
        }
        this.f9854F.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_DISMISS_DIALOG, 0L);
        this.f9850B.m15965b(this.f9737a, "[END] StartLiveness::onDestroy()");
        HandlerThreadC4297i.m15941b();
        this.f9864m = null;
        this.f9854F = null;
        System.gc();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void onError() {
        this.f9850B.m15967a("error", "camera error");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.example.asus.detectionandalign.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f9875x.onPause();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.OnPreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.f9864m.startingLivenessTest(bArr, this.f9875x);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.example.asus.detectionandalign.BaseActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.f9875x.onResume();
        if (!this.f9860i) {
            this.f9875x.setAutoFocus(1L);
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    protected void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    protected void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        if (this.f9876y) {
            this.f9876y = false;
            this.f9877z = true;
            this.f9875x.stopRecordFrame();
        }
    }

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void recordSuccess(String str, Bitmap bitmap) {
        this.f9849A = new File(str);
        this.f9876y = false;
        if (this.f9877z) {
            this.f9849A.delete();
            this.f9850B.m15967a(this.f9737a, " Delete视频  ");
            this.f9877z = false;
        } else if (this.f9860i) {
            this.f9850B.m15967a(this.f9737a, "路径返回开启打包 ");
            Message message = new Message();
            message.what = 519;
            this.f9854F.sendMessage(message);
            this.f9864m.videoLivenessCut(this.f9849A);
        }
    }
}
