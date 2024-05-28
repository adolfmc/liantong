package org.p415a.p418b;

import org.p415a.p448g.C12957a;
import org.p415a.p448g.C12975h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ab */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12410ab extends AbstractC12570t {

    /* renamed from: a */
    private byte[] f25112a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12410ab(byte[] bArr) {
        this.f25112a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.mo1620b(23);
        int length = this.f25112a.length;
        c12567r.m1629a(length);
        for (int i = 0; i != length; i++) {
            c12567r.mo1620b(this.f25112a[i]);
        }
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
        if (abstractC12570t instanceof C12410ab) {
            return C12957a.m438a(this.f25112a, ((C12410ab) abstractC12570t).f25112a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int length = this.f25112a.length;
        return C12466bz.m1694a(length) + 1 + length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return C12957a.m441a(this.f25112a);
    }

    public String toString() {
        return C12975h.m386b(this.f25112a);
    }
}
