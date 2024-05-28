package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ai */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12753ai extends AbstractC12850d.AbstractC12852b {

    /* renamed from: i */
    public static final BigInteger f25963i = new BigInteger(1, C12964f.m419a("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFF"));

    /* renamed from: j */
    protected C12756al f25964j;

    public C12753ai() {
        super(f25963i);
        this.f25964j = new C12756al(this, null, null);
        this.f26076b = mo906a(new BigInteger(1, C12964f.m419a("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f26077c = mo906a(new BigInteger(1, C12964f.m419a("5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B")));
        this.f26078d = new BigInteger(1, C12964f.m419a("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551"));
        this.f26079e = BigInteger.valueOf(1L);
        this.f26080f = 2;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public int mo908a() {
        return f25963i.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12856e mo906a(BigInteger bigInteger) {
        return new C12755ak(bigInteger);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        return new C12756al(this, abstractC12856e, abstractC12856e2, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        return new C12756al(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: a */
    public boolean mo907a(int i) {
        return i == 2;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: c */
    public AbstractC12850d mo902c() {
        return new C12753ai();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12850d
    /* renamed from: e */
    public AbstractC12860g mo901e() {
        return this.f25964j;
    }
}
