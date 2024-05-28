package com.alipay.sdk.packet;

import com.alipay.sdk.cons.C2003a;
import com.alipay.sdk.encrypt.C2013c;
import com.alipay.sdk.encrypt.C2015e;
import com.alipay.sdk.encrypt.C2016f;
import com.alipay.sdk.util.C2052n;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.packet.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2022c {

    /* renamed from: a */
    private boolean f3777a;

    /* renamed from: b */
    private String f3778b = C2052n.m20673a(24);

    public C2022c(boolean z) {
        this.f3777a = z;
    }

    /* renamed from: a */
    public C2023d m20811a(C2021b c2021b, boolean z, String str) {
        if (c2021b == null) {
            return null;
        }
        byte[] bytes = c2021b.m20815a().getBytes();
        byte[] bytes2 = c2021b.m20814b().getBytes();
        if (z) {
            try {
                bytes2 = C2013c.m20835a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        return new C2023d(z, this.f3777a ? m20806a(bytes, m20808a(this.f3778b, C2003a.f3671c), m20807a(this.f3778b, bytes2, str)) : m20806a(bytes, bytes2));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alipay.sdk.packet.C2021b m20810a(com.alipay.sdk.packet.C2023d r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            byte[] r2 = r6.m20803b()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r1.read(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r4.<init>(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            int r3 = m20809a(r4)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r1.read(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r4.<init>(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r1.read(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r3.<init>(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            int r2 = m20809a(r3)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r2 <= 0) goto L52
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r1.read(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            boolean r3 = r5.f3777a     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r3 == 0) goto L42
            java.lang.String r3 = r5.f3778b     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            byte[] r2 = m20805b(r3, r2, r7)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
        L42:
            boolean r6 = r6.m20804a()     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r6 == 0) goto L4c
            byte[] r2 = com.alipay.sdk.encrypt.C2013c.m20834b(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
        L4c:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r6.<init>(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            goto L53
        L52:
            r6 = r0
        L53:
            r1.close()     // Catch: java.lang.Exception -> L6b
            goto L6b
        L57:
            r6 = move-exception
            goto L62
        L59:
            r6 = move-exception
            r4 = r0
            goto L62
        L5c:
            r6 = move-exception
            r1 = r0
            goto L77
        L5f:
            r6 = move-exception
            r1 = r0
            r4 = r1
        L62:
            com.alipay.sdk.util.C2040c.m20715a(r6)     // Catch: java.lang.Throwable -> L76
            if (r1 == 0) goto L6a
            r1.close()     // Catch: java.lang.Exception -> L6a
        L6a:
            r6 = r0
        L6b:
            if (r4 != 0) goto L70
            if (r6 != 0) goto L70
            return r0
        L70:
            com.alipay.sdk.packet.b r7 = new com.alipay.sdk.packet.b
            r7.<init>(r4, r6)
            return r7
        L76:
            r6 = move-exception
        L77:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Exception -> L7c
        L7c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.C2022c.m20810a(com.alipay.sdk.packet.d, java.lang.String):com.alipay.sdk.packet.b");
    }

    /* renamed from: a */
    private static byte[] m20808a(String str, String str2) {
        return C2015e.m20832a(str, str2);
    }

    /* renamed from: a */
    private static byte[] m20807a(String str, byte[] bArr, String str2) {
        return C2016f.m20829a(str, bArr, str2);
    }

    /* renamed from: b */
    private static byte[] m20805b(String str, byte[] bArr, String str2) {
        return C2016f.m20827b(str, bArr, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x004e, code lost:
        if (r2 == null) goto L24;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] m20806a(byte[]... r7) {
        /*
            r0 = 0
            if (r7 == 0) goto L5e
            int r1 = r7.length
            if (r1 != 0) goto L7
            goto L5e
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L43
            r1.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L43
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            int r3 = r7.length     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            r4 = 0
        L13:
            if (r4 >= r3) goto L29
            r5 = r7[r4]     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            int r6 = r5.length     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            java.lang.String r6 = m20812a(r6)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            byte[] r6 = r6.getBytes()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            r2.write(r6)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            r2.write(r5)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            int r4 = r4 + 1
            goto L13
        L29:
            r2.flush()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L52
            r1.close()     // Catch: java.lang.Exception -> L33
        L33:
            r2.close()     // Catch: java.lang.Exception -> L51
            goto L51
        L37:
            r7 = move-exception
            goto L46
        L39:
            r7 = move-exception
            r2 = r0
            goto L53
        L3c:
            r7 = move-exception
            r2 = r0
            goto L46
        L3f:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L53
        L43:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L46:
            com.alipay.sdk.util.C2040c.m20715a(r7)     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L4e
            r1.close()     // Catch: java.lang.Exception -> L4e
        L4e:
            if (r2 == 0) goto L51
            goto L33
        L51:
            return r0
        L52:
            r7 = move-exception
        L53:
            if (r1 == 0) goto L58
            r1.close()     // Catch: java.lang.Exception -> L58
        L58:
            if (r2 == 0) goto L5d
            r2.close()     // Catch: java.lang.Exception -> L5d
        L5d:
            throw r7
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.C2022c.m20806a(byte[][]):byte[]");
    }

    /* renamed from: a */
    private static String m20812a(int i) {
        return String.format(Locale.getDefault(), "%05d", Integer.valueOf(i));
    }

    /* renamed from: a */
    private static int m20809a(String str) {
        return Integer.parseInt(str);
    }
}
