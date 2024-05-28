package net.lingala.zip4j.crypto.engine;

import com.cjt2325.cameralibrary.CameraInterface;
import java.lang.reflect.Array;
import net.lingala.zip4j.exception.ZipException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AESEngine {

    /* renamed from: C0 */
    private int f25029C0;

    /* renamed from: C1 */
    private int f25030C1;

    /* renamed from: C2 */
    private int f25031C2;

    /* renamed from: C3 */
    private int f25032C3;
    private int rounds;
    private int[][] workingKey = null;

    /* renamed from: S */
    private static final byte[] f25027S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, C0548c.f1784h, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, C0548c.f1785i, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, CameraInterface.TYPE_CAPTURE};

    /* renamed from: T0 */
    private static final int[] f25028T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};

    private int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    public AESEngine(byte[] bArr) throws ZipException {
        init(bArr);
    }

    private void init(byte[] bArr) throws ZipException {
        this.workingKey = generateWorkingKey(bArr);
    }

    private int[][] generateWorkingKey(byte[] bArr) throws ZipException {
        int length = bArr.length / 4;
        if ((length != 4 && length != 6 && length != 8) || length * 4 != bArr.length) {
            throw new ZipException("invalid key length (not 128/192/256)");
        }
        this.rounds = length + 6;
        int[][] iArr = (int[][]) Array.newInstance(int.class, this.rounds + 1, 4);
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            iArr[i2 >> 2][i2 & 3] = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | (bArr[i + 3] << 24);
            i += 4;
            i2++;
        }
        int i3 = (this.rounds + 1) << 2;
        for (int i4 = length; i4 < i3; i4++) {
            int i5 = i4 - 1;
            int i6 = iArr[i5 >> 2][i5 & 3];
            int i7 = i4 % length;
            if (i7 == 0) {
                i6 = subWord(shift(i6, 8)) ^ rcon[(i4 / length) - 1];
            } else if (length > 6 && i7 == 4) {
                i6 = subWord(i6);
            }
            int i8 = i4 - length;
            iArr[i4 >> 2][i4 & 3] = i6 ^ iArr[i8 >> 2][i8 & 3];
        }
        return iArr;
    }

    public int processBlock(byte[] bArr, byte[] bArr2) throws ZipException {
        return processBlock(bArr, 0, bArr2, 0);
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws ZipException {
        if (this.workingKey == null) {
            throw new ZipException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new ZipException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new ZipException("output buffer too short");
        }
        stateIn(bArr, i);
        encryptBlock(this.workingKey);
        stateOut(bArr2, i2);
        return 16;
    }

    private void stateIn(byte[] bArr, int i) {
        int i2 = i + 1;
        this.f25029C0 = bArr[i] & 255;
        int i3 = i2 + 1;
        this.f25029C0 |= (bArr[i2] & 255) << 8;
        int i4 = i3 + 1;
        this.f25029C0 |= (bArr[i3] & 255) << 16;
        int i5 = i4 + 1;
        this.f25029C0 |= bArr[i4] << 24;
        int i6 = i5 + 1;
        this.f25030C1 = bArr[i5] & 255;
        int i7 = i6 + 1;
        this.f25030C1 = ((bArr[i6] & 255) << 8) | this.f25030C1;
        int i8 = i7 + 1;
        this.f25030C1 |= (bArr[i7] & 255) << 16;
        int i9 = i8 + 1;
        this.f25030C1 |= bArr[i8] << 24;
        int i10 = i9 + 1;
        this.f25031C2 = bArr[i9] & 255;
        int i11 = i10 + 1;
        this.f25031C2 = ((bArr[i10] & 255) << 8) | this.f25031C2;
        int i12 = i11 + 1;
        this.f25031C2 |= (bArr[i11] & 255) << 16;
        int i13 = i12 + 1;
        this.f25031C2 |= bArr[i12] << 24;
        int i14 = i13 + 1;
        this.f25032C3 = bArr[i13] & 255;
        int i15 = i14 + 1;
        this.f25032C3 = ((bArr[i14] & 255) << 8) | this.f25032C3;
        this.f25032C3 |= (bArr[i15] & 255) << 16;
        this.f25032C3 = (bArr[i15 + 1] << 24) | this.f25032C3;
    }

    private void stateOut(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.f25029C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.f25030C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.f25031C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.f25032C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    private void encryptBlock(int[][] iArr) {
        this.f25029C0 ^= iArr[0][0];
        this.f25030C1 ^= iArr[0][1];
        this.f25031C2 ^= iArr[0][2];
        this.f25032C3 ^= iArr[0][3];
        int i = 1;
        while (i < this.rounds - 1) {
            int[] iArr2 = f25028T0;
            int shift = (((shift(iArr2[(this.f25030C1 >> 8) & 255], 24) ^ iArr2[this.f25029C0 & 255]) ^ shift(f25028T0[(this.f25031C2 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25032C3 >> 24) & 255], 8)) ^ iArr[i][0];
            int[] iArr3 = f25028T0;
            int shift2 = (((shift(iArr3[(this.f25031C2 >> 8) & 255], 24) ^ iArr3[this.f25030C1 & 255]) ^ shift(f25028T0[(this.f25032C3 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25029C0 >> 24) & 255], 8)) ^ iArr[i][1];
            int[] iArr4 = f25028T0;
            int shift3 = (((shift(iArr4[(this.f25032C3 >> 8) & 255], 24) ^ iArr4[this.f25031C2 & 255]) ^ shift(f25028T0[(this.f25029C0 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25030C1 >> 24) & 255], 8)) ^ iArr[i][2];
            int[] iArr5 = f25028T0;
            int i2 = i + 1;
            int shift4 = iArr[i][3] ^ (((shift(iArr5[(this.f25029C0 >> 8) & 255], 24) ^ iArr5[this.f25032C3 & 255]) ^ shift(f25028T0[(this.f25030C1 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25031C2 >> 24) & 255], 8));
            int[] iArr6 = f25028T0;
            this.f25029C0 = (((shift(iArr6[(shift2 >> 8) & 255], 24) ^ iArr6[shift & 255]) ^ shift(f25028T0[(shift3 >> 16) & 255], 16)) ^ shift(f25028T0[(shift4 >> 24) & 255], 8)) ^ iArr[i2][0];
            int[] iArr7 = f25028T0;
            this.f25030C1 = (((shift(iArr7[(shift3 >> 8) & 255], 24) ^ iArr7[shift2 & 255]) ^ shift(f25028T0[(shift4 >> 16) & 255], 16)) ^ shift(f25028T0[(shift >> 24) & 255], 8)) ^ iArr[i2][1];
            int[] iArr8 = f25028T0;
            this.f25031C2 = (((shift(iArr8[(shift4 >> 8) & 255], 24) ^ iArr8[shift3 & 255]) ^ shift(f25028T0[(shift >> 16) & 255], 16)) ^ shift(f25028T0[(shift2 >> 24) & 255], 8)) ^ iArr[i2][2];
            int[] iArr9 = f25028T0;
            this.f25032C3 = (((iArr9[shift4 & 255] ^ shift(iArr9[(shift >> 8) & 255], 24)) ^ shift(f25028T0[(shift2 >> 16) & 255], 16)) ^ shift(f25028T0[(shift3 >> 24) & 255], 8)) ^ iArr[i2][3];
            i = i2 + 1;
        }
        int[] iArr10 = f25028T0;
        int shift5 = (((shift(iArr10[(this.f25030C1 >> 8) & 255], 24) ^ iArr10[this.f25029C0 & 255]) ^ shift(f25028T0[(this.f25031C2 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25032C3 >> 24) & 255], 8)) ^ iArr[i][0];
        int[] iArr11 = f25028T0;
        int shift6 = (((shift(iArr11[(this.f25031C2 >> 8) & 255], 24) ^ iArr11[this.f25030C1 & 255]) ^ shift(f25028T0[(this.f25032C3 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25029C0 >> 24) & 255], 8)) ^ iArr[i][1];
        int[] iArr12 = f25028T0;
        int shift7 = (((shift(iArr12[(this.f25032C3 >> 8) & 255], 24) ^ iArr12[this.f25031C2 & 255]) ^ shift(f25028T0[(this.f25029C0 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25030C1 >> 24) & 255], 8)) ^ iArr[i][2];
        int[] iArr13 = f25028T0;
        int i3 = i + 1;
        int shift8 = iArr[i][3] ^ (((shift(iArr13[(this.f25029C0 >> 8) & 255], 24) ^ iArr13[this.f25032C3 & 255]) ^ shift(f25028T0[(this.f25030C1 >> 16) & 255], 16)) ^ shift(f25028T0[(this.f25031C2 >> 24) & 255], 8));
        byte[] bArr = f25027S;
        this.f25029C0 = iArr[i3][0] ^ ((((bArr[shift5 & 255] & 255) ^ ((bArr[(shift6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(shift7 >> 16) & 255] & 255) << 16)) ^ (bArr[(shift8 >> 24) & 255] << 24));
        this.f25030C1 = ((((bArr[shift6 & 255] & 255) ^ ((bArr[(shift7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(shift8 >> 16) & 255] & 255) << 16)) ^ (bArr[(shift5 >> 24) & 255] << 24)) ^ iArr[i3][1];
        this.f25031C2 = ((((bArr[shift7 & 255] & 255) ^ ((bArr[(shift8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(shift5 >> 16) & 255] & 255) << 16)) ^ (bArr[(shift6 >> 24) & 255] << 24)) ^ iArr[i3][2];
        this.f25032C3 = ((((bArr[shift8 & 255] & 255) ^ ((bArr[(shift5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(shift6 >> 16) & 255] & 255) << 16)) ^ (bArr[(shift7 >> 24) & 255] << 24)) ^ iArr[i3][3];
    }

    private int subWord(int i) {
        byte[] bArr = f25027S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }
}
