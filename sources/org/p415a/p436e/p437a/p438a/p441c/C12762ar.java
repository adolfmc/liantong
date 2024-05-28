package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12900l;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ar */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12762ar {

    /* renamed from: a */
    static final int[] f25978a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 511};

    /* renamed from: a */
    public static void m1209a(int[] iArr) {
        int i = iArr[16];
        int m690b = AbstractC12891c.m690b(16, i >>> 9, iArr) + (i & 511);
        if (m690b > 511 || (m690b == 511 && AbstractC12891c.m685b(16, iArr, f25978a))) {
            m690b = (m690b + AbstractC12891c.m682c(16, iArr)) & 511;
        }
        iArr[16] = m690b;
    }

    /* renamed from: a */
    public static void m1208a(int[] iArr, int i, int[] iArr2) {
        int[] m712a = AbstractC12891c.m712a(33);
        m1198f(iArr, m712a);
        while (true) {
            m1203c(m712a, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1198f(iArr2, m712a);
        }
    }

    /* renamed from: a */
    public static void m1207a(int[] iArr, int[] iArr2) {
        int m674d = AbstractC12891c.m674d(16, iArr, iArr2) + iArr[16];
        if (m674d > 511 || (m674d == 511 && AbstractC12891c.m685b(16, iArr2, f25978a))) {
            m674d = (m674d + AbstractC12891c.m682c(16, iArr2)) & 511;
        }
        iArr2[16] = m674d;
    }

    /* renamed from: a */
    public static void m1206a(int[] iArr, int[] iArr2, int[] iArr3) {
        int m697a = AbstractC12891c.m697a(16, iArr, iArr2, iArr3) + iArr[16] + iArr2[16];
        if (m697a > 511 || (m697a == 511 && AbstractC12891c.m685b(16, iArr3, f25978a))) {
            m697a = (m697a + AbstractC12891c.m682c(16, iArr3)) & 511;
        }
        iArr3[16] = m697a;
    }

    /* renamed from: a */
    public static int[] m1210a(BigInteger bigInteger) {
        int[] m707a = AbstractC12891c.m707a(521, bigInteger);
        if (AbstractC12891c.m685b(17, m707a, f25978a)) {
            AbstractC12891c.m670g(17, m707a);
        }
        return m707a;
    }

    /* renamed from: b */
    public static void m1205b(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m673e(17, iArr)) {
            AbstractC12891c.m670g(17, iArr2);
        } else {
            AbstractC12891c.m678c(17, f25978a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1204b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m712a = AbstractC12891c.m712a(33);
        m1200d(iArr, iArr2, m712a);
        m1203c(m712a, iArr3);
    }

    /* renamed from: c */
    public static void m1203c(int[] iArr, int[] iArr2) {
        int i = iArr[32];
        int m703a = (AbstractC12891c.m703a(16, iArr, 16, 9, i, iArr2, 0) >>> 23) + (i >>> 9) + AbstractC12891c.m698a(16, iArr, iArr2);
        if (m703a > 511 || (m703a == 511 && AbstractC12891c.m685b(16, iArr2, f25978a))) {
            m703a = (m703a + AbstractC12891c.m682c(16, iArr2)) & 511;
        }
        iArr2[16] = m703a;
    }

    /* renamed from: c */
    public static void m1202c(int[] iArr, int[] iArr2, int[] iArr3) {
        int m678c = (AbstractC12891c.m678c(16, iArr, iArr2, iArr3) + iArr[16]) - iArr2[16];
        if (m678c < 0) {
            m678c = (m678c + AbstractC12891c.m689b(16, iArr3)) & 511;
        }
        iArr3[16] = m678c;
    }

    /* renamed from: d */
    public static void m1201d(int[] iArr, int[] iArr2) {
        int[] m712a = AbstractC12891c.m712a(33);
        m1198f(iArr, m712a);
        m1203c(m712a, iArr2);
    }

    /* renamed from: d */
    protected static void m1200d(int[] iArr, int[] iArr2, int[] iArr3) {
        AbstractC12900l.m516a(iArr, iArr2, iArr3);
        int i = iArr[16];
        int i2 = iArr2[16];
        iArr3[32] = AbstractC12891c.m708a(16, i, iArr2, i2, iArr, iArr3, 16) + (i * i2);
    }

    /* renamed from: e */
    public static void m1199e(int[] iArr, int[] iArr2) {
        int i = iArr[16];
        iArr2[16] = (AbstractC12891c.m700a(16, iArr, i << 23, iArr2) | (i << 1)) & 511;
    }

    /* renamed from: f */
    protected static void m1198f(int[] iArr, int[] iArr2) {
        AbstractC12900l.m517a(iArr, iArr2);
        int i = iArr[16];
        iArr2[32] = AbstractC12891c.m709a(16, i << 1, iArr, 0, iArr2, 16) + (i * i);
    }
}
