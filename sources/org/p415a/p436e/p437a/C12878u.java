package org.p415a.p436e.p437a;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12860g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.u */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12878u extends AbstractC12735a {
    /* renamed from: a */
    private AbstractC12860g.AbstractC12861a m734a(AbstractC12860g.AbstractC12861a abstractC12861a, C12880w c12880w, InterfaceC12871n interfaceC12871n, byte b, byte b2) {
        C12880w[] c12880wArr = b == 0 ? C12874q.f26125a : C12874q.f26127c;
        return m733a(abstractC12861a, C12874q.m764a(b2, c12880w, (byte) 4, BigInteger.valueOf(16L), C12874q.m767a(b2, 4), c12880wArr), interfaceC12871n);
    }

    /* renamed from: a */
    private static AbstractC12860g.AbstractC12861a m733a(AbstractC12860g.AbstractC12861a abstractC12861a, byte[] bArr, InterfaceC12871n interfaceC12871n) {
        AbstractC12860g.AbstractC12861a[] m758a;
        AbstractC12850d.AbstractC12851a abstractC12851a = (AbstractC12850d.AbstractC12851a) abstractC12861a.m861c();
        byte byteValue = abstractC12851a.m923g().mo893a().byteValue();
        if (interfaceC12871n == null || !(interfaceC12871n instanceof C12879v)) {
            m758a = C12874q.m758a(abstractC12861a, byteValue);
            C12879v c12879v = new C12879v();
            c12879v.m730a(m758a);
            abstractC12851a.m932a(abstractC12861a, "bc_wtnaf", c12879v);
        } else {
            m758a = ((C12879v) interfaceC12871n).m731a();
        }
        AbstractC12860g.AbstractC12861a[] abstractC12861aArr = new AbstractC12860g.AbstractC12861a[m758a.length];
        for (int i = 0; i < m758a.length; i++) {
            abstractC12861aArr[i] = (AbstractC12860g.AbstractC12861a) m758a[i].mo832q();
        }
        AbstractC12860g.AbstractC12861a abstractC12861a2 = (AbstractC12860g.AbstractC12861a) abstractC12861a.m861c().mo901e();
        int i2 = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i2++;
            byte b = bArr[length];
            if (b != 0) {
                abstractC12861a2 = (AbstractC12860g.AbstractC12861a) abstractC12861a2.m848c(i2).mo839b(b > 0 ? m758a[b >>> 1] : abstractC12861aArr[(-b) >>> 1]);
                i2 = 0;
            }
        }
        return i2 > 0 ? abstractC12861a2.m848c(i2) : abstractC12861a2;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12735a
    /* renamed from: b */
    protected AbstractC12860g mo732b(AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        if (abstractC12860g instanceof AbstractC12860g.AbstractC12861a) {
            AbstractC12860g.AbstractC12861a abstractC12861a = (AbstractC12860g.AbstractC12861a) abstractC12860g;
            AbstractC12850d.AbstractC12851a abstractC12851a = (AbstractC12850d.AbstractC12851a) abstractC12861a.m861c();
            int a = abstractC12851a.mo908a();
            byte byteValue = abstractC12851a.m923g().mo893a().byteValue();
            byte m763a = C12874q.m763a(byteValue);
            return m734a(abstractC12861a, C12874q.m761a(bigInteger, a, byteValue, abstractC12851a.m914m(), m763a, (byte) 10), abstractC12851a.m933a(abstractC12861a, "bc_wtnaf"), byteValue, m763a);
        }
        throw new IllegalArgumentException("Only ECPoint.AbstractF2m can be used in WTauNafMultiplier");
    }
}
