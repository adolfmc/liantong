package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p046q0;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.q0.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC0632a {
    /* renamed from: a */
    public static int m22691a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
    }

    /* renamed from: b */
    public static long m22680b(byte[] bArr, int i) {
        int m22691a = m22691a(bArr, i);
        return (m22691a(bArr, i + 4) & 4294967295L) | ((m22691a & 4294967295L) << 32);
    }

    /* renamed from: c */
    public static int m22673c(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    /* renamed from: d */
    public static long m22672d(byte[] bArr, int i) {
        return ((m22673c(bArr, i + 4) & 4294967295L) << 32) | (m22673c(bArr, i) & 4294967295L);
    }

    /* renamed from: b */
    public static void m22679b(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = m22673c(bArr, i);
            i += 4;
        }
    }

    /* renamed from: a */
    public static void m22690a(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = m22691a(bArr, i);
            i += 4;
        }
    }

    /* renamed from: b */
    public static byte[] m22684b(int i) {
        byte[] bArr = new byte[4];
        m22683b(i, bArr, 0);
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m22695a(int i) {
        byte[] bArr = new byte[4];
        m22694a(i, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static void m22683b(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    /* renamed from: a */
    public static void m22694a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    /* renamed from: b */
    public static byte[] m22677b(int[] iArr) {
        byte[] bArr = new byte[iArr.length * 4];
        m22676b(iArr, bArr, 0);
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m22688a(int[] iArr) {
        byte[] bArr = new byte[iArr.length * 4];
        m22687a(iArr, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static void m22676b(int[] iArr, byte[] bArr, int i) {
        for (int i2 : iArr) {
            m22683b(i2, bArr, i);
            i += 4;
        }
    }

    /* renamed from: a */
    public static void m22687a(int[] iArr, byte[] bArr, int i) {
        for (int i2 : iArr) {
            m22694a(i2, bArr, i);
            i += 4;
        }
    }

    /* renamed from: b */
    public static void m22678b(byte[] bArr, int i, long[] jArr) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = m22672d(bArr, i);
            i += 8;
        }
    }

    /* renamed from: a */
    public static void m22689a(byte[] bArr, int i, long[] jArr) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = m22680b(bArr, i);
            i += 8;
        }
    }

    /* renamed from: b */
    public static byte[] m22682b(long j) {
        byte[] bArr = new byte[8];
        m22681b(j, bArr, 0);
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m22693a(long j) {
        byte[] bArr = new byte[8];
        m22692a(j, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static void m22681b(long j, byte[] bArr, int i) {
        m22683b((int) (4294967295L & j), bArr, i);
        m22683b((int) (j >>> 32), bArr, i + 4);
    }

    /* renamed from: a */
    public static void m22692a(long j, byte[] bArr, int i) {
        m22694a((int) (j >>> 32), bArr, i);
        m22694a((int) (j & 4294967295L), bArr, i + 4);
    }

    /* renamed from: b */
    public static byte[] m22675b(long[] jArr) {
        byte[] bArr = new byte[jArr.length * 8];
        m22674b(jArr, bArr, 0);
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m22686a(long[] jArr) {
        byte[] bArr = new byte[jArr.length * 8];
        m22685a(jArr, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static void m22674b(long[] jArr, byte[] bArr, int i) {
        for (long j : jArr) {
            m22681b(j, bArr, i);
            i += 8;
        }
    }

    /* renamed from: a */
    public static void m22685a(long[] jArr, byte[] bArr, int i) {
        for (long j : jArr) {
            m22692a(j, bArr, i);
            i += 8;
        }
    }
}
