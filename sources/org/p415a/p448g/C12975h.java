package org.p415a.p448g;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12975h {

    /* renamed from: a */
    private static String f26253a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.g.h$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C12977a extends ArrayList<String> implements InterfaceC12974g {
        private C12977a() {
        }

        @Override // org.p415a.p448g.InterfaceC12974g
        /* renamed from: a */
        public /* synthetic */ String mo383a(int i) {
            return (String) super.get(i);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        /* renamed from: a */
        public String set(int i, String str) {
            return (String) super.set(i, str);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        /* renamed from: a */
        public boolean add(String str) {
            return super.add(str);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        /* renamed from: b */
        public void add(int i, String str) {
            super.add(i, str);
        }
    }

    static {
        try {
            try {
                f26253a = (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.a.g.h.1
                    @Override // java.security.PrivilegedAction
                    /* renamed from: a */
                    public String run() {
                        return System.getProperty("line.separator");
                    }
                });
            } catch (Exception unused) {
                f26253a = "\n";
            }
        } catch (Exception unused2) {
            f26253a = String.format("%n", new Object[0]);
        }
    }

    /* renamed from: a */
    public static String m390a(byte[] bArr) {
        char c;
        int i;
        byte b;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            i4++;
            if ((bArr[i3] & 240) == 240) {
                i4++;
                i3 += 4;
            } else {
                i3 = (bArr[i3] & 224) == 224 ? i3 + 3 : (bArr[i3] & 192) == 192 ? i3 + 2 : i3 + 1;
            }
        }
        char[] cArr = new char[i4];
        int i5 = 0;
        while (i2 < bArr.length) {
            if ((bArr[i2] & 240) == 240) {
                int i6 = (((((bArr[i2] & 3) << 18) | ((bArr[i2 + 1] & 63) << 12)) | ((bArr[i2 + 2] & 63) << 6)) | (bArr[i2 + 3] & 63)) - 65536;
                c = (char) ((i6 & 1023) | 56320);
                cArr[i5] = (char) (55296 | (i6 >> 10));
                i2 += 4;
                i5++;
            } else if ((bArr[i2] & 224) == 224) {
                c = (char) (((bArr[i2] & 15) << 12) | ((bArr[i2 + 1] & 63) << 6) | (bArr[i2 + 2] & 63));
                i2 += 3;
            } else {
                if ((bArr[i2] & 208) == 208) {
                    i = (bArr[i2] & 31) << 6;
                    b = bArr[i2 + 1];
                } else if ((bArr[i2] & 192) == 192) {
                    i = (bArr[i2] & 31) << 6;
                    b = bArr[i2 + 1];
                } else {
                    c = (char) (bArr[i2] & 255);
                    i2++;
                }
                c = (char) (i | (b & 63));
                i2 += 2;
            }
            cArr[i5] = c;
            i5++;
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static InterfaceC12974g m392a() {
        return new C12977a();
    }

    /* renamed from: a */
    public static void m388a(char[] cArr, OutputStream outputStream) {
        int i;
        char c;
        int i2 = 0;
        while (i2 < cArr.length) {
            char c2 = cArr[i2];
            char c3 = c2;
            if (c2 >= 128) {
                if (c2 < 2048) {
                    i = (c2 >> 6) | 192;
                } else if (c2 < 55296 || c2 > 57343) {
                    outputStream.write((c2 >> '\f') | 224);
                    i = ((c2 >> 6) & 63) | 128;
                } else {
                    i2++;
                    if (i2 >= cArr.length) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    char c4 = cArr[i2];
                    if (c2 > 56319) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    int i3 = (((c2 & 1023) << 10) | (c4 & 1023)) + 65536;
                    outputStream.write((i3 >> 18) | 240);
                    outputStream.write(((i3 >> 12) & 63) | 128);
                    outputStream.write(((i3 >> 6) & 63) | 128);
                    c = i3;
                    c3 = (c & 63) | 128;
                }
                outputStream.write(i);
                c = c2;
                c3 = (c & 63) | 128;
            }
            outputStream.write(c3);
            i2++;
        }
    }

    /* renamed from: a */
    public static byte[] m391a(String str) {
        return m389a(str.toCharArray());
    }

    /* renamed from: a */
    public static byte[] m389a(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            m388a(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    /* renamed from: b */
    public static String m387b(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('A' <= c && 'Z' >= c) {
                charArray[i] = (char) ((c - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    /* renamed from: b */
    public static String m386b(byte[] bArr) {
        return new String(m385c(bArr));
    }

    /* renamed from: c */
    public static char[] m385c(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }
}
