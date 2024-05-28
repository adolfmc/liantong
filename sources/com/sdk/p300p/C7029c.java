package com.sdk.p300p;

import com.sdk.p290f.C6998d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.p.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7029c {

    /* renamed from: a */
    public static final char[] f18186a;

    /* renamed from: b */
    public static final byte[] f18187b;

    static {
        boolean z = C6998d.f18135a;
        f18186a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        f18187b = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 63, -1, 63, 52, 53, C0548c.f1784h, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
    }

    /* renamed from: a */
    public static String m8138a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(((bArr.length - 1) / 3) << 6);
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 % 3;
            int i4 = 16 - (i3 * 8);
            i |= (bArr[i2] << i4) & (255 << i4);
            if (i3 == 2 || i2 == bArr.length - 1) {
                char[] cArr = f18186a;
                stringBuffer.append(cArr[(16515072 & i) >>> 18]);
                stringBuffer.append(cArr[(258048 & i) >>> 12]);
                stringBuffer.append(cArr[(i & 4032) >>> 6]);
                stringBuffer.append(cArr[i & 63]);
                i = 0;
            }
        }
        if (bArr.length % 3 > 0) {
            stringBuffer.setCharAt(stringBuffer.length() - 1, '=');
        }
        if (bArr.length % 3 == 1) {
            stringBuffer.setCharAt(stringBuffer.length() - 2, '=');
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static byte[] m8139a(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 4 == 0) {
            if (str.length() == 0) {
                return new byte[0];
            }
            int i = str.charAt(length + (-1)) == '=' ? 1 : 0;
            if (str.charAt(length - 2) == '=') {
                i++;
            }
            int i2 = ((length / 4) * 3) - i;
            byte[] bArr = new byte[i2];
            for (int i3 = 0; i3 < length; i3 += 4) {
                int i4 = (i3 / 4) * 3;
                char charAt = str.charAt(i3);
                char charAt2 = str.charAt(i3 + 1);
                char charAt3 = str.charAt(i3 + 2);
                char charAt4 = str.charAt(i3 + 3);
                byte[] bArr2 = f18187b;
                int i5 = (bArr2[charAt] << 18) | (bArr2[charAt2] << 12) | (bArr2[charAt3] << 6) | bArr2[charAt4];
                bArr[i4] = (byte) ((16711680 & i5) >> 16);
                if (i3 < length - 4) {
                    bArr[i4 + 1] = (byte) ((65280 & i5) >> 8);
                    bArr[i4 + 2] = (byte) (i5 & 255);
                } else {
                    int i6 = i4 + 1;
                    if (i6 < i2) {
                        bArr[i6] = (byte) ((65280 & i5) >> 8);
                    }
                    int i7 = i4 + 2;
                    if (i7 < i2) {
                        bArr[i7] = (byte) (i5 & 255);
                    }
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Base64 string length must be 4*n");
    }
}
