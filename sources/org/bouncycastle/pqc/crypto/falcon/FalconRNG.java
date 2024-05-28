package org.bouncycastle.pqc.crypto.falcon;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class FalconRNG {

    /* renamed from: bd */
    byte[] f27143bd = new byte[512];
    long bdummy_u64 = 0;
    int ptr = 0;

    /* renamed from: sd */
    byte[] f27144sd = new byte[256];
    long sdummy_u64 = 0;
    int type = 0;
    FalconConversions convertor = new FalconConversions();

    private void QROUND(int i, int i2, int i3, int i4, int[] iArr) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = iArr[i4] ^ iArr[i];
        iArr[i4] = (iArr[i4] << 16) | (iArr[i4] >>> 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = iArr[i2] ^ iArr[i3];
        iArr[i2] = (iArr[i2] << 12) | (iArr[i2] >>> 20);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = iArr[i] ^ iArr[i4];
        iArr[i4] = (iArr[i4] << 8) | (iArr[i4] >>> 24);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = iArr[i2] ^ iArr[i3];
        iArr[i2] = (iArr[i2] << 7) | (iArr[i2] >>> 25);
    }

    void prng_get_bytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int length = this.f27143bd.length - this.ptr;
            if (length > i2) {
                length = i2;
            }
            System.arraycopy(this.f27143bd, 0, bArr, i, length);
            i += length;
            i2 -= length;
            this.ptr += length;
            if (this.ptr == this.f27143bd.length) {
                prng_refill();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long prng_get_u64() {
        int i = this.ptr;
        if (i >= this.f27143bd.length - 9) {
            prng_refill();
            i = 0;
        }
        this.ptr = i + 8;
        byte[] bArr = this.f27143bd;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i + 0] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte prng_get_u8() {
        byte[] bArr = this.f27143bd;
        int i = this.ptr;
        this.ptr = i + 1;
        byte b = bArr[i];
        if (this.ptr == bArr.length) {
            prng_refill();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void prng_init(SHAKE256 shake256) {
        byte[] bArr = new byte[56];
        shake256.inner_shake256_extract(bArr, 0, 56);
        for (int i = 0; i < 14; i++) {
            int i2 = i << 2;
            System.arraycopy(this.convertor.int_to_bytes(((bArr[i2 + 1] & 255) << 8) | (bArr[i2 + 0] & 255) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24)), 0, this.f27144sd, i2, 4);
        }
        System.arraycopy(this.convertor.long_to_bytes((this.convertor.bytes_to_int(this.f27144sd, 48) & 4294967295L) + ((4294967295L & this.convertor.bytes_to_int(this.f27144sd, 52)) << 32)), 0, this.f27144sd, 48, 8);
        prng_refill();
    }

    void prng_refill() {
        int[] iArr = {1634760805, 857760878, 2036477234, 1797285236};
        long bytes_to_long = this.convertor.bytes_to_long(this.f27144sd, 48);
        for (int i = 0; i < 8; i++) {
            int[] iArr2 = new int[16];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            System.arraycopy(this.convertor.bytes_to_int_array(this.f27144sd, 0, 12), 0, iArr2, 4, 12);
            int i2 = 14;
            int i3 = (int) bytes_to_long;
            iArr2[14] = iArr2[14] ^ i3;
            int i4 = (int) (bytes_to_long >>> 32);
            iArr2[15] = iArr2[15] ^ i4;
            int i5 = 0;
            while (i5 < 10) {
                QROUND(0, 4, 8, 12, iArr2);
                QROUND(1, 5, 9, 13, iArr2);
                QROUND(2, 6, 10, 14, iArr2);
                QROUND(3, 7, 11, 15, iArr2);
                QROUND(0, 5, 10, 15, iArr2);
                QROUND(1, 6, 11, 12, iArr2);
                QROUND(2, 7, 8, 13, iArr2);
                QROUND(3, 4, 9, 14, iArr2);
                i5++;
                i2 = i2;
                i4 = i4;
                i3 = i3;
            }
            int i6 = i4;
            int i7 = i3;
            int i8 = i2;
            for (int i9 = 0; i9 < 4; i9++) {
                iArr2[i9] = iArr2[i9] + iArr[i9];
            }
            for (int i10 = 4; i10 < i8; i10++) {
                iArr2[i10] = iArr2[i10] + this.convertor.bytes_to_int(this.f27144sd, (i10 * 4) - 16);
            }
            iArr2[i8] = iArr2[i8] + (this.convertor.bytes_to_int(this.f27144sd, 40) ^ i7);
            iArr2[15] = iArr2[15] + (this.convertor.bytes_to_int(this.f27144sd, 44) ^ i6);
            bytes_to_long++;
            for (int i11 = 0; i11 < 16; i11++) {
                byte[] bArr = this.f27143bd;
                int i12 = (i << 2) + (i11 << 5);
                bArr[i12 + 0] = (byte) iArr2[i11];
                bArr[i12 + 1] = (byte) (iArr2[i11] >>> 8);
                bArr[i12 + 2] = (byte) (iArr2[i11] >>> 16);
                bArr[i12 + 3] = (byte) (iArr2[i11] >>> 24);
            }
        }
        System.arraycopy(this.convertor.long_to_bytes(bytes_to_long), 0, this.f27144sd, 48, 8);
        this.ptr = 0;
    }
}
