package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
interface DRBGProvider {
    SP80090DRBG get(EntropySource entropySource);

    String getAlgorithm();
}
