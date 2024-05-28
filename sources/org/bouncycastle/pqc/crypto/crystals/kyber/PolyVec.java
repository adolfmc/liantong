package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PolyVec {
    private KyberEngine engine;
    private int kyberK;
    private int polyVecBytes;
    Poly[] vec;

    public PolyVec() throws Exception {
        throw new Exception("Requires Parameter");
    }

    public PolyVec(KyberEngine kyberEngine) {
        this.engine = kyberEngine;
        this.kyberK = kyberEngine.getKyberK();
        this.polyVecBytes = kyberEngine.getKyberPolyVecBytes();
        this.vec = new Poly[this.kyberK];
        for (int i = 0; i < this.kyberK; i++) {
            this.vec[i] = new Poly(kyberEngine);
        }
    }

    public static void pointwiseAccountMontgomery(Poly poly, PolyVec polyVec, PolyVec polyVec2, KyberEngine kyberEngine) {
        Poly poly2 = new Poly(kyberEngine);
        Poly.baseMultMontgomery(poly, polyVec.getVectorIndex(0), polyVec2.getVectorIndex(0));
        for (int i = 1; i < kyberEngine.getKyberK(); i++) {
            Poly.baseMultMontgomery(poly2, polyVec.getVectorIndex(i), polyVec2.getVectorIndex(i));
            poly.addCoeffs(poly2);
        }
        poly.reduce();
    }

    public void addPoly(PolyVec polyVec) {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).addCoeffs(polyVec.getVectorIndex(i));
        }
    }

    public byte[] compressPolyVec() {
        conditionalSubQ();
        byte[] bArr = new byte[this.engine.getKyberPolyVecCompressedBytes()];
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 320) {
            short[] sArr = new short[4];
            int i = 0;
            int i2 = 0;
            while (i < this.kyberK) {
                int i3 = i2;
                for (int i4 = 0; i4 < 64; i4++) {
                    for (int i5 = 0; i5 < 4; i5++) {
                        sArr[i5] = (short) ((((getVectorIndex(i).getCoeffIndex((i4 * 4) + i5) << 10) + 1664) / 3329) & 1023);
                    }
                    bArr[i3 + 0] = (byte) (sArr[0] >> 0);
                    bArr[i3 + 1] = (byte) ((sArr[0] >> 8) | (sArr[1] << 2));
                    bArr[i3 + 2] = (byte) ((sArr[1] >> 6) | (sArr[2] << 4));
                    bArr[i3 + 3] = (byte) ((sArr[2] >> 4) | (sArr[3] << 6));
                    bArr[i3 + 4] = (byte) (sArr[3] >> 2);
                    i3 += 5;
                }
                i++;
                i2 = i3;
            }
        } else if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        } else {
            short[] sArr2 = new short[8];
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.kyberK) {
                int i8 = i7;
                for (int i9 = 0; i9 < 32; i9++) {
                    for (int i10 = 0; i10 < 8; i10++) {
                        sArr2[i10] = (short) ((((getVectorIndex(i6).getCoeffIndex((i9 * 8) + i10) << 11) + 1664) / 3329) & 2047);
                    }
                    bArr[i8 + 0] = (byte) (sArr2[0] >> 0);
                    bArr[i8 + 1] = (byte) ((sArr2[0] >> 8) | (sArr2[1] << 3));
                    bArr[i8 + 2] = (byte) ((sArr2[1] >> 5) | (sArr2[2] << 6));
                    bArr[i8 + 3] = (byte) (sArr2[2] >> 2);
                    bArr[i8 + 4] = (byte) ((sArr2[2] >> 10) | (sArr2[3] << 1));
                    bArr[i8 + 5] = (byte) ((sArr2[3] >> 7) | (sArr2[4] << 4));
                    bArr[i8 + 6] = (byte) ((sArr2[4] >> 4) | (sArr2[5] << 7));
                    bArr[i8 + 7] = (byte) (sArr2[5] >> 1);
                    bArr[i8 + 8] = (byte) ((sArr2[5] >> 9) | (sArr2[6] << 2));
                    bArr[i8 + 9] = (byte) ((sArr2[6] >> 6) | (sArr2[7] << 5));
                    bArr[i8 + 10] = (byte) (sArr2[7] >> 3);
                    i8 += 11;
                }
                i6++;
                i7 = i8;
            }
        }
        return bArr;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).conditionalSubQ();
        }
    }

    public void decompressPolyVec(byte[] bArr) {
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 320) {
            short[] sArr = new short[4];
            int i = 0;
            int i2 = 0;
            while (i < this.kyberK) {
                int i3 = i2;
                for (int i4 = 0; i4 < 64; i4++) {
                    int i5 = i3 + 1;
                    sArr[0] = (short) (((bArr[i3] & 255) >> 0) | ((short) ((bArr[i5] & 255) << 8)));
                    int i6 = i3 + 2;
                    sArr[1] = (short) (((bArr[i5] & 255) >> 2) | ((short) ((bArr[i6] & 255) << 6)));
                    int i7 = i3 + 3;
                    sArr[2] = (short) (((bArr[i6] & 255) >> 4) | ((short) ((bArr[i7] & 255) << 4)));
                    sArr[3] = (short) (((bArr[i7] & 255) >> 6) | ((short) ((bArr[i3 + 4] & 255) << 2)));
                    i3 += 5;
                    for (int i8 = 0; i8 < 4; i8++) {
                        this.vec[i].setCoeffIndex((i4 * 4) + i8, (short) ((((sArr[i8] & 1023) * 3329) + 512) >> 10));
                    }
                }
                i++;
                i2 = i3;
            }
        } else if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        } else {
            short[] sArr2 = new short[8];
            int i9 = 0;
            int i10 = 0;
            while (i9 < this.kyberK) {
                int i11 = i10;
                for (int i12 = 0; i12 < 32; i12++) {
                    int i13 = i11 + 1;
                    sArr2[0] = (short) (((bArr[i11] & 255) >> 0) | (((short) (bArr[i13] & 255)) << 8));
                    int i14 = i11 + 2;
                    sArr2[1] = (short) (((bArr[i13] & 255) >> 3) | (((short) (bArr[i14] & 255)) << 5));
                    int i15 = ((bArr[i14] & 255) >> 6) | (((short) (bArr[i11 + 3] & 255)) << 2);
                    int i16 = i11 + 4;
                    sArr2[2] = (short) (i15 | ((short) ((bArr[i16] & 255) << 10)));
                    int i17 = i11 + 5;
                    sArr2[3] = (short) (((bArr[i16] & 255) >> 1) | (((short) (bArr[i17] & 255)) << 7));
                    int i18 = i11 + 6;
                    sArr2[4] = (short) (((bArr[i17] & 255) >> 4) | (((short) (bArr[i18] & 255)) << 4));
                    int i19 = ((bArr[i18] & 255) >> 7) | (((short) (bArr[i11 + 7] & 255)) << 1);
                    int i20 = i11 + 8;
                    sArr2[5] = (short) (i19 | ((short) ((bArr[i20] & 255) << 9)));
                    int i21 = i11 + 9;
                    sArr2[6] = (short) (((bArr[i20] & 255) >> 2) | (((short) (bArr[i21] & 255)) << 6));
                    sArr2[7] = (short) (((bArr[i21] & 255) >> 5) | (((short) (bArr[i11 + 10] & 255)) << 3));
                    i11 += 11;
                    for (int i22 = 0; i22 < 8; i22++) {
                        this.vec[i9].setCoeffIndex((i12 * 8) + i22, (short) ((((sArr2[i22] & 2047) * 3329) + 1024) >> 11));
                    }
                }
                i9++;
                i10 = i11;
            }
        }
    }

    public void fromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.kyberK) {
            Poly vectorIndex = getVectorIndex(i);
            int i2 = i * 384;
            i++;
            vectorIndex.fromBytes(Arrays.copyOfRange(bArr, i2, i * 384));
        }
    }

    public Poly getVectorIndex(int i) {
        return this.vec[i];
    }

    public void polyVecInverseNttToMont() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyInverseNttToMont();
        }
    }

    public void polyVecNtt() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyNtt();
        }
    }

    public void reducePoly() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).reduce();
        }
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.polyVecBytes];
        for (int i = 0; i < this.kyberK; i++) {
            System.arraycopy(this.vec[i].toBytes(), 0, bArr, i * 384, 384);
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (int i = 0; i < this.kyberK; i++) {
            stringBuffer.append(this.vec[i].toString());
            if (i != this.kyberK - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
