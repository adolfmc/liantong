package com.megvii.lv5;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.megvii.lv5.AbstractC5500m;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.o */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5521o extends AbstractC5500m implements Camera.PictureCallback, Camera.PreviewCallback {

    /* renamed from: A */
    public long f13068A;

    /* renamed from: k */
    public HandlerThread f13069k;

    /* renamed from: l */
    public Handler f13070l;

    /* renamed from: m */
    public Handler f13071m;

    /* renamed from: n */
    public Camera f13072n;

    /* renamed from: p */
    public boolean f13074p;

    /* renamed from: q */
    public AbstractC5500m.InterfaceC5502b f13075q;

    /* renamed from: r */
    public AbstractC5500m.InterfaceC5505e f13076r;

    /* renamed from: s */
    public AbstractC5500m.InterfaceC5504d f13077s;

    /* renamed from: t */
    public SurfaceTexture f13078t;

    /* renamed from: u */
    public AbstractC5500m.InterfaceC5503c f13079u;

    /* renamed from: v */
    public byte[] f13080v;

    /* renamed from: x */
    public List<String> f13082x;

    /* renamed from: y */
    public File f13083y;

    /* renamed from: z */
    public long f13084z;

    /* renamed from: o */
    public int f13073o = -1;

    /* renamed from: w */
    public Lock f13081w = new ReentrantLock();

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.o$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5522a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ int f13085a;

        /* renamed from: b */
        public final /* synthetic */ ArrayList f13086b;

        public RunnableC5522a(int i, ArrayList arrayList) {
            this.f13085a = i;
            this.f13086b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            C5521o c5521o = C5521o.this;
            if (c5521o.m13268a(c5521o.f13075q)) {
                int i = this.f13085a;
                if (i == 1) {
                    C5521o.this.f13075q.mo12923c();
                    C5521o.this.f13075q.mo12930a(this.f13086b);
                } else if (i == 2) {
                    C5521o.this.f13075q.mo12939a();
                } else if (i != 3) {
                } else {
                    C5521o.this.f13075q.mo12927b();
                }
            }
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.o$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5523b implements Comparator<Camera.Size> {

        /* renamed from: a */
        public final /* synthetic */ int f13088a;

        /* renamed from: b */
        public final /* synthetic */ int f13089b;

        public C5523b(C5521o c5521o, int i, int i2) {
            this.f13088a = i;
            this.f13089b = i2;
        }

        @Override // java.util.Comparator
        public int compare(Camera.Size size, Camera.Size size2) {
            Camera.Size size3 = size;
            Camera.Size size4 = size2;
            return Math.abs((size3.width * size3.height) - (this.f13088a * this.f13089b)) - Math.abs((size4.width * size4.height) - (this.f13088a * this.f13089b));
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.o$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5524c implements Comparator<Camera.Size> {

        /* renamed from: a */
        public final /* synthetic */ int f13090a;

        /* renamed from: b */
        public final /* synthetic */ int f13091b;

        public C5524c(C5521o c5521o, int i, int i2) {
            this.f13090a = i;
            this.f13091b = i2;
        }

        @Override // java.util.Comparator
        public int compare(Camera.Size size, Camera.Size size2) {
            Camera.Size size3 = size;
            Camera.Size size4 = size2;
            return Math.abs((size3.width * size3.height) - (this.f13090a * this.f13091b)) - Math.abs((size4.width * size4.height) - (this.f13090a * this.f13091b));
        }
    }

    public C5521o() {
        ArrayList arrayList = new ArrayList();
        this.f13082x = arrayList;
        this.f13084z = 0L;
        this.f13068A = 0L;
        arrayList.add("cloudy-daylight");
        this.f13082x.add("incandescent");
        Collections.shuffle(this.f13082x);
        this.f13082x.add("auto");
        this.f13082x.add("auto");
        this.f13082x.add("auto");
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public int mo13274a(Context context) {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i = 0;
        while (i < numberOfCameras) {
            Camera.getCameraInfo(i, cameraInfo);
            if ((this.f13074p && cameraInfo.facing == 0) || cameraInfo.facing == 1) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* renamed from: a */
    public final ArrayList<Camera.Size> m13272a(Camera.Parameters parameters, int i, int i2) {
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList<Camera.Size> arrayList = new ArrayList<>();
        for (Camera.Size size : supportedPictureSizes) {
            String str = "tmpSize.width===" + size.width + ", tmpSize.height===" + size.height;
            if (size.width > size.height) {
                arrayList.add(size);
            }
        }
        Collections.sort(arrayList, new C5524c(this, i, i2));
        return arrayList;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13277a() {
        if (m13268a(this.f13072n)) {
            Camera.Parameters parameters = this.f13072n.getParameters();
            parameters.setWhiteBalance("auto");
            this.f13072n.setParameters(parameters);
            this.f13079u = null;
            this.f13072n.stopPreview();
            this.f13072n.setPreviewCallback(null);
            this.f13072n.release();
            this.f13072n = null;
        }
        this.f13073o = -1;
        this.f13078t = null;
        this.f13075q = null;
        this.f13079u = null;
        this.f13074p = false;
        if (m13268a(this.f13071m)) {
            this.f13071m.removeCallbacksAndMessages(null);
        }
        if (m13268a(this.f13070l)) {
            this.f13070l.removeCallbacksAndMessages(null);
        }
        if (m13268a(this.f13069k)) {
            this.f13069k.quit();
        }
        this.f13069k = null;
        this.f13070l = null;
        this.f13071m = null;
    }

    /* renamed from: a */
    public final void m13275a(int i, ArrayList<Camera.Size> arrayList) {
        if (m13268a(this.f13071m)) {
            this.f13071m.post(new RunnableC5522a(i, arrayList));
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13273a(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null) {
            this.f13078t = surfaceTexture;
        }
        Handler handler = this.f13070l;
        if (handler != null) {
            Message.obtain(handler, 2).sendToTarget();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13271a(AbstractC5500m.InterfaceC5503c interfaceC5503c) {
        this.f13079u = interfaceC5503c;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13270a(AbstractC5500m.InterfaceC5504d interfaceC5504d) {
        if (System.currentTimeMillis() - this.f13068A < 3000) {
            return;
        }
        this.f13068A = System.currentTimeMillis();
        this.f13077s = interfaceC5504d;
        Handler handler = this.f13070l;
        if (handler != null) {
            Message.obtain(handler, 4).sendToTarget();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13269a(AbstractC5500m.InterfaceC5505e interfaceC5505e) {
        if (System.currentTimeMillis() - this.f13084z < 3000) {
            return;
        }
        this.f13084z = System.currentTimeMillis();
        this.f13076r = interfaceC5505e;
        Handler handler = this.f13070l;
        if (handler != null) {
            Message.obtain(handler, 3).sendToTarget();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13267a(String str) {
        if (m13268a(this.f13072n)) {
            Camera.Parameters parameters = this.f13072n.getParameters();
            if (parameters.getSupportedWhiteBalance().contains(str)) {
                parameters.setWhiteBalance(str);
                this.f13072n.setParameters(parameters);
            }
        }
    }

    /* renamed from: a */
    public final boolean m13268a(Object obj) {
        return obj != null;
    }

    /* renamed from: b */
    public final ArrayList<Camera.Size> m13263b(Camera.Parameters parameters, int i, int i2) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        ArrayList<Camera.Size> arrayList = new ArrayList<>();
        for (Camera.Size size : supportedPreviewSizes) {
            String str = "tmpSize.width===" + size.width + ", tmpSize.height===" + size.height;
            if (size.width > size.height) {
                arrayList.add(size);
            }
        }
        Collections.sort(arrayList, new C5523b(this, i, i2));
        String str2 = "best matched size:" + arrayList.get(0).width + ":" + arrayList.get(0).height;
        return arrayList;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: b */
    public void mo13264b(int i) {
        if (m13268a(this.f13072n)) {
            Camera.Parameters parameters = this.f13072n.getParameters();
            parameters.setExposureCompensation(i);
            this.f13072n.setParameters(parameters);
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: b */
    public int[] mo13265b() {
        int[] iArr = new int[3];
        if (m13268a(this.f13072n)) {
            Camera.Parameters parameters = this.f13072n.getParameters();
            iArr[0] = parameters.getExposureCompensation();
            iArr[1] = parameters.getMaxExposureCompensation();
            iArr[2] = parameters.getMinExposureCompensation();
            if (this.f12876j == -1000) {
                this.f12876j = iArr[0];
            }
        }
        return iArr;
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: c */
    public void mo13262c() {
        if (m13268a(this.f13072n)) {
            this.f13072n.stopPreview();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: c */
    public void mo13261c(int i) {
        if (this.f12873g && this.f12872f.containsKey(Integer.valueOf(i))) {
            mo13267a(this.f12872f.get(Integer.valueOf(i)));
            String str = "setWhiteBalance: success  ---" + i;
        }
    }

    /* renamed from: d */
    public final ArrayList<Camera.Size> m13260d() {
        try {
            if (m13268a(this.f13072n)) {
                Camera.Parameters parameters = this.f13072n.getParameters();
                ArrayList<Camera.Size> m13263b = m13263b(parameters, this.f12867a, this.f12868b);
                Camera.Size size = m13263b.get(0);
                int i = size.width;
                this.f12867a = i;
                int i2 = size.height;
                this.f12868b = i2;
                parameters.setPreviewSize(i, i2);
                parameters.setPreviewFormat(17);
                Camera.Size size2 = m13272a(parameters, this.f12870d, this.f12871e).get(0);
                int i3 = size2.width;
                this.f12870d = i3;
                int i4 = size2.height;
                this.f12871e = i4;
                parameters.setPictureSize(i3, i4);
                parameters.setPictureFormat(256);
                if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                    parameters.setFocusMode("continuous-video");
                }
                String str = "Angle==" + this.f12869c + "|" + this.f12867a + "|" + this.f12868b;
                this.f13072n.setParameters(parameters);
                return m13263b;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.hardware.Camera.PictureCallback
    public void onPictureTaken(byte[] bArr, Camera camera) {
        double d;
        try {
            SurfaceTexture surfaceTexture = this.f13078t;
            if (surfaceTexture != null) {
                this.f13078t = surfaceTexture;
            }
            Handler handler = this.f13070l;
            if (handler != null) {
                Message.obtain(handler, 2).sendToTarget();
            }
            File file = null;
            if (m13268a((Object) this.f13077s)) {
                this.f13077s.mo12929a(bArr);
                this.f13077s = null;
            }
            if (this.f13076r == null) {
                return;
            }
            if (this.f13083y != null) {
                File file2 = new File(this.f13083y, "flashImage");
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
            String attribute = exifInterface.getAttribute("FNumber");
            String attribute2 = exifInterface.getAttribute("ExposureTime");
            String attribute3 = exifInterface.getAttribute("ISOSpeedRatings");
            if (attribute != null && attribute2 != null && attribute3 != null) {
                double doubleValue = Double.valueOf(attribute).doubleValue();
                double doubleValue2 = Double.valueOf(attribute2).doubleValue();
                double doubleValue3 = Double.valueOf(attribute3.split(",")[0]).doubleValue() * 1.0d;
                try {
                    d = (Math.log(Math.pow(doubleValue, 2.0d) / doubleValue2) / Math.log(2.0d)) - (Math.log(doubleValue3 / 100.0d) / Math.log(2.0d));
                } catch (Exception e) {
                    e.printStackTrace();
                    d = -101.0d;
                }
                this.f13076r.mo13438a(d);
                file.delete();
            }
            this.f13076r.mo13438a(-102.0d);
            file.delete();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.f13081w.lock();
        byte[] bArr2 = this.f13080v;
        if (bArr2 == null || bArr2.length != bArr.length) {
            this.f13080v = new byte[bArr.length];
        }
        System.arraycopy(bArr, 0, this.f13080v, 0, bArr.length);
        camera.addCallbackBuffer(bArr);
        if (m13268a((Object) this.f13079u)) {
            this.f13079u.onPreviewFrame(this.f13080v, camera);
        }
        this.f13081w.unlock();
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13266a(boolean z, Context context, AbstractC5500m.InterfaceC5502b interfaceC5502b) {
        String str = "openCamera()...back camera:" + z;
        super.mo13266a(z, context, interfaceC5502b);
        this.f13083y = context.getFilesDir();
        HandlerThread handlerThread = new HandlerThread("CameraWrapperImpl");
        this.f13069k = handlerThread;
        handlerThread.start();
        this.f13070l = new Handler(this.f13069k.getLooper(), new C5514n(this));
        this.f13071m = new Handler(context.getMainLooper());
        this.f13074p = z;
        this.f13075q = interfaceC5502b;
        Handler handler = this.f13070l;
        if (handler != null) {
            Message.obtain(handler, 1).sendToTarget();
        }
    }

    @Override // com.megvii.lv5.AbstractC5500m
    /* renamed from: a */
    public void mo13276a(int i) {
        if (this.f12875i && this.f12874h.containsKey(Integer.valueOf(i))) {
            int intValue = this.f12874h.get(Integer.valueOf(i)).intValue();
            if (m13268a(this.f13072n)) {
                int[] mo13265b = mo13265b();
                if (intValue > mo13265b[1] || intValue < mo13265b[2] || !m13268a(this.f13072n)) {
                    return;
                }
                Camera.Parameters parameters = this.f13072n.getParameters();
                parameters.setExposureCompensation(intValue);
                this.f13072n.setParameters(parameters);
            }
        }
    }
}
