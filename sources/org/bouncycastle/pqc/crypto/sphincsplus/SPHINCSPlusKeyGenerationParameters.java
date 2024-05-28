package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPlusKeyGenerationParameters extends KeyGenerationParameters {
    private final SPHINCSPlusParameters parameters;

    public SPHINCSPlusKeyGenerationParameters(SecureRandom secureRandom, SPHINCSPlusParameters sPHINCSPlusParameters) {
        super(secureRandom, -1);
        this.parameters = sPHINCSPlusParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusParameters getParameters() {
        return this.parameters;
    }
}
