package com.megvii.kas.livenessdetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import com.megvii.kas.livenessdetection.DetectionConfig;
import com.megvii.kas.livenessdetection.DetectionFrame;
import com.megvii.kas.livenessdetection.bean.FaceIDDataStruct;
import com.megvii.kas.livenessdetection.bean.FaceInfo;
import com.megvii.kas.livenessdetection.impl.C5323a;
import com.megvii.kas.livenessdetection.impl.C5324b;
import com.megvii.kas.livenessdetection.obf.C5325a;
import com.megvii.kas.livenessdetection.obf.C5327b;
import com.megvii.kas.livenessdetection.obf.C5328c;
import com.megvii.kas.livenessdetection.obf.C5330d;
import com.megvii.kas.livenessdetection.obf.C5331e;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Detector {
    public static final int DETECTOR_INIT_FAILED_BADCIPHER = 4;
    public static final int DETECTOR_INIT_FAILED_EXPIRE = 5;
    public static final int DETECTOR_INIT_FAILED_INVALIDMODEL = 1;
    public static final int DETECTOR_INIT_FAILED_NATIVEINITFAILED = 3;
    public static final int DETECTOR_INIT_FAILED_SHAREDLIBLOADFAILED = 2;
    public static final int DETECTOR_INIT_OK = 0;

    /* renamed from: d */
    private static boolean f12284d;

    /* renamed from: a */
    private DetectionConfig f12285a;

    /* renamed from: b */
    private long f12286b;

    /* renamed from: e */
    private Context f12288e;

    /* renamed from: f */
    private C5331e f12289f;

    /* renamed from: g */
    private BlockingQueue<C5324b> f12290g;

    /* renamed from: h */
    private C5315a f12291h;

    /* renamed from: i */
    private DetectionListener f12292i;

    /* renamed from: j */
    private boolean f12293j;

    /* renamed from: k */
    private Handler f12294k;

    /* renamed from: l */
    private boolean f12295l;

    /* renamed from: m */
    private C5325a f12296m;

    /* renamed from: n */
    private Map<String, DetectionFrame> f12297n;

    /* renamed from: t */
    private ArrayList<DetectionFrame> f12303t;

    /* renamed from: c */
    private long f12287c = 10;

    /* renamed from: o */
    private boolean f12298o = true;

    /* renamed from: p */
    private C5324b f12299p = null;

    /* renamed from: q */
    private C5324b f12300q = null;

    /* renamed from: r */
    private long f12301r = -1;

    /* renamed from: s */
    private DetectionType f12302s = DetectionType.NONE;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum DetectionFailedType {
        ACTIONBLEND,
        NOTVIDEO,
        TIMEOUT,
        MASK,
        FACENOTCONTINUOUS,
        TOOMANYFACELOST,
        FACELOSTNOTCONTINUOUS
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface DetectionListener {
        void onDetectionFailed(DetectionFailedType detectionFailedType);

        DetectionType onDetectionSuccess(DetectionFrame detectionFrame);

        void onFrameDetected(long j, DetectionFrame detectionFrame);
    }

    public static String getVersion() {
        return "MegLive 2.4.7A";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native String nativeDetection(long j, int i, byte[] bArr, int i2, int i3, int i4);

    private native String nativeEncode(long j, byte[] bArr);

    private native String nativeFaceQuality(long j, byte[] bArr, int i, int i2);

    private static native String nativeGetVersion();

    private native long nativeRawInit(Context context, byte[] bArr, String str, String str2, String str3);

    private native void nativeRelease(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeReset(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void waitNormal(long j);

    /* renamed from: a */
    static /* synthetic */ boolean m13684a(Detector detector, boolean z) {
        detector.f12293j = true;
        return true;
    }

    /* renamed from: b */
    static /* synthetic */ boolean m13679b(Detector detector, boolean z) {
        detector.f12295l = false;
        return false;
    }

    public Detector(Context context, DetectionConfig detectionConfig) {
        this.f12285a = null;
        this.f12286b = 0L;
        this.f12293j = false;
        this.f12295l = true;
        if (detectionConfig == null) {
            this.f12285a = new DetectionConfig.Builder().build();
        }
        this.f12288e = context.getApplicationContext();
        this.f12285a = detectionConfig;
        this.f12286b = 0L;
        this.f12293j = false;
        this.f12295l = true;
        this.f12296m = new C5325a();
        this.f12289f = new C5331e(this.f12288e);
        this.f12297n = new HashMap();
    }

    public synchronized boolean init(Context context, String str) {
        return m13692a(context, str, (byte[]) null, (String) null, (String) null) == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0021 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0023 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean init(android.content.Context r9, byte[] r10, java.lang.String r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = 0
            r1 = 1
            if (r11 == 0) goto L12
            java.lang.String r2 = "W6VLf6PitAIkKiFuVXBeTe54CSc8jB"
            boolean r11 = r11.equals(r2)     // Catch: java.lang.Throwable -> L10
            if (r11 != 0) goto Le
            goto L12
        Le:
            r11 = r0
            goto L13
        L10:
            r9 = move-exception
            goto L25
        L12:
            r11 = r1
        L13:
            r8.f12298o = r11     // Catch: java.lang.Throwable -> L10
            r4 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            r5 = r10
            int r9 = r2.m13692a(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L10
            if (r9 != 0) goto L23
            monitor-exit(r8)
            return r1
        L23:
            monitor-exit(r8)
            return r0
        L25:
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.kas.livenessdetection.Detector.init(android.content.Context, byte[], java.lang.String):boolean");
    }

    public synchronized int init(Context context, byte[] bArr, String str, String str2) {
        boolean z;
        if (str2 != null) {
            try {
                if (str2.equals("W6VLf6PitAIkKiFuVXBeTe54CSc8jB")) {
                    z = false;
                    this.f12298o = z;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        z = true;
        this.f12298o = z;
        return m13692a(context, (String) null, bArr, str, (String) null);
    }

    public synchronized int init(Context context, byte[] bArr, String str, String str2, String str3) {
        return m13692a(context, (String) null, bArr, str, str2);
    }

    /* renamed from: a */
    private synchronized int m13692a(Context context, String str, byte[] bArr, String str2, String str3) {
        this.f12288e = context;
        if (str == null && bArr == null) {
            return 1;
        }
        byte[] m13643a = bArr == null ? C5327b.m13643a(str) : bArr;
        if (m13643a != null && "b3c61531d3a785d8af140218304940e5b24834d3".equalsIgnoreCase(C5327b.m13641a(m13643a))) {
            if (f12284d || C5328c.m13639a(context.getApplicationContext()).m13635a("livenessdetection_ka", "v2.4.7") || (str3 != null && C5327b.m13640b(str3))) {
                try {
                    if (System.currentTimeMillis() > new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(nativeGetVersion().split(",")[1]).getTime()) {
                        return 5;
                    }
                } catch (Exception unused) {
                }
                release();
                this.f12290g = new LinkedBlockingDeque(3);
                this.f12286b = nativeRawInit(context, m13643a, str2, this.f12289f.m13626a("cb072839e1e240a23baae123ca6cf165") + ":" + this.f12289f.m13626a("e2380b201325a8f252636350338aeae8"), this.f12285a.toJsonString());
                if (this.f12286b == 0) {
                    return 3;
                }
                this.f12291h = new C5315a();
                this.f12291h.start();
                this.f12302s = DetectionType.NONE;
                this.f12294k = new Handler(Looper.getMainLooper());
                this.f12303t = new ArrayList<>();
                return 0;
            }
            return 2;
        }
        return 1;
    }

    public synchronized void release() {
        if (this.f12291h != null) {
            this.f12291h.interrupt();
            try {
                this.f12291h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f12291h = null;
        }
        if (this.f12294k != null) {
            this.f12294k.removeCallbacksAndMessages(null);
            this.f12294k = null;
        }
        if (this.f12290g != null) {
            this.f12290g.clear();
            this.f12290g = null;
        }
        if (this.f12303t != null) {
            this.f12303t.clear();
            this.f12303t = null;
        }
        if (this.f12286b != 0) {
            nativeRelease(this.f12286b);
        }
        this.f12286b = 0L;
    }

    public void enableDebug(boolean z) {
        if (z) {
            C5330d.m13632a();
        } else {
            C5330d.m13629b();
        }
    }

    public String getLog() {
        C5325a c5325a = this.f12296m;
        if (c5325a == null) {
            return null;
        }
        return c5325a.toString();
    }

    public DetectionType getCurDetectType() {
        return this.f12302s;
    }

    public boolean doDetection(byte[] bArr, int i, int i2, int i3) {
        DetectionType detectionType;
        if (this.f12286b == 0 || this.f12292i == null || this.f12302s == DetectionType.DONE || (detectionType = this.f12302s) == null || this.f12293j) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(this.f12286b != 0);
            objArr[1] = Boolean.valueOf(this.f12292i == null);
            C5330d.m13628b(String.format("detector inited:%b, detectionlistener is null:%b", objArr));
            return false;
        }
        try {
            return this.f12290g.offer(new C5324b(bArr, i, i2, i3, detectionType));
        } catch (Exception unused) {
            return false;
        }
    }

    public synchronized void setDetectionListener(DetectionListener detectionListener) {
        this.f12292i = detectionListener;
    }

    static {
        try {
            System.loadLibrary("livenessdetection_ka_v2.4.7");
            f12284d = true;
        } catch (UnsatisfiedLinkError unused) {
            C5330d.m13628b("static load library error ");
            f12284d = false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum DetectionType {
        NONE(0),
        BLINK(1),
        MOUTH(2),
        POS_YAW(3),
        POS_PITCH(4),
        POS_YAW_LEFT(7),
        POS_YAW_RIGHT(8),
        DONE(6),
        POS_PITCH_UP(9),
        POS_PITCH_DOWN(10),
        AIMLESS(-1);
        
        private int mInterVal;

        DetectionType(int i) {
            this.mInterVal = -1;
            this.mInterVal = i;
        }
    }

    public synchronized void changeDetectionType(DetectionType detectionType) {
        if (this.f12286b == 0) {
            return;
        }
        if (detectionType == null) {
            throw new RuntimeException("DetectionType could not be null");
        }
        this.f12293j = false;
        this.f12302s = detectionType;
        nativeReset(this.f12286b);
        this.f12301r = System.currentTimeMillis();
        this.f12295l = true;
        this.f12296m.m13649a(detectionType);
    }

    public synchronized void reset() {
        if (this.f12286b == 0) {
            return;
        }
        this.f12299p = null;
        this.f12300q = null;
        this.f12303t = new ArrayList<>();
        this.f12293j = false;
        changeDetectionType(DetectionType.NONE);
        this.f12295l = true;
        this.f12296m.m13651a();
        this.f12297n.clear();
    }

    public synchronized void resetAction() {
        if (this.f12286b == 0) {
            return;
        }
        this.f12293j = false;
        this.f12295l = true;
        changeDetectionType(this.f12302s);
    }

    public synchronized ArrayList<DetectionFrame> getValidFrame() {
        if (this.f12303t == null) {
            return null;
        }
        ArrayList<DetectionFrame> arrayList = new ArrayList<>(this.f12303t);
        arrayList.add(0, this.f12299p);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.megvii.kas.livenessdetection.Detector$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5315a extends Thread {

        /* renamed from: b */
        private C5327b f12305b = new C5327b();

        public C5315a() {
            this.f12305b.m13642a(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            while (true) {
                try {
                    final C5324b c5324b = (C5324b) Detector.this.f12290g.take();
                    if (c5324b != null && Detector.this.f12286b != 0 && Detector.this.f12302s != DetectionType.DONE) {
                        if (System.currentTimeMillis() > Detector.this.f12301r + Detector.this.f12285a.timeout && Detector.this.f12302s != DetectionType.NONE && Detector.this.f12302s != DetectionType.AIMLESS) {
                            if (!Detector.this.f12293j) {
                                m13665a(DetectionFailedType.TIMEOUT, Detector.this.f12292i, c5324b);
                            }
                        } else {
                            byte[] yUVData = c5324b.getYUVData();
                            int imageWidth = c5324b.getImageWidth();
                            int imageHeight = c5324b.getImageHeight();
                            int rotation = c5324b.getRotation();
                            DetectionType detectionType = Detector.this.f12302s;
                            final DetectionListener detectionListener = Detector.this.f12292i;
                            if (detectionType != null && Detector.this.f12286b != 0 && detectionListener != null && !Detector.this.f12293j) {
                                if (Detector.this.f12295l) {
                                    Detector.m13679b(Detector.this, false);
                                    Detector.this.waitNormal(Detector.this.f12286b);
                                }
                                String nativeDetection = Detector.this.nativeDetection(Detector.this.f12286b, detectionType.mInterVal, yUVData, imageWidth, imageHeight, rotation);
                                try {
                                    JSONObject jSONObject = new JSONObject(nativeDetection);
                                    if (!Detector.this.f12293j && detectionType == c5324b.m13657a()) {
                                        c5324b.m13653a(nativeDetection, Detector.this.f12285a, this.f12305b);
                                        if (detectionType != DetectionType.NONE && detectionType != DetectionType.AIMLESS) {
                                            if (c5324b.hasFace()) {
                                                Detector.this.f12300q = c5324b;
                                                Detector.this.m13691a(c5324b);
                                            }
                                            switch (jSONObject.getInt("result")) {
                                                case 1:
                                                    if (c5324b == null || !c5324b.hasFace() || !c5324b.getFaceInfo().faceTooLarge) {
                                                        Detector.this.f12303t.add(Detector.this.f12300q);
                                                        Detector.m13684a(Detector.this, true);
                                                        c5324b.setFrameType(DetectionFrame.FrameType.NONE);
                                                        Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.3
                                                            @Override // java.lang.Runnable
                                                            public final void run() {
                                                                detectionListener.onFrameDetected((Detector.this.f12301r + Detector.this.f12285a.timeout) - System.currentTimeMillis(), c5324b);
                                                                DetectionType onDetectionSuccess = detectionListener.onDetectionSuccess(c5324b);
                                                                if (onDetectionSuccess == null || onDetectionSuccess == DetectionType.DONE) {
                                                                    Detector.this.f12302s = DetectionType.DONE;
                                                                    if (Detector.this.f12290g != null) {
                                                                        Detector.this.f12290g.clear();
                                                                    }
                                                                    if (Detector.this.f12296m != null) {
                                                                        Detector.this.f12296m.m13649a(Detector.this.f12302s);
                                                                        Detector.m13671j(Detector.this);
                                                                        return;
                                                                    }
                                                                    return;
                                                                }
                                                                Detector.this.changeDetectionType(onDetectionSuccess);
                                                            }
                                                        });
                                                        continue;
                                                    } else {
                                                        Detector.this.nativeReset(Detector.this.f12286b);
                                                        break;
                                                    }
                                                case 2:
                                                    c5324b.setFrameType(DetectionFrame.FrameType.NONE);
                                                    m13664a(c5324b);
                                                    Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.4
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            detectionListener.onFrameDetected((Detector.this.f12301r + Detector.this.f12285a.timeout) - System.currentTimeMillis(), c5324b);
                                                        }
                                                    });
                                                    continue;
                                                case 3:
                                                    Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.5
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            detectionListener.onFrameDetected((Detector.this.f12301r + Detector.this.f12285a.timeout) - System.currentTimeMillis(), c5324b);
                                                        }
                                                    });
                                                    continue;
                                                case 4:
                                                    m13665a(DetectionFailedType.NOTVIDEO, detectionListener, c5324b);
                                                    continue;
                                                case 5:
                                                    m13665a(DetectionFailedType.ACTIONBLEND, detectionListener, c5324b);
                                                    continue;
                                                case 6:
                                                    C5330d.m13630a("LivenessDetection", "wait for normal success");
                                                    c5324b.setFrameType(DetectionFrame.FrameType.WAITINGNORMAL);
                                                    m13664a(c5324b);
                                                    Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.6
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            detectionListener.onFrameDetected((Detector.this.f12301r + Detector.this.f12285a.timeout) - System.currentTimeMillis(), c5324b);
                                                        }
                                                    });
                                                    continue;
                                                case 7:
                                                    C5330d.m13630a("LivenessDetection", "is waiting for normal");
                                                    c5324b.setFrameType(DetectionFrame.FrameType.WAITINGNORMAL);
                                                    m13664a(c5324b);
                                                    Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.7
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            detectionListener.onFrameDetected((Detector.this.f12301r + Detector.this.f12285a.timeout) - System.currentTimeMillis(), c5324b);
                                                        }
                                                    });
                                                    continue;
                                                case 8:
                                                    m13665a(DetectionFailedType.MASK, detectionListener, c5324b);
                                                    continue;
                                                case 9:
                                                    m13665a(DetectionFailedType.FACENOTCONTINUOUS, detectionListener, c5324b);
                                                    continue;
                                                case 10:
                                                    m13665a(DetectionFailedType.TOOMANYFACELOST, detectionListener, c5324b);
                                                    continue;
                                                case 11:
                                                    m13665a(DetectionFailedType.FACELOSTNOTCONTINUOUS, detectionListener, c5324b);
                                                    continue;
                                                default:
                                                    continue;
                                            }
                                        }
                                        c5324b.setFrameType(DetectionFrame.FrameType.NONE);
                                        Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.2
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                detectionListener.onFrameDetected(Detector.this.f12285a.timeout, c5324b);
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }

        /* renamed from: a */
        private void m13665a(final DetectionFailedType detectionFailedType, final DetectionListener detectionListener, final DetectionFrame detectionFrame) {
            Detector.this.f12296m.m13650a(detectionFailedType);
            if (Detector.this.f12296m != null && Detector.this.f12289f != null) {
                Detector.m13671j(Detector.this);
            }
            Detector.m13684a(Detector.this, true);
            Detector.this.f12294k.post(new Runnable() { // from class: com.megvii.kas.livenessdetection.Detector.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    detectionListener.onFrameDetected((Detector.this.f12301r + Detector.this.f12285a.timeout) - System.currentTimeMillis(), detectionFrame);
                    detectionListener.onDetectionFailed(detectionFailedType);
                }
            });
        }

        /* renamed from: a */
        private void m13664a(C5324b c5324b) {
            if (Detector.this.f12299p == null) {
                Detector.this.f12299p = c5324b;
            }
            if (c5324b.m13654a(Detector.this.f12299p)) {
                Detector.this.f12299p = c5324b;
            }
        }
    }

    public synchronized DetectionFrame faceQualityDetection(Bitmap bitmap) {
        C5323a c5323a = new C5323a(bitmap);
        byte[] m13661a = c5323a.m13661a();
        int imageWidth = c5323a.getImageWidth();
        int imageHeight = c5323a.getImageHeight();
        if (m13661a != null && imageWidth != -1 && imageHeight != -1) {
            c5323a.m13660a(nativeFaceQuality(this.f12286b, m13661a, imageWidth, imageHeight), this.f12285a, new C5327b());
            return c5323a;
        }
        return null;
    }

    public FaceIDDataStruct getFaceIDDataStruct(int i) {
        JSONObject jSONObject = new JSONObject();
        FaceIDDataStruct faceIDDataStruct = new FaceIDDataStruct();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        C5324b c5324b = this.f12299p;
        try {
            jSONObject2.put("image_best", m13690a((DetectionFrame) c5324b, i, "image_best", faceIDDataStruct, true));
            if (this.f12303t != null) {
                int i2 = 0;
                while (i2 < this.f12303t.size()) {
                    StringBuilder sb = new StringBuilder("image_action");
                    int i3 = i2 + 1;
                    sb.append(i3);
                    jSONObject2.put(sb.toString(), m13690a(this.f12303t.get(i2), i, "image_action" + i3, faceIDDataStruct, true));
                    i2 = i3;
                }
            }
            if (this.f12297n != null) {
                for (Map.Entry<String, DetectionFrame> entry : this.f12297n.entrySet()) {
                    JSONObject m13683b = m13683b(entry.getValue());
                    if (m13683b != null) {
                        jSONObject3.put(entry.getKey(), m13683b);
                    }
                }
            }
            jSONObject2.put("image_env", m13690a((DetectionFrame) c5324b, i, "image_env", faceIDDataStruct, false));
            jSONObject.put("images", jSONObject2);
            jSONObject.put("snapshot", jSONObject3);
            jSONObject.put("datetime", new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date()));
            jSONObject.put("sdk_version", getVersion());
            jSONObject.put("user_info", C5327b.m13647a());
            jSONObject.put("log", getLog());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        faceIDDataStruct.delta = nativeEncode(this.f12286b, (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes());
        return faceIDDataStruct;
    }

    /* renamed from: a */
    final void m13691a(DetectionFrame detectionFrame) {
        DetectionFrame detectionFrame2;
        DetectionFrame detectionFrame3;
        DetectionFrame detectionFrame4;
        DetectionFrame detectionFrame5;
        if (detectionFrame == null || !detectionFrame.hasFace()) {
            return;
        }
        if (Math.abs(detectionFrame.getFaceInfo().yaw) >= 0.167d && ((detectionFrame5 = this.f12297n.get("yaw")) == null || detectionFrame5.getFaceInfo() == null || detectionFrame5.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
            this.f12297n.put("yaw", detectionFrame);
        }
        if (Math.abs(detectionFrame.getFaceInfo().pitch) >= 0.111d && ((detectionFrame4 = this.f12297n.get("pitch")) == null || detectionFrame4.getFaceInfo() == null || detectionFrame4.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
            this.f12297n.put("pitch", detectionFrame);
        }
        if (Math.abs(detectionFrame.getFaceInfo().mouthHWRatio) >= 0.2f && ((detectionFrame3 = this.f12297n.get("mouth")) == null || detectionFrame3.getFaceInfo() == null || detectionFrame3.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
            this.f12297n.put("mouth", detectionFrame);
        }
        if (Math.abs(detectionFrame.getFaceInfo().leftEyeHWRatio) <= 0.3f && Math.abs(detectionFrame.getFaceInfo().rightEyeHWRatio) <= 0.3f && ((detectionFrame2 = this.f12297n.get("eye")) == null || detectionFrame2.getFaceInfo() == null || detectionFrame2.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
            this.f12297n.put("eye", detectionFrame);
        }
        if (this.f12298o) {
            DetectionFrame detectionFrame6 = this.f12297n.get("max_pitch");
            if (detectionFrame6 == null || detectionFrame6.getFaceInfo() == null || Math.abs(detectionFrame6.getFaceInfo().pitch) < Math.abs(detectionFrame.getFaceInfo().pitch)) {
                if (Math.abs(detectionFrame.getFaceInfo().pitch) > 0.2d) {
                    RectF rectF = detectionFrame.getFaceInfo().position;
                    float width = rectF.width();
                    float height = rectF.height();
                    float f = width / 10.0f;
                    rectF.left -= f;
                    rectF.right += f;
                    float f2 = height / 10.0f;
                    rectF.top -= f2;
                    rectF.bottom += f2;
                }
                this.f12297n.put("max_pitch", detectionFrame);
            }
            DetectionFrame detectionFrame7 = this.f12297n.get("max_yaw");
            if (detectionFrame7 == null || detectionFrame7.getFaceInfo() == null || Math.abs(detectionFrame7.getFaceInfo().yaw) < Math.abs(detectionFrame.getFaceInfo().yaw)) {
                if (Math.abs(detectionFrame.getFaceInfo().yaw) > 0.2d) {
                    RectF rectF2 = detectionFrame.getFaceInfo().position;
                    float width2 = rectF2.width();
                    float height2 = rectF2.height();
                    float f3 = width2 / 10.0f;
                    rectF2.left -= f3;
                    rectF2.right += f3;
                    float f4 = height2 / 10.0f;
                    rectF2.top -= f4;
                    rectF2.bottom += f4;
                }
                this.f12297n.put("max_yaw", detectionFrame);
            }
        }
    }

    public FaceIDDataStruct getFaceIDDataStruct() {
        return getFaceIDDataStruct(-1);
    }

    /* renamed from: b */
    private static JSONObject m13683b(DetectionFrame detectionFrame) {
        if (detectionFrame == null || !detectionFrame.hasFace()) {
            return null;
        }
        Rect rect = new Rect();
        byte[] imageData = detectionFrame.getImageData(rect, true, 90, 150, false, false, 0);
        if (imageData == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("image", Base64.encodeToString(imageData, 2));
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(rect.left);
            jSONArray.put(rect.top);
            jSONArray.put(rect.right);
            jSONArray.put(rect.bottom);
            jSONObject.put("rect", jSONArray);
            jSONObject.put("smooth_quality", detectionFrame.getFaceInfo().smoothQuality);
            jSONObject.put("quality", detectionFrame.getFaceInfo().faceQuality);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m13690a(DetectionFrame detectionFrame, int i, String str, FaceIDDataStruct faceIDDataStruct, boolean z) {
        byte[] imageData;
        if (detectionFrame == null || !detectionFrame.hasFace()) {
            return null;
        }
        Rect rect = new Rect();
        if (!z) {
            FaceInfo faceInfo = detectionFrame.getFaceInfo();
            imageData = detectionFrame.getImageData(rect, false, 70, (int) (150.0f / Math.min(faceInfo.position.width(), faceInfo.position.height())), false, false, 0);
        } else {
            imageData = detectionFrame.getImageData(rect, true, 70, i, false, false, 0);
        }
        if (imageData == null) {
            return null;
        }
        faceIDDataStruct.images.put(str, imageData);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("quality", detectionFrame.getFaceInfo().faceQuality);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(rect.left);
            jSONArray.put(rect.top);
            jSONArray.put(rect.right);
            jSONArray.put(rect.bottom);
            jSONObject.put("rect", jSONArray);
            jSONObject.put("checksum", C5327b.m13641a(imageData));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: j */
    static /* synthetic */ void m13671j(Detector detector) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        if (detector.f12296m != null) {
            try {
                jSONArray = new JSONArray(detector.f12289f.m13624b("8cd0604ba33e2ba7f38a56f0aec08a54"));
            } catch (Exception unused) {
                jSONArray = new JSONArray();
            }
            jSONArray.put(detector.f12296m.toString());
            if (jSONArray.length() > detector.f12287c) {
                jSONArray2 = new JSONArray();
                for (int i = 1; i < jSONArray.length(); i++) {
                    try {
                        jSONArray2.put(jSONArray.get(i));
                    } catch (JSONException unused2) {
                    }
                }
            } else {
                jSONArray2 = jSONArray;
            }
            detector.f12289f.m13625a("8cd0604ba33e2ba7f38a56f0aec08a54", !(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2));
        }
    }
}
