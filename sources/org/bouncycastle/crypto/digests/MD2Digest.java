package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: S */
    private static final byte[] f26453S = {41, 46, 67, -55, -94, -40, 124, 1, 61, C0548c.f1784h, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, -68, 76, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, 66, 111, 24, -118, 23, -27, 18, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, 63, -108, -62, 16, -119, 11, 34, 95, 33, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, 25, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, -88, 2, 27, 96, 37, -83, -82, -80, -71, -10, 28, 70, 97, 105, 52, 64, 126, 15, 85, 71, -93, 35, -35, 81, -81, 58, -61, C0548c.f1785i, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, 12, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, -14, -17, -73, 14, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, 31, 26, -37, -103, -115, 51, -97, 17, -125, 20};

    /* renamed from: C */
    private byte[] f26454C;
    private int COff;

    /* renamed from: M */
    private byte[] f26455M;

    /* renamed from: X */
    private byte[] f26456X;
    private int mOff;
    private final CryptoServicePurpose purpose;
    private int xOff;

    public MD2Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public MD2Digest(CryptoServicePurpose cryptoServicePurpose) {
        this.f26456X = new byte[48];
        this.f26455M = new byte[16];
        this.f26454C = new byte[16];
        this.purpose = cryptoServicePurpose;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 64, cryptoServicePurpose));
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.f26456X = new byte[48];
        this.f26455M = new byte[16];
        this.f26454C = new byte[16];
        this.purpose = mD2Digest.purpose;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 64, this.purpose));
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f26456X;
        System.arraycopy(bArr, 0, this.f26456X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.f26455M;
        System.arraycopy(bArr2, 0, this.f26455M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.f26454C;
        System.arraycopy(bArr3, 0, this.f26454C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int length = this.f26455M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.f26455M;
            if (i2 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.f26455M);
                processBlock(this.f26454C);
                System.arraycopy(this.f26456X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "MD2";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    protected void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f26456X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            int i4 = i2;
            for (int i5 = 0; i5 < 48; i5++) {
                byte[] bArr3 = this.f26456X;
                byte b = (byte) (f26453S[i4] ^ bArr3[i5]);
                bArr3[i5] = b;
                i4 = b & 255;
            }
            i2 = (i4 + i3) % 256;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.f26454C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f26454C;
            bArr2[i] = (byte) (f26453S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            b = bArr2[i];
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f26456X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f26455M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f26454C;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.f26455M;
        int i = this.mOff;
        this.mOff = i + 1;
        bArr[i] = b;
        if (this.mOff == 16) {
            processCheckSum(bArr);
            processBlock(this.f26455M);
            this.mOff = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 >= 16) {
            System.arraycopy(bArr, i, this.f26455M, 0, 16);
            processCheckSum(this.f26455M);
            processBlock(this.f26455M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
