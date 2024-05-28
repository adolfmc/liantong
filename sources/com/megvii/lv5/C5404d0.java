package com.megvii.lv5;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
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
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.d0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5404d0 extends DetectBasePresenter<InterfaceC5612t, MegliveModeImpl> implements AbstractC5500m.InterfaceC5502b, AbstractC5500m.InterfaceC5504d, AbstractC5500m.InterfaceC5505e, C5553s1.InterfaceC5554a, C5553s1.InterfaceC5555b {

    /* renamed from: x0 */
    public static String[] f12437x0;

    /* renamed from: B */
    public int f12439B;

    /* renamed from: F */
    public C5515n0 f12443F;

    /* renamed from: H */
    public LinkedList<C5452g1> f12445H;

    /* renamed from: I */
    public boolean f12446I;

    /* renamed from: K */
    public boolean f12448K;

    /* renamed from: L */
    public boolean f12449L;

    /* renamed from: M */
    public int f12450M;

    /* renamed from: N */
    public int f12451N;

    /* renamed from: O */
    public int f12452O;

    /* renamed from: P */
    public int f12453P;

    /* renamed from: Q */
    public Handler f12454Q;

    /* renamed from: R */
    public C5475i1 f12455R;

    /* renamed from: S */
    public C5475i1 f12456S;

    /* renamed from: T */
    public C5475i1 f12457T;

    /* renamed from: U */
    public C5475i1 f12458U;

    /* renamed from: c */
    public String[][] f12468c;

    /* renamed from: f0 */
    public long f12475f0;

    /* renamed from: g */
    public C5412h f12476g;

    /* renamed from: i */
    public int f12480i;

    /* renamed from: t */
    public int f12502t;

    /* renamed from: a */
    public int f12464a = -1;

    /* renamed from: b */
    public Object f12466b = new Object();

    /* renamed from: d */
    public int f12470d = 10;

    /* renamed from: e */
    public int f12472e = 0;

    /* renamed from: f */
    public int f12474f = 4;

    /* renamed from: h */
    public long f12478h = 0;

    /* renamed from: j */
    public String f12482j = "";

    /* renamed from: k */
    public int f12484k = 0;

    /* renamed from: l */
    public int f12486l = 10;

    /* renamed from: m */
    public volatile int f12488m = -1;

    /* renamed from: n */
    public List<Integer> f12490n = new ArrayList();

    /* renamed from: o */
    public int f12492o = -1;

    /* renamed from: p */
    public int f12494p = -1;

    /* renamed from: q */
    public int f12496q = -1;

    /* renamed from: r */
    public int f12498r = -1;

    /* renamed from: s */
    public String f12500s = "";

    /* renamed from: u */
    public int f12504u = 3;

    /* renamed from: v */
    public int f12506v = 10;

    /* renamed from: w */
    public int f12508w = 0;

    /* renamed from: x */
    public int f12510x = 0;

    /* renamed from: y */
    public boolean f12511y = true;

    /* renamed from: z */
    public String f12512z = "";

    /* renamed from: A */
    public String f12438A = "";

    /* renamed from: C */
    public int f12440C = 0;

    /* renamed from: D */
    public String f12441D = "";

    /* renamed from: E */
    public int f12442E = -1;

    /* renamed from: G */
    public float f12444G = 0.0f;

    /* renamed from: J */
    public double f12447J = 0.0d;

    /* renamed from: V */
    public String f12459V = "";

    /* renamed from: W */
    public long f12460W = 0;

    /* renamed from: X */
    public String f12461X = "";

    /* renamed from: Y */
    public int f12462Y = 0;

    /* renamed from: Z */
    public boolean f12463Z = true;

    /* renamed from: a0 */
    public boolean f12465a0 = false;

    /* renamed from: b0 */
    public int f12467b0 = 0;

    /* renamed from: c0 */
    public boolean f12469c0 = false;

    /* renamed from: d0 */
    public volatile boolean f12471d0 = false;

    /* renamed from: e0 */
    public volatile boolean f12473e0 = true;

    /* renamed from: g0 */
    public int f12477g0 = 0;

    /* renamed from: h0 */
    public List<LivenessFile> f12479h0 = new ArrayList();

    /* renamed from: i0 */
    public List<LivenessFile> f12481i0 = new ArrayList();

    /* renamed from: j0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12483j0 = new C5408d(this);

    /* renamed from: k0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12485k0 = new C5409e();

    /* renamed from: l0 */
    public Object f12487l0 = new Object();

    /* renamed from: m0 */
    public boolean f12489m0 = false;

    /* renamed from: n0 */
    public boolean f12491n0 = false;

    /* renamed from: o0 */
    public boolean f12493o0 = false;

    /* renamed from: p0 */
    public boolean f12495p0 = false;

    /* renamed from: q0 */
    public boolean f12497q0 = false;

    /* renamed from: r0 */
    public String f12499r0 = null;

    /* renamed from: s0 */
    public String f12501s0 = null;

    /* renamed from: t0 */
    public boolean f12503t0 = false;

    /* renamed from: u0 */
    public boolean f12505u0 = false;

    /* renamed from: v0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12507v0 = new C5410f();

    /* renamed from: w0 */
    public final AbstractRunnableC5460h1.InterfaceC5461a f12509w0 = new C5411g();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class RunnableC5405a implements Runnable {
        public RunnableC5405a(C5404d0 c5404d0) {
        }

        @Override // java.lang.Runnable
        public void run() {
            C5462h2.C5464b.f12721a.m13495a();
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5406b implements InterfaceC5546r0 {

        /* renamed from: a */
        public final /* synthetic */ int f12513a;

        /* renamed from: b */
        public final /* synthetic */ String f12514b;

        /* renamed from: c */
        public final /* synthetic */ int f12515c;

        /* renamed from: d */
        public final /* synthetic */ byte[] f12516d;

        public C5406b(int i, String str, int i2, byte[] bArr) {
            this.f12513a = i;
            this.f12514b = str;
            this.f12515c = i2;
            this.f12516d = bArr;
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12907a(int i, byte[] bArr) {
            String str = "mfxtest autoUploadWhiteBalance onFailure: response=" + new String(bArr);
            if (i >= 0 && i != 500) {
                C5404d0.m13592a(C5404d0.this, this.f12513a, false);
                return;
            }
            C5404d0 c5404d0 = C5404d0.this;
            int i2 = c5404d0.f12510x;
            c5404d0.f12510x = i2 + 1;
            if (i2 < c5404d0.f12504u) {
                c5404d0.m13584b(this.f12514b, this.f12515c, this.f12516d, this.f12513a);
            } else {
                C5404d0.m13592a(c5404d0, this.f12513a, false);
            }
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12906a(String str) {
            String str2 = "autoUploadWhiteBalance:  success = " + System.currentTimeMillis();
            String str3 = "mfxtest autoUploadWhiteBalance onSuccess: response=" + str;
            C5404d0.m13592a(C5404d0.this, this.f12513a, true);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5407c implements InterfaceC5546r0 {

        /* renamed from: a */
        public final /* synthetic */ int f12518a;

        /* renamed from: b */
        public final /* synthetic */ int f12519b;

        /* renamed from: c */
        public final /* synthetic */ String f12520c;

        /* renamed from: d */
        public final /* synthetic */ byte[] f12521d;

        public C5407c(int i, int i2, String str, byte[] bArr) {
            this.f12518a = i;
            this.f12519b = i2;
            this.f12520c = str;
            this.f12521d = bArr;
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12907a(int i, byte[] bArr) {
            int i2;
            String str = "mfxtest autoUpload onFailure: response=" + new String(bArr);
            if (i >= 0 && i != 500) {
                C5404d0.m13586b(C5404d0.this, this.f12518a, false);
                return;
            }
            C5404d0 c5404d0 = C5404d0.this;
            int i3 = c5404d0.f12508w;
            c5404d0.f12508w = i3 + 1;
            if (i3 >= c5404d0.f12504u || (i2 = this.f12519b) != 1) {
                C5404d0.m13586b(c5404d0, this.f12518a, false);
            } else {
                c5404d0.m13588a(this.f12520c, i2, this.f12521d, this.f12518a);
            }
        }

        @Override // com.megvii.lv5.InterfaceC5546r0
        /* renamed from: a */
        public void mo12906a(String str) {
            String str2 = "autoUpload: live success = " + System.currentTimeMillis();
            String str3 = "mfxtest autoUpload onSuccess: response=" + str;
            C5404d0.m13586b(C5404d0.this, this.f12518a, true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5408d implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5408d(C5404d0 c5404d0) {
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
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5409e implements AbstractRunnableC5460h1.InterfaceC5461a {

        /* renamed from: a */
        public long f12523a = System.currentTimeMillis();

        public C5409e() {
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
            File m13244a;
            int size;
            JSONObject jSONObject;
            if (abstractRunnableC5460h1 instanceof C5482j1) {
                if (C5404d0.this.f12488m == 14 || C5404d0.this.f12488m == 17) {
                    C5404d0 c5404d0 = C5404d0.this;
                    if (c5404d0.f12448K || c5404d0.f12449L ? c5404d0.f12488m == 17 && C5404d0.this.f12442E == 0 : c5404d0.f12488m == 14 && C5404d0.this.f12442E == 0) {
                        C5404d0 c5404d02 = C5404d0.this;
                        C5402d.f12429a = c5404d02.f12482j;
                        String str = c5404d02.f12500s;
                        int i = c5404d02.f12502t;
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
                    byte[] m13164a = C5404d0.this.getModel() == null ? null : C5404d0.this.getModel().m13164a();
                    if (C5404d0.this.getView() != null && !TextUtils.isEmpty(C5404d0.this.getView().mo12999e())) {
                        this.f12523a = System.currentTimeMillis();
                        if (C5404d0.this.m13575h() && (size = C5404d0.this.f12479h0.size()) < 4 && size > 0) {
                            int i3 = 4 - size;
                            for (int i4 = 0; i4 < i3; i4++) {
                                List<LivenessFile> list = C5404d0.this.f12479h0;
                                list.add(list.get(0));
                            }
                        }
                        if (C5404d0.this.m13574i()) {
                            C5404d0 c5404d03 = C5404d0.this;
                            c5404d03.f12479h0.add(new LivenessFile(c5404d03.f12499r0, "video", ""));
                        }
                        if (C5404d0.this.m13575h()) {
                            String str2 = C5404d0.this.f12441D + "/imagebest.jpg";
                            C5527o2.m13240a(m13164a, str2);
                            C5404d0.this.f12479h0.add(new LivenessFile(str2, "image", ""));
                        }
                        File file = new File(C5404d0.this.getView().getContext().getFilesDir(), "livenessFile");
                        long currentTimeMillis = System.currentTimeMillis();
                        List<LivenessFile> list2 = C5404d0.this.f12479h0;
                        if (list2 != null && list2.size() > 0 && (m13244a = C5527o2.m13244a("meglive_flash", C5404d0.this.f12479h0, file.getAbsolutePath(), "liveness_file.megvii", C5404d0.this.getView().mo12999e())) != null) {
                            C5404d0.this.mMegliveLocalFileInfo.setFilePath(m13244a.getAbsolutePath());
                            for (LivenessFile livenessFile : C5404d0.this.f12479h0) {
                                if ("image".equals(livenessFile.getFileType())) {
                                    File file2 = new File(livenessFile.getPath());
                                    if (file2.exists()) {
                                        file2.delete();
                                    }
                                }
                            }
                            String str3 = "live file save cost time : " + (System.currentTimeMillis() - this.f12523a);
                            StringBuilder sb = new StringBuilder();
                            sb.append("mLivenessFilePath：");
                            C5404d0.this.getClass();
                            sb.append((String) null);
                            C5628v2.m12958b("RecordFinish", sb.toString());
                            C5628v2.m12958b("RecordFinish", "加密耗时：" + (System.currentTimeMillis() - currentTimeMillis));
                        }
                    }
                    C5404d0.this.f12493o0 = true;
                    C5404d0.this.m13580d();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5410f implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5410f() {
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
                C5404d0 c5404d0 = C5404d0.this;
                C5402d.f12429a = c5404d0.f12482j;
                String str2 = c5404d0.f12500s;
                int i = c5404d0.f12502t;
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
                C5404d0 c5404d02 = C5404d0.this;
                if (c5404d02.f12446I) {
                    c5404d02.f12495p0 = true;
                    C5404d0.this.m13580d();
                } else if (c5404d02.getView() != null) {
                    C5404d0.this.getView().mo13006b(1);
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$g */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5411g implements AbstractRunnableC5460h1.InterfaceC5461a {
        public C5411g() {
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
                if (C5404d0.this.f12488m == 14 || C5404d0.this.f12488m == 17) {
                    C5404d0 c5404d0 = C5404d0.this;
                    if (c5404d0.f12446I) {
                        c5404d0.f12497q0 = true;
                        C5404d0.this.m13580d();
                    }
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.d0$h */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5412h extends Thread {

        /* renamed from: a */
        public boolean f12527a = false;

        public C5412h() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f12527a) {
                try {
                    C5452g1 take = C5404d0.this.mFrameDataQueue.take();
                    byte[] bArr = take.f12686a;
                    if (C5404d0.this.f12488m == -1) {
                        C5404d0 c5404d0 = C5404d0.this;
                        if (c5404d0.f12494p != 3) {
                            C5402d.f12429a = c5404d0.f12482j;
                            String str = c5404d0.f12500s;
                            int i = c5404d0.f12502t;
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
                    C5404d0 c5404d02 = C5404d0.this;
                    C5391c m13156a = C5404d0.this.getModel().m13156a(bArr, i3, i4, c5404d02.f12480i, c5404d02.f12465a0, c5404d02.f12444G, false, C5404d0.m13593a(c5404d02));
                    m13156a.toString();
                    C5404d0.m13591a(C5404d0.this, m13156a, take);
                } catch (Throwable unused) {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public static void m13592a(C5404d0 c5404d0, int i, boolean z) {
        synchronized (c5404d0) {
            c5404d0.f12505u0 = true;
            if (c5404d0.f12503t0 && c5404d0.getView() != null) {
                c5404d0.getView().mo13004b(z);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: MOVE  (r172 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r48 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), expected to be less than 17
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* renamed from: a */
    public static void m13591a(com.megvii.lv5.C5404d0 r14, com.megvii.lv5.C5391c r15, com.megvii.lv5.C5452g1 r16) {
        /*
            Method dump skipped, instructions count: 1295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5404d0.m13591a(com.megvii.lv5.d0, com.megvii.lv5.c, com.megvii.lv5.g1):void");
    }

    /* renamed from: b */
    public static void m13586b(C5404d0 c5404d0, int i, boolean z) {
        c5404d0.getClass();
        if (i != 0 || c5404d0.getView() == null) {
            return;
        }
        synchronized (c5404d0) {
            c5404d0.f12503t0 = true;
            if (c5404d0.f12505u0) {
                c5404d0.getView().mo13004b(z);
            }
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12939a() {
        if (getView() != null) {
            getView().mo13008a(false);
        }
    }

    @Override // com.megvii.lv5.C5553s1.InterfaceC5555b
    /* renamed from: a */
    public void mo12937a(SurfaceTexture surfaceTexture) {
        if (getView() != null) {
            getView().mo13010a(surfaceTexture);
        }
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0565: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
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
    /*  JADX ERROR: Failed to decode insn: 0x0446: CONST_METHOD_HANDLE r238, method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0446: CONST_METHOD_HANDLE r238'
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
    /*  JADX ERROR: Failed to decode insn: 0x0542: CONST_STRING r0, method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
    /*  JADX ERROR: Failed to decode insn: 0x0565: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
    /*  JADX ERROR: Failed to decode insn: 0x074E: UNKNOWN(0x00EE), method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x074E: UNKNOWN(0x00EE)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0754: CONST_STRING r0, method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        java.lang.IllegalArgumentException: newPosition > limit: (73924744 > 13931064)
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
    /*  JADX ERROR: Failed to decode insn: 0x0888: UNKNOWN(0x0043), method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0888: UNKNOWN(0x0043)'
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
    /*  JADX ERROR: Failed to decode insn: 0x08D3: UNKNOWN(0x0040), method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x08D3: UNKNOWN(0x0040)'
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
    /*  JADX ERROR: Failed to decode insn: 0x09F8: CONST_STRING r0, method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
    /*  JADX ERROR: Failed to decode insn: 0x0A1C: CONST_STRING r0, method: com.megvii.lv5.d0.a(com.megvii.lv5.c, com.megvii.lv5.g1):void
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
    public final void m13594a(com.megvii.lv5.C5391c r42, com.megvii.lv5.C5452g1 r43) {
        /*
            Method dump skipped, instructions count: 2764
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5404d0.m13594a(com.megvii.lv5.c, com.megvii.lv5.g1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* renamed from: a */
    public void m13588a(String str, int i, byte[] bArr, int i2) {
        String str2 = "mfxtest autoUpload: delta len =" + bArr.length;
        ?? r0 = C5527o2.m13238b((Context) null);
        if (i2 != 0) {
            r0 = -1;
        }
        C5658y0.m12893a().m12892a(null, str, this.f12500s, i, bArr, r0, new C5407c(i2, i, str, bArr));
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: a */
    public void mo12930a(ArrayList<Camera.Size> arrayList) {
    }

    /* renamed from: a */
    public final void m13587a(byte[] bArr, int i, int i2, String str, int i3) {
        if (m13575h()) {
            String str2 = this.f12441D + "/" + str + ".jpg";
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
            int i4 = (360 - this.f12480i) % 360;
            byte[] m13241a = C5527o2.m13241a(bArr, i, i2, i4);
            if (i4 == 90 || i4 == 270) {
                C5492l c5492l = this.mCameraManager;
                i = c5492l.f12849d;
                i2 = c5492l.f12848c;
            }
            try {
                new YuvImage(m13241a, 17, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, new FileOutputStream(str2));
                this.f12479h0.add(new LivenessFile(str2, "image", str3));
            } catch (FileNotFoundException unused) {
            }
        }
    }

    /* renamed from: a */
    public final boolean m13589a(String str) {
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
            getView().mo13008a(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* renamed from: b */
    public void m13584b(String str, int i, byte[] bArr, int i2) {
        String str2 = "mfxtest autoUploadWhiteBalance: delta len =" + bArr.length;
        ?? r0 = C5527o2.m13238b((Context) null);
        if (i2 != 0) {
            r0 = -1;
        }
        int i3 = r0;
        String str3 = "autoUploadWhiteBalance:  start = " + System.currentTimeMillis();
        C5658y0.m12893a().m12892a(null, str, this.f12500s, i, bArr, i3, new C5406b(i2, str, i, bArr));
    }

    /* renamed from: b */
    public final void m13583b(byte[] bArr) {
        C5475i1 c5475i1;
        if (bArr == null || (c5475i1 = this.f12458U) == null) {
            return;
        }
        c5475i1.m13457a(new C5452g1(C5527o2.m13241a(bArr, getCameraWidth(), getCameraHeight(), (360 - this.f12480i) % 360)));
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5502b
    /* renamed from: c */
    public void mo12923c() {
        if (getView() != null) {
            getView().mo13008a(true);
        }
    }

    /* renamed from: c */
    public final void m13581c(byte[] bArr) {
        int cameraWidth;
        int cameraHeight;
        String str;
        if (bArr == null) {
            return;
        }
        C5475i1 c5475i1 = this.f12456S;
        if (c5475i1 != null) {
            c5475i1.m13457a(new C5452g1(C5527o2.m13241a(bArr, getCameraWidth(), getCameraHeight(), (360 - this.f12480i) % 360)));
        }
        int i = this.f12477g0 + 1;
        this.f12477g0 = i;
        if (i == 1) {
            cameraWidth = getCameraWidth();
            cameraHeight = getCameraHeight();
            str = "image_1";
        } else {
            int i2 = this.f12439B;
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
        m13587a(bArr, cameraWidth, cameraHeight, str, -1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0026, code lost:
        if (r3.f12493o0 != false) goto L17;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m13580d() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f12487l0
            monitor-enter(r0)
            boolean r1 = r3.f12446I     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L1c
            boolean r1 = r3.f12489m0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            boolean r1 = r3.f12491n0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            boolean r1 = r3.f12493o0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            boolean r1 = r3.f12495p0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            boolean r1 = r3.f12497q0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            goto L28
        L1c:
            boolean r1 = r3.f12489m0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            boolean r1 = r3.f12491n0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
            boolean r1 = r3.f12493o0     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L2a
        L28:
            r1 = 1
            goto L2b
        L2a:
            r1 = 0
        L2b:
            if (r1 == 0) goto L3e
            com.megvii.lv5.sdk.base.BaseView r1 = r3.getView()     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L3e
            com.megvii.lv5.sdk.base.BaseView r1 = r3.getView()     // Catch: java.lang.Throwable -> L40
            com.megvii.lv5.t r1 = (com.megvii.lv5.InterfaceC5612t) r1     // Catch: java.lang.Throwable -> L40
            int r2 = r3.f12442E     // Catch: java.lang.Throwable -> L40
            r1.mo13013a(r2)     // Catch: java.lang.Throwable -> L40
        L3e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            return
        L40:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5404d0.m13580d():void");
    }

    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    public void detach() {
        try {
            C5665z0.m12886a().m12884b();
            synchronized (this.f12466b) {
                if (getModel() != null) {
                    getModel().m13152d();
                }
            }
            m13562u();
            m13563t();
            m13565r();
            m13578e();
            m13561v();
            cleanFiles(this.f12442E);
            super.detach();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    public void m13578e() {
        if (m13573j()) {
            this.f12454Q.post(new RunnableC5405a(this));
        }
    }

    /* renamed from: f */
    public final void m13577f() {
        String[][] strArr = this.f12468c;
        if (strArr != null && strArr.length > 1) {
            if (this.f12472e == strArr.length - 1) {
                this.f12472e = 0;
            }
            int i = this.f12472e;
            f12437x0 = strArr[i];
            this.f12472e = i + 1;
        }
        String[] strArr2 = f12437x0;
        int length = (strArr2 == null || strArr2.length == 0) ? this.f12443F.f13023r1 : strArr2.length;
        this.f12470d = length;
        this.f12439B = (this.f12474f * length) + this.f12443F.f12994i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] m13576g() {
        /*
            r5 = this;
            java.lang.String r0 = r5.f12501s0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L51
            java.lang.String r0 = r5.f12482j
            com.megvii.lv5.C5402d.f12429a = r0
            com.megvii.lv5.d r0 = com.megvii.lv5.C5402d.C5403a.f12436a
            java.lang.String r2 = r5.f12500s
            int r3 = r5.f12502t
            java.lang.String r4 = "whitebalance_exposure_video_detect"
            org.json.JSONObject r2 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r2)
            java.lang.String r2 = r5.f12501s0
            boolean r2 = r5.m13589a(r2)
            if (r2 == 0) goto L3f
            java.lang.String r2 = r5.f12482j
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = r5.f12500s
            int r3 = r5.f12502t
            java.lang.String r4 = "whitebalance_exposure_video_detect_pass"
            org.json.JSONObject r0 = r0.m13599a(r4, r2, r3)
            com.megvii.lv5.C5399c3.m13606a(r0)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r5.f12501s0
            r0.<init>(r2)
            goto L52
        L3f:
            java.lang.String r2 = r5.f12482j
            com.megvii.lv5.C5402d.f12429a = r2
            java.lang.String r2 = r5.f12500s
            int r3 = r5.f12502t
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
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5404d0.m13576g():byte[]");
    }

    /* renamed from: h */
    public final boolean m13575h() {
        int i = this.f12440C;
        return (i == 1 || i == 3) && !TextUtils.isEmpty(getView().mo12999e());
    }

    /* renamed from: i */
    public final boolean m13574i() {
        int i = this.f12440C;
        return (i == 1 || i == 2) && !TextUtils.isEmpty(getView().mo12999e());
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x027b, code lost:
        if (r42.f12443F.f12976c > 1) goto L61;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0204 A[Catch: all -> 0x0250, TryCatch #0 {all -> 0x0250, blocks: (B:36:0x01d5, B:47:0x020b, B:49:0x0211, B:52:0x0232, B:51:0x0217, B:45:0x0204, B:39:0x01f8), top: B:91:0x01d5 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0217 A[Catch: all -> 0x0250, TryCatch #0 {all -> 0x0250, blocks: (B:36:0x01d5, B:47:0x020b, B:49:0x0211, B:52:0x0232, B:51:0x0217, B:45:0x0204, B:39:0x01f8), top: B:91:0x01d5 }] */
    @Override // com.megvii.lv5.sdk.base.DetectBasePresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init() {
        /*
            Method dump skipped, instructions count: 931
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5404d0.init():void");
    }

    /* renamed from: j */
    public final boolean m13573j() {
        int i = this.f12443F.f12952R1;
        if (i != 0) {
            return (i == 2 && getView().getScreenRecordContext() == null) ? false : true;
        }
        return false;
    }

    /* renamed from: k */
    public void m13572k() {
        this.f12471d0 = true;
    }

    /* renamed from: l */
    public final String m13571l() {
        String[] strArr = f12437x0;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String str = strArr[this.f12462Y];
        this.f12461X = str;
        String replace = str.replace("0x", "#");
        int i = this.f12462Y;
        if (i < this.f12470d - 1) {
            this.f12462Y = i + 1;
        }
        return replace;
    }

    /* renamed from: m */
    public final void m13570m() {
        MegliveModeImpl model = getModel();
        this.f12443F.getClass();
        C5515n0 c5515n0 = this.f12443F;
        model.m13163a(0.5f, c5515n0.f12932L, c5515n0.f12935M, c5515n0.f12938N, 0.5f, c5515n0.f12941O, c5515n0.f12947Q, c5515n0.f12944P, c5515n0.f12950R, c5515n0.f12953S, c5515n0.f12956T, c5515n0.f12958U, c5515n0.f12960V, c5515n0.f12962W, c5515n0.f12964X, c5515n0.f12966Y, 0.99f, c5515n0.f12968Z, c5515n0.f12971a0, c5515n0.f13022r0, c5515n0.f13024s, c5515n0.f13030u, c5515n0.f13027t);
    }

    /* renamed from: n */
    public final void m13569n() {
        String str = "setThresholdMirror: getFaceMinSize = " + this.f12443F.f13024s;
        String str2 = "setThresholdMirror: isCheckMultyFace = " + this.f12443F.f13030u;
        String str3 = "setThresholdMirror: getFaceMultyMinSize = " + this.f12443F.f13027t;
        MegliveModeImpl model = getModel();
        this.f12443F.getClass();
        C5515n0 c5515n0 = this.f12443F;
        model.m13163a(0.5f, c5515n0.f13033v, c5515n0.f13036w, c5515n0.f13039x, 0.5f, c5515n0.f13042y, c5515n0.f12899A, c5515n0.f13045z, c5515n0.f12902B, c5515n0.f12905C, c5515n0.f12908D, c5515n0.f12911E, c5515n0.f12914F, c5515n0.f12917G, c5515n0.f12920H, c5515n0.f12923I, 0.99f, c5515n0.f12926J, c5515n0.f12929K, c5515n0.f13022r0, c5515n0.f13024s, c5515n0.f13030u, c5515n0.f13027t);
    }

    /* renamed from: o */
    public final void m13568o() {
        if (!m13574i()) {
            this.f12497q0 = true;
            return;
        }
        try {
            C5475i1 c5475i1 = new C5475i1(getView().getContext(), "fmp_all_frames");
            this.f12458U = c5475i1;
            c5475i1.f12804h = EnumC5488k1.AllFrames;
            if (this.f12511y) {
                AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a = this.f12509w0;
                C5492l c5492l = this.mCameraManager;
                new C5482j1(c5475i1, interfaceC5461a, c5492l.f12849d, c5492l.f12848c);
            }
            this.f12458U.m13453c();
            this.f12458U.m13452d();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void m13567p() {
        try {
            System.currentTimeMillis();
            this.f12476g = new C5412h();
            this.f12478h = System.currentTimeMillis();
            C5492l c5492l = this.mCameraManager;
            this.f12480i = c5492l.f12852g;
            if (!c5492l.m13444b()) {
                this.f12480i -= 180;
            }
            String str = "startDetect: angle = " + this.f12480i;
            if (this.f12476g != null) {
                getModel().m13151e();
                getModel().m13150f();
                C5412h c5412h = this.f12476g;
                c5412h.f12527a = true;
                c5412h.start();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: q */
    public final void m13566q() {
        try {
            C5475i1 c5475i1 = new C5475i1(getView().getContext(), "fmp_wb");
            this.f12457T = c5475i1;
            if (this.f12511y) {
                AbstractRunnableC5460h1.InterfaceC5461a interfaceC5461a = this.f12507v0;
                C5492l c5492l = this.mCameraManager;
                new C5482j1(c5475i1, interfaceC5461a, c5492l.f12849d, c5492l.f12848c);
            }
            this.f12457T.m13453c();
            this.f12457T.m13452d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: r */
    public final void m13565r() {
        if (m13574i()) {
            try {
                if (this.f12458U != null) {
                    C5628v2.m12958b("ActionFlashDetectPresen", "all frames stopRecording...");
                    this.f12458U.m13451e();
                    this.f12499r0 = this.f12458U.f12797a;
                    this.f12458U = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: s */
    public void m13564s() {
        try {
            C5412h c5412h = this.f12476g;
            if (c5412h != null) {
                c5412h.f12527a = false;
                getModel().m13149g();
                this.f12476g.interrupt();
                this.f12476g = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: t */
    public final void m13563t() {
        try {
            if (this.f12456S != null) {
                C5628v2.m12958b("FlashDetect", "liveness stopRecording...");
                this.f12456S.m13451e();
                this.f12512z = this.f12456S.f12797a;
                this.f12456S = null;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: u */
    public final void m13562u() {
        try {
            if (this.f12455R != null) {
                C5628v2.m12958b("FlashDetect", "action liveness stopRecording...");
                this.f12455R.m13451e();
                this.f12438A = this.f12455R.f12797a;
                this.f12455R = null;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: v */
    public final void m13561v() {
        try {
            if (this.f12457T != null) {
                C5628v2.m12958b("FlashDetect", "wb stopRecording...");
                this.f12457T.m13451e();
                this.f12501s0 = this.f12457T.f12797a;
                this.f12457T = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: w */
    public void m13560w() {
        int bestImageActionIndex;
        if (getModel() == null) {
            return;
        }
        MegliveModeImpl model = getModel();
        model.getClass();
        synchronized (MegliveModeImpl.class) {
            bestImageActionIndex = model.f13283a == 0 ? -1 : MegLiveDetector.m13440a().getBestImageActionIndex(model.f13283a);
        }
        String str = "getImageBest: index = " + bestImageActionIndex;
        String str2 = "getImageBest: datas size = " + this.f12445H.size();
        if (bestImageActionIndex >= 0 && bestImageActionIndex < this.f12445H.size()) {
            MegliveModeImpl model2 = getModel();
            byte[] bArr = this.f12445H.get(bestImageActionIndex).f12686a;
            int i = C5486k.f12832b;
            int i2 = C5486k.f12831a;
            model2.getClass();
            synchronized (MegliveModeImpl.class) {
                if (model2.f13283a != 0) {
                    MegLiveDetector.m13440a().setActionBestImage(model2.f13283a, bArr, 0, i, i2, bestImageActionIndex);
                }
            }
        }
        this.f12445H.clear();
    }

    /* renamed from: a */
    public static boolean m13593a(C5404d0 c5404d0) {
        if (c5404d0.f12488m != 3) {
            return true;
        }
        int i = c5404d0.f12484k;
        if (i != 1 && (i != 2 || System.currentTimeMillis() - c5404d0.f12478h > 2000)) {
            return true;
        }
        return C5665z0.m12886a().m12883b(c5404d0.getView().getContext());
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5504d
    /* renamed from: a */
    public void mo12929a(byte[] bArr) {
        C5402d.f12429a = this.f12482j;
        C5402d c5402d = C5402d.C5403a.f12436a;
        C5399c3.m13606a(c5402d.m13599a("pass_hd_image", this.f12500s, this.f12502t));
        if (this.f12448K) {
            C5402d.f12429a = this.f12482j;
            C5399c3.m13606a(c5402d.m13599a("enter_whitebalance", this.f12500s, this.f12502t));
            m13566q();
        } else if (this.f12449L) {
            C5402d.f12429a = this.f12482j;
            C5399c3.m13606a(c5402d.m13599a("enter_exposure", this.f12500s, this.f12502t));
            m13566q();
        }
        this.mFrameDataQueueHD.offer(bArr);
        this.f12491n0 = true;
        m13580d();
    }

    @Override // com.megvii.lv5.AbstractC5500m.InterfaceC5505e
    /* renamed from: a */
    public void mo13438a(double d) {
        float f = (float) d;
        this.f12444G = f;
        if (f == Float.POSITIVE_INFINITY) {
            try {
                this.f12444G = 0.0f;
            } catch (Throwable th) {
                th.printStackTrace();
                this.f12444G = 0.0f;
            }
        }
        if (this.f12444G < 0.0f) {
            C5402d.f12429a = this.f12482j;
            String str = "fail_ev:" + C5402d.f12435g[1];
            String str2 = this.f12500s;
            int i = this.f12502t;
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
        C5628v2.m12959a("onEVCallback", "evLight:" + this.f12444G);
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0170 A[Catch: Exception -> 0x0086, TryCatch #0 {Exception -> 0x0086, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:10:0x0033, B:12:0x003b, B:15:0x0046, B:17:0x004d, B:21:0x0065, B:23:0x006a, B:26:0x0075, B:38:0x0099, B:40:0x00a3, B:41:0x00a7, B:43:0x00be, B:45:0x00c2, B:48:0x00c7, B:50:0x00cd, B:67:0x0134, B:53:0x00d7, B:55:0x00e7, B:57:0x00ee, B:59:0x00fc, B:61:0x011a, B:63:0x011f, B:66:0x0130, B:68:0x0138, B:71:0x013d, B:73:0x0144, B:75:0x014c, B:77:0x0154, B:79:0x0170, B:81:0x017b, B:87:0x0189, B:90:0x0196, B:92:0x01a1, B:72:0x0140, B:18:0x0056, B:20:0x005d), top: B:96:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0189 A[Catch: Exception -> 0x0086, TRY_ENTER, TryCatch #0 {Exception -> 0x0086, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:10:0x0033, B:12:0x003b, B:15:0x0046, B:17:0x004d, B:21:0x0065, B:23:0x006a, B:26:0x0075, B:38:0x0099, B:40:0x00a3, B:41:0x00a7, B:43:0x00be, B:45:0x00c2, B:48:0x00c7, B:50:0x00cd, B:67:0x0134, B:53:0x00d7, B:55:0x00e7, B:57:0x00ee, B:59:0x00fc, B:61:0x011a, B:63:0x011f, B:66:0x0130, B:68:0x0138, B:71:0x013d, B:73:0x0144, B:75:0x014c, B:77:0x0154, B:79:0x0170, B:81:0x017b, B:87:0x0189, B:90:0x0196, B:92:0x01a1, B:72:0x0140, B:18:0x0056, B:20:0x005d), top: B:96:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0196 A[Catch: Exception -> 0x0086, TryCatch #0 {Exception -> 0x0086, blocks: (B:4:0x0008, B:6:0x0011, B:8:0x002a, B:10:0x0033, B:12:0x003b, B:15:0x0046, B:17:0x004d, B:21:0x0065, B:23:0x006a, B:26:0x0075, B:38:0x0099, B:40:0x00a3, B:41:0x00a7, B:43:0x00be, B:45:0x00c2, B:48:0x00c7, B:50:0x00cd, B:67:0x0134, B:53:0x00d7, B:55:0x00e7, B:57:0x00ee, B:59:0x00fc, B:61:0x011a, B:63:0x011f, B:66:0x0130, B:68:0x0138, B:71:0x013d, B:73:0x0144, B:75:0x014c, B:77:0x0154, B:79:0x0170, B:81:0x017b, B:87:0x0189, B:90:0x0196, B:92:0x01a1, B:72:0x0140, B:18:0x0056, B:20:0x005d), top: B:96:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x019f  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] m13595a(int r27) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5404d0.m13595a(int):byte[]");
    }
}
