package org.bouncycastle.pqc.crypto.frodo;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class FrodoMatrixGenerator {

    /* renamed from: n */
    int f27162n;

    /* renamed from: q */
    int f27163q;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Aes128MatrixGenerator extends FrodoMatrixGenerator {
        BufferedBlockCipher cipher;

        public Aes128MatrixGenerator(int i, int i2) {
            super(i, i2);
            this.cipher = new BufferedBlockCipher(new AESEngine());
        }

        void aes128(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            try {
                this.cipher.init(true, new KeyParameter(bArr2));
                this.cipher.doFinal(bArr, this.cipher.processBytes(bArr3, 0, bArr3.length, bArr, 0));
            } catch (InvalidCipherTextException e) {
                throw Exceptions.illegalStateException(e.toString(), e);
            }
        }

        @Override // org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator
        short[] genMatrix(byte[] bArr) {
            short[] sArr = new short[this.f27162n * this.f27162n];
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            for (int i = 0; i < this.f27162n; i++) {
                for (int i2 = 0; i2 < this.f27162n; i2 += 8) {
                    System.arraycopy(Pack.shortToLittleEndian((short) (i & 65535)), 0, bArr2, 0, 2);
                    System.arraycopy(Pack.shortToLittleEndian((short) (65535 & i2)), 0, bArr2, 2, 2);
                    aes128(bArr3, bArr, bArr2);
                    for (int i3 = 0; i3 < 8; i3++) {
                        sArr[(this.f27162n * i) + i2 + i3] = (short) (Pack.littleEndianToShort(bArr3, i3 * 2) % this.f27163q);
                    }
                }
            }
            return sArr;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Shake128MatrixGenerator extends FrodoMatrixGenerator {
        public Shake128MatrixGenerator(int i, int i2) {
            super(i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator
        short[] genMatrix(byte[] bArr) {
            short[] sArr = new short[this.f27162n * this.f27162n];
            byte[] bArr2 = new byte[(this.f27162n * 16) / 8];
            for (short s = 0; s < this.f27162n; s = (short) (s + 1)) {
                byte[] concatenate = Arrays.concatenate(Pack.shortToLittleEndian(s), bArr);
                SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
                sHAKEDigest.update(concatenate, 0, concatenate.length);
                sHAKEDigest.doFinal(bArr2, 0, bArr2.length);
                for (short s2 = 0; s2 < this.f27162n; s2 = (short) (s2 + 1)) {
                    sArr[(this.f27162n * s) + s2] = (short) (Pack.littleEndianToShort(bArr2, s2 * 2) % this.f27163q);
                }
            }
            return sArr;
        }
    }

    public FrodoMatrixGenerator(int i, int i2) {
        this.f27162n = i;
        this.f27163q = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract short[] genMatrix(byte[] bArr);
}
