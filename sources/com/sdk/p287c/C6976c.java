package com.sdk.p287c;

import com.sdk.p290f.C6998d;

/* renamed from: com.sdk.c.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6976c {

    /* renamed from: a */
    public static final Boolean f18085a = Boolean.valueOf(C6998d.f18135a);

    /* JADX WARN: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m8182a(java.net.HttpURLConnection r17, com.sdk.p287c.InterfaceC6975b r18, java.lang.String r19) {
        /*
            r16 = this;
            r0 = r19
            r1 = 0
            int r2 = r17.getContentLength()     // Catch: java.lang.Exception -> L8a
            long r9 = (long) r2     // Catch: java.lang.Exception -> L8a
            r8 = 1
            r3 = r18
            com.sdk.a.c r3 = (com.sdk.p285a.C6958c) r3     // Catch: java.lang.Exception -> L8a
            r11 = 0
            r4 = r9
            r6 = r11
            boolean r2 = r3.m8213a(r4, r6, r8)     // Catch: java.lang.Exception -> L8a
            if (r2 != 0) goto L18
            return r1
        L18:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8a
            r2.<init>()     // Catch: java.lang.Exception -> L8a
            java.io.InputStream r3 = r17.getInputStream()     // Catch: java.lang.Exception -> L88
            java.io.BufferedReader r13 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L88
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L88
            r4.<init>(r3, r0)     // Catch: java.lang.Exception -> L88
            r13.<init>(r4)     // Catch: java.lang.Exception -> L88
        L2b:
            java.lang.String r3 = r13.readLine()     // Catch: java.lang.Exception -> L88
            if (r3 == 0) goto L7d
            r2.append(r3)     // Catch: java.lang.Exception -> L88
            r4 = 10
            r2.append(r4)     // Catch: java.lang.Exception -> L88
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L88
            r5 = 0
            if (r4 == 0) goto L42
            goto L6d
        L42:
            int r4 = r3.length()     // Catch: java.lang.Exception -> L88
            r7 = 100
            if (r4 >= r7) goto L51
            byte[] r3 = r3.getBytes(r0)     // Catch: java.lang.Exception -> L88
            int r3 = r3.length     // Catch: java.lang.Exception -> L88
            long r5 = (long) r3     // Catch: java.lang.Exception -> L88
            goto L6d
        L51:
            r7 = 0
        L52:
            if (r7 >= r4) goto L6d
            int r8 = r7 + 100
            if (r8 >= r4) goto L5a
            r14 = r8
            goto L5b
        L5a:
            r14 = r4
        L5b:
            java.lang.String r15 = new java.lang.String     // Catch: java.lang.Exception -> L88
            java.lang.String r7 = r3.substring(r7, r14)     // Catch: java.lang.Exception -> L88
            r15.<init>(r7)     // Catch: java.lang.Exception -> L88
            byte[] r7 = r15.getBytes(r0)     // Catch: java.lang.Exception -> L88
            int r7 = r7.length     // Catch: java.lang.Exception -> L88
            long r14 = (long) r7     // Catch: java.lang.Exception -> L88
            long r5 = r5 + r14
            r7 = r8
            goto L52
        L6d:
            long r11 = r11 + r5
            r8 = 0
            r3 = r18
            com.sdk.a.c r3 = (com.sdk.p285a.C6958c) r3     // Catch: java.lang.Exception -> L88
            r4 = r9
            r6 = r11
            boolean r3 = r3.m8213a(r4, r6, r8)     // Catch: java.lang.Exception -> L88
            if (r3 != 0) goto L2b
            r6 = r11
            goto L7e
        L7d:
            r6 = r11
        L7e:
            r8 = 1
            r3 = r18
            com.sdk.a.c r3 = (com.sdk.p285a.C6958c) r3     // Catch: java.lang.Exception -> L88
            r4 = r9
            r3.m8213a(r4, r6, r8)     // Catch: java.lang.Exception -> L88
            goto L97
        L88:
            r0 = move-exception
            goto L8c
        L8a:
            r0 = move-exception
            r2 = r1
        L8c:
            java.lang.String r0 = r0.getMessage()
            java.lang.Boolean r3 = com.sdk.p287c.C6976c.f18085a
            java.lang.String r4 = "StringDownloadHandler"
            com.sdk.base.framework.utils.log.LogUtils.m8186e(r4, r0, r3)
        L97:
            if (r2 != 0) goto L9a
            goto La2
        L9a:
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = r0.trim()
        La2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p287c.C6976c.m8182a(java.net.HttpURLConnection, com.sdk.c.b, java.lang.String):java.lang.String");
    }
}
