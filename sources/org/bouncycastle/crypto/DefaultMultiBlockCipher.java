package org.bouncycastle.crypto;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class DefaultMultiBlockCipher implements MultiBlockCipher {
    @Override // org.bouncycastle.crypto.MultiBlockCipher
    public int getMultiBlockSize() {
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.MultiBlockCipher
    public int processBlocks(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        int multiBlockSize = getMultiBlockSize();
        int i4 = i;
        int i5 = 0;
        for (int i6 = 0; i6 != i2; i6++) {
            i5 += processBlock(bArr, i4, bArr2, i3 + i5);
            i4 += multiBlockSize;
        }
        return i5;
    }
}
