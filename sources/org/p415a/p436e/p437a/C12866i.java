package org.p415a.p436e.p437a;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12866i extends AbstractC12735a {
    /* renamed from: a */
    protected int m828a(int i) {
        return i > 257 ? 6 : 5;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12735a
    /* renamed from: b */
    protected AbstractC12860g mo732b(AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        AbstractC12850d m861c = abstractC12860g.m861c();
        int m821a = C12868k.m821a(m861c);
        if (bigInteger.bitLength() <= m821a) {
            C12867j m820a = C12868k.m820a(abstractC12860g, m828a(m821a));
            AbstractC12860g[] m823b = m820a.m823b();
            int m822c = m820a.m822c();
            int i = ((m821a + m822c) - 1) / m822c;
            int i2 = (m822c * i) - 1;
            AbstractC12860g mo901e = m861c.mo901e();
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = 0;
                for (int i5 = i2 - i3; i5 >= 0; i5 -= i) {
                    i4 <<= 1;
                    if (bigInteger.testBit(i5)) {
                        i4 |= 1;
                    }
                }
                mo901e = mo901e.mo836d(m823b[i4]);
            }
            return mo901e.mo839b(m820a.m827a());
        }
        throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
    }
}
