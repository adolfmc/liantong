package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.pqc.math.ntru.Polynomial;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class PolynomialPair {

    /* renamed from: a */
    private final Polynomial f27221a;

    /* renamed from: b */
    private final Polynomial f27222b;

    public PolynomialPair(Polynomial polynomial, Polynomial polynomial2) {
        this.f27221a = polynomial;
        this.f27222b = polynomial2;
    }

    /* renamed from: f */
    public Polynomial m251f() {
        return this.f27221a;
    }

    /* renamed from: g */
    public Polynomial m250g() {
        return this.f27222b;
    }

    /* renamed from: m */
    public Polynomial m249m() {
        return this.f27222b;
    }

    /* renamed from: r */
    public Polynomial m248r() {
        return this.f27221a;
    }
}
