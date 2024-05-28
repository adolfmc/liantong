package org.bouncycastle.crypto.signers;

import java.io.IOException;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface DSAEncoding {
    BigInteger[] decode(BigInteger bigInteger, byte[] bArr) throws IOException;

    byte[] encode(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) throws IOException;
}
