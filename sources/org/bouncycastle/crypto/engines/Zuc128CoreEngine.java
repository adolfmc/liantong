package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Memoable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;

    /* renamed from: F */
    private final int[] f26664F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;

    /* renamed from: S0 */
    private static final byte[] f26662S0 = {62, 114, 91, 71, -54, -32, 0, 51, 4, -47, 84, -104, 9, -71, 109, -53, 123, 27, -7, 50, -81, -99, 106, -91, -72, 45, -4, 29, 8, 83, 3, -112, 77, 78, -124, -103, -28, -50, -39, -111, -35, -74, -123, 72, -117, 41, 110, -84, -51, -63, -8, 30, 115, 67, 105, -58, -75, -67, -3, 57, 99, 32, -44, 56, 118, 125, -78, -89, -49, -19, 87, -59, -13, 44, -69, 20, 33, 6, 85, -101, -29, -17, 94, 49, 79, Byte.MAX_VALUE, 90, -92, 13, -126, 81, 73, 95, -70, 88, 28, 74, 22, -43, 23, -88, -110, 36, 31, -116, -1, -40, -82, 46, 1, -45, -83, 59, 75, -38, 70, -21, -55, -34, -102, -113, -121, -41, 58, Byte.MIN_VALUE, 111, 47, -56, -79, -76, 55, -9, 10, 34, 19, 40, 124, -52, 60, -119, -57, -61, -106, 86, 7, -65, 126, -16, 11, 43, -105, 82, 53, 65, 121, 97, -90, 76, 16, -2, -68, 38, -107, -120, -118, -80, -93, -5, -64, 24, -108, -14, -31, -27, -23, 93, -48, -36, 17, 102, 100, C0548c.f1785i, -20, 89, 66, 117, 18, -11, 116, -100, -86, 35, 14, -122, -85, -66, 42, 2, -25, 103, -26, 68, -94, 108, -62, -109, -97, -15, -10, -6, C0548c.f1784h, -46, 80, 104, -98, 98, 113, 21, 61, -42, 64, -60, -30, 15, -114, -125, 119, 107, 37, 5, 63, 12, 48, -22, 112, -73, -95, -24, -87, 101, -115, 39, 26, -37, -127, -77, -96, -12, 69, 122, 25, -33, -18, 120, 52, 96};

    /* renamed from: S1 */
    private static final byte[] f26663S1 = {85, -62, 99, 113, 59, -56, 71, -122, -97, 60, -38, 91, 41, -86, -3, 119, -116, -59, -108, 12, -90, 26, 19, 0, -29, -88, 22, 114, 64, -7, -8, 66, 68, 38, 104, -106, -127, -39, 69, 62, 16, 118, -58, -89, -117, 57, 67, -31, 58, -75, 86, 42, -64, 109, -77, 5, 34, 102, -65, -36, 11, -6, 98, 72, -35, 32, 17, 6, C0548c.f1784h, -55, -63, -49, -10, 39, 82, -69, 105, -11, -44, -121, Byte.MAX_VALUE, -124, 76, -46, -100, 87, -92, -68, 79, -102, -33, -2, -42, -115, 122, -21, 43, 83, -40, C0548c.f1785i, -95, 20, 23, -5, 35, -43, 125, 48, 103, 115, 8, 9, -18, -73, 112, 63, 97, -78, 25, -114, 78, -27, 75, -109, -113, 93, -37, -87, -83, -15, -82, 46, -53, 13, -4, -12, 45, 70, 110, 29, -105, -24, -47, -23, 77, 55, -91, 117, 94, -125, -98, -85, -126, -99, -71, 28, -32, -51, 73, -119, 1, -74, -67, 88, 36, -94, 95, 56, 120, -103, 21, -112, 80, -72, -107, -28, -48, -111, -57, -50, -19, 15, -76, 111, -96, -52, -16, 2, 74, 121, -61, -34, -93, -17, -22, 81, -26, 107, 24, -20, 27, 44, Byte.MIN_VALUE, -9, 116, -25, -1, 33, 90, 106, 84, 30, 65, 49, -110, 53, -60, 51, 7, 10, -70, 126, 14, 52, -120, -79, -104, 124, -13, 61, 96, 108, 123, -54, -45, 31, 50, 101, 4, 40, 100, -66, -123, -101, 47, 89, -118, -41, -80, 37, -84, -81, 18, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.f26664F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.f26664F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int i, int i2) {
        int i3 = i + i2;
        return (Integer.MAX_VALUE & i3) + (i3 >>> 31);
    }

    private void BitReorganization() {
        int[] iArr = this.BRC;
        int[] iArr2 = this.LFSR;
        iArr[0] = ((iArr2[15] & 2147450880) << 1) | (iArr2[14] & 65535);
        iArr[1] = ((iArr2[11] & 65535) << 16) | (iArr2[9] >>> 15);
        iArr[2] = ((iArr2[7] & 65535) << 16) | (iArr2[5] >>> 15);
        iArr[3] = (iArr2[0] >>> 15) | ((iArr2[2] & 65535) << 16);
    }

    /* renamed from: L1 */
    private static int m271L1(int i) {
        return ROT(i, 24) ^ (((ROT(i, 2) ^ i) ^ ROT(i, 10)) ^ ROT(i, 18));
    }

    /* renamed from: L2 */
    private static int m270L2(int i) {
        return ROT(i, 30) ^ (((ROT(i, 8) ^ i) ^ ROT(i, 14)) ^ ROT(i, 22));
    }

    private void LFSRWithInitialisationMode(int i) {
        int[] iArr = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(AddM(iArr[0], MulByPow2(iArr[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), i);
        int[] iArr2 = this.LFSR;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = iArr2[5];
        iArr2[5] = iArr2[6];
        iArr2[6] = iArr2[7];
        iArr2[7] = iArr2[8];
        iArr2[8] = iArr2[9];
        iArr2[9] = iArr2[10];
        iArr2[10] = iArr2[11];
        iArr2[11] = iArr2[12];
        iArr2[12] = iArr2[13];
        iArr2[13] = iArr2[14];
        iArr2[14] = iArr2[15];
        iArr2[15] = AddM;
    }

    private void LFSRWithWorkMode() {
        int[] iArr = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(iArr[0], MulByPow2(iArr[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] iArr2 = this.LFSR;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = iArr2[5];
        iArr2[5] = iArr2[6];
        iArr2[6] = iArr2[7];
        iArr2[7] = iArr2[8];
        iArr2[8] = iArr2[9];
        iArr2[9] = iArr2[10];
        iArr2[10] = iArr2[11];
        iArr2[11] = iArr2[12];
        iArr2[12] = iArr2[13];
        iArr2[13] = iArr2[14];
        iArr2[14] = iArr2[15];
        iArr2[15] = AddM;
    }

    private static int MAKEU31(byte b, short s, byte b2) {
        return ((b & 255) << 23) | ((s & 65535) << 8) | (b2 & 255);
    }

    private static int MAKEU32(byte b, byte b2, byte b3, byte b4) {
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    private static int MulByPow2(int i, int i2) {
        return ((i >>> (31 - i2)) | (i << i2)) & Integer.MAX_VALUE;
    }

    static int ROT(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static void encode32be(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    private void makeKeyStream() {
        encode32be(makeKeyStreamWord(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        setKeyAndIV(this.LFSR, bArr, bArr2);
        int[] iArr = this.f26664F;
        iArr[0] = 0;
        iArr[1] = 0;
        for (int i = 32; i > 0; i--) {
            BitReorganization();
            LFSRWithInitialisationMode(m272F() >>> 1);
        }
        BitReorganization();
        m272F();
        LFSRWithWorkMode();
    }

    /* renamed from: F */
    int m272F() {
        int[] iArr = this.BRC;
        int i = iArr[0];
        int[] iArr2 = this.f26664F;
        int i2 = (i ^ iArr2[0]) + iArr2[1];
        int i3 = iArr2[0] + iArr[1];
        int i4 = iArr[2] ^ iArr2[1];
        int m271L1 = m271L1((i3 << 16) | (i4 >>> 16));
        int m270L2 = m270L2((i4 << 16) | (i3 >>> 16));
        int[] iArr3 = this.f26664F;
        byte[] bArr = f26662S0;
        byte b = bArr[m271L1 >>> 24];
        byte[] bArr2 = f26663S1;
        iArr3[0] = MAKEU32(b, bArr2[(m271L1 >>> 16) & 255], bArr[(m271L1 >>> 8) & 255], bArr2[m271L1 & 255]);
        int[] iArr4 = this.f26664F;
        byte[] bArr3 = f26662S0;
        byte b2 = bArr3[m270L2 >>> 24];
        byte[] bArr4 = f26663S1;
        iArr4[1] = MAKEU32(b2, bArr4[(m270L2 >>> 16) & 255], bArr3[(m270L2 >>> 8) & 255], bArr4[m270L2 & 255]);
        return i2;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    protected int getMaxIterations() {
        return 2047;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            cipherParameters2 = cipherParameters;
            bArr = null;
        }
        byte[] key = cipherParameters2 instanceof KeyParameter ? ((KeyParameter) cipherParameters2).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, bArr);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, z ? CryptoServicePurpose.ENCRYPTION : CryptoServicePurpose.DECRYPTION));
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int makeKeyStreamWord() {
        int i = this.theIterations;
        this.theIterations = i + 1;
        if (i < getMaxIterations()) {
            BitReorganization();
            int m272F = m272F() ^ this.BRC[3];
            LFSRWithWorkMode();
            return m272F;
        }
        throw new IllegalStateException("Too much data processed by singleKey/IV");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.theResetState == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                for (int i4 = 0; i4 < i2; i4++) {
                    bArr2[i4 + i3] = returnByte(bArr[i4 + i]);
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] iArr = zuc128CoreEngine.LFSR;
        int[] iArr2 = this.LFSR;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = zuc128CoreEngine.f26664F;
        int[] iArr4 = this.f26664F;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = zuc128CoreEngine.BRC;
        int[] iArr6 = this.BRC;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int i = this.theIndex;
        byte b2 = (byte) (b ^ bArr[i]);
        this.theIndex = (i + 1) % 4;
        return b2;
    }

    protected void setKeyAndIV(int[] iArr, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        this.LFSR[0] = MAKEU31(bArr[0], EK_d[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], EK_d[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], EK_d[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], EK_d[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], EK_d[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], EK_d[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], EK_d[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], EK_d[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], EK_d[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], EK_d[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], EK_d[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], EK_d[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], EK_d[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], EK_d[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], EK_d[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], EK_d[15], bArr2[15]);
    }
}
