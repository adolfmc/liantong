package org.bouncycastle.jce.provider;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class OldPKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;

    /* renamed from: u */
    private int f26909u;

    /* renamed from: v */
    private int f26910v;

    public OldPKCS12ParametersGenerator(Digest digest) {
        this.digest = digest;
        if (digest instanceof MD5Digest) {
            this.f26909u = 16;
        } else if (!(digest instanceof SHA1Digest) && !(digest instanceof RIPEMD160Digest)) {
            throw new IllegalArgumentException("Digest " + digest.getAlgorithmName() + " unsupported");
        } else {
            this.f26909u = 20;
        }
        this.f26910v = 64;
    }

    private void adjust(byte[] bArr, int i, byte[] bArr2) {
        int i2 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i) - 1] & 255) + 1;
        bArr[(bArr2.length + i) - 1] = (byte) i2;
        int i3 = i2 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i4 = i + length;
            int i5 = i3 + (bArr2[length] & 255) + (bArr[i4] & 255);
            bArr[i4] = (byte) i5;
            i3 = i5 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int i, int i2) {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3 = new byte[this.f26910v];
        byte[] bArr4 = new byte[i2];
        for (int i3 = 0; i3 != bArr3.length; i3++) {
            bArr3[i3] = (byte) i;
        }
        if (this.salt == null || this.salt.length == 0) {
            bArr = new byte[0];
        } else {
            int i4 = this.f26910v;
            int length = this.salt.length;
            int i5 = this.f26910v;
            bArr = new byte[i4 * (((length + i5) - 1) / i5)];
            for (int i6 = 0; i6 != bArr.length; i6++) {
                bArr[i6] = this.salt[i6 % this.salt.length];
            }
        }
        if (this.password == null || this.password.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i7 = this.f26910v;
            int length2 = this.password.length;
            int i8 = this.f26910v;
            bArr2 = new byte[i7 * (((length2 + i8) - 1) / i8)];
            for (int i9 = 0; i9 != bArr2.length; i9++) {
                bArr2[i9] = this.password[i9 % this.password.length];
            }
        }
        byte[] bArr5 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        byte[] bArr6 = new byte[this.f26910v];
        int i10 = this.f26909u;
        int i11 = ((i2 + i10) - 1) / i10;
        for (int i12 = 1; i12 <= i11; i12++) {
            byte[] bArr7 = new byte[this.f26909u];
            this.digest.update(bArr3, 0, bArr3.length);
            this.digest.update(bArr5, 0, bArr5.length);
            this.digest.doFinal(bArr7, 0);
            for (int i13 = 1; i13 != this.iterationCount; i13++) {
                this.digest.update(bArr7, 0, bArr7.length);
                this.digest.doFinal(bArr7, 0);
            }
            for (int i14 = 0; i14 != bArr6.length; i14++) {
                bArr6[i12] = bArr7[i14 % bArr7.length];
            }
            int i15 = 0;
            while (true) {
                int length3 = bArr5.length;
                int i16 = this.f26910v;
                if (i15 == length3 / i16) {
                    break;
                }
                adjust(bArr5, i16 * i15, bArr6);
                i15++;
            }
            if (i12 == i11) {
                int i17 = i12 - 1;
                int i18 = this.f26909u;
                System.arraycopy(bArr7, 0, bArr4, i17 * i18, bArr4.length - (i17 * i18));
            } else {
                System.arraycopy(bArr7, 0, bArr4, (i12 - 1) * this.f26909u, bArr7.length);
            }
        }
        return bArr4;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(3, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(1, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] generateDerivedKey = generateDerivedKey(1, i3);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, i3), generateDerivedKey(2, i4), 0, i4);
    }
}
