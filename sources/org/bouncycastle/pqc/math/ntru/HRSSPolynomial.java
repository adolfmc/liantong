package org.bouncycastle.pqc.math.ntru;

import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSSParameterSet;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HRSSPolynomial extends Polynomial {
    public HRSSPolynomial(NTRUHRSSParameterSet nTRUHRSSParameterSet) {
        super(nTRUHRSSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        int length = this.coeffs.length;
        HRSSPolynomial hRSSPolynomial = new HRSSPolynomial((NTRUHRSSParameterSet) this.params);
        short s = (short) (3 - (length % 3));
        int i = 0;
        int i2 = 2 - s;
        hRSSPolynomial.coeffs[0] = (short) ((polynomial.coeffs[0] * i2) + (polynomial.coeffs[1] * 0) + (polynomial.coeffs[2] * s));
        hRSSPolynomial.coeffs[1] = (short) ((polynomial.coeffs[1] * i2) + (polynomial.coeffs[2] * 0));
        hRSSPolynomial.coeffs[2] = (short) (polynomial.coeffs[2] * i2);
        short s2 = 0;
        for (int i3 = 3; i3 < length; i3++) {
            short[] sArr = hRSSPolynomial.coeffs;
            sArr[0] = (short) (sArr[0] + (polynomial.coeffs[i3] * ((s * 2) + s2)));
            short[] sArr2 = hRSSPolynomial.coeffs;
            int i4 = s2 + s;
            sArr2[1] = (short) (sArr2[1] + (polynomial.coeffs[i3] * i4));
            short[] sArr3 = hRSSPolynomial.coeffs;
            sArr3[2] = (short) (sArr3[2] + (polynomial.coeffs[i3] * s2));
            s2 = (short) (i4 % 3);
        }
        short[] sArr4 = hRSSPolynomial.coeffs;
        int i5 = s + s2;
        sArr4[1] = (short) (sArr4[1] + (polynomial.coeffs[0] * i5));
        short[] sArr5 = hRSSPolynomial.coeffs;
        sArr5[2] = (short) (sArr5[2] + (polynomial.coeffs[0] * s2));
        short[] sArr6 = hRSSPolynomial.coeffs;
        sArr6[2] = (short) (sArr6[2] + (polynomial.coeffs[1] * i5));
        for (int i6 = 3; i6 < length; i6++) {
            hRSSPolynomial.coeffs[i6] = (short) (hRSSPolynomial.coeffs[i6 - 3] + ((polynomial.coeffs[i6] + polynomial.coeffs[i6 - 1] + polynomial.coeffs[i6 - 2]) * 2));
        }
        hRSSPolynomial.mod3PhiN();
        hRSSPolynomial.z3ToZq();
        this.coeffs[0] = (short) (-hRSSPolynomial.coeffs[0]);
        while (i < length - 1) {
            int i7 = i + 1;
            this.coeffs[i7] = (short) (hRSSPolynomial.coeffs[i] - hRSSPolynomial.coeffs[i7]);
            i = i7;
        }
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void r2Inv(Polynomial polynomial) {
        r2Inv(polynomial, new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void rqInv(Polynomial polynomial) {
        rqInv(polynomial, new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void s3Inv(Polynomial polynomial) {
        s3Inv(polynomial, new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 13;
            int i4 = i3 + 1;
            this.coeffs[i2 + 0] = (short) ((bArr[i3 + 0] & 255) | ((((short) (bArr[i4] & 255)) & 31) << 8));
            int i5 = ((bArr[i4] & 255) >>> 5) | (((short) (bArr[i3 + 2] & 255)) << 3);
            int i6 = i3 + 3;
            this.coeffs[i2 + 1] = (short) (i5 | ((((short) (bArr[i6] & 255)) & 3) << 11));
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (((bArr[i6] & 255) >>> 2) | ((((short) (bArr[i7] & 255)) & 127) << 6));
            int i8 = ((bArr[i7] & 255) >>> 7) | (((short) (bArr[i3 + 5] & 255)) << 1);
            int i9 = i3 + 6;
            this.coeffs[i2 + 3] = (short) (i8 | ((((short) (bArr[i9] & 255)) & 15) << 9));
            int i10 = i3 + 8;
            this.coeffs[i2 + 4] = (short) ((((short) (bArr[i3 + 7] & 255)) << 4) | ((bArr[i9] & 255) >>> 4) | ((((short) (bArr[i10] & 255)) & 1) << 12));
            int i11 = i3 + 9;
            this.coeffs[i2 + 5] = (short) (((bArr[i10] & 255) >>> 1) | ((((short) (bArr[i11] & 255)) & 63) << 7));
            int i12 = i3 + 11;
            this.coeffs[i2 + 6] = (short) ((((short) (bArr[i3 + 10] & 255)) << 2) | ((bArr[i11] & 255) >>> 6) | ((((short) (bArr[i12] & 255)) & 7) << 10));
            this.coeffs[i2 + 7] = (short) (((bArr[i12] & 255) >>> 3) | (((short) (bArr[i3 + 12] & 255)) << 5));
            i++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i13 = i * 8;
            int i14 = i * 13;
            int i15 = i14 + 1;
            this.coeffs[i13 + 0] = (short) ((bArr[i14 + 0] & 255) | ((((short) (bArr[i15] & 255)) & 31) << 8));
            this.coeffs[i13 + 1] = (short) (((((short) (bArr[i14 + 3] & 255)) & 3) << 11) | ((bArr[i15] & 255) >>> 5) | (((short) (bArr[i14 + 2] & 255)) << 3));
        } else if (packDegree == 4) {
            int i16 = i * 8;
            int i17 = i * 13;
            int i18 = i17 + 1;
            this.coeffs[i16 + 0] = (short) ((bArr[i17 + 0] & 255) | ((((short) (bArr[i18] & 255)) & 31) << 8));
            int i19 = ((bArr[i18] & 255) >>> 5) | (((short) (bArr[i17 + 2] & 255)) << 3);
            int i20 = i17 + 3;
            this.coeffs[i16 + 1] = (short) (i19 | ((((short) (bArr[i20] & 255)) & 3) << 11));
            int i21 = i17 + 4;
            this.coeffs[i16 + 2] = (short) (((bArr[i20] & 255) >>> 2) | ((((short) (bArr[i21] & 255)) & 127) << 6));
            this.coeffs[i16 + 3] = (short) (((((short) (bArr[i17 + 6] & 255)) & 15) << 9) | ((bArr[i21] & 255) >>> 7) | (((short) (bArr[i17 + 5] & 255)) << 1));
        }
        this.coeffs[this.params.m232n() - 1] = 0;
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
            int i4 = i2 * 13;
            bArr[i4 + 0] = (byte) (sArr[0] & 255);
            bArr[i4 + 1] = (byte) ((sArr[0] >>> 8) | ((sArr[1] & 7) << 5));
            bArr[i4 + 2] = (byte) ((sArr[1] >>> 3) & 255);
            bArr[i4 + 3] = (byte) ((sArr[1] >>> 11) | ((sArr[2] & 63) << 2));
            bArr[i4 + 4] = (byte) ((sArr[2] >>> 6) | ((sArr[3] & 1) << 7));
            bArr[i4 + 5] = (byte) ((sArr[3] >>> 1) & 255);
            bArr[i4 + 6] = (byte) ((sArr[3] >>> 9) | ((sArr[4] & 15) << 4));
            bArr[i4 + 7] = (byte) ((sArr[4] >>> 4) & 255);
            bArr[i4 + 8] = (byte) ((sArr[4] >>> 12) | ((sArr[5] & 127) << 1));
            bArr[i4 + 9] = (byte) ((sArr[5] >>> 7) | ((sArr[6] & 3) << 6));
            bArr[i4 + 10] = (byte) ((sArr[6] >>> 2) & 255);
            bArr[i4 + 11] = (byte) ((sArr[6] >>> 10) | ((sArr[7] & 31) << 3));
            bArr[i4 + 12] = (byte) (sArr[7] >>> 5);
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
        int packDegree = this.params.packDegree() - ((this.params.packDegree() / 8) * 8);
        if (packDegree != 2) {
            if (packDegree == 4) {
                int i7 = i2 * 13;
                bArr[i7 + 0] = (byte) (sArr[0] & 255);
                bArr[i7 + 1] = (byte) ((sArr[0] >>> 8) | ((sArr[1] & 7) << 5));
                bArr[i7 + 2] = (byte) ((sArr[1] >>> 3) & 255);
                bArr[i7 + 3] = (byte) ((sArr[1] >>> 11) | ((sArr[2] & 63) << 2));
                bArr[i7 + 4] = (byte) ((sArr[2] >>> 6) | ((sArr[3] & 1) << 7));
                bArr[i7 + 5] = (byte) ((sArr[3] >>> 1) & 255);
                bArr[i7 + 6] = (byte) ((sArr[3] >>> 9) | ((sArr[4] & 15) << 4));
            }
            return bArr;
        }
        int i8 = i2 * 13;
        bArr[i8 + 0] = (byte) (sArr[0] & 255);
        bArr[i8 + 1] = (byte) ((sArr[0] >>> 8) | ((sArr[1] & 7) << 5));
        bArr[i8 + 2] = (byte) ((sArr[1] >>> 3) & 255);
        bArr[i8 + 3] = (byte) ((sArr[1] >>> 11) | ((sArr[2] & 63) << 2));
        return bArr;
    }
}
