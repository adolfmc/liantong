package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.C12878u;
import org.p415a.p436e.p437a.InterfaceC12859f;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bi */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12780bi extends AbstractC12850d.AbstractC12851a {

    /* renamed from: i */
    protected C12781bj f25993i;

    public C12780bi() {
        super(163, 3, 6, 7);
        this.f25993i = new C12781bj(this, null, null);
        this.f26076b = mo906a(BigInteger.valueOf(1L));
        this.f26077c = this.f26076b;
        this.f26078d = new BigInteger(1, C12964f.m419a("04000000000000000000020108A2E0CC0D99F8A5EF"));
        this.f26079e = BigInteger.valueOf(2L);
        this.f26080f = 6;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public int mo908a() {
        return 163;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12856e mo906a(BigInteger bigInteger) {
        return new C12779bh(bigInteger);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        return new C12781bj(this, abstractC12856e, abstractC12856e2, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        return new C12781bj(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public boolean mo907a(int i) {
        return i == 6;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: c */
    public AbstractC12850d mo902c() {
        return new C12780bi();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: d */
    public InterfaceC12859f mo909d() {
        return new C12878u();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: e */
    public AbstractC12860g mo901e() {
        return this.f25993i;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d.AbstractC12851a
    /* renamed from: n */
    public boolean mo913n() {
        return true;
    }
}
