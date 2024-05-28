package org.bouncycastle.pqc.legacy.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class WinternitzOTSignature {
    private int checksumsize;
    private GMSSRandom gmssRandom;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[][] privateKeyOTS;

    /* renamed from: w */
    private int f27302w;

    public WinternitzOTSignature(byte[] bArr, Digest digest, int i) {
        this.f27302w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        this.messagesize = (((this.mdsize << 3) + i) - 1) / i;
        this.checksumsize = getLog((this.messagesize << i) + 1);
        this.keysize = this.messagesize + (((this.checksumsize + i) - 1) / i);
        this.privateKeyOTS = new byte[this.keysize];
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        for (int i2 = 0; i2 < this.keysize; i2++) {
            this.privateKeyOTS[i2] = this.gmssRandom.nextSeed(bArr2);
        }
    }

    private void hashPrivateKeyBlock(int i, int i2, byte[] bArr, int i3) {
        if (i2 < 1) {
            System.arraycopy(this.privateKeyOTS[i], 0, bArr, i3, this.mdsize);
            return;
        }
        this.messDigestOTS.update(this.privateKeyOTS[i], 0, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr, i3);
            i2--;
            if (i2 <= 0) {
                return;
            }
            this.messDigestOTS.update(bArr, i3, this.mdsize);
        }
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public byte[][] getPrivateKey() {
        return this.privateKeyOTS;
    }

    public byte[] getPublicKey() {
        byte[] bArr = new byte[this.keysize * this.mdsize];
        int i = (1 << this.f27302w) - 1;
        int i2 = 0;
        for (int i3 = 0; i3 < this.keysize; i3++) {
            hashPrivateKeyBlock(i3, i, bArr, i2);
            i2 += this.mdsize;
        }
        this.messDigestOTS.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[this.mdsize];
        this.messDigestOTS.doFinal(bArr2, 0);
        return bArr2;
    }

    public byte[] getSignature(byte[] bArr) {
        int i;
        int i2 = this.keysize;
        int i3 = this.mdsize;
        byte[] bArr2 = new byte[i2 * i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        this.messDigestOTS.doFinal(bArr3, 0);
        int i5 = this.f27302w;
        if (8 % i5 == 0) {
            int i6 = 8 / i5;
            int i7 = (1 << i5) - 1;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i8 < bArr3.length) {
                int i11 = i9;
                for (int i12 = 0; i12 < i6; i12++) {
                    int i13 = bArr3[i8] & i7;
                    i11 += i13;
                    hashPrivateKeyBlock(i10, i13, bArr2, this.mdsize * i10);
                    bArr3[i8] = (byte) (bArr3[i8] >>> this.f27302w);
                    i10++;
                }
                i8++;
                i9 = i11;
            }
            int i14 = (this.messagesize << this.f27302w) - i9;
            while (i4 < this.checksumsize) {
                hashPrivateKeyBlock(i10, i14 & i7, bArr2, this.mdsize * i10);
                int i15 = this.f27302w;
                i14 >>>= i15;
                i10++;
                i4 += i15;
            }
        } else if (i5 < 8) {
            int i16 = this.mdsize / i5;
            int i17 = (1 << i5) - 1;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            while (i18 < i16) {
                int i22 = i19;
                long j = 0;
                for (int i23 = 0; i23 < this.f27302w; i23++) {
                    j ^= (bArr3[i22] & 255) << (i23 << 3);
                    i22++;
                }
                for (int i24 = 0; i24 < 8; i24++) {
                    int i25 = ((int) j) & i17;
                    i21 += i25;
                    hashPrivateKeyBlock(i20, i25, bArr2, this.mdsize * i20);
                    j >>>= this.f27302w;
                    i20++;
                }
                i18++;
                i19 = i22;
            }
            int i26 = this.mdsize % this.f27302w;
            long j2 = 0;
            for (int i27 = 0; i27 < i26; i27++) {
                j2 ^= (bArr3[i19] & 255) << (i27 << 3);
                i19++;
            }
            int i28 = i26 << 3;
            int i29 = 0;
            long j3 = j2;
            while (i29 < i28) {
                int i30 = ((int) j3) & i17;
                i21 += i30;
                hashPrivateKeyBlock(i20, i30, bArr2, this.mdsize * i20);
                int i31 = this.f27302w;
                j3 >>>= i31;
                i20++;
                i29 += i31;
            }
            int i32 = (this.messagesize << this.f27302w) - i21;
            while (i4 < this.checksumsize) {
                hashPrivateKeyBlock(i20, i32 & i17, bArr2, this.mdsize * i20);
                int i33 = this.f27302w;
                i32 >>>= i33;
                i20++;
                i4 += i33;
            }
        } else if (i5 < 57) {
            int i34 = this.mdsize;
            int i35 = (i34 << 3) - i5;
            int i36 = (1 << i5) - 1;
            byte[] bArr4 = new byte[i34];
            int i37 = 0;
            int i38 = 0;
            int i39 = 0;
            while (i37 <= i35) {
                int i40 = i37 % 8;
                i37 += this.f27302w;
                int i41 = 0;
                long j4 = 0;
                for (int i42 = i37 >>> 3; i42 < ((i37 + 7) >>> 3); i42++) {
                    j4 ^= (bArr3[i42] & 255) << (i41 << 3);
                    i41++;
                }
                long j5 = (j4 >>> i40) & i36;
                i39 = (int) (i39 + j5);
                System.arraycopy(this.privateKeyOTS[i38], 0, bArr4, 0, this.mdsize);
                while (j5 > 0) {
                    this.messDigestOTS.update(bArr4, 0, bArr4.length);
                    this.messDigestOTS.doFinal(bArr4, 0);
                    j5--;
                }
                int i43 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, i38 * i43, i43);
                i38++;
            }
            int i44 = i37 >>> 3;
            if (i44 < this.mdsize) {
                int i45 = i37 % 8;
                int i46 = 0;
                long j6 = 0;
                while (true) {
                    i = this.mdsize;
                    if (i44 >= i) {
                        break;
                    }
                    j6 ^= (bArr3[i44] & 255) << (i46 << 3);
                    i46++;
                    i44++;
                }
                long j7 = (j6 >>> i45) & i36;
                i39 = (int) (i39 + j7);
                System.arraycopy(this.privateKeyOTS[i38], 0, bArr4, 0, i);
                while (j7 > 0) {
                    this.messDigestOTS.update(bArr4, 0, bArr4.length);
                    this.messDigestOTS.doFinal(bArr4, 0);
                    j7--;
                }
                int i47 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, i38 * i47, i47);
                i38++;
            }
            int i48 = (this.messagesize << this.f27302w) - i39;
            int i49 = 0;
            while (i49 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i38], 0, bArr4, 0, this.mdsize);
                for (long j8 = i48 & i36; j8 > 0; j8--) {
                    this.messDigestOTS.update(bArr4, 0, bArr4.length);
                    this.messDigestOTS.doFinal(bArr4, 0);
                }
                int i50 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, i38 * i50, i50);
                int i51 = this.f27302w;
                i48 >>>= i51;
                i38++;
                i49 += i51;
            }
        }
        return bArr2;
    }
}
