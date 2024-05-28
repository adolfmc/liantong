package com.megvii.lv5.sdk.detect.action;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.megvii.lv5.C5380a3;
import com.megvii.lv5.C5383b;
import com.megvii.lv5.C5385b0;
import com.megvii.lv5.C5399c3;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5496l2;
import com.megvii.lv5.C5506m0;
import com.megvii.lv5.C5515n0;
import com.megvii.lv5.C5525o0;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.C5531p0;
import com.megvii.lv5.C5538q0;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.C5628v2;
import com.megvii.lv5.C5636w2;
import com.megvii.lv5.C5641x;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.CountDownTimerC5533p2;
import com.megvii.lv5.EnumC5548r2;
import com.megvii.lv5.EnumC5556s2;
import com.megvii.lv5.InterfaceC5551s;
import com.megvii.lv5.RunnableC5508m2;
import com.megvii.lv5.RunnableC5517n2;
import com.megvii.lv5.animationAnimation$AnimationListenerC5377a0;
import com.megvii.lv5.lib.jni.MegBlur;
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
import java.util.Arrays;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ActionLivenessActivity extends DetectBaseActivity<C5641x> implements View.OnClickListener, InterfaceC5551s {

    /* renamed from: U */
    public static final /* synthetic */ int f13285U = 0;

    /* renamed from: A */
    public SurfaceTexture f13286A;

    /* renamed from: C */
    public AnimationDrawable f13288C;

    /* renamed from: D */
    public AnimationDrawable f13289D;

    /* renamed from: E */
    public AnimationDrawable f13290E;

    /* renamed from: F */
    public AnimationDrawable f13291F;

    /* renamed from: G */
    public AnimationDrawable f13292G;

    /* renamed from: H */
    public C5496l2 f13293H;

    /* renamed from: I */
    public C5636w2 f13294I;

    /* renamed from: J */
    public ImageView f13295J;

    /* renamed from: K */
    public ImageView f13296K;

    /* renamed from: L */
    public ImageView f13297L;

    /* renamed from: M */
    public ImageView f13298M;

    /* renamed from: N */
    public ImageView f13299N;

    /* renamed from: O */
    public ImageView f13300O;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public CameraGLSurfaceView f13306a;

    /* renamed from: b */
    public RadarView f13307b;

    /* renamed from: c */
    public LinearLayout f13308c;

    /* renamed from: d */
    public C5515n0 f13309d;

    /* renamed from: e */
    public String f13310e;

    /* renamed from: f */
    public ImageView f13311f;

    /* renamed from: g */
    public ImageView f13312g;

    /* renamed from: h */
    public ImageView f13313h;

    /* renamed from: i */
    public TextView f13314i;

    /* renamed from: j */
    public TextView f13315j;

    /* renamed from: k */
    public TextView f13316k;

    /* renamed from: l */
    public TextView f13317l;

    /* renamed from: m */
    public TextView f13318m;

    /* renamed from: n */
    public TextView f13319n;

    /* renamed from: o */
    public ImageView f13320o;

    /* renamed from: p */
    public LoadingCoverView f13321p;

    /* renamed from: q */
    public CountDownTimerC5533p2 f13322q;

    /* renamed from: s */
    public View.OnClickListener f13324s;

    /* renamed from: t */
    public int[] f13325t;

    /* renamed from: r */
    public CountDownTimer f13323r = null;

    /* renamed from: u */
    public int f13326u = 0;

    /* renamed from: v */
    public int f13327v = 2;

    /* renamed from: w */
    public int f13328w = 0;

    /* renamed from: x */
    public String f13329x = "";

    /* renamed from: y */
    public boolean f13330y = true;

    /* renamed from: z */
    public volatile boolean f13331z = false;

    /* renamed from: B */
    public boolean f13287B = false;

    /* renamed from: P */
    public boolean f13301P = false;

    /* renamed from: Q */
    public boolean f13302Q = false;

    /* renamed from: R */
    public Handler f13303R = new HandlerC5568a();

    /* renamed from: S */
    public boolean f13304S = false;

    /* renamed from: T */
    public boolean f13305T = false;

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class HandlerC5568a extends Handler {

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class CountDownTimerC5569a extends CountDownTimer {
            public CountDownTimerC5569a(long j, long j2) {
                super(j, j2);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ActionLivenessActivity.this.mo13130b(false);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                String str = "onTick: millisUntilFinished = " + j;
            }
        }

        public HandlerC5568a() {
        }

        /*  JADX ERROR: Failed to decode insn: 0x0403: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.a.handleMessage(android.os.Message):void
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
        /*  JADX ERROR: Failed to decode insn: 0x0425: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0425: UNKNOWN(0xF541)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0447: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0447: UNKNOWN(0xF541)'
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
        /*  JADX ERROR: Failed to decode insn: 0x06C4: UNKNOWN(0x00EB), method: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x06C4: UNKNOWN(0x00EB)'
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
        /*  JADX ERROR: Failed to decode insn: 0x06D6: CONST_STRING r0, method: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.a.handleMessage(android.os.Message):void
            java.lang.IllegalArgumentException: newPosition > limit: (114294940 > 13931064)
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
        /*  JADX ERROR: Failed to decode insn: 0x06E4: UNKNOWN(0xBA3E), method: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x06E4: UNKNOWN(0xBA3E)'
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
        public void handleMessage(android.os.Message r13) {
            /*
                Method dump skipped, instructions count: 1874
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.HandlerC5568a.handleMessage(android.os.Message):void");
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5570b implements CountDownTimerC5533p2.InterfaceC5534a {

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$b$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class RunnableC5571a implements Runnable {
            public RunnableC5571a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ActionLivenessActivity actionLivenessActivity = ActionLivenessActivity.this;
                ActionLivenessActivity.m13137a(actionLivenessActivity, actionLivenessActivity.f13299N, 1);
            }
        }

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$b$b */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class RunnableC5572b implements Runnable {
            public RunnableC5572b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ActionLivenessActivity actionLivenessActivity = ActionLivenessActivity.this;
                ActionLivenessActivity.m13137a(actionLivenessActivity, actionLivenessActivity.f13300O, 0);
            }
        }

        public C5570b() {
        }

        @Override // com.megvii.lv5.CountDownTimerC5533p2.InterfaceC5534a
        /* renamed from: a */
        public void mo13067a() {
            ActionLivenessActivity actionLivenessActivity = ActionLivenessActivity.this;
            AnimationDrawable animationDrawable = actionLivenessActivity.f13292G;
            if (animationDrawable == actionLivenessActivity.f13291F) {
                actionLivenessActivity.f13299N.setVisibility(0);
                ActionLivenessActivity.this.f13299N.post(new RunnableC5571a());
            } else if (animationDrawable == actionLivenessActivity.f13290E) {
                actionLivenessActivity.f13300O.setVisibility(0);
                ActionLivenessActivity.this.f13300O.post(new RunnableC5572b());
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnClickListenerC5573c implements DialogInterface.OnClickListener {
        public DialogInterface$OnClickListenerC5573c() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            ActionLivenessActivity.this.isRequestingScreenRecordPermission = false;
            dialogInterface.dismiss();
            ActionLivenessActivity actionLivenessActivity = ActionLivenessActivity.this;
            actionLivenessActivity.m13139a(EnumC5548r2.SCRN_AUTHORIZATION_FAIL, actionLivenessActivity.f13310e);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5574d implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ int f13338a;

        /* renamed from: b */
        public final /* synthetic */ int f13339b;

        /* renamed from: c */
        public final /* synthetic */ int f13340c;

        public RunnableC5574d(int i, int i2, int i3) {
            this.f13338a = i;
            this.f13339b = i2;
            this.f13340c = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionLivenessActivity actionLivenessActivity = ActionLivenessActivity.this;
            int i = this.f13338a;
            int i2 = this.f13339b;
            int i3 = ActionLivenessActivity.f13285U;
            actionLivenessActivity.m13125d(i, i2);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5575e implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ int f13342a;

        /* renamed from: b */
        public final /* synthetic */ int f13343b;

        public RunnableC5575e(int i, int i2) {
            this.f13342a = i;
            this.f13343b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionLivenessActivity.m13138a(ActionLivenessActivity.this, this.f13342a, this.f13343b);
        }
    }

    /* compiled from: Proguard */
    @NBSInstrumented
    /* renamed from: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class View$OnClickListenerC5576f implements View.OnClickListener {

        /* renamed from: a */
        public WeakReference<ActionLivenessActivity> f13345a;

        public View$OnClickListenerC5576f(ActionLivenessActivity actionLivenessActivity) {
            this.f13345a = new WeakReference<>(actionLivenessActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            ActionLivenessActivity actionLivenessActivity = this.f13345a.get();
            if (actionLivenessActivity != null) {
                if (actionLivenessActivity.isFinishing()) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if (view.getId() == C5559R.C5562id.ll_action_close) {
                    actionLivenessActivity.m13118q();
                } else {
                    JSONObject jSONObject = null;
                    if (view.getId() == C5559R.C5562id.tv_megvii_dialog_left) {
                        int i = ActionLivenessActivity.f13285U;
                        AlertDialog alertDialog = actionLivenessActivity.alertDialog;
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                        }
                        C5402d.f12429a = actionLivenessActivity.f13329x;
                        String str = actionLivenessActivity.f13310e;
                        int i2 = actionLivenessActivity.f13327v;
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
                        actionLivenessActivity.f13330y = true;
                        actionLivenessActivity.f13305T = false;
                        actionLivenessActivity.f13326u++;
                        C5496l2 c5496l2 = actionLivenessActivity.f13293H;
                        ((Activity) c5496l2.f12862b).runOnUiThread(new RunnableC5517n2(c5496l2));
                        C5628v2.m12958b("actionQueue", Arrays.toString(actionLivenessActivity.f13325t));
                        C5538q0.m13183a(actionLivenessActivity.f13325t, actionLivenessActivity.f13309d.f13032u1);
                        C5628v2.m12958b("actionQueue", Arrays.toString(actionLivenessActivity.f13325t));
                        C5641x presenter = actionLivenessActivity.getPresenter();
                        int[] iArr = actionLivenessActivity.f13325t;
                        presenter.getClass();
                        try {
                            C5628v2.m12958b("ActionDetect", "resetDetect...");
                            presenter.f13832a = -1;
                            presenter.f13838d = -1;
                            presenter.f13834b = -1;
                            presenter.f13840e = -1;
                            presenter.f13829X = true;
                            presenter.f13853l.f13096e = iArr;
                            presenter.getModel().m13155a(iArr);
                            presenter.getModel().m13149g();
                            presenter.getModel().m13150f();
                            presenter.f13809D.clear();
                            presenter.f13810E.clear();
                            File file = new File(presenter.f13862u);
                            if (file.exists()) {
                                C5527o2.m13247a(file);
                            }
                            file.mkdir();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        actionLivenessActivity.f13306a.m13037b();
                        actionLivenessActivity.getPresenter().m12913k();
                        C5538q0.m13181b((Activity) actionLivenessActivity);
                    } else if (view.getId() == C5559R.C5562id.tv_megvii_dialog_right) {
                        int i4 = ActionLivenessActivity.f13285U;
                        AlertDialog alertDialog2 = actionLivenessActivity.alertDialog;
                        if (alertDialog2 != null) {
                            alertDialog2.dismiss();
                        }
                        C5402d.f12429a = actionLivenessActivity.f13329x;
                        String str2 = actionLivenessActivity.f13310e;
                        int i5 = actionLivenessActivity.f13327v;
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
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        }
                        C5399c3.m13606a(jSONObject);
                        actionLivenessActivity.m13139a(EnumC5548r2.USER_CANCEL, actionLivenessActivity.f13310e);
                    }
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a */
    public static void m13138a(ActionLivenessActivity actionLivenessActivity, int i, int i2) {
        String str;
        TextView textView;
        Resources resources;
        int i3;
        actionLivenessActivity.f13319n.setVisibility(8);
        actionLivenessActivity.f13313h.setVisibility(8);
        actionLivenessActivity.f13317l.setVisibility(0);
        actionLivenessActivity.f13315j.setVisibility(8);
        actionLivenessActivity.f13316k.setVisibility(8);
        if (i == 4) {
            actionLivenessActivity.f13307b.setVisibility(8);
            actionLivenessActivity.f13320o.setVisibility(8);
            actionLivenessActivity.f13318m.setVisibility(8);
            str = actionLivenessActivity.getResources().getString(C5667z2.m12879a(actionLivenessActivity).m12875d(actionLivenessActivity.getString(C5559R.string.key_liveness_home_promptWait_text)));
        } else if (i == 2) {
            if (i2 == 15) {
                actionLivenessActivity.f13318m.setText(actionLivenessActivity.getResources().getString(C5667z2.m12879a(actionLivenessActivity).m12875d(actionLivenessActivity.getString(C5559R.string.key_livenessHomePromptVerticalText))));
                actionLivenessActivity.f13318m.setVisibility(0);
                actionLivenessActivity.f13320o.setVisibility(0);
                C5383b.C5384a.f12381a.m13609a(3, 1.0f);
            } else {
                actionLivenessActivity.f13318m.setVisibility(8);
                actionLivenessActivity.f13320o.setVisibility(8);
                C5383b.C5384a.f12381a.m13609a(2, 1.0f);
            }
            str = actionLivenessActivity.getMirroFailedMsg(i2);
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i == 4) {
            actionLivenessActivity.f13317l.setVisibility(8);
            actionLivenessActivity.f13315j.setVisibility(8);
            actionLivenessActivity.f13316k.setVisibility(0);
            actionLivenessActivity.f13316k.setText(str);
        } else if (i2 == 14) {
            TextView textView2 = actionLivenessActivity.f13317l;
            int color = actionLivenessActivity.getResources().getColor(actionLivenessActivity.livenessHomeNormalRemindTextColor);
            textView2.setText(str);
            textView2.setTextColor(color);
            int[] m13195a = C5538q0.m13195a(-1);
            C5383b c5383b = C5383b.C5384a.f12381a;
            int i4 = m13195a[0];
            int i5 = m13195a[1];
            int i6 = m13195a[2];
            long j = c5383b.f12380a.f12359a;
            if (j != 0) {
                MegBlur.nativeSetColorContour(j, i4, i5, i6, 0.1f);
            }
        } else {
            if (actionLivenessActivity.f13330y) {
                textView = actionLivenessActivity.f13317l;
                resources = actionLivenessActivity.getResources();
                i3 = actionLivenessActivity.livenessHomeNormalRemindTextColor;
            } else {
                textView = actionLivenessActivity.f13317l;
                resources = actionLivenessActivity.getResources();
                i3 = actionLivenessActivity.livenessHomeFailedRemindTextColor;
            }
            int color2 = resources.getColor(i3);
            textView.setText(str);
            textView.setTextColor(color2);
            int[] m13195a2 = C5538q0.m13195a(-1);
            C5383b c5383b2 = C5383b.C5384a.f12381a;
            int i7 = m13195a2[0];
            int i8 = m13195a2[1];
            int i9 = m13195a2[2];
            long j2 = c5383b2.f12380a.f12359a;
            if (j2 != 0) {
                MegBlur.nativeSetColorContour(j2, i7, i8, i9, 1.0f);
            }
        }
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m13136a(ActionLivenessActivity actionLivenessActivity, boolean z) {
        actionLivenessActivity.f13331z = z;
        return z;
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m13131b(ActionLivenessActivity actionLivenessActivity, boolean z) {
        actionLivenessActivity.f13330y = z;
        return z;
    }

    /* renamed from: c */
    public static /* synthetic */ boolean m13128c(ActionLivenessActivity actionLivenessActivity, boolean z) {
        actionLivenessActivity.isRequestingScreenRecordPermission = z;
        return z;
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13148a() {
        m13143a(116, new Bundle());
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13146a(int i, float f, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("color", null);
        bundle.putInt("curStep", i);
        bundle.putFloat("progress", f);
        m13143a(115, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13145a(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("tipsType", i);
        bundle.putInt("qualityResult", i2);
        if (i == 4) {
            runOnUiThread(new RunnableC5575e(i, i2));
        } else {
            m13143a(105, bundle);
        }
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13144a(int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        bundle.putInt("actionIndex", i2);
        bundle.putInt("detectResult", i3);
        if (i == 14) {
            runOnUiThread(new RunnableC5574d(i, i2, i3));
        } else {
            m13143a(104, bundle);
        }
    }

    /* renamed from: a */
    public final void m13143a(int i, Bundle bundle) {
        Message message = new Message();
        message.what = i;
        message.setData(bundle);
        this.f13303R.sendMessage(message);
    }

    /* renamed from: a */
    public final void m13142a(int i, String str, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putString("bizToken", str);
        bundle.putSerializable("failedType", i == 1 ? EnumC5548r2.LIVENESS_TIME_OUT : i == 0 ? EnumC5548r2.LIVENESS_FINISH : i == 5 ? EnumC5548r2.SCRN_RECORD_FAIL : i == 7 ? EnumC5548r2.VIDEO_SAVE_FAIL : EnumC5548r2.LIVENESS_FAILURE);
        bundle.putByteArray("delta", bArr);
        m13143a(107, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13141a(SurfaceTexture surfaceTexture) {
        this.f13286A = surfaceTexture;
        if (surfaceTexture == null || !this.f13287B) {
            return;
        }
        this.f13306a.m13037b();
        getPresenter().m12913k();
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13140a(C5492l c5492l, C5553s1.InterfaceC5554a interfaceC5554a, C5553s1.InterfaceC5555b interfaceC5555b) {
        this.f13306a.m13038a(c5492l, interfaceC5554a, interfaceC5555b);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: b */
    public String mo13134b() {
        return this.apiKey;
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: b */
    public void mo13133b(int i) {
        byte[] bytes;
        m13143a(110, new Bundle());
        if (this.f13301P) {
            return;
        }
        C5641x presenter = getPresenter();
        presenter.getClass();
        try {
            byte[] m12918f = presenter.m12918f();
            String m13228d = C5527o2.m13228d();
            C5402d.f12429a = presenter.f13854m;
            C5525o0 c5525o0 = presenter.f13853l;
            String str = c5525o0.f13093b;
            int i2 = c5525o0.f13092a;
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
            bytes = model.m13159a(m13228d, z, false, m13607a, "", bytes2, m12918f, bytes3, bytes4, bytes5, c5492l.f12850e, c5492l.f12851f);
        } catch (Exception e2) {
            e2.printStackTrace();
            bytes = "".getBytes();
        }
        getPresenter().m12924b(this.mHost, 1, bytes, i);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: b */
    public void mo13132b(int i, int i2) {
        if (this.f13305T) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("actionIndex", i);
        bundle.putInt("currentAction", i2);
        m13143a(102, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: b */
    public void mo13130b(boolean z) {
        Bundle bundle = new Bundle();
        EnumC5548r2 enumC5548r2 = z ? EnumC5548r2.LIVENESS_FINISH : EnumC5548r2.DATA_UPLOAD_FAIL;
        bundle.putString("bizToken", this.f13310e);
        bundle.putSerializable("failedType", enumC5548r2);
        bundle.putByteArray("delta", "".getBytes());
        m13143a(107, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: c */
    public void mo13129c() {
        m13143a(118, new Bundle());
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public C5641x createPresenter() {
        return new C5641x();
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: d */
    public int mo13127d() {
        return this.f13326u;
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: d */
    public void mo13126d(int i) {
        if (i == 14 || i == 17) {
            this.f13331z = true;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("actionStep", i);
        m13143a(113, bundle);
    }

    /* renamed from: d */
    public final void m13125d(int i, int i2) {
        m13117r();
        m13116s();
        if (i != 4) {
            if (i == 14) {
                this.f13321p.m13017d();
                this.f13321p.setProgress(100.0f);
                this.f13321p.setProgressLineColor(getResources().getColor(this.livenessHomeProcessBarColor));
                return;
            } else if (i == 3) {
                this.f13321p.setProgress(0.0f);
                return;
            } else {
                return;
            }
        }
        this.f13321p.m13018c();
        float f = 1.0f / this.f13309d.f12976c;
        float f2 = i2 * f;
        float f3 = f * (i2 - 1);
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        this.f13321p.setProgressLineColor(getResources().getColor(this.livenessHomeProcessBarColor));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(300L);
        ofFloat.addUpdateListener(new C5385b0(this, f3, f2));
        ofFloat.start();
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public int getLayoutResId() {
        return C5559R.C5563layout.activity_action_liveness;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MediaProjection getScreenRecordContext() {
        return C5462h2.C5464b.f12721a.f12717d;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MegLiveDetectConfig getUserDetectConfig() {
        return null;
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: h */
    public void mo13124h() {
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initData() {
        this.f13301P = true;
        this.f13294I = new C5636w2(this);
        this.f13299N.setImageDrawable(getResources().getDrawable(C5667z2.m12879a(this).m12877b(getString(C5559R.string.key_liveness_action_shake_tips_icon))));
        this.f13300O.setImageDrawable(getResources().getDrawable(C5667z2.m12879a(this).m12877b(getString(C5559R.string.key_liveness_action_nod_tips_icon))));
        C5515n0 m13223h = C5527o2.m13223h(getContext());
        this.f13309d = m13223h;
        this.f13327v = m13223h.f12973b;
        int intValue = ((Integer) C5527o2.m13252a(getContext(), "megvii_liveness_platform", (Object) 1)).intValue();
        this.f13328w = intValue;
        this.f13329x = intValue == 1 ? "liveness-sdk" : "FaceIDZFAC";
        this.f13325t = this.f13309d.f12985f;
        this.f13310e = C5527o2.m13256a(getContext());
        ImageView imageView = (ImageView) findViewById(C5559R.C5562id.image_animation);
        this.f13313h = imageView;
        this.f13293H = new C5496l2(this, imageView);
        this.f13311f.setImageDrawable(getResources().getDrawable(this.livenessCloseDrawableId));
        this.f13320o.setImageDrawable(getResources().getDrawable(this.livenessVerticalDrawableId));
        this.f13317l.setTextSize(0, getResources().getDimension(this.livenessHomeRemindSize));
        this.f13315j.setTextSize(0, getResources().getDimension(this.livenessHomeActionRemindSize));
        this.f13315j.setTextColor(-1);
        this.f13316k.setTextSize(0, getResources().getDimension(this.livenessHomeLoadingTextSize));
        this.f13316k.setTextColor(-1);
        this.f13318m.setTextSize(0, getResources().getDimension(this.livenessHomeDeviceVerticalRemindSize));
        this.f13318m.setTextColor(getResources().getColor(this.livenessHomeDeviceVerticalRemindColor));
        this.f13319n.setTextSize(0, getResources().getDimension(this.livenessHomeActionTimeTextSize));
        this.f13319n.setTextColor(getResources().getColor(this.livenessHomeActionTimeTextColor));
        if (this.mIsShowLogo) {
            this.f13312g.setImageDrawable(getResources().getDrawable(this.livenessLogoDrawableId));
            this.f13312g.setVisibility(0);
        } else {
            this.f13312g.setVisibility(8);
        }
        m13119p();
        C5515n0 c5515n0 = this.f13309d;
        if (c5515n0.f12913E1 == 1) {
            String string = this.f13301P ? getResources().getString(C5667z2.m12879a(this).m12875d(getString(C5559R.string.key_liveness_home_prompt_text))) : c5515n0.f12916F1;
            if (!TextUtils.isEmpty(string)) {
                this.f13314i.setVisibility(0);
                this.f13314i.setText(string);
                if (this.livenessHomeCustomPromptBackgroundColor != 0) {
                    this.f13314i.setBackgroundColor(getResources().getColor(this.livenessHomeCustomPromptBackgroundColor));
                }
                if (this.livenessHomeCustomPromptTextColor != 0) {
                    this.f13314i.setTextColor(getResources().getColor(this.livenessHomeCustomPromptTextColor));
                }
            }
        }
        if (this.f13309d.f12949Q1) {
            soundOff();
        } else if (this.mIsAutoAdjustVolume) {
            changeVolume(this.mSuggestVolume);
        }
        this.f13322q = new CountDownTimerC5533p2(this.f13319n, this.f13309d.f12982e * 1000, 1000L, new C5570b());
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initView() {
        CameraGLSurfaceView cameraGLSurfaceView = (CameraGLSurfaceView) findViewById(C5559R.C5562id.livess_layout_cameraview);
        this.f13306a = cameraGLSurfaceView;
        cameraGLSurfaceView.setVisibility(0);
        this.f13307b = (RadarView) findViewById(C5559R.C5562id.radar_view);
        this.f13321p = (LoadingCoverView) findViewById(C5559R.C5562id.liveness_layout_lcv);
        this.f13324s = new View$OnClickListenerC5576f(this);
        this.f13312g = (ImageView) findViewById(C5559R.C5562id.iv_megvii_powerby);
        this.f13311f = (ImageView) findViewById(C5559R.C5562id.iv_liveness_homepage_close);
        LinearLayout linearLayout = (LinearLayout) findViewById(C5559R.C5562id.ll_action_close);
        this.f13308c = linearLayout;
        linearLayout.setOnClickListener(this.f13324s);
        this.f13314i = (TextView) findViewById(C5559R.C5562id.tv_liveness_top_tips);
        this.f13315j = (TextView) findViewById(C5559R.C5562id.tv_action_tips);
        this.f13316k = (TextView) findViewById(C5559R.C5562id.tv_finish_tips);
        this.f13317l = (TextView) findViewById(C5559R.C5562id.tv_status_tips);
        this.f13318m = (TextView) findViewById(C5559R.C5562id.tv_vertical_tips);
        this.f13319n = (TextView) findViewById(C5559R.C5562id.tv_the_countdown);
        this.f13320o = (ImageView) findViewById(C5559R.C5562id.iv_bg_vertical);
        ImageView imageView = (ImageView) findViewById(C5559R.C5562id.tv_circle_topleft);
        this.f13295J = imageView;
        imageView.setImageBitmap(this.bitmapTop);
        ImageView imageView2 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomright);
        this.f13296K = imageView2;
        imageView2.setImageBitmap(this.bitmapBottom);
        ImageView imageView3 = (ImageView) findViewById(C5559R.C5562id.tv_circle_topright);
        this.f13297L = imageView3;
        imageView3.setImageBitmap(this.bitmapTop);
        ImageView imageView4 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomleft);
        this.f13298M = imageView4;
        imageView4.setImageBitmap(this.bitmapBottom);
        this.f13299N = (ImageView) findViewById(C5559R.C5562id.image_animation_shake);
        this.f13300O = (ImageView) findViewById(C5559R.C5562id.image_animation_nod);
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: j */
    public String mo13123j() {
        return this.videoKey;
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: k */
    public C5525o0 mo13122k() {
        C5525o0 c5525o0 = new C5525o0();
        C5515n0 c5515n0 = this.f13309d;
        c5525o0.f13102k = c5515n0.f13015p;
        c5525o0.f13092a = c5515n0.f12973b;
        c5525o0.f13093b = this.f13310e;
        c5525o0.f13094c = c5515n0.f12982e;
        c5525o0.f13097f = C5527o2.m13254a(this, EnumC5556s2.RECT);
        c5525o0.f13098g = C5527o2.m13254a(this, EnumC5556s2.LMK);
        c5525o0.f13099h = C5527o2.m13254a(this, EnumC5556s2.ACTION);
        C5515n0 c5515n02 = this.f13309d;
        c5525o0.f13095d = c5515n02.f12976c;
        c5525o0.f13096e = c5515n02.f12985f;
        c5525o0.f13100i = this.mVerticalCheckType;
        c5525o0.f13101j = this.f13328w;
        c5525o0.f13103l = c5515n02.f13017p1;
        C5531p0 c5531p0 = new C5531p0();
        c5531p0.f13164n = c5515n02.f12926J;
        c5531p0.f13165o = c5515n02.f12929K;
        c5531p0.f13152b = c5515n02.f13036w;
        c5531p0.f13163m = c5515n02.f12923I;
        c5531p0.f13156f = c5515n02.f12902B;
        c5531p0.f13157g = c5515n02.f12905C;
        c5531p0.f13158h = c5515n02.f12908D;
        c5531p0.f13159i = c5515n02.f12911E;
        c5531p0.f13151a = c5515n02.f13033v;
        c5531p0.f13161k = c5515n02.f12917G;
        c5531p0.f13160j = c5515n02.f12914F;
        c5531p0.f13162l = c5515n02.f12920H;
        c5531p0.f13153c = c5515n02.f13039x;
        c5531p0.f13155e = c5515n02.f12899A;
        c5531p0.f13154d = c5515n02.f13042y;
        c5531p0.f13166p = c5515n02.f12970a == 0 ? 0 : c5515n02.f13022r0;
        c5525o0.f13104m = c5531p0;
        C5531p0 c5531p02 = new C5531p0();
        C5515n0 c5515n03 = this.f13309d;
        c5531p02.f13164n = c5515n03.f12968Z;
        c5531p02.f13165o = c5515n03.f12971a0;
        c5531p02.f13152b = c5515n03.f12935M;
        c5531p02.f13163m = c5515n03.f12966Y;
        c5531p02.f13156f = c5515n03.f12950R;
        c5531p02.f13157g = c5515n03.f12953S;
        c5531p02.f13158h = c5515n03.f12956T;
        c5531p02.f13159i = c5515n03.f12958U;
        c5531p02.f13151a = c5515n03.f12932L;
        c5531p02.f13161k = c5515n03.f12962W;
        c5531p02.f13160j = c5515n03.f12960V;
        c5531p02.f13162l = c5515n03.f12964X;
        c5531p02.f13153c = c5515n03.f12938N;
        c5531p02.f13155e = c5515n03.f12947Q;
        c5531p02.f13154d = c5515n03.f12941O;
        c5531p02.f13166p = c5515n03.f12970a != 0 ? c5515n03.f13022r0 : 0;
        c5525o0.f13105n = c5531p02;
        C5506m0 c5506m0 = new C5506m0();
        c5506m0.f12877a = c5515n03.f12993h1;
        c5506m0.f12880d = c5515n03.f13002k1;
        c5506m0.f12882f = c5515n03.f13008m1;
        c5506m0.f12884h = c5515n03.f13014o1;
        c5506m0.f12878b = c5515n03.f12996i1;
        c5506m0.f12881e = c5515n03.f13005l1;
        c5506m0.f12883g = c5515n03.f13011n1;
        c5506m0.f12879c = c5515n03.f12999j1;
        c5525o0.f13106o = c5506m0;
        return c5525o0;
    }

    /* renamed from: n */
    public final void m13121n() {
        if (this.f13304S) {
            return;
        }
        C5462h2.C5464b.f12721a.m13492a((Context) this);
        getPresenter().m12911m();
        getPresenter().closeCamera();
        getPresenter().detach();
        this.f13306a.onPause();
        Handler handler = this.f13303R;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        CountDownTimer countDownTimer = this.f13323r;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        C5636w2 c5636w2 = this.f13294I;
        if (c5636w2 != null) {
            c5636w2.m12943a();
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        MediaProjection mediaProjection = this.mediaProjection;
        if (mediaProjection != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                mediaProjection.stop();
            }
            this.mediaProjection = null;
        }
        this.f13304S = true;
    }

    /* renamed from: o */
    public final void m13120o() {
        this.f13305T = true;
        this.f13303R.removeCallbacksAndMessages(null);
        CountDownTimerC5533p2 countDownTimerC5533p2 = this.f13322q;
        if (countDownTimerC5533p2 != null) {
            countDownTimerC5533p2.cancel();
        }
        m13117r();
        m13116s();
        C5641x presenter = getPresenter();
        presenter.getClass();
        try {
            C5492l c5492l = presenter.mCameraManager;
            if (c5492l != null) {
                c5492l.f12846a.mo13262c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getPresenter().m12911m();
        C5496l2 c5496l2 = this.f13293H;
        ((Activity) c5496l2.f12862b).runOnUiThread(new RunnableC5508m2(c5496l2));
        this.f13294I.m12941b();
        this.f13294I.m12940b(-1, null);
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
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.action.ActionLivenessActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 17);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m13121n();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m13118q();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onPause() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.isRequestingScreenRecordPermission) {
            super.onPause();
            return;
        }
        C5402d.f12432d = true;
        m13120o();
        if (!isFinishing() && !this.f13331z) {
            m13139a(EnumC5548r2.GO_TO_BACKGROUND, this.f13310e);
        }
        if (isFinishing()) {
            m13121n();
        }
        super.onPause();
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
        if (this.f13331z) {
            NBSAppInstrumentation.activityResumeEndIns();
            return;
        }
        int i = this.f13309d.f12952R1;
        if (i != 0) {
            C5462h2 c5462h2 = C5462h2.C5464b.f12721a;
            if (c5462h2.f12717d == null && (!this.f13302Q || this.isRequestingScreenRecordPermission)) {
                if (i == 1 && !C5538q0.m13193a((Context) this)) {
                    m13139a(EnumC5548r2.NO_AUDIO_RECORD_PERMISSION, this.f13310e);
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else if (this.f13302Q) {
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else {
                    C5402d.f12429a = "liveness-sdk";
                    String str = this.f13310e;
                    int i2 = this.f13327v;
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
                            jSONObject3.put("biz_token", str);
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
                    this.f13302Q = true;
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
        bundle.putInt("cameraWidth", getPresenter().getCameraWidth());
        bundle.putInt("cameraHeight", getPresenter().getCameraHeight());
        Message message = new Message();
        message.what = 100;
        message.setData(bundle);
        this.f13303R.sendMessageDelayed(message, 200L);
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
    public final void m13119p() {
        LinearLayout linearLayout;
        int i;
        if (this.f13309d.f13044y1) {
            linearLayout = this.f13308c;
            i = 0;
        } else {
            linearLayout = this.f13308c;
            i = 8;
        }
        linearLayout.setVisibility(i);
    }

    /* renamed from: q */
    public void m13118q() {
        if (this.f13331z || !this.f13309d.f13044y1) {
            return;
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.alertDialog = this.mDialogUtil.m13172a(this.f13301P, this.f13324s);
            C5402d.f12429a = this.f13329x;
            String str = this.f13310e;
            int i = this.f13327v;
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
            m13120o();
        }
    }

    /* renamed from: r */
    public final void m13117r() {
        this.f13300O.setVisibility(8);
        this.f13300O.clearAnimation();
    }

    /* renamed from: s */
    public final void m13116s() {
        this.f13299N.setVisibility(8);
        this.f13299N.clearAnimation();
    }

    /* renamed from: a */
    public static void m13137a(ActionLivenessActivity actionLivenessActivity, ImageView imageView, int i) {
        actionLivenessActivity.getClass();
        if (imageView == null || imageView.getAnimation() == null) {
            float height = imageView.getHeight() / 2.0f;
            String str = "shakeAnimation: centerY = " + height;
            String str2 = "shakeAnimation handleMessage: nod width =" + imageView.getWidth();
            String str3 = "shakeAnimation handleMessage: nod height =" + imageView.getHeight();
            C5380a3 c5380a3 = new C5380a3(actionLivenessActivity, 0.0f, 180.0f, imageView.getWidth() / 2.0f, height, 1.0f, true, i);
            c5380a3.setDuration(1000L);
            c5380a3.setRepeatCount(-1);
            c5380a3.setRepeatMode(2);
            c5380a3.setInterpolator(new LinearInterpolator());
            c5380a3.setAnimationListener(new animationAnimation$AnimationListenerC5377a0(actionLivenessActivity));
            imageView.startAnimation(c5380a3);
        }
    }

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13135a(boolean z) {
        String str = "cameraOpenResult: result = " + z;
        if (!z) {
            m13139a(EnumC5548r2.DEVICE_NOT_SUPPORT, this.f13310e);
            return;
        }
        this.f13287B = true;
        if (this.f13286A != null) {
            this.f13306a.m13037b();
            getPresenter().m12913k();
        }
        C5402d.f12429a = "liveness-sdk";
        String str2 = this.f13310e;
        int i = this.f13327v;
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
                jSONObject3.put("biz_token", str2);
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

    @Override // com.megvii.lv5.InterfaceC5551s
    /* renamed from: a */
    public void mo13147a(int i) {
        if (!C5527o2.m13221j(this) && !C5527o2.m13226e(this)) {
            m13143a(110, new Bundle());
        }
        int i2 = i != 0 ? 2 : 1;
        if (i != 0) {
            JSONObject jSONObject = null;
            if (i == 1) {
                C5402d.f12429a = this.f13329x;
                String str = this.f13310e;
                int i3 = this.f13327v;
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
                C5402d.f12429a = this.f13329x;
                String str2 = this.f13310e;
                int i5 = this.f13327v;
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
                C5402d.f12429a = this.f13329x;
                String str3 = this.f13310e;
                int i7 = this.f13327v;
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
                C5402d.f12429a = this.f13329x;
                String str4 = this.f13310e;
                int i9 = this.f13327v;
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
        byte[] m12938a = getPresenter().m12938a(i);
        if (!this.f13301P) {
            getPresenter().m12931a(this.mHost, i2, m12938a, i);
            if (i != 0) {
                m13142a(i, this.f13310e, "".getBytes());
                return;
            } else {
                m13143a(117, new Bundle());
                return;
            }
        }
        String str5 = "detectFinish: detectResult = " + i;
        String str6 = this.f13310e;
        if (i != 0) {
            m12938a = "".getBytes();
        }
        m13142a(i, str6, m12938a);
    }

    /* renamed from: a */
    public final void m13139a(EnumC5548r2 enumC5548r2, String str) {
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
        C5402d.f12429a = this.f13329x;
        String str3 = "fail_detect:" + str2;
        String str4 = this.f13310e;
        int i = this.f13327v;
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
        if (!this.f13301P) {
            getPresenter().m12931a(this.mHost, 2, getPresenter().m12938a(4), 4);
        }
        m13143a(107, bundle);
    }
}
