package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SerpentEngineBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;
    protected boolean encrypting;
    protected int keyBits;
    protected int[] wKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerpentEngineBase() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
    }

    private CryptoServicePurpose getPurpose() {
        return this.wKey == null ? CryptoServicePurpose.ANY : this.encrypting ? CryptoServicePurpose.ENCRYPTION : CryptoServicePurpose.DECRYPTION;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    protected static int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: LT */
    public final void m279LT(int[] iArr) {
        int rotateLeft = rotateLeft(iArr[0], 13);
        int rotateLeft2 = rotateLeft(iArr[2], 3);
        iArr[1] = rotateLeft((iArr[1] ^ rotateLeft) ^ rotateLeft2, 1);
        iArr[3] = rotateLeft((iArr[3] ^ rotateLeft2) ^ (rotateLeft << 3), 7);
        iArr[0] = rotateLeft((rotateLeft ^ iArr[1]) ^ iArr[3], 5);
        iArr[2] = rotateLeft((iArr[3] ^ rotateLeft2) ^ (iArr[1] << 7), 22);
    }

    protected abstract void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Serpent";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib0(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i;
        int i7 = (i5 | i6) ^ i4;
        int i8 = i3 ^ i7;
        iArr[2] = i6 ^ i8;
        int i9 = (i6 & i4) ^ i5;
        iArr[1] = (iArr[2] & i9) ^ i7;
        iArr[3] = (i & i7) ^ (iArr[1] | i8);
        iArr[0] = iArr[3] ^ (i9 ^ i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib1(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i4 ^ i2;
        int i6 = i ^ (i2 & i5);
        int i7 = i5 ^ i6;
        iArr[3] = i3 ^ i7;
        int i8 = i2 ^ (i5 & i6);
        iArr[1] = i6 ^ (iArr[3] | i8);
        int i9 = ~iArr[1];
        int i10 = i8 ^ iArr[3];
        iArr[0] = i9 ^ i10;
        iArr[2] = (i9 | i10) ^ i7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib2(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = ~i5;
        int i7 = i ^ i3;
        int i8 = i3 ^ i5;
        iArr[0] = (i2 & i8) ^ i7;
        iArr[3] = (((i | i6) ^ i4) | i7) ^ i5;
        int i9 = ~i8;
        int i10 = iArr[3] | iArr[0];
        iArr[1] = i9 ^ i10;
        iArr[2] = (i9 & i4) ^ (i10 ^ i7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib3(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i | i2;
        int i6 = i2 ^ i3;
        int i7 = i ^ (i2 & i6);
        int i8 = i3 ^ i7;
        int i9 = i4 | i7;
        iArr[0] = i6 ^ i9;
        int i10 = (i9 | i6) ^ i4;
        iArr[2] = i8 ^ i10;
        int i11 = i5 ^ i10;
        iArr[3] = i7 ^ (iArr[0] & i11);
        iArr[1] = iArr[3] ^ (i11 ^ iArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib4(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 ^ ((i3 | i4) & i);
        int i6 = i3 ^ (i & i5);
        iArr[1] = i4 ^ i6;
        int i7 = ~i;
        iArr[3] = (i6 & iArr[1]) ^ i5;
        int i8 = (iArr[1] | i7) ^ i4;
        iArr[0] = iArr[3] ^ i8;
        iArr[2] = (i7 ^ iArr[1]) ^ (i5 & i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib5(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i3;
        int i6 = (i2 & i5) ^ i4;
        int i7 = i & i6;
        iArr[3] = (i2 ^ i5) ^ i7;
        int i8 = iArr[3] | i2;
        iArr[1] = i6 ^ (i & i8);
        int i9 = i4 | i;
        iArr[0] = (i5 ^ i8) ^ i9;
        iArr[2] = ((i ^ i3) | i7) ^ (i2 & i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib6(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i3 ^ i6;
        int i8 = (i3 | i5) ^ i4;
        iArr[1] = i7 ^ i8;
        int i9 = i6 ^ (i7 & i8);
        iArr[3] = i8 ^ (i2 | i9);
        int i10 = i2 | iArr[3];
        iArr[0] = i9 ^ i10;
        iArr[2] = (i4 & i5) ^ (i10 ^ i7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib7(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = (i & i2) | i3;
        int i6 = (i | i2) & i4;
        iArr[3] = i5 ^ i6;
        int i7 = i2 ^ i6;
        iArr[1] = ((iArr[3] ^ (~i4)) | i7) ^ i;
        iArr[0] = (i7 ^ i3) ^ (iArr[1] | i4);
        iArr[2] = ((i & iArr[3]) ^ iArr[0]) ^ (iArr[1] ^ i5);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = z;
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.wKey = makeWorkingKey(key);
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, getPurpose()));
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void inverseLT(int[] iArr) {
        int rotateRight = (rotateRight(iArr[2], 22) ^ iArr[3]) ^ (iArr[1] << 7);
        int rotateRight2 = (rotateRight(iArr[0], 5) ^ iArr[1]) ^ iArr[3];
        int rotateRight3 = rotateRight(iArr[3], 7);
        int rotateRight4 = rotateRight(iArr[1], 1);
        iArr[3] = (rotateRight3 ^ rotateRight) ^ (rotateRight2 << 3);
        iArr[1] = (rotateRight4 ^ rotateRight2) ^ rotateRight;
        iArr[2] = rotateRight(rotateRight, 3);
        iArr[0] = rotateRight(rotateRight2, 13);
    }

    protected abstract int[] makeWorkingKey(byte[] bArr);

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.wKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + 16 <= bArr.length) {
            if (i2 + 16 <= bArr2.length) {
                if (this.encrypting) {
                    encryptBlock(bArr, i, bArr2, i2);
                    return 16;
                }
                decryptBlock(bArr, i, bArr2, i2);
                return 16;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb0(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ i5;
        int i7 = i2 ^ i6;
        iArr[3] = (i4 & i) ^ i7;
        int i8 = i ^ (i2 & i5);
        iArr[2] = (i3 | i8) ^ i7;
        int i9 = iArr[3] & (i6 ^ i8);
        iArr[1] = (~i6) ^ i9;
        iArr[0] = (~i8) ^ i9;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb1(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = (~i) ^ i2;
        int i6 = (i | i5) ^ i3;
        iArr[2] = i4 ^ i6;
        int i7 = i2 ^ (i4 | i5);
        int i8 = iArr[2] ^ i5;
        iArr[3] = (i6 & i7) ^ i8;
        int i9 = i7 ^ i6;
        iArr[1] = iArr[3] ^ i9;
        iArr[0] = i6 ^ (i9 & i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb2(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i4;
        iArr[0] = (i3 & i5) ^ i6;
        int i7 = i3 ^ i5;
        int i8 = i2 & (i3 ^ iArr[0]);
        iArr[3] = i7 ^ i8;
        iArr[2] = i ^ ((i8 | i4) & (iArr[0] | i7));
        iArr[1] = (iArr[3] ^ i6) ^ (iArr[2] ^ (i4 | i5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb3(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i ^ i2;
        int i6 = i & i3;
        int i7 = i | i4;
        int i8 = i3 ^ i4;
        int i9 = i6 | (i5 & i7);
        iArr[2] = i8 ^ i9;
        int i10 = (i7 ^ i2) ^ i9;
        iArr[0] = i5 ^ (i8 & i10);
        int i11 = iArr[2] & iArr[0];
        iArr[1] = i10 ^ i11;
        iArr[3] = (i2 | i4) ^ (i8 ^ i11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb4(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ (i4 & i5);
        int i7 = i2 | i6;
        iArr[3] = i5 ^ i7;
        int i8 = ~i2;
        iArr[0] = (i5 | i8) ^ i6;
        int i9 = i8 ^ i5;
        iArr[2] = (i7 & i9) ^ (iArr[0] & i);
        iArr[1] = (i ^ i6) ^ (i9 & iArr[2]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb5(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i ^ i4;
        iArr[0] = (i3 ^ i5) ^ (i6 | i7);
        int i8 = iArr[0] & i4;
        iArr[1] = (iArr[0] ^ i6) ^ i8;
        int i9 = i7 ^ (iArr[0] | i5);
        iArr[2] = (i6 | i8) ^ i9;
        iArr[3] = (i9 & iArr[1]) ^ (i2 ^ i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb6(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i4;
        int i7 = i2 ^ i6;
        int i8 = i3 ^ (i5 | i6);
        iArr[1] = i2 ^ i8;
        int i9 = (i6 | iArr[1]) ^ i4;
        iArr[2] = (i8 & i9) ^ i7;
        int i10 = i9 ^ i8;
        iArr[0] = iArr[2] ^ i10;
        iArr[3] = (i10 & i7) ^ (~i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb7(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i3;
        int i6 = (i3 & i5) ^ i4;
        int i7 = i ^ i6;
        iArr[1] = i2 ^ ((i4 | i5) & i7);
        iArr[3] = (i & i7) ^ i5;
        int i8 = i7 ^ (iArr[1] | i6);
        iArr[2] = (iArr[3] & i8) ^ i6;
        iArr[0] = (~i8) ^ (iArr[3] & iArr[2]);
    }
}
