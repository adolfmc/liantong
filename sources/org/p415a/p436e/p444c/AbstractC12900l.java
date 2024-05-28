package org.p415a.p436e.p444c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12900l {
    /* renamed from: a */
    public static void m517a(int[] iArr, int[] iArr2) {
        AbstractC12896h.m537d(iArr, iArr2);
        AbstractC12896h.m544c(iArr, 8, iArr2, 16);
        int m561a = AbstractC12896h.m561a(iArr2, 8, iArr2, 16);
        int m560a = m561a + AbstractC12896h.m560a(iArr2, 24, iArr2, 16, AbstractC12896h.m560a(iArr2, 0, iArr2, 8, 0) + m561a);
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12896h.m559a(iArr, 8, iArr, 0, m569a, 0);
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(m569a, m546c);
        AbstractC12891c.m710a(32, m560a + AbstractC12891c.m686b(16, m546c, 0, iArr2, 8), iArr2, 24);
    }

    /* renamed from: a */
    public static void m516a(int[] iArr, int[] iArr2, int[] iArr3) {
        AbstractC12896h.m541c(iArr, iArr2, iArr3);
        AbstractC12896h.m550b(iArr, 8, iArr2, 8, iArr3, 16);
        int m561a = AbstractC12896h.m561a(iArr3, 8, iArr3, 16);
        int m560a = m561a + AbstractC12896h.m560a(iArr3, 24, iArr3, 16, AbstractC12896h.m560a(iArr3, 0, iArr3, 8, 0) + m561a);
        int[] m569a = AbstractC12896h.m569a();
        int[] m569a2 = AbstractC12896h.m569a();
        boolean z = AbstractC12896h.m559a(iArr, 8, iArr, 0, m569a, 0) != AbstractC12896h.m559a(iArr2, 8, iArr2, 0, m569a2, 0);
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m541c(m569a, m569a2, m546c);
        AbstractC12891c.m710a(32, m560a + (z ? AbstractC12891c.m699a(16, m546c, 0, iArr3, 8) : AbstractC12891c.m686b(16, m546c, 0, iArr3, 8)), iArr3, 24);
    }
}
