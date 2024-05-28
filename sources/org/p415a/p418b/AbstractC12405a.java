package org.p415a.p418b;

import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12405a extends AbstractC12570t {

    /* renamed from: a */
    protected final boolean f25104a;

    /* renamed from: b */
    protected final int f25105b;

    /* renamed from: c */
    protected final byte[] f25106c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC12405a(boolean z, int i, byte[] bArr) {
        this.f25104a = z;
        this.f25105b = i;
        this.f25106c = C12957a.m430b(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1627a(this.f25104a ? 96 : 64, this.f25105b, this.f25106c);
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return this.f25104a;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof AbstractC12405a) {
            AbstractC12405a abstractC12405a = (AbstractC12405a) abstractC12570t;
            return this.f25104a == abstractC12405a.f25104a && this.f25105b == abstractC12405a.f25105b && C12957a.m438a(this.f25106c, abstractC12405a.f25106c);
        }
        return false;
    }

    /* renamed from: b */
    public int m1740b() {
        return this.f25105b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1692b(this.f25105b) + C12466bz.m1694a(this.f25106c.length) + this.f25106c.length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        boolean z = this.f25104a;
        return ((z ? 1 : 0) ^ this.f25105b) ^ C12957a.m441a(this.f25106c);
    }
}
