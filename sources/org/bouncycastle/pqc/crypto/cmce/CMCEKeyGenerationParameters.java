package org.bouncycastle.pqc.crypto.cmce;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEKeyGenerationParameters extends KeyGenerationParameters {
    private CMCEParameters params;

    public CMCEKeyGenerationParameters(SecureRandom secureRandom, CMCEParameters cMCEParameters) {
        super(secureRandom, 256);
        this.params = cMCEParameters;
    }

    public CMCEParameters getParameters() {
        return this.params;
    }
}
