package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n */
    private int f27310n;

    /* renamed from: t */
    private int f27311t;

    public McElieceCCA2PublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix, String str) {
        super(false, str);
        this.f27310n = i;
        this.f27311t = i2;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }

    public int getN() {
        return this.f27310n;
    }

    public int getT() {
        return this.f27311t;
    }
}
