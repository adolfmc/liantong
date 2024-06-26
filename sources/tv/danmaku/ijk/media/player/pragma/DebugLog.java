package tv.danmaku.ijk.media.player.pragma;

import android.util.Log;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DebugLog {
    public static final boolean ENABLE_DEBUG = true;
    public static final boolean ENABLE_ERROR = true;
    public static final boolean ENABLE_INFO = true;
    public static final boolean ENABLE_VERBOSE = true;
    public static final boolean ENABLE_WARN = true;

    /* renamed from: e */
    public static void m87e(String str, String str2) {
        Log.e(str, str2);
    }

    /* renamed from: e */
    public static void m86e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public static void efmt(String str, String str2, Object... objArr) {
        Log.e(str, String.format(Locale.US, str2, objArr));
    }

    /* renamed from: i */
    public static void m85i(String str, String str2) {
        Log.i(str, str2);
    }

    /* renamed from: i */
    public static void m84i(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    public static void ifmt(String str, String str2, Object... objArr) {
        Log.i(str, String.format(Locale.US, str2, objArr));
    }

    /* renamed from: w */
    public static void m81w(String str, String str2) {
        Log.w(str, str2);
    }

    /* renamed from: w */
    public static void m80w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    public static void wfmt(String str, String str2, Object... objArr) {
        Log.w(str, String.format(Locale.US, str2, objArr));
    }

    /* renamed from: d */
    public static void m89d(String str, String str2) {
        Log.d(str, str2);
    }

    /* renamed from: d */
    public static void m88d(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    public static void dfmt(String str, String str2, Object... objArr) {
        Log.d(str, String.format(Locale.US, str2, objArr));
    }

    /* renamed from: v */
    public static void m83v(String str, String str2) {
        Log.v(str, str2);
    }

    /* renamed from: v */
    public static void m82v(String str, String str2, Throwable th) {
        Log.v(str, str2, th);
    }

    public static void vfmt(String str, String str2, Object... objArr) {
        Log.v(str, String.format(Locale.US, str2, objArr));
    }

    public static void printStackTrace(Throwable th) {
        th.printStackTrace();
    }

    public static void printCause(Throwable th) {
        Throwable cause = th.getCause();
        if (cause != null) {
            th = cause;
        }
        printStackTrace(th);
    }
}
