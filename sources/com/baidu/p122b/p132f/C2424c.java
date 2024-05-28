package com.baidu.p122b.p132f;

import java.io.Closeable;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.f.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2424c {
    /* JADX WARN: Removed duplicated region for block: B:40:0x003f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m20178a(java.io.File r5) {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2c
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2c
            r5 = 8192(0x2000, float:1.14794E-41)
            char[] r5 = new char[r5]     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            java.io.CharArrayWriter r2 = new java.io.CharArrayWriter     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            r2.<init>()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
        Lf:
            int r3 = r1.read(r5)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            if (r3 <= 0) goto L1a
            r4 = 0
            r2.write(r5, r4, r3)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            goto Lf
        L1a:
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            r1.close()     // Catch: java.lang.Exception -> L22
            goto L26
        L22:
            r0 = move-exception
            m20177a(r0)
        L26:
            return r5
        L27:
            r5 = move-exception
            goto L2e
        L29:
            r5 = move-exception
            r1 = r0
            goto L3d
        L2c:
            r5 = move-exception
            r1 = r0
        L2e:
            m20177a(r5)     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto L3b
            r1.close()     // Catch: java.lang.Exception -> L37
            goto L3b
        L37:
            r5 = move-exception
            m20177a(r5)
        L3b:
            return r0
        L3c:
            r5 = move-exception
        L3d:
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.lang.Exception -> L43
            goto L47
        L43:
            r0 = move-exception
            m20177a(r0)
        L47:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p122b.p132f.C2424c.m20178a(java.io.File):java.lang.String");
    }

    /* renamed from: a */
    public static void m20179a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                m20177a(e);
            }
        }
    }

    /* renamed from: a */
    public static void m20177a(Throwable th) {
    }
}
