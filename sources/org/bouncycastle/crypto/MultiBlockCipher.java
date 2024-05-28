package org.bouncycastle.crypto;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface MultiBlockCipher extends BlockCipher {
    int getMultiBlockSize();

    int processBlocks(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException;
}
