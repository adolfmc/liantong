package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.os.Build;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RomUtils {
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    private static final String TAG = "Rom";
    private static String sName;
    private static String sVersion;

    public static boolean isEmui() {
        return check("EMUI");
    }

    public static boolean isMiui() {
        return check("MIUI");
    }

    public static boolean isVivo() {
        return check("VIVO");
    }

    public static boolean isOppo() {
        return check("OPPO");
    }

    public static boolean isFlyme() {
        return check("FLYME");
    }

    public static boolean is360() {
        return check("QIKU") || check("360");
    }

    public static boolean isSmartisan() {
        return check("SMARTISAN");
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static boolean check(String str) {
        String str2 = sName;
        if (str2 != null) {
            return str2.equals(str);
        }
        String prop = getProp("ro.miui.ui.version.name");
        sVersion = prop;
        if (!TextUtils.isEmpty(prop)) {
            sName = "MIUI";
        } else {
            String prop2 = getProp("ro.build.version.emui");
            sVersion = prop2;
            if (!TextUtils.isEmpty(prop2)) {
                sName = "EMUI";
            } else {
                String prop3 = getProp("ro.build.version.opporom");
                sVersion = prop3;
                if (!TextUtils.isEmpty(prop3)) {
                    sName = "OPPO";
                } else {
                    String prop4 = getProp("ro.vivo.os.version");
                    sVersion = prop4;
                    if (!TextUtils.isEmpty(prop4)) {
                        sName = "VIVO";
                    } else {
                        String prop5 = getProp("ro.smartisan.version");
                        sVersion = prop5;
                        if (!TextUtils.isEmpty(prop5)) {
                            sName = "SMARTISAN";
                        } else {
                            sVersion = Build.DISPLAY;
                            if (sVersion.toUpperCase().contains("FLYME")) {
                                sName = "FLYME";
                            } else {
                                sVersion = "unknown";
                                sName = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return sName.equals(str);
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0062: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:24:0x0062 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getProp(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.<init>()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.append(r6)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L61
            r2.close()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L61
            r2.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r6 = move-exception
            r6.printStackTrace()
        L39:
            return r1
        L3a:
            r1 = move-exception
            goto L40
        L3c:
            r6 = move-exception
            goto L63
        L3e:
            r1 = move-exception
            r2 = r0
        L40:
            java.lang.String r3 = "Rom"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61
            r4.<init>()     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "Unable to read prop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L61
            r4.append(r6)     // Catch: java.lang.Throwable -> L61
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L61
            android.util.Log.e(r3, r6, r1)     // Catch: java.lang.Throwable -> L61
            if (r2 == 0) goto L60
            r2.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r6 = move-exception
            r6.printStackTrace()
        L60:
            return r0
        L61:
            r6 = move-exception
            r0 = r2
        L63:
            if (r0 == 0) goto L6d
            r0.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r0 = move-exception
            r0.printStackTrace()
        L6d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.huotijiance.util.RomUtils.getProp(java.lang.String):java.lang.String");
    }
}
