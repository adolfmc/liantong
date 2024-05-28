package org.bouncycastle.pqc.crypto.hqc;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQCKeyGenerationParameters extends KeyGenerationParameters {
    private HQCParameters params;

    public HQCKeyGenerationParameters(SecureRandom secureRandom, HQCParameters hQCParameters) {
        super(secureRandom, 256);
        this.params = hQCParameters;
    }

    public HQCParameters getParameters() {
        return this.params;
    }
}
