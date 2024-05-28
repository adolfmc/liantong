package com.megvii.lv5;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ExifInterface;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.megvii.lv5.AbstractC5500m;
import com.megvii.lv5.C5479j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@TargetApi(21)
/* renamed from: com.megvii.lv5.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5426f extends AbstractC5500m {

    /* renamed from: Q */
    public static final Map<String, Integer> f12547Q = new C5428b();

    /* renamed from: R */
    public static final SparseIntArray f12548R;

    /* renamed from: A */
    public File f12549A;

    /* renamed from: C */
    public CaptureRequest.Builder f12551C;

    /* renamed from: D */
    public CameraCaptureSession f12552D;

    /* renamed from: G */
    public boolean f12555G;

    /* renamed from: H */
    public boolean f12556H;

    /* renamed from: I */
    public ImageReader.OnImageAvailableListener f12557I;

    /* renamed from: J */
    public ImageReader.OnImageAvailableListener f12558J;

    /* renamed from: K */
    public final CameraDevice.StateCallback f12559K;

    /* renamed from: L */
    public volatile int f12560L;

    /* renamed from: M */
    public int f12561M;

    /* renamed from: N */
    public CameraCaptureSession.CaptureCallback f12562N;

    /* renamed from: O */
    public long f12563O;

    /* renamed from: P */
    public long f12564P;

    /* renamed from: k */
    public Context f12565k;

    /* renamed from: l */
    public HandlerThread f12566l;

    /* renamed from: m */
    public Handler f12567m;

    /* renamed from: n */
    public Handler f12568n;

    /* renamed from: o */
    public CameraManager f12569o;

    /* renamed from: p */
    public CameraDevice f12570p;

    /* renamed from: q */
    public String f12571q;

    /* renamed from: r */
    public boolean f12572r;

    /* renamed from: s */
    public AbstractC5500m.InterfaceC5502b f12573s;

    /* renamed from: t */
    public AbstractC5500m.InterfaceC5504d f12574t;

    /* renamed from: u */
    public AbstractC5500m.InterfaceC5505e f12575u;

    /* renamed from: v */
    public ImageReader f12576v;

    /* renamed from: w */
    public AbstractC5500m.InterfaceC5503c f12577w;

    /* renamed from: x */
    public SurfaceTexture f12578x;

    /* renamed from: y */
    public ImageReader f12579y;

    /* renamed from: z */
    public AbstractC5500m.InterfaceC5501a f12580z;

    /* renamed from: B */
    public Map<Integer, Integer> f12550B = new HashMap();

    /* renamed from: E */
    public Lock f12553E = new ReentrantLock();

    /* renamed from: F */
    public Lock f12554F = new ReentrantLock();

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5427a implements Comparator<Size> {
        public C5427a() {
        }

        @Override // java.util.Comparator
        public int compare(Size size, Size size2) {
            Size size3 = size;
            Size size4 = size2;
            int width = size3.getWidth() * size3.getHeight();
            C5426f c5426f = C5426f.this;
            int abs = Math.abs(width - (c5426f.f12870d * c5426f.f12871e));
            int width2 = size4.getWidth() * size4.getHeight();
            C5426f c5426f2 = C5426f.this;
            return abs - Math.abs(width2 - (c5426f2.f12870d * c5426f2.f12871e));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5428b extends HashMap<String, Integer> {
        public C5428b() {
            put("off", 0);
            put("auto", 1);
            put("incandescent", 2);
            put("fluorescent", 3);
            put("warm-fluorescent", 4);
            put("daylight", 5);
            put("cloudy-daylight", 6);
            put("twilight", 7);
            put("shade", 8);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5429c implements ImageReader.OnImageAvailableListener {
        public C5429c() {
        }

        /* JADX WARN: Removed duplicated region for block: B:51:0x00cc  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00d2  */
        @Override // android.media.ImageReader.OnImageAvailableListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onImageAvailable(android.media.ImageReader r12) {
            /*
                Method dump skipped, instructions count: 223
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5426f.C5429c.onImageAvailable(android.media.ImageReader):void");
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5430d implements ImageReader.OnImageAvailableListener {
        public C5430d() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            String attribute;
            String attribute2;
            String attribute3;
            double d;
            if (C5426f.this.f12552D == null) {
                return;
            }
            Image acquireNextImage = imageReader.acquireNextImage();
            ByteBuffer buffer = acquireNextImage.getPlanes()[0].getBuffer();
            byte[] bArr = new byte[buffer.remaining()];
            buffer.get(bArr);
            C5426f c5426f = C5426f.this;
            File file = null;
            if (c5426f.m13547a((Object) c5426f.f12574t)) {
                C5426f.this.f12574t.mo12929a(bArr);
                C5426f.this.f12574t = null;
            }
            C5426f c5426f2 = C5426f.this;
            if (c5426f2.f12575u == null) {
                acquireNextImage.close();
                return;
            }
            try {
                if (c5426f2.f12549A != null) {
                    File file2 = new File(c5426f2.f12549A, "flashImage");
                    if (file2.exists()) {
                        C5527o2.m13247a(file2);
                    }
                    file2.mkdirs();
                    if (file2.canWrite()) {
                        file = new File(file2, "evcheck");
                    }
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                ExifInterface exifInterface = new ExifInterface(file.getPath());
                attribute = exifInterface.getAttribute("FNumber");
                attribute2 = exifInterface.getAttribute("ExposureTime");
                attribute3 = exifInterface.getAttribute("ISOSpeedRatings");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (attribute != null && attribute2 != null && attribute3 != null) {
                double doubleValue = Double.valueOf(attribute).doubleValue();
                double doubleValue2 = Double.valueOf(attribute2).doubleValue();
                double doubleValue3 = Double.valueOf(attribute3.split(",")[0]).doubleValue() * 1.0d;
                C5426f.this.getClass();
                try {
                    d = (Math.log(Math.pow(doubleValue, 2.0d) / doubleValue2) / Math.log(2.0d)) - (Math.log(doubleValue3 / 100.0d) / Math.log(2.0d));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    d = -101.0d;
                }
                C5426f.this.f12575u.mo13438a(d);
                file.delete();
                acquireNextImage.close();
            }
            C5426f.this.f12575u.mo13438a(-102.0d);
            file.delete();
            acquireNextImage.close();
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5431e extends CameraDevice.StateCallback {
        public C5431e() {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            try {
                CameraDevice cameraDevice2 = C5426f.this.f12570p;
                if (cameraDevice2 != null) {
                    cameraDevice2.close();
                    C5426f.this.f12570p = null;
                }
            } catch (Throwable unused) {
            }
            C5426f.this.m13544d(2);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            String str = "onError()...code:" + i;
            try {
                CameraDevice cameraDevice2 = C5426f.this.f12570p;
                if (cameraDevice2 != null) {
                    cameraDevice2.close();
                    C5426f.this.f12570p = null;
                }
            } catch (Throwable unused) {
            }
            C5426f.this.m13544d(3);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            C5426f c5426f = C5426f.this;
            c5426f.f12570p = cameraDevice;
            c5426f.m13544d(1);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5432f extends CameraCaptureSession.CaptureCallback {
        public C5432f() {
        }

        /* renamed from: a */
        public final void m13542a(CaptureResult captureResult) {
            Integer num;
            C5426f c5426f;
            String str = "CaptureCallback... mState = " + C5426f.this.f12560L;
            int i = C5426f.this.f12560L;
            if (i == 1) {
                C5426f.this.f12561M = 0;
                Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num2 == null) {
                    C5426f.m13550a(C5426f.this);
                    return;
                } else if ((4 == num2.intValue() || 5 == num2.intValue()) && (num = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE)) != null && num.intValue() != 2) {
                    C5426f c5426f2 = C5426f.this;
                    c5426f2.getClass();
                    try {
                        c5426f2.f12551C.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                        c5426f2.f12560L = 2;
                        c5426f2.f12552D.capture(c5426f2.f12551C.build(), c5426f2.f12562N, c5426f2.f12567m);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            } else if (i == 2) {
                Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num3 == null || num3.intValue() != 2) {
                    if (num3 == null || num3.intValue() == 5 || num3.intValue() == 4) {
                        C5426f.this.f12560L = 3;
                        return;
                    }
                    return;
                }
            } else if (i != 3) {
                return;
            } else {
                Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num4 != null && num4.intValue() == 5) {
                    c5426f = C5426f.this;
                    int i2 = c5426f.f12561M + 1;
                    c5426f.f12561M = i2;
                    if (i2 >= 2) {
                        c5426f.f12561M = 0;
                        c5426f.f12560L = 4;
                        C5426f.m13550a(C5426f.this);
                    }
                    return;
                }
            }
            c5426f = C5426f.this;
            c5426f.f12560L = 4;
            C5426f.m13550a(C5426f.this);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            m13542a(totalCaptureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            m13542a(captureResult);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f$g */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5433g implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ int f12586a;

        public RunnableC5433g(int i) {
            this.f12586a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            C5426f c5426f = C5426f.this;
            if (c5426f.m13547a(c5426f.f12573s)) {
                int i = this.f12586a;
                if (i == 1) {
                    C5426f.this.f12573s.mo12923c();
                } else if (i == 2) {
                    C5426f.this.f12573s.mo12939a();
                } else if (i != 3) {
                } else {
                    C5426f.this.f12573s.mo12927b();
                }
            }
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f12548R = sparseIntArray;
        sparseIntArray.append(0, 90);
        sparseIntArray.append(1, 0);
        sparseIntArray.append(2, SubsamplingScaleImageView.ORIENTATION_270);
        sparseIntArray.append(3, 180);
    }

    public C5426f() {
        new ReentrantLock();
        this.f12555G = false;
        this.f12556H = true;
        this.f12557I = new C5429c();
        this.f12558J = new C5430d();
        this.f12559K = new C5431e();
        this.f12560L = 0;
        this.f12561M = 0;
        this.f12562N = new C5432f();
        this.f12563O = 0L;
        this.f12564P = 0L;
        if (Build.VERSION.SDK_INT < 21) {
            throw new RuntimeException("can't use this class");
        }
    }

    /* renamed from: a */
    public static void m13550a(C5426f c5426f) {
        c5426f.getClass();
        try {
            CaptureRequest.Builder createCaptureRequest = c5426f.f12570p.createCaptureRequest(2);
            createCaptureRequest.addTarget(c5426f.f12579y.getSurface());
            createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 4);
            C5445g c5445g = new C5445g(c5426f);
            if (Build.VERSION.SDK_INT <= 24) {
                c5426f.f12552D.stopRepeating();
                c5426f.f12552D.abortCaptures();
            }
            c5426f.f12552D.capture(createCaptureRequest.build(), c5445g, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public int mo13274a(Context context) {
        String str;
        int[] iArr;
        Size[] outputSizes;
        Size[] outputSizes2;
        try {
            if (this.f12569o == null) {
                this.f12569o = (CameraManager) context.getSystemService("camera");
            }
            String[] cameraIdList = this.f12569o.getCameraIdList();
            int length = cameraIdList.length;
            for (int i = 0; i < length; i++) {
                str = cameraIdList[i];
                String str2 = "selectBackOrFrontCamera()...id = " + str;
                CameraCharacteristics cameraCharacteristics = this.f12569o.getCameraCharacteristics(str);
                StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (streamConfigurationMap != null) {
                    for (int i2 = 0; i2 < ((int[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)).length; i2++) {
                        String str3 = "selectBackOrFrontCamera: support awb modes = " + iArr[i2];
                    }
                    String str4 = "selectBackOrFrontCamera: support awb modes hardware support= " + ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
                    for (Size size : streamConfigurationMap.getOutputSizes(35)) {
                        String str5 = "preiview width * height = " + size.getWidth() + " * " + size.getHeight();
                    }
                    for (Size size2 : streamConfigurationMap.getOutputSizes(256)) {
                        String str6 = "picture width * height = " + size2.getWidth() + " * " + size2.getHeight();
                    }
                    Size size3 = (Size) Collections.min(Arrays.asList(streamConfigurationMap.getOutputSizes(256)), new C5427a());
                    this.f12870d = size3.getWidth();
                    this.f12871e = size3.getHeight();
                    String str7 = "mPictureWidth * mPictureHeight = " + this.f12870d + " * " + this.f12871e;
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    String str8 = "selectBackOrFrontCamera()...facing = " + num;
                    if (num != null) {
                        if ((this.f12572r && 1 == num.intValue()) || num.intValue() == 0) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            String str9 = "selectBackOrFrontCamera: " + e.toString();
        }
        str = null;
        String str10 = "Camera Id:" + str;
        if (str == null) {
            return -1;
        }
        return Integer.parseInt(str);
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13277a() {
        Handler handler = this.f12567m;
        if (handler != null) {
            handler.sendEmptyMessage(6);
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13276a(int i) {
        if (this.f12875i && this.f12874h.containsKey(Integer.valueOf(i))) {
            int intValue = this.f12874h.get(Integer.valueOf(i)).intValue();
            if (m13547a(this.f12552D) && m13547a(this.f12551C)) {
                int[] mo13265b = mo13265b();
                if (intValue > mo13265b[1]) {
                    intValue = mo13265b[1];
                } else if (intValue < mo13265b[2]) {
                    intValue = mo13265b[2];
                }
                mo13264b(intValue);
            }
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13273a(SurfaceTexture surfaceTexture) {
        this.f12560L = 0;
        this.f12578x = surfaceTexture;
        this.f12567m.sendEmptyMessage(2);
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13439a(AbstractC5500m.InterfaceC5501a interfaceC5501a) {
        this.f12580z = interfaceC5501a;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13271a(AbstractC5500m.InterfaceC5503c interfaceC5503c) {
        this.f12577w = interfaceC5503c;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13270a(AbstractC5500m.InterfaceC5504d interfaceC5504d) {
        if (System.currentTimeMillis() - this.f12564P < 3000) {
            return;
        }
        this.f12564P = System.currentTimeMillis();
        this.f12574t = interfaceC5504d;
        Handler handler = this.f12567m;
        if (handler != null) {
            Message.obtain(handler, 4).sendToTarget();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13269a(AbstractC5500m.InterfaceC5505e interfaceC5505e) {
        if (System.currentTimeMillis() - this.f12563O < 3000) {
            return;
        }
        this.f12563O = System.currentTimeMillis();
        this.f12575u = interfaceC5505e;
        Handler handler = this.f12567m;
        if (handler != null) {
            Message.obtain(handler, 3).sendToTarget();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13267a(String str) {
        int intValue = f12547Q.get(str).intValue();
        if (m13547a(this.f12552D) && m13547a(this.f12551C)) {
            this.f12551C.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(intValue));
            try {
                this.f12552D.setRepeatingRequest(this.f12551C.build(), null, this.f12567m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final boolean m13547a(Object obj) {
        return obj != null;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: b */
    public void mo13264b(int i) {
        this.f12551C.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(i));
        try {
            this.f12552D.setRepeatingRequest(this.f12551C.build(), null, this.f12567m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: b */
    public int[] mo13265b() {
        int[] iArr = new int[3];
        try {
            Range range = (Range) this.f12569o.getCameraCharacteristics(this.f12571q).get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
            iArr[0] = ((Integer) this.f12551C.get(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION)).intValue();
            iArr[1] = ((Integer) range.getUpper()).intValue();
            iArr[2] = ((Integer) range.getLower()).intValue();
            C5527o2.m13235b(this.f12565k, "exposure_high", Integer.valueOf(iArr[1]));
            C5527o2.m13235b(this.f12565k, "exposure_low", Integer.valueOf(iArr[2]));
            if (this.f12876j == -1000) {
                this.f12876j = iArr[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iArr;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: c */
    public void mo13262c() {
        Handler handler = this.f12567m;
        if (handler != null) {
            handler.sendEmptyMessage(5);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:9|(3:11|(1:13)|14)|46|(3:17|(1:19)|20)(3:41|(1:43)|44)|22|(7:24|(3:26|(1:28)|29)|31|32|33|34|35)|39|31|32|33|34|35) */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005e, code lost:
        if (r6 > 255.0f) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007c, code lost:
        if (r2 > 255.0f) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0099, code lost:
        if (r2 > 255.0f) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009b, code lost:
        r2 = 255.0f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c5, code lost:
        if (r4 > 255.0f) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0121, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0122, code lost:
        r0.printStackTrace();
     */
    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo13261c(int r11) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5426f.mo13261c(int):void");
    }

    /* renamed from: d */
    public final void m13545d() {
        if (m13547a(this.f12568n)) {
            this.f12568n.removeCallbacksAndMessages(null);
        }
        if (m13547a(this.f12567m)) {
            this.f12567m.removeCallbacksAndMessages(null);
        }
        if (m13547a(this.f12566l)) {
            this.f12566l.quit();
        }
        this.f12566l = null;
        this.f12567m = null;
        this.f12568n = null;
    }

    /* renamed from: d */
    public final void m13544d(int i) {
        if (m13547a(this.f12568n)) {
            this.f12568n.post(new RunnableC5433g(i));
        }
    }

    /* renamed from: e */
    public final void m13543e() {
        synchronized (this.f12553E) {
            this.f12577w = null;
            if (m13547a(this.f12552D) && m13547a(this.f12570p)) {
                try {
                    this.f12552D.stopRepeating();
                    this.f12552D.close();
                    this.f12552D = null;
                } catch (Throwable unused) {
                }
            }
            if (m13547a(this.f12576v)) {
                this.f12576v.close();
                this.f12576v = null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m13549a(com.megvii.lv5.C5426f r19, android.media.Image r20, byte[] r21) {
        /*
            Method dump skipped, instructions count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5426f.m13549a(com.megvii.lv5.f, android.media.Image, byte[]):int");
    }

    /* renamed from: a */
    public boolean m13546a(boolean z) {
        int i;
        int intValue;
        if (Build.VERSION.SDK_INT >= 21) {
            Context context = this.f12565k;
            C5479j c5479j = new C5479j(context);
            try {
                i = ((CameraManager) context.getSystemService("camera")).getCameraIdList().length;
            } catch (Throwable th) {
                th.printStackTrace();
                i = 0;
            }
            for (int i2 = 0; i2 < i; i2++) {
                CameraManager cameraManager = (CameraManager) c5479j.f12808a.getSystemService("camera");
                try {
                    intValue = ((Integer) cameraManager.getCameraCharacteristics(cameraManager.getCameraIdList()[i2]).get(CameraCharacteristics.LENS_FACING)).intValue();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                C5479j.EnumC5480a enumC5480a = intValue != 0 ? intValue != 1 ? intValue != 2 ? C5479j.EnumC5480a.FACING_UNKNOWN : C5479j.EnumC5480a.FACING_EXTERNAL : C5479j.EnumC5480a.FACING_BACK : C5479j.EnumC5480a.FACING_FRONT;
                if ((z && enumC5480a == C5479j.EnumC5480a.FACING_FRONT) || (!z && enumC5480a == C5479j.EnumC5480a.FACING_BACK)) {
                    return c5479j.m13450a(i2);
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13266a(boolean z, Context context, AbstractC5500m.InterfaceC5502b interfaceC5502b) {
        String str = "openCamera()...back camera:" + z;
        super.mo13266a(z, context, interfaceC5502b);
        HashMap hashMap = new HashMap();
        String str2 = (String) C5527o2.m13252a(context, "white_balance_info", "");
        if (!TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                JSONArray jSONArray = jSONObject.getJSONArray("frame_sequence");
                JSONArray jSONArray2 = jSONObject.getJSONArray("temperature_sequence");
                if (jSONArray.length() > 0 && jSONArray2.length() > 0 && jSONArray.length() == jSONArray2.length()) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        hashMap.put(Integer.valueOf(jSONArray.optInt(i)), Integer.valueOf(jSONArray2.optInt(i)));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.f12550B = hashMap;
        this.f12549A = context.getFilesDir();
        HandlerThread handlerThread = new HandlerThread("Camera2WrapperImpl");
        this.f12566l = handlerThread;
        handlerThread.start();
        this.f12567m = new Handler(this.f12566l.getLooper(), new C5458h(this));
        this.f12568n = new Handler(context.getMainLooper());
        this.f12565k = context;
        this.f12572r = z;
        this.f12573s = interfaceC5502b;
        Message.obtain(this.f12567m, 1).sendToTarget();
    }
}
