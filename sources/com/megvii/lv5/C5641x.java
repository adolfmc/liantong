package com.megvii.lv5;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.megvii.lv5.AbstractC5500m;
import com.megvii.lv5.AbstractRunnableC5460h1;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.sdk.base.DetectBasePresenter;
import com.megvii.lv5.sdk.detect.MegliveModeImpl;
import com.megvii.lv5.sdk.result.LivenessFile;
import com.megvii.lv5.sdk.screen.service.MediaProjectionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.x */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5641x extends DetectBasePresenter<InterfaceC5551s, MegliveModeImpl> implements AbstractC5500m.InterfaceC5501a, AbstractC5500m.InterfaceC5502b, AbstractC5500m.InterfaceC5504d, C5553s1.InterfaceC5554a, C5553s1.InterfaceC5555b {

    /* renamed from: F */
    public LinkedList<C5452g1> f13811F;

    /* renamed from: I */
    public int f13814I;

    /* renamed from: J */
    public boolean f13815J;

    /* renamed from: K */
    public boolean f13816K;

    /* renamed from: L */
    public int f13817L;

    /* renamed from: M */
    public int f13818M;

    /* renamed from: N */
    public int f13819N;

    /* renamed from: O */
    public int f13820O;

    /* renamed from: P */
    public HandlerThread f13821P;

    /* renamed from: Q */
    public Handler f13822Q;

    /* renamed from: R */
    public boolean f13823R;

    /* renamed from: Y */
    public long f13830Y;

    /* renamed from: h */
    public C5648g f13846h;

    /* renamed from: i */
    public int f13848i;

    /* renamed from: l */
    public C5525o0 f13853l;

    /* renamed from: n */
    public C5475i1 f13855n;

    /* renamed from: o */
    public C5475i1 f13856o;

    /* renamed from: p */
    public C5475i1 f13857p;

    /* renamed from: y */
    public C5515n0 f13866y;

    /* renamed from: a */
    public volatile int f13832a = -1;

    /* renamed from: b */
    public int f13834b = -1;

    /* renamed from: c */
    public int f13836c = -1;

    /* renamed from: d */
    public int f13838d = -1;

    /* renamed from: e */
    public int f13840e = -1;

    /* renamed from: f */
    public int f13842f = -1;

    /* renamed from: g */
    public int f13844g = 10;

    /* renamed from: j */
    public int f13850j = 0;

    /* renamed from: k */
    public long f13852k = 0;

    /* renamed from: m */
    public String f13854m = "";

    /* renamed from: q */
    public boolean f13858q = true;

    /* renamed from: r */
    public String f13859r = "";

    /* renamed from: s */
    public String f13860s = "";

    /* renamed from: t */
    public int f13861t = -1;

    /* renamed from: u */
    public String f13862u = "";

    /* renamed from: v */
    public String f13863v = "";

    /* renamed from: w */
    public int f13864w = 0;

    /* renamed from: x */
    public Object f13865x = new Object();

    /* renamed from: z */
    public int f13867z = 3;

    /* renamed from: A */
    public int f13806A = 10;

    /* renamed from: B */
    public int f13807B = 0;

    /* renamed from: C */
    public int f13808C = 0;

    /* renamed from: D */
    public List<LivenessFile> f13809D = new ArrayList();

    /* renamed from: E */
    public List<LivenessFile> f13810E = new ArrayList();

    /* renamed from: G */
    public byte[] f13812G = null;

    /* renamed from: H */
    public List<Integer> f13813H = new ArrayList();

    /* renamed from: S */
    public C5452g1 f13824S = null;

    /* renamed from: T */
    public int f13825T = 0;

    /* renamed from: U */
    public boolean f13826U = false;

    /* renamed from: V */
    public volatile boolean f13827V = false;

    /* renamed from: W */
    public boolean f13828W = false;

    /* renamed from: X */
    public volatile boolean f13829X = true;

    /* renamed from: Z */
    public final AbstractRunnableC5460h1.InterfaceC5461a f13831Z = new C5645d();

    /* renamed from: a0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f13833a0 = new C5646e();

    /* renamed from: b0 */
    public boolean f13835b0 = false;

    /* renamed from: c0 */
    public boolean f13837c0 = false;

    /* renamed from: d0 */
    public boolean f13839d0 = false;

    /* renamed from: e0 */
    public boolean f13841e0 = false;

    /* renamed from: f0 */
    public boolean f13843f0 = false;

    /* renamed from: g0 */
    public String f13845g0 = null;

    /* renamed from: h0 */
    public boolean f13847h0 = false;

    /* renamed from: i0 */
    public boolean f13849i0 = false;

    /* renamed from: j0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f13851j0 = new C5647f();

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5642a implements InterfaceC5546r0 {

        /* renamed from: a */
        public final /* synthetic */ int f13868a;

        /* renamed from: b */
        public final /* synthetic */ String f13869b;

        /* renamed from: c */
        public final /* synthetic */ int f13870c;

        /* renamed from: d */
        public final /* synthetic */ byte[] f13871d;

        public C5642a(int i, String str, int i2, byte[] bArr) {
            this.f13868a = i;
            this.f13869b = str;
            this.f13870c = i2;
            this.f13871d = bArr;
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12907a(int i, byte[] bArr) {
            String str = "mfxtest autoUploadWhiteBalance onFailure: response=" + new String(bArr);
            if (i >= 0 && i != 500) {
                C5641x.m12934a(C5641x.this, this.f13868a, false);
                return;
            }
            C5641x c5641x = C5641x.this;
            int i2 = c5641x.f13808C;
            c5641x.f13808C = i2 + 1;
            if (i2 < c5641x.f13867z) {
                c5641x.m12924b(this.f13869b, this.f13870c, this.f13871d, this.f13868a);
            } else {
                C5641x.m12934a(c5641x, this.f13868a, false);
            }
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12906a(String str) {
            String str2 = "autoUploadWhiteBalance:  success = " + System.currentTimeMillis();
            String str3 = "mfxtest autoUploadWhiteBalance onSuccess: response=" + str;
            C5641x.m12934a(C5641x.this, this.f13868a, true);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5643b implements InterfaceC5546r0 {

        /* renamed from: a */
        public final /* synthetic */ int f13873a;

        /* renamed from: b */
        public final /* synthetic */ int f13874b;

        /* renamed from: c */
        public final /* synthetic */ String f13875c;

        /* renamed from: d */
        public final /* synthetic */ byte[] f13876d;

        public C5643b(int i, int i2, String str, byte[] bArr) {
            this.f13873a = i;
            this.f13874b = i2;
            this.f13875c = str;
            this.f13876d = bArr;
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12907a(int i, byte[] bArr) {
            int i2;
            String str = "mfxtest autoUpload onFailure: statusCode=" + i;
            String str2 = "mfxtest autoUpload onFailure: response=" + new String(bArr);
            if (i >= 0 && i != 500) {
                C5641x.m12926b(C5641x.this, this.f13873a, false);
                return;
            }
            C5641x c5641x = C5641x.this;
            int i3 = c5641x.f13807B;
            c5641x.f13807B = i3 + 1;
            if (i3 >= c5641x.f13867z || (i2 = this.f13874b) != 1) {
                C5641x.m12926b(c5641x, this.f13873a, false);
            } else {
                c5641x.m12931a(this.f13875c, i2, this.f13876d, this.f13873a);
            }
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12906a(String str) {
            String str2 = "mfxtest autoUpload onSuccess: response=" + str;
            C5641x.m12926b(C5641x.this, this.f13873a, true);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5644c implements Runnable {
        public RunnableC5644c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C5641x.this.f13857p.m13451e();
            C5641x c5641x = C5641x.this;
            c5641x.f13863v = c5641x.f13857p.f12797a;
            c5641x.f13857p = null;
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5645d implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5645d() {
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
                if (C5641x.this.f13832a == 14 || C5641x.this.f13832a == 17) {
                    C5641x.this.f13841e0 = true;
                    C5641x.this.m12921d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5646e implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5646e() {
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
                if (C5641x.this.f13832a == 14 || C5641x.this.f13832a == 17) {
                    C5641x.this.f13839d0 = true;
                    C5641x.this.m12921d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5647f implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5647f() {
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
                if (C5641x.this.getView() != null) {
                    C5641x c5641x = C5641x.this;
                    C5402d.f12429a = c5641x.f13854m;
                    C5525o0 c5525o0 = c5641x.f13853l;
                    String str = c5525o0.f13093b;
                    int i = c5525o0.f13092a;
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
                            jSONObject3.put("biz_token", str);
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
                    C5641x.this.getView().mo13133b(1);
                }
                C5641x c5641x2 = C5641x.this;
                if (c5641x2.f13823R) {
                    c5641x2.f13843f0 = true;
                    C5641x.this.m12921d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x$g */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5648g extends Thread {

        /* renamed from: a */
        public boolean f13882a = false;

        public C5648g() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f13882a) {
                try {
                    C5452g1 take = C5641x.this.mFrameDataQueue.take();
                    byte[] bArr = take.f12686a;
                    if (C5641x.this.f13832a == -1) {
                        C5641x c5641x = C5641x.this;
                        if (c5641x.f13836c != 17) {
                            C5402d.f12429a = c5641x.f13854m;
                            C5525o0 c5525o0 = c5641x.f13853l;
                            String str = c5525o0.f13093b;
                            int i = c5525o0.f13092a;
                            JSONObject jSONObject = null;
                            if (!C5402d.f12432d) {
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("type", "track");
                                    jSONObject2.put("project", C5402d.f12429a);
                                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                                    jSONObject2.put("time", System.currentTimeMillis());
                                    jSONObject2.put("event", "enter_mirror");
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("liveness", i);
                                    jSONObject3.put("biz_token", str);
                                    jSONObject3.put("try_times", 0);
                                    int i2 = C5402d.f12431c + 1;
                                    C5402d.f12431c = i2;
                                    jSONObject3.put("index", i2);
                                    jSONObject2.put("properties", jSONObject3);
                                    C5402d.f12430b = "enter_mirror";
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
                    C5641x c5641x2 = C5641x.this;
                    C5391c m13156a = C5641x.this.getModel().m13156a(bArr, i3, i4, c5641x2.f13848i, false, 0.0f, false, C5641x.m12935a(c5641x2));
                    m13156a.toString();
                    C5651x2.m12902a(m13156a.toString());
                    C5641x.this.m12936a(m13156a, take);
                } catch (Throwable th) {
                    C5651x2.m12902a(th.toString());
                    th.toString();
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public static void m12934a(C5641x c5641x, int i, boolean z) {
        synchronized (c5641x) {
            c5641x.f13849i0 = true;
            if (c5641x.f13847h0 && c5641x.getView() != null) {
                c5641x.getView().mo13130b(z);
            }
        }
    }

    /* renamed from: b */
    public static void m12926b(C5641x c5641x, int i, boolean z) {
        c5641x.getClass();
        if (i != 0 || c5641x.getView() == null) {
            return;
        }
        synchronized (c5641x) {
            c5641x.f13847h0 = true;
            if (c5641x.f13849i0) {
                c5641x.getView().mo13130b(z);
            }
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12939a() {
        if (getView() != null) {
            getView().mo13135a(false);
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5555b
    /* renamed from: a */
    public void mo12937a(SurfaceTexture surfaceTexture) {
        if (getView() != null) {
            getView().mo13141a(surfaceTexture);
        }
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0575: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
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
    /*  JADX ERROR: Failed to decode insn: 0x0552: CONST_STRING r0, method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0575: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
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
    /*  JADX ERROR: Failed to decode insn: 0x06FA: CONST_STRING r0, method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        java.lang.IllegalArgumentException: newPosition > limit: (49021056 > 13931064)
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
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x070F: UNKNOWN(0xD042), method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x070F: UNKNOWN(0xD042)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x07A2: UNKNOWN(0xF9EC), method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x07A2: UNKNOWN(0xF9EC)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0954: UNKNOWN(0xF9F6), method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0954: UNKNOWN(0xF9F6)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0A0C: CONST_STRING r0, method: com.megvii.lv5.x.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        java.lang.IllegalArgumentException: newPosition < 0: (-1052245904 < 0)
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
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /* renamed from: a */
    public final void m12936a(com.megvii.lv5.C5391c r46, com.megvii.lv5.C5452g1 r47) {
        /*
            Method dump skipped, instructions count: 2600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5641x.m12936a(com.megvii.lv5.c, com.megvii.lv5.g1):void");
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12930a(ArrayList<Camera.Size> arrayList) {
    }

    /* renamed from: a */
    public final void m12928a(byte[] bArr, int i, int i2, String str, int i3) {
        if (m12917g()) {
            String str2 = this.f13862u + "/" + str + ".jpg";
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
            int i4 = (360 - this.f13848i) % 360;
            byte[] m13241a = C5527o2.m13241a(bArr, i, i2, i4);
            if (i4 == 90 || i4 == 270) {
                C5492l c5492l = this.mCameraManager;
                i = c5492l.f12849d;
                i2 = c5492l.f12848c;
            }
            try {
                new YuvImage(m13241a, 17, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, new FileOutputStream(str2));
                this.f13809D.add(new LivenessFile(str2, "image", str3));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final boolean m12932a(String str) {
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
            getView().mo13135a(false);
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: c */
    public void mo12923c() {
        if (getView() != null) {
            getView().mo13135a(true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0029, code lost:
        if (r6.f13837c0 != false) goto L16;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void m12921d() {
        /*
            Method dump skipped, instructions count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5641x.m12921d():void");
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void detach() {
        try {
            C5665z0.m12886a().m12884b();
            synchronized (this.f13865x) {
                if (getModel() != null) {
                    getModel().m13152d();
                }
            }
            m12910n();
            m12909o();
            m12919e();
            m12908p();
            cleanFiles(this.f13861t);
            super.detach();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    public final boolean m12919e() {
        C5651x2.m12902a("doStopScreenRecord");
        if (m12915i()) {
            try {
                C5462h2 c5462h2 = C5462h2.C5464b.f12721a;
                WeakReference<MediaProjectionService> weakReference = c5462h2.f12718e;
                if ((weakReference == null ? null : weakReference.get()) != null) {
                    c5462h2.m13495a();
                    C5651x2.m12902a("StopScreenRecord success");
                    return true;
                }
            } catch (Exception e) {
                C5651x2.m12902a(e.toString());
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] m12918f() {
        /*
            r5 = this;
            java.lang.String r0 = r5.f13845g0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L57
            java.lang.String r0 = r5.f13854m
            com.megvii.lv5.C5402d.f12429a = r0
            com.megvii.lv5.d r0 = com.megvii.lv5.C5402d.C5403a.f12436a
            java.lang.String r2 = "whitebalance_exposure_video_detect"
            com.megvii.lv5.o0 r3 = r5.f13853l
            java.lang.String r4 = r3.f13093b
            int r3 = r3.f13092a
            org.json.JSONObject r2 = r0.m13599a(r2, r4, r3)
            com.megvii.lv5.C5399c3.m13606a(r2)
            java.lang.String r2 = r5.f13845g0
            boolean r2 = r5.m12932a(r2)
            if (r2 == 0) goto L43
            java.lang.String r2 = r5.f13854m
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = "whitebalance_exposure_video_detect_pass"
            com.megvii.lv5.o0 r3 = r5.f13853l
            java.lang.String r4 = r3.f13093b
            int r3 = r3.f13092a
            org.json.JSONObject r0 = r0.m13599a(r2, r4, r3)
            com.megvii.lv5.C5399c3.m13606a(r0)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r5.f13845g0
            r0.<init>(r2)
            goto L58
        L43:
            java.lang.String r2 = r5.f13854m
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = "whitebalance_exposure_video_detect_fail"
            com.megvii.lv5.o0 r3 = r5.f13853l
            java.lang.String r4 = r3.f13093b
            int r3 = r3.f13092a
            org.json.JSONObject r0 = r0.m13599a(r2, r4, r3)
            com.megvii.lv5.C5399c3.m13606a(r0)
        L57:
            r0 = r1
        L58:
            if (r0 == 0) goto L61
            byte[] r1 = com.megvii.lv5.C5527o2.m13234b(r0)
            r0.delete()
        L61:
            if (r1 != 0) goto L69
            java.lang.String r0 = ""
            byte[] r1 = r0.getBytes()
        L69:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5641x.m12918f():byte[]");
    }

    /* renamed from: g */
    public final boolean m12917g() {
        int i = this.f13864w;
        return (i == 1 || i == 3) && !TextUtils.isEmpty(getView().mo13123j());
    }

    /* renamed from: h */
    public final boolean m12916h() {
        int i = this.f13864w;
        return (i == 1 || i == 2) && !TextUtils.isEmpty(getView().mo13123j());
    }

    /* renamed from: i */
    public final boolean m12915i() {
        int i = this.f13866y.f12952R1;
        if (i != 0) {
            return (i == 2 && getView().getScreenRecordContext() == null) ? false : true;
        }
        return false;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void init() {
        File file;
        File file2;
        super.init();
        Context context = getView().getContext();
        if (C5651x2.f13901f && (C5651x2.f13896a == null || C5651x2.f13897b == null || (file2 = C5651x2.f13898c) == null || !file2.exists())) {
            C5651x2.f13896a = context;
            C5651x2.f13897b = new C5651x2();
            if (Environment.getExternalStorageState().equals("mounted")) {
                file = new File(C5651x2.f13896a.getExternalFilesDir("Log").getPath() + "/");
            } else {
                file = new File(C5651x2.f13896a.getFilesDir().getPath() + "/Log/");
            }
            if (!file.exists()) {
                file.mkdir();
            }
            File file3 = new File(file.getPath() + "/logs.txt");
            if (!file3.exists()) {
                try {
                    file3.createNewFile();
                } catch (Exception e) {
                    String str = "Create log file failure !!! " + e.toString();
                }
            }
            C5651x2.f13898c = file3;
            String str2 = "LogFilePath is: " + C5651x2.f13898c.getPath();
            File file4 = C5651x2.f13898c;
            long j = 0;
            if (file4.exists()) {
                try {
                    j = new FileInputStream(file4).available();
                } catch (Exception e2) {
                    e2.toString();
                }
            }
            String str3 = "Log max size is: " + Formatter.formatFileSize(context, 10485760L);
            String str4 = "log now size is: " + Formatter.formatFileSize(context, j);
            if (10485760 < j) {
                File file5 = new File(C5651x2.f13898c.getParent() + "/lastLog.txt");
                if (file5.exists()) {
                    file5.delete();
                }
                C5651x2.f13898c.renameTo(file5);
                try {
                    C5651x2.f13898c.createNewFile();
                } catch (Exception e3) {
                    String str5 = "Create log file failure !!! " + e3.toString();
                }
            }
        }
        C5651x2.m12902a("*******************************************************");
        C5537q.f13180g.m13196f(0);
        this.mCameraManager.f12854i = this;
        getView().mo13140a(this.mCameraManager, this, this);
        File file6 = new File(getView().getContext().getFilesDir(), "megviiImage");
        if (file6.exists()) {
            C5527o2.m13247a(file6);
        }
        file6.mkdirs();
        HandlerThread handlerThread = new HandlerThread("jd-video");
        this.f13821P = handlerThread;
        handlerThread.start();
        this.f13822Q = new Handler(this.f13821P.getLooper());
        C5515n0 m13223h = C5527o2.m13223h(getView().getContext());
        this.f13866y = m13223h;
        this.f13867z = m13223h.f13041x1;
        this.f13806A = m13223h.f12979d;
        this.f13862u = file6.getAbsolutePath();
        C5525o0 mo13122k = getView().mo13122k();
        this.f13853l = mo13122k;
        this.f13864w = mo13122k.f13103l;
        this.f13811F = new C5615t2(this.f13806A);
        String str6 = "init: getLivenessVideo = " + this.f13864w;
        String str7 = "init: VideoEncryptKey = " + getView().mo13123j();
        C5525o0 c5525o0 = this.f13853l;
        this.f13844g = c5525o0.f13094c;
        this.f13854m = c5525o0.f13101j == 1 ? "liveness-sdk" : "FaceIDZFAC";
        this.f13815J = C5527o2.m13221j(getView().getContext());
        this.f13816K = C5527o2.m13226e(getView().getContext());
        this.f13817L = this.f13815J ? C5527o2.m13222i(getView().getContext()) : C5527o2.m13227d(getView().getContext());
        this.f13818M = this.f13815J ? C5527o2.m13220k(getView().getContext()) : C5527o2.m13231c(getView().getContext());
        this.f13819N = this.f13815J ? C5527o2.m13218m(getView().getContext()) : C5527o2.m13224g(getView().getContext());
        this.f13820O = this.f13815J ? C5527o2.m13219l(getView().getContext()) : C5527o2.m13225f(getView().getContext());
        MegliveModeImpl model = getModel();
        EnumC5633w enumC5633w = EnumC5633w.MegliveLivenessLiveTypeAction;
        double d = this.f13806A;
        int i = this.f13866y.f12943O1;
        int i2 = this.f13844g;
        C5525o0 c5525o02 = this.f13853l;
        model.m13161a(enumC5633w, 0.0d, d, i, i2, c5525o02.f13095d, c5525o02.f13096e, c5525o02.f13097f, c5525o02.f13098g, c5525o02.f13099h, getView().mo13134b(), C5527o2.m13217n(getView().getContext()), 0, 0L, 0L, 0L, 0L, 0.0f, null, 0.0f, 0.0f, null, 0, false, false, 0, 0.0f, 0, this.f13866y.f13028t0);
        m12914j();
        C5506m0 c5506m0 = this.f13853l.f13106o;
        getModel().m13157a(c5506m0.f12877a, c5506m0.f12878b, c5506m0.f12879c, c5506m0.f12880d, c5506m0.f12881e, c5506m0.f12882f, c5506m0.f12883g, c5506m0.f12884h);
        if (this.f13815J || this.f13816K) {
            getModel().m13158a(true);
        } else {
            this.f13849i0 = true;
            this.f13843f0 = true;
        }
        C5515n0 c5515n0 = this.f13866y;
        if (!c5515n0.f13032u1) {
            this.f13837c0 = true;
        }
        int i3 = c5515n0.f12940N1;
        this.f13814I = i3;
        if (i3 == 1) {
            this.f13841e0 = true;
        }
        this.f13850j = this.f13853l.f13100i;
        String str8 = "init: mVerticalCheckType = " + this.f13850j;
        this.f13823R = true;
    }

    /* renamed from: j */
    public final void m12914j() {
        C5531p0 c5531p0 = this.f13853l.f13104m;
        MegliveModeImpl model = getModel();
        c5531p0.getClass();
        float f = c5531p0.f13151a;
        float f2 = c5531p0.f13152b;
        float f3 = c5531p0.f13153c;
        float f4 = c5531p0.f13154d;
        float f5 = c5531p0.f13155e;
        float f6 = c5531p0.f13156f;
        float f7 = c5531p0.f13157g;
        float f8 = c5531p0.f13158h;
        float f9 = c5531p0.f13159i;
        float f10 = c5531p0.f13160j;
        float f11 = c5531p0.f13161k;
        float f12 = c5531p0.f13162l;
        float f13 = c5531p0.f13163m;
        float f14 = c5531p0.f13164n;
        float f15 = c5531p0.f13165o;
        int i = c5531p0.f13166p;
        C5515n0 c5515n0 = this.f13866y;
        model.m13163a(0.5f, f, f2, f3, 0.5f, f4, f5, 0.15f, f6, f7, f8, f9, f10, f11, f12, f13, 0.99f, f14, f15, i, c5515n0.f13024s, c5515n0.f13030u, c5515n0.f13027t);
    }

    /* renamed from: k */
    public void m12913k() {
        try {
            C5628v2.m12958b("ActionDetect", "startDetect...");
            this.f13846h = new C5648g();
            System.currentTimeMillis();
            this.f13852k = System.currentTimeMillis();
            C5492l c5492l = this.mCameraManager;
            this.f13848i = c5492l.f12852g;
            c5492l.f12846a.mo13439a((AbstractC5500m.InterfaceC5501a) this);
            if (!this.mCameraManager.m13444b()) {
                this.f13848i -= 180;
            }
            if (this.f13846h != null) {
                getModel().m13151e();
                getModel().m13150f();
                C5648g c5648g = this.f13846h;
                c5648g.f13882a = true;
                c5648g.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: l */
    public final void m12912l() {
        try {
            C5475i1 c5475i1 = new C5475i1(getView().getContext(), "action_wb");
            this.f13855n = c5475i1;
            if (this.f13858q) {
                AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a = this.f13851j0;
                C5492l c5492l = this.mCameraManager;
                new C5482j1(c5475i1, interfaceC5461a, c5492l.f12849d, c5492l.f12848c);
            }
            this.f13855n.m13453c();
            this.f13855n.m13452d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: m */
    public void m12911m() {
        try {
            if (this.f13846h != null) {
                C5628v2.m12958b("ActionDetect", "stopDetect...");
                C5648g c5648g = this.f13846h;
                c5648g.f13882a = false;
                c5648g.interrupt();
                this.f13846h = null;
                getModel().m13149g();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: n */
    public final boolean m12910n() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.f13814I != 1 && m12916h()) {
            if (this.f13857p == null) {
                return false;
            }
            C5628v2.m12958b("ActionDetect", "stopJDRecording...");
            this.f13822Q.post(new RunnableC5644c());
            return false;
        }
        return true;
    }

    /* renamed from: o */
    public final boolean m12909o() {
        try {
            if (this.f13856o != null) {
                C5628v2.m12958b("ActionDetect", "stopRecording...");
                this.f13856o.m13451e();
                this.f13859r = this.f13856o.f12797a;
                this.f13856o = null;
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5554a
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr != null) {
            this.cameraData = bArr;
        }
        C5452g1 c5452g1 = new C5452g1(this.cameraData);
        this.f13824S = c5452g1;
        this.mFrameDataQueue.offer(c5452g1);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public boolean openCamera() {
        return super.openCamera();
    }

    /* renamed from: p */
    public final void m12908p() {
        try {
            if (this.f13855n != null) {
                C5628v2.m12958b("ActionDetect", "stopRecording...");
                this.f13855n.m13451e();
                this.f13845g0 = this.f13855n.f12797a;
                this.f13855n = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m12935a(C5641x c5641x) {
        if (c5641x.f13832a != 3) {
            return true;
        }
        int i = c5641x.f13850j;
        if (i != 1 && (i != 2 || System.currentTimeMillis() - c5641x.f13852k > 2000)) {
            return true;
        }
        return C5665z0.m12886a().m12883b(c5641x.getView().getContext());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* renamed from: b */
    public void m12924b(String str, int i, byte[] bArr, int i2) {
        String str2 = "mfxtest autoUploadWhiteBalance: delta len =" + bArr.length;
        ?? r0 = C5527o2.m13238b((Context) null);
        if (i2 != 0) {
            r0 = -1;
        }
        int i3 = r0;
        String str3 = "autoUploadWhiteBalance:  start = " + System.currentTimeMillis();
        C5658y0.m12893a().m12892a(null, str, this.f13853l.f13093b, i, bArr, i3, new C5642a(i2, str, i, bArr));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* renamed from: a */
    public void m12931a(String str, int i, byte[] bArr, int i2) {
        String str2 = "mfxtest autoUpload: detectResult=" + i2;
        String str3 = "mfxtest autoUpload: delta len =" + bArr.length;
        ?? r0 = C5527o2.m13238b((Context) null);
        if (i2 != 0) {
            r0 = -1;
        }
        int i3 = r0;
        String str4 = "mfxtest autoUpload: creditAgree =" + i3;
        C5658y0.m12893a().m12892a(null, str, this.f13853l.f13093b, i, bArr, i3, new C5643b(i2, i, str, bArr));
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5504d
    /* renamed from: a */
    public void mo12929a(byte[] bArr) {
        String str;
        C5525o0 c5525o0;
        String str2;
        C5402d.f12429a = this.f13854m;
        C5402d c5402d = C5402d.C5403a.f12436a;
        C5525o0 c5525o02 = this.f13853l;
        C5399c3.m13606a(c5402d.m13599a("pass_hd_image", c5525o02.f13093b, c5525o02.f13092a));
        if (this.f13815J) {
            C5402d.f12429a = this.f13854m;
            str = "enter_whitebalance";
            c5525o0 = this.f13853l;
            str2 = c5525o0.f13093b;
        } else {
            if (this.f13816K) {
                C5402d.f12429a = this.f13854m;
                str = "enter_exposure";
                c5525o0 = this.f13853l;
                str2 = c5525o0.f13093b;
            }
            this.mFrameDataQueueHD.offer(bArr);
            this.f13837c0 = true;
            m12921d();
        }
        C5399c3.m13606a(c5402d.m13599a(str, str2, c5525o0.f13092a));
        m12912l();
        this.mFrameDataQueueHD.offer(bArr);
        this.f13837c0 = true;
        m12921d();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003c A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e9 A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f8 A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011c A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0139 A[Catch: all -> 0x015c, TRY_ENTER, TryCatch #0 {all -> 0x015c, blocks: (B:4:0x0009, B:6:0x0011, B:14:0x002d, B:15:0x0032, B:17:0x003c, B:18:0x0040, B:20:0x005d, B:22:0x0061, B:25:0x0066, B:27:0x006c, B:44:0x00d5, B:30:0x0078, B:32:0x0088, B:34:0x008f, B:36:0x009d, B:38:0x00bb, B:40:0x00c0, B:43:0x00d1, B:45:0x00d9, B:48:0x00de, B:52:0x00e9, B:54:0x00f2, B:56:0x00f8, B:58:0x0100, B:60:0x011c, B:62:0x0127, B:68:0x0139, B:69:0x013f, B:49:0x00e1), top: B:73:0x0009, inners: #1 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] m12938a(int r28) {
        /*
            Method dump skipped, instructions count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5641x.m12938a(int):byte[]");
    }
}
