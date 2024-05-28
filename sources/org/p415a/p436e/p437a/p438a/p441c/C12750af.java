package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12896h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.af */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12750af {

    /* renamed from: a */
    static final int[] f25958a = {-977, -2, -1, -1, -1, -1, -1, -1};

    /* renamed from: b */
    static final int[] f25959b = {954529, 1954, 1, 0, 0, 0, 0, 0, -1954, -3, -1, -1, -1, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f25960c = {-954529, -1955, -2, -1, -1, -1, -1, -1, 1953, 2};

    /* renamed from: a */
    public static void m1250a(int i, int[] iArr) {
        if ((i == 0 || AbstractC12896h.m568a(977, i, iArr, 0) == 0) && !(iArr[7] == -1 && AbstractC12896h.m542c(iArr, f25958a))) {
            return;
        }
        AbstractC12891c.m711a(8, 977, iArr);
    }

    /* renamed from: a */
    public static void m1248a(int[] iArr, int i, int[] iArr2) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(iArr, m546c);
        while (true) {
            m1243c(m546c, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12896h.m537d(iArr2, m546c);
        }
    }

    /* renamed from: a */
    public static void m1247a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(8, iArr, iArr2) != 0 || (iArr2[7] == -1 && AbstractC12896h.m542c(iArr2, f25958a))) {
            AbstractC12891c.m711a(8, 977, iArr2);
        }
    }

    /* renamed from: a */
    public static void m1246a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m557a(iArr, iArr2, iArr3) != 0 || (iArr3[7] == -1 && AbstractC12896h.m542c(iArr3, f25958a))) {
            AbstractC12891c.m711a(8, 977, iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1249a(BigInteger bigInteger) {
        int[] m564a = AbstractC12896h.m564a(bigInteger);
        if (m564a[7] == -1 && AbstractC12896h.m542c(m564a, f25958a)) {
            AbstractC12896h.m535e(f25958a, m564a);
        }
        return m564a;
    }

    /* renamed from: b */
    public static void m1245b(int[] iArr, int[] iArr2) {
        if (AbstractC12896h.m552b(iArr)) {
            AbstractC12896h.m538d(iArr2);
        } else {
            AbstractC12896h.m534e(f25958a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1244b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m541c(iArr, iArr2, m546c);
        m1243c(m546c, iArr3);
    }

    /* renamed from: c */
    public static void m1243c(int[] iArr, int[] iArr2) {
        if (AbstractC12896h.m567a(977, AbstractC12896h.m566a(977, iArr, 8, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[7] == -1 && AbstractC12896h.m542c(iArr2, f25958a))) {
            AbstractC12891c.m711a(8, 977, iArr2);
        }
    }

    /* renamed from: c */
    public static void m1242c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m536d(iArr, iArr2, iArr3) != 0 || (iArr3[15] == -1 && AbstractC12891c.m679c(16, iArr3, f25959b))) {
            int[] iArr4 = f25960c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(16, iArr3, f25960c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m1241d(int[] iArr, int[] iArr2) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(iArr, m546c);
        m1243c(m546c, iArr2);
    }

    /* renamed from: d */
    public static void m1240d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m534e(iArr, iArr2, iArr3) != 0) {
            AbstractC12891c.m683c(8, 977, iArr3);
        }
    }

    /* renamed from: e */
    public static void m1239e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(8, iArr, 0, iArr2) != 0 || (iArr2[7] == -1 && AbstractC12896h.m542c(iArr2, f25958a))) {
            AbstractC12891c.m711a(8, 977, iArr2);
        }
    }
}
