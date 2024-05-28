package com.utils.sm3;

import java.util.Arrays;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SM3Digest {
    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: H1 */
    private int f20850H1;

    /* renamed from: H2 */
    private int f20851H2;

    /* renamed from: H3 */
    private int f20852H3;

    /* renamed from: H4 */
    private int f20853H4;

    /* renamed from: H5 */
    private int f20854H5;

    /* renamed from: H6 */
    private int f20855H6;

    /* renamed from: H7 */
    private int f20856H7;

    /* renamed from: H8 */
    private int f20857H8;

    /* renamed from: W */
    private final int[] f20858W;

    /* renamed from: W1 */
    private final int[] f20859W1;

    /* renamed from: X */
    private final int[] f20860X;
    private long byteCount;
    private final byte[] xBuf;
    private int xBufOff;
    private int xOff;

    /* renamed from: FF */
    private int m5821FF(int i, int i2, int i3, int i4) {
        if (i4 <= 15) {
            return (i ^ i2) ^ i3;
        }
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: GG */
    private int m5820GG(int i, int i2, int i3, int i4) {
        if (i4 <= 15) {
            return (i ^ i2) ^ i3;
        }
        return ((~i) & i3) | (i2 & i);
    }

    private int LShift(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: P0 */
    private int m5819P0(int i) {
        return ((i >>> 15) | (i << 17)) ^ (((i << 9) | (i >>> 23)) ^ i);
    }

    /* renamed from: P1 */
    private int m5818P1(int i) {
        return ((i >>> 9) | (i << 23)) ^ (((i << 15) | (i >>> 17)) ^ i);
    }

    public String getAlgorithmName() {
        return "SM3";
    }

    public int getDigestSize() {
        return 32;
    }

    public SM3Digest() {
        this.f20860X = new int[64];
        this.f20858W = new int[68];
        this.f20859W1 = new int[64];
        this.xBuf = new byte[4];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        this.f20860X = new int[64];
        this.f20858W = new int[68];
        this.f20859W1 = new int[64];
        this.xBuf = new byte[4];
        byte[] bArr = sM3Digest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = sM3Digest.xBufOff;
        this.byteCount = sM3Digest.byteCount;
        this.f20850H1 = sM3Digest.f20850H1;
        this.f20851H2 = sM3Digest.f20851H2;
        this.f20852H3 = sM3Digest.f20852H3;
        this.f20853H4 = sM3Digest.f20853H4;
        this.f20854H5 = sM3Digest.f20854H5;
        this.f20855H6 = sM3Digest.f20855H6;
        this.f20856H7 = sM3Digest.f20856H7;
        this.f20857H8 = sM3Digest.f20857H8;
        int[] iArr = sM3Digest.f20860X;
        System.arraycopy(iArr, 0, this.f20860X, 0, iArr.length);
        this.xOff = sM3Digest.xOff;
    }

    private void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f20860X;
        int i5 = this.xOff;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.xOff = i6;
        if (i6 == 16) {
            processBlock();
        }
    }

    private void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f20860X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        intToBigEndian(this.f20850H1, bArr, i);
        intToBigEndian(this.f20851H2, bArr, i + 4);
        intToBigEndian(this.f20852H3, bArr, i + 8);
        intToBigEndian(this.f20853H4, bArr, i + 12);
        intToBigEndian(this.f20854H5, bArr, i + 16);
        intToBigEndian(this.f20855H6, bArr, i + 20);
        intToBigEndian(this.f20856H7, bArr, i + 24);
        intToBigEndian(this.f20857H8, bArr, i + 28);
        reset();
        return 32;
    }

    private void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        Arrays.fill(this.xBuf, (byte) 0);
        this.f20850H1 = 1937774191;
        this.f20851H2 = 1226093241;
        this.f20852H3 = 388252375;
        this.f20853H4 = -628488704;
        this.f20854H5 = -1452330820;
        this.f20855H6 = 372324522;
        this.f20856H7 = -477237683;
        this.f20857H8 = -1325724082;
        this.xOff = 0;
        Arrays.fill(this.f20860X, 0);
    }

    private void processBlock() {
        for (int i = 0; i < 16; i++) {
            this.f20858W[i] = this.f20860X[i];
        }
        for (int i2 = 16; i2 <= 67; i2++) {
            int[] iArr = this.f20858W;
            iArr[i2] = (LShift(this.f20858W[i2 - 13], 7) ^ m5818P1((iArr[i2 - 16] ^ iArr[i2 - 9]) ^ LShift(iArr[i2 - 3], 15))) ^ this.f20858W[i2 - 6];
        }
        for (int i3 = 0; i3 <= 63; i3++) {
            int[] iArr2 = this.f20859W1;
            int[] iArr3 = this.f20858W;
            iArr2[i3] = iArr3[i3 + 4] ^ iArr3[i3];
        }
        int i4 = this.f20850H1;
        int i5 = this.f20851H2;
        int i6 = this.f20852H3;
        int i7 = this.f20853H4;
        int i8 = this.f20854H5;
        int i9 = this.f20855H6;
        int i10 = this.f20856H7;
        int i11 = 2043430169;
        int i12 = this.f20857H8;
        int i13 = i10;
        int i14 = i9;
        int i15 = i8;
        int i16 = i7;
        int i17 = i6;
        int i18 = i5;
        int i19 = i4;
        int i20 = 0;
        while (i20 < 64) {
            if (i20 >= 16) {
                i11 = 2055708042;
            }
            int LShift = LShift(LShift(i19, 12) + i15 + LShift(i11, i20), 7);
            int m5821FF = m5821FF(i19, i18, i17, i20) + i16;
            int LShift2 = LShift(i18, 9);
            int LShift3 = LShift(i14, 19);
            i20++;
            i18 = i19;
            i19 = m5821FF + (LShift(i19, 12) ^ LShift) + this.f20859W1[i20];
            i16 = i17;
            i17 = LShift2;
            i14 = i15;
            i15 = m5819P0(m5820GG(i15, i14, i13, i20) + i12 + LShift + this.f20858W[i20]);
            i12 = i13;
            i13 = LShift3;
        }
        this.f20850H1 ^= i19;
        this.f20851H2 ^= i18;
        this.f20852H3 ^= i17;
        this.f20853H4 ^= i16;
        this.f20854H5 ^= i15;
        this.f20855H6 ^= i14;
        this.f20856H7 ^= i13;
        this.f20857H8 ^= i12;
        this.xOff = 0;
        for (int i21 = 0; i21 < 16; i21++) {
            this.f20860X[i21] = 0;
        }
    }

    private void intToBigEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    public void update(byte b) {
        byte[] bArr = this.xBuf;
        int i = this.xBufOff;
        this.xBufOff = i + 1;
        bArr[i] = b;
        if (this.xBufOff == bArr.length) {
            processWord(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    public void update(byte[] bArr, int i, int i2) {
        while (this.xBufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.xBuf.length) {
            processWord(bArr, i);
            byte[] bArr2 = this.xBuf;
            i += bArr2.length;
            i2 -= bArr2.length;
            this.byteCount += bArr2.length;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }

    private void finish() {
        long j = this.byteCount << 3;
        update(Byte.MIN_VALUE);
        while (this.xBufOff != 0) {
            update((byte) 0);
        }
        processLength(j);
        processBlock();
    }
}
