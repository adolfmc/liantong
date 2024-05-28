package org.simalliance.openmobileapi.internal;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BerTlvParser extends TlvParser {
    @Override // org.simalliance.openmobileapi.internal.TlvParser
    public byte[] getLengthBytes(byte[] bArr, int i) {
        byte[] bArr2;
        if ((bArr[i] & 255) < 128) {
            bArr2 = new byte[1];
        } else if ((bArr[i] & 255) == 129) {
            bArr2 = new byte[2];
        } else if ((bArr[i] & 255) == 130) {
            bArr2 = new byte[3];
        } else if ((bArr[i] & 255) == 131) {
            bArr2 = new byte[4];
        } else if ((bArr[i] & 255) == 132) {
            bArr2 = new byte[5];
        } else {
            throw new IllegalArgumentException("Invalid length field at position " + i + ".");
        }
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return bArr2;
    }

    @Override // org.simalliance.openmobileapi.internal.TlvParser
    public int getLengthValue(byte[] bArr) {
        byte[] bArr2;
        int i = 1;
        if (bArr.length == 1) {
            bArr2 = new byte[1];
            i = 0;
        } else {
            bArr2 = new byte[bArr.length - 1];
        }
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return ByteArrayConverter.byteArrayToInt(bArr2);
    }

    @Override // org.simalliance.openmobileapi.internal.TlvParser
    public byte[] getTagBytes(byte[] bArr, int i) {
        byte[] bArr2;
        if ((bArr[i] & 31) != 31) {
            bArr2 = new byte[1];
        } else {
            int i2 = i + 1;
            if ((bArr[i2] < 0 || bArr[i2] > 30) && (bArr[i2] & 255) != 128) {
                if ((bArr[i2] & 128) == 0) {
                    bArr2 = new byte[2];
                } else if ((bArr[i2] & 128) == 128 && (bArr[i + 2] & 128) == 0) {
                    bArr2 = new byte[3];
                } else {
                    throw new IllegalArgumentException("Invalid \"tag\" field at position " + i + ".");
                }
            } else {
                throw new IllegalArgumentException("Invalid \"tag\" field at position " + i + ".");
            }
        }
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return bArr2;
    }
}
