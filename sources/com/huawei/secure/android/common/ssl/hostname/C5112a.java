package com.huawei.secure.android.common.ssl.hostname;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.security.auth.x500.X500Principal;

/* renamed from: com.huawei.secure.android.common.ssl.hostname.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5112a {

    /* renamed from: a */
    private final String f12078a;

    /* renamed from: b */
    private final int f12079b;

    /* renamed from: c */
    private int f12080c;

    /* renamed from: d */
    private int f12081d;

    /* renamed from: e */
    private int f12082e;

    /* renamed from: f */
    private int f12083f;

    /* renamed from: g */
    private char[] f12084g;

    public C5112a(X500Principal x500Principal) {
        this.f12078a = x500Principal.getName("RFC2253");
        this.f12079b = this.f12078a.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a7, code lost:
        return new java.lang.String(r1, r2, r8.f12083f - r2);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m13914a() {
        /*
            r8 = this;
            int r0 = r8.f12080c
            r8.f12081d = r0
            r8.f12082e = r0
        L6:
            int r0 = r8.f12080c
            int r1 = r8.f12079b
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f12084g
            int r2 = r8.f12081d
            int r3 = r8.f12082e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.f12084g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L60
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.f12082e
            int r3 = r2 + 1
            r8.f12082e = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.f12080c = r0
            goto L6
        L40:
            int r0 = r8.f12082e
            int r2 = r0 + 1
            r8.f12082e = r2
            char r2 = r8.m13911b()
            r1[r0] = r2
            int r0 = r8.f12080c
            int r0 = r0 + 1
            r8.f12080c = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f12084g
            int r2 = r8.f12081d
            int r3 = r8.f12082e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L60:
            int r2 = r8.f12082e
            r8.f12083f = r2
            int r0 = r0 + 1
            r8.f12080c = r0
            int r0 = r2 + 1
            r8.f12082e = r0
            r1[r2] = r6
        L6e:
            int r0 = r8.f12080c
            int r1 = r8.f12079b
            if (r0 >= r1) goto L87
            char[] r1 = r8.f12084g
            char r2 = r1[r0]
            if (r2 != r6) goto L87
            int r2 = r8.f12082e
            int r7 = r2 + 1
            r8.f12082e = r7
            r1[r2] = r6
            int r0 = r0 + 1
            r8.f12080c = r0
            goto L6e
        L87:
            int r0 = r8.f12080c
            int r1 = r8.f12079b
            if (r0 == r1) goto L9b
            char[] r1 = r8.f12084g
            char r2 = r1[r0]
            if (r2 == r3) goto L9b
            char r2 = r1[r0]
            if (r2 == r4) goto L9b
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L9b:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f12084g
            int r2 = r8.f12081d
            int r3 = r8.f12083f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.ssl.hostname.C5112a.m13914a():java.lang.String");
    }

    /* renamed from: b */
    private char m13911b() {
        this.f12080c++;
        int i = this.f12080c;
        if (i != this.f12079b) {
            char[] cArr = this.f12084g;
            char c = cArr[i];
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
                                        return m13909c();
                                }
                        }
                }
            }
            return cArr[i];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f12078a);
    }

    /* renamed from: c */
    private char m13909c() {
        int i;
        int i2;
        int m13913a = m13913a(this.f12080c);
        this.f12080c++;
        if (m13913a < 128) {
            return (char) m13913a;
        }
        if (m13913a < 192 || m13913a > 247) {
            return '?';
        }
        if (m13913a <= 223) {
            i2 = m13913a & 31;
            i = 1;
        } else if (m13913a <= 239) {
            i = 2;
            i2 = m13913a & 15;
        } else {
            i = 3;
            i2 = m13913a & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.f12080c++;
            int i4 = this.f12080c;
            if (i4 == this.f12079b || this.f12084g[i4] != '\\') {
                return '?';
            }
            this.f12080c = i4 + 1;
            int m13913a2 = m13913a(this.f12080c);
            this.f12080c++;
            if ((m13913a2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (m13913a2 & 63);
        }
        return (char) i2;
    }

    /* renamed from: d */
    private String m13908d() {
        int i = this.f12080c;
        if (i + 4 < this.f12079b) {
            this.f12081d = i;
            this.f12080c = i + 1;
            while (true) {
                int i2 = this.f12080c;
                if (i2 == this.f12079b) {
                    break;
                }
                char[] cArr = this.f12084g;
                if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                    break;
                } else if (cArr[i2] == ' ') {
                    this.f12082e = i2;
                    this.f12080c = i2 + 1;
                    while (true) {
                        int i3 = this.f12080c;
                        if (i3 >= this.f12079b || this.f12084g[i3] != ' ') {
                            break;
                        }
                        this.f12080c = i3 + 1;
                    }
                } else {
                    if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                        cArr[i2] = (char) (cArr[i2] + ' ');
                    }
                    this.f12080c++;
                }
            }
            this.f12082e = this.f12080c;
            int i4 = this.f12082e;
            int i5 = this.f12081d;
            int i6 = i4 - i5;
            if (i6 >= 5 && (i6 & 1) != 0) {
                byte[] bArr = new byte[i6 / 2];
                int i7 = i5 + 1;
                for (int i8 = 0; i8 < bArr.length; i8++) {
                    bArr[i8] = (byte) m13913a(i7);
                    i7 += 2;
                }
                return new String(this.f12084g, this.f12081d, i6);
            }
            throw new IllegalStateException("Unexpected end of DN: " + this.f12078a);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f12078a);
    }

    /* renamed from: e */
    private String m13907e() {
        while (true) {
            int i = this.f12080c;
            if (i >= this.f12079b || this.f12084g[i] != ' ') {
                break;
            }
            this.f12080c = i + 1;
        }
        int i2 = this.f12080c;
        if (i2 == this.f12079b) {
            return null;
        }
        this.f12081d = i2;
        this.f12080c = i2 + 1;
        while (true) {
            int i3 = this.f12080c;
            if (i3 >= this.f12079b) {
                break;
            }
            char[] cArr = this.f12084g;
            if (cArr[i3] == '=' || cArr[i3] == ' ') {
                break;
            }
            this.f12080c = i3 + 1;
        }
        int i4 = this.f12080c;
        if (i4 < this.f12079b) {
            this.f12082e = i4;
            if (this.f12084g[i4] == ' ') {
                while (true) {
                    int i5 = this.f12080c;
                    if (i5 >= this.f12079b) {
                        break;
                    }
                    char[] cArr2 = this.f12084g;
                    if (cArr2[i5] == '=' || cArr2[i5] != ' ') {
                        break;
                    }
                    this.f12080c = i5 + 1;
                }
                char[] cArr3 = this.f12084g;
                int i6 = this.f12080c;
                if (cArr3[i6] != '=' || i6 == this.f12079b) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.f12078a);
                }
            }
            this.f12080c++;
            while (true) {
                int i7 = this.f12080c;
                if (i7 >= this.f12079b || this.f12084g[i7] != ' ') {
                    break;
                }
                this.f12080c = i7 + 1;
            }
            int i8 = this.f12082e;
            int i9 = this.f12081d;
            if (i8 - i9 > 4) {
                char[] cArr4 = this.f12084g;
                if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                    char[] cArr5 = this.f12084g;
                    int i10 = this.f12081d + 1;
                    if (cArr5[i10] == 'I' || cArr5[i10] == 'i') {
                        char[] cArr6 = this.f12084g;
                        int i11 = this.f12081d + 2;
                        if (cArr6[i11] == 'D' || cArr6[i11] == 'd') {
                            this.f12081d += 4;
                        }
                    }
                }
            }
            char[] cArr7 = this.f12084g;
            int i12 = this.f12081d;
            return new String(cArr7, i12, this.f12082e - i12);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f12078a);
    }

    /* renamed from: f */
    private String m13906f() {
        this.f12080c++;
        this.f12081d = this.f12080c;
        this.f12082e = this.f12081d;
        while (true) {
            int i = this.f12080c;
            if (i != this.f12079b) {
                char[] cArr = this.f12084g;
                if (cArr[i] == '\"') {
                    this.f12080c = i + 1;
                    while (true) {
                        int i2 = this.f12080c;
                        if (i2 >= this.f12079b || this.f12084g[i2] != ' ') {
                            break;
                        }
                        this.f12080c = i2 + 1;
                    }
                    char[] cArr2 = this.f12084g;
                    int i3 = this.f12081d;
                    return new String(cArr2, i3, this.f12082e - i3);
                }
                if (cArr[i] == '\\') {
                    cArr[this.f12082e] = m13911b();
                } else {
                    cArr[this.f12082e] = cArr[i];
                }
                this.f12080c++;
                this.f12082e++;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.f12078a);
            }
        }
    }

    /* renamed from: b */
    public List<String> m13910b(String str) {
        this.f12080c = 0;
        this.f12081d = 0;
        this.f12082e = 0;
        this.f12083f = 0;
        this.f12084g = this.f12078a.toCharArray();
        List<String> emptyList = Collections.emptyList();
        String m13907e = m13907e();
        if (m13907e == null) {
            return emptyList;
        }
        do {
            int i = this.f12080c;
            if (i < this.f12079b) {
                String str2 = "";
                char c = this.f12084g[i];
                if (c == '\"') {
                    str2 = m13906f();
                } else if (c == '#') {
                    str2 = m13908d();
                } else if (c != '+' && c != ',' && c != ';') {
                    str2 = m13914a();
                }
                if (str.equalsIgnoreCase(m13907e)) {
                    if (emptyList.isEmpty()) {
                        emptyList = new ArrayList<>();
                    }
                    emptyList.add(str2);
                }
                int i2 = this.f12080c;
                if (i2 < this.f12079b) {
                    char[] cArr = this.f12084g;
                    if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                        throw new IllegalStateException("Malformed DN: " + this.f12078a);
                    }
                    this.f12080c++;
                    m13907e = m13907e();
                }
            }
            return emptyList;
        } while (m13907e != null);
        throw new IllegalStateException("Malformed DN: " + this.f12078a);
    }

    /* renamed from: a */
    private int m13913a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.f12079b) {
            char c = this.f12084g[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c < 'A' || c > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f12078a);
            } else {
                i2 = c - '7';
            }
            char c2 = this.f12084g[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f12078a);
            } else {
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f12078a);
    }

    /* renamed from: a */
    public String m13912a(String str) {
        this.f12080c = 0;
        this.f12081d = 0;
        this.f12082e = 0;
        this.f12083f = 0;
        this.f12084g = this.f12078a.toCharArray();
        String m13907e = m13907e();
        if (m13907e == null) {
            return null;
        }
        do {
            String str2 = "";
            int i = this.f12080c;
            if (i == this.f12079b) {
                return null;
            }
            char c = this.f12084g[i];
            if (c == '\"') {
                str2 = m13906f();
            } else if (c == '#') {
                str2 = m13908d();
            } else if (c != '+' && c != ',' && c != ';') {
                str2 = m13914a();
            }
            if (str.equalsIgnoreCase(m13907e)) {
                return str2;
            }
            int i2 = this.f12080c;
            if (i2 >= this.f12079b) {
                return null;
            }
            char[] cArr = this.f12084g;
            if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f12078a);
            }
            this.f12080c++;
            m13907e = m13907e();
        } while (m13907e != null);
        throw new IllegalStateException("Malformed DN: " + this.f12078a);
    }
}
