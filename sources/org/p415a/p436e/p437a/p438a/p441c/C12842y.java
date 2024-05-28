package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12895g;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.y */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12842y extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f26054a = C12840w.f26049i;

    /* renamed from: i */
    private static final int[] f26055i = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};

    /* renamed from: b */
    protected int[] f26056b;

    public C12842y() {
        this.f26056b = AbstractC12895g.m590a();
    }

    public C12842y(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26054a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.f26056b = C12841x.m970a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12842y(int[] iArr) {
        this.f26056b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12895g.m577c(this.f26056b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m967a(this.f26056b, ((C12842y) abstractC12856e).f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f26054a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m961d(this.f26056b, ((C12842y) abstractC12856e).f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m968a(this.f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m965b(this.f26056b, ((C12842y) abstractC12856e).f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m966b(this.f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        AbstractC12890b.m713a(C12841x.f26051a, ((C12842y) abstractC12856e).f26056b, m590a);
        C12841x.m965b(m590a, this.f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m962d(this.f26056b, m590a);
        return new C12842y(m590a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12842y) {
            return AbstractC12895g.m579b(this.f26056b, ((C12842y) obj).f26056b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m590a = AbstractC12895g.m590a();
        AbstractC12890b.m713a(C12841x.f26051a, this.f26056b, m590a);
        return new C12842y(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f26056b;
        if (AbstractC12895g.m580b(iArr) || AbstractC12895g.m585a(iArr)) {
            return this;
        }
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m962d(iArr, m590a);
        C12841x.m965b(m590a, iArr, m590a);
        C12841x.m962d(m590a, m590a);
        C12841x.m965b(m590a, iArr, m590a);
        int[] m590a2 = AbstractC12895g.m590a();
        C12841x.m962d(m590a, m590a2);
        C12841x.m965b(m590a2, iArr, m590a2);
        int[] m590a3 = AbstractC12895g.m590a();
        C12841x.m969a(m590a2, 4, m590a3);
        C12841x.m965b(m590a3, m590a2, m590a3);
        int[] m590a4 = AbstractC12895g.m590a();
        C12841x.m969a(m590a3, 3, m590a4);
        C12841x.m965b(m590a4, m590a, m590a4);
        C12841x.m969a(m590a4, 8, m590a4);
        C12841x.m965b(m590a4, m590a3, m590a4);
        C12841x.m969a(m590a4, 4, m590a3);
        C12841x.m965b(m590a3, m590a2, m590a3);
        C12841x.m969a(m590a3, 19, m590a2);
        C12841x.m965b(m590a2, m590a4, m590a2);
        int[] m590a5 = AbstractC12895g.m590a();
        C12841x.m969a(m590a2, 42, m590a5);
        C12841x.m965b(m590a5, m590a2, m590a5);
        C12841x.m969a(m590a5, 23, m590a2);
        C12841x.m965b(m590a2, m590a3, m590a2);
        C12841x.m969a(m590a2, 84, m590a3);
        C12841x.m965b(m590a3, m590a5, m590a3);
        C12841x.m969a(m590a3, 20, m590a3);
        C12841x.m965b(m590a3, m590a4, m590a3);
        C12841x.m969a(m590a3, 3, m590a3);
        C12841x.m965b(m590a3, iArr, m590a3);
        C12841x.m969a(m590a3, 2, m590a3);
        C12841x.m965b(m590a3, iArr, m590a3);
        C12841x.m969a(m590a3, 4, m590a3);
        C12841x.m965b(m590a3, m590a, m590a3);
        C12841x.m962d(m590a3, m590a3);
        C12841x.m962d(m590a3, m590a5);
        if (AbstractC12895g.m579b(iArr, m590a5)) {
            return new C12842y(m590a3);
        }
        C12841x.m965b(m590a3, f26055i, m590a3);
        C12841x.m962d(m590a3, m590a5);
        if (AbstractC12895g.m579b(iArr, m590a5)) {
            return new C12842y(m590a3);
        }
        return null;
    }

    public int hashCode() {
        return f26054a.hashCode() ^ C12957a.m434a(this.f26056b, 0, 7);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12895g.m585a(this.f26056b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12895g.m580b(this.f26056b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12895g.m584a(this.f26056b, 0) == 1;
    }
}
