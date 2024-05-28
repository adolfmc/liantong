package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SIG {

    /* renamed from: r */
    private final byte[] f27244r;
    private final SIG_FORS[] sig_fors;
    private final SIG_XMSS[] sig_ht;

    public SIG(int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr) {
        this.f27244r = new byte[i];
        System.arraycopy(bArr, 0, this.f27244r, 0, i);
        this.sig_fors = new SIG_FORS[i2];
        int i7 = i;
        int i8 = 0;
        while (i8 != i2) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, i7, bArr2, 0, i);
            byte[][] bArr3 = new byte[i3];
            int i9 = i7 + i;
            for (int i10 = 0; i10 != i3; i10++) {
                bArr3[i10] = new byte[i];
                System.arraycopy(bArr, i9, bArr3[i10], 0, i);
                i9 += i;
            }
            this.sig_fors[i8] = new SIG_FORS(bArr2, bArr3);
            i8++;
            i7 = i9;
        }
        this.sig_ht = new SIG_XMSS[i4];
        int i11 = 0;
        while (i11 != i4) {
            byte[] bArr4 = new byte[i6 * i];
            System.arraycopy(bArr, i7, bArr4, 0, bArr4.length);
            byte[][] bArr5 = new byte[i5];
            int length = i7 + bArr4.length;
            for (int i12 = 0; i12 != i5; i12++) {
                bArr5[i12] = new byte[i];
                System.arraycopy(bArr, length, bArr5[i12], 0, i);
                length += i;
            }
            this.sig_ht[i11] = new SIG_XMSS(bArr4, bArr5);
            i11++;
            i7 = length;
        }
        if (i7 != bArr.length) {
            throw new IllegalArgumentException("signature wrong length");
        }
    }

    public byte[] getR() {
        return this.f27244r;
    }

    public SIG_FORS[] getSIG_FORS() {
        return this.sig_fors;
    }

    public SIG_XMSS[] getSIG_HT() {
        return this.sig_ht;
    }
}
