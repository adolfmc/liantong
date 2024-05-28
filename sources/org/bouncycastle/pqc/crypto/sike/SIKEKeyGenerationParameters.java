package org.bouncycastle.pqc.crypto.sike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SIKEKeyGenerationParameters extends KeyGenerationParameters {
    private SIKEParameters params;

    public SIKEKeyGenerationParameters(SecureRandom secureRandom, SIKEParameters sIKEParameters) {
        super(secureRandom, 256);
        this.params = sIKEParameters;
    }

    public SIKEParameters getParameters() {
        return this.params;
    }
}
