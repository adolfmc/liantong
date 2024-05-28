package com.bytedance.pangle.util.p183a;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3940b {

    /* renamed from: a */
    private static String f9366a = "";

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bc, code lost:
        if (r4 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00be, code lost:
        r0 = com.bytedance.pangle.util.C3946f.m16639a(r10.array());
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c7, code lost:
        r1 = "without v2 & v3 signature.";
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] m16655a(java.io.File r10) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            r2 = 0
            r3 = 1
            java.nio.ByteBuffer r10 = m16653b(r10)     // Catch: java.lang.Exception -> Ld3
            java.nio.ByteOrder r4 = r10.order()     // Catch: java.lang.Exception -> Ld3
            java.nio.ByteOrder r5 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch: java.lang.Exception -> Ld3
            if (r4 != r5) goto Lcb
            int r4 = r10.capacity()     // Catch: java.lang.Exception -> Ld3
            int r4 = r4 + (-24)
            java.nio.ByteBuffer r4 = m16654a(r10, r4)     // Catch: java.lang.Exception -> Ld3
            r5 = r2
        L1d:
            boolean r6 = r4.hasRemaining()     // Catch: java.lang.Exception -> Ld3
            if (r6 == 0) goto Lbb
            int r5 = r5 + r3
            int r6 = r4.remaining()     // Catch: java.lang.Exception -> Ld3
            r7 = 8
            if (r6 < r7) goto Lab
            long r6 = r4.getLong()     // Catch: java.lang.Exception -> Ld3
            r8 = 4
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 < 0) goto L8f
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 > 0) goto L8f
            int r6 = (int) r6     // Catch: java.lang.Exception -> Ld3
            int r7 = r4.position()     // Catch: java.lang.Exception -> Ld3
            int r7 = r7 + r6
            int r8 = r4.remaining()     // Catch: java.lang.Exception -> Ld3
            if (r6 > r8) goto L67
            int r6 = r4.getInt()     // Catch: java.lang.Exception -> Ld3
            r8 = -262969152(0xfffffffff05368c0, float:-2.6171189E29)
            if (r6 != r8) goto L58
            java.lang.String r4 = "V3"
            com.bytedance.pangle.util.p183a.C3940b.f9366a = r4     // Catch: java.lang.Exception -> Ld3
            r4 = r3
            goto Lbc
        L58:
            r8 = 1896449818(0x7109871a, float:6.810044E29)
            if (r6 != r8) goto L63
            java.lang.String r4 = "V2"
            com.bytedance.pangle.util.p183a.C3940b.f9366a = r4     // Catch: java.lang.Exception -> Ld3
            r4 = r3
            goto Lbc
        L63:
            r4.position(r7)     // Catch: java.lang.Exception -> Ld3
            goto L1d
        L67:
            java.lang.Exception r10 = new java.lang.Exception     // Catch: java.lang.Exception -> Ld3
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld3
            java.lang.String r8 = "APK Signing Block entry #"
            r7.<init>(r8)     // Catch: java.lang.Exception -> Ld3
            r7.append(r5)     // Catch: java.lang.Exception -> Ld3
            java.lang.String r5 = " size out of range: "
            r7.append(r5)     // Catch: java.lang.Exception -> Ld3
            r7.append(r6)     // Catch: java.lang.Exception -> Ld3
            java.lang.String r5 = ", available: "
            r7.append(r5)     // Catch: java.lang.Exception -> Ld3
            int r4 = r4.remaining()     // Catch: java.lang.Exception -> Ld3
            r7.append(r4)     // Catch: java.lang.Exception -> Ld3
            java.lang.String r4 = r7.toString()     // Catch: java.lang.Exception -> Ld3
            r10.<init>(r4)     // Catch: java.lang.Exception -> Ld3
            throw r10     // Catch: java.lang.Exception -> Ld3
        L8f:
            java.lang.Exception r10 = new java.lang.Exception     // Catch: java.lang.Exception -> Ld3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld3
            java.lang.String r8 = "APK Signing Block entry #"
            r4.<init>(r8)     // Catch: java.lang.Exception -> Ld3
            r4.append(r5)     // Catch: java.lang.Exception -> Ld3
            java.lang.String r5 = " size out of range: "
            r4.append(r5)     // Catch: java.lang.Exception -> Ld3
            r4.append(r6)     // Catch: java.lang.Exception -> Ld3
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Ld3
            r10.<init>(r4)     // Catch: java.lang.Exception -> Ld3
            throw r10     // Catch: java.lang.Exception -> Ld3
        Lab:
            java.lang.Exception r10 = new java.lang.Exception     // Catch: java.lang.Exception -> Ld3
            java.lang.String r4 = "Insufficient data to read size of APK Signing Block entry #"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Exception -> Ld3
            java.lang.String r4 = r4.concat(r5)     // Catch: java.lang.Exception -> Ld3
            r10.<init>(r4)     // Catch: java.lang.Exception -> Ld3
            throw r10     // Catch: java.lang.Exception -> Ld3
        Lbb:
            r4 = r2
        Lbc:
            if (r4 == 0) goto Lc7
            byte[] r10 = r10.array()     // Catch: java.lang.Exception -> Ld3
            java.lang.String r0 = com.bytedance.pangle.util.C3946f.m16639a(r10)     // Catch: java.lang.Exception -> Ld3
            goto Ld3
        Lc7:
            java.lang.String r1 = "without v2 & v3 signature."
            goto Ld3
        Lcb:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch: java.lang.Exception -> Ld3
            java.lang.String r4 = "ByteBuffer byte order must be little endian"
            r10.<init>(r4)     // Catch: java.lang.Exception -> Ld3
            throw r10     // Catch: java.lang.Exception -> Ld3
        Ld3:
            r10 = 3
            java.lang.String[] r10 = new java.lang.String[r10]
            r10[r2] = r0
            java.lang.String r0 = com.bytedance.pangle.util.p183a.C3940b.f9366a
            r10[r3] = r0
            r0 = 2
            r10[r0] = r1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.util.p183a.C3940b.m16655a(java.io.File):java.lang.String[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b A[Catch: all -> 0x018e, TryCatch #1 {all -> 0x018e, blocks: (B:4:0x0008, B:12:0x0024, B:14:0x003a, B:19:0x004b, B:21:0x005d, B:25:0x0075, B:27:0x00a8, B:29:0x00b5, B:33:0x00c9, B:35:0x00d3, B:37:0x00f6, B:40:0x0106, B:41:0x0121, B:42:0x0122, B:43:0x0131, B:44:0x0132, B:45:0x0141, B:46:0x0142, B:47:0x0149, B:48:0x014a, B:49:0x0159, B:50:0x015a, B:51:0x0161, B:52:0x0162, B:53:0x017d, B:54:0x017e, B:55:0x0185, B:56:0x0186, B:57:0x018d, B:7:0x0014, B:10:0x001b), top: B:67:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017e A[Catch: all -> 0x018e, TryCatch #1 {all -> 0x018e, blocks: (B:4:0x0008, B:12:0x0024, B:14:0x003a, B:19:0x004b, B:21:0x005d, B:25:0x0075, B:27:0x00a8, B:29:0x00b5, B:33:0x00c9, B:35:0x00d3, B:37:0x00f6, B:40:0x0106, B:41:0x0121, B:42:0x0122, B:43:0x0131, B:44:0x0132, B:45:0x0141, B:46:0x0142, B:47:0x0149, B:48:0x014a, B:49:0x0159, B:50:0x015a, B:51:0x0161, B:52:0x0162, B:53:0x017d, B:54:0x017e, B:55:0x0185, B:56:0x0186, B:57:0x018d, B:7:0x0014, B:10:0x001b), top: B:67:0x0008 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.nio.ByteBuffer m16653b(java.io.File r13) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.util.p183a.C3940b.m16653b(java.io.File):java.nio.ByteBuffer");
    }

    /* renamed from: a */
    private static ByteBuffer m16654a(ByteBuffer byteBuffer, int i) {
        if (i < 8) {
            throw new IllegalArgumentException("end < start: " + i + " < 8");
        }
        int capacity = byteBuffer.capacity();
        if (i > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i + " > " + capacity);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i);
            byteBuffer.position(8);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            return slice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(limit);
            byteBuffer.position(position);
        }
    }
}
