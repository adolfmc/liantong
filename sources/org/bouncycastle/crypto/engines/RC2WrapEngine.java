package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RC2WrapEngine implements Wrapper {
    private static final byte[] IV2 = {74, -35, -94, 44, 121, -24, 33, 5};
    private CBCBlockCipher engine;
    private boolean forWrapping;

    /* renamed from: iv */
    private byte[] f26627iv;
    private CipherParameters param;
    private ParametersWithIV paramPlusIV;

    /* renamed from: sr */
    private SecureRandom f26628sr;
    Digest sha1 = DigestFactory.createSHA1();
    byte[] digest = new byte[20];

    private byte[] calculateCMSKeyChecksum(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.sha1.update(bArr, 0, bArr.length);
        this.sha1.doFinal(this.digest, 0);
        System.arraycopy(this.digest, 0, bArr2, 0, 8);
        return bArr2;
    }

    private boolean checkCMSKeyChecksum(byte[] bArr, byte[] bArr2) {
        return Arrays.constantTimeAreEqual(calculateCMSKeyChecksum(bArr), bArr2);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forWrapping = z;
        this.engine = new CBCBlockCipher(new RC2Engine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f26628sr = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.f26628sr = CryptoServicesRegistrar.getSecureRandom();
        }
        if (!(cipherParameters instanceof ParametersWithIV)) {
            this.param = cipherParameters;
            if (this.forWrapping) {
                this.f26627iv = new byte[8];
                this.f26628sr.nextBytes(this.f26627iv);
                this.paramPlusIV = new ParametersWithIV(this.param, this.f26627iv);
                return;
            }
            return;
        }
        this.paramPlusIV = (ParametersWithIV) cipherParameters;
        this.f26627iv = this.paramPlusIV.getIV();
        this.param = this.paramPlusIV.getParameters();
        if (!this.forWrapping) {
            throw new IllegalArgumentException("You should not supply an IV for unwrapping");
        }
        byte[] bArr = this.f26627iv;
        if (bArr == null || bArr.length != 8) {
            throw new IllegalArgumentException("IV is not 8 octets");
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr != null) {
            if (i2 % this.engine.getBlockSize() != 0) {
                throw new InvalidCipherTextException("Ciphertext not multiple of " + this.engine.getBlockSize());
            }
            this.engine.init(false, new ParametersWithIV(this.param, IV2));
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            for (int i3 = 0; i3 < bArr2.length / this.engine.getBlockSize(); i3++) {
                int blockSize = this.engine.getBlockSize() * i3;
                this.engine.processBlock(bArr2, blockSize, bArr2, blockSize);
            }
            byte[] bArr3 = new byte[bArr2.length];
            int i4 = 0;
            while (i4 < bArr2.length) {
                int i5 = i4 + 1;
                bArr3[i4] = bArr2[bArr2.length - i5];
                i4 = i5;
            }
            this.f26627iv = new byte[8];
            byte[] bArr4 = new byte[bArr3.length - 8];
            System.arraycopy(bArr3, 0, this.f26627iv, 0, 8);
            System.arraycopy(bArr3, 8, bArr4, 0, bArr3.length - 8);
            this.paramPlusIV = new ParametersWithIV(this.param, this.f26627iv);
            this.engine.init(false, this.paramPlusIV);
            byte[] bArr5 = new byte[bArr4.length];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
            for (int i6 = 0; i6 < bArr5.length / this.engine.getBlockSize(); i6++) {
                int blockSize2 = this.engine.getBlockSize() * i6;
                this.engine.processBlock(bArr5, blockSize2, bArr5, blockSize2);
            }
            byte[] bArr6 = new byte[bArr5.length - 8];
            byte[] bArr7 = new byte[8];
            System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length - 8);
            System.arraycopy(bArr5, bArr5.length - 8, bArr7, 0, 8);
            if (checkCMSKeyChecksum(bArr6, bArr7)) {
                if (bArr6.length - ((bArr6[0] & 255) + 1) <= 7) {
                    byte[] bArr8 = new byte[bArr6[0]];
                    System.arraycopy(bArr6, 1, bArr8, 0, bArr8.length);
                    return bArr8;
                }
                throw new InvalidCipherTextException("too many pad bytes (" + (bArr6.length - ((bArr6[0] & 255) + 1)) + ")");
            }
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        throw new InvalidCipherTextException("Null pointer as ciphertext");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (this.forWrapping) {
            int i3 = i2 + 1;
            int i4 = i3 % 8;
            byte[] bArr2 = new byte[i4 != 0 ? (8 - i4) + i3 : i3];
            bArr2[0] = (byte) i2;
            System.arraycopy(bArr, i, bArr2, 1, i2);
            byte[] bArr3 = new byte[(bArr2.length - i2) - 1];
            if (bArr3.length > 0) {
                this.f26628sr.nextBytes(bArr3);
                System.arraycopy(bArr3, 0, bArr2, i3, bArr3.length);
            }
            byte[] calculateCMSKeyChecksum = calculateCMSKeyChecksum(bArr2);
            byte[] bArr4 = new byte[bArr2.length + calculateCMSKeyChecksum.length];
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
            System.arraycopy(calculateCMSKeyChecksum, 0, bArr4, bArr2.length, calculateCMSKeyChecksum.length);
            byte[] bArr5 = new byte[bArr4.length];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
            int length = bArr4.length / this.engine.getBlockSize();
            if (bArr4.length % this.engine.getBlockSize() == 0) {
                this.engine.init(true, this.paramPlusIV);
                for (int i5 = 0; i5 < length; i5++) {
                    int blockSize = this.engine.getBlockSize() * i5;
                    this.engine.processBlock(bArr5, blockSize, bArr5, blockSize);
                }
                byte[] bArr6 = this.f26627iv;
                byte[] bArr7 = new byte[bArr6.length + bArr5.length];
                System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
                System.arraycopy(bArr5, 0, bArr7, this.f26627iv.length, bArr5.length);
                byte[] bArr8 = new byte[bArr7.length];
                int i6 = 0;
                while (i6 < bArr7.length) {
                    int i7 = i6 + 1;
                    bArr8[i6] = bArr7[bArr7.length - i7];
                    i6 = i7;
                }
                this.engine.init(true, new ParametersWithIV(this.param, IV2));
                for (int i8 = 0; i8 < length + 1; i8++) {
                    int blockSize2 = this.engine.getBlockSize() * i8;
                    this.engine.processBlock(bArr8, blockSize2, bArr8, blockSize2);
                }
                return bArr8;
            }
            throw new IllegalStateException("Not multiple of block length");
        }
        throw new IllegalStateException("Not initialized for wrapping");
    }
}
