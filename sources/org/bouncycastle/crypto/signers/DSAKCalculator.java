package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface DSAKCalculator {
    void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr);

    void init(BigInteger bigInteger, SecureRandom secureRandom);

    boolean isDeterministic();

    BigInteger nextK();
}
