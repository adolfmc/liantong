package org.bouncycastle.crypto.p458ec;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.ec.ECPairTransform */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ECPairTransform {
    void init(CipherParameters cipherParameters);

    ECPair transform(ECPair eCPair);
}
