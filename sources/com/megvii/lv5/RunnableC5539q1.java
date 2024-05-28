package com.megvii.lv5;

import android.hardware.Camera;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.q1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5539q1 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ byte[] f13188a;

    /* renamed from: b */
    public final /* synthetic */ int f13189b;

    /* renamed from: c */
    public final /* synthetic */ int f13190c;

    /* renamed from: d */
    public final /* synthetic */ Camera f13191d;

    /* renamed from: e */
    public final /* synthetic */ C5553s1 f13192e;

    public RunnableC5539q1(C5553s1 c5553s1, byte[] bArr, int i, int i2, Camera camera) {
        this.f13192e = c5553s1;
        this.f13188a = bArr;
        this.f13189b = i;
        this.f13190c = i2;
        this.f13191d = camera;
    }

    @Override // java.lang.Runnable
    public void run() {
        C5553s1 c5553s1 = this.f13192e;
        byte[] bArr = this.f13188a;
        int i = this.f13189b;
        int i2 = this.f13190c;
        Camera camera = this.f13191d;
        C5666z1 c5666z1 = c5553s1.f13263n;
        int i3 = c5553s1.f13258i;
        int i4 = c5553s1.f13257h;
        c5666z1.f13965r = i3;
        c5666z1.f13966s = i4;
        c5553s1.f13266q.onPreviewFrame(bArr, camera);
        c5553s1.f13263n.m12880a(bArr, c5553s1.f13264o, i, i2, c5553s1.f13251b.m13444b());
    }
}
