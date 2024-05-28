package org.p415a.p436e.p443b;

import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12884d implements InterfaceC12886f {

    /* renamed from: a */
    protected final int[] f26144a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12884d(int[] iArr) {
        this.f26144a = C12957a.m429b(iArr);
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12886f
    /* renamed from: a */
    public int mo727a() {
        int[] iArr = this.f26144a;
        return iArr[iArr.length - 1];
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12886f
    /* renamed from: b */
    public int[] mo726b() {
        return C12957a.m429b(this.f26144a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C12884d) {
            return C12957a.m433a(this.f26144a, ((C12884d) obj).f26144a);
        }
        return false;
    }

    public int hashCode() {
        return C12957a.m435a(this.f26144a);
    }
}
