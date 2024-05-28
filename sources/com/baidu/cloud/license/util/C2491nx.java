package com.baidu.cloud.license.util;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Base64Utils.java */
/* renamed from: com.baidu.cloud.license.util.nx */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2491nx {

    /* renamed from: a */
    private static char[] f4357a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: b */
    private static byte[] f4358b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, C0548c.f1784h, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0087, code lost:
        if (r3 == (-1)) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0089, code lost:
        r1.append((char) (r3 | ((r6 & 3) << 6)));
        r3 = r5;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m20081a(java.lang.String r9) {
        /*
            r0 = 0
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Exception -> La0
            r1.<init>()     // Catch: java.lang.Exception -> La0
            java.lang.String r2 = "US-ASCII"
            byte[] r9 = r9.getBytes(r2)     // Catch: java.lang.Exception -> La0
            int r2 = r9.length     // Catch: java.lang.Exception -> La0
            r3 = r0
        Le:
            if (r3 >= r2) goto L95
        L10:
            byte[] r4 = com.baidu.cloud.license.util.C2491nx.f4358b     // Catch: java.lang.Exception -> La0
            int r5 = r3 + 1
            r3 = r9[r3]     // Catch: java.lang.Exception -> La0
            r3 = r4[r3]     // Catch: java.lang.Exception -> La0
            r4 = -1
            if (r5 >= r2) goto L20
            if (r3 == r4) goto L1e
            goto L20
        L1e:
            r3 = r5
            goto L10
        L20:
            if (r3 == r4) goto L95
        L22:
            byte[] r6 = com.baidu.cloud.license.util.C2491nx.f4358b     // Catch: java.lang.Exception -> La0
            int r7 = r5 + 1
            r5 = r9[r5]     // Catch: java.lang.Exception -> La0
            r5 = r6[r5]     // Catch: java.lang.Exception -> La0
            if (r7 >= r2) goto L31
            if (r5 == r4) goto L2f
            goto L31
        L2f:
            r5 = r7
            goto L22
        L31:
            if (r5 == r4) goto L95
            int r3 = r3 << 2
            r6 = r5 & 48
            int r6 = r6 >>> 4
            r3 = r3 | r6
            char r3 = (char) r3     // Catch: java.lang.Exception -> La0
            r1.append(r3)     // Catch: java.lang.Exception -> La0
        L3e:
            int r3 = r7 + 1
            r6 = r9[r7]     // Catch: java.lang.Exception -> La0
            r7 = 61
            if (r6 != r7) goto L51
            java.lang.String r9 = r1.toString()     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = "iso8859-1"
            byte[] r9 = r9.getBytes(r1)     // Catch: java.lang.Exception -> La0
            return r9
        L51:
            byte[] r8 = com.baidu.cloud.license.util.C2491nx.f4358b     // Catch: java.lang.Exception -> La0
            r6 = r8[r6]     // Catch: java.lang.Exception -> La0
            if (r3 >= r2) goto L5c
            if (r6 == r4) goto L5a
            goto L5c
        L5a:
            r7 = r3
            goto L3e
        L5c:
            if (r6 == r4) goto L95
            r5 = r5 & 15
            int r5 = r5 << 4
            r8 = r6 & 60
            int r8 = r8 >>> 2
            r5 = r5 | r8
            char r5 = (char) r5     // Catch: java.lang.Exception -> La0
            r1.append(r5)     // Catch: java.lang.Exception -> La0
        L6b:
            int r5 = r3 + 1
            r3 = r9[r3]     // Catch: java.lang.Exception -> La0
            if (r3 != r7) goto L7c
            java.lang.String r9 = r1.toString()     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = "iso8859-1"
            byte[] r9 = r9.getBytes(r1)     // Catch: java.lang.Exception -> La0
            return r9
        L7c:
            byte[] r8 = com.baidu.cloud.license.util.C2491nx.f4358b     // Catch: java.lang.Exception -> La0
            r3 = r8[r3]     // Catch: java.lang.Exception -> La0
            if (r5 >= r2) goto L87
            if (r3 == r4) goto L85
            goto L87
        L85:
            r3 = r5
            goto L6b
        L87:
            if (r3 == r4) goto L95
            r4 = r6 & 3
            int r4 = r4 << 6
            r3 = r3 | r4
            char r3 = (char) r3     // Catch: java.lang.Exception -> La0
            r1.append(r3)     // Catch: java.lang.Exception -> La0
            r3 = r5
            goto Le
        L95:
            java.lang.String r9 = r1.toString()     // Catch: java.lang.Exception -> La0
            java.lang.String r1 = "iso8859-1"
            byte[] r9 = r9.getBytes(r1)     // Catch: java.lang.Exception -> La0
            return r9
        La0:
            r9 = move-exception
            r9.printStackTrace()
            byte[] r9 = new byte[r0]
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.license.util.C2491nx.m20081a(java.lang.String):byte[]");
    }
}
