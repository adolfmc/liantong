package org.bouncycastle.math.p464ec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.AbstractECLookupTable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class AbstractECLookupTable implements ECLookupTable {
    @Override // org.bouncycastle.math.p464ec.ECLookupTable
    public ECPoint lookupVar(int i) {
        return lookup(i);
    }
}
