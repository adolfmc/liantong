package org.bouncycastle.crypto;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface StreamCipher {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

    int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;

    void reset();

    byte returnByte(byte b);
}
