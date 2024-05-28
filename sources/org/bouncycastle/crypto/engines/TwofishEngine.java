package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;

    /* renamed from: P */
    private static final byte[][] f26657P = {new byte[]{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, Byte.MIN_VALUE, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 37, 11, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, C0548c.f1785i, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, Byte.MAX_VALUE, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, 15, 0, 111, -99, C0548c.f1784h, 66, 74, 94, -63, -32}, new byte[]{117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, -68, -99, 109, -63, -79, 14, Byte.MIN_VALUE, 93, -46, -43, -96, -124, 7, 20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, C0548c.f1784h, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, -114, 120, C0548c.f1785i, 88, 25, -115, -27, -104, 87, 103, Byte.MAX_VALUE, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i = 0; i < 256; i++) {
            int i2 = f26657P[0][i] & 255;
            iArr[0] = i2;
            iArr2[0] = Mx_X(i2) & 255;
            iArr3[0] = Mx_Y(i2) & 255;
            int i3 = f26657P[1][i] & 255;
            iArr[1] = i3;
            iArr2[1] = Mx_X(i3) & 255;
            iArr3[1] = Mx_Y(i3) & 255;
            this.gMDS0[i] = iArr[1] | (iArr2[1] << 8) | (iArr3[1] << 16) | (iArr3[1] << 24);
            this.gMDS1[i] = iArr3[0] | (iArr3[0] << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            this.gMDS2[i] = (iArr3[1] << 24) | iArr2[1] | (iArr3[1] << 8) | (iArr[1] << 16);
            this.gMDS3[i] = iArr2[0] | (iArr[0] << 8) | (iArr3[0] << 16) | (iArr2[0] << 24);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int F32(int i, int[] iArr) {
        int i2;
        int i3;
        int m276b0 = m276b0(i);
        int m275b1 = m275b1(i);
        int m274b2 = m274b2(i);
        int m273b3 = m273b3(i);
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        switch (3 & this.k64Cnt) {
            case 0:
                m276b0 = (f26657P[1][m276b0] & 255) ^ m276b0(i7);
                m275b1 = (f26657P[0][m275b1] & 255) ^ m275b1(i7);
                m274b2 = (f26657P[0][m274b2] & 255) ^ m274b2(i7);
                m273b3 = (f26657P[1][m273b3] & 255) ^ m273b3(i7);
                m276b0 = m276b0(i6) ^ (f26657P[1][m276b0] & 255);
                m275b1 = m275b1(i6) ^ (f26657P[1][m275b1] & 255);
                m274b2 = m274b2(i6) ^ (f26657P[0][m274b2] & 255);
                m273b3 = (f26657P[0][m273b3] & 255) ^ m273b3(i6);
                int[] iArr2 = this.gMDS0;
                byte[][] bArr = f26657P;
                int i8 = iArr2[(bArr[0][(bArr[0][m276b0] & 255) ^ m276b0(i5)] & 255) ^ m276b0(i4)];
                int[] iArr3 = this.gMDS1;
                byte[][] bArr2 = f26657P;
                int i9 = i8 ^ iArr3[(bArr2[0][(bArr2[1][m275b1] & 255) ^ m275b1(i5)] & 255) ^ m275b1(i4)];
                int[] iArr4 = this.gMDS2;
                byte[][] bArr3 = f26657P;
                i2 = i9 ^ iArr4[(bArr3[1][(bArr3[0][m274b2] & 255) ^ m274b2(i5)] & 255) ^ m274b2(i4)];
                int[] iArr5 = this.gMDS3;
                byte[][] bArr4 = f26657P;
                i3 = iArr5[(bArr4[1][(bArr4[1][m273b3] & 255) ^ m273b3(i5)] & 255) ^ m273b3(i4)];
                break;
            case 1:
                i2 = (this.gMDS0[(f26657P[0][m276b0] & 255) ^ m276b0(i4)] ^ this.gMDS1[(f26657P[0][m275b1] & 255) ^ m275b1(i4)]) ^ this.gMDS2[(f26657P[1][m274b2] & 255) ^ m274b2(i4)];
                i3 = this.gMDS3[(f26657P[1][m273b3] & 255) ^ m273b3(i4)];
                break;
            case 2:
                int[] iArr22 = this.gMDS0;
                byte[][] bArr5 = f26657P;
                int i82 = iArr22[(bArr5[0][(bArr5[0][m276b0] & 255) ^ m276b0(i5)] & 255) ^ m276b0(i4)];
                int[] iArr32 = this.gMDS1;
                byte[][] bArr22 = f26657P;
                int i92 = i82 ^ iArr32[(bArr22[0][(bArr22[1][m275b1] & 255) ^ m275b1(i5)] & 255) ^ m275b1(i4)];
                int[] iArr42 = this.gMDS2;
                byte[][] bArr32 = f26657P;
                i2 = i92 ^ iArr42[(bArr32[1][(bArr32[0][m274b2] & 255) ^ m274b2(i5)] & 255) ^ m274b2(i4)];
                int[] iArr52 = this.gMDS3;
                byte[][] bArr42 = f26657P;
                i3 = iArr52[(bArr42[1][(bArr42[1][m273b3] & 255) ^ m273b3(i5)] & 255) ^ m273b3(i4)];
                break;
            case 3:
                m276b0 = m276b0(i6) ^ (f26657P[1][m276b0] & 255);
                m275b1 = m275b1(i6) ^ (f26657P[1][m275b1] & 255);
                m274b2 = m274b2(i6) ^ (f26657P[0][m274b2] & 255);
                m273b3 = (f26657P[0][m273b3] & 255) ^ m273b3(i6);
                int[] iArr222 = this.gMDS0;
                byte[][] bArr52 = f26657P;
                int i822 = iArr222[(bArr52[0][(bArr52[0][m276b0] & 255) ^ m276b0(i5)] & 255) ^ m276b0(i4)];
                int[] iArr322 = this.gMDS1;
                byte[][] bArr222 = f26657P;
                int i922 = i822 ^ iArr322[(bArr222[0][(bArr222[1][m275b1] & 255) ^ m275b1(i5)] & 255) ^ m275b1(i4)];
                int[] iArr422 = this.gMDS2;
                byte[][] bArr322 = f26657P;
                i2 = i922 ^ iArr422[(bArr322[1][(bArr322[0][m274b2] & 255) ^ m274b2(i5)] & 255) ^ m274b2(i4)];
                int[] iArr522 = this.gMDS3;
                byte[][] bArr422 = f26657P;
                i3 = iArr522[(bArr422[1][(bArr422[1][m273b3] & 255) ^ m273b3(i5)] & 255) ^ m273b3(i4)];
                break;
            default:
                return 0;
        }
        return i2 ^ i3;
    }

    private int Fe32_0(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 24) & 255) * 2) + 513] ^ ((iArr[((i & 255) * 2) + 0] ^ iArr[(((i >>> 8) & 255) * 2) + 1]) ^ iArr[(((i >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 16) & 255) * 2) + 513] ^ ((iArr[(((i >>> 24) & 255) * 2) + 0] ^ iArr[((i & 255) * 2) + 1]) ^ iArr[(((i >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int i) {
        return ((i & 1) != 0 ? 180 : 0) ^ (i >> 1);
    }

    private int LFSR2(int i) {
        return ((i >> 2) ^ ((i & 2) != 0 ? 180 : 0)) ^ ((i & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int i) {
        return i ^ LFSR2(i);
    }

    private int Mx_Y(int i) {
        return LFSR2(i) ^ (LFSR1(i) ^ i);
    }

    private int RS_MDS_Encode(int i, int i2) {
        int i3 = i2;
        for (int i4 = 0; i4 < 4; i4++) {
            i3 = RS_rem(i3);
        }
        int i5 = i ^ i3;
        for (int i6 = 0; i6 < 4; i6++) {
            i5 = RS_rem(i5);
        }
        return i5;
    }

    private int RS_rem(int i) {
        int i2 = (i >>> 24) & 255;
        int i3 = ((i2 << 1) ^ ((i2 & 128) != 0 ? 333 : 0)) & 255;
        int i4 = ((i2 >>> 1) ^ ((i2 & 1) != 0 ? 166 : 0)) ^ i3;
        return ((((i << 8) ^ (i4 << 24)) ^ (i3 << 16)) ^ (i4 << 8)) ^ i2;
    }

    /* renamed from: b0 */
    private int m276b0(int i) {
        return i & 255;
    }

    /* renamed from: b1 */
    private int m275b1(int i) {
        return (i >>> 8) & 255;
    }

    /* renamed from: b2 */
    private int m274b2(int i) {
        return (i >>> 16) & 255;
    }

    /* renamed from: b3 */
    private int m273b3(int i) {
        return (i >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i) ^ this.gSubKeys[4];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4) ^ this.gSubKeys[5];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8) ^ this.gSubKeys[6];
        int i3 = 39;
        int i4 = littleEndianToInt2;
        int i5 = littleEndianToInt;
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12) ^ this.gSubKeys[7];
        int i6 = 0;
        while (i6 < 16) {
            int Fe32_0 = Fe32_0(i5);
            int Fe32_3 = Fe32_3(i4);
            int i7 = i3 - 1;
            int i8 = Fe32_0 + Fe32_3;
            int i9 = i7 - 1;
            littleEndianToInt3 = Integers.rotateLeft(littleEndianToInt3, 1) ^ (i8 + this.gSubKeys[i7]);
            littleEndianToInt4 = Integers.rotateRight(littleEndianToInt4 ^ (((Fe32_3 * 2) + Fe32_0) + this.gSubKeys[i3]), 1);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int i10 = i9 - 1;
            i5 = Integers.rotateLeft(i5, 1) ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[i10]);
            i4 = Integers.rotateRight(i4 ^ (((Fe32_32 * 2) + Fe32_02) + this.gSubKeys[i9]), 1);
            i6 += 2;
            i3 = i10 - 1;
        }
        Pack.intToLittleEndian(this.gSubKeys[0] ^ littleEndianToInt3, bArr2, i2);
        Pack.intToLittleEndian(this.gSubKeys[1] ^ littleEndianToInt4, bArr2, i2 + 4);
        Pack.intToLittleEndian(this.gSubKeys[2] ^ i5, bArr2, i2 + 8);
        Pack.intToLittleEndian(this.gSubKeys[3] ^ i4, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i) ^ this.gSubKeys[0];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4) ^ this.gSubKeys[1];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8) ^ this.gSubKeys[2];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12) ^ this.gSubKeys[3];
        int i4 = 8;
        while (i3 < 16) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            int i5 = i4 + 1;
            littleEndianToInt3 = Integers.rotateRight(littleEndianToInt3 ^ ((Fe32_0 + Fe32_3) + this.gSubKeys[i4]), 1);
            int i6 = Fe32_0 + (Fe32_3 * 2);
            int i7 = i5 + 1;
            littleEndianToInt4 = Integers.rotateLeft(littleEndianToInt4, 1) ^ (i6 + this.gSubKeys[i5]);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int i8 = i7 + 1;
            littleEndianToInt = Integers.rotateRight(littleEndianToInt ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[i7]), 1);
            littleEndianToInt2 = Integers.rotateLeft(littleEndianToInt2, 1) ^ ((Fe32_02 + (Fe32_32 * 2)) + this.gSubKeys[i8]);
            i3 += 2;
            i4 = i8 + 1;
        }
        Pack.intToLittleEndian(this.gSubKeys[4] ^ littleEndianToInt3, bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[5], bArr2, i2 + 4);
        Pack.intToLittleEndian(this.gSubKeys[6] ^ littleEndianToInt, bArr2, i2 + 8);
        Pack.intToLittleEndian(this.gSubKeys[7] ^ littleEndianToInt2, bArr2, i2 + 12);
    }

    private void setKey(byte[] bArr) {
        int m276b0;
        int m275b1;
        int m274b2;
        int m273b3;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.gSubKeys = new int[40];
        for (int i5 = 0; i5 < this.k64Cnt; i5++) {
            int i6 = i5 * 8;
            iArr[i5] = Pack.littleEndianToInt(bArr, i6);
            iArr2[i5] = Pack.littleEndianToInt(bArr, i6 + 4);
            iArr3[(this.k64Cnt - 1) - i5] = RS_MDS_Encode(iArr[i5], iArr2[i5]);
        }
        for (int i7 = 0; i7 < 20; i7++) {
            int i8 = 33686018 * i7;
            int F32 = F32(i8, iArr);
            int rotateLeft = Integers.rotateLeft(F32(i8 + 16843009, iArr2), 8);
            int i9 = F32 + rotateLeft;
            int[] iArr4 = this.gSubKeys;
            int i10 = i7 * 2;
            iArr4[i10] = i9;
            int i11 = i9 + rotateLeft;
            iArr4[i10 + 1] = (i11 << 9) | (i11 >>> 23);
        }
        int i12 = iArr3[0];
        int i13 = iArr3[1];
        int i14 = iArr3[2];
        int i15 = iArr3[3];
        this.gSBox = new int[1024];
        for (int i16 = 0; i16 < 256; i16++) {
            switch (this.k64Cnt & 3) {
                case 0:
                    m276b0 = (f26657P[1][i16] & 255) ^ m276b0(i15);
                    m275b1 = (f26657P[0][i16] & 255) ^ m275b1(i15);
                    m274b2 = (f26657P[0][i16] & 255) ^ m274b2(i15);
                    m273b3 = (f26657P[1][i16] & 255) ^ m273b3(i15);
                    i = (f26657P[1][m276b0] & 255) ^ m276b0(i14);
                    i2 = (f26657P[1][m275b1] & 255) ^ m275b1(i14);
                    i3 = (f26657P[0][m274b2] & 255) ^ m274b2(i14);
                    i4 = (f26657P[0][m273b3] & 255) ^ m273b3(i14);
                    break;
                case 1:
                    int i17 = i16 * 2;
                    this.gSBox[i17] = this.gMDS0[(f26657P[0][i16] & 255) ^ m276b0(i12)];
                    this.gSBox[i17 + 1] = this.gMDS1[(f26657P[0][i16] & 255) ^ m275b1(i12)];
                    this.gSBox[i17 + 512] = this.gMDS2[(f26657P[1][i16] & 255) ^ m274b2(i12)];
                    this.gSBox[i17 + 513] = this.gMDS3[(f26657P[1][i16] & 255) ^ m273b3(i12)];
                    continue;
                case 2:
                    i = i16;
                    i2 = i;
                    i3 = i2;
                    i4 = i3;
                    break;
                case 3:
                    m276b0 = i16;
                    m275b1 = m276b0;
                    m274b2 = m275b1;
                    m273b3 = m274b2;
                    i = (f26657P[1][m276b0] & 255) ^ m276b0(i14);
                    i2 = (f26657P[1][m275b1] & 255) ^ m275b1(i14);
                    i3 = (f26657P[0][m274b2] & 255) ^ m274b2(i14);
                    i4 = (f26657P[0][m273b3] & 255) ^ m273b3(i14);
                    break;
                default:
            }
            int[] iArr5 = this.gSBox;
            int i18 = i16 * 2;
            int[] iArr6 = this.gMDS0;
            byte[][] bArr2 = f26657P;
            iArr5[i18] = iArr6[(bArr2[0][(bArr2[0][i] & 255) ^ m276b0(i13)] & 255) ^ m276b0(i12)];
            int[] iArr7 = this.gMDS1;
            byte[][] bArr3 = f26657P;
            this.gSBox[i18 + 1] = iArr7[(bArr3[0][(bArr3[1][i2] & 255) ^ m275b1(i13)] & 255) ^ m275b1(i12)];
            int[] iArr8 = this.gMDS2;
            byte[][] bArr4 = f26657P;
            this.gSBox[i18 + 512] = iArr8[(bArr4[1][(bArr4[0][i3] & 255) ^ m274b2(i13)] & 255) ^ m274b2(i12)];
            int[] iArr9 = this.gMDS3;
            byte[][] bArr5 = f26657P;
            this.gSBox[i18 + 513] = iArr9[(bArr5[1][(bArr5[1][i4] & 255) ^ m273b3(i13)] & 255) ^ m273b3(i12)];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        this.workingKey = ((KeyParameter) cipherParameters).getKey();
        int length = this.workingKey.length * 8;
        if (length != 128 && length != 192 && length != 256) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), length, cipherParameters, Utils.getPurpose(z)));
        byte[] bArr = this.workingKey;
        this.k64Cnt = bArr.length / 8;
        setKey(bArr);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    if (this.encrypting) {
                        encryptBlock(bArr, i, bArr2, i2);
                        return 16;
                    }
                    decryptBlock(bArr, i, bArr2, i2);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Twofish not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
