package org.simalliance.openmobileapi.internal;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class DerTlvCoder {
    public static final byte[] TAG_INTEGER = {2};
    public static final byte[] TAG_OCTET_STRING = {4};
    public static final byte[] TAG_SEQUENCE = {48};

    public static byte[] encodeInteger(int i) {
        byte[] intToByteArray = ByteArrayConverter.intToByteArray(i);
        while (intToByteArray[0] == 0) {
            int length = intToByteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(intToByteArray, 1, bArr, 0, length);
            intToByteArray = new byte[length];
            System.arraycopy(bArr, 0, intToByteArray, 0, length);
        }
        byte[] encodeLength = encodeLength(intToByteArray.length);
        byte[] bArr2 = TAG_INTEGER;
        byte[] bArr3 = new byte[bArr2.length + encodeLength.length + intToByteArray.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(encodeLength, 0, bArr3, TAG_INTEGER.length, encodeLength.length);
        System.arraycopy(intToByteArray, 0, bArr3, TAG_INTEGER.length + encodeLength.length, intToByteArray.length);
        return bArr3;
    }

    public static byte[] encodeLength(int i) {
        if (i >= 0) {
            byte[] intToByteArray = ByteArrayConverter.intToByteArray(i);
            return i <= 127 ? new byte[]{(byte) (intToByteArray[3] & Byte.MAX_VALUE)} : i <= 255 ? new byte[]{-127, intToByteArray[3]} : i <= 65535 ? new byte[]{-126, intToByteArray[2], intToByteArray[3]} : i <= 16777215 ? new byte[]{-125, intToByteArray[1], intToByteArray[2], intToByteArray[3]} : new byte[]{-124, intToByteArray[0], intToByteArray[1], intToByteArray[2], intToByteArray[3]};
        }
        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("lengthValue"));
    }

    public static byte[] encodeOctetString(byte[] bArr) {
        byte[] encodeLength = encodeLength(bArr.length);
        byte[] bArr2 = TAG_OCTET_STRING;
        byte[] bArr3 = new byte[bArr2.length + encodeLength.length + bArr.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(encodeLength, 0, bArr3, TAG_OCTET_STRING.length, encodeLength.length);
        System.arraycopy(bArr, 0, bArr3, TAG_OCTET_STRING.length + encodeLength.length, bArr.length);
        return bArr3;
    }

    public static byte[] encodeSequence(byte[] bArr) {
        byte[] encodeLength = encodeLength(bArr.length);
        byte[] bArr2 = TAG_SEQUENCE;
        byte[] bArr3 = new byte[bArr2.length + encodeLength.length + bArr.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(encodeLength, 0, bArr3, TAG_SEQUENCE.length, encodeLength.length);
        System.arraycopy(bArr, 0, bArr3, TAG_SEQUENCE.length + encodeLength.length, bArr.length);
        return bArr3;
    }
}
