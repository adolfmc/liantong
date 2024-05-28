package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumKeyGenerationParameters extends KeyGenerationParameters {
    private final DilithiumParameters params;

    public DilithiumKeyGenerationParameters(SecureRandom secureRandom, DilithiumParameters dilithiumParameters) {
        super(secureRandom, 256);
        this.params = dilithiumParameters;
    }

    public DilithiumParameters getParameters() {
        return this.params;
    }
}
