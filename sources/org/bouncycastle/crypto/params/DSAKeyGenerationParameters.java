package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSAKeyGenerationParameters extends KeyGenerationParameters {
    private DSAParameters params;

    public DSAKeyGenerationParameters(SecureRandom secureRandom, DSAParameters dSAParameters) {
        super(secureRandom, dSAParameters.getP().bitLength() - 1);
        this.params = dSAParameters;
    }

    public DSAParameters getParameters() {
        return this.params;
    }
}
