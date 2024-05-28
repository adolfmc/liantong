package org.p415a.p448g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12971e {
    /* renamed from: a */
    public static int m398a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
    }

    /* renamed from: a */
    public static void m400a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    /* renamed from: a */
    public static void m399a(long j, byte[] bArr, int i) {
        m400a((int) (j >>> 32), bArr, i);
        m400a((int) (j & 4294967295L), bArr, i + 4);
    }

    /* renamed from: b */
    public static long m397b(byte[] bArr, int i) {
        int m398a = m398a(bArr, i);
        return (m398a(bArr, i + 4) & 4294967295L) | ((m398a & 4294967295L) << 32);
    }

    /* renamed from: c */
    public static int m396c(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }
}
