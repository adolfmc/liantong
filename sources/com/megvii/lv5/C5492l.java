package com.megvii.lv5;

import android.app.Activity;
import android.hardware.Camera;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.megvii.lv5.AbstractC5500m;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5492l {

    /* renamed from: a */
    public AbstractC5500m f12846a;

    /* renamed from: b */
    public int f12847b;

    /* renamed from: c */
    public int f12848c;

    /* renamed from: d */
    public int f12849d;

    /* renamed from: e */
    public int f12850e;

    /* renamed from: f */
    public int f12851f;

    /* renamed from: g */
    public int f12852g;

    /* renamed from: h */
    public WeakReference<Activity> f12853h;

    /* renamed from: i */
    public AbstractC5500m.InterfaceC5502b f12854i;

    public C5492l(Activity activity) {
        this.f12847b = 1;
        this.f12848c = 1920;
        this.f12849d = 1080;
        this.f12850e = 1280;
        this.f12851f = 720;
        this.f12852g = SubsamplingScaleImageView.ORIENTATION_270;
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        this.f12853h = weakReference;
        int[] m13378c = C5527o2.m13223h(weakReference.get().getApplication()).m13378c();
        int[] m13371d = C5527o2.m13223h(this.f12853h.get().getApplication()).m13371d();
        String str = "CameraManager: picture size=" + m13378c[0] + ",  " + m13378c[1];
        String str2 = "CameraManager: video size=" + m13371d[0] + ",  " + m13371d[1];
        C5486k.f12833c = m13378c[0];
        C5486k.f12834d = m13378c[1];
        this.f12850e = C5486k.f12833c;
        this.f12851f = C5486k.f12834d;
        C5486k.f12831a = m13371d[0];
        C5486k.f12832b = m13371d[1];
        this.f12848c = C5486k.f12831a;
        this.f12849d = C5486k.f12832b;
        AbstractC5500m m13446a = C5486k.m13446a();
        this.f12846a = m13446a;
        int mo13274a = m13446a.mo13274a(this.f12853h.get().getApplication());
        this.f12847b = mo13274a;
        if (mo13274a == -1) {
            this.f12847b = 0;
        }
        int m13445a = m13445a();
        this.f12852g = m13445a;
        C5486k.f12835e = m13445a;
    }

    /* renamed from: a */
    public int m13445a() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.f12847b, cameraInfo);
        int i = 90;
        if (this.f12853h.get() == null) {
            return 90;
        }
        int rotation = this.f12853h.get().getWindowManager().getDefaultDisplay().getRotation();
        String str = "rotation = " + rotation;
        switch (rotation) {
            case 0:
            default:
                i = 0;
                break;
            case 1:
                break;
            case 2:
                i = 180;
                break;
            case 3:
                i = SubsamplingScaleImageView.ORIENTATION_270;
                break;
        }
        String str2 = "xie getAngle: origin onPreviewFrame" + i + "orient" + cameraInfo.orientation;
        int i2 = (cameraInfo.facing == 1 ? 360 - ((cameraInfo.orientation + i) % 360) : (cameraInfo.orientation - i) + 360) % 360;
        String str3 = "xie getAngle: process" + i2;
        return i2;
    }

    /* renamed from: b */
    public boolean m13444b() {
        return this.f12847b == 1;
    }

    /* renamed from: c */
    public void m13443c() {
        this.f12846a.mo13267a("auto");
    }
}
