package org.bouncycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialRingGF2;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m */
    private int f27283m;

    /* renamed from: n */
    private int f27284n;

    /* renamed from: t */
    private int f27285t;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public McElieceKeyGenParameterSpec(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f27283m = 0;
        this.f27284n = 1;
        while (true) {
            int i2 = this.f27284n;
            if (i2 >= i) {
                this.f27285t = i2 >>> 1;
                int i3 = this.f27285t;
                int i4 = this.f27283m;
                this.f27285t = i3 / i4;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i4);
                return;
            }
            this.f27284n = i2 << 1;
            this.f27283m++;
        }
    }

    public McElieceKeyGenParameterSpec(int i, int i2) throws InvalidParameterException {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f27283m = i;
        this.f27284n = 1 << i;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > this.f27284n) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f27285t = i2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i);
    }

    public McElieceKeyGenParameterSpec(int i, int i2, int i3) {
        this.f27283m = i;
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        this.f27284n = 1 << i;
        this.f27285t = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > this.f27284n) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(i3) != i || !PolynomialRingGF2.isIrreducible(i3)) {
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
        }
        this.fieldPoly = i3;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f27283m;
    }

    public int getN() {
        return this.f27284n;
    }

    public int getT() {
        return this.f27285t;
    }
}
