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
/* renamed from: org.a.e.a.a.c.cg */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12805cg extends AbstractC12850d.AbstractC12851a {

    /* renamed from: i */
    protected C12806ch f26008i;

    public C12805cg() {
        super(283, 5, 7, 12);
        this.f26008i = new C12806ch(this, null, null);
        this.f26076b = mo906a(BigInteger.valueOf(0L));
        this.f26077c = mo906a(BigInteger.valueOf(1L));
        this.f26078d = new BigInteger(1, C12964f.m419a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE9AE2ED07577265DFF7F94451E061E163C61"));
        this.f26079e = BigInteger.valueOf(4L);
        this.f26080f = 6;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public int mo908a() {
        return 283;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12856e mo906a(BigInteger bigInteger) {
        return new C12804cf(bigInteger);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        return new C12806ch(this, abstractC12856e, abstractC12856e2, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        return new C12806ch(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public boolean mo907a(int i) {
        return i == 6;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: c */
    public AbstractC12850d mo902c() {
        return new C12805cg();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: d */
    public InterfaceC12859f mo909d() {
        return new C12878u();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: e */
    public AbstractC12860g mo901e() {
        return this.f26008i;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d.AbstractC12851a
    /* renamed from: n */
    public boolean mo913n() {
        return true;
    }
}
