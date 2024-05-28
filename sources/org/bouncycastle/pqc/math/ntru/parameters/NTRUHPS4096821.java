package org.bouncycastle.pqc.math.ntru.parameters;

import org.bouncycastle.pqc.math.ntru.HPS4096Polynomial;
import org.bouncycastle.pqc.math.ntru.Polynomial;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRUHPS4096821 extends NTRUHPSParameterSet {
    public NTRUHPS4096821() {
        super(821, 12, 32, 32, 32);
    }

    @Override // org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet, org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet
    public Polynomial createPolynomial() {
        return new HPS4096Polynomial(this);
    }
}
