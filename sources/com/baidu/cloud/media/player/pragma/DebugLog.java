package com.baidu.cloud.media.player.pragma;

import android.util.Log;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DebugLog {
    public static boolean ENABLE_DEBUG = false;
    public static boolean ENABLE_ERROR = true;
    public static boolean ENABLE_INFO = false;
    public static boolean ENABLE_VERBOSE = false;
    public static boolean ENABLE_WARN = true;

    public static void setDebugLogEnabled(boolean z) {
        if (z) {
            ENABLE_ERROR = true;
            ENABLE_WARN = true;
            ENABLE_INFO = true;
            ENABLE_DEBUG = true;
            ENABLE_VERBOSE = true;
            return;
        }
        ENABLE_ERROR = true;
        ENABLE_WARN = true;
        ENABLE_INFO = false;
        ENABLE_DEBUG = false;
        ENABLE_VERBOSE = false;
    }

    /* renamed from: e */
    public static void m20062e(String str, String str2) {
        if (ENABLE_ERROR) {
            Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m20061e(String str, String str2, Throwable th) {
        if (ENABLE_ERROR) {
            Log.e(str, str2, th);
        }
    }

    public static void efmt(String str, String str2, Object... objArr) {
        if (ENABLE_ERROR) {
            Log.e(str, String.format(Locale.US, str2, objArr));
        }
    }

    /* renamed from: i */
    public static void m20060i(String str, String str2) {
        if (ENABLE_INFO) {
            Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m20059i(String str, String str2, Throwable th) {
        if (ENABLE_INFO) {
            Log.i(str, str2, th);
        }
    }

    public static void ifmt(String str, String str2, Object... objArr) {
        if (ENABLE_INFO) {
            Log.i(str, String.format(Locale.US, str2, objArr));
        }
    }

    /* renamed from: w */
    public static void m20056w(String str, String str2) {
        if (ENABLE_WARN) {
            Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m20055w(String str, String str2, Throwable th) {
        if (ENABLE_WARN) {
            Log.w(str, str2, th);
        }
    }

    public static void wfmt(String str, String str2, Object... objArr) {
        if (ENABLE_WARN) {
            Log.w(str, String.format(Locale.US, str2, objArr));
        }
    }

    /* renamed from: d */
    public static void m20064d(String str, String str2) {
        if (ENABLE_DEBUG) {
            Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m20063d(String str, String str2, Throwable th) {
        if (ENABLE_DEBUG) {
            Log.d(str, str2, th);
        }
    }

    public static void dfmt(String str, String str2, Object... objArr) {
        if (ENABLE_DEBUG) {
            Log.d(str, String.format(Locale.US, str2, objArr));
        }
    }

    /* renamed from: v */
    public static void m20058v(String str, String str2) {
        if (ENABLE_VERBOSE) {
            Log.v(str, str2);
        }
    }

    /* renamed from: v */
    public static void m20057v(String str, String str2, Throwable th) {
        if (ENABLE_VERBOSE) {
            Log.v(str, str2, th);
        }
    }

    public static void vfmt(String str, String str2, Object... objArr) {
        if (ENABLE_VERBOSE) {
            Log.v(str, String.format(Locale.US, str2, objArr));
        }
    }

    public static void printStackTrace(Throwable th) {
        if (ENABLE_WARN) {
            th.printStackTrace();
        }
    }

    public static void printCause(Throwable th) {
        if (ENABLE_WARN) {
            Throwable cause = th.getCause();
            if (cause != null) {
                th = cause;
            }
            printStackTrace(th);
        }
    }
}
