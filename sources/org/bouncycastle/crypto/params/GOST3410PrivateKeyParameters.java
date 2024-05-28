package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {

    /* renamed from: x */
    private BigInteger f26814x;

    public GOST3410PrivateKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(true, gOST3410Parameters);
        this.f26814x = bigInteger;
    }

    public BigInteger getX() {
        return this.f26814x;
    }
}
