package com.megvii.lv5.sdk.detect.actionflash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p083v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.megvii.lv5.C5383b;
import com.megvii.lv5.C5399c3;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5404d0;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.C5490k3;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5496l2;
import com.megvii.lv5.C5515n0;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.C5537q;
import com.megvii.lv5.C5538q0;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.C5636w2;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.CountDownTimerC5533p2;
import com.megvii.lv5.EnumC5548r2;
import com.megvii.lv5.InterfaceC5526o1;
import com.megvii.lv5.InterfaceC5612t;
import com.megvii.lv5.RunnableC5489k2;
import com.megvii.lv5.lib.jni.MegBlur;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.base.DetectBaseActivity;
import com.megvii.lv5.sdk.detect.MegliveModeImpl;
import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;
import com.megvii.lv5.sdk.view.CameraGLSurfaceView;
import com.megvii.lv5.sdk.view.HorizontalMarqueeView;
import com.megvii.lv5.sdk.view.HorizontalStepView;
import com.megvii.lv5.sdk.view.LoadingCoverView;
import com.megvii.lv5.sdk.view.RadarView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ActionFlashLivenessActivity extends DetectBaseActivity<C5404d0> implements View.OnClickListener, InterfaceC5526o1, InterfaceC5612t {

    /* renamed from: Y */
    public static final /* synthetic */ int f13346Y = 0;

    /* renamed from: A */
    public LinearLayout f13347A;

    /* renamed from: B */
    public ImageView f13348B;

    /* renamed from: C */
    public ImageView f13349C;

    /* renamed from: D */
    public ImageView f13350D;

    /* renamed from: E */
    public ImageView f13351E;

    /* renamed from: F */
    public ImageView f13352F;

    /* renamed from: G */
    public TextView f13353G;

    /* renamed from: H */
    public TextView f13354H;

    /* renamed from: I */
    public TextView f13355I;

    /* renamed from: J */
    public TextView f13356J;

    /* renamed from: K */
    public TextView f13357K;

    /* renamed from: L */
    public HorizontalStepView f13358L;

    /* renamed from: M */
    public TextSwitcher f13359M;

    /* renamed from: N */
    public C5515n0 f13360N;

    /* renamed from: Q */
    public int[] f13363Q;

    /* renamed from: T */
    public View.OnClickListener f13366T;

    /* renamed from: U */
    public Context f13367U;

    /* renamed from: X */
    public String f13370X;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public String f13371a;

    /* renamed from: f */
    public SurfaceTexture f13376f;

    /* renamed from: j */
    public LoadingCoverView f13380j;

    /* renamed from: l */
    public C5496l2 f13382l;

    /* renamed from: m */
    public C5636w2 f13383m;

    /* renamed from: p */
    public CameraGLSurfaceView f13386p;

    /* renamed from: q */
    public ImageView f13387q;

    /* renamed from: r */
    public ImageView f13388r;

    /* renamed from: s */
    public RadarView f13389s;

    /* renamed from: t */
    public ImageView f13390t;

    /* renamed from: u */
    public ImageView f13391u;

    /* renamed from: v */
    public ImageView f13392v;

    /* renamed from: w */
    public TextView f13393w;

    /* renamed from: x */
    public ImageView f13394x;

    /* renamed from: y */
    public HorizontalMarqueeView f13395y;

    /* renamed from: z */
    public ViewGroup f13396z;

    /* renamed from: b */
    public int f13372b = 1;

    /* renamed from: c */
    public String f13373c = "";

    /* renamed from: d */
    public boolean f13374d = false;

    /* renamed from: e */
    public volatile boolean f13375e = false;

    /* renamed from: g */
    public boolean f13377g = false;

    /* renamed from: h */
    public int f13378h = 0;

    /* renamed from: i */
    public int f13379i = 0;

    /* renamed from: k */
    public AnimationDrawable f13381k = null;

    /* renamed from: n */
    public CountDownTimer f13384n = null;

    /* renamed from: o */
    public CountDownTimerC5533p2 f13385o = null;

    /* renamed from: O */
    public volatile boolean f13361O = true;

    /* renamed from: P */
    public boolean f13362P = false;

    /* renamed from: R */
    public boolean f13364R = false;

    /* renamed from: S */
    public boolean f13365S = false;

    /* renamed from: V */
    public Handler f13368V = new HandlerC5577a(Looper.getMainLooper());

    /* renamed from: W */
    public boolean f13369W = false;

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class HandlerC5577a extends Handler {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class RunnableC5578a implements Runnable {
            public RunnableC5578a(HandlerC5577a handlerC5577a) {
            }

            @Override // java.lang.Runnable
            public void run() {
                C5537q.f13180g.m13204a(0, 0, 0);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$a$b */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class RunnableC5579b implements Runnable {
            public RunnableC5579b(HandlerC5577a handlerC5577a) {
            }

            @Override // java.lang.Runnable
            public void run() {
                C5537q.f13180g.m13206a(-1);
            }
        }

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$a$c */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class RunnableC5580c implements Runnable {
            public RunnableC5580c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ActionFlashLivenessActivity.this.f13355I.setVisibility(0);
            }
        }

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$a$d */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class CountDownTimerC5581d extends CountDownTimer {
            public CountDownTimerC5581d(long j, long j2) {
                super(j, j2);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ActionFlashLivenessActivity.this.mo13004b(false);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                String str = "onTick: millisUntilFinished = " + j;
            }
        }

        public HandlerC5577a(Looper looper) {
            super(looper);
        }

        /*  JADX ERROR: Failed to decode insn: 0x0403: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.a.handleMessage(android.os.Message):void
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
        /*  JADX ERROR: Failed to decode insn: 0x0451: CONST_STRING r191, method: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.a.handleMessage(android.os.Message):void
            java.lang.IllegalArgumentException: newPosition < 0: (-721141200 < 0)
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
        /*  JADX ERROR: Failed to decode insn: 0x06D0: CONST_STRING r0, method: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.a.handleMessage(android.os.Message):void
            java.lang.IllegalArgumentException: newPosition > limit: (130547848 > 13931064)
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
        /*  JADX ERROR: Failed to decode insn: 0x06DE: UNKNOWN(0x8842), method: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x06DE: UNKNOWN(0x8842)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0723: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0723: UNKNOWN(0xF541)'
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
        /*  JADX ERROR: Failed to decode insn: 0x0B8D: UNKNOWN(0xF541), method: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.a.handleMessage(android.os.Message):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0B8D: UNKNOWN(0xF541)'
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
        public void handleMessage(android.os.Message r24) {
            /*
                Method dump skipped, instructions count: 3152
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.HandlerC5577a.handleMessage(android.os.Message):void");
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5582b implements ViewSwitcher.ViewFactory {
        public C5582b() {
        }

        @Override // android.widget.ViewSwitcher.ViewFactory
        public View makeView() {
            TextView textView = new TextView(ActionFlashLivenessActivity.this);
            Resources resources = ActionFlashLivenessActivity.this.getResources();
            ActionFlashLivenessActivity actionFlashLivenessActivity = ActionFlashLivenessActivity.this;
            int i = ActionFlashLivenessActivity.f13346Y;
            textView.setTextSize(0, resources.getDimension(actionFlashLivenessActivity.livenessHomeRemindSize));
            textView.setTextColor(ActionFlashLivenessActivity.this.getResources().getColor(ActionFlashLivenessActivity.this.livenessHomeNormalRemindTextColor));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            textView.setLayoutParams(layoutParams);
            return textView;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5583c implements CountDownTimerC5533p2.InterfaceC5534a {
        public C5583c(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        }

        @Override // com.megvii.lv5.CountDownTimerC5533p2.InterfaceC5534a
        /* renamed from: a */
        public void mo13067a() {
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnClickListenerC5584d implements DialogInterface.OnClickListener {
        public DialogInterface$OnClickListenerC5584d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            ActionFlashLivenessActivity.this.isRequestingScreenRecordPermission = false;
            dialogInterface.dismiss();
            ActionFlashLivenessActivity actionFlashLivenessActivity = ActionFlashLivenessActivity.this;
            actionFlashLivenessActivity.m13106a(EnumC5548r2.SCRN_AUTHORIZATION_FAIL, actionFlashLivenessActivity.f13371a);
        }
    }

    /* compiled from: Proguard */
    @NBSInstrumented
    /* renamed from: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class View$OnClickListenerC5585e implements View.OnClickListener {

        /* renamed from: a */
        public WeakReference<ActionFlashLivenessActivity> f13402a;

        public View$OnClickListenerC5585e(ActionFlashLivenessActivity actionFlashLivenessActivity) {
            this.f13402a = new WeakReference<>(actionFlashLivenessActivity);
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x011f  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0176  */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClick(android.view.View r11) {
            /*
                Method dump skipped, instructions count: 538
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.View$OnClickListenerC5585e.onClick(android.view.View):void");
        }
    }

    /* renamed from: B */
    public static /* synthetic */ HorizontalStepView m13114B(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13358L;
    }

    /* renamed from: a */
    public static /* synthetic */ TextView m13105a(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13353G;
    }

    /* renamed from: a */
    public static void m13103a(ActionFlashLivenessActivity actionFlashLivenessActivity, int i, int i2) {
        int i3;
        int i4;
        int i5;
        long j;
        int i6;
        int i7;
        int i8;
        long j2;
        TextView textView;
        String str;
        Resources resources;
        int i9;
        actionFlashLivenessActivity.f13370X = actionFlashLivenessActivity.getMirroFailedMsg(i);
        String str2 = "showMirroFailedMsg: isFirstLookMirror = " + actionFlashLivenessActivity.f13361O;
        if (i2 != 3) {
            if (i == 14) {
                actionFlashLivenessActivity.m13107a(actionFlashLivenessActivity.f13353G, actionFlashLivenessActivity.f13370X, actionFlashLivenessActivity.getResources().getColor(actionFlashLivenessActivity.livenessHomeNormalRemindTextColor));
                int[] m13195a = C5538q0.m13195a(-1);
                C5383b c5383b = C5383b.C5384a.f12381a;
                int i10 = m13195a[0];
                int i11 = m13195a[1];
                int i12 = m13195a[2];
                long j3 = c5383b.f12380a.f12359a;
                if (j3 != 0) {
                    i6 = i12;
                    i7 = i10;
                    i8 = i11;
                    j2 = j3;
                    MegBlur.nativeSetColorContour(j2, i7, i8, i6, 0.1f);
                }
                return;
            }
            actionFlashLivenessActivity.m13107a(actionFlashLivenessActivity.f13353G, actionFlashLivenessActivity.f13370X, actionFlashLivenessActivity.getResources().getColor(actionFlashLivenessActivity.livenessHomeFailedRemindTextColor));
            int[] m13195a2 = C5538q0.m13195a(-1);
            C5383b c5383b2 = C5383b.C5384a.f12381a;
            int i13 = m13195a2[0];
            int i14 = m13195a2[1];
            int i15 = m13195a2[2];
            long j4 = c5383b2.f12380a.f12359a;
            if (j4 != 0) {
                i3 = i15;
                i4 = i13;
                i5 = i14;
                j = j4;
                MegBlur.nativeSetColorContour(j, i4, i5, i3, 1.0f);
            }
        } else if (i == 14) {
            actionFlashLivenessActivity.m13107a(actionFlashLivenessActivity.f13353G, actionFlashLivenessActivity.f13370X, actionFlashLivenessActivity.getResources().getColor(actionFlashLivenessActivity.livenessHomeNormalRemindTextColor));
            int[] m13195a3 = C5538q0.m13195a(-1);
            C5383b c5383b3 = C5383b.C5384a.f12381a;
            int i16 = m13195a3[0];
            int i17 = m13195a3[1];
            int i18 = m13195a3[2];
            long j5 = c5383b3.f12380a.f12359a;
            if (j5 != 0) {
                i6 = i18;
                i7 = i16;
                i8 = i17;
                j2 = j5;
                MegBlur.nativeSetColorContour(j2, i7, i8, i6, 0.1f);
            }
        } else {
            if (actionFlashLivenessActivity.f13361O) {
                textView = actionFlashLivenessActivity.f13353G;
                str = actionFlashLivenessActivity.f13370X;
                resources = actionFlashLivenessActivity.getResources();
                i9 = actionFlashLivenessActivity.livenessHomeNormalRemindTextColor;
            } else {
                textView = actionFlashLivenessActivity.f13353G;
                str = actionFlashLivenessActivity.f13370X;
                resources = actionFlashLivenessActivity.getResources();
                i9 = actionFlashLivenessActivity.livenessHomeFailedRemindTextColor;
            }
            actionFlashLivenessActivity.m13107a(textView, str, resources.getColor(i9));
            int[] m13195a4 = C5538q0.m13195a(-1);
            C5383b c5383b4 = C5383b.C5384a.f12381a;
            int i19 = m13195a4[0];
            int i20 = m13195a4[1];
            int i21 = m13195a4[2];
            long j6 = c5383b4.f12380a.f12359a;
            if (j6 != 0) {
                i3 = i21;
                i4 = i19;
                i5 = i20;
                j = j6;
                MegBlur.nativeSetColorContour(j, i4, i5, i3, 1.0f);
            }
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m13102a(ActionFlashLivenessActivity actionFlashLivenessActivity, TextView textView, String str, int i) {
        actionFlashLivenessActivity.m13107a(textView, str, i);
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m13101a(ActionFlashLivenessActivity actionFlashLivenessActivity, boolean z) {
        actionFlashLivenessActivity.f13361O = z;
        return z;
    }

    /* renamed from: b */
    public static /* synthetic */ TextSwitcher m13100b(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13359M;
    }

    /* renamed from: b */
    public static void m13099b(ActionFlashLivenessActivity actionFlashLivenessActivity, int i, int i2) {
        Resources resources;
        C5667z2 m12879a;
        int i3;
        actionFlashLivenessActivity.getClass();
        try {
            if (actionFlashLivenessActivity.f13364R) {
                int i4 = actionFlashLivenessActivity.f13360N.f12976c;
                ArrayList arrayList = new ArrayList();
                int i5 = 0;
                while (i5 < i4) {
                    arrayList.add(i5 < i2 ? new C5490k3(1) : i5 == i2 ? new C5490k3(2) : new C5490k3(-1));
                    i5++;
                }
                HorizontalStepView horizontalStepView = actionFlashLivenessActivity.f13358L;
                horizontalStepView.f13608b = arrayList;
                horizontalStepView.f13607a.setStepNum(arrayList);
                actionFlashLivenessActivity.f13358L.setVisibility(0);
            }
            String str = i + "";
            if (i == 3) {
                resources = actionFlashLivenessActivity.getResources();
                m12879a = C5667z2.m12879a(actionFlashLivenessActivity);
                i3 = C5559R.string.key_liveness_home_dynamic_action_timeout_text;
            } else {
                resources = actionFlashLivenessActivity.getResources();
                m12879a = C5667z2.m12879a(actionFlashLivenessActivity);
                i3 = C5559R.string.key_liveness_home_dynamic_action_wrong_text;
            }
            actionFlashLivenessActivity.f13357K.setText(resources.getString(m12879a.m12875d(actionFlashLivenessActivity.getString(i3))));
            actionFlashLivenessActivity.f13357K.setVisibility(0);
            actionFlashLivenessActivity.f13354H.setVisibility(8);
            actionFlashLivenessActivity.f13383m.m12941b();
            actionFlashLivenessActivity.f13383m.m12940b(-1, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m13098b(ActionFlashLivenessActivity actionFlashLivenessActivity, boolean z) {
        actionFlashLivenessActivity.f13375e = z;
        return z;
    }

    /* renamed from: c */
    public static /* synthetic */ boolean m13097c(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13361O;
    }

    /* renamed from: c */
    public static /* synthetic */ boolean m13096c(ActionFlashLivenessActivity actionFlashLivenessActivity, boolean z) {
        actionFlashLivenessActivity.isRequestingScreenRecordPermission = z;
        return z;
    }

    /* renamed from: e */
    public static /* synthetic */ Context m13094e(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13367U;
    }

    /* renamed from: f */
    public static /* synthetic */ LoadingCoverView m13093f(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13380j;
    }

    /* renamed from: g */
    public static /* synthetic */ CameraGLSurfaceView m13092g(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13386p;
    }

    /* renamed from: h */
    public static /* synthetic */ ImageView m13091h(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13392v;
    }

    /* renamed from: i */
    public static /* synthetic */ ImageView m13090i(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13352F;
    }

    /* renamed from: j */
    public static /* synthetic */ void m13089j(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        actionFlashLivenessActivity.m13081p();
    }

    /* renamed from: k */
    public static /* synthetic */ RadarView m13088k(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13389s;
    }

    /* renamed from: l */
    public static /* synthetic */ int m13087l(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.livenessHomeNormalRemindTextColor;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m13086m(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13364R;
    }

    /* renamed from: n */
    public static /* synthetic */ TextView m13084n(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13355I;
    }

    /* renamed from: o */
    public static /* synthetic */ C5515n0 m13082o(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13360N;
    }

    /* renamed from: p */
    public static /* synthetic */ ViewGroup m13080p(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13396z;
    }

    /* renamed from: q */
    public static /* synthetic */ TextView m13078q(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13354H;
    }

    /* renamed from: r */
    public static /* synthetic */ CountDownTimerC5533p2 m13076r(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13385o;
    }

    /* renamed from: s */
    public static /* synthetic */ HorizontalMarqueeView m13075s(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13395y;
    }

    /* renamed from: t */
    public static /* synthetic */ int m13074t(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.livenessHomeNormalRemindTextColor;
    }

    /* renamed from: u */
    public static /* synthetic */ int m13073u(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.livenessHomeProcessBarColor;
    }

    /* renamed from: v */
    public static /* synthetic */ void m13072v(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        actionFlashLivenessActivity.m13079q();
    }

    /* renamed from: w */
    public static /* synthetic */ C5496l2 m13071w(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13382l;
    }

    /* renamed from: x */
    public static /* synthetic */ C5636w2 m13070x(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        return actionFlashLivenessActivity.f13383m;
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13014a() {
        m13109a(109, new Bundle());
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13012a(int i, float f, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("color", str);
        bundle.putInt("curStep", i);
        bundle.putFloat("progress", f);
        m13109a(103, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13011a(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        bundle.putInt("qualityResult", i2);
        m13109a(102, bundle);
    }

    /* renamed from: a */
    public final void m13109a(int i, Bundle bundle) {
        Message message = new Message();
        message.what = i;
        message.setData(bundle);
        this.f13368V.sendMessage(message);
    }

    /* renamed from: a */
    public final void m13108a(int i, String str, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putString("bizToken", str);
        bundle.putSerializable("failedType", i == 1 ? EnumC5548r2.LIVENESS_TIME_OUT : i == 2 ? EnumC5548r2.MEGLIVE_FLASH_ACTION_ERROR : i == 10 ? EnumC5548r2.MEGLIVE_FLASH_ACTION_TIMEOUT : i == 0 ? EnumC5548r2.LIVENESS_FINISH : i == 5 ? EnumC5548r2.SCRN_RECORD_FAIL : i == 7 ? EnumC5548r2.VIDEO_SAVE_FAIL : EnumC5548r2.LIVENESS_FAILURE);
        bundle.putByteArray("delta", bArr);
        m13109a(104, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13010a(SurfaceTexture surfaceTexture) {
        this.f13376f = surfaceTexture;
        if (surfaceTexture == null || !this.f13377g) {
            return;
        }
        this.f13386p.m13037b();
        getPresenter().m13567p();
    }

    /* renamed from: a */
    public final void m13107a(TextView textView, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        textView.setText(str);
        textView.setTextColor(i);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13009a(C5492l c5492l, C5553s1.InterfaceC5554a interfaceC5554a, C5553s1.InterfaceC5555b interfaceC5555b) {
        this.f13386p.m13038a(c5492l, interfaceC5554a, interfaceC5555b);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: b */
    public String mo13007b() {
        return this.apiKey;
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: b */
    public void mo13005b(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("actionIndex", i);
        bundle.putInt("currentAction", i2);
        m13109a(114, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: b */
    public void mo13004b(boolean z) {
        Bundle bundle = new Bundle();
        EnumC5548r2 enumC5548r2 = z ? EnumC5548r2.LIVENESS_FINISH : EnumC5548r2.DATA_UPLOAD_FAIL;
        bundle.putString("bizToken", this.f13371a);
        bundle.putSerializable("failedType", enumC5548r2);
        bundle.putByteArray("delta", "".getBytes());
        m13109a(104, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: c */
    public void mo13003c() {
        m13109a(111, new Bundle());
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: c */
    public void mo13002c(int i) {
        if (i == 14 || i == 17) {
            this.f13375e = true;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        m13109a(115, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: c */
    public void mo13001c(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("result_type", i);
        bundle.putInt("action_index", i2);
        m13109a(116, bundle);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public C5404d0 createPresenter() {
        return new C5404d0();
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: d */
    public int mo13000d() {
        return this.f13378h;
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: e */
    public String mo12999e() {
        return this.videoKey;
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: f */
    public int mo12998f() {
        return this.mVerticalCheckType;
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: g */
    public String mo12997g() {
        return this.mediaSourcePath;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public int getLayoutResId() {
        return C5559R.C5563layout.activity_action_flash_liveness;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MediaProjection getScreenRecordContext() {
        return C5462h2.C5464b.f12721a.f12717d;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MegLiveDetectConfig getUserDetectConfig() {
        return null;
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: i */
    public void mo12996i() {
        m13109a(113, new Bundle());
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initData() {
        this.f13364R = true;
        this.f13371a = C5527o2.m13256a(getContext());
        C5515n0 m13223h = C5527o2.m13223h(this);
        this.f13360N = m13223h;
        this.f13362P = m13223h.f12976c != 1;
        this.f13372b = m13223h.f12973b;
        this.f13348B.setImageDrawable(getResources().getDrawable(this.livenessVerticalDrawableId));
        AnimationDrawable animationDrawable = new AnimationDrawable();
        this.f13381k = animationDrawable;
        animationDrawable.addFrame(getResources().getDrawable(C5559R.C5561drawable.megvii_live5_audio_horns_0), 500);
        this.f13381k.addFrame(getResources().getDrawable(C5559R.C5561drawable.megvii_live5_audio_horns_1), 500);
        this.f13381k.addFrame(getResources().getDrawable(C5559R.C5561drawable.megvii_live5_audio_horns_2), 500);
        this.f13381k.setOneShot(false);
        DrawableCompat.setTintList(this.f13381k, ColorStateList.valueOf(getResources().getColor(C5667z2.m12879a(this).m12878a(getString(C5559R.string.key_liveness_liveness_custom_prompt_color)))));
        this.f13353G.setTextSize(0, getResources().getDimension(this.livenessHomeRemindSize));
        this.f13355I.setTextSize(0, getResources().getDimension(this.livenessHomeLoadingTextSize));
        this.f13355I.setTextColor(-1);
        this.f13356J.setTextSize(0, getResources().getDimension(this.livenessHomeDeviceVerticalRemindSize));
        this.f13356J.setTextColor(getResources().getColor(this.livenessHomeDeviceVerticalRemindColor));
        this.f13354H.setTextColor(getResources().getColor(this.livenessHomeNormalRemindTextColor));
        this.f13392v.setImageDrawable(getResources().getDrawable(this.livenessActionSuccessDrawableId));
        this.f13394x.setColorFilter(Color.argb(255, 0, 255, 0));
        if (this.mIsShowLogo) {
            this.f13391u.setImageDrawable(getResources().getDrawable(this.livenessLogoDrawableId));
            this.f13391u.setVisibility(0);
        } else {
            this.f13391u.setVisibility(8);
        }
        C5515n0 c5515n0 = this.f13360N;
        if (c5515n0.f12913E1 == 1) {
            String string = this.f13364R ? getResources().getString(C5667z2.m12879a(this.f13367U).m12875d(getString(C5559R.string.key_liveness_home_prompt_text))) : c5515n0.f12916F1;
            if (!TextUtils.isEmpty(string)) {
                this.f13393w.setVisibility(0);
                this.f13393w.setText(string);
                if (this.livenessHomeCustomPromptBackgroundColor != 0) {
                    this.f13393w.setBackgroundColor(getResources().getColor(this.livenessHomeCustomPromptBackgroundColor));
                }
                if (this.livenessHomeCustomPromptTextColor != 0) {
                    this.f13393w.setTextColor(getResources().getColor(this.livenessHomeCustomPromptTextColor));
                }
            }
        }
        m13079q();
        this.f13374d = C5527o2.m13223h(this).f13020q1;
        if (this.f13360N.f12949Q1) {
            soundOff();
        } else if (this.mIsAutoAdjustVolume) {
            changeVolume(this.mSuggestVolume);
        }
        this.f13385o = new CountDownTimerC5533p2(this.f13354H, this.f13360N.f12982e * 1000, 1000L, new C5583c(this));
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initView() {
        this.f13367U = getApplicationContext();
        CameraGLSurfaceView cameraGLSurfaceView = (CameraGLSurfaceView) findViewById(C5559R.C5562id.liveness_layout_cameraView);
        this.f13386p = cameraGLSurfaceView;
        cameraGLSurfaceView.setVisibility(0);
        this.f13389s = (RadarView) findViewById(C5559R.C5562id.radar_view);
        this.f13387q = (ImageView) findViewById(C5559R.C5562id.liveness_img_color_frame);
        this.f13388r = (ImageView) findViewById(C5559R.C5562id.liveness_img_color_line);
        LoadingCoverView loadingCoverView = (LoadingCoverView) findViewById(C5559R.C5562id.liveness_layout_lcv);
        this.f13380j = loadingCoverView;
        ImageView imageView = this.f13387q;
        ImageView imageView2 = this.f13388r;
        loadingCoverView.f13655W = imageView;
        loadingCoverView.f13657a0 = imageView2;
        loadingCoverView.setmProgressCallback(this);
        this.f13366T = new View$OnClickListenerC5585e(this);
        this.f13393w = (TextView) findViewById(C5559R.C5562id.tv_liveness_top_tips);
        this.f13394x = (ImageView) findViewById(C5559R.C5562id.img_audio);
        this.f13395y = (HorizontalMarqueeView) findViewById(C5559R.C5562id.marquee_tips);
        this.f13396z = (ViewGroup) findViewById(C5559R.C5562id.ll_custom_marquee);
        this.f13353G = (TextView) findViewById(C5559R.C5562id.tv_flash_tips);
        this.f13354H = (TextView) findViewById(C5559R.C5562id.tv_countdown_tips);
        this.f13355I = (TextView) findViewById(C5559R.C5562id.tv_finish_tips);
        this.f13356J = (TextView) findViewById(C5559R.C5562id.tv_vertical_tips);
        this.f13391u = (ImageView) findViewById(C5559R.C5562id.iv_megvii_powerby);
        this.f13392v = (ImageView) findViewById(C5559R.C5562id.img_green_success);
        this.f13390t = (ImageView) findViewById(C5559R.C5562id.iv_liveness_homepage_close);
        LinearLayout linearLayout = (LinearLayout) findViewById(C5559R.C5562id.ll_detect_close);
        this.f13347A = linearLayout;
        linearLayout.setOnClickListener(this.f13366T);
        this.f13348B = (ImageView) findViewById(C5559R.C5562id.iv_bg_vertical);
        ImageView imageView3 = (ImageView) findViewById(C5559R.C5562id.tv_circle_topleft);
        this.f13349C = imageView3;
        imageView3.setImageBitmap(this.bitmapTop);
        ImageView imageView4 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomright);
        this.f13350D = imageView4;
        imageView4.setImageBitmap(this.bitmapBottom);
        ImageView imageView5 = (ImageView) findViewById(C5559R.C5562id.tv_circle_topright);
        this.f13351E = imageView5;
        imageView5.setImageBitmap(this.bitmapTop);
        ImageView imageView6 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomleft);
        this.f13352F = imageView6;
        imageView6.setImageBitmap(this.bitmapBottom);
        this.f13382l = new C5496l2(this, this.f13394x);
        this.f13383m = new C5636w2(this);
        this.f13357K = (TextView) findViewById(C5559R.C5562id.tv_action_failed_tips);
        this.f13358L = (HorizontalStepView) findViewById(C5559R.C5562id.step_view);
        TextSwitcher textSwitcher = (TextSwitcher) findViewById(C5559R.C5562id.tv_action_tips);
        this.f13359M = textSwitcher;
        textSwitcher.setFactory(new C5582b());
    }

    @Override // com.megvii.lv5.InterfaceC5526o1
    /* renamed from: l */
    public void mo13047l() {
        getPresenter().m13578e();
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: m */
    public void mo12995m() {
        String str;
        if (this.f13362P) {
            if (TextUtils.isEmpty(this.mediaSourcePath)) {
                str = null;
            } else {
                str = this.mediaSourcePath + "/" + getResources().getString(C5559R.string.key_meglive_well_done_m4a) + ".m4a";
            }
            this.f13383m.m12942a(C5667z2.m12879a(this).m12876c(getResources().getString(C5559R.string.key_meglive_well_done_m4a)), str);
            this.f13383m.m12940b(-1, null);
        }
    }

    /* renamed from: n */
    public final void m13085n() {
        if (this.f13369W) {
            return;
        }
        getPresenter().m13564s();
        getPresenter().closeCamera();
        getPresenter().detach();
        this.f13386p.onPause();
        CountDownTimer countDownTimer = this.f13384n;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimerC5533p2 countDownTimerC5533p2 = this.f13385o;
        if (countDownTimerC5533p2 != null) {
            countDownTimerC5533p2.cancel();
        }
        Handler handler = this.f13368V;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        C5636w2 c5636w2 = this.f13383m;
        if (c5636w2 != null) {
            c5636w2.m12943a();
        }
        C5462h2.C5464b.f12721a.m13492a((Context) this);
        MediaProjection mediaProjection = this.mediaProjection;
        if (mediaProjection != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                mediaProjection.stop();
            }
            this.mediaProjection = null;
        }
        this.f13369W = true;
    }

    /* renamed from: o */
    public final void m13083o() {
        this.f13368V.removeCallbacksAndMessages(null);
        C5404d0 presenter = getPresenter();
        presenter.getClass();
        try {
            C5492l c5492l = presenter.mCameraManager;
            if (c5492l != null) {
                c5492l.f12846a.mo13262c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        getPresenter().m13564s();
        CountDownTimerC5533p2 countDownTimerC5533p2 = this.f13385o;
        if (countDownTimerC5533p2 != null) {
            countDownTimerC5533p2.cancel();
        }
        C5496l2 c5496l2 = this.f13382l;
        ((Activity) c5496l2.f12862b).runOnUiThread(new RunnableC5489k2(c5496l2));
        this.f13383m.m12941b();
        this.f13383m.m12940b(-1, null);
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
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity.onActivityResult(int, int, android.content.Intent):void");
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
        JniLib.m15918cV(this, bundle, 18);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m13085n();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m13077r();
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
            m13083o();
            if (!isFinishing() && !this.f13375e) {
                C5402d.f12432d = true;
                m13106a(EnumC5548r2.GO_TO_BACKGROUND, this.f13371a);
            }
            if (isFinishing()) {
                m13085n();
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
        if (this.f13375e) {
            NBSAppInstrumentation.activityResumeEndIns();
            return;
        }
        int i = this.f13360N.f12952R1;
        if (i != 0) {
            C5462h2 c5462h2 = C5462h2.C5464b.f12721a;
            if (c5462h2.f12717d == null && (!this.f13365S || this.isRequestingScreenRecordPermission)) {
                if (i == 1 && !C5538q0.m13193a((Context) this)) {
                    m13106a(EnumC5548r2.NO_AUDIO_RECORD_PERMISSION, this.f13371a);
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else if (this.f13365S) {
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else {
                    C5402d.f12429a = "liveness-sdk";
                    String str = this.f13371a;
                    int i2 = this.f13372b;
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
                    this.f13365S = true;
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
        this.f13368V.sendMessageDelayed(message, 200L);
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
    public final void m13081p() {
        this.f13347A.setVisibility(8);
    }

    /* renamed from: q */
    public final void m13079q() {
        if (!this.f13360N.f13044y1) {
            m13081p();
            return;
        }
        this.f13390t.setImageDrawable(getResources().getDrawable(this.livenessCloseDrawableId));
        this.f13347A.setVisibility(0);
    }

    /* renamed from: r */
    public void m13077r() {
        if (this.f13375e || !this.f13360N.f13044y1) {
            return;
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.alertDialog = this.mDialogUtil.m13172a(this.f13364R, this.f13366T);
            C5402d.f12429a = this.f13373c;
            String str = this.f13371a;
            int i = this.f13372b;
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
            m13083o();
        }
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: b */
    public void mo13006b(int i) {
        byte[] bytes;
        if (this.f13364R) {
            return;
        }
        C5404d0 presenter = getPresenter();
        presenter.getClass();
        try {
            byte[] m13576g = presenter.m13576g();
            String m13228d = C5527o2.m13228d();
            C5402d.f12429a = presenter.f12482j;
            String str = presenter.f12500s;
            int i2 = presenter.f12502t;
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
            bytes = model.m13159a(m13228d, z, false, m13607a, "", bytes2, m13576g, bytes3, bytes4, bytes5, c5492l.f12850e, c5492l.f12851f);
        } catch (Exception e2) {
            e2.printStackTrace();
            bytes = "".getBytes();
        }
        getPresenter().m13584b(this.mHost, 1, bytes, i);
    }

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13008a(boolean z) {
        if (!z) {
            m13106a(EnumC5548r2.DEVICE_NOT_SUPPORT, this.f13371a);
            return;
        }
        this.f13377g = true;
        if (this.f13376f != null) {
            this.f13386p.m13037b();
            getPresenter().m13567p();
        }
        C5402d.f12429a = "liveness-sdk";
        String str = this.f13371a;
        int i = this.f13372b;
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

    @Override // com.megvii.lv5.InterfaceC5612t
    /* renamed from: a */
    public void mo13013a(int i) {
        if (!C5527o2.m13221j(this) && !C5527o2.m13226e(this)) {
            m13109a(108, new Bundle());
        }
        int i2 = i != 0 ? 2 : 1;
        if (i != 0) {
            JSONObject jSONObject = null;
            if (i == 1) {
                C5402d.f12429a = this.f13373c;
                String str = this.f13371a;
                int i3 = this.f13372b;
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
                C5402d.f12429a = this.f13373c;
                String str2 = this.f13371a;
                int i5 = this.f13372b;
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
                C5402d.f12429a = this.f13373c;
                String str3 = this.f13371a;
                int i7 = this.f13372b;
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
                C5402d.f12429a = this.f13373c;
                String str4 = this.f13371a;
                int i9 = this.f13372b;
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
        byte[] m13595a = getPresenter().m13595a(i);
        if (this.f13364R) {
            String str5 = this.f13371a;
            if (i != 0) {
                m13595a = "".getBytes();
            }
            m13108a(i, str5, m13595a);
            return;
        }
        getPresenter().m13588a(this.mHost, i2, m13595a, i);
        if (i != 0) {
            m13108a(i, this.f13371a, "".getBytes());
        } else {
            m13109a(110, new Bundle());
        }
    }

    /* renamed from: a */
    public final void m13106a(EnumC5548r2 enumC5548r2, String str) {
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
        C5402d.f12429a = this.f13373c;
        String str3 = "fail_detect:" + str2;
        String str4 = this.f13371a;
        int i = this.f13372b;
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
        if (!this.f13364R) {
            getPresenter().m13588a(this.mHost, 2, getPresenter().m13595a(4), 4);
        }
        m13109a(104, bundle);
    }
}
