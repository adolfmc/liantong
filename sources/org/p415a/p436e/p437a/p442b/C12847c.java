package org.p415a.p436e.p437a.p442b;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.C12872o;
import org.p415a.p436e.p437a.InterfaceC12849c;
import org.p415a.p436e.p437a.InterfaceC12865h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12847c implements InterfaceC12846b {

    /* renamed from: a */
    protected final AbstractC12850d f26057a;

    /* renamed from: b */
    protected final C12848d f26058b;

    /* renamed from: c */
    protected final InterfaceC12865h f26059c;

    public C12847c(AbstractC12850d abstractC12850d, C12848d c12848d) {
        this.f26057a = abstractC12850d;
        this.f26058b = c12848d;
        this.f26059c = new C12872o(abstractC12850d.mo906a(c12848d.m945a()));
    }

    /* renamed from: a */
    protected BigInteger m947a(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        boolean z = bigInteger2.signum() < 0;
        BigInteger multiply = bigInteger.multiply(bigInteger2.abs());
        boolean testBit = multiply.testBit(i - 1);
        BigInteger shiftRight = multiply.shiftRight(i);
        if (testBit) {
            shiftRight = shiftRight.add(InterfaceC12849c.f26070d);
        }
        return z ? shiftRight.negate() : shiftRight;
    }

    @Override // org.p415a.p436e.p437a.p442b.InterfaceC12845a
    /* renamed from: a */
    public InterfaceC12865h mo949a() {
        return this.f26059c;
    }

    @Override // org.p415a.p436e.p437a.p442b.InterfaceC12846b
    /* renamed from: a */
    public BigInteger[] mo948a(BigInteger bigInteger) {
        int m937h = this.f26058b.m937h();
        BigInteger m947a = m947a(bigInteger, this.f26058b.m939f(), m937h);
        BigInteger m947a2 = m947a(bigInteger, this.f26058b.m938g(), m937h);
        C12848d c12848d = this.f26058b;
        return new BigInteger[]{bigInteger.subtract(m947a.multiply(c12848d.m943b()).add(m947a2.multiply(c12848d.m941d()))), m947a.multiply(c12848d.m942c()).add(m947a2.multiply(c12848d.m940e())).negate()};
    }

    @Override // org.p415a.p436e.p437a.p442b.InterfaceC12845a
    /* renamed from: b */
    public boolean mo946b() {
        return true;
    }
}
