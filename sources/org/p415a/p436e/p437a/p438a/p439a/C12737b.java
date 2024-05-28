package org.p415a.p436e.p437a.p438a.p439a;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12896h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12737b {

    /* renamed from: a */
    static final int[] f25936a = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};

    /* renamed from: b */
    private static final int[] f25937b = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, 1073741823};

    /* renamed from: a */
    private static int m1299a(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 19;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = AbstractC12891c.m705a(7, iArr, 1);
        }
        long j3 = j2 + (4294967295L & iArr[7]) + 2147483648L;
        iArr[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    /* renamed from: a */
    public static void m1301a(int i, int[] iArr) {
        int i2 = iArr[7];
        iArr[7] = (i2 & Integer.MAX_VALUE) + AbstractC12891c.m690b(7, ((i << 1) | (i2 >>> 31)) * 19, iArr);
        if (AbstractC12896h.m542c(iArr, f25936a)) {
            m1295b(iArr);
        }
    }

    /* renamed from: a */
    public static void m1298a(int[] iArr, int i, int[] iArr2) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(iArr, m546c);
        while (true) {
            m1291c(m546c, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12896h.m537d(iArr2, m546c);
        }
    }

    /* renamed from: a */
    public static void m1297a(int[] iArr, int[] iArr2) {
        AbstractC12891c.m674d(8, iArr, iArr2);
        if (AbstractC12896h.m542c(iArr2, f25936a)) {
            m1295b(iArr2);
        }
    }

    /* renamed from: a */
    public static void m1296a(int[] iArr, int[] iArr2, int[] iArr3) {
        AbstractC12896h.m557a(iArr, iArr2, iArr3);
        if (AbstractC12896h.m542c(iArr3, f25936a)) {
            m1295b(iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1300a(BigInteger bigInteger) {
        int[] m564a = AbstractC12896h.m564a(bigInteger);
        while (AbstractC12896h.m542c(m564a, f25936a)) {
            AbstractC12896h.m535e(f25936a, m564a);
        }
        return m564a;
    }

    /* renamed from: b */
    private static int m1295b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 19;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = AbstractC12891c.m688b(7, iArr, 1);
        }
        long j3 = j2 + ((4294967295L & iArr[7]) - 2147483648L);
        iArr[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    /* renamed from: b */
    public static void m1294b(int[] iArr, int[] iArr2) {
        if (AbstractC12896h.m552b(iArr)) {
            AbstractC12896h.m538d(iArr2);
        } else {
            AbstractC12896h.m534e(f25936a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1293b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m541c(iArr, iArr2, m546c);
        m1291c(m546c, iArr3);
    }

    /* renamed from: c */
    private static int m1292c(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - (f25937b[0] & 4294967295L);
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = AbstractC12891c.m705a(8, iArr, 1);
        }
        long j3 = j2 + (iArr[8] & 4294967295L) + 19;
        iArr[8] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            j4 = AbstractC12891c.m688b(15, iArr, 9);
        }
        long j5 = j4 + ((iArr[15] & 4294967295L) - (4294967295L & (f25937b[15] + 1)));
        iArr[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    /* renamed from: c */
    public static void m1291c(int[] iArr, int[] iArr2) {
        int i = iArr[7];
        AbstractC12891c.m701a(8, iArr, 8, i, iArr2, 0);
        int i2 = iArr2[7];
        iArr2[7] = (i2 & Integer.MAX_VALUE) + AbstractC12891c.m690b(7, ((AbstractC12896h.m565a(19, iArr, iArr2) << 1) + ((i2 >>> 31) - (i >>> 31))) * 19, iArr2);
        if (AbstractC12896h.m542c(iArr2, f25936a)) {
            m1295b(iArr2);
        }
    }

    /* renamed from: c */
    public static void m1290c(int[] iArr, int[] iArr2, int[] iArr3) {
        AbstractC12896h.m536d(iArr, iArr2, iArr3);
        if (AbstractC12891c.m679c(16, iArr3, f25937b)) {
            m1292c(iArr3);
        }
    }

    /* renamed from: d */
    public static void m1289d(int[] iArr, int[] iArr2) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(iArr, m546c);
        m1291c(m546c, iArr2);
    }

    /* renamed from: d */
    public static void m1288d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m534e(iArr, iArr2, iArr3) != 0) {
            m1299a(iArr3);
        }
    }

    /* renamed from: e */
    public static void m1287e(int[] iArr, int[] iArr2) {
        AbstractC12891c.m700a(8, iArr, 0, iArr2);
        if (AbstractC12896h.m542c(iArr2, f25936a)) {
            m1295b(iArr2);
        }
    }
}
