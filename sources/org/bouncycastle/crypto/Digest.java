package org.bouncycastle.crypto;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Digest {
    int doFinal(byte[] bArr, int i);

    String getAlgorithmName();

    int getDigestSize();

    void reset();

    void update(byte b);

    void update(byte[] bArr, int i, int i2);
}
