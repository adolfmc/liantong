package org.bouncycastle.pqc.math.ntru;

import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HPSPolynomial extends Polynomial {
    public HPSPolynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        System.arraycopy(polynomial.coeffs, 0, this.coeffs, 0, this.coeffs.length);
        z3ToZq();
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void r2Inv(Polynomial polynomial) {
        r2Inv(polynomial, new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void rqInv(Polynomial polynomial) {
        rqInv(polynomial, new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void s3Inv(Polynomial polynomial) {
        s3Inv(polynomial, new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 11;
            int i4 = i3 + 1;
            this.coeffs[i2 + 0] = (short) (((bArr[i3 + 0] & 255) >>> 0) | ((((short) (bArr[i4] & 255)) & 7) << 8));
            int i5 = i3 + 2;
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & 255) >>> 3) | ((((short) (bArr[i5] & 255)) & 63) << 5));
            int i6 = ((bArr[i5] & 255) >>> 6) | ((((short) (bArr[i3 + 3] & 255)) & 255) << 2);
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (i6 | ((((short) (bArr[i7] & 255)) & 1) << 10));
            int i8 = i3 + 5;
            this.coeffs[i2 + 3] = (short) (((bArr[i7] & 255) >>> 1) | ((((short) (bArr[i8] & 255)) & 15) << 7));
            int i9 = i3 + 6;
            this.coeffs[i2 + 4] = (short) (((((short) (bArr[i9] & 255)) & 127) << 4) | ((bArr[i8] & 255) >>> 4));
            int i10 = i3 + 8;
            this.coeffs[i2 + 5] = (short) (((bArr[i9] & 255) >>> 7) | ((((short) (bArr[i3 + 7] & 255)) & 255) << 1) | ((((short) (bArr[i10] & 255)) & 3) << 9));
            int i11 = i3 + 9;
            this.coeffs[i2 + 6] = (short) (((bArr[i10] & 255) >>> 2) | ((((short) (bArr[i11] & 255)) & 31) << 6));
            this.coeffs[i2 + 7] = (short) (((bArr[i11] & 255) >>> 5) | ((((short) (bArr[i3 + 10] & 255)) & 255) << 3));
            i++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i12 = i * 8;
            int i13 = i * 11;
            int i14 = i13 + 1;
            this.coeffs[i12 + 0] = (short) (((bArr[i13 + 0] & 255) >>> 0) | ((((short) (bArr[i14] & 255)) & 7) << 8));
            this.coeffs[i12 + 1] = (short) (((((short) (bArr[i13 + 2] & 255)) & 63) << 5) | ((bArr[i14] & 255) >>> 3));
        } else if (packDegree == 4) {
            int i15 = i * 8;
            int i16 = i * 11;
            int i17 = i16 + 1;
            this.coeffs[i15 + 0] = (short) (((bArr[i16 + 0] & 255) >>> 0) | ((((short) (bArr[i17] & 255)) & 7) << 8));
            int i18 = i16 + 2;
            this.coeffs[i15 + 1] = (short) (((bArr[i17] & 255) >>> 3) | ((((short) (bArr[i18] & 255)) & 63) << 5));
            int i19 = i16 + 4;
            this.coeffs[i15 + 2] = (short) (((((short) (bArr[i16 + 3] & 255)) & 255) << 2) | ((bArr[i18] & 255) >>> 6) | ((((short) (bArr[i19] & 255)) & 1) << 10));
            this.coeffs[i15 + 3] = (short) (((((short) (bArr[i16 + 5] & 255)) & 15) << 7) | ((bArr[i19] & 255) >>> 1));
        }
        this.coeffs[length - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        short[] sArr = new short[8];
        int i2 = 0;
        while (true) {
            short s = 65535;
            if (i2 >= this.params.packDegree() / 8) {
                break;
            }
            int i3 = 0;
            while (i3 < 8) {
                sArr[i3] = (short) modQ(this.coeffs[(i2 * 8) + i3] & s, this.params.m231q());
                i3++;
                s = 65535;
            }
            int i4 = i2 * 11;
            bArr[i4 + 0] = (byte) (sArr[0] & 255);
            bArr[i4 + 1] = (byte) ((sArr[0] >>> 8) | ((sArr[1] & 31) << 3));
            bArr[i4 + 2] = (byte) ((sArr[1] >>> 5) | ((sArr[2] & 3) << 6));
            bArr[i4 + 3] = (byte) ((sArr[2] >>> 2) & 255);
            bArr[i4 + 4] = (byte) ((sArr[2] >>> 10) | ((sArr[3] & 127) << 1));
            bArr[i4 + 5] = (byte) ((sArr[3] >>> 7) | ((sArr[4] & 15) << 4));
            bArr[i4 + 6] = (byte) ((sArr[4] >>> 4) | ((sArr[5] & 1) << 7));
            bArr[i4 + 7] = (byte) ((sArr[5] >>> 1) & 255);
            bArr[i4 + 8] = (byte) ((sArr[5] >>> 9) | ((sArr[6] & 63) << 2));
            bArr[i4 + 9] = (byte) ((sArr[6] >>> 6) | ((sArr[7] & 7) << 5));
            bArr[i4 + 10] = (byte) (sArr[7] >>> 3);
            i2++;
        }
        int i5 = 0;
        while (true) {
            int i6 = i2 * 8;
            if (i5 >= this.params.packDegree() - i6) {
                break;
            }
            sArr[i5] = (short) modQ(this.coeffs[i6 + i5] & 65535, this.params.m231q());
            i5++;
        }
        while (i5 < 8) {
            sArr[i5] = 0;
            i5++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i7 = i2 * 11;
            bArr[i7 + 0] = (byte) (sArr[0] & 255);
            bArr[i7 + 1] = (byte) ((sArr[0] >>> 8) | ((sArr[1] & 31) << 3));
            bArr[i7 + 2] = (byte) ((sArr[1] >>> 5) | ((sArr[2] & 3) << 6));
        } else if (packDegree == 4) {
            int i8 = i2 * 11;
            bArr[i8 + 0] = (byte) (sArr[0] & 255);
            bArr[i8 + 1] = (byte) ((sArr[0] >>> 8) | ((sArr[1] & 31) << 3));
            bArr[i8 + 2] = (byte) ((sArr[1] >>> 5) | ((sArr[2] & 3) << 6));
            bArr[i8 + 3] = (byte) ((sArr[2] >>> 2) & 255);
            bArr[i8 + 4] = (byte) ((sArr[2] >>> 10) | ((sArr[3] & 127) << 1));
            bArr[i8 + 5] = (byte) ((sArr[3] >>> 7) | ((sArr[4] & 15) << 4));
        }
        return bArr;
    }
}
