package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DigestingStateAwareMessageSigner extends DigestingMessageSigner {
    private final StateAwareMessageSigner signer;

    public DigestingStateAwareMessageSigner(StateAwareMessageSigner stateAwareMessageSigner, Digest digest) {
        super(stateAwareMessageSigner, digest);
        this.signer = stateAwareMessageSigner;
    }

    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        return this.signer.getUpdatedPrivateKey();
    }
}
