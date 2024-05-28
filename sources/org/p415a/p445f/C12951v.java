package org.p415a.p445f;

import java.util.Iterator;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.v */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12951v implements InterfaceC12970d<C12950u> {

    /* renamed from: a */
    C12950u[] f26231a;

    public C12951v(C12950u[] c12950uArr) {
        this.f26231a = new C12950u[c12950uArr.length];
        System.arraycopy(c12950uArr, 0, this.f26231a, 0, c12950uArr.length);
    }

    @Override // java.lang.Iterable
    public Iterator<C12950u> iterator() {
        return new C12957a.C12958a(this.f26231a);
    }
}
