package org.bouncycastle.crypto.signers;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ISO9796d2Signer implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private boolean fullMessage;
    private int keyBits;
    private byte[] mBuf;
    private int messageLength;
    private byte[] preBlock;
    private byte[] preSig;
    private byte[] recoveredMessage;
    private int trailer;

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean z) {
        int intValue;
        this.cipher = asymmetricBlockCipher;
        this.digest = digest;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.trailer = intValue;
    }

    private void clearBlock(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    private boolean isSameAs(byte[] bArr, byte[] bArr2) {
        boolean z;
        int i = this.messageLength;
        byte[] bArr3 = this.mBuf;
        if (i > bArr3.length) {
            z = bArr3.length <= bArr2.length;
            for (int i2 = 0; i2 != this.mBuf.length; i2++) {
                if (bArr[i2] != bArr2[i2]) {
                    z = false;
                }
            }
        } else {
            z = i == bArr2.length;
            for (int i3 = 0; i3 != bArr2.length; i3++) {
                if (bArr[i3] != bArr2[i3]) {
                    z = false;
                }
            }
        }
        return z;
    }

    private boolean returnFalse(byte[] bArr) {
        this.messageLength = 0;
        clearBlock(this.mBuf);
        clearBlock(bArr);
        return false;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        int i;
        int i2;
        byte b;
        int i3;
        byte[] bArr;
        int digestSize = this.digest.getDigestSize();
        if (this.trailer == 188) {
            byte[] bArr2 = this.block;
            i2 = (bArr2.length - digestSize) - 1;
            this.digest.doFinal(bArr2, i2);
            byte[] bArr3 = this.block;
            bArr3[bArr3.length - 1] = -68;
            i = 8;
        } else {
            i = 16;
            byte[] bArr4 = this.block;
            int length = (bArr4.length - digestSize) - 2;
            this.digest.doFinal(bArr4, length);
            byte[] bArr5 = this.block;
            int i4 = this.trailer;
            bArr5[bArr5.length - 2] = (byte) (i4 >>> 8);
            bArr5[bArr5.length - 1] = (byte) i4;
            i2 = length;
        }
        int i5 = this.messageLength;
        int i6 = ((((digestSize + i5) * 8) + i) + 4) - this.keyBits;
        if (i6 > 0) {
            int i7 = i5 - ((i6 + 7) / 8);
            b = 96;
            i3 = i2 - i7;
            System.arraycopy(this.mBuf, 0, this.block, i3, i7);
            bArr = new byte[i7];
        } else {
            b = 64;
            i3 = i2 - i5;
            System.arraycopy(this.mBuf, 0, this.block, i3, i5);
            bArr = new byte[this.messageLength];
        }
        this.recoveredMessage = bArr;
        int i8 = i3 - 1;
        if (i8 > 0) {
            for (int i9 = i8; i9 != 0; i9--) {
                this.block[i9] = -69;
            }
            byte[] bArr6 = this.block;
            bArr6[i8] = (byte) (bArr6[i8] ^ 1);
            bArr6[0] = 11;
            bArr6[0] = (byte) (bArr6[0] | b);
        } else {
            byte[] bArr7 = this.block;
            bArr7[0] = 10;
            bArr7[0] = (byte) (bArr7[0] | b);
        }
        AsymmetricBlockCipher asymmetricBlockCipher = this.cipher;
        byte[] bArr8 = this.block;
        byte[] processBlock = asymmetricBlockCipher.processBlock(bArr8, 0, bArr8.length);
        this.fullMessage = (b & 32) == 0;
        byte[] bArr9 = this.mBuf;
        byte[] bArr10 = this.recoveredMessage;
        System.arraycopy(bArr9, 0, bArr10, 0, bArr10.length);
        this.messageLength = 0;
        clearBlock(this.mBuf);
        clearBlock(this.block);
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public byte[] getRecoveredMessage() {
        return this.recoveredMessage;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public boolean hasFullMessage() {
        return this.fullMessage;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        this.cipher.init(z, rSAKeyParameters);
        this.keyBits = rSAKeyParameters.getModulus().bitLength();
        this.block = new byte[(this.keyBits + 7) / 8];
        this.mBuf = new byte[this.trailer == 188 ? (this.block.length - this.digest.getDigestSize()) - 2 : (this.block.length - this.digest.getDigestSize()) - 3];
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
        this.messageLength = 0;
        clearBlock(this.mBuf);
        byte[] bArr = this.recoveredMessage;
        if (bArr != null) {
            clearBlock(bArr);
        }
        this.recoveredMessage = null;
        this.fullMessage = false;
        if (this.preSig != null) {
            this.preSig = null;
            clearBlock(this.preBlock);
            this.preBlock = null;
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.digest.update(b);
        int i = this.messageLength;
        byte[] bArr = this.mBuf;
        if (i < bArr.length) {
            bArr[i] = b;
        }
        this.messageLength++;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0 && this.messageLength < this.mBuf.length) {
            update(bArr[i]);
            i++;
            i2--;
        }
        this.digest.update(bArr, i, i2);
        this.messageLength += i2;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public void updateWithRecoveredMessage(byte[] bArr) throws InvalidCipherTextException {
        byte[] bArr2;
        int length;
        byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
        if (((processBlock[0] & 192) ^ 64) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        if (((processBlock[processBlock.length - 1] & 15) ^ 12) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        int i = 2;
        if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
            i = 1;
        } else {
            int i2 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
            Integer trailer = ISOTrailers.getTrailer(this.digest);
            if (trailer == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            int intValue = trailer.intValue();
            if (i2 != intValue && (intValue != 15052 || i2 != 16588)) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
            }
        }
        int i3 = 0;
        while (i3 != processBlock.length && ((processBlock[i3] & 15) ^ 10) != 0) {
            i3++;
        }
        int i4 = i3 + 1;
        int length2 = ((processBlock.length - i) - this.digest.getDigestSize()) - i4;
        if (length2 <= 0) {
            throw new InvalidCipherTextException("malformed block");
        }
        if ((processBlock[0] & 32) == 0) {
            this.fullMessage = true;
            this.recoveredMessage = new byte[length2];
            bArr2 = this.recoveredMessage;
            length = bArr2.length;
        } else {
            this.fullMessage = false;
            this.recoveredMessage = new byte[length2];
            bArr2 = this.recoveredMessage;
            length = bArr2.length;
        }
        System.arraycopy(processBlock, i4, bArr2, 0, length);
        this.preSig = bArr;
        this.preBlock = processBlock;
        Digest digest = this.digest;
        byte[] bArr3 = this.recoveredMessage;
        digest.update(bArr3, 0, bArr3.length);
        byte[] bArr4 = this.recoveredMessage;
        this.messageLength = bArr4.length;
        System.arraycopy(bArr4, 0, this.mBuf, 0, bArr4.length);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] processBlock;
        byte[] bArr2;
        int length;
        byte[] bArr3 = this.preSig;
        if (bArr3 == null) {
            try {
                processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
            } catch (Exception unused) {
                return false;
            }
        } else if (!Arrays.areEqual(bArr3, bArr)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        } else {
            processBlock = this.preBlock;
            this.preSig = null;
            this.preBlock = null;
        }
        if (((processBlock[0] & 192) ^ 64) == 0 && ((processBlock[processBlock.length - 1] & 15) ^ 12) == 0) {
            int i = 2;
            if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
                i = 1;
            } else {
                int i2 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
                Integer trailer = ISOTrailers.getTrailer(this.digest);
                if (trailer == null) {
                    throw new IllegalArgumentException("unrecognised hash in signature");
                }
                int intValue = trailer.intValue();
                if (i2 != intValue && (intValue != 15052 || i2 != 16588)) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
                }
            }
            int i3 = 0;
            while (i3 != processBlock.length && ((processBlock[i3] & 15) ^ 10) != 0) {
                i3++;
            }
            int i4 = i3 + 1;
            byte[] bArr4 = new byte[this.digest.getDigestSize()];
            int length2 = (processBlock.length - i) - bArr4.length;
            int i5 = length2 - i4;
            if (i5 <= 0) {
                return returnFalse(processBlock);
            }
            if ((processBlock[0] & 32) == 0) {
                this.fullMessage = true;
                if (this.messageLength > i5) {
                    return returnFalse(processBlock);
                }
                this.digest.reset();
                this.digest.update(processBlock, i4, i5);
                this.digest.doFinal(bArr4, 0);
                boolean z = true;
                for (int i6 = 0; i6 != bArr4.length; i6++) {
                    int i7 = length2 + i6;
                    processBlock[i7] = (byte) (processBlock[i7] ^ bArr4[i6]);
                    if (processBlock[i7] != 0) {
                        z = false;
                    }
                }
                if (!z) {
                    return returnFalse(processBlock);
                }
                this.recoveredMessage = new byte[i5];
                bArr2 = this.recoveredMessage;
                length = bArr2.length;
            } else {
                this.fullMessage = false;
                this.digest.doFinal(bArr4, 0);
                boolean z2 = true;
                for (int i8 = 0; i8 != bArr4.length; i8++) {
                    int i9 = length2 + i8;
                    processBlock[i9] = (byte) (processBlock[i9] ^ bArr4[i8]);
                    if (processBlock[i9] != 0) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    return returnFalse(processBlock);
                }
                this.recoveredMessage = new byte[i5];
                bArr2 = this.recoveredMessage;
                length = bArr2.length;
            }
            System.arraycopy(processBlock, i4, bArr2, 0, length);
            if (this.messageLength == 0 || isSameAs(this.mBuf, this.recoveredMessage)) {
                clearBlock(this.mBuf);
                clearBlock(processBlock);
                this.messageLength = 0;
                return true;
            }
            return returnFalse(processBlock);
        }
        return returnFalse(processBlock);
    }
}
