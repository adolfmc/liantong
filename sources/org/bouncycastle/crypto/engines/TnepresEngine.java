package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class TnepresEngine extends SerpentEngineBase {
    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase
    protected void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = {Pack.bigEndianToInt(bArr, i + 12) ^ r1, this.wKey[129] ^ Pack.bigEndianToInt(bArr, i + 8), this.wKey[130] ^ Pack.bigEndianToInt(bArr, i + 4), this.wKey[131] ^ Pack.bigEndianToInt(bArr, i)};
        int i3 = this.wKey[128];
        ib7(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[124];
        iArr[1] = iArr[1] ^ this.wKey[125];
        iArr[2] = iArr[2] ^ this.wKey[126];
        iArr[3] = iArr[3] ^ this.wKey[127];
        inverseLT(iArr);
        ib6(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[120];
        iArr[1] = iArr[1] ^ this.wKey[121];
        iArr[2] = iArr[2] ^ this.wKey[122];
        iArr[3] = iArr[3] ^ this.wKey[123];
        inverseLT(iArr);
        ib5(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[116];
        iArr[1] = iArr[1] ^ this.wKey[117];
        iArr[2] = iArr[2] ^ this.wKey[118];
        iArr[3] = iArr[3] ^ this.wKey[119];
        inverseLT(iArr);
        ib4(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[112];
        iArr[1] = iArr[1] ^ this.wKey[113];
        iArr[2] = iArr[2] ^ this.wKey[114];
        iArr[3] = iArr[3] ^ this.wKey[115];
        inverseLT(iArr);
        ib3(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[108];
        iArr[1] = iArr[1] ^ this.wKey[109];
        iArr[2] = iArr[2] ^ this.wKey[110];
        iArr[3] = iArr[3] ^ this.wKey[111];
        inverseLT(iArr);
        ib2(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[104];
        iArr[1] = iArr[1] ^ this.wKey[105];
        iArr[2] = iArr[2] ^ this.wKey[106];
        iArr[3] = iArr[3] ^ this.wKey[107];
        inverseLT(iArr);
        ib1(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[100];
        iArr[1] = iArr[1] ^ this.wKey[101];
        iArr[2] = iArr[2] ^ this.wKey[102];
        iArr[3] = iArr[3] ^ this.wKey[103];
        inverseLT(iArr);
        ib0(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[96];
        iArr[1] = iArr[1] ^ this.wKey[97];
        iArr[2] = iArr[2] ^ this.wKey[98];
        iArr[3] = iArr[3] ^ this.wKey[99];
        inverseLT(iArr);
        ib7(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[92];
        iArr[1] = iArr[1] ^ this.wKey[93];
        iArr[2] = iArr[2] ^ this.wKey[94];
        iArr[3] = iArr[3] ^ this.wKey[95];
        inverseLT(iArr);
        ib6(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[88];
        iArr[1] = iArr[1] ^ this.wKey[89];
        iArr[2] = iArr[2] ^ this.wKey[90];
        iArr[3] = iArr[3] ^ this.wKey[91];
        inverseLT(iArr);
        ib5(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[84];
        iArr[1] = iArr[1] ^ this.wKey[85];
        iArr[2] = iArr[2] ^ this.wKey[86];
        iArr[3] = iArr[3] ^ this.wKey[87];
        inverseLT(iArr);
        ib4(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[80];
        iArr[1] = iArr[1] ^ this.wKey[81];
        iArr[2] = iArr[2] ^ this.wKey[82];
        iArr[3] = iArr[3] ^ this.wKey[83];
        inverseLT(iArr);
        ib3(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[76];
        iArr[1] = iArr[1] ^ this.wKey[77];
        iArr[2] = iArr[2] ^ this.wKey[78];
        iArr[3] = iArr[3] ^ this.wKey[79];
        inverseLT(iArr);
        ib2(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[72];
        iArr[1] = iArr[1] ^ this.wKey[73];
        iArr[2] = iArr[2] ^ this.wKey[74];
        iArr[3] = iArr[3] ^ this.wKey[75];
        inverseLT(iArr);
        ib1(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[68];
        iArr[1] = iArr[1] ^ this.wKey[69];
        iArr[2] = iArr[2] ^ this.wKey[70];
        iArr[3] = iArr[3] ^ this.wKey[71];
        inverseLT(iArr);
        ib0(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[64];
        iArr[1] = iArr[1] ^ this.wKey[65];
        iArr[2] = iArr[2] ^ this.wKey[66];
        iArr[3] = iArr[3] ^ this.wKey[67];
        inverseLT(iArr);
        ib7(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[60];
        iArr[1] = iArr[1] ^ this.wKey[61];
        iArr[2] = iArr[2] ^ this.wKey[62];
        iArr[3] = iArr[3] ^ this.wKey[63];
        inverseLT(iArr);
        ib6(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[56];
        iArr[1] = iArr[1] ^ this.wKey[57];
        iArr[2] = iArr[2] ^ this.wKey[58];
        iArr[3] = iArr[3] ^ this.wKey[59];
        inverseLT(iArr);
        ib5(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[52];
        iArr[1] = iArr[1] ^ this.wKey[53];
        iArr[2] = iArr[2] ^ this.wKey[54];
        iArr[3] = iArr[3] ^ this.wKey[55];
        inverseLT(iArr);
        ib4(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[48];
        iArr[1] = iArr[1] ^ this.wKey[49];
        iArr[2] = iArr[2] ^ this.wKey[50];
        iArr[3] = iArr[3] ^ this.wKey[51];
        inverseLT(iArr);
        ib3(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[44];
        iArr[1] = iArr[1] ^ this.wKey[45];
        iArr[2] = iArr[2] ^ this.wKey[46];
        iArr[3] = iArr[3] ^ this.wKey[47];
        inverseLT(iArr);
        ib2(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[40];
        iArr[1] = iArr[1] ^ this.wKey[41];
        iArr[2] = iArr[2] ^ this.wKey[42];
        iArr[3] = iArr[3] ^ this.wKey[43];
        inverseLT(iArr);
        ib1(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[36];
        iArr[1] = iArr[1] ^ this.wKey[37];
        iArr[2] = iArr[2] ^ this.wKey[38];
        iArr[3] = iArr[3] ^ this.wKey[39];
        inverseLT(iArr);
        ib0(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[32];
        iArr[1] = iArr[1] ^ this.wKey[33];
        iArr[2] = iArr[2] ^ this.wKey[34];
        iArr[3] = iArr[3] ^ this.wKey[35];
        inverseLT(iArr);
        ib7(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[28];
        iArr[1] = iArr[1] ^ this.wKey[29];
        iArr[2] = iArr[2] ^ this.wKey[30];
        iArr[3] = iArr[3] ^ this.wKey[31];
        inverseLT(iArr);
        ib6(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[24];
        iArr[1] = iArr[1] ^ this.wKey[25];
        iArr[2] = iArr[2] ^ this.wKey[26];
        iArr[3] = iArr[3] ^ this.wKey[27];
        inverseLT(iArr);
        ib5(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[20];
        iArr[1] = iArr[1] ^ this.wKey[21];
        iArr[2] = iArr[2] ^ this.wKey[22];
        iArr[3] = iArr[3] ^ this.wKey[23];
        inverseLT(iArr);
        ib4(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[16];
        iArr[1] = iArr[1] ^ this.wKey[17];
        iArr[2] = iArr[2] ^ this.wKey[18];
        iArr[3] = iArr[3] ^ this.wKey[19];
        inverseLT(iArr);
        ib3(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[12];
        iArr[1] = iArr[1] ^ this.wKey[13];
        iArr[2] = iArr[2] ^ this.wKey[14];
        iArr[3] = iArr[3] ^ this.wKey[15];
        inverseLT(iArr);
        ib2(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[8];
        iArr[1] = iArr[1] ^ this.wKey[9];
        iArr[2] = iArr[2] ^ this.wKey[10];
        iArr[3] = iArr[3] ^ this.wKey[11];
        inverseLT(iArr);
        ib1(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        iArr[0] = iArr[0] ^ this.wKey[4];
        iArr[1] = iArr[1] ^ this.wKey[5];
        iArr[2] = iArr[2] ^ this.wKey[6];
        iArr[3] = iArr[3] ^ this.wKey[7];
        inverseLT(iArr);
        ib0(iArr, iArr[0], iArr[1], iArr[2], iArr[3]);
        Pack.intToBigEndian(iArr[3] ^ this.wKey[3], bArr2, i2);
        Pack.intToBigEndian(iArr[2] ^ this.wKey[2], bArr2, i2 + 4);
        Pack.intToBigEndian(iArr[1] ^ this.wKey[1], bArr2, i2 + 8);
        Pack.intToBigEndian(iArr[0] ^ this.wKey[0], bArr2, i2 + 12);
    }

    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase
    protected void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = {Pack.bigEndianToInt(bArr, i + 12), Pack.bigEndianToInt(bArr, i + 8), Pack.bigEndianToInt(bArr, i + 4), Pack.bigEndianToInt(bArr, i)};
        sb0(iArr, this.wKey[0] ^ iArr[0], this.wKey[1] ^ iArr[1], this.wKey[2] ^ iArr[2], this.wKey[3] ^ iArr[3]);
        m279LT(iArr);
        sb1(iArr, this.wKey[4] ^ iArr[0], this.wKey[5] ^ iArr[1], this.wKey[6] ^ iArr[2], this.wKey[7] ^ iArr[3]);
        m279LT(iArr);
        sb2(iArr, this.wKey[8] ^ iArr[0], this.wKey[9] ^ iArr[1], this.wKey[10] ^ iArr[2], this.wKey[11] ^ iArr[3]);
        m279LT(iArr);
        sb3(iArr, this.wKey[12] ^ iArr[0], this.wKey[13] ^ iArr[1], this.wKey[14] ^ iArr[2], this.wKey[15] ^ iArr[3]);
        m279LT(iArr);
        sb4(iArr, this.wKey[16] ^ iArr[0], this.wKey[17] ^ iArr[1], this.wKey[18] ^ iArr[2], this.wKey[19] ^ iArr[3]);
        m279LT(iArr);
        sb5(iArr, this.wKey[20] ^ iArr[0], this.wKey[21] ^ iArr[1], this.wKey[22] ^ iArr[2], this.wKey[23] ^ iArr[3]);
        m279LT(iArr);
        sb6(iArr, this.wKey[24] ^ iArr[0], this.wKey[25] ^ iArr[1], this.wKey[26] ^ iArr[2], this.wKey[27] ^ iArr[3]);
        m279LT(iArr);
        sb7(iArr, this.wKey[28] ^ iArr[0], this.wKey[29] ^ iArr[1], this.wKey[30] ^ iArr[2], this.wKey[31] ^ iArr[3]);
        m279LT(iArr);
        sb0(iArr, this.wKey[32] ^ iArr[0], this.wKey[33] ^ iArr[1], this.wKey[34] ^ iArr[2], this.wKey[35] ^ iArr[3]);
        m279LT(iArr);
        sb1(iArr, this.wKey[36] ^ iArr[0], this.wKey[37] ^ iArr[1], this.wKey[38] ^ iArr[2], this.wKey[39] ^ iArr[3]);
        m279LT(iArr);
        sb2(iArr, this.wKey[40] ^ iArr[0], this.wKey[41] ^ iArr[1], this.wKey[42] ^ iArr[2], this.wKey[43] ^ iArr[3]);
        m279LT(iArr);
        sb3(iArr, this.wKey[44] ^ iArr[0], this.wKey[45] ^ iArr[1], this.wKey[46] ^ iArr[2], this.wKey[47] ^ iArr[3]);
        m279LT(iArr);
        sb4(iArr, this.wKey[48] ^ iArr[0], this.wKey[49] ^ iArr[1], this.wKey[50] ^ iArr[2], this.wKey[51] ^ iArr[3]);
        m279LT(iArr);
        sb5(iArr, this.wKey[52] ^ iArr[0], this.wKey[53] ^ iArr[1], this.wKey[54] ^ iArr[2], this.wKey[55] ^ iArr[3]);
        m279LT(iArr);
        sb6(iArr, this.wKey[56] ^ iArr[0], this.wKey[57] ^ iArr[1], this.wKey[58] ^ iArr[2], this.wKey[59] ^ iArr[3]);
        m279LT(iArr);
        sb7(iArr, this.wKey[60] ^ iArr[0], this.wKey[61] ^ iArr[1], this.wKey[62] ^ iArr[2], this.wKey[63] ^ iArr[3]);
        m279LT(iArr);
        sb0(iArr, this.wKey[64] ^ iArr[0], this.wKey[65] ^ iArr[1], this.wKey[66] ^ iArr[2], this.wKey[67] ^ iArr[3]);
        m279LT(iArr);
        sb1(iArr, this.wKey[68] ^ iArr[0], this.wKey[69] ^ iArr[1], this.wKey[70] ^ iArr[2], this.wKey[71] ^ iArr[3]);
        m279LT(iArr);
        sb2(iArr, this.wKey[72] ^ iArr[0], this.wKey[73] ^ iArr[1], this.wKey[74] ^ iArr[2], this.wKey[75] ^ iArr[3]);
        m279LT(iArr);
        sb3(iArr, this.wKey[76] ^ iArr[0], this.wKey[77] ^ iArr[1], this.wKey[78] ^ iArr[2], this.wKey[79] ^ iArr[3]);
        m279LT(iArr);
        sb4(iArr, this.wKey[80] ^ iArr[0], this.wKey[81] ^ iArr[1], this.wKey[82] ^ iArr[2], this.wKey[83] ^ iArr[3]);
        m279LT(iArr);
        sb5(iArr, this.wKey[84] ^ iArr[0], this.wKey[85] ^ iArr[1], this.wKey[86] ^ iArr[2], this.wKey[87] ^ iArr[3]);
        m279LT(iArr);
        sb6(iArr, this.wKey[88] ^ iArr[0], this.wKey[89] ^ iArr[1], this.wKey[90] ^ iArr[2], this.wKey[91] ^ iArr[3]);
        m279LT(iArr);
        sb7(iArr, this.wKey[92] ^ iArr[0], this.wKey[93] ^ iArr[1], this.wKey[94] ^ iArr[2], this.wKey[95] ^ iArr[3]);
        m279LT(iArr);
        sb0(iArr, this.wKey[96] ^ iArr[0], this.wKey[97] ^ iArr[1], this.wKey[98] ^ iArr[2], this.wKey[99] ^ iArr[3]);
        m279LT(iArr);
        sb1(iArr, this.wKey[100] ^ iArr[0], this.wKey[101] ^ iArr[1], this.wKey[102] ^ iArr[2], this.wKey[103] ^ iArr[3]);
        m279LT(iArr);
        sb2(iArr, this.wKey[104] ^ iArr[0], this.wKey[105] ^ iArr[1], this.wKey[106] ^ iArr[2], this.wKey[107] ^ iArr[3]);
        m279LT(iArr);
        sb3(iArr, this.wKey[108] ^ iArr[0], this.wKey[109] ^ iArr[1], this.wKey[110] ^ iArr[2], this.wKey[111] ^ iArr[3]);
        m279LT(iArr);
        sb4(iArr, this.wKey[112] ^ iArr[0], this.wKey[113] ^ iArr[1], this.wKey[114] ^ iArr[2], this.wKey[115] ^ iArr[3]);
        m279LT(iArr);
        sb5(iArr, this.wKey[116] ^ iArr[0], this.wKey[117] ^ iArr[1], this.wKey[118] ^ iArr[2], this.wKey[119] ^ iArr[3]);
        m279LT(iArr);
        sb6(iArr, this.wKey[120] ^ iArr[0], this.wKey[121] ^ iArr[1], this.wKey[122] ^ iArr[2], this.wKey[123] ^ iArr[3]);
        m279LT(iArr);
        sb7(iArr, this.wKey[124] ^ iArr[0], this.wKey[125] ^ iArr[1], this.wKey[126] ^ iArr[2], this.wKey[127] ^ iArr[3]);
        Pack.intToBigEndian(this.wKey[131] ^ iArr[3], bArr2, i2);
        Pack.intToBigEndian(this.wKey[130] ^ iArr[2], bArr2, i2 + 4);
        Pack.intToBigEndian(this.wKey[129] ^ iArr[1], bArr2, i2 + 8);
        Pack.intToBigEndian(this.wKey[128] ^ iArr[0], bArr2, i2 + 12);
    }

    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase, org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Tnepres";
    }

    @Override // org.bouncycastle.crypto.engines.SerpentEngineBase
    protected int[] makeWorkingKey(byte[] bArr) throws IllegalArgumentException {
        int[] iArr = new int[16];
        int length = bArr.length - 4;
        int i = 0;
        while (length > 0) {
            iArr[i] = Pack.bigEndianToInt(bArr, length);
            length -= 4;
            i++;
        }
        if (length == 0) {
            int i2 = i + 1;
            iArr[i] = Pack.bigEndianToInt(bArr, 0);
            if (i2 < 8) {
                iArr[i2] = 1;
            }
            int[] iArr2 = new int[132];
            for (int i3 = 8; i3 < 16; i3++) {
                int i4 = i3 - 8;
                iArr[i3] = rotateLeft(((-1640531527) ^ (((iArr[i4] ^ iArr[i3 - 5]) ^ iArr[i3 - 3]) ^ iArr[i3 - 1])) ^ i4, 11);
            }
            System.arraycopy(iArr, 8, iArr2, 0, 8);
            for (int i5 = 8; i5 < 132; i5++) {
                iArr2[i5] = rotateLeft(((((iArr2[i5 - 8] ^ iArr2[i5 - 5]) ^ iArr2[i5 - 3]) ^ iArr2[i5 - 1]) ^ (-1640531527)) ^ i5, 11);
            }
            int[] iArr3 = new int[4];
            sb3(iArr3, iArr2[0], iArr2[1], iArr2[2], iArr2[3]);
            iArr2[0] = iArr3[0];
            iArr2[1] = iArr3[1];
            iArr2[2] = iArr3[2];
            iArr2[3] = iArr3[3];
            sb2(iArr3, iArr2[4], iArr2[5], iArr2[6], iArr2[7]);
            iArr2[4] = iArr3[0];
            iArr2[5] = iArr3[1];
            iArr2[6] = iArr3[2];
            iArr2[7] = iArr3[3];
            sb1(iArr3, iArr2[8], iArr2[9], iArr2[10], iArr2[11]);
            iArr2[8] = iArr3[0];
            iArr2[9] = iArr3[1];
            iArr2[10] = iArr3[2];
            iArr2[11] = iArr3[3];
            sb0(iArr3, iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
            iArr2[12] = iArr3[0];
            iArr2[13] = iArr3[1];
            iArr2[14] = iArr3[2];
            iArr2[15] = iArr3[3];
            sb7(iArr3, iArr2[16], iArr2[17], iArr2[18], iArr2[19]);
            iArr2[16] = iArr3[0];
            iArr2[17] = iArr3[1];
            iArr2[18] = iArr3[2];
            iArr2[19] = iArr3[3];
            sb6(iArr3, iArr2[20], iArr2[21], iArr2[22], iArr2[23]);
            iArr2[20] = iArr3[0];
            iArr2[21] = iArr3[1];
            iArr2[22] = iArr3[2];
            iArr2[23] = iArr3[3];
            sb5(iArr3, iArr2[24], iArr2[25], iArr2[26], iArr2[27]);
            iArr2[24] = iArr3[0];
            iArr2[25] = iArr3[1];
            iArr2[26] = iArr3[2];
            iArr2[27] = iArr3[3];
            sb4(iArr3, iArr2[28], iArr2[29], iArr2[30], iArr2[31]);
            iArr2[28] = iArr3[0];
            iArr2[29] = iArr3[1];
            iArr2[30] = iArr3[2];
            iArr2[31] = iArr3[3];
            sb3(iArr3, iArr2[32], iArr2[33], iArr2[34], iArr2[35]);
            iArr2[32] = iArr3[0];
            iArr2[33] = iArr3[1];
            iArr2[34] = iArr3[2];
            iArr2[35] = iArr3[3];
            sb2(iArr3, iArr2[36], iArr2[37], iArr2[38], iArr2[39]);
            iArr2[36] = iArr3[0];
            iArr2[37] = iArr3[1];
            iArr2[38] = iArr3[2];
            iArr2[39] = iArr3[3];
            sb1(iArr3, iArr2[40], iArr2[41], iArr2[42], iArr2[43]);
            iArr2[40] = iArr3[0];
            iArr2[41] = iArr3[1];
            iArr2[42] = iArr3[2];
            iArr2[43] = iArr3[3];
            sb0(iArr3, iArr2[44], iArr2[45], iArr2[46], iArr2[47]);
            iArr2[44] = iArr3[0];
            iArr2[45] = iArr3[1];
            iArr2[46] = iArr3[2];
            iArr2[47] = iArr3[3];
            sb7(iArr3, iArr2[48], iArr2[49], iArr2[50], iArr2[51]);
            iArr2[48] = iArr3[0];
            iArr2[49] = iArr3[1];
            iArr2[50] = iArr3[2];
            iArr2[51] = iArr3[3];
            sb6(iArr3, iArr2[52], iArr2[53], iArr2[54], iArr2[55]);
            iArr2[52] = iArr3[0];
            iArr2[53] = iArr3[1];
            iArr2[54] = iArr3[2];
            iArr2[55] = iArr3[3];
            sb5(iArr3, iArr2[56], iArr2[57], iArr2[58], iArr2[59]);
            iArr2[56] = iArr3[0];
            iArr2[57] = iArr3[1];
            iArr2[58] = iArr3[2];
            iArr2[59] = iArr3[3];
            sb4(iArr3, iArr2[60], iArr2[61], iArr2[62], iArr2[63]);
            iArr2[60] = iArr3[0];
            iArr2[61] = iArr3[1];
            iArr2[62] = iArr3[2];
            iArr2[63] = iArr3[3];
            sb3(iArr3, iArr2[64], iArr2[65], iArr2[66], iArr2[67]);
            iArr2[64] = iArr3[0];
            iArr2[65] = iArr3[1];
            iArr2[66] = iArr3[2];
            iArr2[67] = iArr3[3];
            sb2(iArr3, iArr2[68], iArr2[69], iArr2[70], iArr2[71]);
            iArr2[68] = iArr3[0];
            iArr2[69] = iArr3[1];
            iArr2[70] = iArr3[2];
            iArr2[71] = iArr3[3];
            sb1(iArr3, iArr2[72], iArr2[73], iArr2[74], iArr2[75]);
            iArr2[72] = iArr3[0];
            iArr2[73] = iArr3[1];
            iArr2[74] = iArr3[2];
            iArr2[75] = iArr3[3];
            sb0(iArr3, iArr2[76], iArr2[77], iArr2[78], iArr2[79]);
            iArr2[76] = iArr3[0];
            iArr2[77] = iArr3[1];
            iArr2[78] = iArr3[2];
            iArr2[79] = iArr3[3];
            sb7(iArr3, iArr2[80], iArr2[81], iArr2[82], iArr2[83]);
            iArr2[80] = iArr3[0];
            iArr2[81] = iArr3[1];
            iArr2[82] = iArr3[2];
            iArr2[83] = iArr3[3];
            sb6(iArr3, iArr2[84], iArr2[85], iArr2[86], iArr2[87]);
            iArr2[84] = iArr3[0];
            iArr2[85] = iArr3[1];
            iArr2[86] = iArr3[2];
            iArr2[87] = iArr3[3];
            sb5(iArr3, iArr2[88], iArr2[89], iArr2[90], iArr2[91]);
            iArr2[88] = iArr3[0];
            iArr2[89] = iArr3[1];
            iArr2[90] = iArr3[2];
            iArr2[91] = iArr3[3];
            sb4(iArr3, iArr2[92], iArr2[93], iArr2[94], iArr2[95]);
            iArr2[92] = iArr3[0];
            iArr2[93] = iArr3[1];
            iArr2[94] = iArr3[2];
            iArr2[95] = iArr3[3];
            sb3(iArr3, iArr2[96], iArr2[97], iArr2[98], iArr2[99]);
            iArr2[96] = iArr3[0];
            iArr2[97] = iArr3[1];
            iArr2[98] = iArr3[2];
            iArr2[99] = iArr3[3];
            sb2(iArr3, iArr2[100], iArr2[101], iArr2[102], iArr2[103]);
            iArr2[100] = iArr3[0];
            iArr2[101] = iArr3[1];
            iArr2[102] = iArr3[2];
            iArr2[103] = iArr3[3];
            sb1(iArr3, iArr2[104], iArr2[105], iArr2[106], iArr2[107]);
            iArr2[104] = iArr3[0];
            iArr2[105] = iArr3[1];
            iArr2[106] = iArr3[2];
            iArr2[107] = iArr3[3];
            sb0(iArr3, iArr2[108], iArr2[109], iArr2[110], iArr2[111]);
            iArr2[108] = iArr3[0];
            iArr2[109] = iArr3[1];
            iArr2[110] = iArr3[2];
            iArr2[111] = iArr3[3];
            sb7(iArr3, iArr2[112], iArr2[113], iArr2[114], iArr2[115]);
            iArr2[112] = iArr3[0];
            iArr2[113] = iArr3[1];
            iArr2[114] = iArr3[2];
            iArr2[115] = iArr3[3];
            sb6(iArr3, iArr2[116], iArr2[117], iArr2[118], iArr2[119]);
            iArr2[116] = iArr3[0];
            iArr2[117] = iArr3[1];
            iArr2[118] = iArr3[2];
            iArr2[119] = iArr3[3];
            sb5(iArr3, iArr2[120], iArr2[121], iArr2[122], iArr2[123]);
            iArr2[120] = iArr3[0];
            iArr2[121] = iArr3[1];
            iArr2[122] = iArr3[2];
            iArr2[123] = iArr3[3];
            sb4(iArr3, iArr2[124], iArr2[125], iArr2[126], iArr2[127]);
            iArr2[124] = iArr3[0];
            iArr2[125] = iArr3[1];
            iArr2[126] = iArr3[2];
            iArr2[127] = iArr3[3];
            sb3(iArr3, iArr2[128], iArr2[129], iArr2[130], iArr2[131]);
            iArr2[128] = iArr3[0];
            iArr2[129] = iArr3[1];
            iArr2[130] = iArr3[2];
            iArr2[131] = iArr3[3];
            return iArr2;
        }
        throw new IllegalArgumentException("key must be a multiple of 4 bytes");
    }
}
