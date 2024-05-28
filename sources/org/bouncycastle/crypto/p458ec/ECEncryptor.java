package org.bouncycastle.crypto.p458ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.ec.ECEncryptor */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ECEncryptor {
    ECPair encrypt(ECPoint eCPoint);

    void init(CipherParameters cipherParameters);
}
