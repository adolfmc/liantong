package org.bouncycastle.crypto.params;

import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int certainty;

    /* renamed from: l */
    private final int f26794l;

    /* renamed from: n */
    private final int f26795n;
    private final SecureRandom random;
    private final int usageIndex;

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom) {
        this(i, i2, i3, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom, int i4) {
        this.f26794l = i;
        this.f26795n = i2;
        this.certainty = i3;
        this.usageIndex = i4;
        this.random = secureRandom;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public int getL() {
        return this.f26794l;
    }

    public int getN() {
        return this.f26795n;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }
}
