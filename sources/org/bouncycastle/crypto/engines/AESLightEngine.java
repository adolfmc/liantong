package org.bouncycastle.crypto.engines;

import com.cjt2325.cameralibrary.CameraInterface;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: m1 */
    private static final int f26572m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f26573m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f26574m3 = 27;

    /* renamed from: m4 */
    private static final int f26575m4 = -1061109568;

    /* renamed from: m5 */
    private static final int f26576m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    /* renamed from: S */
    private static final byte[] f26570S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, C0548c.f1784h, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, C0548c.f1785i, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};

    /* renamed from: Si */
    private static final byte[] f26571Si = {82, 9, 106, -43, 48, C0548c.f1784h, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, C0548c.f1785i, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, CameraInterface.TYPE_CAPTURE};

    public AESLightEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity()));
    }

    private static int FFmulX(int i) {
        return (((i & (-2139062144)) >>> 7) * 27) ^ ((2139062143 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = i & (-1061109568);
        int i3 = i2 ^ (i2 >>> 1);
        return (i3 >>> 5) ^ (((1061109567 & i) << 2) ^ (i3 >>> 2));
    }

    private int bitsOfSecurity() {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            return 256;
        }
        return (iArr.length - 7) << 5;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = this.ROUNDS;
        int i4 = littleEndianToInt ^ iArr[i3][0];
        int i5 = littleEndianToInt2 ^ iArr[i3][1];
        int i6 = littleEndianToInt3 ^ iArr[i3][2];
        int i7 = i3 - 1;
        int i8 = littleEndianToInt4 ^ iArr[i3][3];
        while (i7 > 1) {
            byte[] bArr3 = f26571Si;
            int inv_mcol = inv_mcol((bArr3[(i5 >> 24) & 255] << 24) ^ (((bArr3[i4 & 255] & 255) ^ ((bArr3[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & 255) << 16))) ^ iArr[i7][0];
            byte[] bArr4 = f26571Si;
            int inv_mcol2 = inv_mcol((bArr4[(i6 >> 24) & 255] << 24) ^ (((bArr4[i5 & 255] & 255) ^ ((bArr4[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i8 >> 16) & 255] & 255) << 16))) ^ iArr[i7][1];
            byte[] bArr5 = f26571Si;
            int inv_mcol3 = inv_mcol((bArr5[(i8 >> 24) & 255] << 24) ^ (((bArr5[i6 & 255] & 255) ^ ((bArr5[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i7][2];
            byte[] bArr6 = f26571Si;
            int i9 = i7 - 1;
            int inv_mcol4 = inv_mcol((((bArr6[i8 & 255] & 255) ^ ((bArr6[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr6[(i4 >> 24) & 255] << 24)) ^ iArr[i7][3];
            byte[] bArr7 = f26571Si;
            int inv_mcol5 = inv_mcol((bArr7[(inv_mcol2 >> 24) & 255] << 24) ^ (((bArr7[inv_mcol & 255] & 255) ^ ((bArr7[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(inv_mcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i9][0];
            byte[] bArr8 = f26571Si;
            int inv_mcol6 = inv_mcol((bArr8[(inv_mcol3 >> 24) & 255] << 24) ^ (((bArr8[inv_mcol2 & 255] & 255) ^ ((bArr8[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr8[(inv_mcol4 >> 16) & 255] & 255) << 16))) ^ iArr[i9][1];
            byte[] bArr9 = f26571Si;
            int inv_mcol7 = inv_mcol((bArr9[(inv_mcol4 >> 24) & 255] << 24) ^ (((bArr9[inv_mcol3 & 255] & 255) ^ ((bArr9[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(inv_mcol >> 16) & 255] & 255) << 16))) ^ iArr[i9][2];
            byte[] bArr10 = f26571Si;
            int i10 = i9 - 1;
            i8 = inv_mcol((((bArr10[inv_mcol4 & 255] & 255) ^ ((bArr10[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr10[(inv_mcol >> 24) & 255] << 24)) ^ iArr[i9][3];
            i4 = inv_mcol5;
            i5 = inv_mcol6;
            i6 = inv_mcol7;
            i7 = i10;
        }
        byte[] bArr11 = f26571Si;
        int inv_mcol8 = inv_mcol((bArr11[(i5 >> 24) & 255] << 24) ^ (((bArr11[i4 & 255] & 255) ^ ((bArr11[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i6 >> 16) & 255] & 255) << 16))) ^ iArr[i7][0];
        byte[] bArr12 = f26571Si;
        int inv_mcol9 = inv_mcol((bArr12[(i6 >> 24) & 255] << 24) ^ (((bArr12[i5 & 255] & 255) ^ ((bArr12[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr12[(i8 >> 16) & 255] & 255) << 16))) ^ iArr[i7][1];
        byte[] bArr13 = f26571Si;
        int inv_mcol10 = inv_mcol((bArr13[(i8 >> 24) & 255] << 24) ^ (((bArr13[i6 & 255] & 255) ^ ((bArr13[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i7][2];
        byte[] bArr14 = f26571Si;
        int inv_mcol11 = inv_mcol((((bArr14[i8 & 255] & 255) ^ ((bArr14[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr14[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr14[(i4 >> 24) & 255] << 24)) ^ iArr[i7][3];
        byte[] bArr15 = f26571Si;
        int i11 = ((((bArr15[inv_mcol8 & 255] & 255) ^ ((bArr15[(inv_mcol11 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(inv_mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr15[(inv_mcol9 >> 24) & 255] << 24)) ^ iArr[0][0];
        int i12 = ((((bArr15[inv_mcol9 & 255] & 255) ^ ((bArr15[(inv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(inv_mcol11 >> 16) & 255] & 255) << 16)) ^ (bArr15[(inv_mcol10 >> 24) & 255] << 24)) ^ iArr[0][1];
        int i13 = ((((bArr15[inv_mcol10 & 255] & 255) ^ ((bArr15[(inv_mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(inv_mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr15[(inv_mcol11 >> 24) & 255] << 24)) ^ iArr[0][2];
        int i14 = ((((bArr15[inv_mcol11 & 255] & 255) ^ ((bArr15[(inv_mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(inv_mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr15[(inv_mcol8 >> 24) & 255] << 24)) ^ iArr[0][3];
        Pack.intToLittleEndian(i11, bArr2, i2 + 0);
        Pack.intToLittleEndian(i12, bArr2, i2 + 4);
        Pack.intToLittleEndian(i13, bArr2, i2 + 8);
        Pack.intToLittleEndian(i14, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = littleEndianToInt ^ iArr[0][0];
        int i4 = littleEndianToInt2 ^ iArr[0][1];
        int i5 = littleEndianToInt3 ^ iArr[0][2];
        int i6 = littleEndianToInt4 ^ iArr[0][3];
        int i7 = i5;
        int i8 = i4;
        int i9 = i3;
        int i10 = 1;
        while (i10 < this.ROUNDS - 1) {
            byte[] bArr3 = f26570S;
            int mcol = mcol((bArr3[(i6 >> 24) & 255] << 24) ^ (((bArr3[i9 & 255] & 255) ^ ((bArr3[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i10][0];
            byte[] bArr4 = f26570S;
            int mcol2 = mcol((bArr4[(i9 >> 24) & 255] << 24) ^ (((bArr4[i8 & 255] & 255) ^ ((bArr4[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i6 >> 16) & 255] & 255) << 16))) ^ iArr[i10][1];
            byte[] bArr5 = f26570S;
            int mcol3 = mcol((bArr5[(i8 >> 24) & 255] << 24) ^ (((bArr5[i7 & 255] & 255) ^ ((bArr5[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i9 >> 16) & 255] & 255) << 16))) ^ iArr[i10][2];
            byte[] bArr6 = f26570S;
            int mcol4 = mcol(((((bArr6[(i9 >> 8) & 255] & 255) << 8) ^ (bArr6[i6 & 255] & 255)) ^ ((bArr6[(i8 >> 16) & 255] & 255) << 16)) ^ (bArr6[(i7 >> 24) & 255] << 24));
            int i11 = i10 + 1;
            int i12 = iArr[i10][3] ^ mcol4;
            byte[] bArr7 = f26570S;
            i9 = mcol((bArr7[(i12 >> 24) & 255] << 24) ^ (((bArr7[mcol & 255] & 255) ^ ((bArr7[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(mcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i11][0];
            byte[] bArr8 = f26570S;
            int mcol5 = mcol((bArr8[(mcol >> 24) & 255] << 24) ^ (((bArr8[mcol2 & 255] & 255) ^ ((bArr8[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr8[(i12 >> 16) & 255] & 255) << 16))) ^ iArr[i11][1];
            byte[] bArr9 = f26570S;
            int mcol6 = mcol((bArr9[(mcol2 >> 24) & 255] << 24) ^ (((bArr9[mcol3 & 255] & 255) ^ ((bArr9[(i12 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(mcol >> 16) & 255] & 255) << 16))) ^ iArr[i11][2];
            byte[] bArr10 = f26570S;
            int i13 = i11 + 1;
            int mcol7 = mcol((((bArr10[i12 & 255] & 255) ^ ((bArr10[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr10[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr10[(mcol3 >> 24) & 255] << 24)) ^ iArr[i11][3];
            i8 = mcol5;
            i7 = mcol6;
            i6 = mcol7;
            i10 = i13;
        }
        byte[] bArr11 = f26570S;
        int mcol8 = mcol((bArr11[(i6 >> 24) & 255] << 24) ^ (((bArr11[i9 & 255] & 255) ^ ((bArr11[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i10][0];
        byte[] bArr12 = f26570S;
        int mcol9 = mcol((bArr12[(i9 >> 24) & 255] << 24) ^ (((bArr12[i8 & 255] & 255) ^ ((bArr12[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr12[(i6 >> 16) & 255] & 255) << 16))) ^ iArr[i10][1];
        byte[] bArr13 = f26570S;
        int mcol10 = mcol((bArr13[(i8 >> 24) & 255] << 24) ^ (((bArr13[i7 & 255] & 255) ^ ((bArr13[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(i9 >> 16) & 255] & 255) << 16))) ^ iArr[i10][2];
        byte[] bArr14 = f26570S;
        int mcol11 = mcol(((((bArr14[(i9 >> 8) & 255] & 255) << 8) ^ (bArr14[i6 & 255] & 255)) ^ ((bArr14[(i8 >> 16) & 255] & 255) << 16)) ^ (bArr14[(i7 >> 24) & 255] << 24));
        int i14 = i10 + 1;
        int i15 = iArr[i10][3] ^ mcol11;
        byte[] bArr15 = f26570S;
        int i16 = iArr[i14][0] ^ ((((bArr15[mcol8 & 255] & 255) ^ ((bArr15[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr15[(i15 >> 24) & 255] << 24));
        int i17 = ((((bArr15[mcol9 & 255] & 255) ^ ((bArr15[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(i15 >> 16) & 255] & 255) << 16)) ^ (bArr15[(mcol8 >> 24) & 255] << 24)) ^ iArr[i14][1];
        int i18 = ((((bArr15[mcol10 & 255] & 255) ^ ((bArr15[(i15 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr15[(mcol9 >> 24) & 255] << 24)) ^ iArr[i14][2];
        int i19 = ((((bArr15[i15 & 255] & 255) ^ ((bArr15[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr15[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr15[(mcol10 >> 24) & 255] << 24)) ^ iArr[i14][3];
        Pack.intToLittleEndian(i16, bArr2, i2 + 0);
        Pack.intToLittleEndian(i17, bArr2, i2 + 4);
        Pack.intToLittleEndian(i18, bArr2, i2 + 8);
        Pack.intToLittleEndian(i19, bArr2, i2 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        this.ROUNDS = i + 6;
        int[][] iArr = (int[][]) Array.newInstance(int.class, this.ROUNDS + 1, 4);
        int i2 = 8;
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            int i3 = littleEndianToInt2;
            int i4 = littleEndianToInt;
            int i5 = littleEndianToInt4;
            for (int i6 = 1; i6 <= 10; i6++) {
                i4 ^= subWord(shift(i5, 8)) ^ rcon[i6 - 1];
                iArr[i6][0] = i4;
                i3 ^= i4;
                iArr[i6][1] = i3;
                littleEndianToInt3 ^= i3;
                iArr[i6][2] = littleEndianToInt3;
                i5 ^= littleEndianToInt3;
                iArr[i6][3] = i5;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int i7 = littleEndianToInt5;
            int i8 = littleEndianToInt8;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i9 = 1;
            int i10 = 1;
            while (true) {
                iArr[i9][0] = littleEndianToInt9;
                iArr[i9][1] = littleEndianToInt10;
                int subWord = subWord(shift(littleEndianToInt10, 8)) ^ i10;
                int i11 = i10 << 1;
                int i12 = i7 ^ subWord;
                iArr[i9][2] = i12;
                int i13 = littleEndianToInt6 ^ i12;
                iArr[i9][3] = i13;
                int i14 = littleEndianToInt7 ^ i13;
                int i15 = i9 + 1;
                iArr[i15][0] = i14;
                int i16 = i8 ^ i14;
                iArr[i15][1] = i16;
                int i17 = littleEndianToInt9 ^ i16;
                iArr[i15][2] = i17;
                int i18 = littleEndianToInt10 ^ i17;
                iArr[i15][3] = i18;
                int subWord2 = subWord(shift(i18, 8)) ^ i11;
                i10 = i11 << 1;
                i7 = i12 ^ subWord2;
                int i19 = i9 + 2;
                iArr[i19][0] = i7;
                littleEndianToInt6 = i13 ^ i7;
                iArr[i19][1] = littleEndianToInt6;
                littleEndianToInt7 = i14 ^ littleEndianToInt6;
                iArr[i19][2] = littleEndianToInt7;
                i8 = i16 ^ littleEndianToInt7;
                iArr[i19][3] = i8;
                i9 += 3;
                if (i9 >= 13) {
                    break;
                }
                littleEndianToInt9 = i17 ^ i8;
                littleEndianToInt10 = i18 ^ littleEndianToInt9;
            }
        } else if (i != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i20 = 2;
            int i21 = littleEndianToInt17;
            int i22 = littleEndianToInt16;
            int i23 = littleEndianToInt15;
            int i24 = littleEndianToInt11;
            int i25 = 1;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, i2)) ^ i25;
                i25 <<= 1;
                i24 ^= subWord3;
                iArr[i20][0] = i24;
                littleEndianToInt12 ^= i24;
                iArr[i20][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i20][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i20][3] = littleEndianToInt14;
                int i26 = i20 + 1;
                if (i26 >= 15) {
                    break;
                }
                i23 ^= subWord(littleEndianToInt14);
                iArr[i26][0] = i23;
                i22 ^= i23;
                iArr[i26][1] = i22;
                i21 ^= i22;
                iArr[i26][2] = i21;
                littleEndianToInt18 ^= i21;
                iArr[i26][3] = littleEndianToInt18;
                i20 = i26 + 1;
                i2 = 8;
            }
        }
        if (!z) {
            for (int i27 = 1; i27 < this.ROUNDS; i27++) {
                for (int i28 = 0; i28 < 4; i28++) {
                    iArr[i27][i28] = inv_mcol(iArr[i27][i28]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = f26570S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return C0108a.f85c;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr != null) {
            if (i <= bArr.length - 16) {
                if (i2 <= bArr2.length - 16) {
                    if (this.forEncryption) {
                        encryptBlock(bArr, i, bArr2, i2, iArr);
                    } else {
                        decryptBlock(bArr, i, bArr2, i2, iArr);
                    }
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
