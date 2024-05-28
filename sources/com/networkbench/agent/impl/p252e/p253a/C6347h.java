package com.networkbench.agent.impl.p252e.p253a;

import android.os.Build;
import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6347h {

    /* renamed from: a */
    private static final InterfaceC6393e f15967a = C6394f.m10150a();

    /* renamed from: a */
    public static boolean m10331a() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    /* renamed from: b */
    public static boolean m10329b() {
        return !TextUtils.isEmpty(m10330a("ro.miui.ui.version.name"));
    }

    /* renamed from: c */
    public static boolean m10328c() {
        String m10330a = m10330a("ro.build.display.id");
        if (TextUtils.isEmpty(m10330a)) {
            return false;
        }
        return m10330a.contains("flyme") || m10330a.toLowerCase(Locale.getDefault()).contains("flyme");
    }

    /* renamed from: d */
    public static boolean m10327d() {
        return Build.MANUFACTURER.contains("QiKU") || Build.MANUFACTURER.contains("360");
    }

    /* renamed from: e */
    public static int m10326e() {
        String m10330a = m10330a("ro.miui.ui.version.name");
        if (m10330a != null) {
            try {
                return Integer.parseInt(m10330a.substring(1));
            } catch (Exception unused) {
                InterfaceC6393e interfaceC6393e = f15967a;
                interfaceC6393e.mo10122a("get miui version code error, version : " + m10330a);
                return -1;
            }
        }
        return -1;
    }

    /* renamed from: f */
    public static double m10325f() {
        try {
            String m10330a = m10330a("ro.build.version.emui");
            return Double.parseDouble(m10330a.substring(m10330a.indexOf("_") + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return 4.0d;
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0065: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:19:0x0065 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m10330a(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            r2.<init>()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            r2.append(r5)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L40 java.lang.Throwable -> L64
            r2.close()     // Catch: java.io.IOException -> L40 java.lang.Throwable -> L64
            r2.close()     // Catch: java.io.IOException -> L35
            goto L3c
        L35:
            com.networkbench.agent.impl.f.e r5 = com.networkbench.agent.impl.p252e.p253a.C6347h.f15967a
            java.lang.String r0 = "Exception while closing InputStream"
            r5.mo10122a(r0)
        L3c:
            return r1
        L3d:
            r5 = move-exception
            goto L66
        L3f:
            r2 = r0
        L40:
            com.networkbench.agent.impl.f.e r1 = com.networkbench.agent.impl.p252e.p253a.C6347h.f15967a     // Catch: java.lang.Throwable -> L64
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L64
            r3.<init>()     // Catch: java.lang.Throwable -> L64
            java.lang.String r4 = "Unable to read sysprop "
            r3.append(r4)     // Catch: java.lang.Throwable -> L64
            r3.append(r5)     // Catch: java.lang.Throwable -> L64
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Throwable -> L64
            r1.mo10122a(r5)     // Catch: java.lang.Throwable -> L64
            if (r2 == 0) goto L63
            r2.close()     // Catch: java.io.IOException -> L5c
            goto L63
        L5c:
            com.networkbench.agent.impl.f.e r5 = com.networkbench.agent.impl.p252e.p253a.C6347h.f15967a
            java.lang.String r1 = "Exception while closing InputStream"
            r5.mo10122a(r1)
        L63:
            return r0
        L64:
            r5 = move-exception
            r0 = r2
        L66:
            if (r0 == 0) goto L73
            r0.close()     // Catch: java.io.IOException -> L6c
            goto L73
        L6c:
            com.networkbench.agent.impl.f.e r0 = com.networkbench.agent.impl.p252e.p253a.C6347h.f15967a
            java.lang.String r1 = "Exception while closing InputStream"
            r0.mo10122a(r1)
        L73:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.p252e.p253a.C6347h.m10330a(java.lang.String):java.lang.String");
    }
}
