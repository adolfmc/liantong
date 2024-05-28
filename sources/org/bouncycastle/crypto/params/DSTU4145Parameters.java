package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSTU4145Parameters extends ECDomainParameters {
    private final byte[] dke;

    public DSTU4145Parameters(ECDomainParameters eCDomainParameters, byte[] bArr) {
        super(eCDomainParameters.getCurve(), eCDomainParameters.getG(), eCDomainParameters.getN(), eCDomainParameters.getH(), eCDomainParameters.getSeed());
        this.dke = Arrays.clone(bArr);
    }

    public byte[] getDKE() {
        return Arrays.clone(this.dke);
    }
}
