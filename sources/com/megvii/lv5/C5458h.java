package com.megvii.lv5;

import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Handler;
import android.os.Message;
import android.support.p083v4.app.ActivityCompat;
import android.view.Surface;
import java.util.ArrayList;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5458h implements Handler.Callback {

    /* renamed from: a */
    public final /* synthetic */ C5426f f12694a;

    public C5458h(C5426f c5426f) {
        this.f12694a = c5426f;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        String str = "handleMessage()..." + message;
        switch (message.what) {
            case 1:
                C5426f c5426f = this.f12694a;
                CameraManager cameraManager = (CameraManager) c5426f.f12565k.getSystemService("camera");
                c5426f.f12569o = cameraManager;
                if (c5426f.m13547a(cameraManager)) {
                    String str2 = c5426f.mo13274a(c5426f.f12565k) + "";
                    c5426f.f12571q = str2;
                    if (c5426f.m13547a((Object) str2)) {
                        try {
                            if (ActivityCompat.checkSelfPermission(c5426f.f12565k, "android.permission.CAMERA") == 0) {
                                c5426f.f12569o.openCamera(c5426f.f12571q, c5426f.f12559K, c5426f.f12567m);
                                break;
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                c5426f.m13544d(3);
                break;
            case 2:
                C5426f c5426f2 = this.f12694a;
                c5426f2.getClass();
                String str3 = "initImageReader()...width * height = " + c5426f2.f12867a + " * " + c5426f2.f12868b;
                ImageReader newInstance = ImageReader.newInstance(c5426f2.f12867a, c5426f2.f12868b, 35, 1);
                c5426f2.f12576v = newInstance;
                newInstance.setOnImageAvailableListener(c5426f2.f12557I, c5426f2.f12567m);
                String str4 = "initImageReader()...pitcurewidth * pitcureheight = " + c5426f2.f12870d + " * " + c5426f2.f12871e;
                ImageReader newInstance2 = ImageReader.newInstance(c5426f2.f12870d, c5426f2.f12871e, 256, 1);
                c5426f2.f12579y = newInstance2;
                newInstance2.setOnImageAvailableListener(c5426f2.f12558J, c5426f2.f12567m);
                if (c5426f2.m13547a(c5426f2.f12570p)) {
                    c5426f2.f12578x.setDefaultBufferSize(C5486k.f12831a, C5486k.f12832b);
                    Surface surface = new Surface(c5426f2.f12578x);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(c5426f2.f12576v.getSurface());
                    arrayList.add(c5426f2.f12579y.getSurface());
                    arrayList.add(surface);
                    try {
                        CaptureRequest.Builder createCaptureRequest = c5426f2.f12570p.createCaptureRequest(1);
                        c5426f2.f12551C = createCaptureRequest;
                        createCaptureRequest.addTarget(c5426f2.f12576v.getSurface());
                        c5426f2.f12551C.addTarget(surface);
                        c5426f2.f12570p.createCaptureSession(arrayList, new C5468i(c5426f2), c5426f2.f12567m);
                        break;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        break;
                    }
                }
                break;
            case 3:
                C5426f c5426f3 = this.f12694a;
                c5426f3.getClass();
                try {
                    c5426f3.f12551C.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                    c5426f3.f12560L = 1;
                    c5426f3.f12552D.capture(c5426f3.f12551C.build(), c5426f3.f12562N, c5426f3.f12567m);
                    break;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    break;
                }
            case 4:
                C5426f c5426f4 = this.f12694a;
                c5426f4.getClass();
                try {
                    c5426f4.f12551C.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                    c5426f4.f12560L = 1;
                    c5426f4.f12552D.capture(c5426f4.f12551C.build(), c5426f4.f12562N, c5426f4.f12567m);
                    break;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    break;
                }
            case 5:
                this.f12694a.m13543e();
                break;
            case 6:
                C5426f c5426f5 = this.f12694a;
                c5426f5.f12560L = 5;
                if (!c5426f5.f12555G) {
                    c5426f5.f12555G = true;
                    long currentTimeMillis = System.currentTimeMillis();
                    while (!c5426f5.f12556H && System.currentTimeMillis() - currentTimeMillis < 50) {
                        try {
                            Thread.sleep(1L);
                        } catch (InterruptedException e5) {
                            e5.printStackTrace();
                        }
                    }
                    c5426f5.m13543e();
                    synchronized (c5426f5.f12553E) {
                        if (c5426f5.m13547a(c5426f5.f12570p)) {
                            c5426f5.f12570p.close();
                            c5426f5.f12570p = null;
                        }
                        if (c5426f5.m13547a(c5426f5.f12579y)) {
                            c5426f5.f12579y.close();
                            c5426f5.f12579y = null;
                        }
                        c5426f5.f12571q = null;
                        c5426f5.f12578x = null;
                        c5426f5.f12573s = null;
                        c5426f5.f12577w = null;
                        c5426f5.f12572r = false;
                        c5426f5.m13545d();
                    }
                    break;
                }
                break;
        }
        return false;
    }
}
