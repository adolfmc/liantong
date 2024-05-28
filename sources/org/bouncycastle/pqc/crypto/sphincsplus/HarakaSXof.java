package org.bouncycastle.pqc.crypto.sphincsplus;

import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class HarakaSXof extends HarakaSBase {
    public HarakaSXof(byte[] bArr) {
        byte[] bArr2 = new byte[640];
        update(bArr, 0, bArr.length);
        doFinal(bArr2, 0, bArr2.length);
        this.haraka512_rc = (long[][]) Array.newInstance(long.class, 10, 8);
        this.haraka256_rc = (int[][]) Array.newInstance(int.class, 10, 8);
        for (int i = 0; i < 10; i++) {
            interleaveConstant32(this.haraka256_rc[i], bArr2, i << 5);
            interleaveConstant(this.haraka512_rc[i], bArr2, i << 6);
        }
    }

    public int doFinal(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2 = this.buffer;
        int i4 = this.off;
        bArr2[i4] = (byte) (bArr2[i4] ^ 31);
        byte[] bArr3 = this.buffer;
        bArr3[31] = (byte) (bArr3[31] ^ 128);
        this.off = 0;
        int i5 = i;
        int i6 = i2;
        while (i6 > 0) {
            haraka512Perm(this.buffer);
            int i7 = 0;
            while (i7 < 32 && (i3 = i7 + i5) < bArr.length) {
                bArr[i3] = this.buffer[i7];
                i7++;
            }
            i5 += i7;
            i6 -= i7;
        }
        if (i6 != 0) {
            byte[] bArr4 = new byte[64];
            haraka512Perm(bArr4);
            System.arraycopy(bArr4, 0, bArr, i5, -i6);
        }
        reset();
        return i2;
    }

    public String getAlgorithmName() {
        return "Haraka-S";
    }

    public void update(byte b) {
        byte[] bArr = this.buffer;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = (byte) (b ^ bArr[i]);
        if (this.off == 32) {
            haraka512Perm(this.buffer);
            this.off = 0;
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        int i3 = (this.off + i2) >> 5;
        int i4 = i;
        for (int i5 = 0; i5 < i3; i5++) {
            while (this.off < 32) {
                byte[] bArr2 = this.buffer;
                int i6 = this.off;
                this.off = i6 + 1;
                bArr2[i6] = (byte) (bArr[i4] ^ bArr2[i6]);
                i4++;
            }
            haraka512Perm(this.buffer);
            this.off = 0;
        }
        while (i4 < i + i2) {
            byte[] bArr3 = this.buffer;
            int i7 = this.off;
            this.off = i7 + 1;
            bArr3[i7] = (byte) (bArr3[i7] ^ bArr[i4]);
            i4++;
        }
    }
}
