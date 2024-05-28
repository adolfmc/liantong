package com.baidu.feedcv.soundchanger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SoundTouch implements AudioProcessor {

    /* renamed from: a */
    private int f4946a;

    /* renamed from: b */
    private int f4947b;

    /* renamed from: c */
    private int f4948c;

    /* renamed from: d */
    private float f4949d;

    /* renamed from: e */
    private float f4950e;

    /* renamed from: f */
    private float f4951f = 1.0f;

    /* renamed from: g */
    private int f4952g;

    static {
        System.loadLibrary("soundtouch");
    }

    public SoundTouch(int i, int i2, int i3, int i4, float f, float f2) {
        this.f4946a = i2;
        this.f4947b = i3;
        this.f4948c = i4;
        this.f4949d = f;
        this.f4950e = f2;
        this.f4952g = i;
        setup(i, i2, i3, i4, f, f2);
    }

    private static native synchronized void clearBytes(int i);

    private static native synchronized void finish(int i, int i2);

    private static native synchronized int getBytes(int i, byte[] bArr, int i2);

    private static native synchronized long getOutputBufferSize(int i);

    private static native synchronized void putBytes(int i, byte[] bArr, int i2);

    private static native synchronized void setPitchSemi(int i, float f);

    private static native synchronized void setRate(int i, float f);

    private static native synchronized void setRateChange(int i, float f);

    private static native synchronized void setSpeech(int i, boolean z);

    private static native synchronized void setTempo(int i, float f);

    private static native synchronized void setTempoChange(int i, float f);

    private static native synchronized void setup(int i, int i2, int i3, int i4, float f, float f2);

    /* renamed from: a */
    public void m19717a(int i) {
        this.f4948c = i;
    }

    /* renamed from: b */
    public void m19713b(int i) {
        this.f4946a = i;
    }

    /* renamed from: c */
    public int m19711c() {
        return this.f4946a;
    }

    /* renamed from: d */
    public void m19706d(int i) {
        switch (i) {
            case 1:
                m19718a(10.0f);
                m19707d(1.0f);
                m19714b(1.0f);
                return;
            case 2:
                m19718a(-5.0f);
                m19707d(1.0f);
                m19714b(1.0f);
                return;
            case 3:
                m19718a(7.0f);
                m19707d(1.0f);
                m19714b(1.0f);
                return;
            case 4:
                m19718a(-3.0f);
                m19707d(1.0f);
                m19714b(1.0f);
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    public float m19705e() {
        return this.f4950e;
    }

    /* renamed from: f */
    public float m19703f() {
        return this.f4951f;
    }

    /* renamed from: g */
    public int m19702g() {
        return this.f4947b;
    }

    /* renamed from: h */
    public float m19701h() {
        return this.f4949d;
    }

    /* renamed from: i */
    public int m19700i() {
        return this.f4952g;
    }

    /* renamed from: a */
    public void m19718a(float f) {
        this.f4950e = f;
        setPitchSemi(this.f4952g, f);
    }

    /* renamed from: b */
    public void m19714b(float f) {
        this.f4951f = f;
        setRate(this.f4952g, f);
    }

    /* renamed from: c */
    public void m19709c(int i) {
        this.f4947b = i;
    }

    /* renamed from: e */
    public void m19704e(float f) {
        if (f >= -50.0f && f <= 100.0f) {
            this.f4949d = (0.01f * f) + 1.0f;
            setTempoChange(this.f4952g, f);
            return;
        }
        throw new SoundStreamRuntimeException("Tempo percentage must be between -50 and 100");
    }

    /* renamed from: c */
    public void m19710c(float f) {
        this.f4951f = f;
        setRateChange(this.f4952g, f);
    }

    /* renamed from: a */
    public void m19719a() {
        clearBytes(this.f4952g);
    }

    /* renamed from: b */
    public void m19712b(byte[] bArr) {
        putBytes(this.f4952g, bArr, bArr.length);
    }

    /* renamed from: a */
    public int m19716a(byte[] bArr) {
        return getBytes(this.f4952g, bArr, bArr.length);
    }

    /* renamed from: b */
    public void m19715b() {
        finish(this.f4952g, 2048);
    }

    /* renamed from: d */
    public long m19708d() {
        return getOutputBufferSize(this.f4952g);
    }

    /* renamed from: d */
    public void m19707d(float f) {
        this.f4949d = f;
        setTempo(this.f4952g, f);
    }
}
