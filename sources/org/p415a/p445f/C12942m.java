package org.p415a.p445f;

import java.util.Iterator;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12942m implements InterfaceC12970d<C12941l> {

    /* renamed from: a */
    C12941l[] f26208a;

    public C12942m(C12941l[] c12941lArr) {
        this.f26208a = new C12941l[c12941lArr.length];
        System.arraycopy(c12941lArr, 0, this.f26208a, 0, c12941lArr.length);
    }

    @Override // java.lang.Iterable
    public Iterator<C12941l> iterator() {
        return new C12957a.C12958a(this.f26208a);
    }
}
