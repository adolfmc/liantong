package com.example.asus.detectionandalign.p193a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.example.asus.detectionandalign.listener.CompressListener;
import com.example.asus.detectionandalign.p194b.p195a.C4286b;
import com.example.asus.detectionandalign.utils.C4291c;
import com.example.asus.detectionandalign.utils.C4292d;
import com.example.asus.detectionandalign.utils.C4301k;
import com.example.asus.detectionandalign.utils.CountDownTimerC4299j;
import com.example.asus.detectionandalign.utils.LogUtils;
import com.example.asus.detectionandalign.videocompress.VideoCompress;
import com.example.landmarksdk.faceDetectionResult;
import com.example.landmarksdk.faceRecognition;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import comp.android.app.face.p381sz.camera.JCameraView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: com.example.asus.detectionandalign.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4259b extends C4257a {

    /* renamed from: l */
    private static C4259b f9890l;

    /* renamed from: B */
    private int[] f9892B;

    /* renamed from: C */
    private int f9893C;

    /* renamed from: D */
    private int f9894D;

    /* renamed from: N */
    private int f9904N;

    /* renamed from: O */
    private int f9905O;

    /* renamed from: P */
    private int f9906P;

    /* renamed from: Q */
    private int f9907Q;

    /* renamed from: T */
    private boolean f9910T;

    /* renamed from: W */
    private boolean f9913W;

    /* renamed from: e */
    private String f9925e;

    /* renamed from: f */
    private String f9926f;

    /* renamed from: g */
    private Context f9927g;

    /* renamed from: h */
    private StringBuffer f9928h;

    /* renamed from: i */
    private CountDownTimerC4299j f9929i;

    /* renamed from: m */
    private AsyncTaskC4264b f9932m;

    /* renamed from: n */
    private AsyncTaskC4265c f9933n;

    /* renamed from: o */
    private AsyncTaskC4266d f9934o;

    /* renamed from: p */
    private AsyncTaskC4267e f9935p;

    /* renamed from: q */
    private AsyncTaskC4263a f9936q;

    /* renamed from: r */
    private Handler f9937r;

    /* renamed from: s */
    private DetectionAuthentic f9938s;

    /* renamed from: v */
    private int f9941v;

    /* renamed from: d */
    private String f9924d = "";

    /* renamed from: k */
    private faceRecognition f9931k = new faceRecognition();

    /* renamed from: t */
    private List<String> f9939t = new ArrayList();

    /* renamed from: u */
    private int f9940u = 0;

    /* renamed from: w */
    private int f9942w = 0;

    /* renamed from: x */
    private int f9943x = 0;

    /* renamed from: y */
    private int f9944y = 500;

    /* renamed from: z */
    private int f9945z = 500;

    /* renamed from: A */
    private int f9891A = 500;

    /* renamed from: E */
    private int f9895E = 4;

    /* renamed from: F */
    private int f9896F = 144;

    /* renamed from: G */
    private int f9897G = 0;

    /* renamed from: H */
    private int f9898H = 0;

    /* renamed from: I */
    private int f9899I = 1;

    /* renamed from: J */
    private int f9900J = 0;

    /* renamed from: K */
    private int f9901K = 0;

    /* renamed from: L */
    private int f9902L = 0;

    /* renamed from: M */
    private int f9903M = 1000;

    /* renamed from: R */
    private float f9908R = 0.7f;

    /* renamed from: S */
    private float f9909S = 0.4f;

    /* renamed from: U */
    private boolean f9911U = false;

    /* renamed from: V */
    private boolean f9912V = false;

    /* renamed from: X */
    private boolean f9914X = false;

    /* renamed from: Y */
    private boolean f9915Y = false;

    /* renamed from: Z */
    private boolean f9916Z = false;

    /* renamed from: aa */
    private boolean f9917aa = false;

    /* renamed from: ab */
    private boolean f9918ab = true;

    /* renamed from: ac */
    private boolean f9919ac = false;

    /* renamed from: ad */
    private Handler f9920ad = new Handler();

    /* renamed from: ae */
    private Runnable f9921ae = new Runnable() { // from class: com.example.asus.detectionandalign.a.b.1
        @Override // java.lang.Runnable
        public void run() {
            C4259b.m16202a(C4259b.this);
            if (C4259b.this.f9920ad != null) {
                C4259b.this.f9920ad.postDelayed(this, 1000L);
            }
        }
    };

    /* renamed from: af */
    private long f9922af = 0;

    /* renamed from: ag */
    private long f9923ag = 0;

    /* renamed from: j */
    private LogUtils f9930j = LogUtils.m15966a(true);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.example.asus.detectionandalign.a.b$3 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static /* synthetic */ class C42623 {

        /* renamed from: a */
        static final /* synthetic */ int[] f9949a = new int[AsyncTask.Status.values().length];

        static {
            try {
                f9949a[AsyncTask.Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9949a[AsyncTask.Status.PENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.example.asus.detectionandalign.a.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4263a extends AsyncTask<Void, Void, String> {

        /* renamed from: a */
        public String f9950a;

        private AsyncTaskC4263a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x023c, code lost:
            if (r12.f9951b.f9938s == null) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x023e, code lost:
            r12.f9951b.f9938s.onSDKUsingFail("生成数据包失败", "2003");
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x024c, code lost:
            r12.f9951b.f9937r.sendEmptyMessageDelayed(com.example.asus.detectionandalign.DetectionAuthentic.DETECT_FINISH, 0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0255, code lost:
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x028a, code lost:
            if (r12.f9951b.f9938s == null) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x02c1, code lost:
            if (r12.f9951b.f9938s == null) goto L53;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String doInBackground(java.lang.Void... r13) {
            /*
                Method dump skipped, instructions count: 709
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p193a.C4259b.AsyncTaskC4263a.doInBackground(java.lang.Void[]):java.lang.String");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0052, code lost:
            if (r6.f9951b.f9938s != null) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0054, code lost:
            r6.f9951b.f9938s.onSDKUsingFail("活体检测失败，请重试", "2002");
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0062, code lost:
            r6.f9951b.f9937r.sendEmptyMessageDelayed(com.example.asus.detectionandalign.DetectionAuthentic.DETECT_FINISH, 0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x006b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0049, code lost:
            if (r6.f9951b.f9938s == null) goto L12;
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
                com.example.asus.detectionandalign.a.b r0 = com.example.asus.detectionandalign.p193a.C4259b.this
                com.example.asus.detectionandalign.utils.LogUtils r0 = com.example.asus.detectionandalign.p193a.C4259b.m16169c(r0)
                java.lang.String r1 = "check success："
                java.lang.String r2 = "success"
                r0.m15967a(r1, r2)
                com.example.asus.detectionandalign.a.b r0 = com.example.asus.detectionandalign.p193a.C4259b.this
                android.os.Handler r0 = com.example.asus.detectionandalign.p193a.C4259b.m16136u(r0)
                r1 = 0
                r3 = 8807(0x2267, float:1.2341E-41)
                r0.sendEmptyMessageDelayed(r3, r1)
                r0 = 8756(0x2234, float:1.227E-41)
                if (r7 == 0) goto L4c
                com.example.asus.detectionandalign.a.b r3 = com.example.asus.detectionandalign.p193a.C4259b.this
                com.example.asus.detectionandalign.utils.LogUtils r3 = com.example.asus.detectionandalign.p193a.C4259b.m16169c(r3)
                java.lang.String r4 = "check result："
                java.lang.String r5 = "not null"
                r3.m15967a(r4, r5)
                com.example.asus.detectionandalign.a.b r3 = com.example.asus.detectionandalign.p193a.C4259b.this     // Catch: java.lang.Exception -> L3f
                com.example.asus.detectionandalign.DetectionAuthentic r3 = com.example.asus.detectionandalign.p193a.C4259b.m16215S(r3)     // Catch: java.lang.Exception -> L3f
                r3.onActionImageCaptured(r7)     // Catch: java.lang.Exception -> L3f
                com.example.asus.detectionandalign.a.b r7 = com.example.asus.detectionandalign.p193a.C4259b.this     // Catch: java.lang.Exception -> L3f
                android.os.Handler r7 = com.example.asus.detectionandalign.p193a.C4259b.m16136u(r7)     // Catch: java.lang.Exception -> L3f
                r7.sendEmptyMessageDelayed(r0, r1)     // Catch: java.lang.Exception -> L3f
                goto L6b
            L3f:
                r7 = move-exception
                r7.printStackTrace()
                com.example.asus.detectionandalign.a.b r7 = com.example.asus.detectionandalign.p193a.C4259b.this
                com.example.asus.detectionandalign.DetectionAuthentic r7 = com.example.asus.detectionandalign.p193a.C4259b.m16215S(r7)
                if (r7 == 0) goto L62
                goto L54
            L4c:
                com.example.asus.detectionandalign.a.b r7 = com.example.asus.detectionandalign.p193a.C4259b.this
                com.example.asus.detectionandalign.DetectionAuthentic r7 = com.example.asus.detectionandalign.p193a.C4259b.m16215S(r7)
                if (r7 == 0) goto L62
            L54:
                com.example.asus.detectionandalign.a.b r7 = com.example.asus.detectionandalign.p193a.C4259b.this
                com.example.asus.detectionandalign.DetectionAuthentic r7 = com.example.asus.detectionandalign.p193a.C4259b.m16215S(r7)
                java.lang.String r3 = "活体检测失败，请重试"
                java.lang.String r4 = "2002"
                r7.onSDKUsingFail(r3, r4)
            L62:
                com.example.asus.detectionandalign.a.b r7 = com.example.asus.detectionandalign.p193a.C4259b.this
                android.os.Handler r7 = com.example.asus.detectionandalign.p193a.C4259b.m16136u(r7)
                r7.sendEmptyMessageDelayed(r0, r1)
            L6b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p193a.C4259b.AsyncTaskC4263a.onPostExecute(java.lang.String):void");
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.b$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4264b extends AsyncTask<Void, Void, Void> {

        /* renamed from: b */
        private byte[] f9953b;

        AsyncTaskC4264b(byte[] bArr) {
            this.f9953b = bArr;
        }

        /* renamed from: a */
        public Bitmap m16128a(Bitmap bitmap) {
            Matrix matrix = new Matrix();
            matrix.setScale(-1.0f, 1.0f);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        /* renamed from: a */
        public Bitmap m16127a(Bitmap bitmap, float f) {
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
            Message obtainMessage;
            Handler handler;
            try {
                Bitmap m16127a = m16127a(C4259b.this.m16237a(this.f9953b, C4259b.this.f9943x, C4259b.this.f9942w), C4259b.this.f9941v == 0 ? 90.0f : -90.0f);
                if (C4259b.this.f9941v == 0) {
                    m16127a = m16128a(m16127a);
                }
                Log.e("PalmTask", "原图片宽高：" + m16127a.getWidth() + "   " + m16127a.getHeight());
                createScaledBitmap = Bitmap.createScaledBitmap(m16127a, C4259b.this.f9944y, (C4259b.this.f9944y * C4259b.this.f9943x) / C4259b.this.f9942w, false);
                Log.e("PalmTask", "照图片宽度500高等比缩放：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                if (C4259b.this.f9918ab) {
                    createScaledBitmap = C4292d.m15952a(createScaledBitmap, C4259b.this.f9891A, C4259b.this.f9945z, false);
                    Log.e("PalmTask", "按照500宽度裁剪：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                }
                LogUtils logUtils = C4259b.this.f9930j;
                logUtils.m15967a("PalmTask", "图片宽高：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                C4259b.this.m16204a(createScaledBitmap);
                detectionResult = C4259b.this.f9931k.getDetectionResult(C4259b.this.f9892B, C4259b.this.f9893C, C4259b.this.f9894D, C4259b.this.f9896F, C4259b.this.f9895E);
            } catch (Exception e) {
                LogUtils logUtils2 = C4259b.this.f9930j;
                logUtils2.m15967a("PalmTask", "Exception:" + e.getMessage());
            }
            if (detectionResult.length == 0 || !C4259b.this.m16241a(detectionResult[0])) {
                obtainMessage = C4259b.this.f9937r.obtainMessage(546);
                handler = C4259b.this.f9937r;
            } else if (C4259b.this.m16185a("headF", C4257a.m16236a(C4259b.this.f9931k.extractLandmark(C4259b.this.f9892B, C4259b.this.f9893C, C4259b.this.f9894D, detectionResult[0], C4259b.this.f9895E)))) {
                if (C4259b.this.m16242a(createScaledBitmap, detectionResult)) {
                    float liveRecognition = C4259b.this.f9931k.liveRecognition(C4259b.this.f9892B, C4259b.this.f9893C, C4259b.this.f9894D, detectionResult[0], C4259b.this.f9895E);
                    if (C4259b.this.f9908R < liveRecognition) {
                        C4259b.this.f9930j.m15967a("PalmTask", "检测到对象，是活体");
                        if (isCancelled()) {
                            C4259b.this.f9930j.m15967a("PalmTask", "PalmTask isCancelled2");
                            return null;
                        } else if (!C4259b.this.f9915Y) {
                            C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_KEEP_ON, 0L);
                            C4259b.this.f9915Y = true;
                            C4259b.this.f9935p = new AsyncTaskC4267e();
                            C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_STOP_COUNT_DOWN_TIMER, 0L);
                            C4259b.this.f9935p.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Integer.valueOf(C4259b.this.f9905O));
                            C4259b.this.f9916Z = true;
                            if (C4259b.this.f9919ac) {
                                C4259b.this.f9930j.m15967a("PalmTask", "startRecorder");
                                C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.GENERATED_START_RECORD, 0L);
                                C4259b.this.f9920ad.postDelayed(C4259b.this.f9921ae, 1000L);
                                C4259b.this.f9902L = C4259b.this.f9900J;
                            }
                        }
                    } else {
                        C4259b.this.f9937r.sendMessage(C4259b.this.f9937r.obtainMessage(DetectionAuthentic.DETECT_NON_LIVING_BODY2));
                        C4259b c4259b = C4259b.this;
                        boolean z = C4259b.this.f9917aa;
                        c4259b.m16238a(z, createScaledBitmap, "活体分数" + String.format("%.3f", Float.valueOf(liveRecognition)));
                    }
                } else {
                    C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_FACING_VIEWFINDER1, 0L);
                }
                C4291c.m15954a(createScaledBitmap);
                System.gc();
                return null;
            } else {
                obtainMessage = C4259b.this.f9937r.obtainMessage(546);
                handler = C4259b.this.f9937r;
            }
            handler.sendMessage(obtainMessage);
            C4291c.m15954a(createScaledBitmap);
            System.gc();
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            super.onCancelled();
            C4259b.this.f9930j.m15967a("PalmTask：", "onCancelled。。。");
            if (C4259b.this.f9933n != null) {
                C4259b.this.f9933n.cancel(true);
                C4259b.this.f9930j.m15967a("PalmTask：", "mTakePicture cancel...");
            }
            if (C4259b.this.f9935p != null) {
                C4259b.this.f9930j.m15967a("PalmTask：", "mtimer cancel...");
                C4259b.this.f9935p.cancel(true);
            }
            C4259b.this.f9916Z = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.b$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4265c extends AsyncTask<Void, Void, Void> {

        /* renamed from: b */
        private byte[] f9955b;

        AsyncTaskC4265c(byte[] bArr) {
            this.f9955b = bArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            Bitmap createScaledBitmap;
            AsyncTaskC4266d asyncTaskC4266d;
            Executor executor;
            Void[] voidArr2;
            C4259b.this.f9930j.m15967a("TakePicture", "start currentPictures =" + String.valueOf(C4259b.this.f9897G));
            if (C4259b.this.f9897G >= C4259b.this.f9907Q) {
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Bitmap m16203a = C4259b.this.m16203a(C4259b.this.m16237a(this.f9955b, C4259b.this.f9943x, C4259b.this.f9942w), C4259b.this.f9941v == 0 ? 90.0f : -90.0f);
                if (C4259b.this.f9941v == 0) {
                    m16203a = C4259b.this.m16179b(m16203a);
                }
                Log.e("TakePicture", "原图片宽高：" + m16203a.getWidth() + "   " + m16203a.getHeight());
                createScaledBitmap = Bitmap.createScaledBitmap(m16203a, C4259b.this.f9944y, (C4259b.this.f9944y * C4259b.this.f9943x) / C4259b.this.f9942w, false);
                Log.e("TakePicture", "照图片宽度500高等比缩放：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                if (C4259b.this.f9918ab) {
                    createScaledBitmap = C4292d.m15952a(createScaledBitmap, C4259b.this.f9891A, C4259b.this.f9945z, false);
                    Log.e("TakePicture", "按照500宽度裁剪：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
                }
                C4259b.this.f9930j.m15967a("TakePicture", "图片宽高：" + createScaledBitmap.getWidth() + "   " + createScaledBitmap.getHeight());
            } catch (InterruptedException e) {
                C4259b c4259b = C4259b.this;
                c4259b.m16240a(c4259b.f9925e);
                C4259b.this.f9939t = null;
                C4259b.this.f9930j.m15967a("TakePicture", "InterruptedException   " + e);
                System.gc();
                return null;
            } catch (Exception e2) {
                C4259b.this.f9930j.m15967a("TakePicture", "Exception   " + e2);
                e2.printStackTrace();
            }
            if (isCancelled()) {
                return null;
            }
            C4259b.this.f9930j.m15967a("TakePicture", String.valueOf(C4259b.this.f9897G) + ".jpg taken successfully!");
            C4259b.this.m16186a(String.valueOf(C4259b.this.f9897G), createScaledBitmap);
            C4259b.this.f9930j.m15967a("TakePicture_save", String.valueOf(C4259b.this.f9897G) + ".jpg saved successfully!");
            C4259b.this.f9930j.m15967a("____getFileNo", String.valueOf(C4259b.this.m16181b()));
            if (C4259b.this.f9897G == 0 && C4259b.this.f9919ac) {
                C4259b.this.f9902L = C4259b.this.f9900J;
                C4259b.this.f9930j.m15967a("TakePicture", "refresh startPoint: " + String.valueOf(C4259b.this.f9902L));
            }
            C4259b.this.f9897G++;
            C4259b.this.f9930j.m15967a("TakePicture", "+1 currentPictures =" + String.valueOf(C4259b.this.f9897G));
            if (C4259b.this.f9897G == C4259b.this.f9907Q && C4259b.this.f9919ac) {
                C4259b.this.f9901K = C4259b.this.f9900J;
                C4259b.this.f9930j.m15967a("TakePicture", "refresh endPoint: " + String.valueOf(C4259b.this.f9901K));
            }
            if (C4259b.this.f9897G == 1 && C4259b.this.f9899I == 1) {
                C4259b.this.f9930j.m15967a("TakePicture", "currentPictures == 1 && livenessRound ==1");
                if (C4259b.this.f9934o == null) {
                    C4259b.this.f9930j.m15967a("TakePicture", "new livenessDetectTask()");
                    C4259b.this.f9934o = new AsyncTaskC4266d();
                    asyncTaskC4266d = C4259b.this.f9934o;
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    voidArr2 = new Void[0];
                } else if (C4259b.this.f9934o.getStatus() == AsyncTask.Status.PENDING) {
                    C4259b.this.f9934o.cancel(false);
                    C4259b.this.f9934o = new AsyncTaskC4266d();
                    asyncTaskC4266d = C4259b.this.f9934o;
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    voidArr2 = new Void[0];
                }
                asyncTaskC4266d.executeOnExecutor(executor, voidArr2);
            }
            long currentTimeMillis2 = C4259b.this.f9904N - (System.currentTimeMillis() - currentTimeMillis);
            if (currentTimeMillis2 > 0) {
                C4259b.this.f9930j.m15967a("TakePicture", "sleep " + String.valueOf(currentTimeMillis2) + "ms");
                Thread.currentThread();
                Thread.sleep(currentTimeMillis2);
            }
            C4291c.m15954a(createScaledBitmap);
            System.gc();
            return null;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.b$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4266d extends AsyncTask<Void, Void, Void> {
        private AsyncTaskC4266d() {
        }

        /*  JADX ERROR: Failed to decode insn: 0x0448: CONST_METHOD_HANDLE r3, method: com.example.asus.detectionandalign.a.b.d.a(java.lang.Void[]):java.lang.Void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0448: CONST_METHOD_HANDLE r3'
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
        /*  JADX ERROR: Failed to decode insn: 0x0848: UNKNOWN(0x00EC), method: com.example.asus.detectionandalign.a.b.d.a(java.lang.Void[]):java.lang.Void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0848: UNKNOWN(0x00EC)'
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
        /*  JADX ERROR: Failed to decode insn: 0x093C: UNKNOWN(0x0043), method: com.example.asus.detectionandalign.a.b.d.a(java.lang.Void[]):java.lang.Void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x093C: UNKNOWN(0x0043)'
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
        /*  JADX ERROR: Failed to decode insn: 0x09B7: UNKNOWN(0x53F1), method: com.example.asus.detectionandalign.a.b.d.a(java.lang.Void[]):java.lang.Void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x09B7: UNKNOWN(0x53F1)'
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
        public java.lang.Void doInBackground(java.lang.Void... r27) {
            /*
                Method dump skipped, instructions count: 2512
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p193a.C4259b.AsyncTaskC4266d.doInBackground(java.lang.Void[]):java.lang.Void");
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            super.onCancelled();
            C4259b.this.f9930j.m15967a("livenessDetectTask：", "livenessDetectTask_onCancelled。。。");
            C4259b.this.f9910T = true;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.asus.detectionandalign.a.b$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC4267e extends AsyncTask<Integer, Integer, Boolean> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.example.asus.detectionandalign.a.b$e$1 */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class C42681 implements CountDownTimerC4299j.InterfaceC4300a {
            C42681() {
            }

            @Override // com.example.asus.detectionandalign.utils.CountDownTimerC4299j.InterfaceC4300a
            /* renamed from: a */
            public void mo15939a() {
                C4259b.this.f9930j.m15967a("BaseAsyncTaskTool", "等待路径返回失败 ");
                C4259b.this.f9929i.cancel();
                C4259b.this.f9914X = false;
                if (C4259b.this.f9938s != null) {
                    C4259b.this.f9938s.onSDKUsingFail("生成数据包失败", "2003");
                }
                C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_FINISH, 0L);
            }

            @Override // com.example.asus.detectionandalign.utils.CountDownTimerC4299j.InterfaceC4300a
            /* renamed from: a */
            public void mo15938a(long j) {
                C4259b.this.f9930j.m15967a("BaseAsyncTaskTool", "等待路径返回 ");
                if (C4259b.this.f9914X) {
                    C4259b.this.f9914X = false;
                    C4259b.this.f9929i.cancel();
                }
            }
        }

        private AsyncTaskC4267e() {
        }

        /*  JADX ERROR: IllegalArgumentException in pass: AttachTryCatchVisitor
            java.lang.IllegalArgumentException: newPosition > limit: (689639389 > 13931064)
            	at java.nio.Buffer.createPositionException(Buffer.java:269)
            	at java.nio.Buffer.position(Buffer.java:244)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:177)
            	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
            	at jadx.plugins.input.dex.sections.DexCodeReader.getCatchHandlers(DexCodeReader.java:170)
            	at jadx.plugins.input.dex.sections.DexCodeReader.getTries(DexCodeReader.java:142)
            	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
            */
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public java.lang.Boolean doInBackground(java.lang.Integer... r13) {
            /*
                Method dump skipped, instructions count: 1022
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p193a.C4259b.AsyncTaskC4267e.doInBackground(java.lang.Integer[]):java.lang.Boolean");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            LogUtils logUtils = C4259b.this.f9930j;
            logUtils.m15967a("timer：", "onPostExecute, status = " + String.valueOf(bool));
            if (bool == null) {
                return;
            }
            if (!bool.booleanValue()) {
                C4259b.this.f9930j.m15967a("timer", "onPostExecute，onSDKUsingFail");
                if (C4259b.this.f9938s != null) {
                    C4259b.this.f9938s.onSDKUsingFail("活体检测失败，请重试", "2002");
                    return;
                }
                return;
            }
            C4259b.this.f9916Z = false;
            C4259b.this.f9930j.m15967a("timer", "timerfinished pictureSwitch结束拍摄");
            if (C4259b.this.f9919ac) {
                C4259b.this.f9930j.m15967a("timer", "录制完成，结束录制");
                C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.GENERATED_FINISH_RECORD, 0L);
                C4259b.this.f9929i = new CountDownTimerC4299j(5000L, 1000L, new C42681());
                return;
            }
            C4259b.this.f9911U = true;
            if (C4259b.this.f9898H != C4259b.this.f9907Q) {
                C4259b.this.f9930j.m15967a("timer", "pictureCounted != totalActions, this should not happen");
                return;
            }
            Message message = new Message();
            message.what = 519;
            C4259b.this.f9937r.sendMessage(message);
            C4259b c4259b = C4259b.this;
            c4259b.f9936q = new AsyncTaskC4263a();
            C4259b.this.f9936q.execute(new Void[0]);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            Message obtainMessage = C4259b.this.f9937r.obtainMessage(DetectionAuthentic.DETECT_COUNT_DOWN_TEXT);
            obtainMessage.obj = numArr[0] + "";
            C4259b.this.f9937r.sendMessage(obtainMessage);
            if (isCancelled()) {
                C4259b.this.f9930j.m15967a("timer", " isCancelled 倒计时终止");
            }
        }
    }

    private C4259b(Context context, Handler handler, DetectionAuthentic detectionAuthentic, int i, int i2, int i3, int i4) {
        this.f9941v = 0;
        this.f9913W = false;
        this.f9913W = false;
        this.f9927g = context;
        this.f9937r = handler;
        this.f9938s = detectionAuthentic;
        this.f9907Q = i2;
        this.f9941v = i;
        this.f9905O = i3;
        this.f9906P = i4;
        this.f9925e = m16243a(context) + "/Pic";
        this.f9904N = (i3 * 1000) / i2;
        LogUtils logUtils = this.f9930j;
        logUtils.m15967a("BaseAsyncTaskTool", "delayMills：" + String.valueOf(this.f9904N));
    }

    /* renamed from: A */
    static /* synthetic */ int m16233A(C4259b c4259b) {
        return c4259b.f9897G;
    }

    /* renamed from: B */
    static /* synthetic */ int m16232B(C4259b c4259b) {
        return c4259b.f9907Q;
    }

    /* renamed from: C */
    static /* synthetic */ int m16231C(C4259b c4259b) {
        return c4259b.m16181b();
    }

    /* renamed from: F */
    static /* synthetic */ int m16228F(C4259b c4259b) {
        return c4259b.f9899I;
    }

    /* renamed from: G */
    static /* synthetic */ AsyncTaskC4266d m16227G(C4259b c4259b) {
        return c4259b.f9934o;
    }

    /* renamed from: H */
    static /* synthetic */ int m16226H(C4259b c4259b) {
        return c4259b.f9904N;
    }

    /* renamed from: I */
    static /* synthetic */ String m16225I(C4259b c4259b) {
        return c4259b.f9925e;
    }

    /* renamed from: J */
    static /* synthetic */ boolean m16224J(C4259b c4259b) {
        return c4259b.f9910T;
    }

    /* renamed from: K */
    static /* synthetic */ int m16223K(C4259b c4259b) {
        return c4259b.f9898H;
    }

    /* renamed from: L */
    static /* synthetic */ String m16222L(C4259b c4259b) {
        return c4259b.f9924d;
    }

    /* renamed from: M */
    static /* synthetic */ boolean m16221M(C4259b c4259b) {
        return c4259b.f9911U;
    }

    /* renamed from: N */
    static /* synthetic */ boolean m16220N(C4259b c4259b) {
        return c4259b.f9916Z;
    }

    /* renamed from: O */
    static /* synthetic */ List m16219O(C4259b c4259b) {
        return c4259b.f9939t;
    }

    /* renamed from: P */
    static /* synthetic */ int m16218P(C4259b c4259b) {
        return c4259b.f9903M;
    }

    /* renamed from: Q */
    static /* synthetic */ boolean m16217Q(C4259b c4259b) {
        return c4259b.f9913W;
    }

    /* renamed from: R */
    static /* synthetic */ boolean m16216R(C4259b c4259b) {
        return c4259b.f9912V;
    }

    /* renamed from: S */
    static /* synthetic */ DetectionAuthentic m16215S(C4259b c4259b) {
        return c4259b.f9938s;
    }

    /* renamed from: V */
    static /* synthetic */ AsyncTaskC4263a m16212V(C4259b c4259b) {
        return c4259b.f9936q;
    }

    /* renamed from: a */
    static /* synthetic */ int m16202a(C4259b c4259b) {
        int i = c4259b.f9900J;
        c4259b.f9900J = i + 1;
        return i;
    }

    /* renamed from: a */
    static /* synthetic */ int m16201a(C4259b c4259b, int i) {
        c4259b.f9902L = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Bitmap m16203a(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: a */
    static /* synthetic */ Bitmap m16193a(C4259b c4259b, String str) {
        return c4259b.m16159d(str);
    }

    /* renamed from: a */
    static /* synthetic */ AsyncTaskC4263a m16197a(C4259b c4259b, AsyncTaskC4263a asyncTaskC4263a) {
        c4259b.f9936q = asyncTaskC4263a;
        return asyncTaskC4263a;
    }

    /* renamed from: a */
    public static synchronized C4259b m16205a(Context context, Handler handler, DetectionAuthentic detectionAuthentic, int i, int i2, int i3, int i4) {
        C4259b c4259b;
        synchronized (C4259b.class) {
            if (f9890l == null) {
                f9890l = new C4259b(context, handler, detectionAuthentic, i, i2, i3, i4);
            }
            c4259b = f9890l;
        }
        return c4259b;
    }

    /* renamed from: a */
    static /* synthetic */ CountDownTimerC4299j m16194a(C4259b c4259b, CountDownTimerC4299j countDownTimerC4299j) {
        c4259b.f9929i = countDownTimerC4299j;
        return countDownTimerC4299j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m16204a(Bitmap bitmap) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        this.f9893C = copy.getWidth();
        this.f9894D = copy.getHeight();
        int i = this.f9893C;
        int i2 = this.f9894D;
        this.f9892B = new int[i * i2];
        copy.getPixels(this.f9892B, 0, i, 0, 0, i, i2);
    }

    /* renamed from: a */
    static /* synthetic */ void m16199a(C4259b c4259b, Bitmap bitmap) {
        c4259b.m16204a(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m16186a(String str, Bitmap bitmap) {
        try {
            File file = new File(this.f9925e, str);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getByteCount());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            byteArrayOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    static /* synthetic */ boolean m16191a(C4259b c4259b, String str, C4286b[] c4286bArr) {
        return c4259b.m16185a(str, c4286bArr);
    }

    /* renamed from: a */
    static /* synthetic */ boolean m16189a(C4259b c4259b, boolean z) {
        c4259b.f9916Z = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m16185a(String str, C4286b[] c4286bArr) {
        float[] a = m16235a(c4286bArr);
        String str2 = "【眨眼：" + a[2] + "】";
        String str3 = "【张嘴：" + a[3] + "】";
        String str4 = "【抬头：" + a[4] + "】";
        String str5 = "【左转：" + a[0] + "】";
        String str6 = "【右转：" + a[1] + "】";
        StringBuilder sb = new StringBuilder();
        sb.append("【正对：");
        sb.append(a[1] < a[0] ? a[1] : a[0]);
        sb.append("】");
        String sb2 = sb.toString();
        Message message = new Message();
        message.what = DetectionAuthentic.DETECT_ABNORMAL;
        Bundle bundle = new Bundle();
        bundle.putString("error", str2 + str3 + str4 + str5 + str6 + "" + sb2);
        message.setData(bundle);
        this.f9937r.sendMessage(message);
        char c = 65535;
        if (str.hashCode() == 99151462 && str.equals("headF")) {
            c = 0;
        }
        if (c == 0) {
            float f = a[0];
            float f2 = this.f9909S;
            if (f >= f2 && a[1] >= f2) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    static /* synthetic */ int[] m16188a(C4259b c4259b, int[] iArr) {
        c4259b.f9892B = iArr;
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public int m16181b() {
        File file = new File(this.f9925e);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles().length;
        }
        return 0;
    }

    /* renamed from: b */
    static /* synthetic */ int m16177b(C4259b c4259b, int i) {
        c4259b.f9897G = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public Bitmap m16179b(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: b */
    static /* synthetic */ String m16174b(C4259b c4259b, String str) {
        c4259b.f9924d = str;
        return str;
    }

    /* renamed from: b */
    private void m16172b(File file) {
        try {
            C4301k c4301k = new C4301k();
            c4301k.m15934b(this.f9902L);
            c4301k.m15936a(this.f9901K);
            this.f9930j.m15967a("videoCut  time1和time2", this.f9902L + "   +  " + this.f9901K);
            this.f9930j.m15967a("原视频地址", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "");
            c4301k.m15935a(this.f9926f + "/" + file.getName());
            c4301k.m15933b(this.f9926f);
            this.f9930j.m15967a("Environment", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "");
            this.f9930j.m15967a("jia/Record", this.f9926f);
            c4301k.m15932c("New" + file.getName());
            c4301k.m15937a();
            file.delete();
            File file2 = new File(this.f9926f + "/New" + file.getName());
            this.f9930j.m15967a("BaseAsyncTaskTool", "压缩前大小   " + m16154e(file2.getPath()) + "MB");
            if (m16154e(file2.getPath()) < 1.5d) {
                this.f9930j.m15967a("BaseAsyncTaskTool", "小于1.5MB不压缩");
                String encodeToString = Base64.encodeToString(m16151f(file2.getPath()), 2);
                file2.delete();
                System.gc();
                if (this.f9898H == this.f9907Q) {
                    this.f9936q = new AsyncTaskC4263a();
                    this.f9936q.execute(new Void[0]);
                    this.f9936q.f9950a = encodeToString;
                } else {
                    this.f9930j.m15967a("timer", "pictureCounted != totalActions, this should not happen");
                }
            } else {
                final String str = this.f9926f + "/compress_" + file.getName();
                this.f9930j.m15967a("BaseAsyncTaskTool", "原视频路径:" + file2.getPath() + "   压缩后频路径:" + str);
                VideoCompress.compressVideoLow(file2.getPath(), str, new CompressListener() { // from class: com.example.asus.detectionandalign.a.b.2
                    @Override // com.example.asus.detectionandalign.listener.CompressListener
                    public void onFail() {
                        C4259b.this.f9930j.m15967a("BaseAsyncTaskTool", "压缩onFail");
                    }

                    @Override // com.example.asus.detectionandalign.listener.CompressListener
                    public void onProgress(float f) {
                        LogUtils logUtils = C4259b.this.f9930j;
                        logUtils.m15967a("BaseAsyncTaskTool", " 压缩onProgress" + String.valueOf(f) + "%");
                    }

                    @Override // com.example.asus.detectionandalign.listener.CompressListener
                    public void onStart() {
                        C4259b.this.f9930j.m15967a("BaseAsyncTaskTool", "onStart");
                        C4259b.this.f9922af = System.currentTimeMillis();
                    }

                    @Override // com.example.asus.detectionandalign.listener.CompressListener
                    public void onSuccess() {
                        C4259b.this.f9923ag = System.currentTimeMillis();
                        LogUtils logUtils = C4259b.this.f9930j;
                        logUtils.m15967a("BaseAsyncTaskTool", "压缩耗时   " + (C4259b.this.f9923ag - C4259b.this.f9922af));
                        LogUtils logUtils2 = C4259b.this.f9930j;
                        logUtils2.m15967a("BaseAsyncTaskTool", "onSuccess输出：   " + str + "\n压缩后大小   " + C4259b.this.m16154e(str) + "MB");
                        try {
                            String encodeToString2 = Base64.encodeToString(C4259b.this.m16151f(str), 2);
                            new File(str).delete();
                            System.gc();
                            if (C4259b.this.f9898H == C4259b.this.f9907Q) {
                                C4259b.this.f9936q = new AsyncTaskC4263a();
                                C4259b.this.f9936q.execute(new Void[0]);
                                C4259b.this.f9936q.f9950a = encodeToString2;
                            } else {
                                C4259b.this.f9930j.m15967a("timer", "pictureCounted != totalActions, this should not happen");
                            }
                        } catch (Exception e) {
                            if (C4259b.this.f9938s != null) {
                                C4259b.this.f9938s.onSDKUsingFail("生成数据包失败", "2003");
                            }
                            C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_DISMISS_DIALOG, 0L);
                            LogUtils logUtils3 = C4259b.this.f9930j;
                            logUtils3.m15967a("BaseAsyncTaskTool", " 压缩Exception  111 " + e);
                            C4259b.this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_FINISH, 0L);
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_DISMISS_DIALOG, 0L);
            DetectionAuthentic detectionAuthentic = this.f9938s;
            if (detectionAuthentic != null) {
                detectionAuthentic.onSDKUsingFail("生成数据包失败", "2003");
            }
            this.f9937r.sendEmptyMessageDelayed(DetectionAuthentic.DETECT_FINISH, 0L);
            this.f9930j.m15967a("BaseAsyncTaskTool", " 压缩Exception   222" + e);
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    static /* synthetic */ LogUtils m16169c(C4259b c4259b) {
        return c4259b.f9930j;
    }

    /* renamed from: c */
    static /* synthetic */ boolean m16166c(C4259b c4259b, boolean z) {
        c4259b.f9910T = z;
        return z;
    }

    /* renamed from: d */
    static /* synthetic */ int m16162d(C4259b c4259b, int i) {
        c4259b.f9898H = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public Bitmap m16159d(String str) {
        try {
            if (new File(this.f9925e, str).exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                return BitmapFactory.decodeFile(this.f9925e + "/" + str, options);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    static /* synthetic */ boolean m16160d(C4259b c4259b, boolean z) {
        c4259b.f9912V = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public double m16154e(String str) {
        File file = new File(str);
        if (file.exists()) {
            return (((float) file.length()) / 1024.0f) / 1024.0f;
        }
        return 0.0d;
    }

    /* renamed from: e */
    static /* synthetic */ int m16156e(C4259b c4259b, int i) {
        c4259b.f9899I = i;
        return i;
    }

    /* renamed from: f */
    static /* synthetic */ boolean m16152f(C4259b c4259b, boolean z) {
        c4259b.f9911U = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public byte[] m16151f(String str) {
        File file = new File(str);
        byte[] bArr = null;
        try {
            if (!file.exists()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            LogUtils logUtils = this.f9930j;
            logUtils.m15967a("fis的大小", fileInputStream.available() + "");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr2);
                if (read == -1) {
                    bArr = byteArrayOutputStream.toByteArray();
                    LogUtils logUtils2 = this.f9930j;
                    logUtils2.m15967a("buffer的大小", bArr.length + "");
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return bArr;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return bArr;
        } catch (IOException e2) {
            e2.printStackTrace();
            return bArr;
        }
    }

    /* renamed from: j */
    static /* synthetic */ boolean m16147j(C4259b c4259b) {
        return c4259b.f9918ab;
    }

    /* renamed from: m */
    static /* synthetic */ int[] m16144m(C4259b c4259b) {
        return c4259b.f9892B;
    }

    /* renamed from: n */
    static /* synthetic */ int m16143n(C4259b c4259b) {
        return c4259b.f9893C;
    }

    /* renamed from: o */
    static /* synthetic */ int m16142o(C4259b c4259b) {
        return c4259b.f9894D;
    }

    /* renamed from: p */
    static /* synthetic */ int m16141p(C4259b c4259b) {
        return c4259b.f9896F;
    }

    /* renamed from: q */
    static /* synthetic */ int m16140q(C4259b c4259b) {
        return c4259b.f9895E;
    }

    /* renamed from: r */
    static /* synthetic */ faceRecognition m16139r(C4259b c4259b) {
        return c4259b.f9931k;
    }

    /* renamed from: s */
    static /* synthetic */ float m16138s(C4259b c4259b) {
        return c4259b.f9908R;
    }

    /* renamed from: u */
    static /* synthetic */ Handler m16136u(C4259b c4259b) {
        return c4259b.f9937r;
    }

    /* renamed from: v */
    static /* synthetic */ int m16135v(C4259b c4259b) {
        return c4259b.f9905O;
    }

    /* renamed from: w */
    static /* synthetic */ boolean m16134w(C4259b c4259b) {
        return c4259b.f9919ac;
    }

    /* renamed from: y */
    static /* synthetic */ int m16132y(C4259b c4259b) {
        return c4259b.f9900J;
    }

    /* renamed from: z */
    static /* synthetic */ boolean m16131z(C4259b c4259b) {
        return c4259b.f9917aa;
    }

    /* renamed from: a */
    public void m16207a() {
        this.f9913W = true;
        if (f9890l != null) {
            f9890l = null;
        }
        AsyncTaskC4265c asyncTaskC4265c = this.f9933n;
        if (asyncTaskC4265c != null) {
            asyncTaskC4265c.cancel(true);
            this.f9930j.m15967a("mCloseImageBtn：", "mTakePicture cancel...");
        }
        AsyncTaskC4266d asyncTaskC4266d = this.f9934o;
        if (asyncTaskC4266d != null) {
            asyncTaskC4266d.cancel(true);
        }
        AsyncTaskC4264b asyncTaskC4264b = this.f9932m;
        if (asyncTaskC4264b != null) {
            asyncTaskC4264b.cancel(true);
        }
        AsyncTaskC4263a asyncTaskC4263a = this.f9936q;
        if (asyncTaskC4263a != null) {
            asyncTaskC4263a.cancel(true);
        }
        AsyncTaskC4267e asyncTaskC4267e = this.f9935p;
        if (asyncTaskC4267e != null) {
            asyncTaskC4267e.cancel(true);
        }
        if (this.f9931k != null) {
            this.f9931k = null;
        }
        this.f9928h = null;
        this.f9920ad = null;
        this.f9921ae = null;
        this.f9935p = null;
        this.f9934o = null;
        this.f9933n = null;
        this.f9932m = null;
        this.f9936q = null;
        this.f9892B = null;
        System.gc();
    }

    /* renamed from: a */
    public void m16206a(float f) {
        this.f9908R = f;
    }

    /* renamed from: a */
    public void m16187a(File file) {
        this.f9911U = true;
        this.f9914X = true;
        m16172b(file);
        Log.e("====================   ", this.f9900J + "   " + this.f9901K);
    }

    /* renamed from: a */
    public void m16184a(boolean z) {
        this.f9917aa = z;
    }

    /* renamed from: a */
    public void m16183a(byte[] bArr, JCameraView jCameraView) {
        this.f9940u++;
        if (this.f9940u < 20) {
            this.f9930j.m15967a("onPreviewFrame", "onPreviewFrame, drop frame id: " + this.f9940u);
            Camera.Size previewSize = jCameraView.getPreviewSize();
            if (previewSize != null) {
                this.f9942w = previewSize.height;
                this.f9943x = previewSize.width;
            }
        } else if (!this.f9915Y) {
            if (this.f9932m != null) {
                switch (C42623.f9949a[this.f9932m.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f9932m.cancel(false);
                        break;
                }
            }
            this.f9932m = new AsyncTaskC4264b(bArr);
            this.f9932m.execute(null);
        } else if (this.f9916Z) {
            if (this.f9933n != null) {
                switch (C42623.f9949a[this.f9933n.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f9933n.cancel(false);
                        break;
                }
            }
            this.f9933n = new AsyncTaskC4265c(bArr);
            this.f9933n.execute(null);
        }
    }

    /* renamed from: b */
    public void m16180b(float f) {
        this.f9909S = f;
    }

    /* renamed from: b */
    public void m16171b(boolean z) {
        this.f9918ab = z;
    }

    /* renamed from: b */
    public void m16170b(byte[] bArr, int i, int i2) {
        this.f9940u++;
        if (this.f9940u < 20) {
            this.f9930j.m15967a("onPreviewFrame", "onPreviewFrame, drop frame id: " + this.f9940u);
            this.f9942w = i2;
            this.f9943x = i;
        } else if (!this.f9915Y) {
            if (this.f9932m != null) {
                switch (C42623.f9949a[this.f9932m.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f9932m.cancel(false);
                        break;
                }
            }
            this.f9932m = new AsyncTaskC4264b(bArr);
            this.f9932m.execute(null);
        } else if (this.f9916Z) {
            if (this.f9933n != null) {
                switch (C42623.f9949a[this.f9933n.getStatus().ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        this.f9933n.cancel(false);
                        break;
                }
            }
            this.f9933n = new AsyncTaskC4265c(bArr);
            this.f9933n.execute(null);
        }
    }

    /* renamed from: c */
    public void m16165c(String str) {
        this.f9926f = str;
    }

    /* renamed from: c */
    public void m16164c(boolean z) {
        this.f9919ac = z;
    }

    /* renamed from: d */
    public void m16158d(boolean z) {
        if (z) {
            this.f9944y = 600;
            this.f9945z = 600;
            this.f9891A = 800;
        }
    }
}
