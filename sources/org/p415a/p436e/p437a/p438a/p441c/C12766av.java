package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12892d;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.av */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12766av extends AbstractC12856e {

    /* renamed from: a */
    protected long[] f25981a;

    public C12766av() {
        this.f25981a = AbstractC12892d.m661b();
    }

    public C12766av(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 113) {
            throw new IllegalArgumentException("x value invalid for SecT113FieldElement");
        }
        this.f25981a = C12765au.m1196a(bigInteger);
    }

    protected C12766av(long[] jArr) {
        this.f25981a = jArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12892d.m651c(this.f25981a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        if (i < 1) {
            return this;
        }
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1194a(this.f25981a, i, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1192a(this.f25981a, ((C12766av) abstractC12856e).f25981a, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        long[] jArr = this.f25981a;
        long[] jArr2 = ((C12766av) abstractC12856e).f25981a;
        long[] jArr3 = ((C12766av) abstractC12856e2).f25981a;
        long[] m650d = AbstractC12892d.m650d();
        C12765au.m1183f(jArr, m650d);
        C12765au.m1186d(jArr2, jArr3, m650d);
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1189c(m650d, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return 113;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        return mo889a(abstractC12856e);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        long[] jArr = this.f25981a;
        long[] jArr2 = ((C12766av) abstractC12856e).f25981a;
        long[] jArr3 = ((C12766av) abstractC12856e2).f25981a;
        long[] jArr4 = ((C12766av) abstractC12856e3).f25981a;
        long[] m650d = AbstractC12892d.m650d();
        C12765au.m1186d(jArr, jArr2, m650d);
        C12765au.m1186d(jArr3, jArr4, m650d);
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1189c(m650d, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1193a(this.f25981a, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1188c(this.f25981a, ((C12766av) abstractC12856e).f25981a, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        return this;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        return mo878c(abstractC12856e.mo871f());
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1185e(this.f25981a, m661b);
        return new C12766av(m661b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12766av) {
            return AbstractC12892d.m662a(this.f25981a, ((C12766av) obj).f25981a);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1191b(this.f25981a, m661b);
        return new C12766av(m661b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        long[] m661b = AbstractC12892d.m661b();
        C12765au.m1187d(this.f25981a, m661b);
        return new C12766av(m661b);
    }

    public int hashCode() {
        return C12957a.m431a(this.f25981a, 0, 2) ^ 113009;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12892d.m663a(this.f25981a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12892d.m656b(this.f25981a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return (this.f25981a[0] & 1) != 0;
    }
}
