package org.p415a.p418b;

import org.p415a.p448g.C12957a;
import org.p415a.p448g.C12975h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bc */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12443bc extends AbstractC12570t {

    /* renamed from: a */
    private final byte[] f25215a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12443bc(byte[] bArr) {
        this.f25215a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1626a(19, this.f25215a);
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
        if (abstractC12570t instanceof C12443bc) {
            return C12957a.m438a(this.f25215a, ((C12443bc) abstractC12570t).f25215a);
        }
        return false;
    }

    /* renamed from: b */
    public String m1713b() {
        return C12975h.m386b(this.f25215a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1694a(this.f25215a.length) + 1 + this.f25215a.length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return C12957a.m441a(this.f25215a);
    }

    public String toString() {
        return m1713b();
    }
}
