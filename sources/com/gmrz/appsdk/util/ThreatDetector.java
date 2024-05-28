package com.gmrz.appsdk.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ThreatDetector {
    private static boolean checkRootMethod1() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        Process process;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            try {
                boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
                if (process != null) {
                    process.destroy();
                }
                return z;
            } catch (Throwable unused) {
                if (process != null) {
                    process.destroy();
                }
                return false;
            }
        } catch (Throwable unused2) {
            process = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d A[Catch: Exception -> 0x0076, TryCatch #0 {Exception -> 0x0076, blocks: (B:3:0x0001, B:4:0x002a, B:6:0x0031, B:8:0x0039, B:10:0x0041, B:11:0x0050, B:12:0x0057, B:14:0x005d, B:17:0x006c), top: B:24:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean findHookAppFile() {
        /*
            r0 = 0
            java.util.HashSet r1 = new java.util.HashSet     // Catch: java.lang.Exception -> L76
            r1.<init>()     // Catch: java.lang.Exception -> L76
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L76
            r2.<init>()     // Catch: java.lang.Exception -> L76
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch: java.lang.Exception -> L76
            int r3 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L76
            r2.append(r3)     // Catch: java.lang.Exception -> L76
            java.lang.String r3 = "/maps"
            r2.append(r3)     // Catch: java.lang.Exception -> L76
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L76
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L76
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Exception -> L76
            r4.<init>(r2)     // Catch: java.lang.Exception -> L76
            r3.<init>(r4)     // Catch: java.lang.Exception -> L76
        L2a:
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Exception -> L76
            r4 = 1
            if (r2 == 0) goto L50
            java.lang.String r5 = ".so"
            boolean r5 = r2.endsWith(r5)     // Catch: java.lang.Exception -> L76
            if (r5 != 0) goto L41
            java.lang.String r5 = ".jar"
            boolean r5 = r2.endsWith(r5)     // Catch: java.lang.Exception -> L76
            if (r5 == 0) goto L2a
        L41:
            java.lang.String r5 = " "
            int r5 = r2.lastIndexOf(r5)     // Catch: java.lang.Exception -> L76
            int r5 = r5 + r4
            java.lang.String r2 = r2.substring(r5)     // Catch: java.lang.Exception -> L76
            r1.add(r2)     // Catch: java.lang.Exception -> L76
            goto L2a
        L50:
            r3.close()     // Catch: java.lang.Exception -> L76
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L76
        L57:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Exception -> L76
            if (r2 == 0) goto L75
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Exception -> L76
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Exception -> L76
            java.lang.String r3 = "com.saurik.substrate"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Exception -> L76
            if (r3 == 0) goto L6c
            return r4
        L6c:
            java.lang.String r3 = "XposedBridge.jar"
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Exception -> L76
            if (r2 == 0) goto L57
            return r4
        L75:
            return r0
        L76:
            r1 = move-exception
            r1.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.util.ThreatDetector.findHookAppFile():boolean");
    }

    private static boolean findHookAppName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            try {
                packageManager.getApplicationInfo("de.robv.android.xposed.installer", 128);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                packageManager.getApplicationInfo("com.saurik.substrate", 128);
                return true;
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            return false;
        }
    }

    private static boolean findHookStack() {
        StackTraceElement[] stackTrace;
        try {
            throw new Exception("findhook");
        } catch (Exception e) {
            int i = 0;
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i = i + 1) == 2) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    public static boolean isHook(Context context) {
        return findHookAppName(context) || findHookAppFile() || findHookStack();
    }
}
