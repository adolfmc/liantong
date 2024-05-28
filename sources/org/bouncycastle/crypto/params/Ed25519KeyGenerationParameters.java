package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Ed25519KeyGenerationParameters extends KeyGenerationParameters {
    public Ed25519KeyGenerationParameters(SecureRandom secureRandom) {
        super(secureRandom, 256);
    }
}
