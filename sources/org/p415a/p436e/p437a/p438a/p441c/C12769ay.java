package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ay */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12769ay extends AbstractC12850d.AbstractC12851a {

    /* renamed from: i */
    protected C12770az f25983i;

    public C12769ay() {
        super(113, 9, 0, 0);
        this.f25983i = new C12770az(this, null, null);
        this.f26076b = mo906a(new BigInteger(1, C12964f.m419a("00689918DBEC7E5A0DD6DFC0AA55C7")));
        this.f26077c = mo906a(new BigInteger(1, C12964f.m419a("0095E9A9EC9B297BD4BF36E059184F")));
        this.f26078d = new BigInteger(1, C12964f.m419a("010000000000000108789B2496AF93"));
        this.f26079e = BigInteger.valueOf(2L);
        this.f26080f = 6;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public int mo908a() {
        return 113;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12856e mo906a(BigInteger bigInteger) {
        return new C12766av(bigInteger);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        return new C12770az(this, abstractC12856e, abstractC12856e2, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        return new C12770az(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public boolean mo907a(int i) {
        return i == 6;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: c */
    public AbstractC12850d mo902c() {
        return new C12769ay();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: e */
    public AbstractC12860g mo901e() {
        return this.f25983i;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d.AbstractC12851a
    /* renamed from: n */
    public boolean mo913n() {
        return false;
    }
}
