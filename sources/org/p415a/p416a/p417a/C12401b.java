package org.p415a.p416a.p417a;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.a.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12401b extends OutputStream implements InterfaceC12400a {

    /* renamed from: A */
    private int f25064A;

    /* renamed from: B */
    private boolean f25065B;

    /* renamed from: C */
    private int f25066C;

    /* renamed from: D */
    private int f25067D;

    /* renamed from: E */
    private int f25068E;

    /* renamed from: F */
    private int f25069F;

    /* renamed from: G */
    private int f25070G;

    /* renamed from: H */
    private int f25071H;

    /* renamed from: I */
    private OutputStream f25072I;

    /* renamed from: J */
    private int[] f25073J;

    /* renamed from: b */
    int f25074b;

    /* renamed from: c */
    int f25075c;

    /* renamed from: d */
    int f25076d;

    /* renamed from: e */
    boolean f25077e;

    /* renamed from: f */
    int f25078f;

    /* renamed from: g */
    int f25079g;

    /* renamed from: h */
    int f25080h;

    /* renamed from: i */
    C12404c f25081i;

    /* renamed from: j */
    boolean f25082j;

    /* renamed from: k */
    private boolean f25083k;

    /* renamed from: l */
    private boolean[] f25084l;

    /* renamed from: m */
    private int f25085m;

    /* renamed from: n */
    private char[] f25086n;

    /* renamed from: o */
    private char[] f25087o;

    /* renamed from: p */
    private char[] f25088p;

    /* renamed from: q */
    private char[] f25089q;

    /* renamed from: r */
    private char[] f25090r;

    /* renamed from: s */
    private int[] f25091s;

    /* renamed from: t */
    private int[] f25092t;

    /* renamed from: u */
    private short[] f25093u;

    /* renamed from: v */
    private int[] f25094v;

    /* renamed from: w */
    private int f25095w;

    /* renamed from: x */
    private int[] f25096x;

    /* renamed from: y */
    private int f25097y;

    /* renamed from: z */
    private int f25098z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.a.a.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12403a {

        /* renamed from: a */
        int f25099a;

        /* renamed from: b */
        int f25100b;

        /* renamed from: c */
        int f25101c;

        private C12403a() {
        }
    }

    public C12401b(OutputStream outputStream) {
        this(outputStream, 9);
    }

    public C12401b(OutputStream outputStream, int i) {
        this.f25081i = new C12404c();
        this.f25084l = new boolean[256];
        this.f25086n = new char[256];
        this.f25087o = new char[256];
        this.f25088p = new char[18002];
        this.f25089q = new char[18002];
        this.f25096x = new int[258];
        this.f25067D = -1;
        this.f25068E = 0;
        this.f25082j = false;
        this.f25073J = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
        this.f25090r = null;
        this.f25091s = null;
        this.f25092t = null;
        this.f25094v = null;
        outputStream.write(66);
        outputStream.write(90);
        m1766a(outputStream);
        this.f25097y = 50;
        int i2 = i <= 9 ? i : 9;
        this.f25076d = i2 < 1 ? 1 : i2;
        m1745o();
        m1755e();
        m1754f();
    }

    /* renamed from: a */
    private char m1770a(char c, char c2, char c3) {
        if (c <= c2) {
            c2 = c;
            c = c2;
        }
        if (c > c3) {
            c = c3;
        }
        return c2 > c ? c2 : c;
    }

    /* renamed from: a */
    private void m1769a(int i) {
        m1768a(8, i);
    }

    /* renamed from: a */
    private void m1768a(int i, int i2) {
        while (true) {
            int i3 = this.f25080h;
            if (i3 < 8) {
                this.f25079g = (i2 << ((32 - i3) - i)) | this.f25079g;
                this.f25080h = i3 + i;
                return;
            }
            try {
                this.f25072I.write(this.f25079g >> 24);
                this.f25079g <<= 8;
                this.f25080h -= 8;
                this.f25078f++;
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0012, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0012, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m1767a(int r10, int r11, int r12) {
        /*
            r9 = this;
            int r0 = r11 - r10
            int r0 = r0 + 1
            r1 = 2
            if (r0 >= r1) goto L8
            return
        L8:
            r1 = 0
        L9:
            int[] r2 = r9.f25073J
            r2 = r2[r1]
            if (r2 >= r0) goto L12
            int r1 = r1 + 1
            goto L9
        L12:
            int r1 = r1 + (-1)
            if (r1 < 0) goto La4
            int[] r0 = r9.f25073J
            r0 = r0[r1]
            int r2 = r10 + r0
            r3 = r2
        L1d:
            if (r3 <= r11) goto L20
            goto L73
        L20:
            int[] r4 = r9.f25092t
            r4 = r4[r3]
            r5 = r3
        L25:
            int[] r6 = r9.f25092t
            int r7 = r5 - r0
            r6 = r6[r7]
            int r6 = r6 + r12
            int r8 = r4 + r12
            boolean r6 = r9.m1758c(r6, r8)
            if (r6 == 0) goto L42
            int[] r6 = r9.f25092t
            r8 = r6[r7]
            r6[r5] = r8
            int r5 = r2 + (-1)
            if (r7 > r5) goto L40
            r5 = r7
            goto L42
        L40:
            r5 = r7
            goto L25
        L42:
            int[] r6 = r9.f25092t
            r6[r5] = r4
            int r3 = r3 + 1
            if (r3 <= r11) goto L4b
            goto L73
        L4b:
            r4 = r6[r3]
            r5 = r3
        L4e:
            int[] r6 = r9.f25092t
            int r7 = r5 - r0
            r6 = r6[r7]
            int r6 = r6 + r12
            int r8 = r4 + r12
            boolean r6 = r9.m1758c(r6, r8)
            if (r6 == 0) goto L6b
            int[] r6 = r9.f25092t
            r8 = r6[r7]
            r6[r5] = r8
            int r5 = r2 + (-1)
            if (r7 > r5) goto L69
            r5 = r7
            goto L6b
        L69:
            r5 = r7
            goto L4e
        L6b:
            int[] r6 = r9.f25092t
            r6[r5] = r4
            int r3 = r3 + 1
            if (r3 <= r11) goto L74
        L73:
            goto L12
        L74:
            r4 = r6[r3]
            r5 = r3
        L77:
            int[] r6 = r9.f25092t
            int r7 = r5 - r0
            r6 = r6[r7]
            int r6 = r6 + r12
            int r8 = r4 + r12
            boolean r6 = r9.m1758c(r6, r8)
            if (r6 == 0) goto L94
            int[] r6 = r9.f25092t
            r8 = r6[r7]
            r6[r5] = r8
            int r5 = r2 + (-1)
            if (r7 > r5) goto L92
            r5 = r7
            goto L94
        L92:
            r5 = r7
            goto L77
        L94:
            int[] r6 = r9.f25092t
            r6[r5] = r4
            int r3 = r3 + 1
            int r4 = r9.f25098z
            int r5 = r9.f25064A
            if (r4 <= r5) goto L1d
            boolean r4 = r9.f25065B
            if (r4 == 0) goto L1d
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p416a.p417a.C12401b.m1767a(int, int, int):void");
    }

    /* renamed from: a */
    private void m1766a(OutputStream outputStream) {
        this.f25072I = outputStream;
        this.f25080h = 0;
        this.f25079g = 0;
        this.f25078f = 0;
    }

    /* renamed from: a */
    protected static void m1765a(char[] cArr, int[] iArr, int i, int i2) {
        int i3 = 260;
        int[] iArr2 = new int[260];
        int i4 = 516;
        int[] iArr3 = new int[516];
        int[] iArr4 = new int[516];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = 1;
            if (i6 >= i) {
                break;
            }
            int i8 = i6 + 1;
            if (iArr[i6] != 0) {
                i7 = iArr[i6];
            }
            iArr3[i8] = i7 << 8;
            i6 = i8;
        }
        while (true) {
            iArr2[i5] = i5;
            iArr3[i5] = i5;
            iArr4[i5] = -2;
            int i9 = i5;
            for (int i10 = 1; i10 <= i; i10++) {
                iArr4[i10] = -1;
                i9++;
                iArr2[i9] = i10;
                int i11 = iArr2[i9];
                int i12 = i9;
                while (true) {
                    int i13 = i12 >> 1;
                    if (iArr3[i11] < iArr3[iArr2[i13]]) {
                        iArr2[i12] = iArr2[i13];
                        i12 = i13;
                    }
                }
                iArr2[i12] = i11;
            }
            if (i9 >= i3) {
                m1763b();
            }
            int i14 = i;
            while (i9 > 1) {
                int i15 = iArr2[1];
                iArr2[1] = iArr2[i9];
                int i16 = i9 - 1;
                int i17 = iArr2[1];
                int i18 = 1;
                while (true) {
                    int i19 = i18 << 1;
                    if (i19 > i16) {
                        break;
                    }
                    if (i19 < i16) {
                        int i20 = i19 + 1;
                        if (iArr3[iArr2[i20]] < iArr3[iArr2[i19]]) {
                            i19 = i20;
                        }
                    }
                    if (iArr3[i17] < iArr3[iArr2[i19]]) {
                        break;
                    }
                    iArr2[i18] = iArr2[i19];
                    i18 = i19;
                }
                iArr2[i18] = i17;
                int i21 = iArr2[1];
                iArr2[1] = iArr2[i16];
                int i22 = i16 - 1;
                int i23 = iArr2[1];
                int i24 = 1;
                while (true) {
                    int i25 = i24 << 1;
                    if (i25 > i22) {
                        break;
                    }
                    if (i25 < i22) {
                        int i26 = i25 + 1;
                        if (iArr3[iArr2[i26]] < iArr3[iArr2[i25]]) {
                            i25 = i26;
                        }
                    }
                    if (iArr3[i23] < iArr3[iArr2[i25]]) {
                        break;
                    }
                    iArr2[i24] = iArr2[i25];
                    i24 = i25;
                }
                iArr2[i24] = i23;
                i14++;
                iArr4[i21] = i14;
                iArr4[i15] = i14;
                iArr3[i14] = ((((iArr3[i15] & 255) > (iArr3[i21] & 255) ? iArr3[i15] : iArr3[i21]) & 255) + 1) | ((iArr3[i15] & (-256)) + (iArr3[i21] & (-256)));
                iArr4[i14] = -1;
                i9 = i22 + 1;
                iArr2[i9] = i14;
                int i27 = iArr2[i9];
                int i28 = i9;
                while (true) {
                    int i29 = i28 >> 1;
                    if (iArr3[i27] < iArr3[iArr2[i29]]) {
                        iArr2[i28] = iArr2[i29];
                        i28 = i29;
                    }
                }
                iArr2[i28] = i27;
                i4 = 516;
            }
            int i30 = i4;
            if (i14 >= i30) {
                m1763b();
            }
            boolean z = false;
            for (int i31 = 1; i31 <= i; i31++) {
                int i32 = i31;
                int i33 = 0;
                while (iArr4[i32] >= 0) {
                    i32 = iArr4[i32];
                    i33++;
                }
                cArr[i31 - 1] = (char) i33;
                if (i33 > i2) {
                    z = true;
                }
            }
            if (!z) {
                return;
            }
            for (int i34 = 1; i34 < i; i34++) {
                iArr3[i34] = (((iArr3[i34] >> 8) / 2) + 1) << 8;
            }
            i4 = i30;
            i3 = 260;
            i5 = 0;
        }
    }

    /* renamed from: a */
    private void m1764a(int[] iArr, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            int i5 = i4;
            for (int i6 = 0; i6 < i3; i6++) {
                if (cArr[i6] == i) {
                    iArr[i6] = i5;
                    i5++;
                }
            }
            i4 = i5 << 1;
            i++;
        }
    }

    /* renamed from: b */
    private static void m1763b() {
        System.out.println("panic");
    }

    /* renamed from: b */
    private void m1762b(int i) {
        m1768a(8, (i >> 24) & 255);
        m1768a(8, (i >> 16) & 255);
        m1768a(8, (i >> 8) & 255);
        m1768a(8, i & 255);
    }

    /* renamed from: b */
    private void m1761b(int i, int i2) {
        m1768a(i, i2);
    }

    /* renamed from: b */
    private void m1760b(int i, int i2, int i3) {
        while (i3 > 0) {
            int[] iArr = this.f25092t;
            int i4 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i4;
            i++;
            i2++;
            i3--;
        }
    }

    /* renamed from: c */
    private void m1759c() {
        this.f25085m = 0;
        for (int i = 0; i < 256; i++) {
            if (this.f25084l[i]) {
                char[] cArr = this.f25086n;
                int i2 = this.f25085m;
                cArr[i2] = (char) i;
                this.f25087o[i] = (char) i2;
                this.f25085m = i2 + 1;
            }
        }
    }

    /* renamed from: c */
    private void m1757c(int i, int i2, int i3) {
        C12403a[] c12403aArr = new C12403a[1000];
        for (int i4 = 0; i4 < 1000; i4++) {
            c12403aArr[i4] = new C12403a();
        }
        c12403aArr[0].f25099a = i;
        c12403aArr[0].f25100b = i2;
        c12403aArr[0].f25101c = i3;
        int i5 = 1;
        while (i5 > 0) {
            if (i5 >= 1000) {
                m1763b();
            }
            i5--;
            int i6 = c12403aArr[i5].f25099a;
            int i7 = c12403aArr[i5].f25100b;
            int i8 = c12403aArr[i5].f25101c;
            if (i7 - i6 < 20 || i8 > 10) {
                m1767a(i6, i7, i8);
                if (this.f25098z > this.f25064A && this.f25065B) {
                    return;
                }
            } else {
                char[] cArr = this.f25090r;
                int[] iArr = this.f25092t;
                char m1770a = m1770a(cArr[iArr[i6] + i8 + 1], cArr[iArr[i7] + i8 + 1], cArr[iArr[(i6 + i7) >> 1] + i8 + 1]);
                int i9 = i6;
                int i10 = i9;
                int i11 = i7;
                int i12 = i11;
                while (true) {
                    if (i9 <= i11) {
                        char[] cArr2 = this.f25090r;
                        int[] iArr2 = this.f25092t;
                        int i13 = cArr2[(iArr2[i9] + i8) + 1] - m1770a;
                        if (i13 == 0) {
                            int i14 = iArr2[i9];
                            iArr2[i9] = iArr2[i10];
                            iArr2[i10] = i14;
                            i10++;
                        } else if (i13 > 0) {
                        }
                        i9++;
                    }
                    while (i9 <= i11) {
                        char[] cArr3 = this.f25090r;
                        int[] iArr3 = this.f25092t;
                        int i15 = cArr3[(iArr3[i11] + i8) + 1] - m1770a;
                        if (i15 == 0) {
                            int i16 = iArr3[i11];
                            iArr3[i11] = iArr3[i12];
                            iArr3[i12] = i16;
                            i12--;
                        } else if (i15 < 0) {
                            break;
                        }
                        i11--;
                    }
                    if (i9 > i11) {
                        break;
                    }
                    int[] iArr4 = this.f25092t;
                    int i17 = iArr4[i9];
                    iArr4[i9] = iArr4[i11];
                    iArr4[i11] = i17;
                    i9++;
                    i11--;
                }
                if (i12 < i10) {
                    c12403aArr[i5].f25099a = i6;
                    c12403aArr[i5].f25100b = i7;
                    c12403aArr[i5].f25101c = i8 + 1;
                    i5++;
                } else {
                    int i18 = i10 - i6;
                    int i19 = i9 - i10;
                    if (i18 >= i19) {
                        i18 = i19;
                    }
                    m1760b(i6, i9 - i18, i18);
                    int i20 = i7 - i12;
                    int i21 = i12 - i11;
                    if (i20 >= i21) {
                        i20 = i21;
                    }
                    m1760b(i9, (i7 - i20) + 1, i20);
                    int i22 = ((i9 + i6) - i10) - 1;
                    int i23 = (i7 - i21) + 1;
                    c12403aArr[i5].f25099a = i6;
                    c12403aArr[i5].f25100b = i22;
                    c12403aArr[i5].f25101c = i8;
                    int i24 = i5 + 1;
                    c12403aArr[i24].f25099a = i22 + 1;
                    c12403aArr[i24].f25100b = i23 - 1;
                    c12403aArr[i24].f25101c = i8 + 1;
                    int i25 = i24 + 1;
                    c12403aArr[i25].f25099a = i23;
                    c12403aArr[i25].f25100b = i7;
                    c12403aArr[i25].f25101c = i8;
                    i5 = i25 + 1;
                }
            }
        }
    }

    /* renamed from: c */
    private boolean m1758c(int i, int i2) {
        char[] cArr = this.f25090r;
        int i3 = i + 1;
        char c = cArr[i3];
        int i4 = i2 + 1;
        char c2 = cArr[i4];
        if (c != c2) {
            return c > c2;
        }
        int i5 = i3 + 1;
        char c3 = cArr[i5];
        int i6 = i4 + 1;
        char c4 = cArr[i6];
        if (c3 != c4) {
            return c3 > c4;
        }
        int i7 = i5 + 1;
        char c5 = cArr[i7];
        int i8 = i6 + 1;
        char c6 = cArr[i8];
        if (c5 != c6) {
            return c5 > c6;
        }
        int i9 = i7 + 1;
        char c7 = cArr[i9];
        int i10 = i8 + 1;
        char c8 = cArr[i10];
        if (c7 != c8) {
            return c7 > c8;
        }
        int i11 = i9 + 1;
        char c9 = cArr[i11];
        int i12 = i10 + 1;
        char c10 = cArr[i12];
        if (c9 != c10) {
            return c9 > c10;
        }
        int i13 = i11 + 1;
        char c11 = cArr[i13];
        int i14 = i12 + 1;
        char c12 = cArr[i14];
        if (c11 != c12) {
            return c11 > c12;
        }
        int i15 = this.f25074b + 1;
        do {
            char[] cArr2 = this.f25090r;
            int i16 = i13 + 1;
            char c13 = cArr2[i16];
            int i17 = i14 + 1;
            char c14 = cArr2[i17];
            if (c13 != c14) {
                return c13 > c14;
            }
            int[] iArr = this.f25091s;
            int i18 = iArr[i13];
            int i19 = iArr[i14];
            if (i18 != i19) {
                return i18 > i19;
            }
            int i20 = i16 + 1;
            char c15 = cArr2[i20];
            int i21 = i17 + 1;
            char c16 = cArr2[i21];
            if (c15 != c16) {
                return c15 > c16;
            }
            int i22 = iArr[i16];
            int i23 = iArr[i17];
            if (i22 != i23) {
                return i22 > i23;
            }
            int i24 = i20 + 1;
            char c17 = cArr2[i24];
            int i25 = i21 + 1;
            char c18 = cArr2[i25];
            if (c17 != c18) {
                return c17 > c18;
            }
            int i26 = iArr[i20];
            int i27 = iArr[i21];
            if (i26 != i27) {
                return i26 > i27;
            }
            i13 = i24 + 1;
            char c19 = cArr2[i13];
            int i28 = i25 + 1;
            char c20 = cArr2[i28];
            if (c19 != c20) {
                return c19 > c20;
            }
            int i29 = iArr[i24];
            int i30 = iArr[i25];
            if (i29 != i30) {
                return i29 > i30;
            }
            int i31 = this.f25074b;
            if (i13 > i31) {
                i13 = (i13 - i31) - 1;
            }
            int i32 = this.f25074b;
            if (i28 > i32) {
                i28 = (i28 - i32) - 1;
            }
            i14 = i28;
            i15 -= 4;
            this.f25098z++;
        } while (i15 >= 0);
        return false;
    }

    /* renamed from: d */
    private void m1756d() {
        int i;
        if (this.f25074b >= this.f25071H) {
            m1753g();
            m1754f();
            m1756d();
            return;
        }
        this.f25084l[this.f25067D] = true;
        int i2 = 0;
        while (true) {
            i = this.f25068E;
            if (i2 >= i) {
                break;
            }
            this.f25081i.m1742a((char) this.f25067D);
            i2++;
        }
        switch (i) {
            case 1:
                this.f25074b++;
                this.f25090r[this.f25074b + 1] = (char) this.f25067D;
                return;
            case 2:
                this.f25074b++;
                char[] cArr = this.f25090r;
                int i3 = this.f25074b;
                int i4 = this.f25067D;
                cArr[i3 + 1] = (char) i4;
                this.f25074b = i3 + 1;
                cArr[this.f25074b + 1] = (char) i4;
                return;
            case 3:
                this.f25074b++;
                char[] cArr2 = this.f25090r;
                int i5 = this.f25074b;
                int i6 = this.f25067D;
                cArr2[i5 + 1] = (char) i6;
                this.f25074b = i5 + 1;
                int i7 = this.f25074b;
                cArr2[i7 + 1] = (char) i6;
                this.f25074b = i7 + 1;
                cArr2[this.f25074b + 1] = (char) i6;
                return;
            default:
                this.f25084l[i - 4] = true;
                this.f25074b++;
                char[] cArr3 = this.f25090r;
                int i8 = this.f25074b;
                int i9 = this.f25067D;
                cArr3[i8 + 1] = (char) i9;
                this.f25074b = i8 + 1;
                int i10 = this.f25074b;
                cArr3[i10 + 1] = (char) i9;
                this.f25074b = i10 + 1;
                int i11 = this.f25074b;
                cArr3[i11 + 1] = (char) i9;
                this.f25074b = i11 + 1;
                int i12 = this.f25074b;
                cArr3[i12 + 1] = (char) i9;
                this.f25074b = i12 + 1;
                cArr3[this.f25074b + 1] = (char) (i - 4);
                return;
        }
    }

    /* renamed from: e */
    private void m1755e() {
        this.f25078f = 0;
        this.f25066C = 0;
        m1769a(104);
        m1769a(this.f25076d + 48);
        this.f25070G = 0;
    }

    /* renamed from: f */
    private void m1754f() {
        this.f25081i.m1743a();
        this.f25074b = -1;
        for (int i = 0; i < 256; i++) {
            this.f25084l[i] = false;
        }
        this.f25071H = (this.f25076d * 100000) - 20;
    }

    /* renamed from: g */
    private void m1753g() {
        this.f25069F = this.f25081i.m1741b();
        int i = this.f25070G;
        this.f25070G = (i >>> 31) | (i << 1);
        this.f25070G ^= this.f25069F;
        m1746n();
        m1769a(49);
        m1769a(65);
        m1769a(89);
        m1769a(38);
        m1769a(83);
        m1769a(89);
        m1762b(this.f25069F);
        if (this.f25077e) {
            m1768a(1, 1);
            this.f25066C++;
        } else {
            m1768a(1, 0);
        }
        m1749k();
    }

    /* renamed from: h */
    private void m1752h() {
        m1769a(23);
        m1769a(114);
        m1769a(69);
        m1769a(56);
        m1769a(80);
        m1769a(144);
        m1762b(this.f25070G);
        m1751i();
    }

    /* renamed from: i */
    private void m1751i() {
        while (this.f25080h > 0) {
            try {
                this.f25072I.write(this.f25079g >> 24);
                this.f25079g <<= 8;
                this.f25080h -= 8;
                this.f25078f++;
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [int] */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    /* JADX WARN: Type inference failed for: r3v14, types: [int] */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* renamed from: j */
    private void m1750j() {
        int i;
        int i2 = 258;
        int i3 = 6;
        char[][] cArr = (char[][]) Array.newInstance(char.class, 6, 258);
        int i4 = this.f25085m + 2;
        short s = 0;
        for (int i5 = 0; i5 < 6; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                cArr[i5][i6] = 15;
            }
        }
        if (this.f25095w <= 0) {
            m1763b();
        }
        int i7 = this.f25095w;
        int i8 = i7 < 200 ? 2 : i7 < 600 ? 3 : i7 < 1200 ? 4 : i7 < 2400 ? 5 : 6;
        int i9 = this.f25095w;
        int i10 = 0;
        int i11 = i8;
        while (true) {
            i = 1;
            if (i11 <= 0) {
                break;
            }
            int i12 = i9 / i11;
            int i13 = 0;
            int i14 = i10 - 1;
            while (i13 < i12 && i14 < i4 - 1) {
                i14++;
                i13 += this.f25096x[i14];
            }
            if (i14 > i10 && i11 != i8 && i11 != 1 && (i8 - i11) % 2 == 1) {
                i13 -= this.f25096x[i14];
                i14--;
            }
            for (int i15 = 0; i15 < i4; i15++) {
                if (i15 < i10 || i15 > i14) {
                    cArr[i11 - 1][i15] = 15;
                } else {
                    cArr[i11 - 1][i15] = 0;
                }
            }
            i11--;
            i10 = i14 + 1;
            i9 -= i13;
            i2 = 258;
        }
        int[][] iArr = (int[][]) Array.newInstance(int.class, 6, i2);
        int[] iArr2 = new int[6];
        short[] sArr = new short[6];
        int i16 = 0;
        int i17 = 0;
        while (true) {
            int i18 = 20;
            if (i16 >= 4) {
                break;
            }
            for (int i19 = s; i19 < i8; i19++) {
                iArr2[i19] = s;
            }
            for (int i20 = s; i20 < i8; i20++) {
                for (int i21 = s; i21 < i4; i21++) {
                    iArr[i20][i21] = s;
                }
            }
            int i22 = s;
            i17 = i22;
            while (true) {
                int i23 = this.f25095w;
                if (i22 >= i23) {
                    break;
                }
                int i24 = (i22 + 50) - i;
                if (i24 >= i23) {
                    i24 = i23 - 1;
                }
                for (int i25 = s; i25 < i8; i25++) {
                    sArr[i25] = s;
                }
                if (i8 == i3) {
                    int i26 = i22;
                    short s2 = s;
                    short s3 = s2;
                    short s4 = s3;
                    short s5 = s4;
                    short s6 = s5;
                    short s7 = s6;
                    while (i26 <= i24) {
                        short s8 = this.f25093u[i26];
                        short s9 = (short) (s2 + cArr[s][s8]);
                        short s10 = (short) (s5 + cArr[3][s8]);
                        i26++;
                        s6 = (short) (s6 + cArr[4][s8]);
                        s4 = (short) (s4 + cArr[2][s8]);
                        s7 = (short) (s7 + cArr[5][s8]);
                        s5 = s10;
                        s2 = s9;
                        s = 0;
                        s3 = (short) (s3 + cArr[i][s8]);
                        i = 1;
                    }
                    sArr[s] = s2;
                    sArr[1] = s3;
                    sArr[2] = s4;
                    sArr[3] = s5;
                    sArr[4] = s6;
                    sArr[5] = s7;
                } else {
                    for (int i27 = i22; i27 <= i24; i27++) {
                        short s11 = this.f25093u[i27];
                        for (int i28 = 0; i28 < i8; i28++) {
                            sArr[i28] = (short) (sArr[i28] + cArr[i28][s11]);
                        }
                    }
                }
                int i29 = -1;
                short s12 = 999999999;
                for (int i30 = 0; i30 < i8; i30++) {
                    if (sArr[i30] < s12) {
                        s12 = sArr[i30];
                        i29 = i30;
                    }
                }
                iArr2[i29] = iArr2[i29] + 1;
                this.f25088p[i17] = (char) i29;
                i17++;
                while (i22 <= i24) {
                    int[] iArr3 = iArr[i29];
                    short s13 = this.f25093u[i22];
                    iArr3[s13] = iArr3[s13] + 1;
                    i22++;
                }
                i22 = i24 + 1;
                i3 = 6;
                s = 0;
                i18 = 20;
                i = 1;
            }
            for (int i31 = s; i31 < i8; i31++) {
                m1765a(cArr[i31], iArr[i31], i4, i18);
            }
            i16++;
        }
        if (i8 >= 8) {
            m1763b();
        }
        if (i17 >= 32768 || i17 > 18002) {
            m1763b();
        }
        char[] cArr2 = new char[6];
        for (int i32 = 0; i32 < i8; i32++) {
            cArr2[i32] = (char) i32;
        }
        for (int i33 = 0; i33 < i17; i33++) {
            char c = this.f25088p[i33];
            char c2 = cArr2[0];
            int i34 = 0;
            while (c != c2) {
                i34++;
                char c3 = cArr2[i34];
                cArr2[i34] = c2;
                c2 = c3;
            }
            cArr2[0] = c2;
            this.f25089q[i33] = (char) i34;
        }
        int[][] iArr4 = (int[][]) Array.newInstance(int.class, 6, 258);
        for (int i35 = 0; i35 < i8; i35++) {
            char c4 = ' ';
            char c5 = 0;
            for (int i36 = 0; i36 < i4; i36++) {
                if (cArr[i35][i36] > c5) {
                    c5 = cArr[i35][i36];
                }
                if (cArr[i35][i36] < c4) {
                    c4 = cArr[i35][i36];
                }
            }
            if (c5 > 20) {
                m1763b();
            }
            if (c4 < 1) {
                m1763b();
            }
            m1764a(iArr4[i35], cArr[i35], c4, c5, i4);
        }
        boolean[] zArr = new boolean[16];
        for (int i37 = 0; i37 < 16; i37++) {
            zArr[i37] = false;
            for (int i38 = 0; i38 < 16; i38++) {
                if (this.f25084l[(i37 * 16) + i38]) {
                    zArr[i37] = true;
                }
            }
        }
        for (int i39 = 0; i39 < 16; i39++) {
            if (zArr[i39]) {
                m1768a(1, 1);
            } else {
                m1768a(1, 0);
            }
        }
        for (int i40 = 0; i40 < 16; i40++) {
            if (zArr[i40]) {
                for (int i41 = 0; i41 < 16; i41++) {
                    if (this.f25084l[(i40 * 16) + i41]) {
                        m1768a(1, 1);
                    } else {
                        m1768a(1, 0);
                    }
                }
            }
        }
        m1768a(3, i8);
        m1768a(15, i17);
        for (int i42 = 0; i42 < i17; i42++) {
            for (int i43 = 0; i43 < this.f25089q[i42]; i43++) {
                m1768a(1, 1);
            }
            m1768a(1, 0);
        }
        int i44 = 0;
        int i45 = 0;
        while (i45 < i8) {
            char c6 = cArr[i45][i44];
            m1768a(5, c6);
            char c7 = c6;
            int i46 = 0;
            while (i46 < i4) {
                while (c7 < cArr[i45][i46]) {
                    m1768a(2, 2);
                    c7++;
                }
                char c8 = c7;
                while (c8 > cArr[i45][i46]) {
                    m1768a(2, 3);
                    c8--;
                }
                m1768a(1, 0);
                i46++;
                c7 = c8;
            }
            i45++;
            i44 = 0;
        }
        int i47 = i44;
        int i48 = i47;
        while (true) {
            int i49 = this.f25095w;
            if (i47 >= i49) {
                break;
            }
            int i50 = (i47 + 50) - 1;
            if (i50 >= i49) {
                i50 = i49 - 1;
            }
            while (i47 <= i50) {
                char[] cArr3 = this.f25088p;
                char[] cArr4 = cArr[cArr3[i48]];
                short[] sArr2 = this.f25093u;
                m1768a(cArr4[sArr2[i47]], iArr4[cArr3[i48]][sArr2[i47]]);
                i47++;
            }
            i47 = i50 + 1;
            i48++;
        }
        if (i48 != i17) {
            m1763b();
        }
    }

    /* renamed from: k */
    private void m1749k() {
        m1761b(24, this.f25075c);
        m1744p();
        m1750j();
    }

    /* renamed from: l */
    private void m1748l() {
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        boolean[] zArr = new boolean[256];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = 2;
            i2 = 20;
            if (i6 >= 20) {
                break;
            }
            char[] cArr = this.f25090r;
            int i7 = this.f25074b;
            cArr[i7 + i6 + 2] = cArr[(i6 % (i7 + 1)) + 1];
            i6++;
        }
        int i8 = 0;
        while (true) {
            i3 = this.f25074b;
            if (i8 > i3 + 20) {
                break;
            }
            this.f25091s[i8] = 0;
            i8++;
        }
        char[] cArr2 = this.f25090r;
        cArr2[0] = cArr2[i3 + 1];
        if (i3 >= 4000) {
            for (int i9 = 0; i9 <= 255; i9++) {
                zArr[i9] = false;
            }
            for (int i10 = 0; i10 <= 65536; i10++) {
                this.f25094v[i10] = 0;
            }
            char c = this.f25090r[0];
            int i11 = 0;
            while (i11 <= this.f25074b) {
                i11++;
                char c2 = this.f25090r[i11];
                int[] iArr3 = this.f25094v;
                int i12 = (c << '\b') + c2;
                iArr3[i12] = iArr3[i12] + 1;
                c = c2;
            }
            for (int i13 = 1; i13 <= 65536; i13++) {
                int[] iArr4 = this.f25094v;
                iArr4[i13] = iArr4[i13] + iArr4[i13 - 1];
            }
            char c3 = this.f25090r[1];
            int i14 = 0;
            while (true) {
                i4 = this.f25074b;
                if (i14 >= i4) {
                    break;
                }
                char c4 = this.f25090r[i14 + 2];
                int i15 = (c3 << '\b') + c4;
                int[] iArr5 = this.f25094v;
                iArr5[i15] = iArr5[i15] - 1;
                this.f25092t[iArr5[i15]] = i14;
                i14++;
                c3 = c4;
            }
            char[] cArr3 = this.f25090r;
            int i16 = (cArr3[i4 + 1] << '\b') + cArr3[1];
            int[] iArr6 = this.f25094v;
            iArr6[i16] = iArr6[i16] - 1;
            this.f25092t[iArr6[i16]] = i4;
            for (int i17 = 0; i17 <= 255; i17++) {
                iArr[i17] = i17;
            }
            int i18 = 1;
            do {
                i18 = (i18 * 3) + 1;
            } while (i18 <= 256);
            do {
                i18 /= 3;
                for (int i19 = i18; i19 <= 255; i19++) {
                    int i20 = iArr[i19];
                    int i21 = i19;
                    while (true) {
                        int[] iArr7 = this.f25094v;
                        int i22 = i21 - i18;
                        if (iArr7[(iArr[i22] + 1) << 8] - iArr7[iArr[i22] << 8] > iArr7[(i20 + 1) << 8] - iArr7[i20 << 8]) {
                            iArr[i21] = iArr[i22];
                            if (i22 <= i18 - 1) {
                                i21 = i22;
                                break;
                            }
                            i21 = i22;
                        }
                    }
                    iArr[i21] = i20;
                }
            } while (i18 != 1);
            int i23 = 0;
            while (i23 <= 255) {
                int i24 = iArr[i23];
                for (int i25 = i5; i25 <= 255; i25++) {
                    int i26 = (i24 << 8) + i25;
                    int[] iArr8 = this.f25094v;
                    if ((iArr8[i26] & 2097152) != 2097152) {
                        int i27 = iArr8[i26] & (-2097153);
                        int i28 = ((-2097153) & iArr8[i26 + 1]) - 1;
                        if (i28 > i27) {
                            m1757c(i27, i28, i);
                            if (this.f25098z > this.f25064A && this.f25065B) {
                                return;
                            }
                        }
                        int[] iArr9 = this.f25094v;
                        iArr9[i26] = 2097152 | iArr9[i26];
                    }
                }
                zArr[i24] = true;
                if (i23 < 255) {
                    int[] iArr10 = this.f25094v;
                    int i29 = iArr10[i24 << 8] & (-2097153);
                    int i30 = (iArr10[(i24 + 1) << 8] & (-2097153)) - i29;
                    int i31 = 0;
                    while ((i30 >> i31) > 65534) {
                        i31++;
                    }
                    int i32 = 0;
                    while (i32 < i30) {
                        int i33 = this.f25092t[i29 + i32];
                        int i34 = i32 >> i31;
                        int[] iArr11 = this.f25091s;
                        iArr11[i33] = i34;
                        if (i33 < i2) {
                            iArr11[i33 + this.f25074b + 1] = i34;
                        }
                        i32++;
                        i2 = 20;
                    }
                    if (((i30 - 1) >> i31) > 65535) {
                        m1763b();
                    }
                }
                for (int i35 = 0; i35 <= 255; i35++) {
                    iArr2[i35] = this.f25094v[(i35 << 8) + i24] & (-2097153);
                }
                for (int i36 = this.f25094v[i24 << 8] & (-2097153); i36 < (this.f25094v[(i24 + 1) << 8] & (-2097153)); i36++) {
                    char[] cArr4 = this.f25090r;
                    int[] iArr12 = this.f25092t;
                    char c5 = cArr4[iArr12[i36]];
                    if (!zArr[c5]) {
                        iArr12[iArr2[c5]] = iArr12[i36] == 0 ? this.f25074b : iArr12[i36] - 1;
                        iArr2[c5] = iArr2[c5] + 1;
                    }
                }
                for (int i37 = 0; i37 <= 255; i37++) {
                    int[] iArr13 = this.f25094v;
                    int i38 = (i37 << 8) + i24;
                    iArr13[i38] = iArr13[i38] | 2097152;
                }
                i23++;
                i5 = 0;
                i = 2;
                i2 = 20;
            }
            return;
        }
        int i39 = 0;
        while (true) {
            int i40 = this.f25074b;
            if (i39 > i40) {
                this.f25065B = false;
                this.f25064A = 0;
                this.f25098z = 0;
                m1767a(0, i40, 0);
                return;
            }
            this.f25092t[i39] = i39;
            i39++;
        }
    }

    /* renamed from: m */
    private void m1747m() {
        for (int i = 0; i < 256; i++) {
            this.f25084l[i] = false;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 <= this.f25074b) {
            if (i3 == 0) {
                i3 = (char) f25063a[i4];
                i4++;
                if (i4 == 512) {
                    i4 = 0;
                }
            }
            i3--;
            char[] cArr = this.f25090r;
            i2++;
            cArr[i2] = (char) (cArr[i2] ^ (i3 == 1 ? (char) 1 : (char) 0));
            char[] cArr2 = this.f25090r;
            cArr2[i2] = (char) (cArr2[i2] & 255);
            this.f25084l[cArr2[i2]] = true;
        }
    }

    /* renamed from: n */
    private void m1746n() {
        this.f25064A = this.f25097y * this.f25074b;
        int i = 0;
        this.f25098z = 0;
        this.f25077e = false;
        this.f25065B = true;
        m1748l();
        if (this.f25098z > this.f25064A && this.f25065B) {
            m1747m();
            this.f25098z = 0;
            this.f25064A = 0;
            this.f25077e = true;
            this.f25065B = false;
            m1748l();
        }
        this.f25075c = -1;
        while (true) {
            if (i > this.f25074b) {
                break;
            } else if (this.f25092t[i] == 0) {
                this.f25075c = i;
                break;
            } else {
                i++;
            }
        }
        if (this.f25075c == -1) {
            m1763b();
        }
    }

    /* renamed from: o */
    private void m1745o() {
        int i = this.f25076d * 100000;
        this.f25090r = new char[i + 1 + 20];
        this.f25091s = new int[i + 20];
        this.f25092t = new int[i];
        this.f25094v = new int[65537];
        if (this.f25090r != null && this.f25091s != null && this.f25092t != null) {
            int[] iArr = this.f25094v;
        }
        this.f25093u = new short[i * 2];
    }

    /* renamed from: p */
    private void m1744p() {
        char[] cArr = new char[256];
        m1759c();
        int i = this.f25085m + 1;
        for (int i2 = 0; i2 <= i; i2++) {
            this.f25096x[i2] = 0;
        }
        for (int i3 = 0; i3 < this.f25085m; i3++) {
            cArr[i3] = (char) i3;
        }
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 <= this.f25074b; i6++) {
            char c = this.f25087o[this.f25090r[this.f25092t[i6]]];
            char c2 = cArr[0];
            int i7 = 0;
            while (c != c2) {
                i7++;
                char c3 = cArr[i7];
                cArr[i7] = c2;
                c2 = c3;
            }
            cArr[0] = c2;
            if (i7 == 0) {
                i4++;
            } else {
                if (i4 > 0) {
                    int i8 = i4 - 1;
                    while (true) {
                        switch (i8 % 2) {
                            case 0:
                                this.f25093u[i5] = 0;
                                i5++;
                                int[] iArr = this.f25096x;
                                iArr[0] = iArr[0] + 1;
                                break;
                            case 1:
                                this.f25093u[i5] = 1;
                                i5++;
                                int[] iArr2 = this.f25096x;
                                iArr2[1] = iArr2[1] + 1;
                                break;
                        }
                        if (i8 < 2) {
                            i4 = 0;
                        } else {
                            i8 = (i8 - 2) / 2;
                        }
                    }
                }
                int i9 = i7 + 1;
                this.f25093u[i5] = (short) i9;
                i5++;
                int[] iArr3 = this.f25096x;
                iArr3[i9] = iArr3[i9] + 1;
            }
        }
        if (i4 > 0) {
            int i10 = i4 - 1;
            while (true) {
                switch (i10 % 2) {
                    case 0:
                        this.f25093u[i5] = 0;
                        i5++;
                        int[] iArr4 = this.f25096x;
                        iArr4[0] = iArr4[0] + 1;
                        break;
                    case 1:
                        this.f25093u[i5] = 1;
                        i5++;
                        int[] iArr5 = this.f25096x;
                        iArr5[1] = iArr5[1] + 1;
                        break;
                }
                if (i10 >= 2) {
                    i10 = (i10 - 2) / 2;
                }
            }
        }
        this.f25093u[i5] = (short) i;
        int[] iArr6 = this.f25096x;
        iArr6[i] = iArr6[i] + 1;
        this.f25095w = i5 + 1;
    }

    /* renamed from: a */
    public void m1771a() {
        if (this.f25083k) {
            return;
        }
        if (this.f25068E > 0) {
            m1756d();
        }
        this.f25067D = -1;
        m1753g();
        m1752h();
        this.f25083k = true;
        flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f25082j) {
            return;
        }
        m1771a();
        this.f25082j = true;
        super.close();
        this.f25072I.close();
    }

    protected void finalize() {
        close();
        super.finalize();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        super.flush();
        this.f25072I.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        int i2;
        int i3 = (i + 256) % 256;
        int i4 = this.f25067D;
        if (i4 == -1) {
            this.f25067D = i3;
            i2 = this.f25068E + 1;
        } else if (i4 != i3) {
            m1756d();
            this.f25068E = 1;
            this.f25067D = i3;
            return;
        } else {
            this.f25068E++;
            if (this.f25068E <= 254) {
                return;
            }
            m1756d();
            this.f25067D = -1;
            i2 = 0;
        }
        this.f25068E = i2;
    }
}
