package org.simalliance.openmobileapi.internal;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OidParser {
    public static byte[] encodeOid(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split("\\.");
        if (split.length >= 3) {
            try {
                arrayList.add(Byte.valueOf(encondeFirstDigits(Integer.parseInt(split[0]), Integer.parseInt(split[1]))));
                for (int i = 2; i < split.length; i++) {
                    try {
                        for (byte b : encodeInteger(Integer.parseInt(split[i]))) {
                            arrayList.add(Byte.valueOf(b));
                        }
                    } catch (Exception unused) {
                        throw new IllegalArgumentException();
                    }
                }
                byte[] bArr = new byte[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    bArr[i2] = ((Byte) arrayList.get(i2)).byteValue();
                }
                return bArr;
            } catch (Exception unused2) {
                throw new IllegalArgumentException();
            }
        }
        throw new IllegalArgumentException();
    }

    public static byte encondeFirstDigits(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        return (byte) ((i * 40) + i2);
    }

    public static byte[] encodeInteger(int i) {
        if (i >= 0) {
            if (i == 0) {
                return new byte[]{0};
            }
            byte[] bArr = new byte[0];
            boolean z = true;
            while (i != 0) {
                int length = bArr.length;
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                bArr = new byte[bArr.length + 1];
                System.arraycopy(bArr2, 0, bArr, 1, length);
                byte b = (byte) (i & 127);
                if (z) {
                    z = false;
                } else {
                    b = (byte) (b | 128);
                }
                bArr[0] = b;
                i >>= 7;
            }
            return bArr;
        }
        throw new IllegalArgumentException();
    }
}
