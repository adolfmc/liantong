package org.p415a.p436e.p437a;

import java.math.BigInteger;
import org.p415a.p436e.p437a.p442b.InterfaceC12846b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12869l extends AbstractC12735a {

    /* renamed from: a */
    protected final AbstractC12850d f26113a;

    /* renamed from: b */
    protected final InterfaceC12846b f26114b;

    public C12869l(AbstractC12850d abstractC12850d, InterfaceC12846b interfaceC12846b) {
        if (abstractC12850d == null || abstractC12850d.m921i() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.f26113a = abstractC12850d;
        this.f26114b = interfaceC12846b;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12735a
    /* renamed from: b */
    protected AbstractC12860g mo732b(AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        if (this.f26113a.m934a(abstractC12860g.m861c())) {
            BigInteger[] mo948a = this.f26114b.mo948a(bigInteger.mod(abstractC12860g.m861c().m921i()));
            BigInteger bigInteger2 = mo948a[0];
            BigInteger bigInteger3 = mo948a[1];
            InterfaceC12865h a = this.f26114b.mo949a();
            return this.f26114b.mo946b() ? C12844b.m955a(abstractC12860g, bigInteger2, a, bigInteger3) : C12844b.m956a(abstractC12860g, bigInteger2, a.mo778a(abstractC12860g), bigInteger3);
        }
        throw new IllegalStateException();
    }
}
