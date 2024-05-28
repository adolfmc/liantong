package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0512f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RIPEMD256Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: H0 */
    private int f26479H0;

    /* renamed from: H1 */
    private int f26480H1;

    /* renamed from: H2 */
    private int f26481H2;

    /* renamed from: H3 */
    private int f26482H3;

    /* renamed from: H4 */
    private int f26483H4;

    /* renamed from: H5 */
    private int f26484H5;

    /* renamed from: H6 */
    private int f26485H6;

    /* renamed from: H7 */
    private int f26486H7;

    /* renamed from: X */
    private int[] f26487X;
    private int xOff;

    public RIPEMD256Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public RIPEMD256Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        this.f26487X = new int[16];
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 128, cryptoServicePurpose));
        reset();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest.purpose);
        this.f26487X = new int[16];
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 128, this.purpose));
        copyIn(rIPEMD256Digest);
    }

    /* renamed from: F1 */
    private int m329F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m324f1(i2, i3, i4) + i5, i6);
    }

    /* renamed from: F2 */
    private int m328F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m323f2(i2, i3, i4) + i5 + C0512f.f1670m, i6);
    }

    /* renamed from: F3 */
    private int m327F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m322f3(i2, i3, i4) + i5 + C0512f.f1671n, i6);
    }

    /* renamed from: F4 */
    private int m326F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m321f4(i2, i3, i4) + i5 + C0512f.f1672o, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m324f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m323f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m322f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m325RL(i + m321f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    /* renamed from: RL */
    private int m325RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD256Digest rIPEMD256Digest) {
        super.copyIn((GeneralDigest) rIPEMD256Digest);
        this.f26479H0 = rIPEMD256Digest.f26479H0;
        this.f26480H1 = rIPEMD256Digest.f26480H1;
        this.f26481H2 = rIPEMD256Digest.f26481H2;
        this.f26482H3 = rIPEMD256Digest.f26482H3;
        this.f26483H4 = rIPEMD256Digest.f26483H4;
        this.f26484H5 = rIPEMD256Digest.f26484H5;
        this.f26485H6 = rIPEMD256Digest.f26485H6;
        this.f26486H7 = rIPEMD256Digest.f26486H7;
        int[] iArr = rIPEMD256Digest.f26487X;
        System.arraycopy(iArr, 0, this.f26487X, 0, iArr.length);
        this.xOff = rIPEMD256Digest.xOff;
    }

    /* renamed from: f1 */
    private int m324f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m323f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m322f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m321f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD256Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToLittleEndian(this.f26479H0, bArr, i);
        Pack.intToLittleEndian(this.f26480H1, bArr, i + 4);
        Pack.intToLittleEndian(this.f26481H2, bArr, i + 8);
        Pack.intToLittleEndian(this.f26482H3, bArr, i + 12);
        Pack.intToLittleEndian(this.f26483H4, bArr, i + 16);
        Pack.intToLittleEndian(this.f26484H5, bArr, i + 20);
        Pack.intToLittleEndian(this.f26485H6, bArr, i + 24);
        Pack.intToLittleEndian(this.f26486H7, bArr, i + 28);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f26479H0;
        int i2 = this.f26480H1;
        int i3 = this.f26481H2;
        int i4 = this.f26482H3;
        int i5 = this.f26483H4;
        int i6 = this.f26484H5;
        int i7 = this.f26485H6;
        int i8 = this.f26486H7;
        int m329F1 = m329F1(i, i2, i3, i4, this.f26487X[0], 11);
        int m329F12 = m329F1(i4, m329F1, i2, i3, this.f26487X[1], 14);
        int m329F13 = m329F1(i3, m329F12, m329F1, i2, this.f26487X[2], 15);
        int m329F14 = m329F1(i2, m329F13, m329F12, m329F1, this.f26487X[3], 12);
        int m329F15 = m329F1(m329F1, m329F14, m329F13, m329F12, this.f26487X[4], 5);
        int m329F16 = m329F1(m329F12, m329F15, m329F14, m329F13, this.f26487X[5], 8);
        int m329F17 = m329F1(m329F13, m329F16, m329F15, m329F14, this.f26487X[6], 7);
        int m329F18 = m329F1(m329F14, m329F17, m329F16, m329F15, this.f26487X[7], 9);
        int m329F19 = m329F1(m329F15, m329F18, m329F17, m329F16, this.f26487X[8], 11);
        int m329F110 = m329F1(m329F16, m329F19, m329F18, m329F17, this.f26487X[9], 13);
        int m329F111 = m329F1(m329F17, m329F110, m329F19, m329F18, this.f26487X[10], 14);
        int m329F112 = m329F1(m329F18, m329F111, m329F110, m329F19, this.f26487X[11], 15);
        int m329F113 = m329F1(m329F19, m329F112, m329F111, m329F110, this.f26487X[12], 6);
        int m329F114 = m329F1(m329F110, m329F113, m329F112, m329F111, this.f26487X[13], 7);
        int m329F115 = m329F1(m329F111, m329F114, m329F113, m329F112, this.f26487X[14], 9);
        int m329F116 = m329F1(m329F112, m329F115, m329F114, m329F113, this.f26487X[15], 8);
        int FF4 = FF4(i5, i6, i7, i8, this.f26487X[5], 8);
        int FF42 = FF4(i8, FF4, i6, i7, this.f26487X[14], 9);
        int FF43 = FF4(i7, FF42, FF4, i6, this.f26487X[7], 9);
        int FF44 = FF4(i6, FF43, FF42, FF4, this.f26487X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f26487X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f26487X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f26487X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f26487X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f26487X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f26487X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f26487X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f26487X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f26487X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f26487X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f26487X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f26487X[12], 6);
        int m328F2 = m328F2(FF413, m329F116, m329F115, m329F114, this.f26487X[7], 7);
        int m328F22 = m328F2(m329F114, m328F2, m329F116, m329F115, this.f26487X[4], 6);
        int m328F23 = m328F2(m329F115, m328F22, m328F2, m329F116, this.f26487X[13], 8);
        int m328F24 = m328F2(m329F116, m328F23, m328F22, m328F2, this.f26487X[1], 13);
        int m328F25 = m328F2(m328F2, m328F24, m328F23, m328F22, this.f26487X[10], 11);
        int m328F26 = m328F2(m328F22, m328F25, m328F24, m328F23, this.f26487X[6], 9);
        int m328F27 = m328F2(m328F23, m328F26, m328F25, m328F24, this.f26487X[15], 7);
        int m328F28 = m328F2(m328F24, m328F27, m328F26, m328F25, this.f26487X[3], 15);
        int m328F29 = m328F2(m328F25, m328F28, m328F27, m328F26, this.f26487X[12], 7);
        int m328F210 = m328F2(m328F26, m328F29, m328F28, m328F27, this.f26487X[0], 12);
        int m328F211 = m328F2(m328F27, m328F210, m328F29, m328F28, this.f26487X[9], 15);
        int m328F212 = m328F2(m328F28, m328F211, m328F210, m328F29, this.f26487X[5], 9);
        int m328F213 = m328F2(m328F29, m328F212, m328F211, m328F210, this.f26487X[2], 11);
        int m328F214 = m328F2(m328F210, m328F213, m328F212, m328F211, this.f26487X[14], 7);
        int m328F215 = m328F2(m328F211, m328F214, m328F213, m328F212, this.f26487X[11], 13);
        int m328F216 = m328F2(m328F212, m328F215, m328F214, m328F213, this.f26487X[8], 12);
        int FF3 = FF3(m329F113, FF416, FF415, FF414, this.f26487X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f26487X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f26487X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f26487X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f26487X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f26487X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f26487X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f26487X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f26487X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f26487X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f26487X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f26487X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f26487X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f26487X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f26487X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f26487X[2], 11);
        int m327F3 = m327F3(m328F213, FF316, m328F215, m328F214, this.f26487X[3], 11);
        int m327F32 = m327F3(m328F214, m327F3, FF316, m328F215, this.f26487X[10], 13);
        int m327F33 = m327F3(m328F215, m327F32, m327F3, FF316, this.f26487X[14], 6);
        int m327F34 = m327F3(FF316, m327F33, m327F32, m327F3, this.f26487X[4], 7);
        int m327F35 = m327F3(m327F3, m327F34, m327F33, m327F32, this.f26487X[9], 14);
        int m327F36 = m327F3(m327F32, m327F35, m327F34, m327F33, this.f26487X[15], 9);
        int m327F37 = m327F3(m327F33, m327F36, m327F35, m327F34, this.f26487X[8], 13);
        int m327F38 = m327F3(m327F34, m327F37, m327F36, m327F35, this.f26487X[1], 15);
        int m327F39 = m327F3(m327F35, m327F38, m327F37, m327F36, this.f26487X[2], 14);
        int m327F310 = m327F3(m327F36, m327F39, m327F38, m327F37, this.f26487X[7], 8);
        int m327F311 = m327F3(m327F37, m327F310, m327F39, m327F38, this.f26487X[0], 13);
        int m327F312 = m327F3(m327F38, m327F311, m327F310, m327F39, this.f26487X[6], 6);
        int m327F313 = m327F3(m327F39, m327F312, m327F311, m327F310, this.f26487X[13], 5);
        int m327F314 = m327F3(m327F310, m327F313, m327F312, m327F311, this.f26487X[11], 12);
        int m327F315 = m327F3(m327F311, m327F314, m327F313, m327F312, this.f26487X[5], 7);
        int m327F316 = m327F3(m327F312, m327F315, m327F314, m327F313, this.f26487X[12], 5);
        int FF2 = FF2(FF313, m328F216, FF315, FF314, this.f26487X[15], 9);
        int FF22 = FF2(FF314, FF2, m328F216, FF315, this.f26487X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, m328F216, this.f26487X[1], 15);
        int FF24 = FF2(m328F216, FF23, FF22, FF2, this.f26487X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f26487X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f26487X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f26487X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f26487X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f26487X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f26487X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f26487X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f26487X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f26487X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f26487X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f26487X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f26487X[13], 5);
        int m326F4 = m326F4(m327F313, m327F316, FF215, m327F314, this.f26487X[1], 11);
        int m326F42 = m326F4(m327F314, m326F4, m327F316, FF215, this.f26487X[9], 12);
        int m326F43 = m326F4(FF215, m326F42, m326F4, m327F316, this.f26487X[11], 14);
        int m326F44 = m326F4(m327F316, m326F43, m326F42, m326F4, this.f26487X[10], 15);
        int m326F45 = m326F4(m326F4, m326F44, m326F43, m326F42, this.f26487X[0], 14);
        int m326F46 = m326F4(m326F42, m326F45, m326F44, m326F43, this.f26487X[8], 15);
        int m326F47 = m326F4(m326F43, m326F46, m326F45, m326F44, this.f26487X[12], 9);
        int m326F48 = m326F4(m326F44, m326F47, m326F46, m326F45, this.f26487X[4], 8);
        int m326F49 = m326F4(m326F45, m326F48, m326F47, m326F46, this.f26487X[13], 9);
        int m326F410 = m326F4(m326F46, m326F49, m326F48, m326F47, this.f26487X[3], 14);
        int m326F411 = m326F4(m326F47, m326F410, m326F49, m326F48, this.f26487X[7], 5);
        int m326F412 = m326F4(m326F48, m326F411, m326F410, m326F49, this.f26487X[15], 6);
        int m326F413 = m326F4(m326F49, m326F412, m326F411, m326F410, this.f26487X[14], 8);
        int m326F414 = m326F4(m326F410, m326F413, m326F412, m326F411, this.f26487X[5], 6);
        int m326F415 = m326F4(m326F411, m326F414, m326F413, m326F412, this.f26487X[6], 5);
        int m326F416 = m326F4(m326F412, m326F415, m326F414, m326F413, this.f26487X[2], 12);
        int FF1 = FF1(FF213, FF216, m327F315, FF214, this.f26487X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, m327F315, this.f26487X[6], 5);
        int FF13 = FF1(m327F315, FF12, FF1, FF216, this.f26487X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f26487X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f26487X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f26487X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f26487X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f26487X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f26487X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f26487X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f26487X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f26487X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f26487X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f26487X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f26487X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f26487X[14], 8);
        this.f26479H0 += m326F413;
        this.f26480H1 += m326F416;
        this.f26481H2 += m326F415;
        this.f26482H3 += FF114;
        this.f26483H4 += FF113;
        this.f26484H5 += FF116;
        this.f26485H6 += FF115;
        this.f26486H7 += m326F414;
        this.xOff = 0;
        int i9 = 0;
        while (true) {
            int[] iArr = this.f26487X;
            if (i9 == iArr.length) {
                return;
            }
            iArr[i9] = 0;
            i9++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f26487X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f26487X;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr[i2] = Pack.littleEndianToInt(bArr, i);
        if (this.xOff == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f26479H0 = 1732584193;
        this.f26480H1 = -271733879;
        this.f26481H2 = -1732584194;
        this.f26482H3 = 271733878;
        this.f26483H4 = 1985229328;
        this.f26484H5 = -19088744;
        this.f26485H6 = -1985229329;
        this.f26486H7 = 19088743;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f26487X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD256Digest) memoable);
    }
}
