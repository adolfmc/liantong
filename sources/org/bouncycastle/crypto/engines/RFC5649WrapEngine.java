package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RFC5649WrapEngine implements Wrapper {
    private BlockCipher engine;
    private boolean forWrapping;
    private KeyParameter param;
    private byte[] highOrderIV = {-90, 89, 89, -90};
    private byte[] preIV = this.highOrderIV;
    private byte[] extractedAIV = null;

    public RFC5649WrapEngine(BlockCipher blockCipher) {
        this.engine = blockCipher;
    }

    private byte[] padPlaintext(byte[] bArr) {
        int length = bArr.length;
        int i = (8 - (length % 8)) % 8;
        byte[] bArr2 = new byte[length + i];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        if (i != 0) {
            System.arraycopy(new byte[i], 0, bArr2, length, i);
        }
        return bArr2;
    }

    private byte[] rfc3394UnwrapNoIvCheck(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[8];
        byte[] bArr3 = new byte[i2 - bArr2.length];
        byte[] bArr4 = new byte[bArr2.length];
        byte[] bArr5 = new byte[bArr2.length + 8];
        System.arraycopy(bArr, i, bArr4, 0, bArr2.length);
        System.arraycopy(bArr, i + bArr2.length, bArr3, 0, i2 - bArr2.length);
        this.engine.init(false, this.param);
        int i3 = (i2 / 8) - 1;
        for (int i4 = 5; i4 >= 0; i4--) {
            for (int i5 = i3; i5 >= 1; i5--) {
                System.arraycopy(bArr4, 0, bArr5, 0, bArr2.length);
                int i6 = (i5 - 1) * 8;
                System.arraycopy(bArr3, i6, bArr5, bArr2.length, 8);
                int i7 = (i3 * i4) + i5;
                int i8 = 1;
                while (i7 != 0) {
                    int length = bArr2.length - i8;
                    bArr5[length] = (byte) (bArr5[length] ^ ((byte) i7));
                    i7 >>>= 8;
                    i8++;
                }
                this.engine.processBlock(bArr5, 0, bArr5, 0);
                System.arraycopy(bArr5, 0, bArr4, 0, 8);
                System.arraycopy(bArr5, 8, bArr3, i6, 8);
            }
        }
        this.extractedAIV = bArr4;
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forWrapping = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.param = (KeyParameter) cipherParameters;
            this.preIV = this.highOrderIV;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.preIV = parametersWithIV.getIV();
            this.param = (KeyParameter) parametersWithIV.getParameters();
            if (this.preIV.length != 4) {
                throw new IllegalArgumentException("IV length not equal to 4");
            }
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] rfc3394UnwrapNoIvCheck;
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i3 = i2 / 8;
        if (i3 * 8 == i2) {
            if (i3 > 1) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                byte[] bArr3 = new byte[i2];
                if (i3 == 2) {
                    this.engine.init(false, this.param);
                    int i4 = 0;
                    while (i4 < bArr2.length) {
                        this.engine.processBlock(bArr2, i4, bArr3, i4);
                        i4 += this.engine.getBlockSize();
                    }
                    this.extractedAIV = new byte[8];
                    byte[] bArr4 = this.extractedAIV;
                    System.arraycopy(bArr3, 0, bArr4, 0, bArr4.length);
                    int length = bArr3.length;
                    byte[] bArr5 = this.extractedAIV;
                    rfc3394UnwrapNoIvCheck = new byte[length - bArr5.length];
                    System.arraycopy(bArr3, bArr5.length, rfc3394UnwrapNoIvCheck, 0, rfc3394UnwrapNoIvCheck.length);
                } else {
                    rfc3394UnwrapNoIvCheck = rfc3394UnwrapNoIvCheck(bArr, i, i2);
                }
                int i5 = 4;
                byte[] bArr6 = new byte[4];
                byte[] bArr7 = new byte[4];
                System.arraycopy(this.extractedAIV, 0, bArr6, 0, bArr6.length);
                System.arraycopy(this.extractedAIV, bArr6.length, bArr7, 0, bArr7.length);
                int bigEndianToInt = Pack.bigEndianToInt(bArr7, 0);
                boolean constantTimeAreEqual = Arrays.constantTimeAreEqual(bArr6, this.preIV);
                int length2 = rfc3394UnwrapNoIvCheck.length;
                if (bigEndianToInt <= length2 - 8) {
                    constantTimeAreEqual = false;
                }
                if (bigEndianToInt > length2) {
                    constantTimeAreEqual = false;
                }
                int i6 = length2 - bigEndianToInt;
                if (i6 >= 8 || i6 < 0) {
                    constantTimeAreEqual = false;
                } else {
                    i5 = i6;
                }
                byte[] bArr8 = new byte[i5];
                System.arraycopy(rfc3394UnwrapNoIvCheck, rfc3394UnwrapNoIvCheck.length - i5, bArr8, 0, i5);
                if (!Arrays.constantTimeAreEqual(bArr8, new byte[i5])) {
                    constantTimeAreEqual = false;
                }
                if (constantTimeAreEqual) {
                    byte[] bArr9 = new byte[bigEndianToInt];
                    System.arraycopy(rfc3394UnwrapNoIvCheck, 0, bArr9, 0, bArr9.length);
                    return bArr9;
                }
                throw new InvalidCipherTextException("checksum failed");
            }
            throw new InvalidCipherTextException("unwrap data must be at least 16 bytes");
        }
        throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (this.forWrapping) {
            byte[] bArr2 = new byte[8];
            byte[] intToBigEndian = Pack.intToBigEndian(i2);
            byte[] bArr3 = this.preIV;
            int i3 = 0;
            System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
            System.arraycopy(intToBigEndian, 0, bArr2, this.preIV.length, intToBigEndian.length);
            byte[] bArr4 = new byte[i2];
            System.arraycopy(bArr, i, bArr4, 0, i2);
            byte[] padPlaintext = padPlaintext(bArr4);
            if (padPlaintext.length != 8) {
                RFC3394WrapEngine rFC3394WrapEngine = new RFC3394WrapEngine(this.engine);
                rFC3394WrapEngine.init(true, new ParametersWithIV(this.param, bArr2));
                return rFC3394WrapEngine.wrap(padPlaintext, 0, padPlaintext.length);
            }
            byte[] bArr5 = new byte[padPlaintext.length + bArr2.length];
            System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
            System.arraycopy(padPlaintext, 0, bArr5, bArr2.length, padPlaintext.length);
            this.engine.init(true, this.param);
            while (i3 < bArr5.length) {
                this.engine.processBlock(bArr5, i3, bArr5, i3);
                i3 += this.engine.getBlockSize();
            }
            return bArr5;
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
