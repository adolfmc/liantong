package comp.android.app.face.p381sz.camera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.ImageView;
import com.cjt2325.cameralibrary.CameraInterface;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import comp.android.app.face.p381sz.camera.listener.ErrorListener;
import comp.android.app.face.p381sz.camera.listener.JCameraListener;
import comp.android.app.face.p381sz.camera.listener.OnPreviewCallback;
import comp.android.app.face.p381sz.camera.util.C11781a;
import comp.android.app.face.p381sz.camera.util.C11782b;
import comp.android.app.face.p381sz.camera.util.C11786c;
import comp.android.app.face.p381sz.camera.util.C11789f;
import comp.android.app.face.p381sz.camera.util.C11791g;
import comp.android.app.face.p381sz.camera.util.ScreenUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.FileDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import szcom.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl;

/* renamed from: comp.android.app.face.sz.camera.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11753a implements Camera.PreviewCallback {

    /* renamed from: c */
    private static volatile C11753a f23890c;

    /* renamed from: B */
    private byte[] f23892B;

    /* renamed from: E */
    private Camera.Size f23895E;

    /* renamed from: F */
    private Camera.Size f23896F;

    /* renamed from: G */
    private Camera.Size f23897G;

    /* renamed from: L */
    private int f23902L;

    /* renamed from: N */
    private C11789f f23904N;

    /* renamed from: a */
    JCameraListener f23907a;

    /* renamed from: d */
    private Camera f23909d;

    /* renamed from: e */
    private Camera.Parameters f23910e;

    /* renamed from: g */
    private int f23912g;

    /* renamed from: m */
    private MediaRecorder f23918m;

    /* renamed from: n */
    private String f23919n;

    /* renamed from: o */
    private String f23920o;

    /* renamed from: p */
    private String f23921p;

    /* renamed from: q */
    private String f23922q;

    /* renamed from: s */
    private OnPreviewCallback f23924s;

    /* renamed from: t */
    private ErrorListener f23925t;

    /* renamed from: u */
    private ImageView f23926u;

    /* renamed from: v */
    private ImageView f23927v;

    /* renamed from: w */
    private int f23928w;

    /* renamed from: x */
    private int f23929x;

    /* renamed from: f */
    private boolean f23911f = false;

    /* renamed from: h */
    private int f23913h = -1;

    /* renamed from: i */
    private int f23914i = -1;

    /* renamed from: j */
    private SurfaceHolder f23915j = null;

    /* renamed from: k */
    private float f23916k = -1.0f;

    /* renamed from: l */
    private boolean f23917l = false;

    /* renamed from: r */
    private Bitmap f23923r = null;

    /* renamed from: y */
    private int f23930y = 0;

    /* renamed from: z */
    private int f23931z = 90;

    /* renamed from: A */
    private int f23891A = 0;

    /* renamed from: C */
    private int f23893C = 0;

    /* renamed from: D */
    private int f23894D = 0;

    /* renamed from: H */
    private boolean f23898H = true;

    /* renamed from: I */
    private int f23899I = 1600000;

    /* renamed from: J */
    private SensorManager f23900J = null;

    /* renamed from: K */
    private SensorEventListener f23901K = new SensorEventListener() { // from class: comp.android.app.face.sz.camera.a.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (1 != sensorEvent.sensor.getType()) {
                return;
            }
            float[] fArr = sensorEvent.values;
            C11753a.this.f23930y = C11781a.m2151a(fArr[0], fArr[1]);
            C11753a.this.m2198m();
        }
    };

    /* renamed from: M */
    private int f23903M = 30;

    /* renamed from: O */
    private boolean f23905O = false;

    /* renamed from: P */
    private boolean f23906P = false;

    /* renamed from: b */
    int f23908b = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: comp.android.app.face.sz.camera.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11757a {
        void cameraHasOpened();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: comp.android.app.face.sz.camera.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11758b {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: comp.android.app.face.sz.camera.a$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11759c {
        /* renamed from: a */
        void mo2238a();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: comp.android.app.face.sz.camera.a$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11760d {
        /* renamed from: a */
        void mo2189a(String str, Bitmap bitmap);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: comp.android.app.face.sz.camera.a$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11761e {
        /* renamed from: a */
        void mo2181a(byte[] bArr, boolean z);
    }

    private C11753a(int i) {
        this.f23912g = -1;
        m2197n();
        if (i == 0) {
            this.f23912g = 0;
        } else if (i == 1) {
            this.f23912g = 1;
        } else {
            this.f23912g = this.f23913h;
        }
        this.f23920o = Environment.getExternalStorageDirectory().getPath();
        this.f23921p = this.f23920o + "/test.h264";
    }

    /* renamed from: a */
    private static int m2232a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    /* renamed from: a */
    private static Rect m2235a(float f, float f2, float f3, Context context) {
        int intValue = Float.valueOf(f3 * 300.0f).intValue();
        int i = intValue / 2;
        int m2232a = m2232a(((int) (((f / ScreenUtils.getScreenWidth(context)) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        int m2232a2 = m2232a(((int) (((f2 / ScreenUtils.getScreenHeight(context)) * 2000.0f) - 1000.0f)) - i, -1000, 1000);
        RectF rectF = new RectF(m2232a, m2232a2, m2232a + intValue, m2232a2 + intValue);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    /* renamed from: a */
    public static synchronized C11753a m2236a() {
        C11753a c11753a;
        synchronized (C11753a.class) {
            if (f23890c == null) {
                synchronized (C11753a.class) {
                    if (f23890c == null) {
                        f23890c = new C11753a(-1);
                    }
                }
            }
            c11753a = f23890c;
        }
        return c11753a;
    }

    /* renamed from: a */
    public static synchronized C11753a m2233a(int i) {
        C11753a c11753a;
        synchronized (C11753a.class) {
            if (f23890c == null) {
                synchronized (C11753a.class) {
                    if (f23890c == null) {
                        f23890c = new C11753a(i);
                    }
                }
            }
            c11753a = f23890c;
        }
        return c11753a;
    }

    /* renamed from: b */
    public static synchronized void m2216b() {
        synchronized (C11753a.class) {
            if (f23890c != null) {
                f23890c = null;
            }
        }
    }

    /* renamed from: c */
    private synchronized void m2208c(int i) {
        try {
            this.f23909d = Camera.open(i);
        } catch (Exception e) {
            e.printStackTrace();
            if (this.f23925t != null) {
                this.f23925t.onError();
            }
        }
        if (Build.VERSION.SDK_INT > 17 && this.f23909d != null) {
            try {
                this.f23909d.enableShutterSound(false);
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.e("CJT", "enable shutter sound faild");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public void m2198m() {
        int i;
        int i2;
        if (this.f23926u == null || (i = this.f23891A) == (i2 = this.f23930y)) {
            return;
        }
        int i3 = SubsamplingScaleImageView.ORIENTATION_270;
        int i4 = 180;
        int i5 = 90;
        if (i == 0) {
            i4 = i2 != 90 ? i2 != 270 ? 0 : 90 : -90;
            i5 = 0;
        } else if (i == 90) {
            i4 = (i2 == 0 || i2 != 180) ? 0 : -180;
            i5 = -90;
        } else if (i == 180) {
            if (i2 != 90) {
                i3 = i2 != 270 ? 0 : 90;
            }
            i5 = 180;
            i4 = i3;
        } else if (i != 270) {
            i4 = 0;
            i5 = 0;
        } else if (i2 == 0 || i2 != 180) {
            i4 = 0;
        }
        float f = i5;
        float f2 = i4;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f23926u, "rotation", f, f2);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f23927v, "rotation", f, f2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(500L);
        animatorSet.start();
        this.f23891A = this.f23930y;
    }

    /* renamed from: n */
    private void m2197n() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            switch (cameraInfo.facing) {
                case 0:
                    this.f23913h = cameraInfo.facing;
                    break;
                case 1:
                    this.f23914i = cameraInfo.facing;
                    break;
            }
        }
    }

    /* renamed from: a */
    public void m2234a(float f, int i) {
        int i2;
        int maxZoom;
        Camera camera = this.f23909d;
        if (camera == null) {
            return;
        }
        if (this.f23910e == null) {
            this.f23910e = camera.getParameters();
        }
        if (this.f23910e.isZoomSupported() && this.f23910e.isSmoothZoomSupported()) {
            switch (i) {
                case 144:
                    if (this.f23917l && f >= 0.0f && (i2 = (int) (f / 40.0f)) <= this.f23910e.getMaxZoom() && i2 >= this.f23893C && this.f23894D != i2) {
                        this.f23910e.setZoom(i2);
                        this.f23909d.setParameters(this.f23910e);
                        this.f23894D = i2;
                        return;
                    }
                    return;
                case CameraInterface.TYPE_CAPTURE /* 145 */:
                    if (this.f23917l) {
                        return;
                    }
                    int i3 = (int) (f / 50.0f);
                    if (i3 < this.f23910e.getMaxZoom()) {
                        this.f23893C += i3;
                        int i4 = this.f23893C;
                        if (i4 < 0) {
                            maxZoom = 0;
                        } else {
                            if (i4 > this.f23910e.getMaxZoom()) {
                                maxZoom = this.f23910e.getMaxZoom();
                            }
                            this.f23910e.setZoom(this.f23893C);
                            this.f23909d.setParameters(this.f23910e);
                        }
                        this.f23893C = maxZoom;
                        this.f23910e.setZoom(this.f23893C);
                        this.f23909d.setParameters(this.f23910e);
                    }
                    C11791g.m2121a("setZoom = " + this.f23893C);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2231a(Context context) {
        if (this.f23900J == null) {
            this.f23900J = (SensorManager) context.getSystemService("sensor");
        }
        SensorManager sensorManager = this.f23900J;
        sensorManager.registerListener(this.f23901K, sensorManager.getDefaultSensor(1), 3);
    }

    /* renamed from: a */
    public void m2230a(final Context context, final float f, final float f2, final InterfaceC11759c interfaceC11759c) {
        Camera camera = this.f23909d;
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        Rect m2235a = m2235a(f, f2, 1.0f, context);
        this.f23909d.cancelAutoFocus();
        if (parameters.getMaxNumFocusAreas() <= 0) {
            Log.i("CameraInterface", "focus areas not supported");
            interfaceC11759c.mo2238a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Camera.Area(m2235a, 800));
        parameters.setFocusAreas(arrayList);
        final String focusMode = parameters.getFocusMode();
        try {
            parameters.setFocusMode("auto");
            this.f23909d.setParameters(parameters);
            this.f23909d.autoFocus(new Camera.AutoFocusCallback() { // from class: comp.android.app.face.sz.camera.a.3
                @Override // android.hardware.Camera.AutoFocusCallback
                public void onAutoFocus(boolean z, Camera camera2) {
                    Log.e("=======================", "onAutoFocus");
                    if (!z && C11753a.this.f23908b <= 10) {
                        C11753a.this.f23908b++;
                        C11753a.this.m2230a(context, f, f2, interfaceC11759c);
                        return;
                    }
                    Camera.Parameters parameters2 = camera2.getParameters();
                    parameters2.setFocusMode(focusMode);
                    camera2.setParameters(parameters2);
                    C11753a.this.f23908b = 0;
                    interfaceC11759c.mo2238a();
                }
            });
        } catch (Exception unused) {
            Log.e("CameraInterface", "autoFocus failer");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x017d, code lost:
        if (r14 == 270) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0188, code lost:
        if (r14 == 270) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x018a, code lost:
        r11.f23918m.setOrientationHint(90);
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01d4  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m2229a(android.view.Surface r12, float r13, comp.android.app.face.p381sz.camera.C11753a.InterfaceC11758b r14) {
        /*
            Method dump skipped, instructions count: 571
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: comp.android.app.face.p381sz.camera.C11753a.m2229a(android.view.Surface, float, comp.android.app.face.sz.camera.a$b):void");
    }

    /* renamed from: a */
    public synchronized void m2228a(SurfaceHolder surfaceHolder, float f) {
        this.f23912g = this.f23912g == this.f23913h ? this.f23914i : this.f23913h;
        m2203h();
        C11791g.m2121a("open start");
        m2208c(this.f23912g);
        if (Build.VERSION.SDK_INT > 17 && this.f23909d != null) {
            try {
                this.f23909d.enableShutterSound(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        C11791g.m2121a("open end");
        m2213b(surfaceHolder, f);
    }

    /* renamed from: a */
    public void m2227a(ImageView imageView, ImageView imageView2) {
        this.f23926u = imageView;
        this.f23927v = imageView2;
        if (imageView != null) {
            this.f23931z = C11782b.m2150a().m2149a(imageView.getContext(), this.f23912g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2226a(InterfaceC11757a interfaceC11757a) {
        ErrorListener errorListener;
        if (Build.VERSION.SDK_INT < 23 && !C11786c.m2141a(this.f23912g) && (errorListener = this.f23925t) != null) {
            errorListener.onError();
            return;
        }
        if (this.f23909d == null) {
            m2208c(this.f23912g);
        }
        interfaceC11757a.cameraHasOpened();
    }

    /* renamed from: a */
    public void m2225a(final InterfaceC11761e interfaceC11761e) {
        if (this.f23909d == null) {
            return;
        }
        Log.i("CJT", this.f23930y + " = " + this.f23931z + " = " + this.f23902L);
        this.f23909d.takePicture(null, null, new Camera.PictureCallback() { // from class: comp.android.app.face.sz.camera.a.2
            @Override // android.hardware.Camera.PictureCallback
            public void onPictureTaken(byte[] bArr, Camera camera) {
                InterfaceC11761e interfaceC11761e2;
                boolean z;
                if (interfaceC11761e != null) {
                    if (C11753a.this.f23902L == 90 || C11753a.this.f23902L == 270) {
                        interfaceC11761e2 = interfaceC11761e;
                        z = true;
                    } else {
                        interfaceC11761e2 = interfaceC11761e;
                        z = false;
                    }
                    interfaceC11761e2.mo2181a(bArr, z);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2222a(ErrorListener errorListener) {
        this.f23925t = errorListener;
    }

    /* renamed from: a */
    public void m2221a(JCameraListener jCameraListener) {
        this.f23907a = jCameraListener;
    }

    /* renamed from: a */
    public void m2220a(OnPreviewCallback onPreviewCallback) {
        this.f23924s = onPreviewCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2219a(String str) {
        this.f23920o = str;
        this.f23921p = str + "/test.h264";
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* renamed from: a */
    public void m2218a(boolean z) {
        this.f23898H = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
        if (r2 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r4 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0045, code lost:
        if (comp.android.app.face.p381sz.camera.util.C11788e.m2138a(r3.f23922q) == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        r5.mo2189a(null, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004d, code lost:
        if (r3.f23898H == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004f, code lost:
        m2204g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:
        r5.mo2189a(r3.f23920o + java.io.File.separator + r3.f23919n, r3.f23923r);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m2217a(boolean r4, comp.android.app.face.p381sz.camera.C11753a.InterfaceC11760d r5) {
        /*
            r3 = this;
            boolean r0 = r3.f23917l
            if (r0 != 0) goto L5
            return
        L5:
            android.media.MediaRecorder r0 = r3.f23918m
            if (r0 == 0) goto L7c
            r1 = 0
            r0.setOnErrorListener(r1)
            android.media.MediaRecorder r0 = r3.f23918m
            r0.setOnInfoListener(r1)
            android.media.MediaRecorder r0 = r3.f23918m
            r0.setPreviewDisplay(r1)
            r0 = 0
            android.media.MediaRecorder r2 = r3.f23918m     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2b
            r2.stop()     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2b
            android.media.MediaRecorder r2 = r3.f23918m
            if (r2 == 0) goto L24
        L21:
            r2.release()
        L24:
            r3.f23918m = r1
            r3.f23917l = r0
            goto L3d
        L29:
            r4 = move-exception
            goto L70
        L2b:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L29
            r3.f23918m = r1     // Catch: java.lang.Throwable -> L29
            android.media.MediaRecorder r2 = new android.media.MediaRecorder     // Catch: java.lang.Throwable -> L29
            r2.<init>()     // Catch: java.lang.Throwable -> L29
            r3.f23918m = r2     // Catch: java.lang.Throwable -> L29
            android.media.MediaRecorder r2 = r3.f23918m
            if (r2 == 0) goto L24
            goto L21
        L3d:
            if (r4 == 0) goto L4b
            java.lang.String r4 = r3.f23922q
            boolean r4 = comp.android.app.face.p381sz.camera.util.C11788e.m2138a(r4)
            if (r4 == 0) goto L4a
            r5.mo2189a(r1, r1)
        L4a:
            return
        L4b:
            boolean r4 = r3.f23898H
            if (r4 == 0) goto L52
            r3.m2204g()
        L52:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = r3.f23920o
            r4.append(r0)
            java.lang.String r0 = java.io.File.separator
            r4.append(r0)
            java.lang.String r0 = r3.f23919n
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            android.graphics.Bitmap r0 = r3.f23923r
            r5.mo2189a(r4, r0)
            goto L7c
        L70:
            android.media.MediaRecorder r5 = r3.f23918m
            if (r5 == 0) goto L77
            r5.release()
        L77:
            r3.f23918m = r1
            r3.f23917l = r0
            throw r4
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: comp.android.app.face.p381sz.camera.C11753a.m2217a(boolean, comp.android.app.face.sz.camera.a$d):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2215b(int i) {
        this.f23899I = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2214b(Context context) {
        if (this.f23900J == null) {
            this.f23900J = (SensorManager) context.getSystemService("sensor");
        }
        this.f23900J.unregisterListener(this.f23901K);
    }

    /* renamed from: b */
    public void m2213b(SurfaceHolder surfaceHolder, float f) {
        if (this.f23911f) {
            C11791g.m2121a("doStartPreview isPreviewing");
        }
        if (this.f23916k < 0.0f) {
            this.f23916k = f;
        }
        if (surfaceHolder == null) {
            return;
        }
        this.f23915j = surfaceHolder;
        Camera camera = this.f23909d;
        if (camera != null) {
            try {
                this.f23910e = camera.getParameters();
                this.f23895E = C11782b.m2150a().m2148a(this.f23910e.getSupportedPreviewSizes(), f);
                this.f23896F = C11782b.m2150a().m2145b(this.f23910e.getSupportedPictureSizes(), f);
                Log.e("CameraInterface", "screenProp: ");
                Log.e("CameraInterface", "setPreviewSize: " + this.f23895E.width + "  " + this.f23895E.height);
                Log.e("CameraInterface", "===========================setPreviewSize: " + this.f23895E.width + "  " + this.f23895E.height);
                this.f23910e.setPreviewSize(this.f23895E.width, this.f23895E.height);
                this.f23928w = this.f23895E.width;
                this.f23929x = this.f23895E.height;
                Log.e("CameraInterface", "setPictureSize:" + this.f23896F.width + "  " + this.f23896F.height);
                this.f23910e.setPictureSize(this.f23896F.width, this.f23896F.height);
                if (C11782b.m2150a().m2146a(this.f23910e.getSupportedFocusModes(), "auto")) {
                    this.f23910e.setFocusMode("auto");
                }
                if (C11782b.m2150a().m2147a(this.f23910e.getSupportedPictureFormats(), 256)) {
                    this.f23910e.setPictureFormat(256);
                    this.f23910e.setJpegQuality(100);
                }
                this.f23909d.setParameters(this.f23910e);
                this.f23910e = this.f23909d.getParameters();
                this.f23909d.setPreviewDisplay(surfaceHolder);
                this.f23909d.setDisplayOrientation(this.f23931z);
                this.f23909d.setPreviewCallback(this);
                this.f23909d.startPreview();
                this.f23911f = true;
                Log.i("CameraInterface", "=== Start Preview ===");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public void m2211b(String str) {
        Camera camera = this.f23909d;
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(str);
        this.f23909d.setParameters(parameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2210b(boolean z) {
        this.f23911f = z;
    }

    /* renamed from: c */
    public Camera.Size m2209c() {
        return this.f23895E;
    }

    /* renamed from: d */
    public Camera.Size m2207d() {
        return this.f23896F;
    }

    /* renamed from: e */
    public Camera.Size m2206e() {
        return this.f23897G;
    }

    /* renamed from: f */
    public boolean m2205f() {
        return this.f23911f;
    }

    /* renamed from: g */
    public void m2204g() {
        Camera camera = this.f23909d;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.f23909d.stopPreview();
                this.f23909d.setPreviewDisplay(null);
                this.f23911f = false;
                Log.i("CameraInterface", "=== Stop Preview ===");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public void m2203h() {
        this.f23925t = null;
        Camera camera = this.f23909d;
        if (camera == null) {
            Log.i("CameraInterface", "=== Camera  Null===");
            return;
        }
        try {
            camera.setPreviewCallback(null);
            this.f23926u = null;
            this.f23927v = null;
            this.f23909d.stopPreview();
            this.f23909d.setPreviewDisplay(null);
            this.f23915j = null;
            this.f23911f = false;
            this.f23909d.release();
            this.f23909d = null;
            Log.i("CameraInterface", "=== Destroy Camera ===");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public void m2202i() {
        if (this.f23905O) {
            return;
        }
        this.f23905O = true;
        Log.e("CameraInterface", "======recordFrame_PreviewSize: " + this.f23895E.width + "  " + this.f23895E.height);
        this.f23904N = new C11789f(this.f23895E.width, this.f23895E.height, this.f23903M, new File(this.f23921p), this.f23912g);
        this.f23904N.m2126c();
    }

    /* renamed from: j */
    public void m2201j() {
        String str;
        StringBuilder sb;
        FileOutputStream fileOutputStream;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f23905O) {
            this.f23905O = false;
            Log.e("CameraInterface", "======stopRecordFrame ");
            C11789f c11789f = this.f23904N;
            if (c11789f != null) {
                c11789f.m2124d();
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    String str2 = this.f23920o + "/video_" + System.currentTimeMillis() + ".mp4";
                    H264TrackImpl h264TrackImpl = new H264TrackImpl(new FileDataSourceImpl(this.f23921p));
                    Movie movie = new Movie();
                    movie.addTrack(h264TrackImpl);
                    Container build = new DefaultMp4Builder().build(movie);
                    Log.e("CameraInterface", "mp4Path:  " + str2);
                    fileOutputStream = new FileOutputStream(str2);
                    try {
                        build.writeContainer(fileOutputStream.getChannel());
                        if (this.f23907a != null) {
                            this.f23907a.recordSuccess(str2, null);
                        }
                        File file = new File(this.f23921p);
                        if (file.exists()) {
                            file.delete();
                        }
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream2 = fileOutputStream;
                        Log.e("CameraInterface", "Exception  " + e);
                        e.printStackTrace();
                        File file2 = new File(this.f23921p);
                        if (file2.exists()) {
                            file2.delete();
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e2) {
                                e = e2;
                                str = "CameraInterface";
                                sb = new StringBuilder();
                                sb.append("IOException ");
                                sb.append(e);
                                Log.e(str, sb.toString());
                                e.printStackTrace();
                                long currentTimeMillis2 = System.currentTimeMillis();
                                Log.e("CameraInterface", "toMP4 elapsed_time: " + (currentTimeMillis2 - currentTimeMillis));
                            }
                        }
                        long currentTimeMillis22 = System.currentTimeMillis();
                        Log.e("CameraInterface", "toMP4 elapsed_time: " + (currentTimeMillis22 - currentTimeMillis));
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream2 = fileOutputStream;
                        File file3 = new File(this.f23921p);
                        if (file3.exists()) {
                            file3.delete();
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e3) {
                                Log.e("CameraInterface", "IOException " + e3);
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
            try {
                fileOutputStream.close();
            } catch (IOException e5) {
                e = e5;
                str = "CameraInterface";
                sb = new StringBuilder();
                sb.append("IOException ");
                sb.append(e);
                Log.e(str, sb.toString());
                e.printStackTrace();
                long currentTimeMillis222 = System.currentTimeMillis();
                Log.e("CameraInterface", "toMP4 elapsed_time: " + (currentTimeMillis222 - currentTimeMillis));
            }
        }
        long currentTimeMillis2222 = System.currentTimeMillis();
        Log.e("CameraInterface", "toMP4 elapsed_time: " + (currentTimeMillis2222 - currentTimeMillis));
    }

    /* renamed from: k */
    public void m2200k() {
        if (this.f23905O) {
            if (this.f23906P) {
                Log.e("CameraInterface", "pauseRecordFrame  继续");
                this.f23906P = false;
                C11789f c11789f = this.f23904N;
                if (c11789f != null) {
                    c11789f.m2128b();
                    return;
                }
                return;
            }
            Log.e("CameraInterface", "pauseRecordFrame  暂停");
            this.f23906P = true;
            C11789f c11789f2 = this.f23904N;
            if (c11789f2 != null) {
                c11789f2.m2137a();
            }
        }
    }

    @RequiresApi(api = 24)
    /* renamed from: l */
    public void m2199l() {
        if (this.f23917l) {
            if (this.f23906P) {
                this.f23906P = false;
                this.f23918m.resume();
                return;
            }
            this.f23906P = true;
            this.f23918m.pause();
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        C11789f c11789f;
        this.f23892B = bArr;
        if (this.f23905O && (c11789f = this.f23904N) != null) {
            c11789f.m2131a(bArr, this.f23912g);
        }
        OnPreviewCallback onPreviewCallback = this.f23924s;
        if (onPreviewCallback != null) {
            onPreviewCallback.onPreviewFrame(bArr, camera);
        }
    }
}
