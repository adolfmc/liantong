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
import android.text.Html;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StartActionNew extends BaseActivity implements ErrorListener, JCameraListener, OnPreviewCallback {

    /* renamed from: e */
    public static final String f9815e = "StartActionNew";

    /* renamed from: B */
    private float f9817B;

    /* renamed from: C */
    private float f9818C;

    /* renamed from: F */
    private int f9821F;

    /* renamed from: H */
    private Message f9823H;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: d */
    public int f9825d;

    /* renamed from: k */
    private LogUtils f9831k;

    /* renamed from: l */
    private JCameraView f9832l;

    /* renamed from: m */
    private TextView f9833m;

    /* renamed from: n */
    private TextView f9834n;

    /* renamed from: o */
    private TextView f9835o;

    /* renamed from: p */
    private TextView f9836p;

    /* renamed from: q */
    private TextView f9837q;

    /* renamed from: r */
    private TextView f9838r;

    /* renamed from: s */
    private ImageButton f9839s;

    /* renamed from: u */
    private DetectionAuthentic f9841u;

    /* renamed from: v */
    private String f9842v;

    /* renamed from: w */
    private String f9843w;

    /* renamed from: x */
    private ProgressDialog f9844x;

    /* renamed from: h */
    private boolean f9828h = false;

    /* renamed from: i */
    private boolean f9829i = false;

    /* renamed from: j */
    private List<String> f9830j = new ArrayList(Arrays.asList("eye", "mouth", "headL", "headR", "headUp"));

    /* renamed from: t */
    private int f9840t = 1;

    /* renamed from: y */
    private List<String> f9845y = new ArrayList();

    /* renamed from: f */
    public boolean f9826f = false;

    /* renamed from: g */
    public boolean f9827g = true;

    /* renamed from: z */
    private boolean f9846z = true;

    /* renamed from: A */
    private boolean f9816A = false;

    /* renamed from: D */
    private boolean f9819D = false;

    /* renamed from: E */
    private boolean f9820E = false;

    /* renamed from: G */
    private List<Double> f9822G = new ArrayList();

    /* renamed from: I */
    private Handler f9824I = new Handler() { // from class: com.example.asus.detectionandalign.StartActionNew.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            TextView textView;
            String str;
            TextView textView2;
            StringBuilder sb;
            String sb2;
            try {
                switch (message.what) {
                    case 519:
                        StartActionNew.this.f9844x = null;
                        if (StartActionNew.this.isFinishing()) {
                            return;
                        }
                        StartActionNew.this.f9844x = ProgressDialog.show(StartActionNew.this, null, StartActionNew.this.getResources().getString(C4243R.string.generated_packet), true, false);
                        StartActionNew.this.f9844x.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.example.asus.detectionandalign.StartActionNew.1.1
                            @Override // android.content.DialogInterface.OnKeyListener
                            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                if (i == 4) {
                                    StartActionNew.this.f9844x.dismiss();
                                    StartActionNew.this.onBackPressed();
                                    return false;
                                }
                                return false;
                            }
                        });
                        return;
                    case DetectionAuthentic.DETECT_KEEP_ON /* 533 */:
                        StartActionNew.this.f9838r.setText(message.getData().getString("hint"));
                        textView = StartActionNew.this.f9838r;
                        str = "#FFFF00";
                        textView.setTextColor(Color.parseColor(str));
                        return;
                    case DetectionAuthentic.DETECT_LOG /* 535 */:
                        String[] split = ((String) message.obj).split("##");
                        String str2 = "0";
                        String str3 = "0";
                        String str4 = "0";
                        String str5 = "0";
                        String str6 = "";
                        if (split.length > 0) {
                            str2 = split[0];
                            if (split.length > 1) {
                                str3 = split[1];
                                if (split.length > 2) {
                                    str4 = split[2];
                                    if (split.length > 3) {
                                        str5 = split[3];
                                        if (split.length > 4) {
                                            str6 = split[4];
                                        }
                                    }
                                }
                            }
                        }
                        TextView textView3 = StartActionNew.this.f9835o;
                        textView3.setText("检测帧率：" + str2 + " FPS");
                        TextView textView4 = StartActionNew.this.f9833m;
                        textView4.setText("采集/检测帧数：" + String.valueOf(str3) + "/" + String.valueOf(str4) + "      " + str6);
                        TextView textView5 = StartActionNew.this.f9836p;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("动作结果：");
                        sb3.append(str5);
                        textView5.setText(Html.fromHtml(sb3.toString()));
                        return;
                    case DetectionAuthentic.DETECT_ABNORMAL /* 536 */:
                        String string = message.getData().getString("error");
                        textView2 = StartActionNew.this.f9837q;
                        sb = new StringBuilder();
                        sb.append("检测异常：");
                        sb.append(string);
                        sb2 = sb.toString();
                        textView2.setText(sb2);
                        return;
                    case 544:
                        StartActionNew.this.f9838r.setText(C4243R.string.living_step_hint_camera_aperture);
                        StartActionNew.this.f9838r.setTextColor(Color.parseColor("#FFF44747"));
                        if (StartActionNew.this.f9823H != null) {
                            StartActionNew.this.f9824I.removeMessages(DetectionAuthentic.DETECT_HINT);
                        }
                        String actionHint = StartActionNew.this.f9841u.getActionHint(StartActionNew.this.f9841u.getCurrentAction());
                        StartActionNew.this.f9823H = StartActionNew.this.f9824I.obtainMessage(DetectionAuthentic.DETECT_HINT);
                        Bundle bundle = new Bundle();
                        bundle.putString("hint", actionHint);
                        StartActionNew.this.f9823H.setData(bundle);
                        StartActionNew.this.f9824I.sendMessageDelayed(StartActionNew.this.f9823H, 800L);
                        return;
                    case 546:
                        StartActionNew.this.f9838r.setText(StartActionNew.this.getString(C4243R.string.living_step_hint_preview));
                        textView = StartActionNew.this.f9838r;
                        str = "#FFFF00";
                        textView.setTextColor(Color.parseColor(str));
                        return;
                    case DetectionAuthentic.DETECT_FINISH /* 8756 */:
                        StartActionNew.this.finish();
                        return;
                    case DetectionAuthentic.DETECT_COUNT_DOWN_TEXT /* 8773 */:
                        textView2 = StartActionNew.this.f9834n;
                        sb = new StringBuilder();
                        sb.append(message.obj);
                        sb.append("");
                        sb2 = sb.toString();
                        textView2.setText(sb2);
                        return;
                    case DetectionAuthentic.DETECT_DISMISS_DIALOG /* 8807 */:
                        if (StartActionNew.this.f9844x != null) {
                            StartActionNew.this.f9844x.dismiss();
                            StartActionNew.this.f9844x = null;
                            return;
                        }
                        return;
                    case DetectionAuthentic.DETECT_HINT /* 8808 */:
                        StartActionNew.this.f9838r.setTextColor(Color.parseColor("#FFFF00"));
                        StartActionNew.this.f9823H = null;
                        sb2 = message.getData().getString("hint");
                        textView2 = StartActionNew.this.f9838r;
                        textView2.setText(sb2);
                        return;
                    default:
                        return;
                }
            } catch (Exception unused) {
            }
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    private void m16269c() {
        this.f9825d = getIntent().getIntExtra("TotalActions", 3);
        this.f9821F = getIntent().getIntExtra("TimeoutMs", 15);
        this.f9820E = false;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.f9826f = defaultSharedPreferences.getBoolean("pref_action_debug_mode", false);
        if (this.f9826f) {
            this.f9825d = Integer.valueOf(defaultSharedPreferences.getString("pref_action_counts_list", "3")).intValue();
            this.f9821F = Integer.valueOf(defaultSharedPreferences.getString("pref_action_detection_overtime_list", "15")).intValue();
            this.f9828h = defaultSharedPreferences.getBoolean("pref_fix_action", false);
            this.f9829i = defaultSharedPreferences.getBoolean("pref_action_liveness_check", false);
            this.f9819D = defaultSharedPreferences.getBoolean("pre_action_is_cache_failed_liveness", false);
            this.f9846z = defaultSharedPreferences.getBoolean("pre_action_kjnova_clipper", false);
            this.f9820E = defaultSharedPreferences.getBoolean("pre_verify_animation_clipper", false);
            this.f9817B = Float.valueOf(defaultSharedPreferences.getString("pref_action_enfilade_param", "0.6")).floatValue();
            ArrayList arrayList = new ArrayList(Arrays.asList(defaultSharedPreferences.getString("pref_action_one_list", "default"), defaultSharedPreferences.getString("pref_action_two_list", "default"), defaultSharedPreferences.getString("pref_action_three_list", "default"), defaultSharedPreferences.getString("pref_action_four_list", "default"), defaultSharedPreferences.getString("pref_action_five_list", "default"), defaultSharedPreferences.getString("pref_action_six_list", "default"), defaultSharedPreferences.getString("pref_action_seven_list", "default"), defaultSharedPreferences.getString("pref_action_eight_list", "default"), defaultSharedPreferences.getString("pref_action_nine_list", "default")));
            this.f9827g = defaultSharedPreferences.getBoolean("pre_action_is_preposition", true);
            this.f9822G.clear();
            this.f9822G.add(Double.valueOf(defaultSharedPreferences.getString("pref_eye_param", "default")));
            this.f9822G.add(Double.valueOf(defaultSharedPreferences.getString("pref_mouth_param", "default")));
            this.f9822G.add(Double.valueOf(defaultSharedPreferences.getString("pref_headUp_param", "default")));
            this.f9822G.add(Double.valueOf(defaultSharedPreferences.getString("pref_headR_param", "default")));
            this.f9822G.add(Double.valueOf(defaultSharedPreferences.getString("pref_headL_param", "default")));
            if (this.f9828h) {
                this.f9825d = Integer.valueOf(defaultSharedPreferences.getString("pref_fix_frame_counts_list", "default")).intValue();
                for (int i = 0; i < this.f9825d; i++) {
                    this.f9845y.add(arrayList.get(i));
                }
            } else {
                m16275a(this.f9825d);
            }
            this.f9818C = Float.valueOf(defaultSharedPreferences.getString("pref_action_liveness_param", "0.5")).floatValue();
            if (defaultSharedPreferences.getBoolean("pref_action_log", false)) {
                this.f9833m.setVisibility(0);
                this.f9836p.setVisibility(0);
                this.f9837q.setVisibility(0);
            }
            this.f9831k.m15967a(f9815e, "this is DebugMode!");
        } else {
            Serializable serializableExtra = getIntent().getSerializableExtra("ActionConfiguration");
            if (serializableExtra instanceof ActionConfig) {
                ActionConfig actionConfig = (ActionConfig) serializableExtra;
                this.f9818C = actionConfig.getLivenessParam();
                this.f9828h = actionConfig.getIsFixAction();
                this.f9820E = actionConfig.getIsVerifyAnimation();
                this.f9846z = actionConfig.getIsTailor();
                this.f9816A = actionConfig.isTailor4_3();
                this.f9829i = actionConfig.getIsActionLivenessCheck();
                this.f9817B = actionConfig.getEnfiladeParam();
                this.f9827g = actionConfig.getIsPreposition();
                this.f9822G.clear();
                this.f9822G.addAll(actionConfig.getActionThresholdList());
                List<String> fixActionList = actionConfig.getFixActionList();
                if (fixActionList != null && fixActionList.size() > 0) {
                    this.f9830j.clear();
                    this.f9830j.addAll(fixActionList);
                }
                if (this.f9828h) {
                    List<String> list = this.f9830j;
                    if (list == null || list.size() < this.f9825d) {
                        DetectionAuthentic detectionAuthentic = this.f9841u;
                        if (detectionAuthentic != null) {
                            detectionAuthentic.onSDKUsingFail("初始化参数错误", "3000");
                        }
                        finish();
                    } else {
                        for (int i2 = 0; i2 < this.f9825d; i2++) {
                            this.f9845y.add(this.f9830j.get(i2));
                        }
                    }
                } else {
                    if (this.f9825d > this.f9830j.size()) {
                        this.f9825d = this.f9830j.size();
                    }
                    m16275a(this.f9825d);
                }
            }
        }
        if (this.f9827g) {
            this.f9840t = 1;
        } else {
            this.f9840t = 0;
        }
        m16271b();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void AudioPermissionError() {
        Toast.makeText(this, "给点录音权限可以?", 0).show();
    }

    /* renamed from: a */
    public void m16275a(int i) {
        this.f9831k.m15967a(f9815e, "initRandomAction");
        if (i < 6 && i > 0) {
            Collections.shuffle(this.f9830j);
            for (int i2 = 0; i2 < i; i2++) {
                this.f9845y.add(this.f9830j.get(i2));
            }
        } else if (i >= 6) {
            Collections.shuffle(this.f9830j);
            for (int i3 = 0; i3 < 5; i3++) {
                this.f9845y.add(this.f9830j.get(i3));
            }
            Collections.shuffle(this.f9830j);
            for (int i4 = 0; i4 < i - 5; i4++) {
                this.f9845y.add(this.f9830j.get(i4));
            }
        } else {
            DetectionAuthentic detectionAuthentic = this.f9841u;
            if (detectionAuthentic != null) {
                detectionAuthentic.onSDKUsingFail("初始化参数错误", "3000");
            }
            finish();
        }
        String str = "";
        for (int i5 = 0; i5 < this.f9845y.size(); i5++) {
            str = str + this.f9845y.get(i5) + " ";
        }
        this.f9831k.m15967a("actionList", str);
    }

    /* renamed from: b */
    public void m16271b() {
        String str = "";
        for (int i = 0; i < this.f9845y.size(); i++) {
            str = str + this.f9845y.get(i) + " ";
        }
        this.f9831k.m15967a("actionList", str);
        this.f9831k.m15967a(f9815e, "totalActions：" + String.valueOf(this.f9825d));
        this.f9831k.m15967a(f9815e, "timeoutMS：" + String.valueOf(this.f9821F));
        this.f9831k.m15967a(f9815e, "mFixAction：" + String.valueOf(this.f9828h));
        this.f9831k.m15967a(f9815e, "fixActionList：" + str);
        this.f9831k.m15967a(f9815e, "livenessParam：" + String.valueOf(this.f9818C));
        List<Double> list = this.f9822G;
        if (list == null || list.size() != 5) {
            return;
        }
        this.f9831k.m15967a(f9815e, "t_eye：" + this.f9822G.get(0));
        this.f9831k.m15967a(f9815e, "t_mouth：" + this.f9822G.get(1));
        this.f9831k.m15967a(f9815e, "t_headUp：" + this.f9822G.get(2));
        this.f9831k.m15967a(f9815e, "t_headR：" + this.f9822G.get(3));
        this.f9831k.m15967a(f9815e, "t_headL：" + this.f9822G.get(4));
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
        setContentView(C4243R.C4247layout.activity_start_action_new);
        this.f9831k = LogUtils.m15966a(true);
        this.f9831k.m15964c(f9815e, "####################################################################################");
        HandlerThreadC4297i.m15943a();
        this.f9832l = (JCameraView) findViewById(C4243R.C4246id.surfaceView);
        this.f9833m = (TextView) findViewById(C4243R.C4246id.livenessScore);
        this.f9835o = (TextView) findViewById(C4243R.C4246id.frameDetected);
        this.f9836p = (TextView) findViewById(C4243R.C4246id.round);
        this.f9837q = (TextView) findViewById(C4243R.C4246id.error);
        this.f9839s = (ImageButton) findViewById(C4243R.C4246id.close_image_button);
        this.f9834n = (TextView) findViewById(C4243R.C4246id.count_time_textview);
        this.f9838r = (TextView) findViewById(C4243R.C4246id.oliveapp_detected_hint_text);
        setLayoutParams((RelativeLayout) findViewById(C4243R.C4246id.relative));
        setLayoutParams1((RelativeLayout) findViewById(C4243R.C4246id.prompt_message));
        this.f9841u = DetectionAuthentic.getInstance(this, null);
        this.f9842v = m16293a((Context) this) + "/Pic";
        this.f9843w = m16293a((Context) this) + "/Media";
        m16269c();
        LogUtils logUtils = this.f9831k;
        logUtils.m15967a("camera_direct", "" + this.f9840t);
        this.f9832l.start(this.f9840t);
        this.f9832l.setSaveVideoPath(this.f9843w);
        this.f9832l.setPlayVideo(false);
        this.f9832l.setFeatures(258);
        this.f9832l.setMediaQuality(1600000);
        this.f9832l.setCameraSettings(false);
        this.f9832l.setOnPreviewCallback(this);
        this.f9832l.setErrorLisenter(this);
        this.f9832l.setJCameraLisenter(this);
        m16294a();
        DetectionAuthentic detectionAuthentic = this.f9841u;
        detectionAuthentic.getActionInstance(this, this.f9824I, detectionAuthentic, this.f9840t, this.f9821F);
        this.f9841u.setActionLivenessParam(this.f9818C);
        this.f9841u.setActionEnfiladeParam(this.f9817B);
        this.f9841u.setActionCachePathFailedLiveness(this.f9819D);
        this.f9841u.setActionVerifyAnimation(this.f9820E);
        this.f9841u.setActionPreKjnovaClipper(this.f9846z);
        this.f9841u.setActionIsTailor4_3(this.f9816A);
        this.f9841u.setAction(this.f9845y);
        this.f9841u.setActionLivenessCheck(this.f9829i);
        this.f9841u.setActionList(this.f9822G);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.example.asus.detectionandalign.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f9832l.onDestroy();
        this.f9831k.m15965b(f9815e, "[BEGIN] StartLiveness::onDestroy()");
        super.onDestroy();
        ProgressDialog progressDialog = this.f9844x;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        HandlerThreadC4297i.m15941b();
        this.f9831k.m15965b(f9815e, "[END] StartLiveness::onDestroy()");
        setContentView(C4243R.C4247layout.view_null);
        this.f9841u.onActionDestroy();
        this.f9841u = null;
        this.f9830j = null;
        this.f9824I = null;
        this.f9823H = null;
        this.f9844x = null;
        System.gc();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void onError() {
        this.f9831k.m15967a("error", "camera error");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.example.asus.detectionandalign.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f9832l.onPause();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.OnPreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.f9841u.startingActionTest(bArr, this.f9832l);
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
        this.f9832l.onResume();
        this.f9832l.setAutoFocus(1L);
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
    }

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void recordSuccess(String str, Bitmap bitmap) {
    }
}
