package org.bouncycastle.pqc.crypto.frodo;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FrodoKeyGenerationParameters extends KeyGenerationParameters {
    private FrodoParameters params;

    public FrodoKeyGenerationParameters(SecureRandom secureRandom, FrodoParameters frodoParameters) {
        super(secureRandom, 256);
        this.params = frodoParameters;
    }

    public FrodoParameters getParameters() {
        return this.params;
    }
}
