package org.bouncycastle.pqc.crypto.saber;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Utils {
    private final int SABER_EP;
    private final int SABER_ET;
    private final int SABER_KEYBYTES;
    private final int SABER_L;
    private final int SABER_N;
    private final int SABER_POLYBYTES;

    public Utils(SABEREngine sABEREngine) {
        this.SABER_N = sABEREngine.getSABER_N();
        this.SABER_L = sABEREngine.getSABER_L();
        this.SABER_ET = sABEREngine.getSABER_ET();
        this.SABER_POLYBYTES = sABEREngine.getSABER_POLYBYTES();
        this.SABER_EP = sABEREngine.getSABER_EP();
        this.SABER_KEYBYTES = sABEREngine.getSABER_KEYBYTES();
    }

    private void BS2POLq(byte[] bArr, int i, short[] sArr) {
        for (short s = 0; s < this.SABER_N / 8; s = (short) (s + 1)) {
            short s2 = (short) (s * 8);
            int i2 = ((short) (s * 13)) + i;
            int i3 = i2 + 1;
            sArr[s2 + 0] = (short) ((bArr[i2 + 0] & 255) | ((bArr[i3] & 31) << 8));
            int i4 = ((bArr[i3] >> 5) & 7) | ((bArr[i2 + 2] & 255) << 3);
            int i5 = i2 + 3;
            sArr[s2 + 1] = (short) (i4 | ((bArr[i5] & 3) << 11));
            int i6 = i2 + 4;
            sArr[s2 + 2] = (short) (((bArr[i5] >> 2) & 63) | ((bArr[i6] & Byte.MAX_VALUE) << 6));
            int i7 = ((bArr[i6] >> 7) & 1) | ((bArr[i2 + 5] & 255) << 1);
            int i8 = i2 + 6;
            sArr[s2 + 3] = (short) (i7 | ((bArr[i8] & 15) << 9));
            int i9 = ((bArr[i8] >> 4) & 15) | ((bArr[i2 + 7] & 255) << 4);
            int i10 = i2 + 8;
            sArr[s2 + 4] = (short) (i9 | ((bArr[i10] & 1) << 12));
            int i11 = i2 + 9;
            sArr[s2 + 5] = (short) (((bArr[i10] >> 1) & 127) | ((bArr[i11] & 63) << 7));
            int i12 = ((bArr[i11] >> 6) & 3) | ((bArr[i2 + 10] & 255) << 2);
            int i13 = i2 + 11;
            sArr[s2 + 6] = (short) (i12 | ((bArr[i13] & 7) << 10));
            sArr[s2 + 7] = (short) (((bArr[i2 + 12] & 255) << 5) | ((bArr[i13] >> 3) & 31));
        }
    }

    private void POLp2BS(byte[] bArr, int i, short[] sArr) {
        for (short s = 0; s < this.SABER_N / 4; s = (short) (s + 1)) {
            short s2 = (short) (s * 4);
            int i2 = ((short) (s * 5)) + i;
            int i3 = s2 + 0;
            bArr[i2 + 0] = (byte) (sArr[i3] & 255);
            int i4 = s2 + 1;
            bArr[i2 + 1] = (byte) (((sArr[i3] >> 8) & 3) | ((sArr[i4] & 63) << 2));
            int i5 = s2 + 2;
            bArr[i2 + 2] = (byte) (((sArr[i4] >> 6) & 15) | ((sArr[i5] & 15) << 4));
            int i6 = s2 + 3;
            bArr[i2 + 3] = (byte) (((sArr[i5] >> 4) & 63) | ((sArr[i6] & 3) << 6));
            bArr[i2 + 4] = (byte) ((sArr[i6] >> 2) & 255);
        }
    }

    private void POLq2BS(byte[] bArr, int i, short[] sArr) {
        for (short s = 0; s < this.SABER_N / 8; s = (short) (s + 1)) {
            short s2 = (short) (s * 8);
            int i2 = ((short) (s * 13)) + i;
            int i3 = s2 + 0;
            bArr[i2 + 0] = (byte) (sArr[i3] & 255);
            int i4 = s2 + 1;
            bArr[i2 + 1] = (byte) (((sArr[i3] >> 8) & 31) | ((sArr[i4] & 7) << 5));
            bArr[i2 + 2] = (byte) ((sArr[i4] >> 3) & 255);
            int i5 = s2 + 2;
            bArr[i2 + 3] = (byte) (((sArr[i4] >> 11) & 3) | ((sArr[i5] & 63) << 2));
            int i6 = s2 + 3;
            bArr[i2 + 4] = (byte) (((sArr[i5] >> 6) & 127) | ((sArr[i6] & 1) << 7));
            bArr[i2 + 5] = (byte) ((sArr[i6] >> 1) & 255);
            int i7 = s2 + 4;
            bArr[i2 + 6] = (byte) (((sArr[i6] >> 9) & 15) | ((sArr[i7] & 15) << 4));
            bArr[i2 + 7] = (byte) ((sArr[i7] >> 4) & 255);
            int i8 = s2 + 5;
            bArr[i2 + 8] = (byte) (((sArr[i7] >> 12) & 1) | ((sArr[i8] & 127) << 1));
            int i9 = s2 + 6;
            bArr[i2 + 9] = (byte) (((sArr[i8] >> 7) & 63) | ((sArr[i9] & 3) << 6));
            bArr[i2 + 10] = (byte) ((sArr[i9] >> 2) & 255);
            int i10 = s2 + 7;
            bArr[i2 + 11] = (byte) (((sArr[i9] >> 10) & 7) | ((sArr[i10] & 31) << 3));
            bArr[i2 + 12] = (byte) ((sArr[i10] >> 5) & 255);
        }
    }

    public void BS2POLT(byte[] bArr, int i, short[] sArr) {
        int i2 = this.SABER_ET;
        short s = 0;
        if (i2 == 3) {
            while (s < this.SABER_N / 8) {
                short s2 = (short) (s * 8);
                int i3 = ((short) (s * 3)) + i;
                int i4 = i3 + 0;
                sArr[s2 + 0] = (short) (bArr[i4] & 7);
                sArr[s2 + 1] = (short) ((bArr[i4] >> 3) & 7);
                int i5 = i3 + 1;
                sArr[s2 + 2] = (short) (((bArr[i4] >> 6) & 3) | ((bArr[i5] & 1) << 2));
                sArr[s2 + 3] = (short) ((bArr[i5] >> 1) & 7);
                sArr[s2 + 4] = (short) ((bArr[i5] >> 4) & 7);
                int i6 = i3 + 2;
                sArr[s2 + 5] = (short) (((bArr[i5] >> 7) & 1) | ((bArr[i6] & 3) << 1));
                sArr[s2 + 6] = (short) ((bArr[i6] >> 2) & 7);
                sArr[s2 + 7] = (short) ((bArr[i6] >> 5) & 7);
                s = (short) (s + 1);
            }
        } else if (i2 == 4) {
            while (s < this.SABER_N / 2) {
                short s3 = (short) (s * 2);
                int i7 = i + s;
                sArr[s3] = (short) (bArr[i7] & 15);
                sArr[s3 + 1] = (short) ((bArr[i7] >> 4) & 15);
                s = (short) (s + 1);
            }
        } else if (i2 == 6) {
            while (s < this.SABER_N / 4) {
                short s4 = (short) (s * 4);
                int i8 = ((short) (s * 3)) + i;
                int i9 = i8 + 0;
                sArr[s4 + 0] = (short) (bArr[i9] & 63);
                int i10 = i8 + 1;
                sArr[s4 + 1] = (short) (((bArr[i9] >> 6) & 3) | ((bArr[i10] & 15) << 2));
                int i11 = i8 + 2;
                sArr[s4 + 2] = (short) (((bArr[i10] & 255) >> 4) | ((bArr[i11] & 3) << 4));
                sArr[s4 + 3] = (short) ((bArr[i11] & 255) >> 2);
                s = (short) (s + 1);
            }
        }
    }

    public void BS2POLVECp(byte[] bArr, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            BS2POLp(bArr, ((this.SABER_EP * this.SABER_N) / 8) * b, sArr[b]);
        }
    }

    public void BS2POLVECq(byte[] bArr, int i, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            BS2POLq(bArr, (this.SABER_POLYBYTES * b) + i, sArr[b]);
        }
    }

    public void BS2POLmsg(byte[] bArr, short[] sArr) {
        for (byte b = 0; b < this.SABER_KEYBYTES; b = (byte) (b + 1)) {
            for (byte b2 = 0; b2 < 8; b2 = (byte) (b2 + 1)) {
                sArr[(b * 8) + b2] = (short) ((bArr[b] >> b2) & 1);
            }
        }
    }

    public void BS2POLp(byte[] bArr, int i, short[] sArr) {
        for (short s = 0; s < this.SABER_N / 4; s = (short) (s + 1)) {
            short s2 = (short) (s * 4);
            int i2 = ((short) (s * 5)) + i;
            int i3 = i2 + 1;
            sArr[s2 + 0] = (short) ((bArr[i2 + 0] & 255) | ((bArr[i3] & 3) << 8));
            int i4 = i2 + 2;
            sArr[s2 + 1] = (short) (((bArr[i3] >> 2) & 63) | ((bArr[i4] & 15) << 6));
            int i5 = i2 + 3;
            sArr[s2 + 2] = (short) (((bArr[i4] >> 4) & 15) | ((bArr[i5] & 63) << 4));
            sArr[s2 + 3] = (short) (((bArr[i2 + 4] & 255) << 2) | ((bArr[i5] >> 6) & 3));
        }
    }

    public void POLT2BS(byte[] bArr, int i, short[] sArr) {
        int i2 = this.SABER_ET;
        short s = 0;
        if (i2 == 3) {
            while (s < this.SABER_N / 8) {
                short s2 = (short) (s * 8);
                int i3 = ((short) (s * 3)) + i;
                int i4 = s2 + 2;
                bArr[i3 + 0] = (byte) ((sArr[s2 + 0] & 7) | ((sArr[s2 + 1] & 7) << 3) | ((sArr[i4] & 3) << 6));
                int i5 = ((sArr[i4] >> 2) & 1) | ((sArr[s2 + 3] & 7) << 1) | ((sArr[s2 + 4] & 7) << 4);
                int i6 = s2 + 5;
                bArr[i3 + 1] = (byte) (i5 | ((sArr[i6] & 1) << 7));
                bArr[i3 + 2] = (byte) (((sArr[s2 + 7] & 7) << 5) | ((sArr[i6] >> 1) & 3) | ((sArr[s2 + 6] & 7) << 2));
                s = (short) (s + 1);
            }
        } else if (i2 == 4) {
            while (s < this.SABER_N / 2) {
                short s3 = (short) (s * 2);
                bArr[i + s] = (byte) (((sArr[s3 + 1] & 15) << 4) | (sArr[s3] & 15));
                s = (short) (s + 1);
            }
        } else if (i2 == 6) {
            while (s < this.SABER_N / 4) {
                short s4 = (short) (s * 4);
                int i7 = ((short) (s * 3)) + i;
                int i8 = s4 + 1;
                bArr[i7 + 0] = (byte) ((sArr[s4 + 0] & 63) | ((sArr[i8] & 3) << 6));
                int i9 = s4 + 2;
                bArr[i7 + 1] = (byte) (((sArr[i8] >> 2) & 15) | ((sArr[i9] & 15) << 4));
                bArr[i7 + 2] = (byte) (((sArr[s4 + 3] & 63) << 2) | ((sArr[i9] >> 4) & 3));
                s = (short) (s + 1);
            }
        }
    }

    public void POLVECp2BS(byte[] bArr, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            POLp2BS(bArr, ((this.SABER_EP * this.SABER_N) / 8) * b, sArr[b]);
        }
    }

    public void POLVECq2BS(byte[] bArr, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            POLq2BS(bArr, this.SABER_POLYBYTES * b, sArr[b]);
        }
    }

    public void POLmsg2BS(byte[] bArr, short[] sArr) {
        for (byte b = 0; b < this.SABER_KEYBYTES; b = (byte) (b + 1)) {
            for (byte b2 = 0; b2 < 8; b2 = (byte) (b2 + 1)) {
                bArr[b] = (byte) (bArr[b] | ((sArr[(b * 8) + b2] & 1) << b2));
            }
        }
    }
}
