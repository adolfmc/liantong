package org.p415a.p427d.p435h;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.InterfaceC12849c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12709e implements InterfaceC12849c {

    /* renamed from: a */
    private AbstractC12850d f25899a;

    /* renamed from: b */
    private byte[] f25900b;

    /* renamed from: i */
    private AbstractC12860g f25901i;

    /* renamed from: j */
    private BigInteger f25902j;

    /* renamed from: k */
    private BigInteger f25903k;

    public C12709e(AbstractC12850d abstractC12850d, AbstractC12860g abstractC12860g, BigInteger bigInteger) {
        this(abstractC12850d, abstractC12860g, bigInteger, f26070d, null);
    }

    public C12709e(AbstractC12850d abstractC12850d, AbstractC12860g abstractC12860g, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f25899a = abstractC12850d;
        this.f25901i = abstractC12860g.m852m();
        this.f25902j = bigInteger;
        this.f25903k = bigInteger2;
        this.f25900b = bArr;
    }

    /* renamed from: a */
    public AbstractC12860g m1340a() {
        return this.f25901i;
    }

    /* renamed from: b */
    public BigInteger m1339b() {
        return this.f25902j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C12709e) {
            C12709e c12709e = (C12709e) obj;
            return this.f25899a.m934a(c12709e.f25899a) && this.f25901i.m863a(c12709e.f25901i) && this.f25902j.equals(c12709e.f25902j) && this.f25903k.equals(c12709e.f25903k);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.f25899a.hashCode() * 37) ^ this.f25901i.hashCode()) * 37) ^ this.f25902j.hashCode()) * 37) ^ this.f25903k.hashCode();
    }
}
