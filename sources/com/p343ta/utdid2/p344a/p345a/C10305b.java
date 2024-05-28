package com.p343ta.utdid2.p344a.p345a;

import android.annotation.SuppressLint;
import java.io.UnsupportedEncodingException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.a.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10305b {

    /* renamed from: a */
    static final /* synthetic */ boolean f19714a = !C10305b.class.desiredAssertionStatus();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ta.utdid2.a.a.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC10306a {

        /* renamed from: a */
        public int f19715a;

        /* renamed from: a */
        public byte[] f19716a;

        AbstractC10306a() {
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        C10307b c10307b = new C10307b(i3, new byte[(i2 * 3) / 4]);
        if (!c10307b.m6448a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (c10307b.f19715a == c10307b.f19716a.length) {
            return c10307b.f19716a;
        }
        byte[] bArr2 = new byte[c10307b.f19715a];
        System.arraycopy(c10307b.f19716a, 0, bArr2, 0, c10307b.f19715a);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ta.utdid2.a.a.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C10307b extends AbstractC10306a {

        /* renamed from: a */
        private static final int[] f19717a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: b */
        private static final int[] f19718b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: c */
        private final int[] f19719c;
        private int state;
        private int value;

        public C10307b(int i, byte[] bArr) {
            this.f19716a = bArr;
            this.f19719c = (i & 8) == 0 ? f19717a : f19718b;
            this.state = 0;
            this.value = 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:54:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x00eb  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean m6448a(byte[] r12, int r13, int r14, boolean r15) {
            /*
                Method dump skipped, instructions count: 304
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p343ta.utdid2.p344a.p345a.C10305b.C10307b.m6448a(byte[], int, int, boolean):boolean");
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    @SuppressLint({"Assert"})
    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        C10308c c10308c = new C10308c(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!c10308c.f19724b) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (c10308c.f19725c && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (c10308c.f19726d ? 2 : 1);
        }
        c10308c.f19716a = new byte[i4];
        c10308c.m6447a(bArr, i, i2, true);
        if (f19714a || c10308c.f19715a == i4) {
            return c10308c.f19716a;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ta.utdid2.a.a.b$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C10308c extends AbstractC10306a {

        /* renamed from: a */
        static final /* synthetic */ boolean f19720a = !C10305b.class.desiredAssertionStatus();

        /* renamed from: b */
        private static final byte[] f19721b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 43, 47};

        /* renamed from: c */
        private static final byte[] f19722c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 45, 95};

        /* renamed from: b */
        int f19723b;

        /* renamed from: b */
        public final boolean f19724b;

        /* renamed from: c */
        public final boolean f19725c;
        private int count;

        /* renamed from: d */
        public final boolean f19726d;

        /* renamed from: d */
        private final byte[] f19727d;

        /* renamed from: e */
        private final byte[] f19728e;

        public C10308c(int i, byte[] bArr) {
            this.f19716a = bArr;
            this.f19724b = (i & 1) == 0;
            this.f19725c = (i & 2) == 0;
            this.f19726d = (i & 4) != 0;
            this.f19728e = (i & 8) == 0 ? f19721b : f19722c;
            this.f19727d = new byte[2];
            this.f19723b = 0;
            this.count = this.f19725c ? 19 : -1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* renamed from: a */
        public boolean m6447a(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            int i5;
            int i6;
            byte b;
            int i7;
            byte b2;
            int i8;
            byte b3;
            int i9;
            int i10;
            int i11;
            byte[] bArr2 = this.f19728e;
            byte[] bArr3 = this.f19716a;
            int i12 = this.count;
            int i13 = i2 + i;
            int i14 = 0;
            switch (this.f19723b) {
                case 0:
                default:
                    i3 = i;
                    i4 = -1;
                    break;
                case 1:
                    if (i + 2 <= i13) {
                        int i15 = i + 1;
                        i3 = i15 + 1;
                        i4 = ((this.f19727d[0] & 255) << 16) | ((bArr[i] & 255) << 8) | (bArr[i15] & 255);
                        this.f19723b = 0;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
                case 2:
                    int i16 = i + 1;
                    if (i16 <= i13) {
                        byte[] bArr4 = this.f19727d;
                        int i17 = ((bArr4[1] & 255) << 8) | ((bArr4[0] & 255) << 16) | (bArr[i] & 255);
                        this.f19723b = 0;
                        i3 = i16;
                        i4 = i17;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
            }
            if (i4 != -1) {
                bArr3[0] = bArr2[(i4 >> 18) & 63];
                bArr3[1] = bArr2[(i4 >> 12) & 63];
                bArr3[2] = bArr2[(i4 >> 6) & 63];
                bArr3[3] = bArr2[i4 & 63];
                i12--;
                if (i12 == 0) {
                    if (this.f19726d) {
                        i11 = 5;
                        bArr3[4] = 13;
                    } else {
                        i11 = 4;
                    }
                    i5 = i11 + 1;
                    bArr3[i11] = 10;
                    i12 = 19;
                } else {
                    i5 = 4;
                }
            } else {
                i5 = 0;
            }
            while (true) {
                int i18 = i3 + 3;
                if (i18 <= i13) {
                    int i19 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16) | (bArr[i3 + 2] & 255);
                    bArr3[i5] = bArr2[(i19 >> 18) & 63];
                    bArr3[i5 + 1] = bArr2[(i19 >> 12) & 63];
                    bArr3[i5 + 2] = bArr2[(i19 >> 6) & 63];
                    bArr3[i5 + 3] = bArr2[i19 & 63];
                    i5 += 4;
                    i12--;
                    if (i12 == 0) {
                        if (this.f19726d) {
                            i10 = i5 + 1;
                            bArr3[i5] = 13;
                        } else {
                            i10 = i5;
                        }
                        i5 = i10 + 1;
                        bArr3[i10] = 10;
                        i3 = i18;
                        i12 = 19;
                    } else {
                        i3 = i18;
                    }
                } else {
                    if (z) {
                        int i20 = this.f19723b;
                        if (i3 - i20 == i13 - 1) {
                            if (i20 > 0) {
                                b3 = this.f19727d[0];
                                i14 = 1;
                            } else {
                                byte b4 = bArr[i3];
                                i3++;
                                b3 = b4;
                            }
                            int i21 = (b3 & 255) << 4;
                            this.f19723b -= i14;
                            int i22 = i5 + 1;
                            bArr3[i5] = bArr2[(i21 >> 6) & 63];
                            i5 = i22 + 1;
                            bArr3[i22] = bArr2[i21 & 63];
                            if (this.f19724b) {
                                int i23 = i5 + 1;
                                bArr3[i5] = 61;
                                i5 = i23 + 1;
                                bArr3[i23] = 61;
                            }
                            if (this.f19725c) {
                                if (this.f19726d) {
                                    i9 = i5 + 1;
                                    bArr3[i5] = 13;
                                } else {
                                    i9 = i5;
                                }
                                i5 = i9 + 1;
                                bArr3[i9] = 10;
                            }
                        } else if (i3 - i20 == i13 - 2) {
                            if (i20 > 1) {
                                b = this.f19727d[0];
                                i14 = 1;
                            } else {
                                byte b5 = bArr[i3];
                                i3++;
                                b = b5;
                            }
                            int i24 = (b & 255) << 10;
                            if (this.f19723b > 0) {
                                i7 = i14 + 1;
                                b2 = this.f19727d[i14];
                            } else {
                                i7 = i14;
                                b2 = bArr[i3];
                                i3++;
                            }
                            int i25 = i24 | ((b2 & 255) << 2);
                            this.f19723b -= i7;
                            int i26 = i5 + 1;
                            bArr3[i5] = bArr2[(i25 >> 12) & 63];
                            int i27 = i26 + 1;
                            bArr3[i26] = bArr2[(i25 >> 6) & 63];
                            int i28 = i27 + 1;
                            bArr3[i27] = bArr2[i25 & 63];
                            if (this.f19724b) {
                                i8 = i28 + 1;
                                bArr3[i28] = 61;
                            } else {
                                i8 = i28;
                            }
                            if (this.f19725c) {
                                if (this.f19726d) {
                                    bArr3[i8] = 13;
                                    i8++;
                                }
                                bArr3[i8] = 10;
                                i8++;
                            }
                            i5 = i8;
                        } else if (this.f19725c && i5 > 0 && i12 != 19) {
                            if (this.f19726d) {
                                i6 = i5 + 1;
                                bArr3[i5] = 13;
                            } else {
                                i6 = i5;
                            }
                            i5 = i6 + 1;
                            bArr3[i6] = 10;
                        }
                        if (!f19720a && this.f19723b != 0) {
                            throw new AssertionError();
                        }
                        if (!f19720a && i3 != i13) {
                            throw new AssertionError();
                        }
                    } else if (i3 == i13 - 1) {
                        byte[] bArr5 = this.f19727d;
                        int i29 = this.f19723b;
                        this.f19723b = i29 + 1;
                        bArr5[i29] = bArr[i3];
                    } else if (i3 == i13 - 2) {
                        byte[] bArr6 = this.f19727d;
                        int i30 = this.f19723b;
                        this.f19723b = i30 + 1;
                        bArr6[i30] = bArr[i3];
                        int i31 = this.f19723b;
                        this.f19723b = i31 + 1;
                        bArr6[i31] = bArr[i3 + 1];
                    }
                    this.f19715a = i5;
                    this.count = i12;
                    return true;
                }
            }
        }
    }

    private C10305b() {
    }
}
