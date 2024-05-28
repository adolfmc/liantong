package org.bouncycastle.pqc.crypto.cmce;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.pqc.crypto.cmce.GF */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class AbstractC13366GF {
    protected final int GFBITS;
    protected final int GFMASK;

    public AbstractC13366GF(int i) {
        this.GFBITS = i;
        this.GFMASK = (1 << this.GFBITS) - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short gf_add(short s, short s2) {
        return (short) (s ^ s2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract short gf_frac(short s, short s2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract short gf_inv(short s);

    /* JADX INFO: Access modifiers changed from: package-private */
    public short gf_iszero(short s) {
        return (short) ((s - 1) >>> 19);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract short gf_mul(short s, short s2);
}
