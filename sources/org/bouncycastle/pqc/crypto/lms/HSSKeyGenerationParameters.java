package org.bouncycastle.pqc.crypto.lms;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HSSKeyGenerationParameters extends KeyGenerationParameters {
    private final LMSParameters[] lmsParameters;

    public HSSKeyGenerationParameters(LMSParameters[] lMSParametersArr, SecureRandom secureRandom) {
        super(secureRandom, LmsUtils.calculateStrength(lMSParametersArr[0]));
        if (lMSParametersArr.length == 0 || lMSParametersArr.length > 8) {
            throw new IllegalArgumentException("lmsParameters length should be between 1 and 8 inclusive");
        }
        this.lmsParameters = lMSParametersArr;
    }

    public int getDepth() {
        return this.lmsParameters.length;
    }

    public LMSParameters[] getLmsParameters() {
        return this.lmsParameters;
    }
}
