package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ElGamalPublicKey extends DHPublicKey, ElGamalKey {
    @Override // org.bouncycastle.jce.interfaces.ElGamalPublicKey
    BigInteger getY();
}
