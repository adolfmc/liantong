package org.bouncycastle.pqc.math.ntru;

import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HPS4096Polynomial extends HPSPolynomial {
    public HPS4096Polynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        for (int i = 0; i < this.params.packDegree() / 2; i++) {
            int i2 = i * 2;
            int i3 = i * 3;
            int i4 = i3 + 1;
            this.coeffs[i2 + 0] = (short) (((bArr[i3 + 0] & 255) >>> 0) | ((((short) (bArr[i4] & 255)) & 15) << 8));
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & 255) >>> 4) | ((((short) (bArr[i3 + 2] & 255)) & 255) << 4));
        }
        this.coeffs[this.params.m232n() - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        int m231q = this.params.m231q();
        for (int i2 = 0; i2 < this.params.packDegree() / 2; i2++) {
            int i3 = i2 * 3;
            int i4 = i2 * 2;
            int i5 = i4 + 0;
            bArr[i3 + 0] = (byte) (modQ(this.coeffs[i5] & 65535, m231q) & 255);
            int i6 = i4 + 1;
            bArr[i3 + 1] = (byte) ((modQ(this.coeffs[i5] & 65535, m231q) >>> 8) | ((modQ(this.coeffs[i6] & 65535, m231q) & 15) << 4));
            bArr[i3 + 2] = (byte) (modQ(this.coeffs[i6] & 65535, m231q) >>> 4);
        }
        return bArr;
    }
}
