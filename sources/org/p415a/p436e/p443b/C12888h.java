package org.p415a.p436e.p443b;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.b.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12888h implements InterfaceC12882b {

    /* renamed from: a */
    protected final BigInteger f26147a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12888h(BigInteger bigInteger) {
        this.f26147a = bigInteger;
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12882b
    /* renamed from: a */
    public BigInteger mo724a() {
        return this.f26147a;
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12882b
    /* renamed from: b */
    public int mo723b() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C12888h) {
            return this.f26147a.equals(((C12888h) obj).f26147a);
        }
        return false;
    }

    public int hashCode() {
        return this.f26147a.hashCode();
    }
}
