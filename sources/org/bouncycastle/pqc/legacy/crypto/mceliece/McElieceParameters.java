package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialRingGF2;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class McElieceParameters implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private Digest digest;
    private int fieldPoly;

    /* renamed from: m */
    private int f27327m;

    /* renamed from: n */
    private int f27328n;

    /* renamed from: t */
    private int f27329t;

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(int i) {
        this(i, (Digest) null);
    }

    public McElieceParameters(int i, int i2) {
        this(i, i2, (Digest) null);
    }

    public McElieceParameters(int i, int i2, int i3) {
        this(i, i2, i3, null);
    }

    public McElieceParameters(int i, int i2, int i3, Digest digest) {
        this.f27327m = i;
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        this.f27328n = 1 << i;
        this.f27329t = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > this.f27328n) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(i3) != i || !PolynomialRingGF2.isIrreducible(i3)) {
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
        }
        this.fieldPoly = i3;
        this.digest = digest;
    }

    public McElieceParameters(int i, int i2, Digest digest) {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f27327m = i;
        this.f27328n = 1 << i;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > this.f27328n) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f27329t = i2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i);
        this.digest = digest;
    }

    public McElieceParameters(int i, Digest digest) {
        if (i < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f27327m = 0;
        this.f27328n = 1;
        while (true) {
            int i2 = this.f27328n;
            if (i2 >= i) {
                this.f27329t = i2 >>> 1;
                int i3 = this.f27329t;
                int i4 = this.f27327m;
                this.f27329t = i3 / i4;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i4);
                this.digest = digest;
                return;
            }
            this.f27328n = i2 << 1;
            this.f27327m++;
        }
    }

    public McElieceParameters(Digest digest) {
        this(11, 50, digest);
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f27327m;
    }

    public int getN() {
        return this.f27328n;
    }

    public int getT() {
        return this.f27329t;
    }
}
