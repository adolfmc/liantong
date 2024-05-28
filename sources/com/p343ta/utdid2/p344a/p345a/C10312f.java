package com.p343ta.utdid2.p344a.p345a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.a.a.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10312f {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ta.utdid2.a.a.f$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10314a {

        /* renamed from: d */
        public int[] f19729d;

        /* renamed from: x */
        public int f19730x;

        /* renamed from: y */
        public int f19731y;

        private C10314a() {
            this.f19729d = new int[256];
        }
    }

    /* renamed from: a */
    public static byte[] m6438a(byte[] bArr) {
        C10314a m6439a;
        if (bArr == null || (m6439a = m6439a("QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK")) == null) {
            return null;
        }
        return m6437a(bArr, m6439a);
    }

    /* renamed from: a */
    private static C10314a m6439a(String str) {
        if (str != null) {
            C10314a c10314a = new C10314a();
            for (int i = 0; i < 256; i++) {
                c10314a.f19729d[i] = i;
            }
            c10314a.f19730x = 0;
            c10314a.f19731y = 0;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < 256; i4++) {
                try {
                    i3 = ((str.charAt(i2) + c10314a.f19729d[i4]) + i3) % 256;
                    int i5 = c10314a.f19729d[i4];
                    c10314a.f19729d[i4] = c10314a.f19729d[i3];
                    c10314a.f19729d[i3] = i5;
                    i2 = (i2 + 1) % str.length();
                } catch (Exception unused) {
                    return null;
                }
            }
            return c10314a;
        }
        return null;
    }

    /* renamed from: a */
    private static byte[] m6437a(byte[] bArr, C10314a c10314a) {
        if (bArr == null || c10314a == null) {
            return null;
        }
        int i = c10314a.f19730x;
        int i2 = c10314a.f19731y;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) % 256;
            i2 = (c10314a.f19729d[i] + i2) % 256;
            int i4 = c10314a.f19729d[i];
            c10314a.f19729d[i] = c10314a.f19729d[i2];
            c10314a.f19729d[i2] = i4;
            bArr[i3] = (byte) (c10314a.f19729d[(c10314a.f19729d[i] + c10314a.f19729d[i2]) % 256] ^ bArr[i3]);
        }
        c10314a.f19730x = i;
        c10314a.f19731y = i2;
        return bArr;
    }
}
