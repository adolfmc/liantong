package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class XMSSKeyGenerationParameters extends KeyGenerationParameters {
    private final XMSSParameters xmssParameters;

    public XMSSKeyGenerationParameters(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        super(secureRandom, -1);
        this.xmssParameters = xMSSParameters;
    }

    public XMSSParameters getParameters() {
        return this.xmssParameters;
    }
}
