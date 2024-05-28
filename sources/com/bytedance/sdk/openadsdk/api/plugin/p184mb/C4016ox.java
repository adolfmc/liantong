package com.bytedance.sdk.openadsdk.api.plugin.p184mb;

import javax.security.auth.x500.X500Principal;

/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb.ox */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C4016ox {

    /* renamed from: b */
    private int f9594b;

    /* renamed from: h */
    private int f9595h;

    /* renamed from: hj */
    private int f9596hj;

    /* renamed from: ko */
    private char[] f9597ko;

    /* renamed from: mb */
    private final String f9598mb;

    /* renamed from: ox */
    private final int f9599ox;

    /* renamed from: u */
    private int f9600u;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4016ox(X500Principal x500Principal) {
        this.f9598mb = x500Principal.getName("RFC2253");
        this.f9599ox = this.f9598mb.length();
    }

    /* renamed from: mb */
    private String m16469mb() {
        while (true) {
            int i = this.f9594b;
            if (i >= this.f9599ox || this.f9597ko[i] != ' ') {
                break;
            }
            this.f9594b = i + 1;
        }
        int i2 = this.f9594b;
        if (i2 == this.f9599ox) {
            return null;
        }
        this.f9596hj = i2;
        this.f9594b = i2 + 1;
        while (true) {
            int i3 = this.f9594b;
            if (i3 >= this.f9599ox) {
                break;
            }
            char[] cArr = this.f9597ko;
            if (cArr[i3] == '=' || cArr[i3] == ' ') {
                break;
            }
            this.f9594b = i3 + 1;
        }
        int i4 = this.f9594b;
        if (i4 >= this.f9599ox) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f9598mb);
        }
        this.f9595h = i4;
        if (this.f9597ko[i4] == ' ') {
            while (true) {
                int i5 = this.f9594b;
                if (i5 >= this.f9599ox) {
                    break;
                }
                char[] cArr2 = this.f9597ko;
                if (cArr2[i5] == '=' || cArr2[i5] != ' ') {
                    break;
                }
                this.f9594b = i5 + 1;
            }
            char[] cArr3 = this.f9597ko;
            int i6 = this.f9594b;
            if (cArr3[i6] != '=' || i6 == this.f9599ox) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f9598mb);
            }
        }
        this.f9594b++;
        while (true) {
            int i7 = this.f9594b;
            if (i7 >= this.f9599ox || this.f9597ko[i7] != ' ') {
                break;
            }
            this.f9594b = i7 + 1;
        }
        int i8 = this.f9595h;
        int i9 = this.f9596hj;
        if (i8 - i9 > 4) {
            char[] cArr4 = this.f9597ko;
            if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                char[] cArr5 = this.f9597ko;
                int i10 = this.f9596hj;
                if (cArr5[i10 + 1] == 'I' || cArr5[i10 + 1] == 'i') {
                    char[] cArr6 = this.f9597ko;
                    int i11 = this.f9596hj;
                    if (cArr6[i11 + 2] == 'D' || cArr6[i11 + 2] == 'd') {
                        this.f9596hj += 4;
                    }
                }
            }
        }
        char[] cArr7 = this.f9597ko;
        int i12 = this.f9596hj;
        return new String(cArr7, i12, this.f9595h - i12);
    }

    /* renamed from: ox */
    private String m16465ox() {
        this.f9594b++;
        this.f9596hj = this.f9594b;
        this.f9595h = this.f9596hj;
        while (true) {
            int i = this.f9594b;
            if (i == this.f9599ox) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f9598mb);
            }
            char[] cArr = this.f9597ko;
            if (cArr[i] == '\"') {
                this.f9594b = i + 1;
                while (true) {
                    int i2 = this.f9594b;
                    if (i2 >= this.f9599ox || this.f9597ko[i2] != ' ') {
                        break;
                    }
                    this.f9594b = i2 + 1;
                }
                char[] cArr2 = this.f9597ko;
                int i3 = this.f9596hj;
                return new String(cArr2, i3, this.f9595h - i3);
            }
            if (cArr[i] == '\\') {
                cArr[this.f9595h] = m16471h();
            } else {
                cArr[this.f9595h] = cArr[i];
            }
            this.f9594b++;
            this.f9595h++;
        }
    }

    /* renamed from: b */
    private String m16472b() {
        int i = this.f9594b;
        if (i + 4 >= this.f9599ox) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f9598mb);
        }
        this.f9596hj = i;
        this.f9594b = i + 1;
        while (true) {
            int i2 = this.f9594b;
            if (i2 == this.f9599ox) {
                break;
            }
            char[] cArr = this.f9597ko;
            if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                break;
            } else if (cArr[i2] == ' ') {
                this.f9595h = i2;
                this.f9594b = i2 + 1;
                while (true) {
                    int i3 = this.f9594b;
                    if (i3 >= this.f9599ox || this.f9597ko[i3] != ' ') {
                        break;
                    }
                    this.f9594b = i3 + 1;
                }
            } else {
                if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                    cArr[i2] = (char) (cArr[i2] + ' ');
                }
                this.f9594b++;
            }
        }
        this.f9595h = this.f9594b;
        int i4 = this.f9595h;
        int i5 = this.f9596hj;
        int i6 = i4 - i5;
        if (i6 < 5 || (i6 & 1) == 0) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f9598mb);
        }
        byte[] bArr = new byte[i6 / 2];
        int i7 = i5 + 1;
        for (int i8 = 0; i8 < bArr.length; i8++) {
            bArr[i8] = (byte) m16468mb(i7);
            i7 += 2;
        }
        return new String(this.f9597ko, this.f9596hj, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a6, code lost:
        return new java.lang.String(r1, r2, r6.f9600u - r2);
     */
    /* renamed from: hj */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m16470hj() {
        /*
            r6 = this;
            int r0 = r6.f9594b
            r6.f9596hj = r0
            r6.f9595h = r0
        L6:
            int r0 = r6.f9594b
            int r1 = r6.f9599ox
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.f9597ko
            int r2 = r6.f9596hj
            int r3 = r6.f9595h
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r6.f9597ko
            char r2 = r1[r0]
            r3 = 59
            r4 = 32
            if (r2 == r4) goto L5b
            if (r2 == r3) goto L4e
            r3 = 92
            if (r2 == r3) goto L3b
            switch(r2) {
                case 43: goto L4e;
                case 44: goto L4e;
                default: goto L2c;
            }
        L2c:
            int r2 = r6.f9595h
            int r3 = r2 + 1
            r6.f9595h = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r6.f9594b = r0
            goto L6
        L3b:
            int r0 = r6.f9595h
            int r2 = r0 + 1
            r6.f9595h = r2
            char r2 = r6.m16471h()
            r1[r0] = r2
            int r0 = r6.f9594b
            int r0 = r0 + 1
            r6.f9594b = r0
            goto L6
        L4e:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.f9597ko
            int r2 = r6.f9596hj
            int r3 = r6.f9595h
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L5b:
            int r2 = r6.f9595h
            r6.f9600u = r2
            int r0 = r0 + 1
            r6.f9594b = r0
            int r0 = r2 + 1
            r6.f9595h = r0
            r1[r2] = r4
        L69:
            int r0 = r6.f9594b
            int r1 = r6.f9599ox
            if (r0 >= r1) goto L82
            char[] r1 = r6.f9597ko
            char r2 = r1[r0]
            if (r2 != r4) goto L82
            int r2 = r6.f9595h
            int r5 = r2 + 1
            r6.f9595h = r5
            r1[r2] = r4
            int r0 = r0 + 1
            r6.f9594b = r0
            goto L69
        L82:
            int r0 = r6.f9594b
            int r1 = r6.f9599ox
            if (r0 == r1) goto L9a
            char[] r1 = r6.f9597ko
            char r2 = r1[r0]
            r4 = 44
            if (r2 == r4) goto L9a
            char r2 = r1[r0]
            r4 = 43
            if (r2 == r4) goto L9a
            char r0 = r1[r0]
            if (r0 != r3) goto L6
        L9a:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.f9597ko
            int r2 = r6.f9596hj
            int r3 = r6.f9600u
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.openadsdk.api.plugin.p184mb.C4016ox.m16470hj():java.lang.String");
    }

    /* renamed from: h */
    private char m16471h() {
        this.f9594b++;
        int i = this.f9594b;
        if (i == this.f9599ox) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f9598mb);
        }
        char c = this.f9597ko[i];
        if (c != ' ' && c != '%' && c != '\\' && c != '_') {
            switch (c) {
                case '\"':
                case '#':
                    break;
                default:
                    switch (c) {
                        case '*':
                        case '+':
                        case ',':
                            break;
                        default:
                            switch (c) {
                                case ';':
                                case '<':
                                case '=':
                                case '>':
                                    break;
                                default:
                                    return m16464u();
                            }
                    }
            }
        }
        return this.f9597ko[this.f9594b];
    }

    /* renamed from: u */
    private char m16464u() {
        int i;
        int i2;
        int m16468mb = m16468mb(this.f9594b);
        this.f9594b++;
        if (m16468mb < 128) {
            return (char) m16468mb;
        }
        if (m16468mb < 192 || m16468mb > 247) {
            return '?';
        }
        if (m16468mb <= 223) {
            i2 = m16468mb & 31;
            i = 1;
        } else if (m16468mb <= 239) {
            i = 2;
            i2 = m16468mb & 15;
        } else {
            i = 3;
            i2 = m16468mb & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.f9594b++;
            int i4 = this.f9594b;
            if (i4 == this.f9599ox || this.f9597ko[i4] != '\\') {
                return '?';
            }
            this.f9594b = i4 + 1;
            int m16468mb2 = m16468mb(this.f9594b);
            this.f9594b++;
            if ((m16468mb2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (m16468mb2 & 63);
        }
        return (char) i2;
    }

    /* renamed from: mb */
    private int m16468mb(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 >= this.f9599ox) {
            throw new IllegalStateException("Malformed DN: " + this.f9598mb);
        }
        char c = this.f9597ko[i];
        if (c >= '0' && c <= '9') {
            i2 = c - '0';
        } else if (c >= 'a' && c <= 'f') {
            i2 = c - 'W';
        } else if (c < 'A' || c > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.f9598mb);
        } else {
            i2 = c - '7';
        }
        char c2 = this.f9597ko[i4];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 'W';
        } else if (c2 < 'A' || c2 > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.f9598mb);
        } else {
            i3 = c2 - '7';
        }
        return (i2 << 4) + i3;
    }

    /* renamed from: mb */
    public String m16466mb(String str) {
        this.f9594b = 0;
        this.f9596hj = 0;
        this.f9595h = 0;
        this.f9600u = 0;
        this.f9597ko = this.f9598mb.toCharArray();
        String m16469mb = m16469mb();
        if (m16469mb == null) {
            return null;
        }
        do {
            String str2 = "";
            int i = this.f9594b;
            if (i == this.f9599ox) {
                return null;
            }
            switch (this.f9597ko[i]) {
                case '\"':
                    str2 = m16465ox();
                    break;
                case '#':
                    str2 = m16472b();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = m16470hj();
                    break;
            }
            if (str.equalsIgnoreCase(m16469mb)) {
                return str2;
            }
            int i2 = this.f9594b;
            if (i2 >= this.f9599ox) {
                return null;
            }
            char[] cArr = this.f9597ko;
            if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f9598mb);
            }
            this.f9594b++;
            m16469mb = m16469mb();
        } while (m16469mb != null);
        throw new IllegalStateException("Malformed DN: " + this.f9598mb);
    }
}
