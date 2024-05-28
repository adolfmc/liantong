package org.simalliance.openmobileapi.internal;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DerTlvParser extends TlvParser {
    @Override // org.simalliance.openmobileapi.internal.TlvParser
    public byte[] getLengthBytes(byte[] bArr, int i) {
        byte[] bArr2;
        if ((bArr[i] & 128) != 128) {
            bArr2 = new byte[1];
        } else if ((bArr[i] & 255) != 255) {
            bArr2 = new byte[(bArr[i] & 127) + 1];
        } else {
            throw new IllegalArgumentException("Invalid length field.");
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
            if ((bArr[i2] & Byte.MAX_VALUE) != 0) {
                int i3 = 2;
                while ((bArr[i2] & 128) == 128) {
                    i3++;
                    i2++;
                }
                bArr2 = new byte[i3];
            } else {
                throw new IllegalArgumentException("Invalid tag.");
            }
        }
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return bArr2;
    }
}
