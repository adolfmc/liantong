package com.networkbench.agent.impl.p262i;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.i.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6465b {

    /* renamed from: a */
    private byte f16336a;

    /* renamed from: b */
    private byte f16337b;

    /* renamed from: c */
    private byte f16338c;

    /* renamed from: d */
    private int f16339d;

    /* renamed from: e */
    private int f16340e;

    /* renamed from: f */
    private int f16341f;

    public C6465b(byte b, byte b2, byte b3, int i, int i2, int i3) {
        this.f16336a = b;
        this.f16337b = b2;
        this.f16338c = b3;
        this.f16339d = i;
        this.f16340e = i2;
        this.f16341f = i3;
    }

    /* renamed from: a */
    public byte[] m9926a() {
        return m9925a((this.f16337b << 62) | (this.f16336a << 59) | (this.f16338c << 56) | (this.f16341f << 38) | (this.f16340e << 27) | this.f16339d);
    }

    /* renamed from: a */
    public static C6465b m9924a(byte[] bArr) {
        long m9923b = m9923b(bArr);
        return new C6465b((byte) ((4035225266123964416L & m9923b) >>> 59), (byte) (((-4611686018427387904L) & m9923b) >>> 62), (byte) ((504403158265495552L & m9923b) >>> 56), (int) (m9923b & 134217727), (int) ((274743689216L & m9923b) >>> 27), (int) ((17317308137472L & m9923b) >>> 38));
    }

    /* renamed from: a */
    private static byte[] m9925a(long j) {
        return new byte[]{(byte) (((-72057594037927936L) & j) >> 56), (byte) ((71776119061217280L & j) >> 48), (byte) ((280375465082880L & j) >> 40), (byte) ((1095216660480L & j) >> 32), (byte) ((4278190080L & j) >> 24), (byte) ((16711680 & j) >> 16), (byte) ((65280 & j) >> 8), (byte) (j & 255)};
    }

    /* renamed from: b */
    public static long m9923b(byte[] bArr) {
        return ((bArr[0] & 255) << 56) | ((bArr[1] & 255) << 48) | ((bArr[2] & 255) << 40) | ((bArr[3] & 255) << 32) | ((bArr[4] & 255) << 24) | ((bArr[5] & 255) << 16) | ((bArr[6] & 255) << 8) | (255 & bArr[7]);
    }
}
