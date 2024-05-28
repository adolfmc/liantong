package com.utils.sm4;

import com.cjt2325.cameralibrary.CameraInterface;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SM4 {
    int[] key_r;

    private static int jointBytes(byte b, byte b2, byte b3, byte b4) {
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    private static int shift(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    public SM4(byte[] bArr) {
        this.key_r = keyGenerate(bArr);
    }

    private int[] keyGenerate(byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = new int[4];
        int[] iArr3 = {-1548633402, 1453994832, 1736282519, -1301273892};
        int[] iArr4 = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
        for (int i = 0; i < 4; i++) {
            int i2 = i * 4;
            iArr2[i] = jointBytes(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]);
            iArr2[i] = iArr2[i] ^ iArr3[i];
        }
        for (int i3 = 0; i3 < 32; i3++) {
            int sBox = sBox(((iArr2[1] ^ iArr2[2]) ^ iArr2[3]) ^ iArr4[i3]);
            iArr[i3] = shift(sBox, 23) ^ ((iArr2[0] ^ sBox) ^ shift(sBox, 13));
            iArr2[0] = iArr2[1];
            iArr2[1] = iArr2[2];
            iArr2[2] = iArr2[3];
            iArr2[3] = iArr[i3];
        }
        return iArr;
    }

    private static byte[] sm4Main(byte[] bArr, int[] iArr, int i) {
        int[] iArr2 = new int[4];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 4;
            iArr2[i2] = jointBytes(bArr[i3], bArr[i3 + 1], bArr[i3 + 2], bArr[i3 + 3]);
        }
        for (int i4 = 0; i4 < 32; i4++) {
            int sBox = sBox(iArr[i == 0 ? i4 : 31 - i4] ^ ((iArr2[1] ^ iArr2[2]) ^ iArr2[3]));
            int shift = shift(sBox, 24) ^ ((((iArr2[0] ^ sBox) ^ shift(sBox, 2)) ^ shift(sBox, 10)) ^ shift(sBox, 18));
            iArr2[0] = iArr2[1];
            iArr2[1] = iArr2[2];
            iArr2[2] = iArr2[3];
            iArr2[3] = shift;
        }
        byte[] bArr2 = new byte[16];
        for (int i5 = 0; i5 < 4; i5++) {
            System.arraycopy(splitInt(iArr2[3 - i5]), 0, bArr2, i5 * 4, 4);
        }
        return bArr2;
    }

    public byte[] encrypt(byte[] bArr) {
        return sm4Main(bArr, this.key_r, 0);
    }

    public byte[] decrypt(byte[] bArr) {
        return sm4Main(bArr, this.key_r, 1);
    }

    private static byte[] splitInt(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    private static int sBox(int i) {
        int[] iArr = {214, 144, 233, 254, 204, 225, 61, 183, 22, 182, 20, 194, 40, 251, 44, 5, 43, 103, 154, 118, 42, 190, 4, 195, 170, 68, 19, 38, 73, 134, 6, 153, 156, 66, 80, 244, CameraInterface.TYPE_CAPTURE, 239, 152, 122, 51, 84, 11, 67, 237, 207, 172, 98, 228, 179, 28, 169, 201, 8, 232, 149, 128, 223, 148, 250, 117, 143, 63, 166, 71, 7, 167, 252, 243, 115, 23, 186, 131, 89, 60, 25, 230, 133, 79, 168, 104, 107, 129, 178, 113, 100, 218, 139, 248, 235, 15, 75, 112, 86, 157, 53, 30, 36, 14, 94, 99, 88, 209, 162, 37, 34, 124, 59, 1, 33, 120, 135, 212, 0, 70, 87, 159, 211, 39, 82, 76, 54, 2, 231, C0567f.f1819h, 196, 200, 158, 234, 191, 138, 210, 64, 199, 56, 181, 163, 247, 242, 206, 249, 97, 21, 161, 224, 174, 93, 164, 155, 52, 26, 85, 173, 147, 50, 48, 245, 140, 177, 227, 29, 246, 226, 46, 130, 102, 202, 96, 192, 41, 35, 171, 13, 83, 78, 111, 213, 219, 55, 69, 222, 253, 142, 47, 3, 255, 106, 114, 109, 108, 91, 81, 141, 27, 175, 146, 187, 221, 188, 127, 17, 217, 92, 65, 31, 16, 90, 216, 10, 193, 49, 136, 165, 205, 123, 189, 45, 116, 208, 18, 184, 229, 180, 176, 137, 105, 151, 74, 12, 150, 119, 126, 101, 185, 241, 9, 197, 110, 198, 132, 24, 240, 125, 236, 58, 220, 77, 32, 121, 238, 95, 62, 215, 203, 57, 72};
        byte[] splitInt = splitInt(i);
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) iArr[splitInt[i2] & 255];
        }
        return jointBytes(bArr[0], bArr[1], bArr[2], bArr[3]);
    }
}
