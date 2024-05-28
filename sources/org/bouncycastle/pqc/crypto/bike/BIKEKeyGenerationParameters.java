package org.bouncycastle.pqc.crypto.bike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKEKeyGenerationParameters extends KeyGenerationParameters {
    private BIKEParameters params;

    public BIKEKeyGenerationParameters(SecureRandom secureRandom, BIKEParameters bIKEParameters) {
        super(secureRandom, 256);
        this.params = bIKEParameters;
    }

    public BIKEParameters getParameters() {
        return this.params;
    }
}
