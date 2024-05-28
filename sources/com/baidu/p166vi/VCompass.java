package com.baidu.p166vi;

import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.VCompass */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VCompass {

    /* renamed from: e */
    private static final Handler f8189e = new HandlerC3329c();

    /* renamed from: b */
    private float f8191b;

    /* renamed from: a */
    private SensorManager f8190a = null;

    /* renamed from: c */
    private float f8192c = 2.0f;

    /* renamed from: d */
    private int f8193d = 0;

    /* renamed from: f */
    private SensorEventListener f8194f = new C3330d(this);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public float m17407a(float f) {
        this.f8191b = m17406a(this.f8191b, f, this.f8192c);
        return this.f8191b;
    }

    /* renamed from: a */
    private float m17406a(float f, float f2, float f3) {
        float f4 = f - f2;
        return (f4 > 180.0f || f4 < -180.0f) ? f2 : (f4 < (-f3) || f3 < f4) ? (f + f2) / 2.0f : f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void updateCompass(int i);
}
