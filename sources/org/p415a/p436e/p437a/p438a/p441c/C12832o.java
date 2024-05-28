package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.InterfaceC12849c;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.o */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12832o extends AbstractC12850d.AbstractC12852b {

    /* renamed from: i */
    public static final BigInteger f26035i = new BigInteger(1, C12964f.m419a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFEE37"));

    /* renamed from: j */
    protected C12835r f26036j;

    public C12832o() {
        super(f26035i);
        this.f26036j = new C12835r(this, null, null);
        this.f26076b = mo906a(InterfaceC12849c.f26069c);
        this.f26077c = mo906a(BigInteger.valueOf(3L));
        this.f26078d = new BigInteger(1, C12964f.m419a("FFFFFFFFFFFFFFFFFFFFFFFE26F2FC170F69466A74DEFD8D"));
        this.f26079e = BigInteger.valueOf(1L);
        this.f26080f = 2;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public int mo908a() {
        return f26035i.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12856e mo906a(BigInteger bigInteger) {
        return new C12834q(bigInteger);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        return new C12835r(this, abstractC12856e, abstractC12856e2, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        return new C12835r(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public boolean mo907a(int i) {
        return i == 2;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: c */
    public AbstractC12850d mo902c() {
        return new C12832o();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: e */
    public AbstractC12860g mo901e() {
        return this.f26036j;
    }
}
