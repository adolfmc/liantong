package com.megvii.lv5;

import android.hardware.Camera;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.t1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5614t1 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ byte[] f13732a;

    /* renamed from: b */
    public final /* synthetic */ int f13733b;

    /* renamed from: c */
    public final /* synthetic */ int f13734c;

    /* renamed from: d */
    public final /* synthetic */ Camera f13735d;

    /* renamed from: e */
    public final /* synthetic */ C5627v1 f13736e;

    public RunnableC5614t1(C5627v1 c5627v1, byte[] bArr, int i, int i2, Camera camera) {
        this.f13736e = c5627v1;
        this.f13732a = bArr;
        this.f13733b = i;
        this.f13734c = i2;
        this.f13735d = camera;
    }

    @Override // java.lang.Runnable
    public void run() {
        C5627v1 c5627v1 = this.f13736e;
        byte[] bArr = this.f13732a;
        int i = this.f13733b;
        int i2 = this.f13734c;
        Camera camera = this.f13735d;
        C5666z1 c5666z1 = c5627v1.f13764i;
        c5666z1.f13965r = 480;
        c5666z1.f13966s = 640;
        c5627v1.f13766k.onPreviewFrame(bArr, camera);
        if (C5627v1.f13755n) {
            c5627v1.f13768m++;
        }
        if (c5627v1.f13768m >= 2) {
            return;
        }
        c5627v1.f13764i.m12880a(bArr, c5627v1.f13765j, i, i2, c5627v1.f13757b.m13444b());
    }
}
