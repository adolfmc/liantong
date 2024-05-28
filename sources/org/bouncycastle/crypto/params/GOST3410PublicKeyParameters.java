package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410PublicKeyParameters extends GOST3410KeyParameters {

    /* renamed from: y */
    private BigInteger f26815y;

    public GOST3410PublicKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(false, gOST3410Parameters);
        this.f26815y = bigInteger;
    }

    public BigInteger getY() {
        return this.f26815y;
    }
}
