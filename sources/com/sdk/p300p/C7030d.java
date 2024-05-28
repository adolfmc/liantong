package com.sdk.p300p;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.p.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7030d {

    /* renamed from: a */
    public static char[] f18188a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: b */
    public static byte[] f18189b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, C0548c.f1784h, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0059, code lost:
        if (r5 != (-1)) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
        r1.write(((r4 & 15) << 4) | ((r5 & 60) >>> 2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
        r4 = r2 + 1;
        r2 = r8[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
        if (r2 != 61) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0072, code lost:
        return r1.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0073, code lost:
        r2 = com.sdk.p300p.C7030d.f18189b[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0077, code lost:
        if (r4 >= r0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0079, code lost:
        if (r2 == (-1)) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007c, code lost:
        r2 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007e, code lost:
        if (r2 != (-1)) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0081, code lost:
        r1.write(r2 | ((r5 & 3) << 6));
        r2 = r4;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m8137a(java.lang.String r8) {
        /*
            java.nio.charset.Charset r0 = java.nio.charset.Charset.defaultCharset()
            byte[] r8 = r8.getBytes(r0)
            int r0 = r8.length
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>(r0)
            r2 = 0
        Lf:
            if (r2 >= r0) goto L8b
        L11:
            byte[] r3 = com.sdk.p300p.C7030d.f18189b
            int r4 = r2 + 1
            r2 = r8[r2]
            r2 = r3[r2]
            r3 = -1
            if (r4 >= r0) goto L21
            if (r2 == r3) goto L1f
            goto L21
        L1f:
            r2 = r4
            goto L11
        L21:
            if (r2 != r3) goto L25
            goto L8b
        L25:
            byte[] r5 = com.sdk.p300p.C7030d.f18189b
            int r6 = r4 + 1
            r4 = r8[r4]
            r4 = r5[r4]
            if (r6 >= r0) goto L34
            if (r4 == r3) goto L32
            goto L34
        L32:
            r4 = r6
            goto L25
        L34:
            if (r4 != r3) goto L37
            goto L8b
        L37:
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            r1.write(r2)
        L41:
            int r2 = r6 + 1
            r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L4e
            byte[] r8 = r1.toByteArray()
            return r8
        L4e:
            byte[] r7 = com.sdk.p300p.C7030d.f18189b
            r5 = r7[r5]
            if (r2 >= r0) goto L59
            if (r5 == r3) goto L57
            goto L59
        L57:
            r6 = r2
            goto L41
        L59:
            if (r5 != r3) goto L5c
            goto L8b
        L5c:
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            r1.write(r4)
        L68:
            int r4 = r2 + 1
            r2 = r8[r2]
            if (r2 != r6) goto L73
            byte[] r8 = r1.toByteArray()
            return r8
        L73:
            byte[] r7 = com.sdk.p300p.C7030d.f18189b
            r2 = r7[r2]
            if (r4 >= r0) goto L7e
            if (r2 == r3) goto L7c
            goto L7e
        L7c:
            r2 = r4
            goto L68
        L7e:
            if (r2 != r3) goto L81
            goto L8b
        L81:
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            r1.write(r2)
            r2 = r4
            goto Lf
        L8b:
            byte[] r8 = r1.toByteArray()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p300p.C7030d.m8137a(java.lang.String):byte[]");
    }
}
