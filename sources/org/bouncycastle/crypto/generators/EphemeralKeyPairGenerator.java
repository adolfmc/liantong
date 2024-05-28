package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.KeyEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EphemeralKeyPairGenerator {
    private AsymmetricCipherKeyPairGenerator gen;
    private KeyEncoder keyEncoder;

    public EphemeralKeyPairGenerator(AsymmetricCipherKeyPairGenerator asymmetricCipherKeyPairGenerator, KeyEncoder keyEncoder) {
        this.gen = asymmetricCipherKeyPairGenerator;
        this.keyEncoder = keyEncoder;
    }

    public EphemeralKeyPair generate() {
        return new EphemeralKeyPair(this.gen.generateKeyPair(), this.keyEncoder);
    }
}
