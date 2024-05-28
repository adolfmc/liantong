package p470p0;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.c */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13638c {

    /* renamed from: a */
    public static final char[] f27483a;

    /* renamed from: b */
    public static final byte[] f27484b;

    static {
        System.getProperty("line.separator");
        f27483a = new char[64];
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            f27483a[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            f27483a[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            f27483a[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = f27483a;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        f27484b = new byte[128];
        int i2 = 0;
        while (true) {
            byte[] bArr = f27484b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            f27484b[f27483a[i3]] = (byte) i3;
        }
    }

    /* renamed from: a */
    public static String m187a(String str) {
        int i;
        int i2;
        int i3;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        int i4 = ((length * 4) + 2) / 3;
        char[] cArr = new char[((length + 2) / 3) * 4];
        int i5 = length + 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i5) {
            int i8 = i6 + 1;
            int i9 = bytes[i6] & 255;
            if (i8 < i5) {
                i = bytes[i8] & 255;
                i8++;
            } else {
                i = 0;
            }
            if (i8 < i5) {
                i2 = i8 + 1;
                i3 = bytes[i8] & 255;
            } else {
                i2 = i8;
                i3 = 0;
            }
            int i10 = i9 >>> 2;
            int i11 = ((i9 & 3) << 4) | (i >>> 4);
            int i12 = ((i & 15) << 2) | (i3 >>> 6);
            int i13 = i3 & 63;
            int i14 = i7 + 1;
            char[] cArr2 = f27483a;
            cArr[i7] = cArr2[i10];
            int i15 = i14 + 1;
            cArr[i14] = cArr2[i11];
            char c = '=';
            cArr[i15] = i15 < i4 ? cArr2[i12] : '=';
            int i16 = i15 + 1;
            if (i16 < i4) {
                c = cArr2[i13];
            }
            cArr[i16] = c;
            i7 = i16 + 1;
            i6 = i2;
        }
        return new String(cArr);
    }
}
