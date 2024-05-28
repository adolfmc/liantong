package com.sdk.p296l;

import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;
import com.sdk.p302r.C7037a;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.l.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7011a {

    /* renamed from: a */
    public static final String f18161a = "com.sdk.l.a";

    /* renamed from: b */
    public static final Boolean f18162b = Boolean.valueOf(C6998d.f18135a);

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c A[Catch: all -> 0x0057, TryCatch #1 {all -> 0x0057, blocks: (B:5:0x000e, B:8:0x0014, B:11:0x001f, B:13:0x002c, B:18:0x0039, B:20:0x004f, B:19:0x003d, B:21:0x0052, B:10:0x0019), top: B:31:0x000e }] */
    @android.annotation.SuppressLint({"DefaultLocale", "NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m8151a(android.content.Context r7) {
        /*
            java.lang.String r0 = ""
            java.lang.Boolean r1 = com.sdk.p302r.C7037a.m8130a(r0)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L63
            if (r7 == 0) goto L57
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L57
            r2 = 17
            if (r1 < r2) goto L19
            java.lang.String r7 = android.webkit.WebSettings.getDefaultUserAgent(r7)     // Catch: java.lang.Exception -> L19 java.lang.Throwable -> L57
            goto L1f
        L19:
            java.lang.String r7 = "http.agent"
            java.lang.String r7 = java.lang.System.getProperty(r7)     // Catch: java.lang.Throwable -> L57
        L1f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57
            r1.<init>()     // Catch: java.lang.Throwable -> L57
            int r2 = r7.length()     // Catch: java.lang.Throwable -> L57
            r3 = 0
            r4 = r3
        L2a:
            if (r4 >= r2) goto L52
            char r5 = r7.charAt(r4)     // Catch: java.lang.Throwable -> L57
            r6 = 31
            if (r5 <= r6) goto L3d
            r6 = 127(0x7f, float:1.78E-43)
            if (r5 < r6) goto L39
            goto L3d
        L39:
            r1.append(r5)     // Catch: java.lang.Throwable -> L57
            goto L4f
        L3d:
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L57
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L57
            r6[r3] = r5     // Catch: java.lang.Throwable -> L57
            java.lang.String r5 = "\\u%04x"
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch: java.lang.Throwable -> L57
            r1.append(r5)     // Catch: java.lang.Throwable -> L57
        L4f:
            int r4 = r4 + 1
            goto L2a
        L52:
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Throwable -> L57
            r0 = r7
        L57:
            java.lang.Boolean r7 = com.sdk.p302r.C7037a.m8130a(r0)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L63
            java.lang.String r0 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1"
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p296l.C7011a.m8151a(android.content.Context):java.lang.String");
    }

    /* renamed from: a */
    public static String m8149a(HttpURLConnection httpURLConnection) {
        try {
            String headerField = httpURLConnection.getHeaderField("Content-Disposition");
            if (C7037a.m8129b(headerField).booleanValue()) {
                String str = new String(headerField.getBytes("ISO-8859-1"), "GBK");
                if (C7037a.m8129b(str).booleanValue()) {
                    return str.substring(str.indexOf(34) + 1, str.lastIndexOf("\""));
                }
            }
        } catch (Throwable th) {
            LogUtils.m8186e(f18161a, th.getMessage(), f18162b);
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m8150a(String str) {
        String[] split;
        try {
            for (String str2 : new URL(str).getHost().split("\\.")) {
                for (int i = 0; i < str2.length(); i++) {
                    if (!Character.isDigit(str2.charAt(i))) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m8148b(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Accept-Ranges");
        if (C7037a.m8129b(headerField).booleanValue()) {
            return "bytes".equals(headerField);
        }
        if (C7037a.m8129b(httpURLConnection.getHeaderField("Content-Range")).booleanValue()) {
            return headerField.startsWith("bytes");
        }
        return false;
    }
}
