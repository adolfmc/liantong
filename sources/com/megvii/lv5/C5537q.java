package com.megvii.lv5;

import com.megvii.lv5.C5383b;
import com.megvii.lv5.lib.jni.MegBlur;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.q */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5537q {

    /* renamed from: g */
    public static final C5537q f13180g = new C5537q();

    /* renamed from: b */
    public int f13182b;

    /* renamed from: c */
    public int f13183c;

    /* renamed from: e */
    public int f13185e;

    /* renamed from: a */
    public float f13181a = 1.18f;

    /* renamed from: d */
    public int f13184d = 0;

    /* renamed from: f */
    public boolean f13186f = false;

    /* renamed from: a */
    public void m13208a() {
        m13198d(0);
        m13197e(this.f13182b);
        m13200c(this.f13182b);
        C5383b c5383b = C5383b.C5384a.f12381a;
        c5383b.m13610a(1.0f, 0, 0, 0);
        c5383b.m13609a(3, 1.0f);
        m13202b(this.f13185e);
    }

    /* renamed from: a */
    public void m13206a(int i) {
        int[] m13195a = C5538q0.m13195a(i);
        m13204a(m13195a[0], m13195a[1], m13195a[2]);
    }

    /* renamed from: b */
    public void m13203b() {
        m13198d(this.f13183c);
        m13197e(this.f13182b);
        m13200c(this.f13182b);
        C5383b.C5384a.f12381a.m13609a(2, 1.0f);
    }

    /* renamed from: b */
    public void m13202b(int i) {
        int[] m13195a = C5538q0.m13195a(i);
        m13204a(m13195a[0], m13195a[1], m13195a[2]);
    }

    /* renamed from: c */
    public void m13201c() {
        C5383b c5383b = C5383b.C5384a.f12381a;
        c5383b.m13610a(1.0f, 0, 0, 0);
        c5383b.m13609a(3, 1.0f);
        m13202b(this.f13185e);
    }

    /* renamed from: d */
    public void m13199d() {
        C5383b.C5384a.f12381a.m13609a(2, 1.0f);
    }

    /* renamed from: e */
    public final void m13197e(int i) {
        C5383b c5383b = C5383b.C5384a.f12381a;
        long j = c5383b.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetTextureMaskBlur(j, i, 275, 303);
        }
        C5530p c5530p = C5530p.f13115J;
        int i2 = (int) c5530p.f13129e;
        int i3 = (int) (c5530p.f13130f * 0.95f);
        float f = ((c5530p.f13145u * 1.0f) / 275.0f) * this.f13181a;
        long j2 = c5383b.f12380a.f12359a;
        if (j2 != 0) {
            MegBlur.nativeSetTransformMaskBlur(j2, i2, i3, f);
        }
    }

    /* renamed from: f */
    public void m13196f(int i) {
        this.f13184d = i;
        this.f13181a = i == 0 ? 0.95f : 1.18f;
    }

    /* renamed from: a */
    public void m13207a(float f) {
        long j = C5383b.C5384a.f12381a.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetBlendStrength(j, f);
        }
    }

    /* renamed from: c */
    public final void m13200c(int i) {
        C5383b c5383b = C5383b.C5384a.f12381a;
        long j = c5383b.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetTextureMaskColor(j, i, 275, 303);
        }
        C5530p c5530p = C5530p.f13115J;
        int i2 = (int) c5530p.f13129e;
        int i3 = (int) (c5530p.f13130f * 0.95f);
        float f = ((c5530p.f13145u * 1.0f) / 275.0f) * this.f13181a;
        long j2 = c5383b.f12380a.f12359a;
        if (j2 != 0) {
            MegBlur.nativeSetTransformMaskColor(j2, i2, i3, f);
        }
    }

    /* renamed from: d */
    public final void m13198d(int i) {
        C5383b c5383b = C5383b.C5384a.f12381a;
        long j = c5383b.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetTextureContour(j, i, 275, 303);
        }
        C5530p c5530p = C5530p.f13115J;
        int i2 = (int) c5530p.f13129e;
        int i3 = (int) (c5530p.f13130f * 0.95f);
        float f = ((c5530p.f13145u * 1.0f) / 275.0f) * this.f13181a;
        long j2 = c5383b.f12380a.f12359a;
        if (j2 != 0) {
            MegBlur.nativeSetTransformContour(j2, i2, i3, f);
        }
    }

    /* renamed from: a */
    public void m13205a(int i, float f) {
        int[] m13195a = C5538q0.m13195a(i);
        C5383b c5383b = C5383b.C5384a.f12381a;
        int i2 = m13195a[0];
        int i3 = m13195a[1];
        int i4 = m13195a[2];
        long j = c5383b.f12380a.f12359a;
        if (j != 0) {
            MegBlur.nativeSetColorContour(j, i2, i3, i4, f);
        }
    }

    /* renamed from: a */
    public void m13204a(int i, int i2, int i3) {
        int i4 = this.f13184d;
        if (i4 != 1 && i4 != 0) {
            int[] m13195a = C5538q0.m13195a(this.f13185e);
            C5383b.C5384a.f12381a.m13610a(0.0f, m13195a[0], m13195a[1], m13195a[2]);
            return;
        }
        C5383b.C5384a.f12381a.m13610a(0.7f, i, i2, i3);
    }
}
