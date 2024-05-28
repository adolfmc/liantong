package org.p415a.p418b;

import org.p415a.p448g.C12957a;
import org.p415a.p448g.C12972f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12556h extends AbstractC12570t {

    /* renamed from: b */
    private static C12556h[] f25497b = new C12556h[12];

    /* renamed from: a */
    private final byte[] f25498a;

    public C12556h(byte[] bArr) {
        if (!C12972f.m394a("org.bouncycastle1.asn1.allow_unsafe_integer") && C12560l.m1646a(bArr)) {
            throw new IllegalArgumentException("malformed enumerated");
        }
        this.f25498a = C12957a.m430b(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12556h m1658a(byte[] bArr) {
        if (bArr.length > 1) {
            return new C12556h(bArr);
        }
        if (bArr.length != 0) {
            int i = bArr[0] & 255;
            C12556h[] c12556hArr = f25497b;
            if (i >= c12556hArr.length) {
                return new C12556h(C12957a.m430b(bArr));
            }
            C12556h c12556h = c12556hArr[i];
            if (c12556h == null) {
                C12556h c12556h2 = new C12556h(C12957a.m430b(bArr));
                c12556hArr[i] = c12556h2;
                return c12556h2;
            }
            return c12556h;
        }
        throw new IllegalArgumentException("ENUMERATED has zero length");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1626a(10, this.f25498a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof C12556h) {
            return C12957a.m438a(this.f25498a, ((C12556h) abstractC12570t).f25498a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1694a(this.f25498a.length) + 1 + this.f25498a.length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return C12957a.m441a(this.f25498a);
    }
}
