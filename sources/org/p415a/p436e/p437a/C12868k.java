package org.p415a.p436e.p437a;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12868k {
    /* renamed from: a */
    public static int m821a(AbstractC12850d abstractC12850d) {
        BigInteger m921i = abstractC12850d.m921i();
        return m921i == null ? abstractC12850d.mo908a() + 1 : m921i.bitLength();
    }

    /* renamed from: a */
    public static C12867j m820a(AbstractC12860g abstractC12860g, int i) {
        AbstractC12850d m861c = abstractC12860g.m861c();
        int i2 = 1 << i;
        C12867j m819a = m819a(m861c.m933a(abstractC12860g, "bc_fixed_point"));
        AbstractC12860g[] m823b = m819a.m823b();
        if (m823b == null || m823b.length < i2) {
            int m821a = ((m821a(m861c) + i) - 1) / i;
            AbstractC12860g[] abstractC12860gArr = new AbstractC12860g[i + 1];
            abstractC12860gArr[0] = abstractC12860g;
            for (int i3 = 1; i3 < i; i3++) {
                abstractC12860gArr[i3] = abstractC12860gArr[i3 - 1].mo841b(m821a);
            }
            abstractC12860gArr[i] = abstractC12860gArr[0].mo845c(abstractC12860gArr[1]);
            m861c.m930a(abstractC12860gArr);
            AbstractC12860g[] abstractC12860gArr2 = new AbstractC12860g[i2];
            abstractC12860gArr2[0] = abstractC12860gArr[0];
            for (int i4 = i - 1; i4 >= 0; i4--) {
                AbstractC12860g abstractC12860g2 = abstractC12860gArr[i4];
                int i5 = 1 << i4;
                for (int i6 = i5; i6 < i2; i6 += i5 << 1) {
                    abstractC12860gArr2[i6] = abstractC12860gArr2[i6 - i5].mo839b(abstractC12860g2);
                }
            }
            m861c.m930a(abstractC12860gArr2);
            m819a.m825a(abstractC12860gArr[i]);
            m819a.m824a(abstractC12860gArr2);
            m819a.m826a(i);
            m861c.m932a(abstractC12860g, "bc_fixed_point", m819a);
        }
        return m819a;
    }

    /* renamed from: a */
    public static C12867j m819a(InterfaceC12871n interfaceC12871n) {
        return (interfaceC12871n == null || !(interfaceC12871n instanceof C12867j)) ? new C12867j() : (C12867j) interfaceC12871n;
    }
}
