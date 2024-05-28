package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g */
    private GF2Matrix f27339g;

    /* renamed from: n */
    private int f27340n;

    /* renamed from: t */
    private int f27341t;

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f27340n = i;
        this.f27341t = i2;
        this.f27339g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.f27339g;
    }

    public int getK() {
        return this.f27339g.getNumRows();
    }

    public int getN() {
        return this.f27340n;
    }

    public int getT() {
        return this.f27341t;
    }
}
