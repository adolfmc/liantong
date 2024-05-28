package com.example.asus.detectionandalign.p193a;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.example.asus.detectionandalign.C4243R;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.example.asus.detectionandalign.animation.C4279a;
import com.example.asus.detectionandalign.animation.C4280b;
import com.example.asus.detectionandalign.p194b.p195a.C4286b;
import com.example.asus.detectionandalign.utils.C4289a;
import com.example.asus.detectionandalign.utils.C4291c;
import com.example.asus.detectionandalign.utils.C4292d;
import com.example.asus.detectionandalign.utils.C4294f;
import com.example.asus.detectionandalign.utils.C4303m;
import com.example.asus.detectionandalign.utils.HandlerThreadC4297i;
import com.example.asus.detectionandalign.utils.LogUtils;
import com.example.landmarksdk.faceDetectionResult;
import com.example.landmarksdk.faceRecognition;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import comp.android.app.face.p381sz.camera.JCameraView;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.example.asus.detectionandalign.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4269c extends C4257a {

    /* renamed from: e */
    private static C4269c f9959e;

    /* renamed from: A */
    private int f9960A;

    /* renamed from: D */
    private int[] f9963D;

    /* renamed from: E */
    private int f9964E;

    /* renamed from: F */
    private int f9965F;

    /* renamed from: J */
    private int f9969J;

    /* renamed from: K */
    private int f9970K;

    /* renamed from: L */
    private int f9971L;

    /* renamed from: ab */
    private float[] f9987ab;

    /* renamed from: ae */
    private boolean f9990ae;

    /* renamed from: am */
    private boolean f9998am;

    /* renamed from: g */
    private Activity f10009g;

    /* renamed from: i */
    private AsyncTaskC4274b f10011i;

    /* renamed from: j */
    private AsyncTaskC4275c f10012j;

    /* renamed from: k */
    private AsyncTaskC4276d f10013k;

    /* renamed from: m */
    private AsyncTaskC4273a f10015m;

    /* renamed from: n */
    private C4289a f10016n;

    /* renamed from: o */
    private Handler f10017o;

    /* renamed from: p */
    private C4280b f10018p;

    /* renamed from: q */
    private DetectionAuthentic f10019q;

    /* renamed from: r */
    private Handler f10020r;

    /* renamed from: w */
    private String f10025w;

    /* renamed from: x */
    private String f10026x;

    /* renamed from: z */
    private StringBuffer f10028z;

    /* renamed from: f */
    private faceRecognition f10008f = new faceRecognition();

    /* renamed from: s */
    private List<String> f10021s = new ArrayList();

    /* renamed from: t */
    private List<String> f10022t = new ArrayList();

    /* renamed from: u */
    private List<String> f10023u = new ArrayList();

    /* renamed from: v */
    private List<String> f10024v = new ArrayList();

    /* renamed from: d */
    public String f10007d = "";

    /* renamed from: y */
    private String f10027y = "";

    /* renamed from: B */
    private int f9961B = 4;

    /* renamed from: C */
    private int f9962C = 144;

    /* renamed from: G */
    private int f9966G = 0;

    /* renamed from: H */
    private int f9967H = 0;

    /* renamed from: I */
    private int f9968I = 0;

    /* renamed from: M */
    private int f9972M = 500;

    /* renamed from: N */
    private int f9973N = 500;

    /* renamed from: O */
    private int f9974O = 500;

    /* renamed from: P */
    private int f9975P = 0;

    /* renamed from: Q */
    private int f9976Q = 300;

    /* renamed from: R */
    private int f9977R = 0;

    /* renamed from: S */
    private int f9978S = 0;

    /* renamed from: T */
    private double f9979T = 0.2d;

    /* renamed from: U */
    private double f9980U = 0.2d;

    /* renamed from: V */
    private double f9981V = 0.15d;

    /* renamed from: W */
    private double f9982W = 0.5d;

    /* renamed from: X */
    private double f9983X = 0.5d;

    /* renamed from: Y */
    private float f9984Y = 0.0f;

    /* renamed from: Z */
    private float f9985Z = 0.7f;

    /* renamed from: aa */
    private float f9986aa = 0.4f;

    /* renamed from: ac */
    private boolean f9988ac = true;

    /* renamed from: ad */
    private boolean f9989ad = false;

    /* renamed from: af */
    private boolean f9991af = false;

    /* renamed from: ag */
    private boolean f9992ag = false;

    /* renamed from: ah */
    private boolean f9993ah = false;

    /* renamed from: ai */
    private boolean f9994ai = true;

    /* renamed from: aj */
    private boolean f9995aj = false;

    /* renamed from: ak */
    private boolean f9996ak = false;

    /* renamed from: al */
    private boolean f9997al = false;

    /* renamed from: an */
    private boolean f9999an = false;

    /* renamed from: ao */
    private boolean f10000ao = false;

    /* renamed from: ap */
    private boolean f10001ap = false;

    /* renamed from: aq */
    private boolean f10002aq = false;

    /* renamed from: ar */
    private boolean f10003ar = false;

    /* renamed from: as */
    private String f10004as = "";

    /* renamed from: at */
    private Runnable f10005at = new RunnableC42701();

    /* renamed from: au */
    private Runnable f10006au = new Runnable() { // from class: com.example.asus.detectionandalign.a.c.2
        @Override // java.lang.Runnable
        public void run() {
            if (C4269c.this.f10016n != null) {
                C4269c.this.f10016n.m15961a(C4269c.this.f10009g, C4269c.this.m16039f("headF"));
                if (C4269c.this.f10018p != null && C4269c.this.f9993ah) {
                    C4269c.this.f10018p.m15998b();
                }
                C4269c.this.f10017o.postDelayed(this, 4000L);
            }
        }
    };

    /* renamed from: h */
    private LogUtils f10010h = LogUtils.m15966a(true);

    /* renamed from: l */
    private AsyncTaskC4278e f10014l = new AsyncTaskC4278e(this, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.c$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC42701 implements Runnable {
        RunnableC42701() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C4269c.this.f10016n != null) {
                C4289a c4289a = C4269c.this.f10016n;
                Activity activity = C4269c.this.f10009g;
                C4269c c4269c = C4269c.this;
                c4289a.m15961a(activity, c4269c.m16039f(c4269c.f10007d));
                if (C4269c.this.f10018p != null && C4269c.this.f9987ab != null && C4269c.this.f9987ab.length > 0 && C4269c.this.f9993ah) {
                    C4269c.this.f10018p.m16000a(C4269c.this.f10007d, C4279a.m16004a(C4269c.this.f9994ai, C4269c.this.f9972M, C4269c.this.f9974O, C4269c.this.f9973N, C4269c.this.f9967H, C4269c.this.f9968I, C4269c.this.f9970K, C4269c.this.f9971L, C4269c.this.f9987ab));
                }
                C4269c.this.f10017o.postDelayed(this, 4000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.example.asus.detectionandalign.a.c$3 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static /* synthetic */ class C42723 {

        /* renamed from: a */
        static final /* synthetic */ int[] f10031a = new int[AsyncTask.Status.values().length];

        static {
            try {
                f10031a[AsyncTask.Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10031a[AsyncTask.Status.PENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.example.asus.detectionandalign.a.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4273a extends AsyncTask<Void, Void, String> {
        private AsyncTaskC4273a() {
        }

        /* synthetic */ AsyncTaskC4273a(C4269c c4269c, RunnableC42701 runnableC42701) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(Void... voidArr) {
            String str;
            String str2;
            try {
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONObject2.put("client_type", new C4294f(C4269c.this.f10009g).m15949a());
                jSONObject2.put("client_info", Build.MODEL);
                if (C4269c.this.f10028z != null) {
                    str = "extend";
                    str2 = C4269c.this.f10028z.toString();
                } else {
                    str = "extend";
                    str2 = "";
                }
                jSONObject2.put(str, str2);
                jSONObject.put("sdk_version", "sz.face.sdk.android.living_all_v1.12.1.190916_action");
                jSONObject.put("sdk_type", "1");
                jSONObject.put("data_sime_stamp", format);
                jSONObject.put("client_info", jSONObject2);
                jSONObject3.put("check_result", "0");
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("pic", C4291c.m15953b(C4269c.this.m16047d("preview")));
                Log.e("BaseAsyncTaskTool", "上传图片大小preview :" + C4269c.this.f9994ai + "  " + C4269c.this.m16047d("preview").getWidth() + "  " + C4269c.this.m16047d("preview").getHeight());
                jSONObject4.put("action_type", "0");
                jSONObject4.put("score", (double) C4269c.this.f9984Y);
                jSONArray.put(jSONObject4);
                for (int i = 0; i < C4269c.this.f10024v.size(); i++) {
                    JSONObject jSONObject5 = new JSONObject();
                    String str3 = (String) C4269c.this.f10024v.get(i);
                    jSONObject5.put("pic", C4291c.m15953b(C4269c.this.m16047d(str3)));
                    Log.e("BaseAsyncTaskTool", "上传图片大小 :" + C4269c.this.f9994ai + "  " + C4269c.this.m16047d(String.valueOf(i)).getWidth() + "  " + C4269c.this.m16047d(String.valueOf(i)).getHeight());
                    jSONObject5.put("score", C4269c.this.f10021s.get(Integer.valueOf(str3).intValue()));
                    jSONObject5.put("action_type", C4269c.this.f10022t.get(Integer.valueOf(str3).intValue()));
                    jSONArray.put(jSONObject5);
                }
                jSONObject3.put("video", "");
                jSONObject3.put("pic_list", jSONArray);
                jSONObject.put("data_type", "1");
                jSONObject.put("data_package", jSONObject3);
                System.gc();
                C4269c.this.f10010h.m15967a("packageData", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                String m15930a = C4303m.m15930a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                C4269c.this.m16240a(C4269c.this.f10025w);
                if (C4269c.this.f10021s != null) {
                    C4269c.this.f10021s.clear();
                }
                C4269c.this.f10021s = null;
                if (C4269c.this.f10022t != null) {
                    C4269c.this.f10022t.clear();
                }
                C4269c.this.f10022t = null;
                System.gc();
                return m15930a;
            } catch (NullPointerException unused) {
                C4269c c4269c = C4269c.this;
                c4269c.m16240a(c4269c.f10025w);
                C4269c.this.f10021s = null;
                C4269c.this.f10022t = null;
                System.gc();
                if (C4269c.this.f10019q != null) {
                    C4269c.this.f10019q.onSDKUsingFail("生成数据包失败", "2003");
                }
                return null;
            } catch (JSONException unused2) {
                C4269c c4269c2 = C4269c.this;
                c4269c2.m16240a(c4269c2.f10025w);
                C4269c.this.f10021s = null;
                C4269c.this.f10022t = null;
                System.gc();
                if (C4269c.this.f10019q != null) {
                    C4269c.this.f10019q.onSDKUsingFail("生成数据包失败", "2003");
                }
                return null;
            } catch (Exception e) {
                C4269c c4269c3 = C4269c.this;
                c4269c3.m16240a(c4269c3.f10025w);
                C4269c.this.f10021s = null;
                C4269c.this.f10022t = null;
                System.gc();
                e.printStackTrace();
                if (C4269c.this.f10019q != null) {
                    C4269c.this.f10019q.onSDKUsingFail("生成数据包失败", "2003");
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x005f, code lost:
            if (r6.f10032a.f10019q != null) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0061, code lost:
            r6.f10032a.f10019q.onSDKUsingFail("活体检测失败，请重试", "2002");
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x006f, code lost:
            r6.f10032a.f10020r.sendEmptyMessageDelayed(com.example.asus.detectionandalign.DetectionAuthentic.DETECT_FINISH, 0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0049, code lost:
            if (r6.f10032a.f10019q == null) goto L12;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onPostExecute(java.lang.String r7) {
            /*
                r6 = this;
                com.example.asus.detectionandalign.a.c r0 = com.example.asus.detectionandalign.p193a.C4269c.this
                com.example.asus.detectionandalign.utils.LogUtils r0 = com.example.asus.detectionandalign.p193a.C4269c.m16089a(r0)
                java.lang.String r1 = "check success："
                java.lang.String r2 = "success"
                r0.m15967a(r1, r2)
                com.example.asus.detectionandalign.a.c r0 = com.example.asus.detectionandalign.p193a.C4269c.this
                android.os.Handler r0 = com.example.asus.detectionandalign.p193a.C4269c.m16017x(r0)
                r1 = 0
                r3 = 8807(0x2267, float:1.2341E-41)
                r0.sendEmptyMessageDelayed(r3, r1)
                r0 = 8756(0x2234, float:1.227E-41)
                if (r7 == 0) goto L4c
                com.example.asus.detectionandalign.a.c r3 = com.example.asus.detectionandalign.p193a.C4269c.this
                com.example.asus.detectionandalign.utils.LogUtils r3 = com.example.asus.detectionandalign.p193a.C4269c.m16089a(r3)
                java.lang.String r4 = "check result："
                java.lang.String r5 = "not null"
                r3.m15967a(r4, r5)
                com.example.asus.detectionandalign.a.c r3 = com.example.asus.detectionandalign.p193a.C4269c.this     // Catch: java.lang.Exception -> L3f
                com.example.asus.detectionandalign.DetectionAuthentic r3 = com.example.asus.detectionandalign.p193a.C4269c.m16105P(r3)     // Catch: java.lang.Exception -> L3f
                r3.onActionImageCaptured(r7)     // Catch: java.lang.Exception -> L3f
                com.example.asus.detectionandalign.a.c r7 = com.example.asus.detectionandalign.p193a.C4269c.this     // Catch: java.lang.Exception -> L3f
                android.os.Handler r7 = com.example.asus.detectionandalign.p193a.C4269c.m16017x(r7)     // Catch: java.lang.Exception -> L3f
                r7.sendEmptyMessageDelayed(r0, r1)     // Catch: java.lang.Exception -> L3f
                goto L78
            L3f:
                r7 = move-exception
                r7.printStackTrace()
                com.example.asus.detectionandalign.a.c r7 = com.example.asus.detectionandalign.p193a.C4269c.this
                com.example.asus.detectionandalign.DetectionAuthentic r7 = com.example.asus.detectionandalign.p193a.C4269c.m16105P(r7)
                if (r7 == 0) goto L6f
                goto L61
            L4c:
                com.example.asus.detectionandalign.a.c r7 = com.example.asus.detectionandalign.p193a.C4269c.this
                com.example.asus.detectionandalign.utils.LogUtils r7 = com.example.asus.detectionandalign.p193a.C4269c.m16089a(r7)
                java.lang.String r3 = "onSDKUsingFail"
                java.lang.String r4 = "2222222222222222222"
                r7.m15967a(r3, r4)
                com.example.asus.detectionandalign.a.c r7 = com.example.asus.detectionandalign.p193a.C4269c.this
                com.example.asus.detectionandalign.DetectionAuthentic r7 = com.example.asus.detectionandalign.p193a.C4269c.m16105P(r7)
                if (r7 == 0) goto L6f
            L61:
                com.example.asus.detectionandalign.a.c r7 = com.example.asus.detectionandalign.p193a.C4269c.this
                com.example.asus.detectionandalign.DetectionAuthentic r7 = com.example.asus.detectionandalign.p193a.C4269c.m16105P(r7)
                java.lang.String r3 = "活体检测失败，请重试"
                java.lang.String r4 = "2002"
                r7.onSDKUsingFail(r3, r4)
            L6f:
                com.example.asus.detectionandalign.a.c r7 = com.example.asus.detectionandalign.p193a.C4269c.this
                android.os.Handler r7 = com.example.asus.detectionandalign.p193a.C4269c.m16017x(r7)
                r7.sendEmptyMessageDelayed(r0, r1)
            L78:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p193a.C4269c.AsyncTaskC4273a.onPostExecute(java.lang.String):void");
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            Message message = new Message();
            message.what = 519;
            C4269c.this.f10020r.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.c$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4274b extends AsyncTask<Void, Void, Void> {

        /* renamed from: b */
        private byte[] f10034b;

        AsyncTaskC4274b(byte[] bArr) {
            this.f10034b = bArr;
        }

        /* renamed from: a */
        public Bitmap m16012a(Bitmap bitmap) {
            Matrix matrix = new Matrix();
            matrix.setScale(-1.0f, 1.0f);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        /* renamed from: a */
        public Bitmap m16011a(Bitmap bitmap, float f) {
            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.setRotate(f);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            Bitmap createScaledBitmap;
            faceDetectionResult[] detectionResult;
            C4269c c4269c;
            String str;
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Bitmap m16012a = m16012a(m16011a(C4269c.this.m16237a(this.f10034b, C4269c.this.f9967H, C4269c.this.f9968I), C4269c.this.f9969J == 0 ? 90.0f : -90.0f));
                Log.e("PalmTask", "原图片宽高：" + m16012a.getWidth() + "   " + m16012a.getHeight());
                createScaledBitmap = Bitmap.createScaledBitmap(m16012a, C4269c.this.f9972M, (C4269c.this.f9972M * C4269c.this.f9967H) / C4269c.this.f9968I, false);
                Log.e("PalmTask", "照图片宽度500高等比缩放：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                if (C4269c.this.f9994ai) {
                    createScaledBitmap = C4292d.m15952a(createScaledBitmap, C4269c.this.f9974O, C4269c.this.f9973N, false);
                    Log.e("PalmTask", "按照500宽度裁剪：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                }
                LogUtils logUtils = C4269c.this.f10010h;
                logUtils.m15967a("PalmTask", "图片宽高：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                C4269c.this.m16091a(createScaledBitmap);
                long currentTimeMillis2 = System.currentTimeMillis();
                LogUtils logUtils2 = C4269c.this.f10010h;
                logUtils2.m15967a("PalmTask", "yuv....cost:" + (currentTimeMillis2 - currentTimeMillis));
                detectionResult = C4269c.this.f10008f.getDetectionResult(C4269c.this.f9963D, C4269c.this.f9964E, C4269c.this.f9965F, C4269c.this.f9962C, C4269c.this.f9961B);
                long currentTimeMillis3 = System.currentTimeMillis();
                LogUtils logUtils3 = C4269c.this.f10010h;
                logUtils3.m15967a("PalmTask", "+++++++++++++++++++++++++++++" + (currentTimeMillis3 - currentTimeMillis2));
            } catch (Exception e) {
                LogUtils logUtils4 = C4269c.this.f10010h;
                logUtils4.m15967a("PalmTask", "Error:" + e.getMessage());
            }
            if (detectionResult.length == 0 || !C4269c.this.m16241a(detectionResult[0])) {
                C4269c.this.f10020r.sendEmptyMessageDelayed(546, 0L);
                C4269c.this.f10026x = "未检测到人脸";
                C4269c.this.f10010h.m15967a("livenessDetectTask", C4269c.this.f10026x);
                c4269c = C4269c.this;
                str = C4269c.this.f10026x;
            } else {
                LogUtils logUtils5 = C4269c.this.f10010h;
                logUtils5.m15967a("FaceRec", "人脸框大小" + detectionResult[0].rect.width + "*" + detectionResult[0].rect.height);
                C4269c.this.f9987ab = detectionResult[0].raw_facial_points;
                if (!C4269c.this.m16079a("headF", C4257a.m16236a(C4269c.this.f10008f.extractLandmark(C4269c.this.f9963D, C4269c.this.f9964E, C4269c.this.f9965F, detectionResult[0], C4269c.this.f9961B)))) {
                    C4269c.this.f10020r.sendEmptyMessageDelayed(546, 0L);
                    C4269c.this.f10026x = "未正对屏幕";
                    C4269c.this.f10010h.m15967a("livenessDetectTask", C4269c.this.f10026x);
                    c4269c = C4269c.this;
                    str = C4269c.this.f10026x;
                } else if (C4269c.this.m16242a(createScaledBitmap, detectionResult)) {
                    long currentTimeMillis4 = System.currentTimeMillis();
                    float liveRecognition = C4269c.this.f10008f.liveRecognition(C4269c.this.f9963D, C4269c.this.f9964E, C4269c.this.f9965F, detectionResult[0], C4269c.this.f9961B);
                    long currentTimeMillis5 = System.currentTimeMillis();
                    LogUtils logUtils6 = C4269c.this.f10010h;
                    logUtils6.m15967a("PalmTask", "----------------" + (currentTimeMillis5 - currentTimeMillis4));
                    C4269c.this.f10010h.m15967a("PalmTaskLiveScore", String.valueOf(liveRecognition));
                    if (C4269c.this.f9985Z < liveRecognition) {
                        C4269c.this.f10010h.m15967a("PalmTask", "检测到对象，是活体");
                        if (isCancelled()) {
                            C4269c.this.f10010h.m15967a("PalmTask", "PalmTask isCancelled2");
                            return null;
                        }
                        if (!C4269c.this.f10001ap) {
                            C4269c.this.f10017o.removeCallbacks(C4269c.this.f10006au);
                            C4269c.this.f10001ap = true;
                            C4269c.this.f10002aq = true;
                            C4269c.this.m16239a(C4269c.this.f10025w, "preview", createScaledBitmap);
                            C4269c.this.f9984Y = liveRecognition;
                            C4269c.this.f10000ao = true;
                        }
                        C4291c.m15954a(createScaledBitmap);
                        System.gc();
                        return null;
                    }
                    C4269c c4269c2 = C4269c.this;
                    boolean z = C4269c.this.f9992ag;
                    c4269c2.m16238a(z, createScaledBitmap, "活体分数" + String.format("%.3f", Float.valueOf(liveRecognition)));
                    C4269c c4269c3 = C4269c.this;
                    c4269c3.f10026x = "活体检测不过，活体分数 = " + String.format("%.3f", Float.valueOf(liveRecognition)) + " < " + C4269c.this.f9985Z;
                    C4269c.this.f10010h.m15967a("livenessDetectTask", C4269c.this.f10026x);
                    c4269c = C4269c.this;
                    str = C4269c.this.f10026x;
                } else {
                    C4269c.this.f10020r.sendEmptyMessageDelayed(544, 0L);
                    C4269c.this.f10026x = "未正取景框";
                    C4269c.this.f10010h.m15967a("livenessDetectTask", C4269c.this.f10026x);
                    c4269c = C4269c.this;
                    str = C4269c.this.f10026x;
                }
            }
            c4269c.m16054c(str);
            C4291c.m15954a(createScaledBitmap);
            System.gc();
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            super.onCancelled();
            C4269c.this.f10010h.m15967a("PalmTaskCancelled：", "onCancelled。。。");
            if (C4269c.this.f10012j != null) {
                C4269c.this.f10012j.cancel(true);
                C4269c.this.f10010h.m15967a("PalmTask：", "mTakePicture cancel...");
            }
            if (C4269c.this.f10014l != null) {
                C4269c.this.f10010h.m15967a("PalmTask：", "mtimer cancel...");
                C4269c.this.f10014l.cancel(true);
            }
            C4269c.this.f10000ao = false;
            C4269c.this.f9998am = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.c$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4275c extends AsyncTask<Void, Void, Void> {

        /* renamed from: b */
        private byte[] f10036b;

        AsyncTaskC4275c(byte[] bArr) {
            this.f10036b = bArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            AsyncTaskC4276d asyncTaskC4276d;
            Executor executor;
            Void[] voidArr2;
            try {
            } catch (InterruptedException unused) {
                System.gc();
                return null;
            } catch (Exception e) {
                LogUtils logUtils = C4269c.this.f10010h;
                logUtils.m15967a("Sys", "Error:" + e.getMessage());
                e.printStackTrace();
            }
            if (C4269c.this.f10002aq) {
                return null;
            }
            Bitmap m16090a = C4269c.this.m16090a(C4269c.this.m16237a(this.f10036b, C4269c.this.f9967H, C4269c.this.f9968I), C4269c.this.f9969J == 0 ? 90.0f : -90.0f);
            Log.e("TakePicture", "原图片宽高：" + m16090a.getWidth() + "   " + m16090a.getHeight());
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(m16090a, C4269c.this.f9972M, (C4269c.this.f9972M * C4269c.this.f9967H) / C4269c.this.f9968I, false);
            Log.e("TakePicture", "照图片宽度500高等比缩放：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
            if (C4269c.this.f9994ai) {
                createScaledBitmap = C4292d.m15952a(createScaledBitmap, C4269c.this.f9974O, C4269c.this.f9973N, false);
                Log.e("TakePicture", "按照500宽度裁剪：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
            }
            LogUtils logUtils2 = C4269c.this.f10010h;
            logUtils2.m15967a("TakePicture", "图片宽高：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
            Bitmap m16068b = C4269c.this.m16068b(createScaledBitmap);
            if (isCancelled()) {
                return null;
            }
            C4269c.this.m16239a(C4269c.this.f10025w, String.valueOf(C4269c.this.f9975P), m16068b);
            LogUtils logUtils3 = C4269c.this.f10010h;
            logUtils3.m15967a("TakePicture_save", String.valueOf(C4269c.this.f9975P) + ".jpg saved successfully!");
            C4269c.this.f9975P = C4269c.this.f9975P + 1;
            if (C4269c.this.f9975P == 1) {
                C4269c.this.f10010h.m15967a("TakePicture", "currentPictures == 1,active mliveTask");
                if (C4269c.this.f10013k == null) {
                    C4269c.this.f10010h.m15967a("TakePicture", "new livenessDetectTask()");
                    C4269c.this.f10013k = new AsyncTaskC4276d(C4269c.this, null);
                    asyncTaskC4276d = C4269c.this.f10013k;
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    voidArr2 = new Void[0];
                } else if (C4269c.this.f10013k.getStatus() == AsyncTask.Status.PENDING) {
                    C4269c.this.f10013k.cancel(false);
                    C4269c.this.f10013k = new AsyncTaskC4276d(C4269c.this, null);
                    asyncTaskC4276d = C4269c.this.f10013k;
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    voidArr2 = new Void[0];
                }
                asyncTaskC4276d.executeOnExecutor(executor, voidArr2);
            }
            Thread.currentThread();
            Thread.sleep(C4269c.this.f9976Q);
            C4291c.m15954a(m16068b);
            System.gc();
            return null;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.c$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4276d extends AsyncTask<Void, Void, Void> {

        /* renamed from: com.example.asus.detectionandalign.a.c$d$1 */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        class RunnableC42771 implements Runnable {
            RunnableC42771() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C4269c.this.f10010h.m15967a("onSDKUsingFail", "11111111111111111111111");
                if (C4269c.this.f10019q != null) {
                    C4269c.this.f10019q.onSDKUsingFail("活体检测失败，请重试", "2002");
                }
            }
        }

        private AsyncTaskC4276d() {
        }

        /* synthetic */ AsyncTaskC4276d(C4269c c4269c, RunnableC42701 runnableC42701) {
            this();
        }

        /*  JADX ERROR: Failed to decode insn: 0x040E: FILLED_NEW_ARRAY_RANGE , method: com.example.asus.detectionandalign.a.c.d.a(java.lang.Void[]):java.lang.Void
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
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
        /*  JADX ERROR: Failed to decode insn: 0x0674: UNKNOWN(0x03F4), method: com.example.asus.detectionandalign.a.c.d.a(java.lang.Void[]):java.lang.Void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0674: UNKNOWN(0x03F4)'
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
        /*  JADX ERROR: Failed to decode insn: 0x073E: UNKNOWN(0x0043), method: com.example.asus.detectionandalign.a.c.d.a(java.lang.Void[]):java.lang.Void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x073E: UNKNOWN(0x0043)'
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
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public java.lang.Void doInBackground(java.lang.Void... r28) {
            /*
                Method dump skipped, instructions count: 2493
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p193a.C4269c.AsyncTaskC4276d.doInBackground(java.lang.Void[]):java.lang.Void");
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            super.onCancelled();
            C4269c.this.f10010h.m15967a("livenessDetectTask：", "livenessDetectTask_onCancelled。。。");
            C4269c c4269c = C4269c.this;
            c4269c.m16240a(c4269c.f10025w);
            C4269c.this.f10021s = null;
            C4269c.this.f10022t = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.c$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4278e extends AsyncTask<Integer, Integer, Boolean> {
        private AsyncTaskC4278e() {
        }

        /* synthetic */ AsyncTaskC4278e(C4269c c4269c, RunnableC42701 runnableC42701) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Boolean doInBackground(Integer... numArr) {
            LogUtils logUtils;
            String str;
            String str2;
            int intValue = numArr[0].intValue();
            while (intValue > 0) {
                if (C4269c.this.f10003ar) {
                    return true;
                }
                if (isCancelled()) {
                    logUtils = C4269c.this.f10010h;
                    str = "timer";
                    str2 = " isCancelled 倒计时终止";
                } else if (C4269c.this.f9990ae) {
                    return null;
                } else {
                    if (C4269c.this.f10007d.isEmpty() && C4269c.this.f10023u != null && C4269c.this.f10023u.size() > 0) {
                        C4269c c4269c = C4269c.this;
                        c4269c.f10007d = (String) c4269c.f10023u.get(C4269c.this.f9978S);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(intValue == C4269c.this.f9960A);
                    sb.append("  ");
                    sb.append(C4269c.this.f10001ap);
                    sb.append("  ");
                    sb.append(C4269c.this.f9995aj);
                    Log.e("========动作1   ", sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    C4269c c4269c2 = C4269c.this;
                    sb2.append(c4269c2.m16043e(c4269c2.f10007d));
                    sb2.append(" isThePlay   ");
                    sb2.append(C4269c.this.f9996ak);
                    sb2.append("  previewPassed  ");
                    sb2.append(C4269c.this.f10001ap);
                    sb2.append("   actionFrontPassed ");
                    sb2.append(C4269c.this.f9995aj);
                    Log.e("=ee=r========动作1   ", sb2.toString());
                    if (C4269c.this.f9996ak && C4269c.this.f10001ap && C4269c.this.f9995aj) {
                        C4269c.this.f9996ak = false;
                        C4269c.this.f10020r.removeMessages(DetectionAuthentic.DETECT_HINT);
                        if (C4269c.this.f10023u != null && C4269c.this.f10023u.size() > 0) {
                            C4269c c4269c3 = C4269c.this;
                            c4269c3.f10007d = (String) c4269c3.f10023u.get(C4269c.this.f9978S);
                        }
                        C4269c c4269c4 = C4269c.this;
                        String m16043e = c4269c4.m16043e(c4269c4.f10007d);
                        Message message = new Message();
                        message.what = DetectionAuthentic.DETECT_KEEP_ON;
                        Bundle bundle = new Bundle();
                        bundle.putString("hint", m16043e);
                        C4269c.this.f10010h.m15967a("timer-hint", m16043e);
                        C4269c.this.f10017o.removeCallbacksAndMessages(null);
                        C4269c.this.f10017o.post(C4269c.this.f10005at);
                        message.setData(bundle);
                        C4269c.this.f10020r.sendMessage(message);
                        C4269c.this.m16054c("");
                    }
                    C4269c.this.f10010h.m15967a("timer", "timeCount = " + String.valueOf(intValue));
                    try {
                        if (isCancelled()) {
                            C4269c.this.f10010h.m15967a("timer", " isCancelled 倒计时终止");
                            return null;
                        }
                        publishProgress(Integer.valueOf(intValue));
                        if (intValue == 1 && !C4269c.this.f10003ar) {
                            return false;
                        }
                        if (C4269c.this.f10002aq) {
                            intValue = C4269c.this.f9960A + 1;
                            C4269c.this.f10002aq = false;
                        } else {
                            Thread.sleep(1000L);
                        }
                        intValue--;
                    } catch (InterruptedException unused) {
                        logUtils = C4269c.this.f10010h;
                        str = "timer";
                        str2 = "倒计时终止";
                    }
                }
                logUtils.m15967a(str, str2);
                return null;
            }
            C4269c.this.f10010h.m15967a("timer", "timerfinished, goto结束跳到onPostExecute");
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            C4269c.this.m16052d();
            super.onPostExecute(bool);
            LogUtils logUtils = C4269c.this.f10010h;
            logUtils.m15967a("timer：", "onPostExecute, status = " + String.valueOf(bool));
            if (bool != null && bool.booleanValue()) {
                C4269c.this.f10000ao = false;
                C4269c.this.f10010h.m15967a("timer", "timerfinished pictureSwitch结束拍摄");
                C4269c.this.f9991af = true;
                C4269c c4269c = C4269c.this;
                c4269c.f10015m = new AsyncTaskC4273a(c4269c, null);
                C4269c.this.f10015m.execute(new Void[0]);
                return;
            }
            C4269c.this.f10000ao = false;
            C4269c.this.f10010h.m15967a("timer", "onPostExecute，onSDKUsingFail");
            if (C4269c.this.f10019q != null) {
                C4269c.this.f10019q.onSDKUsingFail("活体检测失败，请重试", "2002");
            }
            if (C4269c.this.f10013k != null) {
                C4269c.this.f10013k.cancel(true);
                C4269c.this.f10010h.m15967a("BaseAsyncTaskTool", "onBackPressed_mLiveTask cancel...");
            }
            C4269c.this.f10020r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_FINISH, 0L);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            Message obtainMessage = C4269c.this.f10020r.obtainMessage(DetectionAuthentic.DETECT_COUNT_DOWN_TEXT);
            obtainMessage.obj = numArr[0] + "";
            C4269c.this.f10020r.sendMessage(obtainMessage);
            if (isCancelled()) {
                C4269c.this.f10010h.m15967a("timer", " isCancelled 倒计时终止");
            }
        }
    }

    private C4269c(Activity activity, Handler handler, DetectionAuthentic detectionAuthentic, int i, int i2) {
        this.f10017o = null;
        this.f9969J = 0;
        this.f9970K = 0;
        this.f9971L = 0;
        this.f9990ae = false;
        this.f9990ae = false;
        this.f10014l.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Integer.valueOf(i2));
        this.f9990ae = false;
        this.f10009g = activity;
        this.f10020r = handler;
        this.f10019q = detectionAuthentic;
        this.f9969J = i;
        this.f9960A = i2;
        this.f10025w = m16243a(activity) + "/Pic";
        this.f10016n = new C4289a();
        this.f10017o = new Handler();
        this.f10017o.post(this.f10006au);
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        this.f9970K = defaultDisplay.getWidth();
        this.f9971L = defaultDisplay.getHeight();
        this.f9987ab = new float[10];
    }

    /* renamed from: A */
    static /* synthetic */ AsyncTaskC4276d m16120A(C4269c c4269c) {
        return c4269c.f10013k;
    }

    /* renamed from: B */
    static /* synthetic */ int m16119B(C4269c c4269c) {
        return c4269c.f9976Q;
    }

    /* renamed from: C */
    static /* synthetic */ List m16118C(C4269c c4269c) {
        return c4269c.f10023u;
    }

    /* renamed from: D */
    static /* synthetic */ int m16117D(C4269c c4269c) {
        return c4269c.f9978S;
    }

    /* renamed from: E */
    static /* synthetic */ boolean m16116E(C4269c c4269c) {
        return c4269c.f10003ar;
    }

    /* renamed from: F */
    static /* synthetic */ int m16115F(C4269c c4269c) {
        return c4269c.f9977R;
    }

    /* renamed from: G */
    static /* synthetic */ boolean m16114G(C4269c c4269c) {
        return c4269c.f9991af;
    }

    /* renamed from: H */
    static /* synthetic */ boolean m16113H(C4269c c4269c) {
        return c4269c.f9995aj;
    }

    /* renamed from: I */
    static /* synthetic */ List m16112I(C4269c c4269c) {
        return c4269c.f10021s;
    }

    /* renamed from: J */
    static /* synthetic */ List m16111J(C4269c c4269c) {
        return c4269c.f10024v;
    }

    /* renamed from: P */
    static /* synthetic */ DetectionAuthentic m16105P(C4269c c4269c) {
        return c4269c.f10019q;
    }

    /* renamed from: R */
    static /* synthetic */ boolean m16103R(C4269c c4269c) {
        return c4269c.f9990ae;
    }

    /* renamed from: S */
    static /* synthetic */ int m16102S(C4269c c4269c) {
        return c4269c.f9960A;
    }

    /* renamed from: T */
    static /* synthetic */ boolean m16101T(C4269c c4269c) {
        return c4269c.f9996ak;
    }

    /* renamed from: U */
    static /* synthetic */ Runnable m16100U(C4269c c4269c) {
        return c4269c.f10005at;
    }

    /* renamed from: V */
    static /* synthetic */ void m16099V(C4269c c4269c) {
        c4269c.m16052d();
    }

    /* renamed from: W */
    static /* synthetic */ AsyncTaskC4273a m16098W(C4269c c4269c) {
        return c4269c.f10015m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: Z */
    public static /* synthetic */ C4289a m16095Z(C4269c c4269c) {
        return c4269c.f10016n;
    }

    /* renamed from: a */
    static /* synthetic */ AsyncTaskC4273a m16086a(C4269c c4269c, AsyncTaskC4273a asyncTaskC4273a) {
        c4269c.f10015m = asyncTaskC4273a;
        return asyncTaskC4273a;
    }

    /* renamed from: a */
    public static synchronized C4269c m16092a(Activity activity, Handler handler, DetectionAuthentic detectionAuthentic, int i, int i2) {
        C4269c c4269c;
        synchronized (C4269c.class) {
            if (f9959e == null) {
                f9959e = new C4269c(activity, handler, detectionAuthentic, i, i2);
            }
            c4269c = f9959e;
        }
        return c4269c;
    }

    /* renamed from: a */
    static /* synthetic */ LogUtils m16089a(C4269c c4269c) {
        return c4269c.f10010h;
    }

    /* renamed from: a */
    static /* synthetic */ List m16083a(C4269c c4269c, List list) {
        c4269c.f10021s = list;
        return list;
    }

    /* renamed from: a */
    static /* synthetic */ boolean m16082a(C4269c c4269c, boolean z) {
        c4269c.f10000ao = z;
        return z;
    }

    /* renamed from: a */
    static /* synthetic */ float[] m16081a(C4269c c4269c, float[] fArr) {
        c4269c.f9987ab = fArr;
        return fArr;
    }

    /* renamed from: a */
    static /* synthetic */ int[] m16080a(C4269c c4269c, int[] iArr) {
        c4269c.f9963D = iArr;
        return iArr;
    }

    /* renamed from: b */
    static /* synthetic */ int m16066b(C4269c c4269c, int i) {
        c4269c.f9977R = i;
        return i;
    }

    /* renamed from: b */
    static /* synthetic */ String m16065b(C4269c c4269c, String str) {
        c4269c.f10027y = str;
        return str;
    }

    /* renamed from: b */
    static /* synthetic */ List m16064b(C4269c c4269c, List list) {
        c4269c.f10022t = list;
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m16052d() {
        Handler handler = this.f10017o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f10017o = null;
        }
        C4289a c4289a = this.f10016n;
        if (c4289a != null) {
            c4289a.m15962a();
            this.f10016n = null;
        }
    }

    /* renamed from: d */
    static /* synthetic */ boolean m16048d(C4269c c4269c, boolean z) {
        c4269c.f10002aq = z;
        return z;
    }

    /* renamed from: e */
    static /* synthetic */ boolean m16044e(C4269c c4269c, boolean z) {
        c4269c.f9995aj = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: f */
    public String m16039f(String str) {
        char c;
        switch (str.hashCode()) {
            case -1221271397:
                if (str.equals("headUp")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 100913:
                if (str.equals("eye")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 99151462:
                if (str.equals("headF")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 99151468:
                if (str.equals("headL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 99151474:
                if (str.equals("headR")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 104086727:
                if (str.equals("mouth")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "eyeblink";
            case 1:
                return "mouthopen";
            case 2:
                return "headup";
            case 3:
                return "headleft";
            case 4:
                return "headright";
            case 5:
                return "mobileprepare";
            default:
                return "异常";
        }
    }

    /* renamed from: f */
    static /* synthetic */ boolean m16040f(C4269c c4269c, boolean z) {
        c4269c.f9996ak = z;
        return z;
    }

    /* renamed from: h */
    static /* synthetic */ boolean m16036h(C4269c c4269c) {
        return c4269c.f9994ai;
    }

    /* renamed from: j */
    static /* synthetic */ boolean m16031j(C4269c c4269c, boolean z) {
        c4269c.f9991af = z;
        return z;
    }

    /* renamed from: k */
    static /* synthetic */ int[] m16030k(C4269c c4269c) {
        return c4269c.f9963D;
    }

    /* renamed from: l */
    static /* synthetic */ int m16029l(C4269c c4269c) {
        return c4269c.f9964E;
    }

    /* renamed from: m */
    static /* synthetic */ int m16028m(C4269c c4269c) {
        return c4269c.f9965F;
    }

    /* renamed from: n */
    static /* synthetic */ int m16027n(C4269c c4269c) {
        return c4269c.f9962C;
    }

    /* renamed from: o */
    static /* synthetic */ int m16026o(C4269c c4269c) {
        return c4269c.f9961B;
    }

    /* renamed from: p */
    static /* synthetic */ faceRecognition m16025p(C4269c c4269c) {
        return c4269c.f10008f;
    }

    /* renamed from: q */
    static /* synthetic */ float m16024q(C4269c c4269c) {
        return c4269c.f9985Z;
    }

    /* renamed from: r */
    static /* synthetic */ boolean m16023r(C4269c c4269c) {
        return c4269c.f10001ap;
    }

    /* renamed from: t */
    static /* synthetic */ Handler m16021t(C4269c c4269c) {
        return c4269c.f10017o;
    }

    /* renamed from: u */
    static /* synthetic */ String m16020u(C4269c c4269c) {
        return c4269c.f10025w;
    }

    /* renamed from: x */
    static /* synthetic */ Handler m16017x(C4269c c4269c) {
        return c4269c.f10020r;
    }

    /* renamed from: y */
    static /* synthetic */ boolean m16016y(C4269c c4269c) {
        return c4269c.f10002aq;
    }

    /* renamed from: z */
    static /* synthetic */ int m16015z(C4269c c4269c) {
        return c4269c.f9975P;
    }

    /* renamed from: a */
    public Bitmap m16090a(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: a */
    public void m16094a() {
        this.f9990ae = true;
        this.f10010h.m15965b("BaseAsyncTaskTool", "[BEGIN] StartLiveness::onDestroy()");
        if (f9959e != null) {
            f9959e = null;
        }
        AsyncTaskC4275c asyncTaskC4275c = this.f10012j;
        if (asyncTaskC4275c != null) {
            asyncTaskC4275c.cancel(true);
            this.f10010h.m15967a("mCloseImageBtn：", "mTakePicture cancel...");
        }
        AsyncTaskC4276d asyncTaskC4276d = this.f10013k;
        if (asyncTaskC4276d != null) {
            asyncTaskC4276d.cancel(true);
        }
        AsyncTaskC4274b asyncTaskC4274b = this.f10011i;
        if (asyncTaskC4274b != null) {
            asyncTaskC4274b.cancel(true);
        }
        AsyncTaskC4273a asyncTaskC4273a = this.f10015m;
        if (asyncTaskC4273a != null) {
            asyncTaskC4273a.cancel(true);
        }
        AsyncTaskC4278e asyncTaskC4278e = this.f10014l;
        if (asyncTaskC4278e != null) {
            asyncTaskC4278e.cancel(true);
        }
        HandlerThreadC4297i.m15941b();
        this.f10010h.m15965b("BaseAsyncTaskTool", "[END] StartLiveness::onDestroy()");
        if (this.f10008f != null) {
            this.f10008f = null;
        }
        this.f9987ab = null;
        this.f10018p = null;
        this.f10023u = null;
        this.f10024v = null;
        this.f10028z = null;
        this.f10019q = null;
        this.f10014l = null;
        this.f10013k = null;
        this.f10012j = null;
        this.f10011i = null;
        this.f10015m = null;
        this.f9963D = null;
        m16052d();
        System.gc();
    }

    /* renamed from: a */
    public void m16093a(float f) {
        this.f9985Z = f;
    }

    /* renamed from: a */
    public void m16091a(Bitmap bitmap) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        this.f9964E = copy.getWidth();
        this.f9965F = copy.getHeight();
        int i = this.f9964E;
        int i2 = this.f9965F;
        this.f9963D = new int[i * i2];
        copy.getPixels(this.f9963D, 0, i, 0, 0, i, i2);
    }

    /* renamed from: a */
    public void m16078a(List<String> list) {
        this.f10023u = list;
    }

    /* renamed from: a */
    public void m16077a(boolean z) {
        this.f9993ah = z;
        if (this.f9993ah) {
            this.f10018p = new C4280b(this.f10009g, this.f9970K, this.f9971L);
        }
    }

    /* renamed from: a */
    public void m16076a(byte[] bArr, JCameraView jCameraView) {
        this.f9966G++;
        if (this.f9966G < 20) {
            this.f10010h.m15967a("onPreviewFrame", "onPreviewFrame, drop frame id: " + this.f9966G);
            Camera.Size previewSize = jCameraView.getPreviewSize();
            if (previewSize != null) {
                this.f9968I = previewSize.height;
                this.f9967H = previewSize.width;
            }
        } else if (!this.f10001ap) {
            if (this.f10011i != null) {
                switch (C42723.f10031a[this.f10011i.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f10011i.cancel(false);
                        break;
                }
            }
            this.f10011i = new AsyncTaskC4274b(bArr);
            this.f10011i.execute(null);
        } else if (this.f10000ao) {
            if (this.f10012j != null) {
                switch (C42723.f10031a[this.f10012j.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f10012j.cancel(false);
                        break;
                }
            }
            this.f10012j = new AsyncTaskC4275c(bArr);
            this.f10012j.execute(null);
        }
    }

    /* renamed from: a */
    public boolean m16079a(String str, C4286b[] c4286bArr) {
        String str2;
        StringBuilder sb;
        StringBuilder sb2;
        float[] a = m16235a(c4286bArr);
        String str3 = "【眨眼：" + a[2] + "】";
        String str4 = "【张嘴：" + a[3] + "】";
        String str5 = "【抬头：" + a[4] + "】";
        StringBuilder sb3 = new StringBuilder();
        sb3.append("【左转：");
        boolean z = false;
        sb3.append(a[0]);
        sb3.append("】");
        String sb4 = sb3.toString();
        String str6 = "【右转：" + a[1] + "】";
        StringBuilder sb5 = new StringBuilder();
        sb5.append("【正对：");
        sb5.append(a[1] < a[0] ? a[1] : a[0]);
        sb5.append("】");
        String sb6 = sb5.toString();
        this.f10010h.m15967a("checkAction-currentAction", str);
        char c = 65535;
        switch (str.hashCode()) {
            case -1221271397:
                if (str.equals("headUp")) {
                    c = 2;
                    break;
                }
                break;
            case 100913:
                if (str.equals("eye")) {
                    c = 0;
                    break;
                }
                break;
            case 99151462:
                if (str.equals("headF")) {
                    c = 5;
                    break;
                }
                break;
            case 99151468:
                if (str.equals("headL")) {
                    c = 3;
                    break;
                }
                break;
            case 99151474:
                if (str.equals("headR")) {
                    c = 4;
                    break;
                }
                break;
            case 104086727:
                if (str.equals("mouth")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                str2 = sb6;
                if (a[2] >= this.f9979T) {
                    sb = new StringBuilder();
                    sb.append("<font color='#f1020d'>");
                    sb.append(str3);
                    sb.append("</font>");
                    sb.append(str4);
                    sb.append(str5);
                    sb.append(sb4);
                    sb.append(str6);
                    sb.append("");
                    sb.append(str2);
                    this.f10004as = sb.toString();
                    break;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append("<font color='#009933'>");
                    sb2.append(str3);
                    sb2.append("</font>");
                    sb2.append(str4);
                    sb2.append(str5);
                    sb2.append(sb4);
                    sb2.append(str6);
                    sb2.append("");
                    sb2.append(str2);
                    this.f10004as = sb2.toString();
                    z = true;
                    break;
                }
            case 1:
                str2 = sb6;
                if (a[3] <= this.f9980U) {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append("<font color='#f1020d'>");
                    sb.append(str4);
                    sb.append("</font>");
                    sb.append(str5);
                    sb.append(sb4);
                    sb.append(str6);
                    sb.append("");
                    sb.append(str2);
                    this.f10004as = sb.toString();
                    break;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str3);
                    sb2.append("<font color='#009933'>");
                    sb2.append(str4);
                    sb2.append("</font>");
                    sb2.append(str5);
                    sb2.append(sb4);
                    sb2.append(str6);
                    sb2.append("");
                    sb2.append(str2);
                    this.f10004as = sb2.toString();
                    z = true;
                    break;
                }
            case 2:
                str2 = sb6;
                if (a[4] >= this.f9981V) {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(str4);
                    sb.append("<font color='#f1020d'>");
                    sb.append(str5);
                    sb.append("</font>");
                    sb.append(sb4);
                    sb.append(str6);
                    sb.append("");
                    sb.append(str2);
                    this.f10004as = sb.toString();
                    break;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str3);
                    sb2.append(str4);
                    sb2.append("<font color='#009933'>");
                    sb2.append(str5);
                    sb2.append("</font>");
                    sb2.append(sb4);
                    sb2.append(str6);
                    sb2.append("");
                    sb2.append(str2);
                    this.f10004as = sb2.toString();
                    z = true;
                    break;
                }
            case 3:
                str2 = sb6;
                if (a[0] >= this.f9983X) {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(str4);
                    sb.append(str5);
                    sb.append("<font color='#f1020d'>");
                    sb.append(sb4);
                    sb.append("</font>");
                    sb.append(str6);
                    sb.append("");
                    sb.append(str2);
                    this.f10004as = sb.toString();
                    break;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str3);
                    sb2.append(str4);
                    sb2.append(str5);
                    sb2.append("<font color='#009933'>");
                    sb2.append(sb4);
                    sb2.append("</font>");
                    sb2.append(str6);
                    sb2.append("");
                    sb2.append(str2);
                    this.f10004as = sb2.toString();
                    z = true;
                    break;
                }
            case 4:
                str2 = sb6;
                if (a[1] >= this.f9982W) {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(str4);
                    sb.append(str5);
                    sb.append(sb4);
                    sb.append("<font color='#f1020d'>");
                    sb.append(str6);
                    sb.append("</font>");
                    sb.append("");
                    sb.append(str2);
                    this.f10004as = sb.toString();
                    break;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str3);
                    sb2.append(str4);
                    sb2.append(str5);
                    sb2.append(sb4);
                    sb2.append("<font color='#009933'>");
                    sb2.append(str6);
                    sb2.append("</font>");
                    sb2.append("");
                    sb2.append(str2);
                    this.f10004as = sb2.toString();
                    z = true;
                    break;
                }
            case 5:
                float f = a[0];
                float f2 = this.f9986aa;
                if (f >= f2 && a[1] >= f2) {
                    this.f10004as = str3 + str4 + str5 + sb4 + str6 + "<font color='#009933'>" + sb6 + "</font>";
                    z = true;
                    break;
                } else {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(str4);
                    sb.append(str5);
                    sb.append(sb4);
                    sb.append(str6);
                    sb.append("");
                    sb.append("<font color='#f1020d'>");
                    sb.append(sb6);
                    sb.append("</font>");
                    this.f10004as = sb.toString();
                    break;
                }
        }
        this.f10010h.m15967a("checkAction-resultBoolen", String.valueOf(z));
        this.f10010h.m15967a("actionResult", this.f10004as);
        return z;
    }

    /* renamed from: b */
    public int m16070b() {
        File file = new File(this.f10025w);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles().length;
        }
        return 0;
    }

    /* renamed from: b */
    public Bitmap m16068b(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: b */
    public void m16069b(float f) {
        this.f9986aa = f;
    }

    /* renamed from: b */
    public void m16062b(List<Double> list) {
        if (list == null || list.size() != 5) {
            return;
        }
        this.f9979T = list.get(0).doubleValue();
        this.f9980U = list.get(1).doubleValue();
        this.f9981V = list.get(2).doubleValue();
        this.f9982W = list.get(3).doubleValue();
        this.f9983X = list.get(4).doubleValue();
    }

    /* renamed from: b */
    public void m16061b(boolean z) {
        this.f9992ag = z;
    }

    /* renamed from: b */
    public void m16060b(byte[] bArr, int i, int i2) {
        this.f9966G++;
        if (this.f9966G < 20) {
            this.f10010h.m15967a("onPreviewFrame", "onPreviewFrame, drop frame id: " + this.f9966G);
            this.f9968I = i2;
            this.f9967H = i;
        } else if (!this.f10001ap) {
            if (this.f10011i != null) {
                switch (C42723.f10031a[this.f10011i.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f10011i.cancel(false);
                        break;
                }
            }
            this.f10011i = new AsyncTaskC4274b(bArr);
            this.f10011i.execute(null);
        } else if (this.f10000ao) {
            if (this.f10012j != null) {
                switch (C42723.f10031a[this.f10012j.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f10012j.cancel(false);
                        break;
                }
            }
            this.f10012j = new AsyncTaskC4275c(bArr);
            this.f10012j.execute(null);
        }
    }

    /* renamed from: c */
    public String m16059c() {
        return this.f10007d;
    }

    /* renamed from: c */
    public void m16054c(String str) {
        Message message = new Message();
        message.what = DetectionAuthentic.DETECT_ABNORMAL;
        Bundle bundle = new Bundle();
        bundle.putString("error", str);
        message.setData(bundle);
        this.f10020r.sendMessage(message);
    }

    /* renamed from: c */
    public void m16053c(boolean z) {
        if (z) {
            this.f9972M = 600;
            this.f9973N = 600;
            this.f9974O = 800;
        }
    }

    /* renamed from: d */
    public Bitmap m16047d(String str) {
        try {
            String str2 = this.f10025w;
            File file = new File(str2, str + ".jpg");
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
                this.f10010h.m15967a("getBitmapFromLocal", "Bitmap acquired from the cache");
                fileInputStream.close();
                return decodeStream;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public void m16046d(boolean z) {
        this.f9994ai = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: e */
    public String m16043e(String str) {
        char c;
        Activity activity;
        int i;
        switch (str.hashCode()) {
            case -1221271925:
                if (str.equals("headF1")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1221271397:
                if (str.equals("headUp")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 100913:
                if (str.equals("eye")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 99151462:
                if (str.equals("headF")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 99151468:
                if (str.equals("headL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 99151474:
                if (str.equals("headR")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 104086727:
                if (str.equals("mouth")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_eye;
                break;
            case 1:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_mouth;
                break;
            case 2:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_headUp;
                break;
            case 3:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_headL;
                break;
            case 4:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_headR;
                break;
            case 5:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_focus;
                break;
            case 6:
                activity = this.f10009g;
                i = C4243R.string.living_step_hint_preview;
                break;
            default:
                return "异常";
        }
        return activity.getString(i);
    }

    /* renamed from: e */
    public void m16042e(boolean z) {
        this.f9989ad = z;
    }
}
