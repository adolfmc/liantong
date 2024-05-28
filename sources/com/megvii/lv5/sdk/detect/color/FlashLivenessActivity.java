package com.megvii.lv5.sdk.detect.color;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.SurfaceTexture;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.megvii.lv5.C5399c3;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5446g0;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5515n0;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.C5537q;
import com.megvii.lv5.C5538q0;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.EnumC5548r2;
import com.megvii.lv5.InterfaceC5526o1;
import com.megvii.lv5.InterfaceC5625v;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.base.DetectBaseActivity;
import com.megvii.lv5.sdk.detect.MegliveModeImpl;
import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;
import com.megvii.lv5.sdk.view.CameraGLSurfaceView;
import com.megvii.lv5.sdk.view.LoadingCoverView;
import com.megvii.lv5.sdk.view.RadarView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FlashLivenessActivity extends DetectBaseActivity<C5446g0> implements View.OnClickListener, InterfaceC5526o1, InterfaceC5625v {

    /* renamed from: J */
    public static final /* synthetic */ int f13403J = 0;

    /* renamed from: A */
    public C5515n0 f13404A;

    /* renamed from: E */
    public View.OnClickListener f13408E;

    /* renamed from: F */
    public Context f13409F;

    /* renamed from: I */
    public String f13412I;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public String f13413a;

    /* renamed from: f */
    public SurfaceTexture f13418f;

    /* renamed from: i */
    public LoadingCoverView f13421i;

    /* renamed from: k */
    public CameraGLSurfaceView f13423k;

    /* renamed from: l */
    public ImageView f13424l;

    /* renamed from: m */
    public ImageView f13425m;

    /* renamed from: n */
    public RadarView f13426n;

    /* renamed from: o */
    public ImageView f13427o;

    /* renamed from: p */
    public ImageView f13428p;

    /* renamed from: q */
    public TextView f13429q;

    /* renamed from: r */
    public LinearLayout f13430r;

    /* renamed from: s */
    public ImageView f13431s;

    /* renamed from: t */
    public ImageView f13432t;

    /* renamed from: u */
    public ImageView f13433u;

    /* renamed from: v */
    public ImageView f13434v;

    /* renamed from: w */
    public ImageView f13435w;

    /* renamed from: x */
    public TextView f13436x;

    /* renamed from: y */
    public TextView f13437y;

    /* renamed from: z */
    public TextView f13438z;

    /* renamed from: b */
    public int f13414b = 1;

    /* renamed from: c */
    public String f13415c = "";

    /* renamed from: d */
    public boolean f13416d = false;

    /* renamed from: e */
    public volatile boolean f13417e = false;

    /* renamed from: g */
    public boolean f13419g = false;

    /* renamed from: h */
    public int f13420h = 0;

    /* renamed from: j */
    public CountDownTimer f13422j = null;

    /* renamed from: B */
    public volatile boolean f13405B = true;

    /* renamed from: C */
    public boolean f13406C = false;

    /* renamed from: D */
    public boolean f13407D = false;

    /* renamed from: G */
    public Handler f13410G = new HandlerC5586a(Looper.getMainLooper());

    /* renamed from: H */
    public boolean f13411H = false;

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class HandlerC5586a extends Handler {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class RunnableC5587a implements Runnable {
            public RunnableC5587a(HandlerC5586a handlerC5586a) {
            }

            @Override // java.lang.Runnable
            public void run() {
                C5537q.f13180g.m13206a(-1);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$a$b */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class RunnableC5588b implements Runnable {
            public RunnableC5588b(HandlerC5586a handlerC5586a) {
            }

            @Override // java.lang.Runnable
            public void run() {
                C5537q.f13180g.m13204a(0, 0, 0);
            }
        }

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$a$c */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class RunnableC5589c implements Runnable {
            public RunnableC5589c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                FlashLivenessActivity.this.f13437y.setVisibility(0);
            }
        }

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$a$d */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class CountDownTimerC5590d extends CountDownTimer {
            public CountDownTimerC5590d(long j, long j2) {
                super(j, j2);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                FlashLivenessActivity.this.mo12964b(false);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                String str = "onTick: millisUntilFinished = " + j;
            }
        }

        public HandlerC5586a(Looper looper) {
            super(looper);
        }

        /*  JADX ERROR: Failed to decode insn: 0x0403: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0403: UNKNOWN(0xF541)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x05F4: CONST_STRING r0, method: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity.a.handleMessage(android.os.Message):void
            java.lang.IllegalArgumentException: newPosition > limit: (93585544 > 13931064)
            	at java.nio.Buffer.createPositionException(Buffer.java:269)
            	at java.nio.Buffer.position(Buffer.java:244)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0602: UNKNOWN(0xFB42), method: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0602: UNKNOWN(0xFB42)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        @Override // android.os.Handler
        @android.annotation.SuppressLint({"HandlerLeak"})
        public void handleMessage(android.os.Message r21) {
            /*
                Method dump skipped, instructions count: 1560
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity.HandlerC5586a.handleMessage(android.os.Message):void");
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnClickListenerC5591b implements DialogInterface.OnClickListener {
        public DialogInterface$OnClickListenerC5591b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            FlashLivenessActivity.this.isRequestingScreenRecordPermission = false;
            dialogInterface.dismiss();
            FlashLivenessActivity flashLivenessActivity = FlashLivenessActivity.this;
            flashLivenessActivity.m13063a(EnumC5548r2.SCRN_AUTHORIZATION_FAIL, flashLivenessActivity.f13413a);
        }
    }

    /* compiled from: Proguard */
    @NBSInstrumented
    /* renamed from: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class View$OnClickListenerC5592c implements View.OnClickListener {

        /* renamed from: a */
        public WeakReference<FlashLivenessActivity> f13443a;

        public View$OnClickListenerC5592c(FlashLivenessActivity flashLivenessActivity) {
            this.f13443a = new WeakReference<>(flashLivenessActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            FlashLivenessActivity flashLivenessActivity = this.f13443a.get();
            if (flashLivenessActivity != null) {
                if (flashLivenessActivity.isFinishing()) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if (view.getId() == C5559R.C5562id.ll_detect_close) {
                    flashLivenessActivity.m13055r();
                } else {
                    JSONObject jSONObject = null;
                    if (view.getId() == C5559R.C5562id.tv_megvii_dialog_left) {
                        int i = FlashLivenessActivity.f13403J;
                        AlertDialog alertDialog = flashLivenessActivity.alertDialog;
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                        }
                        C5402d.f12429a = flashLivenessActivity.f13415c;
                        String str = flashLivenessActivity.f13413a;
                        int i2 = flashLivenessActivity.f13414b;
                        if (!C5402d.f12432d) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("type", "track");
                                jSONObject2.put("project", C5402d.f12429a);
                                jSONObject2.put("event_id", UUID.randomUUID().toString());
                                jSONObject2.put("time", System.currentTimeMillis());
                                jSONObject2.put("event", "click_cancel_quit");
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("liveness", i2);
                                jSONObject3.put("biz_token", str);
                                jSONObject3.put("try_times", 0);
                                int i3 = C5402d.f12431c + 1;
                                C5402d.f12431c = i3;
                                jSONObject3.put("index", i3);
                                jSONObject2.put("properties", jSONObject3);
                                C5402d.f12430b = "click_cancel_quit";
                                jSONObject = jSONObject2;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        C5399c3.m13606a(jSONObject);
                        flashLivenessActivity.f13405B = true;
                        flashLivenessActivity.f13420h++;
                        C5446g0 presenter = flashLivenessActivity.getPresenter();
                        presenter.getModel().m13151e();
                        presenter.f12633X.clear();
                        presenter.f12634Y.clear();
                        presenter.f12657m = -1;
                        presenter.f12658n = -1;
                        presenter.f12622M = 0L;
                        presenter.f12625P = true;
                        presenter.f12626Q = false;
                        presenter.f12624O = 0;
                        presenter.m13512f();
                        File file = new File(presenter.f12669y);
                        if (file.exists()) {
                            C5527o2.m13247a(file);
                        }
                        file.mkdir();
                        flashLivenessActivity.f13423k.m13037b();
                        flashLivenessActivity.getPresenter().m13506l();
                        C5538q0.m13181b((Activity) flashLivenessActivity);
                    } else if (view.getId() == C5559R.C5562id.tv_megvii_dialog_right) {
                        int i4 = FlashLivenessActivity.f13403J;
                        AlertDialog alertDialog2 = flashLivenessActivity.alertDialog;
                        if (alertDialog2 != null) {
                            alertDialog2.dismiss();
                        }
                        C5402d.f12429a = flashLivenessActivity.f13415c;
                        String str2 = flashLivenessActivity.f13413a;
                        int i5 = flashLivenessActivity.f13414b;
                        if (!C5402d.f12432d) {
                            try {
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("type", "track");
                                jSONObject4.put("project", C5402d.f12429a);
                                jSONObject4.put("event_id", UUID.randomUUID().toString());
                                jSONObject4.put("time", System.currentTimeMillis());
                                jSONObject4.put("event", "click_confirm_quit");
                                JSONObject jSONObject5 = new JSONObject();
                                jSONObject5.put("liveness", i5);
                                jSONObject5.put("biz_token", str2);
                                jSONObject5.put("try_times", 0);
                                int i6 = C5402d.f12431c + 1;
                                C5402d.f12431c = i6;
                                jSONObject5.put("index", i6);
                                jSONObject4.put("properties", jSONObject5);
                                C5402d.f12430b = "click_confirm_quit";
                                jSONObject = jSONObject4;
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                        C5399c3.m13606a(jSONObject);
                        flashLivenessActivity.m13063a(EnumC5548r2.USER_CANCEL, flashLivenessActivity.f13413a);
                    }
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m13062a(FlashLivenessActivity flashLivenessActivity, boolean z) {
        flashLivenessActivity.f13405B = z;
        return z;
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m13061b(FlashLivenessActivity flashLivenessActivity, boolean z) {
        flashLivenessActivity.f13417e = z;
        return z;
    }

    /* renamed from: c */
    public static /* synthetic */ boolean m13060c(FlashLivenessActivity flashLivenessActivity, boolean z) {
        flashLivenessActivity.isRequestingScreenRecordPermission = z;
        return z;
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12973a() {
        m13066a(109, new Bundle());
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12971a(int i, float f, String str) {
        String str2 = "setFlashStep: color = " + str;
        if (i == 14 || i == 17) {
            this.f13417e = true;
        }
        Bundle bundle = new Bundle();
        bundle.putString("color", str);
        bundle.putInt("curStep", i);
        bundle.putFloat("progress", f);
        m13066a(103, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12970a(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        bundle.putInt("qualityResult", i2);
        m13066a(102, bundle);
    }

    /* renamed from: a */
    public final void m13066a(int i, Bundle bundle) {
        Message message = new Message();
        message.what = i;
        message.setData(bundle);
        this.f13410G.sendMessage(message);
    }

    /* renamed from: a */
    public final void m13065a(int i, String str, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putString("bizToken", str);
        bundle.putSerializable("failedType", i == 1 ? EnumC5548r2.LIVENESS_TIME_OUT : i == 0 ? EnumC5548r2.LIVENESS_FINISH : i == 5 ? EnumC5548r2.SCRN_RECORD_FAIL : i == 7 ? EnumC5548r2.VIDEO_SAVE_FAIL : EnumC5548r2.LIVENESS_FAILURE);
        bundle.putByteArray("delta", bArr);
        m13066a(104, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12969a(SurfaceTexture surfaceTexture) {
        this.f13418f = surfaceTexture;
        if (surfaceTexture == null || !this.f13419g) {
            return;
        }
        this.f13423k.m13037b();
        getPresenter().m13506l();
    }

    /* renamed from: a */
    public final void m13064a(TextView textView, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        textView.setText(str);
        textView.setTextColor(i);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12968a(C5492l c5492l, C5553s1.InterfaceC5554a interfaceC5554a, C5553s1.InterfaceC5555b interfaceC5555b) {
        this.f13423k.m13038a(c5492l, interfaceC5554a, interfaceC5555b);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12967a(boolean z) {
        if (!z) {
            m13063a(EnumC5548r2.DEVICE_NOT_SUPPORT, this.f13413a);
            return;
        }
        this.f13419g = true;
        if (this.f13418f != null) {
            this.f13423k.m13037b();
            getPresenter().m13506l();
        }
        C5402d.f12429a = "liveness-sdk";
        String str = this.f13413a;
        int i = this.f13414b;
        JSONObject jSONObject = null;
        if (!C5402d.f12432d) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "track");
                jSONObject2.put("project", C5402d.f12429a);
                jSONObject2.put("event_id", UUID.randomUUID().toString());
                jSONObject2.put("time", System.currentTimeMillis());
                jSONObject2.put("event", "enter_page_success");
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("liveness", i);
                jSONObject3.put("biz_token", str);
                jSONObject3.put("try_times", 0);
                int i2 = C5402d.f12431c + 1;
                C5402d.f12431c = i2;
                jSONObject3.put("index", i2);
                jSONObject2.put("properties", jSONObject3);
                C5402d.f12430b = "enter_page_success";
                jSONObject = jSONObject2;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        C5399c3.m13606a(jSONObject);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: b */
    public String mo12966b() {
        return this.apiKey;
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: b */
    public void mo12965b(int i) {
        byte[] bytes;
        if (this.f13406C) {
            return;
        }
        C5446g0 presenter = getPresenter();
        presenter.getClass();
        try {
            byte[] m13511g = presenter.m13511g();
            String m13228d = C5527o2.m13228d();
            C5402d.f12429a = presenter.f12654j;
            String str = presenter.f12660p;
            int i2 = presenter.f12661q;
            JSONObject jSONObject = null;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "pass_detect");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i2);
                    jSONObject3.put("biz_token", str);
                    jSONObject3.put("try_times", 0);
                    int i3 = C5402d.f12431c + 1;
                    C5402d.f12431c = i3;
                    jSONObject3.put("index", i3);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "pass_detect";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
            String m13607a = C5399c3.m13607a();
            MegliveModeImpl model = presenter.getModel();
            boolean z = i == 0;
            byte[] bytes2 = "".getBytes();
            byte[] bytes3 = "".getBytes();
            byte[] bytes4 = "".getBytes();
            byte[] bytes5 = "".getBytes();
            C5492l c5492l = presenter.mCameraManager;
            bytes = model.m13159a(m13228d, z, false, m13607a, "", bytes2, m13511g, bytes3, bytes4, bytes5, c5492l.f12850e, c5492l.f12851f);
        } catch (Exception e2) {
            e2.printStackTrace();
            bytes = "".getBytes();
        }
        getPresenter().m13517b(this.mHost, 1, bytes, i);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: b */
    public void mo12964b(boolean z) {
        Bundle bundle = new Bundle();
        EnumC5548r2 enumC5548r2 = z ? EnumC5548r2.LIVENESS_FINISH : EnumC5548r2.DATA_UPLOAD_FAIL;
        bundle.putString("bizToken", this.f13413a);
        bundle.putSerializable("failedType", enumC5548r2);
        bundle.putByteArray("delta", "".getBytes());
        m13066a(104, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: c */
    public void mo12963c() {
        m13066a(111, new Bundle());
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public C5446g0 createPresenter() {
        return new C5446g0();
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: d */
    public int mo12962d() {
        return this.f13420h;
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: e */
    public String mo12961e() {
        return this.videoKey;
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: f */
    public int mo12960f() {
        return this.mVerticalCheckType;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public int getLayoutResId() {
        return C5559R.C5563layout.activity_flash_liveness;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MediaProjection getScreenRecordContext() {
        return C5462h2.C5464b.f12721a.f12717d;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MegLiveDetectConfig getUserDetectConfig() {
        return null;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initData() {
        this.f13406C = true;
        this.f13413a = C5527o2.m13256a(getContext());
        C5515n0 m13223h = C5527o2.m13223h(this);
        this.f13404A = m13223h;
        this.f13414b = m13223h.f12973b;
        this.f13431s.setImageDrawable(getResources().getDrawable(this.livenessVerticalDrawableId));
        this.f13436x.setTextSize(0, getResources().getDimension(this.livenessHomeRemindSize));
        this.f13437y.setTextSize(0, getResources().getDimension(this.livenessHomeLoadingTextSize));
        this.f13437y.setTextColor(-1);
        this.f13438z.setTextSize(0, getResources().getDimension(this.livenessHomeDeviceVerticalRemindSize));
        this.f13438z.setTextColor(getResources().getColor(this.livenessHomeDeviceVerticalRemindColor));
        if (this.mIsShowLogo) {
            this.f13428p.setImageDrawable(getResources().getDrawable(this.livenessLogoDrawableId));
            this.f13428p.setVisibility(0);
        } else {
            this.f13428p.setVisibility(8);
        }
        C5515n0 c5515n0 = this.f13404A;
        if (c5515n0.f12913E1 == 1) {
            String string = this.f13406C ? getResources().getString(C5667z2.m12879a(this).m12875d(getString(C5559R.string.key_liveness_home_prompt_text))) : c5515n0.f12916F1;
            if (!TextUtils.isEmpty(string)) {
                this.f13429q.setVisibility(0);
                this.f13429q.setText(string);
                if (this.livenessHomeCustomPromptBackgroundColor != 0) {
                    this.f13429q.setBackgroundColor(getResources().getColor(this.livenessHomeCustomPromptBackgroundColor));
                }
                if (this.livenessHomeCustomPromptTextColor != 0) {
                    this.f13429q.setTextColor(getResources().getColor(this.livenessHomeCustomPromptTextColor));
                }
            }
        }
        m13056q();
        this.f13416d = C5527o2.m13223h(this).f13020q1;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initView() {
        this.f13409F = getApplicationContext();
        CameraGLSurfaceView cameraGLSurfaceView = (CameraGLSurfaceView) findViewById(C5559R.C5562id.liveness_layout_cameraView);
        this.f13423k = cameraGLSurfaceView;
        cameraGLSurfaceView.setVisibility(0);
        this.f13426n = (RadarView) findViewById(C5559R.C5562id.radar_view);
        this.f13424l = (ImageView) findViewById(C5559R.C5562id.liveness_img_color_frame);
        this.f13425m = (ImageView) findViewById(C5559R.C5562id.liveness_img_color_line);
        LoadingCoverView loadingCoverView = (LoadingCoverView) findViewById(C5559R.C5562id.liveness_layout_lcv);
        this.f13421i = loadingCoverView;
        ImageView imageView = this.f13424l;
        ImageView imageView2 = this.f13425m;
        loadingCoverView.f13655W = imageView;
        loadingCoverView.f13657a0 = imageView2;
        loadingCoverView.setmProgressCallback(this);
        this.f13408E = new View$OnClickListenerC5592c(this);
        this.f13429q = (TextView) findViewById(C5559R.C5562id.tv_liveness_top_tips);
        this.f13436x = (TextView) findViewById(C5559R.C5562id.tv_flash_tips);
        this.f13437y = (TextView) findViewById(C5559R.C5562id.tv_finish_tips);
        this.f13438z = (TextView) findViewById(C5559R.C5562id.tv_vertical_tips);
        this.f13428p = (ImageView) findViewById(C5559R.C5562id.iv_megvii_powerby);
        this.f13427o = (ImageView) findViewById(C5559R.C5562id.iv_liveness_homepage_close);
        LinearLayout linearLayout = (LinearLayout) findViewById(C5559R.C5562id.ll_detect_close);
        this.f13430r = linearLayout;
        linearLayout.setOnClickListener(this.f13408E);
        this.f13431s = (ImageView) findViewById(C5559R.C5562id.iv_bg_vertical);
        ImageView imageView3 = (ImageView) findViewById(C5559R.C5562id.tv_circle_topleft);
        this.f13432t = imageView3;
        imageView3.setImageBitmap(this.bitmapTop);
        ImageView imageView4 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomright);
        this.f13433u = imageView4;
        imageView4.setImageBitmap(this.bitmapBottom);
        ImageView imageView5 = (ImageView) findViewById(C5559R.C5562id.tv_circle_topright);
        this.f13434v = imageView5;
        imageView5.setImageBitmap(this.bitmapTop);
        ImageView imageView6 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomleft);
        this.f13435w = imageView6;
        imageView6.setImageBitmap(this.bitmapBottom);
    }

    @Override // com.megvii.lv5.InterfaceC5526o1
    /* renamed from: l */
    public void mo13047l() {
        getPresenter().m13513e();
    }

    /* renamed from: n */
    public final void m13059n() {
        if (this.f13411H) {
            return;
        }
        getPresenter().m13504n();
        getPresenter().closeCamera();
        getPresenter().detach();
        this.f13423k.onPause();
        CountDownTimer countDownTimer = this.f13422j;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        Handler handler = this.f13410G;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        C5462h2.C5464b.f12721a.m13492a((Context) this);
        MediaProjection mediaProjection = this.mediaProjection;
        if (mediaProjection != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                mediaProjection.stop();
            }
            this.mediaProjection = null;
        }
        this.f13411H = true;
    }

    /* renamed from: o */
    public final void m13058o() {
        this.f13410G.removeCallbacksAndMessages(null);
        C5446g0 presenter = getPresenter();
        presenter.getClass();
        try {
            C5492l c5492l = presenter.mCameraManager;
            if (c5492l != null) {
                c5492l.f12846a.mo13262c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        getPresenter().m13504n();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013d  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onActivityResult(int r11, int r12, android.content.Intent r13) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.color.FlashLivenessActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (isFinishing()) {
            NBSActionInstrumentation.onClickEventExit();
        } else {
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 19);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m13059n();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m13055r();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isRequestingScreenRecordPermission) {
            return;
        }
        try {
            m13058o();
            if (!isFinishing() && !this.f13417e) {
                C5402d.f12432d = true;
                m13063a(EnumC5548r2.GO_TO_BACKGROUND, this.f13413a);
            }
            if (isFinishing()) {
                m13059n();
            }
            super.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        String str = "getScrnVideoOpen: " + this.f13404A.f12952R1;
        if (this.f13417e) {
            NBSAppInstrumentation.activityResumeEndIns();
            return;
        }
        int i = this.f13404A.f12952R1;
        if (i != 0) {
            C5462h2 c5462h2 = C5462h2.C5464b.f12721a;
            if (c5462h2.f12717d == null && (!this.f13407D || this.isRequestingScreenRecordPermission)) {
                if (i == 1 && !C5538q0.m13193a((Context) this)) {
                    m13063a(EnumC5548r2.NO_AUDIO_RECORD_PERMISSION, this.f13413a);
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else if (this.f13407D) {
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else {
                    C5402d.f12429a = "liveness-sdk";
                    String str2 = this.f13413a;
                    int i2 = this.f13414b;
                    JSONObject jSONObject = null;
                    if (!C5402d.f12432d) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("type", "track");
                            jSONObject2.put("project", C5402d.f12429a);
                            jSONObject2.put("event_id", UUID.randomUUID().toString());
                            jSONObject2.put("time", System.currentTimeMillis());
                            jSONObject2.put("event", "scrn_enter_permissions_detect");
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("liveness", i2);
                            jSONObject3.put("biz_token", str2);
                            jSONObject3.put("try_times", 0);
                            int i3 = C5402d.f12431c + 1;
                            C5402d.f12431c = i3;
                            jSONObject3.put("index", i3);
                            jSONObject2.put("properties", jSONObject3);
                            C5402d.f12430b = "scrn_enter_permissions_detect";
                            jSONObject = jSONObject2;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    C5399c3.m13606a(jSONObject);
                    this.f13407D = true;
                    this.isRequestingScreenRecordPermission = true;
                    c5462h2.m13493a((Activity) this);
                    NBSAppInstrumentation.activityResumeEndIns();
                }
            }
        }
        C5462h2 c5462h22 = C5462h2.C5464b.f12721a;
        if (c5462h22.f12717d != null) {
            c5462h22.m13493a((Activity) this);
        }
        getPresenter().openCamera();
        Bundle bundle = new Bundle();
        Message message = new Message();
        message.what = 100;
        message.setData(bundle);
        this.f13410G.sendMessageDelayed(message, 200L);
        NBSAppInstrumentation.activityResumeEndIns();
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

    /* renamed from: p */
    public final void m13057p() {
        this.f13430r.setVisibility(8);
    }

    /* renamed from: q */
    public final void m13056q() {
        if (!this.f13404A.f13044y1) {
            m13057p();
            return;
        }
        this.f13427o.setImageDrawable(getResources().getDrawable(this.livenessCloseDrawableId));
        this.f13430r.setVisibility(0);
    }

    /* renamed from: r */
    public void m13055r() {
        if (this.f13417e || !this.f13404A.f13044y1) {
            return;
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.alertDialog = this.mDialogUtil.m13172a(this.f13406C, this.f13408E);
            C5402d.f12429a = this.f13415c;
            String str = this.f13413a;
            int i = this.f13414b;
            JSONObject jSONObject = null;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "click_quit_icon");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i);
                    jSONObject3.put("biz_token", str);
                    jSONObject3.put("try_times", 0);
                    int i2 = C5402d.f12431c + 1;
                    C5402d.f12431c = i2;
                    jSONObject3.put("index", i2);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "click_quit_icon";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
            m13058o();
        }
    }

    @Override // com.megvii.lv5.InterfaceC5625v
    /* renamed from: a */
    public void mo12972a(int i) {
        if (!C5527o2.m13221j(this) && !C5527o2.m13226e(this)) {
            m13066a(108, new Bundle());
        }
        int i2 = i != 0 ? 2 : 1;
        if (i != 0) {
            JSONObject jSONObject = null;
            if (i == 1) {
                C5402d.f12429a = this.f13415c;
                String str = this.f13413a;
                int i3 = this.f13414b;
                boolean z = C5402d.f12432d;
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "fail_detect:time_out");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i3);
                    jSONObject3.put("biz_token", str);
                    jSONObject3.put("try_times", 0);
                    int i4 = C5402d.f12431c + 1;
                    C5402d.f12431c = i4;
                    jSONObject3.put("index", i4);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "fail_detect:time_out";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (i == 5) {
                C5402d.f12429a = this.f13415c;
                String str2 = this.f13413a;
                int i5 = this.f13414b;
                boolean z2 = C5402d.f12432d;
                try {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("type", "track");
                    jSONObject4.put("project", C5402d.f12429a);
                    jSONObject4.put("event_id", UUID.randomUUID().toString());
                    jSONObject4.put("time", System.currentTimeMillis());
                    jSONObject4.put("event", "fail_detect:scrn_video_generate_fail");
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("liveness", i5);
                    jSONObject5.put("biz_token", str2);
                    jSONObject5.put("try_times", 0);
                    int i6 = C5402d.f12431c + 1;
                    C5402d.f12431c = i6;
                    jSONObject5.put("index", i6);
                    jSONObject4.put("properties", jSONObject5);
                    C5402d.f12430b = "fail_detect:scrn_video_generate_fail";
                    jSONObject = jSONObject4;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (i == 7) {
                C5402d.f12429a = this.f13415c;
                String str3 = this.f13413a;
                int i7 = this.f13414b;
                boolean z3 = C5402d.f12432d;
                try {
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("type", "track");
                    jSONObject6.put("project", C5402d.f12429a);
                    jSONObject6.put("event_id", UUID.randomUUID().toString());
                    jSONObject6.put("time", System.currentTimeMillis());
                    jSONObject6.put("event", "fail_detect:scrn_video_save_fail");
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("liveness", i7);
                    jSONObject7.put("biz_token", str3);
                    jSONObject7.put("try_times", 0);
                    int i8 = C5402d.f12431c + 1;
                    C5402d.f12431c = i8;
                    jSONObject7.put("index", i8);
                    jSONObject6.put("properties", jSONObject7);
                    C5402d.f12430b = "fail_detect:scrn_video_save_fail";
                    jSONObject = jSONObject6;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            } else {
                C5402d.f12429a = this.f13415c;
                String str4 = this.f13413a;
                int i9 = this.f13414b;
                boolean z4 = C5402d.f12432d;
                try {
                    JSONObject jSONObject8 = new JSONObject();
                    jSONObject8.put("type", "track");
                    jSONObject8.put("project", C5402d.f12429a);
                    jSONObject8.put("event_id", UUID.randomUUID().toString());
                    jSONObject8.put("time", System.currentTimeMillis());
                    jSONObject8.put("event", "fail_detect:action_fail");
                    JSONObject jSONObject9 = new JSONObject();
                    jSONObject9.put("liveness", i9);
                    jSONObject9.put("biz_token", str4);
                    jSONObject9.put("try_times", 0);
                    int i10 = C5402d.f12431c + 1;
                    C5402d.f12431c = i10;
                    jSONObject9.put("index", i10);
                    jSONObject8.put("properties", jSONObject9);
                    C5402d.f12430b = "fail_detect:action_fail";
                    jSONObject = jSONObject8;
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
        }
        byte[] m13529a = getPresenter().m13529a(i);
        if (this.f13406C) {
            String str5 = this.f13413a;
            if (i != 0) {
                m13529a = "".getBytes();
            }
            m13065a(i, str5, m13529a);
            return;
        }
        getPresenter().m13522a(this.mHost, i2, m13529a, i);
        if (i != 0) {
            m13065a(i, this.f13413a, "".getBytes());
        } else {
            m13066a(110, new Bundle());
        }
    }

    /* renamed from: a */
    public final void m13063a(EnumC5548r2 enumC5548r2, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("bizToken", str);
        bundle.putSerializable("failedType", enumC5548r2);
        bundle.putByteArray("delta", "".getBytes());
        String str2 = "";
        if (enumC5548r2 == EnumC5548r2.DEVICE_NOT_SUPPORT) {
            str2 = "camera_fail";
        } else if (enumC5548r2 == EnumC5548r2.GO_TO_BACKGROUND) {
            str2 = "go_to_background";
        } else if (enumC5548r2 == EnumC5548r2.USER_CANCEL) {
            str2 = "user_cancel";
        } else if (enumC5548r2 == EnumC5548r2.SCRN_AUTHORIZATION_FAIL) {
            str2 = "scrn_authorized_fail";
        }
        C5402d.f12429a = this.f13415c;
        String str3 = "fail_detect:" + str2;
        String str4 = this.f13413a;
        int i = this.f13414b;
        JSONObject jSONObject = null;
        if (!C5402d.f12432d || str3.contains("fail_detect")) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "track");
                jSONObject2.put("project", C5402d.f12429a);
                jSONObject2.put("event_id", UUID.randomUUID().toString());
                jSONObject2.put("time", System.currentTimeMillis());
                jSONObject2.put("event", str3);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("liveness", i);
                jSONObject3.put("biz_token", str4);
                jSONObject3.put("try_times", 0);
                int i2 = C5402d.f12431c + 1;
                C5402d.f12431c = i2;
                jSONObject3.put("index", i2);
                jSONObject2.put("properties", jSONObject3);
                C5402d.f12430b = str3;
                jSONObject = jSONObject2;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        C5399c3.m13606a(jSONObject);
        if (!this.f13406C) {
            getPresenter().m13522a(this.mHost, 2, getPresenter().m13529a(4), 4);
        }
        m13066a(104, bundle);
    }
}
