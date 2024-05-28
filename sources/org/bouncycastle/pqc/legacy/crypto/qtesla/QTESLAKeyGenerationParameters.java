package org.bouncycastle.pqc.legacy.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class QTESLAKeyGenerationParameters extends KeyGenerationParameters {
    private final int securityCategory;

    public QTESLAKeyGenerationParameters(int i, SecureRandom secureRandom) {
        super(secureRandom, -1);
        QTESLASecurityCategory.getPrivateSize(i);
        this.securityCategory = i;
    }

    public int getSecurityCategory() {
        return this.securityCategory;
    }
}
