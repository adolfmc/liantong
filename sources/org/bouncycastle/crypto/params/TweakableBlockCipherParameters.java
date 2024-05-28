package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TweakableBlockCipherParameters implements CipherParameters {
    private final KeyParameter key;
    private final byte[] tweak;

    public TweakableBlockCipherParameters(KeyParameter keyParameter, byte[] bArr) {
        this.key = keyParameter;
        this.tweak = Arrays.clone(bArr);
    }

    public KeyParameter getKey() {
        return this.key;
    }

    public byte[] getTweak() {
        return this.tweak;
    }
}
