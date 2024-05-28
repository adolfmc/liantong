package com.megvii.lv5;

import android.content.Context;
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
import com.megvii.lv5.lib.jni.MegDelta;
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
/* renamed from: com.megvii.lv5.g0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5446g0 extends DetectBasePresenter<InterfaceC5625v, MegliveModeImpl> implements AbstractC5500m.InterfaceC5502b, AbstractC5500m.InterfaceC5504d, AbstractC5500m.InterfaceC5505e, C5553s1.InterfaceC5554a, C5553s1.InterfaceC5555b {

    /* renamed from: j0 */
    public static String[] f12609j0;

    /* renamed from: A */
    public C5515n0 f12610A;

    /* renamed from: C */
    public boolean f12612C;

    /* renamed from: D */
    public boolean f12613D;

    /* renamed from: E */
    public boolean f12614E;

    /* renamed from: F */
    public int f12615F;

    /* renamed from: G */
    public int f12616G;

    /* renamed from: H */
    public int f12617H;

    /* renamed from: I */
    public int f12618I;

    /* renamed from: J */
    public C5475i1 f12619J;

    /* renamed from: K */
    public C5475i1 f12620K;

    /* renamed from: V */
    public long f12631V;

    /* renamed from: c */
    public String[][] f12640c;

    /* renamed from: g */
    public C5451e f12648g;

    /* renamed from: i */
    public int f12652i;

    /* renamed from: q */
    public int f12661q;

    /* renamed from: w */
    public int f12667w;

    /* renamed from: a */
    public int f12636a = -1;

    /* renamed from: b */
    public Object f12638b = new Object();

    /* renamed from: d */
    public int f12642d = 10;

    /* renamed from: e */
    public int f12644e = 0;

    /* renamed from: f */
    public int f12646f = 4;

    /* renamed from: h */
    public long f12650h = 0;

    /* renamed from: j */
    public String f12654j = "";

    /* renamed from: k */
    public int f12655k = 0;

    /* renamed from: l */
    public int f12656l = 10;

    /* renamed from: m */
    public volatile int f12657m = -1;

    /* renamed from: n */
    public int f12658n = -1;

    /* renamed from: o */
    public int f12659o = -1;

    /* renamed from: p */
    public String f12660p = "";

    /* renamed from: r */
    public int f12662r = 3;

    /* renamed from: s */
    public int f12663s = 0;

    /* renamed from: t */
    public int f12664t = 0;

    /* renamed from: u */
    public boolean f12665u = true;

    /* renamed from: v */
    public String f12666v = "";

    /* renamed from: x */
    public int f12668x = 0;

    /* renamed from: y */
    public String f12669y = "";

    /* renamed from: z */
    public int f12670z = -1;

    /* renamed from: B */
    public float f12611B = 0.0f;

    /* renamed from: L */
    public String f12621L = "";

    /* renamed from: M */
    public long f12622M = 0;

    /* renamed from: N */
    public String f12623N = "";

    /* renamed from: O */
    public int f12624O = 0;

    /* renamed from: P */
    public boolean f12625P = true;

    /* renamed from: Q */
    public boolean f12626Q = false;

    /* renamed from: R */
    public int f12627R = 0;

    /* renamed from: S */
    public boolean f12628S = false;

    /* renamed from: T */
    public volatile boolean f12629T = false;

    /* renamed from: U */
    public volatile boolean f12630U = true;

    /* renamed from: W */
    public int f12632W = 0;

    /* renamed from: X */
    public List<LivenessFile> f12633X = new ArrayList();

    /* renamed from: Y */
    public List<LivenessFile> f12634Y = new ArrayList();

    /* renamed from: Z */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12635Z = new C5449c();

    /* renamed from: a0 */
    public Object f12637a0 = new Object();

    /* renamed from: b0 */
    public boolean f12639b0 = false;

    /* renamed from: c0 */
    public boolean f12641c0 = false;

    /* renamed from: d0 */
    public boolean f12643d0 = false;

    /* renamed from: e0 */
    public boolean f12645e0 = false;

    /* renamed from: f0 */
    public String f12647f0 = null;

    /* renamed from: g0 */
    public boolean f12649g0 = false;

    /* renamed from: h0 */
    public boolean f12651h0 = false;

    /* renamed from: i0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12653i0 = new C5450d();

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.g0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5447a implements InterfaceC5546r0 {

        /* renamed from: a */
        public final /* synthetic */ int f12671a;

        /* renamed from: b */
        public final /* synthetic */ String f12672b;

        /* renamed from: c */
        public final /* synthetic */ int f12673c;

        /* renamed from: d */
        public final /* synthetic */ byte[] f12674d;

        public C5447a(int i, String str, int i2, byte[] bArr) {
            this.f12671a = i;
            this.f12672b = str;
            this.f12673c = i2;
            this.f12674d = bArr;
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12907a(int i, byte[] bArr) {
            String str = "mfxtest autoUploadWhiteBalance onFailure: response=" + new String(bArr);
            if (i >= 0 && i != 500) {
                C5446g0.m13526a(C5446g0.this, this.f12671a, false);
                return;
            }
            C5446g0 c5446g0 = C5446g0.this;
            int i2 = c5446g0.f12664t;
            c5446g0.f12664t = i2 + 1;
            if (i2 < c5446g0.f12662r) {
                c5446g0.m13517b(this.f12672b, this.f12673c, this.f12674d, this.f12671a);
            } else {
                C5446g0.m13526a(c5446g0, this.f12671a, false);
            }
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12906a(String str) {
            String str2 = "autoUploadWhiteBalance:  success = " + System.currentTimeMillis();
            String str3 = "mfxtest autoUploadWhiteBalance onSuccess: response=" + str;
            C5446g0.m13526a(C5446g0.this, this.f12671a, true);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.g0$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5448b implements InterfaceC5546r0 {

        /* renamed from: a */
        public final /* synthetic */ int f12676a;

        /* renamed from: b */
        public final /* synthetic */ int f12677b;

        /* renamed from: c */
        public final /* synthetic */ String f12678c;

        /* renamed from: d */
        public final /* synthetic */ byte[] f12679d;

        public C5448b(int i, int i2, String str, byte[] bArr) {
            this.f12676a = i;
            this.f12677b = i2;
            this.f12678c = str;
            this.f12679d = bArr;
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12907a(int i, byte[] bArr) {
            int i2;
            String str = "mfxtest autoUpload onFailure: response=" + new String(bArr);
            if (i >= 0 && i != 500) {
                C5446g0.m13519b(C5446g0.this, this.f12676a, false);
                return;
            }
            C5446g0 c5446g0 = C5446g0.this;
            int i3 = c5446g0.f12663s;
            c5446g0.f12663s = i3 + 1;
            if (i3 >= c5446g0.f12662r || (i2 = this.f12677b) != 1) {
                C5446g0.m13519b(c5446g0, this.f12676a, false);
            } else {
                c5446g0.m13522a(this.f12678c, i2, this.f12679d, this.f12676a);
            }
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12906a(String str) {
            String str2 = "autoUpload: live success = " + System.currentTimeMillis();
            String str3 = "mfxtest autoUpload onSuccess: response=" + str;
            C5446g0.m13519b(C5446g0.this, this.f12676a, true);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.g0$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5449c implements AbstractRunnableC5460h1.InterfaceC5461a {

        /* renamed from: a */
        public long f12681a = System.currentTimeMillis();

        public C5449c() {
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
            int size;
            JSONObject jSONObject;
            if (abstractRunnableC5460h1 instanceof C5482j1) {
                if (C5446g0.this.f12657m == 14 || C5446g0.this.f12657m == 17) {
                    C5446g0 c5446g0 = C5446g0.this;
                    if (c5446g0.f12613D || c5446g0.f12614E ? c5446g0.f12657m == 17 && C5446g0.this.f12670z == 0 : c5446g0.f12657m == 14 && C5446g0.this.f12670z == 0) {
                        C5446g0 c5446g02 = C5446g0.this;
                        C5402d.f12429a = c5446g02.f12654j;
                        String str = c5446g02.f12660p;
                        int i = c5446g02.f12661q;
                        if (!C5402d.f12432d) {
                            try {
                                jSONObject = new JSONObject();
                                jSONObject.put("type", "track");
                                jSONObject.put("project", C5402d.f12429a);
                                jSONObject.put("event_id", UUID.randomUUID().toString());
                                jSONObject.put("time", System.currentTimeMillis());
                                jSONObject.put("event", "video_generate");
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("liveness", i);
                                jSONObject2.put("biz_token", str);
                                jSONObject2.put("try_times", 0);
                                int i2 = C5402d.f12431c + 1;
                                C5402d.f12431c = i2;
                                jSONObject2.put("index", i2);
                                jSONObject.put("properties", jSONObject2);
                                C5402d.f12430b = "video_generate";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            C5399c3.m13606a(jSONObject);
                        }
                        jSONObject = null;
                        C5399c3.m13606a(jSONObject);
                    }
                    if (C5446g0.this.getView() != null && !TextUtils.isEmpty(C5446g0.this.getView().mo12961e())) {
                        this.f12681a = System.currentTimeMillis();
                        if (C5446g0.this.m13510h() && (size = C5446g0.this.f12633X.size()) < 3 && size > 0) {
                            int i3 = 3 - size;
                            for (int i4 = 0; i4 < i3; i4++) {
                                List<LivenessFile> list = C5446g0.this.f12633X;
                                list.add(list.get(0));
                            }
                        }
                        if (C5446g0.m13520b(C5446g0.this)) {
                            C5446g0 c5446g03 = C5446g0.this;
                            c5446g03.f12633X.add(new LivenessFile(c5446g03.f12666v, "video", ""));
                        }
                        if (C5446g0.this.m13510h() && C5446g0.this.getModel() != null) {
                            String str2 = C5446g0.this.f12669y + "/imagebest.jpg";
                            C5527o2.m13240a(C5446g0.this.getModel().m13164a(), str2);
                            C5446g0.this.f12633X.add(new LivenessFile(str2, "image", ""));
                        }
                        File file = new File(C5446g0.this.getView().getContext().getFilesDir(), "livenessFile");
                        long currentTimeMillis = System.currentTimeMillis();
                        String str3 = "";
                        C5446g0 c5446g04 = C5446g0.this;
                        int i5 = c5446g04.f12661q;
                        if (i5 == 1) {
                            str3 = "still";
                        } else if (i5 == 2) {
                            str3 = "meglive";
                        } else if (i5 == 3) {
                            str3 = "flash";
                        }
                        List<LivenessFile> list2 = c5446g04.f12633X;
                        if (list2 != null && list2.size() > 0) {
                            C5446g0.this.mMegliveLocalFileInfo.setFilePath(C5527o2.m13244a(str3, C5446g0.this.f12633X, file.getAbsolutePath(), "liveness_file.megvii", C5446g0.this.getView().mo12961e()).getAbsolutePath());
                            for (LivenessFile livenessFile : C5446g0.this.f12633X) {
                                if ("image".equals(livenessFile.getFileType())) {
                                    File file2 = new File(livenessFile.getPath());
                                    if (file2.exists()) {
                                        file2.delete();
                                    }
                                }
                            }
                            String str4 = "live file save cost time : " + (System.currentTimeMillis() - this.f12681a);
                            StringBuilder sb = new StringBuilder();
                            sb.append("mLivenessFilePath：");
                            C5446g0.this.getClass();
                            sb.append((String) null);
                            C5628v2.m12958b("RecordFinish", sb.toString());
                            C5628v2.m12958b("RecordFinish", "加密耗时：" + (System.currentTimeMillis() - currentTimeMillis));
                        }
                    }
                    C5446g0.this.f12643d0 = true;
                    C5446g0.this.m13514d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.g0$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5450d implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5450d() {
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
                C5446g0 c5446g0 = C5446g0.this;
                C5402d.f12429a = c5446g0.f12654j;
                String str2 = c5446g0.f12660p;
                int i = c5446g0.f12661q;
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
                if (C5446g0.this.getView() != null) {
                    C5446g0.this.getView().mo12965b(1);
                }
                C5446g0 c5446g02 = C5446g0.this;
                if (c5446g02.f12612C) {
                    c5446g02.f12645e0 = true;
                    C5446g0.this.m13514d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.g0$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5451e extends Thread {

        /* renamed from: a */
        public boolean f12684a = false;

        public C5451e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f12684a) {
                try {
                    C5452g1 take = C5446g0.this.mFrameDataQueue.take();
                    byte[] bArr = take.f12686a;
                    if (C5446g0.this.f12657m == -1 && C5446g0.this.f12659o != 3) {
                        String str = "handleStepChange: 2222222     lastStepReal = " + C5446g0.this.f12659o;
                        C5446g0 c5446g0 = C5446g0.this;
                        C5402d.f12429a = c5446g0.f12654j;
                        String str2 = c5446g0.f12660p;
                        int i = c5446g0.f12661q;
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
                                jSONObject3.put("biz_token", str2);
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
                    int i3 = C5486k.f12831a;
                    int i4 = C5486k.f12832b;
                    C5446g0 c5446g02 = C5446g0.this;
                    C5446g0.m13525a(C5446g0.this, C5446g0.this.getModel().m13156a(bArr, i3, i4, c5446g02.f12652i, c5446g02.f12626Q, c5446g02.f12611B, false, C5446g0.m13527a(c5446g02)), take);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public static void m13526a(C5446g0 c5446g0, int i, boolean z) {
        synchronized (c5446g0) {
            c5446g0.f12651h0 = true;
            if (c5446g0.f12649g0 && c5446g0.getView() != null) {
                c5446g0.getView().mo12964b(z);
            }
        }
    }

    /* renamed from: b */
    public static void m13519b(C5446g0 c5446g0, int i, boolean z) {
        c5446g0.getClass();
        if (i != 0 || c5446g0.getView() == null) {
            return;
        }
        synchronized (c5446g0) {
            c5446g0.f12649g0 = true;
            if (c5446g0.f12651h0) {
                c5446g0.getView().mo12964b(z);
            }
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12939a() {
        if (getView() != null) {
            getView().mo12967a(false);
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5555b
    /* renamed from: a */
    public void mo12937a(SurfaceTexture surfaceTexture) {
        if (getView() != null) {
            getView().mo12969a(surfaceTexture);
        }
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x054B: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
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
    /*  JADX ERROR: Failed to decode insn: 0x0528: CONST_STRING r0, method: com.megvii.lv5.g0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
    /*  JADX ERROR: Failed to decode insn: 0x054B: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.megvii.lv5.g0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
    /* renamed from: a */
    public final void m13528a(com.megvii.lv5.C5391c r38, com.megvii.lv5.C5452g1 r39) {
        /*
            Method dump skipped, instructions count: 1712
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5446g0.m13528a(com.megvii.lv5.c, com.megvii.lv5.g1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* renamed from: a */
    public void m13522a(String str, int i, byte[] bArr, int i2) {
        String str2 = "mfxtest autoUpload: delta len =" + bArr.length;
        ?? r0 = C5527o2.m13238b((Context) null);
        if (i2 != 0) {
            r0 = -1;
        }
        C5658y0.m12893a().m12892a(null, str, this.f12660p, i, bArr, r0, new C5448b(i2, i, str, bArr));
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12930a(ArrayList<Camera.Size> arrayList) {
    }

    /* renamed from: a */
    public final void m13521a(byte[] bArr, int i, int i2, String str, String str2) {
        if (m13510h()) {
            String str3 = this.f12669y + "/" + str + ".jpg";
            C5628v2.m12958b("recordFinish", "saveImage :" + str3);
            int i3 = (360 - this.f12652i) % 360;
            byte[] m13241a = C5527o2.m13241a(bArr, i, i2, i3);
            if (i3 == 90 || i3 == 270) {
                C5492l c5492l = this.mCameraManager;
                i = c5492l.f12849d;
                i2 = c5492l.f12848c;
            }
            try {
                new YuvImage(m13241a, 17, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, new FileOutputStream(str3));
                this.f12633X.add(new LivenessFile(str3, "image", str2));
            } catch (FileNotFoundException unused) {
            }
        }
    }

    /* renamed from: a */
    public final boolean m13523a(String str) {
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
            getView().mo12967a(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* renamed from: b */
    public void m13517b(String str, int i, byte[] bArr, int i2) {
        String str2 = "mfxtest autoUploadWhiteBalance: delta len =" + bArr.length;
        ?? r0 = C5527o2.m13238b((Context) null);
        if (i2 != 0) {
            r0 = -1;
        }
        int i3 = r0;
        String str3 = "autoUploadWhiteBalance:  start = " + System.currentTimeMillis();
        C5658y0.m12893a().m12892a(null, str, this.f12660p, i, bArr, i3, new C5447a(i2, str, i, bArr));
    }

    /* renamed from: b */
    public final void m13516b(byte[] bArr) {
        int cameraWidth;
        int cameraHeight;
        String str;
        if (bArr == null) {
            return;
        }
        C5475i1 c5475i1 = this.f12619J;
        if (c5475i1 != null) {
            c5475i1.m13457a(new C5452g1(C5527o2.m13241a(bArr, getCameraWidth(), getCameraHeight(), (360 - this.f12652i) % 360)));
        }
        int i = this.f12632W + 1;
        this.f12632W = i;
        if (i == 1) {
            cameraWidth = getCameraWidth();
            cameraHeight = getCameraHeight();
            str = "image_1";
        } else {
            int i2 = this.f12667w;
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
        m13521a(bArr, cameraWidth, cameraHeight, str, "");
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: c */
    public void mo12923c() {
        if (getView() != null) {
            getView().mo12967a(true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
        if (r3.f12643d0 != false) goto L15;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m13514d() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f12637a0
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72
            r1.<init>()     // Catch: java.lang.Throwable -> L72
            java.lang.String r2 = "detectFinish: isScreenVideoOK = "
            r1.append(r2)     // Catch: java.lang.Throwable -> L72
            boolean r2 = r3.f12639b0     // Catch: java.lang.Throwable -> L72
            r1.append(r2)     // Catch: java.lang.Throwable -> L72
            r1.toString()     // Catch: java.lang.Throwable -> L72
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72
            r1.<init>()     // Catch: java.lang.Throwable -> L72
            java.lang.String r2 = "detectFinish: isCaptureOK = "
            r1.append(r2)     // Catch: java.lang.Throwable -> L72
            boolean r2 = r3.f12641c0     // Catch: java.lang.Throwable -> L72
            r1.append(r2)     // Catch: java.lang.Throwable -> L72
            r1.toString()     // Catch: java.lang.Throwable -> L72
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72
            r1.<init>()     // Catch: java.lang.Throwable -> L72
            java.lang.String r2 = "detectFinish: isRecordOk = "
            r1.append(r2)     // Catch: java.lang.Throwable -> L72
            boolean r2 = r3.f12643d0     // Catch: java.lang.Throwable -> L72
            r1.append(r2)     // Catch: java.lang.Throwable -> L72
            r1.toString()     // Catch: java.lang.Throwable -> L72
            boolean r1 = r3.f12612C     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L4e
            boolean r1 = r3.f12639b0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            boolean r1 = r3.f12641c0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            boolean r1 = r3.f12643d0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            boolean r1 = r3.f12645e0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            goto L5a
        L4e:
            boolean r1 = r3.f12639b0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            boolean r1 = r3.f12641c0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            boolean r1 = r3.f12643d0     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
        L5a:
            r1 = 1
            goto L5d
        L5c:
            r1 = 0
        L5d:
            if (r1 == 0) goto L70
            com.megvii.lv5.sdk.base.BaseView r1 = r3.getView()     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L70
            com.megvii.lv5.sdk.base.BaseView r1 = r3.getView()     // Catch: java.lang.Throwable -> L72
            com.megvii.lv5.v r1 = (com.megvii.lv5.InterfaceC5625v) r1     // Catch: java.lang.Throwable -> L72
            int r2 = r3.f12670z     // Catch: java.lang.Throwable -> L72
            r1.mo12972a(r2)     // Catch: java.lang.Throwable -> L72
        L70:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L72
            return
        L72:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L72
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5446g0.m13514d():void");
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void detach() {
        try {
            C5665z0.m12886a().m12884b();
            synchronized (this.f12638b) {
                if (getModel() != null) {
                    getModel().m13152d();
                }
            }
            m13503o();
            m13513e();
            m13502p();
            cleanFiles(this.f12670z);
            super.detach();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    public void m13513e() {
        if (m13509i()) {
            C5462h2.C5464b.f12721a.m13495a();
        }
    }

    /* renamed from: f */
    public final void m13512f() {
        String[][] strArr = this.f12640c;
        if (strArr != null && strArr.length > 1) {
            if (this.f12644e == strArr.length - 1) {
                this.f12644e = 0;
            }
            int i = this.f12644e;
            f12609j0 = strArr[i];
            this.f12644e = i + 1;
        }
        String[] strArr2 = f12609j0;
        this.f12642d = (strArr2 == null || strArr2.length == 0) ? this.f12610A.f13023r1 : strArr2.length;
        String str = "getFlashNumber: falshSequenceLength = " + this.f12642d;
        this.f12667w = (this.f12646f * this.f12642d) + this.f12610A.f12994i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] m13511g() {
        /*
            r5 = this;
            java.lang.String r0 = r5.f12647f0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L51
            java.lang.String r0 = r5.f12654j
            com.megvii.lv5.C5402d.f12429a = r0
            com.megvii.lv5.d r0 = com.megvii.lv5.C5402d.C5403a.f12436a
            java.lang.String r2 = r5.f12660p
            int r3 = r5.f12661q
            java.lang.String r4 = "whitebalance_exposure_video_detect"
            org.json.JSONObject r2 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r2)
            java.lang.String r2 = r5.f12647f0
            boolean r2 = r5.m13523a(r2)
            if (r2 == 0) goto L3f
            java.lang.String r2 = r5.f12654j
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = r5.f12660p
            int r3 = r5.f12661q
            java.lang.String r4 = "whitebalance_exposure_video_detect_pass"
            org.json.JSONObject r0 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r0)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r5.f12647f0
            r0.<init>(r2)
            goto L52
        L3f:
            java.lang.String r2 = r5.f12654j
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = r5.f12660p
            int r3 = r5.f12661q
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
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5446g0.m13511g():byte[]");
    }

    /* renamed from: h */
    public final boolean m13510h() {
        int i = this.f12668x;
        return (i == 1 || i == 3) && !TextUtils.isEmpty(getView().mo12961e());
    }

    /* renamed from: i */
    public final boolean m13509i() {
        int i = this.f12610A.f12952R1;
        if (i != 0) {
            return (i == 2 && getView().getScreenRecordContext() == null) ? false : true;
        }
        return false;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void init() {
        super.init();
        File file = new File(getView().getContext().getFilesDir(), "megviiImage");
        if (file.exists()) {
            C5527o2.m13247a(file);
        }
        file.mkdirs();
        this.f12669y = file.getAbsolutePath();
        this.mCameraManager.f12854i = this;
        getView().mo12968a(this.mCameraManager, this, this);
        this.f12660p = C5527o2.m13256a(getView().getContext());
        C5515n0 m13223h = C5527o2.m13223h(getView().getContext());
        this.f12610A = m13223h;
        C5537q.f13180g.m13196f(m13223h.f13020q1 ? 1 : 2);
        this.f12655k = getView().mo12960f();
        C5515n0 c5515n0 = this.f12610A;
        this.f12662r = c5515n0.f13041x1;
        this.f12661q = c5515n0.f12973b;
        this.f12668x = c5515n0.f13017p1;
        this.f12656l = c5515n0.f13009n;
        this.f12640c = c5515n0.f12988g;
        this.f12646f = c5515n0.f12991h;
        this.f12654j = "liveness-sdk";
        m13512f();
        this.f12613D = C5527o2.m13221j(getView().getContext());
        this.f12614E = C5527o2.m13226e(getView().getContext());
        this.f12615F = this.f12613D ? C5527o2.m13222i(getView().getContext()) : C5527o2.m13227d(getView().getContext());
        this.f12616G = this.f12613D ? C5527o2.m13220k(getView().getContext()) : C5527o2.m13231c(getView().getContext());
        this.f12617H = this.f12613D ? C5527o2.m13218m(getView().getContext()) : C5527o2.m13224g(getView().getContext());
        this.f12618I = this.f12613D ? C5527o2.m13219l(getView().getContext()) : C5527o2.m13225f(getView().getContext());
        MegliveModeImpl model = getModel();
        EnumC5633w enumC5633w = EnumC5633w.MegliveLivenessLiveTypeFlash;
        byte[] m13254a = C5527o2.m13254a(getView().getContext(), EnumC5556s2.RECT);
        byte[] m13254a2 = C5527o2.m13254a(getView().getContext(), EnumC5556s2.LMK);
        byte[] m13254a3 = C5527o2.m13254a(getView().getContext(), EnumC5556s2.ACTION);
        String mo12966b = getView().mo12966b();
        String m13217n = C5527o2.m13217n(getView().getContext());
        int i = this.f12656l;
        long j = this.f12642d;
        long j2 = this.f12646f;
        C5515n0 c5515n02 = this.f12610A;
        model.m13161a(enumC5633w, 0.0d, 10.0d, 1, 0, 0, null, m13254a, m13254a2, m13254a3, mo12966b, m13217n, i, j, j2, c5515n02.f12994i, c5515n02.f13026s1, c5515n02.f13000k, c5515n02.f12997j, c5515n02.f13003l, c5515n02.f13006m, "", c5515n02.f13012o, !c5515n02.f13020q1, false, 0, 0.0f, 0, c5515n02.f13028t0);
        String str = "init: isWhiteBalanceOpened = " + this.f12613D;
        String str2 = "init: isExposureOpened = " + this.f12614E;
        if (this.f12613D || this.f12614E) {
            getModel().m13158a(true);
        } else {
            this.f12651h0 = true;
            this.f12645e0 = true;
        }
        if (!this.f12610A.f13032u1) {
            this.f12641c0 = true;
        }
        m13507k();
        C5515n0 c5515n03 = this.f12610A;
        int i2 = c5515n03.f13021r;
        int[] m13393a = c5515n03.m13393a();
        if (m13393a != null && m13393a.length > 0) {
            getModel().m13162a(i2, m13393a);
        }
        this.f12612C = true;
    }

    /* renamed from: j */
    public final String m13508j() {
        String[] strArr = f12609j0;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String str = strArr[this.f12624O];
        this.f12623N = str;
        String replace = str.replace("0x", "#");
        int i = this.f12624O;
        if (i < this.f12642d - 1) {
            this.f12624O = i + 1;
        }
        return replace;
    }

    /* renamed from: k */
    public final void m13507k() {
        String str = "setThresholdMirror: getFaceMinSize = " + this.f12610A.f13024s;
        String str2 = "setThresholdMirror: isCheckMultyFace = " + this.f12610A.f13030u;
        String str3 = "setThresholdMirror: getFaceMultyMinSize = " + this.f12610A.f13027t;
        MegliveModeImpl model = getModel();
        this.f12610A.getClass();
        C5515n0 c5515n0 = this.f12610A;
        model.m13163a(0.5f, c5515n0.f13033v, c5515n0.f13036w, c5515n0.f13039x, 0.5f, c5515n0.f13042y, c5515n0.f12899A, c5515n0.f13045z, c5515n0.f12902B, c5515n0.f12905C, c5515n0.f12908D, c5515n0.f12911E, c5515n0.f12914F, c5515n0.f12917G, c5515n0.f12920H, c5515n0.f12923I, 0.99f, c5515n0.f12926J, c5515n0.f12929K, c5515n0.f13022r0, c5515n0.f13024s, c5515n0.f13030u, c5515n0.f13027t);
    }

    /* renamed from: l */
    public void m13506l() {
        try {
            System.currentTimeMillis();
            this.f12648g = new C5451e();
            this.f12650h = System.currentTimeMillis();
            C5492l c5492l = this.mCameraManager;
            this.f12652i = c5492l.f12852g;
            if (!c5492l.m13444b()) {
                this.f12652i -= 180;
            }
            if (this.f12648g != null) {
                getModel().m13151e();
                getModel().m13150f();
                C5451e c5451e = this.f12648g;
                c5451e.f12684a = true;
                c5451e.start();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: m */
    public final void m13505m() {
        C5628v2.m12958b("FlashDetect", "wb startWbtRecording...");
        try {
            C5475i1 c5475i1 = new C5475i1(getView().getContext(), "fmp_wb");
            this.f12620K = c5475i1;
            if (this.f12665u) {
                AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a = this.f12653i0;
                C5492l c5492l = this.mCameraManager;
                new C5482j1(c5475i1, interfaceC5461a, c5492l.f12849d, c5492l.f12848c);
            }
            this.f12620K.m13453c();
            this.f12620K.m13452d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: n */
    public void m13504n() {
        try {
            C5451e c5451e = this.f12648g;
            if (c5451e != null) {
                c5451e.f12684a = false;
                getModel().m13149g();
                this.f12648g.interrupt();
                this.f12648g = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: o */
    public final void m13503o() {
        try {
            if (this.f12619J != null) {
                C5628v2.m12958b("FlashDetect", "liveness stopRecording...");
                this.f12619J.m13451e();
                this.f12666v = this.f12619J.f12797a;
                this.f12619J = null;
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5554a
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr == null) {
            return;
        }
        try {
            this.cameraData = bArr;
            this.mFrameDataQueue.offer(new C5452g1(bArr));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public boolean openCamera() {
        return super.openCamera();
    }

    /* renamed from: p */
    public final void m13502p() {
        try {
            if (this.f12620K != null) {
                C5628v2.m12958b("FlashDetect", "wb stopRecording...");
                this.f12620K.m13451e();
                this.f12647f0 = this.f12620K.f12797a;
                this.f12620K = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m13527a(C5446g0 c5446g0) {
        if (c5446g0.f12657m != 3) {
            return true;
        }
        int i = c5446g0.f12655k;
        if (i != 1 && (i != 2 || System.currentTimeMillis() - c5446g0.f12650h > 2000)) {
            return true;
        }
        return C5665z0.m12886a().m12883b(c5446g0.getView().getContext());
    }

    /* renamed from: b */
    public static boolean m13520b(C5446g0 c5446g0) {
        int i = c5446g0.f12668x;
        return (i == 1 || i == 2) && !TextUtils.isEmpty(c5446g0.getView().mo12961e());
    }

    /* renamed from: a */
    public static void m13525a(C5446g0 c5446g0, C5391c c5391c, C5452g1 c5452g1) {
        int i;
        JSONObject jSONObject;
        c5446g0.getClass();
        if (c5391c == null) {
            return;
        }
        c5446g0.f12657m = c5391c.f12400a;
        c5446g0.f12659o = c5446g0.f12657m;
        String str = "coolor handleDetectResult: currentStep = " + c5446g0.f12657m + "      , isSynchronized = " + c5446g0.f12629T;
        if (!c5446g0.f12629T && (c5446g0.f12657m == 17 || c5446g0.f12657m == 14)) {
            c5446g0.getView().mo12963c();
            return;
        }
        c5446g0.m13528a(c5391c, c5452g1);
        c5446g0.f12658n = c5446g0.f12657m;
        String str2 = null;
        if (c5446g0.f12657m == 3) {
            c5446g0.f12629T = false;
            int i2 = c5391c.f12401b;
            if (i2 == 6 || i2 == 7) {
                c5446g0.changeExposure(i2, c5446g0.f12610A.f13015p == 1);
            }
            c5446g0.getView().mo12970a(2, i2);
            if (i2 <= 0 || i2 > 18 || i2 == 14 || c5446g0.f12636a == i2) {
                return;
            }
            c5446g0.f12636a = i2;
            if (c5446g0.f12630U) {
                c5446g0.f12631V = System.currentTimeMillis();
                c5446g0.f12630U = false;
            }
            if (System.currentTimeMillis() - c5446g0.f12631V > 200) {
                C5402d.f12429a = c5446g0.f12654j;
                String str3 = c5446g0.f12660p;
                int i3 = c5446g0.f12661q;
                String uuid = UUID.randomUUID().toString();
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put("type", "track");
                    jSONObject.put("project", C5402d.f12429a);
                    jSONObject.put("event_id", uuid);
                    jSONObject.put("time", System.currentTimeMillis());
                    jSONObject.put("event", "fail_mirror:" + C5402d.f12433e[i2]);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("biz_token", str3);
                    jSONObject2.put("liveness", i3);
                    jSONObject2.put("try_times", 0);
                    int i4 = C5402d.f12431c + 1;
                    C5402d.f12431c = i4;
                    jSONObject2.put("index", i4);
                    jSONObject.put("properties", jSONObject2);
                    C5402d.f12430b = "fail_mirror";
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONObject = null;
                }
                C5399c3.m13606a(jSONObject);
            }
        } else if (c5446g0.f12657m != 17) {
            if (c5446g0.f12657m == 11) {
                C5515n0 c5515n0 = c5446g0.f12610A;
                if (c5515n0.f13020q1 && c5515n0.f12919G1) {
                    c5446g0.mCameraManager.f12846a.mo13269a((AbstractC5500m.InterfaceC5505e) c5446g0);
                }
            } else if (c5446g0.f12657m != 12) {
                if (c5446g0.f12657m == 15 || c5446g0.f12657m != 14 || c5446g0.f12613D || c5446g0.f12614E) {
                    return;
                }
                c5446g0.m13516b(c5452g1.f12686a);
                c5446g0.m13503o();
            } else {
                if (c5446g0.f12626Q) {
                    c5446g0.f12626Q = false;
                    c5446g0.m13516b(c5452g1.f12686a);
                }
                InterfaceC5625v view = c5446g0.getView();
                int i5 = c5446g0.f12657m;
                float f = c5391c.f12406g * ((c5446g0.f12667w * 1.0f) / (i - 2));
                if (c5446g0.f12625P) {
                    c5446g0.f12625P = false;
                    str2 = c5446g0.m13508j();
                }
                long j = c5446g0.f12622M + 1;
                c5446g0.f12622M = j;
                if (j > 0) {
                    c5446g0.f12626Q = true;
                } else {
                    c5446g0.f12626Q = false;
                }
                String str4 = "doFlashLight: flashIndex = " + c5446g0.f12622M;
                if (c5446g0.f12622M == c5446g0.f12646f + 0) {
                    c5446g0.f12622M = 0L;
                    str2 = c5446g0.m13508j();
                }
                view.mo12971a(i5, f, str2);
            }
        } else if (c5446g0.f12641c0) {
            C5475i1 c5475i1 = c5446g0.f12620K;
            if (c5475i1 != null) {
                c5475i1.m13457a(new C5452g1(C5527o2.m13241a(c5452g1.f12686a, c5446g0.getCameraWidth(), c5446g0.getCameraHeight(), (360 - c5446g0.f12652i) % 360), c5452g1.f12687b));
            }
            if (c5446g0.f12613D) {
                C5492l c5492l = c5446g0.mCameraManager;
                int i6 = c5446g0.f12627R;
                c5446g0.f12627R = i6 + 1;
                c5492l.f12846a.mo13261c(i6);
                if (c5446g0.f12627R >= c5446g0.f12615F) {
                    c5446g0.f12628S = MegDelta.checkWhite(c5452g1.f12686a, c5446g0.getCameraWidth(), c5446g0.getCameraHeight(), c5446g0.f12617H, c5446g0.f12618I);
                }
            } else if (c5446g0.f12614E) {
                C5492l c5492l2 = c5446g0.mCameraManager;
                int i7 = c5446g0.f12627R;
                c5446g0.f12627R = i7 + 1;
                c5492l2.f12846a.mo13276a(i7);
                if (c5446g0.f12627R >= c5446g0.f12615F) {
                    c5446g0.f12628S = MegDelta.checkExposure(c5452g1.f12686a, c5446g0.getCameraWidth(), c5446g0.getCameraHeight(), c5446g0.f12617H, c5446g0.f12618I);
                }
            }
            if (c5446g0.f12627R == c5446g0.f12616G) {
                c5446g0.f12628S = true;
            }
            if (c5446g0.f12628S) {
                if (c5446g0.f12613D) {
                    C5402d.f12429a = c5446g0.f12654j;
                    C5402d c5402d = C5402d.C5403a.f12436a;
                    C5399c3.m13606a(c5402d.m13599a("pass_whitebalance", c5446g0.f12660p, c5446g0.f12661q));
                    C5402d.f12429a = c5446g0.f12654j;
                    C5399c3.m13606a(c5402d.m13599a("pass_whitebalance_frame:" + c5446g0.f12627R, c5446g0.f12660p, c5446g0.f12661q));
                } else if (c5446g0.f12614E) {
                    C5402d.f12429a = c5446g0.f12654j;
                    C5402d c5402d2 = C5402d.C5403a.f12436a;
                    C5399c3.m13606a(c5402d2.m13599a("pass_exposure", c5446g0.f12660p, c5446g0.f12661q));
                    C5402d.f12429a = c5446g0.f12654j;
                    C5399c3.m13606a(c5402d2.m13599a("pass_exposure_frame:" + c5446g0.f12627R, c5446g0.f12660p, c5446g0.f12661q));
                }
                c5446g0.getModel().m13158a(false);
                c5446g0.m13502p();
                if (c5446g0.f12613D) {
                    c5446g0.mCameraManager.m13443c();
                }
            }
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5504d
    /* renamed from: a */
    public void mo12929a(byte[] bArr) {
        C5402d.f12429a = this.f12654j;
        C5402d c5402d = C5402d.C5403a.f12436a;
        C5399c3.m13606a(c5402d.m13599a("pass_hd_image", this.f12660p, this.f12661q));
        if (this.f12613D) {
            C5402d.f12429a = this.f12654j;
            C5399c3.m13606a(c5402d.m13599a("enter_whitebalance", this.f12660p, this.f12661q));
            m13505m();
        } else if (this.f12614E) {
            C5402d.f12429a = this.f12654j;
            C5399c3.m13606a(c5402d.m13599a("enter_exposure", this.f12660p, this.f12661q));
            m13505m();
        }
        this.mFrameDataQueueHD.offer(bArr);
        this.f12641c0 = true;
        m13514d();
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5505e
    /* renamed from: a */
    public void mo13438a(double d) {
        float f = (float) d;
        this.f12611B = f;
        if (f == Float.POSITIVE_INFINITY) {
            try {
                this.f12611B = 0.0f;
            } catch (Throwable th) {
                th.printStackTrace();
                this.f12611B = 0.0f;
            }
        }
        if (this.f12611B < 0.0f) {
            C5402d.f12429a = this.f12654j;
            String str = "fail_ev:" + C5402d.f12435g[1];
            String str2 = this.f12660p;
            int i = this.f12661q;
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
        C5628v2.m12959a("onEVCallback", "evLight:" + this.f12611B);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x014b A[Catch: Exception -> 0x005b, TryCatch #0 {Exception -> 0x005b, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:11:0x0035, B:13:0x003c, B:17:0x0054, B:28:0x006d, B:30:0x007e, B:32:0x0087, B:34:0x0091, B:37:0x0099, B:39:0x009d, B:42:0x00a2, B:44:0x00a8, B:61:0x010f, B:47:0x00b2, B:49:0x00c2, B:51:0x00c9, B:53:0x00d7, B:55:0x00f5, B:57:0x00fa, B:60:0x010b, B:62:0x0113, B:65:0x0118, B:67:0x011f, B:69:0x0127, B:71:0x012f, B:73:0x014b, B:75:0x0156, B:81:0x0165, B:82:0x016b, B:66:0x011b, B:14:0x0045, B:16:0x004c), top: B:86:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0165 A[Catch: Exception -> 0x005b, TRY_ENTER, TryCatch #0 {Exception -> 0x005b, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:11:0x0035, B:13:0x003c, B:17:0x0054, B:28:0x006d, B:30:0x007e, B:32:0x0087, B:34:0x0091, B:37:0x0099, B:39:0x009d, B:42:0x00a2, B:44:0x00a8, B:61:0x010f, B:47:0x00b2, B:49:0x00c2, B:51:0x00c9, B:53:0x00d7, B:55:0x00f5, B:57:0x00fa, B:60:0x010b, B:62:0x0113, B:65:0x0118, B:67:0x011f, B:69:0x0127, B:71:0x012f, B:73:0x014b, B:75:0x0156, B:81:0x0165, B:82:0x016b, B:66:0x011b, B:14:0x0045, B:16:0x004c), top: B:86:0x0008, inners: #1 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] m13529a(int r27) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5446g0.m13529a(int):byte[]");
    }
}
