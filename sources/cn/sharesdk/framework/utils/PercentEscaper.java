package cn.sharesdk.framework.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PercentEscaper extends UnicodeEscaper {

    /* renamed from: a */
    private static final char[] f2965a = {'+'};

    /* renamed from: b */
    private static final char[] f2966b = "0123456789ABCDEF".toCharArray();

    /* renamed from: c */
    private final boolean f2967c;

    /* renamed from: d */
    private final boolean[] f2968d;

    public PercentEscaper(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        if (z && str.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        this.f2967c = z;
        this.f2968d = m21693a(str);
    }

    /* renamed from: a */
    private static boolean[] m21693a(String str) {
        char[] charArray = str.toCharArray();
        int i = 122;
        for (char c : charArray) {
            i = Math.max((int) c, i);
        }
        boolean[] zArr = new boolean[i + 1];
        for (int i2 = 48; i2 <= 57; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            zArr[i3] = true;
        }
        for (int i4 = 97; i4 <= 122; i4++) {
            zArr[i4] = true;
        }
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        return zArr;
    }

    @Override // cn.sharesdk.framework.utils.UnicodeEscaper
    /* renamed from: a */
    protected int mo21673a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            boolean[] zArr = this.f2968d;
            if (charAt >= zArr.length || !zArr[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override // cn.sharesdk.framework.utils.UnicodeEscaper, cn.sharesdk.framework.utils.Escaper
    public String escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            boolean[] zArr = this.f2968d;
            if (charAt >= zArr.length || !zArr[charAt]) {
                return m21672a(str, i);
            }
        }
        return str;
    }

    @Override // cn.sharesdk.framework.utils.UnicodeEscaper
    /* renamed from: a */
    protected char[] mo21674a(int i) {
        boolean[] zArr = this.f2968d;
        if (i >= zArr.length || !zArr[i]) {
            if (i == 32 && this.f2967c) {
                return f2965a;
            }
            if (i <= 127) {
                char[] cArr = f2966b;
                return new char[]{'%', cArr[i >>> 4], cArr[i & 15]};
            } else if (i <= 2047) {
                char[] cArr2 = f2966b;
                char[] cArr3 = {'%', cArr2[(r14 >>> 4) | 12], cArr2[r14 & 15], '%', cArr2[(r14 & 3) | 8], cArr2[i & 15]};
                int i2 = i >>> 4;
                int i3 = i2 >>> 2;
                return cArr3;
            } else if (i <= 65535) {
                char[] cArr4 = f2966b;
                char[] cArr5 = {'%', 'E', cArr4[r14 >>> 2], '%', cArr4[(r14 & 3) | 8], cArr4[r14 & 15], '%', cArr4[(r14 & 3) | 8], cArr4[i & 15]};
                int i4 = i >>> 4;
                int i5 = i4 >>> 2;
                int i6 = i5 >>> 4;
                return cArr5;
            } else if (i <= 1114111) {
                char[] cArr6 = f2966b;
                char[] cArr7 = {'%', 'F', cArr6[(r14 >>> 2) & 7], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[i & 15]};
                int i7 = i >>> 4;
                int i8 = i7 >>> 2;
                int i9 = i8 >>> 4;
                int i10 = i9 >>> 2;
                int i11 = i10 >>> 4;
                return cArr7;
            } else {
                throw new IllegalArgumentException("Invalid unicode character value " + i);
            }
        }
        return null;
    }
}
