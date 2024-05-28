package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12893e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12829l {

    /* renamed from: a */
    static final int[] f26030a = {-21389, -2, -1, -1, -1};

    /* renamed from: b */
    static final int[] f26031b = {457489321, 42778, 1, 0, 0, -42778, -3, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f26032c = {-457489321, -42779, -2, -1, -1, 42777, 2};

    /* renamed from: a */
    public static void m1009a(int i, int[] iArr) {
        if ((i == 0 || AbstractC12893e.m644a(21389, i, iArr, 0) == 0) && !(iArr[4] == -1 && AbstractC12893e.m633b(iArr, f26030a))) {
            return;
        }
        AbstractC12891c.m711a(5, 21389, iArr);
    }

    /* renamed from: a */
    public static void m1007a(int[] iArr, int i, int[] iArr2) {
        int[] m636b = AbstractC12893e.m636b();
        AbstractC12893e.m630c(iArr, m636b);
        while (true) {
            m1002c(m636b, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12893e.m630c(iArr2, m636b);
        }
    }

    /* renamed from: a */
    public static void m1006a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(5, iArr, iArr2) != 0 || (iArr2[4] == -1 && AbstractC12893e.m633b(iArr2, f26030a))) {
            AbstractC12891c.m711a(5, 21389, iArr2);
        }
    }

    /* renamed from: a */
    public static void m1005a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12893e.m637a(iArr, iArr2, iArr3) != 0 || (iArr3[4] == -1 && AbstractC12893e.m633b(iArr3, f26030a))) {
            AbstractC12891c.m711a(5, 21389, iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1008a(BigInteger bigInteger) {
        int[] m641a = AbstractC12893e.m641a(bigInteger);
        if (m641a[4] == -1 && AbstractC12893e.m633b(m641a, f26030a)) {
            AbstractC12893e.m627d(f26030a, m641a);
        }
        return m641a;
    }

    /* renamed from: b */
    public static void m1004b(int[] iArr, int[] iArr2) {
        if (AbstractC12893e.m634b(iArr)) {
            AbstractC12893e.m628d(iArr2);
        } else {
            AbstractC12893e.m625e(f26030a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1003b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m636b = AbstractC12893e.m636b();
        AbstractC12893e.m629c(iArr, iArr2, m636b);
        m1002c(m636b, iArr3);
    }

    /* renamed from: c */
    public static void m1002c(int[] iArr, int[] iArr2) {
        if (AbstractC12893e.m643a(21389, AbstractC12893e.m642a(21389, iArr, 5, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[4] == -1 && AbstractC12893e.m633b(iArr2, f26030a))) {
            AbstractC12891c.m711a(5, 21389, iArr2);
        }
    }

    /* renamed from: c */
    public static void m1001c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12893e.m626d(iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && AbstractC12891c.m679c(10, iArr3, f26031b))) {
            int[] iArr4 = f26032c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(10, iArr3, f26032c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m1000d(int[] iArr, int[] iArr2) {
        int[] m636b = AbstractC12893e.m636b();
        AbstractC12893e.m630c(iArr, m636b);
        m1002c(m636b, iArr2);
    }

    /* renamed from: d */
    public static void m999d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12893e.m625e(iArr, iArr2, iArr3) != 0) {
            AbstractC12891c.m683c(5, 21389, iArr3);
        }
    }

    /* renamed from: e */
    public static void m998e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(5, iArr, 0, iArr2) != 0 || (iArr2[4] == -1 && AbstractC12893e.m633b(iArr2, f26030a))) {
            AbstractC12891c.m711a(5, 21389, iArr2);
        }
    }
}
