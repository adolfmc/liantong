package org.bouncycastle.jcajce.provider.asymmetric.util;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.jce.spec.IESParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class IESUtil {
    public static IESParameterSpec guessParameterSpec(BufferedBlockCipher bufferedBlockCipher, byte[] bArr) {
        if (bufferedBlockCipher == null) {
            return new IESParameterSpec(null, null, 128);
        }
        BlockCipher underlyingCipher = bufferedBlockCipher.getUnderlyingCipher();
        return (underlyingCipher.getAlgorithmName().equals("DES") || underlyingCipher.getAlgorithmName().equals("RC2") || underlyingCipher.getAlgorithmName().equals("RC5-32") || underlyingCipher.getAlgorithmName().equals("RC5-64")) ? new IESParameterSpec(null, null, 64, 64, bArr) : underlyingCipher.getAlgorithmName().equals("SKIPJACK") ? new IESParameterSpec(null, null, 80, 80, bArr) : underlyingCipher.getAlgorithmName().equals("GOST28147") ? new IESParameterSpec(null, null, 256, 256, bArr) : new IESParameterSpec(null, null, 128, 128, bArr);
    }
}
