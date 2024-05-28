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
public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: Y1 */
    private static final int f26499Y1 = 1518500249;

    /* renamed from: Y2 */
    private static final int f26500Y2 = 1859775393;

    /* renamed from: Y3 */
    private static final int f26501Y3 = -1894007588;

    /* renamed from: Y4 */
    private static final int f26502Y4 = -899497514;

    /* renamed from: H1 */
    private int f26503H1;

    /* renamed from: H2 */
    private int f26504H2;

    /* renamed from: H3 */
    private int f26505H3;

    /* renamed from: H4 */
    private int f26506H4;

    /* renamed from: H5 */
    private int f26507H5;

    /* renamed from: X */
    private int[] f26508X;
    private int xOff;

    public SHA1Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public SHA1Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        this.f26508X = new int[80];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f26508X = new int[80];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f26508X = new int[80];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        this.f26503H1 = Pack.bigEndianToInt(bArr, 16);
        this.f26504H2 = Pack.bigEndianToInt(bArr, 20);
        this.f26505H3 = Pack.bigEndianToInt(bArr, 24);
        this.f26506H4 = Pack.bigEndianToInt(bArr, 28);
        this.f26507H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            this.f26508X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.f26503H1 = sHA1Digest.f26503H1;
        this.f26504H2 = sHA1Digest.f26504H2;
        this.f26505H3 = sHA1Digest.f26505H3;
        this.f26506H4 = sHA1Digest.f26506H4;
        this.f26507H5 = sHA1Digest.f26507H5;
        int[] iArr = sHA1Digest.f26508X;
        System.arraycopy(iArr, 0, this.f26508X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }

    /* renamed from: f */
    private int m314f(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: g */
    private int m313g(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: h */
    private int m312h(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, 128, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f26503H1, bArr, i);
        Pack.intToBigEndian(this.f26504H2, bArr, i + 4);
        Pack.intToBigEndian(this.f26505H3, bArr, i + 8);
        Pack.intToBigEndian(this.f26506H4, bArr, i + 12);
        Pack.intToBigEndian(this.f26507H5, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-1";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 40 + 1];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f26503H1, bArr, 16);
        Pack.intToBigEndian(this.f26504H2, bArr, 20);
        Pack.intToBigEndian(this.f26505H3, bArr, 24);
        Pack.intToBigEndian(this.f26506H4, bArr, 28);
        Pack.intToBigEndian(this.f26507H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f26508X[i], bArr, (i * 4) + 40);
        }
        bArr[bArr.length - 1] = (byte) this.purpose.ordinal();
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f26508X;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.f26503H1;
        int i4 = this.f26504H2;
        int i5 = this.f26505H3;
        int i6 = this.f26506H4;
        int i7 = this.f26507H5;
        int i8 = i6;
        int i9 = 0;
        int i10 = i5;
        int i11 = i4;
        int i12 = i3;
        int i13 = 0;
        while (i13 < 4) {
            int i14 = i9 + 1;
            int m314f = i7 + ((i12 << 5) | (i12 >>> 27)) + m314f(i11, i10, i8) + this.f26508X[i9] + C0512f.f1670m;
            int i15 = (i11 >>> 2) | (i11 << 30);
            int i16 = i14 + 1;
            int m314f2 = i8 + ((m314f << 5) | (m314f >>> 27)) + m314f(i12, i15, i10) + this.f26508X[i14] + C0512f.f1670m;
            int i17 = (i12 >>> 2) | (i12 << 30);
            int i18 = i16 + 1;
            int m314f3 = i10 + ((m314f2 << 5) | (m314f2 >>> 27)) + m314f(m314f, i17, i15) + this.f26508X[i16] + C0512f.f1670m;
            i7 = (m314f >>> 2) | (m314f << 30);
            int i19 = i18 + 1;
            i11 = i15 + ((m314f3 << 5) | (m314f3 >>> 27)) + m314f(m314f2, i7, i17) + this.f26508X[i18] + C0512f.f1670m;
            i8 = (m314f2 >>> 2) | (m314f2 << 30);
            i12 = i17 + ((i11 << 5) | (i11 >>> 27)) + m314f(m314f3, i8, i7) + this.f26508X[i19] + C0512f.f1670m;
            i10 = (m314f3 >>> 2) | (m314f3 << 30);
            i13++;
            i9 = i19 + 1;
        }
        int i20 = 0;
        while (i20 < 4) {
            int i21 = i9 + 1;
            int m312h = i7 + ((i12 << 5) | (i12 >>> 27)) + m312h(i11, i10, i8) + this.f26508X[i9] + C0512f.f1671n;
            int i22 = (i11 >>> 2) | (i11 << 30);
            int i23 = i21 + 1;
            int m312h2 = i8 + ((m312h << 5) | (m312h >>> 27)) + m312h(i12, i22, i10) + this.f26508X[i21] + C0512f.f1671n;
            int i24 = (i12 >>> 2) | (i12 << 30);
            int i25 = i23 + 1;
            int m312h3 = i10 + ((m312h2 << 5) | (m312h2 >>> 27)) + m312h(m312h, i24, i22) + this.f26508X[i23] + C0512f.f1671n;
            i7 = (m312h >>> 2) | (m312h << 30);
            int i26 = i25 + 1;
            i11 = i22 + ((m312h3 << 5) | (m312h3 >>> 27)) + m312h(m312h2, i7, i24) + this.f26508X[i25] + C0512f.f1671n;
            i8 = (m312h2 >>> 2) | (m312h2 << 30);
            i12 = i24 + ((i11 << 5) | (i11 >>> 27)) + m312h(m312h3, i8, i7) + this.f26508X[i26] + C0512f.f1671n;
            i10 = (m312h3 >>> 2) | (m312h3 << 30);
            i20++;
            i9 = i26 + 1;
        }
        int i27 = 0;
        while (i27 < 4) {
            int i28 = i9 + 1;
            int m313g = i7 + ((i12 << 5) | (i12 >>> 27)) + m313g(i11, i10, i8) + this.f26508X[i9] + C0512f.f1672o;
            int i29 = (i11 >>> 2) | (i11 << 30);
            int i30 = i28 + 1;
            int m313g2 = i8 + ((m313g << 5) | (m313g >>> 27)) + m313g(i12, i29, i10) + this.f26508X[i28] + C0512f.f1672o;
            int i31 = (i12 >>> 2) | (i12 << 30);
            int i32 = i30 + 1;
            int m313g3 = i10 + ((m313g2 << 5) | (m313g2 >>> 27)) + m313g(m313g, i31, i29) + this.f26508X[i30] + C0512f.f1672o;
            i7 = (m313g >>> 2) | (m313g << 30);
            int i33 = i32 + 1;
            i11 = i29 + ((m313g3 << 5) | (m313g3 >>> 27)) + m313g(m313g2, i7, i31) + this.f26508X[i32] + C0512f.f1672o;
            i8 = (m313g2 >>> 2) | (m313g2 << 30);
            i12 = i31 + ((i11 << 5) | (i11 >>> 27)) + m313g(m313g3, i8, i7) + this.f26508X[i33] + C0512f.f1672o;
            i10 = (m313g3 >>> 2) | (m313g3 << 30);
            i27++;
            i9 = i33 + 1;
        }
        int i34 = 0;
        while (i34 <= 3) {
            int i35 = i9 + 1;
            int m312h4 = i7 + ((i12 << 5) | (i12 >>> 27)) + m312h(i11, i10, i8) + this.f26508X[i9] + C0512f.f1673p;
            int i36 = (i11 >>> 2) | (i11 << 30);
            int i37 = i35 + 1;
            int m312h5 = i8 + ((m312h4 << 5) | (m312h4 >>> 27)) + m312h(i12, i36, i10) + this.f26508X[i35] + C0512f.f1673p;
            int i38 = (i12 >>> 2) | (i12 << 30);
            int i39 = i37 + 1;
            int m312h6 = i10 + ((m312h5 << 5) | (m312h5 >>> 27)) + m312h(m312h4, i38, i36) + this.f26508X[i37] + C0512f.f1673p;
            i7 = (m312h4 >>> 2) | (m312h4 << 30);
            int i40 = i39 + 1;
            i11 = i36 + ((m312h6 << 5) | (m312h6 >>> 27)) + m312h(m312h5, i7, i38) + this.f26508X[i39] + C0512f.f1673p;
            i8 = (m312h5 >>> 2) | (m312h5 << 30);
            i12 = i38 + ((i11 << 5) | (i11 >>> 27)) + m312h(m312h6, i8, i7) + this.f26508X[i40] + C0512f.f1673p;
            i10 = (m312h6 >>> 2) | (m312h6 << 30);
            i34++;
            i9 = i40 + 1;
        }
        this.f26503H1 += i12;
        this.f26504H2 += i11;
        this.f26505H3 += i10;
        this.f26506H4 += i8;
        this.f26507H5 += i7;
        this.xOff = 0;
        for (int i41 = 0; i41 < 16; i41++) {
            this.f26508X[i41] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f26508X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) j;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        this.f26508X[this.xOff] = Pack.bigEndianToInt(bArr, i);
        int i2 = this.xOff + 1;
        this.xOff = i2;
        if (i2 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f26503H1 = 1732584193;
        this.f26504H2 = -271733879;
        this.f26505H3 = -1732584194;
        this.f26506H4 = 271733878;
        this.f26507H5 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f26508X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }
}
