package com.gmrz.fido.offline;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.fido.offline.h */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Converter {
    /* renamed from: a */
    public static String m15724a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static byte[] m15725a(String str) {
        int i;
        int i2;
        String trim = str.toUpperCase().trim();
        if (trim.length() % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[trim.length() / 2];
        int i3 = 0;
        while (i3 < trim.length()) {
            char charAt = trim.charAt(i3);
            if (charAt < '0' || charAt > '9') {
                if (charAt >= 'A' && charAt <= 'F') {
                    i = charAt - '7';
                }
                return null;
            }
            i = charAt - '0';
            int i4 = i * 16;
            int i5 = i3 + 1;
            char charAt2 = trim.charAt(i5);
            if (charAt2 < '0' || charAt2 > '9') {
                if (charAt2 >= 'A' && charAt2 <= 'F') {
                    i2 = charAt2 - '7';
                }
                return null;
            }
            i2 = charAt2 - '0';
            bArr[i5 / 2] = (byte) (i4 + i2);
            i3 = i5 + 1;
        }
        return bArr;
    }
}
