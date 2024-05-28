package org.p415a.p436e.p437a;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12875r extends AbstractC12735a {
    /* renamed from: a */
    protected int m755a(int i) {
        return AbstractC12877t.m748a(i);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12735a
    /* renamed from: b */
    protected AbstractC12860g mo732b(AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        AbstractC12860g abstractC12860g2;
        int max = Math.max(2, Math.min(16, m755a(bigInteger.bitLength())));
        C12876s m743a = AbstractC12877t.m743a(abstractC12860g, max, true);
        AbstractC12860g[] m754a = m743a.m754a();
        AbstractC12860g[] m751b = m743a.m751b();
        int[] m747a = AbstractC12877t.m747a(max, bigInteger);
        AbstractC12860g mo901e = abstractC12860g.m861c().mo901e();
        int length = m747a.length;
        if (length > 1) {
            length--;
            int i = m747a[length];
            int i2 = i >> 16;
            int i3 = i & 65535;
            int abs = Math.abs(i2);
            AbstractC12860g[] abstractC12860gArr = i2 < 0 ? m751b : m754a;
            if ((abs << 2) < (1 << max)) {
                byte b = C12870m.f26115a[abs];
                int i4 = max - b;
                abstractC12860g2 = abstractC12860gArr[((1 << (max - 1)) - 1) >>> 1].mo839b(abstractC12860gArr[(((abs ^ (1 << (b - 1))) << i4) + 1) >>> 1]);
                i3 -= i4;
            } else {
                abstractC12860g2 = abstractC12860gArr[abs >>> 1];
            }
            mo901e = abstractC12860g2.mo841b(i3);
        }
        while (length > 0) {
            length--;
            int i5 = m747a[length];
            int i6 = i5 >> 16;
            mo901e = mo901e.mo836d((i6 < 0 ? m751b : m754a)[Math.abs(i6) >>> 1]).mo841b(i5 & 65535);
        }
        return mo901e;
    }
}
