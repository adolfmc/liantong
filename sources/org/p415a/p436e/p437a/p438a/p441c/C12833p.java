package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12894f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.p */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12833p {

    /* renamed from: a */
    static final int[] f26037a = {-4553, -2, -1, -1, -1, -1};

    /* renamed from: b */
    static final int[] f26038b = {20729809, 9106, 1, 0, 0, 0, -9106, -3, -1, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f26039c = {-20729809, -9107, -2, -1, -1, -1, 9105, 2};

    /* renamed from: a */
    public static void m997a(int i, int[] iArr) {
        if ((i == 0 || AbstractC12894f.m623a(4553, i, iArr, 0) == 0) && !(iArr[5] == -1 && AbstractC12894f.m605b(iArr, f26037a))) {
            return;
        }
        AbstractC12891c.m711a(6, 4553, iArr);
    }

    /* renamed from: a */
    public static void m995a(int[] iArr, int i, int[] iArr2) {
        int[] m602c = AbstractC12894f.m602c();
        AbstractC12894f.m598c(iArr, m602c);
        while (true) {
            m990c(m602c, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12894f.m598c(iArr2, m602c);
        }
    }

    /* renamed from: a */
    public static void m994a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(6, iArr, iArr2) != 0 || (iArr2[5] == -1 && AbstractC12894f.m605b(iArr2, f26037a))) {
            AbstractC12891c.m711a(6, 4553, iArr2);
        }
    }

    /* renamed from: a */
    public static void m993a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12894f.m613a(iArr, iArr2, iArr3) != 0 || (iArr3[5] == -1 && AbstractC12894f.m605b(iArr3, f26037a))) {
            AbstractC12891c.m711a(6, 4553, iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m996a(BigInteger bigInteger) {
        int[] m620a = AbstractC12894f.m620a(bigInteger);
        if (m620a[5] == -1 && AbstractC12894f.m605b(m620a, f26037a)) {
            AbstractC12894f.m593d(f26037a, m620a);
        }
        return m620a;
    }

    /* renamed from: b */
    public static void m992b(int[] iArr, int[] iArr2) {
        if (AbstractC12894f.m608b(iArr)) {
            AbstractC12894f.m594d(iArr2);
        } else {
            AbstractC12894f.m591e(f26037a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m991b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m602c = AbstractC12894f.m602c();
        AbstractC12894f.m597c(iArr, iArr2, m602c);
        m990c(m602c, iArr3);
    }

    /* renamed from: c */
    public static void m990c(int[] iArr, int[] iArr2) {
        if (AbstractC12894f.m622a(4553, AbstractC12894f.m621a(4553, iArr, 6, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[5] == -1 && AbstractC12894f.m605b(iArr2, f26037a))) {
            AbstractC12891c.m711a(6, 4553, iArr2);
        }
    }

    /* renamed from: c */
    public static void m989c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12894f.m592d(iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && AbstractC12891c.m679c(12, iArr3, f26038b))) {
            int[] iArr4 = f26039c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(12, iArr3, f26039c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m988d(int[] iArr, int[] iArr2) {
        int[] m602c = AbstractC12894f.m602c();
        AbstractC12894f.m598c(iArr, m602c);
        m990c(m602c, iArr2);
    }

    /* renamed from: d */
    public static void m987d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12894f.m591e(iArr, iArr2, iArr3) != 0) {
            AbstractC12891c.m683c(6, 4553, iArr3);
        }
    }

    /* renamed from: e */
    public static void m986e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(6, iArr, 0, iArr2) != 0 || (iArr2[5] == -1 && AbstractC12894f.m605b(iArr2, f26037a))) {
            AbstractC12891c.m711a(6, 4553, iArr2);
        }
    }
}
