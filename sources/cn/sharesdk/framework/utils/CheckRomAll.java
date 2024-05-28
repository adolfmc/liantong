package cn.sharesdk.framework.utils;

import android.os.Build;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CheckRomAll {

    /* renamed from: a */
    private static String f2952a;

    /* renamed from: b */
    private static String f2953b;

    /* renamed from: a */
    public static boolean m21711a() {
        return m21710a("MIUI");
    }

    /* renamed from: a */
    public static boolean m21710a(String str) {
        String str2 = f2952a;
        if (str2 != null) {
            return str2.equals(str);
        }
        String m21709b = m21709b("ro.miui.ui.version.name");
        f2953b = m21709b;
        if (!TextUtils.isEmpty(m21709b)) {
            f2952a = "MIUI";
        } else {
            String m21709b2 = m21709b("ro.build.version.emui");
            f2953b = m21709b2;
            if (!TextUtils.isEmpty(m21709b2)) {
                f2952a = "EMUI";
            } else {
                String m21709b3 = m21709b("ro.build.version.opporom");
                f2953b = m21709b3;
                if (!TextUtils.isEmpty(m21709b3)) {
                    f2952a = "OPPO";
                } else {
                    String m21709b4 = m21709b("ro.vivo.os.version");
                    f2953b = m21709b4;
                    if (!TextUtils.isEmpty(m21709b4)) {
                        f2952a = "VIVO";
                    } else {
                        String m21709b5 = m21709b("ro.smartisan.version");
                        f2953b = m21709b5;
                        if (!TextUtils.isEmpty(m21709b5)) {
                            f2952a = "SMARTISAN";
                        } else {
                            f2953b = Build.DISPLAY;
                            if (f2953b.toUpperCase().contains("FLYME")) {
                                f2952a = "FLYME";
                            } else {
                                f2953b = "unknown";
                                f2952a = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return f2952a.equals(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m21709b(java.lang.String r7) {
        /*
            r0 = 0
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            r3.<init>()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.lang.String r4 = "getprop "
            r3.append(r4)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            r3.append(r7)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.io.InputStream r2 = r2.getInputStream()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            r2 = 1024(0x400, float:1.435E-42)
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.lang.String r2 = r3.readLine()     // Catch: java.io.IOException -> L52 java.lang.Throwable -> L9d
            r3.close()     // Catch: java.io.IOException -> L52 java.lang.Throwable -> L9d
            r3.close()     // Catch: java.io.IOException -> L36
            goto L51
        L36:
            r7 = move-exception
            cn.sharesdk.framework.utils.SSDKLog r0 = cn.sharesdk.framework.utils.SSDKLog.m21740b()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "CheckRomAll getProp finally catch "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r0.m21744a(r7, r1)
        L51:
            return r2
        L52:
            r2 = move-exception
            goto L59
        L54:
            r7 = move-exception
            r3 = r0
            goto L9e
        L57:
            r2 = move-exception
            r3 = r0
        L59:
            cn.sharesdk.framework.utils.SSDKLog r4 = cn.sharesdk.framework.utils.SSDKLog.m21740b()     // Catch: java.lang.Throwable -> L9d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9d
            r5.<init>()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r6 = "CheckRomAll unable to read prop "
            r5.append(r6)     // Catch: java.lang.Throwable -> L9d
            r5.append(r7)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r7 = " ex "
            r5.append(r7)     // Catch: java.lang.Throwable -> L9d
            r5.append(r2)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r7 = r5.toString()     // Catch: java.lang.Throwable -> L9d
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L9d
            r4.m21744a(r7, r2)     // Catch: java.lang.Throwable -> L9d
            if (r3 == 0) goto L9c
            r3.close()     // Catch: java.io.IOException -> L81
            goto L9c
        L81:
            r7 = move-exception
            cn.sharesdk.framework.utils.SSDKLog r2 = cn.sharesdk.framework.utils.SSDKLog.m21740b()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "CheckRomAll getProp finally catch "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2.m21744a(r7, r1)
        L9c:
            return r0
        L9d:
            r7 = move-exception
        L9e:
            if (r3 == 0) goto Lbf
            r3.close()     // Catch: java.io.IOException -> La4
            goto Lbf
        La4:
            r0 = move-exception
            cn.sharesdk.framework.utils.SSDKLog r2 = cn.sharesdk.framework.utils.SSDKLog.m21740b()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "CheckRomAll getProp finally catch "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2.m21744a(r0, r1)
        Lbf:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.utils.CheckRomAll.m21709b(java.lang.String):java.lang.String");
    }
}
