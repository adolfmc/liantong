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
public class RIPEMD128Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: H0 */
    private int f26468H0;

    /* renamed from: H1 */
    private int f26469H1;

    /* renamed from: H2 */
    private int f26470H2;

    /* renamed from: H3 */
    private int f26471H3;

    /* renamed from: X */
    private int[] f26472X;
    private int xOff;

    public RIPEMD128Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public RIPEMD128Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        this.f26472X = new int[16];
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 128, cryptoServicePurpose));
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest.purpose);
        this.f26472X = new int[16];
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 128, this.purpose));
        copyIn(rIPEMD128Digest);
    }

    /* renamed from: F1 */
    private int m344F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m339f1(i2, i3, i4) + i5, i6);
    }

    /* renamed from: F2 */
    private int m343F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m338f2(i2, i3, i4) + i5 + C0512f.f1670m, i6);
    }

    /* renamed from: F3 */
    private int m342F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m337f3(i2, i3, i4) + i5 + C0512f.f1671n, i6);
    }

    /* renamed from: F4 */
    private int m341F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m336f4(i2, i3, i4) + i5 + C0512f.f1672o, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m339f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m338f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m337f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m340RL(i + m336f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    /* renamed from: RL */
    private int m340RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD128Digest rIPEMD128Digest) {
        super.copyIn((GeneralDigest) rIPEMD128Digest);
        this.f26468H0 = rIPEMD128Digest.f26468H0;
        this.f26469H1 = rIPEMD128Digest.f26469H1;
        this.f26470H2 = rIPEMD128Digest.f26470H2;
        this.f26471H3 = rIPEMD128Digest.f26471H3;
        int[] iArr = rIPEMD128Digest.f26472X;
        System.arraycopy(iArr, 0, this.f26472X, 0, iArr.length);
        this.xOff = rIPEMD128Digest.xOff;
    }

    /* renamed from: f1 */
    private int m339f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m338f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m337f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m336f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD128Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToLittleEndian(this.f26468H0, bArr, i);
        Pack.intToLittleEndian(this.f26469H1, bArr, i + 4);
        Pack.intToLittleEndian(this.f26470H2, bArr, i + 8);
        Pack.intToLittleEndian(this.f26471H3, bArr, i + 12);
        reset();
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f26468H0;
        int i2 = this.f26469H1;
        int i3 = this.f26470H2;
        int i4 = this.f26471H3;
        int m344F1 = m344F1(i, i2, i3, i4, this.f26472X[0], 11);
        int m344F12 = m344F1(i4, m344F1, i2, i3, this.f26472X[1], 14);
        int m344F13 = m344F1(i3, m344F12, m344F1, i2, this.f26472X[2], 15);
        int m344F14 = m344F1(i2, m344F13, m344F12, m344F1, this.f26472X[3], 12);
        int m344F15 = m344F1(m344F1, m344F14, m344F13, m344F12, this.f26472X[4], 5);
        int m344F16 = m344F1(m344F12, m344F15, m344F14, m344F13, this.f26472X[5], 8);
        int m344F17 = m344F1(m344F13, m344F16, m344F15, m344F14, this.f26472X[6], 7);
        int m344F18 = m344F1(m344F14, m344F17, m344F16, m344F15, this.f26472X[7], 9);
        int m344F19 = m344F1(m344F15, m344F18, m344F17, m344F16, this.f26472X[8], 11);
        int m344F110 = m344F1(m344F16, m344F19, m344F18, m344F17, this.f26472X[9], 13);
        int m344F111 = m344F1(m344F17, m344F110, m344F19, m344F18, this.f26472X[10], 14);
        int m344F112 = m344F1(m344F18, m344F111, m344F110, m344F19, this.f26472X[11], 15);
        int m344F113 = m344F1(m344F19, m344F112, m344F111, m344F110, this.f26472X[12], 6);
        int m344F114 = m344F1(m344F110, m344F113, m344F112, m344F111, this.f26472X[13], 7);
        int m344F115 = m344F1(m344F111, m344F114, m344F113, m344F112, this.f26472X[14], 9);
        int m344F116 = m344F1(m344F112, m344F115, m344F114, m344F113, this.f26472X[15], 8);
        int m343F2 = m343F2(m344F113, m344F116, m344F115, m344F114, this.f26472X[7], 7);
        int m343F22 = m343F2(m344F114, m343F2, m344F116, m344F115, this.f26472X[4], 6);
        int m343F23 = m343F2(m344F115, m343F22, m343F2, m344F116, this.f26472X[13], 8);
        int m343F24 = m343F2(m344F116, m343F23, m343F22, m343F2, this.f26472X[1], 13);
        int m343F25 = m343F2(m343F2, m343F24, m343F23, m343F22, this.f26472X[10], 11);
        int m343F26 = m343F2(m343F22, m343F25, m343F24, m343F23, this.f26472X[6], 9);
        int m343F27 = m343F2(m343F23, m343F26, m343F25, m343F24, this.f26472X[15], 7);
        int m343F28 = m343F2(m343F24, m343F27, m343F26, m343F25, this.f26472X[3], 15);
        int m343F29 = m343F2(m343F25, m343F28, m343F27, m343F26, this.f26472X[12], 7);
        int m343F210 = m343F2(m343F26, m343F29, m343F28, m343F27, this.f26472X[0], 12);
        int m343F211 = m343F2(m343F27, m343F210, m343F29, m343F28, this.f26472X[9], 15);
        int m343F212 = m343F2(m343F28, m343F211, m343F210, m343F29, this.f26472X[5], 9);
        int m343F213 = m343F2(m343F29, m343F212, m343F211, m343F210, this.f26472X[2], 11);
        int m343F214 = m343F2(m343F210, m343F213, m343F212, m343F211, this.f26472X[14], 7);
        int m343F215 = m343F2(m343F211, m343F214, m343F213, m343F212, this.f26472X[11], 13);
        int m343F216 = m343F2(m343F212, m343F215, m343F214, m343F213, this.f26472X[8], 12);
        int m342F3 = m342F3(m343F213, m343F216, m343F215, m343F214, this.f26472X[3], 11);
        int m342F32 = m342F3(m343F214, m342F3, m343F216, m343F215, this.f26472X[10], 13);
        int m342F33 = m342F3(m343F215, m342F32, m342F3, m343F216, this.f26472X[14], 6);
        int m342F34 = m342F3(m343F216, m342F33, m342F32, m342F3, this.f26472X[4], 7);
        int m342F35 = m342F3(m342F3, m342F34, m342F33, m342F32, this.f26472X[9], 14);
        int m342F36 = m342F3(m342F32, m342F35, m342F34, m342F33, this.f26472X[15], 9);
        int m342F37 = m342F3(m342F33, m342F36, m342F35, m342F34, this.f26472X[8], 13);
        int m342F38 = m342F3(m342F34, m342F37, m342F36, m342F35, this.f26472X[1], 15);
        int m342F39 = m342F3(m342F35, m342F38, m342F37, m342F36, this.f26472X[2], 14);
        int m342F310 = m342F3(m342F36, m342F39, m342F38, m342F37, this.f26472X[7], 8);
        int m342F311 = m342F3(m342F37, m342F310, m342F39, m342F38, this.f26472X[0], 13);
        int m342F312 = m342F3(m342F38, m342F311, m342F310, m342F39, this.f26472X[6], 6);
        int m342F313 = m342F3(m342F39, m342F312, m342F311, m342F310, this.f26472X[13], 5);
        int m342F314 = m342F3(m342F310, m342F313, m342F312, m342F311, this.f26472X[11], 12);
        int m342F315 = m342F3(m342F311, m342F314, m342F313, m342F312, this.f26472X[5], 7);
        int m342F316 = m342F3(m342F312, m342F315, m342F314, m342F313, this.f26472X[12], 5);
        int m341F4 = m341F4(m342F313, m342F316, m342F315, m342F314, this.f26472X[1], 11);
        int m341F42 = m341F4(m342F314, m341F4, m342F316, m342F315, this.f26472X[9], 12);
        int m341F43 = m341F4(m342F315, m341F42, m341F4, m342F316, this.f26472X[11], 14);
        int m341F44 = m341F4(m342F316, m341F43, m341F42, m341F4, this.f26472X[10], 15);
        int m341F45 = m341F4(m341F4, m341F44, m341F43, m341F42, this.f26472X[0], 14);
        int m341F46 = m341F4(m341F42, m341F45, m341F44, m341F43, this.f26472X[8], 15);
        int m341F47 = m341F4(m341F43, m341F46, m341F45, m341F44, this.f26472X[12], 9);
        int m341F48 = m341F4(m341F44, m341F47, m341F46, m341F45, this.f26472X[4], 8);
        int m341F49 = m341F4(m341F45, m341F48, m341F47, m341F46, this.f26472X[13], 9);
        int m341F410 = m341F4(m341F46, m341F49, m341F48, m341F47, this.f26472X[3], 14);
        int m341F411 = m341F4(m341F47, m341F410, m341F49, m341F48, this.f26472X[7], 5);
        int m341F412 = m341F4(m341F48, m341F411, m341F410, m341F49, this.f26472X[15], 6);
        int m341F413 = m341F4(m341F49, m341F412, m341F411, m341F410, this.f26472X[14], 8);
        int m341F414 = m341F4(m341F410, m341F413, m341F412, m341F411, this.f26472X[5], 6);
        int m341F415 = m341F4(m341F411, m341F414, m341F413, m341F412, this.f26472X[6], 5);
        int m341F416 = m341F4(m341F412, m341F415, m341F414, m341F413, this.f26472X[2], 12);
        int FF4 = FF4(i, i2, i3, i4, this.f26472X[5], 8);
        int FF42 = FF4(i4, FF4, i2, i3, this.f26472X[14], 9);
        int FF43 = FF4(i3, FF42, FF4, i2, this.f26472X[7], 9);
        int FF44 = FF4(i2, FF43, FF42, FF4, this.f26472X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f26472X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f26472X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f26472X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f26472X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f26472X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f26472X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f26472X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f26472X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f26472X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f26472X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f26472X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f26472X[12], 6);
        int FF3 = FF3(FF413, FF416, FF415, FF414, this.f26472X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f26472X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f26472X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f26472X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f26472X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f26472X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f26472X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f26472X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f26472X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f26472X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f26472X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f26472X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f26472X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f26472X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f26472X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f26472X[2], 11);
        int FF2 = FF2(FF313, FF316, FF315, FF314, this.f26472X[15], 9);
        int FF22 = FF2(FF314, FF2, FF316, FF315, this.f26472X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, FF316, this.f26472X[1], 15);
        int FF24 = FF2(FF316, FF23, FF22, FF2, this.f26472X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f26472X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f26472X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f26472X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f26472X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f26472X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f26472X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f26472X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f26472X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f26472X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f26472X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f26472X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f26472X[13], 5);
        int FF1 = FF1(FF213, FF216, FF215, FF214, this.f26472X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, FF215, this.f26472X[6], 5);
        int FF13 = FF1(FF215, FF12, FF1, FF216, this.f26472X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f26472X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f26472X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f26472X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f26472X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f26472X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f26472X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f26472X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f26472X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f26472X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f26472X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f26472X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f26472X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f26472X[14], 8);
        this.f26469H1 = this.f26470H2 + m341F414 + FF113;
        this.f26470H2 = this.f26471H3 + m341F413 + FF116;
        this.f26471H3 = this.f26468H0 + m341F416 + FF115;
        this.f26468H0 = FF114 + m341F415 + this.f26469H1;
        this.xOff = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = this.f26472X;
            if (i5 == iArr.length) {
                return;
            }
            iArr[i5] = 0;
            i5++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f26472X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f26472X;
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
        this.f26468H0 = 1732584193;
        this.f26469H1 = -271733879;
        this.f26470H2 = -1732584194;
        this.f26471H3 = 271733878;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f26472X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD128Digest) memoable);
    }
}
