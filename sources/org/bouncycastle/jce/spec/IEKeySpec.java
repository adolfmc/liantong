package org.bouncycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.bouncycastle.jce.interfaces.IESKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class IEKeySpec implements KeySpec, IESKey {
    private PrivateKey privKey;
    private PublicKey pubKey;

    public IEKeySpec(PrivateKey privateKey, PublicKey publicKey) {
        this.privKey = privateKey;
        this.pubKey = publicKey;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "IES";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    @Override // org.bouncycastle.jce.interfaces.IESKey
    public PrivateKey getPrivate() {
        return this.privKey;
    }

    @Override // org.bouncycastle.jce.interfaces.IESKey
    public PublicKey getPublic() {
        return this.pubKey;
    }
}
