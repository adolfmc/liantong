package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PSSSigner implements Signer {
    public static final byte TRAILER_IMPLICIT = -68;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest contentDigest1;
    private Digest contentDigest2;
    private int emBits;
    private int hLen;
    private byte[] mDash;
    private Digest mgfDigest;
    private int mgfhLen;
    private SecureRandom random;
    private int sLen;
    private boolean sSet;
    private byte[] salt;
    private byte trailer;

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i) {
        this(asymmetricBlockCipher, digest, i, (byte) -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i, byte b) {
        this(asymmetricBlockCipher, digest, digest, i, b);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i) {
        this(asymmetricBlockCipher, digest, digest2, i, (byte) -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i, byte b) {
        this(asymmetricBlockCipher, digest, digest, digest2, i, b);
    }

    private PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, Digest digest3, int i, byte b) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest1 = digest;
        this.contentDigest2 = digest2;
        this.mgfDigest = digest3;
        this.hLen = digest2.getDigestSize();
        this.mgfhLen = digest3.getDigestSize();
        this.sSet = false;
        this.sLen = i;
        this.salt = new byte[i];
        this.mDash = new byte[i + 8 + this.hLen];
        this.trailer = b;
    }

    private PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, Digest digest3, byte[] bArr, byte b) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest1 = digest;
        this.contentDigest2 = digest2;
        this.mgfDigest = digest3;
        this.hLen = digest2.getDigestSize();
        this.mgfhLen = digest3.getDigestSize();
        this.sSet = true;
        this.sLen = bArr.length;
        this.salt = bArr;
        this.mDash = new byte[this.sLen + 8 + this.hLen];
        this.trailer = b;
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest2, bArr, (byte) -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr, byte b) {
        this(asymmetricBlockCipher, digest, digest, digest2, bArr, b);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest, bArr, (byte) -68);
    }

    private void ItoOSP(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    private void clearBlock(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    public static PSSSigner createRawSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i, byte b) {
        return new PSSSigner(asymmetricBlockCipher, new NullDigest(), digest, digest2, i, b);
    }

    public static PSSSigner createRawSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr, byte b) {
        return new PSSSigner(asymmetricBlockCipher, new NullDigest(), digest, digest2, bArr, b);
    }

    private byte[] maskGenerator(byte[] bArr, int i, int i2, int i3) {
        Digest digest = this.mgfDigest;
        if (digest instanceof Xof) {
            byte[] bArr2 = new byte[i3];
            digest.update(bArr, i, i2);
            ((Xof) this.mgfDigest).doFinal(bArr2, 0, bArr2.length);
            return bArr2;
        }
        return maskGeneratorFunction1(bArr, i, i2, i3);
    }

    private byte[] maskGeneratorFunction1(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[this.mgfhLen];
        byte[] bArr4 = new byte[4];
        this.mgfDigest.reset();
        int i5 = 0;
        while (true) {
            i4 = this.mgfhLen;
            if (i5 >= i3 / i4) {
                break;
            }
            ItoOSP(i5, bArr4);
            this.mgfDigest.update(bArr, i, i2);
            this.mgfDigest.update(bArr4, 0, bArr4.length);
            this.mgfDigest.doFinal(bArr3, 0);
            int i6 = this.mgfhLen;
            System.arraycopy(bArr3, 0, bArr2, i5 * i6, i6);
            i5++;
        }
        if (i4 * i5 < i3) {
            ItoOSP(i5, bArr4);
            this.mgfDigest.update(bArr, i, i2);
            this.mgfDigest.update(bArr4, 0, bArr4.length);
            this.mgfDigest.doFinal(bArr3, 0);
            int i7 = this.mgfhLen;
            System.arraycopy(bArr3, 0, bArr2, i5 * i7, bArr2.length - (i5 * i7));
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        int digestSize = this.contentDigest1.getDigestSize();
        int i = this.hLen;
        if (digestSize == i) {
            Digest digest = this.contentDigest1;
            byte[] bArr = this.mDash;
            digest.doFinal(bArr, (bArr.length - i) - this.sLen);
            if (this.sLen != 0) {
                if (!this.sSet) {
                    this.random.nextBytes(this.salt);
                }
                byte[] bArr2 = this.salt;
                byte[] bArr3 = this.mDash;
                int length = bArr3.length;
                int i2 = this.sLen;
                System.arraycopy(bArr2, 0, bArr3, length - i2, i2);
            }
            byte[] bArr4 = new byte[this.hLen];
            Digest digest2 = this.contentDigest2;
            byte[] bArr5 = this.mDash;
            digest2.update(bArr5, 0, bArr5.length);
            this.contentDigest2.doFinal(bArr4, 0);
            byte[] bArr6 = this.block;
            int length2 = bArr6.length;
            int i3 = this.sLen;
            int i4 = this.hLen;
            bArr6[(((length2 - i3) - 1) - i4) - 1] = 1;
            System.arraycopy(this.salt, 0, bArr6, ((bArr6.length - i3) - i4) - 1, i3);
            byte[] maskGenerator = maskGenerator(bArr4, 0, bArr4.length, (this.block.length - this.hLen) - 1);
            for (int i5 = 0; i5 != maskGenerator.length; i5++) {
                byte[] bArr7 = this.block;
                bArr7[i5] = (byte) (bArr7[i5] ^ maskGenerator[i5]);
            }
            byte[] bArr8 = this.block;
            int length3 = bArr8.length;
            int i6 = this.hLen;
            System.arraycopy(bArr4, 0, bArr8, (length3 - i6) - 1, i6);
            byte[] bArr9 = this.block;
            bArr9[0] = (byte) ((255 >>> ((bArr9.length * 8) - this.emBits)) & bArr9[0]);
            bArr9[bArr9.length - 1] = this.trailer;
            byte[] processBlock = this.cipher.processBlock(bArr9, 0, bArr9.length);
            clearBlock(this.block);
            return processBlock;
        }
        throw new IllegalStateException();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        RSAKeyParameters rSAKeyParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            cipherParameters2 = parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            if (z) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof RSABlindingParameters) {
            rSAKeyParameters = ((RSABlindingParameters) cipherParameters2).getPublicKey();
            this.cipher.init(z, cipherParameters);
        } else {
            rSAKeyParameters = (RSAKeyParameters) cipherParameters2;
            this.cipher.init(z, cipherParameters2);
        }
        this.emBits = rSAKeyParameters.getModulus().bitLength() - 1;
        int i = this.emBits;
        if (i < (this.hLen * 8) + (this.sLen * 8) + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.block = new byte[(i + 7) / 8];
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.contentDigest1.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.contentDigest1.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.contentDigest1.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        int digestSize = this.contentDigest1.getDigestSize();
        int i = this.hLen;
        if (digestSize == i) {
            Digest digest = this.contentDigest1;
            byte[] bArr2 = this.mDash;
            digest.doFinal(bArr2, (bArr2.length - i) - this.sLen);
            try {
                byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
                Arrays.fill(this.block, 0, this.block.length - processBlock.length, (byte) 0);
                System.arraycopy(processBlock, 0, this.block, this.block.length - processBlock.length, processBlock.length);
                byte[] bArr3 = this.block;
                int length = 255 >>> ((bArr3.length * 8) - this.emBits);
                if ((255 & bArr3[0]) == (bArr3[0] & length) && bArr3[bArr3.length - 1] == this.trailer) {
                    int length2 = bArr3.length;
                    int i2 = this.hLen;
                    byte[] maskGenerator = maskGenerator(bArr3, (length2 - i2) - 1, i2, (bArr3.length - i2) - 1);
                    for (int i3 = 0; i3 != maskGenerator.length; i3++) {
                        byte[] bArr4 = this.block;
                        bArr4[i3] = (byte) (bArr4[i3] ^ maskGenerator[i3]);
                    }
                    byte[] bArr5 = this.block;
                    bArr5[0] = (byte) (length & bArr5[0]);
                    int i4 = 0;
                    while (true) {
                        byte[] bArr6 = this.block;
                        int length3 = bArr6.length;
                        int i5 = this.hLen;
                        int i6 = this.sLen;
                        if (i4 != ((length3 - i5) - i6) - 2) {
                            if (bArr6[i4] != 0) {
                                clearBlock(bArr6);
                                return false;
                            }
                            i4++;
                        } else if (bArr6[((bArr6.length - i5) - i6) - 2] != 1) {
                            clearBlock(bArr6);
                            return false;
                        } else {
                            if (this.sSet) {
                                byte[] bArr7 = this.salt;
                                byte[] bArr8 = this.mDash;
                                System.arraycopy(bArr7, 0, bArr8, bArr8.length - i6, i6);
                            } else {
                                byte[] bArr9 = this.mDash;
                                System.arraycopy(bArr6, ((bArr6.length - i6) - i5) - 1, bArr9, bArr9.length - i6, i6);
                            }
                            Digest digest2 = this.contentDigest2;
                            byte[] bArr10 = this.mDash;
                            digest2.update(bArr10, 0, bArr10.length);
                            Digest digest3 = this.contentDigest2;
                            byte[] bArr11 = this.mDash;
                            digest3.doFinal(bArr11, bArr11.length - this.hLen);
                            int length4 = this.block.length;
                            int i7 = this.hLen;
                            int i8 = (length4 - i7) - 1;
                            int length5 = this.mDash.length - i7;
                            while (true) {
                                byte[] bArr12 = this.mDash;
                                if (length5 == bArr12.length) {
                                    clearBlock(bArr12);
                                    clearBlock(this.block);
                                    return true;
                                } else if ((this.block[i8] ^ bArr12[length5]) != 0) {
                                    clearBlock(bArr12);
                                    break;
                                } else {
                                    i8++;
                                    length5++;
                                }
                            }
                        }
                    }
                }
                clearBlock(this.block);
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
        throw new IllegalStateException();
    }
}
