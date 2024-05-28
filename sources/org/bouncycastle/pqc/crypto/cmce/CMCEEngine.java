package org.bouncycastle.pqc.crypto.cmce;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEEngine {
    private int COND_BYTES;
    private int GFBITS;
    private int GFMASK;
    private int IRR_BYTES;
    private int PK_NCOLS;
    private int PK_NROWS;
    private int PK_ROW_BYTES;
    private int SYND_BYTES;
    private int SYS_N;
    private int SYS_T;
    private BENES benes;
    private boolean countErrorIndices;
    private final int defaultKeySize;

    /* renamed from: gf */
    private AbstractC13366GF f27113gf;
    private int[] poly;
    private boolean usePadding;
    private boolean usePivots;

    public CMCEEngine(int i, int i2, int i3, int[] iArr, boolean z, int i4) {
        BENES benes13;
        this.usePivots = z;
        this.SYS_N = i2;
        this.SYS_T = i3;
        this.GFBITS = i;
        this.poly = iArr;
        this.defaultKeySize = i4;
        int i5 = this.SYS_T;
        this.IRR_BYTES = i5 * 2;
        int i6 = this.GFBITS;
        this.COND_BYTES = (1 << (i6 - 4)) * ((i6 * 2) - 1);
        this.PK_NROWS = i5 * i6;
        int i7 = this.SYS_N;
        int i8 = this.PK_NROWS;
        this.PK_NCOLS = i7 - i8;
        this.PK_ROW_BYTES = (this.PK_NCOLS + 7) / 8;
        this.SYND_BYTES = (i8 + 7) / 8;
        this.GFMASK = (1 << i6) - 1;
        if (i6 == 12) {
            this.f27113gf = new GF12(i6);
            benes13 = new BENES12(this.SYS_N, this.SYS_T, this.GFBITS);
        } else {
            this.f27113gf = new GF13(i6);
            benes13 = new BENES13(this.SYS_N, this.SYS_T, this.GFBITS);
        }
        this.benes = benes13;
        this.usePadding = this.SYS_T % 8 != 0;
        this.countErrorIndices = (1 << this.GFBITS) > this.SYS_N;
    }

    private void GF_mul(short[] sArr, short[] sArr2, short[] sArr3) {
        int i;
        int i2;
        short[] sArr4 = new short[(this.SYS_T * 2) - 1];
        for (int i3 = 0; i3 < (this.SYS_T * 2) - 1; i3++) {
            sArr4[i3] = 0;
        }
        int i4 = 0;
        while (true) {
            i = this.SYS_T;
            if (i4 >= i) {
                break;
            }
            for (int i5 = 0; i5 < this.SYS_T; i5++) {
                int i6 = i4 + i5;
                sArr4[i6] = (short) (this.f27113gf.gf_mul(sArr2[i4], sArr3[i5]) ^ sArr4[i6]);
            }
            i4++;
        }
        int i7 = (i - 1) * 2;
        while (true) {
            i2 = this.SYS_T;
            if (i7 < i2) {
                break;
            }
            int i8 = 0;
            while (true) {
                int[] iArr = this.poly;
                if (i8 != iArr.length) {
                    int i9 = iArr[i8];
                    if (i9 == 0 && this.GFBITS == 12) {
                        int i10 = i7 - this.SYS_T;
                        sArr4[i10] = (short) (sArr4[i10] ^ this.f27113gf.gf_mul(sArr4[i7], (short) 2));
                    } else {
                        int i11 = (i7 - this.SYS_T) + i9;
                        sArr4[i11] = (short) (sArr4[i11] ^ sArr4[i7]);
                    }
                    i8++;
                }
            }
            i7--;
        }
        System.arraycopy(sArr4, 0, sArr, 0, i2);
        for (int i12 = 0; i12 < this.SYS_T; i12++) {
            sArr[i12] = sArr4[i12];
        }
    }

    /* renamed from: bm */
    private void m254bm(short[] sArr, short[] sArr2) {
        int i;
        int i2 = this.SYS_T;
        short[] sArr3 = new short[i2 + 1];
        short[] sArr4 = new short[i2 + 1];
        short[] sArr5 = new short[i2 + 1];
        int i3 = 0;
        for (int i4 = 0; i4 < this.SYS_T + 1; i4++) {
            sArr5[i4] = 0;
            sArr4[i4] = 0;
        }
        sArr4[0] = 1;
        sArr5[1] = 1;
        short s = 1;
        short s2 = 0;
        short s3 = 0;
        while (s2 < this.SYS_T * 2) {
            int i5 = i3;
            short s4 = i5;
            while (i5 <= min(s2, this.SYS_T)) {
                s4 = (short) (s4 ^ this.f27113gf.gf_mul(sArr4[i5], sArr2[s2 - i5]));
                i5++;
            }
            short s5 = (short) (((short) (((short) (((short) (s4 - 1)) >> 15)) & 1)) - 1);
            short s6 = (short) (((short) (((short) (((short) (((short) (s2 - (s3 * 2))) >> 15)) & 1)) - 1)) & s5);
            for (int i6 = i3; i6 <= this.SYS_T; i6++) {
                sArr3[i6] = sArr4[i6];
            }
            short gf_frac = this.f27113gf.gf_frac(s, s4);
            for (int i7 = i3; i7 <= this.SYS_T; i7++) {
                sArr4[i7] = (short) ((this.f27113gf.gf_mul(gf_frac, sArr5[i7]) & s5) ^ sArr4[i7]);
            }
            int i8 = ~s6;
            int i9 = s2 + 1;
            s3 = (short) (((i9 - s3) & s6) | (s3 & i8));
            int i10 = 0;
            while (true) {
                i = this.SYS_T;
                if (i10 > i) {
                    break;
                }
                sArr5[i10] = (short) ((sArr5[i10] & i8) | (sArr3[i10] & s6));
                i10++;
            }
            s = (short) ((i8 & s) | (s4 & s6));
            while (i >= 1) {
                sArr5[i] = sArr5[i - 1];
                i--;
            }
            i3 = 0;
            sArr5[0] = 0;
            s2 = (short) i9;
        }
        while (true) {
            int i11 = this.SYS_T;
            if (i3 > i11) {
                return;
            }
            sArr[i3] = sArr4[i11 - i3];
            i3++;
        }
    }

    static void cbrecursion(byte[] bArr, long j, long j2, short[] sArr, int i, long j3, long j4, int[] iArr) {
        long j5;
        int i2;
        int i3;
        long j6 = j4;
        if (j3 == 1) {
            int i4 = (int) (j >> 3);
            bArr[i4] = (byte) ((get_q_short(iArr, i) << ((int) (j & 7))) ^ bArr[i4]);
            return;
        }
        if (sArr != null) {
            for (long j7 = 0; j7 < j6; j7++) {
                int i5 = (int) j7;
                iArr[i5] = sArr[(int) (j7 ^ 1)] | ((sArr[i5] ^ 1) << 16);
            }
        } else {
            for (long j8 = 0; j8 < j6; j8++) {
                long j9 = i;
                iArr[(int) j8] = ((get_q_short(iArr, (int) (j9 + j8)) ^ 1) << 16) | get_q_short(iArr, (int) (j9 + (j8 ^ 1)));
            }
        }
        int i6 = (int) j6;
        sort32(iArr, 0, i6);
        for (long j10 = 0; j10 < j6; j10++) {
            int i7 = (int) j10;
            int i8 = 65535 & iArr[i7];
            if (j10 >= i8) {
                i7 = i8;
            }
            iArr[(int) (j6 + j10)] = i7 | (i8 << 16);
        }
        for (long j11 = 0; j11 < j6; j11++) {
            iArr[(int) j11] = (int) ((iArr[i3] << 16) | j11);
        }
        sort32(iArr, 0, i6);
        for (long j12 = 0; j12 < j6; j12++) {
            int i9 = (int) j12;
            iArr[i9] = (iArr[i9] << 16) + (iArr[(int) (j6 + j12)] >> 16);
        }
        sort32(iArr, 0, i6);
        if (j3 <= 10) {
            for (long j13 = 0; j13 < j6; j13++) {
                int i10 = (int) (j6 + j13);
                iArr[i10] = ((iArr[(int) j13] & 65535) << 10) | (iArr[i10] & 1023);
            }
            long j14 = 1;
            for (long j15 = 1; j14 < j3 - j15; j15 = 1) {
                long j16 = 0;
                while (j16 < j6) {
                    iArr[(int) j16] = (int) (((iArr[(int) (j6 + j16)] & (-1024)) << 6) | j16);
                    j16++;
                    j14 = j14;
                }
                long j17 = j14;
                sort32(iArr, 0, i6);
                for (long j18 = 0; j18 < j6; j18++) {
                    int i11 = (int) j18;
                    iArr[i11] = (iArr[i11] << 20) | iArr[(int) (j6 + j18)];
                }
                sort32(iArr, 0, i6);
                for (long j19 = 0; j19 < j6; j19++) {
                    int i12 = (int) j19;
                    int i13 = iArr[i12] & 1048575;
                    int i14 = (int) (j6 + j19);
                    int i15 = (iArr[i12] & 1047552) | (iArr[i14] & 1023);
                    if (i13 >= i15) {
                        i13 = i15;
                    }
                    iArr[i14] = i13;
                }
                j14 = j17 + 1;
            }
            for (long j20 = 0; j20 < j6; j20++) {
                int i16 = (int) (j6 + j20);
                iArr[i16] = iArr[i16] & 1023;
            }
        } else {
            for (long j21 = 0; j21 < j6; j21++) {
                int i17 = (int) (j6 + j21);
                iArr[i17] = (iArr[(int) j21] << 16) | (iArr[i17] & 65535);
            }
            long j22 = 1;
            for (long j23 = 1; j22 < j3 - j23; j23 = 1) {
                for (long j24 = 0; j24 < j6; j24++) {
                    iArr[(int) j24] = (int) ((iArr[(int) (j6 + j24)] & (-65536)) | j24);
                }
                sort32(iArr, 0, i6);
                for (long j25 = 0; j25 < j6; j25++) {
                    int i18 = (int) j25;
                    iArr[i18] = (iArr[i18] << 16) | (iArr[(int) (j6 + j25)] & 65535);
                }
                if (j22 < j3 - 2) {
                    for (long j26 = 0; j26 < j6; j26++) {
                        int i19 = (int) (j6 + j26);
                        iArr[i19] = (iArr[(int) j26] & (-65536)) | (iArr[i19] >> 16);
                    }
                    sort32(iArr, i6, (int) (j6 * 2));
                    for (long j27 = 0; j27 < j6; j27++) {
                        int i20 = (int) (j6 + j27);
                        iArr[i20] = (iArr[i20] << 16) | (iArr[(int) j27] & 65535);
                    }
                }
                sort32(iArr, 0, i6);
                for (long j28 = 0; j28 < j6; j28++) {
                    int i21 = (int) (j6 + j28);
                    int i22 = (iArr[i21] & (-65536)) | (iArr[(int) j28] & 65535);
                    if (i22 < iArr[i21]) {
                        iArr[i21] = i22;
                    }
                }
                j22++;
            }
            for (long j29 = 0; j29 < j6; j29++) {
                int i23 = (int) (j6 + j29);
                iArr[i23] = iArr[i23] & 65535;
            }
        }
        if (sArr != null) {
            for (long j30 = 0; j30 < j6; j30++) {
                iArr[(int) j30] = (int) ((sArr[i2] << 16) + j30);
            }
        } else {
            for (long j31 = 0; j31 < j6; j31++) {
                iArr[(int) j31] = (int) ((get_q_short(iArr, (int) (i + j31)) << 16) + j31);
            }
        }
        sort32(iArr, 0, i6);
        long j32 = j;
        long j33 = 2;
        long j34 = 0;
        while (true) {
            j5 = j6 / j33;
            if (j34 >= j5) {
                break;
            }
            long j35 = j34 * j33;
            long j36 = j6 + j35;
            int i24 = (int) j36;
            int i25 = iArr[i24] & 1;
            int i26 = (int) (i25 + j35);
            int i27 = (int) (j32 >> 3);
            bArr[i27] = (byte) ((i25 << ((int) (j32 & 7))) ^ bArr[i27]);
            j32 += j2;
            iArr[i24] = (iArr[(int) j35] << 16) | i26;
            iArr[(int) (j36 + 1)] = (i26 ^ 1) | (iArr[(int) (j35 + 1)] << 16);
            j34++;
            i6 = i6;
            j33 = 2;
        }
        int i28 = i6;
        long j37 = j33;
        long j38 = j6 * j37;
        int i29 = i28;
        sort32(iArr, i29, (int) j38);
        long j39 = j3 * j37;
        long j40 = j32 + ((j39 - 3) * j2 * j5);
        long j41 = 0;
        while (j41 < j5) {
            long j42 = j41 * j37;
            long j43 = j6 + j42;
            int i30 = (int) j43;
            int i31 = iArr[i30] & 1;
            int i32 = (int) (i31 + j42);
            long j44 = j39;
            int i33 = (int) (j40 >> 3);
            bArr[i33] = (byte) ((i31 << ((int) (j40 & 7))) ^ bArr[i33]);
            j40 += j2;
            iArr[(int) j42] = (iArr[i30] & 65535) | (i32 << 16);
            iArr[(int) (j42 + 1)] = (iArr[(int) (j43 + 1)] & 65535) | ((i32 ^ 1) << 16);
            j41++;
            i29 = i29;
            j38 = j38;
            j39 = j44;
            j6 = j4;
            j37 = 2;
        }
        long j45 = j38;
        int i34 = i29;
        sort32(iArr, 0, i34);
        long j46 = 2;
        long j47 = j40 - (((j39 - 2) * j2) * j5);
        short[] sArr2 = new short[i34 * 4];
        long j48 = 0;
        while (j48 < j45) {
            long j49 = j48 * j46;
            int i35 = (int) j48;
            sArr2[(int) (j49 + 0)] = (short) iArr[i35];
            sArr2[(int) (j49 + 1)] = (short) ((iArr[i35] & (-65536)) >> 16);
            j48++;
            j46 = 2;
        }
        for (long j50 = 0; j50 < j5; j50++) {
            long j51 = j50 * 2;
            sArr2[(int) j50] = (short) ((iArr[(int) j51] & 65535) >>> 1);
            sArr2[(int) (j50 + j5)] = (short) ((iArr[(int) (j51 + 1)] & 65535) >>> 1);
        }
        for (long j52 = 0; j52 < j5; j52++) {
            long j53 = j52 * 2;
            iArr[(int) (j4 + (j4 / 4) + j52)] = (sArr2[(int) (j53 + 1)] << 16) | sArr2[(int) j53];
        }
        long j54 = j2 * 2;
        long j55 = j4 + (j4 / 4);
        long j56 = j3 - 1;
        cbrecursion(bArr, j47, j54, null, ((int) j55) * 2, j56, j5, iArr);
        cbrecursion(bArr, j47 + j2, j54, null, (int) ((j55 * 2) + j5), j56, j5, iArr);
    }

    private static void controlbitsfrompermutation(byte[] bArr, short[] sArr, long j, long j2) {
        long j3 = 2;
        int[] iArr = new int[(int) (j2 * 2)];
        int i = (int) j2;
        short[] sArr2 = new short[i];
        while (true) {
            short s = 0;
            for (int i2 = 0; i2 < (((((j * j3) - 1) * j2) / j3) + 7) / 8; i2++) {
                bArr[i2] = 0;
            }
            int i3 = i;
            short[] sArr3 = sArr2;
            int[] iArr2 = iArr;
            cbrecursion(bArr, 0L, 1L, sArr, 0, j, j2, iArr);
            for (int i4 = 0; i4 < j2; i4++) {
                sArr3[i4] = (short) i4;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < j; i6++) {
                layer(sArr3, bArr, i5, i6, i3);
                i5 = (int) (i5 + (j2 >> 4));
            }
            for (int i7 = (int) (j - 2); i7 >= 0; i7--) {
                layer(sArr3, bArr, i5, i7, i3);
                i5 = (int) (i5 + (j2 >> 4));
            }
            int i8 = 0;
            while (i8 < j2) {
                i8++;
                s = (short) (s | (sArr[i8] ^ sArr3[i8]));
            }
            if (s == 0) {
                return;
            }
            sArr2 = sArr3;
            i = i3;
            iArr = iArr2;
            j3 = 2;
        }
    }

    private static int ctz(long j) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 64; i3++) {
            int i4 = (int) ((j >> i3) & 1);
            i2 |= i4;
            i += (i4 ^ 1) & (i2 ^ 1);
        }
        return i;
    }

    private int decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i;
        int i2;
        int i3 = this.SYS_T;
        short[] sArr = new short[i3 + 1];
        int i4 = this.SYS_N;
        short[] sArr2 = new short[i4];
        short[] sArr3 = new short[i3 * 2];
        short[] sArr4 = new short[i3 * 2];
        short[] sArr5 = new short[i3 + 1];
        short[] sArr6 = new short[i4];
        byte[] bArr4 = new byte[i4 / 8];
        int i5 = 0;
        while (true) {
            i = this.SYND_BYTES;
            if (i5 >= i) {
                break;
            }
            bArr4[i5] = bArr3[i5];
            i5++;
        }
        while (i < this.SYS_N / 8) {
            bArr4[i] = 0;
            i++;
        }
        int i6 = 0;
        while (true) {
            i2 = this.SYS_T;
            if (i6 >= i2) {
                break;
            }
            sArr[i6] = Utils.load_gf(bArr2, (i6 * 2) + 40, this.GFMASK);
            i6++;
        }
        sArr[i2] = 1;
        this.benes.support_gen(sArr2, bArr2);
        synd(sArr3, sArr, sArr2, bArr4);
        m254bm(sArr5, sArr3);
        root(sArr6, sArr5, sArr2);
        for (int i7 = 0; i7 < this.SYS_N / 8; i7++) {
            bArr[i7] = 0;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < this.SYS_N; i9++) {
            short gf_iszero = (short) (this.f27113gf.gf_iszero(sArr6[i9]) & 1);
            int i10 = i9 / 8;
            bArr[i10] = (byte) (bArr[i10] | (gf_iszero << (i9 % 8)));
            i8 += gf_iszero;
        }
        synd(sArr4, sArr, sArr2, bArr);
        int i11 = this.SYS_T ^ i8;
        for (int i12 = 0; i12 < this.SYS_T * 2; i12++) {
            i11 |= sArr3[i12] ^ sArr4[i12];
        }
        return (((i11 - 1) >> 15) & 1) ^ 1;
    }

    private void encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        generate_error_vector(bArr3, secureRandom);
        syndrome(bArr, bArr2, bArr3);
    }

    private short eval(short[] sArr, short s) {
        int i = this.SYS_T;
        short s2 = sArr[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            s2 = this.f27113gf.gf_add(this.f27113gf.gf_mul(s2, s), sArr[i2]);
        }
        return s2;
    }

    private void generate_error_vector(byte[] bArr, SecureRandom secureRandom) {
        int i = this.SYS_T;
        short[] sArr = new short[i * 2];
        short[] sArr2 = new short[i];
        byte[] bArr2 = new byte[i];
        while (true) {
            if (this.countErrorIndices) {
                byte[] bArr3 = new byte[this.SYS_T * 4];
                secureRandom.nextBytes(bArr3);
                for (int i2 = 0; i2 < this.SYS_T * 2; i2++) {
                    sArr[i2] = Utils.load_gf(bArr3, i2 * 2, this.GFMASK);
                }
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    int i5 = this.SYS_T;
                    if (i3 >= i5 * 2 || i4 >= i5) {
                        break;
                    }
                    if (sArr[i3] < this.SYS_N) {
                        sArr2[i4] = sArr[i3];
                        i4++;
                    }
                    i3++;
                }
                if (i4 < this.SYS_T) {
                    continue;
                }
            } else {
                byte[] bArr4 = new byte[this.SYS_T * 2];
                secureRandom.nextBytes(bArr4);
                for (int i6 = 0; i6 < this.SYS_T; i6++) {
                    sArr2[i6] = Utils.load_gf(bArr4, i6 * 2, this.GFMASK);
                }
            }
            boolean z = false;
            for (int i7 = 1; i7 < this.SYS_T && !z; i7++) {
                int i8 = 0;
                while (true) {
                    if (i8 >= i7) {
                        break;
                    } else if (sArr2[i7] == sArr2[i8]) {
                        z = true;
                        break;
                    } else {
                        i8++;
                    }
                }
            }
            if (!z) {
                break;
            }
        }
        for (int i9 = 0; i9 < this.SYS_T; i9++) {
            bArr2[i9] = (byte) (1 << (sArr2[i9] & 7));
        }
        for (short s = 0; s < this.SYS_N / 8; s = (short) (s + 1)) {
            bArr[s] = 0;
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                bArr[s] = (byte) ((((short) (same_mask32(s, (short) (sArr2[i10] >> 3)) & 255)) & bArr2[i10]) | bArr[s]);
            }
        }
    }

    private int generate_irr_poly(short[] sArr) {
        int i = this.SYS_T;
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, i + 1, i);
        sArr2[0][0] = 1;
        for (int i2 = 1; i2 < this.SYS_T; i2++) {
            sArr2[0][i2] = 0;
        }
        for (int i3 = 0; i3 < this.SYS_T; i3++) {
            sArr2[1][i3] = sArr[i3];
        }
        for (int i4 = 2; i4 <= this.SYS_T; i4++) {
            GF_mul(sArr2[i4], sArr2[i4 - 1], sArr);
        }
        int i5 = 0;
        while (i5 < this.SYS_T) {
            int i6 = i5 + 1;
            for (int i7 = i6; i7 < this.SYS_T; i7++) {
                short gf_iszero = this.f27113gf.gf_iszero(sArr2[i5][i5]);
                for (int i8 = i5; i8 < this.SYS_T + 1; i8++) {
                    sArr2[i8][i5] = (short) (sArr2[i8][i5] ^ (sArr2[i8][i7] & gf_iszero));
                }
            }
            if (sArr2[i5][i5] == 0) {
                return -1;
            }
            short gf_inv = this.f27113gf.gf_inv(sArr2[i5][i5]);
            for (int i9 = i5; i9 < this.SYS_T + 1; i9++) {
                sArr2[i9][i5] = this.f27113gf.gf_mul(sArr2[i9][i5], gf_inv);
            }
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                if (i10 != i5) {
                    short s = sArr2[i5][i10];
                    for (int i11 = i5; i11 < this.SYS_T + 1; i11++) {
                        short[] sArr3 = sArr2[i11];
                        sArr3[i10] = (short) (sArr3[i10] ^ this.f27113gf.gf_mul(sArr2[i11][i5], s));
                    }
                }
            }
            i5 = i6;
        }
        int i12 = 0;
        while (true) {
            int i13 = this.SYS_T;
            if (i12 >= i13) {
                return 0;
            }
            sArr[i12] = sArr2[i13][i12];
            i12++;
        }
    }

    static short get_q_short(int[] iArr, int i) {
        int i2 = i / 2;
        return (short) (i % 2 == 0 ? iArr[i2] : (iArr[i2] & (-65536)) >> 16);
    }

    private static void layer(short[] sArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 1 << i2;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            int i7 = i6;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = i5 + i8;
                int i10 = i9 + i4;
                int i11 = (sArr[i9] ^ sArr[i10]) & (-((bArr[(i7 >> 3) + i] >> (i7 & 7)) & 1));
                sArr[i9] = (short) (sArr[i9] ^ i11);
                sArr[i10] = (short) (sArr[i10] ^ i11);
                i7++;
            }
            i5 += i4 * 2;
            i6 = i7;
        }
    }

    private static int min(short s, int i) {
        return s < i ? s : i;
    }

    private int mov_columns(byte[][] bArr, short[] sArr, long[] jArr) {
        byte[] bArr2;
        long load8;
        int i;
        int i2;
        long[] jArr2 = new long[64];
        long[] jArr3 = new long[32];
        byte[] bArr3 = new byte[9];
        int i3 = this.PK_NROWS - 32;
        int i4 = i3 / 8;
        int i5 = i3 % 8;
        if (this.usePadding) {
            for (int i6 = 0; i6 < 32; i6++) {
                for (int i7 = 0; i7 < 9; i7++) {
                    bArr3[i7] = bArr[i3 + i6][i4 + i7];
                }
                int i8 = 0;
                while (i8 < 8) {
                    int i9 = i8 + 1;
                    bArr3[i8] = (byte) (((bArr3[i8] & 255) >> i5) | (bArr3[i9] << (8 - i5)));
                    i8 = i9;
                }
                jArr2[i6] = Utils.load8(bArr3, 0);
            }
        } else {
            for (int i10 = 0; i10 < 32; i10++) {
                jArr2[i10] = Utils.load8(bArr[i3 + i10], i4);
            }
        }
        long j = 0;
        jArr[0] = 0;
        int i11 = 0;
        while (i11 < 32) {
            long j2 = jArr2[i11];
            int i12 = i11 + 1;
            for (int i13 = i12; i13 < 32; i13++) {
                j2 |= jArr2[i13];
            }
            if (j2 == j) {
                return -1;
            }
            int ctz = ctz(j2);
            jArr3[i11] = ctz;
            byte[] bArr4 = bArr3;
            jArr[0] = (1 << ((int) jArr3[i11])) | jArr[0];
            for (int i14 = i12; i14 < 32; i14++) {
                jArr2[i11] = jArr2[i11] ^ (jArr2[i14] & (((jArr2[i11] >> ctz) & 1) - 1));
            }
            for (int i15 = i12; i15 < 32; i15++) {
                jArr2[i15] = jArr2[i15] ^ (jArr2[i11] & (-((jArr2[i15] >> ctz) & 1)));
            }
            i11 = i12;
            bArr3 = bArr4;
            j = 0;
        }
        byte[] bArr5 = bArr3;
        int i16 = 0;
        while (i16 < 32) {
            int i17 = i16 + 1;
            for (int i18 = i17; i18 < 64; i18++) {
                long same_mask64 = (sArr[i] ^ sArr[i2]) & same_mask64((short) i18, (short) jArr3[i16]);
                sArr[i3 + i16] = (short) (sArr[i] ^ same_mask64);
                sArr[i3 + i18] = (short) (sArr[i2] ^ same_mask64);
            }
            i16 = i17;
        }
        int i19 = 0;
        while (i19 < this.PK_NROWS) {
            if (this.usePadding) {
                for (int i20 = 0; i20 < 9; i20++) {
                    bArr5[i20] = bArr[i19][i4 + i20];
                }
                int i21 = 0;
                while (i21 < 8) {
                    int i22 = i21 + 1;
                    bArr5[i21] = (byte) ((bArr5[i22] << (8 - i5)) | ((bArr5[i21] & 255) >> i5));
                    i21 = i22;
                }
                bArr2 = bArr5;
                load8 = Utils.load8(bArr2, 0);
            } else {
                bArr2 = bArr5;
                load8 = Utils.load8(bArr[i19], i4);
            }
            for (int i23 = 0; i23 < 32; i23++) {
                long j3 = ((load8 >> i23) ^ (load8 >> ((int) jArr3[i23]))) & 1;
                load8 = (load8 ^ (j3 << ((int) jArr3[i23]))) ^ (j3 << i23);
            }
            if (this.usePadding) {
                Utils.store8(bArr2, 0, load8);
                int i24 = i4 + 8;
                int i25 = 8 - i5;
                bArr[i19][i24] = (byte) ((((bArr[i19][i24] & 255) >>> i5) << i5) | ((bArr2[7] & 255) >>> i25));
                bArr[i19][i4 + 0] = (byte) (((bArr2[0] & 255) << i5) | (((bArr[i19][i4] & 255) << i25) >>> i25));
                for (int i26 = 7; i26 >= 1; i26--) {
                    bArr[i19][i4 + i26] = (byte) (((bArr2[i26] & 255) << i5) | ((bArr2[i26 - 1] & 255) >>> i25));
                }
            } else {
                Utils.store8(bArr[i19], i4, load8);
            }
            i19++;
            bArr5 = bArr2;
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:128:0x01e6, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int pk_gen(byte[] r18, byte[] r19, int[] r20, short[] r21, long[] r22) {
        /*
            Method dump skipped, instructions count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.cmce.CMCEEngine.pk_gen(byte[], byte[], int[], short[], long[]):int");
    }

    private void root(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i = 0; i < this.SYS_N; i++) {
            sArr[i] = eval(sArr2, sArr3[i]);
        }
    }

    private static byte same_mask32(short s, short s2) {
        return (byte) ((-(((s ^ s2) - 1) >>> 31)) & 255);
    }

    private static long same_mask64(short s, short s2) {
        return -(((s ^ s2) - 1) >>> 63);
    }

    private static void sort32(int[] iArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 2) {
            return;
        }
        int i4 = 1;
        while (i4 < i3 - i4) {
            i4 += i4;
        }
        for (int i5 = i4; i5 > 0; i5 >>>= 1) {
            for (int i6 = 0; i6 < i3 - i5; i6++) {
                if ((i6 & i5) == 0) {
                    int i7 = i + i6;
                    int i8 = i7 + i5;
                    int i9 = iArr[i8] ^ iArr[i7];
                    int i10 = iArr[i8] - iArr[i7];
                    int i11 = i9 & ((i10 ^ ((iArr[i8] ^ i10) & i9)) >> 31);
                    iArr[i7] = iArr[i7] ^ i11;
                    iArr[i8] = iArr[i8] ^ i11;
                }
            }
            int i12 = 0;
            for (int i13 = i4; i13 > i5; i13 >>>= 1) {
                while (i12 < i3 - i13) {
                    if ((i12 & i5) == 0) {
                        int i14 = i + i12;
                        int i15 = i14 + i5;
                        int i16 = iArr[i15];
                        for (int i17 = i13; i17 > i5; i17 >>>= 1) {
                            int i18 = i14 + i17;
                            int i19 = iArr[i18] ^ i16;
                            int i20 = iArr[i18] - i16;
                            int i21 = i19 & ((i20 ^ ((iArr[i18] ^ i20) & i19)) >> 31);
                            i16 ^= i21;
                            iArr[i18] = i21 ^ iArr[i18];
                        }
                        iArr[i15] = i16;
                    }
                    i12++;
                }
            }
        }
    }

    private static void sort64(long[] jArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 2) {
            return;
        }
        int i4 = 1;
        while (i4 < i3 - i4) {
            i4 += i4;
        }
        for (int i5 = i4; i5 > 0; i5 >>>= 1) {
            for (int i6 = 0; i6 < i3 - i5; i6++) {
                if ((i6 & i5) == 0) {
                    int i7 = i + i6;
                    int i8 = i7 + i5;
                    long j = (-((jArr[i8] - jArr[i7]) >>> 63)) & (jArr[i7] ^ jArr[i8]);
                    jArr[i7] = jArr[i7] ^ j;
                    jArr[i8] = jArr[i8] ^ j;
                }
            }
            int i9 = 0;
            for (int i10 = i4; i10 > i5; i10 >>>= 1) {
                while (i9 < i3 - i10) {
                    if ((i9 & i5) == 0) {
                        int i11 = i + i9;
                        int i12 = i11 + i5;
                        long j2 = jArr[i12];
                        for (int i13 = i10; i13 > i5; i13 >>>= 1) {
                            int i14 = i11 + i13;
                            long j3 = (-((jArr[i14] - j2) >>> 63)) & (jArr[i14] ^ j2);
                            j2 ^= j3;
                            jArr[i14] = j3 ^ jArr[i14];
                        }
                        jArr[i12] = j2;
                    }
                    i9++;
                }
            }
        }
    }

    private void synd(short[] sArr, short[] sArr2, short[] sArr3, byte[] bArr) {
        for (int i = 0; i < this.SYS_T * 2; i++) {
            sArr[i] = 0;
        }
        for (int i2 = 0; i2 < this.SYS_N; i2++) {
            short s = (short) ((bArr[i2 / 8] >> (i2 % 8)) & 1);
            short eval = eval(sArr2, sArr3[i2]);
            AbstractC13366GF abstractC13366GF = this.f27113gf;
            short gf_inv = abstractC13366GF.gf_inv(abstractC13366GF.gf_mul(eval, eval));
            for (int i3 = 0; i3 < this.SYS_T * 2; i3++) {
                AbstractC13366GF abstractC13366GF2 = this.f27113gf;
                sArr[i3] = abstractC13366GF2.gf_add(sArr[i3], abstractC13366GF2.gf_mul(gf_inv, s));
                gf_inv = this.f27113gf.gf_mul(gf_inv, sArr3[i2]);
            }
        }
    }

    private void syndrome(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        short[] sArr = new short[this.SYS_N / 8];
        int i = this.PK_NROWS % 8;
        for (int i2 = 0; i2 < this.SYND_BYTES; i2++) {
            bArr[i2] = 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.PK_NROWS; i4++) {
            for (int i5 = 0; i5 < this.SYS_N / 8; i5++) {
                sArr[i5] = 0;
            }
            int i6 = 0;
            while (true) {
                int i7 = this.PK_ROW_BYTES;
                if (i6 >= i7) {
                    break;
                }
                sArr[((this.SYS_N / 8) - i7) + i6] = bArr2[i3 + i6];
                i6++;
            }
            if (this.usePadding) {
                for (int i8 = (this.SYS_N / 8) - 1; i8 >= (this.SYS_N / 8) - this.PK_ROW_BYTES; i8--) {
                    sArr[i8] = (short) ((((sArr[i8] & 255) << i) | ((sArr[i8 - 1] & 255) >>> (8 - i))) & 255);
                }
            }
            int i9 = i4 / 8;
            int i10 = i4 % 8;
            sArr[i9] = (short) (sArr[i9] | (1 << i10));
            byte b = 0;
            for (int i11 = 0; i11 < this.SYS_N / 8; i11++) {
                b = (byte) (b ^ (sArr[i11] & bArr3[i11]));
            }
            byte b2 = (byte) ((b >>> 4) ^ b);
            byte b3 = (byte) (b2 ^ (b2 >>> 2));
            bArr[i9] = (byte) ((((byte) (1 & ((byte) (b3 ^ (b3 >>> 1))))) << i10) | bArr[i9]);
            i3 += this.PK_ROW_BYTES;
        }
    }

    int check_c_padding(byte[] bArr) {
        return ((byte) ((((byte) (((byte) ((bArr[this.SYND_BYTES - 1] & 255) >>> (this.PK_NROWS % 8))) - 1)) & 255) >>> 7)) - 1;
    }

    int check_pk_padding(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < this.PK_NROWS; i++) {
            int i2 = this.PK_ROW_BYTES;
            b = (byte) (b | bArr[((i * i2) + i2) - 1]);
        }
        return ((byte) ((((byte) (((byte) ((b & 255) >>> (this.PK_NCOLS % 8))) - 1)) & 255) >>> 7)) - 1;
    }

    public byte[] decompress_private_key(byte[] bArr) {
        int i;
        int i2;
        byte[] bArr2 = new byte[getPrivateKeySize()];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[(this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + this.IRR_BYTES + 32];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 64);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, bArr3.length);
        if (bArr.length <= 40) {
            short[] sArr = new short[this.SYS_T];
            int i3 = this.IRR_BYTES;
            byte[] bArr4 = new byte[i3];
            int length = (bArr3.length - 32) - i3;
            for (int i4 = 0; i4 < this.SYS_T; i4++) {
                sArr[i4] = Utils.load_gf(bArr3, (i4 * 2) + length, this.GFMASK);
            }
            generate_irr_poly(sArr);
            for (int i5 = 0; i5 < this.SYS_T; i5++) {
                Utils.store_gf(bArr4, i5 * 2, sArr[i5]);
            }
            System.arraycopy(bArr4, 0, bArr2, 40, this.IRR_BYTES);
        }
        int length2 = bArr.length;
        int i6 = this.IRR_BYTES;
        if (length2 <= i6 + 40) {
            int i7 = this.GFBITS;
            int[] iArr = new int[1 << i7];
            short[] sArr2 = new short[1 << i7];
            int length3 = ((bArr3.length - 32) - i6) - ((1 << i7) * 4);
            int i8 = 0;
            while (true) {
                i = this.GFBITS;
                if (i8 >= (1 << i)) {
                    break;
                }
                iArr[i8] = Utils.load4(bArr3, (i8 * 4) + length3);
                i8++;
            }
            if (this.usePivots) {
                pk_gen(null, bArr2, iArr, sArr2, new long[]{0});
            } else {
                long[] jArr = new long[1 << i];
                for (int i9 = 0; i9 < (1 << this.GFBITS); i9++) {
                    jArr[i9] = iArr[i9];
                    jArr[i9] = jArr[i9] << 31;
                    jArr[i9] = jArr[i9] | i9;
                    jArr[i9] = jArr[i9] & Long.MAX_VALUE;
                }
                sort64(jArr, 0, jArr.length);
                for (int i10 = 0; i10 < (1 << this.GFBITS); i10++) {
                    sArr2[i10] = (short) (jArr[i10] & this.GFMASK);
                }
            }
            byte[] bArr5 = new byte[this.COND_BYTES];
            controlbitsfrompermutation(bArr5, sArr2, this.GFBITS, 1 << i2);
            System.arraycopy(bArr5, 0, bArr2, this.IRR_BYTES + 40, bArr5.length);
        }
        int privateKeySize = getPrivateKeySize();
        int i11 = this.SYS_N;
        System.arraycopy(bArr3, 0, bArr2, privateKeySize - (i11 / 8), i11 / 8);
        return bArr2;
    }

    public byte[] generate_public_key_from_private_key(byte[] bArr) {
        byte[] bArr2 = new byte[getPublicKeySize()];
        int i = this.GFBITS;
        short[] sArr = new short[1 << i];
        long[] jArr = {0};
        int[] iArr = new int[1 << i];
        byte[] bArr3 = new byte[(this.SYS_N / 8) + ((1 << i) * 4)];
        int length = ((bArr3.length - 32) - this.IRR_BYTES) - ((1 << i) * 4);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 64);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, bArr3.length);
        for (int i2 = 0; i2 < (1 << this.GFBITS); i2++) {
            iArr[i2] = Utils.load4(bArr3, (i2 * 4) + length);
        }
        pk_gen(bArr2, bArr, iArr, sArr, jArr);
        return bArr2;
    }

    public int getCipherTextSize() {
        return this.SYND_BYTES + 32;
    }

    public int getCondBytes() {
        return this.COND_BYTES;
    }

    public int getDefaultSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getIrrBytes() {
        return this.IRR_BYTES;
    }

    public int getPrivateKeySize() {
        return this.COND_BYTES + this.IRR_BYTES + (this.SYS_N / 8) + 40;
    }

    public int getPublicKeySize() {
        if (this.usePadding) {
            int i = this.PK_NROWS;
            return i * ((this.SYS_N / 8) - ((i - 1) / 8));
        }
        return (this.PK_NROWS * this.PK_NCOLS) / 8;
    }

    public int kem_dec(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[this.SYS_N / 8];
        int check_c_padding = this.usePadding ? check_c_padding(bArr2) : 0;
        byte decrypt = (byte) decrypt(bArr5, bArr3, bArr2);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 2);
        sHAKEDigest.update(bArr5, 0, bArr5.length);
        sHAKEDigest.doFinal(bArr4, 0, 32);
        byte b = 0;
        for (int i = 0; i < 32; i++) {
            b = (byte) (b | (bArr4[i] ^ bArr2[this.SYND_BYTES + i]));
        }
        short s = (short) (((short) (((short) (((short) (decrypt | b)) - 1)) >> 8)) & 255);
        byte[] bArr6 = new byte[(this.SYS_N / 8) + 1 + this.SYND_BYTES + 32];
        bArr6[0] = (byte) (s & 1);
        int i2 = 0;
        while (i2 < this.SYS_N / 8) {
            int i3 = i2 + 1;
            bArr6[i3] = (byte) ((bArr5[i2] & s) | ((~s) & bArr3[i2 + 40 + this.IRR_BYTES + this.COND_BYTES]));
            i2 = i3;
        }
        for (int i4 = 0; i4 < this.SYND_BYTES + 32; i4++) {
            bArr6[(this.SYS_N / 8) + 1 + i4] = bArr2[i4];
        }
        SHAKEDigest sHAKEDigest2 = new SHAKEDigest(256);
        sHAKEDigest2.update(bArr6, 0, bArr6.length);
        sHAKEDigest2.doFinal(bArr, 0, bArr.length);
        if (this.usePadding) {
            byte b2 = (byte) check_c_padding;
            for (int i5 = 0; i5 < bArr.length; i5++) {
                bArr[i5] = (byte) (bArr[i5] | b2);
            }
            return check_c_padding;
        }
        return 0;
    }

    public int kem_enc(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        byte[] bArr4 = new byte[this.SYS_N / 8];
        int check_pk_padding = this.usePadding ? check_pk_padding(bArr3) : 0;
        encrypt(bArr, bArr3, bArr4, secureRandom);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 2);
        sHAKEDigest.update(bArr4, 0, bArr4.length);
        sHAKEDigest.doFinal(bArr, this.SYND_BYTES, 32);
        sHAKEDigest.update((byte) 1);
        sHAKEDigest.update(bArr4, 0, bArr4.length);
        sHAKEDigest.update(bArr, 0, bArr.length);
        sHAKEDigest.doFinal(bArr2, 0, bArr2.length);
        if (this.usePadding) {
            byte b = (byte) (((byte) check_pk_padding) ^ 255);
            for (int i = 0; i < this.SYND_BYTES + 32; i++) {
                bArr[i] = (byte) (bArr[i] & b);
            }
            for (int i2 = 0; i2 < 32; i2++) {
                bArr2[i2] = (byte) (bArr2[i2] & b);
            }
            return check_pk_padding;
        }
        return 0;
    }

    public void kem_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        int i;
        int i2;
        short[] sArr;
        int i3;
        int i4;
        long j;
        int i5 = 32;
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = {64};
        secureRandom.nextBytes(bArr3);
        byte[] bArr5 = new byte[(this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + (this.SYS_T * 2) + 32];
        long[] jArr = {0};
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        byte[] bArr6 = bArr3;
        while (true) {
            sHAKEDigest.update(bArr4, 0, bArr4.length);
            sHAKEDigest.update(bArr3, 0, bArr3.length);
            sHAKEDigest.doFinal(bArr5, 0, bArr5.length);
            int length = bArr5.length - i5;
            byte[] copyOfRange = Arrays.copyOfRange(bArr5, length, length + 32);
            System.arraycopy(bArr6, 0, bArr2, 0, i5);
            byte[] copyOfRange2 = Arrays.copyOfRange(copyOfRange, 0, i5);
            int i6 = this.SYS_T;
            short[] sArr2 = new short[i6];
            int length2 = (bArr5.length - i5) - (i6 * 2);
            for (int i7 = 0; i7 < this.SYS_T; i7++) {
                sArr2[i7] = Utils.load_gf(bArr5, (i7 * 2) + length2, this.GFMASK);
            }
            if (generate_irr_poly(sArr2) != -1) {
                for (int i8 = 0; i8 < this.SYS_T; i8++) {
                    Utils.store_gf(bArr2, 40 + (i8 * 2), sArr2[i8]);
                }
                int i9 = this.GFBITS;
                int[] iArr = new int[1 << i9];
                i = length2 - ((1 << i9) * 4);
                int i10 = 0;
                while (true) {
                    i2 = this.GFBITS;
                    if (i10 >= (1 << i2)) {
                        break;
                    }
                    iArr[i10] = Utils.load4(bArr5, i + (i10 * 4));
                    i10++;
                }
                sArr = new short[1 << i2];
                if (pk_gen(bArr, bArr2, iArr, sArr, jArr) != -1) {
                    break;
                }
            }
            bArr3 = copyOfRange;
            bArr6 = copyOfRange2;
            i5 = 32;
        }
        byte[] bArr7 = new byte[this.COND_BYTES];
        controlbitsfrompermutation(bArr7, sArr, this.GFBITS, 1 << i3);
        System.arraycopy(bArr7, 0, bArr2, this.IRR_BYTES + 40, bArr7.length);
        int i11 = this.SYS_N;
        System.arraycopy(bArr5, i - (i11 / 8), bArr2, bArr2.length - (i11 / 8), i11 / 8);
        if (this.usePivots) {
            i4 = 32;
            j = jArr[0];
        } else {
            j = 4294967295L;
            i4 = 32;
        }
        Utils.store8(bArr2, i4, j);
    }
}
