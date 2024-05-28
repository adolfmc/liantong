package com.bytedance.pangle.util.p183a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3939a {
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b6  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] m16656a(java.io.File r12) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            r2 = 1
            r3 = 0
            r4 = 0
            java.util.zip.ZipFile r5 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La5
            r5.<init>(r12)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La5
            java.util.Enumeration r12 = r5.entries()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r4.<init>()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r6 = r3
            r7 = r6
            r8 = r7
        L18:
            boolean r9 = r12.hasMoreElements()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r9 == 0) goto L65
            java.lang.Object r9 = r12.nextElement()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.util.zip.ZipEntry r9 = (java.util.zip.ZipEntry) r9     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r10 = r9.getName()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r11 = "META-INF/"
            boolean r10 = r10.startsWith(r11)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r10 == 0) goto L18
            java.lang.String r10 = r9.getName()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r11 = "MANIFEST.MF"
            boolean r10 = r10.endsWith(r11)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r10 == 0) goto L3e
            r8 = r2
            goto L59
        L3e:
            java.lang.String r10 = r9.getName()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r11 = ".SF"
            boolean r10 = r10.endsWith(r11)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r10 == 0) goto L4c
            r6 = r2
            goto L59
        L4c:
            java.lang.String r10 = r9.getName()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r11 = ".RSA"
            boolean r10 = r10.endsWith(r11)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r10 == 0) goto L59
            r7 = r2
        L59:
            long r9 = r9.getCrc()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r4.add(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            goto L18
        L65:
            java.util.Comparator r12 = java.util.Collections.reverseOrder()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.util.Collections.sort(r4, r12)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r12.<init>()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
        L75:
            boolean r9 = r4.hasNext()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r9 == 0) goto L85
            java.lang.Object r9 = r4.next()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.Long r9 = (java.lang.Long) r9     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r12.append(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            goto L75
        L85:
            if (r8 == 0) goto L94
            if (r6 == 0) goto L94
            if (r7 == 0) goto L94
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r0 = com.bytedance.pangle.util.C3946f.m16640a(r12)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            goto L97
        L94:
            java.lang.String r1 = "without v1 signature."
        L97:
            com.bytedance.pangle.util.p183a.C3941c.m16649a(r5)
            goto La8
        L9b:
            r12 = move-exception
            goto La1
        L9d:
            r4 = r5
            goto La5
        L9f:
            r12 = move-exception
            r5 = r4
        La1:
            com.bytedance.pangle.util.p183a.C3941c.m16649a(r5)
            throw r12
        La5:
            com.bytedance.pangle.util.p183a.C3941c.m16649a(r4)
        La8:
            r12 = 3
            java.lang.String[] r12 = new java.lang.String[r12]
            r12[r3] = r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Lb6
            java.lang.String r0 = ""
            goto Lb8
        Lb6:
            java.lang.String r0 = "V1"
        Lb8:
            r12[r2] = r0
            r0 = 2
            r12[r0] = r1
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.util.p183a.C3939a.m16656a(java.io.File):java.lang.String[]");
    }
}
