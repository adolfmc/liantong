package com.megvii.lv5;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.text.TextUtils;
import com.megvii.lv5.AbstractC5500m;
import com.megvii.lv5.AbstractRunnableC5460h1;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.lib.jni.MegLiveDetector;
import com.megvii.lv5.sdk.base.DetectBasePresenter;
import com.megvii.lv5.sdk.detect.MegliveModeImpl;
import com.megvii.lv5.sdk.result.LivenessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.i0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5469i0 extends DetectBasePresenter<InterfaceC5618u, MegliveModeImpl> implements AbstractC5500m.InterfaceC5502b, AbstractC5500m.InterfaceC5504d, AbstractC5500m.InterfaceC5505e, C5553s1.InterfaceC5554a, C5553s1.InterfaceC5555b {

    /* renamed from: n0 */
    public static String[] f12725n0;

    /* renamed from: A */
    public C5621u2<C5452g1> f12726A;

    /* renamed from: D */
    public boolean f12729D;

    /* renamed from: E */
    public boolean f12730E;

    /* renamed from: F */
    public boolean f12731F;

    /* renamed from: G */
    public int f12732G;

    /* renamed from: H */
    public int f12733H;

    /* renamed from: I */
    public int f12734I;

    /* renamed from: J */
    public int f12735J;

    /* renamed from: O */
    public long f12740O;

    /* renamed from: c */
    public String[][] f12756c;

    /* renamed from: d0 */
    public C5475i1 f12759d0;

    /* renamed from: e0 */
    public C5475i1 f12761e0;

    /* renamed from: f0 */
    public C5475i1 f12763f0;

    /* renamed from: g */
    public C5474e f12764g;

    /* renamed from: g0 */
    public C5475i1 f12765g0;

    /* renamed from: h */
    public int f12766h;

    /* renamed from: o */
    public int f12779o;

    /* renamed from: u */
    public int f12785u;

    /* renamed from: y */
    public C5515n0 f12789y;

    /* renamed from: a */
    public int f12752a = -1;

    /* renamed from: b */
    public Object f12754b = new Object();

    /* renamed from: d */
    public int f12758d = 10;

    /* renamed from: e */
    public int f12760e = 0;

    /* renamed from: f */
    public int f12762f = 4;

    /* renamed from: i */
    public String f12768i = "";

    /* renamed from: j */
    public int f12770j = 10;

    /* renamed from: k */
    public volatile int f12772k = -1;

    /* renamed from: l */
    public int f12774l = -1;

    /* renamed from: m */
    public int f12776m = -1;

    /* renamed from: n */
    public String f12778n = "";

    /* renamed from: p */
    public boolean f12780p = true;

    /* renamed from: q */
    public String f12781q = "";

    /* renamed from: r */
    public String f12782r = "";

    /* renamed from: s */
    public String f12783s = "";

    /* renamed from: t */
    public String f12784t = null;

    /* renamed from: v */
    public int f12786v = 0;

    /* renamed from: w */
    public String f12787w = "";

    /* renamed from: x */
    public int f12788x = -1;

    /* renamed from: z */
    public float f12790z = 0.0f;

    /* renamed from: B */
    public List<LivenessFile> f12727B = new ArrayList();

    /* renamed from: C */
    public List<LivenessFile> f12728C = new ArrayList();

    /* renamed from: K */
    public int f12736K = 0;

    /* renamed from: L */
    public boolean f12737L = false;

    /* renamed from: M */
    public volatile boolean f12738M = false;

    /* renamed from: N */
    public volatile boolean f12739N = true;

    /* renamed from: P */
    public Object f12741P = new Object();

    /* renamed from: Q */
    public boolean f12742Q = false;

    /* renamed from: R */
    public boolean f12743R = false;

    /* renamed from: S */
    public boolean f12744S = false;

    /* renamed from: T */
    public boolean f12745T = false;

    /* renamed from: U */
    public boolean f12746U = false;

    /* renamed from: V */
    public boolean f12747V = false;

    /* renamed from: W */
    public int f12748W = 0;

    /* renamed from: X */
    public int f12749X = 0;

    /* renamed from: Y */
    public long f12750Y = 0;

    /* renamed from: Z */
    public String f12751Z = "";

    /* renamed from: a0 */
    public int f12753a0 = 0;

    /* renamed from: b0 */
    public boolean f12755b0 = true;

    /* renamed from: c0 */
    public boolean f12757c0 = false;

    /* renamed from: h0 */
    public String f12767h0 = "";

    /* renamed from: i0 */
    public int f12769i0 = 0;

    /* renamed from: j0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12771j0 = new C5470a();

    /* renamed from: k0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12773k0 = new C5471b();

    /* renamed from: l0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12775l0 = new C5472c();

    /* renamed from: m0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12777m0 = new C5473d();

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.i0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5470a implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5470a() {
            System.currentTimeMillis();
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: a */
        public void mo12905a(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: b */
        public void mo12904b(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: c */
        public void mo12903c(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
            if (abstractRunnableC5460h1 instanceof C5482j1) {
                if (C5469i0.this.f12772k == 14 || C5469i0.this.f12772k == 17) {
                    C5469i0 c5469i0 = C5469i0.this;
                    if (c5469i0.f12730E || c5469i0.f12731F ? c5469i0.f12772k == 17 && C5469i0.this.f12788x == 0 : c5469i0.f12772k == 14 && C5469i0.this.f12788x == 0) {
                        C5469i0 c5469i02 = C5469i0.this;
                        C5402d.f12429a = c5469i02.f12768i;
                        String str = c5469i02.f12778n;
                        int i = c5469i02.f12779o;
                        JSONObject jSONObject = null;
                        if (!C5402d.f12432d) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("type", "track");
                                jSONObject2.put("project", C5402d.f12429a);
                                jSONObject2.put("event_id", UUID.randomUUID().toString());
                                jSONObject2.put("time", System.currentTimeMillis());
                                jSONObject2.put("event", "video_generate");
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("liveness", i);
                                jSONObject3.put("biz_token", str);
                                jSONObject3.put("try_times", 0);
                                int i2 = C5402d.f12431c + 1;
                                C5402d.f12431c = i2;
                                jSONObject3.put("index", i2);
                                jSONObject2.put("properties", jSONObject3);
                                C5402d.f12430b = "video_generate";
                                jSONObject = jSONObject2;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        C5399c3.m13606a(jSONObject);
                    }
                    C5469i0.this.f12744S = true;
                    C5469i0.this.m13480d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.i0$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5471b implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5471b() {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: a */
        public void mo12905a(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: b */
        public void mo12904b(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: c */
        public void mo12903c(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
            if (abstractRunnableC5460h1 instanceof C5482j1) {
                String str = "onReleased: whitebalance start = " + System.currentTimeMillis();
                C5469i0 c5469i0 = C5469i0.this;
                C5402d.f12429a = c5469i0.f12768i;
                String str2 = c5469i0.f12778n;
                int i = c5469i0.f12779o;
                JSONObject jSONObject = null;
                if (!C5402d.f12432d) {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("type", "track");
                        jSONObject2.put("project", C5402d.f12429a);
                        jSONObject2.put("event_id", UUID.randomUUID().toString());
                        jSONObject2.put("time", System.currentTimeMillis());
                        jSONObject2.put("event", "whitebalance_exposure_video_generate");
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("liveness", i);
                        jSONObject3.put("biz_token", str2);
                        jSONObject3.put("try_times", 0);
                        int i2 = C5402d.f12431c + 1;
                        C5402d.f12431c = i2;
                        jSONObject3.put("index", i2);
                        jSONObject2.put("properties", jSONObject3);
                        C5402d.f12430b = "whitebalance_exposure_video_generate";
                        jSONObject = jSONObject2;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                C5399c3.m13606a(jSONObject);
                C5469i0 c5469i02 = C5469i0.this;
                if (c5469i02.f12729D) {
                    c5469i02.f12745T = true;
                    C5469i0.this.m13480d();
                } else if (c5469i02.getView() != null) {
                    C5469i0.this.getView().mo12979b(1);
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.i0$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5472c implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5472c() {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: a */
        public void mo12905a(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: b */
        public void mo12904b(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: c */
        public void mo12903c(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
            if (!(abstractRunnableC5460h1 instanceof C5482j1) || C5469i0.this.f12772k == 0) {
                return;
            }
            C5469i0 c5469i0 = C5469i0.this;
            if (c5469i0.f12729D) {
                c5469i0.f12746U = true;
                C5469i0.this.m13480d();
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.i0$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5473d implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5473d() {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: a */
        public void mo12905a(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: b */
        public void mo12904b(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        }

        @Override // com.megvii.lv5.AbstractRunnableC5460h1.InterfaceC5461a
        /* renamed from: c */
        public void mo12903c(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
            if (!(abstractRunnableC5460h1 instanceof C5482j1) || C5469i0.this.f12772k == 0) {
                return;
            }
            C5469i0 c5469i0 = C5469i0.this;
            if (c5469i0.f12729D) {
                c5469i0.f12747V = true;
                C5469i0.this.m13480d();
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.i0$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5474e extends Thread {

        /* renamed from: a */
        public boolean f12795a = false;

        public C5474e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f12795a) {
                try {
                    C5452g1 take = C5469i0.this.mFrameDataQueue.take();
                    byte[] bArr = take.f12686a;
                    if (C5469i0.this.f12772k == -1) {
                        C5469i0 c5469i0 = C5469i0.this;
                        if (c5469i0.f12776m != 0) {
                            C5402d.f12429a = c5469i0.f12768i;
                            String str = c5469i0.f12778n;
                            int i = c5469i0.f12779o;
                            JSONObject jSONObject = null;
                            if (!C5402d.f12432d) {
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("type", "track");
                                    jSONObject2.put("project", C5402d.f12429a);
                                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                                    jSONObject2.put("time", System.currentTimeMillis());
                                    jSONObject2.put("event", "enter_far_mirror");
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("liveness", i);
                                    jSONObject3.put("biz_token", str);
                                    jSONObject3.put("try_times", 0);
                                    int i2 = C5402d.f12431c + 1;
                                    C5402d.f12431c = i2;
                                    jSONObject3.put("index", i2);
                                    jSONObject2.put("properties", jSONObject3);
                                    C5402d.f12430b = "enter_far_mirror";
                                    jSONObject = jSONObject2;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            C5399c3.m13606a(jSONObject);
                        }
                    }
                    int i3 = C5486k.f12831a;
                    int i4 = C5486k.f12832b;
                    C5469i0 c5469i02 = C5469i0.this;
                    C5469i0.m13488a(C5469i0.this, C5469i0.this.getModel().m13156a(bArr, i3, i4, c5469i02.f12766h, c5469i02.f12757c0, c5469i02.f12790z, false, true), take);
                } catch (Throwable unused) {
                    return;
                }
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fe: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r348 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), expected to be less than 15
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* renamed from: a */
    public static void m13488a(com.megvii.lv5.C5469i0 r12, com.megvii.lv5.C5391c r13, com.megvii.lv5.C5452g1 r14) {
        /*
            Method dump skipped, instructions count: 1177
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5469i0.m13488a(com.megvii.lv5.i0, com.megvii.lv5.c, com.megvii.lv5.g1):void");
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12939a() {
        if (getView() != null) {
            getView().mo12981a(false);
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5555b
    /* renamed from: a */
    public void mo12937a(SurfaceTexture surfaceTexture) {
        if (getView() != null) {
            getView().mo12983a(surfaceTexture);
        }
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0525: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:128)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:84)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:82)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:67)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:56)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:41)
        	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:282)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0502: CONST_STRING r0, method: com.megvii.lv5.i0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.plugins.input.dex.DexException: Bad byte
        	at jadx.plugins.input.dex.utils.MUtf8.decode(MUtf8.java:36)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:178)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0525: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.megvii.lv5.i0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    /*  JADX ERROR: Failed to decode insn: 0x052C: UNKNOWN(0xF4EF), method: com.megvii.lv5.i0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x052C: UNKNOWN(0xF4EF)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x070E: UNKNOWN(0x00ED), method: com.megvii.lv5.i0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x070E: UNKNOWN(0x00ED)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0714: CONST_STRING r0, method: com.megvii.lv5.i0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        java.lang.IllegalArgumentException: newPosition > limit: (73662600 > 13931064)
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /* renamed from: a */
    public final void m13489a(com.megvii.lv5.C5391c r37, com.megvii.lv5.C5452g1 r38) {
        /*
            Method dump skipped, instructions count: 2270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5469i0.m13489a(com.megvii.lv5.c, com.megvii.lv5.g1):void");
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12930a(ArrayList<Camera.Size> arrayList) {
    }

    /* renamed from: a */
    public final void m13485a(byte[] bArr, int i, int i2, String str, int i3) {
        if (m13473h()) {
            String str2 = this.f12787w + "/" + str + ".jpg";
            C5628v2.m12958b("recordFinish", "saveImage :" + str2);
            String str3 = "";
            if (i3 == 1) {
                str3 = "blink";
            } else if (i3 == 2) {
                str3 = "open_mouth";
            } else if (i3 == 3) {
                str3 = "shake";
            } else if (i3 == 4) {
                str3 = "nod";
            }
            int i4 = (360 - this.f12766h) % 360;
            byte[] m13241a = C5527o2.m13241a(bArr, i, i2, i4);
            if (i4 == 90 || i4 == 270) {
                C5492l c5492l = this.mCameraManager;
                i = c5492l.f12849d;
                i2 = c5492l.f12848c;
            }
            try {
                new YuvImage(m13241a, 17, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, new FileOutputStream(str2));
                this.f12727B.add(new LivenessFile(str2, "image", str3));
            } catch (FileNotFoundException unused) {
            }
        }
    }

    /* renamed from: a */
    public final boolean m13486a(String str) {
        for (int i = 40; i > 0; i--) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (C5527o2.m13245a(str)) {
                return true;
            }
            Thread.sleep(50L);
        }
        return false;
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: b */
    public void mo12927b() {
        if (getView() != null) {
            getView().mo12981a(false);
        }
    }

    /* renamed from: b */
    public final void m13483b(byte[] bArr) {
        C5475i1 c5475i1;
        if (bArr == null || (c5475i1 = this.f12765g0) == null) {
            return;
        }
        c5475i1.m13457a(new C5452g1(C5527o2.m13241a(bArr, getCameraWidth(), getCameraHeight(), (360 - this.f12766h) % 360)));
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: c */
    public void mo12923c() {
        if (getView() != null) {
            getView().mo12981a(true);
        }
    }

    /* renamed from: c */
    public final void m13481c(byte[] bArr) {
        if (m13472i()) {
            C5621u2<C5452g1> c5621u2 = this.f12726A;
            C5452g1 c5452g1 = new C5452g1(C5527o2.m13241a(bArr, getCameraWidth(), getCameraHeight(), (360 - this.f12766h) % 360));
            if (c5621u2.f13743a.size() < c5621u2.f13744b) {
                c5621u2.f13743a.add(c5452g1);
            }
        }
    }

    /* renamed from: d */
    public final void m13480d() {
        byte[] farMirrorImage;
        synchronized (this.f12741P) {
            String str = "detectFinish: isScreenVideoOK = " + this.f12742Q + ",  isRecordOk = " + this.f12744S + ",  isWBRecordOk = " + this.f12745T + ",  isMoveRecordOk = " + this.f12746U + ",  isAllDistanceRecordOk = " + this.f12747V;
            if ((this.f12729D ? this.f12742Q && this.f12744S && this.f12745T && this.f12746U && this.f12747V : this.f12744S) && getView() != null && getModel() != null && !TextUtils.isEmpty(getView().mo12976e()) && this.f12788x == 0) {
                byte[] m13164a = getModel() == null ? null : getModel().m13164a();
                if (getModel() == null) {
                    farMirrorImage = null;
                } else {
                    MegliveModeImpl model = getModel();
                    model.getClass();
                    synchronized (MegliveModeImpl.class) {
                        farMirrorImage = model.f13283a == 0 ? null : MegLiveDetector.m13440a().getFarMirrorImage(model.f13283a);
                    }
                }
                if (m13473h()) {
                    int size = this.f12727B.size();
                    if (size < 3 && size > 0) {
                        int i = 3 - size;
                        for (int i2 = 0; i2 < i; i2++) {
                            List<LivenessFile> list = this.f12727B;
                            list.add(list.get(0));
                        }
                    }
                    String str2 = this.f12787w + "/imageFar.jpg";
                    C5527o2.m13240a(farMirrorImage, str2);
                    this.f12727B.add(new LivenessFile(str2, "image", ""));
                    String str3 = this.f12787w + "/imagebest.jpg";
                    C5527o2.m13240a(m13164a, str3);
                    LivenessFile livenessFile = new LivenessFile(str3, "image", "");
                    this.f12727B.add(livenessFile);
                    this.f12727B.add(livenessFile);
                }
                if (m13471j()) {
                    this.f12727B.add(new LivenessFile(this.f12783s, "video", ""));
                }
                File file = new File(getView().getContext().getFilesDir(), "livenessFile");
                long currentTimeMillis = System.currentTimeMillis();
                List<LivenessFile> list2 = this.f12727B;
                if (list2 != null && list2.size() > 0) {
                    File m13244a = C5527o2.m13244a("meglive_flash", this.f12727B, file.getAbsolutePath(), "liveness_file.megvii", getView().mo12976e());
                    if (m13244a != null) {
                        this.mMegliveLocalFileInfo.setFilePath(m13244a.getAbsolutePath());
                        for (LivenessFile livenessFile2 : this.f12727B) {
                            if ("image".equals(livenessFile2.getFileType())) {
                                File file2 = new File(livenessFile2.getPath());
                                if (file2.exists()) {
                                    file2.delete();
                                }
                            }
                        }
                        C5527o2.m13247a(new File(this.f12783s));
                        C5628v2.m12958b("RecordFinish", "mLivenessFilePath：" + ((String) null));
                        C5628v2.m12958b("RecordFinish", "加密耗时：" + (System.currentTimeMillis() - currentTimeMillis));
                    }
                }
                if (getView() != null) {
                    getView().mo12987a(this.f12788x);
                }
            }
        }
    }

    /* renamed from: d */
    public final void m13478d(byte[] bArr) {
        int cameraWidth;
        int cameraHeight;
        String str;
        if (bArr == null) {
            return;
        }
        C5475i1 c5475i1 = this.f12759d0;
        if (c5475i1 != null) {
            c5475i1.m13457a(new C5452g1(C5527o2.m13241a(bArr, getCameraWidth(), getCameraHeight(), (360 - this.f12766h) % 360)));
        }
        int i = this.f12769i0 + 1;
        this.f12769i0 = i;
        if (i == 1) {
            cameraWidth = getCameraWidth();
            cameraHeight = getCameraHeight();
            str = "image_1";
        } else {
            int i2 = this.f12785u;
            if (i == i2 / 2) {
                cameraWidth = getCameraWidth();
                cameraHeight = getCameraHeight();
                str = "image_2";
            } else if (i != i2) {
                return;
            } else {
                cameraWidth = getCameraWidth();
                cameraHeight = getCameraHeight();
                str = "image_3";
            }
        }
        m13485a(bArr, cameraWidth, cameraHeight, str, -1);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void detach() {
        try {
            C5665z0.m12886a().m12884b();
            synchronized (this.f12754b) {
                if (getModel() != null) {
                    getModel().m13152d();
                }
            }
            m13461t();
            m13460u();
            m13462s();
            m13477e();
            m13464q();
            cleanFiles(this.f12788x);
            super.detach();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    public void m13477e() {
        if (m13470k()) {
            C5462h2.C5464b.f12721a.m13495a();
        }
    }

    /* renamed from: f */
    public final void m13475f() {
        String[][] strArr = this.f12756c;
        if (strArr != null && strArr.length > 1) {
            if (this.f12760e == strArr.length - 1) {
                this.f12760e = 0;
            }
            int i = this.f12760e;
            f12725n0 = strArr[i];
            this.f12760e = i + 1;
        }
        String[] strArr2 = f12725n0;
        int length = (strArr2 == null || strArr2.length == 0) ? this.f12789y.f13023r1 : strArr2.length;
        this.f12758d = length;
        this.f12785u = (this.f12762f * length) + this.f12789y.f12994i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] m13474g() {
        /*
            r5 = this;
            java.lang.String r0 = r5.f12784t
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L51
            java.lang.String r0 = r5.f12768i
            com.megvii.lv5.C5402d.f12429a = r0
            com.megvii.lv5.d r0 = com.megvii.lv5.C5402d.C5403a.f12436a
            java.lang.String r2 = r5.f12778n
            int r3 = r5.f12779o
            java.lang.String r4 = "whitebalance_exposure_video_detect"
            org.json.JSONObject r2 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r2)
            java.lang.String r2 = r5.f12784t
            boolean r2 = r5.m13486a(r2)
            if (r2 == 0) goto L3f
            java.lang.String r2 = r5.f12768i
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = r5.f12778n
            int r3 = r5.f12779o
            java.lang.String r4 = "whitebalance_exposure_video_detect_pass"
            org.json.JSONObject r0 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r0)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r5.f12784t
            r0.<init>(r2)
            goto L52
        L3f:
            java.lang.String r2 = r5.f12768i
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = r5.f12778n
            int r3 = r5.f12779o
            java.lang.String r4 = "whitebalance_exposure_video_detect_fail"
            org.json.JSONObject r0 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r0)
        L51:
            r0 = r1
        L52:
            if (r0 == 0) goto L5b
            byte[] r1 = com.megvii.lv5.C5527o2.m13234b(r0)
            r0.delete()
        L5b:
            if (r1 != 0) goto L63
            java.lang.String r0 = ""
            byte[] r1 = r0.getBytes()
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5469i0.m13474g():byte[]");
    }

    /* renamed from: h */
    public final boolean m13473h() {
        int i = this.f12786v;
        return (i == 1 || i == 3) && !TextUtils.isEmpty(getView().mo12976e());
    }

    /* renamed from: i */
    public final boolean m13472i() {
        return this.f12748W == 1 && this.f12749X > 0;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void init() {
        super.init();
        File file = new File(getView().getContext().getFilesDir(), "megviiImage");
        if (file.exists()) {
            C5527o2.m13247a(file);
        }
        file.mkdirs();
        this.f12729D = true;
        this.f12787w = file.getAbsolutePath();
        this.mCameraManager.f12854i = this;
        getView().mo12982a(this.mCameraManager, this, this);
        this.f12778n = C5527o2.m13256a(getView().getContext());
        C5515n0 m13223h = C5527o2.m13223h(getView().getContext());
        this.f12789y = m13223h;
        int i = m13223h.f12942O0;
        this.f12748W = i;
        int i2 = m13223h.f12945P0;
        this.f12749X = i2;
        if (i == 1 && i2 > 0) {
            this.f12726A = new C5621u2<>(i2);
        } else if (i == 0) {
            this.f12746U = true;
        }
        C5515n0 c5515n0 = this.f12789y;
        this.f12779o = c5515n0.f12973b;
        this.f12786v = c5515n0.f13017p1;
        this.f12770j = c5515n0.f13009n;
        this.f12756c = c5515n0.f12988g;
        this.f12762f = c5515n0.f12991h;
        this.f12768i = "liveness-sdk";
        m13475f();
        String str = "init: getNearMirrorFaceMinSizeRatio = " + this.f12789y.f12967Y0;
        String str2 = "init: getNearMirrorFaceMaxSizeRatio = " + this.f12789y.f12965X0;
        this.f12730E = C5527o2.m13221j(getView().getContext());
        this.f12731F = C5527o2.m13226e(getView().getContext());
        this.f12732G = this.f12730E ? C5527o2.m13222i(getView().getContext()) : C5527o2.m13227d(getView().getContext());
        this.f12733H = this.f12730E ? C5527o2.m13220k(getView().getContext()) : C5527o2.m13231c(getView().getContext());
        this.f12734I = this.f12730E ? C5527o2.m13218m(getView().getContext()) : C5527o2.m13224g(getView().getContext());
        this.f12735J = this.f12730E ? C5527o2.m13219l(getView().getContext()) : C5527o2.m13225f(getView().getContext());
        MegliveModeImpl model = getModel();
        EnumC5633w enumC5633w = EnumC5633w.MegliveLivenessLiveTypeDistanceFlash;
        byte[] m13254a = C5527o2.m13254a(getView().getContext(), EnumC5556s2.RECT);
        byte[] m13254a2 = C5527o2.m13254a(getView().getContext(), EnumC5556s2.LMK);
        byte[] m13254a3 = C5527o2.m13254a(getView().getContext(), EnumC5556s2.ACTION);
        String mo12980b = getView().mo12980b();
        String m13217n = C5527o2.m13217n(getView().getContext());
        int i3 = this.f12770j;
        long j = this.f12758d;
        long j2 = this.f12762f;
        C5515n0 c5515n02 = this.f12789y;
        model.m13161a(enumC5633w, 0.0d, 0.0d, 0, 0, 0, null, m13254a, m13254a2, m13254a3, mo12980b, m13217n, i3, j, j2, c5515n02.f12994i, c5515n02.f13026s1, c5515n02.f13000k, c5515n02.f12997j, c5515n02.f13003l, c5515n02.f13006m, "", c5515n02.f13012o, !c5515n02.f13020q1, c5515n02.f12933L0, c5515n02.f12936M0, c5515n02.f12967Y0, c5515n02.f12939N0, c5515n02.f13028t0);
        if (this.f12730E || this.f12731F) {
            getModel().m13158a(true);
        } else {
            this.f12745T = true;
        }
        if (!this.f12789y.f13032u1) {
            this.f12743R = true;
        }
        m13467n();
        C5515n0 c5515n03 = this.f12789y;
        int i4 = c5515n03.f13021r;
        int[] m13393a = c5515n03.m13393a();
        if (m13393a == null || m13393a.length <= 0) {
            return;
        }
        getModel().m13162a(i4, m13393a);
    }

    /* renamed from: j */
    public final boolean m13471j() {
        int i = this.f12786v;
        return (i == 1 || i == 2) && !TextUtils.isEmpty(getView().mo12976e());
    }

    /* renamed from: k */
    public final boolean m13470k() {
        int i = this.f12789y.f12952R1;
        if (i != 0) {
            return (i == 2 && getView().getScreenRecordContext() == null) ? false : true;
        }
        return false;
    }

    /* renamed from: l */
    public final void m13469l() {
        this.f12750Y = 0L;
        this.f12755b0 = true;
        this.f12757c0 = false;
        this.f12753a0 = 0;
        m13475f();
    }

    /* renamed from: m */
    public final String m13468m() {
        String[] strArr = f12725n0;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String str = strArr[this.f12753a0];
        this.f12751Z = str;
        String replace = str.replace("0x", "#");
        int i = this.f12753a0;
        if (i < this.f12758d - 1) {
            this.f12753a0 = i + 1;
        }
        return replace;
    }

    /* renamed from: n */
    public final void m13467n() {
        MegliveModeImpl model = getModel();
        this.f12789y.getClass();
        C5515n0 c5515n0 = this.f12789y;
        float f = c5515n0.f12900A0;
        float f2 = c5515n0.f12918G0;
        float f3 = c5515n0.f12921H0;
        c5515n0.getClass();
        C5515n0 c5515n02 = this.f12789y;
        float f4 = c5515n02.f12909D0;
        float f5 = c5515n02.f12912E0;
        float f6 = c5515n02.f12915F0;
        float f7 = c5515n02.f13034v0;
        float f8 = c5515n02.f13031u0;
        float f9 = c5515n02.f13040x0;
        float f10 = c5515n02.f13037w0;
        float f11 = c5515n02.f12906C0;
        float f12 = c5515n02.f12903B0;
        float f13 = c5515n02.f12927J0;
        float f14 = c5515n02.f12924I0;
        c5515n02.getClass();
        C5515n0 c5515n03 = this.f12789y;
        model.m13163a(0.5f, f, f2, f3, 0.5f, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, 0.99f, c5515n03.f13043y0, c5515n03.f13046z0, c5515n03.f12930K0, c5515n03.f13024s, c5515n03.f13030u, c5515n03.f13027t);
    }

    /* renamed from: o */
    public void m13466o() {
        try {
            synchronized (this) {
                if (this.f12764g != null) {
                    return;
                }
                this.f12764g = new C5474e();
                C5492l c5492l = this.mCameraManager;
                this.f12766h = c5492l.f12852g;
                if (!c5492l.m13444b()) {
                    this.f12766h -= 180;
                }
                String str = "startDetect: angle = " + this.f12766h;
                if (this.f12764g != null) {
                    getModel().m13151e();
                    getModel().m13150f();
                    C5474e c5474e = this.f12764g;
                    c5474e.f12795a = true;
                    c5474e.start();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5554a
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr != null) {
            try {
                this.cameraData = bArr;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.mFrameDataQueue.offer(new C5452g1(bArr));
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public boolean openCamera() {
        return super.openCamera();
    }

    /* renamed from: p */
    public final void m13465p() {
        try {
            C5475i1 c5475i1 = new C5475i1(getView().getContext(), "fmp_wb");
            this.f12761e0 = c5475i1;
            if (this.f12780p) {
                AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a = this.f12773k0;
                C5492l c5492l = this.mCameraManager;
                new C5482j1(c5475i1, interfaceC5461a, c5492l.f12849d, c5492l.f12848c);
            }
            this.f12761e0.m13453c();
            this.f12761e0.m13452d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: q */
    public final void m13464q() {
        if (m13471j()) {
            try {
                if (this.f12765g0 != null) {
                    C5628v2.m12958b("DistanceFlashPresenterI", "stopRecording...");
                    this.f12765g0.m13451e();
                    this.f12783s = this.f12765g0.f12797a;
                    this.f12765g0 = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: r */
    public void m13463r() {
        try {
            C5474e c5474e = this.f12764g;
            if (c5474e != null) {
                c5474e.f12795a = false;
                getModel().m13149g();
                this.f12764g.interrupt();
                this.f12764g = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: s */
    public final void m13462s() {
        if (m13472i()) {
            try {
                if (this.f12763f0 != null) {
                    C5628v2.m12958b("DistanceFlashPresenterI", "==stopMoveRecording...");
                    this.f12763f0.m13451e();
                    this.f12782r = this.f12763f0.f12797a;
                    this.f12763f0 = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: t */
    public final void m13461t() {
        try {
            if (this.f12759d0 != null) {
                C5628v2.m12958b("DistanceFlashPresenterI", "liveness stopRecording...");
                this.f12759d0.m13451e();
                this.f12781q = this.f12759d0.f12797a;
                this.f12759d0 = null;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: u */
    public final void m13460u() {
        try {
            if (this.f12761e0 != null) {
                C5628v2.m12958b("DistanceFlashPresenterI", "wb stopRecording...");
                this.f12761e0.m13451e();
                this.f12784t = this.f12761e0.f12797a;
                this.f12761e0 = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5504d
    /* renamed from: a */
    public void mo12929a(byte[] bArr) {
        C5402d.f12429a = this.f12768i;
        C5402d c5402d = C5402d.C5403a.f12436a;
        C5399c3.m13606a(c5402d.m13599a("pass_hd_image", this.f12778n, this.f12779o));
        if (this.f12730E) {
            C5402d.f12429a = this.f12768i;
            C5399c3.m13606a(c5402d.m13599a("enter_whitebalance", this.f12778n, this.f12779o));
            m13465p();
        } else if (this.f12731F) {
            C5402d.f12429a = this.f12768i;
            C5399c3.m13606a(c5402d.m13599a("enter_exposure", this.f12778n, this.f12779o));
            m13465p();
        }
        this.mFrameDataQueueHD.offer(bArr);
        this.f12743R = true;
        m13480d();
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5505e
    /* renamed from: a */
    public void mo13438a(double d) {
        float f = (float) d;
        this.f12790z = f;
        if (f == Float.POSITIVE_INFINITY) {
            try {
                this.f12790z = 0.0f;
            } catch (Throwable th) {
                th.printStackTrace();
                this.f12790z = 0.0f;
            }
        }
        if (this.f12790z < 0.0f) {
            C5402d.f12429a = this.f12768i;
            String str = "fail_ev:" + C5402d.f12435g[1];
            String str2 = this.f12778n;
            int i = this.f12779o;
            JSONObject jSONObject = null;
            if (!C5402d.f12432d || str.contains("fail_detect")) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", str);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i);
                    jSONObject3.put("biz_token", str2);
                    jSONObject3.put("try_times", 0);
                    int i2 = C5402d.f12431c + 1;
                    C5402d.f12431c = i2;
                    jSONObject3.put("index", i2);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = str;
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
        }
        C5628v2.m12959a("onEVCallback", "evLight:" + this.f12790z);
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x016f A[Catch: Exception -> 0x0086, TryCatch #0 {Exception -> 0x0086, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:10:0x0033, B:12:0x003b, B:15:0x0046, B:17:0x004d, B:21:0x0065, B:23:0x006a, B:26:0x0075, B:38:0x0099, B:40:0x00a3, B:41:0x00a7, B:43:0x00bd, B:45:0x00c1, B:48:0x00c6, B:50:0x00cc, B:67:0x0133, B:53:0x00d6, B:55:0x00e6, B:57:0x00ed, B:59:0x00fb, B:61:0x0119, B:63:0x011e, B:66:0x012f, B:68:0x0137, B:71:0x013c, B:73:0x0143, B:75:0x014b, B:77:0x0153, B:79:0x016f, B:81:0x017a, B:87:0x0188, B:89:0x0193, B:91:0x019b, B:93:0x01a6, B:72:0x013f, B:18:0x0056, B:20:0x005d), top: B:97:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0188 A[Catch: Exception -> 0x0086, TRY_ENTER, TryCatch #0 {Exception -> 0x0086, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:10:0x0033, B:12:0x003b, B:15:0x0046, B:17:0x004d, B:21:0x0065, B:23:0x006a, B:26:0x0075, B:38:0x0099, B:40:0x00a3, B:41:0x00a7, B:43:0x00bd, B:45:0x00c1, B:48:0x00c6, B:50:0x00cc, B:67:0x0133, B:53:0x00d6, B:55:0x00e6, B:57:0x00ed, B:59:0x00fb, B:61:0x0119, B:63:0x011e, B:66:0x012f, B:68:0x0137, B:71:0x013c, B:73:0x0143, B:75:0x014b, B:77:0x0153, B:79:0x016f, B:81:0x017a, B:87:0x0188, B:89:0x0193, B:91:0x019b, B:93:0x01a6, B:72:0x013f, B:18:0x0056, B:20:0x005d), top: B:97:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x019b A[Catch: Exception -> 0x0086, TryCatch #0 {Exception -> 0x0086, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:10:0x0033, B:12:0x003b, B:15:0x0046, B:17:0x004d, B:21:0x0065, B:23:0x006a, B:26:0x0075, B:38:0x0099, B:40:0x00a3, B:41:0x00a7, B:43:0x00bd, B:45:0x00c1, B:48:0x00c6, B:50:0x00cc, B:67:0x0133, B:53:0x00d6, B:55:0x00e6, B:57:0x00ed, B:59:0x00fb, B:61:0x0119, B:63:0x011e, B:66:0x012f, B:68:0x0137, B:71:0x013c, B:73:0x0143, B:75:0x014b, B:77:0x0153, B:79:0x016f, B:81:0x017a, B:87:0x0188, B:89:0x0193, B:91:0x019b, B:93:0x01a6, B:72:0x013f, B:18:0x0056, B:20:0x005d), top: B:97:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a4  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] m13490a(int r27) {
        /*
            Method dump skipped, instructions count: 449
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5469i0.m13490a(int):byte[]");
    }
}
