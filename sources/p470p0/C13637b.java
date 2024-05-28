package p470p0;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.b */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13637b {

    /* renamed from: a */
    public static byte[] f27479a = new byte[255];

    /* renamed from: b */
    public static byte[] f27480b = new byte[64];

    /* renamed from: c */
    public static final char[] f27481c;

    /* renamed from: d */
    public static final int[] f27482d;

    static {
        int i;
        int i2;
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        f27481c = charArray;
        int[] iArr = new int[256];
        f27482d = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            f27482d[f27481c[i4]] = i4;
        }
        f27482d[61] = 0;
        for (int i5 = 0; i5 < 255; i5++) {
            f27479a[i5] = -1;
        }
        for (int i6 = 90; i6 >= 65; i6--) {
            f27479a[i6] = (byte) (i6 - 65);
        }
        int i7 = 122;
        while (true) {
            i = 26;
            if (i7 < 97) {
                break;
            }
            f27479a[i7] = (byte) ((i7 - 97) + 26);
            i7--;
        }
        int i8 = 57;
        while (true) {
            i2 = 52;
            if (i8 < 48) {
                break;
            }
            f27479a[i8] = (byte) ((i8 - 48) + 52);
            i8--;
        }
        byte[] bArr = f27479a;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i9 = 0; i9 <= 25; i9++) {
            f27480b[i9] = (byte) (i9 + 65);
        }
        int i10 = 0;
        while (i <= 51) {
            f27480b[i] = (byte) (i10 + 97);
            i++;
            i10++;
        }
        while (i2 <= 61) {
            f27480b[i2] = (byte) (i3 + 48);
            i2++;
            i3++;
        }
        byte[] bArr2 = f27480b;
        bArr2[62] = 43;
        bArr2[63] = 47;
    }

    /* renamed from: a */
    public static final byte[] m189a(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i3 = length - 1;
        int i4 = 0;
        while (i4 < i3 && f27482d[str.charAt(i4) & 255] < 0) {
            i4++;
        }
        while (i3 > 0 && f27482d[str.charAt(i3) & 255] < 0) {
            i3--;
        }
        int i5 = str.charAt(i3) == '=' ? str.charAt(i3 + (-1)) == '=' ? 2 : 1 : 0;
        int i6 = (i3 - i4) + 1;
        if (length > 76) {
            i = (str.charAt(76) == '\r' ? i6 / 78 : 0) << 1;
        } else {
            i = 0;
        }
        int i7 = (((i6 - i) * 6) >> 3) - i5;
        byte[] bArr = new byte[i7];
        int i8 = (i7 / 3) * 3;
        int i9 = 0;
        int i10 = i4;
        int i11 = 0;
        while (i11 < i8) {
            int[] iArr = f27482d;
            int i12 = i10 + 1;
            int i13 = i12 + 1;
            int i14 = (iArr[str.charAt(i10)] << 18) | (iArr[str.charAt(i12)] << 12);
            int i15 = i13 + 1;
            int i16 = i14 | (iArr[str.charAt(i13)] << 6);
            int i17 = i15 + 1;
            int i18 = i16 | iArr[str.charAt(i15)];
            int i19 = i11 + 1;
            bArr[i11] = (byte) (i18 >> 16);
            int i20 = i19 + 1;
            bArr[i19] = (byte) (i18 >> 8);
            int i21 = i20 + 1;
            bArr[i20] = (byte) i18;
            if (i <= 0 || (i9 = i9 + 1) != 19) {
                i11 = i21;
                i10 = i17;
            } else {
                i10 = i17 + 2;
                i9 = 0;
                i11 = i21;
            }
        }
        if (i11 < i7) {
            int i22 = 0;
            while (i10 <= i3 - i5) {
                i2 |= f27482d[str.charAt(i10)] << (18 - (i22 * 6));
                i22++;
                i10++;
            }
            int i23 = 16;
            while (i11 < i7) {
                bArr[i11] = (byte) (i2 >> i23);
                i23 -= 8;
                i11++;
            }
        }
        return bArr;
    }

    /* renamed from: a */
    public static final char[] m188a(byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        if (length == 0) {
            return new char[0];
        }
        int i = (length / 3) * 3;
        int i2 = length - 1;
        int i3 = (((i2 / 3) + 1) << 2) + 0;
        char[] cArr = new char[i3];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = i4 + 1;
            int i7 = i6 + 1;
            int i8 = ((bArr[i4] & 255) << 16) | ((bArr[i6] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | (bArr[i7] & 255);
            int i11 = i5 + 1;
            char[] cArr2 = f27481c;
            cArr[i5] = cArr2[(i10 >>> 18) & 63];
            int i12 = i11 + 1;
            cArr[i11] = cArr2[(i10 >>> 12) & 63];
            int i13 = i12 + 1;
            cArr[i12] = cArr2[(i10 >>> 6) & 63];
            i5 = i13 + 1;
            cArr[i13] = cArr2[i10 & 63];
            i4 = i9;
        }
        int i14 = length - i;
        if (i14 > 0) {
            int i15 = ((bArr[i] & 255) << 10) | (i14 == 2 ? (bArr[i2] & 255) << 2 : 0);
            char[] cArr3 = f27481c;
            cArr[i3 - 4] = cArr3[i15 >> 12];
            cArr[i3 - 3] = cArr3[(i15 >>> 6) & 63];
            cArr[i3 - 2] = i14 == 2 ? cArr3[i15 & 63] : '=';
            cArr[i3 - 1] = '=';
        }
        return cArr;
    }
}
