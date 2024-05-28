package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.nostra13.universalimageloader.utils.L */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6846L {
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    private static volatile boolean writeDebugLogs = false;
    private static volatile boolean writeLogs = true;

    private C6846L() {
    }

    @Deprecated
    public static void enableLogging() {
        writeLogs(true);
    }

    @Deprecated
    public static void disableLogging() {
        writeLogs(false);
    }

    public static void writeDebugLogs(boolean z) {
        writeDebugLogs = z;
    }

    public static void writeLogs(boolean z) {
        writeLogs = z;
    }

    /* renamed from: d */
    public static void m8370d(String str, Object... objArr) {
        if (writeDebugLogs) {
            log(3, null, str, objArr);
        }
    }

    /* renamed from: i */
    public static void m8366i(String str, Object... objArr) {
        log(4, null, str, objArr);
    }

    /* renamed from: w */
    public static void m8365w(String str, Object... objArr) {
        log(5, null, str, objArr);
    }

    /* renamed from: e */
    public static void m8368e(Throwable th) {
        log(6, th, null, new Object[0]);
    }

    /* renamed from: e */
    public static void m8369e(String str, Object... objArr) {
        log(6, null, str, objArr);
    }

    /* renamed from: e */
    public static void m8367e(Throwable th, String str, Object... objArr) {
        log(6, th, str, objArr);
    }

    private static void log(int i, Throwable th, String str, Object... objArr) {
        if (writeLogs) {
            if (objArr.length > 0) {
                str = String.format(str, objArr);
            }
            if (th != null) {
                if (str == null) {
                    str = th.getMessage();
                }
                str = String.format("%1$s\n%2$s", str, Log.getStackTraceString(th));
            }
            Log.println(i, ImageLoader.TAG, str);
        }
    }
}
