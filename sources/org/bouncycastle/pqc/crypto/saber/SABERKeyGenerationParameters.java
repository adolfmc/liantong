package org.bouncycastle.pqc.crypto.saber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SABERKeyGenerationParameters extends KeyGenerationParameters {
    private SABERParameters params;

    public SABERKeyGenerationParameters(SecureRandom secureRandom, SABERParameters sABERParameters) {
        super(secureRandom, 256);
        this.params = sABERParameters;
    }

    public SABERParameters getParameters() {
        return this.params;
    }
}
