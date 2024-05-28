package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ElGamalPrivateKey extends DHPrivateKey, ElGamalKey {
    @Override // org.bouncycastle.jce.interfaces.ElGamalPrivateKey
    BigInteger getX();
}
