package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12895g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12841x {

    /* renamed from: a */
    static final int[] f26051a = {-6803, -2, -1, -1, -1, -1, -1};

    /* renamed from: b */
    static final int[] f26052b = {46280809, 13606, 1, 0, 0, 0, 0, -13606, -3, -1, -1, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f26053c = {-46280809, -13607, -2, -1, -1, -1, -1, 13605, 2};

    /* renamed from: a */
    public static void m971a(int i, int[] iArr) {
        if ((i == 0 || AbstractC12895g.m589a(6803, i, iArr, 0) == 0) && !(iArr[6] == -1 && AbstractC12895g.m576c(iArr, f26051a))) {
            return;
        }
        AbstractC12891c.m711a(7, 6803, iArr);
    }

    /* renamed from: a */
    public static void m969a(int[] iArr, int i, int[] iArr2) {
        int[] m581b = AbstractC12895g.m581b();
        AbstractC12895g.m573d(iArr, m581b);
        while (true) {
            m964c(m581b, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12895g.m573d(iArr2, m581b);
        }
    }

    /* renamed from: a */
    public static void m968a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(7, iArr, iArr2) != 0 || (iArr2[6] == -1 && AbstractC12895g.m576c(iArr2, f26051a))) {
            AbstractC12891c.m711a(7, 6803, iArr2);
        }
    }

    /* renamed from: a */
    public static void m967a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12895g.m582a(iArr, iArr2, iArr3) != 0 || (iArr3[6] == -1 && AbstractC12895g.m576c(iArr3, f26051a))) {
            AbstractC12891c.m711a(7, 6803, iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m970a(BigInteger bigInteger) {
        int[] m586a = AbstractC12895g.m586a(bigInteger);
        if (m586a[6] == -1 && AbstractC12895g.m576c(m586a, f26051a)) {
            AbstractC12891c.m711a(7, 6803, m586a);
        }
        return m586a;
    }

    /* renamed from: b */
    public static void m966b(int[] iArr, int[] iArr2) {
        if (AbstractC12895g.m580b(iArr)) {
            AbstractC12895g.m574d(iArr2);
        } else {
            AbstractC12895g.m570e(f26051a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m965b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m581b = AbstractC12895g.m581b();
        AbstractC12895g.m575c(iArr, iArr2, m581b);
        m964c(m581b, iArr3);
    }

    /* renamed from: c */
    public static void m964c(int[] iArr, int[] iArr2) {
        if (AbstractC12895g.m588a(6803, AbstractC12895g.m587a(6803, iArr, 7, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[6] == -1 && AbstractC12895g.m576c(iArr2, f26051a))) {
            AbstractC12891c.m711a(7, 6803, iArr2);
        }
    }

    /* renamed from: c */
    public static void m963c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12895g.m572d(iArr, iArr2, iArr3) != 0 || (iArr3[13] == -1 && AbstractC12891c.m679c(14, iArr3, f26052b))) {
            int[] iArr4 = f26053c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(14, iArr3, f26053c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m962d(int[] iArr, int[] iArr2) {
        int[] m581b = AbstractC12895g.m581b();
        AbstractC12895g.m573d(iArr, m581b);
        m964c(m581b, iArr2);
    }

    /* renamed from: d */
    public static void m961d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12895g.m570e(iArr, iArr2, iArr3) != 0) {
            AbstractC12891c.m683c(7, 6803, iArr3);
        }
    }

    /* renamed from: e */
    public static void m960e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(7, iArr, 0, iArr2) != 0 || (iArr2[6] == -1 && AbstractC12895g.m576c(iArr2, f26051a))) {
            AbstractC12891c.m711a(7, 6803, iArr2);
        }
    }
}
